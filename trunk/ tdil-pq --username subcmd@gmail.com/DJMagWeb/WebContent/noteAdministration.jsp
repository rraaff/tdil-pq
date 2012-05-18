<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.model.Note"%>
<%@page import="com.tdil.djmag.model.Section"%>
<%@page import="com.tdil.djmag.struts.forms.NoteForm"%>
<%@page import="com.tdil.djmag.struts.forms.NoteImageBean"%>
<%@page import="com.tdil.djmag.struts.forms.SectionSelectionVO"%>
<%@page import="com.tdil.djmag.model.Country"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<script type="text/javascript" src="./ckeditor.js"></script>
<script>
	$(function() {
		$("input[name=fromDate]").datepicker({dateFormat: 'yy-mm-dd'});
		$("input[name=toDate]").datepicker({dateFormat: 'yy-mm-dd'});
		$("input[name=agendaDate]").datepicker({dateFormat: 'yy-mm-dd'});
	});
</script>
</head>
<body>
<div id="header"></div>
<div id="container">
	<div style="height:50px; display:block;"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<h1>Administraci&oacute;n de notas simples</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveNote" enctype="multipart/form-data">
				<input type="hidden" name="id" value="">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
				<div class="renglon width860">
					<div class="label width50">T&iacute;tulo</div>
					<div class="label width200"><html:text name="NoteForm" property="title" styleClass="width180" /></div>
					<div class="label width20"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.title.err")%></div>
					<div class="label width80">T&iacute;tulo en URL</div>
					<div class="label width200"><html:text name="NoteForm" property="webTitle" styleClass="width180"/></div>
					<div class="label width20"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.webtitle.err")%></div>
					<div class="label width50">Secci&oacute;n</div>
					<div class="label width150">
						<html:select name="NoteForm" property="sectionId" styleClass="width120">
							<logic:iterate name="NoteForm" property="allSections"
								id="iterSection">
								<option
									<%=((SectionSelectionVO) iterSection).isSelected() ? "selected" : ""%>
									value="<%=((SectionSelectionVO) iterSection).getSection().getId()%>">
									&nbsp;&nbsp;&nbsp;<%=((SectionSelectionVO) iterSection).getSection().getName()%></option>
							</logic:iterate>
						</html:select><%=DJMagErrorFormatter.getErrorFrom(request, "Note.section.err")%>
					</div>
				</div>
				<div class="renglon width860 height60">
					<div class="label width50">Bajada</div>
					<div class="label width760 height60"><html:textarea name="NoteForm" property="summary" styleClass="width740 height50"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.summary.err")%></div>
				</div>
				<div class="renglon width860 height180">
					<div class="label width50">Texto</div>
					<div class="label width760 height180"><html:textarea name="NoteForm" property="content" styleClass="width740 height200" /></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.content.err")%></div>
				</div>
				<div class="renglon width860 height60">
					<div class="label width50">Extra html</div>
					<div class="label width760 height180"><html:textarea name="NoteForm" property="extraHtml" styleClass="width740 height200" /></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.extraHtml.err")%></div>
				</div>
				<div class="renglon width860">
					<div class="label width100">Publicada desde</div>
					<div class="label width120"><html:text name="NoteForm" property="fromDate" styleClass="width100"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.fromdate.err")%></div>
					<div class="label width100">Publicada hasta</div>
					<div class="label width120"><html:text name="NoteForm" property="toDate" styleClass="width100"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Note.todate.err")%></div>
				</div>
				<div class="renglon width860">
					<div class="label width120"><html:checkbox name="NoteForm" property="frontCover" />Ver en Portada</div>
					<div class="label width180"><html:checkbox name="NoteForm" property="popular" />Ver en &Uacute;ltimas noticias</div>
					<div class="label width150"><html:checkbox name="NoteForm" property="showInAgenda" />Mostrar en agenda</div>
					<div class="label width120">Fecha de agenda</div>
					<div class="label width120"><html:text name="NoteForm" property="agendaDate" styleClass="width100"/><%=DJMagErrorFormatter.getErrorFrom(request, "Note.agendadate.err")%></div>
				</div>
				<h2>Im&aacute;gen en Portada</h2><%=DJMagErrorFormatter.getErrorFrom(request, "Note.front_cover.err")%>
				<div class="renglon width740 height150 border1 padding10 bgF2">
					<logic:equal name="NoteForm" property="hasFrontCoverImage" value="true">
						<html:img action="/viewImageNote.do?type=cover" align="middle" width="75" height="50" alt="" />
						<bean:write name="NoteForm" property="frontCoverImage.fileName" />
						<a href="javascript:document.NoteForm.action='./deleteNoteFrontCover.do';document.NoteForm.submit();">Borrar</a>
					</logic:equal>
					Im&aacute;gen
					<html:file name="NoteForm"
						property="frontCoverImageFormFile" /><html:button property="operation"
						onclick="this.form.action='./uploadNoteFrontCover.do';this.form.submit();">
						<bean:message key="uploadImage" />
					</html:button>
				</div>
				<h2>Im&aacute;gen en &Uacute;ltimas noticias (Destacadas)</h2><%=DJMagErrorFormatter.getErrorFrom(request, "Note.last_news_cover.err")%>
				<div class="renglon width740 height150 border1 padding10 bgF2">
					<logic:equal name="NoteForm" property="hasNewsCover" value="true">
						<html:img action="/viewImageNote.do?type=newsCover" align="middle" width="75" height="50" alt="" />
						<bean:write name="NoteForm" property="lastNewsCoverImage.fileName" />
						<a href="javascript:document.NoteForm.action='./deleteNewsCover.do';document.NoteForm.submit();">Borrar</a>
					</logic:equal>
					Im&aacute;gen
					<html:file name="NoteForm"
						property="lastNewsCoverImageFormFile" /><html:button property="operation"
						onclick="this.form.action='./uploadNewsCover.do';this.form.submit();">
						<bean:message key="uploadImage" />
					</html:button>
				</div>
				<h2>Im&aacute;gen en &Uacute;ltimas noticias (chicas)</h2><%=DJMagErrorFormatter.getErrorFrom(request, "Note.last_news_thumb.err")%>
				<div class="renglon width740 height150 border1 padding10 bgF2">
					<logic:equal name="NoteForm" property="hasNewsThumb" value="true">
						<html:img action="/viewImageNote.do?type=newsThumb" align="middle" width="75" height="50" alt="" />
						<bean:write name="NoteForm" property="lastNewsThumbImage.fileName" />
						<a href="javascript:document.NoteForm.action='./deleteNewsThumb.do';document.NoteForm.submit();">Borrar</a>
					</logic:equal>
					Im&aacute;gen
					<html:file name="NoteForm"
						property="lastNewsThumbImageFormFile" /><html:button property="operation"
						onclick="this.form.action='./uploadNewsThumb.do';this.form.submit();">
						<bean:message key="uploadImage" />
					</html:button>
				</div>
				<h2>Im&aacute;gen de Agenda (thumbnail)</h2><%=DJMagErrorFormatter.getErrorFrom(request, "Note.agenda.err")%>
				<div class="renglon width740 height150 border1 padding10 bgF2">
					<logic:equal name="NoteForm" property="hasAgendaImage" value="true">
						<html:img action="/viewImageNote.do?type=agenda" align="middle" width="75" height="50" alt="" />
						<bean:write name="NoteForm" property="agendaImage.fileName" />
						<a href="javascript:document.NoteForm.action='./deleteNoteAgenda.do';document.NoteForm.submit();">Borrar</a>
					</logic:equal>
					Im&aacute;gen <html:file name="NoteForm"
						property="agendaImageFormFile" /><html:button property="operation"
						onclick="this.form.action='./uploadNoteAgenda.do';this.form.submit();">
						<bean:message key="uploadImage" />
					</html:button>
				</div>
				<h2>Im&aacute;genes de la nota</h2><%=DJMagErrorFormatter.getErrorFrom(request, "Note.image.err")%>
				<table>
					<tr>
						<td class="headerTablas" width="40">&Oacute;rden</td>
						<td class="headerTablas" width="80%">Previsualizaci&oacute;n</td>
						<td colspan="3" class="headerTablas" width="60">Acciones</td>
					</tr>
					<logic:iterate id="currImage" name="NoteForm" property="images"
						indexId="iterIndex">
						<tr>
							<td><%=iterIndex%></td>
							<td>
								<table>
									<tr>
										<td><html:img action="/viewImageNote.do?" paramName="currImage" paramProperty="id" paramId="id" align="middle" width="75" height="50" alt="" /></td>
									</tr>
									<tr>
										<td><bean:write name="currImage" property="uploadData.fileName" /></td>
									</tr>
								</table></td>
							<td><a href="javascript:document.NoteForm.action='./moveImageUp.do?id=<%= ((NoteImageBean)currImage).getId() %>';document.NoteForm.submit();"><img src="boImages/subir.png" alt="Subir"></a></td>
							<td><a href="javascript:document.NoteForm.action='./moveImageDown.do?id=<%= ((NoteImageBean)currImage).getId() %>';document.NoteForm.submit();"><img src="boImages/bajar.png" alt="Bajar"></a></td>
							<td><a href="javascript:document.NoteForm.action='./deleteImage.do?id=<%= ((NoteImageBean)currImage).getId() %>';document.NoteForm.submit();"><img src="boImages/borrar.png" alt="Borrar"></a></td>
						</tr>
					</logic:iterate>
					<tr>
						<td colspan="5">Im&aacute;gen <html:file name="NoteForm"
								property="noteImage" /><html:button property="operation"
								onclick="this.form.action='./uploadImageNote.do';this.form.submit();">
								<bean:message key="uploadImage" />
							</html:button></td>
					</tr>
				</table>
				<h2>Pa&iacute;ses en los que se podr&aacute; ver</h2>
					<!-- td>Activa</td-->
				<table>
					<tr>
						<td class="headerTablas" width="40">Pa&iacute;s</td>
						<td class="headerTablas"></td>
					</tr>
					<logic:iterate id="selectedCountry" name="NoteForm"
						property="selectedCountries">
						<tr>
							<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>
							<td><bean:write name="selectedCountry" property="countryName" /></td>
						</tr>
					</logic:iterate>
				</table>
				<br>
				<logic:equal name="NoteForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="NoteForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="modify" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="reset" />
				</html:submit>
			</html:form>
			<hr>
			<h2>Listado de Notas</h2>
			<table>
				<tr>
					<td class="headerTablas" width="80">Fecha</td>
					<td class="headerTablas" width="200">T&iacute;tulo</td>
					<td class="headerTablas" width="150">Secci&oacute;n</td>
					<td class="headerTablas" width="430">Pa&iacute;ses</td>
					<td class="headerTablas" width="60">Acciones</td>
				</tr>
				<logic:iterate name="NoteForm" property="allNotes" id="iterNote"
					indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1 ? "class=\"notActive\"" : ""%>
							align="left">
								<% Note note = (Note)iterNote; %>
								<%= NoteForm.formatDate(note.getFromDate())%>
						</td>
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1 ? "class=\"notActive\"" : ""%>
							align="left"><bean:write name="iterNote" property="title" /></td>
						<td	<%=((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1 ? "class=\"notActive\""
									: ""%> align="left">
							<% Section section = NoteForm.getSectionWithId(((Note) iterNote).getIdSection()); %>
								<%= section.getName() %>
						</td>
						<td	<%=((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1 ? "class=\"notActive\""
									: ""%> align="left">
							<% for (Country country : NoteForm.getAllCountriesForNoteId(((com.tdil.ibatis.PersistentObject) iterNote).getId())) { %>
								<%= country.getName() %>&nbsp;
							<% } %>
						</td>
						<td align="center">
							<html:link action="/editNote.do" paramName="iterNote" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
							<html:link action="/toggleDeletedNote" paramName="iterNote"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1) { %>
									<img src="boImages/activar.png" alt="Activar">
								<% } else { %>
									<img src="boImages/desactivar.png" alt="Desactivar">
								<% } %>
							</html:link>
						</td>
					</tr>
				</logic:iterate>
			</table>
			<script type="text/javascript">
				//<![CDATA[
					// Replace the <textarea id="content"> with an CKEditor instance.
					var editor = CKEDITOR.replace( 'content',
						{
							// Defines a simpler toolbar to be used in this sample.
							// Note that we have added out "MyButton" button here.
							toolbar : [ ['Format'] ],
							height:"100", width:"740"
							
						});
				//]]>
				</script>
	</div>
</div>
</body>
</html>