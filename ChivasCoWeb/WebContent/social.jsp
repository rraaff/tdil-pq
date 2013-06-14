<%@page import="com.tdil.chivas.co.servlet.FacebookConnect"%>
<%@page import="com.tdil.chivas.co.servlet.TwitterConnect"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.SocialConnectionsBean"%>
<%@page import="net.sf.json.JSON"%>
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

		$( "#closeresetPassLayer" ).click(function() {
			$( "#resetPassLayer" ).fadeOut();
		});
	}
);

function register() {
	centerLayer($(window), $( "#resetPassLayer" ));
}

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
<script type="text/javascript">
<!-- 
function newPage(num) {
	var url=new Array();
	url[0]="index.jsp";
	window.location=url[num];
}
// -->
</script>
</head>
<body onLoad="javascript:register();">
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
<!-- Edit password layer -->
<div id="resetPassLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="socialLayerStyles">
		<div class="socialLayerContent">
			<% SocialConnectionsBean socialConnectionsBean = new SocialConnectionsBean(((JSONObject)ThalamusClientFacade.getSocial(websiteUser.getToken())).getJSONArray("socialConnections"));%>
				<p align="center">
				<% if (socialConnectionsBean.hasFacebook()) { %>
					<a href="./facebookremove.do">Remove Facebook connection</a>
				<% } else { %>
					No Facebook: 
						<% URLHolder facebookAdd = ThalamusClientBeanFacade.getFacebookAdd(websiteUser.getToken());%>
						<a href="<%= facebookAdd.getUrl() %>" class="a_colored">Connect with my Facebook account</a>
					<% if ("1".equals((String)session.getAttribute(FacebookConnect.FACEBOOK_CONN_ERR))) { %>
						<br>El usuario de facebook ya tiene una cuenta
					<% session.removeAttribute(FacebookConnect.FACEBOOK_CONN_ERR);
						} %>
				<% } %>
				<br>
				<% if (socialConnectionsBean.hasTwitter()) { %>
					<a href="./twitterremove.do">Remove twitter connection <%=socialConnectionsBean.getTwitter().getDisplayName()%></a>
				<% } else { %>
					No twitter: <% URLHolder twitterAdd = ThalamusClientBeanFacade.getTwitterAdd(websiteUser.getToken());%>
					<a href="<%= twitterAdd.getUrl() %>">Connect with my Twitter account</a>
					<% if ("1".equals((String)session.getAttribute(TwitterConnect.TWITTER_CONN_ERR))) { %>
						<br>El usuario de twitter ya tiene una cuenta
					<% session.removeAttribute(TwitterConnect.TWITTER_CONN_ERR);
						} %>
			<% } %>
			</p>
		</div>
		<form action="#">
			<div class="myRow" align="center"><input type="button" onclick="newPage(0);" value="Back home"></div>
		</form>
	</div>
</div>
<!-- Layer legales -->
<%@ include file="includes/legalesLayer.jsp" %>
</body>
</html>