<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>
<%@ page import="com.nkang.kxmoment.baseobject.MdmDataQualityView" %>
<%@ page import="com.nkang.kxmoment.baseobject.GeoLocation" %>  
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%@ page import="com.nkang.kxmoment.util.RestUtils"%>
<%@ page import="com.nkang.kxmoment.baseobject.WeChatUser"%>
<%@ page import="com.nkang.kxmoment.util.Constants"%>
<%@ page import="com.nkang.kxmoment.baseobject.ExtendedOpportunity"%>
<%					
MongoDBBasic.addSkimNum();
String fromUserName = request.getParameter("UID"); 
String CurType = "customer";
int curNum = 0;
%>

<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
  <head>
	<meta charset="utf-8" />
	<title>Customer Loaded</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="hpe" />
	<link href="../nkang/assets_athena/bootstrap/css/bootstrap.min.css" 			rel="stylesheet" type="text/css"/>
	<link href="../nkang/assets_athena/bootstrap/css/bootstrap-responsive.min.css" 	rel="stylesheet" type="text/css"/>
	<link href="../nkang/assets_athena/font-awesome/css/font-awesome.css" 			rel="stylesheet" type="text/css"/>
	<link href="../nkang/css_athena/style.css" 										rel="stylesheet" type="text/css"/>
	<link href="../nkang/assets_athena/icomoon/iconMoon.css" 						rel="stylesheet" type="text/css"/>
	<link href="../nkang/css_athena/style-responsive.css" 							rel="stylesheet" type="text/css"/>
	<link href="../nkang/css_athena/style-default.css" 								rel="stylesheet" type="text/css"/>
	<link href="../nkang/assets_athena/data-tables/DT_bootstrap.css" 				rel="stylesheet" type="text/css"/>
	<link href="../nkang/c3.css" 													rel="stylesheet" type="text/css"/>
	<script src="../nkang/js_athena/jquery-1.8.3.min.js"></script>
	<script src="../nkang/assets_athena/bootstrap/js/bootstrap.js"></script>
	<script src="../nkang/assets_athena/jquery-ui/jQuery_UI_1_10_3.js"></script>
	<script src="../nkang/js_athena/typeahead.js"></script>
	<script src="../nkang/js_athena/jquery.feedback_me.js"></script>
	<script src="../nkang/assets_athena/raty/ratyViewJs.js"></script>
	<script src="../nkang/assets_athena/data-tables/jquery.dataTables.js"></script>
	<script src="../nkang/assets_athena/data-tables/DT_bootstrap.js"></script>
	<script src="../nkang/js_athena/common-scripts.js"></script>
	<script src="../nkang/c3.min.js"></script>
    <script src="../nkang/d3.v3.min.js"></script>
    <script src="../nkang/liquidFillGauge.js"></script>
    <script src="../nkang/Donut3D.js"></script>
	<script src="../nkang/Chart.js"></script>
	<script src="../nkang/gauge.js"></script>
	<script src="../nkang/RadarChart.js"></script>
	
	<script src="../nkang/iscroll.js"></script>
	<script type="text/javascript">
	var myScroll,
	pullDownEl, pullDownOffset,
	pullUpEl, pullUpOffset,
	generatedCount = 0;

	function pullDownAction () {
		setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
			var el, li, i;
			el = document.getElementById('thelist');
			var div = document.getElementById('scroller');
	        var lis = div.getElementsByTagName("li");
	        curNum = lis.length;
			<%= MongoDBBasic.getJSMoreFiveOfCustomersFromMongoDB(fromUserName, "customer", curNum)%>
			myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
		}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
	}
	
	function pullUpAction () {
		setTimeout(function () {	// <-- Simulate network congestion, remove setTimeout from production!
			var el, li, i;
			el = document.getElementById('thelist');
			var div = document.getElementById('scroller');
	        var lis = div.getElementsByTagName("li");
	        curNum = lis.length;
			<%= MongoDBBasic.getJSMoreFiveOfCustomersFromMongoDB(fromUserName, "customer", curNum)%>
			myScroll.refresh();		// Remember to refresh when contents are loaded (ie: on ajax completion)
		}, 1000);	// <-- Simulate network congestion, remove setTimeout from production!
	}
	
	function loaded() {
		pullDownEl = document.getElementById('pullDown');
		pullDownOffset = pullDownEl.offsetHeight;
		pullUpEl = document.getElementById('pullUp');	
		pullUpOffset = pullUpEl.offsetHeight;
		
		myScroll = new iScroll('wrapper', {
			useTransition: true,
			topOffset: pullDownOffset,
			onRefresh: function () {
				if (pullDownEl.className.match('loading')) {
					pullDownEl.className = '';
					pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull Down To Refresh...';
				} else if (pullUpEl.className.match('loading')) {
					pullUpEl.className = '';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull Up To Load More...';
				}
			},
			onScrollMove: function () {
				if (this.y > 5 && !pullDownEl.className.match('flip')) {
					pullDownEl.className = 'flip';
					pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Release to refresh...';
					this.minScrollY = 0;
				} else if (this.y < 5 && pullDownEl.className.match('flip')) {
					pullDownEl.className = '';
					pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Pull down to refresh...';
					this.minScrollY = -pullDownOffset;
				} else if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
					pullUpEl.className = 'flip';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Release to refresh...';
					this.maxScrollY = this.maxScrollY;
				} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
					pullUpEl.className = '';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Pull up to load more...';
					this.maxScrollY = pullUpOffset;
				}
			},
			onScrollEnd: function () {
				if (pullDownEl.className.match('flip')) {
					pullDownEl.className = 'loading';
					pullDownEl.querySelector('.pullDownLabel').innerHTML = 'Loading...';				
					pullDownAction();	// Execute custom function (ajax call?)
				} else if (pullUpEl.className.match('flip')) {
					pullUpEl.className = 'loading';
					pullUpEl.querySelector('.pullUpLabel').innerHTML = 'Loading...';				
					pullUpAction();	// Execute custom function (ajax call?)
				}
			}
		});
		
		setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
	}
	
	document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
	
	document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);
	</script>
<style type="text/css" media="all">
body,ul,li {
	padding:0;
	margin:0;
	border:0;
}

body {
	font-size:12px;
	-webkit-user-select:none;
    -webkit-text-size-adjust:none;
	font-family:helvetica;
}

#header {
	position:absolute; z-index:2;
	top:0; left:0;
	width:100%;
	height:45px;
	line-height:45px;
	background-color:#00b287;
/* 	background-image:-webkit-gradient(linear, 0 0, 0 100%, color-stop(0, #fe96c9), color-stop(0.05, #d51875), color-stop(1, #7b0a2e));
	background-image:-moz-linear-gradient(top, #fe96c9, #d51875 5%, #7b0a2e);
	background-image:-o-linear-gradient(top, #fe96c9, #d51875 5%, #7b0a2e); */
	padding:0;
	color:#eee;
	font-size:20px;
	text-align:center;
}

#header a {
	color:#f3f3f3;
	text-decoration:none;
	font-weight:bold;
	text-shadow:0 -1px 0 rgba(0,0,0,0.5);
}

/* #footer {
	position:absolute; z-index:2;
	bottom:0; left:0;
	width:100%;
	height:48px;
	background-color:#222;
	background-image:-webkit-gradient(linear, 0 0, 0 100%, color-stop(0, #999), color-stop(0.02, #666), color-stop(1, #222));
	background-image:-moz-linear-gradient(top, #999, #666 2%, #222);
	background-image:-o-linear-gradient(top, #999, #666 2%, #222);
	padding:0;
	border-top:1px solid #444;
}
 */
#wrapper {
	position:absolute; z-index:1;
	top:45px; bottom:48px; left:-9999px;
	width:100%;
	background:#aaa;
	overflow:auto;
}

#scroller {
	position:absolute; z-index:1;
/*	-webkit-touch-callout:none;*/
	-webkit-tap-highlight-color:rgba(0,0,0,0);
	width:100%;
	padding:0;
}

#scroller ul {
	list-style:none;
	padding:0;
	margin:0;
	width:100%;
	text-align:left;
}

#scroller li {
	padding:0 10px;
	height:40px;
	line-height:40px;
	border-bottom:1px solid #ccc;
	border-top:1px solid #fff;
	background-color:#fafafa;
	font-size:14px;
}

#myFrame {
	position:absolute;
	top:0; left:0;
}



/**
 *
 * Pull down styles
 *
 */
#pullDown, #pullUp {
	background:#fff;
	height:40px;
	line-height:40px;
	padding:5px 10px;
	border-bottom:1px solid #ccc;
	font-weight:bold;
	font-size:14px;
	color:#888;
}
#pullDown .pullDownIcon, #pullUp .pullUpIcon  {
	display:block; float:left;
	width:40px; height:40px;
	background:url(pull-icon@2x.png) 0 0 no-repeat;
	-webkit-background-size:40px 80px; background-size:40px 80px;
	-webkit-transition-property:-webkit-transform;
	-webkit-transition-duration:250ms;	
}
#pullDown .pullDownIcon {
	-webkit-transform:rotate(0deg) translateZ(0);
}
#pullUp .pullUpIcon  {
	-webkit-transform:rotate(-180deg) translateZ(0);
}

#pullDown.flip .pullDownIcon {
	-webkit-transform:rotate(-180deg) translateZ(0);
}

#pullUp.flip .pullUpIcon {
	-webkit-transform:rotate(0deg) translateZ(0);
}

#pullDown.loading .pullDownIcon, #pullUp.loading .pullUpIcon {
	background-position:0 100%;
	-webkit-transform:rotate(0deg) translateZ(0);
	-webkit-transition-duration:0ms;

	-webkit-animation-name:loading;
	-webkit-animation-duration:2s;
	-webkit-animation-iteration-count:infinite;
	-webkit-animation-timing-function:linear;
}

@-webkit-keyframes loading {
	from { -webkit-transform:rotate(0deg) translateZ(0); }
	to { -webkit-transform:rotate(360deg) translateZ(0); }
}

</style>
</head>
<body>

<div id="header"><a href="http://cubiq.org/iscroll">Nearby Customers</a></div>
<div id="wrapper">
	<div id="scroller">
		<div id="pullDown">
			<span class="pullDownIcon"></span><span class="pullDownLabel">Pull Down To Refresh...</span>
		</div>

		<ul id="thelist">
			<%= MongoDBBasic.getJSListOfCustomersFromMongoDB(fromUserName, "customer")%>
		</ul>
		<div id="pullUp">
			<span class="pullUpIcon"></span><span class="pullUpLabel">Pull Up to Refresh...</span>
		</div>
	</div>
</div>
  <div id="footer"> <span>©</span> Hewlett-Packard Enterprise Development Company, L.P.   |   HP Restricted </div>
</body>
</html>