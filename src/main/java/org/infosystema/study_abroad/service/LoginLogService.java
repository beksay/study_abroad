package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.LoginLog;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Local
public interface LoginLogService extends GenericService<LoginLog, Integer> {

}
