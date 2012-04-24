<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){
	
	function generateTooltips() {
	  //make sure tool tip is enabled for any new error label
		$("img[id*='error']").tooltip({
			showURL: false,
			opacity: 0.99,
			fade: 150,
			positionRight: true,
				bodyHandler: function() {
					return $("#"+this.id).attr("hovertext");
				}
		});
		//make sure tool tip is enabled for any new valid label
		$("img[src*='tick.gif']").tooltip({
			showURL: false,
				bodyHandler: function() {
					return "OK";
				}
		});
	}
	
	$("#newsletterForm").mouseover(function(){
		      generateTooltips();
		    });
	
	$("#newsletterForm").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("td").next("td") );
			},
			rules: { email: {required: true}
			},
			messages: { email: {required: "<img id='codigoerror' src='img/unchecked.gif' hovertext='Ingrese el email.' />"}
			},
			submitHandler: function() {
	            $('#newsletterForm').ajaxSubmit({
	    			type: "POST",
	    			url: "./subscribeToNewsletter.do",
	    			dataType: "json",
	    			success: postSubscrition
	    			});
	        }
		});
		
		function postSubscrition(jsonData) {
			alert(jsonData.result);
			//alert("Gracias por subscribirte");
		}
	
	}
);
</script>
</head>
<body>
<form action="newsletter" name="newsletterForm" id="newsletterForm" method="POST">
	<table>
		<tr>
			<td><input type="text" name="email" id="email"></td>
			<td width="25" id="codigoerr"></td>
		</tr>
		<tr>
		<td><input type="submit" class="codeButton" value="Subscribirme" border="0"></td>
		</tr>
	</table>
</form>

</body>
</html>			