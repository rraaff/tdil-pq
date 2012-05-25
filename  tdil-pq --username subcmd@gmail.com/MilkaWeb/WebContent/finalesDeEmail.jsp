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
<style>
<!-- 
body {
	font-family: Georgia, "Times New Roman", Times, serif;
}
#header {
	background-image: url(images/experiencias/papapedia/header.gif);
	background-repeat: no-repeat;
	background-position: center top;
	width:950px;
	height:248px;
	margin:0 auto;
}
#content h1 {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size: 22px;
	font-style: italic;
	color: #FFFFFF;
	padding-top:16px;
	margin-left:16px;
}
#insertContentBlock {
	background-color: #f2583c;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	width:606px;
	height:98px;
	margin-right: auto;
	margin-left: auto;
	margin-top:3px;
	margin-bottom:8px;
}
#insertContentBlock #complete {
	margin-left:16px;
	margin-right:16px;
	margin-top:3px;
	width:100%;
	height:33px;
}
#insertContentBlock #complete input[type="text"], #insertContentBlock #complete input[type="password"], #insertContentBlock #complete select {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size:14px;
	font-style: italic;
	color:#5a5a5a;
	float:left;
	margin:0;
	padding:2px;
	line-height: normal;
	width:480px;
	height:23px;
	border: solid 1px #999999;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border-radius: 2px;
}
input[type="button"], input[type="submit"] {
	font-family: Georgia, "Times New Roman", Times, serif;
	font-size:15px;
	color: #FFFFFF;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	padding-top: 5px;
	padding-right: 11px;
	padding-bottom: 5px;
	padding-left: 11px;
	margin: 5px;
	margin-top:0px;
	float:left;
	cursor:hand;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.9);
	background-color: #1c1c1c;
}
#counter {
	color:#f2583c;
	size:12px;
	height: 20px;
	width: 606px;
	margin-top: 8px;
	margin-right: auto;
	margin-bottom: 8px;
	margin-left: auto;
}
.line {
	height: 2px;
	width: 606px;
	margin-top: 8px;
	margin-right: auto;
	margin-bottom: 8px;
	margin-left: auto;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999999;
}
.texto {
	color:#5a5a5a;
	font-size:12px;
	width: 606px;
	padding-top: 14px;
	margin-right: auto;
	padding-bottom: 14px;
	margin-left: auto;
	border-bottom-width: 1px;
	border-bottom-style: dotted;
	border-bottom-color: #999999;
}
#paginado {
	width: 606px;
	height:25px;
	margin-top: 14px;
	margin-right: auto;
	margin-bottom: 68px;
	margin-left: auto;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #f2583c;
}
#paginado #previuos {
	width:250px;
	height:20px;
	padding-left:10px;
	float:left;
	background-image: url(images/experiencias/papapedia/prev.gif);
	background-repeat: no-repeat;
	background-position: left top;
}
#paginado #previuos a {
	color:#f2583c;
}
#paginado #next {
	width:250px;
	height:20px;
	padding-right:10px;
	text-align:right;
	float:right;
	background-image: url(images/experiencias/papapedia/next.gif);
	background-repeat: no-repeat;
	background-position: right top;
}
#paginado #next a {
	color:#f2583c;
}
-->
</style>
</head>

<body>
<h1>Aca va el flash</h1>
<a href="finalesDeEmail.xml">Este es el servlet que te da la data</a>
<a href="agregarFinalDeEmail.jsp">Agregar mi final de email</a>
</body>
</html>
