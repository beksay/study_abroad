package org.infosystema.study_abroad.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.infosystema.study_abroad.dto.rest.OnoiBeanRest;
import org.infosystema.study_abroad.enums.AuthType;
import org.infosystema.study_abroad.util.Configuration;
import org.infosystema.study_abroad.util.web.LoginUtil;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class SessionListener implements HttpSessionListener {
	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("closed session: = " + se.getSession().getId());
		HttpSession session = se.getSession();
		
		if (session.getAttribute(LoginUtil.AUTH_TYPE) != null && session.getAttribute(LoginUtil.AUTH_TYPE).equals(AuthType.ONOI)) {
		    if(session.getAttribute(LoginUtil.SESSION_ID) == null) return;
		    	
		    Configuration configuration = Configuration.getInstance();
		    String url = "https://smartidkg.onoi.kg/api/Session/CloseSession/" + session.getAttribute(LoginUtil.SESSION_ID);
		    Client client = ClientBuilder.newClient();
		    WebTarget webTarget = client.target(url);
			webTarget.request(MediaType.APPLICATION_JSON).header("apiKey", configuration.getProperty("onoiBackEndApiKey")).post(Entity.entity(new OnoiBeanRest(), MediaType.APPLICATION_JSON));
	    }
	}
}
