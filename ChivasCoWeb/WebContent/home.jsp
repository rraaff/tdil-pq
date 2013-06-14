<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){

		<%@ include file="includes/closeLegalesLayer.jsp" %>
	}
);

<%@ include file="includes/openLegalesLayer.jsp" %>

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
</head>
<body>
<div id="structure">
	<div id="content">
		<!-- top menu -->
		<%
			if (logged && websiteUser.appliesToActivity("EMileage")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "EMileage");
		%>
			<%@ include file="includes/EMileageMenu.jsp" %>
		<%
			} else {
		%>
			<%@ include file="includes/regularMenu.jsp" %>
		<%
			}
		%>
		<!-- Activities content -->
		<div id="activities">
			<%
				if (websiteUser.appliesToActivity("BarNights")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "BarNights");
			%>
				<%
					if (websiteUser.appliesToActivity("University")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "University");
				%>
					<%
						if (websiteUser.appliesToActivity("NRG")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "NRG");
					%>
						<!-- composición 1 + 2 + 3 -->
						<div id="activity01" style="background-position: -80px -40px;">&nbsp;</div>
						<div id="activity02" style="background-position: -80px -45px;">&nbsp;</div>
						<div id="activity03" style="background-position: -100px -30px;">&nbsp;</div>
					<%
						} else {
					%>
						<!-- composición 1 + 2 -->
						<div id="activity01" style="background-position: 90px -40px;">&nbsp;</div>
						<div id="activity02" style="background-position: -200px -45px;">&nbsp;</div>
					<%
						}
					%>
				<%
					} else {
				%>
					<%
						if (websiteUser.appliesToActivity("NRG")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "NRG");
					%>
						<!-- composición las 1 + 3 -->
						<div id="activity01" style="background-position: 450px -60px;">&nbsp;</div>
						<div id="activity03" style="background-position: -240px -30px;">&nbsp;</div>
					<%
						} else {
					%>
						<!-- composición 1 -->
						<div id="activity01" style="background-position: 250px -60px;">&nbsp;</div>
					<%
						}
					%>
				<%
					}
				%>
			<%
				} else {
			%>
				<%
					if (websiteUser.appliesToActivity("University")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "University");
				%>
					<%
						if (websiteUser.appliesToActivity("NRG")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "NRG");
					%>
						<!-- composición 2 + 3 -->
						<div id="activity02" style="background-position: -200px -40px;">&nbsp;</div>
						<div id="activity03" style="background-position: -240px -35px;">&nbsp;</div>
					<%
						} else {
					%>
						<!-- composición 2 -->
						<div id="activity02" style="background-position: -400px -80px;">&nbsp;</div>
					<%
						}
					%>
				<%
					} else {
				%>
					<%
						if (websiteUser.appliesToActivity("NRG")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "NRG");
					%>
						<!-- composición 3 -->
						<div id="activity03">&nbsp;</div>
					<%
						} else {
					%>
						<!-- neutral -->
						<div id="activityNN">&nbsp;</div>
					<%
						}
					%>
				<%
					}
				%>
			<%
				}
			%>
		</div>
		<!-- Activities content end -->
	</div>
	<div id="footer">
		<%
			if (logged && websiteUser.appliesToActivity("EMileage")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "EMileage");
		%>
			<div id="insertCodeButton">
				<ul>
					<li class="icb_logoAtFooter"><img src="images/skin_nrg2/logos/nrg_on_footer.png"></li>
					<li class="icb_buttonCode"><a href="mp/cupon.jsp" title="Insert your code now!"></a></li>
				</ul>
			</div>
		<% } else { %>
			<div class="insertCodeButton"><img src="images/skin_nrg2/logos/nrg_on_footer.png"></div>
		<% } %>
		<div class="theThalamusLogo"><a href="http://www.thalamuscorp.com/" title="Thalamus driven" target="_blank"><img src="images/skin_nrg2/logos/thalamus_diven.png" alt="Thalamus driven"></a></div>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>
