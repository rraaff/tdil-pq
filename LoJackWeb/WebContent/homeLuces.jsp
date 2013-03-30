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

    $( "#closeLogLayer" ).click(function() {
		$( "#logLayer" ).fadeOut();
	});
	
	$( "#closeSavedConfLayer" ).click(function() {
		$( "#confSavedLayer" ).fadeOut();
	});
  });

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

	function activateRandom(objCheckbox, lightId) {
		  $.ajax({
	          type: "GET",
	          cache: false,
	          url: "./activateLightRandomSequence.do",
	          data: {lightId: lightId},
	          contentType: "application/json; charset=utf-8",
	          success: function(data) {
	        	  if (data.result == 'OK') {
						//$( "#confAlertLayer" ).fadeOut();
						//centerLayer($(window), $( "#confSavedLayer" ));
						alert('Se ha enviado el comando');
					} else {
						alert('El comando no ha podido enviarse');
						objCheckbox.checked = false;
					}
	          },
	          error: function() {
	        	  alert('El comando no ha podido enviarse');
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
						//$( "#confAlertLayer" ).fadeOut();
						//centerLayer($(window), $( "#confSavedLayer" ));
						alert('Se ha enviado el comando');
					} else {
						alert('El comando no ha podido enviarse');
						objCheckbox.checked = true;
					}
	          },
	          error: function() {
	        	  alert('El comando no ha podido enviarse');
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
  <h3><%= light.getDescription() %> <%=light.isHasOnOffInfo() ? (light.isOn() ? "Encendida" : "Apagada") : ""%></h3>
  <div>
    <p>
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
	<input type="button" id="closeLogLayer" value="Cerrar">
</div>

<div id="confAlertLayer" style="display: none; z-index: 500;">
	<div id="confAlert">
		Consultando datos...
	</div>
</div>
<div id="confSavedLayer" style="display: none; z-index: 500;">
	La configuracion ha sido salvada
	<input type="button" id="closeSavedConfLayer" value="Cerrar">
</div>
</div>
</body>
</html>