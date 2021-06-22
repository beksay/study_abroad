package org.infosystema.iselect.controller.app;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

import org.infosystema.iselect.controller.FileUploadController;
import org.infosystema.iselect.model.Attachment;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.enums.ApplicationType;
import org.infosystema.iselect.enums.ModuleStatus;
import org.infosystema.iselect.model.app.Applications;
import org.infosystema.iselect.model.app.Documents;
import org.infosystema.iselect.model.app.DocumentsStep;
import org.infosystema.iselect.service.DocumentsService;
import org.infosystema.iselect.service.DocumentsStepService;

@Named
@ConversationScoped
public class DocumentsController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DocumentsService service;
	@EJB
	private DocumentsStepService moduleService;
	@Inject
	private FileUploadController uploadController;
	private DocumentsStep module;
	private List<Documents> list;
	private Documents documents;
	
	@PostConstruct
	public void init() {
		if (documents==null) documents= new Documents();
	}
	
	public String edit(DocumentsStep module) {
		this.module = module;
		if(module.getApplications().getType().equals(ApplicationType.IMPORT) || module.getApplications().getType().equals(ApplicationType.TRANSIT)) {
			documents.setName("Фитосанитарный сертификат");
		}		
		list = service.findByPropertyWithFields("module", module, new String[] { "attachments" });
		return "documents_form.xhtml";
	}
	
	public List<Documents> getList() {
		return list;
	}
	
	public List<Documents> getDocumentsList(Applications applications) {
		return service.findByProperty("module.applications", applications);
	}
	
	public String save() {
		if(documents.getId()==null) {
			Set<Attachment> attachments = new HashSet<>();
			try {
				attachments.addAll(uploadController.convert());
			} catch (SystemException e) {
				e.printStackTrace();
			}
			
			documents.setAttachments(attachments);
			documents.setModule(module);
			documents = service.persist(documents);
			
			list.add(documents);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		}else {
			documents = service.merge(documents);
			list = service.findByPropertyWithFields("module", documents.getModule(), new String[] { "attachments" });
		}

		documents = new Documents();
		
		return "documents_form.xhtml";
	}
	
	public String editData(Documents documents) {
		this.documents = service.findById(documents.getId(), false);
		return "documents_form.xhtml";
	}
	
	public String delete(Documents documents) {
		System.out.println("Documents ==" + documents);
		
		service.remove(documents);
		
		list = service.findByPropertyWithFields("module", documents.getModule(), new String[] { "attachments" });
		return "documents_form.xhtml";
	}
	
	public String cancel() {
		return "main_app.xhtml";
	}

	public Documents getDocuments() {
		return documents;
	}

	public void setDocuments(Documents documents) {
		this.documents = documents;
	}
	
	public DocumentsStep getModule() {
		return module;
	}
	
	public void setModule(DocumentsStep module) {
		this.module = module;
	}
}
