package org.infosystema.study_abroad.data;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.controller.Serializer;
import org.infosystema.study_abroad.model.PersistentEntity;
import org.infosystema.study_abroad.service.GenericService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 * 
 * @author Akzholbek Omorov
 * @param <E>
 *
 */

public abstract class BaseDTOModel<S extends GenericService<E, ID>, E extends PersistentEntity<ID>, ID extends Serializable, K, SR> extends LazyDataModel<K> {
	
	private static final long serialVersionUID = 4131500216891889187L;
	
	private Serializer serializer;
	protected S service;
	protected List<FilterExample> examples = new ArrayList<>();
	private List<K> data = Collections.emptyList();
	protected abstract ID getKey(String key);
	
	private int rowCount;
	private BigDecimal totalSum;
	
	
	public BaseDTOModel(List<FilterExample> examples, S service, SR serializer) {
		this.examples = examples;
		this.service = service;
		this.serializer=(Serializer) serializer;
		initRowCount();
	}
	
	
	@Override
	public Object getRowKey(K entity) {
		try {
			return serializer.serialize(entity);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public K getRowData(String key) {
		try {
			return (K)serializer.deserialize(key);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	@Override	
	public List<K> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
		try {
			sortField = sortField == null ? "id" : sortField;
			
			SortEnum sortEnum = sortOrder == null || sortOrder.equals(SortOrder.DESCENDING) ? SortEnum.DESCENDING : SortEnum.ASCENDING;
			data = getByExample(first, first + pageSize, sortEnum, sortField); 
			return data;
		} catch(Exception e){
			return Collections.emptyList();
		}
	}
	
	private void initRowCount(){
		try {
			Map<String, Object> rows=service.getModelTotal(examples);
			if(rows.get("count")!=null)
				rowCount =((BigInteger)rows.get("count")).intValue();
			else 
				rowCount=0;
			
			if(rows.get("totalSum")!=null)
				setTotalSum( (BigDecimal) rows.get("totalSum"));
			else 
				setTotalSum( new BigDecimal(0));
			
		} catch (Exception e) {
			e.printStackTrace();
			rowCount = 0;
		}
	}
	
	
	private List<K> getByExample(int first, int pageSize, SortEnum sortEnum, String sortField) {
		return service.getModel(pageSize, first,sortEnum,sortField, examples);
	}
	
	@Override
	public int getRowCount() {
		return rowCount;
	}
	
	public BigDecimal getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(BigDecimal totalSum) {
		this.totalSum = totalSum;
	}
}
