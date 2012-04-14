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

	<br>Country Administration


	<html:form method="POST" action="/saveCountry">
		<span class="errorText"><html:errors property="general" />
		</span>
		<br>
		Nombre: <html:text name="CountryForm" property="name" /><html:errors property="Country.name.err" />
		<br>
		Borrado: <html:checkbox name="CountryForm" property="deleted" />
		<br>

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
		<logic:iterate name="CountryForm" property="allCountries"
			id="iterCountry" indexId="iterIndex">
			<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
				<td
					<%=((com.tdil.ibatis.PersistentObject) iterCountry).getDeleted() == 1 ? "class=\"notActive\""
						: ""%>
					align="left"><bean:write name="iterCountry" property="name" />
				</td>
				<td><html:link action="editCountry.st?" paramName="iterCountry"
						paramProperty="id" paramId="id">
			Editar
		</html:link>
				</td>
				<td width="10" bgcolor="#FFFFFF"><img src="images/null.gif"
					width="10" height="1">
				</td>
			</tr>
		</logic:iterate>
	</table>

</body>
</html>