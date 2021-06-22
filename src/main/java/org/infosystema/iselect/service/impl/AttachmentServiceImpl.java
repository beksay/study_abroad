package org.infosystema.iselect.service.impl;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.SystemException;

import org.infosystema.iselect.dto.AttachmentBinaryDTO;
import org.infosystema.iselect.dto.AttachmentDataSource;
import org.infosystema.iselect.dto.IdentifyResponse;
import org.infosystema.iselect.soa.RepositoryService;
import org.infosystema.iselect.soa.RepositoryServiceFactory;
import org.infosystema.iselect.dao.AttachmentDao;
import org.infosystema.iselect.dao.impl.AttachmentDaoImpl;
import org.infosystema.iselect.model.Attachment;
import org.infosystema.iselect.service.AttachmentService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(AttachmentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AttachmentServiceImpl extends GenericServiceImpl<Attachment, Integer, AttachmentDao> implements AttachmentService {

    private RepositoryService service;
	
	@PostConstruct
	private void init(){
		try {
			service = RepositoryServiceFactory.getInstance().getService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected AttachmentDao getDao() {
		return new AttachmentDaoImpl(em, se);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Attachment saveFromDTO(AttachmentBinaryDTO binary) throws SystemException {
		binary.setName(binary.getAttachment().getRepositoryLink());
		try { 
			DataHandler handler = new DataHandler(new AttachmentDataSource(binary));
			System.out.println("1111");
			IdentifyResponse response = service.save(binary.getAttachment().getFileName(), handler);
			System.out.println(response.getCode() + " " + response.getComment() + " " + response.getChecksum());
			binary.getAttachment().setRepositoryLink(response.getChecksum());
		} catch (Exception e) {
			e.printStackTrace();
			throw new SystemException(e.getLocalizedMessage());
		}
		
		return persist(binary.getAttachment());
	}

}
