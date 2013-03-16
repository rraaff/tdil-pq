<body onLoad="javascript:showContent();">
<div id="structure">
	<div id="content">

	</div>
	<div id="footer">
		<%
			if (logged && websiteUser.appliesToActivity("EMileage")) { com.tdil.lojack.utils.ThalamusWebUtils.loginToActivity(websiteUser, "EMileage");
		%>
			<div class="signInButtons" style="width:760px;"><img src="../images/skin_nrg2/logos/nrg_on_footer.png" width="446" height="119"><a href="cupon.jsp" title="Insert your code now!"><img src="../images/skin_nrg2/buttons/btn_cupon.png" width="314" height="99"></a></div>
		<% } else { %>
			<div class="signInButtons"><img src="../images/skin_nrg2/logos/nrg_on_footer.png"></div>
		<% } %>
		<div class="theThalamusLogo"><a href="http://www.thalamuscorp.com/" title="Thalamus driven" target="_blank"><img src="../images/skin_nrg2/logos/thalamus_diven.png" alt="Thalamus driven"></a></div>
	</div>
</div>