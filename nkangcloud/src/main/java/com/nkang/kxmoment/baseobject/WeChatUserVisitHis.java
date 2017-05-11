package com.nkang.kxmoment.baseobject;

import java.util.Date;

public class WeChatUserVisitHis {
	private String lat;
	private String lng;
	private Date visitDate;
	public String FAddr;
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getFAddr() {
		return FAddr;
	}
	public void setFAddr(String fAddr) {
		FAddr = fAddr;
	}
	
	
}
