<%@ page language="java" pageEncoding="UTF-8"%>
 <%@ page import="java.util.*,org.json.JSONObject"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.nkang.kxmoment.util.RestUtils"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%@ page import="com.nkang.kxmoment.baseobject.WeChatUser"%>
<%@ page import="com.nkang.kxmoment.baseobject.ClientMeta"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%	
String uid = request.getParameter("UID");
SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd"); 
Date date=new Date();
String currentDate = format.format(date);
HashMap<String, String> res=MongoDBBasic.getWeChatUserFromOpenID(uid);
MongoDBBasic.updateVisited(uid,currentDate,"RoleOfAreaMap",res.get("HeadUrl"),res.get("NickName"));
MongoDBBasic.updateUser(uid);
%>
<!Doctype html>
<html>
<head>
<title>我的订阅</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<link rel="stylesheet" href="../nkang/jquery.mobile.min.css" />
<link rel="stylesheet" type="text/css" href="../nkang/assets_athena/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="../nkang/assets_athena/bootstrap/css/bootstrap-responsive.min.css" />
<script type="text/javascript" src="../Jsp/JS/jquery-1.8.0.js"></script>
<script src="../nkang/assets_athena/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="../nkang/jquery.mobile.min.js"></script>
<link rel="stylesheet" type="text/css" href="../MetroStyleFiles/sweetalert.css"/>
<link rel="stylesheet" type="text/css" href="../MetroStyleFiles//CSS/animation-effects.css"/>
<script type="text/javascript" src="../MetroStyleFiles/sweetalert.min.js"></script>

<script type="text/javascript" src="../Jsp/JS/fusioncharts.js"></script>
<script type="text/javascript" src="../Jsp/JS/fusioncharts.powercharts.js"></script>
<script type="text/javascript" src="../Jsp/JS/fusioncharts.theme.fint.js"></script>
<style>
body{
	padding:0px;
}
#echarts{height: 350px;width:750px;}
span.chartButton.now {
    background-color: #005CA1;
}
span.chartButton {
    cursor: pointer;
    padding: 8px 20px;
    background-color: #ccc;
    font-weight: bold;
    color: #fff;
}
#data_model_div
{
    position: fixed;
    top: 0;
    height: 130%;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 90;
    background-color: white;
    transition: opacity .15s linear;
}
 #return-top{position:fixed;bottom:40px;right:10px; text-align:center; display:none;z-index:9999;} 
.HpLogo {
    position: relative;
    top: 8px;
    left: 1%;
    width: 150px;
    height: 58px;
}
ul li.singleQuote{
	font-size:18px;
	border-bottom:1px solid #ccc;
	padding:20px;
	color:#333;
}
ul li.singleQuote .firstLayer.attention{
	color:#0761A5;
}
ul li.singleQuote .firstLayer .quoteTitle{
	float:left;
	cursor:pointer;
}
ul li.singleQuote .firstLayer .quoteTitle .tag{
	font-size:11px;
	background-color:orange;
	color:#fff;
	padding:1px 5px;
	font-weight:normol;
	font-family:微软雅黑;
	margin-left:8px;
}
ul li.singleQuote .firstLayer .quotePrice{
	float:right;
	color:#333;
}
ul li.singleQuote .firstLayer .quotePrice.high,ul li.singleQuote .firstLayer  .change.high{
	color:red;
}
ul li.singleQuote .firstLayer .quotePrice.normal,ul li.singleQuote .firstLayer  .change.normal{
	color:#333;
}
ul li.singleQuote .firstLayer.attention .quotePrice.high,ul li.singleQuote .firstLayer.attention  .change.high{
	color:red;
}
ul li.singleQuote .firstLayer .quotePrice.low,ul li.singleQuote .firstLayer  .change.low{
	color:green;
}
ul li.singleQuote .firstLayer .quotePrice.lose,ul li.singleQuote .firstLayer  .change.lose{
	color:#bbb;
}
ul li.singleQuote .firstLayer  .change{
	font-size:10px;
	float:right;
	margin-top:-35px;
	position:relative;
	clear:both;
}
.clear{
	clear:both;
}
.edit
{
	width: 60px;
    height: 100%;
    color: #fff;
    font-family:微软雅黑;
    text-align: center;
    position: absolute;
    top: 0px;
    right: -60px;
	font-size:14px;
    background: orange;
    border-bottom: 1px solid #ccc;
}
.edit img {
    width:25px;height:auto;position:absolute;top:8px;margin-left: 2px;
}
.edit p
{
	width:50%;
	height:100%;
	line-height:90px;
	margin-right:auto;
	margin-left:auto;
	font-weight:bold;
}
.edit.no{
background: #999;
}
.picClose
{
cursor:pointer;
}
.edit.no p
{
	line-height:25px;
	padding-top:5px;
}
.editBtn
{
	position: relative;
    left: -60px;
}
#footer {
	background: #DCD9D9;
	bottom: 0;
	color: #757575;
	font-size: 12px;
	padding: 10px 1%;
	position: fixed;
	text-align: center;
	width: 100%;
	z-index: 1002;
	left:0;
}
</style>

<script>
var likeRoleNum=0;
var AreaObj=new Object();
var RoleObj=new Object();
$(function(){
	   $(function(){  
	      	 $(window).scroll(function(){  
	           	  if($(window).scrollTop()>200){  
	                 $('#return-top').fadeIn(200);  
	               }  
	               else{
	            	   $('#return-top').fadeOut(200);
	            	 }  
	           });  
	           $('#return-top').click(function(){  
	               $('body,html').animate({scrollTop:0},200);  
	               return false;  
	            });  
	   });
	    /**
	   $(".quoteTitle").live("click",function(){
			$("#pic").css("display","block");
		});
		$(".picClose").live("click",function(){
			$("#pic").css("display","none");
		});
	  */ 
	   
	getAllDatas();
	$(".singleQuote").live("swiperight",function(){
		$(this).css("overflow","hidden");
		$(this).removeClass("editBtn");
		$(this).find(".edit").remove();
	}); 
	$(".singleQuote").live("swipeleft",function(){
		$(this).siblings().removeClass("editBtn");
		$(this).siblings().find(".edit").remove();
		
		
		var priceNum=$(this).find('span.price').length;
		if(priceNum==0){
			$(this).css("overflow","visible");
			$(this).addClass("editBtn");
			var tagNum=$(this).find('span.tag').length;
			var item=$(this).find('span.id').text();
			if(tagNum==0){
				$(this).append('<div class="edit"><p onclick="UpdateTag(\''+item+'\',\'add\',this)"><img src="../mdm/images/focus.png" />关注</p></div>');
			}else{
				$(this).append('<div class="edit no"><p onclick="UpdateTag(\''+item+'\',\'del\',this)">取消<br/>关注</p></div>');
			}
		}
	});
});
function ToCharPage(item){
	//location.href="priceCharts.jsp?itemNo="+item;
	showKMPanel(item);
}
function showKMPanel(item){
	showCommonPanel();
	$("body").append('<div id="UpdateUserKmPart" class="bouncePart" style="position:fixed;z-index:9999;top:100px;width:100%">'
	//+'<legend>编辑关注的牌号</legend><div id="UpdateUserPartDiv" style="margin-top:0px;margin-bottom: -20px;background-color:#fff;">'
			/* +'<div class="title">'+item+'的价格趋势分析</div>'
			+'	<div class="subtext">2016-12-30 - 2017-02-23</div>'
			+'	<div class="chart-box">'
			+'		<div id="echarts" _echarts_instance_="ec_1487834284265" style="width: 371px; -webkit-tap-highlight-color: transparent; -webkit-user-select: none; position: relative; background: transparent;"><div style="position: relative; overflow: hidden; width: 371px; height: 350px; cursor: default;"><canvas width="371" height="350" data-zr-dom-id="zr_0" style="position: absolute; left: 0px; top: 0px; width: 371px; height: 350px; -webkit-user-select: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></canvas></div><div style="position: absolute; display: none; border: 0px solid rgb(51, 51, 51); white-space: nowrap; z-index: 9999999; transition: left 0.4s cubic-bezier(0.23, 1, 0.32, 1), top 0.4s cubic-bezier(0.23, 1, 0.32, 1); border-radius: 4px; color: rgb(255, 255, 255); font-style: normal; font-variant: normal; font-weight: normal; font-stretch: normal; font-size: 14px; font-family: &#39;Microsoft YaHei&#39;; line-height: 21px; padding: 5px; left: 211.95px; top: 122px; background-color: rgba(50, 50, 50, 0.701961);">02-13<br><span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#7DB5E8"></span>价格 : 9,840</div></div>'
			+'	</div>'
			+'				</div>'); */
			+'	<div class="chart-box">'
			+'		<div id="chart-container" style="text-align: center;overflow-x: auto;"></div>'
			+'	</div>'
			+'<div style="text-align:center;margin-top:20px;"></div>');
	$('#UpdateUserKmPart').addClass('form-horizontal bounceInDown animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
	      $(this).removeClass("bounceInDown animated");
	 });
	//---start-----
	FusionCharts.ready(function () {
    var salesChart = new FusionCharts({
        type: 'msarea',
        renderAt: 'chart-container',
        width: '350',
        height: '300',
        dataFormat: 'json',
        dataSource: {
            "chart": {
                "caption": item,
                "subCaption": "",
                "xAxisName": "",
                "yAxisName": "",
                "paletteColors": "#0075c2,#1aaf5d",
                "bgColor": "#ffffff",
                "showBorder": "0",
                "showCanvasBorder": "0",
                "plotBorderAlpha": "10",
                "usePlotGradientColor": "0",
                "legendBorderAlpha": "0",
                "legendShadow": "0",
                "plotFillAlpha": "60",
                "showXAxisLine": "1",
                "axisLineAlpha": "25",                
                "showValues": "0",
                "captionFontSize": "14",
                "subcaptionFontSize": "14",
                "subcaptionFontBold": "0",
                "divlineColor": "#999999",                
                "divLineIsDashed": "1",
                "divLineDashLen": "1",
                "divLineGapLen": "1",
                "showAlternateHGridColor": "0",
                "toolTipColor": "#ffffff",
                "toolTipBorderThickness": "0",
                "toolTipBgColor": "#000000",
                "toolTipBgAlpha": "80",
                "toolTipBorderRadius": "2",
                "toolTipPadding": "5",
            },
            
            "categories": [
                {
                    "category": [
                        {
                            "label": "活动参与"
                        }, 
                        {
                            "label": "技术分享"
                        }, 
                        {
                            "label": "领导评测"
                        }, 
                        {
                            "label": "个人提升"
                        }, 
                        {
                            "label": "知识储备"
                        }
                    ]
                }
            ],
            
            "dataset": [
                {
                    "seriesname": "职位要求",
                    "data": [
                        {
                            "value": "13"
                        }, 
                        {
                            "value": "14"
                        }, 
                        {
                            "value": "13"
                        }, 
                        {
                            "value": "15"
                        }, 
                        {
                            "value": "15"
                        } 
                    ]
                }, 
                {
                    "seriesname": "目前状态",
                    "data": [
                        {
                            "value": "8"
                        }, 
                        {
                            "value": "9"
                        }, 
                        {
                            "value": "12"
                        }, 
                        {
                            "value": "14"
                        }, 
                        {
                            "value": "9"
                        }
                    ]
                }
            ]
        }
    })
    .render();
});
	//---end------
}
function showCommonPanel()
{
	$("body").append("<div  id='data_model_div' style='z-index:9999;'  class='dataModelPanel'><img onclick='hideBouncePanel()' src='../MetroStyleFiles/EXIT1.png' style='width: 30px; height: 30px;position:absolute;top:20px;left:20px;' />	<img style='position:relative;height: 50px;float: right;top:8px;margin-right:20px;width:auto;' class='HpLogo' src='https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9IMj&amp;oid=00D90000000pkXM' alt='Logo' class='HpLogo'><div style='width:100%;height:4px;background:#000;position:absolute;top:70px;'></div></div>");
	$('#data_model_div').removeClass().addClass('panelShowAnmitation').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
	      $(this).removeClass();
	    }); }
function hideBouncePanel()
{
	$("body").find(".bouncePart").remove();
	$("body").find("#data_model_div").remove();
}
function UpdateTag(item,flag,obj){
	var tempObj=$(obj).parent().parent(".singleQuote");
	$(".singleQuote").removeClass("editBtn");
	$(".singleQuote").find(".edit").remove();
	if(likeRoleNum>=2&&item.indexOf("Role")==0&&flag=='add'){
		 swal("操作失败", "最多只能关注两个职位", "error");
	}else{
		$.ajax({
			 url:'../roleOfAreaMap/saveUserKM',
			 type:"POST",
			 data : {
				 openid : $("#openid").val(),
				 kmItem : item,
				 flag : flag
			 },
			 success:function(result){
				 if(result==true){
					 if(flag=='add'){
						 context='恭喜你成功关注该项';
						 if(RoleObj[item]!=null&&RoleObj[item].length>0){
							 var context='';
							 for(var i=0;i<RoleObj[item].length;i++){
								 if(context!='')context+='，';
								 context+=AreaObj[RoleObj[item][i]];
							 }
							 context='推荐您关注【'+context+'】技术领域';
						 }
						 swal("关注成功 ",context, "success");
						 getAllDatas();
						 tempObj.find(".firstLayer").addClass("attention");
						 tempObj.find(".firstLayer").find(".quoteTitle").append('<span class="tag">已关注</span>');
						 if(item.indexOf("Role")==0){
							 likeRoleNum++;
						 }
					 }else  if(flag=='del'){
						 swal("取消成功","你取消了对该项的关注", "success");
						 getAllDatas();
						 tempObj.find(".firstLayer").removeClass("attention");
						 tempObj.find(".firstLayer").find(".quoteTitle").find(".tag").remove();
						 if(item.indexOf("Role")==0){
							 likeRoleNum--;
						 }
					 }
				 }else{
					 swal("操作失败", "请刷新页面后重试", "error");
				 }
			 }
		});	
	}
}
function getAllDatas(){
	$.ajax({
		 url:'../roleOfAreaMap/queryUserKM',
		 type:"GET",
		 data : {
			 openid : $("#openid").val()
		 },
		 success:function(KMLikeArr){ 
		 	$.ajax({
			 url:'../roleOfAreaMap/findList',
			 type:"GET",
			 success:function(resData){
			 if(resData.length)
			{
				var NowRoleArr=new Array();
			    var NoLikeArr=new Array();
				var LikeArr=new Array();
				var recommendArr=new Array();
				likeRoleNum=0;
				var nowRole=$("#role").val();
				if(KMLikeArr.length>0){
						 for(var i=0;i<resData.length;i++){
							 
							 if(resData[i].flag=='Role'&&resData[i].id==nowRole)
				 			 {
								 NowRoleArr.push(resData[i]);
								 recommendArr=$.merge(recommendArr, resData[i].relateLists); 
				 			 }else{
						 		var itemTemp=$.trim(resData[i].id);
						 		var index=$.inArray(itemTemp,KMLikeArr);
						 		if(index>-1){
						 			resData[i]["like"]=true;
						 			LikeArr.push(resData[i]);
						 			KMLikeArr.splice(index,1);
						 			if(resData[i].flag=='Role')
					 				{
					 					likeRoleNum++;
										recommendArr=$.merge(recommendArr, resData[i].relateLists); 
					 				}
						 		}else{
						 			NoLikeArr.push(resData[i]);
						 		}
				 			 }
						}
		 		}else{
		 			 for(var i=0;i<resData.length;i++){
						 if(resData[i].flag=='Role'&&resData[i].id==nowRole)
			 			 {
							 NowRoleArr.push(resData[i]);
							 recommendArr=$.merge(recommendArr, resData[i].relateLists); 
			 			 }
					}
		 			NoLikeArr=resData;
		 		}
				var data=$.merge(LikeArr, NoLikeArr); 
				 var roleHtml="";
				 var areaHtml="";
				 var totalNum=0;
				 if(NowRoleArr.length>0){
					 var priceColor="lose";
					 var tag='';
					 var attention='';
					 var priceStyle='';
					 var unit='<span class="unit"></span>';
					 roleHtml='<li class="singleQuote">'
						 +'	<div class="firstLayer '+attention+'">'
						 +'		<div class="quoteTitle"><span class="id" style="display:none;">'+NowRoleArr[0].id+'</span><span class="item">'+NowRoleArr[0].name+'</span>'+tag+'</div>'
						 +'		<div class="quotePrice '+priceColor+'" '+priceStyle+'><span class="price" style="font-size:13px;font-weight:blod;color:#1A7CAB;">当前职位</span>'+unit+'</div>'
						 +'		<div class="clear"></div>'
						 +'	</div>'
						 +'</li>'; 
				 }
				 AreaObj=new Object();
				 RoleObj=new Object();
				 for(var i=0;i<resData.length;i++){
					 if(resData[i].flag=='Area')
		 			 {
							AreaObj[resData[i].id]=resData[i].name;
		 			 }
					 if(resData[i].flag=='Role')
		 			 {
						 RoleObj[resData[i].id]=resData[i].relateLists;
		 			 }
				 }
				 
				 for(var i=0;i<data.length;i++){
					 if(data[i].id!=""){
						 var priceColor="lose";
						 var tag='';
						 var attention='';
						 var priceStyle='';
						 var onclick='';
						 var unit='<span class="price2"></span><span class="unit"></span>';
						 if(data[i]["like"]==true){
							 tag='<span class="tag">已关注</span>';
							 attention='attention';
							 if(data[i].flag=='Role'){
								 onclick=' onclick="ToCharPage(\''+data[i].name+'\')" ';
							 }
						 }
					 	 var index=$.inArray(data[i].id,recommendArr);
					 	 if(index>-1){
					 		 unit='<span class="price2" style="font-size:13px;font-weight:blod;color:#1A7CAB;">推荐关注</span><span class="unit"></span>'
					 	 }
						 var tempHtml='<li class="singleQuote">'
							 +'	<div class="firstLayer '+attention+'">'
							 +'		<div class="quoteTitle"><span class="id" style="display:none;">'+data[i].id+'</span><span class="item" '+onclick+'>'+data[i].name+'</span>'+tag+'</div>'
							 +'		<div class="quotePrice '+priceColor+'" '+priceStyle+'>'+unit+'</div>'
							 /*  +'		<span class="change high">+10</span>' */
							 +'		<span class="recommendArea" style="display:none;"></span>'
							 +'		<div class="clear"></div>'
							 +'	</div>'
							 +'</li>'; 
							 
						if(data[i].flag=='Role'){
							roleHtml+=tempHtml;
						}else{
							areaHtml+=tempHtml;
							totalNum++;
						}
					 }
				 }
				 $("#roleList").html(roleHtml);
				 $("#areaList").html(areaHtml);
				 
				 $("input.ui-input-text.ui-body-c").attr("placeholder","输入关键字【"+totalNum+"个供您查询】");
				 }
			 }
		 });
		 		
		}
	}); 
}
</script>
</head>
<body style="padding-bottom: 70px;">
<div id="pic" style="width:90%;border-radius:10px;background:rgba(0,0,0,0.7);height:80%;position:fixed;left:5%;top:10%;display:none;z-index:9999" >
<img style="position:absolute;left:5%;top:5%;width:90%;height:90%;" src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000DmjQs&oid=00D90000000pkXM" alt=""/>
 <img class="picClose" style="position:absolute;top:10px;right:10px;width:15px;height:auto;" src="../MetroStyleFiles/Close2.png" alt=""/> 

</div>
<div id="return-top" style="display: none;"><img class="scroll-top"  src="../Jsp/PIC/upgrade.png"  alt="" width="50px"></div>
<div style="padding:10px;padding-top:5px;border-bottom:2px solid #000;position:relative;padding-bottom:5px;margin-bottom:5px;"> 
					<img src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9IMj&oid=00D90000000pkXM" alt="Logo" class="HpLogo" style="display:inline !important;height:55px !important;width:auto !important;float:none;padding:0px;vertical-align:bottom;padding-bottom:10px;">
					<!-- <span class="clientSubName" style="font-size:12px;padding-left:7px;color:#333;">MDM China</span>
					<h2 style="color:#333;font-size:18px;padding:0px;padding-left:5px;font-weight:bold;margin-top:5px;font-family:HP Simplified, Arial, Sans-Serif !important;" class="clientName">DXC Technology Coperation</h2>
					 -->
			
					<p style="position: absolute;right: 10px;top: 10px;font-size: 15px;">欢迎您： <%=res.get("NickName") %></p><img style="border-radius:25px;height:35px;width:35px;position:absolute;top:30px;right:10px;" src="<%=res.get("HeadUrl") %>" alt=""/>
				<input id="openid" type="hidden" value="<%=uid%>"/><input id="role" type="hidden" value="<%=res.get("role")%>"/>
</div>		
	<div class="TABclass">
		<div id="logo_now_color" style="border-top: 4px solid #fff; padding-top: 5px;">
			<ul class="nav nav-tabs" id="myTabs"
				style="border-color: #000;" style="padding-left: 5px;">
				<li  class="active"><a href="#roleElements" data-toggle="tab"
					style="border-right-color: #000; border-top-color: #000; border-left-color: #000;font-size:18px;vertical-align:middle;line-height:20px;"><img src="../MetroStyleFiles/Directions.png" style="height:20px;vertical-align:top;padding-right:5px;"/>职业发展</a></li>
				<li><a href="#areaElements" data-toggle="tab"
					style="border-right-color: #000; border-top-color: #000; border-left-color: #000;font-size:18px;vertical-align:middle;line-height:20px;"><img src="../MetroStyleFiles/Workshop.png" style="height:20px;vertical-align:top;padding-right:5px;"/>技术领域</a></li>
			</ul>
			<div class="tab-content" id="dvTabContent"
				style="border: 0px; padding-top: 0px;margin-top:0px;">
				<div class="tab-pane" id="areaElements">
				<!-- start logoElements-->
					<div  style="position: absolute; top: 125px;overflow:hidden" data-role="page" style="padding-top:15px" data-theme="c">
						<ul id="areaList" data-role="listview" data-autodividers="false" data-filter="true" data-filter-placeholder="输入关键字" data-inset="true" style="margin-top:15px">
						</ul>
					</div>
					<!-- end logoElements-->
				</div>
				<div class="tab-pane active" id="roleElements">
					<!-- start logoElements
					<div  style="position: absolute; top: 120px;overflow:hidden" data-role="page" style="padding-top:15px" data-theme="c">
						<ul id="roleList" data-role="listview" data-autodividers="false" data-filter="true" data-filter-placeholder="输入关键字" data-inset="true" style="margin-top:15px">
						</ul>
					</div>-->
					<div style="width:100%;overflow:hidden">
					<ul id="roleList"  style="margin: 0px;padding: 0px; margin-top: -20px;">
						</ul>
					<!-- end logoElements-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- BEGIN FOOTER -->
	<div id="footer">
		<span class="clientCopyRight"><nobr></nobr></span>
	</div>
	<!-- END FOOTER -->
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
	
</body>
</html>