<%@ include file="includes/agentInfo.jspf" %><%--
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
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/tdil.bootstrap.modifier.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_menu.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_modales.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/index_social.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/copyright.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/laruedita.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/home_styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/flexi-background.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/mediaQueries.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/font_embeder.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/ie8-fixes.css" />
<![endif]-->
<%@ include file="includes/headLogged.jsp" %>
<script>
	$(document).ready(
			function(){
	<%@ include file="includes/closeLegalesLayer.jsp" %>
	<%@ include file="includes/closeLayers.jspf" %>
	<%@ include file="includes/externalLogins.jspf" %>
				
			}
	);

	function viewClubLoJack() {
		centerLayer($(window), $( "#clubLoJackLayer" ));
		centerLayer($(window), $( "#centradorModalesClubLoJack" ));
	}
	
	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/centerLayerJS.jspf" %>

	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>
	
	<%@ include file="includes/nuevaRuedita.jspf" %>

	function showVluMessages(dni) {
		<%@ include file="includes/blockUI.jspf" %>
		$('#vluMessagesLayer').load('vluMessagesNoPrevent.jsp?dni=' + dni, function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#vluMessagesLayer" ));
				centerLayer($(window), $( "#vluMessages" ));
			}
		});
	}
	
</script>
</head>
<body>
<script src="js/flexi-background.js" type="text/javascript" charset="utf-8"></script>
<header>
	<div id="floatyMenu">
		<div class="wrapper">
			<ul>
				<li class="avatarLi"><a href="javascript:changeAvatar();">
					<% if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
						<img id="avatarImg" src="./download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" width="30" height="30" align="absmiddle"> 
					<% } else { %>
						<img id="avatarImg" src="images/skin_lj_rl/logos/avatarBase.png" width="32" height="32" align="absmiddle"> 
					<% } %></a></li>
				<li class="saludationAndUsername"><span class="userSaludation">Hola:&nbsp;</span><span class="userName"><%=websiteUser.getName()%></span></li>
				<li><a href="javascript:updatePerson();" title="Cambiar mis datos">Cambiar mis datos</a></li>
				<li><a href="javascript:changePassword();" title="Cambiar mis clave">Cambiar mi clave</a></li>
				<% if (websiteUser.isClientClubLoJack() && LoJackConfig.isClubLoJackShow() ) { %>
					<li><a href="javascript:viewClubLoJack();">Club lo jack</a></li>
				<% } %>
				<li><a href="logout.do" title="Salir del sistema">Salir</a></li>
			</ul>
		</div>
	</div>
</header>
<%@ include file="includes/laRuedita.jsp" %>

<!-- forgot password -->
<div id="clubLoJackLayer" class="layerOnTop" style="display: none; z-index: 1501;">
	<div id="centradorModalesClubLoJack" class="defaultLayerStyles">
		<div class="clubLoJackLayerContent">
			<div id="xContainer"><button class="buttonLink" cl="clubLoJackLayer">X</button></div>
			<div id="tarjeta">
				<span class="nameoncard"><%=websiteUser.getName()%></span>
			</div>
			<a class="linkAsButton" href="<%=LoJackConfig.getClubLoJackUrl()%>" target="_blank">Ver Beneficios</a>
		</div>
	</div>
</div>

<%@ include file="includes/contactLayers.jspf" %>
<%@ include file="includes/copyright.jsp" %>

<%@ include file="includes/videoLayers.jsp" %>

<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<!-- Layer legales -->
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/legalesLayer.jsp" %>

<!-- Update person -->
<div id="vluMessagesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="vluMessages">
		Consultando datos...
	</div>
</div>

<%@ include file="includes/version.jspf" %>
</body>
</html>