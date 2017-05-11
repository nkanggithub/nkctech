<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head lang="en">
<script src="nkang/js_athena/jquery-1.8.3.min.js"></script>
    <title>MDM Data Quality</title>
 	<script>
 	$(function() { 
	 	$("#btn_showmore").click(function() { 
	  		$.ajax({
	 			type : "POST",
	 			data : {"aa":"bb"},
	 			dataType : "string",
	 			url : "getInformationJS",
	 			success : function(data) {
	 				console.log("aaaaaa");
	 				$("#div_show").html(data);
	 			}
	 			
	 		}); 

	 	})
 	})
 	</script>
</head>
<body>
	hello ${totalRecordCount}
	
	<input id="btn_showmore" type="button" value="Click Mes" >
	
	<div id="div_show"></div>
	<input id="input_text" type="text" />
	
</body>
</html>