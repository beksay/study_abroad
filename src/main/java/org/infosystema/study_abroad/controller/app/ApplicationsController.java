package org.infosystema.study_abroad.controller.app;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.controller.BaseAppController;
import org.infosystema.study_abroad.conversiation.ConversationApp;
import org.infosystema.study_abroad.enums.AppStatus;
import org.infosystema.study_abroad.enums.Applicant;
import org.infosystema.study_abroad.enums.ApplicationType;
import org.infosystema.study_abroad.enums.ScopeConstants;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.app.AdditionalInfo;
import org.infosystema.study_abroad.model.app.AppModule;
import org.infosystema.study_abroad.model.app.Applications;
import org.infosystema.study_abroad.model.app.Exporters;
import org.infosystema.study_abroad.model.app.Importers;
import org.infosystema.study_abroad.model.app.Transportations;
import org.infosystema.study_abroad.service.AdditionalInfoService;
import org.infosystema.study_abroad.service.AppModuleService;
import org.infosystema.study_abroad.service.ApplicationsService;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.ExporterService;
import org.infosystema.study_abroad.service.ImporterService;
import org.infosystema.study_abroad.service.TransportationService;
import org.infosystema.study_abroad.util.PasswordBuilder;
import org.infosystema.study_abroad.util.web.FacesScopeQualifier;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.ScopeQualifier;
import org.primefaces.event.SelectEvent;

@Named
@RequestScoped
public class ApplicationsController extends BaseAppController {

	private static final long serialVersionUID = 1L;
	@EJB
	private ApplicationsService appService;
	@EJB
	private AppModuleService moduleService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private ExporterService exporterService;
	@EJB
	private ImporterService importerService;
	@EJB
	private TransportationService transportationService;
	@EJB
	private AdditionalInfoService additionalInfoService;
	private UIComponent innField;

	@Inject
	private LoginUtil loginUtil;
	@Inject
	private ConversationApp conversation;

	public String add() {
		conversation.initialize();
		conversation.setApplications(new Applications());
		conversation.getApplications().setDateCreated(new Date());
		PasswordBuilder numberBuilder = new PasswordBuilder();
		numberBuilder.digits(7);
		String appNumber = numberBuilder.build();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String strDate = dateFormat.format(date);

		conversation.getApplications().setNumber(strDate + "-" + appNumber);
		return form();
	}

	public String save() {

		if (conversation.getApplications().getId()==null) {
			Applications apps = appService.initialize(loginUtil.getCurrentUser(), conversation.getApplications());
			ScopeQualifier qualifier = new FacesScopeQualifier();
			qualifier.setValue(APPS_KEY, apps.getId(), ScopeConstants.SESSION_SCOPE);

			System.out.println(conversation.getApplications().getId() + "------------");
		}

		appService.merge(conversation.getApplications());

		return "/view/apps/main_app.xhtml";

	}

	public void onRowSelect(SelectEvent event) throws IOException {
		conversation.setApplications((Applications) event.getObject());

		conversation.setApplications(conversation.getApplications());

		List<Exporters> exporters = exporterService.findByProperty("applications", conversation.getApplications());
		conversation.setExporters(exporters.get(0));

		List<Importers> importers = importerService.findByProperty("applications", conversation.getApplications());
		conversation.setImporters(importers.get(0));

		List<Transportations> transportations = transportationService.findByProperty("applications",
				conversation.getApplications());
		conversation.setTransportations(transportations.get(0));

		List<AdditionalInfo> additionalInfos = additionalInfoService.findByProperty("applications",
				conversation.getApplications());
		conversation.setAdditionalInfo(additionalInfos.get(0));

		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(APPS_KEY, conversation.getApplications().getId(), ScopeConstants.SESSION_SCOPE);

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/study_abroad/view/applications/app_preview.xhtml?cid=" + conversation.getId());
	}

	public List<AppModule> getModules() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		Integer id = qualifier.getValue(APPS_KEY, ScopeConstants.SESSION_SCOPE);
		List<AppModule> modules = qualifier.getValue(id + "_", ScopeConstants.REQUEST_SCOPE);

		if (modules == null) {
			List<FilterExample> list = new ArrayList<>();
			list.add(new FilterExample("applications.id", id, InequalityConstants.EQUAL));
			modules = moduleService.findByExample(0, 200, SortEnum.ASCENDING, list, "index");

			qualifier.setValue(id + "_", modules, ScopeConstants.REQUEST_SCOPE);
		}

		System.out.println("modules = " + modules + " id = " + id);

		return modules;
	}

	public Boolean checkForFilled(Applications applications) {
		List<Integer> selectedPC = new ArrayList<>();
		for (AppModule lm : getModules()) {
			selectedPC.add(lm.getStatus().ordinal());
			if (lm.getIndex()!=6) {
				if (applications.getType().equals(ApplicationType.EXPORT) || applications.getType().equals(ApplicationType.INTERNAL)) {
					if (lm.getIndex()!=5) {
						if (selectedPC.contains(0)) {
							return false;
						}
					}
			    }else {
			    	if (selectedPC.contains(0)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public String sendApp() {

		conversation.getApplications().setStatus(AppStatus.SENDED);
		appService.merge(conversation.getApplications());

		return "/view/apps/sended.xhtml";

	}

	public String declineForm(Applications applications) {
		conversation.setApplications(applications);
		return "/view/applications/decline_form.xhtml";
	}

	public String declineApp() {
		conversation.getApplications().setStatus(AppStatus.DECLINED);
		appService.merge(conversation.getApplications());
		return "/view/applications/applications_journal.xhtml";
	}

	public String getColoredJournal(Applications applications) {
		if (applications.getStatus().equals(AppStatus.ACCEPTED)) {
			return "GREENAPP";
		} else if (applications.getStatus().equals(AppStatus.DECLINED)) {
			return "REDAPP";
		} else {
			return "";
		}
	}

	public String delete(Applications c) {
		System.out.println(c);
		appService.remove(c);
		return cancel();
	}

	public String cancel() {
		conversation.setApplications(null);
		return list();
	}
	
	public String back() {
		return form();
	}

	private String list() {
		return "/view/home.xhtml";
	}

	private String form() {
		return "/view/apps/applications_form.xhtml";
	}

	public List<Dictionary> getLegalFormList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 5, InequalityConstants.EQUAL));

		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getCountryList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<ApplicationType> getAllMemberType() {
		return Arrays.asList(ApplicationType.values());
	}

	public List<Applicant> getAllApplicant() {
		return Arrays.asList(Applicant.values());
	}

	public ConversationApp getConversation() {
		return conversation;
	}

	public void setConversation(ConversationApp conversation) {
		this.conversation = conversation;
	}

	public UIComponent getInnField() {
		return innField;
	}

	public void setInnField(UIComponent innField) {
		this.innField = innField;
	}

}
