<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script type="text/javascript">
  jQuery(function(){ 
    jQuery('.jbar').jbar();
  });
</script>
<div id="portaMenu">
	<ul class="nav">
		<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Contenidos del sitio</a>
			<ul>
				<li><a href="#">Tu foto Milka</a>
					<ul>
						<li><html:link action="/goToMilkaPhotoApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
						<li><html:link action="/goToMilkaPhotoReview" >Revision de aprobados</html:link></li>
					</ul>
				</li>
				<li><html:link action="/goToVideoABM" >Administracion de videos</html:link></li>
			</ul>
		</li>
		<li><a href="#" style="background:none; color:#000000; -webkit-border-radius: 0px; -moz-border-radius: 0px; border-radius: 0px; box-shadow: none; -webkit-box-shadow: none; -moz-box-shadow: none; -o-box-shadow: none;">Administraci&oacute;n de experiencias</a>
			<ul>
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
				<li><a href="#">Apodos de amor</a>
					<ul>
						<li><html:link action="/goToLoveNicknameApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
						<li><html:link action="/goToLoveNicknameReview" >Revision de aprobados</html:link></li>
					</ul>
				</li>
				<li><a href="#">Cartas de padres a hijos</a>
					<ul>
						<li><html:link action="/goToMailToChildApprove" >Pendientes de Aprobaci&oacute;n</html:link></li>
						<li><html:link action="/goToMailToChildReview" >Revision de aprobados</html:link></li>
					</ul>
				</li>
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