<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="homeCliente"%>
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
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<% ProfesionalHomeForm profesionalHomeForm = (ProfesionalHomeForm)session.getAttribute("ProfesionalHomeForm"); 
	Profesional profesional = profesionalHomeForm.getProfesional();
%>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Home del profesional <%=profesionalHomeForm.getProfesional().getFirstname() %></h1>
			<h2></h2>
		</div>
		<div style="border: 1px Solid Black;">
			<legend>Datos personales <%= profesionalHomeForm.isPersonalDataChanged() ? "*" : "" %></legend>
			<%= profesional.getFirstname() %> <%= profesional.getLastname() %><br>
			Telefono: <%= profesional.getPhonetype() %> <%= profesional.getPhoneareacode() %> <%= profesional.getPhonenumber() %> <%= profesional.getPhoneextension() == null ? "" : profesional.getPhoneextension() %> <br>
			Sexo: <%= profesional.getSex().equals("m") ? "Masculino" : "Femenino" %>
			<a href="./goToEditProfesionalPersonalData.do?id=<%=profesional.getId()%>">Editar</a>
		</div><br>
		
		<div style="border: 1px Solid Black;">
			<legend>Datos profesionales <%= profesionalHomeForm.isBusinessDataChanged() ? "*" : "" %></legend>
			<%= profesional.getBusinessname() %><br>
			CUIT: <%= profesional.getCuit() %><br>
			IIBB: <%= profesional.getIibb() %><br>
			Localizado en: <%= profesionalHomeForm.getLocation().getNombre4()%>, <%= profesionalHomeForm.getLocation().getNombre3()%>, <%= profesionalHomeForm.getLocation().getNombre2()%>
			<a href="./goToEditProfesionalBusinessData.do?id=<%=profesional.getId()%>">Editar</a>
		</div><br>
		
		<div style="border: 1px Solid Black;">
			<legend>Productos / Servicios</legend>
			<a href="./goToEditProfesionalProducts.do?id=<%=profesional.getId()%>">Editar</a>
			<a href="./goToEditProfesionalServices.do?id=<%=profesional.getId()%>">Editar</a>
		</div><br>
		<div class="myRow">
			<%
			java.util.List source = profesionalHomeForm.getSells();
			com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
			request.setAttribute( "sells",  paginated);
			%>
			<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./homeProfesional.jsp">
				<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width250" property="name"></display:column>
				<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width250" property="sellTypeDescription"></display:column>
				<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width250" property="categoryText"></display:column>
				<display:column title="Precio Unitario" sortable="true" sortName="precio" headerClass="sortable width100" property="referenceprice"></display:column>
				<display:column title="Estado" sortable="true" sortName="Estado" headerClass="sortable width100">
					<%= ((SellValueObject)pageContext.getAttribute("sells")).getStatusText()%>
				</display:column>
			</display:table>
			<%=DisplayTagParamHelper.getFields(request)%>
		</div>
		
		<div style="border: 1px Solid Black;">
			<legend>Areas de cobertura </legend>
			<a href="./goToEditProfesionalServiceArea.do?id=<%=profesional.getId()%>">Editar</a>
		</div><br>
		
		<div style="border: 1px Solid Black;">
			<legend>Muro</legend>
		</div><br>
		
		<div style="border: 1px Solid Black;">
			<legend>Agenda</legend>
			<a href="./goToEditProfesionalAgenda.do?id=<%=profesional.getId()%>">Editar</a>
		</div><br>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>