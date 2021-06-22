package org.infosystema.iselect.beans.filter;

import java.io.Serializable;

import org.infosystema.iselect.enums.ScopeConstants;
import org.infosystema.iselect.util.GlobalFilter;
import org.infosystema.iselect.util.web.FacesScopeQualifier;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class FilterData implements Serializable {
	
	private static final long serialVersionUID = 3689878884019459508L;
	private Integer rowCount;
	private Integer first;
	private GlobalFilter filter=new FacesScopeQualifier().getValue("org.infosystema.iselect.current_user_global_filter_key", ScopeConstants.SESSION_SCOPE);
	
	public void saveGlobalFilter() {
		
	}
	
	public FilterData() {}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getFirst() {
		first=getFilter().getCurrentPage();
		return first;
	}

	public void setFirst(Integer first,String page) {
		getFilter().setCurrentPage(first);
		getFilter().setLastPage(page);
		this.first = first;
	}

	public GlobalFilter getFilter() {
		return filter;
	}

	public void setFilter(GlobalFilter filter) {
		this.filter = filter;
	}
}
