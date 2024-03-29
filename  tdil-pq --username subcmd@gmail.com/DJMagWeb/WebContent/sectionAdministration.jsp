<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
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
<div id="header"></div>
<div id="container">
	<div style="height:50px; display:block;"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<html:form method="POST" action="/saveSection">
			<h1>Administraci&oacute;n de secciones</h1>
			<div id="conteinerScrollable" style="overflow:hidden;">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
				<div class="renglon width500 height50">
					<div class="label width180">Nombre secci&oacute;n por defecto</div>
					<div class="label width200"><html:text name="SectionForm" property="name" styleClass="width180"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Section.name.err")%></div>
					<div class="label width500 height25 comment">Es el nombre que tendr� dentro del sistema. Luego, cada pa�s deber� tener un nombre espec�fico para la misma secci�n. En caso de no completarlo para cada pa�s, autom�ticamente tomar� el nombre por refecto.</div>
				</div>
				<div class="renglon height40">
					<logic:equal name="SectionForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="SectionForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<div id="fiftyfiftyLeft">
					<h2>Secciones</h2>
					<div class="renglon width450 height300" style="overflow:auto;">
						<table>
							<tr>
								<td class="headerTablas" width="40">Activa</td>
								<td class="headerTablas">Pa&iacute;s</td>
								<td class="headerTablas">Nombre</td>
							</tr>
						<logic:iterate id="selectedCountry" name="SectionForm" property="selectedCountries">  
							<tr>
								<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>  
								<td><bean:write name="selectedCountry" property="countryName" /></td>
								<td><html:text name="selectedCountry" property="sectionName" indexed="true" /></td>
							</tr>
						</logic:iterate>  
						</table>
					</div>
				</div>
				<!-- hr -->
				<div id="fiftyfiftyRight">
					<h2>Listado de Secciones</h2>
					<div class="renglon width450 height300" style="overflow:auto;">
						<table>
							<tr>
								<td class="headerTablas" width="140">Nombre de secci&oacute;n</td>
								<td class="headerTablas">Pa&iacute;s</td>
								<td class="headerTablas" width="50">Acciones</td>
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
									<td>
										<html:link action="editSection.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
										<html:link action="/toggleDeletedSection" paramName="iterSection"
											paramProperty="id" paramId="id">
											<% if (((com.tdil.ibatis.PersistentObject) iterSection).getDeleted() == 1) { %>
												<img src="boImages/activar.png" alt="Activar">
											<% } else { %>
												<img src="boImages/desactivar.png" alt="Desactivar">
											<% } %>
										</html:link>
									</td>
								</tr>
							</logic:iterate>
						</table>
					</div>
				</div>
		</html:form>
	</div>
</div>
</body>
</html>