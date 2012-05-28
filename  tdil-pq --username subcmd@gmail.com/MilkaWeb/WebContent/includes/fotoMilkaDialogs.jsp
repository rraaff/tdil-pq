<div id="dialog-modal" class="hide" title="Subi tu foto con chocolate">
	<p>
		Gracias por subir tu foto.<br>
		Te avisaremos cuando este aprobada.
	</p>
</div>
<div id="dialog-modal-err" class="hide" title="Subi tu foto con chocolate">
	Ha ocurrido un error, intentelo nuevamente.
</div>

<div id="dialog-form" class="hide" title="Subi tu foto con chocolate">
	<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
		<table>
			<tr>
				<td>Nombre:<html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="width180"/></td>
				<td width="25" id="authorBean.nameerr"></td>
			</tr>
			<tr>
				<td>email:<html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="width180"/></td>
				<td width="25" id="authorBean.emailerr"></td>
			</tr>
			<tr>
				<td>Politicas:<html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics" styleClass="width180"/></td>
				<td width="25" id="authorBean.acceptPoliticserr"></td>
			</tr>
			<tr>
				<td><html:file name="MilkaPhotoForm" property="photoFormFile" /></td>
				<td width="25" id="photoFormFileerr"></td>
			</tr>
			<tr>
				<td>
					<html:submit property="operation">Upload</html:submit>
				</td>
			</tr>
		</table>	
	</html:form>
</div>