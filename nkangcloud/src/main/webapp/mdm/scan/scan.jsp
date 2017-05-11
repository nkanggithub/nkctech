<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,org.json.JSONObject"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="com.nkang.kxmoment.baseobject.GeoLocation"%>
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
MongoDBBasic.updateVisited(uid,currentDate,"scan",res.get("HeadUrl"),res.get("NickName"));
MongoDBBasic.updateUser(uid);
String AccessKey = RestUtils.callGetValidAccessKey();
WeChatUser wcu;
session.setAttribute("UID", uid);
if (session.getAttribute("location") == null) {
	//GeoLocation loc = RestUtils.callGetDBUserGeoInfo(uid);
	wcu = RestUtils.getWeChatUserInfo(AccessKey, uid);
	session.setAttribute("wcu", wcu);
} else {
	wcu = (WeChatUser) session.getAttribute("wcu");
} 
%>
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <title>扫描附近</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="stylesheet" href="ming.css">
    
<link rel="stylesheet" type="text/css"
	href="../../nkang/css_athena/profile.css" />
    <script src="jquery.min.js"></script>
<script type="text/javascript" src="../../Jsp/JS/fusioncharts.js"></script>
<script type="text/javascript" src="../../Jsp/JS/fusioncharts.powercharts.js"></script>
<script type="text/javascript" src="../../Jsp/JS/fusioncharts.theme.fint.js"></script>
<style>
ul li.Work_Mates_div_list_div2 {
    list-style: none;
    min-height: 60px !important;
}
.Work_Mates_div2 .Work_Mates_div_list_div2 {
    border-top: #ccc 1px solid;
    border-bottom: 0px;
    padding-left: 15px;
    padding-right: 10px;
}
</style>
</head>
<body>
    <div class="container">
        <div id="load">
            <div class="border1">
                <div class="pointer"></div>
                <div class="user">
                </div>
                <div class="border2">
                    <div class="border3">
                        <div class="radar">
                            <span class="avatar">
                                <img src="<%=wcu.getHeadimgurl()%>" width="100%">
                            </span>
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn">开始扫描</button>
        </div>
    </div>
    <script>
    $(".btn").html("停止扫描");
	$(".pointer").addClass("pointerAnim");  //添加雷达旋转动画
	var timeout = 1000;  //每隔1000ms
	var index = 0;
	var addClass;
	var flag=true;
	var flagButton=true;
	var data;
	addClass = setInterval(function(){
		if(flag){
			 jQuery.ajax({
					type : "POST",
					url : "../../CallGetWeChatUserDistanceList?openid=<%=uid %>",
					cache : false,
					success : function(resData) {
						data=resData;
						for (var i = 0; i < data.length ; i++) {
							var temp = data[i];
							if(i==0){
								$("div.user").append('<span class="userOne">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'" width="100%">'
										+'                    </span>');
							}else if(i==1){
								$("div.user").append('                    <span class="userTwo">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==2){
								$("div.user").append('                    <span class="userThree">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==3){
								$("div.user").append('                    <span class="userFour">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==4){
								$("div.user").append('                    <span class="userFive">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==5){
								$("div.user").append('                    <span class="userSix">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==6){
								$("div.user").append('                    <span class="userSeven">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==7){
								$("div.user").append('                    <span class="userEight">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}else if(i==8){
								$("div.user").append('                    <span class="userNine">'
										+'                        <img src="'+temp.headimgurl+'" title="'+temp.nickname+'"  width="100%">'
										+'                    </span>');
							}
						}
						flag=false;
						$(".user span").eq(index++).fadeIn(timeout).addClass("flip");
					}
				});
		}else{
			if(index >= $(".user span").length){
				index = 0;
				addClass = clearInterval(addClass);  //结束循环
				$(".user span").removeClass("flip").addClass("shadow")
			}else {
				$(".user span").eq(index++).fadeIn(timeout).addClass("flip");
			}
		}
	},timeout);
       $(".btn").click(function () {
       	if(flagButton){
       		flagButton=false;
       	   //window.location.href="../personCharts.jsp?openid=<%=uid %>";
         	$(".pointer").removeClass("pointerAnim");  //删除雷达旋转动画
         	addClass = clearInterval(addClass);  //结束循环
			$(".user span").removeClass("shadow").addClass("flip");
			$(".btn").html("查看扫描结果");
			$('body').append('<a name="result"></a>:<div id="chart-container"></div>'
					+'<h2 style="text-align:center;background-color:#fff;font-size:16px;padding:15px 0px;">附近人员列表</h2><ul class="Work_Mates_div2" id="Work_Mates_div" data-role="listview" data-autodividers="false" data-filter="true" data-filter-placeholder="输入关键字" data-inset="true" style="background-color:#fff;">'
					+'</ul>');
			
			
			
			FusionCharts.ready(function () {
			    var budgetChart = new FusionCharts({
			        type: 'radar',
			        renderAt: 'chart-container',
			        width: '100%',
			        height: '450',
			        dataFormat: 'json',
			        dataSource: {
			    "chart": {
			        "caption": "附近人员【"+data.length+"/170】分布情况",
			        "bgcolor": "FFFFFF",
			        "radarfillcolor": "FFFFFF",
			        "plotfillalpha": "5",
			        "plotborderthickness": "2",
			        "anchoralpha": "500",
			        "numberprefix": "",
			        "numdivlines": "2",
			        "legendposition": "bottom",
			        "showborder": "0"
			    },
			    "categories": [
			        {
			            "font": "Arial",
			            "fontsize": "11",
			            "category": [
			                {
			                    "label": "北",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "西北",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "西",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "西南",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "南",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "东南",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "东",
			                    "font": "Arial",
			                    "fontsize": "11"
			                },
			                {
			                    "label": "东北",
			                    "font": "Arial",
			                    "fontsize": "11"
			                }
			               
			            ]
			        }
			    ],
			    "dataset": [
			     {
			            "seriesname": "10km以内",
			            "color": "0099FF",
			            "anchorsides": "10",
			            "anchorbordercolor": "0099FF",
			            "anchorgbalpha": "0",
			            "anchorradius": "2",
			            "data": [
			                {
			                    "value": "8"
			                },
			                {
			                    "value": "23"
			                },
			                {
			                    "value": "13"
			                },
			                {
			                    "value": "16"
			                },
			                {
			                    "value": "11"
			                },
			                {
			                    "value": "14"
			                },
			                {
			                    "value": "10"
			                },
			                {
			                    "value": "45"
			                },
			                {
			                    "value": "23"
			                },
			                {
			                    "value": "45"
			                },
			                {
			                    "value": "23"
			                },
			                {
			                    "value": "43"
			                }
			            ]
			        },
			        {
			            "seriesname": "50km以内",
			            "color": "CD6AC0",
			            "anchorsides": "6",
			            "anchorradius": "2",
			            "anchorbordercolor": "CD6AC0",
			            "anchorgbalpha": "0",
			            "data": [
			                {
			                    "value": "50"
			                },
			                {
			                    "value": "60"
			                },
			                {
			                    "value": "48"
			                },
			                {
			                    "value": "47"
			                },
			                {
			                    "value": "37"
			                },
			                {
			                    "value": "45"
			                },
			                {
			                    "value": "36"
			                },
			                {
			                    "value": "75"
			                },
			                {
			                    "value": "35"
			                },
			                {
			                    "value": "65"
			                },
			                {
			                    "value": "34"
			                },
			                {
			                    "value": "35"
			                }
			            ]
			        }
			       
			    ]
			}
			    }).render();
			});
			
			
			var ul = "",regNumber=0;
			//for (var i = data.length-1; i >0 ; i--) {
			for (var i = 0; i < data.length ; i++) {
				var temp = data[i];
				var companyName=temp.companyName;
				var companyRole=temp.role;
				var workDay=temp.workDay;
				if(temp.openid==$('#uid').val()){
					LastToLikeDate=temp.like.lastLikeDate;
					lastLikeTo=temp.like.lastLikeTo;
				}
				if(companyRole==null||companyRole=='null'||companyRole==''){
					companyRole="";
				}else{
					companyRole="【"+companyRole+"】";
				}
				companyName+=companyRole;
				if(temp.distance<1){
					workDay='<div style="float:right;background-color:#eee;color:#333;font-size:13px;padding:3px;margin-right:5px;position:relative;margin-top:-25px;opacity:0.85;">'+temp.distance*1000+'m</div>';
				}else{
					workDay='<div style="float:right;background-color:#eee;color:#333;font-size:13px;padding:3px;margin-right:5px;position:relative;margin-top:-25px;opacity:0.85;">'+temp.distance+'km</div>';
				}
				var li='	<li class="Work_Mates_div_list_div2">'
					+'                                           	 	<div class="Work_Mates_img_div2">'
					+'                                        			 <img src="'
					+ ((temp.headimgurl==null||temp.headimgurl=='')?'../MetroStyleFiles/image/user.jpg':temp.headimgurl)
					+ '" alt="userImage" class="matesUserImage" alt="no_username" /> '
					+'                                         		</div>'
					+'                                         		<div class="Work_Mates_text_div" style="margin-left: 80px;">'
					+'                                        			 <h2><span class="openid" style="display:none;">'+ temp.openid + '</span><span class="name">'
					+ temp.nickname
					+ '</span>'
					+'</h2>'
					+ '<div>'
					+'</div>'+workDay+'<div style="margin-top:5px;">'
					+'												</div>'
					+'                                        		</div>'
					+'                                                <div class="clear"></div>'
					+'                                          </li>';
				ul += li;
			}
			$("#Work_Mates_div").html(ul);
			
			
			}else{
				window.location.href="#result";
			}
       });
    </script>

</body></html>