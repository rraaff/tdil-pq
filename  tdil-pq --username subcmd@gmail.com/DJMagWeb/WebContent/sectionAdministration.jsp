<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.struts.forms.SectionForm"%>
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

	<br>Section Administration


	<html:form method="POST" action="/saveSection">
		<span class="errorText"><html:errors property="general" /> </span>
		<br>
		Nombre seccion: <html:text name="SectionForm" property="name" /><html:errors property="Section.name.err" />
		<br>
		Borrada: <html:checkbox name="SectionForm" property="deleted" />
		<br>
		Secciones
		<table>
		<tr>
			<td>Activa</td>
			<td>Pais</td>
			<td>Nombre</td>
		</tr>
		<logic:iterate id="selectedCountry" name="SectionForm" property="selectedCountries">  
			<tr>
   				<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>  
   				<td><bean:write name="selectedCountry" property="countryName" /></td>
   				<td><html:text name="selectedCountry" property="sectionName" indexed="true" /></td>
   			</tr>
		</logic:iterate>  
		</table>
		<br>
		<logic:equal name="SectionForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="save" />
			</html:submit>
		</logic:equal>
		<logic:notEqual name="SectionForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="modify" />
			</html:submit>
		</logic:notEqual>
		<html:submit property="operation">
			<bean:message key="reset" />
		</html:submit>
	</html:form>
		<hr>
	Listado de Secciones
	<table>
		<tr>
			<td>Nombre de seccion</td>
			<td>Paises</td>
			<td>Editar</td>
		</tr>
		<logic:iterate name="SectionForm" property="allSections"
			id="iterSection" indexId="iterIndex">
			<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
				<td
					<%=((com.tdil.ibatis.PersistentObject) iterSection).getDeleted() == 1 ? "class=\"notActive\""
						: ""%>
					align="left"><bean:write name="iterSection" property="name" />
				</td>
			<td	<%=((com.tdil.ibatis.PersistentObject) iterSection).getDeleted() == 1 ? "class=\"notActive\""
						: ""%> align="left">
						
				<% for (Country country : SectionForm.getAllCountriesForSectionId(((com.tdil.ibatis.PersistentObject) iterSection).getId())) { %>
					<a href="./goToReorderMenu.do?id=<%= country.getId() %>"> <%= country.getName() %></a> &nbsp;
				<% } %>
			</td>
				<td><html:link action="editSection.st?" paramName="iterSection"
						paramProperty="id" paramId="id">
			Editar
		</html:link></td>
			</tr>
		</logic:iterate>
	</table>

</body>
</html>