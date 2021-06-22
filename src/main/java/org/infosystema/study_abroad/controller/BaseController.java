package org.infosystema.study_abroad.controller;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

public abstract class BaseController {
	
	@Inject
	protected FacesContext context;
	
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
