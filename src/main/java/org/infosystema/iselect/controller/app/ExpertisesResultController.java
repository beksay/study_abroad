package org.infosystema.iselect.controller.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.enums.GoodStatus;
import org.infosystema.iselect.enums.ResultStatus;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.Subdivision;
import org.infosystema.iselect.model.app.DisinfectionResult;
import org.infosystema.iselect.model.app.ExpertisesGoods;
import org.infosystema.iselect.model.app.ExpertisesResult;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.ExpertisesGoodsService;
import org.infosystema.iselect.service.ExpertisesResultService;
import org.infosystema.iselect.service.SubdivisionService;
import org.infosystema.iselect.util.web.Messages;

@Named
@ConversationScoped
public class ExpertisesResultController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ExpertisesResultService service;
	@EJB
	private ExpertisesGoodsService goodService;
	@EJB
	private DictionaryService dictService;
	@EJB
	private SubdivisionService subdivisionService;

	private ExpertisesResult result;

	public ExpertisesResultController() {
		
		// Empty constructor
	}

	@PostConstruct
	public void init() {
		if (result == null)
			result = new ExpertisesResult();
	}

	public String cancel() {
		result = new ExpertisesResult();
		return list();
	}

	public String acceptResult(ExpertisesGoods goods) {
		result.setExpertisesGoods(goods);
		result.setDate(new Date());
		return form();
	}
	
	public String view(ExpertisesGoods goods) {
		List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("expertisesGoods", goods , InequalityConstants.EQUAL));
        List<ExpertisesResult> expertiseResults = service.findByExample(0, 20,examples);
        result = expertiseResults.get(0);
		result.setExpertisesGoods(goods);
		return "/view/applications/expertises/expertises_preview.xhtml";
	}

	public String save() {
		if (result == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		if (result.getId() == null) {
			service.persist(result);
		} else {
			service.merge(result);
		}
		result.getExpertisesGoods().setStatus(GoodStatus.ACCEPTED);
		goodService.merge(result.getExpertisesGoods());
		return cancel();
	}

	public List<Dictionary> getMeasurementUnitList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 1, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<ResultStatus> getAllResultStatus() {
		return Arrays.asList(ResultStatus.values());
	}

	private String list() {
		return "/view/applications/expertises/expertises_journal.xhtml";
	}

	private String form() {
		return "/view/applications/expertises/expertises_result_form.xhtml";
	}

	public List<Subdivision> getSubdivisionList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("parent.code", Arrays.asList("1", "00"), InequalityConstants.NOT_IN));

		Long count = subdivisionService.countByExample(examples);

		return subdivisionService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public ExpertisesResult getResult() {
		return result;
	}

	public void setResult(ExpertisesResult result) {
		this.result = result;
	}

}