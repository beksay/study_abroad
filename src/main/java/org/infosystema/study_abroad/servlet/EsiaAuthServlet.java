package org.infosystema.study_abroad.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.infosystema.study_abroad.dto.EsiaUserInfoDto;
import org.infosystema.study_abroad.util.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

 
/**
 * 
 * @author Akzholbek Omorov
 *
 */

@WebServlet(urlPatterns = {"/esia-auth"})
public class EsiaAuthServlet extends HttpServlet {

	private static final long serialVersionUID = -5856275545635536383L;

	private Configuration configuration = Configuration.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("SSSSSSstart");
		System.out.println(req.getParameter("code"));
		System.out.println(req.getParameter("state")); 
		
		if(req.getParameter("code")==null) return;
		
		
		String accessToken=getTokenRequest(req.getParameter("code"));
		
		JSONObject payload = new JSONObject(testDecodeJWT(accessToken));
		JSONArray array=payload.getJSONArray("amr");
		String authType= array.getString(0);
		System.out.println("auth-Type="+authType);
		
		
		String userInfo=getUserInfo(accessToken);
		System.out.println(userInfo);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		EsiaUserInfoDto dto=new EsiaUserInfoDto();
		JSONObject json = new JSONObject(userInfo);
		try {
			dto.setBirthdate(sdf.parse(json.getString("birthdate")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		dto.setSub(json.getString("sub"));
		if (json.has("organization_tin")) {
			dto.setOrganizationInn(json.getString("organization_tin"));
			dto.setOrganization(true);
		}
		if (json.has("position_name"))
			dto.setPositionName(json.getString("position_name"));
		if (json.has("pin"))
			dto.setPersonInn(json.getString("pin"));
		if (json.has("citizenship"))
			dto.setCitizenship(json.getString("citizenship"));
		if (json.has("family_name"))
			dto.setFamilyName(json.getString("family_name"));
		if (json.has("given_name"))
			dto.setGivenName(json.getString("given_name"));
		if (json.has("middle_name"))
			dto.setMiddleName(json.getString("middle_name"));
		if (json.has("name"))
			dto.setName(json.getString("name"));
		if (json.has("gender"))
			dto.setGender(json.getString("gender"));
		if (json.has("email"))
			dto.setEmail(json.getString("email"));
		if (json.has("email_verified"))
			dto.setEmailVerified(json.getBoolean("email_verified"));
		if (json.has("phone_number"))
			dto.setPhoneNomber(json.getString("phone_number"));
		if (json.has("phone_number_verified"))
			dto.setPhoneNomberVerified(json.getBoolean("phone_number_verified"));
		if(authType!=null) {
			dto.setAuthType(authType);
		}
		
		System.out.println("name=="+dto.getName());
		System.out.println("family_name=="+dto.getFamilyName());
		System.out.println("given_name=="+dto.getGivenName());
		System.out.println("middle_name=="+dto.getMiddleName());
		
		
		dto.setAccessToken(accessToken);
		
		req.setAttribute("userInfoDto", dto);
		
		
		
		ServletContext servletContext = getServletContext();
	    RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/view/user/register_esia.xhtml");
	    requestDispatcher.forward(req, resp);		
	}
	
	 
	private String getTokenRequest(String code) throws IOException {
		
		OkHttpClient client = new OkHttpClient();
				MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
				RequestBody body = RequestBody.create(mediaType, "code="+code+"&grant_type=authorization_code&redirect_uri="+
						configuration.getProperty("url")+"esia-auth");
				Request request = new Request.Builder()
				  .url(configuration.getProperty("esiaUrl")+"connect/token")
				  .method("POST", body)
				  .addHeader("Authorization", "Basic "+configuration.getProperty("esiaBasic"))
				  .addHeader("Content-Type", "application/x-www-form-urlencoded")
				  .addHeader("Cookie", ".AspNetCore.Culture=c%3Dky%7Cuic%3Dky")
				  .build();
				
				System.out.println("request="+request);
				Response response = client.newCall(request).execute();
				
				
				System.out.println(response.body().toString());
				String jsonData = response.body().string();
				System.out.println(jsonData);
				JSONObject Jobject = new JSONObject(jsonData);
				String accessToken = Jobject.getString("access_token");
				System.out.println("access_token="+accessToken);
				
				return accessToken;
	}
	private String getUserInfo(String accessToken) throws IOException {
			OkHttpClient client = new OkHttpClient();
				Request request = new Request.Builder()
				  .url(configuration.getProperty("esiaUrl")+"connect/userinfo")
				  .method("GET", null)
				  .addHeader("Authorization", "Bearer "+accessToken)
				  .build();
				Response response = client.newCall(request).execute();
				String jsonData = response.body().string();
				return jsonData;
	}
	
	
	 public String testDecodeJWT(String token){
	        String jwtToken = token;
	        System.out.println("------------ Decode JWT ------------");
	        String[] split_string = jwtToken.split("\\.");
	        String base64EncodedHeader = split_string[0];
	        String base64EncodedBody = split_string[1];

	        System.out.println("~~~~~~~~~ JWT Header ~~~~~~~");
	        String header = new String(Base64.getDecoder().decode(base64EncodedHeader));
	        System.out.println("JWT Header : " + header);


	        System.out.println("~~~~~~~~~ JWT Body ~~~~~~~");
	        String body = new String(Base64.getDecoder().decode(base64EncodedBody));
	        System.out.println("JWT Body : "+body);    
	        return body;
	    }

	
	
	
	String generateCodeChallange(String codeVerifier) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	    byte[] bytes = codeVerifier.getBytes("US-ASCII");
	    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	    messageDigest.update(bytes, 0, bytes.length);
	    byte[] digest = messageDigest.digest();
	    return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
	}
	
}
