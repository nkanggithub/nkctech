package com.nkang.kxmoment.baseobject;

public class ExtendedOpportunity extends Opportunity implements Comparable {
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}


	public int compareTo(Object obj) {
		int flag = 0 ;
		if( obj instanceof ExtendedOpportunity ) {
			ExtendedOpportunity eo2 = (ExtendedOpportunity)obj ;
			if(getDistance() > eo2.getDistance()) {
				flag = 1;
			}else if (getDistance() < eo2.getDistance()) {
				flag = -1 ;
			}else {
				flag = 0 ;
			}
			
		}
		return flag;
	}
	
	
	
	
	
	
	
}
