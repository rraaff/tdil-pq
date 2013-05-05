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
	        	  if (data.result == 'OK') {
						centerLayer($(window), $( "#lightTurnedOnLayer" ));
					} else {
						centerLayer($(window), $( "#lightNotTurnedOnLayer" ));
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
	        	  if (data.result == 'OK') {
						centerLayer($(window), $( "#lightTurnedOffLayer" ));
					} else {
						centerLayer($(window), $( "#lightNotTurnedOffLayer" ));
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
	        	  if (data.result == 'OK') {
						centerLayer($(window), $( "#randomActivatedLayer" ));
					} else {
						centerLayer($(window), $( "#randomNotActivatedLayer" ));
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
	        	  if (data.result == 'OK') {
	        		  centerLayer($(window), $( "#randomDeactivatedLayer" ));
					} else {
						centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
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
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="logout.do">Salir</a>
<hr>
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr>
Mis Lucess<br><br>
<% LightsForm lightsForm = (LightsForm)session.getAttribute("LightsForm"); %>
<div id="accordion">
<% for (Light light : lightsForm.getLights()) { %>
  <h3>
  	<%= light.getDescription() %>
  	<% if (light.isInRandomMode()) { %>
  		Modo random <span onclick="deactivateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Desactivar modo random</span>
  	<% } else  { %>
  		<% if (light.isOn()) { %>
	  		Encendida <span onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Apagar</span>
  		<% } else  { %>
			<% if (light.isOff()) { %>
	  			Apagada <span onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Encender</span>
	  			<span onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Activar modo random</span>
  			<% } else  { %>
  				Estado desconocido <span onclick="turnOnLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Encender</span>
  				<span onclick="turnOffLight(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Apagar</span>
  				<span onclick="activateRandomSequence(<%=light.getIdEntidad()%>, <%=light.getIdLuz()%>)">Activar modo random</span>
		    <% } %>
	  <% } %>
	 <% } %>
  </h3>
  <div>
    <p>
    	<div id="<%=light.getIdEntidad()%>-<%=light.getIdLuz()%>" class="editable"><%= light.getDescription() %></div>
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

<div id="lightTurnedOffLayer" style="display: none; z-index: 500;">
	Se ha enviado el comando de apagado de la luz
	<input type="button" id="closerlightTurnedOffLayer" cl="lightTurnedOffLayer" value="Cerrar">
</div>
<div id="lightNotTurnedOffLayer" style="display: none; z-index: 500;">
	No ha podido enviarse el comando de apagado de la luz
	<input type="button" id="closelightNotTurnedOffLayer" cl="lightNotTurnedOffLayer" value="Cerrar">
</div>

</body>
</html>