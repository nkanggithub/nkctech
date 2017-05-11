<%@ page import="com.nkang.kxmoment.util.RestUtils"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%@ page import="com.nkang.kxmoment.baseobject.WeChatUser"%>
<%
String inputVar = "";
String uid = request.getParameter("UID");
MongoDBBasic.updateUser(uid);
WeChatUser wcu = new WeChatUser();
wcu.setNickname("Vistor");
try{
	String AccessKey =RestUtils.callGetValidAccessKey();
	if(uid != "" & AccessKey != null){
		wcu = RestUtils.getWeChatUserInfo(AccessKey, uid);
	}
}
catch(Exception e){
	wcu.setNickname("Vistor");
}

%>
<!DOCTYPE html>
<html lang="en" class="csstransforms csstransforms3d csstransitions">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MDM Makes it Matter</title> 
	<meta content="" name="description" />
	<meta content="" name="hpe" />
	<meta name="keywords" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<link rel="icon" type="image/x-icon" href="../webitem/hpe.ico">
	<link rel="short icon" type="image/x-icon" href="../webitem/hpe.ico">
	<link rel="stylesheet" type="text/css" media="all" href="../MetroStyleFiles/metro.css">
	<script src="../MetroStyleFiles/jquery.min.js"></script>
	<script src="../MetroStyleFiles/jquery.plugins.min.js"></script>
	<script src="../MetroStyleFiles/metro.js"></script>
	<script src="../MetroStyleFiles/sweetalert.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../MetroStyleFiles/sweetalert.css">
	<style type="text/css"> 
		.metro-layout .boxs { float: left; position: relative; margin: 0.5em; padding: 0.5em; background: #555; width: 20em; height: 6em; text-decoration: none; cursor: pointer; overflow: hidden; color: #fff; background: #00a8ec; z-index: 9; }
		.metro-layout .boxs:hover { opacity: 0.85; filter: alpha(opacity=85); }
		.metro-layout .boxs span { position: absolute; left: 0.5em; bottom: 0.5em; font-size: 0.9em; font-weight: normal; z-index: 8; }
		.metro-layout .boxs img.icon { position: absolute; left: 50%; top: 50%; margin-left: -32px; margin-top: -32px; z-index: 8; }
		.metro-layout .boxs img.big { margin-left: -64px; margin-top: -64px; }
		.metro-layout .boxs img.cover { position: absolute; left: 0; top: 0; width: 100%; z-index: 6; }
		.metro-layout .width2 { width: 20em; }
		.metro-layout .width3 { width: 49em; }
		.metro-layout .width4 { width: 66em; }
		.metro-layout .height2 { height: 6em; }
		.metro-layout .height3 { height: 28em; }
		.metro-layout .height4 { height: 38em; }
		
		.horizontal .contents .items { height: 105%; }
		.metro-layout .contents { height: 80%; overflow: hidden; }
		.metro-layout .contents .items { padding: 0 20px; position: relative; overflow: hidden; }

	</style>
	
	<script>
		var clientWidth;
		var clientHeight;
		function stylerevise(){
			// 6plus : 414 + 672
			// 6: 375 + 603
			clientWidth = document.body.clientWidth; 
			clientHeight = document.body.clientHeight;
			//alert(clientWidth + "+" + clientHeight);
			if(clientWidth < 414 & clientHeight < 672){
				document.getElementById("div_content").className = "contents clearfix";
				document.getElementById("IndustrySegmentCodes").className = "boxs isotope-item";
				document.getElementById("CustomerInformation").className = "boxs isotope-item";
				document.getElementById("PartnerInformation").className = "boxs isotope-item";
				document.getElementById("CompetitorInformation").className = "boxs isotope-item";
				document.getElementById("RPLScanning").className = "boxs isotope-item";
				document.getElementById("OtherPartySiteInstance").className = "boxs isotope-item";
				document.getElementById("OrganizationInformation").className = "boxs isotope-item";
				document.getElementById("PersonInformation").className = "boxs isotope-item";
				document.getElementById("data_lake_id").className = "data_lake_imgshort";

			}
		}

		function IndustrySegmentCodes(){
			swal({	title: "1.2k",   
					text: "Industry Segment Codes",   
					imageUrl: "../MetroStyleFiles/tasks.png" });
		}
		function CustomerInformation(){
			swal("1.85M", "Active Customers", "success");
		}
		function PartnerInformation(){
			swal("0.4M", "Active Partnerhip", "success");
		}
		function CompetitorInformation(){
			swal("1.4K", "Competitor", "success");
		}
		function RPLScanning(){
			swal("Every 5 seconds", "Restricted Party List Scanning", "success");
		}
		function OtherPartySiteInstance(){
			swal("350M", "Other Party Site Instance", "success");
		}
		function OrganizationInformation(){
			swal("200M", "Organization", "success");
		}
		function PersonInformation(){
			swal("380M", "Person", "success");
		}
		
		function CommentMe(){
			swal(
					{
						title: "Dear <%= wcu.getNickname()%> Comment US!",   
						text: "Drop us a message and do things better together",   
						type: "input",   
						showCancelButton: true,   
						closeOnConfirm: false,   
						animation: "slide-from-top",   
						inputPlaceholder: "Write something" 
					}, 
					function(inputValue){
						inputVar = inputValue;
						if (inputValue === false) return false;      
						if (inputValue === "") {     
							swal.showInputError("You need to write something!");     
							return false;   
						}
						else{
							<% RestUtils.callInsertCommentsFromVisitor(uid, inputVar);%>
						}
						swal("Thank You!", "We will contact you soon", "success"); 
					}
				);
		}
		
	</script>

	
</head> 
<body onload="stylerevise()">
	<a href="../DQMenu?UID=<%= uid%>"><img src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=0159000000E9rPC&oid=00D90000000pkXM"  class="HpLogo" alt="Logo" style="width:175px;height:90px;position:absolute;left:4%;top:2%; z-index:6;"/></a>
	<a><img id="data_lake_id" src="../MetroStyleFiles/lake-icon.png" onClick="Javascript:CommentMe();" class="data_lake_img"/></a>
	<div class="metro-layout vertical">
		<div class="header"></div>
		<div class="content clearfix" id="div_content">
			<div class="items isotope" style="width: 1360px; position: relative; ">
<!-- 				<a class="box width2 height2 isotope-item" onClick="Javascript:IndustrySegmentCodes();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(292px, 0px, 0px);">
					<span class="subtitle">Industry Segment Area</span>
					<span class="rightbuttom">1.2K</span>
					<img class="cover" src="../MetroStyleFiles/gallery.jpg" alt="">
				</a> -->
				<a id= "IndustrySegmentCodes" class="box isotope-item" onClick="Javascript:IndustrySegmentCodes();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(292px, 0px, 0px); background: rgb(66, 85, 59);">
					<span class="subtitle">Industry Segment Area</span>
					<span class="rightbuttom">1.2K</span>
					<img class="icon" src="../MetroStyleFiles/industry.png" alt="">
				</a>
				<a id = "CustomerInformation" class="box isotope-item" onClick="Javascript:CustomerInformation();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(20px, 0px, 0px);">
					<span class="subtitle">Customer</span>
					<span class="rightbuttom">1.85M</span>
					<img class="icon" src="../MetroStyleFiles/mail.png" alt="">
				</a>
				<a id="PartnerInformation" class="box isotope-item" onClick="Javascript:PartnerInformation();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(20px, 160px, 0px); background: rgb(107, 107, 107);">
					<span class="subtitle">Partner</span>
					<span class="rightbuttom">0.4M</span>
					<img class="icon" src="../MetroStyleFiles/settings.png" alt="">
				</a>
				<a id="CompetitorInformation" class="box isotope-item" onClick="Javascript:CompetitorInformation();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(20px, 320px, 0px); background: rgb(67, 181, 31);">
					<span class="subtitle">Competitor</span>
					<span class="rightbuttom">1.4K</span>
					<img class="icon" src="../MetroStyleFiles/phone.png" alt="">
				</a>

				<a id="RPLScanning" class="box isotope-item" onClick="Javascript:RPLScanning();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(20px, 480px, 0px); background: rgb(211, 44, 44);">
					<span class="subtitle">RPL</span>
					<span class="rightbuttom">20M</span>
					<img class="icon" src="../MetroStyleFiles/music.png" alt="">
				</a>
				<a id="OtherPartySiteInstance" class="box isotope-item" onClick="Javascript:OtherPartySiteInstance();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(292px, 320px, 0px); background: rgb(245, 141, 0);">
					<span class="subtitle">Other Party Site Instance</span>
					<span class="rightbuttom">350M</span>
					<img class="icon" src="../MetroStyleFiles/firefox.png" alt="">
				</a>
				<a id="OrganizationInformation" class="box isotope-item" onClick="Javascript:OrganizationInformation();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(292px, 480px, 0px); background: rgb(100, 15, 108);">
					<span class="subtitle">Organization</span>
					<span class="rightbuttom">200M</span>
					<img class="icon" src="../MetroStyleFiles/yahoo.png" alt="">
				</a>
				<a id="PersonInformation" class="box isotope-item" onClick="Javascript:PersonInformation();" style="position: absolute; left: 0px; top: 0px; transform: translate3d(292px, 480px, 0px); background: rgb(76, 94, 81);">
					<span class="subtitle">Person</span>
					<span class="rightbuttom">380M</span>
					<img class="icon" src="../MetroStyleFiles/person.png" alt="">
				</a>
			</div>
		</div>
	</div>
	<div id="footer"><span class="clientCopyRight"></span></div>

<script>
         jQuery.ajax({
     		type : "GET",
     		url : "../QueryClientMeta",
     		data : {},
     		cache : false,
     		success : function(data) {
     			var jsons = eval(data);
     			$(document).attr("title",jsons.clientStockCode+" - "+$(document).attr("title"));//修改title值  
     			$('img.HpLogo').attr('src',jsons.clientLogo);
     			$('span.clientCopyRight').text('©'+jsons.clientCopyRight);
     		}
     	});
</script>
</body>
</html>
