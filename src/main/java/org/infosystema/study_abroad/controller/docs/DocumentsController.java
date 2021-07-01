package org.infosystema.study_abroad.controller.docs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.FileUploadController;
import org.infosystema.study_abroad.conversiation.ConversationDocs;
import org.infosystema.study_abroad.dto.AttachmentBinaryDTO;
import org.infosystema.study_abroad.enums.DocStatus;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.model.docs.Documents;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.DocumentsService;
import org.infosystema.study_abroad.service.PersonService;
import org.infosystema.study_abroad.util.Util;
import org.infosystema.study_abroad.util.web.LoginUtil;

@Named
@ViewScoped
public class DocumentsController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private DocumentsService docService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private PersonService personService;
	@Inject
	private ConversationDocs conversation;
	@Inject
	private FileUploadController uploadController;
	@Inject
	private LoginUtil loginUtil;

	private Documents documents;

	@PostConstruct
	public void init() {
		if (documents == null) documents = new Documents();
		documents = conversation.getDocuments();
	}

	public String add() {
		documents = new Documents();
		conversation.setDocuments(documents);
		uploadController.setFiles(new ArrayList<AttachmentBinaryDTO>());
		return form();
	}

	public String edit(Documents documents) {
		documents = docService.getByIdWithFields(documents.getId(), new String[]{"attachments"});
		this.documents = documents;
		conversation.setDocuments(documents);
		uploadController.setFiles(Util.getFiles(documents.getAttachments()));
		return form();
	}

	public String save() {

		Set<Attachment> attachments = new HashSet<>();
		try {
			attachments.addAll(uploadController.convert());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		documents.setAttachments(attachments);
		documents.setUser(loginUtil.getCurrentUser());
		if (documents.getId() == null) {
			documents.setStatus(DocStatus.NEW);
			documents.setDate(new Date());
			docService.persist(documents);
		} else {
			docService.merge(documents);
		}

		return cancel();

	}

	public List<Dictionary> getDocumentsList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Person> getPerson(User user) {
		return personService.findByProperty("personUser", user);
	}

	public String delete(Documents c) {
		docService.remove(c);
		return cancel();
	}

	public String cancel() {
		documents = null;
		return list();
	}

	private String list() {
		return "/view/documents/documents_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/documents/documents_form.xhtml";
	}

	public Documents getDocuments() {
		return documents;
	}

	public void setDocuments(Documents documents) {
		this.documents = documents;
	}

}
