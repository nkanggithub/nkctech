package com.nkang.kxmoment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.Mongo;
import com.nkang.kxmoment.baseobject.RoleOfAreaMap;
import com.nkang.kxmoment.util.MongoDBBasic;
import com.nkang.kxmoment.util.RestUtils;

@RestController
@RequestMapping("/roleOfAreaMap")
public class RoleOfAreaMapController {
	@RequestMapping(value = "/saveUserKM")
	public static boolean saveUserKM(@RequestParam(value="openid", required=true) String openid,
			@RequestParam(value="kmItem", required=true) String kmItem,
			@RequestParam(value="flag", required=true) String flag) {
		boolean res=false;
		res=MongoDBBasic.saveUserKM(openid, kmItem,flag);
		return res;
	}
	@RequestMapping(value = "/queryUserKM")
	public static List<String> queryUserKM(@RequestParam(value="openid", required=true) String openid) {
		List<String> res=new ArrayList<String>();
		res=MongoDBBasic.queryUserKM(openid);
		return res;
	}
	@RequestMapping("/findList")
	public ArrayList<RoleOfAreaMap> findList(
			@RequestParam(value="flag", required=false) String flag) {
		return MongoDBBasic.QueryRoleOfAreaMap(flag);
	}
	@RequestMapping("/setRoleOfAreaMap")
	public boolean setRoleOfAreaMap() {
		RoleOfAreaMap role=new RoleOfAreaMap();
		role.setId("Role001");
		role.setFlag("Role");
		role.setName("应用负责人 (App Lead)");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role002");
		role.setFlag("Role");
		role.setName("开发协调大师 (SM)");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role003");
		role.setFlag("Role");
		role.setName("产品负责人 (PO/BA)");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role004");
		role.setFlag("Role");
		role.setName("技术专家 (Tech Lead)");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role005");
		role.setFlag("Role");
		role.setName("软件开发工程师");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role006");
		role.setFlag("Role");
		role.setName("软件测试工程师");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role007");
		role.setFlag("Role");
		role.setName("产品运维工程师");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role008");
		role.setFlag("Role");
		role.setName("系统架构师");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		role=new RoleOfAreaMap();
		role.setId("Role009");
		role.setFlag("Role");
		role.setName("质量控制工程师");
		MongoDBBasic.createRoleOfAreaMap(role);
		
		//---------------------------------------------------
		
		RoleOfAreaMap area=new RoleOfAreaMap();
		area.setId("Area001");
		area.setFlag("Area");
		area.setName("Linux 系统操作实践");
		MongoDBBasic.createRoleOfAreaMap(area);

		area=new RoleOfAreaMap();
		area.setId("Area002");
		area.setFlag("Area");
		area.setName("业务系统分析与设计");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area003");
		area.setFlag("Area");
		area.setName("业务问题追踪");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area004");
		area.setFlag("Area");
		area.setName("产品环境重大问题追踪");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area005");
		area.setFlag("Area");
		area.setName("Office 办公软件实践");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area006");
		area.setFlag("Area");
		area.setName("结对编程");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area007");
		area.setFlag("Area");
		area.setName("代码回顾");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area008");
		area.setFlag("Area");
		area.setName("架构设计");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area009");
		area.setFlag("Area");
		area.setName("项目管理讨论");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area010");
		area.setFlag("Area");
		area.setName("数据问题分析");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area011");
		area.setFlag("Area");
		area.setName("团队活动");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area012");
		area.setFlag("Area");
		area.setName("志愿者招募");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area013");
		area.setFlag("Area");
		area.setName("悬赏问题或帮助");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area014");
		area.setFlag("Area");
		area.setName("测试自动化");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area015");
		area.setFlag("Area");
		area.setName("数据库管理");
		MongoDBBasic.createRoleOfAreaMap(area);
		
		area=new RoleOfAreaMap();
		area.setId("Area016");
		area.setFlag("Area");
		area.setName("DevOps");
		MongoDBBasic.createRoleOfAreaMap(area);
		return true;
	}
}
