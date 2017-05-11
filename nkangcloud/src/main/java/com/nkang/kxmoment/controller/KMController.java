package com.nkang.kxmoment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nkang.kxmoment.baseobject.KMVo;
import com.nkang.kxmoment.baseobject.SMEVo;
import com.nkang.kxmoment.service.KMService;

/**
 * Knowledge Meta Controller
 * @author xue-ke.du
 *
 */
@Controller
@RequestMapping("/KMeta")
public class KMController {
	@RequestMapping(value="/saveKM", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveKM(HttpServletRequest request, HttpServletResponse response, KMVo kmVo, @RequestParam("SMETmp") String smeTmpStr){
		allowCrossDomain(request, response);
		Map<String, Object> respParms = new HashMap<String, Object>();
		Gson gson = new GsonBuilder().create();
		SMEVo[] smes = gson.fromJson(smeTmpStr, SMEVo[].class);
		kmVo.setSME(smes);
		String status = KMService.saveKM(kmVo);
		respParms.put("code", "200");
		respParms.put("data", status);
		return respParms;
	} 
	
	@RequestMapping("/readKMList")
	@ResponseBody
	public Map<String, Object> readKMList(HttpServletRequest request, HttpServletResponse response, String keyword){
		allowCrossDomain(request, response);
		Map<String, Object> respParms = new HashMap<String, Object>();
		boolean flag = true;
		String[] keywordArr = null;
		if(keyword==null || keyword.length()<=0){
			flag = false;
		}else{
			keywordArr = keyword.split("\\s+");
			if(keywordArr.length <= 0){
				flag = false;
			}
		}
		if(!flag){
			respParms.put("code", "901");
			respParms.put("message", "Please fill in keyword");
		}else{
			List<KMVo> kmVos = KMService.findKMList(keywordArr);
			if(kmVos == null){
				kmVos = new ArrayList<KMVo>();	
			}
			respParms.put("code", "200");
			respParms.put("data", kmVos);
		}
		return respParms;
	}
	
	@RequestMapping("/readKMDetail")
	@ResponseBody
	public Map<String, Object> readKMItem(HttpServletRequest request, HttpServletResponse response, String id){
		allowCrossDomain(request, response);
		Map<String, Object> respParms = new HashMap<String, Object>();
		KMVo kmVo = KMService.getKMDetail(id);
		respParms.put("code", "200");
		respParms.put("data", kmVo);
		return respParms;
	}
	
	private void allowCrossDomain(HttpServletRequest request, HttpServletResponse response) {
		// 跨域访问设置
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	}
}
