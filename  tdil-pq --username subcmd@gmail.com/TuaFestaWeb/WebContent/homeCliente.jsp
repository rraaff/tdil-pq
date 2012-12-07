<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="homeCliente"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Mi Cuenta (Clientes)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<style>
#formSection {
	width:920px;
}
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<% ClientHomeForm clientHomeForm = (ClientHomeForm)session.getAttribute("ClientHomeForm"); %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Cuenta de <%=clientHomeForm.getClient().getFirstname() %></h1>
			<h2>Desde esta secci&oacute;n podr&aacute;s ver y acceder a modificar todos tus datos.</h2>
		</div>
		<div id="formContent">
			<div id="formSection">
				<h2>Datos de mi cuenta</h2>
				<div class="myRow">
					<div class="myLabel width60">Nombre</div>
					<div class="myLabel width860"><strong><%=clientHomeForm.getClient().getFirstname() %></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width60">Apellido</div>
					<div class="myLabel width860"><strong><%=clientHomeForm.getClient().getLastname() %></strong></div>
				</div>
				<div class="myRow">
					<div class="myLabel width60">Sexo</div>
					<div class="myLabel width150"><strong><%=clientHomeForm.getClient().getSex().equals("m") ? "Masculino" : "Femenino"%></strong></div>
					<div class="myLabel width120">E-mail/usuario</div>
					<div class="myLabel width200"><strong><%=clientHomeForm.getClient().getEmail() %></strong></div>
				</div>
				<% if (clientHomeForm.getLocation() != null) { %>
					<div class="myRow">
						<div class="myLabel width100">Localizado en </div>
						<div class="myLabel width800"><strong><%= clientHomeForm.getLocation().getNombre4()%>, <%= clientHomeForm.getLocation().getNombre3()%>, <%= clientHomeForm.getLocation().getNombre2()%></strong></div>
					</div>
				<% } %>
				<div class="myRow" align="center">
					<a href="./editClient.do?id=<%=clientHomeForm.getClient().getId()%>">Editar mis datos</a>
					<a href="./goToChangeClientPassword.do?id=<%=clientHomeForm.getClient().getId()%>">Cambiar mi password</a>
				</div>
			</div>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>