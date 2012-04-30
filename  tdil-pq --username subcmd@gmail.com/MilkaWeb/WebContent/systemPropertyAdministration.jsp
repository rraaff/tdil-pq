<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
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
		<h1>Administraci&oacute;n de Variables de sistema</h1>
		<div id="conteinerScrollable" style="overflow:hidden;">
			<html:form method="POST" action="/saveSystemProperty">
				<span class="errorText"><%=MilkaErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width700">
					<div class="label width50">Descripcion</div>
					<div class="label width200"><bean:write name="SystemPropertyForm" property="description" /></div>
					<div class="label width50">Clave</div>
					<div class="label width200"><bean:write name="SystemPropertyForm" property="propkey" /></div>
					<div class="label width80">Valor</div>
					<div class="label width80"><html:text name="SystemPropertyForm" property="propvalue" styleClass="width50"/></div>
					<div class="label width50"><%=MilkaErrorFormatter.getErrorFrom(request, "SystemPropertyForm.propValue.err")%></div>
				</div>
				<logic:equal name="SystemPropertyForm" property="objectId" value="0">
					<html:submit property="operation" disabled="true">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="SystemPropertyForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="modify" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="reset" />
				</html:submit>
			</html:form>
			<div class="renglon width920 height300" style="overflow:auto;">
				<table>
					<tr>
						<td class="headerTablas">Clave</td>
						<td class="headerTablas">Valor</td>
						<td class="headerTablas" width="60">Acciones</td>
					</tr>
					<logic:iterate name="SystemPropertyForm" property="allSystemProperties"
						id="iterSystemProperty" indexId="iterIndex">
						<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
							<td
								<%=((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1 ? "class=\"notActive\""
									: ""%>
								align="left"><bean:write name="iterSystemProperty" property="propkey" />
							</td>
							<td><bean:write name="iterSystemProperty" property="propvalue" /></td>
							<td><html:link action="/editSystemProperty" paramName="iterSystemProperty" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
								<html:link action="/toggleDeletedSystemProperty" paramName="iterSystemProperty"
									paramProperty="id" paramId="id">
									<% if (((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1) { %>
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
	</div>
</div>
</body>
</html>