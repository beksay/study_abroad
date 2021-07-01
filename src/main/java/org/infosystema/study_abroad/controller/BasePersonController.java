package org.infosystema.study_abroad.controller;

import javax.ejb.EJB;

import org.infosystema.study_abroad.enums.ScopeConstants;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.service.PersonService;
import org.infosystema.study_abroad.util.web.FacesScopeQualifier;
import org.infosystema.study_abroad.util.web.ScopeQualifier;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public abstract class BasePersonController  extends Conversational{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PERSON_KEY = "person_key";
	public static final String PERSON_PAGE = "person_page";
	
	@EJB
	protected PersonService service;
	
	public Person getCurrentSessionPerson() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		Integer personId = qualifier.getValue(PERSON_KEY, ScopeConstants.SESSION_SCOPE);
		
		if(personId == null) return null;
		
		Person person = qualifier.getValue(PERSON_KEY, ScopeConstants.REQUEST_SCOPE);
		
		if(person == null) {
			person = service.findById(personId, false);
			qualifier.setValue(PERSON_KEY, person, ScopeConstants.REQUEST_SCOPE);
		}
		
		return person;
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
