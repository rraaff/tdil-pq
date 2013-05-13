<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li class="logo" title="Lo-Jack, Lo tuyo es tuyo"><a href="home.jsp" title="Volver al inicio"></a></li>
			<li class="toRight"><a href="productoParkings.jsp" title="Utilizá la App gratuita y encontrá donde estacionar en CABA">Parking</a></li>
			<% if (websiteUser.isPetUser()) { %>
				<li class="toRight"><a href="#" title="Cuidá a tu mascota">Pets</a></li>
			<% } else { %>
				<li class="toRight"><a href="#" title="¿No tenes PET? Adquirilo acá">Pets</a></li>
			<% } %>
			<% if (websiteUser.isPreventUser()) { %>
				<li class="toRight"><a href="productoPrevent.jsp" title="Administrar tus autos">Prevent</a></li>
			<% } else { %>
				<li class="toRight"><a href="#" title="¿No tenes PREVENT? Adquirilo acá">Prevent</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight optHome"><a href="productoHome.jsp" title="Administrá tus alarmas, luces y cámaras">Home</a></li>
			<% } else { %>
				<li class="toRight optHome"><a href="#" title="¿No tenes HOME? Adquirilo acá">Home</a></li>
			<% } %>
		</ul>
	</div>
</section>