<%@page import="com.tdil.milka.model.valueobjects.StatusHelper"%>
<%@page import="com.tdil.milka.model.valueobjects.CreationDateHelper"%>
<%@page import="com.tdil.milka.model.LoveNicknames"%>
<%@page import="com.tdil.milka.struts.forms.LoveNicknameAdministrationForm"%>
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
	LoveNicknameAdministrationForm postItAdministrationForm = (LoveNicknameAdministrationForm)session.getAttribute("LoveNicknameAdministrationForm");
java.util.List source = postItAdministrationForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Experiencia Apodos de Amor</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde esta secci�n podr� aprobar o desaprobar los apodos cargados desde de la experiencia APODOS DE AMOR por los usuarios. Recuerde que los mensajes se cargan directamente cuando el usuario postea, y si bien existe un filtro de palabras inadecuadas, no es ineludible.</span></div>
	</div>
	<div class="renglon width860">
		<display:table name="test" sort="external" pagesize="10" id="testit">
			<display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable"><%= CreationDateHelper.getCreationDateAsString(((LoveNicknames)pageContext.getAttribute("testit")).getCreationdate())%></display:column>
			<display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="originaltext"></display:column>
			<display:column title="estado" sortable="true" sortName="estado" headerClass="sortable"><%= StatusHelper.getStatusRB(((LoveNicknames)pageContext.getAttribute("testit")).getDeleted(), ((LoveNicknames)pageContext.getAttribute("testit")).getApproved())%></display:column>
			<display:column title="acciones"><a href="./reviewLoveNickname.do?id=<%= ((LoveNicknames)pageContext.getAttribute("testit")).getId()%>">Revisar</a></display:column>
		</display:table>
	</div>
	<div class="renglon width860" align="center">
		<logic:notEqual name="LoveNicknameAdministrationForm" property="objectId" value="0">
			<html:form method="POST" action="/approveDisapproveLoveNickname">
				<p style="margin:20px;">Texto cargado por el usuario: <b><bean:write name="LoveNicknameAdministrationForm" property="originaltext"/></b></p>
				<p style="margin:20px;">Sexo cargado por el usuario: <b><bean:write name="LoveNicknameAdministrationForm" property="sex"/></b></p>
				<p style="margin:20px;">Posicion: <b><bean:write name="LoveNicknameAdministrationForm" property="position"/></b></p>
				<html:submit property="operation">
					<bean:message key="approve" />
				</html:submit>
				<html:submit property="operation">
					<bean:message key="disapprove" />
				</html:submit>
			</html:form>
		</logic:notEqual>
	</div>
</div>
</body>
</html>