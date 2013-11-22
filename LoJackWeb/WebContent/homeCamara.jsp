<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.CameraForm"%><%--
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
	<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrapSwitch.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/unified_mobile.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/homeProduct_mobile.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/homeCamera_mobile_adjust.css" />
<% } else { %>
	<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/tdil.bootstrap.modifier.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/index_social.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/copyright.css" />
	<link type="text/css" rel="stylesheet" media="screen" href="css/mediaQueries.css" />
<% } %>

<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
@media only screen and (max-width: 350px) {
	#productHomeContent.col1_798 { padding: 0; }
}
@media only screen and (max-width: 968px) {
	#productHomeMenu.col1_170 { position: relative; }
}
</style>
<%@ include file="includes/headLogged.jsp" %>
<script>
$(function() {
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
});

<%@ include file="includes/centerLayerJS.jspf" %>
<%@ include file="includes/errorAjaxJS.jspf" %>
<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>

<% CameraForm cameraForm = (CameraForm)session.getAttribute("CameraForm"); %>

function up() {
		$.ajax({
				type: "GET",
				cache: false,
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
					url: "./moveCamera?dir=up",
				<% } %>
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
					url: "./moveCameraProxy?dir=up",
				<% } %>
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
					url: "<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>moveCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&dir=up",
				<% } %>
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
					url: "./moveCameraSocket?dir=up",
				<% } %>
				success: function(data) {
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
					$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
					<% } %>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
						$('#cameraImg').attr('src', './viewCameraProxy?img=' + Math.random());
					<% } %>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
						$('#cameraImg').attr('src', '<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>viewCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&img=' + Math.random());
					<% } %>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
						$('#cameraImg').attr('src', './viewCameraSocket?img=' + Math.random());
					<% } %>
			 },
				error: function() {
				}
		});
}
function down() {
		$.ajax({
			type: "GET",
			cache: false,
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
				url: "./moveCamera?dir=down",
			<% } %>
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
				url: "./moveCameraProxy?dir=down",
			<% } %>
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
				url: "<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>moveCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&dir=down",
			<% } %>
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
				url: "./moveCameraSocket?dir=down",
			<% } %>
			success: function(data) {
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
				$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
				<% } %>
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
					$('#cameraImg').attr('src', './viewCameraProxy?img=' + Math.random());
				<% } %>
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
					$('#cameraImg').attr('src', '<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>viewCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&img=' + Math.random());
				<% } %>
				<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
					$('#cameraImg').attr('src', './viewCameraSocket?img=' + Math.random());
				<% } %>
		 },
			error: function() {
			}
	});
}
function left() {
		$.ajax({
		type: "GET",
		cache: false,
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
			url: "./moveCamera?dir=left",
		<% } %>
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
			url: "./moveCameraProxy?dir=left",
		<% } %>
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
			url: "<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>moveCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&dir=left",
		<% } %>
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
			url: "./moveCameraSocket?dir=left",
		<% } %>
		success: function(data) {
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
			$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
			<% } %>
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
				$('#cameraImg').attr('src', './viewCameraProxy?img=' + Math.random());
			<% } %>
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
				$('#cameraImg').attr('src', '<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>viewCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&img=' + Math.random());
			<% } %>
			<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
				$('#cameraImg').attr('src', './viewCameraSocket?img=' + Math.random());
			<% } %>
	 },
		error: function() {
		}
});
}
function right() {
		$.ajax({
	type: "GET",
	cache: false,
	<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
		url: "./moveCamera?dir=right",
	<% } %>
	<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
		url: "./moveCameraProxy?dir=right",
	<% } %>
	<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
		url: "<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>moveCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&dir=right",
	<% } %>
	<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
		url: "./moveCameraSocket?dir=right",
	<% } %>
	success: function(data) {
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
		$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
		<% } %>
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
			$('#cameraImg').attr('src', './viewCameraProxy?img=' + Math.random());
		<% } %>
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
			$('#cameraImg').attr('src', '<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>viewCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&img=' + Math.random());
		<% } %>
		<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
			$('#cameraImg').attr('src', './viewCameraSocket?img=' + Math.random());
		<% } %>
 	},
	error: function() {
	}
});
}

<%@ include file="includes/panicJS.jspf" %>
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
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras active"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div id="productHomeContent" class="camarasBG">
			<div id="cameraTitle">
				<h1>Mi C�mara</h1>
			</div>
			
			<% if (cameraForm.isUseApplet()) { %>
				<div id="appletHolder">
					<object classid="clsid:CAFEEFAC-0016-0000-0000-ABCDEFFEDCBA">
						<param name="wmode" value="transparent" />
						<param name="code" value="com.tdil.lojack.camera.applet.AppletCamara.class">
						<PARAM NAME="TYPE" VALUE="application/x-java-applet;version=1.6">
						<PARAM NAME="ARCHIVE" VALUE="cameraviewer-b201311220003.jar">
						<comment>
							<embed code="com.tdil.lojack.camera.applet.AppletCamara.class" type="application/x-java-applet;jpi-version=1.6"
								ARCHIVE="cameraviewer-b201311220003.jar" width="100%" height="100%" wmode="transparent">
								<noembed>
									No Java Support.
								</noembed>
							</embed>
						</comment>
					</object>
				</div>
				<div id="linksAside">
					<a href="./toggleCameraView.do"><span>Cambiar Simple >></span></a>
				</div>
			<% } else { %>
				<div id="pictureContainer">
					<div id="testerDeAltura" style="display:none;">not set yet</div>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
						<img id="cameraImg" src="./viewCamera">
					<% } %>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
						<img id="cameraImg" src="./viewCameraProxy">
					<% } %>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
						<img id="cameraImg" src="<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>viewCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>">
					<% } %>
					<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
						<img id="cameraImg" src="./viewCameraSocket">
					<% } %>
					<div id="controlsBasicViewId" class="controlsBasicView">
						<a href="javascript:left()" id="right"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_right_off.png" /></a>
						<a href="javascript:up()" id="up"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_up_off.png" /></a>
						<a href="javascript:down()" id="down"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_down_off.png" /></a>
						<a href="javascript:right()" id="left"><img src="images/skin_lj_rl/buttons/AppletCamera/applet_left_off.png" /></a>
					</div>
				</div>
				<script>
					setInterval(function() {
						<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeLocal()) { %>
							$('#cameraImg').attr('src', './viewCamera?img=' + Math.random());
						<% } %>
						<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeProxy()) { %>
							$('#cameraImg').attr('src', './viewCameraProxy?img=' + Math.random());
						<% } %>
						<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeExternal()) { %>
							$('#cameraImg').attr('src', '<%=com.tdil.lojack.utils.LoJackConfig.getCameraMobileExternalUrl()%>viewCameraStateless?url=<%=cameraForm.getUrl()%>&username=<%=cameraForm.getUsername()%>&password=<%=cameraForm.getPassword()%>&model=<%=cameraForm.getModel()%>&img=' + Math.random());
						<% } %>
						<% if (com.tdil.lojack.utils.LoJackConfig.isCameraMobileModeSocket()) { %>
							$('#cameraImg').attr('src', './viewCameraSocket?img=' + Math.random());
						<% } %>
						
					},<%=SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.camera_mobile_refreshTime)%>);
				</script>

				<div id="linksAside">
					<% if (usingMobile || isAndroid) { %>

					<% } else { %>
						<a href="./toggleCameraView.do">Vista Avanzada</a>
					<% } %>
				</div>
			<% } %>
		</div>
	</div>
</section>

<section id="linkBackCameras">
	<a class="especialkkk" href="productoHome.jsp" title="Volver a las home"><img src="images/skin_lj_rl/buttons/AppletCamera/back_off.png" /></a>
</section>
<% if (!cameraForm.isUseApplet()) { %>
	<% if (usingMobile || isAndroid) { %>
		<script>
			var modifyCameraSize = function(){
				var elemToChange1 = document.getElementById("cameraImg");
				var elemToChange2 = document.getElementById("controlsBasicViewId");
				var elemToChange3 = document.getElementById("pictureContainer");
				var elemToChange4 = document.getElementById("productHomeContent");
				var winW = $(window).width();
				var winH = $(window).height();
				
				if (winW > winH) {
					var testervar = document.getElementById("testerDeAltura").innerHTML="LANDSCAPE > WW: " + winW + " - WH " + winH;
					elemToChange1.style.top = "0";
					elemToChange1.style.left = "0";
					elemToChange1.style.width = winW + "px"
					elemToChange1.style.height = winH + "px"
					
					elemToChange2.style.top = winH - 82 + "px"
					elemToChange2.style.bottom = "auto"

					elemToChange3.style.top = "0";
					elemToChange3.style.left = "0";
					elemToChange3.style.width = winW + "px"
					elemToChange3.style.height = winH + "px"

					elemToChange4.style.top = "0";
					elemToChange4.style.left = "0";
					elemToChange4.style.width = winW + "px"
					elemToChange4.style.height = winH + "px"
				} else if (winW < winH) {
					var testervar = document.getElementById("testerDeAltura").innerHTML="PORTRAIT > WW: " + winW + " - WH " + winH;
					elemToChange1.style.width = winW + "px"
					elemToChange1.style.height = "auto"
					
					elemToChange2.style.top = "auto"
					elemToChange2.style.bottom = "auto"

					elemToChange3.style.width = winW + "px"
					elemToChange3.style.height = "auto"

					elemToChange4.style.width = winW + "px"
					elemToChange4.style.height = "auto"
				}
			}
			
			window.onresize=function() {
				modifyCameraSize();
			}
		</script>
	<% } %>
<% } %>
<%@ include file="includes/footerProductoHome.jsp" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
<%@ include file="includes/version.jspf" %>
</body>
</html>