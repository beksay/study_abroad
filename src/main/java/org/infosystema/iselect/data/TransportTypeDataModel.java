package org.infosystema.iselect.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.SortEnum;
import org.infosystema.iselect.model.nomenclature.TransportType;
import org.infosystema.iselect.service.TransportTypeService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

public class TransportTypeDataModel extends LazyDataModel<TransportType> {

	private static final long serialVersionUID = 1575455424649374631L;
	private List<FilterExample> filters = new ArrayList<>();
	private int rowCount;
	private int totalCount;
	private TransportTypeService service;

	public TransportTypeDataModel(List<FilterExample> filters, TransportTypeService service) {
		this.filters=filters;
		this.service = service;
		initRowCount();
	}
	
	@Override
	public Object getRowKey(TransportType entity) {
		return entity.getId();
	}
	
	@Override
	public TransportType getRowData(String key) {
		try {
			Integer id = Integer.parseInt(key);
			
			return service.findById(id, false);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override	
	 public List<TransportType> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		try {
			sortField = sortField == null ? "id" : sortField;
			
			SortEnum sortEnum = SortEnum.DESCENDING;
			if(sortOrder!=null && sortOrder==SortOrder.ASCENDING) sortEnum=SortEnum.ASCENDING; 
			return this.filters == null || this.filters.isEmpty() 
					? service.findEntries(first, pageSize, sortField, sortEnum) 
					: service.findByExample(first, pageSize, sortEnum, this.filters, sortField); 
		} catch(Exception e){
			return Collections.emptyList();
		}
	}
	
	@Override
	public int getRowCount() {
		return rowCount;
	}
	
	public void setFilters(List<FilterExample> filters) {
		this.filters = filters;
		initRowCount();
	}
	
	public List<FilterExample> getFilters() {
		return filters;
	}
	
	private void initRowCount(){
		try {
			rowCount = this.filters == null || this.filters.isEmpty()
					? (int)service.countAll()
					: service.countByExample(filters).intValue();
			totalCount=(int)service.countAll();
		} catch (Exception e) {
			e.printStackTrace();
			rowCount = 0;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}