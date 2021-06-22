package org.infosystema.study_abroad.beans;

public class IdGood {
	
	private String varError;
	private Integer id;
	
	public IdGood(String varError, Integer id) {
		setVarError(varError);
		setId(id);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getVarError() {
		return varError;
	}

	public void setVarError(String varError) {
		this.varError = varError;
	}

	@Override
	public String toString() {
		return "IdGood [varError=" + varError + ", id=" + id + "]";
	}

}