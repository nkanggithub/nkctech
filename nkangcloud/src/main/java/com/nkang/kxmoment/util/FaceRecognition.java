package com.nkang.kxmoment.util;

import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class FaceRecognition {

 public String goface(String picurl) 
 {
	 /*	FaceRecognition - Basic
	  * https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236
	  * https://dev.projectoxford.ai/docs/services/563879b61984550e40cbbe8d/operations/563879b61984550f30395236/console
	  */
     HttpClient httpclient = HttpClients.createDefault();
     String ret = "";
     try
     {
         URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/face/v1.0/detect");
         builder.setParameter("returnFaceId", "true");
         builder.setParameter("returnFaceLandmarks", "false");
         builder.setParameter("returnFaceAttributes", "age,gender,headPose,smile,facialHair,glasses");

         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", "850c8dc0fb914d6791f15d4a3e3ada99");
         // Request body
         StringEntity reqEntity = new StringEntity("{\"url\":\"" +picurl + "\"}");
         request.setEntity(reqEntity);
         HttpResponse response = httpclient.execute(request);
         HttpEntity entity = response.getEntity();
         if (entity != null) 
         {
        	 
        	 ret = EntityUtils.toString(entity);
         }
     }
     catch (Exception e)
     {
         System.out.println(e.getMessage());
     }
     return ret;
 }
 
 public String gofaceEmotion(String picurl) 
 {
	 /* FaceRecognition - Emotion
	  * https://dev.projectoxford.ai/docs/services/5639d931ca73072154c1ce89/operations/563b31ea778daf121cc3a5fa/console
	  */
     HttpClient httpclient = HttpClients.createDefault();
     String ret = "";
     try
     {
    	 URIBuilder builder = new URIBuilder("https://api.projectoxford.ai/emotion/v1.0/recognize");
         URI uri = builder.build();
         HttpPost request = new HttpPost(uri);
         request.setHeader("Content-Type", "application/json");
         request.setHeader("Ocp-Apim-Subscription-Key", "26ba3dbf9bb54c37a984103de20ab33b");
         
         // Request body
         StringEntity reqEntity = new StringEntity("{\"url\":\"" +picurl + "\"}");
         request.setEntity(reqEntity);
         HttpResponse response = httpclient.execute(request);
         HttpEntity entity = response.getEntity();
         if (entity != null) 
         {
        	 ret = EntityUtils.toString(entity);
         }
     }
     catch (Exception e)
     {
         System.out.println(e.getMessage());
     }
     return ret;
 }
 
}

