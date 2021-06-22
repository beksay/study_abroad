package org.infosystema.study_abroad.util.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;


/**
 * 
 * @author Akzholbek Omorov
 *
 */

@ApplicationScoped
public class Messages implements Serializable {
	
	private static final long serialVersionUID = -2065651128811043538L;

	private static Map<Locale, ResourceBundle> resources = new HashMap<>();
	private static Map<Locale, ResourceBundle> enums = new HashMap<>();
	
	public static String getMessage(String message) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = resources.get(locale);
		//System.out.println("locale===="+locale);
		if(bundle == null) {
			bundle = ResourceBundle.getBundle("org.study_abroad.resources.messages", locale);
			resources.put(locale, bundle);
		}
		
		try {
			 return bundle.getString(message);
		} catch(MissingResourceException e){
			e.printStackTrace();
			return "??" + message + "??";
		}
	}
	public static String getEnumMessage(String message) {		
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		ResourceBundle bundle = enums.get(locale);
		if(bundle == null) {
			bundle = ResourceBundle.getBundle("org.study_abroad.resources.enums", locale);
			enums.put(locale, bundle);
		}
		
		try {
			 return bundle.getString(message);
		} catch(MissingResourceException e){
			return "??" + message + "??";
		}
	}
}

