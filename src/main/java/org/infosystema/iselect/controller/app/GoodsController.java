package org.infosystema.iselect.controller.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.enums.ModuleStatus;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.app.Applications;
import org.infosystema.iselect.model.app.GoodStep;
import org.infosystema.iselect.model.app.Goods;
import org.infosystema.iselect.model.nomenclature.Products;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.GoodsService;
import org.infosystema.iselect.service.GoodsStepService;
import org.infosystema.iselect.service.ProductsService;

@Named
@ConversationScoped
public class GoodsController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private GoodsService service;
	@EJB
	private DictionaryService dictService;
	@EJB
	private ProductsService productsService;
	@EJB
	private GoodsStepService moduleService;
	private GoodStep module;
	private List<Goods> list;
	private Goods goods;

	@PostConstruct
	public void init() {
		if (goods == null)
			goods = new Goods();
	}

	public String edit(GoodStep module) {
		this.module = module;

		list = service.findByProperty("module", module);

		return "goods_form.xhtml";
	}

	public List<Goods> getList() {
		return list;
	}

	public List<Goods> getGoodsList(Applications applications) {
		return service.findByProperty("module.applications", applications);
	}

	public String save() {
		if (goods.getId() == null) {
			goods.setModule(module);
			goods = service.persist(goods);

			list.add(goods);

			if (list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}

			module = moduleService.merge(module);
		} else {
			goods = service.merge(goods);
			list = service.findByProperty("module", goods.getModule());
		}

		goods = new Goods();
		return "goods_form.xhtml";
	}

	public String editData(Goods goods) {
		this.goods = service.findById(goods.getId(), false);
		return "goods_form.xhtml";
	}

	public String delete(Goods goods) {
		service.remove(goods);
		list = service.findByProperty("module", goods.getModule());
		return "goods_form.xhtml";
	}

	public String cancel() {
		return "main_app.xhtml";
	}

	public List<Products> getProductInfoList(String query) {
		List<FilterExample> examples = new ArrayList<>();

		Long count = productsService.countByExample(examples);
		return productsService.findByExample(0, Math.toIntExact(count), examples).stream()
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

	public List<Dictionary> getRegionList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 4, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		examples.add(new FilterExample("parent.code", "41700 000 000 00 0", InequalityConstants.EQUAL));
		return dictService.findByExample(0, 100, examples);
	}

	public List<Dictionary> getMeasurementUnitList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 1, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Dictionary> getPackageTypeList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 7, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

}
