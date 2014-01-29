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
	top:312px;	
}
#select {
	width:181px;
	height:125px;
	top:128px;
	left:410px;
	position:relative;
	z-index:5;
}
#select:hover {
	background-image: url(images/skin_nrg/selected.jpg);
	background-repeat: no-repeat;
	background-position: center center;
}
#btn_unselected {
	width:234px;
	height:82px;
	top:306px;
	left:590px;
	position:relative;
	z-index:4;
}
-->
</style>
</head>
<%@ include file="../includes/pageStructureMP.jsp" %>
<!-- Catalog -->
<div id="layerContent" class="layerOnTop" style="display: none;">
	<div id="catalog">
		<div id="select"><a href="catalogSelected.jsp"><img src="../images/skin_nrg2/null.gif" width="181" height="125"></a></div>
		<div id="btn_unselected"><img src="../images/skin_nrg2/buttons/btn_tradeOff_off.png" width="234" height="82"></div>
		<div id="links" align="center"><a href="../index.jsp">back to the home page</a>  .  <a href="myAccount.jsp">My Account</a>  .  <a href="shipments.jsp">Shipment Status</a>  .  <a href="javascript:verLegales();" id="legales" title="Legal">Legal</a></div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="../includes/legalesLayer.jsp" %>
</body>
</html>