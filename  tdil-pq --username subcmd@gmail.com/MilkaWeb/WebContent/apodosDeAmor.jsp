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
<title>Milka.com.ar | Sitio Oficial | Experiencia Apodos de Amor</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.APODOS_DE_AMOR_RENDER);
	}

	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
%>
<%
	String nextPage = "postits.jsp";
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
<script type='text/javascript' src='swf/ExpApodos/scripts/AC_RunActiveContent.js'></script>
<style>
<!-- 
body {
	background:none;
	background-color:#FFFFFF;
	overflow:hidden;
}
-->
</style>
</head>
<body>
<% int barClickCounter = MeltButton.APODOS_DE_AMOR_COUNTER; %>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="flashin">
<div style="height:35px; width:100%;"></div>
	<script language="javascript">
		if (AC_FL_RunContent == 0) {
			alert("Esta p&aacute;gina requiere el archivo AC_RunActiveContent.js.");
		} else {
			AC_FL_RunContent(
				'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0',
				'width', '100%',
				'height', '100%',
				'src', 'swf/ExpApodos/apodos',
				'quality', 'best',
				'pluginspage', 'http://www.macromedia.com/go/getflashplayer',
				'align', 'middle',
				'play', 'true',
				'loop', 'true',
				'scale', 'showall',
				'wmode', 'transparent',
				'devicefont', 'false',
				'id', 'apodos',
				'bgcolor', '#ffffff',
				'name', 'apodos',
				'menu', 'true',
				'allowFullScreen', 'false',
				'allowScriptAccess','sameDomain',
				'movie', 'swf/ExpApodos/apodos',
				'flashvars','xmlfile=apodosDeAmor.xml&tipitospath=swf/ExpApodos/tipitos/&posttarget=./addLoveNick.do&maxtipitos=1500&margintop=80',
				'salign', ''
				); //end AC code
		}
	</script>
	<noscript>
		<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0" width="100%" height="100%" id="apodos" align="middle">
		<param name="allowScriptAccess" value="sameDomain" />
		<param name="allowFullScreen" value="false" />
		<param name="movie" value="swf/ExpApodos/apodos.swf" />
		<param name="quality" value="best" />
		<param name="wmode" value="transparent" />
		<PARAM NAME="flashvars" VALUE="xmlfile=apodosDeAmor.xml&tipitospath=swf/ExpApodos/tipitos/&posttarget=./addLoveNick.do&maxtipitos=1500&margintop=80">
	<embed src="swf/ExpApodos/apodos.swf" 
	flashvars="xmlfile=apodosDeAmor.xml&tipitospath=swf/ExpApodos/tipitos/&posttarget=./addLoveNick.do&maxtipitos=1500&margintop=80"
	quality="best" bgcolor="#ffffff" width="100%" height="100%" name="apodos" align="middle" wmode="transparent" allowScriptAccess="sameDomain" allowFullScreen="false" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />
		</object>
	</noscript>
</div>
<%@ include file="includes/fbShare.jsp" %>
</body>
</html>
