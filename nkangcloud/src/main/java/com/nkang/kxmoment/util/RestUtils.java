package com.nkang.kxmoment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nkang.kxmoment.baseobject.ArticleMessage;
import com.nkang.kxmoment.baseobject.ClientInformation;
import com.nkang.kxmoment.baseobject.ClientMeta;
import com.nkang.kxmoment.baseobject.CongratulateHistory;
import com.nkang.kxmoment.baseobject.GeoLocation;
import com.nkang.kxmoment.baseobject.MdmDataQualityView;
import com.nkang.kxmoment.baseobject.Notification;
import com.nkang.kxmoment.baseobject.OrgCountryCode;
import com.nkang.kxmoment.baseobject.OrgOtherPartySiteInstance;
import com.nkang.kxmoment.baseobject.WeChatMDLUser;
import com.nkang.kxmoment.baseobject.WeChatUser;
import com.nkang.kxmoment.util.Constants;

public class RestUtils {
	private static Logger log=Logger.getLogger(RestUtils.class);
	private static final  double EARTH_RADIUS = 6371000; 
	private static String localInd = "N";
	private static Map<String,OrgCountryCode> OrgCountryCodeMap;
	public static String getAccessKey() {
			String url = "https://"+Constants.wechatapihost+"/cgi-bin/token?grant_type=client_credential&appid="+ Constants.APP_ID+ "&secret=" + Constants.APPSECRET;
			String accessToken = null;
			String expires_in = null;
			try {
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           } 
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           String message = new String(jsonBytes, "UTF-8");
		           JSONObject demoJson = new JSONObject(message);
		           accessToken = demoJson.getString("access_token");
		           expires_in = demoJson.getString("expires_in");
		           //DBUtils.updateAccessKey(accessToken, expires_in);
		           MongoDBBasic.updateAccessKey(accessToken, expires_in);
		           is.close();
		       } catch (Exception e) {
		    	   e.printStackTrace();
		       } 
			return accessToken;
	}
	public static List<String> getWeChatUserListID(String akey){
		List<String> listOfOpenID = new ArrayList<String>();
		String url = "https://"+Constants.wechatapihost+"/cgi-bin/user/get?access_token="+ akey;
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           String message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           if(demoJson.has("data")){
	        	   JSONObject Resultdata = demoJson.getJSONObject("data");
		           if(Resultdata.has("openid")){
		        	   String openIDs = Resultdata.getString("openid");
		        	   String str = openIDs.substring(1, openIDs.length()-1);
		        	   String [] strArray = str.split(",");
		        	   for(String s : strArray){
		        		   listOfOpenID.add(s);
		        	   }
		           }
	           }
	           is.close();
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       } 
		return listOfOpenID;
	}
	public static WeChatUser getWeChatUserInfo(String akey, String openID){
		WeChatUser wcu = new WeChatUser();
		
		String url = "https://"+Constants.wechatapihost+"/cgi-bin/user/info?access_token="+ akey +"&openid="+ openID;
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           String message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           String subscribe 	= demoJson.getString("subscribe");
	           String openid 		= demoJson.getString("openid");
	           String nickname		= demoJson.getString("nickname");
	           String sex			= demoJson.getString("sex");
	           String city			= demoJson.getString("city");
	           String language		= demoJson.getString("language");
	           String province		= demoJson.getString("province");
	           String headimgurl	= demoJson.getString("headimgurl");
	           String subscribe_time= demoJson.getString("subscribe_time");
	           String remark		= demoJson.getString("remark");
	           String groupid		= demoJson.getString("groupid");
	           wcu.setCity(city);
	           wcu.setGroupid(groupid);
	           wcu.setHeadimgurl(headimgurl);
	           wcu.setLanguage(language);
	           wcu.setNickname(nickname);
	           wcu.setOpenid(openid);
	           wcu.setProvince(province);
	           wcu.setRemark(remark);
	           wcu.setSex(sex);
	           wcu.setSubscribe(subscribe);
	           wcu.setSubscribe_time(subscribe_time);
	           
	           WeChatUser wcutmp = MongoDBBasic.queryWeChatUser(openID);
	           wcu.setLat(wcutmp.getLat());
	           wcu.setLng(wcutmp.getLng());
	           
	           is.close();
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       } 
		return wcu;
	}

	
	
	public static String  getWeatherInform(String cityName){ 
         String baiduUrl = "http://"+Constants.baiduapihost+"/telematics/v3/weather?location=??&output=json&ak=75cXdwpimZ6GaFMMdQj20GvS";  
         StringBuffer strBuf;  
         try {                              
             baiduUrl = "http://"+Constants.baiduapihost+"/telematics/v3/weather?location="+URLEncoder.encode(cityName, "utf-8")+"&output=json&ak=75cXdwpimZ6GaFMMdQj20GvS";                    
         } catch (UnsupportedEncodingException e1) {               
             e1.printStackTrace();                     
         }  
         strBuf = new StringBuffer();  
         try{  
             URL url = new URL(baiduUrl);  
             if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	         } 
	         System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	         System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	         URLConnection conn = url.openConnection();
             BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//???  
             String line = null;  
             while ((line = reader.readLine()) != null)  
                 strBuf.append(line + " ");  
                 reader.close();  
         }catch(MalformedURLException e) {  
             e.printStackTrace();   
         }catch(IOException e){  
             e.printStackTrace();   
         }     

         return strBuf.toString();  
     }  
    public static String getUserCurLocStrWithLatLng(String lat, String lng){
    	String ret = "";
    	String url =  "http://"+Constants.baiduapihost+"/geocoder/v2/?ak=" + Constants.BAIDU_APPKEY + "&location=" + lat + "," + lng +"&output=json";
    	String message="";
    	try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           if(demoJson.has("result")){
	        	   JSONObject JsonFormatedLocation = demoJson.getJSONObject("result");
		           ret = JsonFormatedLocation.getString("formatted_address");
	           }
	           is.close();
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       }
		return message;
    }    
    public static String getUserCurLocWithLatLng(String lat, String lng){
    	String ret = "";
    	String url =  "http://"+Constants.baiduapihost+"/geocoder/v2/?ak=" + Constants.BAIDU_APPKEY + "&location=" + lat + "," + lng +"&output=json";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           String message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           if(demoJson.has("result")){
	        	   JSONObject JsonFormatedLocation = demoJson.getJSONObject("result");
		           ret = JsonFormatedLocation.getString("formatted_address");
	           }
	           is.close();
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       }
		return ret;
    }
    
    public static List<String> getUserCurLocWithLatLngV2(String lat, String lng){
    	List<String> ret = new ArrayList<String>();
    	String url = "http://"+Constants.baiduapihost+"/geocoder/v2/?ak=" + Constants.BAIDU_APPKEY + "&location=" + lat + "," + lng +"&output=json";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           String message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           if(demoJson.has("result")){
	        	   JSONObject JsonResult = demoJson.getJSONObject("result");
	        	   
	        	   if(JsonResult.has("addressComponent")){
	        		   JSONObject addressComponent = JsonResult.getJSONObject("addressComponent");
	        		   if(addressComponent.has("country")){
	        			   ret.add(addressComponent.getString("country"));
	        		   }
	        		   else{
	        			   ret.add("country");
	        		   }
	        		   if(addressComponent.has("country_code")){
	        			   ret.add(addressComponent.getString("country_code"));
	        		   }
	        		   else{
	        			   ret.add("country_code");
	        		   }
	        		   if(addressComponent.has("province")){
	        			   ret.add(addressComponent.getString("province"));
	        		   }
	        		   else{
	        			   ret.add("province");
	        		   }

	        		   if(addressComponent.has("city")){
	        			   ret.add(addressComponent.getString("city"));
	        		   }
	        		   else{
	        			   ret.add("city");
	        		   }
	        		   if(addressComponent.has("district")){
	        			   ret.add(addressComponent.getString("district"));
	        		   }
	        		   else{
	        			   ret.add("district");
	        		   }
	        		   if(addressComponent.has("street")){
	        			   ret.add(addressComponent.getString("street"));
	        		   }
	        		   else{
	        			   ret.add("street");
	        		   }
	        		   if(addressComponent.has("street_number")){
	        			   ret.add(addressComponent.getString("street_number"));
	        		   }
	        		   else{
	        			   ret.add("street_number");
	        		   }
	        		   if(addressComponent.has("direction")){
	        			   ret.add(addressComponent.getString("direction"));
	        		   }
	        		   else{
	        			   ret.add("direction");
	        		   }
	        		   if(addressComponent.has("distance")){
	        			   ret.add(addressComponent.getString("distance"));
	        		   }
	        		   else{
	        			   ret.add("distance");
	        		   }
	        	   }
		           //ret = JsonFormatedLocation.getString("formatted_address");
	           }
	           is.close();
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       }
		return ret;
    }
    
    public static List<String> getUserCityInfoWithLatLng(String lat, String lng){
    	String ret = "";
    	List<String> CityComponent =  new ArrayList<String>();
    	String url =  "http://"+Constants.baiduapihost+"/geocoder/v2/?ak=" + Constants.BAIDU_APPKEY + "&location=" + lat + "," + lng +"&output=json";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           String message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           if(demoJson.has("result")){
	        	   JSONObject JsonFormatedLocation = demoJson.getJSONObject("result");
		           ret = JsonFormatedLocation.getString("formatted_address");
		           JSONObject JsonAddressComponent = JsonFormatedLocation.getJSONObject("addressComponent");
		           CityComponent.add(JsonAddressComponent.getString("province"));
		           CityComponent.add(JsonAddressComponent.getString("city"));
		           CityComponent.add(JsonAddressComponent.getString("district"));
	           }
	           is.close();
	       } catch (Exception e) {
	    	   e.printStackTrace();
	       }
		return CityComponent;
    }
    
    public static String createMenu(String access_token) {
        //String menu = "{\"button\":[{\"name\":\"Near By\",\"sub_button\":[{\"type\":\"click\",\"name\":\"Opportunity\",\"key\":\"nboppt\"},{\"type\":\"click\",\"name\":\"Customer\",\"key\":\"nbcust\"},{\"type\":\"click\",\"name\":\"Competitor\",\"key\":\"nbcompe\"},{\"type\":\"click\",\"name\":\"Partner\",\"key\":\"nbpartner\"}]},{\"name\":\"MDG\",\"sub_button\":[{\"type\":\"click\",\"name\":\"Data Lake\",\"key\":\"MDLAKE\"},{\"type\":\"click\",\"name\":\"Customer Care\",\"key\":\"05_SIGN\"}]}]}";
    	String menu = "{\"button\":[{\"name\":\"发现附近\",\"sub_button\":[{\"type\":\"click\",\"name\":\"附近同事\",\"key\":\"nbcolleague\"},{\"type\":\"click\",\"name\":\"我的机遇\",\"key\":\"nboppt\"},{\"type\":\"click\",\"name\":\"我的竞争\",\"key\":\"nbcompe\"},{\"type\":\"click\",\"name\":\"我的伙伴\",\"key\":\"nbpartner\"}]},{\"name\":\"数据可视\",\"sub_button\":[{\"type\":\"click\",\"name\":\"数据湖\",\"key\":\"MDLAKE\"},{\"type\":\"click\",\"name\":\"产品运维\",\"key\":\"opsmetric\"}]},{\"name\":\"应用入口\",\"sub_button\":[{\"type\":\"click\",\"name\":\"我的应用\",\"key\":\"MYAPPS\"},{\"type\":\"click\",\"name\":\"我的荣誉\",\"key\":\"MYRECOG\"},{\"type\":\"click\",\"name\":\"我的订阅\",\"key\":\"mysubscription\"}]}]}"; String action = "https://"+Constants.wechatapihost+"/cgi-bin/menu/create?access_token="+access_token;
          try {
             URL url = new URL(action);
             HttpURLConnection http =   (HttpURLConnection) url.openConnection();    
             http.setRequestMethod("POST");        
             http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
             http.setDoOutput(true);        
             http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
             System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
             System.setProperty("sun.net.client.defaultReadTimeout", "30000");
             http.connect();
             OutputStream os= http.getOutputStream();    
             os.write(menu.getBytes("UTF-8"));
             os.flush();
             os.close();
             InputStream is =http.getInputStream();
             int size =is.available();
             byte[] jsonBytes =new byte[size];
             is.read(jsonBytes);
             String message=new String(jsonBytes,"UTF-8");
             return "Create Menu Success";
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }    
          return "Create Menu Failed";
     }

    public static String deleteMenu(String access_token)
     {
         String action = "https://"+Constants.wechatapihost+"/cgi-bin/menu/delete? access_token="+access_token;
         try {
            URL url = new URL(action);
            HttpURLConnection http =   (HttpURLConnection) url.openConnection();    

            http.setRequestMethod("GET");        
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
            http.setDoOutput(true);        
            http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//????30?
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //????30? 
             http.connect();
            OutputStream os= http.getOutputStream();    
            os.flush();
            os.close();

            InputStream is =http.getInputStream();
            int size =is.available();
            byte[] jsonBytes =new byte[size];
            is.read(jsonBytes);
            String message=new String(jsonBytes,"UTF-8");
            return "deleteMenu:"+message;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
         return "deleteMenu Failed";   
     }
    
    private static double rad(double d)
    {
       return d * Math.PI / 180.0;
    }
    
    public static double GetDistance(double lon1,double lat1,double lon2, double lat2)
    {
       double radLat1 = rad(lat1);
       double radLat2 = rad(lat2);
       double a = radLat1 - radLat2;
       double b = rad(lon1) - rad(lon2);
       double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
       s = s * EARTH_RADIUS;
       s = Math.round(s * 10000) / 10000;
       return s/1000;
    }
    
    
	public static String callMasterDataUpsert(OrgOtherPartySiteInstance opsi) throws UnsupportedEncodingException {
		String url = "http://"+Constants.baehost+"/masterdataupsert?";
		if(opsi != null){
			if(!StringUtils.isEmpty(opsi.getSiteInstanceId())){
				url = url + "siteInstanceId="+URLEncoder.encode(opsi.getSiteInstanceId(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getAddressType())){
				url = url + "&addressType="+URLEncoder.encode(opsi.getAddressType(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getBranchIndicator())){
				url = url + "&branchIndicator="+URLEncoder.encode(opsi.getBranchIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCharScriptCode())){
				url = url + "&charScriptCode="+URLEncoder.encode(opsi.getCharScriptCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCityRegion())){
				url = url + "&cityRegion="+URLEncoder.encode(opsi.getCityRegion(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCountOfEmployee())){
				url = url + "&countOfEmployee="+URLEncoder.encode(opsi.getCountOfEmployee(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCountryCode())){
				url = url + "&countryCode="+URLEncoder.encode(opsi.getCountryCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCountryName())){
				url = url + "&countryName="+URLEncoder.encode(opsi.getCountryName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCountryRegionCode())){
				url = url + "&countryRegionCode="+URLEncoder.encode(opsi.getCountryRegionCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getCountryRegionName())){
				url = url + "&countryRegionName="+URLEncoder.encode(opsi.getCountryRegionName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getDeletionIndicator())){
				url = url + "&deletionIndicator="+URLEncoder.encode(opsi.getDeletionIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getDomesticDuns())){
				url = url + "&domesticDuns="+URLEncoder.encode(opsi.getDomesticDuns(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getDuns())){
				url = url + "&duns="+URLEncoder.encode(opsi.getDuns(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getFocusAccountIndicator())){
				url = url + "&focusAccountIndicator="+URLEncoder.encode(opsi.getFocusAccountIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getGlobalAccountIndicator())){
				url = url + "&globalAccountIndicator="+URLEncoder.encode(opsi.getGlobalAccountIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getGlobalDuns())){
				url = url + "&globalDuns="+URLEncoder.encode(opsi.getGlobalDuns(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getGlobalDunsName())){
				url = url + "&globalDunsName="+URLEncoder.encode(opsi.getGlobalDunsName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getHeadDuns())){
				url = url + "&headDuns="+URLEncoder.encode(opsi.getHeadDuns(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getHeadDunsName())){
				url = url + "&headDunsName="+URLEncoder.encode(opsi.getHeadDunsName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getHyperscaleAccountIndicator())){
				url = url + "&HyperscaleAccountIndicator="+URLEncoder.encode(opsi.getHyperscaleAccountIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIncludePartnerOrgIndicator())){
				url = url + "&includePartnerOrgIndicator="+URLEncoder.encode(opsi.getIncludePartnerOrgIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIndicatorBags())){
				url = url + "&indicatorBags="+URLEncoder.encode(opsi.getIndicatorBags(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIndustrySegmentNames())){
				url = url + "&industrySegmentNames="+URLEncoder.encode(opsi.getIndustrySegmentNames(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIndustryVerticalNames())){
				url = url + "&industryVerticalNames="+URLEncoder.encode(opsi.getIndustryVerticalNames(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIsCompetitor())){
				url = url + "&isCompetitor="+URLEncoder.encode(opsi.getIsCompetitor(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIsGlobalAccount())){
				url = url + "&isGlobalAccount="+URLEncoder.encode(opsi.getIsGlobalAccount(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getIsOutOfBusiness())){
				url = url + "&isOutOfBusiness="+URLEncoder.encode(opsi.getIsOutOfBusiness(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getLanguageCode())){
				url = url + "&languageCode="+URLEncoder.encode(opsi.getLanguageCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getLatinCity())){
				url = url + "&latinCity="+URLEncoder.encode(opsi.getLatinCity(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getLatinStreet1LongName())){
				url = url + "&latinStreet1LongName="+URLEncoder.encode(opsi.getLatinStreet1LongName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getMailingSiteDuns())){
				url = url + "&mailingSiteDuns="+URLEncoder.encode(opsi.getMailingSiteDuns(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getNamedAccountIndicator())){
				url = url + "&namedAccountIndicator="+URLEncoder.encode(opsi.getNamedAccountIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getNonlatinCity())){
				url = url + "&nonlatinCity="+URLEncoder.encode(opsi.getNonlatinCity(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getNonlatinStreet1LongName())){
				url = url + "&nonlatinStreet1LongName="+URLEncoder.encode(opsi.getNonlatinStreet1LongName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOnlyPresaleCustomer())){
				url = url + "&onlyPresaleCustomer="+URLEncoder.encode(opsi.getOnlyPresaleCustomer(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationExtendedName())){
				url = url + "&organizationExtendedName="+URLEncoder.encode(opsi.getOrganizationExtendedName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationId())){
				url = url + "&organizationId="+URLEncoder.encode(opsi.getOrganizationId(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationLegalName())){
				url = url + "&organizationLegalName="+URLEncoder.encode(opsi.getOrganizationLegalName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationNonLatinExtendedName())){
				url = url + "&organizationNonLatinExtendedName="+URLEncoder.encode(opsi.getOrganizationNonLatinExtendedName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationNonLatinLegalName())){
				url = url + "&organizationNonLatinLegalName="+URLEncoder.encode(opsi.getOrganizationNonLatinLegalName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationNonLatinReportingName())){
				url = url + "&organizationNonLatinReportingName="+URLEncoder.encode(opsi.getOrganizationNonLatinReportingName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrganizationReportingName())){
				url = url + "&organizationReportingName="+URLEncoder.encode(opsi.getOrganizationReportingName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrgCountryCode())){
				url = url + "&orgCountryCode="+URLEncoder.encode(opsi.getOrgCountryCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getOrgCountryName())){
				url = url + "&orgCountryName="+URLEncoder.encode(opsi.getOrgCountryName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getParentCountryCode())){
				url = url + "&parentCountryCode="+URLEncoder.encode(opsi.getParentCountryCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getParentOrganizationId())){
				url = url + "&parentOrganizationId="+URLEncoder.encode(opsi.getParentOrganizationId(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getParentOrganizationName())){
				url = url + "&parentOrganizationName="+URLEncoder.encode(opsi.getParentOrganizationName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getPostalCode())){
				url = url + "&postalCode="+URLEncoder.encode(opsi.getPostalCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getPostalCode2())){
				url = url + "&postalCode2="+URLEncoder.encode(opsi.getPostalCode2(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getPresalesId())){
				url = url + "&presalesId="+URLEncoder.encode(opsi.getPresalesId(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getRad())){
				url = url + "&rad="+URLEncoder.encode(opsi.getRad(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getRadBags())){
				url = url + "&radBags="+URLEncoder.encode(opsi.getRadBags(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getRplStatusCode())){
				url = url + "&rplStatusCode="+URLEncoder.encode(opsi.getRplStatusCode(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getRplStatusTime())){
				url = url + "&rplStatusTime="+URLEncoder.encode(opsi.getRplStatusTime(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getSalesCoverageSegments())){
				url = url + "&salesCoverageSegments="+URLEncoder.encode(opsi.getSalesCoverageSegments(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getSiteDuns())){
				url = url + "&siteDuns="+URLEncoder.encode(opsi.getSiteDuns(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getSiteId())){
				url = url + "&siteId="+URLEncoder.encode(opsi.getSiteId(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getSiteName())){
				url = url + "&siteName="+URLEncoder.encode(opsi.getSiteName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getSlsCrgSegmtNameBags())){
				url = url + "&slsCrgSegmtNameBags="+URLEncoder.encode(opsi.getSlsCrgSegmtNameBags(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getState())){
				url = url + "&state="+URLEncoder.encode(opsi.getState(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getStreetAddress1())){
				url = url + "&streetAddress1="+URLEncoder.encode(opsi.getStreetAddress1(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getStreetAddress2())){
				url = url + "&streetAddress2="+URLEncoder.encode(opsi.getStreetAddress2(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getStreetAddress3())){
				url = url + "&streetAddress3="+URLEncoder.encode(opsi.getStreetAddress3(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTargetSegmentNames())){
				url = url + "&targetSegmentNames="+URLEncoder.encode(opsi.getTargetSegmentNames(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTargetSubSegmentNames())){
				url = url + "&targetSubSegmentNames="+URLEncoder.encode(opsi.getTargetSubSegmentNames(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTaxIds())){
				url = url + "&taxIds="+URLEncoder.encode(opsi.getTaxIds(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTgtSegmtNameBags())){
				url = url + "&tgtSegmtNameBags="+URLEncoder.encode(opsi.getTgtSegmtNameBags(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTopAccountIndicator())){
				url = url + "&topAccountIndicator="+URLEncoder.encode(opsi.getTopAccountIndicator(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTopParentOrganizationId())){
				url = url + "&topParentOrganizationId="+URLEncoder.encode(opsi.getTopParentOrganizationId(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getTopParentOrganizationName())){
				url = url + "&topParentOrganizationName="+URLEncoder.encode(opsi.getTopParentOrganizationName(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getWorldRegion())){
				url = url + "&worldRegion="+URLEncoder.encode(opsi.getWorldRegion(),"UTF-8");
			}
			if(!StringUtils.isEmpty(opsi.getWorldRegionPath())){
				url = url + "&worldRegionPath="+URLEncoder.encode(opsi.getWorldRegionPath(),"UTF-8");
			}
			//not from parameter but calculation
/*			if(!StringUtils.isEmpty(opsi.getOrganizationNonLatinExtendedName()) || !StringUtils.isEmpty(opsi.getCityRegion())    ){
				try {
					String latlnginfo = getlatLngwithQuery(opsi.getOrganizationNonLatinExtendedName(), opsi.getCityRegion());
					if(!StringUtils.isEmpty(latlnginfo)){
						String [] latlngarray = latlnginfo.split("-");
						url = url + "&lat="+URLEncoder.encode(latlngarray[0],"UTF-8");
						url = url + "&lng="+URLEncoder.encode(latlngarray[1],"UTF-8");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if(!StringUtils.isEmpty(opsi.getQualityGrade())){
				url = url + "&qualityGrade="+URLEncoder.encode(opsi.getQualityGrade(),"UTF-8");
			}*/
		}
		String message= "errorrrr";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("PUT"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           //JSONObject demoJson = new JSONObject(message);
	           is.close();
	           //System.out.println(message + "- success http ---------" + url);
	       } catch (Exception e) {
	    	   System.out.println("error:::" + message + "failed http ---------" + url);
	    	   return "failed";
	       } 
		return "success";
	}

	public static String callGetValidAccessKey(){
		String url = "http://"+Constants.baehost+"/getValidAccessKey";
		String message="error";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callGetValidAccessKey ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
		return message;
	}
	
	public static MdmDataQualityView callGetDataQualityReport(){
		String url = "http://"+Constants.baehost+"/getDataQualityReport";
		String message="error";
		MdmDataQualityView mdmDataQualityView = new MdmDataQualityView();
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           mdmDataQualityView.setNumberOfCompetitor(Integer.valueOf(demoJson.getString("numberOfCompetitor")) );
	           mdmDataQualityView.setNumberOfCustomer(Integer.valueOf(demoJson.getString("numberOfCustomer")) );
	           mdmDataQualityView.setNumberOfEmptyCityArea(Integer.valueOf(demoJson.getString("numberOfEmptyCityArea")) );
	           mdmDataQualityView.setNumberOfLeads(Integer.valueOf(demoJson.getString("numberOfLeads")) );
	           mdmDataQualityView.setNumberOfNonGeo(Integer.valueOf(demoJson.getString("numberOfNonGeo")) );
	           mdmDataQualityView.setNumberOfOppt(Integer.valueOf(demoJson.getString("numberOfOppt")) );
	           mdmDataQualityView.setNumberOfPartner(Integer.valueOf(demoJson.getString("numberOfPartner")) );
	           mdmDataQualityView.setNumberOfThreeGrade(Integer.valueOf(demoJson.getString("numberOfThreeGrade")) );
	           mdmDataQualityView.setPercents(demoJson.getString("percents"));
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callGetDataQualityReport ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
		return mdmDataQualityView;
	}
	
	/*
	 * author  chang-zheng
	 */
	public static  List<String> getAllStates(String countryCode){
		List<String> ret = new ArrayList<String>();
		//Set<String> ret = new HashSet<String>();
		String message = "false";
		if(localInd == "Y"){
			String url = "http://"+Constants.baehost+"/getCitys?country=";
			try {
		           URL urlGet = new URL(url+countryCode);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }  
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           //String a = message.substring(1, message.length()-1);
		           String [] sp = message.split("\",\"");
		           int cnt = 0;
		           for(String i : sp){
		        	   cnt = cnt + 1;
		        	   
		        	   if(cnt == 1){
		        		   String a  = i.substring(2, i.length());
		        		   if(!a.isEmpty()){
		        			   ret.add(a);
		        		   }
		        	   }
		        	   else if(cnt == sp.length){
		        		   ret.add(i.substring(0, i.length()-1));
		        	   }
		        	   else{
		        		   ret.add(i.trim());
		        	   }
		           }
		           is.close();
		           //ret.add(e;)

		       } catch (Exception e) {
		    	   log.info("error http CallGetFilterNonLatinCityFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{
				ret =  MongoDBBasic.getAllStates(countryCode);
			}
			catch (Exception e) {
		    	   log.info("error DB CallGetFilterNonLatinCityFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		    }
		}
		return ret;
		
	}
	/*
	 * author  chang-zheng
	 */
	public static Map<String, MdmDataQualityView> callGetDataQualityReportByParameter(String stateProvince,List<String> city, String cityRegion){

		String message="error";
		MdmDataQualityView mdmDataQualityView = new MdmDataQualityView();
		List<MdmDataQualityView> ListMqv = new ArrayList<MdmDataQualityView>();
		Map<String, MdmDataQualityView> map = null;
		if(localInd == "Y"){
			try {
					//String url = "http://shenan.duapp.com/getDataQualityReportByParameter?stateProvince="+s+"&"+"nonlatinCity="+c+"&cityRegion="+cr;
					String url = "http://"+Constants.baehost+"/getDataQualityReportByParameterV2?";
					if(stateProvince != "" && stateProvince.toLowerCase() != "null"){
						stateProvince = URLEncoder.encode(stateProvince, "UTF-8");
						url =  url + "stateProvince="+stateProvince;
					}
					if(city != null){
						//city = URLEncoder.encode(city, "UTF-8");
						url =  url + "&nonlatinCity="+city;
					}

				   //String url = "http://shenan.duapp.com/getDataQualityReportByParameter?stateProvince="+s+"&"+"nonlatinCity="+c+"&cityRegion="+cr;
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           System.out.println("============="+message);
		           JSONObject demoJson = new JSONObject(message);
		           mdmDataQualityView.setNumberOfCompetitor(Integer.valueOf(demoJson.getString("numberOfCompetitor")) );
		           mdmDataQualityView.setNumberOfCustomer(Integer.valueOf(demoJson.getString("numberOfCustomer")) );
		           mdmDataQualityView.setNumberOfEmptyCityArea(Integer.valueOf(demoJson.getString("numberOfEmptyCityArea")) );
		           mdmDataQualityView.setNumberOfLeads(Integer.valueOf(demoJson.getString("numberOfLeads")) );
		           mdmDataQualityView.setNumberOfNonGeo(Integer.valueOf(demoJson.getString("numberOfNonGeo")) );
		           mdmDataQualityView.setNumberOfOppt(Integer.valueOf(demoJson.getString("numberOfOppt")) );
		           mdmDataQualityView.setNumberOfPartner(Integer.valueOf(demoJson.getString("numberOfPartner")) );
		           mdmDataQualityView.setNumberOfThreeGrade(Integer.valueOf(demoJson.getString("numberOfThreeGrade")) );
		           mdmDataQualityView.setPercents(demoJson.getString("percents"));
		           is.close();

		       } catch (Exception e) {
		    	   log.info("error callGetDataQualityReport ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{
				//mdmDataQualityView = MongoDBBasic.getDataQualityReport(stateProvince, city,cityRegion);
				map = MongoDBBasic.getDataQualityReport(stateProvince, city,cityRegion);
			}
			catch (Exception e) {
		    	   log.info("error callGetDataQualityReport ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		    }
		}
		return map;
	}
	
	public static MdmDataQualityView callGetDataQualityReportByParameter(String stateProvince, String city, String cityRegion){

		String message="error";
		MdmDataQualityView mdmDataQualityView = new MdmDataQualityView();
		
		if(localInd == "Y"){
			try {
					//String url = "http://shenan.duapp.com/getDataQualityReportByParameter?stateProvince="+s+"&"+"nonlatinCity="+c+"&cityRegion="+cr;
					String url = "http://"+Constants.baehost+"/getDataQualityReportByParameter?";
					if(stateProvince != "" && stateProvince.toLowerCase() != "null"){
						stateProvince = URLEncoder.encode(stateProvince, "UTF-8");
						url =  url + "stateProvince="+stateProvince;
					}
					if(city != "" && city.toLowerCase() != "null"){
						city = URLEncoder.encode(city, "UTF-8");
						url =  url + "&nonlatinCity="+city;
					}
					if(cityRegion != "" && cityRegion.toLowerCase() != "null"){
						cityRegion = URLEncoder.encode(cityRegion, "UTF-8");
						url =  url + "&cityRegion="+cityRegion;
					}

				   //String url = "http://shenan.duapp.com/getDataQualityReportByParameter?stateProvince="+s+"&"+"nonlatinCity="+c+"&cityRegion="+cr;
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           JSONObject demoJson = new JSONObject(message);
		           mdmDataQualityView.setNumberOfCompetitor(Integer.valueOf(demoJson.getString("numberOfCompetitor")) );
		           mdmDataQualityView.setNumberOfCustomer(Integer.valueOf(demoJson.getString("numberOfCustomer")) );
		           mdmDataQualityView.setNumberOfEmptyCityArea(Integer.valueOf(demoJson.getString("numberOfEmptyCityArea")) );
		           mdmDataQualityView.setNumberOfLeads(Integer.valueOf(demoJson.getString("numberOfLeads")) );
		           mdmDataQualityView.setNumberOfNonGeo(Integer.valueOf(demoJson.getString("numberOfNonGeo")) );
		           mdmDataQualityView.setNumberOfOppt(Integer.valueOf(demoJson.getString("numberOfOppt")) );
		           mdmDataQualityView.setNumberOfPartner(Integer.valueOf(demoJson.getString("numberOfPartner")) );
		           mdmDataQualityView.setNumberOfThreeGrade(Integer.valueOf(demoJson.getString("numberOfThreeGrade")) );
		           mdmDataQualityView.setPercents(demoJson.getString("percents"));
		           is.close();

		       } catch (Exception e) {
		    	   log.info("error callGetDataQualityReport ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{
				mdmDataQualityView = MongoDBBasic.getDataQualityReport(stateProvince, city,cityRegion);
			}
			catch (Exception e) {
		    	   log.info("error callGetDataQualityReport ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		    }
		}
		return mdmDataQualityView;
	}
	
	public static GeoLocation callGetDBUserGeoInfo(String OpenId){
		String url = "http://"+Constants.baehost+"/getDBUserGeoInfo?OpenID="+OpenId;
		String message="error";
		GeoLocation geoLocation = new GeoLocation();
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }  
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           geoLocation.setFAddr(demoJson.getString("FAddr"));
	           geoLocation.setLAT(demoJson.getString("LAT"));
	           geoLocation.setLNG(demoJson.getString("LNG"));
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callGetDBUserGeoInfo ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
		return geoLocation;
	}
	
	public static String getlatLngwithQuery(String Query, String Region) throws Exception{
		String latlng = "";
		String url =  "http://"+Constants.baiduapihost+"/place/v2/search?query=" + Query + "&region=" + Region + "&output=json&ak=" + Constants.BAIDU_APPKEY;
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/json");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           } 
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           String message = new String(jsonBytes, "UTF-8");
	           JSONObject demoJson = new JSONObject(message);
	           if(demoJson.has("results")){
		           JSONArray ResultJA = demoJson.getJSONArray("results");
		           if(ResultJA.length() > 0){
		        	   JSONObject placeJO = ResultJA.getJSONObject(0);
		        	   if(placeJO != null &&placeJO.has("location")){
		        		   try{
					           JSONObject locationJO = placeJO.getJSONObject("location");
					           String lng = locationJO.getString("lng");
					           String lat = locationJO.getString("lat");
					           if(lng !="" && lat != ""){
					        	   latlng = lat + "-" + lng;
					           }
		        		   }
		        		   catch(Exception e){
		        			   latlng = "";
		        		   }
		        	   }
		           }
	           }
	           is.close();
	       } catch (Exception e) {
	    	   latlng = "";
	       }
		return latlng;
	}
	
	public static String CallGetJSFirstSegmentArea(){
		String url = "http://"+Constants.baehost+"/getFilterSegmentArea";
		String message="error";
		
		List<String> listOfSegmentArea = new ArrayList<String>();
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }  
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           String a = message.substring(1, message.length()-1);
	           String [] sp = a.split(",");
	           for(String i : sp){
	        	   listOfSegmentArea.add(i.substring(1,  i.length()-1)) ;
	           }
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callGetDBUserGeoInfo ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }

		String RetStr = "";
		String RetStr2 ="";
		String Ret = "";
		for(int i = 0; i < 10 ; i++){
			double num = Math.random();
			RetStr =  RetStr + "{axis:\" " + listOfSegmentArea.get(i) + " \",value:"+ num +"},";
			RetStr2 = RetStr2 + "{axis:\" " + listOfSegmentArea.get(i) + " \",value:"+ (1- num) +"},";
		}
		Ret =  Ret + "[" + RetStr.substring(0, RetStr.length()-1) + "], [" + RetStr2.substring(0, RetStr2.length()-1) + "]";
	    return Ret;
	}
	
	public static List<String> CallGetJSFirstSegmentAreaFromMongo(String cntOfOPSI,String state){
		String url = "http://"+Constants.baehost+"/getFilterSegmentAreaFromMongo?state="+URLEncoder.encode(state);
		String message="error";
		List<String> listOfSegmentArea = new ArrayList<String>();

		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           String a = message.substring(1, message.length()-1);
	           String [] sp = a.split("\",\"");
	           int cnt = 0;
	           for(String i : sp){
	        	   cnt = cnt + 1;
	        	   if(cnt == 1){
	        		   listOfSegmentArea.add(i.substring(1, i.length()));
	        	   }
	        	   else if(cnt == sp.length){
	        		   listOfSegmentArea.add(i.substring(0, i.length()-1));
	        	   }
	        	   else{
	        		   listOfSegmentArea.add(i.trim());
	        	   }
	           }
	           is.close();
	       } catch (Exception e) {
	    	   log.info("error http CallGetJSFirstSegmentAreaFromMongo ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
		return listOfSegmentArea;
	}
	
	public static List<String> CallGetJSFirstSegmentAreaListFromMongo(String state){
		String url = "http://"+Constants.baehost+"/getFilterSegmentAreaFromMongo?state="+URLEncoder.encode(state);;
		String message="error";
		List<String> listOfSegmentArea = new ArrayList<String>();
		
		if(localInd == "Y"){
			try {
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }  
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           String a = message.substring(1, message.length()-1);
		           String [] sp = a.split("\",\"");
		           int cnt = 0;
		           for(String i : sp){
		        	   cnt = cnt + 1;
		        	   if(cnt == 1){
		        		   listOfSegmentArea.add(i.substring(1, i.length()));
		        	   }
		        	   else if(cnt == sp.length){
		        		   listOfSegmentArea.add(i.substring(0, i.length()-1));
		        	   }
		        	   else{
		        		   listOfSegmentArea.add(i.trim());
		        	   }
		           }
		           is.close();
		       } catch (Exception e) {
		    	   log.info("error http CallGetJSFirstSegmentAreaListFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{
				listOfSegmentArea = MongoDBBasic.getFilterSegmentArea(state);
			}
			catch (Exception e) {
		    	   log.info("error DB CallGetJSFirstSegmentAreaListFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		return listOfSegmentArea;
	}
	
	public static String callInsertCommentsFromVisitor(String OpenID, String InputValue){
		String url = "http://"+Constants.baehost+"/insertCommentsFromVisitor?OpenID="+OpenID+"&comments="+InputValue;
		String message = "false";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }  
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callInsertCommentsFromVisitor ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
		return message;
	}
	
	public static String callgetFilterRegionFromMongo(String state){
		String url = "http://"+Constants.baehost+"/getFilterRegionFromMongo?state="+state;
		String message = "false";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }  
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callInsertCommentsFromVisitor ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
		return message;
	}
	
	public static String CallgetFilterTotalOPSIFromMongo(String state, String nonlatinCity, String cityRegion){
		String message = "false";
		if(localInd == "Y"){
			String url = "http://"+Constants.baehost+"/getFilterTotalOPSIFromMongo";
			
			if(state != "" && state!= null && state.toLowerCase()!= "null"){
				state = URLEncoder.encode(state);
				url = url + "?state="+state;
			}
			if(nonlatinCity != "" && nonlatinCity!= null && nonlatinCity.toLowerCase()!= "null"){
				nonlatinCity = URLEncoder.encode(nonlatinCity);
				url = url + "&nonlatinCity="+nonlatinCity;
			}
			if(cityRegion != "" && cityRegion!= null && cityRegion.toLowerCase()!= "null"){
				cityRegion = URLEncoder.encode(cityRegion);
				url = url + "&cityRegion="+cityRegion;
			}
			
			try {
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }  
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           is.close();

		       } catch (Exception e) {
		    	   log.info("error CallgetFilterTotalOPSIFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{
				message = MongoDBBasic.getFilterTotalOPSIFromMongo(state, nonlatinCity, cityRegion);
			}
			catch (Exception e) {
		    	   log.info("error CallgetFilterTotalOPSIFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		    }
		}
		return message;
	}
	
	public static String CallgetFilterCountOnCriteriaFromMongo(String industrySegmentNames, String nonlatinCity, String state, String cityRegion){
		String message = "0";
		//if(true){
		if(localInd == "Y"){
		//	if(localInd == "Y"){
			String parStr = "industrySegmentNames="+URLEncoder.encode(industrySegmentNames);
			parStr = parStr + "&state="+URLEncoder.encode(state);
			String url = "http://"+Constants.baehost+"/getFilterCountOnCriteriaFromMongo?"+parStr;
			try {
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }  
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           is.close();

		       } catch (Exception e) {
		    	   log.info("error http CallgetFilterCountOnCriteriaFromMongo ---------" + e.getMessage());
		    	   //message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{

				message =  MongoDBBasic.getFilterCountOnCriteriaFromMongo(industrySegmentNames, "", state, "");
			}
			catch (Exception e) {
		    	   log.info("error DB CallgetFilterCountOnCriteriaFromMongo ---------" + e.getMessage());
		    	   //message =  "failed with " + e.getMessage();
		    }
		}

		return message;
	}
	
	
	public static List<String> CallGetFilterNonLatinCityFromMongo(String state){
		List<String> ret = new ArrayList<String>();
		String message = "false";
		if(localInd == "Y"){
			String parStr = "state="+URLEncoder.encode(state);
			String url = "http://"+Constants.baehost+"/getFilterNonLatinCityFromMongo?"+parStr;
			try {
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           }  
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           message = new String(jsonBytes, "UTF-8");
		           //String a = message.substring(1, message.length()-1);
		           String [] sp = message.split("\",\"");
		           int cnt = 0;
		           for(String i : sp){
		        	   cnt = cnt + 1;
		        	   if(i.contains(state)){
		   					i = i.replaceAll("\\s+","");
		   					i = i.replaceAll(state, "");
		        	   }
		        	   if(cnt == 1){
		        		   String a  = i.substring(2, i.length());
		        		   if(!a.isEmpty()){
		        			   ret.add(a);
		        		   }
		        	   }
		        	   else if(cnt == sp.length){
		        		   ret.add(i.substring(0, i.length()-1));
		        	   }
		        	   else{
		        		   ret.add(i.trim());
		        	   }
		           }
		           is.close();
		           //ret.add(e;)

		       } catch (Exception e) {
		    	   log.info("error http CallGetFilterNonLatinCityFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		       }
		}
		else{
			try{
				ret =  MongoDBBasic.getFilterNonLatinCitiesFromMongo(state);
			}
			catch (Exception e) {
		    	   log.info("error DB CallGetFilterNonLatinCityFromMongo ---------" + e.getMessage());
		    	   message =  "failed with " + e.getMessage();
		    }
		}

		return ret;
	}

	public static String CallLoadClientIntoMongoDB(String ClientID,
			String ClientIdentifier, String ClientDesc, String WebService) {
		String ret = "";
		String message= "";
		String parStr = "ClientID=" + URLEncoder.encode(ClientID);
		parStr = parStr + "&ClientIdentifier="
				+ URLEncoder.encode(ClientIdentifier);
		parStr = parStr + "&ClientDesc=" + URLEncoder.encode(ClientDesc);
		parStr = parStr + "&WebService=" + URLEncoder.encode(WebService);

		String parStr2 = "{\"ClientID\":\""+ ClientID +"\",\"ClientIdentifier\":\""+ ClientIdentifier +"\", \"ClientDesc\":\""+ ClientDesc +"\",  \"WebService\":\""+ WebService +"\"}";
	    log.info("-----JSON---" + parStr2);
		

		String url = "http://"+Constants.baehost+"/LoadClientIntoMongoDB?" + parStr;
		//String url = "http://"+Constants.baehost+"/LoadClientIntoMongoDB";
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
			http.setRequestMethod("GET"); // must be get request
			http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			if (localInd == "Y") {
				System.setProperty("http.proxyHost", Constants.proxyInfo);
				System.setProperty("http.proxyPort", "8080");
			}
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			message = new String(jsonBytes, "UTF-8");
			// String a = message.substring(1, message.length()-1);
			ret = message;
			is.close();
			// ret.add(e;)

		} catch (Exception e) {
			log.info("error http CallLoadClientIntoMongoDB ---------"
					+ e.getMessage());
			message = "failed with " + e.getMessage();
		}

		return ret;
	}
	
	public static List<ClientInformation> CallGetClientFromMongoDB() {
		List<ClientInformation> ret = new ArrayList<ClientInformation>();
		String message= "";
		String url = "http://"+Constants.baehost+"/CallGetClientFromMongoDB";
		if(localInd == "Y"){
			try {
				URL urlGet = new URL(url);
				HttpURLConnection http = (HttpURLConnection) urlGet
						.openConnection();
				http.setRequestMethod("GET"); // must be get request
				http.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				if (localInd == "Y") {
					System.setProperty("http.proxyHost", Constants.proxyInfo);
					System.setProperty("http.proxyPort", "8080");
				}
				System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
				System.setProperty("sun.net.client.defaultReadTimeout", "30000");
				http.connect();
				InputStream is = http.getInputStream();
				int size = is.available();
				byte[] jsonBytes = new byte[size];
				is.read(jsonBytes);
				message = new String(jsonBytes, "UTF-8");
				// String a = message.substring(1, message.length()-1);

				is.close();
				// ret.add(e;)

			} catch (Exception e) {
				log.info("error http CallLoadClientIntoMongoDB ---------"
						+ e.getMessage());
				message = "failed with " + e.getMessage();
			}
		}
		else{
			ret = MongoDBBasic.CallGetClientFromMongoDB();
		}
		return ret;
	}
	public static  String getMDLUserLists(String openid){
		String urlStr = "http://"+Constants.baehost+"/CallGetWeChatUserFromMongoDB";
		if(openid!=null&&!"".equals(openid)){
			urlStr+="?openid="+openid;
		}
		StringBuffer strBuf =new StringBuffer("");
		try {
			 URL url = new URL(urlStr);  
             if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	         } 
	         System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	         System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	         URLConnection conn = url.openConnection();
             BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//???  
             String line = null;  
             while ((line = reader.readLine()) != null)  
                 strBuf.append(line + " ");  
                 reader.close();  
         }catch(MalformedURLException e) {  
             e.printStackTrace();   
         }catch(IOException e){  
             e.printStackTrace();   
         }     
         return strBuf.toString();  
	}
	public static  String getCallUpdateUserWithSignature(String openid,String svg){
		String urlStr = "http://"+Constants.baehost+"/CallUpdateUserWithSignature";
		try {
			urlStr+="?openid="+openid+"&svg="+URLEncoder.encode(svg, "utf-8");
		} catch (UnsupportedEncodingException e1) {
		}
		StringBuffer strBuf =new StringBuffer("");
		try {
			URL url = new URL(urlStr);  
			if(localInd == "Y"){
				System.setProperty("http.proxyHost", Constants.proxyInfo);  
				System.setProperty("http.proxyPort", "8080");  
			} 
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));//???  
			String line = null;  
			while ((line = reader.readLine()) != null)  
				strBuf.append(line + " ");  
			reader.close();  
		}catch(MalformedURLException e) {  
			e.printStackTrace();   
		}catch(IOException e){  
			e.printStackTrace();   
		}     
		return strBuf.toString();  
	}
	
	//Bit Add Start
		public static String getLocationDetailsforOppt(String Query, String Region, String OpptID, String OpptAddr, String OpsiId, String CityRegion) throws Exception{
			String StatusMessage = "";
			if(Query != null){
				Query = URLEncoder.encode(Query, "UTF-8");
			}
			else{
				Query = "Query";
			}
			if(Region != null){
				Region = URLEncoder.encode(Region, "UTF-8");
			}
			else{
				Region = "China";
			}
			
			String url =  "http://"+Constants.baiduapihost+"/place/v2/search?query=" + Query + "&region=" + Region + "&output=json&ak=" + Constants.BAIDU_APPKEY;
			try {
		           URL urlGet = new URL(url);
		           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
		           http.setRequestMethod("GET"); //must be get request
		           http.setRequestProperty("Content-Type","application/json");
		           http.setDoOutput(true);
		           http.setDoInput(true);
		           if(localInd == "Y"){
			           System.setProperty("http.proxyHost", Constants.proxyInfo);  
			           System.setProperty("http.proxyPort", "8080");  
		           } 
		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
		           http.connect();
		           InputStream is = http.getInputStream();
		           int size = is.available();
		           byte[] jsonBytes = new byte[size];
		           is.read(jsonBytes);
		           String message = new String(jsonBytes, "UTF-8");
		           JSONObject demoJson = new JSONObject(message);
		           if(demoJson.has("results")){
			           JSONArray ResultJA = demoJson.getJSONArray("results");
			           if(ResultJA.length() > 0){
			        	   JSONObject placeJO = ResultJA.getJSONObject(0);
			        	   if(placeJO != null &&placeJO.has("location")){
			        		   try{
						           JSONObject locationJO = placeJO.getJSONObject("location");
						           String lng = locationJO.getString("lng");
						           String lat = locationJO.getString("lat");
						           if(lng !="" && lat != ""){
						        	   DBUtils.updateOppt(lat, lng, OpptID, String.valueOf(ResultJA.length()), OpptAddr, OpsiId, CityRegion);
						           }
			        		   }
			        		   catch(Exception e){
			        			   DBUtils.updateOppt("", "", OpptID, String.valueOf(0), OpptAddr, OpsiId, CityRegion);
			        		   }
			        	   }
			        	   else{
			        		   DBUtils.updateOppt("", "", OpptID, String.valueOf(0), OpptAddr, OpsiId, CityRegion);
			        	   }
			           }
			           else{
			        	   DBUtils.updateOppt("", "", OpptID, String.valueOf(0), OpptAddr, OpsiId, CityRegion);
			           }
		           }
		           else{
		        	   StatusMessage = demoJson.getString("status");
		           }

		           is.close();
		       } catch (Exception e) {
		    	   System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()) + "Exception Occurs in getLocationDetailsforOppt with OPSI: "+ OpsiId +"---" + e.toString());
		    	   e.printStackTrace();
		       }
			return StatusMessage;
		}
		
		
public static String regist(WeChatMDLUser user) {
			
			String urlStr = "http://"+Constants.baehost+"/CallRegisterUser";
			ArrayList<String> arr = new ArrayList<String>();
			if(user.getOpenid()!=null&&!"".equals(user.getOpenid())){
				arr.add("openid="+user.getOpenid());
			}
			if(user.getRealName()!=null&&!"".equals(user.getRealName())){
				arr.add("name="+user.getRealName());
			}
			if(user.getPhone()!=null&&!"".equals(user.getPhone())){
				arr.add("phone="+user.getPhone());
			}
			if(user.getEmail()!=null&&!"".equals(user.getOpenid())){
				arr.add("email="+user.getEmail());
			}
			if(user.getRegisterDate()!=null&&!"".equals(user.getRegisterDate())){
				arr.add("registerDate="+user.getRegisterDate());
			}
	/*		if(user.getRole()!=null&&!"".equals(user.getRole())){
				arr.add("role="+user.getRole());
			}
			if(user.getGroupid()!=null&&!"".equals(user.getGroupid())){
				arr.add("group="+user.getGroupid());
			}*/
			if(user.getSelfIntro()!=null&&!"".equals(user.getSelfIntro())){
				arr.add("selfIntro="+user.getSelfIntro());
			}
			//Skill = html:45,java:50
		//	ArrayList list = user.getTag();
			String skill = "";
		//	Map map = null;
		
			System.out.println(skill);

			String temp="";
			for(int i=0;i<arr.size();i++){
				if(i==0)temp += "?";
				else temp += "&";
				temp += arr.get(i);
			}
			urlStr += temp;
			System.out.println(urlStr);
			String message = "error";
			try {
				
			   URL urlGet = new URL(urlStr);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           if(localInd == "Y"){
		           System.setProperty("http.proxyHost", Constants.proxyInfo);  
		           System.setProperty("http.proxyPort", "8080");  
	           }
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); 
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           System.out.println("============="+message);
	           is.close();

	       } catch (Exception e) {
	    	   log.info("error callGetDataQualityReport ---------" + e.getMessage());
	    	   message =  "failed with " + e.getMessage();
	       }
			return message;
		}

	/*
	 * 
	 * chang-zheng get CountryCody json file
	 */
	public static Map<String,OrgCountryCode> ReadCountryCode(String readPath) {
		JSONObject demoJson = null;
		// List<OrgCountryCode> listorgcode = new ArrayList<OrgCountryCode>();
		if(OrgCountryCodeMap!=null){
			return OrgCountryCodeMap;
		}
		OrgCountryCodeMap = new HashMap<String,OrgCountryCode>();
		try {
			File f = new File(readPath);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while((line = reader.readLine()) != null){
					OrgCountryCode orgCountryCode = new OrgCountryCode();
					try {
						demoJson = new JSONObject(line);
						orgCountryCode.setCountryCode(demoJson.getString("countryCode"));
						orgCountryCode.setCountryName(demoJson.getString("countryName"));
						orgCountryCode.setTotalCount(demoJson.getString("totalCount"));
						orgCountryCode.setCustomerCount(demoJson.getString("customerCount"));
						orgCountryCode.setPartnerCount(demoJson.getString("partnerCount"));
						orgCountryCode.setCompetitorCount(demoJson.getString("competitorCount"));
						//listorgcode.add(orgCountryCode);
						OrgCountryCodeMap.put(demoJson.getString("countryName").toUpperCase(), orgCountryCode);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return OrgCountryCodeMap;

	}
	/*
	 * 
	 * chang-zheng get CountryCody json file
	 
	public static OrgCountryCode ReadCountryCodeByCountryCode(String readPath,String countryCode) {
		OrgCountryCode orgCountryCode = new OrgCountryCode();
		if(OrgCountryCodeMap!=null){
			orgCountryCode = OrgCountryCodeMap.get(countryCode);
		}
		else{
			OrgCountryCodeMap = ReadCountryCode(readPath);
			orgCountryCode = OrgCountryCodeMap.get(countryCode);
		}
		return orgCountryCode;
	}*/
	
	/*
	 * 
	 * chang-zheng get CountryCody json file by name
	 */
	public static OrgCountryCode ReadOrgCountryCodeByName(String readPath,String countryName) {
		OrgCountryCode orgCountryCode = new OrgCountryCode();
		if(OrgCountryCodeMap!=null){
			orgCountryCode = OrgCountryCodeMap.get(countryName.toUpperCase());
		}
		else{
			OrgCountryCodeMap = ReadCountryCode(readPath);
			orgCountryCode = OrgCountryCodeMap.get(countryName.toUpperCase());
		}
		return orgCountryCode;
	}
	
	
	
    public static List<String> sendTextMessageToUserOnlyByCustomInterface(String content,String toUser,String fromUsersOpenID){
 	        List<String> result = new ArrayList<String>();
 	       List<String> dbUser =  MongoDBBasic.getRegisterUserByOpenID(fromUsersOpenID);
 	       Date date=new Date();
 			 String json = 
 				"{"+
 				  "\"touser\":\""+toUser+"\","+ 
 				  "\"msgtype\":\"text\", "+
 				   "\"text\":{"+
 				     "\"content\":\""+dbUser.get(0)+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+" 说：\n---------------\n"+content+"\""+
 				   "}"+
 				"}";

 	        System.out.println(json);

           String access_token = MongoDBBasic.getValidAccessKey();

 	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

 	       result.add(action);
 	       result.add(json);
 	       try {

 	    	 	connectWeiXinInterface(action,json);

 	       } catch (Exception e) {

 	           e.printStackTrace();

 	       }
 	       return result;

   }
    public static List<String> sendImgMessageToUserOnlyByCustomInterface(String toUser,String media_id){
	        List<String> result = new ArrayList<String>();
			 String json = 
				"{"+
				  "\"touser\":\""+toUser+"\","+ 
				  "\"msgtype\":\"image\", "+
				   "\"image\":{"+
				     "\"media_id\":\""+media_id+"\""+
				   "}"+
				"}";

	        System.out.println(json);

       String access_token = MongoDBBasic.getValidAccessKey();

	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

	       result.add(action);
	       result.add(json);
	       try {

	    	 	connectWeiXinInterface(action,json);

	       } catch (Exception e) {

	           e.printStackTrace();

	       }
	       return result;

}

    public static String sendTextMessageToUser(String content,List<String> toUser){
    	String result="";
    	String text="";
    	if(toUser.size()!=1){
    	for(int i=0;i<toUser.size();i++)
    	{
    		
    		if(i==toUser.size()-1)
    		{
    			text=text+toUser.get(i);
    		}
    		else
    		{
    			text=text+toUser.get(i)+"\",\"";
    		}
    	}

    	}

       //获取access_token


        String json = "{\"touser\": [\""+text+"\"],\"msgtype\": \"text\", \"text\": {\"content\": \""+content+"\"}}";
        String accessToken = MongoDBBasic.getValidAccessKey();
/*       String accessToken = "FsEa1w7lutsnI4usB6I_yareExnJ-l7_8-RKkpF47TIU4vjBF_XA6bArxARRf-6B1irPpkFFeZvmi1LAGP9iuTVIgfd38fE39izmQ30_eL4pPftP_bH4s41FWgrryVuvRZUcAEACKF";*/

       //获取请求路径
       String action =  "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token="+accessToken;
       if(toUser.size()==1){
    	   json= "{\"touser\": \""+toUser.get(0)+"\",\"msgtype\": \"text\", \"text\": {\"content\": \""+content+"\"}}";
    	  action =    "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;}
     
       

       System.out.println("json:"+json);

       try {

          result = connectWeiXinInterface(action,json);

       } catch (Exception e) {

           e.printStackTrace();

       }
       return result;

   }

    public static String sendNotificationToUser(String openId,String toOpenId,Notification note){
    	String result ="";
    			String str="{\"title\":\""+note.getTitle()+"\",\"description\":\""+"From "+MongoDBBasic.getRegisterUserByOpenID(openId).get(0)+":"+note.getContent()+"\",\"url\":\"http://"+Constants.baehost+"/mdm/NotificationCenter.jsp?num="+note.getNum()+"&uid="+openId+"\",\"picurl\":"
    					+ "\"https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000DlTWX&oid=00D90000000pkXM\"}";
    	        String json = "{\"touser\":\""+toOpenId+"\",\"msgtype\":\"news\",\"news\":" +

    	                "{\"articles\":[" +str +"]}"+"}";


    	        System.out.println(json);

/*    	       String access_token = "FsEa1w7lutsnI4usB6I_yareExnJ-l7_8-RKkpF47TIU4vjBF_XA6bArxARRf-6B1irPpkFFeZvmi1LAGP9iuTVIgfd38fE39izmQ30_eL4pPftP_bH4s41FWgrryVuvRZUcAEACKF";*/
    	        String access_token =  MongoDBBasic.getValidAccessKey();

    	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

    	       try {

    	    	 	result =  connectWeiXinInterface(action,json);

    	       } catch (Exception e) {

    	           e.printStackTrace();

    	       }
    	       return result;

    	    }
    public static String sendNotificationToUser(String openId,String toOpenId,String img,ArticleMessage am){
    	String result ="";
    	String str="";
    	ClientMeta cm=MongoDBBasic.QueryClientMeta(Constants.clientCode);
    	if("".equals(am.getWebUrl())||""==am.getWebUrl()){
    			str="{\"title\":\""+am.getTitle()+"\",\"description\":\""+"Publisher - "+cm.getClientName()+":\n"+am.getContent()+"\",\"url\":\"http://"+Constants.baehost+"/mdm/NotificationCenter.jsp?num="+am.getNum()+"\",\"picurl\":"
    					+ "\""+img+"\"}";
    	}else
    	{
    		str="{\"title\":\""+am.getTitle()+"\",\"description\":\""+cm.getClientName()+"\",\"url\":\""+am.getWebUrl()+"\",\"picurl\":"
					+ "\""+img+"\"}";
    	}
    	        String json = "{\"touser\":\""+toOpenId+"\",\"msgtype\":\"news\",\"news\":" +

    	                "{\"articles\":[" +str +"]}"+"}";


    	        System.out.println(json);

/*    	       String access_token = "FsEa1w7lutsnI4usB6I_yareExnJ-l7_8-RKkpF47TIU4vjBF_XA6bArxARRf-6B1irPpkFFeZvmi1LAGP9iuTVIgfd38fE39izmQ30_eL4pPftP_bH4s41FWgrryVuvRZUcAEACKF";*/
    	        String access_token =  MongoDBBasic.getValidAccessKey();

    	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

    	       try {

    	    	 	result =  connectWeiXinInterface(action,json);

    	       } catch (Exception e) {

    	           e.printStackTrace();

    	       }
    	       return result;

    	    }
    public static String sendRecognitionToUser(String openId,String toOpenId,CongratulateHistory ch){
    	String result ="";
    			String str="{\"title\":\"Congratulations!! "+ch.getTo()+" \",\"description\":\""+ch.getTo()+" must have done something amazing and has been recognized by"+ch.getFrom()+"!!!\",\"url\":\"http://"+Constants.baehost+"/mdm/RecognitionCenter.jsp?num="+ch.getNum()+"&uid="+openId+"\",\"picurl\":"
    					+ "\"http://"+Constants.baehost+"/MetroStyleFiles/RecognitionImage.jpg\"}";
    	        String json = "{\"touser\":\""+toOpenId+"\",\"msgtype\":\"news\",\"news\":" +

    	                "{\"articles\":[" +str +"]}"+"}";


    	        System.out.println(json);

/*    	       String access_token = "FsEa1w7lutsnI4usB6I_yareExnJ-l7_8-RKkpF47TIU4vjBF_XA6bArxARRf-6B1irPpkFFeZvmi1LAGP9iuTVIgfd38fE39izmQ30_eL4pPftP_bH4s41FWgrryVuvRZUcAEACKF";*/
    	        String access_token =  MongoDBBasic.getValidAccessKey();

    	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

    	       try {

    	    	 	result =  connectWeiXinInterface(action,json);

    	       } catch (Exception e) {

    	           e.printStackTrace();

    	       }
    	       return result;

    	    }
    public static String connectWeiXinInterface(String action,String json){

        URL url;
        String result="";

       try {

           url = new URL(action);

           HttpURLConnection http = (HttpURLConnection) url.openConnection();

           http.setRequestMethod("POST");

           http.setRequestProperty("Content-Type",

                   "application/x-www-form-urlencoded");

           http.setDoOutput(true);

           http.setDoInput(true);

           System.setProperty("sun.NET.client.defaultConnectTimeout", "30000");

           System.setProperty("sun.Net.client.defaultReadTimeout", "30000");

           http.connect();

           OutputStream os = http.getOutputStream();

           os.write(json.getBytes("UTF-8"));// 传入参数

           InputStream is = http.getInputStream();

           int size = is.available();

           byte[] jsonBytes = new byte[size];

           is.read(jsonBytes);

           result = new String(jsonBytes, "UTF-8");

        //   System.out.println("请求返回结果:"+result);

           os.flush();

           os.close();
          

       } catch (Exception e) {

           e.printStackTrace();

       }
       return "status:"+result;

    }
    
    
    /*
     * 
     * 
     */
    public static String callQueryClientMetaByClientCode() throws UnsupportedEncodingException {
		String url = "http://"+Constants.baehost+"/QueryClientMetaByClientCode";
		String message= "errorrrr";
		try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("PUT"); //must be get request
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	          
	           http.connect();
	           InputStream is = http.getInputStream();
	           int size = is.available();
	           byte[] jsonBytes = new byte[size];
	           is.read(jsonBytes);
	           message = new String(jsonBytes, "UTF-8");
	           //JSONObject demoJson = new JSONObject(message);
	           is.close();
	           //System.out.println(message + "- success http ---------" + url);
	       } catch (Exception e) {
	    	   return "failed";
	       } 
		return message;
		}
    public static String sendQuotationToUser(WeChatMDLUser toWeChatMDLUser,String content,String img,String title,String url){
    	String result ="";
    	String str="";
    	
    		str="{\"title\":\""+title+"\",\"description\":\""+content+"\",\"url\":\""+url+toWeChatMDLUser.getOpenid()+"\",\"picurl\":"
					+ "\""+img+"\"}";
    	        String json = "{\"touser\":\""+toWeChatMDLUser.getOpenid()+"\",\"msgtype\":\"news\",\"news\":" +

    	                "{\"articles\":[" +str +"]}"+"}";


    	        System.out.println(json);

/*    	       String access_token = "FsEa1w7lutsnI4usB6I_yareExnJ-l7_8-RKkpF47TIU4vjBF_XA6bArxARRf-6B1irPpkFFeZvmi1LAGP9iuTVIgfd38fE39izmQ30_eL4pPftP_bH4s41FWgrryVuvRZUcAEACKF";*/
    	        String access_token =  MongoDBBasic.getValidAccessKey();

    	       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+access_token;

    	       try {

    	    	 	result =  connectWeiXinInterface(action,json);

    	       } catch (Exception e) {

    	           e.printStackTrace();

    	       }
    	       return result;

    	    }
    
}


