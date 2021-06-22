package org.infosystema.study_abroad.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator<String> {

	public void generateError(String error) {
		FacesMessage message = new FacesMessage(error);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		throw new ValidatorException(message);
	}

	@Override
	public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
		if (value == null)
			generateError("Введенный пароль не удовлетворяет требованиям безопасности");

		if (value != null && value.length() < 8)
			generateError("Введенный пароль не удовлетворяет требованиям безопасности");

		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*()-+=^]).{8,20})";

		if (value != null && !value.matches(regex))
			generateError("Введенный пароль не удовлетворяет требованиям безопасности");
	}
}
