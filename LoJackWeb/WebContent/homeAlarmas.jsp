<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.AsyncJobUtils"%><%--
--%><%@page import="com.tdil.lojack.gis.model.Alarm"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.AlarmsForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%@ include file="includes/mustBeHomeUser.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<% if (usingMobile || isAndroid) { %>
	<link href="css/index_modales.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/bootstrapSwitch.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/unified_mobile.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/homeProduct_mobile.css" rel="stylesheet" type="text/css" media="screen" />
<% } else { %>
	<link href="css/reset-styles.css" rel="stylesheet" media="screen" />
	<link href="css/sizers.css" rel="stylesheet" media="screen" />
	<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" />
	<link href="css/index_modales.css" rel="stylesheet"  type="text/css" />
	<link href="css/index_social.css" rel="stylesheet"  type="text/css" />
	<link href="css/copyright.css" rel="stylesheet"  type="text/css" />
	<link href="css/bootstrapSwitch.css" rel="stylesheet" />
	<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
<% } %>
<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
textarea {
	width: 200px;
	float:left;
}
</style>
<%@ include file="includes/headLogged.jsp" %>
<script src="js/alarms.js"></script>
<script src="js/jQueryRotateCompressed.2.2.js"></script>

<!-- Para los switches -->
<script src="js/bootstrapSwitch.js"></script>
<!-- Fin Switches -->
<% AlarmsForm alarmsForm = (AlarmsForm)session.getAttribute("AlarmsForm"); %>
<script>
  <%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
  $(function() {

	  $( "#closePasswordLayerButton" ).click(function() {
			$( "#passwordLayer" ).fadeOut();
			if (activating) {
				uncheckbgVar();
			} else {
				checkbgVar();
			}
		});
	  
	  <%@ include file="includes/closeLayers.jspf" %>
	  <%@ include file="includes/externalLogins.jspf" %>

	  $( "#closeforgotPasswordLayer" ).click(function() {
			$( "#forgotPasswordLayer" ).fadeOut();
		});

  $('.editable').editable(function(value, settings) {
	     return doRenameAlarm($(this).attr('id'), value);
	  }, {
	     type    : 'textarea',
	     submit  : 'OK',
	 });
  });

  function doRenameAlarm(idEntidad, alarmDesc) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./renameAlarm.do",
          data: {idEntidad: idEntidad, description: alarmDesc},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  if (data.result == 'OK') {
				} else {
					 $('#'+alarmId).prop('innerHTML', 'Error');
				}
          },
          error: function() {
        	  <%@ include file="includes/unblockUI.jspf" %>
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
	  <%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./activateAlarmEmailNotification.do",
          data: {idEntidad: idEntidad},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  if (data.result != 'OK') {
				alert('Ha occurrido un error ejecutando la accion');
				objCheckbox.checked = false;
			}
          },
          error: function() {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  alert('Ha occurrido un error ejecutando la accion');
        	  objCheckbox.checked = false;
          }
      });
  }

function deactivateEmailNotification(objCheckbox, idEntidad) {
	<%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./deactivateAlarmEmailNotification.do",
          data: {idEntidad: idEntidad},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  if (data.result != 'OK') {
				alert('Ha occurrido un error ejecutando la accion');
				objCheckbox.checked = true;
			}
          },
          error: function() {
        	  <%@ include file="includes/unblockUI.jspf" %>
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
	  <%@ include file="includes/blockUI.jspf" %>
	  $('#logData').load('logAlarma.jsp?idEntidad=' + idEntidad, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		  centerLayer($(window), $( "#logLayer" ));
		  centerLayer($(window), $( "#centradorModalesLog" ));
		}
	  });
  }

  <%@ include file="includes/panicJS.jspf" %>

  function confAlarmAlert(alarmId) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $('#confAlert').load('goToHomeAlarmAlertConf.do?alarmId=' + alarmId, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		  centerLayer($(window), $( "#confAlertLayer" ));
		}
	  });
  }

  var bgUpdate = false;
  var activating;
  var idEntidad;
  
  function toggleAlarm(objCheckbox, pidEntidad) {
	  if (!bgUpdate) {
		if (objCheckbox.checked) {
			activating = true;
			idEntidad = pidEntidad;
			activateAlarm(pidEntidad);
		} else {
			activating = false;
			idEntidad = pidEntidad;
			deactivateAlarm(pidEntidad);
		}
	  }
  }

  function activateAlarm(alarmId) {
	  $('#password').attr('value','');
	  $('#passwordLayerButton').attr('onclick', 'doActivate("'+alarmId+'")');
	  centerLayer($(window), $( "#passwordLayer" ));
	  centerLayer($(window), $( "#centradorModalesPassword" ));
  }

  function doActivate(idEntidad) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./activateAlarm.do",
          data: {idEntidad: idEntidad , password:  $('#password').attr('value')},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  if (data.result == 'OK') {
        		  $( "#passwordLayer" ).fadeOut();
				  centerLayer($(window), $( "#alarmActivatedLayer" ));
				  centerLayer($(window), $( "#centradorModalesAA" ));
				  $( "#alarm-job-" +idEntidad ).prop('innerHTML', '*');
				} else {
					uncheckbg(idEntidad);
					if (data.result == 'ERR_PASS') {
	        		  $( "#passwordLayer" ).fadeOut();
					  centerLayer($(window), $( "#invalidPasswordLayer" ));
					  centerLayer($(window), $( "#centradorModalesIP" ));
					} else {
						if (data.result == 'HAS_JOB') {
		        		  $( "#passwordLayer" ).fadeOut();
						  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
						  centerLayer($(window), $( "#centradorModalesJIPE" ));
						} else {
							$( "#passwordLayer" ).fadeOut();
							centerLayer($(window), $( "#alarmNotActivatedLayer" ));
							centerLayer($(window), $( "#centradorModalesANA" ));
						}
					}
				}
          },
          error: function() {
        	  uncheckbg(idEntidad);
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  $( "#passwordLayer" ).fadeOut();
			  centerLayer($(window), $( "#alarmNotActivatedLayer" ));
			  centerLayer($(window), $( "#centradorModalesANA" ));
          }
      });
  }

  function checkbgVar(){
	  bgUpdate = true;
	  $('#b-alarm-switch-' + idEntidad).bootstrapSwitch('setState', true);
	  bgUpdate = false;
  }
  function uncheckbgVar(){
	  bgUpdate = true;
	  $('#b-alarm-switch-' + idEntidad).bootstrapSwitch('setState', false);
	  bgUpdate = false;
  }

  function checkbg(idEntidad){
	  bgUpdate = true;
	  $('#b-alarm-switch-' + idEntidad).bootstrapSwitch('setState', true);
	  bgUpdate = false;
  }
  function uncheckbg(idEntidad){
	  bgUpdate = true;
	  $('#b-alarm-switch-' + idEntidad).bootstrapSwitch('setState', false);
	  bgUpdate = false;
  }
  
  function doDeactivate(idEntidad) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./deactivateAlarm.do",
          data: {idEntidad: idEntidad , password:  $('#password').attr('value')},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  if (data.result == 'OK') {
        		  $( "#passwordLayer" ).fadeOut();
				  centerLayer($(window), $( "#alarmDeactivatedLayer" ));
				  centerLayer($(window), $( "#centradorModalesAD" ));
				  $( "#alarm-job-" +idEntidad ).prop('innerHTML', '*');
				} else {
					checkbg(idEntidad);
					if (data.result == 'ERR_PASS') {
	        		  $( "#passwordLayer" ).fadeOut();
					  centerLayer($(window), $( "#invalidPasswordLayer" ));
					  centerLayer($(window), $( "#centradorModalesIP" ));
					} else {
						if (data.result == 'HAS_JOB') {
		        		  $( "#passwordLayer" ).fadeOut();
						  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
						  centerLayer($(window), $( "#centradorModalesJIPE" ));
						} else {
							$( "#passwordLayer" ).fadeOut();
							centerLayer($(window), $( "#alarmNotDeactivatedLayer" ));
							centerLayer($(window), $( "#centradorModalesAND" ));
						}
					}
				}
          },
          error: function() {
        	  checkbg(idEntidad);
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  $( "#passwordLayer" ).fadeOut();
			  centerLayer($(window), $( "#alarmNotDeactivatedLayer" ));
			  centerLayer($(window), $( "#centradorModalesAND" ));
          }
      });
  }

  function deactivateAlarm(alarmId) {
	  $('#password').attr('value','');
	  $('#passwordLayerButton').attr('onclick', 'doDeactivate("'+alarmId+'")');
	  centerLayer($(window), $( "#passwordLayer" ));
	  centerLayer($(window), $( "#centradorModalesPassword" ));
  }

  <%@ include file="includes/errorAjaxJS.jspf" %>
  <%@ include file="includes/centerLayerJS.jspf" %>

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
		<div id="productHomeMenu" class="col1_170">
			<div id="tab"></div>
			<ul class="tabServices">
				<li class="tabAlarms active" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div id="productHomeContent" class="col1_798 alarmasBG">
			<% for (Alarm alarm : alarmsForm.getAlarms()) { %>
				<div id="accordion">
					<div class="portaToggle"><img src="images/skin_lj_rl/buttons/toggle_arrow.png" id="toggle-<%=alarm.getIdEntidad()%>" onclick="javascript:toggle('<%=alarm.getIdEntidad()%>')"></div>
					<div class="titleContainer">
						<div class="portaTitleAndSwitch">
							<div id="<%=alarm.getIdEntidad()%>" class="editable"><%= alarm.getDescription() %></div>
							<div class="switchContainer">
								<div id="b-alarm-switch-<%=alarm.getIdEntidad()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="Armar" data-off-label="Desarmar">
									<input type="checkbox" id="alarm-switch-<%=alarm.getIdEntidad()%>" onchange="javascript:toggleAlarm(this, <%=alarm.getIdEntidad()%>)" <%=(AsyncJobUtils.displayInactive(alarm, websiteUser) ? "" : "checked=\"true\"") %>>
								</div>
							  	<% if (alarm.isInactive() ) { %>
							  		<span class="" onclick="activateAlarm(<%=alarm.getIdEntidad()%>)"></span>
							  	<% } else { %>
							  		<span class="" onclick="deactivateAlarm(<%=alarm.getIdEntidad()%>)"></span>
							  	<% } %>
							</div>
				  			<% if (alarm.isTriggered()) { %>
				  				<div id="alarm-status-<%=alarm.getIdEntidad()%>" class="alarm-status-<%=alarm.getStatus()%>"><%=alarm.getStatus()%></div>
				  			<% } else { %>
				  				<% if (alarm.isActive()) { %>
					  				<div id="alarm-status-<%=alarm.getIdEntidad()%>" class="alarm-status-<%=alarm.getStatus()%>"><%=alarm.getStatus()%></div>
					  			<% } else { %>
					  				<div id="alarm-status-<%=alarm.getIdEntidad()%>" class="alarm-status-<%=alarm.getStatus()%>"><%=alarm.getStatus()%></div>
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
</section>
<%@ include file="includes/panicButton.jspf" %>
<%@ include file="includes/footerProductoHome.jsp" %>

<div id="logLayer" class="layerOnTop" style="display: none; z-index: 1500; top:0; left:0;">
	<div id="centradorModalesLog" class="defaultLayerStyles">
		<div class="modalStyle logLayerStyle">
			<div id="xContainer"><button class="buttonLink" id="closeLogLayer" cl="logLayer">X</button></div>
			<h3>Log de cambios</h3>
			<div id="logData">
				Cargando datos...
			</div>
		</div>
	</div>
</div>

<div id="confAlertLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div  class="defaultLayerStyles">
		<div class="modalStyle">
			<h3>Atención</h3>
			<div id="confAlert">
				Consultando datos...
			</div>
		</div>
	</div>
</div>

<div id="confSavedLayer" class="layerOnTop" style="display: none; z-index: 1500; top:0; left:0;">
	<div id="centradorModalesConfSaved" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeSavedConfLayer" cl="confSavedLayer">X</button></div>
			<h3>Atención</h3>
			<div>
				<div class="alert alert-success">La configuracion ha sido salvada.</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="includes/passwordLayer.jspf" %>

<div id="alarmActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesAA" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeAlarmActivatedLayer" cl="alarmActivatedLayer">X</button></div>
			<h3>Atención</h3>
			<div class="modalLayerContent">
				<div class="alert alert-success">Se ha enviado el comando de activacion la alarma.</div>
			</div>
		</div>
	</div>
</div>

<div id="alarmNotActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesANA" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeAlarmNotActivatedLayer" cl="alarmNotActivatedLayer">X</button></div>
			<h3>Atención</h3>
			<div>
				<div class="alert alert-error">No ha podido activarse la alarma.</div>
			</div>
		</div>
	</div>
</div>

<div id="invalidPasswordLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesIP" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeinvalidPasswordLayer" cl="invalidPasswordLayer">X</button></div>
			<h3>Atención</h3>
			<div>
				<div class="alert alert-error">La contraseña no es correcta.</div>
			</div>
		</div>
	</div>
</div>

<div id="alarmDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesAD" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeAlarmDeactivatedLayer" cl="alarmDeactivatedLayer">X</button></div>
			<h3>Atención</h3>
			<div>
				<div class="alert alert-success">Se ha enviado el comando de desactivación la alarma.</div>
			</div>
		</div>
	</div>
</div>

<div id="alarmNotDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesAND" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeAlarmNotDeactivatedLayer" cl="alarmNotDeactivatedLayer">X</button></div>
			<h3>Atención</h3>
			<div>
				<div class="alert alert-error">No ha podido desactivarse la alarma.</div>
			</div>
		</div>
	</div>
</div>

<div id="jobInProgressErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesJIPE" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closejobInProgressErrorLayer" cl="jobInProgressErrorLayer">X</button></div>
			<h3>Atención</h3>
			<div>
				<div class="alert alert-block">La alarma esta procesando una tarea, por favor espere.</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="includes/panicLayers.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<!-- Fin panic -->

<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
<%@ include file="includes/version.jspf" %></body>
</html>
