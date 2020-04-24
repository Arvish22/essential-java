package com.essential.domain;

import java.io.Serializable;
import java.util.List;

public class CategoryDomain implements Serializable{
	
	/**
	 * domain class to represent the categories
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String type;
	private List<DealerDomain> dealer;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<DealerDomain> getDealer() {
		return dealer;
	}
	public void setDealer(List<DealerDomain> arrDD) {
		this.dealer = arrDD;
	}
}
