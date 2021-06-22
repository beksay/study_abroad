package org.infosystema.study_abroad.timer;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Startup
@Singleton
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class Reindexer {
	
	@Inject protected EntityManager em;
	
	@Schedule(hour="*", minute="*", second="*/15")
	private void minutely() {
		//reindex();
	}
	
//	public void reindex() {
//		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
//		try {
//			List<ReceiptGoodsHeader> headers = em.createQuery("select entity from ReceiptGoodsHeader entity where entity.reindex = :flag order by id DESC", ReceiptGoodsHeader.class)
//					.setParameter("flag", true).setFirstResult(0).setMaxResults(1000).getResultList();
//			for (ReceiptGoodsHeader header : headers) {
//		    	System.out.println("header to index = " + header.getId());
//				fullTextEntityManager.index(header);
//				fullTextEntityManager.flushToIndexes();
//				header.setReindex(false); 
//				em.merge(header);
//			}
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
