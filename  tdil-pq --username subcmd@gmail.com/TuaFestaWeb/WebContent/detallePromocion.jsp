<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.PromotionPhoto"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.tuafesta.utils.PromotionUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.PromotionValueObject"%>
<%@page import="com.tdil.tuafesta.utils.CategoryUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.CategoryValueObject"%>
<%@page import="com.tdil.tuafesta.utils.GeoLevelUtils"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="listadoRubros"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Inicio | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/nivo_theme/default.css" type="text/css" media="screen" />
<style>
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
}
#slider h1 a, #slider h1 a:active, #slider h1 a:visited, #slider h1 {
	border:none;
	color:#FFFF33;
	text-decoration:none;
}
#slider h1 a:hover {
	text-decoration:underline;
}
#slider .bajada a {
	border:none;
	text-decoration:none;
}
</style>
</head>
<body>
<% 	String id = request.getParameter("id");
	PromotionValueObject pvo = PromotionUtils.getActivePromotion(Integer.valueOf(id)); %>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Detalle promocion</h1>
		</div>
		<% 
			List<String> profesionals = pvo.getUniqueProfesionals();
		%>
		
		<div class="slider-wrapper theme-default" style="width:428px; height:400px; margin-left:15px; float:left;">
		    <div class="ribbon"></div>
		    <div id="slider" class="nivoSlider">
			<% for (PromotionPhoto pp : pvo.getAllImages())  { %>
		        <img src="./downloadThumb.st?width=428&height=385&id=<%=pp.getIdBlobData()%>&type=PUBLIC&ext=<%=pp.getExtBlobData()%>" alt="" title="" width="428" height="385" />
			<% } %>
		    </div>
		</div>
		<%=pvo.getName() %><br>
		<%=pvo.getDescription() %><br>
		$<%=pvo.getPrice() %> - Desde <%=DateUtils.formatDate(pvo.getStartdate()) %> hasta <%=DateUtils.formatDate(pvo.getEnddate()) %><br>
		Productos y servicios<br>
		<% 	boolean first = true;
			for (SellValueObject svo : pvo.getSells()) { %>
				<%=(first) ? "" : "&nbsp;|&nbsp;"%> <%=svo.getName()%>
		<%  	first = false;
			} %><br>
		Profesionales<br>
		<% 	first = true;
			for (String prof : profesionals) { %>
				<%=(first) ? "" : "&nbsp;|&nbsp;"%> <%=prof%>
		<%  	first = false;
			} %><br>
	</div>
	<a href="./contactPromotion.do?id=<%=id %>">Contactar profesionales</a>
</div>
<p>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
<script type="text/javascript">
  $(document).ready(function(){
   $('#slider').nivoSlider({
		effect: 'fold',  // Specify sets like: 'fold,fade,sliceDown');
		pauseTime: 10000
	});
	

  });
   </script>
</body>
</html>