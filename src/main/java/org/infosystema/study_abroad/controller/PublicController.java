package org.infosystema.study_abroad.controller;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Named
@ConversationScoped
public class PublicController extends Conversational {

	private static final long serialVersionUID = 1L;
	
	public String loginForm() {
		System.out.println("login-form =====");
		return "/view/user/login.xhtml";
	}

	
}
