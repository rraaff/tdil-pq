<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="quienes_somos"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R014-M1- Quienes somos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<div id="titleArea">
			<h1>TUA FESTA</h1>
			<h2>Quienes somos y de qu&eacute; se trata el proyecto.</h2>
		</div>
		<div id="formContent" class="height450">
			<div id="leftBar">
				<p>texto para completar</p>
			</div>
			<div style="width:393px; padding-top:34px; margin-left:20px; float:right;">
				<h2 style="padding-left:0;">Seguinos en</h2>
				<div style="margin-right:25px; float:left;"><a href="http://www.facebook.com/"><img src="images/skin_basic/buttons/facebookPlaca.gif" alt="Seguinos en Facebook" /></a></div>
				<div style="float:left;"><a href="http://www.twitter.com/"><img src="images/skin_basic/buttons/twitterPlaca.gif" alt="Seguinos en Twitter" width="184" height="121" /></a></div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>