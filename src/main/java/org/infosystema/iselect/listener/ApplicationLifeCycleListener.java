package org.infosystema.iselect.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.infosystema.iselect.util.Configuration;
import org.infosystema.iselect.util.MailSender;

/***
 * 
 * @author Akzholbek Omorov
 *
 */

public class ApplicationLifeCycleListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		MailSender.destroy();
	}

	public void contextInitialized(ServletContextEvent arg0) {
		Configuration.getInstance();
	}

}
