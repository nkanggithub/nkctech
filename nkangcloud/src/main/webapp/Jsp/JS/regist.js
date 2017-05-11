var $j = jQuery.noConflict();
$j(function() {
	$j(".errorClass").each(function() {
		showError($j(this));
	});
	
	
	$j(".inputClass").focus(function() {
		var labelId = $j(this).attr("id") + "Error";
		$j("#" + labelId).text("");
		showError($j("#" + labelId));
	});
	
	
	$j(".inputClass").blur(function() {
		var id = $j(this).attr("id");
		var funName = "validate" + id.substring(0,1).toUpperCase() + id.substring(1) + "()";
		eval(funName);
	});
	
	regist();
	/*$j("#registForm").submit(function() {
		var bool = true;
//		if(!validateWechatname()) {
//			bool = false;
//		}
		if(!validateTelephone()) {
			bool = false;
		}
		if(!validateEmail()) {
			bool = false;
		}
		
		
		return bool;*/
	//});
});


function validateTelephone() {
	var id = "telephone";
	var value = $j("#" + id).val();
	
	if(!value) {
		$j("#" + id + "Error").text("Telephone can't be empty!");
		showError($j("#" + id + "Error"));
		return false;
	}
	
	if(!(/^1[34578]\d{9}$/.test(value))){  
		$j("#" + id + "Error").text("Telephone number format is wrong!");
		showError($j("#" + id + "Error"));
		return false;
	}
	
	$j.ajax({
		url:"../ajaxValidateTelephone",
		data:{telephone:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result) {
			if(!result) {
				$j("#" + id + "Error").text("Telephone number was existed!");
				showError($j("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;		
}


function validateEmail() {
	var id = "email";
	var value = $j("#" + id).val();
	if(!value) {
		$j("#" + id + "Error").text("Email can't be empty!");
		showError($j("#" + id + "Error"));
		return false;
	}
	
	if(!(/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(value))) {
		
		$j("#" + id + "Error").text("Email format is wrong!");
		showError($j("#" + id + "Error"));
		return false;
	}
	
	$j.ajax({
		url:"../ajaxValidateEmail",
		data:{email:value},
		type:"POST",
		dataType:"json",
		async:false,
		cache:false,
		success:function(result) {
			if(!result) {
				$j("#" + id + "Error").text("Email was existed!");
				showError($j("#" + id + "Error"));
				return false;
			}
		}
	});
	return true;		
}

function regist(){
	$j("#registBtn").click(function(){
		$j.ajax({
			url:"../register",
			data:{uid:$j("#uid").val(),telephone:$j("#telephone").val(),email:$j("#eamil").val(),
				suppovisor:$j("#suppovisor").val(),role:$j("#role").val(),group:$j("#group").val(),
				registerDate:$j("#registerDate").val(),selfIntro:$j("#selfIntro").val()},
			type:"POST",
			dataType:"json",
			cache:false,
			success:function(result) {
				if(result) {
					alert("user regist successfully!");
				} else {
					alert("user regist fail!");
				}
			}
		});
	});
}

function showError(ele) {
	var text = ele.text();
	if(!text) {
		ele.css("display", "none");
	} else {
		ele.css("display", "");
	}
}

