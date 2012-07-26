<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script type="text/javascript">
  jQuery(function(){ 
    jQuery('.jbar').jbar();
  });
</script>
<div id="portaMenu">
	<ul class="nav">
		<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Profesionales</a>
			<ul>
				<li><a href="geoLevelAdministration.jsp">Geo Levels</a></li>
				<li><html:link action="/goToProfesionalAdministration" >Administracion</html:link></li>
			</ul>
		</li>
		<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Contenidos del sitio</a>
			<ul>
				<li><html:link action="/goToProfesionalCategoryABM" >Manejo de categorias</html:link></li>
				<li><html:link action="/goToProfesionalServiceABM" >Manejo de servicios</html:link></li>
			</ul>
		</li>
		<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Administracion</a>
			<ul>
			
				<li><html:link action="/goToRawInsertABM" >Administracion de inserts</html:link></li>
				<li><html:link action="/goToNotificationEmailABM" >Notificaciones por email</html:link></li>
			</ul>
		</li>
		<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Sistema</a>
			<ul>
				<li><html:link action="/goToSystemPropertyABM" >Variables de sistema</html:link></li>
			</ul>
		</li>
		<li><html:link action="/logout" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Salir</html:link></li>
	</ul>
</div>