<%@page import="com.tdil.milka.struts.forms.EmailEndingsAdministrationForm"%>
<%@page import="com.tdil.milka.model.valueobjects.EmailEndingsValueObject"%>
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

	<style>
	.ui-autocomplete-loading { background: white url('css/images/ui-anim_basic_16x16.gif') right center no-repeat; }
	</style>

<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$('#upload_email').ajaxfileupload({
		  	'action': './uploadEmailEndingReplacement.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#img_to_approve').attr('src', './viewEmailEndingReplacement.do');
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
	EmailEndingsAdministrationForm emailEndingsAdministrationForm = (EmailEndingsAdministrationForm)session.getAttribute("EmailEndingsAdministrationForm");
java.util.List source = emailEndingsAdministrationForm.getSourceList();
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
  	<a href="./reviewEmailEndings.do?id=<%= ((EmailEndingsValueObject)pageContext.getAttribute("testit")).getId()%>">Revisar</a>
  </display:column>
</display:table>
<logic:notEqual name="EmailEndingsAdministrationForm" property="idBlobData" value="0">
<img id="img_to_review" width="200" height="200" src="./download.st?id=<bean:write name="EmailEndingsAdministrationForm" property="idBlobData"/>&type=PUBLIC&ext=<bean:write name="EmailEndingsAdministrationForm" property="extBlobData"/>" alt="">

<img id="img_to_approve" src="./viewEmailEndingReplacement.do" width="30" height="30" align="absmiddle"> 
<input type="file" name="upload_email" id="upload_email">Reemplazar<br>

<html:form method="POST" action="/approveDisapproveEmailEndings">
Titulo: <html:text name="EmailEndingsAdministrationForm" property="title" style="width: 300px;"/><br><br>
Descripcion: <html:text name="EmailEndingsAdministrationForm" property="description" style="width: 300px;"/><br><br>
	<html:checkbox name="EmailEndingsAdministrationForm" property="frontcover" /> Portada<br>
	<html:checkbox name="EmailEndingsAdministrationForm" property="showinhome" /> Mostrar en la home<br>

	<html:submit property="operation">
		<bean:message key="approve" />
	</html:submit>
	<html:submit property="operation">
		<bean:message key="disapprove" />
	</html:submit>
</html:form>
<script>
$(document).ready(
	function(){
		//alert($('#img_to_review').prop('width'));
		//$('#img_to_review').resize({maxWidth: '200', maxHeight: '200'});
	}
	
);

$(window).load(function() {
      $('#img_to_review').resize({maxWidth: '200', maxHeight: '200'});
      $('#img_to_approve').resize({maxWidth: '200', maxHeight: '200'});
});
</script>
</logic:notEqual>

</body>
</html>