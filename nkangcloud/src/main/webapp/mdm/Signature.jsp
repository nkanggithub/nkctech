<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.nkang.kxmoment.util.MongoDBBasic"%>
<%
MongoDBBasic.addSkimNum();
%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Signature</title>
  <meta name="description" content="Signature Pad - HTML5 canvas based smooth signature drawing using variable width spline interpolation.">

  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" type="text/css" href="../MetroStyleFiles/sweetalert.css"/>
<link rel="stylesheet" type="text/css" href="../nkang/css_athena/profile.css"/>
<script src="../Jsp/JS/modernizr.js"></script>
<style>
.icon {
    display: inline-block;
    width: 30px; height: 30px;
    overflow: hidden;
    -overflow: hidden;
}
.icon > img.exit {
    position: relative;
    left: -30px;
    border-right: 30px solid transparent;
    -webkit-filter: drop-shadow(30px 0 #fff);
    filter: drop-shadow(20px 0);   
}
canvas.jSignature {
    border-right: 0px !important;
    border-left: 0px !important;
}
</style>
</head>
<body style="padding:0px;margin:0px;">
<a href="profile.jsp?UID=<%=session.getAttribute("UID")%>">
	<i class="icon" style="position:absolute;top:20px;left:20px;"><img class="exit"  src="../MetroStyleFiles//EXIT1.png" style="width: 30px; height: 30px;" /></i>
</a>	
<img style="position:absolute;top:8px;right:20px;" src="" alt="Logo" class="HpLogo">
<div style="width:100%;height:4px;position:absolute;top:70px;" class="clientTheme"></div>
<div style="width:80%;position:absolute;top:103px;left:10%;font-size: 21px;padding:6px 0;color: #444444;border-bottom:1px solid #ddd;">电子签名</div>
<input id="uid" type="hidden" value="<%=session.getAttribute("UID")%>" />											
<div id="old" style="vertical-align:middle;margin-top:150px;margin-bottom:-98px;padding-top:5px;height:170px;border:2px #fff solid;width:80%;margin-left:auto;margin-right:auto;text-align:center;"></div>
<div id="content">		
	<div id="signatureparent">
		<div id="signature"></div></div>
	<div id="tools"></div>
	
</div>

<script src="../Jsp/JS/jquery-1.8.0.js"></script>
<script	src="../MetroStyleFiles/sweetalert.min.js"></script>
<script type="text/javascript">
	jQuery.noConflict()
</script>
<script>
var HpLogoSrc="",copyRight="",clientThemeColor="";
(function($) {
	var topics = {};
	$.publish = function(topic, args) {
	    if (topics[topic]) {
	        var currentTopic = topics[topic],
	        args = args || {};
	
	        for (var i = 0, j = currentTopic.length; i < j; i++) {
	            currentTopic[i].call($, args);
	        }
	    }
	};
	$.subscribe = function(topic, callback) {
	    if (!topics[topic]) {
	        topics[topic] = [];
	    }
	    topics[topic].push(callback);
	    return {
	        "topic": topic,
	        "callback": callback
	    };
	};
	$.unsubscribe = function(handle) {
	    var topic = handle.topic;
	    if (topics[topic]) {
	        var currentTopic = topics[topic];
	
	        for (var i = 0, j = currentTopic.length; i < j; i++) {
	            if (currentTopic[i] === handle.callback) {
	                currentTopic.splice(i, 1);
	            }
	        }
	    }
	};
})(jQuery);

</script>
<script src="../Jsp/JS/jSignature.min.noconflict.js"></script>
<script>
(function($){
	var url="../CallGetUserWithSignature?openid="+$('#uid').val();
	function getOld(){
	  	$.ajax({  
	        cache : true,  
	        url:url,
	        type: 'GET', 
	        timeout: 2000, 
	        success: function(data,textStatus){
	        	if(textStatus=='success'){
		        	if(data!=null&&data!=""&&data!="null"){
		       		 	$('#old').html(data.substring(1,data.length-1));
		        	}else{
		        		$('#old').html("你还未保存个性签名！");
		        	}
	        	}else{
	        		$('#old').html("服务器繁忙！");
	        	}
	        }
	  	});
	}
	function getLogo(){
		jQuery.ajax({
			type : "GET",
			url : "../QueryClientMeta",
			data : {},
			cache : false,
			success : function(data) {
				var jsons = eval(data);
				clientThemeColor=jsons.clientThemeColor;
				$(document).attr("title",jsons.clientStockCode+" - "+$(document).attr("title"));//修改title值  
				$('img.HpLogo').attr('src',jsons.clientLogo);
				$('span.clientCopyRight').text('©'+jsons.clientCopyRight);
				$('.clientTheme').css('background-color',jsons.clientThemeColor);
				$('#old').css('border-color',jsons.clientThemeColor);
				$('#save').css('background-color',jsons.clientThemeColor);
				$('#rest').css('background-color',jsons.clientThemeColor);
				$('canvas').css('border-color',jsons.clientThemeColor);
				$('.icon > img.exit ').css('-webkit-filter','drop-shadow(30px 0 '+jsons.clientThemeColor+')');
			}
		});
	}
	getLogo();
$(document).ready(function() {
	getOld();
	// This is the part where jSignature is initialized.
	var $sigdiv = $("#signature").jSignature({'UndoButton':true})
	// All the code below is just code driving the demo. 
	var $tools = $('#tools')

	$('<input type="button" id="save" name="svg" value="保存签名"  style="float:right;margin-right:10%;color:#fff;background-color:'+clientThemeColor+';border:none;padding:10px;font-size:18px;margin-top:0px;">').bind('click', function(e){
		if (e.target.value !== ''){
			//var data = $sigdiv.jSignature('getData', e.target.value)
			var data = $sigdiv.jSignature('getData', 'svg')
			if($.isArray(data) && data.length === 2){
				var start=data[1].indexOf("<svg ");
				var svg=data[1].substring(start,data[1].length);
				if(svg!='<svg xmlns="http://www.w3.org/2000/svg" version="1.1" width="0" height="0"></svg>')
				jQuery.ajax({
					type : "GET",
					url : "../userProfile/setSignature",
					data : {
						svg : svg
					},
					cache : false,
					success : function(data) {
						if(data.indexOf("true")==0){
							swal("签名保存成功!", "", "success"); 
							getOld();
							/*var canvas = document.getElementsByClassName('jSignature')[0];
						  	var context = canvas.getContext('2d');
						  	context.clearRect(0, 0, canvas.width, canvas.height); */
						  	var $sigdiv = $("#signature")
						  	$sigdiv.jSignature("reset"); //重置画布，可以进行重新作画.
						}else{
							swal("签名保存失败!", "", "error"); 
						}
					}
				});
			}
		}
	}).appendTo($tools)
	
	if (Modernizr.touch){
		$('#scrollgrabber').height($('#content').height())		
	}
	
})

})(jQuery)
</script>

</body>


</html>
