<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script type="text/javascript">
  jQuery(function(){ 
    jQuery('.jbar').jbar();
  });
</script>
<div id="headers">
	<div id="header">
		<div id="logo"><a href="./boHome.jsp" title="Volver al Inicio"><img src="images/skin_basic/logos/headerLogo.png" /></a></div>
		<div id="centralhead">
			<div id="border"></div>
			<div id="taglineAndMenu">
				<p>Administraci&oacute;n del sitio</p>
				<ul class="nav">
					<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Profesionales</a>
						<ul>
							<li><html:link action="/goToGeoLevelAdministration">Geo Levels</html:link></li>
							<li><html:link action="/goToProfesionalAdministration" >Administraci&oacute;n</html:link></li>
						</ul>
					</li>
					<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Contenidos del sitio</a>
						<ul>
							<li><html:link action="/goToServiceCategoryABM" >Manejo de categorias de servicios</html:link></li>
							<li><html:link action="/goToProfesionalServiceABM" >Manejo de servicios</html:link></li>
							<li><html:link action="/goToProductCategoryABM" >Manejo de categorias de productos</html:link></li>
							<li><html:link action="/goToProfesionalProductABM" >Manejo de productos</html:link></li>
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
		</div>
	</div>
</div>