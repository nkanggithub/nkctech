<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%@ page import="java.text.SimpleDateFormat"%>
 <%
MongoDBBasic.addSkimNum();
  String uid = request.getParameter("UID");
Map runMaintain = MongoDBBasic.getRunMaintainMetrics();
List<Integer> apj=(List<Integer>)runMaintain.get("APJ");
List<Integer> usa=(List<Integer>)runMaintain.get("USA");
List<Integer> mexico=(List<Integer>)runMaintain.get("MEXICO");
List<Integer> emea=(List<Integer>)runMaintain.get("EMEA");
Map immetrics = MongoDBBasic.getIMMetrics();
Integer apj2=(Integer)immetrics.get("APJ");
Integer usa2=(Integer)immetrics.get("USA");
Integer mexico2=(Integer)immetrics.get("MEXICO");
Integer emea2=(Integer)immetrics.get("EMEA");
Integer other=(Integer)immetrics.get("OTHER");
SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd"); 
Date date=new Date();
String currentDate = format.format(date);
HashMap<String, String> res=MongoDBBasic.getWeChatUserFromOpenID(uid);
MongoDBBasic.updateVisited(uid,currentDate,"DV_Mobile",res.get("HeadUrl"),res.get("NickName"));

%> 
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <title>数据可视化</title>
    	<script type="text/javascript" src="../Jsp/JS/fusioncharts.js"></script>
    	<script type="text/javascript" src="../Jsp/JS/fusioncharts.powercharts.js"></script>
	<script type="text/javascript" src="../Jsp/JS/fusioncharts.widgets.js"></script>
	<script type="text/javascript" src="../Jsp/JS/fusioncharts.theme.fint.js"></script>
			 <script src="../mdm/uploadfile_js/jquery-1.11.2.min.js"></script>
	<script src="../mdm/uploadfile_js/pageSwitch.js"></script>
	

     <script>
     FusionCharts.ready(function () {
        var estProcChart = new FusionCharts({
            type: 'errorline',
            renderAt: 'chart-container1',
            width: '400',
            height: '300',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    "theme": "fint",
                    "xaxisname": "",
                    "yaxisname": "",
                    "numberSuffix":"",
                    "caption": "Run & Maintain Break Fix By Region",
                    "subcaption": "",
                    "showvalues": "0",
                    "plottooltext": "$seriesname, $value",
                    //Error bar configuration
                    "halferrorbar": "0",
                    "errorBarColor": "#990000",
                    "errorBarAlpha": "0",
                    "errorBarThickness": "4",
                    "errorBarWidth": "8"
                },
                "categories": [
                    {
                        "category": [
                            { "label": "Done" },
                            { "label": "Work In Progress" }, 
                            { "label": "In Planning" }
                        ]
                    }
                ],
                "dataset": [
                            {
                                "seriesname": "APJ",
                                "data": [
        <%  for(int i=0;i<apj.size();i++){ 
        	 if(i==apj.size()-1){%>
        	 {
                "value": "<%=apj.get(i)%>",
                "errorvalue": ""
            }
        	 <%}else{%>
        	 {
                "value": "<%=apj.get(i)%>",
                "errorvalue": ""
            },
        <%}%>
        <%}%>
                                ]
                            }, {
                                "seriesname": "USA",
                                "data": [
        <%  for(int i=0;i<usa.size();i++){ 
        	 if(i==usa.size()-1){%>
        	 {
               "value": "<%=usa.get(i)%>",
               "errorvalue": ""
           }
        	 <%}else{%>
        	 {
               "value": "<%=usa.get(i)%>",
               "errorvalue": ""
           },
        <%}%>
        <%}%>
                                ]
                            }, {
                                "seriesname": "Mexico",
                                "data": [
        <%  for(int i=0;i<mexico.size();i++){ 
        	 if(i==mexico.size()-1){%>
        	 {
               "value": "<%=mexico.get(i)%>",
               "errorvalue": ""
           }
        	 <%}else{%>
        	 {
               "value": "<%=mexico.get(i)%>",
               "errorvalue": ""
           },
        <%}%>
        <%}%>
                                ]
                            }, {
                                "seriesname": "EMEA",
                                "data": [
        <%  for(int i=0;i<emea.size();i++){ 
        	 if(i==mexico.size()-1){%>
        	 {
              "value": "<%=emea.get(i)%>",
              "errorvalue": ""
          }
        	 <%}else{%>
        	 {
              "value": "<%=emea.get(i)%>",
              "errorvalue": ""
          },
        <%}%>
        <%}%>
                                ]
                            }
                ]
            }
        }).render();
        var revenueChart = new FusionCharts({
	        type: 'doughnut2d',
	        renderAt: 'chart-container2',
	        width: '400',
	        height: '300',
	        dataFormat: 'json',
	        dataSource: {
	            "chart": {
	                "caption": "Closed IM Metrics By Region",
	                "subCaption": "",
	                "numberPrefix": "",
	                "startingAngle": "20",
	                "showPercentValues": "1",
	                "showPercentInTooltip": "0",
	                "enableSmartLabels": "0",
	                "enableMultiSlicing": "0",
	                "decimals": "1",
	                //Theme
	                "theme": "fint"
	            },
	            "data": [
	    	                {
	    	                    "label": "APJ",
	    	                    "value": "<%=apj2 %>"
	    	                },
	    	                {
	    	                    "label": "USA",
	    	                    "value": "<%=usa2 %>"
	    	                }, 
	    	                {
	    	                    "label": "MEXICO",
	    	                    "value": "<%=mexico2 %>"
	    	                }, 
	    	                {
	    	                    "label": "EMEA",
	    	                    "value": "<%=emea2 %>"
	    	                },
	    	                {
	    	                    "label": "OTHER",
	    	                    "value": "<%=other %>"
	    	                }
	            ]
	        }
	    }).render();
        var chargePercent = 50,
        flag = 0,
        count = 0,
        chart = new FusionCharts({
            type: 'hled',
            renderAt: 'chart-container3',
            id : 'myHLED',
            width: '150',
            height: '100',
            dataFormat: 'json',
            dataSource: {
                "chart": {
                    "caption": "Battery Charge",
                    "lowerLimit": "0",
                    "upperLimit": "100",
                    "showTickMarks": "0",
                    "numberSuffix": "%",
                    "valueFontSize" : "12",
                    "origW": "300",
                    "origH": "200",
                    "ledGap" :"0",
                    "showhovereffect": "1",
                    //Single Fill color
                    "useSameFillColor" : "1",
                    "chartBottomMargin": "20",
                    "theme" : "fint"
                },
                //All annotations are grouped under this element
                "annotations": {
                    
                    "groups": [                        
                        {                  
                            //Each group needs a unique ID
                            "id": "indicator",
                            "showbelow": "1",
                            "items": [
                                
                                {
                                    "id": "bgRectAngle",
                                    //Polygon item 
                                    "type": "rectangle",
                                    "radius" : "5",
                                    "fillColor": "#333333",            
                                    "x" : "$gaugeEndX - 10",
                                    "tox" : "$gaugeEndX + 12",
                                    "y" : "$gaugeCenterY-20",
                                    "toy" : "$gaugeCenterY + 20"
                                }
                            ]
                        },
                        {                  
                            //Each group needs a unique ID
                            "id": "remainingTime",
                            "showbelow": "0",
                            "items": [
                                
                                {
                                    "id": "remainingTxt",
                                    //Polygon item 
                                    "type": "text",
                                    "text" : "",
                                    "fontColor": "#FFFFFF",
                                    "fontSize" : "12",
                                    "bold" : "1",
                                    "x" : "$gaugeCenterX",
                                    "y" : "$gaugeCenterY",
                                }
                            ]
                        }
                    ]
                    
                },
                "colorRange": {
                    "color": [
                        {
                            "minValue": "0",
                            "maxValue": "45",
                            "code": "#e44a00"
                        }, 
                        {
                            "minValue": "45",
                            "maxValue": "75",
                            "code": "#f8bd19"
                        }, 
                        {
                            "minValue": "75",
                            "maxValue": "100",
                            "code": "#6baa01"
                        }
                    ]
                },
                "value": "100"
            },
            "events" :{
                "renderComplete" : function (evt, arg) {
                    
                },
                "realTimeUpdateComplete" : function (evt, arg){
                
                }
            }
            
        })
    .render();
    }); 
    </script> 
</head>
<body style="margin:0px">
<div style="padding-left: 10px;height: 70px;border-bottom: 4px solid black;padding-top: 10px;">
<img src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9IMj&amp;oid=00D90000000pkXM" alt="" style="width:60%;"></div>
	<div id="container">
		<div class="page page1" id="section0">
			
<div id="chart-container1" style="text-align:center;margin-top: 130px;"></div>
		</div>
		<div class="page page2" id="section1">
<div id="chart-container2" style="text-align:center;margin-top: 120px;"></div>
			</div>
			<div class="page page3" id="section0">
			
<div id="chart-container3" style="text-align:center;margin-top: 130px;"></div>
		</div>
			
		</div>
		
		<img id="guide" style="display:none;width: 50%;position: absolute;left: 25%;top: 400px;" src="../mdm/images/swipe_left.png" alt="" />
	<script type="text/javascript">
	setTimeout(function(){//定时器 
		$("#guide").fadeIn(3000);
		$("#guide").fadeOut(3000);
		},
		500);
	 var a=new pageSwitch('container',{
     duration:1500,
     start:0,
     direction:0,
     loop:false,
     ease:'ease',
     transition:'slide',
		freeze:false,
		mouse:true,
     mousewheel:true,
     arrowkey:true
 });
	
	
	</script>
</body></html>