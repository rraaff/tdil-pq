<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.utils.LocalizationUtils"%>
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
<title>Tua Festa | Detalles de venta</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />
<script src="js/lightbox.js"></script>
<style>
<!--
#muroContainer {
	background:#CCCCCC;
	
	width:270px;
	height:500px;
	
	float:right;
}
#formContent h3 {
	margin-left:0px;
	padding-bottom:20px;
	padding-left:0px;
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
			<h1>Detalles de la venta</h1>
			<h2>Estos son los detalles del producto o servicio seleccionado</h2>
		</div>
		<div id="formContent">
			<% SellDetailsForm sellDetailsForm = (SellDetailsForm)session.getAttribute("SellDetailsForm");  %>
			<div id="formSection" style="width:920px;">
				<h2><bean:write name="SellDetailsForm" property="sellValueObject.name"/></h2>
				<h3><bean:write name="SellDetailsForm" property="sellValueObject.categoryText"/></h3>
				<div class="myRow">
					<% if (sellDetailsForm.hasMedia()) { %>
						<% for (PublicImageBlobBean publicImageBlobBean : sellDetailsForm.getMedia()) { %>
						<div class="fotoHelper" style="width:150px; height:150px; float:left; margin:10px; background-image:url(./downloadThumb.st?id=<%=publicImageBlobBean.getBlobid()%>&width=150&height=150&type=PUBLIC&ext=<%=publicImageBlobBean.getBlobExt()%>);"><a href="./downloadThumb.st?id=<%=publicImageBlobBean.getBlobid()%>&width=800&height=600&type=PUBLIC&ext=<%=publicImageBlobBean.getBlobExt()%>" rel="lightbox[gal]" title="<%=sellDetailsForm.getSellValueObject().getName()%>"><img src="images/null.gif" width="150" height="150" /></a></div>
						<% } %>
					<% } %>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Descripci&oacute;n</div>
					<div class="myLabel width800"><bean:write name="SellDetailsForm" property="sellValueObject.description"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width100">Precio unitario</div>
					<div class="myLabel width800"><strong><%=LocalizationUtils.formatPrice(sellDetailsForm.getSellValueObject().getReferenceprice())%></strong></div>
				</div>
				<% if (sellDetailsForm.getSellValueObject().isProduct()) { %>
					<div class="myRow">
						<div class="myLabel width100">Ubicaci&oacute;n</div>
						<div class="myLabel width800"><strong><bean:write name="SellDetailsForm" property="sellValueObject.geoLevelPath"/></strong></div>
					</div>
				<% } else { %>
					Área de Cobertura
				<% } %>
				<div class="myRow" align="center"><a class="inputButtonHelper" style="color:#000000; text-decoration:none;" href="./viewProfesionalProfile.do?id=<bean:write name='SellDetailsForm' property='sellValueObject.idProfesional'/>">Ver perfil profesional</a><a class="inputButtonHelper" style="color:#000000; text-decoration:none;" href="./contactProfesional.do?id=<bean:write name='SellDetailsForm' property='sellValueObject.idProfesional'/>">Contactar profesional</a></div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>