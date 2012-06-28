<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.milka.struts.forms.UrlUtils"%>
<%@page import="com.tdil.milka.struts.forms.MailToParentAdministrationForm"%>
<%@page import="com.tdil.milka.model.valueobjects.MailToParentValueObject"%>
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
		  	'action': './uploadMailToParentReplacement.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#img_to_approve').attr('src', './viewMailToParentReplacement.do');
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
	MailToParentAdministrationForm mailToParentAdministrationForm = (MailToParentAdministrationForm)session.getAttribute("MailToParentAdministrationForm");
java.util.List source = mailToParentAdministrationForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Experiencia CARTAS DE HIJOS A PADRES</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde esta sección podrá aprobar, modificar o desaprobar las imágenes cargadas desde de la experiencia CARTAS DE HIJOS A PADRES por los usuarios. Respete los tamaños de las imágenes.</span></div>
	</div>
	<div class="renglon width860" style="margin-bottom:20px;">
		<display:table name="test" sort="external" pagesize="10" id="testit" requestURI="./mailToParentPending.jsp">
			<display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable" property="creationDateAsString"></display:column>
			<display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
			<display:column title="email" sortable="true" sortName="email" headerClass="sortable" property="email"></display:column>
			<display:column title="estado" sortable="true" sortName="estado" headerClass="sortable" property="statusRB"></display:column>
			<display:column title="acciones"><a href="./reviewMailToParent.do?id=<%= ((MailToParentValueObject)pageContext.getAttribute("testit")).getId()%><%=DisplayTagParamHelper.getParams(request)%>">Revisar</a></display:column>
		</display:table>
	</div>
	<logic:notEqual name="MailToParentAdministrationForm" property="idBlobData" value="0">
<div class="renglon width860" style="margin-top:20px; border:dotted 1px #CCCCCC;">
			<div style="width:212px; height:177px; float:left;">
				<h3>Imagen original</h3>
				<img id="img_to_review" width="212" height="157" src="./download.st?id=<bean:write name="MailToParentAdministrationForm" property="idBlobData"/>&type=PUBLIC&ext=<bean:write name="MailToParentAdministrationForm" property="extBlobData"/>" alt="">
			</div>
			<div style="width:212px; height:177px; float:left;">
				<h3>Imagen actual</h3>
				<img id="img_to_approve" src="./viewMailToParentReplacement.do" width="30" height="30" align="absmiddle">
			</div>
			<html:form method="POST" action="/approveDisapproveMailToParent">
				<%=DisplayTagParamHelper.getFields(request)%>
				<div style="width:400px; height:200px; float:left;">
					Reemplazar<br/><br/>
					<input type="file" name="upload_email" id="upload_email">Reemplazar<br/><br/>
					<div class="label width80">Titulo: </div><html:text name="MailToParentAdministrationForm" property="title" style="width: 300px;"/><br><br>
					<div class="label width80">Descripcion: </div><html:text name="MailToParentAdministrationForm" property="description" style="width: 300px;"/><br><br>
					<div class="label width80">URL:</div><bean:write name="MailToParentAdministrationForm" property="urlLink"/> <a href="./goToLinkTargetSelectionFromMailToParent.do">Seleccion de link</a> - <a href="./clearLinkTargetMailToParent.do">Borrar link</a><br><br>
					<div class="label width80">Target: </div>
						<html:select name="MailToParentAdministrationForm" property="urlTarget" >
							<% for (String iterTarget : UrlUtils.getAllTargets()) { %>
								<option	<%=	(iterTarget).equals( mailToParentAdministrationForm.getUrlTarget()) ? "selected" : ""%>
									value="<%=iterTarget%>">
									&nbsp;&nbsp;&nbsp;<%=iterTarget%></option>
							<% } %>
						</html:select>
				</div>
				<div class="renglon width860" align="center">
					<html:submit property="operation">
						<bean:message key="approve" />
					</html:submit>
					<html:submit property="operation">
						<bean:message key="disapprove" />
					</html:submit>
				</div>
			</html:form>
		</div>
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
	</div>
</div>
</body>
</html>