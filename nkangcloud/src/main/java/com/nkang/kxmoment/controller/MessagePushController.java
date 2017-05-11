package com.nkang.kxmoment.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nkang.kxmoment.baseobject.CongratulateHistory;
import com.nkang.kxmoment.util.MongoDBBasic;
import com.nkang.kxmoment.util.RestUtils;


@Controller
@RequestMapping("/weChatApp")
public class MessagePushController {

	
	@RequestMapping("/startMsgPush")
	public @ResponseBody String startMsgPush(HttpServletRequest request,
			HttpServletResponse response)
	{
		List<String> openIDs=new ArrayList<String>();
		openIDs.add(request.getParameter("uid"));
	/*	openIDs.add("oqPI_xHLkY6wSAJEmjnQPPazePE8");*/
/*		openIDs.add("oqPI_xLq1YEJOczHi4DS2-1U0zqc");*/

	 return	RestUtils.sendTextMessageToUser("recognization -消息群发推送测试",openIDs);

		
		
	}
	@RequestMapping("/startNewsPush")
	public  @ResponseBody String  startNewsPush(HttpServletRequest request,
			HttpServletResponse response)
	{
	/*	List<String> openIDs=new ArrayList<String>();
		openIDs.add("oqPI_xDdGid-HlbeVKZjpoO5zoKw");*/
	/*	openIDs.add("oqPI_xHLkY6wSAJEmjnQPPazePE8");*/
/*		openIDs.add("oqPI_xLq1YEJOczHi4DS2-1U0zqc");*/

		CongratulateHistory ch=new CongratulateHistory();
		ch.setTo("Panda");
		return RestUtils.sendRecognitionToUser(request.getParameter("uid"),request.getParameter("uid"),ch);

		
		
	}

}
