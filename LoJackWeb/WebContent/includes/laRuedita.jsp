<section id="contentSlider">
	<div id="sliderContainer">
		<div id="slider">
			<div id="slide1" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide2" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide3" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide4" class="slide page2"><a href="#" title="">&nbsp;</a></div>
			<div id="slide5" class="slide page2"><a href="#" title="">&nbsp;</a></div>
		</div>
		<div id="sliderControl"><img src="images/front.png" width="40px;" id="right"/>
		<img src="images/back.png" width="40px;"  id="left"/></div>
	</div>
</section>

<div id="laRuedita">
	<div class="fakeRuedita">
		<% if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoLogin"><a href="logout.do" title="Salir del sistema"><img src="images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoLogin"><a href="javascript:login();" title="Ingresar ahora"><img src="images/null.gif" /></a></div>
		<% } %>

		<div id="iconoParkings"><a href="productoParkings.jsp" title="Utilizá la App gratuita y encontrá donde estacionar en CABA"><img src="images/null.gif" /></a></div>

		<% if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoProfile"><a href="./goToUpdatePerson.do" title="Cambiar mis datos"><img src="images/null.gif" /></a></div>
		<% } else { %>
			<div id="iconoProfile"><a href="javascript:register();" id="register" title="Registrate gratis"><img src="images/null.gif" /></a></div>
		<% } %>

		<% if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
			<!-- logueado y con acceso a prevent -->
			<div id="iconoCar"><a href="" title=""><img src="images/null.gif" /></a></div>
		<% } else { %>
			<!-- no logueado o sin acceso a prevent -->
			<div id="iconoCar"><a href="" title=""><img src="images/null.gif" /></a></div>
		<% } %>

		<% if (websiteUser != null && websiteUser.isLogged() && websiteUser.isHomeUser()) { %>
			<!-- logueado y con acceso a home -->
			<div id="iconoHome"><a href="" title=""><img src="images/null.gif" /></a></div>
		<% } else { %>
			<!-- no logueado o sin acceso a home -->
			<div id="iconoHome"><a href="" title=""><img src="images/null.gif" /></a></div>
		<% } %>

		<% if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPetUser()) { %>
			<!-- logueado y con acceso a pet -->
			<div id="iconoPets"><a href="" title=""><img src="images/null.gif" /></a></div>
		<% } else { %>
			<!-- no logueado o sin acceso a pet -->
			<div id="iconoPets"><a href="" title=""><img src="images/null.gif" /></a></div>
		<% } %>
	</div>
</div>

<div id="logoIndex">&nbsp;</div>
