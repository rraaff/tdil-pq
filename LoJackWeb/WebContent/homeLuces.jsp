<%@page import="com.tdil.lojack.utils.AsyncJobUtils"%>
<%@page import="com.tdil.lojack.gis.model.Light"%>
<%@page import="com.tdil.lojack.struts.forms.LightsForm"%>
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
<script src="js/lights.js"></script>
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
		     return doRenameLight($(this).attr('id'), value);
		  }, {
		     type    : 'textarea',
		     submit  : 'OK',
		 });
  });

  function doRenameLight(idEntidadIdLuz, lightDesc) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./renameLight.do",
          data: {idEntidadIdLuz: idEntidadIdLuz, description: lightDesc},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  if (data.result == 'OK') {
				} else {
					 $('#'+lightId).prop('innerHTML', 'Error');
				}
          },
          error: function() {
        	  $('#'+lightId).prop('innerHTML', 'Error');
          }
      });
      return lightDesc;
  }

  function toggleEmailNotification(objCheckbox, idEntidad, idLuz) {
		if (objCheckbox.checked) {
			activateEmailNotification(objCheckbox, idEntidad, idLuz);
		} else {
			deactivateEmailNotification(objCheckbox, idEntidad, idLuz);
		}
	}

	function activateEmailNotification(objCheckbox, idEntidad, idLuz) {
		  $.ajax({
	        type: "GET",
	        cache: false,
	        url: "./activateLightEmailNotification.do",
	        data: {idEntidad: idEntidad, idLuz: idLuz},
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

	function deactivateEmailNotification(objCheckbox, idEntidad, idLuz) {
		  $.ajax({
	        type: "GET",
	        cache: false,
	        url: "./deactivateLightEmailNotification.do",
	        data: {idEntidad: idEntidad, idLuz: idLuz},
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

  function seeLightLog(idEntidad, idLuz) {
	  $('#logData').load('logLuz.jsp?idEntidad=' + idEntidad + '&idLuz=' + idLuz, function() {
		  centerLayer($(window), $( "#logLayer" ));
		});
  }

  function confLightAlert(lightId) {
	  $('#confAlert').load('goToHomeLightAlertConf.do?lightId=' + lightId, function() {
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
	function toggleRandomSequence(objCheckbox, lightId) {
		if (objCheckbox.checked) {
			activateRandom(objCheckbox, lightId);
		} else {
			deactivateRandom(objCheckbox, lightId);
		}
	}

	function turnOnLight(idEntidad, idLuz) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./turnOnLight.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
				  } else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#lightTurnedOnLayer" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
						} else {
							centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
						}
					}
	          },
	          error: function() {
	        	  centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
	          }
	      });
	  }

	function turnOffLight(idEntidad, idLuz) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./turnOffLight.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
					} else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#lightTurnedOffLayer" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
						} else {
							centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
						}
					}
	          },
	          error: function() {
	        	  centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
	          }
	      });
	  }

	function activateRandomSequence(idEntidad, idLuz) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./activateLightRandomSequence.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
					} else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#randomActivatedLayer" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
						} else {
							centerLayer($(window), $( "#randomNotActivatedLayer" ));
						}
					}
	          },
	          error: function() {
	        	  centerLayer($(window), $( "#randomNotActivatedLayer" ));
	          }
	      });
	  }

	function deactivateRandomSequence(idEntidad, idLuz) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./deactivateLightRandomSequence.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
					} else {
		        	  if (data.result == 'OK') {
		        		  centerLayer($(window), $( "#randomDeactivatedLayer" ));
		        		  $( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
						} else {
							centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
						}
					}
	          },
	          error: function() {
	        	  centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
	          }
	      });
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
				<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights active" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			<ul>
		</div>
		<div class="col1_794 lucesBG">
			<% LightsForm lightsForm = (LightsForm)session.getAttribute("LightsForm"); %>
			<div id="accordion">
			<% for (Light light : lightsForm.getLights()) { %>
			  <h3>
			  	<button id="toggle-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="javascript:toggle('<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>')">+</button>
			  	<div id="<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="editable"><%= light.getDescription() %></div>
			  	<% if (light.isInRandomMode()) { %>
			  		<div id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
			  		<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
		  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
		  			<% } else { %>
		  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
		  			<% } %>
			  		<span onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Desactivar modo random</span>
			  	<% } else  { %>
			  		<% if (light.isOn()) { %>
				  		<div id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
				  		<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
			  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
			  			<% } else { %>
			  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
			  			<% } %>
				  		<span onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Apagar</span>
			  		<% } else  { %>
						<% if (light.isOff()) { %>
				  			<div id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
				  			<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
				  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
				  			<% } else { %>
				  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
				  			<% } %>
				  			<span onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Encender</span>
				  			<span onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Activar modo random</span>
			  			<% } else  { %>
			  				<div id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
			  				<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
				  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
				  			<% } else { %>
				  				<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
				  			<% } %>
		  					<span onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Encender</span>
			  				<span onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Apagar</span>
			  				<span onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Activar modo random</span>
					    <% } %>
				  <% } %>
				 <% } %>
			  </h3>
			  <div id="cont-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
			    <p>
			    	<% if (light.hasChangeData()) { %>
			   			Ultimo cambio: <%=light.getLastChangeDate() %>
			   			- <%=light.getLastChangeAction() %> - <%=light.getLastChangeUser() %> <br>
			   			<a href="javascript:seeLightLog(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Ver log completo</a><br>
			   			<input type="checkbox" onchange="toggleEmailNotification(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" <%= light.isEmailnotification() ? "checked" : ""%>>Envio de notificaciones por email<br>
			   			<a href="./goToHomeLightAgenda.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Configurar horarios</a> de Encendido/Apagado<br>
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

			<div id="randomActivatedLayer" style="display: none; z-index: 500;">
				Se ha enviado el comando de activacion de secuencia random
				<input type="button" id="closerandomActivatedLayer" cl="randomActivatedLayer" value="Cerrar">
			</div>
			<div id="randomNotActivatedLayer" style="display: none; z-index: 500;">
				No ha podido enviarse el comando de activacion de secuencia random
				<input type="button" id="closerandomNotActivatedLayer" cl="randomNotActivatedLayer" value="Cerrar">
			</div>

			<div id="randomDeactivatedLayer" style="display: none; z-index: 500;">
				Se ha enviado el comando de desactivacion de secuencia random
				<input type="button" id="closerandomDeactivatedLayer" cl="randomDeactivatedLayer" value="Cerrar">
			</div>
			<div id="randomNotDeactivatedLayer" style="display: none; z-index: 500;">
				No ha podido enviarse el comando de desactivacion de secuencia random
				<input type="button" id="closerandomNotDeactivatedLayer" cl="randomNotDeactivatedLayer" value="Cerrar">
			</div>

			<div id="lightTurnedOnLayer" style="display: none; z-index: 500;">
				Se ha enviado el comando de encendido de la luz
				<input type="button" id="closerlightTurnedOnLayer" cl="lightTurnedOnLayer" value="Cerrar">
			</div>
			<div id="lightNotTurnedOnLayer" style="display: none; z-index: 500;">
				No ha podido enviarse el comando de encendido de la luz
				<input type="button" id="closelightNotTurnedOnLayer" cl="lightNotTurnedOnLayer" value="Cerrar">
			</div>

			<div id="jobInProgressErrorLayer" style="display: none; z-index: 500;">
				La luz esta procesando una tarea, por favor espere.
				<input type="button" id="closejobInProgressErrorLayer" cl="jobInProgressErrorLayer" value="Cerrar">
			</div>

			<div id="lightTurnedOffLayer" style="display: none; z-index: 500;">
				Se ha enviado el comando de apagado de la luz
				<input type="button" id="closerlightTurnedOffLayer" cl="lightTurnedOffLayer" value="Cerrar">
			</div>
			<div id="lightNotTurnedOffLayer" style="display: none; z-index: 500;">
				No ha podido enviarse el comando de apagado de la luz
				<input type="button" id="closelightNotTurnedOffLayer" cl="lightNotTurnedOffLayer" value="Cerrar">
			</div>
		</div>
	</div>
</section>

<%@ include file="includes/footerProductoHome.jsp" %>

</body>
</html>