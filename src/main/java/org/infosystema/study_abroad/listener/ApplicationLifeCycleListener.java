package org.infosystema.study_abroad.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.infosystema.study_abroad.util.Configuration;
import org.infosystema.study_abroad.util.MailSender;

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
