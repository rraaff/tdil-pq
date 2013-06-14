<body onLoad="javascript:showContent();">
<div id="structure">
	<div id="content">

	</div>
	<div id="footer">
		<%
			if (logged && websiteUser.appliesToActivity("EMileage")) { com.tdil.chivas.co.utils.ThalamusWebUtils.loginToActivity(websiteUser, "EMileage");
		%>
			<div id="insertCodeButton">
				<ul>
					<li class="icb_logoAtFooter"><img src="../images/skin_nrg2/logos/nrg_on_footer.png"></li>
					<li class="icb_buttonCode"><a href="mp/cupon.jsp" title="Insert your code now!"></a></li>
				</ul>
			</div>
		<% } else { %>
			<div class="signInButtons"><img src="../images/skin_nrg2/logos/nrg_on_footer.png"></div>
		<% } %>
		<div class="theThalamusLogo"><a href="http://www.thalamuscorp.com/" title="Thalamus driven" target="_blank"><img src="../images/skin_nrg2/logos/thalamus_diven.png" alt="Thalamus driven"></a></div>
	</div>
</div>