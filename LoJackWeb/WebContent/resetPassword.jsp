<%@page import="com.tdil.lojack.utils.ThalamusErrorFormatter"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="resetPassword"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><% if (websiteUser != null && websiteUser.isLogged()) { %> 
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %><html>
<head>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/errorJS.jsp"%>
<script>
$(document).ready(
	function(){
	
		$( "#closeresetPassLayer" ).click(function() {
			$( "#resetPassLayer" ).fadeOut();
		});
	}
);

function register() {
	centerLayer($(window), $( "#resetPassLayer" ));
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
</head>
<body onLoad="javascript:register();">
<div id="structure">
	<div id="content">

	</div>
	<div id="footer">
		<div class="signInButtons"><img src="images/skin_nrg2/logos/nrg_on_footer.png"></div>
		<div class="theThalamusLogo"><a href="http://www.thalamuscorp.com/" title="Thalamus driven" target="_blank"><img src="images/skin_nrg2/logos/thalamus_diven.png" alt="Thalamus driven"></a></div>
	</div>
</div>
<!-- Edit password layer -->
<div id="resetPassLayer" class="layerOnTop" style="display: none; z-index: 500;">
	<div class="resetPassNewLayerStyles editProfileLayer">
		<div class="resetPassNewLayerContent">
			<html:form method="POST" action="/resetPassword">
				<%=ThalamusErrorFormatter.getErrorFrom(request, "token.err")%>
				<div class="myRow">
					<div class="myLabel width100">&nbsp;</div>
					<div class="myLabel width100">User</div>
					<div class="myLabel width150"><html:text name="ResetPasswordForm" property="username" styleClass="normalField width120"/></div>
				</div>
				<%=ThalamusErrorFormatter.getErrorFrom(request, "principal.err")%>
				<div class="myRow">
					<div class="myLabel width100">&nbsp;</div>
					<div class="myLabel width100">Password</div>
					<div class="myLabel width150"><html:password name="ResetPasswordForm" property="password" styleClass="normalField width120"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width100per" align="center"><input type="submit" id="submitregister" value="Submit"></div>
				</div>
			</html:form>
		</div>
	</div>
</div>
</body>
</html>