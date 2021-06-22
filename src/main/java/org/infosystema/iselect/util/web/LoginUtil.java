package org.infosystema.iselect.util.web;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.commons.codec.binary.Base64;
import org.infosystema.iselect.enums.AuthType;
import org.infosystema.iselect.enums.ScopeConstants;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.util.Digest;
import org.infosystema.iselect.util.Krypto;
import org.infosystema.iselect.util.PasswordEncryptionService;

@Named
@ApplicationScoped
public class LoginUtil implements Serializable {

    private static final long serialVersionUID = 5910093770049428002L;
    public static final String CURRENT_USER_SESSION_KEY = "org.infosystema.iselect.current_user_session_key";
    public static final String CURRENT_USER_ACCESS_TOKEN = "org.infosystema.iselect.current_user_access_token";
    public static final String CURRENT_USER_PIVILAGES_SESSION_KEY = "org.infosystema.iselect.current_user_privilages_session_key";
    public static final String AUTH_TYPE = "org.infosystema.iselect.auth_type";
    public static final String SESSION_ID = "org.infosystema.iselect.session_id";
    public static final String SESSION_INN = "org.infosystema.iselect.session_inn";

    public boolean isPasswordsMatch(String providedPassword, String storedPassword, String salt) {
        return storedPassword.equals(PasswordEncryptionService.hashPassword(providedPassword, salt));
    }
    
    public boolean userHasRole(User user, String roleName) {
		if(roleName == null || user == null || user.getRole() == null) return false;
		
		return user.getRole().getName().contains(roleName);
	}

    public boolean userHasRole(User user, int roleName) {
        if (user == null || user.getRole() == null) return false;
        return user.getRole().getId() == roleName;
    }

    public boolean userHasRole(User user, int[] roleNames) {
        if (roleNames == null || user == null) return false;

        for (int role : roleNames) {
            if (userHasRole(user, role)) return true;
        }

        return false;
    }

	public void setCurrentUser(User user) {
        new FacesScopeQualifier().setValue(CURRENT_USER_SESSION_KEY, user, ScopeConstants.SESSION_SCOPE);
    }
	
	public void setAccessToken(String token) {
        new FacesScopeQualifier().setValue(CURRENT_USER_ACCESS_TOKEN, token, ScopeConstants.SESSION_SCOPE);
    }
	
	public String getAccessToken() {
       return new FacesScopeQualifier().getValue(CURRENT_USER_ACCESS_TOKEN, ScopeConstants.SESSION_SCOPE);
    }	
	
    public User getCurrentUser() {
        return new FacesScopeQualifier().getValue(CURRENT_USER_SESSION_KEY, ScopeConstants.SESSION_SCOPE);
    }

    public void setAuthType(AuthType authType) {
    	new FacesScopeQualifier().setValue(AUTH_TYPE, authType, ScopeConstants.SESSION_SCOPE);
	}

	public AuthType getAuthType() {
    	return new FacesScopeQualifier().getValue(AUTH_TYPE, ScopeConstants.SESSION_SCOPE);
	}
	
	public void setSessionId(String sessionId) {
    	new FacesScopeQualifier().setValue(SESSION_ID, sessionId, ScopeConstants.SESSION_SCOPE);
	}

	public String getSessionId() {
    	return new FacesScopeQualifier().getValue(SESSION_ID, ScopeConstants.SESSION_SCOPE);
	}
	
	public void setSessionOrgInn(String inn) {
    	new FacesScopeQualifier().setValue(SESSION_INN, inn, ScopeConstants.SESSION_SCOPE);
	}

	public String getSessionOrgInn() {
    	return new FacesScopeQualifier().getValue(SESSION_INN, ScopeConstants.SESSION_SCOPE);
	}

    public boolean isLogged() {
        User user = new FacesScopeQualifier().getValue(CURRENT_USER_SESSION_KEY, ScopeConstants.SESSION_SCOPE);
        return (user != null);
    }

    public void logout() {
        new FacesScopeQualifier().getSession().invalidate();
    }
    
    public String getHashPassword(String password) throws Exception {
    	Krypto krypto = new Krypto();
		krypto.setKey(new byte[]{0x21, 0x10, 0x51, 0x09, 0x08, 0x70, 0x07, 0x04});
		byte[] bytes = Base64.encodeBase64(krypto.doEncrypt(password.getBytes()).getBytes());
		return new Digest("SHA-512").doEncypt(bytes);
	}
}
