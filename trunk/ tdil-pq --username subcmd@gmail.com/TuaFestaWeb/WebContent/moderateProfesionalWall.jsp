<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject"%>
<%@page import="com.tdil.tuafesta.struts.forms.WallModerationForm"%>
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="editProfesionalPersonalData"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />

<%@ include file="includes/boErrorJS.jsp" %>
<% WallModerationForm wallModerationForm = (WallModerationForm)session.getAttribute("WallModerationForm"); %>
<script>
$(document).ready(
	function(){
		$('.more').live("click",function() 
			{
				var ID = $(this).attr("id");
				if(ID) {
					$("#more"+ID).html('<img src="img/moreajax.gif" />');
					$.ajax({
					type: "POST",
					url: "moderarMuroPagina.jsp?idwall=<%=wallModerationForm.getWallId()%>&idprof=<%=wallModerationForm.getProfesional().getId()%>",
					data: "items="+ ID, 
					cache: false,
					success: function(html){
						$("#formContent").append(html);
						$("#more"+ID).remove(); // removing old more button
					}
					});
				} else {
					$(".morebox").html('No hay mas resultados');// no results
				}
				return false;
			}
		);
	}
);
</script>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Moderar mi muro</h1>			
			<h2></h2>
		</div>
		<% if (wallModerationForm.isResponding()) { %>
			Respondiendo a: <%=wallModerationForm.getPostClient().getFirstname() + " " + wallModerationForm.getPostClient().getLastname()%><br>
			<%=wallModerationForm.getPostToAnswer().getOriginaltext()%>
		<% } %>
		<html:form method="POST" action="/saveWallWritting">
			<div class="renglon">
				<div class="label width100per" align="center"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
			</div>
			<div class="renglon">
				<div class="label width130">Texto:</div>
				<div class="label width780"><html:text name="WallModerationForm" property="post" styleClass="normalField width740"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, WallModerationForm.post_key + ".err")%></div>
			</div>
			<div class="renglon height50" align="center">
				<logic:equal name="WallModerationForm" property="responding" value="true">
					<html:submit property="operation">
						<bean:message key="answer" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="WallModerationForm" property="responding" value="true">
					<html:submit property="operation">
						<bean:message key="post" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="reset" />
				</html:submit>
			</div>
		</html:form>
			
		<br>
		<% if (wallModerationForm.isShowAll()) { %>
			<b></>Todos</b> - <a href="./viewWallWrittingPending.do">Solo pendientes</a>
		<% } else { %>
			<a href="./viewWallWrittingAll.do">Todos</a> - <b>Solo pendientes</b>
		<% } %>
		<div id="formContent">
			<% int index = 0;
				for (WallWrittingValueObject wwvo : wallModerationForm.getWallWritting()) { %>
					<div>
						<%=wwvo.getOriginaltext() %> (<%=wwvo.getIdAuthor() == null ? wallModerationForm.getProfesional().getBusinessname() : wwvo.getAuthorName()%>)
						<% if (wwvo.isAuthorProfesional()) { %>
							<a href="./loadWallWrittingToEdit.do?id=<%=wwvo.getId() %>">Editar</a>
							<a href="./deleteWallWritting.do?id=<%=wwvo.getId() %>">Borrar</a>
						<% } else { %>
							<% if (wwvo.getResponsePending().equals(1)) { %>
								<a href="./loadWallWrittingToAnswer.do?id=<%=wwvo.getId() %>">Responder</a>
								<a href="./markAsRespondedWallWritting.do?id=<%=wwvo.getId() %>">Marcar como respondido</a>
							<% } %>
							<a href="./deleteWallWritting.do?id=<%=wwvo.getId() %>">Borrar</a>
						<% } %>
					</div>
				<% } %>
				<% if (wallModerationForm.isShowAll() && wallModerationForm.hasMore()) { %>
					<div id="more<%=wallModerationForm.getWallWritting().size()%>" class="morebox">
						<a href="#" class="more" id="<%=wallModerationForm.getWallWritting().size()%>">Ver mas</a>
					</div>
				<% } %>
		</div>
		<!-- aca Termina el formulario -->
		<a href="./goToProfesionalHome.do?id=<%=wallModerationForm.getId()%>">Volver</a>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>