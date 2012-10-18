<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject"%>
<%@page import="com.tdil.tuafesta.struts.forms.WallModerationForm"%>
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="editProfesionalPersonalData"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<% WallModerationForm wallModerationForm = (WallModerationForm)session.getAttribute("WallModerationForm"); %>
		<div id="titleArea">
			<h1>Moderar mi muro</h1>
			
			<h2></h2>
		</div>
		<% if (wallModerationForm.isShowAll()) { %>
			<b></>Todos</b> - <a href="./viewWallWrittingPending.do">Solo pendientes</a>
		<% } else { %>
			<a href="./viewWallWrittingAll.do">Todos</a> - <b>Solo pendientes</b>
		<% } %>
		<div id="formContent">
			<% for (WallWrittingValueObject wwvo : wallModerationForm.getWallWritting()) { %>
					<div>
						<%=wwvo.getOriginaltext() %> (<%=wwvo.getIdAuthor() == null ? wallModerationForm.getProfesional().getBusinessname() : wwvo.getAuthorName()%>)
						<% if (wwvo.isAuthorProfesional()) { %>
							<a href="./">Editar</a>
							<a href="./deleteWallWritting.do?id=<%=wwvo.getId() %>">Borrar</a>
						<% } else { %>
							<% if (wwvo.getResponsePending().equals(1)) { %>
								<a href="./">Responder</a>
								<a href="./markAsRespondedWallWritting.do?id=<%=wwvo.getId() %>">Marcar como respondido</a>
							<% } %>
							<a href="./deleteWallWritting.do?id=<%=wwvo.getId() %>">Borrar</a>
						<% } %>
					</div>
				<% } %>
		</div>
		<!-- aca Termina el formulario -->
		<a href="./goToProfesionalHome.do?id=<%=wallModerationForm.getId()%>">Volver</a>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>