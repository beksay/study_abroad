package org.infosystema.study_abroad.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.infosystema.study_abroad.dto.ReceiptgoodsHeaderDto;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class QueryResult implements Serializable {
	
	private static final long serialVersionUID = 1672949775760176633L;
	
	private List<ReceiptgoodsHeaderDto> list = new ArrayList<>();
	private Integer count;
	
	public QueryResult() {}
	
	public Integer getCount() {
		return count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public List<ReceiptgoodsHeaderDto> getList() {
		return list;
	}
	
	public void setList(List<ReceiptgoodsHeaderDto> list) {
		this.list = list;
	}

}
