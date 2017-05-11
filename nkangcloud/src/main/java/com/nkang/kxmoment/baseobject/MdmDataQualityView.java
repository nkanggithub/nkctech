package com.nkang.kxmoment.baseobject;

import java.text.DecimalFormat;

public class MdmDataQualityView {
	private int numberOfEmptyCityArea;
	private int numberOfThreeGrade;
	private int numberOfNonGeo;
	private int numberOfCustomer;
	private int numberOfPartner;
	private int numberOfCompetitor;
	private int numberOfOppt;
	private int numberOfLeads;
	private String percents;
	

	
	
	public String getPercents() {
		return percents;
	}
	public void setPercents(String percents) {
		this.percents = percents;
	}
	public int getNumberOfLeads() {
		return numberOfLeads;
	}
	public void setNumberOfLeads(int numberOfLeads) {
		this.numberOfLeads = numberOfLeads;
	}
	public int getNumberOfOppt() {
		return numberOfOppt;
	}
	public void setNumberOfOppt(int numberOfOppt) {
		this.numberOfOppt = numberOfOppt;
	}
	public int getNumberOfEmptyCityArea() {
		return numberOfEmptyCityArea;
	}
	public void setNumberOfEmptyCityArea(int numberOfEmptyCityArea) {
		this.numberOfEmptyCityArea = numberOfEmptyCityArea;
	}
	public int getNumberOfThreeGrade() {
		return numberOfThreeGrade;
	}
	public void setNumberOfThreeGrade(int numberOfThreeGrade) {
		this.numberOfThreeGrade = numberOfThreeGrade;
	}
	public int getNumberOfNonGeo() {
		return numberOfNonGeo;
	}
	public void setNumberOfNonGeo(int numberOfNonGeo) {
		this.numberOfNonGeo = numberOfNonGeo;
	}
	public int getNumberOfCustomer() {
		return numberOfCustomer;
	}
	public void setNumberOfCustomer(int numberOfCustomer) {
		this.numberOfCustomer = numberOfCustomer;
	}
	public int getNumberOfPartner() {
		return numberOfPartner;
	}
	public void setNumberOfPartner(int numberOfPartner) {
		this.numberOfPartner = numberOfPartner;
	}
	public int getNumberOfCompetitor() {
		return numberOfCompetitor;
	}
	public void setNumberOfCompetitor(int numberOfCompetitor) {
		this.numberOfCompetitor = numberOfCompetitor;
	}
}
