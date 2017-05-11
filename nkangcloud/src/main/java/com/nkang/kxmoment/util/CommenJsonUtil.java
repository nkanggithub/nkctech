package com.nkang.kxmoment.util;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>Description:JSON解析工具类</p>
 */
public class CommenJsonUtil {

	public static JSONObject jsonToObject(String json) throws JSONException {
		JSONObject jsonData = new JSONObject(json);
		return jsonData;
	}
	
	/**
	 * <p>Description:将Java对象转换成json串</p>
	 * @throws JSONException
	 */
	public static String objectToJson(Object javaObj) throws JSONException {
		JSONObject jsonData = new JSONObject(javaObj);
		return jsonData.toString();
	}
	
	/**
	 * <p>Description:将map对象转换成json串</p>
	 * @throws JSONException
	 */
	public static String mapToJson(Map<?, ?> jsonMap) {
		JSONObject jsonData = new JSONObject(jsonMap);
		return jsonData.toString();
	}
	
	/**
	 * json有序转换为map
	 * @throws IOException
	 */
	public static Map<String,LinkedHashMap<String, Object>> json2map(String json) throws JsonParseException, JsonMappingException, IOException{
		 ObjectMapper objectMapper = new ObjectMapper();
		 return objectMapper.readValue(json, LinkedHashMap.class);
	}
	
	/**
	 * <p>Description: 将集合转成JSON字符串</p>
	 * @return
	 */
	public static String collectionToJson(Collection collect) {
		JSONArray arr = new JSONArray(collect);
		return arr.toString();
	}
	
	/**
	 * <p>Description:根据JSONObject转化为JSONArray数组对象</p>
	 * @throws JSONException
	 */
	public static JSONArray jsonToArray(JSONObject jsonArray, String key) throws JSONException {
		JSONArray jsonArrayData = jsonArray.getJSONArray(key);
		return jsonArrayData;
	}
	
	/**
	 * <p>Description:根据json串，转化为JSONArray数组对象</p>
	 * @throws JSONException
	 */
	public static JSONArray jsonToArray(String key, String json) throws JSONException{
		
		JSONObject jsonArray = new JSONObject(json);
		JSONArray jsonArrayData = jsonArray.getJSONArray(key);
		return jsonArrayData;
	}
	
	/**
	 * <p>Description:根据JSON串和节点名称(key)获得json串中对应key的值</p>
	 * @return String value 返回对应节点的值
	 */
	/*public static String getValueByKeyFromJson(String json, String key) {
		
		if(StringUtils.isBlank(json) || StringUtils.isBlank(key)) 
			return "";
		
		if(!isJson(json)) {
			System.out.println("不是JSON串....");
			return "";
		}
		
		String value;
		try {
			JSONObject obj = CommenJsonUtil.jsonToObject(json);
			value = obj.getString(key);
			return value;
		} catch (JSONException e) {
			//e.printStackTrace();
			return "";
		}
	}
	*/
	/**
	 * <p>Description: 判断是否是json串</p>
	 * @author cs
	 * @return true if source is a json String, false if source is not a json
	 */
	public static boolean isJson(String source) {
		
		if(null == source || "".equals(source)) {
			return false;
		} else if(source.startsWith("{") && source.endsWith("}")) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
}
