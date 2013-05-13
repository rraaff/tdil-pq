<div id="laRuedita">
	<div class="fakeRuedita">
		<%if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoLogin"><a href="logout.do" title="Salir del sistema"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<div id="iconoLogin"><a href="javascript:login();" title="Ingresar ahora"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoParkings"><a href="productoParkings.jsp" title="Utilizá la App gratuita y encontrá donde estacionar en CABA"><img src="images/null.gif" /></a></div>
		<%} else { %> 
			<div id="iconoParkings"><a href="javascript:parkingsNotLogged();" title="Ingresá y utilizá la App gratuita para estacionar en CABA"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoProfile"><a href="./goToUpdatePerson.do" title="Cambiar mis datos"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<div id="iconoProfile"><a href="javascript:register();" id="register" title="Registrate gratis"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
			<!-- logueado y con acceso a prevent -->
			<div id="iconoCar"><a href="productoPrevent.jsp" title="Administrar tus autos"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<!-- no logueado o sin acceso a prevent -->
			<div id="iconoCar"><a href="javascript:showVideo1()" title="Más sobre CAR"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isHomeUser()) { %>
			<!-- logueado y con acceso a home -->
			<div id="iconoHome"><a href="productoHome.jsp" title="Administrá tus alarmas, luces y cámaras"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<!-- no logueado o sin acceso a home -->
			<div id="iconoHome"><a href="javascript:showVideo1()" title="Más sobre HOME"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPetUser()) { %>
			<!-- logueado y con acceso a pet -->
			<div id="iconoPets"><a href="javascript:showVideo1()" title="Cuidá a tus mascostas"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<!-- no logueado o sin acceso a pet -->
			<div id="iconoPets"><a href="javascript:showVideo1()" title="Más sobre PETS"><img src="images/null.gif" /></a></div>
		<%} %>
	</div>
</div>