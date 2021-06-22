package org.infosystema.iselect.util.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.model.Attachment;
import org.infosystema.iselect.util.Krypto;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Named
@ApplicationScoped
@Logged
public class UtilController {
	
	public String getDownloadURL(String key) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		
		return HttpUtil.getContextUrl(request) + "/download?key=" + key;
	}
	
	public String getUrl() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		
		return HttpUtil.getContextUrl(request);
	}
	
	public void gotoLeader() throws IOException {
	    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	    externalContext.redirect(getUrl() + "/auth");
	}
	
	public String generateKeyAliveCurrentSession(Attachment attachment) {
		if(attachment == null) return null;
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			Integer id = attachment.getId();
			
			Krypto krypto = new Krypto();
		    krypto.setKey(new byte[]{0x21, 0x10, 0x51, 0x09, 0x08, 0x70, 0x07, 04});
		    String keyValue = krypto.doEncrypt((session.getId() + "@@@@@@@" + id.toString()).getBytes());
		    keyValue = URLEncoder.encode(keyValue, "UTF-8");
		    
		    return keyValue;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String generateKeyAliveCurrentSession(Integer id) {
		if(id == null) return null;
		
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
			HttpSession session = request.getSession();
			
			Krypto krypto = new Krypto();
		    krypto.setKey(new byte[]{0x21, 0x10, 0x51, 0x09, 0x08, 0x70, 0x07, 04});
		    //System.out.println(id);
		    String keyValue = krypto.doEncrypt((session.getId() + "@@@@@@@" + id.toString()).getBytes());
		    keyValue = URLEncoder.encode(keyValue, "UTF-8");
		    
		    return keyValue;
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
}
