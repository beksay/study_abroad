package org.infosystema.study_abroad.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.model.PersistentEntity;
import org.infosystema.study_abroad.service.GenericService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public abstract class BaseModel<S extends GenericService<E, ID>, E extends PersistentEntity<ID>, ID extends Serializable> extends LazyDataModel<E> {

	private static final long serialVersionUID = 1575455424649374631L;
	
	protected List<FilterExample> filters = new ArrayList<>();
	protected S service;
	
	private int rowCount;
	private String[] fetchProperties;
	private List<E> data = Collections.emptyList();
	private String sortField = "id";
	private SortEnum sortEnum = null;
	
	protected abstract ID getKey(String key);

	public BaseModel(List<FilterExample> filters, S service) {
		this.filters = filters;
		this.service = service;
		this.fetchProperties=null;
		initRowCount();
	}
	
	public BaseModel(List<FilterExample> filters, S service,String[] fetchProperties) {
		this.filters = filters;
		this.service = service;
		this.fetchProperties=fetchProperties;
		initRowCount();
	}
	
	@Override
	public Object getRowKey(E entity) {
		return entity.getId();
	}
	
	@Override
	public E getRowData(String key) {
		try {
			ID id = getKey(key);
			if(data == null) return null;
			
			for (E e : data) {
				if(e.getId().equals(id)) return e;
			}
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	@Override	
	 public List<E> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		try {
			sortField = sortField == null ? this.sortField : sortField;
			
			/*System.out.println(sortField + " " + sortOrder);
			System.out.println(filters);
			System.out.println(first + " " + pageSize);*/
			
			SortEnum sortEnum;
			if (this.sortEnum != null)
				sortEnum = this.sortEnum;
			else 
				sortEnum = sortOrder == null || sortOrder.equals(SortOrder.DESCENDING) ? SortEnum.DESCENDING : SortEnum.ASCENDING;
			
			data = this.filters == null || this.filters.isEmpty() 
					? getEntries(first, first + pageSize, sortEnum, sortField)
					: getByExample(first, first + pageSize, sortEnum, sortField); 
			
			//System.out.println(data);
			return data;
		} catch(Exception e){
			return Collections.emptyList();
		}
	}
	
	private void initRowCount(){
		try {
			if(fetchProperties==null){
				rowCount = this.filters == null || this.filters.isEmpty()
						? (int)service.countAll()
						: service.countByExample(filters).intValue();
			}else{
				rowCount = this.filters == null || this.filters.isEmpty()
						? (int)service.countAll()
						: service.countByExample(filters,fetchProperties).intValue();
			}
					//System.out.println(rowCount);
		} catch (Exception e) {
			e.printStackTrace();
			rowCount = 0;
		}
	}
	
	private List<E> getEntries(int first, int pageSize, SortEnum sortEnum, String sortField) {
		return service.findEntries(first, first + pageSize, sortField, sortEnum);
	}		

	private List<E> getByExample(int first, int pageSize, SortEnum sortEnum, String sortField) {
		//System.out.println(fetchProperties);
		return fetchProperties == null 
					? service.findByExample(first, first + pageSize, sortEnum, this.filters, sortField)
					: service.findByExample(first, first + pageSize, sortEnum, this.filters, sortField, fetchProperties);
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
	
	public String[] getFetchProperties() {
		return fetchProperties;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public SortEnum getSortEnum() {
		return sortEnum;
	}
	
	public void setSortEnum(SortEnum sortEnum) {
		this.sortEnum = sortEnum;
	}
	
}
