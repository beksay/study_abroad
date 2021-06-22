package org.infosystema.study_abroad.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.inject.Inject;

import org.infosystema.study_abroad.beans.filter.FilterData;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
public abstract class PageableDataController<D extends FilterData> extends BaseController implements Serializable {
	
	private static final long serialVersionUID = 7423730593528712939L;
	protected static final long MILLISECOND_OF_DAY = 86400000L;
	
	private D filterData = initData();

	@Inject
	private Serializer serializer;
	
	public void onPageChange(PageEvent event) {
		filterData.setFirst(((DataTable) event.getSource()).getRows() * event.getPage(),filterData.getClass().toString());  
	}

	protected abstract D initData();
	protected abstract D cast(Object data);
	
	protected void restoreFlashData(){
		if(context.getExternalContext().getFlash().get("filtersData") != null){
			String value = (String)context.getExternalContext().getFlash().get("filtersData");
			try {
				setFilterData(cast(serializer.deserialize(value)));
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	public D getFilterData() {
		return filterData;
	}
	
	public void setFilterData(D filterData) {
		this.filterData = filterData;
	}
}
