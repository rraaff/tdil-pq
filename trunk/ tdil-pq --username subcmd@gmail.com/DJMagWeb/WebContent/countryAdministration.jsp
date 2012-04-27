<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
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
		<h1>Administraci&oacute;n de pa&iacute;ses</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveCountry">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width700">
					<div class="label width50">Nombre</div>
					<div class="label width200"><html:text name="CountryForm" property="name" styleClass="width180"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Country.name.err")%></div>
					<div class="label width80">Iso code2</div>
					<div class="label width80"><html:text name="CountryForm" property="iso_code_2" styleClass="width50"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Country.iso_code_2.err")%></div>
					<div class="label width150"><span class="comment">Ejemplo: AR (para Argentina)</span></div>
				</div>
				<logic:equal name="CountryForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="CountryForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="modify" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="reset" />
				</html:submit>
			</html:form>
		
			<table>
				<tr>
					<td class="headerTablas">Pa&iacute;s</td>
					<td class="headerTablas">Iso code2</td>
					<td class="headerTablas">Acciones</td>
				</tr>
				<logic:iterate name="CountryForm" property="allCountries"
					id="iterCountry" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterCountry).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left"><bean:write name="iterCountry" property="name" />
						</td>
						<td><bean:write name="iterCountry" property="isoCode2" /></td>
						<td><html:link action="/editCountry" paramName="iterCountry"
								paramProperty="id" paramId="id">
							Editar
							</html:link>
							<html:link action="/toggleDeletedCountry" paramName="iterCountry"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterCountry).getDeleted() == 1) { %>
									Activar
								<% } else { %>
									Desactivar
								<% } %>
							</html:link>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
	</div>
</div>
</body>
</html>