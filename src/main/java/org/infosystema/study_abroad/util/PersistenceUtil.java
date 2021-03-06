package org.infosystema.study_abroad.util;

import java.io.Serializable;

import javax.persistence.Persistence;

import org.infosystema.study_abroad.model.PersistentEntity;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

public class PersistenceUtil {
	
	public static<T extends PersistentEntity<ID>, ID extends Serializable> boolean isLoaded(T entity, String property) {
		javax.persistence.PersistenceUtil util = Persistence.getPersistenceUtil();
		return !util.isLoaded(entity) || util.isLoaded(entity, property);
	}

}
