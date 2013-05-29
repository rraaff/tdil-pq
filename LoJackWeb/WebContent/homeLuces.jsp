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

<script src="js/bootstrap.min.js"></script>

<%@ include file="includes/headLogged.jsp" %>
<script src="js/lights.js"></script>
<script src="js/jQueryRotateCompressed.2.2.js"></script>
<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
<link href="css/index_modales.css" rel="stylesheet"  type="text/css"/>
<link href="css/index_social.css" rel="stylesheet"  type="text/css"/>
<link href="css/copyright.css" rel="stylesheet"  type="text/css"/>

<!-- Para los switches -->
<link rel="stylesheet" href="css/bootstrap-combined.min.css">
<link rel="stylesheet" href="css/bootstrapSwitch.css">
<script src="js/bootstrapSwitch.js"></script>
<!-- Fin Switches -->
<% LightsForm lightsForm = (LightsForm)session.getAttribute("LightsForm"); %>
<script>
	  <%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
  $(function() {
	  <%@ include file="includes/closeLayers.jspf" %>
	  $('.editable').editable(function(value, settings) {
		     return doRenameLight($(this).attr('id'), value);
		  }, {
		     type    : 'textarea',
		     submit  : 'OK',
		 });
  });

  function doRenameLight(idEntidadIdLuz, lightDesc) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./renameLight.do",
          data: {idEntidadIdLuz: idEntidadIdLuz, description: lightDesc},
          contentType: "application/json; charset=utf-8",
          success: function(data) {
        	  <%@ include file="includes/unblockUI.jspf" %>
        	  if (data.result == 'OK') {
				} else {
					 $('#'+lightId).prop('innerHTML', 'Error');
				}
          },
          error: function() {
        	  <%@ include file="includes/unblockUI.jspf" %>
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
		<%@ include file="includes/blockUI.jspf" %>
		  $.ajax({
	        type: "GET",
	        cache: false,
	        url: "./activateLightEmailNotification.do",
	        data: {idEntidad: idEntidad, idLuz: idLuz},
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

	function deactivateEmailNotification(objCheckbox, idEntidad, idLuz) {
		<%@ include file="includes/blockUI.jspf" %>
		  $.ajax({
	        type: "GET",
	        cache: false,
	        url: "./deactivateLightEmailNotification.do",
	        data: {idEntidad: idEntidad, idLuz: idLuz},
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

	function collapseAll(idEntidad, idLuz) {
		  <% for (Light light : lightsForm.getLights()) { %>
		  if ('<%=light.getIdEntidad()%>' != idEntidad || '<%=light.getIdLuz()%>' != idLuz) {
			  if ($('#cont-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>').css('display') == 'block') {
					$('#cont-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>').slideUp();
					$('#toggle-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>').rotate({ animateTo:0});
				}
		  }
		  <% } %>
	  } 

	function toggle(idEntidad, idLuz) {
		collapseAll(idEntidad, idLuz);
		if ($('#cont-' + idEntidad + '-' + idLuz).css('display') == 'none') {
			$('#cont-' + idEntidad + '-' + idLuz).slideDown();
			$('#toggle-' + idEntidad + '-' + idLuz).rotate({ animateTo:90});
		} else {
			$('#cont-' + idEntidad + '-' + idLuz).slideUp();
			$('#toggle-' + idEntidad + '-' + idLuz).rotate({ animateTo:0});
		}
	}

  function seeLightLog(idEntidad, idLuz) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $('#logData').load('logLuz.jsp?idEntidad=' + idEntidad + '&idLuz=' + idLuz, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		  centerLayer($(window), $( "#logLayer" ));
		}
	  });
  }

  function confLightAlert(lightId) {
	  <%@ include file="includes/blockUI.jspf" %>
	  $('#confAlert').load('goToHomeLightAlertConf.do?lightId=' + lightId, function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		  centerLayer($(window), $( "#confAlertLayer" ));
		}
	  });
  }
  <%@ include file="includes/errorAjaxJS.jspf" %>
  <%@ include file="includes/centerLayerJS.jspf" %>
  
	function toggleRandomSequence(objCheckbox, lightId) {
		if (objCheckbox.checked) {
			activateRandom(objCheckbox, lightId);
		} else {
			deactivateRandom(objCheckbox, lightId);
		}
	}

	function setLightStatusOn(idEntidad, idLuz) {
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop('innerHTML', 'ON');

		$( "#turn-off-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-off-" + idEntidad + "-" + idLuz).click(function() {
			turnOffLight(idEntidad, idLuz);
		});
		$( "#turn-off-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>OFF</b>');

		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop('innerHTML', 'MR ON');
	}
	function setLightStatusOff(idEntidad, idLuz) {
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-on-" + idEntidad + "-" + idLuz).click(function() {
			turnOnLight(idEntidad, idLuz);
		});
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>ON</b>');
		
		$( "#turn-off-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-off-" + idEntidad + "-" + idLuz).prop('innerHTML', 'OFF');

		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-ran-" + idEntidad + "-" + idLuz).click(function() {
			activateRandomSequence(idEntidad, idLuz);
		});
		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>MR ON</b>');
	}
	function setLightStatusRanOn(idEntidad, idLuz) {
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop('innerHTML', 'ON');
		
		$( "#turn-off-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-off-" + idEntidad + "-" + idLuz).prop('innerHTML', 'OFF');

		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-ran-" + idEntidad + "-" + idLuz).click(function() {
			deactivateRandomSequence(idEntidad, idLuz);
		});
		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>MR OFF</b>');
	}
	function setLightStatusRanOff(idEntidad, idLuz) {
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-on-" + idEntidad + "-" + idLuz).click(function() {
			turnOnLight(idEntidad, idLuz);
		});
		$( "#turn-on-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>ON</b>');

		$( "#turn-off-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-off-" + idEntidad + "-" + idLuz).click(function() {
			turnOffLight(idEntidad, idLuz);
		});
		$( "#turn-off-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>OFF</b>');

		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop("onclick", null).attr("onclick", null);
		$( "#turn-ran-" + idEntidad + "-" + idLuz).click(function() {
			activateRandomSequence(idEntidad, idLuz);
		});
		$( "#turn-ran-" + idEntidad + "-" + idLuz).prop('innerHTML', '<b>MR ON</b>');
	}

	function turnOnLight(idEntidad, idLuz) {
		//alert('turn on');
		<%@ include file="includes/blockUI.jspf" %>
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./turnOnLight.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
				  } else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#lightTurnedOnLayer" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
							setLightStatusOn(idEntidad, idLuz);
						} else {
							centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
						}
					}
	          },
	          error: function() {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
	          }
	      });
	  }

	function turnOffLight(idEntidad, idLuz) {
		<%@ include file="includes/blockUI.jspf" %>
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./turnOffLight.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
					} else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#lightTurnedOffLayer" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
							setLightStatusOff(idEntidad, idLuz);
						} else {
							centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
						}
					}
	          },
	          error: function() {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
	          }
	      });
	  }

	function activateRandomSequence(idEntidad, idLuz) {
		<%@ include file="includes/blockUI.jspf" %>
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./activateLightRandomSequence.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
					} else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#randomActivatedLayer" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
							setLightStatusRanOn(idEntidad, idLuz);
						} else {
							centerLayer($(window), $( "#randomNotActivatedLayer" ));
						}
					}
	          },
	          error: function() {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#randomNotActivatedLayer" ));
	          }
	      });
	  }

	function deactivateRandomSequence(idEntidad, idLuz) {
		<%@ include file="includes/blockUI.jspf" %>
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./deactivateLightRandomSequence.do",
	          data: {idEntidad: idEntidad, idLuz: idLuz},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  if (data.result == 'HAS_JOB') {
					  centerLayer($(window), $( "#jobInProgressErrorLayer" ));
					} else {
		        	  if (data.result == 'OK') {
		        		  centerLayer($(window), $( "#randomDeactivatedLayer" ));
		        		  $( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
		        		  setLightStatusRanOff(idEntidad, idLuz);
						} else {
							centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
						}
					}
	          },
	          error: function() {
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
	          }
	      });
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
				<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights active" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div class="col1_794 lucesBG">
				<% for (Light light : lightsForm.getLights()) { %>
					<div id="accordion">
						<div class="titleContainer">
							<div class="portaToggle"><img src="images/skin_lj_rl/buttons/toggle_arrow.png" id="toggle-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="javascript:toggle('<%=light.getIdEntidad()%>', '<%=light.getIdLuz()%>')"></div>
							<div class="portaTitleAndSwitch">
								<div id="<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="editable"><%= light.getDescription() %></div>
								<div class="switchContainer">
									<!-- div class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="Armar" data-off-label="Desarmar">
									    <input type="checkbox">
									</div-->
									<% if (AsyncJobUtils.displayRandom(light, websiteUser)) { %>
										<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
											<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
										<% } else { %>
											<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
										<% } %>
										<span class="fakeButtons" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">ON</span>
										<span class="fakeButtons" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">OFF</span>
										<span class="fakeButtons" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>MR OFF</b></span>
									<% } else  { %>
										<% if (AsyncJobUtils.displayOn(light, websiteUser)) { %>
											<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
												<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
											<% } else { %>
												<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
											<% } %>
											<span class="fakeButtons" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">ON</span>
											<span class="fakeButtons" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>OFF</b></span>
											<span class="fakeButtons" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">MR ON</span>
										<% } else  { %>
											<% if (AsyncJobUtils.displayOff(light, websiteUser)) { %>
												<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
													<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
												<% } else { %>
													<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
												<% } %>
												<span class="fakeButtons" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>ON</b></span>
												<span class="fakeButtons" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">OFF</span>
												<span class="fakeButtons" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>MR ON</b></span>
											<% } else  { %>
												<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
													<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
												<% } else { %>
													<div id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
												<% } %>
												<span class="fakeButtons" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>ON</b></span>
												<span class="fakeButtons" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>OFF</span>
												<span class="fakeButtons" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"><b>MR ON</b></span>
											<% } %>
										<% } %>
									<% } %>
								</div>
								<% if (light.isInRandomMode()) { %>
									<div class="status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
								<% } else  { %>
									<% if (light.isOn()) { %>
										<div class="status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
									<% } else  { %>
										<% if (light.isOff()) { %>
											<div class="status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
										<% } else  { %>
											<div class="status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
										<% } %>
									<% } %>
								<% } %>
							</div>
						</div>
						<div id="switchBoard">
							<div id="cont-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
								<% if (light.hasChangeData()) { %>
									<span class="lastChange">Último cambio: <%=light.getLastChangeDate() %></span>
									<span class="lastAction"><%=light.getLastChangeAction() %> por: <%=light.getLastChangeUser() %></span>
									<span class="changesLog"><a href="javascript:seeLightLog(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Ver log completo</a></span>
									<span class="notifyme"><input type="checkbox" onchange="toggleEmailNotification(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" <%= light.isEmailnotification() ? "checked" : ""%>> Quiero que me notifique los cambios de estado por E-Mail</span>
									<span class="linkToAgenda"><a href="./goToHomeLightAgenda.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Configurar horarios</a> de Encendido/Apagado</span>
								<% } %>
							</div>
						</div>
					</div>
				<% } %>
			</div>
		</div>
	</div>
</section>

<%@ include file="includes/footerProductoHome.jsp" %>

<div id="logLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle">
		<div class="modalWrapper">
			<div id="logData" class="modalLayerContent">
				Consultando datos...
			</div>
			<input type="button" id="closeLogLayer" cl="logLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="confAlertLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div id="confAlert" style="height:auto; padding:20px 0;">
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
				<div class="alert alert-success">La configuración ha sido salvada.</div>
			</div>
			<input type="button" id="closeSavedConfLayer" cl="confSavedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="randomActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de activación de secuencia aleatoria.</div>
			</div>
			<input type="button" id="closerandomActivatedLayer" cl="randomActivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="randomNotActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">No ha podido enviarse el comando de activación de secuencia aleatoria.</div>
			</div>
			<input type="button" id="closerandomNotActivatedLayer" cl="randomNotActivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="randomDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de desactivación de secuencia aleatoria.</div>
			</div>
			<input type="button" id="closerandomDeactivatedLayer" cl="randomDeactivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="randomNotDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">No ha podido enviarse el comando de desactivación de secuencia aleatoria.</div>
			</div>
			<input type="button" id="closerandomNotDeactivatedLayer" cl="randomNotDeactivatedLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="lightTurnedOnLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de encendido de la luz.</div>
			</div>
			<input type="button" id="closerlightTurnedOnLayer" cl="lightTurnedOnLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="lightNotTurnedOnLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">No ha podido enviarse el comando de encendido de la luz.</div>
			</div>
			<input type="button" id="closelightNotTurnedOnLayer" cl="lightNotTurnedOnLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="jobInProgressErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-block">La luz esta procesando una tarea, por favor espere.</div>
			</div>
			<input type="button" id="closejobInProgressErrorLayer" cl="jobInProgressErrorLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>			
</div>

<div id="lightTurnedOffLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-success">Se ha enviado el comando de apagado de la luz.</div>
			</div>
			<input type="button" id="closerlightTurnedOffLayer" cl="lightTurnedOffLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>

<div id="lightNotTurnedOffLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div class="modalStyle" style="width:350px; margin:120px auto;">
		<div class="modalWrapper" style="width:auto;">
			<h3>Atención</h3>
			<div style="height:auto; padding:20px 0;">
				<div class="alert alert-error">No ha podido enviarse el comando de apagado de la luz.</div>
			</div>
			<input type="button" id="closelightNotTurnedOffLayer" cl="lightNotTurnedOffLayer" value="Cerrar" class="indexButtonBase"/>
		</div>
	</div>
</div>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
</body>
</html>