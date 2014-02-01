<section id="nmCentral">
	<div id="nmWrapper">
		<div id="nmMainObjects">
			<div id="nmLogo"><img src="images/skin_lj_rl/logos/lo-jack_2.png" /></div>
			<div id="nmTelefono"><!--  align="center" -->
				<div id="nmRuedita">
					<ul>
						<%@page import="com.tdil.ljpeugeot.utils.LJPeugeotWebUtils"%>
						<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
							<% if (usingMobile || isAndroid) { %>
								<!-- logueado y con acceso a prevent -->
								<li id="liCars" class="car" ><a class="rdCar" href="javascript:loginPrevent()" onmouseover="chbg('liCars', 'car', 'over', 'Car', 'Ingresá ahora')" onmouseout="chbg('liCars', 'car', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>
							<% } else { %>
								<li id="liCars" class="car" ><a class="rdCar" href="#" id="enterPrevent" onmouseover="chbg('liCars', 'car', 'over', 'Car', 'Ingresá ahora')" onmouseout="chbg('liCars', 'car', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>
							<% }  %>
						<%} else { %>
							<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.getVluMessages() > 0) { %>
								<!-- esta logueado, no es usuario de prevent y tiene mensajes asociados -->
								<li id="liVluMessages" class="car" ><a class="rdCar" href="#" onclick="javascript:showVluMessages('<%=websiteUser.%>');" onmouseover="chbg('liVluMessages', 'car', 'over', 'Car', 'Ver avisos de VLU')" onmouseout="chbg('liVluMessages', 'car', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>
							<% } %>
						
						<%if (websiteUser != null && websiteUser.isLogged()) { %>
							<li id="liPark" class="park"><a class="rdPark" href="productoParkings.jsp" onmouseover="chbg('liPark', 'parking', 'over', 'Parking', 'Estacioná en<br/>CABA y GBA')" onmouseout="chbg('liPark', 'parking', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>
						<%} else { %>
							<li id="liPark" class="park"><a class="rdPark" href="#" id="rueditaParkings" onclick="javascript:parkingsNotLogged();" onmouseover="chbg('liPark', 'parking', 'over', 'Parking', 'Estacioná en<br/>CABA y GBA')" onmouseout="chbg('liPark', 'parking', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>
						<%} %>

						<li id="liTvtv" class="tv"><a class="rdTv" href="http://www.lojack.tv" target="_blank" onmouseover="chbg('liTvtv', 'tv', 'over', 'LOJACK TV', 'Ingresar ahora')" onmouseout="chbg('liTvtv', 'tv', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>

						<li id="liLogg" class="login"><a class="rdLoginLogout" href="#" id="rueditaLogin" onclick="javascript:login();" onmouseover="chbg('liLogg', 'login', 'over', 'Ingresá', 'con tus datos')" onmouseout="chbg('liLogg', 'login', 'off', 'Seleccione', 'Una Aplicación')"><img src="images/null.gif" /></a></li>

						<% } %>
					</ul>
				</div>
				<div id="nmPhoneImgContainer"><img src="images/skin_lj_rl/backs/phone_h.png" /></div>
			</div>
			<div id="nmWelcomeText">
				<h1>Viví­ LoAPP 1.1</h1>
				<p>El Primer sistema de tecnología del mundo que integra la seguridad de tu auto, tu casa y tu familia</p>
			</div>
		</div>
		<div id="rdCentral">
			<h2 id="title">Seleccione</h2>
			<h3 id="subTitle">Una Aplicación</h3>
		</div>
		<div id="rdBase"><img src="images/skin_lj_rl/newWheel/base_452.png" /></div>
		<div id="rdPhotoSlider"><img src="images/skin_lj_rl/sliders/couple_1.jpg" /></div>
		<div id="nmSombraTel"><img src="images/skin_lj_rl/backs/sombraTelefonoH.png" /></div>
	</div>
</section>

<section id="nmRuedaHelper">
	<div id="rhWrapper">
		<ul>
			<%if (websiteUser != null && websiteUser.isLogged() && websiteUser.isPreventUser()) { %>
				<% if (usingMobile || isAndroid) { %>
					<!-- logueado y con acceso a prevent -->
					<li><a class="car" href="javascript:loginPrevent()">car</a></li>
				<% } else { %>
					<li><a class="car" href="#" id="enterPrevent" >car</a></li>
				<% }  %>
			<% } %>
			<%if (websiteUser != null && websiteUser.isLogged()) { %>
				<li><a class="park" href="productoParkings.jsp" onclick="javascript:parkingsNotLogged();">parking</a></li>
			<%} else { %>
				<li><a class="park" href="#" id="rueditaParkings" onclick="javascript:parkingsNotLogged();">parking</a></li>
			<%} %>
		</ul>
	</div>
</section>