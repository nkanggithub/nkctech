package com.nkang.kxmoment.util;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.nkang.kxmoment.baseobject.Opportunity;

public class CronJob extends TimerTask{
	private static Logger log=Logger.getLogger(CronJob.class);
	public void run(){
		StopWatch sw = new StopWatch();
		sw.start();
		List<Opportunity> Oppts =  new ArrayList<Opportunity>(); 
		Oppts = DBUtils.QueryOppt();
		log.info("Size: " + Oppts.size());
		String status;
		for(int i=0; i< Oppts.size(); i ++){
			try {
				status = RestUtils.getLocationDetailsforOppt(Oppts.get(i).getOpptName(), Oppts.get(i).getOpptCityName(), Oppts.get(i).getOpptID(), Oppts.get(i).getOpptAddress(), Oppts.get(i).getOPSIID(), Oppts.get(i).getCityArea());
				if(status == "302"){
					log.info("BaiDu API Daily Limited: 302");;
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sw.getElapsedMs();
		java.sql.Timestamp cursqlTS = new java.sql.Timestamp(new java.util.Date().getTime()); 
		log.info(cursqlTS +"Batch Process input in total Takes: " + sw);
	}
}
