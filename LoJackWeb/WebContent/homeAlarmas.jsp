<%@page import="com.tdil.lojack.utils.AsyncJobUtils"%>
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
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<script src="js/bootstrap.min.js"></script>

<%@ include file="includes/headLogged.jsp" %>
<script src="js/alarms.js"></script>
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
<link href="css/index_modales.css" rel="stylesheet"  type="text/css"/>
<link href="css/index_social.css" rel="stylesheet"  type="text/css"/>
<link href="css/copyright.css" rel="stylesheet"  type="text/css"/>

<script>
  $(function() {

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

	function toggle(idEntidad) {
		if ($('#cont-' + idEntidad).css('display') == 'none') {
			$('#cont-' + idEntidad).css('display', 'block');
			$('#toggle-' + idEntidad).prop('innerHTML',"-");
		} else {
			$('#cont-' + idEntidad).css('display', 'none');
			$('#toggle-' + idEntidad).prop('innerHTML',"+");
		}
	}

  function seeAlarmLog(idEntidad) {
	  $('#logData').load('logAlarma.jsp?idEntidad=' + idEntidad, function() {
		  centerLayer($(window), $( "#logLayer" ));
		});
  }

  function sendPanic() {
	  $('#sendPanic').load('sendPanic.jsp', function() {
		  centerLayer($(window), $( "#sendPanicLayer" ));
		});
  }

  function doSendPanic(alarmDesc, idEntidad) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./sendPanic.do",
          data: {idEntidad: idEntidad},
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
					$('#retryPanic').attr('onclick', 'doSendPanic("'+alarmDesc+ '",idEntidad)');
					centerLayer($(window), $( "#sendPanicErrorLayer" ));
				}
          },
          error: function() {
        	  $( "#sendPanicLayer" ).fadeOut();
        	  $( "#sendPanicErrorLayer" ).fadeOut();
			  $('#retryPanic').attr('value', 'Reintentar ' + alarmDesc)
			  $('#retryPanic').attr('onclick', 'doSendPanic("'+alarmDesc+ '",idEntidad)');
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

  function doActivate(idEntidad) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./activateAlarm.do",
          data: {idEntidad: idEntidad , password:  $('#password').attr('value')},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
        		  $( "#passwordLayer" ).fadeOut();
				  centerLayer($(window), $( "#alarmActivatedLayer" ));
				  $( "#alarm-job-" +idEntidad ).prop('innerHTML', '*');
				} else {
					if (data.result == 'ERR_PASS') {
	        		  $( "#passwordLayer" ).fadeOut();
					  centerLayer($(window), $( "#invalidPasswordLayer" ));
					} else {
						if (data.result == 'HAS_JOB') {
		        		  $( "#passwordLayer" ).fadeOut();
						  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
						} else {
							$( "#passwordLayer" ).fadeOut();
							centerLayer($(window), $( "#alarmNotActivatedLayer" ));
						}
					}
				}
          },
          error: function() {
        	  $( "#passwordLayer" ).fadeOut();
			  centerLayer($(window), $( "#alarmNotActivatedLayer" ));
          }
      });
  }

  function doDeactivate(idEntidad) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./deactivateAlarm.do",
          data: {idEntidad: idEntidad , password:  $('#password').attr('value')},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
        		  $( "#passwordLayer" ).fadeOut();
				  centerLayer($(window), $( "#alarmDeactivatedLayer" ));
				  $( "#alarm-job-" +idEntidad ).prop('innerHTML', '*');
				} else {
					if (data.result == 'ERR_PASS') {
	        		  $( "#passwordLayer" ).fadeOut();
					  centerLayer($(window), $( "#invalidPasswordLayer" ));
					} else {
						if (data.result == 'HAS_JOB') {
		        		  $( "#passwordLayer" ).fadeOut();
						  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
						} else {
							$( "#passwordLayer" ).fadeOut();
							centerLayer($(window), $( "#alarmNotDeactivatedLayer" ));
						}
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

<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>

<section id="content">
	<div class="pageWrapper">
		<div class="col1_170">
			<div class="tab"><img src="images/skin_lj_rl/tabs/servicion.png"></div>
			<ul class="tabServices">
				<li class="tabAlarms active" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div class="col1_794 alarmasBG">
			<% AlarmsForm alarmsForm = (AlarmsForm)session.getAttribute("AlarmsForm"); %>
			<div id="accordion">
			<% for (Alarm alarm : alarmsForm.getAlarms()) { %>
					<div class="titleContainer">
						<button id="toggle-<%=alarm.getIdEntidad()%>" onclick="javascript:toggle('<%=alarm.getIdEntidad()%>')">+</button>
							<div id="<%=alarm.getIdEntidad()%>" class="editable"><%= alarm.getDescription() %></div>
						<!-- necesito poder meterle un class distinto a cada status -->
				  			<% if (alarm.isTriggered()) { %>
				  				<div id="alarm-status-<%=alarm.getIdEntidad()%>"><%=alarm.getStatus()%></div>
				  			<% } else { %>
				  				<% if (alarm.isActive()) { %>
					  				<div id="alarm-status-<%=alarm.getIdEntidad()%>"><%=alarm.getStatus()%></div>
					  			<% } else { %>
					  				<div id="alarm-status-<%=alarm.getIdEntidad()%>"><%=alarm.getStatus()%></div>
					  			<% } %>
				  			<% } %>
				  			<% if (AsyncJobUtils.hasJobInProgress(alarm, websiteUser)) { %>
				  				<div id="alarm-job-<%=alarm.getIdEntidad()%>">*</div>
				  			<% } else { %>
				  				<div id="alarm-job-<%=alarm.getIdEntidad()%>"></div>
				  			<% } %>
					</div>
				  	<div class="switchContainer">
					  	<% if (alarm.isInactive() ) { %>
					  		<span onclick="activateAlarm(<%=alarm.getIdEntidad()%>)">Encender</span>
					  	<% } else { %>
					  		<span onclick="deactivateAlarm(<%=alarm.getIdEntidad()%>)">Apagar</span>
					  	<% } %>
				  	</div>
			  <div id="cont-<%=alarm.getIdEntidad()%>" style="display: none;">
				    <p>
				   		<% if (alarm.hasChangeData()) { %>
				   			Ultimo cambio: <%=alarm.getLastChangeDate() %>
				   			- <%=alarm.getLastChangeAction() %> - <%=alarm.getLastChangeUser() %> <br>
				   			<a href="javascript:seeAlarmLog(<%= alarm.getIdEntidad() %>)">Ver log completo</a><br>
				   			<input type="checkbox" onchange="toggleEmailNotification(this, <%=alarm.getIdEntidad()%>)" <%= alarm.isEmailnotification() ? "checked" : ""%>>Envio de notificaciones por email<br>
				   			<a href="./goToHomeAlarmAgenda.do?idEntidad=<%=alarm.getIdEntidad()%>">Configurar horarios</a> de Armado/Desarmado<br>
				   		<% } %>
				    </p>
				  </div>
			   </div>
			  <% } %>
			</div>
			<div id="logLayer" style="display: none; z-index: 1500;">
				<div id="logData">
					Consultando datos...
				</div>
				<input type="button" id="closeLogLayer" cl="logLayer" value="Cerrar">
			</div>

			<div id="confAlertLayer" style="display: none; z-index: 1500;">
				<div id="confAlert">
					Consultando datos...
				</div>
			</div>
			<div id="confSavedLayer" style="display: none; z-index: 1500;">
				La configuracion ha sido salvada
				<input type="button" id="closeSavedConfLayer" cl="confSavedLayer" value="Cerrar">
			</div>

			<%@ include file="includes/passwordLayer.jspf" %>

			<div id="alarmActivatedLayer" style="display: none; z-index: 1500;">
				Se ha enviado el comando de activacion la alarma
				<input type="button" id="closeAlarmActivatedLayer" cl="alarmActivatedLayer" value="Cerrar">
			</div>
			<div id="alarmNotActivatedLayer" style="display: none; z-index: 1500;">
				No ha podido activarse la alarma
				<input type="button" id="closeAlarmNotActivatedLayer" cl="alarmNotActivatedLayer" value="Cerrar">
			</div>
			<div id="invalidPasswordLayer" style="display: none; z-index: 1500;">
				La contrasenia no es correcta
				<input type="button" id="closeinvalidPasswordLayer" cl="invalidPasswordLayer" value="Cerrar">
			</div>
			<div id="alarmDeactivatedLayer" style="display: none; z-index: 1500;">
				Se ha enviado el comando de desactivacion la alarma
				<input type="button" id="closeAlarmDeactivatedLayer" cl="alarmDeactivatedLayer" value="Cerrar">
			</div>
			<div id="alarmNotDeactivatedLayer" style="display: none; z-index: 1500;">
				No ha podido desactivarse la alarma
				<input type="button" id="closeAlarmNotDeactivatedLayer" cl="alarmNotDeactivatedLayer" value="Cerrar">
			</div>
			<div id="jobInProgressErrorLayer" style="display: none; z-index: 500;">
				La alarma esta procesando una tarea, por favor espere.
				<input type="button" id="closejobInProgressErrorLayer" cl="jobInProgressErrorLayer" value="Cerrar">
			</div>

			<!-- Inicio panic -->
			<div id="sendPanicLayer" style="display: none; z-index: 1500;">
				<div id="sendPanic">
					Consultando datos...
				</div>
				<input type="button" id="closePanicLayer" cl="sendPanicLayer" value="Cerrar">
			</div>
			<div id="panicSentLayer" style="display: none; z-index: 1500;">
				Se ha enviado el comando de senial de panico
				<input type="button" id="closePanicSentLayer" cl="panicSentLayer" value="Cerrar">
			</div>
			<div id="sendPanicErrorLayer" style="display: none; z-index: 1500;">
				<div id="sendPanicError">
					Ha occurrido un error enviando la senial de panico
				</div>
				<input type="button" id="retryPanic" value="Reintentar">
				<input type="button" id="closeSendPanicErrorLayer" cl="sendPanicErrorLayer" value="Cerrar">
			</div>
			<!-- Fin panic -->
		</div>
	</div>
</section>
<!-- a href="javascript:sendPanic()">Boton de panico</a -->

<%@ include file="includes/footerProductoHome.jsp" %>
</body>
</html>