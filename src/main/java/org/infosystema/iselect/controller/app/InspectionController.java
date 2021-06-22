package org.infosystema.iselect.controller.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.conversiation.ConversationApp;
import org.infosystema.iselect.enums.AppStatus;
import org.infosystema.iselect.enums.InspectionStatus;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.app.AdditionalInfo;
import org.infosystema.iselect.model.app.Applications;
import org.infosystema.iselect.model.app.Exporters;
import org.infosystema.iselect.model.app.Importers;
import org.infosystema.iselect.model.app.Inspection;
import org.infosystema.iselect.model.app.Transportations;
import org.infosystema.iselect.model.nomenclature.EntryPoint;
import org.infosystema.iselect.model.nomenclature.TransportType;
import org.infosystema.iselect.service.AdditionalInfoService;
import org.infosystema.iselect.service.ApplicationsService;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.EntryPointService;
import org.infosystema.iselect.service.ExporterService;
import org.infosystema.iselect.service.ImporterService;
import org.infosystema.iselect.service.InspectionService;
import org.infosystema.iselect.service.TransportTypeService;
import org.infosystema.iselect.service.TransportationService;
import org.infosystema.iselect.util.web.LoginUtil;
import org.infosystema.iselect.util.web.Messages;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class InspectionController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private InspectionService service;
	@EJB
	private ApplicationsService appService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private EntryPointService entryPointService;
	@EJB
	private ExporterService exporterService;
	@EJB
	private ImporterService importerService;
	@EJB
	private TransportationService transportationService;
	@EJB
	private AdditionalInfoService additionalInfoService;
	@EJB
	private TransportTypeService transportTypeService;
	@Inject
	private LoginUtil loginUtil;
	@Inject
	private ConversationApp conversation;
	private Inspection inspection;
	private Set<EntryPoint> entryPoints;

	public InspectionController() {
	}

	@PostConstruct
	public void init() {
		if (inspection==null) inspection= new Inspection();
	}

	public String save() {
		if (inspection == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		System.out.println("entryPoints=====" + entryPoints);
		//inspection.setEntryPoints(entryPoints);
		inspection.setUser(loginUtil.getCurrentUser());
		inspection.setStatus(InspectionStatus.NEW);
		if(inspection.getId()==null) {
			service.persist(inspection);
		}else {
			service.merge(inspection);
		}
		inspection.getApplications().setStatus(AppStatus.ACCEPTED);
		appService.merge(inspection.getApplications());
		return cancel();
	}


	public String cancel() {
		System.out.println("canceling.....");
		setInspection(null);
		return list();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		inspection  = (Inspection) event.getObject();
	    inspection = service.getByIdWithFields(inspection.getId(), new String[] { "entryPoints" });
		conversation.setApplications(inspection.getApplications());
		
		List<Exporters> exporters = exporterService.findByProperty("applications", conversation.getApplications());
		conversation.setExporters(exporters.get(0));
		
		List<Importers> importers = importerService.findByProperty("applications", conversation.getApplications());
		conversation.setImporters(importers.get(0));
		
		List<Transportations> transportations = transportationService.findByProperty("applications", conversation.getApplications());
		conversation.setTransportations(transportations.get(0));
		
		List<AdditionalInfo> additionalInfos = additionalInfoService.findByProperty("applications", conversation.getApplications());
		conversation.setAdditionalInfo(additionalInfos.get(0));
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/iselect/view/applications/inspection/inspection_preview.xhtml?cid="+conversation.getId());    
    }

	private String list() {
		return "/view/applications/applications_journal.xhtml";
	}
	
	private String form() {
		return "/view/applications/inspection/inspection_form.xhtml";
	}
	
	public String acceptApp(Applications applications) { 
		inspection = new Inspection();
		inspection.setApplications(applications);
		inspection.setActDate(new Date());
		inspection.setActNumber(loginUtil.getCurrentUser().getCodes());
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("applications.subdivision", loginUtil.getCurrentUser().getSubdivision(), InequalityConstants.EQUAL));
		examples.add(new FilterExample("applications.year", Calendar.getInstance().get(Calendar.YEAR), InequalityConstants.EQUAL));
		Long max = service.countByExample(examples);
		String num = max + "";
		while (num.length() < 5) {
			num = "0" + num;
		}
		inspection.setActNumber(inspection.getActNumber()+num);
		List<Transportations> transList = transportationService.findByPropertyWithFields("applications", applications,new String[] { "entryPoints" });
		inspection.setVehicleNumber(transList.get(0).getTripNumber());
		inspection.setTransportType(transList.get(0).getTransportType());
		inspection.setTransporter(transList.get(0).getTransporter());
		inspection.setEntryPoints(new HashSet<EntryPoint>(transList.get(0).getEntryPoints()));
	    return form();
	}
	
	public List<Dictionary> getMarkingList() {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 6, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        return dictService.findByExample(0, 100, examples);
    }
	
	public List<EntryPoint> getEntryPointList() {
        List<FilterExample> examples=new ArrayList<>();
        return entryPointService.findByExample(0, 100, examples);
    }
	
	public List<TransportType> getTransportTypeList() {
		List<FilterExample> examples = new ArrayList<>();
	  return transportTypeService.findByExample(0, 100, examples);
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public Set<EntryPoint> getEntryPoints() {
		return entryPoints;
	}

	public void setEntryPoints(Set<EntryPoint> entryPoints) {
		this.entryPoints = entryPoints;
	}
}