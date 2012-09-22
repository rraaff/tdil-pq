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
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style>
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
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
		</div>
		<div id="formContent">
			<div id="zoneNavigation">

			</div>
		</div>
	</div>
</div>
<p>
					<% for (PromotionValueObject pvo : PromotionUtils.getActivePromotionsWithSells()) { %>
						<% 
							List<String> profesionals = pvo.getUniqueProfesionals();
						%>
						<img src="./downloadThumb.st?width=428&height=385&id=<%=pvo.getFirstImageid()%>&type=PUBLIC&ext=<%=pvo.getFirstImageExt()%>" alt="" title="#htmlcaption<%=pvo.getId() %>" width="428" height="385" /><br>
						<a href="./detallePromocion.jsp?id=<%=pvo.getId() %>"><%=pvo.getName() %></a><br>
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
					<% } %>
					
				</p>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>