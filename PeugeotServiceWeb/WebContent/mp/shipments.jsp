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
	top:456px;
	left:0px;
}
-->
</style>
</head>
<%@ include file="../includes/pageStructureMP.jsp" %>
<!-- Catalog -->
<div id="layerContent" class="layerOnTop" style="display: none;">
	<div id="shipments">
		<div id="links" align="center"><a href="../index.jsp">back to the home page</a>  .  <a href="myAccount.jsp">My Account</a>  .  <a href="catalog.jsp">Rewards Catalog</a>  .  <a href="javascript:verLegales();" id="legales" title="Legal">Legal</a></div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="../includes/legalesLayer.jsp" %>
</body>
</html>