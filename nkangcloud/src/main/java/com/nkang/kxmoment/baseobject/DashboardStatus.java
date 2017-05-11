package com.nkang.kxmoment.baseobject;

import java.util.Date;

import com.google.gson.Gson;

/**
 * MDM DashBoard Status
 * @author DXK
 *
 */
public class DashboardStatus {
	public static Gson gson = new Gson();
	
	public DashboardStatus(){
		updateAt = new Date();
	}
	
	private String type;
	private Object status;
	private Date updateAt = null;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}
	
	@Override
	public String toString() {
		return gson.toJson(this);
	}
}
