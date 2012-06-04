<%@page import="com.tdil.milka.struts.forms.LinkAnchorForm"%>
<%@page import="com.tdil.milka.web.ExperienceUtils"%>
<%@page import="com.tdil.milka.model.valueobjects.ExperienceValueObject"%>
<%@page import="com.tdil.milka.struts.forms.LinkTargetSelectionForm"%>
<%@page import="com.tdil.milka.struts.forms.UrlUtils"%>
<%@page import="com.tdil.milka.model.valueobjects.PostItValueObject"%>
<%@page import="com.tdil.milka.struts.forms.PostItAdministrationForm"%>
<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
<%@ page info="index"%>
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

<%
LinkTargetSelectionForm linkTargetSelectionForm = (LinkTargetSelectionForm)session.getAttribute("LinkTargetSelectionForm");
java.util.List source = linkTargetSelectionForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<html:form method="POST" action="/searchExperiences">
		<h1 align="center">Seleccion de link</h1>
		<div class="renglon width860" style="margin-bottom:20px;">
			<div class="label width860"><span class="comment">Desde esta sección podrá seleccionar el destino del link.</span></div>
		</div>
		<div class="label width80">Experiencia: </div>
			<html:select name="LinkTargetSelectionForm" property="type" >
				<% for (String iterTarget : ExperienceUtils.getAllExperiences()) { %>
					<option	<%=	(iterTarget).equals( linkTargetSelectionForm.getType()) ? "selected" : ""%>
						value="<%=iterTarget%>">
						&nbsp;&nbsp;&nbsp;<%=iterTarget%></option>
				<% } %>
			</html:select><br><br>
		<div class="label width80">Descripcion: </div><html:text name="LinkTargetSelectionForm" property="description" style="width: 300px;"/><br><br>
		<html:submit property="operation">
			<bean:message key="search" />
		</html:submit>
	</html:form>
	<div class="renglon width860">
		<display:table name="test" sort="external" pagesize="10" id="testit">
			<display:column title="id" sortable="true" sortName="fecha" headerClass="sortable" property="id"></display:column>
			<display:column title="description" sortable="true" sortName="name" headerClass="sortable" property="description"></display:column>
			<display:column title="links" sortable="false" property="linkCount"></display:column>
			<display:column title="acciones">
				<% if (linkTargetSelectionForm.canBeSelected(((ExperienceValueObject)pageContext.getAttribute("testit")).getId())) { %> 
					<a href="./selectExperience.do?id=<%= ((ExperienceValueObject)pageContext.getAttribute("testit")).getId()%>">Seleccionar</a>
				<% } %>
			</display:column>
		</display:table>
	</div>
	<a href="./backToExperience.do">Volver</a>
</div>
</body>
</html>