<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="com.tdil.tuafesta.model.ProfesionalChange"%>
<%@page import="com.tdil.tuafesta.struts.forms.ReviewProfesionalForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page info="verifyProfesional"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<style>
th.sorted a,th.sortable a {
	background-position: right;
	display: block;
	width: 100%;
}

th.sortable a {
	background-image: url(img/displaytag/arrow_off.png);
}

th.order1 a {
	background-image: url(img/displaytag/arrow_down.png);
}

th.order2 a {
	background-image: url(img/displaytag/arrow_up.png);
}

tr.odd {
	background-color: #fff
}

tr.tableRowEven,tr.even {
	background-color: #fea
}
th.sorted {
	background-color: orange;
}
</style>
</head>

<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral" class="height450">
		<h1>Revisar datos de las ventas del profesional</h1>
		<% ReviewProfesionalForm reviewProfesionalForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");
			Profesional profesional = reviewProfesionalForm.getProfesional(); 
			ProfesionalChange change = reviewProfesionalForm.getProfesionalChange(); %>
		<div class="width100per" style="float:left;">
			<h2><legend>Productos / Servicios</legend></h2>
			<div class="myRow">
				<%
				java.util.List source = reviewProfesionalForm.getSells();
				com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
				request.setAttribute( "sells",  paginated);
				%>
				<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./reviewSellData.jsp">
					<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width350" property="name"></display:column>
					<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width50" property="sellTypeDescription"></display:column>
					<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width350" property="categoryText"></display:column>
					<display:column title="Precio" sortable="true" sortName="precio" headerClass="sortable width50" property="referenceprice"></display:column>
					<display:column title="Estado" sortable="true" sortName="Estado" headerClass="sortable width50">
						<a href="./reviewSell.do?type=<%= ((SellValueObject)pageContext.getAttribute("sells")).getType()%>&id=<%= ((SellValueObject)pageContext.getAttribute("sells")).getId()%><%=DisplayTagParamHelper.getParams(request)%>">
						<%= ((SellValueObject)pageContext.getAttribute("sells")).getStatusText()%>
						</a>
					</display:column>
				</display:table>
				<%=DisplayTagParamHelper.getFields(request)%>
			</div>
		</div>
	</div>
	
	
</div>
</body>
</html>