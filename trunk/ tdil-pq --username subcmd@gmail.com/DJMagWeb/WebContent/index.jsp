<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.model.Section"%>
<jsp:useBean id="publicHomeBean" scope="session" class="com.tdil.djmag.web.beans.PublicHomeBean"/>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css">
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

<% if (publicHomeBean.hasCountrySelected()) { %>
	Pais: <%= publicHomeBean.getCountry().getName() %>
	<br>
	Secciones<br>
	<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
		<%= section.getName() %><br>
	<% 	} %>
<% } else { %>
	<div id="BlockCoutrySelection">
		<div id="labelCountrySelection">Seleccione su pa&iacute;s / Selecione o Pa&iacute;s<br>Choisissez un pays / Select Country</div>
		<div id="countryCombo"><select><option>Seleccione</option>
			<% 	for (Country country : publicHomeBean.getAllCountries()) {%>
					<option value="./selectCountry.st?id=<%= country.getId() %>"><%= country.getName() %></option>
			<% } %>
			</select>&nbsp;&nbsp;&nbsp;
			<input type="submit" value="ok" class="okCircle">
			<% for (Country country : publicHomeBean.getAllCountries()) {%>
				<a href="./selectCountry.st?id=<%= country.getId() %>"><%= country.getName() %></a><br>
			<% } %>
		</div>
	</div>
<% } %>

<% if (publicHomeBean.hasCountrySelected()) { %>

<%@ include file="includes/homeRanking.jsp" %>

<%@ include file="includes/homeNewsletter.jsp" %>

<%@ include file="includes/homeFrontCovers.jsp" %>

<%@ include file="includes/homeAgenda.jsp" %>

<% } %>
</body>
</html>