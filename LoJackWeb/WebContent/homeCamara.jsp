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
	  setInterval(function() {
		  $('#cameraImg').attr('src', './viewCamera');
	  },1000);
	}
);

function up() {
	  $.ajax({
        type: "GET",
        cache: false,
        url: "./moveCamera?dir=up",
        success: function(data) {
      	  $('#cameraImg').attr('src', './viewCamera');
       },
        error: function() {
        	alert('err');	
        }
    });
}
function down() {
	  $.ajax({
      type: "GET",
      cache: false,
      url: "./moveCamera?dir=down",
      success: function(data) {
    	  $('#cameraImg').attr('src', './viewCamera');
     },
      error: function() {
      	alert('err');	
      }
  });
}
function left() {
	  $.ajax({
    type: "GET",
    cache: false,
    url: "./moveCamera?dir=left",
    success: function(data) {
    	$('#cameraImg').attr('src', './viewCamera');
   },
    error: function() {
    	alert('err');	
    }
});
}
function right() {
	  $.ajax({
  type: "GET",
  cache: false,
  url: "./moveCamera?dir=right",
  success: function(data) {
	  $('#cameraImg').attr('src', './viewCamera');
 },
  error: function() {
  	alert('err');	
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
Mi Camara<br><br>

<object classid="clsid:CAFEEFAC-0016-0000-0000-ABCDEFFEDCBA">
  <param name="code" value="com.tdil.lojack.camera.applet.AppletCamara.class">
  <PARAM NAME="TYPE" VALUE="application/x-java-applet;version=1.6">
  <PARAM NAME="ARCHIVE" VALUE="cameraviewer-b201304170023.jar">
  <PARAM NAME="username" VALUE="preisinger">
  <PARAM NAME="password" VALUE=lj2013>
  <PARAM NAME="url" VALUE="http://ljcam2.dyndns.org:8888">
  <PARAM NAME="model" VALUE="TPLinkSC4171G">
    <comment>
      <embed code="com.tdil.lojack.camera.applet.AppletCamara.class" type="application/x-java-applet;jpi-version=1.6"
      	ARCHIVE="cameraviewer-b201304170023.jar" username="preisinger" password="lj2013" url="http://ljcam2.dyndns.org:8888" model="TPLinkSC4171G">
        <noembed>
          No Java Support.
        </noembed>
      </embed>
    </comment>
  </object>
<img id="cameraImg" src="./viewCamera" width="320" height="240"><br>
<a href="javascript:up()" id="up">Up</a><br>
<a href="javascript:down()" id="down">Down</a><br>
<a href="javascript:left()" id="left">Left</a><br>
<a href="javascript:right()" id="right">Right</a><br>
</body>
</html>