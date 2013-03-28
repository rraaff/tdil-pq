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

    $( "#closeLogLayer" ).click(function() {
		$( "#logLayer" ).fadeOut();
	});

    $( "#closeSavedConfLayer" ).click(function() {
		$( "#confSavedLayer" ).fadeOut();
	});
  });
  
  function seeAlarmLog(alarmId) {
	  $('#logData').load('logAlarma.jsp?alarmId=' + alarmId, function() {
		  centerLayer($(window), $( "#logLayer" ));
		});
  }

  function confAlarmAlert(alarmId) {
	  $('#confAlert').load('goToHomeAlarmAlertConf.do?alarmId=' + alarmId, function() {
		  centerLayer($(window), $( "#confAlertLayer" ));
		});
  }

  function centerLayer(objWin, objLayer) {
		var top = (objWin.height() / 2) - (objLayer.height() / 2);
		var left = (objWin.width() / 2) - (objLayer.width() / 2);
		objLayer.css({
			position: 'absolute',
			top: top + 'px',
			left: left + 'px'
		}).fadeIn(500);
	}

	function append(st) {
		$('#password').attr('value', $('#password').attr('value') + st);
	}
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
  <h3><%= alarm.getDescription() %> <%=alarm.isOn() ? "Encendida" : "Apagada"%>
  	<% if (alarm.isOn()) { %>
  		<span onclick="alert(1)">Apagar</span>
  	<% } else { %>
  		<span onclick="alert(2)">Encender</span>
  	<% } %>
  </h3>
  <div>
    <p>
   		<% if (alarm.hasChangeData()) { %>
   			Ultimo cambio: <%=alarm.getLastChangeDate() %> - <%=alarm.getLastChangeHour() %>
   			- <%=alarm.getLastChangeAction() %> - <%=alarm.getLastChangeUser() %> <br>
   			<a href="javascript:seeAlarmLog('<%= alarm.getId() %>')">Ver log completo</a><br>
   			Envio de <a href="javascript:confAlarmAlert('<%= alarm.getId() %>')">Alertas por Email</a><br>
   			<a href="./goToHomeAlarmAgenda.do?alarmId=<%=alarm.getId()%>">Configurar horarios</a> de Armado/Desarmado<br>
   		<% } %>
    </p>
  </div>
  <% } %>
</div>
<div id="logLayer" style="display: none; z-index: 500;">
	<div id="logData">
		Consultando datos...
	</div>
	<input type="button" id="closeLogLayer" value="Cerrar">
</div>

<div id="confAlertLayer" style="display: none; z-index: 500;">
	<div id="confAlert">
		Consultando datos...
	</div>
</div>
<div id="confSavedLayer" style="display: none; z-index: 500;">
	La configuracion ha sido salvada
	<input type="button" id="closeSavedConfLayer" value="Cerrar">
</div>

<div id="passwordLayer" style="display: none; z-index: 500;">
	<input type="password" id="password">
	<input type="button" onclick="append('1')" value="1">
	<input type="button" onclick="append('1')" value="2">
	<input type="button" onclick="append('1')" value="3">
	<input type="button" onclick="append('1')" value="4">
	<input type="button" onclick="append('1')" value="5">
	<input type="button" onclick="append('1')" value="6">
	<input type="button" onclick="append('1')" value="7">
	<input type="button" onclick="append('1')" value="8">
	<input type="button" onclick="append('1')" value="9">
	<input type="button" onclick="append('1')" value="*">
	<input type="button" onclick="append('1')" value="0">
	<input type="button" onclick="append('1')" value="#">
	<input type="button" onclick="" value="Confirmar">
</div>
</body>
</html>