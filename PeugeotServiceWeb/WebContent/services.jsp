<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.ljpeugeot.model.Service"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.prevent.ServicesForm"%>
<%@page import="com.tdil.ljpeugeot.model.Dealer"%>
<%@page import="com.tdil.ljpeugeot.model.City"%>
<%@page import="com.tdil.ljpeugeot.model.State"%>
<%@page import="com.tdil.ljpeugeot.struts.forms.prevent.EditVehicleDataForm"%>
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
	<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_index_modales.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_unified_mobile.css" rel="stylesheet" media="screen" />
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

</head>
<body>

<% ServicesForm servicesForm = (ServicesForm)session.getAttribute("ServicesForm");%>
<% for (Service service : servicesForm.getServices()) { %>
	<%=service.getKm()%> - <%=DateUtils.formatDateSp(service.getServicedate())%><br> 
<% } %>

<html:form method="POST" action="/saveService">
	<div class="scrollable">
		<fieldset>
			<label>km</label>
			<html:text name="ServicesForm" property="km" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "ServicesForm.km.err")%>
		</fieldset>
		<fieldset>
			<label>fecha</label>
			<html:text name="ServicesForm" property="date" />
			<div class="errorInForm"><%=com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter.getErrorFrom(request, "ServicesForm.date.err")%>
		</fieldset>
	</div>
	<fieldset>
		<input type="submit" id="submitregister" value="Guardar" class="buttonSend">
	</fieldset>
</html:form>

</body>
</html>