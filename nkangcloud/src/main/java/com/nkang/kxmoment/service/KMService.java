package com.nkang.kxmoment.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.nkang.kxmoment.baseobject.KMVo;
import com.nkang.kxmoment.util.Constants;
import com.nkang.kxmoment.util.MongoDBBasic;

/**
 * Knowledge Meta Service Handle
 * @author xue-ke.du
 *
 */
public class KMService {
	private static Logger logger = Logger.getLogger(KMService.class);
	private static DB db = MongoDBBasic.getMongoDB();
	private static DBCollection kmCollection = db.getCollection(Constants.MONGO_CLN_NAMES.KM);
	
	/**
	 * Save or Update(if exists id)
	 * @param kmVo
	 * @return
	 */
	public static String saveKM(KMVo kmVo) {
		String status = "fail";
		try {
			DBObject dbObj = BasicDBObject.parse(kmVo.toString());
			if(kmVo.getId() != null && kmVo.getId().length() > 0){
				DBObject query = new BasicDBObject();
				query.put("_id", new ObjectId(kmVo.getId()));
				dbObj.removeField("id");
				// update
				kmCollection.update(query, dbObj, true, false);
			}else{
				// insert 
				kmCollection.insert(dbObj);	
			}
			status = "success";
		} catch (Exception e) {
			logger.error("Save KM fail.", e);
			status = "fail";
		}
		return status;
	}
	
	/**
	 * Get KM list
	 * @return
	 */
	public static List<KMVo> findKMList(String[] keywordArr) {
		List<KMVo> kmList = null;
		try {
			String keyword = null;
			if(keywordArr.length == 1){
				keyword = keywordArr[0];
			}else{
				keyword = "(";
				for(String key : keywordArr){
					if(key.length()<=0){
						continue;
					}
					keyword += key + "|";
				}
				keyword = keyword.substring(0, keyword.length()-1) + ")";	
			}
			DBObject query = new BasicDBObject();
			Pattern pattern = Pattern.compile("^.*" + keyword + ".*$", Pattern.CASE_INSENSITIVE);
			query.put("$or", new BasicDBObject[]{
					new BasicDBObject("title", pattern),
					new BasicDBObject("tags", pattern),
					new BasicDBObject("desc", pattern),
					new BasicDBObject("SME", pattern)
			});
			DBObject orderBy = new BasicDBObject();
			orderBy.put("rating", -1);
			orderBy.put("count", -1);
			DBCursor cursor = kmCollection.find(query).sort(orderBy);
			if(cursor.length() > 0){
				kmList = new ArrayList<KMVo>();
			}
			Iterator<DBObject> iterator = cursor.iterator();
			while (iterator.hasNext()) {
				DBObject dbObject = (DBObject) iterator.next();
				KMVo kmVo = KMVo.json2Vo(dbObject);
				kmList.add(kmVo);
				// Hit count +1
				dbObject.put("count", ((Integer)dbObject.get("count"))+1);
				DBObject updtateId = new BasicDBObject();
				updtateId.put("_id", new ObjectId(dbObject.get("_id").toString()));
				// update dbObject
				kmCollection.update(updtateId, dbObject, true, false);
			}
		} catch (Exception e) {
			logger.error("Find KM List fail.", e);
			kmList = null;
		}
		return kmList;
	}

	/**
	 * get KM detail
	 * @param kmID
	 * @return
	 */
	public static KMVo getKMDetail(String kmID) {
		KMVo kmVo = null;
		try {
			DBObject query = new BasicDBObject();
			query.put("_id", new ObjectId(kmID));
			DBCursor cursor = kmCollection.find(query);
			Iterator<DBObject> iterator = cursor.iterator();
			if (iterator.hasNext()) {
				DBObject dbObject = (DBObject) iterator.next();
				kmVo = KMVo.json2Vo(dbObject);
			}
		} catch (Exception e) {
			logger.error("Get KM Detail fail.", e);
			kmVo = null;
		}
		return kmVo;
	}
}
