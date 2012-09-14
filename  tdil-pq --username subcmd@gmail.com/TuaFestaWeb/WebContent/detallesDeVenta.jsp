<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.forms.beans.PublicImageBlobBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.SellDetailsForm"%>
<%@ include file="includes/userLogged.jspf" %>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalProfileForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="profesionalContactCard"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R009-M1- Mi cuenta - Detalles de venta</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
<script src="js/lightbox.js"></script>
<style>
<!--
.myRow {
	padding-bottom:5px;
}
#muroContainer {
	background:#CCCCCC;
	
	width:270px;
	height:500px;
	
	float:right;
}
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
}
-->
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Detalles de la venta <bean:write name="SellDetailsForm" property="sellValueObject.name"/></h1>
		</div>
		<% SellDetailsForm sellDetailsForm = (SellDetailsForm)session.getAttribute("SellDetailsForm");  %>
		<% if (sellDetailsForm.hasMedia()) { %>
			<% for (PublicImageBlobBean publicImageBlobBean : sellDetailsForm.getMedia()) { %>
			<div class="fotoHelper" style="width:150px; height:150px; background-image:url(./downloadThumb.st?id=<%=publicImageBlobBean.getBlobid()%>&width=150&height=150&type=PUBLIC&ext=<%=publicImageBlobBean.getBlobExt()%>);">
				<a href="./downloadThumb.st?id=<%=publicImageBlobBean.getBlobid()%>&width=800&height=600&type=PUBLIC&ext=<%=publicImageBlobBean.getBlobExt()%>" rel="lightbox[gal]" title="<%=sellDetailsForm.getSellValueObject().getName()%>">
					<img src="images/null.gif" width="150" height="150" />
				</a>
			</div>
			<% } %>
		<% } %>
		<div id="formContent" class="height300"><a href="./viewProfesionalProfile.do?id=<bean:write name='SellDetailsForm' property='sellValueObject.idProfesional'/>">Ver perfil profesional</a></div>
		
		<div id="formContent" class="height300"><a href="./contactProfesional.do?id=<bean:write name='SellDetailsForm' property='sellValueObject.idProfesional'/>">Contactar profesional</a></div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>