<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="../includes/userLogged.jspf" %>
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
#links {
	top:402px;
	left:-140px;
}
#btn_unselected {
	width:194px;
	height:62px;
	top:360px;
	left:540px;
	position:relative;
	z-index:4;
}
-->
</style>
</head>
<%@ include file="../includes/pageStructureMP.jsp" %>
<!-- Catalog -->
<div id="layerContent" class="layerOnTop" style="display: none;">
	<div id="tradeOff">
		<div id="links" align="center"><a href="catalog.jsp">back to the Prize selection</a></div>
		<div id="btn_unselected"><a href="checkout.jsp" title="Checkout now!"><img src="../images/skin_nrg2/buttons/btn_checkout.png" width="194" height="62"></a></div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="../includes/legalesLayer.jsp" %>
</body>
</html>