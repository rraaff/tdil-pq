<%@page import="com.tdil.lojack.utils.LoJackWebUtils"%>
<div id="laRuedita">
	<div class="fakeRuedita">
		<%if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoLogout"><a href="logout.do" title="Salir del sistema"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<div id="iconoLogin"><a href="#" id="rueditaLogin" onclick="javascript:login();" title="Ingresar ahora"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoParkings"><a href="productoParkings.jsp" title="Utilizá la App gratuita y encontrá donde estacionar en CABA"><img src="images/null.gif" /></a></div>
		<%} else { %> 
			<div id="iconoParkings"><a href="#" id="rueditaParkings" onclick="javascript:parkingsNotLogged();" title="Ingresá y utilizá la App gratuita para estacionar en CABA"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged()) { %>
			<div id="iconoProfile"><a href="#" onclick="javascript:updatePerson();" title="Modificá tus datos"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<div id="iconoProfile"><a href="#" onclick="javascript:register();" id="register" title="Registrate gratis"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
			<% if (LoJackWebUtils.isMobile(request)) { %>
				<!-- logueado y con acceso a prevent -->
				<% if (websiteUser.isPreventLogged()) { %>
					<div id="iconoCar"><a href="./productoPrevent.jsp" title="Administrar tus autos"><img src="images/null.gif" /></a></div>
				<% } else { %>
					<div id="iconoCar"><a href="javascript:loginPrevent()" title="Administrar tus autos"><img src="images/null.gif" /></a></div>
				<% } %>
			<% } else { %>
				<div id="iconoCar"><a href="#" id="enterPrevent" title="Administrar tus autos"><img src="images/null.gif" /></a></div>
			<% }  %>
		<%} else { %>
			<!-- no logueado o sin acceso a prevent -->
			<div id="iconoCar"><a href="#" onclick="javascript:showVideo1('car');" title="Más sobre CAR"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isHomeUser()) { %>
			<!-- logueado y con acceso a home -->
			<div id="iconoHome"><a href="productoHome.jsp" title="Administrá tus alarmas, luces y cámaras"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<!-- no logueado o sin acceso a home -->
			<div id="iconoHome"><a href="#" onclick="javascript:showVideo1('home');" title="Más sobre HOME"><img src="images/null.gif" /></a></div>
		<%} %>

		<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPetUser()) { %>
			<!-- logueado y con acceso a pet -->
			<div id="iconoPets"><a href="#" id="enterPets" title="Cuidá a tus mascostas"><img src="images/null.gif" /></a></div>
		<%} else { %>
			<!-- no logueado o sin acceso a pet -->
			<div id="iconoPets"><a href="#" onclick="javascript:showVideo1('pets');" title="Más sobre PETS"><img src="images/null.gif" /></a></div>
		<%} %>
	</div>
	<div id="centralRuedita">
		<span id="centralRueditaLogin" class="centralRueditaContent">Ingresar</span>
		<span id="centralRueditaParkings" class="centralRueditaContent">Parkings</span>
	</div>
</div>
<div id="losIconos"></div>
<div id="elTelefono" align="center"><img src="images/skin_lj_rl/backs/phone.png" /></div>
<div id="laSombreaT"><img src="images/skin_lj_rl/backs/sombraTelefono.png" /></div>
<div id="welcomeText"></div>