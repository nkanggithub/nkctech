package com.nkang.kxmoment.baseobject;

public class Radar {

	public String axis;
	public double value;
	public double count;
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public String getAxis() {
		return axis;
	}
	public void setAxis(String axis) {
		this.axis = axis;
	}
	public Radar(String axis, double value,double count) {
		super();
		this.axis = axis;
		this.value = value;
		this.count=count;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
}
