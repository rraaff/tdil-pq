<section id="productsMenu">
	<div class="userLoggedThalamusMenu">
		<ul class="correctNav">
			<li class="logo" title="Lo-Jack, Lo tuyo es tuyo"></li>
			<li class="toRight"><a href="#" title="¿Dónde estacioar? Te ayudamos a encontrar un lugar">Parking</a></li>
			<% if (websiteUser.isPetUser()) { %>
				<li class="toRight"><a href="#" title="Cuidá a tu mascota">Pets</a></li>
			<% } else { %>
				<li class="toRight"><a href="#" title="¿No tenes PET? Adquirilo acá">Pets</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight"><a href="#" title="Monitoreá sus vehículos">Prevent</a></li>
			<% } else { %>
				<li class="toRight"><a href="#" title="¿No tenes PREVENT? Adquirilo acá">Prevent</a></li>
			<% } %>
			<% if (websiteUser.isHomeUser()) { %>
				<li class="toRight optHome"><a href="productoHome.jsp" title="Administrá sus alarmas, luces y cámaras">Home</a></li>
			<% } else { %>
				<li class="toRight optHome"><a href="#" title="¿No tenes HOME? Adquirilo acá">Home</a></li>
			<% } %>
		</ul>
	</div>
</section>