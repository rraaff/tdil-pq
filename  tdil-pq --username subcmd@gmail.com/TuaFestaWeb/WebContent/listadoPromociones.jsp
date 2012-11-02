<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<style>
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
}
#promoAllPromos {
	background-image: url(images/skin_basic/backgrounds/formBackground.gif);
	background-repeat: repeat;
	background-color:#fff;
	margin-bottom:20px;
	padding:10px;
	float:left;

	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
	
	box-shadow: 0 0 3px 3px rgba(80, 80, 80, 0.1);
	-webkit-box-shadow: 0 0 3px 3px rgba(80, 80, 80, 0.1);
	-moz-box-shadow: 0 0 3px 3px rgba(80, 80, 80, 0.1);
	-o-box-shadow: 0 0 3px 3px rgba(80, 80, 80, 0.1);
}
#promoAllPromos h2, #promoAllPromos h3, #promoAllPromos h4 {
	font-family:"Tahoma", "Trebuchet MS", Arial, sans-serif;
	font-weight:normal;
	margin:0;
	padding:0px 0px 10px 0px;
}
#promoAllPromos h2 a { color:#005a9e; font-size:24px; line-height:24px; }
#promoAllPromos h3 { color:#dba000; font-size:20px; line-height:20px; }
#promoAllPromos h4 { color:#7b7b7b; font-size:18px; line-height:18px; padding-top:15px; }
#promoAllPromos .imageAllPromo {
	border:solid 1px #8d8d8d;
	width:312px;
	height:153px;
	float:left;
	
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}
#promoAllPromos .descriptionAllPromo, #promoAllPromos .priceAllPromo, #promoAllPromos .activeAllPromo, #promoAllPromos .seeAllPromo a {
	padding-top:5px;
	padding-bottom:5px;
	overflow:hidden;
	display:block;
}
#promoAllPromos .descriptionAllPromo {
	line-height:17px;
	height:65px;
	padding-bottom:5px;
}
#promoAllPromos .priceAllPromo { color:#6400b1; font-size:24px; line-height:24px; }
#promoAllPromos .activeAllPromo { color:#8d8d8d; }
#promoAllPromos .seeAllPromo a {
	text-align:center;
}
#promoAllPromos .productAllPromos, #promoAllPromos .professionalsAllPromos {
	color:#333333;
	font-size:11px;
	font-weight:bold;
	text-transform: lowercase;
	text-transform: capitalize;
}
#promoAllPromos .professionalsAllPromos {
	color:#6acdef;
}
.ladata {
	width:582px;
	margin-left:20px;
	float:left;
}
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Todos las Promociones</h1>
			<h2>En esta secci&oacute;n est&aacute;n todas las promociones activas en el sitio</h2>
		</div>
		<div id="formContent">
			<% for (PromotionValueObject pvo : PromotionUtils.getActivePromotionsWithSells()) { %>
				<% 
					List<String> profesionals = pvo.getUniqueProfesionals();
				%>
				<div id="promoAllPromos">
					<h2><a href="./detallePromocion.jsp?id=<%=pvo.getId() %>"><%=pvo.getName() %></a></h2>
					<div class="imageAllPromo"><a href="./detallePromocion.jsp?id=<%=pvo.getId() %>"><img src="./downloadThumb.st?width=312&height=153&id=<%=pvo.getFirstImageid()%>&type=PUBLIC&ext=<%=pvo.getFirstImageExt()%>" title="#htmlcaption<%=pvo.getId() %>" width="312" height="153" /></a></div>
					<div class="ladata">
						<h3>Detalle de la promoci&oacute;n</h3>
						<span class="activeAllPromo">Activa desde: <strong><%=DateUtils.formatDateSp(pvo.getStartdate()) %></strong> hasta: <strong><%=DateUtils.formatDateSp(pvo.getEnddate()) %></strong></span>
						<span class="descriptionAllPromo"><%=pvo.getDescription() %></span>
						<span class="priceAllPromo">$<%=pvo.getPrice() %></span>
					</div>
					<div class="ladata" style="border-top:dotted 1px #8d8d8d; width:920px; padding-top:10px; margin-top:10px; margin-left:0;">
						<h3>Productos y servicios incluidos en la promoci&oacute;n</h3>
						<% 	boolean first = true;
							for (SellValueObject svo : pvo.getSells()) { %>
								<%=(first) ? "" : "&nbsp;|&nbsp;"%><span class="productAllPromos"><%=svo.getName()%></span>
						<%  	first = false;
							} %>
							<h4>Profesionales</h4>
							<% 	first = true;
								for (String prof : profesionals) { %>
									<%=(first) ? "" : "&nbsp;|&nbsp;"%> <span class="professionalsAllPromos"><%=prof%></span>
							<%  	first = false;
								} %>
					</div>
					<div class="seeAllPromo" style="border-top:dotted 1px #8d8d8d; width:920px; height:22px; margin:10px 0; float:left;"><a href="./detallePromocion.jsp?id=<%=pvo.getId() %>">Ver detalles completos y contactar profesionales</a></div>
				</div>
			<% } %>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>