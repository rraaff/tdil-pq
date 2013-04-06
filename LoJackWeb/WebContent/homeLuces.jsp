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
		     return doRenameLight($(this).attr('id'), value);
		  }, { 
		     type    : 'textarea',
		     submit  : 'OK',
		 });
  });

  function doRenameLight(lightId, lightDesc) {
	  $.ajax({
          type: "GET",
          cache: false,
          url: "./renameLight.do",
          data: {lightId: lightId, description: lightDesc},
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
  
  function seeLightLog(lightId) {
	  $('#logData').load('logLuz.jsp?lightId=' + lightId, function() {
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

	function turnOnLight(lightId) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./turnOnLight.do",
	          data: {lightId: lightId},
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

	function turnOffLight(lightId) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./turnOffLight.do",
	          data: {lightId: lightId},
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

	function activateRandom(objCheckbox, lightId) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./activateLightRandomSequence.do",
	          data: {lightId: lightId},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'OK') {
						centerLayer($(window), $( "#randomActivatedLayer" ));
					} else {
						centerLayer($(window), $( "#randomNotActivatedLayer" ));
						objCheckbox.checked = false;
					}
	          },
	          error: function() {
	        	  centerLayer($(window), $( "#randomNotActivatedLayer" ));
	        	  objCheckbox.checked = false;
	          }
	      });
	  }

	function deactivateRandom(objCheckbox, lightId) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./deactivateLightRandomSequence.do",
	          data: {lightId: lightId},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'OK') {
	        		  centerLayer($(window), $( "#randomDeactivatedLayer" ));
					} else {
						centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
						objCheckbox.checked = true;
					}
	          },
	          error: function() {
	        	  centerLayer($(window), $( "#randomNotDeactivatedLayer" ));
	        	  objCheckbox.checked = true;
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
<% for (Light light : lightsForm.getLights()) { %>>
  <h3>
  	<%= light.getDescription() %> <%=light.isHasOnOffInfo() ? (light.isOn() ? "Encendida" : "Apagada") : ""%>
  	<% if (light.isHasOnOffInfo()) { %>
  		<% if (light.isOn()) { %>
	  		<span onclick="turnOffLight('<%=light.getId()%>')">Apagar</span>
	  	<% } else  { %>
	  		<span onclick="turnOnLight('<%=light.getId()%>')">Encender</span>
	  	<% } %>
  	<% } else  { %>
  		<span onclick="turnOnLight('<%=light.getId()%>')">Encender</span>
  		<span onclick="turnOffLight('<%=light.getId()%>')">Apagar</span>
  	<% } %>
  </h3>
  <div>
    <p>
    	<div id="<%=light.getId()%>" class="editable"><%= light.getDescription() %></div>
   		<% if (light.hasChangeData()) { %>
   			Ultimo cambio: <%=light.getLastChangeDate() %> - <%=light.getLastChangeHour() %>
   			- <%=light.getLastChangeAction() %> - <%=light.getLastChangeUser() %> <br>
   			<a href="javascript:seeLightLog('<%= light.getId() %>')">Ver log completo</a><br>
   			Envio de <a href="javascript:confLightAlert('<%= light.getId() %>')">Alertas por Email</a><br>
   			<a href="./goToHomeLightAgenda.do?lightId=<%=light.getId()%>">Configurar horarios</a> de Encendido/Apagado<br>
 			<input type="checkbox" onchange="toggleRandomSequence(this, '<%=light.getId()%>')" <%= light.isRandomSequence() ? "checked" : ""%>>Activar/Desactivar secuencia aleatoria
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