<header>
	<div class="userLoggedThalamusMenu">
		<!-- if user logged -->
		<ul class="correctNav">
			<li>Usuario: <span class="userName">Nombre</span></li>
			<li class="toRight"><a href="javascript:login();" id="login" title="Access now!">Login</a></li>
			<li class="toRight"><a href="javascript:forgotPassword();" id="forgotPassword" title="Forgot your password? Enter here to recover it">Password recovery</a></li>
			<li class="toRight"><a href="javascript:verLegales();" id="legales" title="Legales">Legal</a></li>
			<li class="toRight"><a href="javascript:register();" id="register" title="Registrate gratis">Registrate ahora</a></li>
			<li class="toRight"><a href="<%=ThalamusClientBeanFacade.getFacebookLogin().getUrl()%>" id="fb" title="Registrate con tu cuenta de Facebook">Ingresa con tu Facebook</a></li>
			<li class="toRight"><a href="<%=ThalamusClientBeanFacade.getTwitterLogin().getUrl()%>" id="fb" title="Registrate con tu cuenta de Twitter">Ingresa con tu Twitter</a></li>
			<li class="toRight"><a href="#" title="Salir del sistema">Salir</a></li>
			<li class="toRight"><a href="#" title="Cambiar mis clave">Cambiar mi clave</a></li>
			<li class="toRight"><a href="#" title="Cambiar mis datos">Cambiar mis datos</a></li>
		</ul>
		<!-- End IF -->
	</div>
</header>