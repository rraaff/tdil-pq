<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@ page info="reorderMenu"%>
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
	<html:form method="POST" action="/saveOrder">
	<h1>Reorder men&uacute;</h1>
	<div id="conteinerScrollable" style="overflow:hidden;">
		<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%> </span>
		<div style="height:30px;">Nombre pais: TODO</div>
		<h2>Secciones</h2><%=DJMagErrorFormatter.getErrorFrom(request, "MenuItem.position.err")%>
		<table>
			<tr>
				<td class="headerTablas" width="60">Posici&oacute;n</td>
				<td class="headerTablas">Nombre</td>
			</tr>
			<logic:iterate id="menuItem" name="ReorderMenuForm" property="menuItems">  
				<tr>
					<td><html:text name="menuItem" property="position" indexed="true" styleClass="width50"/></td>
					<td><bean:write name="menuItem" property="menuItemName" /></td>
				</tr>
			</logic:iterate>  
		</table>
		<html:link action="/goToSectionABM" >Volver</html:link>
		<html:submit property="operation">
			<bean:message key="save" />
		</html:submit>
	</div>
	</html:form>
</div>
</body>
</html>