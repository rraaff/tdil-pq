<%@page import="com.tdil.lojack.vlu.model.VLUDataDTO"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VechiclesVLUMessagesForm"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.lojack.vlu.VLUUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.lojack.model.VLUData"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><script>

$( "#closevluMessagesLayer" ).click(function() {
	$( "#vehiclesVLUMessagesLayer" ).fadeOut();
});

</script>
<% VechiclesVLUMessagesForm selectVehiclesForm = (VechiclesVLUMessagesForm)session.getAttribute("VechiclesVLUMessagesForm");%>
<div id="xContainer"><button id="closevluMessagesLayer">X</button></div>
<h3>Mensaje de LoJack</h3>
<div id="tableStyle">
	<fieldset class="tableHeader">
		<label class="w1">Patente</label>
		<label class="w1"></label>
		<label class="w1">Mensaje</label>
	</fieldset>
	<% for (VLUDataDTO vluData : selectVehiclesForm.getVlDataDTOs()) { %>
		<fieldset>
			<label class="w1"><%=vluData.getDomain() %></label>
		</fieldset>
		<% if (StringUtils.isEmpty(vluData.getMessage())) { %>
			<fieldset>
				<label class="w1"></label>
			</fieldset>
			<fieldset>
				<label class="w1"></label>
			</fieldset>
		<% } else { %>
			<fieldset>
				<label class="w1">X</label>
			</fieldset>
			<fieldset>
				<label class="w1"><%=vluData.getMessage() %></label>
			</fieldset>
		<% } %>
	<% } %>
</div>
<%@ include file="includes/catchModal.jspf" %>