package org.infosystema.study_abroad.service;

import javax.ejb.Local;
import javax.transaction.SystemException;

import org.infosystema.study_abroad.dto.AttachmentBinaryDTO;
import org.infosystema.study_abroad.model.Attachment;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface AttachmentService extends GenericService<Attachment, Integer> {
	
	Attachment saveFromDTO(AttachmentBinaryDTO binary) throws SystemException;

}
