<%@page import="com.tdil.ljpeugeot.model.ContactData"%>
<%@page import="com.tdil.ljpeugeot.model.valueobjects.AlertValueObject"%><%--
--%><%@page import="com.tdil.ljpeugeot.services.PeugeotService"%><%--
--%><%@page import="com.tdil.ljpeugeot.model.Alert"%><%--
--%><%@page import="com.tdil.utils.StringUtils"%><%--
--%><%@page import="com.tdil.struts.resources.ApplicationResources"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.SearchUserForm"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%AlertValueObject alert =  PeugeotService.getAlertInProgress(websiteUser.getId()); 
if (alert== null) { %><jsp:forward page="home.jsp"></jsp:forward><%}%>
<% ContactData contactData = alert.getContactData(); %>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: CallCenter Application</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_backdoor.css" />
<%@ include file="includes/headLogged.jsp" %>
</head>
<body>
<%@ include file="../admin/includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot CallCenter Application</h1>
	<h2>Alerta en progreso</h2>
	<h3>Versión: <%@ include file="../includes/version_view.jspf" %></h3>
</section>
<section id="content">
	<article>
		<h3>Información del usuario</h3>
		<div class="data_group">
			<fieldset class="width100per">
				<label class="dato">Nombre</label>
				<label class="resultado"><%=StringUtils.notNullValueOf(alert.getUser().getFirstname())%></label>
			</fieldset>
			<fieldset class="width100per">
				<label class="dato">Apellido</label>
				<label class="resultado"><%=StringUtils.notNullValueOf(alert.getUser().getLastname())%></label>
			</fieldset>
		</div>
		<% if (contactData == null) { %>
			<p class="attention">El usuario no ha definido sus datos de contacto</p>
		<% } else { %>
			<h3>Información de contacto</h3>
			<div class="data_group">
				<fieldset class="width100per">
					<label class="dato">Primer contacto</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(contactData.getContact1name())%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Relación</label>
					<label class="resultado"><%=!org.apache.commons.lang.StringUtils.isEmpty(contactData.getContact1relation()) ? ApplicationResources.getMessage("relation_" + contactData.getContact1relation()) : "-"%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Teléfono</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(contactData.getContact1phone())%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Palabra clave</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(contactData.getContact1secword())%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Obra Social</label>
					<label class="resultado"><%=StringUtils.nvl(contactData.getContact1healthi(),"-")%></label>
				</fieldset>
				<hr/>
				<fieldset class="width100per">
					<label class="dato">Segundo contacto</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(contactData.getContact2name())%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Relación</label>
					<label class="resultado"><%=!org.apache.commons.lang.StringUtils.isEmpty(contactData.getContact2relation()) ? ApplicationResources.getMessage("relation_" + contactData.getContact2relation()) : "-"%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Teléfono</label>
					<label class="resultado"><%=StringUtils.nvl(contactData.getContact2phone(),"-")%></label>
				</fieldset>
				<hr/>
				<fieldset class="width100per">
					<label class="dato">Tercer contacto</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(contactData.getContact3name())%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Relación</label>
					<label class="resultado"><%=!org.apache.commons.lang.StringUtils.isEmpty(contactData.getContact3relation()) ? ApplicationResources.getMessage("relation_" + contactData.getContact3relation()) : "-"%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Teléfono</label>
					<label class="resultado"><%=StringUtils.nvl(contactData.getContact3phone(),"-")%></label>
				</fieldset>
			</div>
		<% } %>
		<h3>Información de alerta</h3>
		<div class="data_group">
			<fieldset class="width100per">
				<label class="dato">Telefono</label>
				<label class="resultado"><%=StringUtils.notNullValueOf(alert.getAlert().getPhonenumber())%></label>
			</fieldset>
			<% if (alert.getAlert().getLat().intValue() == 0) { %>
				<p class="attention">no hay informacion de coordenadas</p>
			<% } else { %>
				<fieldset class="width100per">
					<label class="dato">Latitud</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(alert.getAlert().getLat().toString())%></label>
				</fieldset>
				<fieldset class="width100per">
					<label class="dato">Longitud</label>
					<label class="resultado"><%=StringUtils.notNullValueOf(alert.getAlert().getLon().toString())%></label>
				</fieldset>
				<div id="map_canvas" style="width:100%; height:400px"></div>
				<script>
				    function initialize() {
				      var myLatlng = new google.maps.LatLng(<%=alert.getAlert().getLat().toString()%>, <%=alert.getAlert().getLon().toString()%>);
				      var myOptions = {
				        zoom: 15,
				        center: myLatlng,
				        mapTypeId: google.maps.MapTypeId.ROADMAP
				      }
				      var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
				      var marker = new google.maps.Marker({
				          position: myLatlng,
				          map: map,
				          title:"Posicion de alerta"
				      });
				    }
				
				    function loadScript() {
				      var script = document.createElement("script");
				      script.type = "text/javascript";
				      script.src = "http://maps.google.com/maps/api/js?sensor=false&callback=initialize";
				      document.body.appendChild(script);
				    }
				
				    window.onload = loadScript;
				</script>
			<% } %>
			<fieldset class="width100per">
				<a class="link_as_button" href="finishProgress.jsp?alertId=<%=alert.getAlert().getId()%>">Finalizar</a>
			</fieldset>
		</div>
	</article>
</section>
</body>
</html>