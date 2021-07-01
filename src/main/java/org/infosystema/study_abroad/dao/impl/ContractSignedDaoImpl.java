package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ContractSignedDao;
import org.infosystema.study_abroad.model.docs.ContractSigned;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ContractSignedDaoImpl extends GenericDaoImpl<ContractSigned, Integer> implements ContractSignedDao {

	public ContractSignedDaoImpl(EntityManager entityManager,Event<ContractSigned> eventSource) {
		super(entityManager,eventSource);
	}
}
