<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.WallWrittingValueObject"%>
<%@ include file="includes/userLogged.jspf" %>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalProfileForm"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="perfilPublicoProfesional"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Perfil de usuario profesional</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script>
<%
ProfesionalProfileForm profesionalProfileForm = (ProfesionalProfileForm)session.getAttribute("ProfesionalProfileForm"); 
Profesional profesional = profesionalProfileForm.getProfesional();
List<WallWrittingValueObject> muro = profesionalProfileForm.getWallWritting();
int totalItems = muro.size();
%>
$(document).ready(
	function(){
		$('.more').live("click",function() 
			{
				var ID = $(this).attr("id");
				if(ID) {
					$("#more"+ID).html('<img src="img/moreajax.gif" />');
					$.ajax({
					type: "POST",
					url: "muroPagina.jsp?idwall=<%=profesional.getIdWall()%>&idprof=<%=profesional.getId()%>",
					data: "items="+ ID, 
					cache: false,
					success: function(html){
						$("#muroContainer").append(html);
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
<style>
<!--

.agendaBusy {
	background:#CCCCCC;
}
.agendaNormal {
	background:#FFFFFF;
}
.agendaNotActual {
	background:#000000;
}

#muroContainer {
	width:280px;
	background-color: #E6E6E6;
	padding: 10px;
	
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
-->
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Perfil del profesional</h1>
			<h2>Mir&aacute; los datos del profesional, sus productos y/o servicios, dejales una consulta en su muro o revis&aacute; su agenda de eventos</h2>
		</div>
		<div id="formContent">
			<div id="formSection" style="width:920px;">
				<h2><bean:write name="ProfesionalProfileForm" property="profesional.businessname"/></h2>
				<div class="fright width300">
					<div id="muroContainer" class="height350">
						<h2 style="font-size:18px;">Muro de <bean:write name="ProfesionalProfileForm" property="profesional.completeName"/></h2>
						<% if (websiteUser != null && websiteUser.isClient()) {
							profesionalProfileForm.setUserId(websiteUser.getId());%>
							<html:form method="POST" action="/addWallComment">
								<div class="myRow height80">
									<div class="myLabel width280 height80"><html:textarea name="ProfesionalProfileForm" property="content" styleClass="width280 height80" /></div>
								</div>
								<div class="myRow height50" align="center"><input type="submit" value="Enviar"/></div>
							</html:form>
						<% } else { %>
							Para poder postear tenes que estar logueado
						<% } %>
						<% int index = 0;
							for (WallWrittingValueObject wwvo : muro) { 
								if(index < 10) { %>
									<div><%=wwvo.getOriginaltext() %> (<%=wwvo.getIdAuthor() == null ? profesional.getBusinessname() : wwvo.getAuthorName()%>)</div>
								<% }
								index = index + 1;
								%>
						<% } %>
						<% if (totalItems > 10) { %>
						<div id="more10" class="morebox">
							<a href="#" class="more" id="10">Ver mas</a>
						</div>
						<% } %>
					</div>
				</div>
				<div class="fleft width600">
					<div class="myRow">
						<div class="myLabel width80">Sexo</div>
						<div class="myLabel width120"><strong><%=profesional.getSex().equals("m") ? "Masculino" : "Femenino"%></strong></div>
						<div class="myLabel width80">Fecha Nac.</div>
						<div class="myLabel width120"><strong><bean:write name="ProfesionalProfileForm" property="birthDate"/></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Ubicaci&oacute;n</div>
						<div class="myLabel width520"><strong><bean:write name="ProfesionalProfileForm" property="geoLevelPath"/></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Web</div>
						<div class="myLabel width520"><strong><%= (profesional.getWebsite() != null) ? profesional.getWebsite() : "-" %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Facebook</div>
						<div class="myLabel width520"><strong><%= (profesional.getFacebook() != null) ? profesional.getFacebook() : "-" %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Horario</div>
						<div class="myLabel width520"><strong><bean:write name="ProfesionalProfileForm" property="profesional.businesshours"/></strong></div>
					</div>
					<div class="myRow height80">
						<div class="myLabel width80">Descripci&oacute;n</div>
						<div class="myLabel width520 height80"><strong><bean:write name="ProfesionalProfileForm" property="profesional.description"/></strong></div>
					</div>
					<h2 style="float:left; padding-left:0; padding-bottom:0; margin-bottom:10px; margin-top:10px;">Productos y Servicios</h2>
					<div class="myRow">ACA VA EL LISTADO - Pendiente</div>
					<div class="myRow" align="center"><a class="inputButtonHelper" style="color:#000000; text-decoration:none;" href="./contactProfesional.do?id=<bean:write name='ProfesionalProfileForm' property='profesional.id'/>">Contactar profesional</a></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>