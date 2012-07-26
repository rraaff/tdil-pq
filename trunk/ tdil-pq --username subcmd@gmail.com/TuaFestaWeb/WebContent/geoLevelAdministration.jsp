<%@page import="com.tdil.tuafesta.struts.forms.GeoLevelForm"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject"%>
<%@page import="com.tdil.tuafesta.utils.ProfesionalCategoryUtils"%>
<%@page import="com.tdil.tuafesta.utils.ProfesionalCategoryTreeNode"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalCategoryForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
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
		<html:form method="POST" action="/saveGeoLevel">
			<% GeoLevelForm geoLevelForm = (GeoLevelForm)session.getAttribute("GeoLevelForm"); %>
			<h1>Administraci&oacute;n de geo levels</h1>
			<div id="conteinerScrollable" style="overflow:hidden;">
				<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span>
				<html:select name="GeoLevelForm" property="level">
					<% for (int i = 2; i <= 4; i++) { %>
						<option	<%=	i == geoLevelForm.getLevel() ? "selected" : ""%>
							value="<%=String.valueOf(i)%>">
							&nbsp;&nbsp;&nbsp;<%=String.valueOf(i)%></option>
					<% } %>
				</html:select>

				<div class="renglon height40">
					<logic:equal name="GeoLevelForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="GeoLevelForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<!-- hr -->
				<div id="fiftyfiftyRight">
					<h2>Listado de Geo Levels</h2>
					Buscar por 
					Nivel:
					<html:select name="GeoLevelForm" property="levelSearch">
					<% for (int i = 2; i <= 4; i++) { %>
						<option	<%=	i == geoLevelForm.getLevelSearch() ? "selected" : ""%>
							value="<%=String.valueOf(i)%>">
							&nbsp;&nbsp;&nbsp;<%=String.valueOf(i)%></option>
					<% } %>
					</html:select>
					Nombre: <html:text name="GeoLevelForm" property="nombreSearch" style="width: 300px;"/>
					<html:button property="operation"
								onclick="this.form.action='./searchGeoLevel.do';this.form.submit();">
								Buscar
					</html:button>
					<div class="renglon width450 height300" style="overflow:auto;">
						<table>
							<tr>
								<td class="headerTablas" width="140">Nombre</td>
								<td class="headerTablas" width="50">Nivel</td>
							</tr>
							<logic:iterate name="GeoLevelForm" property="search"
								id="iterSection" indexId="iterIndex">
								<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
									<td
										<%=((GeoLevelValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
										align="left"><%= ((GeoLevelValueObject) iterSection).getNombre() %>
									</td>
									<td>
										<html:link action="editGeoLevel.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
										<html:link action="/toggleDeletedGeoLevel" paramName="iterSection"
											paramProperty="id" paramId="id">
											<% if (((GeoLevelValueObject) iterSection).getDeleted() == 1) { %>
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