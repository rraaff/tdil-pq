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
<title>Tua Festa | Detalle de la Promoci&oacute;n | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
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

.priceAllPromo { color:#6400b1; font-size:24px; line-height:24px; }
.theme-default .nivo-controlNav {
	position:absolute;
	left:50%;
	bottom:60px;
    margin-left:-40px; /* Tweak this to center bullets */
}
.theme-default a.nivo-prevNav, .theme-default a.nivo-nextNav { top:140px; }
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<% 	String id = request.getParameter("id");
	PromotionValueObject pvo = PromotionUtils.getActivePromotion(Integer.valueOf(id)); %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Promoci&oacute;n: <%=pvo.getName() %></h1>
			<h2>Si est&aacute;s interesado en esta promoci&oacute;n hac&eacute; clic en contactar profesionales</h2>
		</div>
		<% 
			List<String> profesionals = pvo.getUniqueProfesionals();
		%>
		<div id="formContent">
			<div id="formSection" style="width:920px;">
				<h2><%=pvo.getName() %></h2>
				<div class="slider-wrapper theme-default" style="width:920px; height:340px; margin-left:15px; float:left;" align="center">
					<div class="ribbon"></div>
					<div id="slider" class="nivoSlider">
					<% for (PromotionPhoto pp : pvo.getAllImages())  { %>
						<img src="./downloadThumb.st?width=628&height=307&id=<%=pp.getIdBlobData()%>&type=PUBLIC&ext=<%=pp.getExtBlobData()%>" alt="<%=pvo.getName() %>" title="" width="628" height="307" />
					<% } %>
					</div>
				</div>
				<div class="myRow">
					<div class="myLabel width120">Precio de la promo</div>
					<div class="myLabel width200 priceAllPromo">$ <%=pvo.getPrice() %></div>
				</div>
				<div class="myRow">
					<div class="myLabel">Activa desde <strong><%=DateUtils.formatDateSp(pvo.getStartdate()) %></strong> hasta <strong><%=DateUtils.formatDateSp(pvo.getEnddate()) %></strong></div>
				</div>
				<h2>Sobre &eacute;sta promoci&oacute;n</h2>
				<div class="myRow"><%=pvo.getDescription() %></div>
				<h2>Productos y servicios</h2>
				<div class="myRow">
					<div class="myLabel width920 usersListing">
				<% 	boolean first = true;
					for (SellValueObject svo : pvo.getSells()) { %>
						<%=(first) ? "" : ",&nbsp;"%> <%=svo.getName()%>
				<%  	first = false;
					} %></div>
				</div>
				<h2>Profesionales</h2>
				<div class="myRow">
					<div class="myLabel width920 usersListing">
				<% 	first = true;
					for (String prof : profesionals) { %>
						<%=(first) ? "" : "&nbsp;|&nbsp;"%> <%=prof%>
				<%  	first = false;
					} %></div>
				</div>
				<div class="myRow height20" align="center" style="padding-top:20px;"><a class="inputButtonHelper" style="color:#000000; text-decoration:none;" href="./contactPromotion.do?id=<%=id %>">Contactar profesionales</a></div>
			</div>
		</div>
	</div>
</div>
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