<%@page import="com.tdil.lojack.utils.LoJackWebUtils"%>
<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li class="logoContainer">
			<% if (usingMobile) { %>
				<a href="./mobile/home.jsp" class="logo" title="Volver al inicio"><img src="images/skin_lj_rl/logos/lo-jack_mainLogo.png"/></a></li>
			<% } else { %>
				<a href="home.jsp" class="logo" title="Volver al inicio"><img src="images/skin_lj_rl/logos/lo-jack_mainLogo.png"/></a></li>
			<% } %>
			<li class="toRight tabParking"><a href="productoParkings.jsp" title="Utiliz� la App gratuita y encontr� donde estacionar en CABA">Parking</a></li>
			<% if (websiteUser.isPetUser()) { %>
				<li class="toRight tabPet"><a href="#" id="enterPets" title="Cuid� a tu mascota">Pets</a></li>
			<% } else { %>
				<% if (usingMobile || isAndroid) { %>
					<li class="toRight tabPet"><a href="mobile/videoPagePets.jsp" title="M�s sobre PETS">Pets</a></li>
				<% } else { %>
					<li class="toRight tabPet"><a href="#" onclick="javascript:showVideo1('pets');" title="M�s sobre PETS">Pets</a></li>
				<% } %>
			<% } %>
			<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
				<% if (usingMobile || isAndroid) { %>
					<!-- logueado y con acceso a prevent -->
					<li class="toRight tabCar"><a href="javascript:loginPrevent();" title="Administrar tus autos">Car</a></li>
				<% } else { %>
					<li class="toRight tabCar"><a href="#" id="enterPrevent" title="Administrar tus autos">Car</a></li>
				<% }  %>
			<%} else { %>
				<!-- no logueado o sin acceso a prevent -->
				<li class="toRight tabCar"><a href="#" onclick="javascript:showVideo1('car');" title="M�s sobre CAR">Car</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight tabHome"><a href="productoHome.jsp" title="Administr� tus alarmas, luces y c�maras">Home</a></li>
			<% } else { %>	
				<% if (usingMobile || isAndroid) { %>
					<li class="toRight tabHome"><a href="mobile/videoPageHome.jsp" title="M�s sobre HOME">Home</a></li>
				<% } else { %>
					<li class="toRight tabHome"><a href="#" onclick="javascript:showVideo1('home');" title="M�s sobre HOME">Home</a></li>
				<% } %>
			<% } %>
		</ul>
	</div>
</section>