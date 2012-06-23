<div id="graciasporsubir" class="hide" style="z-index: 500;">
	<p>Gracias por subir tu foto.<br>Te avisaremos cuando este aprobada.</p>
	<input type="button" id="closegracias" value="Close">
</div>
<div id="erroralta" class="hide" style="z-index: 500;">
	<p>Ha ocurrido un error, intentelo nuevamente.</p>
	<input type="button" id="closeerror" value="Close">
</div>
<div id="altalayer" class="hide" style="z-index: 500;">
	<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
	
		<div id="lineadecontenido"><html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="specialFields"/></div>
		<div id="lineadecontenido"><html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="specialFields"/></div>
		<div id="lineadecontenido" style="margin-left:20px; margin-top:10px;"><html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics"/> Acepto las pol&iacute;ticas del sitio</div>
		<div id="lineadecontenido" style="margin-left:20px; margin-top:10px;"><html:file name="MilkaPhotoForm" property="photoFormFile" /></div>
		<div id="buttonHolder"><html:submit property="operation" styleClass="okCircle">Enviar</html:submit></div>
	</html:form>
	<input type="button" id="cancelalta" value="Cancel">
</div>