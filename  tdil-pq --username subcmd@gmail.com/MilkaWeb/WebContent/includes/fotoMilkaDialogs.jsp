<div id="dialog-modal" class="hide" title="Subi tu foto con chocolate">
	<p>
		Gracias por subir tu foto.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<div id="dialog-modal-err" class="hide" title="Subi tu foto con chocolate">
	Ha ocurrido un error, intentelo nuevamente.
</div>

<div id="dialog-form" title="Subi tu foto con chocolate">
	<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
		<table width="330" cellspacing="0" cellpadding="0">
			<tr>
				<td width="56" height="30">Nombre:</td>
				<td width="242"><html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="width230"/></td>
				<td width="30" id="authorBean.nameerr"></td>
			</tr>
			<tr>
				<td height="30">E-Mail:</td>
				<td><html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="width230"/></td>
				<td width="30" id="authorBean.emailerr"></td>
			</tr>
			<tr>
				<td height="30" align="center"><html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics"/></td>
				<td>Acepto las pol&iacute;ticas del sitio</td>
				<td width="30" id="authorBean.acceptPoliticserr"></td>
			</tr>
			<tr>
				<td colspan="2" height="30"><html:file name="MilkaPhotoForm" property="photoFormFile" /></td>
				<td width="30" id="photoFormFileerr"></td>
			</tr>
			<tr>
				<td colspan="3" height="30" align="center"><html:submit property="operation">Enviar imagen</html:submit></td>
			</tr>
		</table>
	</html:form>
</div>