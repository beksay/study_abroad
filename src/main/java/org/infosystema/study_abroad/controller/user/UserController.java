package org.infosystema.study_abroad.controller.user;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.service.UserService;
import org.infosystema.study_abroad.util.web.LoginUtil;

/***
 * 
 * @author Akzholbek Omorov
 * 
 */
@Named
@RequestScoped
public class UserController {

	@EJB	private UserService userService;
	@Inject private LoginUtil loginUtil;

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
    
    public String logout() {	
		loginUtil.logout();
		return "/view/user/login.xhtml";
	}
    
    public LoginUtil getLoginUtil() {
		return loginUtil;
	}
    
    public String home() {
		return "/view/main.xhtml";
	}

}
