package com.nkang.kxmoment.baseobject;

import java.util.ArrayList;

public class Teamer{
	public String openid;
	public String suppovisor;
	public String registerDate;
	public String role;
	public String selfIntro;
	public String email;
	public String phone;
	public String point;
	public String like;
	public String groupid;
	public String realName;
	public ArrayList tag;
	public ArrayList getTag() {
		return tag;
	}
	public void setTag(ArrayList list) {
		this.tag = list;
	}
	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSuppovisor() {
		return suppovisor;
	}
	public void setSuppovisor(String suppovisor) {
		this.suppovisor = suppovisor;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSelfIntro() {
		return selfIntro;
	}
	public void setSelfIntro(String selfIntro) {
		this.selfIntro = selfIntro;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getLike() {
		return like;
	}
	public void setLike(String like) {
		this.like = like;
	}
	
	
}
