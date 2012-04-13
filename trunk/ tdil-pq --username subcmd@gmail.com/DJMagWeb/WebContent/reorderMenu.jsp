<%@ page info="reorderMenu"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
</head>

<body>

	<%@ include file="includes/boMenu.jsp"%>

	Reorder menu

	<html:form method="POST" action="/saveOrder">
		<span class="errorText"><html:errors property="general" /> </span>
		Nombre pais: TODO<br>
		Secciones
		<table>
		<tr>
			<td>Posicion</td>
			<td>Nombre</td>
		</tr>
		<logic:iterate id="menuItem" name="ReorderMenuForm" property="menuItems">  
			<tr>
				<td><html:text name="menuItem" property="position" indexed="true"/></td>
   				<td><bean:write name="menuItem" property="menuItemName" /></td>
   			</tr>
		</logic:iterate>  
		</table>
		<br>
		<html:submit property="operation">
			<bean:message key="save" />
		</html:submit>
	</html:form>


	<html:link action="/goToSectionABM" >Volver</html:link>

</body>
</html>