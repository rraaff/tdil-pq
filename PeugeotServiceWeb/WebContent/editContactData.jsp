<%@page import="com.tdil.web.breadcrum.BreadcrumItem"%>
<%@page import="com.tdil.web.breadcrum.Breadcrum"%>
<%@page import="com.tdil.struts.resources.ApplicationResources"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.EditContactDataForm"%>
<%@page import="com.tdil.ljpeugeot.prevent.model.SatellitePosition"%>
<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.utils.LJPeugeotConfig"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%@ include file="includes/mustBePreventUser.jspf" %><%--
--%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Ver/Modificar Configuración de emergencias</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="fonts/peugeot/fonts.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website_logged.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_ie8-fixes.css" />
<![endif]-->
<%
	Boolean apk = (Boolean)session.getAttribute("USING_APK");
if (apk != null && apk) {
	isAndroid = true;
}
%>
<%@ include file="includes/headLogged.jsp" %>
<script>
	$(document).ready(
			function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
				
			}
	);

	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>
	

</script>
</head>
<%@ include file="includes/version.jspf" %>
<body>
<% if (usingMobile || isAndroid) { %>
	<div style="background:#99ECD6; line-height:20px; text-align:center; color:#000;">android or mobile</div>
<% } %>
<%
	Breadcrum breadcrums = new Breadcrum()
	.titles("Inicio","Peugeot App","Configuración de emergencias")
	.pages("home.jsp","home.jsp", "");
%>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="main_content_regular_page">
	<div class="page_header">
		<h2>Determinar contactos de emergencia</h2>
		<p>Los contactos de seguridad permitirán que la compañía LoJack utilice la información para llevar a cabo los protocolos de emergencia en caso de recibir una alerta de emergencia.</p>
	</div>
	<html:form method="POST" action="/saveContactData" styleClass="regular_form_page">
	<% EditContactDataForm editContactDataForm = (EditContactDataForm)session.getAttribute("EditContactDataForm");%>
		<div class="column1of3">
			<h3>Primer contacto</h3>
			<div class="mini_form_wrapper">
				<fieldset>
					<label>Nombre</label>
					<html:text name="EditContactDataForm" property="contact1name" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1name.err")%>
				</fieldset>
				<fieldset>
					<label>Relación</label>
					<html:select name="EditContactDataForm" property="contact1relation">
						<option value="">Seleccione...</option>
						<option <%="RELATIVE".equals(editContactDataForm.getContact1relation()) ? "selected" : ""%> value="RELATIVE">
							<%=ApplicationResources.getMessage("relation_RELATIVE")%></option>
						<option <%="FRIEND".equals(editContactDataForm.getContact1relation()) ? "selected" : ""%> value="FRIEND">
							<%=ApplicationResources.getMessage("relation_FRIEND")%></option>
						<option <%="COWORKER".equals(editContactDataForm.getContact1relation()) ? "selected" : ""%> value="COWORKER">
							<%=ApplicationResources.getMessage("relation_COWORKER")%></option>
					</html:select>
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1relation.err")%>
				</fieldset>
				<fieldset>
					<label>Teléfono</label>
					<html:text name="EditContactDataForm" property="contact1phone" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1phone.err")%>
				</fieldset>
				<fieldset>
					<label>Palabra de seguridad</label>
					<html:text name="EditContactDataForm" property="contact1secword" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1secword.err")%>
				</fieldset>
				<fieldset>
					<label>Cobertura médica</label>
					<html:text name="EditContactDataForm" property="contact1healthi" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1healthi.err")%>
				</fieldset>
			</div>
		</div>
		<div class="column1of3">
			<h3>Segundo contacto</h3>
			<div class="mini_form_wrapper">
				<fieldset>
					<label>Nombre</label>
					<html:text name="EditContactDataForm" property="contact2name" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact2name.err")%>
				</fieldset>
				<fieldset>
					<label>Relación</label>
					<html:select name="EditContactDataForm" property="contact2relation">
						<option value="">Seleccione...</option>
						<option <%="RELATIVE".equals(editContactDataForm.getContact2relation()) ? "selected" : ""%> value="RELATIVE">
							<%=ApplicationResources.getMessage("relation_RELATIVE")%></option>
						<option <%="FRIEND".equals(editContactDataForm.getContact2relation()) ? "selected" : ""%> value="FRIEND">
							<%=ApplicationResources.getMessage("relation_FRIEND")%></option>
						<option <%="COWORKER".equals(editContactDataForm.getContact2relation()) ? "selected" : ""%> value="COWORKER">
							<%=ApplicationResources.getMessage("relation_COWORKER")%></option>
					</html:select>
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact2relation.err")%>
				</fieldset>
				<fieldset>
					<label>Teléfono</label>
					<html:text name="EditContactDataForm" property="contact2phone" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact2phone.err")%>
				</fieldset>
			</div>
		</div>
		<div class="column1of3">
			<h3>Segundo contacto</h3>
			<div class="mini_form_wrapper">
				<fieldset>
					<label>Nombre</label>
					<html:text name="EditContactDataForm" property="contact3name" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact3name.err")%>
				</fieldset>
				<fieldset>
					<label>Relación</label>
					<html:select name="EditContactDataForm" property="contact3relation">
						<option value="">Seleccione...</option>
						<option <%="RELATIVE".equals(editContactDataForm.getContact3relation()) ? "selected" : ""%> value="RELATIVE">
							<%=ApplicationResources.getMessage("relation_RELATIVE")%></option>
						<option <%="FRIEND".equals(editContactDataForm.getContact3relation()) ? "selected" : ""%> value="FRIEND">
							<%=ApplicationResources.getMessage("relation_FRIEND")%></option>
						<option <%="COWORKER".equals(editContactDataForm.getContact3relation()) ? "selected" : ""%> value="COWORKER">
							<%=ApplicationResources.getMessage("relation_COWORKER")%></option>
					</html:select>
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact3relation.err")%>
				</fieldset>
				<fieldset>
					<label>Teléfono</label>
					<html:text name="EditContactDataForm" property="contact3phone" />
					<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact3phone.err")%>
				</fieldset>
			</div>
		</div>
		<fieldset class="button_bar pOnlyTop25">
			<button type="submit" id="submitregister" class="botton_ahead">Guardar<span></span></button>
		</fieldset>
	</html:form>
</section>
<%@ include file="includes/copyright.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

</body>
</html>