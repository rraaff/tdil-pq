<%@page import="com.tdil.milka.model.valueobjects.StatusHelper"%>
<%@page import="com.tdil.milka.model.valueobjects.CreationDateHelper"%>
<%@page import="com.tdil.milka.model.WallWritting"%>
<%@page import="com.tdil.milka.struts.forms.PapapediaAdministrationForm"%>
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
	PapapediaAdministrationForm postItAdministrationForm = (PapapediaAdministrationForm)session.getAttribute("PapapediaAdministrationForm");
java.util.List source = postItAdministrationForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
<display:table name="test" sort="external" pagesize="10" id="testit">
  <display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable">
  	<%= CreationDateHelper.getCreationDateAsString(((WallWritting)pageContext.getAttribute("testit")).getCreationdate())%>
  </display:column>
  <display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="originaltext"></display:column>
  <display:column title="estado" sortable="true" sortName="estado" headerClass="sortable">
  	<%= StatusHelper.getStatusRB(((WallWritting)pageContext.getAttribute("testit")).getDeleted(), ((WallWritting)pageContext.getAttribute("testit")).getApproved())%>
  </display:column>
  <display:column title="acciones">
  	<a href="./reviewPapapedia.do?id=<%= ((WallWritting)pageContext.getAttribute("testit")).getId()%>">Revisar</a>
  </display:column>
</display:table>

<logic:notEqual name="PapapediaAdministrationForm" property="objectId" value="0">
<html:form method="POST" action="/approveDisapprovePapapedia">
	Texto original: <bean:write name="PapapediaAdministrationForm" property="originaltext"/><br><br>
	<html:submit property="operation">
		<bean:message key="approve" />
	</html:submit>
	<html:submit property="operation">
		<bean:message key="disapprove" />
	</html:submit>
</html:form>
</logic:notEqual>
</div>
</body>
</html>