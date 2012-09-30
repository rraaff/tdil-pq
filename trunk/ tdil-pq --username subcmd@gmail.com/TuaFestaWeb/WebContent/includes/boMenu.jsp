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
				<ul class="myMenu">
					<li><a href="#">Profesionales</a>
						<ul>
							<li><html:link action="/goToProfesionalAdministration" >Administraci&oacute;n</html:link></li>
							<li><html:link action="/goToPromotionAdministration" >Promociones</html:link></li>
							<li><html:link action="/goToAdAdministration" >Avisos</html:link></li>
						</ul>
					</li>
					<li><a href="#">Contenidos del sitio</a>
						<ul>
							<li><html:link action="/goToServiceCategoryABM" >Categor&iacute;as de Servicios</html:link></li>
							<li><html:link action="/goToProductCategoryABM" >Categor&iacute;as de Productos</html:link></li>
							<li><html:link action="/goToHighlightedCategoryABM" >Categor&iacute;as Destacadas</html:link></li>
						</ul>
					</li>
					<li><a href="#">Administracion</a>
						<ul>
							<li><html:link action="/goToGeoLevelAdministration">Geo Levels</html:link></li>
							<li><html:link action="/goToRawInsertABM" >Editar de HTML inserts</html:link></li>
							<li><html:link action="/goToNotificationEmailABM" >Notificaciones por E-Mail</html:link></li>
						</ul>
					</li>
					<li><a href="#">Reportes</a>
						<ul>
							<li><a href="./boStatisticReport.jsp">Reportes</a></li>
						</ul>
					</li>
					<li><a href="#">Sistema</a>
						<ul>
							<li><html:link action="/goToSystemPropertyABM" >Variables de sistema</html:link></li>
						</ul>
					</li>
					<li><html:link action="/logout">Salir</html:link></li>
				</ul>
			</div>
		</div>
	</div>
</div>