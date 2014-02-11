<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.tdil.lojack.vlu.VLUUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.lojack.model.VLUData"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><script>
$( "#backvluMessagesLayer" ).click(function() {
	$( "#vluMessagesLayer" ).fadeOut();
});
$( "#closevluMessagesLayer" ).click(function() {
	$( "#vluMessagesLayer" ).fadeOut();
});
</script>
<% List<VLUData> vluDataList = VLUUtils.getVLUData(request.getParameter("dni"));%>
<div id="vluMessages" class="wsmodal">
	<div class="wsmHeader">
		<button id="backvluMessagesLayer" class="wsmButtonBack">&nbsp;</button>
		<span>Mensaje de LoJack</span>
		<button id="closevluMessagesLayer" class="wsmButtonClose">&nbsp;</button>
	</div>
	<div class="wsmBody">
		<span class="ajuteMensaje">
			<p class="information">Si el rastreador de su vehículo requiere mantenimiento, comuníquese al 0800-122-5652</p>
		</span>
		<div class="table">
			<ul class="thead">
				<li class="thcell width50per">Patente</li>
				<li class="thcell width50per">Mensaje</li>
			</ul>
			<% for (VLUData vluData : vluDataList) { %>
				<ul class="tbody">
					<li class="thcell width50per"><%=vluData.getDomain() %></li>
					<% if (StringUtils.isEmpty(vluData.getMessage())) { %>
						<li class="thcell width50per">&nbsp;</li>
					<% } else { %>
						<li class="thcell width50per"><%=vluData.getMessage() %></li>
					<% } %>
				</ul>
			<% } %>
		</div>
	</div>
	<%@ include file="includes/catchModal.jspf" %>
</div>