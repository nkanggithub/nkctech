package com.nkang.kxmoment.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.nkang.kxmoment.baseobject.DashboardStatus;
import com.nkang.kxmoment.baseobject.WeChatMDLUser;
import com.nkang.kxmoment.util.MongoDBBasic;
import com.nkang.kxmoment.util.RestUtils;

/**
 * Dashboard Service 
 * @author xue-ke.du
 *
 */
public class DashboardService {
	private static Logger logger = Logger.getLogger(DashboardService.class);
	private static DB db = MongoDBBasic.getMongoDB();
	private static DBCollection statusCollection = db.getCollection("DashboardStatus");
	
	/**
	 * Save or Update(if exists status of type)
	 * @param statusVo
	 * @return
	 */
	public static String saveStatus(DashboardStatus statusVo) {
		String status = "fail";
		try {
			String statusStr = (String)statusVo.getStatus();
			statusStr = statusStr.replaceAll("\\\"", "\"");
			JSONArray obj = new JSONArray(statusStr);
			statusVo.setStatus(obj);
			DBObject dbObj = BasicDBObject.parse(statusVo.toString());
			DBObject query = new BasicDBObject();
			query.put("type", statusVo.getType());
			// insert or update
			statusCollection.update(query, dbObj, true, false);
			status = "success";
			if(statusStr.toUpperCase().indexOf("DOWN")!=-1){
				List<WeChatMDLUser> allUser = MongoDBBasic.getWeChatUserFromMongoDB("");
				String content="产品运维团队，请立即查看该服务器异常并及时沟通。";
				String title=" 生产环境服务器出现异常，请立即采取措施！！！";
				for(int i=0;i<allUser.size();i++){
					 RestUtils.sendQuotationToUser(allUser.get(i),content,"https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000EBM2m&oid=00D90000000pkXM","【"+allUser.get(i).getNickname()+"】"+title,"http://shenan.duapp.com/mdm/DashboardStatus.jsp?UID=");
				 } 
			}
		} catch (Exception e) {
			logger.error("Save Status fail.", e);
			status = "fail";
		}
		return status;
	}
	
	
	/**
	 * Get KM list
	 * @return
	 */
	public static List<DashboardStatus> findAllStatusList() {
		List<DashboardStatus> list = null;
		try {
			DBCursor cursor = statusCollection.find();
			if(cursor.length() > 0){
				list = new ArrayList<DashboardStatus>(cursor.size());
			}
			Iterator<DBObject> iterator = cursor.iterator();
			while (iterator.hasNext()) {
				DBObject dbObject = (DBObject) iterator.next();
				DashboardStatus statusVo =  DashboardStatus.gson.fromJson(dbObject.toString(), DashboardStatus.class);
				list.add(statusVo);
			}
		} catch (Exception e) {
			logger.error("Find KM List fail.", e);
			list = null;
		}
		return list;
	}

	/**
	 * get status detail
	 * @param type
	 * @return
	 */
	public static DashboardStatus getStatus(String type) {
		DashboardStatus statusVo = null;
		try {
			DBObject query = new BasicDBObject();
			query.put("type", new ObjectId(type));
			DBCursor cursor = statusCollection.find(query);
			Iterator<DBObject> iterator = cursor.iterator();
			if (iterator.hasNext()) {
				DBObject dbObject = (DBObject) iterator.next();
				statusVo = DashboardStatus.gson.fromJson(dbObject.toString(), DashboardStatus.class);
			}
		} catch (Exception e) {
			logger.error("Get status Detail fail.", e);
			statusVo = null;
		}
		return statusVo;
	}
}
