<%@ page info="videoPage"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ include file="../includes/agentInfo.jspf" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/index_menu.css" rel="stylesheet" type="text/css">
<link href="css/videoPages.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="videoLayer">
	<div id="videoWrapper">
		<% if (isIpad || isIphone || isIpod) { %>
			<iframe id="videoIframe" src="<%=com.tdil.lojack.utils.LoJackConfig.getVideohome()%>" frameborder="0" allowscale="false" allowfullscreen></iframe>
		<% } else { %>
			<div id="videoIframe" class="homeSample">
				<p><a href="<%=com.tdil.lojack.utils.LoJackConfig.getMobilevideohome()%>" target="_blank">Su navegador no soporta FLASH.<br/>Ver video en Youtube</a></p>
			</div>
		<% } %>
	</div>
	<div id="footerizer">
		<h3 id="videoTitle">Lo Jack Home</h3>
		<p id="videoDescription">Control� tus alarmas, luces y c�maras. Manten� el control de tu casa.</p>
		<a href="index.jsp" title="Volver">< Volver</a>
	</div>
</div>
<%@ include file="../includes/version.jspf" %></body>
</head>