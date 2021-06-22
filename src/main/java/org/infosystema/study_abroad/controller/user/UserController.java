package org.infosystema.study_abroad.controller.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.dto.rest.OnoiBeanRest;
import org.infosystema.study_abroad.enums.AuthType;
import org.infosystema.study_abroad.enums.LogStatus;
import org.infosystema.study_abroad.enums.ScopeConstants;
import org.infosystema.study_abroad.enums.UserStatus;
import org.infosystema.study_abroad.model.LoginLog;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.service.LoginLogService;
import org.infosystema.study_abroad.service.UserService;
import org.infosystema.study_abroad.util.Configuration;
import org.infosystema.study_abroad.util.web.FacesScopeQualifier;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

/***
 * 
 * @author Akzholbek Omorov
 * 
 */
@Named
@RequestScoped
public class UserController {

	@EJB	private UserService userService;
	@EJB    private LoginLogService loginLogService;
	@Inject private LoginUtil loginUtil;
	
    private String username;
    private String password;
    public static final String CURRENT_USER_SESSION_KEY = "org.infosystema.study_abroad.current_user_session_key";
    
    public void register() {
    	
    }
   
    public boolean contain(ArrayList<Long> roleIdsList) {
    	if (loginUtil == null || loginUtil.getCurrentUser() == null || loginUtil.getCurrentUser().getRole() == null) return false;
		for (Long i : roleIdsList) {
			if (i.intValue() == loginUtil.getCurrentUser().getRole().getId().intValue()) return true;		
		}    	
    	return false;
	}
    
    public boolean contain(Integer roleId) {
    	if (loginUtil == null || loginUtil.getCurrentUser() == null || loginUtil.getCurrentUser().getRole() == null) return false;
		return roleId.equals(loginUtil.getCurrentUser().getRole().getId());
	}
    
    public String cancel() throws IOException{
    	FacesContext.getCurrentInstance().getExternalContext().redirect("/study_abroad/view/main.xhtml");
		return "";
    }
    
    public String login() throws Exception{
    	if( username.equals("") ) {
    		return null;
		} else if ( password.equals("") ) {
			return null;
		}
		
    	List<FilterExample> examples = new ArrayList<FilterExample>();
		examples.add(new FilterExample("username", username, InequalityConstants.EQUAL, true));	
		examples.add(new FilterExample("password", password, InequalityConstants.EQUAL));
		List<User> userList = userService.findByExample(0, 1, SortEnum.ASCENDING, examples, "id");
		if(userList.isEmpty()){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameOrPasswordIncorrect"), null) );
			return null;
		}
			
		User user = userList.get(0);
		user=userService.findByProperty("id", user.getId()).get(0);
		
		
		if(user.getStatus() == null || user.getStatus().equals(UserStatus.INACTIVE)){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsNotActive"), null) );
			FacesContext.getCurrentInstance().getExternalContext().redirect("blocked.xhtml");
			
			return null;
		}
		if(user.getStatus() == null || user.getStatus().equals(UserStatus.BLOCKED)){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsBlocked"), null) );
			return null;
		}
		
		loginUtil.setCurrentUser(user);	
		
		new FacesScopeQualifier().setValue("org.infosystema.current_username", user.getUsername(), ScopeConstants.SESSION_SCOPE);
		FacesContext.getCurrentInstance().getExternalContext().redirect("/study_abroad/view/main.xhtml");
		
		return "";
	
    }
    
    public String logout() {
    	
//    	LoginLog log = new LoginLog();
//		log.setDateCreated(new Date());
//		log.setStatus(LogStatus.OUT);
//		log.setUser(loginUtil.getCurrentUser());
//
//		loginLogService.persist(log);
    	
		loginUtil.logout();
		return "/view/home.xhtml";
	}
    
    public void logoutFromDsSession(){
    	System.out.println("logoutFromDsSession = " + loginUtil.getAuthType().name());
    	if (loginUtil.getAuthType() != null && loginUtil.getAuthType().equals(AuthType.ONOI)) {
	    	if(loginUtil.getSessionId() == null) return;
	    	
	    	Configuration configuration = Configuration.getInstance();
	    	String url = "https://smartidkg.onoi.kg/api/Session/CloseSession/" + loginUtil.getSessionId();
	        Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(url);
			webTarget.request(MediaType.APPLICATION_JSON).header("apiKey", configuration.getProperty("onoiBackEndApiKey")).post(Entity.entity(new OnoiBeanRest(), MediaType.APPLICATION_JSON));
    	}
    }
    
    public LoginUtil getLoginUtil() {
		return loginUtil;
	}
    
    public String settings() {
		return "/view/user/settings.xhtml";
	}
    
    public String home() {
		return "/view/main.xhtml";
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	

}
