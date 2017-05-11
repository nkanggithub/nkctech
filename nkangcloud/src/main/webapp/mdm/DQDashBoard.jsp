<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page import="java.util.*" %>
<%@ page import="com.nkang.kxmoment.baseobject.MdmDataQualityView" %>
<%@ page import="com.nkang.kxmoment.baseobject.GeoLocation" %>  
<%-- <%@ page import="com.nkang.kxmoment.util.DBUtils"%> --%>
<%@ page import="com.nkang.kxmoment.util.RestUtils"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%@ page import="com.nkang.kxmoment.baseobject.WeChatUser"%>
<%@ page import="com.nkang.kxmoment.util.Constants"%>
<%					
MdmDataQualityView mqv = new MdmDataQualityView();
mqv= RestUtils.callGetDataQualityReport();
String AccessKey =RestUtils.callGetValidAccessKey();
String uid = request.getParameter("UID"); 
MongoDBBasic.updateUser(uid);
GeoLocation loc = RestUtils.callGetDBUserGeoInfo(uid);
WeChatUser wcu = RestUtils.getWeChatUserInfo(AccessKey, uid);
String curLoc = RestUtils.getUserCurLocWithLatLng(loc.getLAT() , loc.getLNG()); 
%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
  <head>
	<meta charset="utf-8" />
	<title>MDM DashBoard</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="hpe" />
	<link rel="icon" type="image/x-icon" href="../webitem/hpe.ico">
	<link rel="short icon" type="image/x-icon" href="../webitem/hpe.ico">
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

	<script type="text/javascript">
		$j = jQuery.noConflict();
		$j(function(){
			var gauges;
		    $j("#liBoardCompetitor").click(function(){
		    	gauges = null;
		         <% 
			         MdmDataQualityView mqvByStateCityJB = new MdmDataQualityView();
			         mqvByStateCityJB = RestUtils.callGetDataQualityReportByParameter("重庆市","江北区","");
			         MdmDataQualityView mqvByStateCityYB = new MdmDataQualityView();
			         mqvByStateCityYB = RestUtils.callGetDataQualityReportByParameter("重庆市","渝北区","");
			         MdmDataQualityView mqvByStateCitySPB = new MdmDataQualityView();
			         mqvByStateCitySPB = RestUtils.callGetDataQualityReportByParameter("重庆市","沙坪坝区","");
			         MdmDataQualityView mqvByStateCityBB = new MdmDataQualityView();
			         mqvByStateCityBB = RestUtils.callGetDataQualityReportByParameter("重庆市","北碚区","");
			         MdmDataQualityView mqvByStateCityNA = new MdmDataQualityView();
			         mqvByStateCityNA = RestUtils.callGetDataQualityReportByParameter("重庆市","南岸区","");
			         MdmDataQualityView mqvByStateCityBN = new MdmDataQualityView();
			         mqvByStateCityBN = RestUtils.callGetDataQualityReportByParameter("重庆市","巴南区","");
			         MdmDataQualityView mqvByStateCityJLP = new MdmDataQualityView();
			         mqvByStateCityJLP = RestUtils.callGetDataQualityReportByParameter("重庆市","九龙坡区","");
			         MdmDataQualityView mqvByStateCityZX = new MdmDataQualityView();
			         mqvByStateCityZX = RestUtils.callGetDataQualityReportByParameter("重庆市","忠县","");
		         %>
		     });
		    
		    $j("#liBoardPartner").click(function(){
		    	updateGauges();
		    });
		    
		    $j("#liSocialElements").click(function(){
		    	
			
		    });
		    
		    $j("#liBoardCustomer").click(function(){
		    	var w = 300, h = 300;
				var colorscale = d3.scale.category10();
				//Legend titles
				var LegendOptions = ['Smartphone','Tablet'];
				//Data
				var d = [
							<%=RestUtils.CallGetJSFirstSegmentArea()%>
						];

				//Options for the Radar chart, other than default
				var mycfg = {
				  w: w,
				  h: h,
				  maxValue: 0.6,
				  levels: 6,
				  ExtraWidthX: 300
				};
				//Call function to draw the Radar chart
				//Will expect that data is in %'s
				RadarChart.draw("#chartRadar", d, mycfg);

				////////////////////////////////////////////
				/////////// Initiate legend ////////////////
				////////////////////////////////////////////

				var svg = d3.select('#body')
					.selectAll('svg')
					.append('svg')
					.attr("width", w+10)
					.attr("height", h);

				//Create the title for the legend
				var text = svg.append("text")
					.attr("class", "title")
					.attr('transform', 'translate(-220,0)') 
					.attr("x", w - 70)
					.attr("y", 10)
					.attr("font-size", "12px")
					.attr("fill", "#404040")
					.text("Segment Area Distribution");

				//Initiate Legend	
				var legend = svg.append("g")
					.attr("class", "legend")
					.attr("height", 100)
					.attr("width", 150)
					.attr('transform', 'translate(-220,40)') ;
					//Create colour squares
					legend.selectAll('rect')
					  .data(LegendOptions)
					  .enter()
					  .append("rect")
					  .attr("x", w - 65)
					  .attr("y", function(d, i){ return i * 20;})
					  .attr("width", 10)
					  .attr("height", 10)
					  .style("fill", function(d, i){ return colorscale(i);})
					  ;
					//Create text next to squares
					legend.selectAll('text')
					  .data(LegendOptions)
					  .enter()
					  .append("text")
					  .attr("x", w - 52)
					  .attr("y", function(d, i){ return i * 20 + 9;})
					  .attr("font-size", "11px")
					  .attr("fill", "#737373")
					  .text(function(d) { return d; });	
			});
		 });
	</script>
	<script>
		var gauges = [];
		function createGauge(name, label, min, max)
		{
			var config = 
			{
				size: 130,
				label: label,
				min: undefined != min ? min : 0,
				max: undefined != max ? max : 250,
				minorTicks: 5
			}
			var range = config.max - config.min;
			config.yellowZones = [{ from: config.min + range*0.75, to: config.min + range*0.9 }];
			config.redZones = [{ from: config.min + range*0.9, to: config.max }];
			gauges[name] = new Gauge(name + "GaugeContainer", config);
			gauges[name].render();
		}
		
		function createGauges()
		{
			createGauge("g4t4591", "g4t4591");
			createGauge("g4t4592", "g4t4592");
			createGauge("g4t4593", "g4t4593");
			
		}
		
		function updateGauges()
		{
			for (var key in gauges)
			{
				var value = getRandomValue(gauges[key]);
				gauges[key].redraw(value);
			}
		}
		
		function getRandomValue(gauge)
		{
			var overflow = 0; //10;
			return gauge.config.min - overflow + (gauge.config.max - gauge.config.min + overflow*2) *  0.3;
		}
	
	</script>

<style>
/* Dashboard Style Start */

        .liquidFillGaugeText { font-family: Helvetica; font-weight: bold; }
        .legend span {
				width: 33.333333%;
				display: inline-block;
				text-align: center;
				cursor: pointer;
				color: white;
			}
        #fillgauge4{
        	position:relative;
        	left:25%;
        	visibility:hidden;
        }
        #fillgauge5{
        	position:relative;
        	/* left:25%; */
        	left:25%;
        	top:-25px;
        }

        #svgDistribution{
            position:relative;
        	left:10%;
        	top:-50px;
        }
        
		path.slice{
			stroke-width:2px;
		}
		polyline{
			opacity: .3;
			stroke: black;
			stroke-width: 2px;
			fill: none;
		} 
		svg text.percent{
			fill:white;
			text-anchor:middle;
			font-size:15px;
		}
/* Dashboard Style End */
    
    .modal-body {
	    position: relative;
	    top: -50px;
	    /* max-height: 400px; */
	    padding: 15px;
	    overflow-y: auto;
	    color: #4F4F4F;
	}
    
    
	.HpLogo{
	width:200px;
	height:60px;
	}

	#navlist li
	{
	display: inline;
	list-style-type: none;
	padding-right: 30px;
	white-space: nowrap;
	}
	.mainTitle{
	font-size:15px;
	}

	.loading {
	display: none;
	opacity: 0.7;
	filter:alpha(opacity=70); 
	position: absolute;
	left: 0;
	top: 0;
	width: 1000px;
	height: 100%;
	background-color: #FFFFFF;
	z-index: 1000;
	}


	.myButtons{
	background: none  !important;
	border: 0px  !important;
	background-color: #007DBA  !important;
	color: #fff !important;
	font-size: 13px  !important;
	border-radius: 0px  !important;
	padding: 6px 6px 6px 6px  !important;
	border-bottom-left-radius: 6px  !important;
	border-top-right-radius: 6px  !important;

	}
	.myButtons:hover{
	background-color: #D7410B !important;
	}
	.myDisabledButtons{
	background: none  !important;
	border: 0px  !important;
	background-color: gray !important;
	color: #fff !important;
	font-size: 13px  !important;
	border-radius: 0px  !important;
	padding: 6px 6px 6px 6px  !important;
	border-bottom-left-radius: 6px  !important;
	border-top-right-radius: 6px  !important;
	}
	.parentDisable{
	overflow:hidden;
	width:100%;
	//height:550px;
	clear: both;
	}



	/* upload button */
	::-webkit-file-upload-button {
	background: none;
	border: 0px;
	background-color: #007DBA;
	color: #fff !important;
	font-size: 14px;
	border-radius: 0px;
	padding: 8px 8px 8px 8px;
	border-bottom-left-radius: 6px;
	border-top-right-radius: 6px;
	}

	.linkBtn {
	background: none !important;
	border: 0px !important;
	background-color: #007DBA !important;
	color: #fff !important;
	font-size: 14px !important;
	border-radius: 0px !important;
	padding: 8px 8px 8px 8px !important;
	border-bottom-left-radius: 6px !important;
	border-top-right-radius: 6px !important;
	}
	.clear{clear:both}
	#positionToggle{position:absolute; top:9px; left:9px; }
	#showHideList{display:none; position:absolute; top:10px; left:10px; border-radius: 5px; border:1px solid #cccccc; background-color:#eeeeee; }
	#ListInner{padding:10px 10px 10px 10px; }

	.clearfix {
	*zoom: 1;
	}

	.clearfix:before, .clearfix:after {
	display: table;
	line-height: 0;
	content: "";
	}

	.clearfix:after {
	clear: both;
	}

	/*label,select,.ui-select-menu { float: left; margin-right: 10px; }
	.wrap ul.ui-selectmenu-menu-popup li a { font-weight: bold; }*/
	.ui-selectmenu{
	height: 1.5em;
	}
	.ui-selectmenu-status {
	line-height: 1.0em;
	}
	
	
/* dashboar area start*/		
	.axis path,
	.axis line {
	  fill: none;
	  stroke: #000;
	  shape-rendering: crispEdges;
	}
	.area {
	  fill: #00b287;
	}
/* dashboar area end*/

			
</style>

</head>

<body onload ="createGauges();">
    <div class="navbar">
      <div class="navbar-inner">
        <div class="container-fluid">
			  <img src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=015900000053FQo&oid=00D90000000pkXM&lastMod=1438220916000" alt="HP Logo" class="HpLogo"/> 
              <ul class="nav pull-right top-menu">
                <li class="dropdown">
                	<a href="#" class="dropdown-toggle" data-toggle="dropdown"> Welcome <span class="username colorBlue"> <%= wcu.getNickname()%> </span> 
						<span><a style="float:right;" href="profile.jsp?UID=<%= uid%>"> <img src="<%= wcu.getHeadimgurl()%>" alt="userImage" class="userImage" alt="no_username"/></a></span> 
                  	</a>
                </li>
              </ul>
         </div>
        </div>
    </div>

  <!-- BEGIN CONTAINER -->
  <div id="container" class="row-fluid"> 
    <!-- BEGIN PAGE -->
    <div id="main-content">
      <div class="BDbg">
        <div class="BDheading">
          <div class="span8"> 
            <div id="divBoardName"  style="dispaly:none" title='LBName'></div>
            <h4> <span class="colorDarkBlue"> Master Data Lake Quality Grade</span></h4>
          </div>
          <div class="span4 BoardFormbtn" ></div>
        </div>
        <div class="container-fluid">
          <div class="row-fluid mtop10">
            <div class="span8">
              <div class="PositionR">
              
<svg id="fillgauge4" width="0%" height="0" onclick="gauge4.update(NewValue());"></svg>
<svg id="fillgauge5" width="50%" height="220" onclick="gauge5.update(NewValue());"></svg>

<script>
var percent = <%= mqv.getPercents() %>
var percent2 = 100*percent;
  var gauge4 = loadLiquidFillGauge("fillgauge4", 50);
    var config4 = liquidFillGaugeDefaultSettings();
    config4.circleThickness = 0.15;
   config4.circleColor = "#EDEDED";
    config4.textColor = "#00b287";
    config4.waveTextColor = "#00b287";
    config4.waveColor = "#00b287";
    config4.textVertPosition = 0.8;
    config4.waveAnimateTime = 1000;
    config4.waveHeight = 0.05;
    config4.waveAnimate = true;
    config4.waveRise = false;
    config4.waveHeightScaling = false;
    config4.waveOffset = 0.25;
    config4.textSize = 0.75;
    config4.waveCount = 3;
  var gauge5 = loadLiquidFillGauge("fillgauge5", percent2 , config4);
    var config5 = liquidFillGaugeDefaultSettings();
    config5.circleThickness = 0.4;
    config5.circleColor = "#00b287";
    config5.textColor = "#00b287";
    config5.waveTextColor = "#00b287";
    config5.waveColor = "#00b287";
    config5.textVertPosition = 0.52;
    config5.waveAnimateTime = 5000;
    config5.waveHeight = 0;
    config5.waveAnimate = false;
    config4.waveRise = true;
    config5.waveCount = 2;
    config5.waveOffset = 0.25;
    config5.textSize = 1.2;
    config5.minValue = 30;
    config5.maxValue = 150;
    config5.displayPercent = false;
    function NewValue(){
        return 100*mqv.getPercents;
    }
</script>


                <div class="BoardTit">
                  <div class="span8">
                      <h4 style="float:left;"><%= curLoc%></h4>
                  </div>
                </div>
              </div>
            </div>

            <div class="span4">
              <div class="BDdetailHeight">
                  <div>
						<a class="" data-toggle="modal" href="#LBreadmore"> Read more </a> 
                  </div>
              </div>  

              <div id="LBreadmore" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="static">
                <div class="modal-header">
                  <h2>More about data lake</h2>
                </div><br />
                <div class="modal-body readmoreHpop" style="white-space: pre-line;">
                    If you think of a datamart as a store of bottled water – cleansed and packaged and structured for easy consumption – the data lake is a large body of water in a more natural state. The contents of the data lake stream in from a source to fill the lake, and various users of the lake can come to examine, dive in, or take samples
  					<br /> --Pentaho CTO James Dixon	
                </div>
                <div class="modal-footer">
                  <button class="btnAthena" data-dismiss="modal" aria-hidden="true">Cancel</button>
                </div>
              </div>
                
                
                
              <div id="TopicReadMore" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true" data-backdrop="static">
                <div class="modal-header">
                  <h3>Profile</h3>
                </div>
                <div class="modal-body">
                  <div id="Topic"></div>
                </div>
                <div class="modal-footer">
                  <button class="btnAthena" data-dismiss="modal" aria-hidden="true">Cancel</button>
                </div>
              </div>

            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid">
        <div class="row-fluid mtop20">
          <div class="span8">
            <div class="TABclass">
              <ul class="nav nav-tabs" id="myTabs">
                <li id="liSocialElements" class="active"><a href="#SocialElements" data-toggle="tab">拿铁</a></li>
                <li id="liBoardCustomer" ><a href="#BoardCustomer" data-toggle="tab">普莱诺</a></li>
                <li id="liBoardPartner" ><a href="#BoardPartner" data-toggle="tab">摩卡</a></li>
                <li id="liBoardCompetitor"><a href="#BoardCompetitor" data-toggle="tab">卡布奇诺</a></li>
              </ul>
              <div class="tab-content" id="dvTabContent">
                <div class="tab-pane" id="BoardPartner">
                  <div>
                    <div class="panel-group" id="accordion">
                      <div id="DivLearnings">
                      			<span id="g4t4591GaugeContainer"></span>
								<span id="g4t4592GaugeContainer"></span>
								<span id="g4t4593GaugeContainer"></span>

                      </div>
                    </div>
                  </div>
                </div>
                <div class="SocialElements tab-pane active" id="SocialElements"> <!-- Nati -->
                	<div id="chart2">
	                	<script>
							var chart = c3.generate({
							    data: {
							        columns: [
							            ['Customer', 70],
							            ['Partner', 20],
							            ['Competitor', 10]
							        ],
							        type: 'pie'
							    },
							    pie: {
							        label: {
							            format: function (value, ratio, id) {
							                return d3.format('#')(value);
							            }
							        }
							    },
							    bindto: '#chart2'
							});

						</script>
                	</div>

						
                      
                  </div>
                <div class="tab-pane" id="BoardCompetitor">
                  <div>
                      <div > <!-- class="span4" -->
                          <div class="row-fluid">
                              <div class="hpit_athena_rightDiv span12">
                                  <div style="overflow: auto; width: auto; max-height:442px;">
								     	<div id="chart"> </div>
												<script>
													var chart = c3.generate({
														data: {
															columns: [
																['客户', <%= mqvByStateCityJB.getNumberOfCustomer() %>	, <%= mqvByStateCityYB.getNumberOfCustomer() %>		, <%= mqvByStateCitySPB.getNumberOfCustomer() %>, <%= mqvByStateCityBB.getNumberOfCustomer() %>, <%= mqvByStateCityNA.getNumberOfCustomer() %>, <%= mqvByStateCityBN.getNumberOfCustomer() %>, <%= mqvByStateCityJLP.getNumberOfCustomer() %>, <%= mqvByStateCityZX.getNumberOfCustomer() %>],
																['竞争', <%= mqvByStateCityJB.getNumberOfCompetitor() %>	, <%= mqvByStateCityYB.getNumberOfCompetitor() %>	, <%= mqvByStateCitySPB.getNumberOfCompetitor() %>, <%= mqvByStateCityBB.getNumberOfCompetitor() %>, <%= mqvByStateCityNA.getNumberOfCompetitor() %>, <%= mqvByStateCityBN.getNumberOfCompetitor() %>, <%= mqvByStateCityJLP.getNumberOfCompetitor() %>, <%= mqvByStateCityZX.getNumberOfCompetitor() %>],
																['伙伴', <%= mqvByStateCityJB.getNumberOfPartner() %>	, <%= mqvByStateCityYB.getNumberOfPartner() %>		, <%= mqvByStateCitySPB.getNumberOfPartner() %>, <%= mqvByStateCityBB.getNumberOfPartner() %>, <%= mqvByStateCityNA.getNumberOfPartner() %>, <%= mqvByStateCityBN.getNumberOfPartner() %>, <%= mqvByStateCityJLP.getNumberOfPartner() %>, <%= mqvByStateCityZX.getNumberOfPartner() %>]
															]
														},
														axis: {
															x: {
																type: 'category',
																categories: ['江北区', '渝北区', '沙坪坝区', '北碚区', '南岸区', '巴南区','九龙坡区','忠县']
															}
														},
														zoom: {
													        enabled: true
													    }
													});
											    </script>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                </div>
                <div class="tab-pane" id="BoardCustomer">
                  <div>
                      <div > <!-- class="span4" -->
                          <div class="row-fluid">
                              <div class="hpit_athena_rightDiv span12">
                                  <div style="overflow: auto; width: auto; max-height:442px;">
                                       <div id="divContMember">	
											<div id="body">
											  <div id="chartRadar"></div>
										    </div>
    
											<script type="text/javascript">
												

											</script>
											
                                       </div>
                                  </div>
                              </div>
                          </div>
                      </div>
                  </div>
                </div>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
    <!-- END PAGE -->
      </div>
      
<!-- Modal PAGE Start-->   
      <div id="DivMyAlter" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
          <div class="modal-header">
            <h3>Error Message</h3>
          </div>
          <div class="modal-body">
            <div class="span5 ml0" id="myAlterMessage">
              
            </div>
          </div>
          <div class="modal-footer">
              <button class="btnAthena EbtnLess" data-dismiss="modal" aria-hidden="true">OK</button>
          </div>
    </div>
    <div id="DivMyConfirm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
          <div class="modal-header">
            <h3>Confirm Message</h3>
          </div>
          <div class="modal-body">
            <div class="span5 ml0" id="myConfirmMessage">
              
            </div>
          </div>
          <div class="modal-footer">
              <button class="btnAthena EbtnLess" data-dismiss="modal">Cancel</button>
              <button class="btnAthena EbtnLess" id="myConfirmSubmit">Submit</button>
          </div>
    </div>
<!-- Modal PAGE End-->   

  <div id="footer"> <span>©</span> 2016 Hewlett-Packard Enterprise Development Company, L.P.   |   HP Restricted </div>
  </body>
  </html>