<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script type="text/javascript">
  jQuery(function(){ 
    jQuery('.jbar').jbar();
  });
</script>
<div id="portaMenu">
	<ul class="jbar">
		<li><a href="#">Tu foto Milka</a>
			<ul>
				<li><html:link action="/goToMilkaPhotoApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
				<li><html:link action="/goToMilkaPhotoReview" >Revision de aprobados</html:link></li>
			</ul>
		</li>
		<li><a href="#">Post-Its</a>
			<ul>
				<li><html:link action="/goToPostItApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
				<li><html:link action="/goToPostItReview" >Revision de aprobados</html:link></li>
			</ul>
		</li>
		<li><a href="#">Papapedia</a>
			<ul>
				<li><html:link action="/goToPapapediaApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
				<li><html:link action="/goToPapapediaReview" >Revision de aprobados</html:link></li>
			</ul>
		</li>
		<li><a href="#">Finales de email</a>
			<ul>
				<li><html:link action="/goToEmailEndingsApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
				<li><html:link action="/goToEmailEndingsReview" >Revision de aprobados</html:link></li>
			</ul>
		</li>
		<li><a href="#">Cartas de hijos a padres</a>
			<ul>
				<li><html:link action="/goToMailToParentApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
				<li><html:link action="/goToMailToParentReview" >Revision de aprobados</html:link></li>
			</ul>
		</li>
		<li><a href="#">Reportes</a>
			<ul>
				<li><html:link action="/goToNewsletterReport" >Subscriptos al newsletter</html:link></li>
			</ul>
		</li>
		<li><a href="#">Notificaciones</a>
			<ul>
				<li><html:link action="/goToNotificationEmailABM" >Notificaciones por email</html:link></li>
			</ul>
		</li>
		<li><a href="#">Sistema</a>
			<ul>
				<li><html:link action="/goToSystemPropertyABM" >Variables de sistema</html:link></li>
			</ul>
		</li>
		<li><html:link action="/logout" >Salir</html:link></li>
	</ul>
</div>