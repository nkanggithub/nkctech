package com.nkang.kxmoment.baseobject;

import java.util.ArrayList;

public class RoleOfAreaMap {
	private String id;
	private String flag;
	private String name;
	public ArrayList<String> relateLists;
	
	public ArrayList<String> getRelateLists() {
		return relateLists;
	}
	public void setRelateLists(ArrayList<String> relateLists) {
		this.relateLists = relateLists;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
