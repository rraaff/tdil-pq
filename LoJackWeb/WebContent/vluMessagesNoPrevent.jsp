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
<div class="vluMessages">
	<div id="xContainer"><button class="buttonLink" id="closevluMessagesLayer">X</button></div>
	<h3>Mensaje de LoJack</h3>
	<p class="">Tu equipo LoJack requiere mantenimiento, comunicate al 0800-122-5652</p>
	<div id="tableStyle">
		<fieldset class="tableHeader">
			<label class="w100 monoColumna">Patente</label>
		</fieldset>
		<% for (VLUData vluData : vluDataList) { %>
			<fieldset class="tableBody">
				<label class="w100 monoColumna"><%=vluData.getDomain() %></label>
			</fieldset>
		<% } %>
	</div>
<%@ include file="includes/catchModal.jspf" %>
</div>