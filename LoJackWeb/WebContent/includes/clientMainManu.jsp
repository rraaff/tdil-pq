<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li title="Lo-Jack, Lo tuyo es tuyo"><a href="home.jsp" class="logo" title="Volver al inicio"><img src="images/null.gif" width="173" height="91"/></a></li>
			<li class="toRight"><a href="productoParkings.jsp" title="Utilizá la App gratuita y encontrá donde estacionar en CABA">Parking</a></li>
			<% if (websiteUser.isPetUser()) { %>
				<li class="toRight tabPet"><a href="#" title="Cuidá a tu mascota">Pets</a></li>
			<% } else { %>
				<li class="toRight tabPet"><a href="#" title="¿No tenes PET? Adquirilo acá">Pets</a></li>
			<% } %>
			<% if (websiteUser.isPreventUser()) { %>
				<li class="toRight tabCar"><a href="productoPrevent.jsp" title="Administrar tus autos">Car</a></li>
			<% } else { %>
				<li class="toRight tabCar"><a href="#" title="¿No tenes PREVENT? Adquirilo acá">Car</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight tabHome"><a href="productoHome.jsp" title="Administrá tus alarmas, luces y cámaras">Home</a></li>
			<% } else { %>
				<li class="toRight tabHome"><a href="#" title="¿No tenes HOME? Adquirilo acá">Home</a></li>
			<% } %>
		</ul>
	</div>
</section>