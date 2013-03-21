<%@page import="com.tdil.lojack.gis.model.Alarm"%>
<%@page import="com.tdil.lojack.struts.forms.AlarmsForm"%>
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
--><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<script>
  $(function() {
    $( "#accordion" ).accordion();
  });
  </script>
</head>
<body>
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="logout.do">Salir</a>
<hr> 
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr> 
Mis Alarmas<br><br>
<% AlarmsForm alarmsForm = (AlarmsForm)session.getAttribute("AlarmsForm"); %>
<div id="accordion">
<% for (Alarm alarm : alarmsForm.getAlarms()) { %>>
  <h3><%= alarm.getDescription() %> <%=alarm.isOn() ? "Encendida" : "Apagada"%></h3>
  <div>
    <p>
   		<% if (alarm.hasChangeData()) { %>
   			Ultimo cambio: <%=alarm.getLastChangeDate() %> - <%=alarm.getLastChangeHour() %>
   			- <%=alarm.getLastChangeAction() %> - <%=alarm.getLastChangeUser() %> <br>
   			<a href="#">Ver log completo</a><br>
   			Envio de <a href="#">Alertas por Email</a><br>
   			<a href="./goToHomeAlarmAgenda.do?alarmId=<%=alarm.getId()%>">Configurar horarios</a> de Armado/Desarmado<br>
   		<% } %>
    </p>
  </div>
  <% } %>
</div>
</body>
</html>