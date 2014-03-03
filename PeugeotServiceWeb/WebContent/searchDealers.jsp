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
	<div class="table_services">
		<ul class="table_header">
			<li class="fixed50"></li>
			<li class="dealer">Concesionaria/Service</li>
			<li class="dealer_address">Dirección</li>
			<li class="dealer_phone">Teléfonos</li>
			<li class="dealer_email">E-Mails</li>
		</ul>
		<% for (Dealer dealer : dealers) { %>
			<ul class="table_body">
				<li class="fixed50"><input type="radio" name="selectradio" onclick="selectDealer(<%=dealer.getId()%>)"></li>
				<li class="dealer"><%=dealer.getName()%></li>
				<li class="dealer_address"><%=StringUtils.notNullValueOf(dealer.getAddress())%></li> 
				<li class="dealer_phone"><%=StringUtils.notNullValueOf(dealer.getPhone())%></li>
				<li class="dealer_email"><%=StringUtils.notNullValueOf(dealer.getEmail())%></li>
			</ul>
		<% } %>
	</div>
	<input type="hidden" id="idDealer" name="idDealer">
	<fieldset class="button_bar pOnlyTop25">
		<button class="botton_ahead" type="submit" >Selectionar para service<span></span></button>
	</fieldset>
</form>

<%@ include file="includes/catchModal.jspf" %>