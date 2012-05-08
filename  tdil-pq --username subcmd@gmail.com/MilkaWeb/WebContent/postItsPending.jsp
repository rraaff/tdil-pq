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
PostItAdministrationForm postItAdministrationForm = (PostItAdministrationForm)session.getAttribute("PostItAdministrationForm");
java.util.List source = postItAdministrationForm.getApprovalPending();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated); %>
<body>
<display:table name="test" sort="external" pagesize="10" id="testit">
  <display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable" property="creationDateAsString"></display:column>
  <display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
  <display:column title="email" sortable="true" sortName="email" headerClass="sortable" property="email"></display:column>
  <display:column title="estado" sortable="true" sortName="estado" headerClass="sortable" property="statusRB"></display:column>
  <display:column title="acciones">
  	<a href="./viewMilkaPhoto.do?id=<%= ((PostItValueObject)pageContext.getAttribute("testit")).getId()%>">Ver imagen</a>
  	<a href="./publishMilkaPhoto.do?id=<%= ((PostItValueObject)pageContext.getAttribute("testit")).getId()%>">Publicar</a>
  	<a href="./editMilkaPhoto.do?id=<%= ((PostItValueObject)pageContext.getAttribute("testit")).getId()%>">Editar</a>
  </display:column>
</display:table>
</body>
</html>