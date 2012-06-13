<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.web.SearchPage"%>
<%@page import="com.tdil.milka.model.WallWritting"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.web.PaginationUtils"%>
<%@page import="com.tdil.milka.web.PapapediaUtils"%>
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
<title>Milka.com.ar | Sitio Oficial | Experiencia Postits</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.POSTIT_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
%>
<%
	String nextPage = "apodosDeAmor.jsp";
	String prevPage = "finalesDeEmail.jsp";
%>
<%@ include file="includes/head.jsp" %>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	}
	
);
</script>

<link href='http://fonts.googleapis.com/css?family=Sue+Ellen+Francisco' rel='stylesheet' type='text/css'/>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/es_LA/all.js#xfbml=1&appId=159591494155451";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>
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
<script type='text/javascript' src='swf/ExpPostits/scripts/AC_RunActiveContent.js'></script>
<style>
<!-- 
body {
	font-family: Georgia, "Times New Roman", Times, serif;
	background-image: url(images/experiencias/postits/tramaDeCorcho.jpg);
	background-repeat: repeat;
	color: #FFFFFF;
	overflow:hidden;
}
-->
</style>
</head>

<body>
<% int barClickCounter = MeltButton.POSTIT_COUNTER; %>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
	<script language="javascript">
		if (AC_FL_RunContent == 0) {
			alert("Esta p&aacute;gina requiere el archivo AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent(
				'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
				'width', '100%',
				'height', '100%',
				'src', 'swf/ExpPostits/milka',
				'quality', 'best',
				'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
				'align', 'middle',
				'play', 'true',
				'loop', 'true',
				'scale', 'showall',
				'wmode', 'transparent',
				'devicefont', 'false',
				'id', 'milka',
				'bgcolor', '#000000',
				'name', 'milka',
				'menu', 'true',
				'allowFullScreen', 'false',
				'allowScriptAccess','sameDomain',
				'movie', 'swf/ExpPostits/milka',
	'flashvars','xmlfile=./postIts.xml&separacionpostit=71&scrollspeedx=7&scrollspeedy=5&opacidadshader=70&minpostits=500&imgbotonup=swf/ExpPostits/taller/enviarOut.png&imgbotonover=swf/ExpPostits/taller/enviarOver.png&imgbotondiffh=130&imgbotondiffv=40',
				'salign', ''
				); //end AC code
		}
	</script>
	<noscript>
	<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="100%" height="100%" id="milka" align="middle">
		<param name="allowScriptAccess" value="sameDomain" />
		<param name="allowFullScreen" value="false" />
		<param name="movie" value="swf/ExpPostits/milka.swf" />
		<param name="quality" value="best" />
		<param name="wmode" value="transparent" />
		<PARAM NAME="flashvars" VALUE="xmlfile=./postIts.xml&separacionpostit=72&scrollspeedx=7&scrollspeedy=5&opacidadshader=70&minpostits=500&imgbotonup=swf/ExpPostits/taller/enviarOut.png&imgbotonover=swf/ExpPostits/taller/enviarOver.png&imgbotondiffh=130&imgbotondiffv=40">
	<embed src="swf/ExpPostits/milka.swf" 
	flashvars="xmlfile=./postIts.xml&separacionpostit=72&scrollspeedx=7&scrollspeedy=5&opacidadshader=70&minpostits=500&imgbotonup=swf/ExpPostits/taller/enviarOut.png&imgbotonover=swf/ExpPostits/taller/enviarOver.png&imgbotondiffh=130&imgbotondiffv=40"
	quality="best" bgcolor="#000000" width="100%" height="100%" name="milka" align="middle" wmode="transparent" allowScriptAccess="sameDomain" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
	</object>
	</noscript>
</div>
<%@ include file="includes/fbShare.jsp" %>
</body>
</html>
