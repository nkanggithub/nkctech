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
List<Integer> apj=(List<Integer>)map.get("APJ");
List<Integer> usa=(List<Integer>)map.get("USA");
List<Integer> mexico=(List<Integer>)map.get("MEXICO");
List<Integer> emea=(List<Integer>)map.get("EMEA");

List<String> NameLs = (ArrayList<String>)request.getAttribute("OutOfMapping"); 
%>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据可视化</title>
    	<script type="text/javascript" src="../Jsp/JS/fusioncharts.js"></script>
    	<script type="text/javascript" src="../Jsp/JS/fusioncharts.powercharts.js"></script>
	<script type="text/javascript" src="../Jsp/JS/fusioncharts.theme.fint.js"></script>

     <script>
    FusionCharts.ready(function () {
        var estProcChart = new FusionCharts({
            type: 'errorline',
            renderAt: 'chart-container',
            width: '660',
            height: '440',
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
        
    });
    </script> 
</head>
<body style="margin:0px">
<div style="height: 100px; border-bottom: 4px solid black;">
<img src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9IMj&amp;oid=00D90000000pkXM" alt="" style="width: 25%;"></div>
<div id="chart-container" style="text-align:center;margin-top: 40px;"></div>
<div><% if(NameLs!=null){ %>请联系管理员，更新Mapping <br> <%} %>
<% if(NameLs!=null)for(int i=0;i<NameLs.size();i++){%>
      outName =  <%=NameLs.get(i)%><br>
	
<%}%></div>
</body></html>