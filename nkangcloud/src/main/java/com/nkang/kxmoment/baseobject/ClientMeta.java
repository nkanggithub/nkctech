package com.nkang.kxmoment.baseobject;

import java.util.ArrayList;

public class ClientMeta {
	private String ClientCopyRight;
	private String ClientLogo;
	private String ClientName;
	private String ClientSubName;
	private String clientThemeColor;
	private String clientStockCode;
	private String clientActive;
	private ArrayList<?> SkimNum;
	private ArrayList<?> Slide;
	private String metricsMapping;
	
	
	public String getMetricsMapping() {
		return metricsMapping;
	}
	public ArrayList<?> getSkimNum() {
		return SkimNum;
	}
	public void setSkimNum(ArrayList<?> skimNum) {
		SkimNum = skimNum;
	}
	public void setMetricsMapping(String metricsMapping) {
		this.metricsMapping = metricsMapping;
	}
	public ArrayList<?> getSlide() {
		return Slide;
	}
	public void setSlide(ArrayList<?> slide) {
		Slide = slide;
	}
	public String getClientThemeColor() {
		return clientThemeColor;
	}
	public void setClientThemeColor(String clientThemeColor) {
		this.clientThemeColor = clientThemeColor;
	}
	public String getClientStockCode() {
		return clientStockCode;
	}
	public void setClientStockCode(String clientStockCode) {
		this.clientStockCode = clientStockCode;
	}
	public String getClientActive() {
		return clientActive;
	}
	public void setClientActive(String clientActive) {
		this.clientActive = clientActive;
	}
	public String getClientCopyRight() {
		return ClientCopyRight;
	}
	public void setClientCopyRight(String clientCopyRight) {
		ClientCopyRight = clientCopyRight;
	}
	public String getClientLogo() {
		return ClientLogo;
	}
	public void setClientLogo(String clientLogo) {
		ClientLogo = clientLogo;
	}
	public String getClientName() {
		return ClientName;
	}
	public void setClientName(String clientName) {
		ClientName = clientName;
	}
	public String getClientSubName() {
		return ClientSubName;
	}
	public void setClientSubName(String clientSubName) {
		ClientSubName = clientSubName;
	}

}
