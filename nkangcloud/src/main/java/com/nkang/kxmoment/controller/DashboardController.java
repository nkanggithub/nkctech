package com.nkang.kxmoment.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nkang.kxmoment.baseobject.DashboardStatus;
import com.nkang.kxmoment.service.DashboardService;

@Controller
@RequestMapping("/Dashboard")
public class DashboardController {
	

	@RequestMapping("/saveStatus")
	@ResponseBody
	public String saveStatus(DashboardStatus statusVo){
		return DashboardService.saveStatus(statusVo);
	}
	
	@RequestMapping("/findAllStatusList")
	@ResponseBody
	public List<DashboardStatus> findAllStatusList(){
		return DashboardService.findAllStatusList();
	}
	
	@RequestMapping("/getStatus")
	@ResponseBody
	public DashboardStatus getStatus(String type){
		return DashboardService.getStatus(type);
	}
	
}
