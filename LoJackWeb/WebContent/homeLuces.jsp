<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.AsyncJobUtils"%><%--
--%><%@page import="com.tdil.lojack.gis.model.Light"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.LightsForm"%><%--
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
	<link href="css/index_modales.css"			rel="stylesheet" media="screen" type="text/css" />
	<link href="css/bootstrapSwitch.css"		rel="stylesheet" media="screen" type="text/css" />
	<link href="css/unified_mobile.css"			rel="stylesheet" media="screen" type="text/css" />
	<link href="css/homeProduct_mobile.css"		rel="stylesheet" media="screen" type="text/css" />
<% } else { %>
	<link href="css/reset-styles.css"			rel="stylesheet" media="screen" type="text/css" />
	<link href="css/sizers.css"					rel="stylesheet" media="screen" type="text/css" />
	<link href="css/bootstrap.min.css"			rel="stylesheet" media="screen" type="text/css" />
	<link href="css/bootstrap-combined.min.css" rel="stylesheet" media="screen" type="text/css" />
	<link href="css/bootstrapSwitch.css"		rel="stylesheet" media="screen" type="text/css" />
	<link href="css/mediaQueries.css"			rel="stylesheet" media="screen" type="text/css"  />
	<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" type="text/css" />
	<link href="css/index_modales.css"			rel="stylesheet" media="screen" type="text/css" />
	<link href="css/index_social.css"			rel="stylesheet" media="screen" type="text/css" />
	<link href="css/copyright.css"				rel="stylesheet" media="screen" type="text/css" />
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
<script src="js/bootstrap.min.js"></script>
<%@ include file="includes/headLogged.jsp" %>
<script src="js/lights.js"></script>
<script src="js/jQueryRotateCompressed.2.2.js"></script>
<script src="js/bootstrapSwitch.js"></script>
<!-- Fin Switches -->
<% LightsForm lightsForm = (LightsForm)session.getAttribute("LightsForm"); %>
<script>
	  <%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
  $(function() {
	  <%@ include file="includes/closeLayers.jspf" %>
	  <%@ include file="includes/externalLogins.jspf" %>
	  $('.editable').editable(function(value, settings) {
		     return doRenameLight($(this).attr('id'), value);
		  }, {
		     type    : 'textarea',
		     submit  : 'OK',
		 });
  });

  <%@ include file="includes/panicJS.jspf" %>
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
		  centerLayer($(window), $( "#centradorModalesLog" ));
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
		  centerLayer($(window), $( "#centradorModalesCAL" ));
		}
	  });
  }
  <%@ include file="includes/errorAjaxJS.jspf" %>
  <%@ include file="includes/centerLayerJS.jspf" %>

  var bgUpdate = false;
  var activating;
  var idEntidad;
  var idLuz;
  
	function toggleRandomSequence(objCheckbox, lightId) {
		if (objCheckbox.checked) {
			activateRandom(objCheckbox, lightId);
		} else {
			deactivateRandom(objCheckbox, lightId);
		}
	}

	function setLightStatusOn(idEntidad, idLuz) {
		bgUpdate = true;
		$("#switch-" + idEntidad + "-" + idLuz).css('display', 'block');
		$("#buttons-" + idEntidad + "-" + idLuz).css('display', 'none');
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', true);
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', true);
		$('#light-switch-' + idEntidad + "-" + idLuz).removeAttr("disabled", "disabled");
		
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', false);
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', false);
		$('#light-ran-' + idEntidad + "-" + idLuz).attr("disabled", "disabled");
		bgUpdate = false;
	}
	function setLightStatusOff(idEntidad, idLuz) {
		bgUpdate = true;
		$("#switch-" + idEntidad + "-" + idLuz).css('display', 'block');
		$("#buttons-" + idEntidad + "-" + idLuz).css('display', 'none');
		
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', false);
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', true);
		$('#light-switch-' + idEntidad + "-" + idLuz).removeAttr("disabled", "disabled");
		
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', false);
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', true);
		$('#light-ran-' + idEntidad + "-" + idLuz).removeAttr("disabled", "disabled");
		bgUpdate = false;
	}
	function setLightStatusRanOn(idEntidad, idLuz) {
		bgUpdate = true;
		$("#switch-" + idEntidad + "-" + idLuz).css('display', 'block');
		$("#buttons-" + idEntidad + "-" + idLuz).css('display', 'none');
		
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', false);
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', false);
		$('#light-switch-' + idEntidad + "-" + idLuz).attr("disabled", "disabled");
		
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', true);
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', true);
		$('#light-ran-' + idEntidad + "-" + idLuz).removeAttr("disabled", "disabled");
		bgUpdate = false;
	}
	function setLightStatusRanOff(idEntidad, idLuz) {
		bgUpdate = true;
		$("#switch-" + idEntidad + "-" + idLuz).css('display', 'block');
		$("#buttons-" + idEntidad + "-" + idLuz).css('display', 'none');
		
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', false);
		$('#b-light-switch-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', true);
		$('#light-switch-' + idEntidad + "-" + idLuz).removeAttr("disabled", "disabled");
		
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setState', false);
		$('#b-light-ran-' + idEntidad + "-" + idLuz).bootstrapSwitch('setActive', true);
		$('#light-ran-' + idEntidad + "-" + idLuz).removeAttr("disabled", "disabled");
		bgUpdate = false;
	}
	function setLightStatusUnknown(idEntidad, idLuz) {
		$("#switch-" + idEntidad + "-" + idLuz).css('display', 'none');
		$("#buttons-" + idEntidad + "-" + idLuz).css('display', 'block');
	}

	function toggleLight(objCheckbox, pidEntidad, pidLight) {
		  if (!bgUpdate) {
			idEntidad = pidEntidad;
			idLuz = pidLight;
			if (objCheckbox.checked) {
				activating = true;
				turnOnLight(pidEntidad, pidLight);
			} else {
				activating = false;
				turnOffLight(pidEntidad, pidLight);
			}
		  }
	  }
	function toggleRandom(objCheckbox, pidEntidad, pidLight) {
		  if (!bgUpdate) {
			idEntidad = pidEntidad;
			idLuz = pidLight;
			if (objCheckbox.checked) {
				activating = true;
				activateRandomSequence(pidEntidad, pidLight);
			} else {
				activating = false;
				deactivateRandomSequence(pidEntidad, pidLight);
			}
		  }
	  }

	function turnOnLight(idEntidad, idLuz) {
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
					  centerLayer($(window), $( "#centradorModalesJIPE" ));
				  } else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#lightTurnedOnLayer" ));
							centerLayer($(window), $( "#centradorModalesLTO" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
							//setLightStatusOn(idEntidad, idLuz);
						} else {
							setLightStatusOff(idEntidad, idLuz);
							centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
							centerLayer($(window), $( "#centradorModalesLNO" ));
						}
					}
	          },
	          error: function() {
	        	  setLightStatusOff(idEntidad, idLuz);
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
	        	  centerLayer($(window), $( "#centradorModalesLNO" ));
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
					  centerLayer($(window), $( "#centradorModalesJIPE" ));
					} else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#lightTurnedOffLayer" ));
							centerLayer($(window), $( "#centradorModalesLTOL" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
							//setLightStatusOff(idEntidad, idLuz);
						} else {
							setLightStatusOn(idEntidad, idLuz);
							centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
							centerLayer($(window), $( "#centradorModalesLNTOL" ));
						}
					}
	          },
	          error: function() {
	        	  setLightStatusOn(idEntidad, idLuz);
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
	        	  centerLayer($(window), $( "#centradorModalesLNTOL" ));
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
					  centerLayer($(window), $( "#centradorModalesJIPE" ));
					} else {
		        	  if (data.result == 'OK') {
							centerLayer($(window), $( "#randomActivatedLayer" ));
							centerLayer($(window), $( "#centradorModalesRAL" ));
							$( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
							//setLightStatusRanOn(idEntidad, idLuz);
						} else {
							setLightStatusOff(idEntidad, idLuz);
							centerLayer($(window), $( "#randomNotActivatedLayer" ));
							centerLayer($(window), $( "#centradorModalesRNAL" ));
						}
					}
	          },
	          error: function() {
	        	  setLightStatusOff(idEntidad, idLuz);
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#randomNotActivatedLayer" ));
	        	  centerLayer($(window), $( "#centradorModalesRNAL" ));
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
					  centerLayer($(window), $( "#centradorModalesJIPE" ));
					} else {
		        	  if (data.result == 'OK') {
		        		  centerLayer($(window), $( "#randomDeactivatedLayer" ));
		        		  centerLayer($(window), $( "#centradorModalesRDL" ));
		        		  $( "#light-job-" +idEntidad + "-" + idLuz ).prop('innerHTML', '*');
		        		  //setLightStatusRanOff(idEntidad, idLuz);
						} else {
							setLightStatusRanOn(idEntidad, idLuz);
							centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
							centerLayer($(window), $( "#centradorModalesRNDL" ));
						}
					}
	          },
	          error: function() {
	        	  setLightStatusRanOn(idEntidad, idLuz);
	        	  <%@ include file="includes/unblockUI.jspf" %>
	        	  centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
	        	  centerLayer($(window), $( "#centradorModalesRNDL" ));
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
		<div id="productHomeMenu" class="col1_170">
			<div id="tab"></div>
			<ul class="tabServices">
				<li class="tabAlarms" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights active" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div id="productHomeContent" class="col1_798 lucesBG">
			<% for (Light light : lightsForm.getLights()) { %>
				<div id="accordion">
					<div class="portaToggle"><img src="images/skin_lj_rl/buttons/toggle_arrow.png" id="toggle-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="javascript:toggle('<%=light.getIdEntidad()%>', '<%=light.getIdLuz()%>')"></div>
					<div class="titleContainer">
						<div class="portaTitleAndSwitch">
							<div id="<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="editable"><%= light.getDescription() %></div>
							<div class="switchContainer correctSwitchContainer">
								<% if (light.isInRandomMode()) { %>
									<div class="portaStatus status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
								<% } else  { %>
									<% if (light.isOn()) { %>
										<div class="portaStatus status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
									<% } else  { %>
										<% if (light.isOff()) { %>
											<div class="portaStatus status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
										<% } else  { %>
											<div class="portaStatus status status-<%=light.getStatusDescription()%>" id="light-status-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"><%=light.getStatusDescription()%></div>
										<% } %>
									<% } %>
								<% } %>
								<% if (AsyncJobUtils.displayRandom(light, websiteUser)) { %>
									<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
										<div class="jobsStyle" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
									<% } else { %>
										<div class="jobsStyleOff" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
									<% } %>
									<div class="XswitchContainer" id="switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: block;">
										<div id="normalSwitches">
											<div id="b-light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="ON" data-off-label="OFF">
												<input type="checkbox" id="light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleLight(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" disabled/>
											</div>
										</div>
										<div id="randomSwitches">
											<div id="b-light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="R-ON" data-off-label="R-OFF">
												<input type="checkbox" id="light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleRandom(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" checked/>
											</div>
										</div>
									</div>
									<div class="XswitchContainer" id="buttons-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
										<button class="fakeButtons on hasButton" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">ON</button>
										<button class="fakeButtons off hasButton" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">OFF</button>
										<button class="fakeButtons randomButtonOn hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-ON</button>
										<button class="fakeButtons randomButtonOff hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-OFF</button>
									</div>
								<% } else  { %>
									<% if (AsyncJobUtils.displayOn(light, websiteUser)) { %>
										<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
											<div class="jobsStyle" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
										<% } else { %>
											<div class="jobsStyleOff" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
										<% } %>
										<div class="XswitchContainer" id="switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: block;">
											<div id="normalSwitches">
												<div id="b-light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="ON" data-off-label="OFF">
													<input type="checkbox" id="light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleLight(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" checked/>
												</div>
											</div>
											<div id="randomSwitches">
												<div id="b-light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="R-ON" data-off-label="R-OFF">
													<input type="checkbox" id="light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleRandom(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" disabled/>
												</div>
											</div>
										</div>
										<div class="XswitchContainer" id="buttons-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
											<button class="fakeButtons on hasButton" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">ON</button>
											<button class="fakeButtons off hasButton" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">OFF</button>
											<button class="fakeButtons randomButtonOn hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-ON</button>
											<button class="fakeButtons randomButtonOff hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-OFF</button>
										</div>
									<% } else  { %>
										<% if (AsyncJobUtils.displayOff(light, websiteUser)) { %>
											<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
												<div class="jobsStyle" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
											<% } else { %>
												<div class="jobsStyleOff" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
											<% } %>
											<div class="XswitchContainer" id="switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: block;">
												<div id="normalSwitches">
													<div id="b-light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="ON" data-off-label="OFF">
														<input type="checkbox" id="light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleLight(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"/>
													</div>
												</div>
												<div id="randomSwitches">
													<div id="b-light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="R-ON" data-off-label="R-OFF">
														<input type="checkbox" id="light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleRandom(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"/>
													</div>
												</div>
											</div>
											<div class="XswitchContainer" id="buttons-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
												<button class="fakeButtons on hasButton" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">ON</button>
												<button class="fakeButtons off hasButton" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">OFF</button>
												<button class="fakeButtons randomButtonOn hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-ON</button>
												<button class="fakeButtons randomButtonOff hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-OFF</button>
											</div>
										<% } else  { %>
											<% if (AsyncJobUtils.hasJobInProgress(light, websiteUser)) { %>
												<div class="jobsStyle" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>">*</div>
											<% } else { %>
												<div class="jobsStyleOff" id="light-job-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>"></div>
											<% } %>
											<div class="XswitchContainer" id="switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
												<div id="normalSwitches">
													<div id="b-light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="ON" data-off-label="OFF">
														<input type="checkbox" id="light-switch-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleLight(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"/>
													</div>
												</div>
												<div id="randomSwitches">
													<div id="b-light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="switch switch-mini" data-on="warning" data-off="danger" data-animated="true" data-on-label="R-ON" data-off-label="R-OFF">
														<input type="checkbox" id="light-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onchange="javascript:toggleRandom(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)"/>
													</div>
												</div>
											</div>
											<div class="XswitchContainer" id="buttons-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: block;">
												<button class="fakeButtons on hasButton" id="turn-on-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">ON</button>
												<button class="fakeButtons off hasButton" id="turn-off-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">OFF</button>
												<button class="fakeButtons randomButtonOn hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-ON</button>
												<button class="fakeButtons randomButtonOff hasButton" id="turn-ran-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">R-OFF</button>
											</div>
										<% } %>
									<% } %>
								<% } %>

							</div>
						</div>
					</div>
					<div id="switchBoard">
						<div id="cont-<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" style="display: none;">
							<% if (light.hasChangeData()) { %>
								<span class="lastChange">�ltimo cambio: <%=light.getLastChangeDate() %></span>
								<span class="lastAction"><%=light.getLastChangeAction() %> por: <%=light.getLastChangeUser() %></span>
								<% if (usingMobile || isAndroid) { %>
									<% if (com.tdil.lojack.utils.LoJackConfig.isShowEmailNotification()) { %>
										<span class="notifyme"><input type="checkbox" onchange="toggleEmailNotification(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" <%= light.isEmailnotification() ? "checked" : ""%>> Quiero que me notifique los cambios de estado por E-Mail</span>
									<% } %>
									<span class="changesLog"><button class="buttonFullLog" onClick="javascript:seeLightLog(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Ver log completo</button></span>
									<% if (com.tdil.lojack.utils.LoJackConfig.isShowAgenda()) { %>
										<span class="linkToAgenda"><button class="buttonAgendas" onClick="location.href='./goToHomeLightAgenda.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>'">Configurar agendas</button></span>
									<% } %>
								<% } else { %>
									<span class="changesLog"><a href="javascript:seeLightLog(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Ver log completo</a></span>
									<% if (com.tdil.lojack.utils.LoJackConfig.isShowEmailNotification()) { %>
										<span class="notifyme"><input type="checkbox" onchange="toggleEmailNotification(this, <%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)" <%= light.isEmailnotification() ? "checked" : ""%>> Quiero que me notifique los cambios de estado por E-Mail</span>
									<% } %>
									<% if (com.tdil.lojack.utils.LoJackConfig.isShowAgenda()) { %>
										<span class="linkToAgenda"><a href="./goToHomeLightAgenda.do?idEntidad=<%=light.getIdEntidad()%>&idLuz=<%=light.getIdLuz()%>">Configurar horarios</a> de Encendido/Apagado</span>
									<% } %>
								<% } %>
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

<div id="logLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLog" class="defaultLayerStyles">
		<div class="modalStyle logLayerStyle">
			<div id="xContainer"><button class="buttonLink" id="closeLogLayer" cl="logLayer">X</button></div>
			<h3>Log de cambios</h3>
			<div id="logData" class="modalLayerContent">
				Consultando datos...
			</div>
		</div>
	</div>
</div>

<div id="confAlertLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesCAL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink">X</button></div>
			<h3>Atenci�n</h3>
			<div id="confAlert">
				Consultando datos...
			</div>
		</div>
	</div>
</div>

<div id="confSavedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesCSL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closeSavedConfLayer" cl="confSavedLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-success">La configuraci�n ha sido salvada.</div>
			</div>
		</div>
	</div>
</div>

<div id="randomActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesRAL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closerandomActivatedLayer" cl="randomActivatedLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-success">Se ha enviado el comando de activaci�n de secuencia aleatoria.</div>
			</div>
		</div>
	</div>
</div>

<div id="randomNotActivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesRNAL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closerandomNotActivatedLayer" cl="randomNotActivatedLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-error">No ha podido enviarse el comando de activaci�n de secuencia aleatoria.</div>
			</div>
		</div>
	</div>
</div>

<div id="randomDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesRDL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closerandomDeactivatedLayer" cl="randomDeactivatedLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-success">Se ha enviado el comando de desactivaci�n de secuencia aleatoria.</div>
			</div>
		</div>
	</div>
</div>

<div id="randomNotDeactivatedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesRNDL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closerandomNotDeactivatedLayer" cl="randomNotDeactivatedLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-error">No ha podido enviarse el comando de desactivaci�n de secuencia aleatoria.</div>
			</div>
		</div>
	</div>
</div>

<div id="lightTurnedOnLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLTO" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closerlightTurnedOnLayer" cl="lightTurnedOnLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-success">Se ha enviado el comando de encendido de la luz.</div>
			</div>
		</div>
	</div>
</div>

<div id="lightNotTurnedOnLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLNO" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closelightNotTurnedOnLayer" cl="lightNotTurnedOnLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-error">No ha podido enviarse el comando de encendido de la luz.</div>
			</div>
		</div>
	</div>
</div>

<div id="jobInProgressErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesJIPE" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closejobInProgressErrorLayer" cl="jobInProgressErrorLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-block">La luz esta procesando una tarea, por favor espere.</div>
			</div>
		</div>
	</div>			
</div>

<div id="lightTurnedOffLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLTOL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closerlightTurnedOffLayer" cl="lightTurnedOffLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-success">Se ha enviado el comando de apagado de la luz.</div>
			</div>
		</div>
	</div>
</div>

<div id="lightNotTurnedOffLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesLNTOL" class="defaultLayerStyles">
		<div class="modalStyle">
			<div id="xContainer"><button class="buttonLink" id="closelightNotTurnedOffLayer" cl="lightNotTurnedOffLayer">X</button></div>
			<h3>Atenci�n</h3>
			<div>
				<div class="alert alert-error">No ha podido enviarse el comando de apagado de la luz.</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
<%@ include file="includes/version.jspf" %></body>
</html>
