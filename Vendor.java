package com.Hexaware.CMS.Model;

public class Vendor {
	private int venId;
	private String venName;
	private String venPhone;
	private String venSpecs;

	public Vendor(int venId, String venName, String venPhone, String venSpecs) {
		this.venId = venId;
		this.venName = venName;
		this.venPhone = venPhone;
		this.venSpecs = venSpecs;
	}

	public int getVenId() {
		return venId;
	}

	public void setVenId(int venId) {
		this.venId = venId;
	}

	public String getVenName() {
		return venName;
	}

	public void setVenName(String venName) {
		this.venName = venName;
	}

	public String getVenPhone() {
		return venPhone;
	}

	public void setVenPhone(String venPhone) {
		this.venPhone = venPhone;
	}

	public String getVenSpecs() {
		return venSpecs;
	}

	public void setVenSpecs(String venSpecs) {
		this.venSpecs = venSpecs;
	}

	@Override
	public String toString() {
		return "Vendor---> Vendor Id : " + venId + ", Vendor Name : " + venName + ", Vendor Phone : " + venPhone
				+ ", Category : " + venSpecs + "]";
	}

}