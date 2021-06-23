package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.CountriesDao;
import org.infosystema.study_abroad.dao.impl.CountriesDaoImpl;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.service.CountriesService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(CountriesService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CountriesServiceImpl extends GenericServiceImpl<Countries, Integer, CountriesDao> implements CountriesService {

	@Override
	protected CountriesDao getDao() {
		return new CountriesDaoImpl(em, se);
	}

}
