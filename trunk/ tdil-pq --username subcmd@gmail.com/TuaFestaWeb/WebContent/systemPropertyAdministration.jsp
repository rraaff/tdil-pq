<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="systemPropertyAdministration"%>
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
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Administraci&oacute;n de Variables de sistema</h1>
		<div id="formulariosBase">
			<div class="renglon"><span class="comment">Desde aquí podrá configurar las variables del sistema.</span></div>
			<html:form method="POST" action="/saveSystemProperty">
				<div class="renglon"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				<div class="renglon">
					<div class="label width80">Descripci&oacute;n</div>
					<div class="label"><b><bean:write name="SystemPropertyForm" property="description" /></b></div>
				</div>
				<div class="renglon">
					<div class="label width80">Clave</div>
					<div class="label"><b><bean:write name="SystemPropertyForm" property="propkey" /></b></div>
				</div>
				<div class="renglon">
					<div class="label width80">Valor</div>
					<div class="label height250"><html:textarea name="SystemPropertyForm" property="propvalue" styleClass="width800 height250"/></div>
					<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "SystemPropertyForm.propValue.err")%></div>
				</div>
				<div class="renglon" style="margin-bottom:20px;" align="center">
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
				</div>
			</html:form>
			<div class="renglon">
				<table width="100%">
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
							<td align="center"><html:link action="/editSystemProperty" paramName="iterSystemProperty" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
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