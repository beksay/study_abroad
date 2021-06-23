package org.infosystema.study_abroad.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.enums.UserStatus;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.service.UserService;
import org.infosystema.study_abroad.util.Configuration;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private String email, password;
    @Inject
    private LoginUtil loginUtil;
    @EJB
    private UserService userService;

    @PostConstruct
    private void init() {

    }
    
    public String login() throws Exception{
    	if( email.equals("") ) {
    		return null;
		} else if ( password.equals("") ) {
			return null;
		}
		
    	System.out.println("login:" + email);
    	
		String hashPassword = loginUtil.getHashPassword(password);
    	
    	System.out.println("password:" + password + " hash = " + hashPassword);
    	
    	List<User> users = userService.findByProperty("email", email);
    	if(users.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameIsIncorrect"), null) );
			return null;
    	}
    	
		List<FilterExample> examples = new ArrayList<FilterExample>();
		examples.add(new FilterExample("email", email, InequalityConstants.EQUAL, true));	
		examples.add(new FilterExample("password", hashPassword, InequalityConstants.EQUAL));
		
		List<User> userList = userService.findByExample(0, 1, SortEnum.ASCENDING, examples, "id");
		System.out.println("userList: " + userList);
		
		if(userList.isEmpty()){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameOrPasswordIncorrect"), null) );
			return null;
		}
			
		User user = userList.get(0);
		
		if(user.getStatus() == null || user.getStatus().equals(UserStatus.INACTIVE) || user.getStatus().equals(UserStatus.BLOCKED)){
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("userIsNotActive"), null) );
			FacesContext.getCurrentInstance().getExternalContext().redirect("/study_abroad/view/user/blocked.xhtml");
			
			return null;
		}
		
		loginUtil.setCurrentUser(user);
		
		String address = loginUtil.userHasRole(user, "admin") ? "/" + Configuration.getInstance().getProperty("projectName") 
				+ "/view/main.xhtml" : "/" + Configuration.getInstance().getProperty("projectName") + "/view/main.xhtml";
		userService.merge(user);
		
    	FacesContext.getCurrentInstance().getExternalContext().redirect(address);
		return address;
    }

    public String getEmail() {
		return email;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
