<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="com.tdil.tuafesta.utils.LocalizationUtils"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.GeoLevelNavigationForm"%>
<%@page import="com.tdil.tuafesta.utils.CategoryUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.CategoryValueObject"%>
<%@page import="com.tdil.tuafesta.utils.GeoLevelUtils"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="listadoZonas"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Inicio | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style>
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
}
</style>
<script>
  $(function() {
    $("a[id^='tool-']").each(function(indice,valor) {
		   $(valor).tooltip();
		});
  });
  </script>
</head>
<% 
	GeoLevelNavigationForm form = (GeoLevelNavigationForm)session.getAttribute("GeoLevelNavigationForm");
%>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<% if (form.getGeo2() == null) { %>
				<h1>Todas las Zonas</h1>
			<% } else { %>
				<a href="./navigateGeoAll.do">Todas</a> 
				>> <a href="./navigateGeo.do?id=<%=form.getGeo2().getId() %>&level=2"><%=form.getGeo2().getNombre() %></a>
				<% if (form.getGeo3() != null) { %>
					>> <a href="./navigateGeo.do?id=<%=form.getGeo3().getId() %>&level=3"><%=form.getGeo3().getNombre() %></a>
				<% 	} %>
				<% if (form.getGeo4() != null) { %>
					>> <a href="./navigateGeo.do?id=<%=form.getGeo4().getId() %>&level=4"><%=form.getGeo4().getNombre() %></a>
				<% 	} %>
			<% } %>
		</div>
		<div id="formContent">
			<div id="zoneNavigation">
				<p>
					<% if (!form.getGeo2List().isEmpty()) { %>
						<% for (Geo2 g2 : form.getGeo2List()) { %>
							<a href="./navigateGeo.do?id=<%=g2.getId() %>&level=2"><%=g2.getNombre() %></a>
						<% } %>
					<% } %>
					<% if (!form.getGeo3List().isEmpty()) { %>
						<% for (Geo3 g3 : form.getGeo3List()) { %>
							<a href="./navigateGeo.do?id=<%=g3.getId() %>&level=3"><%=g3.getNombre() %></a>
						<% } %>
					<% } %>
					<% if (!form.getGeo4List().isEmpty()) { %>
						<% for (Geo4 g4 : form.getGeo4List()) { %>
							<a href="./navigateGeo.do?id=<%=g4.getId() %>&level=4"><%=g4.getNombre() %></a>
						<% } %>
					<% } %>
				</p>
			</div>
		</div>
	</div>
</div>
<%
	java.util.List servicesSource = form.getSells();
	com.tdil.struts.pagination.PaginatedListImpl paginatedServices = new com.tdil.struts.pagination.PaginatedListImpl(servicesSource, request, 10);
	request.setAttribute( "sells",  paginatedServices);
	%>
	<% if (!servicesSource.isEmpty()) { %>
	<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./listadoZonas.jsp">
		<display:column title="Producto/Servicio" sortable="true" sortName="name" headerClass="sortable width400" property="name"></display:column>
		<display:column title="Vendedor" sortable="true" sortName="Nombre" headerClass="sortable width150" property="profesionalbusinessname"></display:column>
		<display:column title="Precio" sortable="true" sortName="precio" headerClass="sortable width50">
			<%= LocalizationUtils.formatPrice(((SellValueObject)pageContext.getAttribute("sells")).getReferenceprice())%>
		</display:column>
		<display:column title="Ubicacion" sortable="true" sortName="Ubicacion" headerClass="sortable width250">
			<a id="tool-1" href="" title="<%= ((SellValueObject)pageContext.getAttribute("sells")).getGeoLevelPath()%>">
				<%= com.tdil.utils.StringUtils.getStringOfLen(((SellValueObject)pageContext.getAttribute("sells")).getGeoLevelPath(), 60, "...")%></a>
		</display:column>
		<display:column title="acciones" headerClass="sortable width80"><a href="./viewSellDetails.do?type=<%= ((SellValueObject)pageContext.getAttribute("sells")).getType()%>&id=<%= ((SellValueObject)pageContext.getAttribute("sells")).getId()%><%=DisplayTagParamHelper.getParams(request)%>">Ver detalles</a></display:column>
	</display:table>
	<%=DisplayTagParamHelper.getFields(request)%>
	<% } %>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>