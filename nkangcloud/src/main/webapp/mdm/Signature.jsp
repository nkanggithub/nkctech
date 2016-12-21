<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>HPE - Signature</title>
  <meta name="description" content="Signature Pad - HTML5 canvas based smooth signature drawing using variable width spline interpolation.">

  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">

<link rel="stylesheet" type="text/css" href="../MetroStyleFiles/sweetalert.css"/>
<script src="../Jsp/JS/modernizr.js"></script>
</head>
<body style="padding:0px;margin:0px;">
<a href="profile.jsp?UID=<%=session.getAttribute("UID")%>">
	<img src="../MetroStyleFiles//EXIT1.png" style="width: 30px; height: 30px;position:absolute;top:20px;left:20px;" />
</a>	
<img style="position:absolute;top:10px;right:20px;width:130px;height:auto" src="https://c.ap1.content.force.com/servlet/servlet.ImageServer?id=015900000053FQo&amp;oid=00D90000000pkXM&amp;lastMod=1438220916000" alt="HP Logo" class="HpLogo">
<div style="width:100%;height:4px;background:#56B39D;position:absolute;top:70px;"></div>
<div style="width:80%;position:absolute;top:103px;left:10%;font-size: 21px;padding:6px 0;color: #444444;border-bottom:1px solid #ddd;">电子签名</div>
<input id="uid" type="hidden" value="<%=session.getAttribute("UID")%>" />											
<div id="old" style="margin-top:150px;margin-bottom:-90px;padding-top:5px;height:170px;border:2px #56B39D solid;width:80%;margin-left:auto;margin-right:auto;text-align:center;"></div>
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
	        success: function(data){
	        	if(data!=""&&data!="null"){
	       		 	$('#old').html(data.substring(1,data.length-1));
	        	}else{
	        		$('#old').html("你还未保存个性签名！");
	        	}
	        }
	  	});
	}
$(document).ready(function() {
	getOld();
	// This is the part where jSignature is initialized.
	var $sigdiv = $("#signature").jSignature({'UndoButton':true})
	// All the code below is just code driving the demo. 
	var $tools = $('#tools')

	$('<input type="button" name="svg" value="保存签名"  style="float:right;margin-right:10%;color:#fff;background-color:#56B39D;border:none;padding:10px;font-size:18px;margin-top:0px;">').bind('click', function(e){
		if (e.target.value !== ''){
			//var data = $sigdiv.jSignature('getData', e.target.value)
			var data = $sigdiv.jSignature('getData', 'svg')
			if($.isArray(data) && data.length === 2){
				var start=data[1].indexOf("<svg ");
				var svg=data[1].substring(start,data[1].length);
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
