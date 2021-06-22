package org.infosystema.study_abroad.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.infosystema.study_abroad.util.Configuration;
 
/**
 * 
 * @author Akzholbek Omorov
 *
 */

@WebServlet(urlPatterns = {"/esia-login"})
public class EsiaLoginServlet extends HttpServlet {

	private static final long serialVersionUID = -5856275545635536383L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		try {
			Configuration configuration = Configuration.getInstance();
			OAuthClientRequest authRequest = OAuthClientRequest
					   .authorizationLocation(configuration.getProperty("esiaUrl")+"connect/authorize")
					   .setClientId(configuration.getProperty("esiaClientId"))
					   .setScope("openid profile signature_api")
					   //.setParameter("code_challenge", generateCodeChallange("test"))
					   //.setParameter("code_challenge_method", "S256")
					   .setResponseType(ResponseType.CODE.toString())
					   .setRedirectURI(configuration.getProperty("url")+"esia-auth")
					   .buildQueryMessage();
			
			System.out.println("------------------------------------------------------------");
			System.out.println("authRequest.getHeaders() = " + authRequest.getHeaders());
			System.out.println("AUTH_REQUEST_URL = " + configuration.getProperty("esiaUrl")+"connect/authorize");
			System.out.println("CLIENT_ID = " +configuration.getProperty("esiaClientId"));
			System.out.println("redirectUrl = " + configuration.getProperty("url")+"/esia-auth");
			System.out.println("------------------------------------------------------------");
			
			String type = req.getParameter("type");
			if(type != null) req.getSession().setAttribute("type", type);
			
			String cid = req.getParameter("cid");
			if(cid != null) req.getSession().setAttribute("cid", cid);
			System.out.println(authRequest.getLocationUri());
			resp.sendRedirect(authRequest.getLocationUri());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	String generateCodeChallange(String codeVerifier) throws UnsupportedEncodingException, NoSuchAlgorithmException {
	    byte[] bytes = codeVerifier.getBytes("US-ASCII");
	    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
	    messageDigest.update(bytes, 0, bytes.length);
	    byte[] digest = messageDigest.digest();
	    return Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
	}
	
}
