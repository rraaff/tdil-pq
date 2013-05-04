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
<%@ include file="includes/headLogged.jsp" %>

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

  function doRenameAlarm(idEntidad, alarmDesc) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./renameAlarm.do",
          data: {idEntidad: idEntidad, description: alarmDesc},
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

  function toggleEmailNotification(objCheckbox, idEntidad) {
		if (objCheckbox.checked) {
			activateEmailNotification(objCheckbox, idEntidad);
		} else {
			deactivateEmailNotification(objCheckbox, idEntidad);
		}
	}

  function activateEmailNotification(objCheckbox, idEntidad) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./activateAlarmEmailNotification.do",
          data: {idEntidad: idEntidad},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result != 'OK') {
				alert('Ha occurrido un error ejecutando la accion');
				objCheckbox.checked = false;
			}
          },
          error: function() {
        	  alert('Ha occurrido un error ejecutando la accion');
        	  objCheckbox.checked = false;
          }
      });
  }

function deactivateEmailNotification(objCheckbox, idEntidad) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./deactivateAlarmEmailNotification.do",
          data: {idEntidad: idEntidad},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result != 'OK') {
				alert('Ha occurrido un error ejecutando la accion');
				objCheckbox.checked = true;
			}
          },
          error: function() {
        	  alert('Ha occurrido un error ejecutando la accion');
        	  objCheckbox.checked = true;
          }
      });
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
					if (data.result == 'ERR_PASS') {
	        		  $( "#passwordLayer" ).fadeOut();
					  centerLayer($(window), $( "#invalidPasswordLayer" ));
					} else {
						$( "#passwordLayer" ).fadeOut();
						centerLayer($(window), $( "#alarmNotActivatedLayer" ));
					}
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
					if (data.result == 'ERR_PASS') {
	        		  $( "#passwordLayer" ).fadeOut();
					  centerLayer($(window), $( "#invalidPasswordLayer" ));
					} else {
						$( "#passwordLayer" ).fadeOut();
						centerLayer($(window), $( "#alarmNotDeactivatedLayer" ));
					}
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
<% for (Alarm alarm : alarmsForm.getAlarms()) { %>
  <h3><%= alarm.getDescription() %> <%=alarm.isTriggered() ? "Disparada" : (alarm.isActive() ? "Encendidad" : "Apagada")%>
  	<% if (alarm.isInactive() ) { %>
  		<span onclick="activateAlarm('<%=alarm.getIdEntidad()%>')">Encender</span>
  	<% } else { %>
  		<span onclick="deactivateAlarm('<%=alarm.getIdEntidad()%>')">Apagar</span>
  	<% } %>
  </h3>
  <div>
  	<div id="<%=alarm.getIdEntidad()%>" class="editable"><%= alarm.getDescription() %></div>
    <p>
   		<% if (alarm.hasChangeData()) { %>
   			Ultimo cambio: <%=alarm.getLastChangeDate() %>
   			- <%=alarm.getLastChangeAction() %> - <%=alarm.getLastChangeUser() %> <br>
   			<a href="javascript:seeAlarmLog('<%= alarm.getIdEntidad() %>')">Ver log completo</a><br>
   			<input type="checkbox" onchange="toggleEmailNotification(this, '<%=alarm.getIdEntidad()%>')" <%= alarm.isEmailnotification() ? "checked" : ""%>>Envio de notificaciones por email<br>
   			<a href="./goToHomeAlarmAgenda.do?alarmId=<%=alarm.getIdEntidad()%>">Configurar horarios</a> de Armado/Desarmado<br>
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
<div id="invalidPasswordLayer" style="display: none; z-index: 500;">
	La contrasenia no es correcta
	<input type="button" id="closeinvalidPasswordLayer" cl="invalidPasswordLayer" value="Cerrar">
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