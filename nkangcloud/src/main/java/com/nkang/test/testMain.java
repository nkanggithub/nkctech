package com.nkang.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Timer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.nkang.kxmoment.baseobject.GeoLocation;
import com.nkang.kxmoment.baseobject.MdmDataQualityView;
import com.nkang.kxmoment.baseobject.OrgOtherPartySiteInstance;
import com.nkang.kxmoment.baseobject.OrganizationSearch4Solr;
import com.nkang.kxmoment.baseobject.WeChatUser;
import com.nkang.kxmoment.util.CommenJsonUtil;
import com.nkang.kxmoment.util.Constants;
import com.nkang.kxmoment.util.CsvFileWriter;
import com.nkang.kxmoment.util.FaceRecognition;
import com.nkang.kxmoment.util.GoogleLocationUtils;
import com.nkang.kxmoment.util.MongoDBBasic;
import com.nkang.kxmoment.util.RestUtils;
import com.nkang.kxmoment.util.StopWatch;
import com.nkang.kxmoment.util.StringUtils;
import com.nkang.kxmoment.util.SolrUtils.SolrClientUtils;

import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.security.cert.Certificate;


public class testMain{
	private static Logger log=Logger.getLogger(testMain.class);
	private static Timer timer= new Timer();
	public static void main(String[] args) throws Exception {
		//read("C://MDM_10//Nkang WorkSpace//nkangcloudFY16//src//main//resources//clientLoading.csv");

		//loading data from mdm to baidu cloud
		//RestUtils.getUserCityInfoWithLatLng("29.605253","106.361580");
		
		//opsi =  RestUtils.CallGetOPSIWithOutLatLng();
		
		//System.out.println(RestUtils.getlatLngwithQuery(opsi.getSiteName(), opsi.getCityRegion()));
		
/*		String queryStr = "";
		GoogleLocationUtils gApi = new GoogleLocationUtils();
		GeoLocation geo =  new GeoLocation();
		geo = gApi.geocodeByAddressNoSSL("Sabah");
		System.out.print(geo.getLAT() + "---------"+geo.getLNG());*/
		
  /*  StopWatch sw = new StopWatch();
		sw.start();
		System.out.println("starting data load--please wait");
		List<OrgOtherPartySiteInstance> opsiList = new ArrayList<OrgOtherPartySiteInstance>();
		opsiList = SolrClientUtils.getOPSIList(0);  // 1 of 33
		for (OrgOtherPartySiteInstance opsi : opsiList) {
			RestUtils.callMasterDataUpsert(opsi);
		}
		sw.getElapsedMs();
		System.out.println("over all loading time : " + sw);*/
		
		
/*		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			Locale obj = new Locale("", countryCode);
			System.out.println("Country Code = " + obj.getCountry() + ", Country Name = " + obj.getDisplayCountry());
		}
		System.out.println("Done");*/
		
		
//		FaceRecognition fr = new FaceRecognition();
//		fr.goface();
  String str= "[{'faceId':'3439910a-1eb7-4b93-8af0-2845cbfa605d','faceRectangle':{'top':206,'left':248,'width':356,'height':356},'faceAttributes':{'smile':0.006,'headPose':{'pitch':0.0,'roll':-0.3,'yaw':16.6},'gender':'female','age':19.8,'facialHair':{'moustache':0.0,'beard':0.0,'sideburns':0.0},'glasses':'NoGlasses'}}] ";
  String[] ss = new String[20];
  ObjectMapper mapper = new ObjectMapper();
 // System.out.println(mapper.writeValueAsString(str));
	
 /* String json = "{\"deviceid\": \"12345\",\"cmd\": \"login\"}";
	JSONObject obj = JsonUtil.jsonToObject(json);
	System.out.println(obj.getString("deviceid"));*/
	//-------------------------------------------------
	String jsonArray = str.substring(1, str.length()-1);//"{'loves':[{'1':'read book','2':'swim','3':'kickball'}]}";
	//JSONArray jsonArrayData = CommenJsonUtil.jsonToArray("faceAttributes",jsonArray);
	JSONObject jsonArrayData = CommenJsonUtil.jsonToObject(jsonArray);
	//CommenJsonUtil.jsonToObject((JSONObject)jsonArrayData.get("faceAttributes"));
	//System.out.println((JSONObject)jsonArrayData.get("smile"));
	 
	System.out.println(CommenJsonUtil.jsonToObject(jsonArrayData.get("faceAttributes").toString()).get("age"));
	
	/*PushContentVo v = new PushContentVo("push", "0", "", "channel", 
			"imgurl", "title", "{http://content}", "source", "time", "timestamp");
	
	String json = JsonUtil.objectToJson(v);
	System.out.println(JsonUtil.getValueByKeyFromJson(json, "content"));
	
	BrandVo vo = null;
	List<BrandVo> list = new ArrayList<BrandVo>();
	for(int i=0; i<5; i++) {
		vo = new BrandVo();
		vo.setId("id_"+i);
		vo.setName("name_"+i);
		vo.setFirstLetter("A_"+i);
		System.out.println(JsonUtil.objectToJson(vo));
		list.add(vo);
	}
	System.out.println(collectionToJson(list));*/
}

	
  
 /* ss=str.split(",");
  for (int i = 0; i < ss.length; i++)
      System.out.println(ss[i]);
	
}*/
        

	
	

	
	
	
	public static String read(String readPath) {
		String readTxt = "";
		try {
			File f = new File(readPath);
			if (f.isFile() && f.exists()) {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader reader = new BufferedReader(read);
				String line;
				Map<String, String[]> paramsMap = new HashMap<String, String[]>();
				
				while ((line = reader.readLine()) != null) {
					String [] sp = line.split("\\|");
					if(sp[0].equalsIgnoreCase("hpit:w-mdcp-mdm-prd")){
						continue;
					}
					String[] params = paramsMap.get(sp[0]);
					if(params == null){
						params = new String[4];
						params[0] = sp[0];
						params[1] = sp[2];
						params[2] = sp[3];
						params[3] = sp[1];
						paramsMap.put(sp[0], params);
						continue;
					}
					String webServiceStr = params[3];
					if(webServiceStr.indexOf(sp[1]) <= -1){
						webServiceStr += "," + sp[1];
						params[3] = webServiceStr;
					}
					/*String [] sp = line.split("\\|");
					if(sp.length == 4){
						System.out.println(RestUtils.CallLoadClientIntoMongoDB(sp[0],sp[1],sp[2],sp[3]));
					}*/
				}
				Collection<String[]> paramsCol = paramsMap.values();
				Iterator<String[]> paramsIt = paramsCol.iterator();
				while(paramsIt.hasNext()){
					String[] params = paramsIt.next();
/*					if(params[3].length() > 1000){
						continue;
					}*/
					RestUtils.CallLoadClientIntoMongoDB(params[0],params[1],params[2],params[3]);
					System.out.println(params[0] +  " --- " + params[3]);
					//break;
				}
				read.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return readTxt;
	}
	
	static double  getnolevelcalc(double salary){
			double level1, level2, level3, level4, level5, level6, tax = 0;
			level1 = 1455 * 0.03;
			level2 = level1 + (4155 - 1455) * 0.1;
			level3 = level2 + (7755 - 4155) * 0.2;
			level4 = level3 + (27255 - 7755) * 0.25;
			level5 = level4 + (41255 - 27255) * 0.30;
			level6 = level5 + (57505 - 41255) * 0.35;

			if (salary <= 1455)
				tax = salary * 0.03;
			if (salary > 1455 && salary <= 4155)
				tax = level1 + (salary - 1455) * 0.1;
			if (salary > 4155 && salary <= 7755)
				tax = level2 + (salary - 4155) * 0.2;
			if (salary > 7755 && salary <= 27255)
				tax = level3 + (salary - 7755) * 0.25;
			if (salary > 27255 && salary <= 41255)
				tax = level4 + (salary - 27255) * 0.3;
			if (salary > 41255 && salary <= 57505)
				tax = level5 + (salary - 41255) * 0.35;
			if (salary > 57505)
				tax = level6 + (salary - 57505) * 0.45;
			return tax;
	}
	static double getlevelcalc(double salary) {
		double level1, level2, level3, level4, level5, level6, tax = 0;
		level1 = 1500 * 0.03;
		level2 = level1 + (4500 - 1500) * 0.1;
		level3 = level2 + (9000 - 4500) * 0.2;
		level4 = level3 + (35000 - 9000) * 0.25;
		level5 = level4 + (55000 - 35000) * 0.30;
		level6 = level5 + (80000 - 35000) * 0.35;
	
		if (salary <= 1500)
			tax = salary * 0.03;
		if (salary > 1500 && salary <= 4500)
			tax = level1 + (salary - 1500) * 0.1;
		if (salary > 4500 && salary <= 9000)
			tax = level2 + (salary - 4500) * 0.2;
		if (salary > 9000 && salary <= 35000)
			tax = level3 + (salary - 9000) * 0.25;
		if (salary > 35000 && salary <= 55000)
			tax = level4 + (salary - 35000) * 0.3;
		if (salary > 55000 && salary <= 80000)
			tax = level5 + (salary - 55000) * 0.35;
		if (salary > 80000)
			tax = level6 + (salary - 80000) * 0.45;
		return tax;
	}
	
	
}

