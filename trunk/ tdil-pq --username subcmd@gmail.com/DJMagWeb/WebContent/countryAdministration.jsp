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
				<span class="errorText"><html:errors property="general" /></span><br>
				<div class="renglon">
					<div class="label">Nombre</div><html:text name="CountryForm" property="name" /><html:errors property="Country.name.err" />
				</div>
				<div class="renglon">
					<div class="label">Iso code2</div><html:text name="CountryForm" property="iso_code_2" /><html:errors property="Country.iso_code_2.err" /> <span class="comment">Ejemplo: AR (para Argentina)</span>
				</div>
				<div class="renglon">
					<div class="label"><html:checkbox name="CountryForm" property="deleted" /></div><div class="label" style="width:150px;">Marcar como borrada</div>
				</div>
		
				<logic:equal name="CountryForm" property="id" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="CountryForm" property="id" value="0">
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
						<td></td>
						<td><html:link action="editCountry.st?" paramName="iterCountry"
								paramProperty="id" paramId="id">
					Editar
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