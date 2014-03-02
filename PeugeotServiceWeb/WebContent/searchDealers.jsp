<%@page import="com.tdil.utils.StringUtils"%>
<%@page import="java.util.Collection"%>
<%@page import="com.tdil.ljpeugeot.model.Dealer"%>
<%@page import="com.tdil.ljpeugeot.services.DealersService"%>
<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="java.util.List"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%----%>
<script>

function selectDealer(dealerId) {
	$('#idDealer').val(dealerId);
}

</script>
<form action="./goToChangeDealer.do">
<% int idCity = Integer.parseInt(request.getParameter("idCity"));
Collection<Dealer> dealers = DealersService.getDealers(idCity);
%>
<% for (Dealer dealer : dealers) { %>
	<input type="radio" name="selectradio" onclick="selectDealer(<%=dealer.getId()%>)">
	<%=dealer.getName()%> 
	- <%=StringUtils.notNullValueOf(dealer.getAddress())%> 
	- <%=StringUtils.notNullValueOf(dealer.getPhone())%>
	- <%=StringUtils.notNullValueOf(dealer.getEmail())%><br>
<% } %>
<input type="hidden" id="idDealer" name="idDealer">
<input type="submit" Value="Seleccionar para mis service"><br>
</form>

<%@ include file="includes/catchModal.jspf" %>