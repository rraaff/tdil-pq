<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.struts.forms.SellSearchResultForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="sellSearchResult"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Resultados de la busqueda</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<style>
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
}
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<div id="titleArea">
			<h1>Resultado de la b&uacute;squeda</h1>
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
						<display:column title="Producto/Servicio" sortable="true" sortName="name" headerClass="sortable width400" property="name"></display:column>
						<display:column title="Vendedor" sortable="true" sortName="Nombre" headerClass="sortable width150" property="profesionalbusinessname"></display:column>
						<display:column title="Precio" sortable="true" sortName="precio" headerClass="sortable width50" property="referenceprice"></display:column>
						<display:column title="Ubicacion" sortable="true" sortName="Ubicacion" headerClass="sortable width250" property="geoLevelPath"></display:column>
						<display:column title="acciones" headerClass="sortable width80"><a href="./viewSellDetails.do?type=<%= ((SellValueObject)pageContext.getAttribute("sells")).getType()%>&id=<%= ((SellValueObject)pageContext.getAttribute("sells")).getId()%><%=DisplayTagParamHelper.getParams(request)%>">Ver detalles</a></display:column>
					</display:table>
					<%=DisplayTagParamHelper.getFields(request)%>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>