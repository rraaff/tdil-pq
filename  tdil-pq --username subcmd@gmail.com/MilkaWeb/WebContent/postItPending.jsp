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
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Experiencia POST ITs</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde esta secci�n podr� aprobar, modificar o desaprobar los contenidos de la experiencia POST ITs.</span></div>
	</div>
	<div class="renglon width860">
		<display:table name="test" sort="external" pagesize="10" id="testit">
			<display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable" property="creationDateAsString"></display:column>
			<display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
			<display:column title="email" sortable="true" sortName="email" headerClass="sortable" property="email"></display:column>
			<display:column title="estado" sortable="true" sortName="estado" headerClass="sortable" property="statusRB"></display:column>
			<display:column title="acciones">
			<a href="./reviewPostIt.do?id=<%= ((PostItValueObject)pageContext.getAttribute("testit")).getId()%>">Revisar</a>
			</display:column>
		</display:table>
	</div>
	<div class="renglon width860">
		<logic:notEqual name="PostItAdministrationForm" property="objectId" value="0">
		<html:form method="POST" action="/approveDisapprovePostIt">
			Texto original: <bean:write name="PostItAdministrationForm" property="originaltext"/><br><br>
			Titulo: <html:text name="PostItAdministrationForm" property="title" style="width: 300px;"/><br><br>
			Descripcion: <html:text name="PostItAdministrationForm" property="description" style="width: 300px;"/><br><br>
			Color:<html:select name="PostItAdministrationForm" property="color" styleClass="width120">
				<logic:iterate name="PostItAdministrationForm" property="allColors"
					id="iterColor">
					<option	<%=	((String) iterColor).equals( postItAdministrationForm.getColor()) ? "selected" : ""%>
						value="<%=iterColor%>">
						&nbsp;&nbsp;&nbsp;<%=iterColor%></option>
				</logic:iterate>
			</html:select><br><br>
			<div class="label width80">URL:</div><bean:write name="PostItAdministrationForm" property="urlLink"/> <a href="./goToLinkTargetSelectionFromPostIt.do">Seleccion de link</a> - <a href="./clearLinkTargetPostIt.do">Borrar link</a><br><br>
			Target:<html:select name="PostItAdministrationForm" property="urlTarget" >
				<% for (String iterTarget : UrlUtils.getAllTargets()) { %>
					<option	<%=	(iterTarget).equals( postItAdministrationForm.getUrlTarget()) ? "selected" : ""%>
						value="<%=iterTarget%>">
						&nbsp;&nbsp;&nbsp;<%=iterTarget%></option>
				<% } %>
			</html:select><br><br>
			Miniatura	
			<logic:equal name="PostItAdministrationForm" property="hasThumb" value="true">
				<img id="img_thumb" src="./viewPostItThumb.do" width="30" height="30" align="absmiddle"> 
			</logic:equal>
			<logic:notEqual name="PostItAdministrationForm" property="hasThumb" value="true">
				<img id="img_thumb" src="boImages/na.gif" width="30" height="30" align="absmiddle"> 
			</logic:notEqual>
			<input type="file" name="upload_thumb" id="upload_thumb"><%=MilkaErrorFormatter.getErrorFrom(request, "PostItAdministrationForm.thumb.err")%><br>
			Postit	
			<logic:equal name="PostItAdministrationForm" property="hasCover" value="true">
				<img id="img_cover" src="./viewPostItImage.do" width="30" height="30" align="absmiddle"> 
			</logic:equal>
			<logic:notEqual name="PostItAdministrationForm" property="hasCover" value="true">
				<img id="img_cover" src="boImages/na.gif" width="30" height="30" align="absmiddle"> 
			</logic:notEqual>
			<input type="file" name="upload_cover" id="upload_cover"><%=MilkaErrorFormatter.getErrorFrom(request, "PostItAdministrationForm.cover.err")%><br>
		
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