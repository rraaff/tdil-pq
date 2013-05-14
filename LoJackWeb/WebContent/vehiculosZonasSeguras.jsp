<%@page import="com.tdil.lojack.prevent.model.SecureZone"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SecureZoneSelectionBean"%>
<%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%>
<%@page import="com.tdil.lojack.prevent.model.Vehicle"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBePreventUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/reset-styles" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<%@ include file="includes/headLogged.jsp" %>
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">

<script>
</script>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
Zonas Seguras de los vehiculos<br>
<br>

<html:form method="POST" action="/saveVehiculesSecureZones">
<table>
		<logic:iterate id="selectedSecureZone" name="VehiclesSecureZoneForm" property="secureZones">
			<tr>
				<td><bean:write name="selectedSecureZone" property="vehicle.description" /></td>
				<td><html:select name="selectedSecureZone" property="secureZoneId" indexed="true">
					<option	value="">-</option>
					<% SecureZoneSelectionBean ssb = (SecureZoneSelectionBean)selectedSecureZone;
						SecureZone selected = ssb.getZones().getActiveZone();
						for (SecureZone sl : ssb.getZones().getSecureZones()) { %>	
							<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
							value="<%=sl.getId()%>">
							<%=sl.getDescription()%></option>
					<% } %>
				</html:select></td>
			</tr>
		</logic:iterate>
</table>
				<div class="myRow">
					<div class="myLabel width100per" align="center"><input type="submit" id="submitregister" value="Submit"></div>
				</div>
			</html:form>
</div>
</section>
<footer>
	<div class="pageWrapper">
		<div class="col1_300 marginRight_60">
			<h3>ENTRÁ TRANQUILO<br/>A TU CASA</h3>
			<p>Con escolta Lojack te acompañamos telefónicamente a tu casa cuando entrás.</p>
			<button class="btn btn-mini btn-primary" type="button">Mas info >></button>
		</div>
		<div class="col1_300 marginRight_60">
			<h3>LoJack for<br/>Laptopts</h3>
			<p>Con LoJack for Laptopts sabés que si te roban la computadora, te la encontramos.</p>
			<button class="btn btn-mini btn-primary" type="button">Mas info >></button>
		</div>
		<div class="col_social">
			<ul class="nav nav-pills nav-social">
				<li><a href="#" class="fb"></a></li>
				<li><a href="#" class="tw"></a></li>
				<li><a href="#" class="ln"></a></li>
				<li><a href="#" class="gp"></a></li>
			</ul>
		</div>
	</div>
</footer>
</body>
</html>