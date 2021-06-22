package org.infosystema.iselect.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.beans.SortEnum;
import org.infosystema.iselect.enums.AuthType;
import org.infosystema.iselect.enums.LogStatus;
import org.infosystema.iselect.enums.UserStatus;
import org.infosystema.iselect.model.LoginLog;
import org.infosystema.iselect.model.Role;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.service.LoginLogService;
import org.infosystema.iselect.service.UserService;
import org.infosystema.iselect.util.Configuration;
import org.infosystema.iselect.util.web.LoginUtil;
import org.infosystema.iselect.util.web.Messages;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
	private String username, password;
    private String random,signedData;

    private String sessionId;
    //cloud
    private String pinCode;

    @Inject
    private LoginUtil loginUtil;
    @Inject
    private FacesContext facesContext;

    @EJB
    private UserService userService;
    @EJB
    private LoginLogService loginLogService;

    private AuthType authType;
    private List<User> users;
    private User user;

    @PostConstruct
    private void init() {
        random = new Date().toString() + " " + this.toString();
        random = random.trim();
    }
    
    public String login() throws Exception{
    	if( username.equals("") ) {
    		return null;
		} else if ( password.equals("") ) {
			return null;
		}
		
    	System.out.println("login:" + username);
    	
		String hashPassword = loginUtil.getHashPassword(password);
    	
    	System.out.println("password:" + password + " hash = " + hashPassword);
    	
    	List<User> users = userService.findByProperty("username", username);
    	if(users.isEmpty()){
    		FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("usernameIsIncorrect"), null) );
			return null;
    	}
    	
		List<FilterExample> examples = new ArrayList<FilterExample>();
		examples.add(new FilterExample("username", username, InequalityConstants.EQUAL, true));	
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
			FacesContext.getCurrentInstance().getExternalContext().redirect("/iselect/view/user/blocked.xhtml");
			
			return null;
		}
		
		loginUtil.setCurrentUser(user);
		
		String address = loginUtil.userHasRole(user, "admin") ? "/" + Configuration.getInstance().getProperty("projectName") 
				+ "/view/main.xhtml" : "/" + Configuration.getInstance().getProperty("projectName") + "/view/main.xhtml";
		userService.merge(user);
		
    	FacesContext.getCurrentInstance().getExternalContext().redirect(address);
		return address;
    }
    
    public String loginUser() {
		Optional<User> optional = Optional.of(user);
		authType = loginUtil.getAuthType() == null ? AuthType.CLOUD : loginUtil.getAuthType();
		
		return checkAndAuthorize(optional);
	}
 
    public String loginWithPass() {
        Optional<User> userOptional = findUserByUsernamePassword();
        authType = AuthType.PASSWORD;

        return checkAndAuthorize(userOptional);
    }
    
 

    private Optional<User> findUserByUsernamePassword() {
        List<FilterExample> filter = new ArrayList<>();
        filter.add(new FilterExample("username", username, InequalityConstants.EQUAL, true));
        filter.add(new FilterExample("password", password, InequalityConstants.EQUAL));

        List<User> list = userService.findByExample(0, 1, filter);
        
        System.out.println("list = " + list + ", username = " + username + ", password = " + password);

        if (list.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(list.get(0));
    }
    
    private String checkAndAuthorize(Optional<User> userOptional) {
        if (!userOptional.isPresent()) {
            facesContext.addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("usernameOrPasswordIncorrect"), null));
            return null;
        }

        User user = userOptional.get();

        if (user.getStatus() == null || !user.getStatus().equals(UserStatus.ACTIVE)) {
            facesContext.addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("userIsNotActive"), null));
            return "blocked?faces-redirect=true";
        }

        return authorizeUser(user);
    }

  

    public String authorizeUser(User user) {
        loginUtil.setCurrentUser(user);
        loginUtil.setAuthType(authType);
        loginUtil.setSessionId(sessionId);   
        
        LoginLog log = new LoginLog();
		log.setDateCreated(new Date());
		log.setStatus(LogStatus.IN);
		log.setUser(user);

		log = loginLogService.persist(log);
        

        Role role = user.getRole();
        if (role != null) {
            switch (org.infosystema.iselect.enums.Role.from(role)) {
                case ADMIN:
                    break;
                case DIRECTOR:
                    break;
                case ACCOUNTANT:
                    break;
                case INSPECTOR:
                    return "/inspector/list?faces-redirect=true";
            }
        }

        return "/view/main?faces-redirect=true";
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

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getSignedData() {
        return signedData;
    }

    public void setSignedData(String signedData) {
        this.signedData = signedData;
    }
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public String getPinCode() {
		return pinCode;
	}
	
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
