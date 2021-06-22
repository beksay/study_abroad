package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.AdditionalInfoDao;
import org.infosystema.study_abroad.dao.impl.AdditionalInfoDaoImpl;
import org.infosystema.study_abroad.model.app.AdditionalInfo;
import org.infosystema.study_abroad.service.AdditionalInfoService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(AdditionalInfoService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AdditionalInfoServiceImpl extends GenericServiceImpl<AdditionalInfo, Integer, AdditionalInfoDao> implements AdditionalInfoService {

	@Override
	protected AdditionalInfoDao getDao() {
		return new AdditionalInfoDaoImpl(em, se);
	}

}
