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

    $("input[cl]").each(function(indice,valor) {
	   $(valor).click(function() {
		   $( "#" + $(this).attr('cl') ).fadeOut();
		});
	});
  
  $('.editable').editable(function(value, settings) { 
	     return doRenameAlarm($(this).attr('id'), value);
	  }, { 
	     type    : 'textarea',
	     submit  : 'OK',
	 });
  });

  function doRenameAlarm(alarmId, alarmDesc) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./renameAlarm.do",
          data: {alarmId: alarmId, description: alarmDesc},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
				} else {
					 $('#'+alarmId).prop('innerHTML', 'Error');	
				}
          },
          error: function() {
        	  $('#'+alarmId).prop('innerHTML', 'Error');	
          }
      });
      return alarmDesc;
  }
  
  function seeAlarmLog(alarmId) {
	  $('#logData').load('logAlarma.jsp?alarmId=' + alarmId, function() {
		  centerLayer($(window), $( "#logLayer" ));
		});
  }

  function sendPanic() {
	  $('#sendPanic').load('sendPanic.jsp', function() {
		  centerLayer($(window), $( "#sendPanicLayer" ));
		});
  }

  function doSendPanic(alarmDesc, alarmId) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./sendPanic.do",
          data: {alarmId: alarmId},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
					$( "#sendPanicLayer" ).fadeOut();
					$( "#sendPanicErrorLayer" ).fadeOut();
					centerLayer($(window), $( "#panicSentLayer" ));
				} else {
					$( "#sendPanicLayer" ).fadeOut();
					$( "#sendPanicErrorLayer" ).fadeOut();
					$('#retryPanic').attr('value', 'Reintentar ' + alarmDesc)
					$('#retryPanic').attr('onclick', 'doSendPanic("'+alarmDesc+ '","'+alarmId+'")');
					centerLayer($(window), $( "#sendPanicErrorLayer" ));
				}
          },
          error: function() {
        	  $( "#sendPanicLayer" ).fadeOut();
        	  $( "#sendPanicErrorLayer" ).fadeOut();
			  $('#retryPanic').attr('value', 'Reintentar ' + alarmDesc)
			  $('#retryPanic').attr('onclick', 'doSendPanic("'+alarmDesc+ '","'+alarmId+'")');
			  centerLayer($(window), $( "#sendPanicErrorLayer" ));
          }
      });
  }

  function confAlarmAlert(alarmId) {
	  $('#confAlert').load('goToHomeAlarmAlertConf.do?alarmId=' + alarmId, function() {
		  centerLayer($(window), $( "#confAlertLayer" ));
		});
  }

  function activateAlarm(alarmId) {
	  $('#password').attr('value','');
	  $('#passwordLayerButton').attr('onclick', 'doActivate("'+alarmId+'")');
	  centerLayer($(window), $( "#passwordLayer" ));
  }

  function doActivate(alarmId) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./activateAlarm.do",
          data: {alarmId: alarmId , password:  $('#password').attr('value')},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
        		  $( "#passwordLayer" ).fadeOut();
				  centerLayer($(window), $( "#alarmActivatedLayer" ));
				} else {
					$( "#passwordLayer" ).fadeOut();
					centerLayer($(window), $( "#alarmNotActivatedLayer" ));
				}
          },
          error: function() {
        	  $( "#passwordLayer" ).fadeOut();
			  centerLayer($(window), $( "#alarmNotActivatedLayer" ));
          }
      });
  }

  function doDeactivate(alarmId) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./deactivateAlarm.do",
          data: {alarmId: alarmId , password:  $('#password').attr('value')},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
        		  $( "#passwordLayer" ).fadeOut();
				  centerLayer($(window), $( "#alarmDeactivatedLayer" ));
				} else {
					$( "#passwordLayer" ).fadeOut();
					centerLayer($(window), $( "#alarmNotDeactivatedLayer" ));
				}
          },
          error: function() {
        	  $( "#passwordLayer" ).fadeOut();
			  centerLayer($(window), $( "#alarmNotDeactivatedLayer" ));
          }
      });
  }
  
  function deactivateAlarm(alarmId) {
	  $('#password').attr('value','');
	  $('#passwordLayerButton').attr('onclick', 'doDeactivate("'+alarmId+'")');
	  centerLayer($(window), $( "#passwordLayer" ));
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
Producto Home | Producto Prevent | Producto Pet | Producto Otro | <a href="javascript:sendPanic()">Boton de panico</a>
<hr> 

Mis Alarmas<br><br>
<% AlarmsForm alarmsForm = (AlarmsForm)session.getAttribute("AlarmsForm"); %>
<div id="accordion">
<% for (Alarm alarm : alarmsForm.getAlarms()) { %>>
  <h3><%= alarm.getDescription() %> <%=alarm.isOn() ? "Encendida" : "Apagada"%>
  	<% if (alarm.isOn()) { %>
  		<span onclick="deactivateAlarm('<%=alarm.getId()%>')">Apagar</span>
  	<% } else { %>
  		<span onclick="activateAlarm('<%=alarm.getId()%>')">Encender</span>
  	<% } %>
  </h3>
  <div>
  	<div id="<%=alarm.getId()%>" class="editable"><%= alarm.getDescription() %></div>
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
	<input type="button" id="closeLogLayer" cl="logLayer" value="Cerrar">
</div>

<div id="confAlertLayer" style="display: none; z-index: 500;">
	<div id="confAlert">
		Consultando datos...
	</div>
</div>
<div id="confSavedLayer" style="display: none; z-index: 500;">
	La configuracion ha sido salvada
	<input type="button" id="closeSavedConfLayer" cl="confSavedLayer" value="Cerrar">
</div>

<%@ include file="includes/passwordLayer.jspf" %>

<div id="alarmActivatedLayer" style="display: none; z-index: 500;">
	Se ha activado la alarma
	<input type="button" id="closeAlarmActivatedLayer" cl="alarmActivatedLayer" value="Cerrar">
</div>
<div id="alarmNotActivatedLayer" style="display: none; z-index: 500;">
	No ha podido activarse la alarma
	<input type="button" id="closeAlarmNotActivatedLayer" cl="alarmNotActivatedLayer" value="Cerrar">
</div>
<div id="alarmDeactivatedLayer" style="display: none; z-index: 500;">
	Se ha desactivado la alarma
	<input type="button" id="closeAlarmDeactivatedLayer" cl="alarmDeactivatedLayer" value="Cerrar">
</div>
<div id="alarmNotDeactivatedLayer" style="display: none; z-index: 500;">
	No ha podido desactivarse la alarma
	<input type="button" id="closeAlarmNotDeactivatedLayer" cl="alarmNotDeactivatedLayer" value="Cerrar">
</div>

<!-- Inicio panic -->
<div id="sendPanicLayer" style="display: none; z-index: 500;">
	<div id="sendPanic">
		Consultando datos...
	</div>
	<input type="button" id="closePanicLayer" cl="sendPanicLayer" value="Cerrar">
</div>
<div id="panicSentLayer" style="display: none; z-index: 500;">
	Se ha enviado la senial de panico
	<input type="button" id="closePanicSentLayer" cl="panicSentLayer" value="Cerrar">
</div>
<div id="sendPanicErrorLayer" style="display: none; z-index: 500;">
	<div id="sendPanicError">
		Ha occurrido un error enviando la senial de panico
	</div>
	<input type="button" id="retryPanic" value="Reintentar">
	<input type="button" id="closeSendPanicErrorLayer" cl="sendPanicErrorLayer" value="Cerrar">
</div>
<!-- Fin panic -->

</body>
</html>