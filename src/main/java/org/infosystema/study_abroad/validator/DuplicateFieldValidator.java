package org.infosystema.study_abroad.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="duplicateFieldValidator")
public class DuplicateFieldValidator implements Validator<Object> {
	 @Override
	    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
	        Object otherValue = component.getAttributes().get("otherValue");

	        if (value == null || otherValue == null) {
	            return; // Let required="true" handle.
	        }

	        if (!value.equals(otherValue)) {
	            throw new ValidatorException(new FacesMessage("Новые пароли не совпадают!!!"));
	        }
	    }

	

}