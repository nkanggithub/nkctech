<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.nkang.kxmoment.baseobject.CongratulateHistory"%>
<%@ page import="com.nkang.kxmoment.util.RestUtils"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%	
String uid = request.getParameter("uid");
MongoDBBasic.updateUser(uid);
String num = request.getParameter("num");
List<CongratulateHistory> chList=MongoDBBasic.getRecognitionInfoByOpenID(uid,num);
CongratulateHistory ch=new CongratulateHistory();
if(!chList.isEmpty()){
	ch=chList.get(0);
}
/*
CongratulateHistory ch=new CongratulateHistory();
ch.setComments("thanks");
ch.setGiftImg("http://wonderfulcq.bj.bcebos.com/IMG_0287.JPG");
ch.setPoint("200");
ch.setFrom("panda");
ch.setTo("潘嗒嗒");
ch.setUserImg("http://wx.qlogo.cn/mmopen/soSX1MtHexV6ibXOvfzOoePPqRib4AiaVIT1oCZZ7j0oVfacOT4xibmjF2sqHltyu1sXPPfqkwpXxoWDibQxnaX7FupTpHRNj2S8k/0");
ch.setType(" Bais For Action.");*/
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en"><head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta content="" name="description">
<meta content="" name="hpe">
<title><%=ch.getTo() %> must have done something amazing and has been recognized by <%=ch.getFrom() %></title>
<link rel="stylesheet" type="text/css" href="../nkang/css_athena/style.css"/>
<script type="text/javascript" src="../Jsp/JS/jquery-1.8.0.js"></script>
  </head>
<body style="margin:0;">
            <div style="position: absolute;top: 0px;right: 0px;"><p style="margin-right: 10px;margin-top: 20px;"><%=ch.getTo() %></p><img src="<%=ch.getUserImg() %>" alt="" style="border-radius: 25px;height: 35px;width: 35px;position: absolute;right: 8px;top: 45px;"></div>
            <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing:0;display:table;">
             <tbody>
              <tr>
               <td width="270" valign="top" style="width:202.5pt;padding:0in 7.5pt 0in 15.0pt;padding-left:0px;">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing:0">
                 <tbody>
                  <tr>
                   <td valign="top" style="width:60%"><p class="MsoNormal" style="/* line-height:14.0pt */"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black"><b><img id="_x0000_i1025" src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9IMj&oid=00D90000000pkXM" style="
    width: 140%;
"></b>
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
               <td width="270" valign="bottom" style="width:202.5pt;padding:0in 15.0pt 0in 7.5pt;">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing:0;">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in"><h1 align="right" style="margin:0in;margin-bottom:.0001pt;text-align:right;margin-bottom:0!important;color:inherit;margin-bottom: 24px!important;word-wrap:normal;"><span style="font-size: 12px;font-family:&quot;Arial&quot;,sans-serif;color:black;">
                      <o:p></o:p></span></h1></td>
                  </tr>
                 </tbody>
                </table></td>
              </tr>
             </tbody>
            </table>
            
            <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;/* border-collapse:collapse; *//* display:table; *//* border-spacing: 0; */">
             <tbody>
              <tr>
               <td width="588" valign="top" style="width:441.0pt;/* padding:0in 0in 0in 0in */">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;/* border-collapse:collapse; */padding-bottom:0!important;">
                 <tbody>
                  <tr>
                   <td valign="top"><p class="MsoNormal" style="margin-top:0px;"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black"><img id="_x0000_i1026" src="https://myrecognition.int.hpe.com/hpenterprise/images/designtheme/hp2/1/m2e-hero.jpg" style="
    width: 100%;
">
                      <o:p></o:p></span></p></td>
                   <td width="0" valign="top" style="width:.3pt;padding:0in 0in 0in 0in"><p class="MsoNormal" style="line-height:14.0pt"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">&nbsp;
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
              </tr>
             </tbody>
            </table>
            <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;display:table;border-spacing: 0;height: 100px;">
             <tbody>
              <tr>
               <td width="274" style="width: 50%;background:#F2F2F1;padding:0in 6.0pt 0in 12.0pt;-moz-hyphens: auto;-webkit-hyphens: auto;border-collapse:collapse!important;hyphens: auto;word-wrap: break-word;">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;padding-bottom:0!important">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in">
                    <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing: 0">
                     <tbody>
                      <tr>
                       <td width="40%" valign="top" style="width: 40%;padding:0in 7.5pt 0in 7.5pt;"><p class="MsoNormal" align="center" style="text-align:center"><span style="font-size: 30px;font-family:&quot;Arial&quot;,sans-serif;color:black;display: block;margin-top: 40px;"><%=ch.getPoint() %>
                          <o:p></o:p></span></p></td>
                       <td width="60%" valign="top" style="width: 60.0%;-moz-hyphens: auto;border-collapse:collapse!important;"><p class="MsoNormal" style="line-height:14.0pt;"><strong><span style="font-size: 22px;font-family:&quot;Arial&quot;,sans-serif;color:black;display: block;">Points</span></strong><span style="font-size: 13px;font-family:&quot;Arial&quot;,sans-serif;color:black;display: block;line-height: 15px;"><br>have been deposited into your account for immediate use
                          <o:p></o:p></span></p></td>
                      </tr>
                     </tbody>
                    </table></td>
                  </tr>
                 </tbody>
                </table></td>
               <td width="274" style="width: 50%;background:#464646;padding:0in 12.0pt 0in 6.0pt;">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="text-align:center;width:100.0%;border-collapse:collapse;border-spacing: 0">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in"><p class="MsoNormal" style="line-height:14.0pt;text-align:center;position:relative;">
                   <img src="../Jsp/PIC/gift.png" alt="" style="position: absolute;left: 3%;top: 0px;width: 40px;">
                   <span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black;   display: block;margin-left: 30px;">
                   <b><span style="text-decoration:none"><img border="0" id="_x0000_i1027" src="<%=ch.getGiftImg() %>" style="
    width: 50%;
"></span></b>
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
              </tr>
             </tbody>
            </table>
            <p style="margin: 20px;line-height:14.0pt;"><strong><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">Congratulations, <%=ch.getTo() %>!</span></strong><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">
              <o:p></o:p></span></p><p style="margin: 20px;line-height:14.0pt;"><span style="font-size:13px;font-family:&quot;Arial&quot;,sans-serif;color:black"><%=ch.getFrom() %> recognized you in the Manager-to-Employee FY17 Program for the value M2E: <%=ch.getType() %>.
              <o:p></o:p></span></p>
            <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;display:table;border-spacing: 0;">
             <tbody>
              <tr>
               <td width="588" valign="top" style="width: 100%;/* padding:0in 0in 15.0pt 0in; */-moz-hyphens: auto;/* border-collapse:collapse!important; *//* word-wrap: break-word; */">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;/* border-collapse:collapse; *//* border-spacing: 0; */">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in;width: 100%;">
                    <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing: 0">
                     <tbody>
                      <tr>
                       <td width="100%" valign="top" style="width:100.0%;border:none;border-top:solid #A6A6A6 1.0pt;padding:0in 0in 0in 0in"></td>
                      </tr>
                     </tbody>
                    </table></td>
                   <td width="0" style="width:10%;/* padding:0in 0in 0in 0in */"><p class="MsoNormal" style="line-height:14.0pt"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black"><o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
              </tr>
             </tbody>
            </table><p style="margin: 10px;line-height:14.0pt;"><span style="font-size: 15px;font-family:&quot;Arial&quot;,sans-serif;color:black;">Below shows the impact you made to DXC:
              <o:p></o:p></span></p>
            <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;display:table;border-spacing: 0">
             <tbody>
              <tr>
               <td width="0" style="width: 10%;padding:0in 0in 15.0pt 0in;">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;padding-right:10px!important;">
                 <tbody>
                  <tr>
                   <td valign="top" style="text-align: left;padding:0in 0in 0in 15.0pt;"><p class="MsoNormal" style="line-height: 1px;"><span style="font-size: 1PX;font-family:&quot;Arial&quot;,sans-serif;color:black;"><img border="0" id="_x0000_i1028" src="https://myrecognition.int.hpe.com/hpenterprise/images/designtheme/hp2/1/lquote.png" style="
    width: 110%;
">
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
               <td width="193" style="width:145.0pt;padding:0in 0in 15.0pt 0in">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing: 0">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in"><p class="font-size-16" style="margin:0in;margin-bottom:.0001pt;line-height:14.0pt;margin-bottom:0!important"><span style="font-size: 14px;font-family:&quot;Arial&quot;,sans-serif;color:black;display: block;margin: 10px;margin-left: 15px;"> <%=ch.getComments() %>
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
               <td width="0" style="width: 8%;padding:0in 0in 15.0pt 0in;">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing: 0">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in"><p class="MsoNormal" style="line-height:14.0pt"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black"><img border="0" id="_x0000_i1029" src="https://myrecognition.int.hpe.com/hpenterprise/images/designtheme/hp2/1/rquote.png" style="
    width: 60%;
">
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
              </tr>
             </tbody>
            </table><p style="margin: 10px;margin-bottom: 20px;line-height:14.0pt;"><span style="font-size: 14px;font-family:&quot;Arial&quot;,sans-serif;color:black;">Thank you for putting DXC values into action! Please click the green box above to read the full message. A copy of this recognition was sent to your manager.
              <o:p></o:p></span></p>
            <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;display:table;border-spacing: 0">
             <tbody>
              <tr>
               <td width="588" valign="top" style="width:441.0pt;padding:0in 0in 15.0pt 0in;-moz-hyphens: auto;-webkit-hyphens: auto;border-collapse:collapse!important;hyphens: auto;word-wrap: break-word">
                <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing: 0">
                 <tbody>
                  <tr>
                   <td valign="top" style="padding:0in 0in 0in 0in">
                    <table class="MsoNormalTable" border="0" cellspacing="0" cellpadding="0" width="100%" style="width:100.0%;border-collapse:collapse;border-spacing: 0">
                     <tbody>
                      <tr>
                       <td width="100%" valign="top" style="width:100.0%;border:none;border-top:solid #A6A6A6 1.0pt;padding:0in 0in 0in 0in"><p class="MsoNormal" style="line-height:14.0pt"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">&nbsp;
                          <o:p></o:p></span></p></td>
                      </tr>
                     </tbody>
                    </table></td>
                   <td width="0" style="width:.3pt;padding:0in 0in 0in 0in"><p class="MsoNormal" style="line-height:14.0pt"><span style="font-size:11.0pt;font-family:&quot;Arial&quot;,sans-serif;color:black">&nbsp;
                      <o:p></o:p></span></p></td>
                  </tr>
                 </tbody>
                </table></td>
              </tr>
             </tbody>
            </table>
            
            
   <div>
   
   </div>
  

	<div id="footer">
		<span class="clientCopyRight"><nobr></nobr></span>
	</div>
 	<script>
         jQuery.ajax({
     		type : "GET",
     		url : "../QueryClientMeta",
     		data : {},
     		cache : false,
     		success : function(data) {
     			if(data){
     			var jsons = eval(data);
     			//$('img.HpLogo').attr('src',jsons.clientLogo);
				$(document).attr("title",jsons.clientStockCode+" - "+$(document).attr("title"));//修改title值  
     			$('span.clientCopyRight').text('©'+jsons.clientCopyRight);
     			}
     		}
     	});
         </script> 
</body></html>
