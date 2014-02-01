<%@page import="com.tdil.ljpeugeot.utils.LJPeugeotWebUtils"%>
<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li class="logoContainer">
			<% if (usingMobile) { %>
				<a href="./mobile/home.jsp" class="logo" title="Volver al inicio"><img src="images/skin_lj_rl/logos/lo-jack_mainLogo.png"/></a></li>
			<% } else { %>
				<a href="home.jsp" class="logo" title="Volver al inicio"><img src="images/skin_lj_rl/logos/lo-jack_mainLogo.png"/></a></li>
			<% } %>
			<li class="toRight tabParking"><a href="productoParkings.jsp" title="Utilizá la App gratuita y encontrá donde estacionar en CABA">Parking</a></li>
			<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
				<% if (usingMobile || isAndroid) { %>
					<!-- logueado y con acceso a prevent -->
					<li class="toRight tabCar"><a href="javascript:loginPrevent();" title="Administrar tus autos">Car</a></li>
				<% } else { %>
					<li class="toRight tabCar"><a href="#" id="enterPrevent" title="Administrar tus autos">Car</a></li>
				<% }  %>
			<% } %>
		</ul>
	</div>
</section>