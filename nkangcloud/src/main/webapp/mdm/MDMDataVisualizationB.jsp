<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
 <%
MongoDBBasic.addSkimNum();
 %>
<%
Map map = (HashMap<String,List>)request.getAttribute("map");
Integer apj=(Integer)map.get("APJ");
Integer usa=(Integer)map.get("USA");
Integer mexico=(Integer)map.get("MEXICO");
Integer emea=(Integer)map.get("EMEA");
Integer other=(Integer)map.get("OTHER");

int total = (Integer)request.getAttribute("total");

List<String> NameLs = (ArrayList<String>)request.getAttribute("outNames"); 
%>
<!DOCTYPE html>
<html><head lang="en"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>数据可视化</title>
    <link href="../Jsp/JS/pizzaChart/css/app.css" media="screen, projector, print" rel="stylesheet" type="text/css" />

    	<script type="text/javascript" src="../Jsp/JS/fusioncharts.js"></script>
	<script type="text/javascript" src="../Jsp/JS/fusioncharts.theme.fint.js"></script>

     <script>
     FusionCharts.ready(function () {
    	    var revenueChart = new FusionCharts({
    	        type: 'doughnut2d',
    	        renderAt: 'chart-container',
    	        width: '880',
    	        height: '550',
    	        dataFormat: 'json',
    	        dataSource: {
    	            "chart": {
    	                "caption": "IM Metrics",
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
    	                    "value": "<%=apj %>"
    	                },
    	                {
    	                    "label": "USA",
    	                    "value": "<%=usa %>"
    	                }, 
    	                {
    	                    "label": "MEXICO",
    	                    "value": "<%=mexico %>"
    	                }, 
    	                {
    	                    "label": "EMEA",
    	                    "value": "<%=emea %>"
    	                },
    	                {
    	                    "label": "OTHER",
    	                    "value": "<%=other %>"
    	                }
    	            ]
    	        }
    	    }).render();
    	    
    	});
    </script> 
</head>
<body>
<div style="height: 120px; border-bottom: 4px solid black;">
<img src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9IMj&amp;oid=00D90000000pkXM" alt="" style="width: 25%;"></div>
<div id="chart-container" style="text-align:center;margin-top: 20px;"></div>
<div> total =  <%=total %></div>
<div><% if(NameLs!=null){ %>请联系管理员，更新Mapping <br> <%} %>
<% if(NameLs!=null)for(int i=0;i<NameLs.size();i++){%>
  outNames =  <%=NameLs.get(i)%><br>
	
<%}%></div>
</body></html>