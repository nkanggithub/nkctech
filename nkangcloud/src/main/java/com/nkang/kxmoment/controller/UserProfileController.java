package com.nkang.kxmoment.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidubce.services.bos.model.PutObjectResponse;
import com.nkang.kxmoment.util.Constants;
import com.nkang.kxmoment.baseobject.ArticleMessage;
import com.nkang.kxmoment.baseobject.ClientMeta;
import com.nkang.kxmoment.baseobject.CongratulateHistory;
import com.nkang.kxmoment.baseobject.GeoLocation;
import com.nkang.kxmoment.baseobject.Notification;
import com.nkang.kxmoment.baseobject.WeChatUser;
import com.nkang.kxmoment.util.MongoDBBasic;
import com.nkang.kxmoment.util.RestUtils;
import com.nkang.kxmoment.util.ToolUtils;
import com.nkang.kxmoment.util.BosUtils.MyBosClient;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {
	@RequestMapping(value = "/getWechatUserLists", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getWechatUserLists(HttpServletRequest request,
			HttpServletResponse response) {
		boolean res;
		int sucNum=0;
		String akey=RestUtils.getAccessKey();
		List<String> IdLists=RestUtils.getWeChatUserListID(akey);
		MongoDBBasic.delNullUser();
		for (int i = 0; i < IdLists.size(); i++) {
			WeChatUser user=RestUtils.getWeChatUserInfo(akey,IdLists.get(i).replaceAll("\"",""));
			res=MongoDBBasic.syncWechatUserToMongo(user);
//			res=MongoDBBasic.createUser(user);
			if(res==true){
				sucNum++;
			}
		}
		return (sucNum+1)+"条数据同步成功!";
//		WeChatUser user=RestUtils.getWeChatUserInfo(akey, IdLists.get(0).replaceAll("\"",""));
//		return IdLists.get(0)+"==========="+user.toString();
	}
	@RequestMapping(value = "/getWeather", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getWeather(HttpServletRequest request,
			HttpServletResponse response) {
		String city = (String) request.getSession()
				.getAttribute("city");
		// String location=request.getParameter("location");
		if (city == null || "".equals(city)) {
			city = "重庆市";
		}
		String weather = RestUtils.getWeatherInform(city);
		return weather;
	}

	@RequestMapping(value = "/getLocation", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getLocation(HttpServletRequest request,
			HttpServletResponse response) throws JSONException {
		String uid = request.getParameter("uid");
		GeoLocation loc = RestUtils.callGetDBUserGeoInfo(uid);
		String message = RestUtils.getUserCurLocStrWithLatLng(loc.getLAT(),loc.getLNG());
		JSONObject demoJson = new JSONObject(message);
		String curLoc="";
        if(demoJson.has("result")){
     	    JSONObject JsonFormatedLocation = demoJson.getJSONObject("result");
     	 	curLoc = JsonFormatedLocation.getString("formatted_address");
     	 	String city = JsonFormatedLocation.getJSONObject("addressComponent").getString("city");
     	 	request.getSession().setAttribute("location", curLoc);
     	 	request.getSession().setAttribute("city", city);
        }
		return curLoc;
	}

	@RequestMapping(value = "/getMDLUserLists", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getMDLUserLists(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("UID");
		return RestUtils.getMDLUserLists(openid);
	}

	@RequestMapping(value = "/getTax", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getTax(HttpServletRequest request,
			HttpServletResponse response) {
		String taxIncomeStr = request.getParameter("taxIncome");
		String taxstartStr = request.getParameter("taxstart");
		String paymentStr = request.getParameter("payment");
		double taxIncome = new Double(taxIncomeStr);
		double taxstart = new Double(taxstartStr);
		double payment = new Double(paymentStr);
		HashMap<String, Double> result = new HashMap<String, Double>();
		double levelcalc= ToolUtils.getlevelcalc(taxIncome - taxstart-payment);
		double nolevelcalc= ToolUtils.getnolevelcalc(taxIncome - taxstart-payment);
		return "{\"levelcalc\":"+levelcalc+",\"nolevelcalc\":"+nolevelcalc+"}";
	}
	
	@RequestMapping(value = "/setSignature", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setSignature(HttpServletRequest request,
			HttpServletResponse response) {
		String svg = request.getParameter("svg");
		String openid = (String) request.getSession()
				.getAttribute("UID");
		return RestUtils.getCallUpdateUserWithSignature(openid,svg);
	}
	@RequestMapping(value = "/updateUserWithLike", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateUserWithLike(
			@RequestParam(value="openid", required=false) String openid,
			@RequestParam(value="likeToName", required=false) String likeToName,
			@RequestParam(value="ToOpenId", required=false) String ToOpenId
			){
		boolean ret = false;
		try{
			ret = MongoDBBasic.updateUserWithLike(openid, likeToName, ToOpenId);
		}		
		catch(Exception e){
			ret = false;
		}
		return ret==true?"true":"false";
	}

	/*
	 * chang-zheng
	 *  Congratulate
	 */
	@RequestMapping("/getRegisterUsers")
	public @ResponseBody List<String> getRegisterUsers(HttpServletRequest request,
			HttpServletResponse response){
		return MongoDBBasic.getAllRegisterUsers();
	}
	

	/*
	 *Panda
	 *  getRecognitionInfoByOpenID
	 */
	@RequestMapping("/getRecognitionInfoByOpenID")
	public @ResponseBody List<CongratulateHistory>  getRecognitionInfoByOpenID(HttpServletRequest request,
			HttpServletResponse response ){
		List<CongratulateHistory> chList=MongoDBBasic.getRecognitionInfoByOpenID(request.getParameter("openID"),request.getParameter("num"));
/*		List<CongratulateHistory> chList=new ArrayList<CongratulateHistory>();
		CongratulateHistory ch=new CongratulateHistory();
		ch.setFrom("Panda");
		ch.setTo("Ning");
		ch.setComments("moving NoSQL and Solr match POC smoothly as part of our FY16 team goal. The progress you can your team made is promissing.");
		ch.setPoint("300");
		ch.setType("Innovators at Heart");
		String date="2016-12-22 16:52:07";
		ch.setCongratulateDate(date.substring(0,11));
		chList.add(ch);*/
		return chList;
		
	} 

	
	@RequestMapping("/getRegisterUserByOpenID")
	public @ResponseBody String getRegisterUserByOpenID(HttpServletRequest request,
			HttpServletResponse response){
		String openid=request.getParameter("openID");
		List<String> dbUser = MongoDBBasic.getRegisterUserByOpenID(openid);
		if(dbUser!=null){
			return dbUser.get(0).toString();
		}
		return "nullname";
	}
	
	@RequestMapping("/userCongratulate")
	public @ResponseBody String updateUserCongratulateHistory(HttpServletRequest request,
			HttpServletResponse response ){
		//String openid=request.getParameter("openID");

		String openid=MongoDBBasic.getRegisterUserByrealName(request.getParameter("to"));
		int num=MongoDBBasic.getRecognitionMaxNumByOpenID(openid)+1;
		String fromOpenid=MongoDBBasic.getRegisterUserByrealName(request.getParameter("from"));
		CongratulateHistory conhis=new CongratulateHistory();
		String img="https://myrecognition.int.hpe.com/hpenterprise/images/designtheme/hp2/1/points-link-2.png";
		System.out.println("request.getParameter('imgType')----"+request.getParameter("imgType"));
		if("1".equals(request.getParameter("imgType"))){
			img="http://"+Constants.bucketName+"."+Constants.bosDomain+"/"+request.getParameter("img");
		}
		conhis.setNum(num+"");
		conhis.setFrom(request.getParameter("from"));
		conhis.setTo(request.getParameter("to"));
		conhis.setType(request.getParameter("type"));
		conhis.setPoint(request.getParameter("points"));
		conhis.setComments(request.getParameter("comments"));
		conhis.setUserImg(MongoDBBasic.getWeChatUserFromOpenID(openid).get("HeadUrl"));
		conhis.setGiftImg(img);
		conhis.setCongratulateDate(new Date().toLocaleString());
		MongoDBBasic.updateUserCongratulateHistory(openid,conhis);
		List<String> openIDs = MongoDBBasic.getAllOpenIDByIsRegistered();
		/*List<String> openIDs=new ArrayList<String>();
		openIDs.add("oqPI_xDdGid-HlbeVKZjpoO5zoKw");
		openIDs.add("oqPI_xHLkY6wSAJEmjnQPPazePE8");
		openIDs.add("oqPI_xLq1YEJOczHi4DS2-1U0zqc");
		openIDs.add("oqPI_xACjXB7pVPGi5KH9Nzqonj4");
		openIDs.add("oqPI_xHQJ7iVbPzkluyE6qDPE6OM");
		openIDs.add(openid);
		openIDs.add(fromOpenid);*/
		if("true".equals(request.getParameter("isAll"))){
			for(int i=0;i<openIDs.size();i++){
				RestUtils.sendRecognitionToUser(openid,openIDs.get(i),conhis);
			}
		}else{
			RestUtils.sendRecognitionToUser(openid,openid, conhis);
			RestUtils.sendRecognitionToUser(openid,fromOpenid, conhis);
		}
		return openIDs.size()+"";
	} 
	@RequestMapping("/getCompanyInfo")
	public @ResponseBody List<String> getCompanyInfo(HttpServletRequest request,
			HttpServletResponse response ){
		ClientMeta cm=MongoDBBasic.QueryClientMeta();
		List<String> companyInfo=new ArrayList<String>();
		companyInfo.add(cm.getClientLogo());
		companyInfo.add(cm.getClientCopyRight());
		return companyInfo;
		
	} 
	@RequestMapping("/getRealName")
	public @ResponseBody List<String> getRealName(HttpServletRequest request,
			HttpServletResponse response ){
		List<String> dbUser =  MongoDBBasic.getRegisterUserByOpenID(request.getParameter("openID"));
			return dbUser;
	} 
	@RequestMapping("/getAllRegisterUsers")
	public @ResponseBody List<String> getAllRegisterUsers(HttpServletRequest request,
			HttpServletResponse response ){
		List<String> str = MongoDBBasic.getAllRegisterUsers();
		return str;
		
	} 
	
	@RequestMapping("/getSendAllUsers")
	public @ResponseBody List<String> getSendAllUsers(HttpServletRequest request,
			HttpServletResponse response ){
		List<String> str = MongoDBBasic.getAllOpenIDByIsActivewithIsRegistered();
		return str;
		
	} 
	
	@RequestMapping("/addNotification")
	public @ResponseBody String addNotification(HttpServletRequest request,
			HttpServletResponse response ){
		ArticleMessage am=new ArticleMessage();
		String openid=request.getParameter("openId");
		String img = request.getParameter("img");
		String imgType = request.getParameter("imgType");
		String type = request.getParameter("type");
		if("1".equals(imgType)){
			img="http://"+Constants.bucketName+"."+Constants.bosDomain+"/"+img;
		}
		int num=MongoDBBasic.getArticleMessageMaxNum()+1;
		System.out.println("new Article num--------------"+num);
		am.setNum(num+"");
		am.setType(request.getParameter("type"));
		am.setTitle(request.getParameter("title"));
		am.setContent(request.getParameter("content"));
		am.setWebUrl(request.getParameter("url"));
		am.setAuthor(openid);
		am.setVisitedNum("0");
		am.setTime(new Date().toLocaleString());
		MongoDBBasic.saveArticleMessage(am);
		String content=request.getParameter("typeName")+" 又有新活动啦\n";
		if(request.getParameter("content").length()>200){
			content+=request.getParameter("content").substring(0,180)+"...";
		}
		else{
			content+=request.getParameter("content");
		}
		am.setContent(content);
		List<String> allUser=new ArrayList<String>();
		if("communication".equals(type.trim())){
		allUser = MongoDBBasic.getAllOpenIDByIsRegistered();
		System.out.println("您选择了‘常规沟通’。。。。");
		}
		else {
			allUser = MongoDBBasic.QueryLikeAreaOpenidList(type);
			System.out.println("您选择了‘其他Area：’"+type+"。。。。");
		}
			for(int i=0;i<allUser.size();i++){
				am.setTitle("【"+MongoDBBasic.getWeChatUserFromOpenID(allUser.get(i)).get("NickName")+"】- "+request.getParameter("title"));
				RestUtils.sendNotificationToUser(openid,allUser.get(i),img,am);
			}
		
		return allUser.size()+"";
	} 
		
	
	/*chang-zheng
	 * 
	 */
	@RequestMapping("/userNotification")
	public @ResponseBody String userNotification(HttpServletRequest request,
			HttpServletResponse response ){
		Notification note = new Notification();
		note.setContent("测试消息");
		note.setNum("1");
		note.setPicture("www");
		note.setTime(new Date().toLocaleString());
		note.setType("ee");
		MongoDBBasic.updateNotification("oqPI_xHLkY6wSAJEmjnQPPazePE8",note);
		
		return "ok";
	} 
	
	@RequestMapping(value = "/uploadPicture", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadPicture(HttpServletRequest request,HttpServletResponse response){
	
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(1024 * 1024);
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setFileSizeMax(1024 * 1024 * 2);
	    upload.setHeaderEncoding("utf-8");
	    upload.setSizeMax(1024 * 1024 * 4);

		 List<FileItem> fileList = null;
		 	String message = "文件导入失败，请重新导入..";
		 	Map map =new HashMap<String,List>();
		 	PutObjectResponse putObjectResponseFromInputStream=null;
		 	String bk = Constants.bucketName;
		 	for(int i=0;i<MyBosClient.client.listBuckets().getBuckets().size();i++){
		 	System.out.println("MyBosClient.client.listBuckets("+i+")"+MyBosClient.client.listBuckets().getBuckets().get(i).getName());}
		    try {
		        fileList = upload.parseRequest(new ServletRequestContext(request));
		        if(fileList != null){
		            for(FileItem item:fileList){
		            	//String filename="";
		            	   if(!item.isFormField() && item.getSize() > 0){
		                	InputStream is = item.getInputStream();
		                	message=item.getName();
		                	putObjectResponseFromInputStream = MyBosClient.client.putObject(bk, message, is);
		                	
		                    if(is!=null){
		                    	is.close();
		                    }
		                }
		            }
		        }
		           
		    } catch (Exception e) {
		        e.printStackTrace();
		        message = "fail--"+e.toString()+"  fileList-size="+ fileList.size() +" message="+ message+" item.isFormField() ="+fileList.get(0).isFormField()+" && item.getSize()="+ fileList.get(0).getSize();
		    
		    }
		    return message;

	}
	@RequestMapping(value = "/uploadSelfie", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadSelfie(HttpServletRequest request,HttpServletResponse response,@RequestParam(value = "openId") String uid){
		DiskFileItemFactory factory = new DiskFileItemFactory();
		System.out.println("uid=="+uid);
	    factory.setSizeThreshold(1024 * 1024);
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setFileSizeMax(1024 * 1024 * 2);
	    upload.setHeaderEncoding("utf-8");
	    upload.setSizeMax(1024 * 1024 * 4);

		 List<FileItem> fileList = null;
		 	String message = "文件导入失败，请重新导入..";
		 	Map map =new HashMap<String,List>();
		 	PutObjectResponse putObjectResponseFromInputStream=null;
		 	//String bk = MyBosClient.client.listBuckets().getBuckets().get(1).getName();
		 	String bk = Constants.bucketName;
		 	for(int i=0;i<MyBosClient.client.listBuckets().getBuckets().size();i++){
		 	System.out.println("MyBosClient.client.listBuckets("+i+")"+MyBosClient.client.listBuckets().getBuckets().get(i).getName());}
		    try {
		        fileList = upload.parseRequest(new ServletRequestContext(request));
		        if(fileList != null){
		            for(FileItem item:fileList){
		            	//String filename="";
		            	   if(!item.isFormField() && item.getSize() > 0){
		                	InputStream is = item.getInputStream();
		                	message=uid+".jpg";
		                	putObjectResponseFromInputStream = MyBosClient.client.putObject(bk, message, is);
		                	
		                    if(is!=null){
		                    	is.close();
		                    }
		                }
		            }
		        }
		           
		    } catch (Exception e) {
		        e.printStackTrace();
		        message = "fail--"+e.toString()+"  fileList-size="+ fileList.size() +" message="+ message+" item.isFormField() ="+fileList.get(0).isFormField()+" && item.getSize()="+ fileList.get(0).getSize();
		    
		    }
		    return message;

	}

}
