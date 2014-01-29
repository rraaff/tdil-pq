<%@ include file="../includes/userLogged.jspf" %>
<%@ include file="../includes/mustBeLogged.jspf" %>
<%@page import="com.tdil.lojack.utils.ThalamusJClientWebErrorFormatter"%>
<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<%@ include file="../includes/headMP.jsp" %>

<script>
$(document).ready(
	function(){

		<%@ include file="../includes/closeLegalesLayer.jsp" %>
	}
);

<%@ include file="../includes/openLegalesLayer.jsp" %>

function showContent() {
	centerLayer($(window), $( "#layerContent" ));
}

function centerLayer(objWin, objLayer) {
	var top = (objWin.height() / 2) - (objLayer.height() / 2);
	var left = (objWin.width() / 2) - (objLayer.width() / 2);
	objLayer.css({
		position: 'absolute',
		top: top + 'px',
		left: left + 'px'
	}).fadeIn(500);
}

</script>

<style type="text/css">
<!--
.renglon {
	padding-bottom:15px;
}
-->
</style>
</head>
<%@ include file="../includes/pageStructureMP.jsp" %>
<!-- Catalog -->
<div id="layerContent" class="layerOnTop" style="display: none;">
	<div id="form">
		<div class="renglon width440" style="margin-top:155px;">
			<div class="label width20">&nbsp;</div>
			<div class="label width100" style="padding-top:5px;">Code</div>
			<div class="label width300"><input type="text" class="normalField width200"/></div>
		</div>
		<div class="renglon width440">
			<div class="label width20">&nbsp;</div>
			<div class="label width180" style="padding-top:30px;"><a href="index.jsp" title="Cancel registration and back home">Back home</a></div>
			<div class="label width60" style="padding-top:30px;">or</div>
			<div class="label width180 "><a href="cupon_approved.jsp" title="Send your code"><img src="../images/skin_nrg2/buttons/btn_send_at_form.png" width="174" height="76"/></a></div>
		</div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="../includes/legalesLayer.jsp" %>
</body>
</html>