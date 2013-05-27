<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li class="logoContainer"><a href="home.jsp" class="logo" title="Volver al inicio"><img src="images/skin_lj_rl/logos/lo-jack_mainLogo.png" width="173" height="91"/></a></li>
			<li class="toRight tabParking"><a href="productoParkings.jsp" title="Utiliz� la App gratuita y encontr� donde estacionar en CABA">Parking</a></li>
			<% if (websiteUser.isPetUser()) { %>
				<li class="toRight tabPet"><a href="#" title="Cuid� a tu mascota">Pets</a></li>
			<% } else { %>
				<li class="toRight tabPet"><a href="#" onclick="javascript:showVideo1();" title="M�s sobre PETS">Pets</a></li>
			<% } %>
			<% if (websiteUser.isPreventUser()) { %>
				<% if (websiteUser.isPreventLogged()) { %>
					<li class="toRight tabCar"><a href="./productoPrevent.jsp" title="Administrar tus autos">Car</a></li>
				<% } else { %>
					<li class="toRight tabCar"><a href="javascript:loginPrevent()" title="Administrar tus autos">Car</a></li>
				<% } %>
			<% } else { %>
				<li class="toRight tabCar"><a href="#" onclick="javascript:showVideo1();" title="M�s sobre CAR">Car</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight tabHome"><a href="productoHome.jsp" title="Administr� tus alarmas, luces y c�maras">Home</a></li>
			<% } else { %>
				<li class="toRight tabHome"><a href="#" onclick="javascript:showVideo1();" title="M�s sobre HOME">Home</a></li>
			<% } %>
		</ul>
	</div>
</section>