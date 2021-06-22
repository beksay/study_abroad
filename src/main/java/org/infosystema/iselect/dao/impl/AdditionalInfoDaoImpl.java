package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.AdditionalInfoDao;
import org.infosystema.iselect.model.app.AdditionalInfo;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class AdditionalInfoDaoImpl extends GenericDaoImpl<AdditionalInfo, Integer> implements AdditionalInfoDao {

	public AdditionalInfoDaoImpl(EntityManager entityManager,Event<AdditionalInfo> eventSource) {
		super(entityManager,eventSource);
	}
}
