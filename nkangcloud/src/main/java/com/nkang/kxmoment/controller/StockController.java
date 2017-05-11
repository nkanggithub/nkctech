package com.nkang.kxmoment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StockController {
	

	@RequestMapping("/getStockCode")
	@ResponseBody
	public String getStockCode(HttpServletRequest request, HttpServletResponse response){
		
		return null;
	}
	
	@RequestMapping("/getCodes")
	@ResponseBody
	public List<String> getStockCodes(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//System.out.println(GetAllStock.getAllStockCodesFromLocal());
		System.out.println(GetAllStock.getStockCodes());
		return GetAllStock.codes;
	}
}
