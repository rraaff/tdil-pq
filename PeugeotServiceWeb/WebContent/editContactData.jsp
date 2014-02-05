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
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style type="text/css">
	.smallmap { width:968px; height:450px; }
	#tags { display: none; }
	#docs p { font-size:12px; margin-bottom:0.5em; }
	#placaLoader { display:none; }
@media only screen and (orientation: landscape) and (max-width: 600px) {
	#shortdesc { float:right; width:25%; }
	#map { width:100%; height:100%; }
	#docs { font-size:12px; }
}
</style>
<%
	Boolean apk = (Boolean)session.getAttribute("USING_APK");
if (apk != null && apk) {
	isAndroid = true;
}
%>
<%
	if (usingMobile || isAndroid) {
%>
	<link type="text/css" href="css/index_modales.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/unified_mobile.css" rel="stylesheet" media="screen" />
	<style type="text/css">
		
		@media only screen and (orientation: landscape) and (max-width: 600px) {
			#shortdesc {
		    	float:right;
		    	width:25%;
		    }
			#map {
				width:100%;
				height:100%;
			}
			#docs {
				font-size:12px;
			}
		}
		#docs p {
			font-size:12px;
		    margin-bottom:0.5em;
		}
		#tags { display: none; }
		
		@media all and (orientation:landscape) {
			#productsMenu { position:fixed; z-index:1500; } 
		}
		
		@media all and (orientation:landscape) and (max-height:600px) {
			#productsMenu ul li.logoContainer { display:none; }
		}
		body { overflow:hidden; }
	</style>
<%
	}
%>
<style type="text/css">
#productsMenu ul li.tabCar {
	background:#f05224;
}
</style>
<%@ include file="includes/headLogged.jsp" %>

<html:form method="POST" action="/saveContactData">
<% EditContactDataForm editContactDataForm = (EditContactDataForm)session.getAttribute("EditContactDataForm");%>
	<div class="scrollable">
		<fieldset>
			<label>1 Nombre</label>
			<html:text name="EditContactDataForm" property="contact1name" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1name.err")%>
		</fieldset>
		<fieldset>
			<label>1 relacion</label>
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
			<label>1 phone</label>
			<html:text name="EditContactDataForm" property="contact1phone" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1phone.err")%>
		</fieldset>
		<fieldset>
			<label>contact1secword</label>
			<html:text name="EditContactDataForm" property="contact1secword" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1secword.err")%>
		</fieldset>
		<fieldset>
			<label>contact1healthi</label>
			<html:text name="EditContactDataForm" property="contact1healthi" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact1healthi.err")%>
		</fieldset>
		<fieldset>
			<label>contact2name</label>
			<html:text name="EditContactDataForm" property="contact2name" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact2name.err")%>
		</fieldset>
		<fieldset>
			<label>2 relacion</label>
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
			<label>contact2phone</label>
			<html:text name="EditContactDataForm" property="contact2phone" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact2phone.err")%>
		</fieldset>
		
		<fieldset>
			<label>contact3name</label>
			<html:text name="EditContactDataForm" property="contact3name" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact3name.err")%>
		</fieldset>
		<fieldset>
			<label>3 relacion</label>
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
			<label>contact3phone</label>
			<html:text name="EditContactDataForm" property="contact3phone" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "EditContactDataForm.contact3phone.err")%>
		</fieldset>
	</div>
	<fieldset>
		<input type="submit" id="submitregister" value="Guardar" class="buttonSend">
	</fieldset>
</html:form>

</body>
</html>