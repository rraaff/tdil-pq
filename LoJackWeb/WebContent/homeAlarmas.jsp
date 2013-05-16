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
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<%@ include file="includes/headLogged.jsp" %>
<script src="js/alarms.js"></script>
<script src="js/jQueryRotateCompressed.2.2.js"></script>
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
<link href="css/index_modales.css" rel="stylesheet"  type="text/css"/>
<link href="css/index_social.css" rel="stylesheet"  type="text/css"/>
<link href="css/copyright.css" rel="stylesheet"  type="text/css"/>

<!-- Para los switches -->
<link rel="stylesheet" href="css/bootstrap-combined.min.css">
<link rel="stylesheet" href="css/bootstrapSwitch.css">
<script src="js/bootstrapSwitch.js"></script>
<script src="js/bootstrapSwitch.min.js"></script>
<!-- Fin Switches -->
<% AlarmsForm alarmsForm = (AlarmsForm)session.getAttribute("AlarmsForm"); %>
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

  function collapseAll(idEntidad) {
	  <% for (Alarm alarm : alarmsForm.getAlarms()) { %>
	  if ('<%=alarm.getIdEntidad()%>' != idEntidad) {
		  if ($('#cont-<%=alarm.getIdEntidad()%>').css('display') == 'block') {
				$('#cont-<%=alarm.getIdEntidad()%>').slideUp();
				$('#toggle-<%=alarm.getIdEntidad()%>').rotate({ animateTo:0});
			}
	  }
	  <% } %>
  } 

	function toggle(idEntidad) {
		collapseAll(idEntidad);
		if ($('#cont-' + idEntidad).css('display') == 'none') {
			$('#cont-' + idEntidad).slideDown();
			$('#toggle-' + idEntidad).rotate({ animateTo:90});
		} else {
			$('#cont-' + idEntidad).slideUp();
			$('#toggle-' + idEntidad).rotate({ animateTo:0});
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

  function toggleAlarm(objCheckbox, idEntidad) {
		if (objCheckbox.checked) {
			$( "#alarm-switch-" +idEntidad ).attr('checked', false);
			activateAlarm(idEntidad);
		} else {
			$( "#alarm-switch-" +idEntidad ).attr('checked', true);
			deactivateAlarm(idEntidad);
		}
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

  function check(){
	  alert(document.getElementById('alarm-switch-1').checked);
	  $( "#alarm-switch-1" ).attr('checked', true);
	  alert(document.getElementById('alarm-switch-1').checked);
  }
  function uncheck(){
	  alert(document.getElementById('alarm-switch-1').checked);
	  $( "#alarm-switch-1" ).attr('checked', false);
	  alert(document.getElementById('alarm-switch-1').checked);
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
<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
textarea {
	width: 200px;
	float:left;
}
</style>
</head>
<body>

<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>

<section id="content">
	<div class="pageWrapper">
		<div class="col1_170">
			<div class="tab"></div>
			<ul class="tabServices">
				<li class="tabAlarms active"><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights"><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div class="col1_794 alarmasBG">
			<button onclick="javascript:check()">Check</button>
			<button onclick="javascript:uncheck()">unCheck</button>
				<% for (Alarm alarm : alarmsForm.getAlarms()) { %>
					<div id="accordion">
						<div class="titleContainer">
							<div class="portaToggle"><img src="images/skin_lj_rl/buttons/toggle_arrow.png" id="toggle-<%=alarm.getIdEntidad()%>" onclick="javascript:toggle('<%=alarm.getIdEntidad()%>')"></div>
							<div class="portaTitleAndSwitch">
								<div id="<%=alarm.getIdEntidad()%>" class="editable"><%= alarm.getDescription() %></div>
								<div class="switchContainer">
									<div class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="Armar" data-off-label="Desarmar">
										<input type="checkbox" id="alarm-switch-<%=alarm.getIdEntidad()%>" onchange="javascript:toggleAlarm(this, <%=alarm.getIdEntidad()%>)" <%=(alarm.isInactive() ? "" : "checked=\"true\"") %>>
									</div>
								  	<% if (alarm.isInactive() ) { %>
								  		<span onclick="activateAlarm(<%=alarm.getIdEntidad()%>)">.</span>
								  	<% } else { %>
								  		<span onclick="deactivateAlarm(<%=alarm.getIdEntidad()%>)">*</span>
								  	<% } %>
							  	</div>

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
						</div>
						<div id="switchBoard">
				  			<div id="cont-<%=alarm.getIdEntidad()%>" style="display: none;">
						   		<% if (alarm.hasChangeData()) { %>
						   			<span class="lastChange">Último cambio: <%=alarm.getLastChangeDate() %></span>
						   			<span class="lastAction"><%=alarm.getLastChangeAction() %> por: <%=alarm.getLastChangeUser() %></span>
						   			<span class="changesLog"><a href="javascript:seeAlarmLog(<%= alarm.getIdEntidad() %>)">Ver log completo</a></span>
						   			<span class="notifyme"><input type="checkbox" onchange="toggleEmailNotification(this, <%=alarm.getIdEntidad()%>)" <%= alarm.isEmailnotification() ? "checked" : ""%>> Quiero que me notifique los cambios de estado por E-Mail</span>
						   			<span class="linkToAgenda"><a href="./goToHomeAlarmAgenda.do?idEntidad=<%=alarm.getIdEntidad()%>">Configurar horarios</a> de Armado/Desarmado</span>
						   		<% } %>
							</div>
						</div>
					</div>
				<% } %>
			</div>
		</div>
	</div>
</section>
<!-- a href="javascript:sendPanic()">Boton de panico</a -->

<%@ include file="includes/footerProductoHome.jsp" %>

<div id="logLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle">
		<div class="modalWrapper">
			<div id="logData" class="modalLayerContent">
				Cargando datos...
			</div>
			<input type="button" id="closeLogLayer" cl="logLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="confAlertLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div id="confAlert">
				Consultando datos...
			</div>
		</div>
	</div>
</div>

<div id="confSavedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">La configuracion ha sido salvada.</div>
			</div>
			<input type="button" id="closeSavedConfLayer" cl="confSavedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<%@ include file="includes/passwordLayer.jspf" %>

<div id="alarmActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div class="modalLayerContent" style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de activacion la alarma.</div>
			</div>
			<input type="button" id="closeAlarmActivatedLayer" cl="alarmActivatedLayer" value="Cerrar" class="indexButtonBase">
		</div>
	</div>
</div>

<div id="alarmNotActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">No ha podido activarse la alarma.</div>
			</div>
			<input type="button" id="closeAlarmNotActivatedLayer" cl="alarmNotActivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="invalidPasswordLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">La contraseña no es correcta.</div>
			</div>
			<input type="button" id="closeinvalidPasswordLayer" cl="invalidPasswordLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="alarmDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de desactivación la alarma.</div>
			</div>
			<input type="button" id="closeAlarmDeactivatedLayer" cl="alarmDeactivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="alarmNotDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">No ha podido desactivarse la alarma.</div>
			</div>
			<input type="button" id="closeAlarmNotDeactivatedLayer" cl="alarmNotDeactivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="jobInProgressErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-block">La alarma esta procesando una tarea, por favor espere.</div>
			</div>
			<input type="button" id="closejobInProgressErrorLayer" cl="jobInProgressErrorLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<!-- Inicio panic -->
<div id="sendPanicLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div id="sendPanic" style="height:auto; padding:20px 0;">
				Consultando datos...
			</div>
			<input type="button" id="closePanicLayer" cl="sendPanicLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="panicSentLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de señal de pánico.</div>
			</div>
			<input type="button" id="closePanicSentLayer" cl="panicSentLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="sendPanicErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div id="sendPanicError" style="height:auto; padding:20px 0;">
				<div class="alert alert-error">Ha occurrido un error enviando la señal de panico.</div>
			</div>
			<input type="button" id="retryPanic" value="Reintentar" class="indexButtonBase"/>
			<input type="button" id="closeSendPanicErrorLayer" cl="sendPanicErrorLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>
<!-- Fin panic -->
</body>
</html>