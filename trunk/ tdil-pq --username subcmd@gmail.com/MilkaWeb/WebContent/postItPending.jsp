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
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$('#upload_thumb').ajaxfileupload({
		  	'action': './uploadPostItImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#img_thumb').attr('src', './viewPostItThumb.do');
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		$('#upload_cover').ajaxfileupload({
		  	'action': './uploadPostItImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#img_cover').attr('src', './viewPostItImage.do');
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
	}
);
</script>
</head>

<%
	PostItAdministrationForm postItAdministrationForm = (PostItAdministrationForm)session.getAttribute("PostItAdministrationForm");
java.util.List source = postItAdministrationForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<display:table name="test" sort="external" pagesize="10" id="testit">
  <display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable" property="creationDateAsString"></display:column>
  <display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
  <display:column title="email" sortable="true" sortName="email" headerClass="sortable" property="email"></display:column>
  <display:column title="estado" sortable="true" sortName="estado" headerClass="sortable" property="statusRB"></display:column>
  <display:column title="acciones">
  	<a href="./reviewPostIt.do?id=<%= ((PostItValueObject)pageContext.getAttribute("testit")).getId()%>">Revisar</a>
  </display:column>
</display:table>

<logic:notEqual name="PostItAdministrationForm" property="objectId" value="0">
<html:form method="POST" action="/approveDisapprovePostIt">
	Texto original: <bean:write name="PostItAdministrationForm" property="originaltext"/><br>
	Titulo: <html:text name="PostItAdministrationForm" property="title" style="width: 300px;"/><br>

	Miniatura	
	<logic:equal name="PostItAdministrationForm" property="hasThumb" value="true">
		<img id="img_thumb" src="./viewPostItThumb.do" width="30" height="30" align="absmiddle"> 
	</logic:equal>
	<logic:notEqual name="PostItAdministrationForm" property="hasThumb" value="true">
		<img id="img_thumb" src="boImages/na.gif" width="30" height="30" align="absmiddle"> 
	</logic:notEqual>
	<input type="file" name="upload_thumb" id="upload_thumb"><br>
	Postit	
	<logic:equal name="PostItAdministrationForm" property="hasCover" value="true">
		<img id="img_cover" src="./viewPostItImage.do" width="30" height="30" align="absmiddle"> 
	</logic:equal>
	<logic:notEqual name="PostItAdministrationForm" property="hasCover" value="true">
		<img id="img_cover" src="boImages/na.gif" width="30" height="30" align="absmiddle"> 
	</logic:notEqual>
	<input type="file" name="upload_cover" id="upload_cover"><br>

	<html:submit property="operation">
		<bean:message key="approve" />
	</html:submit>
	<html:submit property="operation">
		<bean:message key="disapprove" />
	</html:submit>
</html:form>
</logic:notEqual>
</body>
</html>