<div id="graciasporsubir" class="hide" style="z-index: 500;">
	<h2 style="color:#FFFFFF; margin-bottom:20px;">Gracias por subir tu foto</h2>
	<p>Te avisaremos cuando este aprobada.</p>
	<div align="center"><input type="button" id="closegracias" value="Close"></div>
</div>
<div id="erroralta" class="hide" style="z-index: 500;">
	<p>Ha ocurrido un error, intentelo nuevamente.</p>
	<div align="center"><input type="button" id="closeerror" value="Close"></div>
</div>
<style>
<!--
div { /*border:dotted 1px #00CC33;*/ }
#altalayer, #graciasporsubir, #erroralta {
	color:#FFFFFF;
	background-color:#000000;
	width:230px;
	padding:15px;
	
	-webkit-border-radius: 10px;
	-moz-border-radius: 10px;
	border-radius: 10px;
}
#lineadecontenido {
	height:30px;
	float:left;
}
-->
</style>
<div id="altalayer" class="hide" style="z-index: 500;">
	<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
		<h2 style="color:#FFFFFF; margin-bottom:20px;">Sub&iacute; tu Foto con chocolate</h2>
		<div id="lineadecontenido" style="width:55px;">Nombre: </div>
		<div id="lineadecontenido"><html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="specialFields"/></div>
		<div id="lineadecontenido" style="width:55px;">E-Mail: </div>
		<div id="lineadecontenido"><html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="specialFields"/></div>
		<div id="lineadecontenido" style="margin-top:10px;"><html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics"/> Acepto las pol&iacute;ticas del sitio</div>
		<div id="lineadecontenido" style="margin-top:10px;"><html:file name="MilkaPhotoForm" property="photoFormFile" /></div>
		<div id="buttonHolder" align="center"><html:submit property="operation" styleClass="okCircle">Enviar</html:submit><input type="button" id="cancelalta" value="Cancel"></div>
	</html:form>
</div>