package org.infosystema.study_abroad.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.transaction.SystemException;

import org.apache.commons.io.IOUtils;
import org.infosystema.study_abroad.dto.AttachmentBinaryDTO;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.service.AttachmentService;
import org.infosystema.study_abroad.util.web.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Named
@ConversationScoped
public class FileUploadController extends Conversational { 
	private static final long serialVersionUID = -388416034659956860L;
	
	@EJB
	AttachmentService service;
	
	private List<AttachmentBinaryDTO> files = new ArrayList<>();
	private List<Attachment> removedFiles = new ArrayList<>();
	
	
	private int max = 20;
    
   
    
    public void handleFileUpload(FileUploadEvent event) throws IOException { 
    	
    	if(files.size() >= max) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(Messages.getMessage("fileLimitMessage")));  
            return;
    	}
    	
    	String fileName =event.getFile().getFileName();
    	files.add(createFileBinary(event.getFile()));
    	
        FacesMessage msg = new FacesMessage(Messages.getMessage("fileSuccessfullyUploaded").replaceAll("\\{0\\}", fileName));  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
    
	public Set<Attachment> convert() throws SystemException {
		Set<Attachment> attachments = new HashSet<>();
		
		for (AttachmentBinaryDTO binary : files) {
			if(binary == null) continue;
			Attachment attachment = createAttachment(binary);
			binary.setAttachment(attachment);
			attachment = binary.getAttachment().getId() == null ? service.saveFromDTO(binary) : binary.getAttachment();
			attachments.add(attachment);
		}
    	files.clear();
		return attachments;
	}
	
	public void remove(String uuid) {
		Set<AttachmentBinaryDTO> binaries = new HashSet<>();
		for (AttachmentBinaryDTO binary : files) {
			if(binary.getUuid().equals(uuid)) binaries.add(binary);
		}
		
		synchronized(files){
			files.removeAll(binaries);
		}
		
		for (AttachmentBinaryDTO binary : binaries) {
			if(binary.getAttachment() != null && binary.getAttachment().getId() != null) removedFiles.add(binary.getAttachment());
		}
	}
	
	public void assertRemovedFiles() {
		if(removedFiles.isEmpty()) return;
		
		for (Attachment attachment : removedFiles) {
			service.remove(attachment);
		}
		
		removedFiles.clear();
	}
	
	private AttachmentBinaryDTO createFileBinary(UploadedFile file) throws IOException {
    	AttachmentBinaryDTO binary = new AttachmentBinaryDTO();
		binary.setName(file.getFileName());
		binary.setMimeType(file.getContentType());
		binary.setBody(IOUtils.toByteArray(file.getInputstream()));
		
		return binary;
	}

	private Attachment createAttachment(AttachmentBinaryDTO binary) {
		if(binary.getAttachment() != null && binary.getAttachment().getId() != null) return binary.getAttachment();
		Attachment attachment = new Attachment();
		attachment.setFileName(binary.getName());
		return attachment;
	}
    
    public List<AttachmentBinaryDTO> getFiles() {
		return files;
	}
    
    public void setFiles(List<AttachmentBinaryDTO> files) {
		this.files = files;
	}
    
    public int getMax() {
		return max;
	}
    
    public void setMax(int max) {
		this.max = max;
	}
}  
