package org.infosystema.study_abroad.controller.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ApplicationType;
import org.infosystema.study_abroad.enums.CertificateStatus;
import org.infosystema.study_abroad.enums.InspectionStatus;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.app.Applications;
import org.infosystema.study_abroad.model.app.Certificate;
import org.infosystema.study_abroad.model.app.Documents;
import org.infosystema.study_abroad.model.app.Exporters;
import org.infosystema.study_abroad.model.app.Goods;
import org.infosystema.study_abroad.model.app.Importers;
import org.infosystema.study_abroad.model.app.Inspection;
import org.infosystema.study_abroad.model.app.Transportations;
import org.infosystema.study_abroad.service.CertificateService;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.DocumentsService;
import org.infosystema.study_abroad.service.ExporterService;
import org.infosystema.study_abroad.service.GoodsService;
import org.infosystema.study_abroad.service.ImporterService;
import org.infosystema.study_abroad.service.InspectionService;
import org.infosystema.study_abroad.service.TransportationService;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ConversationScoped
public class CertificateController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private CertificateService service;
	@EJB
	private InspectionService inspectionService;
	@EJB
	private TransportationService transportationService;
	@EJB
	private ExporterService exporterService;
	@EJB
	private ImporterService importerService;
	@EJB
	private DocumentsService docService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private GoodsService goodsService;
	@Inject
	private LoginUtil loginUtil;
	private Certificate certificate;

	public CertificateController() {
	}

	@PostConstruct
	public void init() {
		if (certificate == null)
			certificate = new Certificate();
	}

	public String save() {
		if (certificate == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		certificate.setInspector(loginUtil.getCurrentUser());
		certificate.setDate(new Date());

		if (certificate.getId() == null) {
			service.persist(certificate);
		} else {
			service.merge(certificate);
		}
		if (certificate.getStatus() != null && certificate.getStatus().equals(CertificateStatus.NOT_GIVE)) {
			certificate.getInspection().setStatus(InspectionStatus.CERTIFICATE_NOT_GIVEN);
		} else {
			certificate.getInspection().setStatus(InspectionStatus.SENDED_TO_CERTIFICATE);
		}
		inspectionService.merge(certificate.getInspection());
		return cancel();
	}

	public String cancel() {
		System.out.println("canceling.....");
		setCertificate(null);
		return list();
	}

	public String acceptCertificate(Inspection inspection) {
		certificate = new Certificate();
		certificate.setInspection(inspection);
		if (inspection.getApplications().getType().equals(ApplicationType.EXPORT)) {
			certificate.setAdditionalDeclaration(
					"Подкарантинная продукция произведена на участках производства свободных от "
							+ "карантинных вредных организмов в соответствии с Единым требованиями,"
							+ " утвержденными Решением Совета ЕЭК от 30.11.2016г. №157.");
			List<Transportations> transportations = transportationService.findByProperty("applications",
					inspection.getApplications());

			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
			String strDate = dateFormat.format(date);

			System.out.println(strDate + "======");

			if (transportations.get(0).getCountryImport().getCode() != null
					&& loginUtil.getCurrentUser().getCodes() != null
					&& loginUtil.getCurrentUser().getSubdivision().getCode() != null) {
				certificate.setDocNumber(transportations.get(0).getCountryImport().getCode()
						+ loginUtil.getCurrentUser().getSubdivision().getParent().getCode()
						+ loginUtil.getCurrentUser().getSubdivision().getCode() + loginUtil.getCurrentUser().getCodes()
						+ strDate);

				List<FilterExample> examples = new ArrayList<>();
				examples.add(new FilterExample("date", new Date(), InequalityConstants.EQUAL));
				Long max = service.countByExample(examples);
				String num = max + "";
				while (num.length() < 3) {
					num = "0" + num;
				}
				certificate.setDocNumber(certificate.getDocNumber() + num);
			} else {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("not found !!! ", "not found !!! "));
				return null;
			}
		} else if (inspection.getApplications().getType().equals(ApplicationType.IMPORT)
				|| inspection.getApplications().getType().equals(ApplicationType.TRANSIT)) {
			List<Documents> docs = docService.findByProperty("module.applications", inspection.getApplications());
			certificate.setDocNumber(docs.get(0).getNumber());
			certificate.setDocumentBy(docs.get(0).getDescription());
		}

		return form();
	}

	public List<CertificateStatus> getAllCertificateStatus() {
		return Arrays.asList(CertificateStatus.values());
	}

	public List<Dictionary> getControlMeasuresList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 9, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getActActivitiesList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 11, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Goods> getGoodsList(Applications applications) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("module.applications", applications, InequalityConstants.EQUAL));
		return goodsService.findByExample(0, 100, examples);
	}

	public List<Transportations> getTransport(Applications applications) {
		return transportationService.findByProperty("applications", applications);
	}

	public List<Exporters> getExporters(Applications applications) {
		return exporterService.findByProperty("applications", applications);
	}

	public List<Importers> getImporters(Applications applications) {
		return importerService.findByProperty("applications", applications);
	}

	private String list() {
		return "/view/applications/inspection/inspection_journal.xhtml";
	}

	private String form() {
		return "/view/applications/certificate/certificate_form.xhtml";
	}

	public Certificate getCertificate() {
		return certificate;
	}

	public void setCertificate(Certificate certificate) {
		this.certificate = certificate;
	}

}