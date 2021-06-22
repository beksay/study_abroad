package org.infosystema.iselect.controller;

import javax.ejb.EJB;

import org.infosystema.iselect.enums.ScopeConstants;
import org.infosystema.iselect.model.app.Applications;
import org.infosystema.iselect.service.ApplicationsService;
import org.infosystema.iselect.util.web.FacesScopeQualifier;
import org.infosystema.iselect.util.web.ScopeQualifier;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public abstract class BaseAppController  extends Conversational{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String APPS_KEY = "apps_key";
	public static final String APPS_PAGE = "apps_page";
	
	@EJB
	protected ApplicationsService service;
	
	public Applications getCurrentSessionApplications() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		Integer appId = qualifier.getValue(APPS_KEY, ScopeConstants.SESSION_SCOPE);
		
		if(appId == null) return null;
		
		Applications applications = qualifier.getValue(APPS_KEY, ScopeConstants.REQUEST_SCOPE);
		
		if(applications == null) {
			applications = service.findById(appId, false);
			qualifier.setValue(APPS_KEY, applications, ScopeConstants.REQUEST_SCOPE);
		}
		
		return applications;
	}
	
	protected String getRootErrorMessage(Exception e) {
        String errorMessage = "Registration failed. See server log for more information";
        if (e == null) {
            return errorMessage;
        }

        Throwable t = e;
        while (t != null) {
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        return errorMessage;
    }

}
