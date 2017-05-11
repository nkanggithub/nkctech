package com.nkang.kxmoment.baseobject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Subject Matter Expert
 * @author duxueke
 *
 */
public class SMEVo {
    public static Gson gson = new GsonBuilder().create();
	private String name;
	private String role;
	private String phone;
	private String email;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
    public String toString() {
        return gson.toJson(this);
    }
}
