package org.infosystema.study_abroad.controller.docs;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.controller.FileUploadController;
import org.infosystema.study_abroad.dto.AttachmentBinaryDTO;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.docs.Documents;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.model.docs.VisaDocument;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.DocumentsService;
import org.infosystema.study_abroad.service.VisaDocumentService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class VisaDocumentController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DocumentsService service;
	@EJB
	private VisaDocumentService moduleService;
	@EJB
	private DictionaryService dictService;
	@Inject
	private FileUploadController uploadController;
	@Inject
	private LoginUtil loginUtil;
	private VisaDocument module;
	private List<Documents> list;
	private Documents documents;
	
	@PostConstruct
	public void init() {
		if (documents==null) documents= new Documents();
	}
	
	public String edit(VisaDocument module) {
		this.module = module;		
		list = service.findByPropertyWithFields("visaModule", module, new String[] { "attachments" });
		uploadController.setFiles(new ArrayList<AttachmentBinaryDTO>());
		return "visa_documents.xhtml";
	}
	
	public List<Documents> getList() {
		return list;
	}
	
	public List<Documents> getDocumentsList(Person person) {
		return service.findByProperty("visaModule.person", person);
	}
	
	public String save() {
		if(documents.getId()==null) {
			Set<Attachment> attachments = new HashSet<>();
			try {
				attachments.addAll(uploadController.convert());
			} catch (SystemException e) {
				e.printStackTrace();
			}
			documents.setDate(new Date());
			documents.setUser(loginUtil.getCurrentUser());
			documents.setAttachments(attachments);
			documents.setVisaModule(module);
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
			list = service.findByPropertyWithFields("visaModule", documents.getModule(), new String[] { "attachments" });
		}

		documents = new Documents();
		
		return "visa_documents.xhtml";
	}
	
	public String editData(Documents documents) {
		this.documents = service.findById(documents.getId(), false);
		return "visa_documents.xhtml";
	}
	
	public String delete(Documents documents) {
		System.out.println("Documents ==" + documents);
		
		service.remove(documents);
		
		list = service.findByPropertyWithFields("visaModule", documents.getModule(), new String[] { "attachments" });
		return "visa_documents.xhtml";
	}
	
	public List<Dictionary> getDictDocumentsList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public String cancel() {
		return "main.xhtml";
	}

	public Documents getDocuments() {
		return documents;
	}

	public void setDocuments(Documents documents) {
		this.documents = documents;
	}
	
	public VisaDocument getModule() {
		return module;
	}
	
	public void setModule(VisaDocument module) {
		this.module = module;
	}
}
