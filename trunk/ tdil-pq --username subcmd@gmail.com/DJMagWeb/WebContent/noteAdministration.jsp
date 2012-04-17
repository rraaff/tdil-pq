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
</head>

<body>

	<%@ include file="includes/boMenu.jsp"%>

	<br>Note Administration


	<html:form method="POST" action="/saveNote" enctype="multipart/form-data">
		<span class="errorText"><html:errors property="general" /> </span>
		<br>
		Titulo: <html:text name="NoteForm" property="title" /><html:errors property="Note.title.err" />
		<br>
		Borrada: <html:checkbox name="NoteForm" property="deleted" />
		<br>
		Seccion<html:select name="NoteForm" property="sectionId" styleClass="textfield_effect">
			<logic:iterate name="NoteForm" property="allSections" id="iterSection"> 
				<option <%=((SectionSelectionVO)iterSection).isSelected() ? "selected" : "" %> value="<%=((SectionSelectionVO)iterSection).getSection().getId()%>">&nbsp;&nbsp;&nbsp;<%=((SectionSelectionVO)iterSection).getSection().getName()%></option>
			</logic:iterate>
		</html:select><br>
		
		<table border="1">
		<tr>
			<td></td>
			<td>Imagen</td>
			<td></td>
			<td></td>
			<td></td>
		</tr>	
		<logic:iterate id="currImage" name="NoteForm" property="images" indexId="iterIndex">
			<tr>
				<td><%=iterIndex%></td>
   				<td>
   					<table>
   						<tr><td><html:img action="/viewImageNote.do?" paramName="currImage" paramProperty="id" paramId="id" align="middle" width="100" height="100" alt=""/> </td></tr>
   						<tr><td><bean:write name="currImage" property="uploadData.fileName" /></td></tr>
   					</table>
   				</td>
   				<td>Subir</td>
   				<td>Bajar</td>
   				<td>Borrar</td>
   			</tr>
		</logic:iterate>  
		<tr><td colspan="5">Imagen: <html:file name="NoteForm" property="noteImage"/>
		<html:button property="operation" onclick="this.form.action='./uploadImageNote.do';this.form.submit();">
			<bean:message key="uploadImage" />
		</html:button></td></tr>
		</table>
		
		Mostrar en
			<td>Activa</td>
		<table>
		<tr>
			<td>Pais</td>
		</tr>
		
		<logic:iterate id="selectedCountry" name="NoteForm" property="selectedCountries">  
			<tr>
   				<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>  
   				<td><bean:write name="selectedCountry" property="countryName" /></td>
   			</tr>
		</logic:iterate>  
		</table>
		<br>
		<logic:equal name="NoteForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="save" />
			</html:submit>
		</logic:equal>
		<logic:notEqual name="NoteForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="modify" />
			</html:submit>
		</logic:notEqual>
		<html:submit property="operation">
			<bean:message key="reset" />
		</html:submit>
	</html:form>
		<hr>
	Listado de Notas
	<table>
		<tr>
			<td>Titulo</td>
			<td>Seccion</td>
			<td>Paises</td>
			<td>Editar</td>
		</tr>
		<logic:iterate name="NoteForm" property="allNotes"
			id="iterNote" indexId="iterIndex">
			<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
				<td
					<%=((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1 ? "class=\"notActive\""
						: ""%>
					align="left"><bean:write name="iterNote" property="title" />
				</td>
			<td	<%=((com.tdil.ibatis.PersistentObject) iterNote).getDeleted() == 1 ? "class=\"notActive\""
						: ""%> align="left">
						

			</td>
				<td><html:link action="editNote.do?" paramName="iterNote"
						paramProperty="id" paramId="id">
			Editar
		</html:link></td>
				<td width="10" bgcolor="#FFFFFF"><img src="images/null.gif"
					width="10" height="1"></td>
			</tr>
		</logic:iterate>
	</table>

</body>
</html>