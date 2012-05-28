<%@page import="java.util.List"%>
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka</title>
<% 
	if (!"true".equals(request.getParameter("dnc"))) {
		MeltButton.incrementCounter(MeltButton.FINALES_DE_EMAIL_RENDER);
	}
%>
<link href='http://fonts.googleapis.com/css?family=Sue+Ellen+Francisco' rel='stylesheet' type='text/css'>
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
<style>
<!-- 
body { background-color:#FFFFFF; font-family: Georgia, "Times New Roman", Times, serif; padding:0; margin:0; height:100%; }
-->
</style>
</head>

<body>
<script>
	if (AC_FL_RunContent == 0) {
		alert("This page requires AC_RunActiveContent.js.");
	} else {
		AC_FL_RunContent( 'codebase', 'http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0', 
			'width', '100%', 
			'height','100%',
			'FlashVars', 'XMLFile=finalesDeEmail.xml&URLtoUploads=agregarFinalDeEmail.jsp',
			'src', 'swf/ExpFinalesDeMails/slider', 
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
			'movie', 'swf/ExpFinalesDeMails/slider',
			'salign', '' ); 
			//end AC code 
	} 
</script>
<!--a href="finalesDeEmail.xml">Este es el servlet que te da la data</a-->
<!--a href="agregarFinalDeEmail.jsp">Agregar mi final de email</a-->
</body>
</html>
