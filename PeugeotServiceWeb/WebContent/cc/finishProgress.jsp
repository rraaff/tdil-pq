<%@page import="com.tdil.ljpeugeot.model.valueobjects.AlertValueObject"%><%--
--%><%@page import="com.tdil.ljpeugeot.services.PeugeotService"%><%--
--%><%@page import="com.tdil.ljpeugeot.model.Alert"%><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%
	Integer idAlert =  Integer.parseInt(request.getParameter("alertId"));
	PeugeotService.finishAlertProgress(idAlert);
	response.sendRedirect(request.getContextPath() + "/cc/home.jsp");
%>