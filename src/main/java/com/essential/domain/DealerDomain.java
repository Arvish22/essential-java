package com.essential.domain;

import java.io.Serializable;
import java.util.List;

public class DealerDomain implements Serializable{

	/**
	 * domain class to represents the Dealer information
	 */
	private static final long serialVersionUID = 1L;
	
	public String id;
	public String businessName;
	public String ownerName;
	public List<AddressDomain> address;
	public Long contactNo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public List<AddressDomain> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDomain> address) {
		this.address = address;
	}
	public Long getContactNo() {
		return contactNo;
	}
	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}
}
