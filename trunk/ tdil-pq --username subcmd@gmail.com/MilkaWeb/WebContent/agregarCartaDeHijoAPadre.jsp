<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<%@ include file="includes/head.jsp" %>
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	}
	
);
</script>
<%@ include file="includes/boErrorJS.jsp" %>

<style>
<!--
body {
	
}
#formContent {
	background-image: url(images/experiencias/cartasDePaH/upload_bg.jpg);
	background-repeat: no-repeat;
	background-position: center top;
	width:900px;
	height:400px;
	margin:0 auto;
}
#Nombre {
	height: 25px;
	width: 170px;
	left: 368px;
	top: 139px;
	position: relative;
}
#E-Mail {
	height: 25px;
	width: 170px;
	left: 598px;
	top: 114px;
	position: relative;
}
#Politicas {
	height: 25px;
	width: 160px;
	left: 309px;
	top: 141px;
	position: relative;
}
#SubirImagen {
	height: 25px;
	width: 260px;
	left: 408px;
	top: 158px;
	position: relative;
}
.normalField {
	width:150px;
	height:25px;
	line-height:22px;
	border: solid 1px #333333;
}
#buttonHolder {
	height: 130px;
	width: 130px;
	left: 742px;
	top: 128px;
	position: relative;
}
#buttonHolder .okCircle, .okCircle a {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 1px;
	line-height:14px;
	color: #FFFFFF;
	background:transparent;
	text-decoration: none;
	background-image: url(images/experiencias/cartasDePaH/upload.png);
	background-repeat: no-repeat;
	background-position: center center;
	width:130px;
	height:130px;
	border:none;
	cursor:hand;
	margin:0;
	padding:0;
}
-->
</style>
</head>
<body>
<div id="formContent">
<html:form method="POST" action="/uploadMailToParent" enctype="multipart/form-data">
	<div id="Nombre"><html:text name="MailToParentForm" property="authorBean.name" styleClass="normalField"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.name.err")%></div>
	<div id="E-Mail"><html:text name="MailToParentForm" property="authorBean.email" styleClass="normalField"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.email.err")%></div>
	<div id="Politicas"><%=MilkaErrorFormatter.getErrorFrom(request, "Author.politics.err")%><html:checkbox name="MailToParentForm" property="authorBean.acceptPolitics"/></div>
	<div id="SubirImagen"><html:file name="MailToParentForm" property="photoFormFile" /><%=MilkaErrorFormatter.getErrorFrom(request, "MailToParentForm.photo.err")%></div>
	<div id="buttonHolder"><html:submit property="operation" styleClass="okCircle"></html:submit></div>
</html:form>
</div>
</body>
</html>