<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.milka.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.milka.web.SystemPropertyUtils"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka.com.ar | Sitio Oficial | Experiencia Cartas de Hijos a Padres</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.CARTAS_DE_HIJOS_A_PADRES_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
%>
<%
	String nextPage = "papapedia.jsp";
	String prevPage = "postits.jsp";
%>
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1&appId=159591494155451";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
<script src="swf/ExpCartasHaP/scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	}
);
</script>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-32381287-1']);
	_gaq.push(['_trackPageview']);
	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>
<style>
<!-- 
body {
	background-color:#FFFFFF;
	font-family: Georgia, "Times New Roman", Times, serif;
	padding:0;
	margin:0;
	height:100%;
	background-image: url(swf/ExpCartasHaP/taller/fondo.jpg);
	background-repeat: repeat-x;
	overflow:hidden;
}
-->
</style>
</head>
<body>
<% int barClickCounter = MeltButton.CARTAS_DE_HIJOS_A_PADRES_COUNTER; %>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<div style="height:50px; width:100%;"></div>
	<script>
		if (AC_FL_RunContent == 0) {
			alert("This page requires AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent( 'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0', 
				'width', '100%', 
				'height','92%',
				'FlashVars', 'XMLFile=cartasDeHijosAPadres.xml&URLtoUploads=agregarCartaDeHijoAPadre.jsp',
				'src', 'swf/ExpCartasHaP/slider', 
				'quality', 'best', 
				'pluginspage', 'http://www.macromedia.com/go/getflashplayer', 
				'align', 'middle', 
				'play', 'true', 
				'loop', 'true', 
				'scale', 'showall', 
				'wmode', 'transparent', 
				'devicefont', 'true', 
				'id', 'ACTest', 
				'bgcolor', '#ffffff', 
				'name', 'slider', 
				'menu', 'true', 
				'allowScriptAccess', 'sameDomain', 
				'movie', 'swf/ExpCartasHaP/slider',
				'salign', '' ); 
				//end AC code 
		} 
	</script>
</div>
<%@ include file="includes/fbShare.jsp" %>
</body>
</html>
