<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.struts.forms.SellSearchResultForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Profesional Normal (paso 2)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="includes/designHeader.jsp" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<div id="titleArea">
			<h1>Resultado de la busqueda</h1>
		</div>
		<div id="formContent" class="height300">
			<%
				SellSearchResultForm profesionalForm = (SellSearchResultForm)session.getAttribute("SellSearchResultForm");
			%>
			<%
					java.util.List servicesSource = profesionalForm.getSearchResult();
					com.tdil.struts.pagination.PaginatedListImpl paginatedServices = new com.tdil.struts.pagination.PaginatedListImpl(servicesSource, request, 10);
					request.setAttribute( "sells",  paginatedServices);
					%>
					<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./sellSearchResult.jsp">
						<display:column title="Producto/Servicio" sortable="true" sortName="name" headerClass="sortable width150" property="name"></display:column>
						<display:column title="Nombre" sortable="true" sortName="Nombre" headerClass="sortable width150" property="profesionalbusinessname"></display:column>
						<display:column title="Precio Ref." sortable="true" sortName="precio" headerClass="sortable width150" property="referenceprice"></display:column>
						<display:column title="acciones" headerClass="sortable width150">
							<a href="./viewProfesionalProfile.do?id=<%= ((SellValueObject)pageContext.getAttribute("sells")).getIdProfesional()%><%=DisplayTagParamHelper.getParams(request)%>">Ver perfil</a>
						</display:column>
					</display:table>
					<%=DisplayTagParamHelper.getFields(request)%>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>