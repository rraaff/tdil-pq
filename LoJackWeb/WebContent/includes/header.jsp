<header class="menuInternas" style="display:inline-block;">
	<div class="userLoggedThanlamusMenu">
		<ul class="correctNav">
			<li class="avatarLi"><a href="javascript:changeAvatar();"><% if (websiteUser.getModelUser().getIdAvatar() != null && !websiteUser.getModelUser().getIdAvatar().equals(0)) { %>
				<img id="avatarImg" src="./download.st?id=<%=websiteUser.getModelUser().getIdAvatar()%>&type=PUBLIC&ext=<%=websiteUser.getModelUser().getExtAvatar()%>" width="30" height="30" align="absmiddle"> 
			<% } else { %>
				<img id="avatarImg" src="images/skin_lj_rl/logos/avatarBase.png" width="32" height="32" align="absmiddle"> 
			<% } %></a></li>
			<li>Usuario: <span class="userName"><%=websiteUser.getName()%></span></li>
			<li class="toRight"><a href="logout.do" title="Salir">Salir</a></li>
			<li class="toRight"><a href="javascript:changePassword();" title="Cambiar mi clave">Cambiar mi clave</a></li>
			<li class="toRight"><a href="javascript:updatePerson();" title="Cambiar mis datos">Cambiar mis datos</a></li>
		</ul>
	</div>
</header>