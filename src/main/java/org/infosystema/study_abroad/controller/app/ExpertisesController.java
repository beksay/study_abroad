package org.infosystema.study_abroad.controller.app;

import java.io.IOException;
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

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ExpertisesStatus;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.app.Expertises;
import org.infosystema.study_abroad.model.app.ExpertisesGoods;
import org.infosystema.study_abroad.model.app.Goods;
import org.infosystema.study_abroad.model.app.Inspection;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.ExpertisesGoodsService;
import org.infosystema.study_abroad.service.ExpertisesService;
import org.infosystema.study_abroad.service.GoodsService;
import org.infosystema.study_abroad.service.InspectionService;
import org.infosystema.study_abroad.util.web.Messages;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ExpertisesController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ExpertisesService service;
	@EJB
	private InspectionService inspectionService;
	@EJB
	private GoodsService goodsService;
	@EJB
	private ExpertisesGoodsService egService;
	@EJB
	private DictionaryService dictService;
	private Expertises expertises;
	private ExpertisesGoods expertisesGoods;
	private List<ExpertisesGoods> expertisesGoodsList;

	public ExpertisesController() {
	}

	@PostConstruct
	public void init() {
		if (expertises == null)
			expertises = new Expertises();
	}

	public String edit(Expertises Expertises) {
		System.out.println("edit");
		setExpertises(Expertises);
		return form();
	}

	public String save() {
		if (expertises == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		expertises.setStatus(ExpertisesStatus.SENDED_EXPERTISE);
		expertises.setDate(new Date());
		if (expertises.getId() == null) {
			service.persist(expertises);
		} else {
			service.merge(expertises);
		}

		for (ExpertisesGoods expertisesGoods : getExpertisesGoodsList()) {
			expertisesGoods.setExpertises(expertises);
			if (expertisesGoods.getId() == null) {
				expertisesGoods = egService.persist(expertisesGoods);
			} else {
				expertisesGoods = egService.merge(expertisesGoods);
			}
		}

		expertises.getInspection().setExpertisesStatus(ExpertisesStatus.SENDED_EXPERTISE);
		inspectionService.merge(expertises.getInspection());
		return cancel();
	}

	public String cancel() {
		System.out.println("canceling.....");
		setExpertises(null);
		closeConversation();
		return list();
	}

	public String acceptExpertises(Inspection inspection) {
		expertises = new Expertises();
		expertisesGoods = new ExpertisesGoods();
		expertisesGoodsList = new ArrayList<>();
		expertises.setInspection(inspection);
		return form();
	}

	public List<Dictionary> getMeasurementUnitList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 1, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}


	public void addTable() {
		getExpertisesGoodsList().add(expertisesGoods);
		expertisesGoods = new ExpertisesGoods();
	}

	public void deleteTable(Integer expenseDetail) {
		System.out.println("-----" + expenseDetail);
		if (expenseDetail == null) {
			return;
		}
		getExpertisesGoodsList().remove((int) expenseDetail);

	}

	public void onRowSelect(SelectEvent event) throws IOException {
		expertises = (Expertises) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/study_abroad/view/applications/expertises/expertises_goods_journal.xhtml?cid=" + getId());
	}

	private String list() {
		return "/view/applications/inspection/inspection_journal.xhtml";
	}

	private String form() {
		return "/view/applications/expertises/expertises_form.xhtml";
	}

	public Expertises getExpertises() {
		return expertises;
	}

	public void setExpertises(Expertises expertises) {
		this.expertises = expertises;
	}

	public ExpertisesGoods getExpertisesGoods() {
		return expertisesGoods;
	}

	public void setExpertisesGoods(ExpertisesGoods expertisesGoods) {
		this.expertisesGoods = expertisesGoods;
	}

	public List<ExpertisesGoods> getExpertisesGoodsList() {
		return expertisesGoodsList;
	}

	public void setExpertisesGoodsList(List<ExpertisesGoods> expertisesGoodsList) {
		this.expertisesGoodsList = expertisesGoodsList;
	}

}