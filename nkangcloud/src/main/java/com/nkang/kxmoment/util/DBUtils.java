package com.nkang.kxmoment.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.nkang.kxmoment.baseobject.ExtendedOpportunity;
import com.nkang.kxmoment.baseobject.GeoLocation;
import com.nkang.kxmoment.baseobject.MdmDataQualityView;
import com.nkang.kxmoment.baseobject.Opportunity;
import com.nkang.kxmoment.baseobject.WeChatUser;
import com.nkang.kxmoment.service.CoreService;
public class DBUtils {
	private static Logger log = Logger.getLogger(DBUtils.class);
	public static MySqlUtil msqlu;
	private static MdmDataQualityView mqv = new MdmDataQualityView();

	public static boolean createUser(WeChatUser wcu){
		msqlu = new MySqlUtil();
		Boolean ret = false;
		String sqlInsertUser = "INSERT INTO Wechat_User  (OpenID, HeadUrl, NickName) VALUES ('"+wcu.getOpenid()+"', '"+wcu.getHeadimgurl()+"', '"+wcu.getNickname()+"')";
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	int exeRet = stmt.executeUpdate(sqlInsertUser);
	    	if(exeRet == 1){
	    		ret = true;
	    	}
	    	else if(exeRet == 0){
	    		ret = false;
	    	}
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
		return ret;
	}
	
	public static boolean updateUser(String OpenID, String Lat, String Lng){
		msqlu = new MySqlUtil();
		Boolean ret = false;
		String faddr = RestUtils.getUserCurLocWithLatLng(Lat, Lng);
		String sqlIupdate = "UPDATE Wechat_User SET FormatAddress='"+ faddr +"',CurLAT='"+ Lat + "', CurLNG='" + Lng + "' where OpenID='"+ OpenID  + "'";
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	int exeRet = stmt.executeUpdate(sqlIupdate);
	    	if(exeRet == 1){
	    		ret = true;
	    	}
	    	else if(exeRet == 0){
	    		ret = false;
	    	}
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
		return ret;
	}

	public static void updateAccessKey(String key, String expiresIn){
		msqlu = new MySqlUtil();
		java.sql.Timestamp cursqlTS = new java.sql.Timestamp(new java.util.Date().getTime()); 
		String sqlUpdateKey = "UPDATE Access_Key SET ExpiresIn=" + Integer.valueOf(expiresIn) + " , LastUpdated='" + cursqlTS + "' ,AKey= '" + key + "' where ID = 1";
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	int exeRet = stmt.executeUpdate(sqlUpdateKey);
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	}
	
	public static GeoLocation getDBUserGeoInfo(String OpenID){
		GeoLocation loc = new GeoLocation();
		msqlu = new MySqlUtil();
		String sqlQueryUser = "SELECT * FROM Wechat_User WHERE OpenID='"+ OpenID  + "'";
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sqlQueryUser);
	    	while (rs.next()) {
	    		loc.setLAT(rs.getString("CurLAT"));
	    		loc.setLNG(rs.getString("CurLNG"));
	    		loc.setFAddr(rs.getString("FormatAddress"));
			}

	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
		return loc;
	}
	
	/*public static String QueryAccessKey(){
		String validKey = null;
		msqlu = new MySqlUtil();

		java.sql.Timestamp sqlTS = null;;
		java.sql.Timestamp cursqlTS = new java.sql.Timestamp(new java.util.Date().getTime()); 
		
		String sqlQueryKey = "SELECT * from Access_Key where ID = 1";
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sqlQueryKey);
	    	while (rs.next()) {
	    		sqlTS = rs.getTimestamp("LastUpdated");
	    		validKey = rs.getString("AKey");
			}
	    	
	    	int diff = (int) ((cursqlTS.getTime() - sqlTS.getTime())/1000);
	    	if((7200 - diff) > 0){
	    		//log.info(diff + " is less than 7200. so use original valid Key as : "+ validKey);
	    	}
	    	else{
	    		log.info(diff + " is close to 7200. and is to re-generate the key");
	    		validKey = null;
	    	}
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	    
	    return validKey;
	}*/

/*	public static String getValidAccessKey(){
		String AccessKey = DBUtils.QueryAccessKey();
		if(AccessKey == null){
			AccessKey = RestUtils.getAccessKey();
		}	
		return AccessKey;
	}
	*/
	public static List<Opportunity> QueryOppt(){
		List<Opportunity> Oppts =  new ArrayList<Opportunity>();
		msqlu = new MySqlUtil();
		String sqlQueryKey = "SELECT * FROM Opportunity WHERE OpptLAT =  'LAT' AND OpptLNG =  'LNG' LIMIT 1200";
		String sqlQueryKey2 = "SELECT * FROM Opportunity WHERE OpptLAT =  'LAT' AND OpptLNG =  'LNG' AND CityArea = '重庆市' LIMIT 1200";
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sqlQueryKey);
	    	while (rs.next()) {
	    		Opportunity Oppt = new Opportunity();
	    		Oppt.setOPSIID(rs.getString("OPSIID"));
	    		Oppt.setOpptID(rs.getString("OpptID"));
	    		Oppt.setOpptName(rs.getString("OpptName"));
	    		Oppt.setOpptCityName(rs.getString("OpptCityName"));
	    		Oppt.setCityArea(rs.getString("CityArea"));
	    		Oppt.setOpptAddress(rs.getString("OpptAddress"));
	    		Oppt.setOpptEstdYr(rs.getString("OpptEstdYr"));
	    		Oppt.setOpptGblHc(rs.getString("OpptGblHc"));
	    		Oppt.setOpptYrRev(rs.getString("OpptYrRev"));
	    		Oppts.add(Oppt);
			}
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	    return Oppts;
	}
	
	public static void updateOppt(String lat, String lng, String OpptID,  String qualityGrade, String OpptAddr, String OpsiID, String CityRegion) throws UnsupportedEncodingException{
		msqlu = new MySqlUtil();
		String ModifiedcityArea ="";
		//江北区大屏之路
		//重庆市江北区大屏之路
		//重庆市渝中区中山二路165号
		//长寿区长寿湖镇
		//荣昌县杜家坝工业园区
		//忠县忠州镇苏家社区内
		//CityRegion = URLEncoder.encode(CityRegion, "UTF-8");
		if(CityRegion == null || CityRegion == ""){
			if(OpptAddr.contains("县")){
				String [] addrsplit = OpptAddr.split("县");
				if(addrsplit[0] != null){
					ModifiedcityArea = addrsplit[0].toString() + "县";
				}
			}
			else if(OpptAddr.contains("区")){
				String [] addrsplit = OpptAddr.split("区");
				if(addrsplit[0] != null){
					if(addrsplit[0].contains("市")){
						String [] addrsplit2 = addrsplit[0].split("市");
						ModifiedcityArea = addrsplit2[1].toString() + "区";
					}
					else{
						ModifiedcityArea = addrsplit[0].toString() + "区";
					}
				}
			}
			CityRegion = ModifiedcityArea;
		}


		//String sqlUpdateKey = "UPDATE Opportunity SET CityArea= '" + CityRegion + "', QualityGrade='" + qualityGrade+ "', OpptLAT='" + lat + "' , OpptLNG='" + lng +"' where OPSIID = '" + OpsiID + "'";
		String sqlUpdateKey = "UPDATE Opportunity SET QualityGrade='" + qualityGrade+ "', OpptLAT='" + lat + "' , OpptLNG='" + lng +"' where OPSIID = '" + OpsiID + "'";

		Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	int exeRet = stmt.executeUpdate(sqlUpdateKey);
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	}
	
	public static MdmDataQualityView getDataQualityReport(){
		mqv = new MdmDataQualityView();
		msqlu = new MySqlUtil();
		String sqlQuerynumberOfNonGeo = "SELECT count(*) as num FROM Opportunity WHERE (OpptLAT =  'LAT' AND OpptLNG =  'LNG') or (OpptLAT =  '' AND OpptLNG =  '') limit 2";
		String sqlQuerynumberOfLeads = "SELECT count(*) as num FROM Opportunity WHERE IsCustomer='false' AND IsPartner='false' and IsCompetitor='false' limit 2";
		String sqlQuerynumberOfEmptyCityArea = "SELECT count(*) as num FROM Opportunity WHERE CityArea = '' limit 2";
		String sqlQuerynumberOfThreeGrade = "SELECT count(*) as num FROM Opportunity WHERE QualityGrade = '1' or QualityGrade = '2' or QualityGrade = '3' or QualityGrade = '4' or QualityGrade = '5' limit 2";
		String sqlQuerynumberOfCustomer = "SELECT count(*) as num FROM Opportunity WHERE IsCustomer	='true' limit 2";
		String sqlQuerynumberOfPartner = "SELECT count(*) as num FROM Opportunity WHERE IsPartner='true' limit 2";
		String sqlQuerynumberOfCompetitor = "SELECT count(*) as num FROM Opportunity WHERE IsCompetitor='true' limit 2";
		String sqlQuerynumberOfOppt = "SELECT count(*) as num FROM Opportunity limit 2";
		
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	
	    	ResultSet rs = stmt.executeQuery(sqlQuerynumberOfNonGeo);
	    	while (rs.next()) {mqv.setNumberOfNonGeo(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfEmptyCityArea);
	    	while (rs.next()) {mqv.setNumberOfEmptyCityArea(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfThreeGrade);
	    	while (rs.next()) {mqv.setNumberOfThreeGrade(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfCustomer);
	    	while (rs.next()) {mqv.setNumberOfCustomer(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfPartner);
	    	while (rs.next()) {mqv.setNumberOfPartner(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfCompetitor);
	    	while (rs.next()) {mqv.setNumberOfCompetitor(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfOppt);
	    	while (rs.next()) {mqv.setNumberOfOppt(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfLeads);
	    	while (rs.next()) {mqv.setNumberOfLeads(Integer.valueOf(rs.getString("num")));}
	    	
	    	float percent= (float)mqv.getNumberOfThreeGrade()/mqv.getNumberOfOppt();
	    	//float percent= (float)mqv.getNumberOfNonGeo()/mqv.getNumberOfOppt();
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	String percents = df.format(percent);
	    	mqv.setPercents(percents);
	    	
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	    return mqv;
	}
	
	public static MdmDataQualityView getDataQualityReport(String stateProvince, String city){
		MdmDataQualityView mqv = new MdmDataQualityView();
		msqlu = new MySqlUtil();
		String additionalQuery = " 1=1 and ";
		
		if(stateProvince != null || stateProvince != ""){
			additionalQuery = additionalQuery + " StateProvince = '" + stateProvince + "' and ";
		}
		if(city != null || stateProvince != ""){
			additionalQuery = additionalQuery + " OpptCityName = '" + city + "' and ";
		}
		
		String sqlQuerynumberOfNonGeo = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " (OpptLAT =  'LAT' AND OpptLNG =  'LNG') or (OpptLAT =  '' AND OpptLNG =  '') limit 2";
		String sqlQuerynumberOfLeads = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " IsCustomer='false' AND IsPartner='false' and IsCompetitor='false' limit 2";
		String sqlQuerynumberOfEmptyCityArea = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " CityArea = '' limit 2";
		String sqlQuerynumberOfThreeGrade = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " (QualityGrade = '1' or QualityGrade = '2' or QualityGrade = '3' or QualityGrade = '4' or QualityGrade = '5') limit 2";
		String sqlQuerynumberOfCustomer = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " IsCustomer	='true' limit 2";
		String sqlQuerynumberOfPartner = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " IsPartner='true' limit 2";
		String sqlQuerynumberOfCompetitor = "SELECT count(*) as num FROM Opportunity WHERE "+ additionalQuery + " IsCompetitor='true' limit 2";
		String sqlQuerynumberOfOppt = "SELECT count(*) as num FROM Opportunity where "+ additionalQuery + " limit 2";
		
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	
	    	ResultSet rs = stmt.executeQuery(sqlQuerynumberOfNonGeo);
	    	while (rs.next()) {mqv.setNumberOfNonGeo(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfEmptyCityArea);
	    	while (rs.next()) {mqv.setNumberOfEmptyCityArea(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfThreeGrade);
	    	while (rs.next()) {mqv.setNumberOfThreeGrade(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfCustomer);
	    	while (rs.next()) {mqv.setNumberOfCustomer(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfPartner);
	    	while (rs.next()) {mqv.setNumberOfPartner(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfCompetitor);
	    	while (rs.next()) {mqv.setNumberOfCompetitor(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfOppt);
	    	while (rs.next()) {mqv.setNumberOfOppt(Integer.valueOf(rs.getString("num")));}
	    	rs = stmt.executeQuery(sqlQuerynumberOfLeads);
	    	while (rs.next()) {mqv.setNumberOfLeads(Integer.valueOf(rs.getString("num")));}
	    	
	    	float percent= (float)mqv.getNumberOfThreeGrade()/mqv.getNumberOfOppt();
	    	//float percent= (float)mqv.getNumberOfNonGeo()/mqv.getNumberOfOppt();
	    	DecimalFormat df = new DecimalFormat("0.00");
	    	String percents = df.format(percent);
	    	mqv.setPercents(percents);
	    	
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	    return mqv;
	}
	
	public static String CleanDatabase(){
		MdmDataQualityView mqv = new MdmDataQualityView();
		mqv= DBUtils.getDataQualityReport();
		int a = (Integer.valueOf(mqv.getNumberOfOppt())/20000);
		msqlu = new MySqlUtil();
		String sqlDelete = "DELETE FROM Opportunity limit 200000";
		Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	for(int i = 0; i< a ; i ++){
	    		stmt.executeUpdate(sqlDelete);
	    	}
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
		return "Done";
	}
	public static List<String> getFilterSegmentArea(){
		List<String> listOfSegmentArea = new ArrayList<String>();
		msqlu = new MySqlUtil();
		String sqlQuerySA = "SELECT distinct(SAName) as SA FROM SegmentArea limit 100000";
		Connection conn = null;
		try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sqlQuerySA);
	    	while (rs.next()) {
	    		String SAName = rs.getString("SA");
	    		listOfSegmentArea.add(SAName);
			}
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	    return listOfSegmentArea;
	}
	
	public static String getJSFirstSegmentArea(){
		List<String> listOfSegmentArea = new ArrayList<String>();
		listOfSegmentArea = getFilterSegmentArea();
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

	public static String getJSListOfCustomers(String fromUserName, String bizType){
		GeoLocation geol = DBUtils.getDBUserGeoInfo(fromUserName);
		String lat = geol.getLAT();
		String lng = geol.getLNG();
		List<ExtendedOpportunity> NearByOpptsExt =  new ArrayList<ExtendedOpportunity>();
		List<String> cityInfo = new ArrayList<String>();
		cityInfo = RestUtils.getUserCityInfoWithLatLng(lat,lng);
		NearByOpptsExt = DBUtils.getNearByOppt(cityInfo.get(0), cityInfo.get(1), cityInfo.get(2), bizType, lat, lng);
		int opptCnt = NearByOpptsExt.size();
		String Ret = "";
		int it = 30;
		if(opptCnt <= 30){
			it = opptCnt;
		}
		for(int i = 0; i < it ; i ++){
			//Ret =  Ret + "<li>" + NearByOpptsExt.get(i).getDistance() + " KM " + NearByOpptsExt.get(i).getOpptName() + "<br />" + NearByOpptsExt.get(i).getSegmentArea() + "</li>";
			Ret =  Ret + "<li id=\" customer" + i + " \"><span style='float:left;'>" + NearByOpptsExt.get(i).getOpptName() + " </span> <span style='float:right;'>" + NearByOpptsExt.get(i).getDistance() + " KM </span></li>";
		}

	    return Ret;
	}
	
	public static String getJSMoreFiveOfCustomers(String fromUserName, String bizType, int lastLi){

		GeoLocation geol = DBUtils.getDBUserGeoInfo(fromUserName);
		String lat = geol.getLAT();
		String lng = geol.getLNG();
		List<ExtendedOpportunity> NearByOpptsExt =  new ArrayList<ExtendedOpportunity>();
		List<String> cityInfo = new ArrayList<String>();
		cityInfo = RestUtils.getUserCityInfoWithLatLng(lat,lng);
		NearByOpptsExt = DBUtils.getNearByOppt(cityInfo.get(0), cityInfo.get(1), cityInfo.get(2), bizType, lat, lng);
		int opptCnt = NearByOpptsExt.size();
		String Ret = "";
		
		for (int i=0; i<5; i++) {
			Ret = Ret + "li = document.createElement('li'); ";
			Ret = Ret + "li.setAttribute('id','customer" + i + "');";
			Ret = Ret + "li.innerHTML = '<span style=\" float:left;\">" + NearByOpptsExt.get(i).getOpptName() + "</span><span style=\" float:right; \">" +NearByOpptsExt.get(i).getDistance() + "</span>'; ";
			Ret = Ret + "el.appendChild(li, el.childNodes[0]); ";
		}
		return Ret;
	}
	
	public static List<String> getAllSegmentArea(){
		List<String> listOfSegmentArea = new ArrayList<String>();
		msqlu = new MySqlUtil();
		String sqlQuerySA = "SELECT distinct(SegmentArea) as SA FROM Opportunity where SegmentArea != 'null' limit 100000";
		Connection conn = null;
		try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sqlQuerySA);
	    	while (rs.next()) {
	    		String SAName = rs.getString("SA");
	    		SAName = SAName.substring(1, SAName.length()-1); 
	    		if(SAName.contains(",")){
	    			String [] SAsplit = SAName.split(",");
	    			for(int i = 0 ; i < SAsplit.length ; i ++ ){
	    				if(SAsplit[i]!= "" && SAsplit[i] != null){
	    					if(!listOfSegmentArea.contains(SAsplit[i])){
	    						listOfSegmentArea.add(SAsplit[i]);
	    					}
	    				}
	    			}
	    		}
	    		else{
	    			if(!listOfSegmentArea.contains(SAName)){
	    				listOfSegmentArea.add(SAName);
	    			}
	    		}
			}
 
			
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
	    return listOfSegmentArea;
	}

	public static int LoadSegmentArea(){
		msqlu = new MySqlUtil();
		int ret = 0;
		List<String> listOfSegmentArea = new ArrayList<String>();
	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	listOfSegmentArea = getAllSegmentArea();
	    	
	    	// remove duplicate
	    	List<String> listTemp= new ArrayList<String>();
			Iterator<String> it = listOfSegmentArea.iterator();  
			while(it.hasNext()){  
			  String a = it.next();  
			  if(listTemp.contains(a)){  
				  it.remove();  
			  }  
			  else{  
				  listTemp.add(a);  
			  }  
			}
			
	    	for(int i = 0 ; i < listOfSegmentArea.size() ; i++){
		    	String sqlInsertSA = "INSERT INTO SegmentArea (SAName) VALUES ('"+ listOfSegmentArea.get(i) +"')";
		    	stmt.executeUpdate(sqlInsertSA);
		    	ret = ret + 1;
	    	}
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){} 
	    }
		return ret;
	}

	@SuppressWarnings("unchecked")
	public static List<ExtendedOpportunity> getNearByOppt(String StateProvince, String OpptCityName, String CityArea, String bizType, String lat, String lng){
		List<ExtendedOpportunity> Oppts =  new ArrayList<ExtendedOpportunity>();
		String SubSQL = " 1=1 ";
		if(StateProvince != null || StateProvince !=""){
			SubSQL = SubSQL + "AND StateProvince = '"+ StateProvince +"' ";
		}

		if(OpptCityName.contains("区")){
			SubSQL = SubSQL + "AND OpptCityName = '"+ OpptCityName +"' ";
		}
		else if(OpptCityName != null || OpptCityName !=""){
			SubSQL = SubSQL + "AND OpptCityName = '"+ CityArea +"' ";
		}
		
		if(bizType == "customer"){
			SubSQL = SubSQL + "AND IsCustomer = 'true' ";
		}
		else if(bizType == "competitor"){
			SubSQL = SubSQL + "AND IsCompetitor = 'true' ";
		}
		else if(bizType == "partner"){
			SubSQL = SubSQL + "AND IsPartner = 'true' ";
		}
		else if(bizType == "" || bizType == null){
			SubSQL = SubSQL + "AND IsPartner = 'false' AND IsCompetitor = 'false' AND  IsCustomer='false' ";
		}

		msqlu = new MySqlUtil();
		String sqlQueryKey = "SELECT * FROM Opportunity WHERE OpptLAT !='LAT' AND OpptLAT !='' AND " + SubSQL + " LIMIT 10000";

	    Connection conn = null;
	    try{
	    	conn = msqlu.getConnection();
	    	Statement stmt = (Statement) conn.createStatement();
	    	ResultSet rs = stmt.executeQuery(sqlQueryKey);
	    	while (rs.next()) {
	    		ExtendedOpportunity opptExt =  new ExtendedOpportunity();
	    		opptExt.setOpptLAT(rs.getString("OpptLAT"));
	    		opptExt.setOpptLNG(rs.getString("OpptLNG"));
	    		opptExt.setOPSIID(rs.getString("OPSIID"));
	    		opptExt.setOpptID(rs.getString("OpptID"));
	    		opptExt.setOpptName(rs.getString("OpptName"));
	    		opptExt.setOpptCityName(rs.getString("OpptCityName"));
	    		opptExt.setCityArea(rs.getString("CityArea"));
	    		opptExt.setOpptAddress(rs.getString("OpptAddress"));
	    		opptExt.setOpptEstdYr(rs.getString("OpptEstdYr"));
	    		opptExt.setOpptGblHc(rs.getString("OpptGblHc"));
	    		opptExt.setOpptYrRev(rs.getString("OpptYrRev"));
	    		opptExt.setSegmentArea(rs.getString("SegmentArea"));
	    		opptExt.setDistance(RestUtils.GetDistance(Double.parseDouble(opptExt.getOpptLNG()), Double.parseDouble(opptExt.getOpptLAT()), Double.parseDouble(lng), Double.parseDouble(lat))); 
	    		if(opptExt.getDistance() < 100){ // only return  distance less than 45 KM
	    			Oppts.add(opptExt);
	    		}
	    		Collections.sort(Oppts);
			}
	    	rs.close();
	    	stmt.close();
	    	conn.close();
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    finally{
	    	if (conn != null) try {conn.close();} catch (Exception ignore){}

	    }
	    return Oppts;
	}
	public double compare(ExtendedOpportunity o1, ExtendedOpportunity o2) {
		return o1.getDistance() - o2.getDistance();
		}
}
