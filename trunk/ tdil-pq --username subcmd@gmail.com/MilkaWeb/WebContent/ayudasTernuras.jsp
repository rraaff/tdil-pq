<%@page import="com.tdil.milka.model.valueobjects.CreationDateHelper"%>
<%@page import="com.tdil.milka.model.valueobjects.GoodMorningValueObject"%>
<%@page import="com.tdil.milka.web.GoodMorningUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.web.SearchPage"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.web.PaginationUtils"%>
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
<title>Milka.com.ar | Sitio Oficial | Experiencia Ayuda Ternura</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.AYUDA_TERNURAS_RENDER);
	}
	String lnk = StringUtils.isEmpty(request.getParameter("lnk")) ? "" : request.getParameter("lnk");
	boolean limitCookie = "true".equals(SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.LIMIT_COOKIE));
	String serverName = SystemPropertyUtils.getSystemPropertValue(SystemPropertiesKeys.SERVER_NAME);
%>
<%
	String nextPage = "cartasDePadresAHijos.jsp";
	String prevPage = "xxxxxc.jsp";
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
<style>
<!-- 
#socialInBlock {
	width:40px;
	height:17px;
	float:right;
	margin-top:10px;
	margin-right:10px;
}
#socialInBlock img { margin-left:3px; }
#bm_me_derrite a{
	float:right;
	width:108px;
	height:41px;
	background:url(images/barra/me-derrite.png) no-repeat;
	position:relative;
	margin-right: 50px;
}
#bm_personas{
	float:left;
	width:180px;
	height:20px;
	position:relative;
	color:#FFF;
	font-family:Arial, Helvetica, sans-serif;
	font-size:11px;
	top:14px;
}
#bm_personas span{
	color:#b398ff;
}
#imageContainer {
	position:relative;
	top:0;
	left:0;
	z-index:4;
}

#bd_data {
	background-image: url(images/experiencias/buenDia/bgData.png);
	background-repeat: repeat;
	width: 100%;
	color:#FFFFFF;
	font-size:16px;
	overflow:hidden;
	position:relative;
	top:0;
	left:0;
	z-index:6;
}
-->
</style>
</head>

<body>
<% int barClickCounter = MeltButton.AYUDA_TERNURAS_COUNTER; 
	
%>
<div id="floater">
	<%@ include file="includes/barraExperiencias.jsp" %>
</div>
<div id="contentAyudas">

</div>
<%@ include file="includes/fbShare.jsp" %>
<div id="bottomLayer" class="hide"><!-- --></div>
</body>
</html>
