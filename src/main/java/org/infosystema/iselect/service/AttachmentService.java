package org.infosystema.iselect.service;

import javax.ejb.Local;
import javax.transaction.SystemException;

import org.infosystema.iselect.dto.AttachmentBinaryDTO;
import org.infosystema.iselect.model.Attachment;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface AttachmentService extends GenericService<Attachment, Integer> {
	
	Attachment saveFromDTO(AttachmentBinaryDTO binary) throws SystemException;

}
