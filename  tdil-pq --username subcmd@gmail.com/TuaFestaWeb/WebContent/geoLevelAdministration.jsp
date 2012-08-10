<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.GeoLevelForm"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject"%>
<%@page import="com.tdil.tuafesta.utils.ServiceCategoryUtils"%>
<%@page import="com.tdil.tuafesta.utils.ServiceCategoryTreeNode"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.ServiceCategoryForm"%>
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
				<logic:equal name="GeoLevelForm" property="objectId" value="0">
					<html:select name="GeoLevelForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Administration.do';this.form.submit()">
						<option value="0">-<option>
						<% for (Geo2 geo2 : geoLevelForm.getLevel2()) { %>	
							<option	<%=	geo2.getId() == geoLevelForm.getGeo2Id() ? "selected" : ""%>
								value="<%=String.valueOf(geo2.getId())%>">
								<%=geo2.getNombre()%></option>
						<% } %>
					</html:select>
				</logic:equal>
				
				<logic:notEqual name="GeoLevelForm" property="objectId" value="0">
					<logic:notEqual name="GeoLevelForm" property="geo2Id" value="0">
						<bean:write name="GeoLevelForm" property="geo2Nombre" />
					</logic:notEqual>
				</logic:notEqual>
				
				<logic:equal name="GeoLevelForm" property="objectId" value="0">
					<logic:notEqual name="GeoLevelForm" property="geo2Id" value="0">
						<html:select name="GeoLevelForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevelAdministration.do';this.form.submit()">
						<option value="0">-<option>
						<% for (Geo3 geo3 : geoLevelForm.getLevel3()) { %>	
							<option	<%=	geo3.getId() == geoLevelForm.getGeo3Id() ? "selected" : ""%>
								value="<%=String.valueOf(geo3.getId())%>">
								<%=geo3.getNombre()%></option>
						<% } %>
						</html:select>
					</logic:notEqual>
				</logic:equal>
				<logic:notEqual name="GeoLevelForm" property="objectId" value="0">
					<logic:notEqual name="GeoLevelForm" property="geo3Id" value="0">
						<bean:write name="GeoLevelForm" property="geo3Nombre" />
					</logic:notEqual>
				</logic:notEqual>
				
				<logic:equal name="GeoLevelForm" property="objectId" value="0">
					<logic:equal name="GeoLevelForm" property="geo2Id" value="0">
						Provincia:
					</logic:equal>
					<logic:notEqual name="GeoLevelForm" property="geo2Id" value="0">
						<logic:equal name="GeoLevelForm" property="geo3Id" value="0">
							Partido:
						</logic:equal>
						<logic:notEqual name="GeoLevelForm" property="geo3Id" value="0">
							Localidad:
						</logic:notEqual>
					</logic:notEqual>
				</logic:equal>
				
				<logic:notEqual name="GeoLevelForm" property="objectId" value="0">
					<logic:equal name="GeoLevelForm" property="level" value="2">
						Provincia:
					</logic:equal>
					<logic:equal name="GeoLevelForm" property="level" value="3">
						Partido:
					</logic:equal>
					<logic:equal name="GeoLevelForm" property="level" value="4">
						Localidad:
					</logic:equal>
				</logic:notEqual>
				
				<html:text name="GeoLevelForm" property="nombre" styleClass="width180"/></div>
				<%=TuaFestaErrorFormatter.getErrorFrom(request, "GeoLevel.name.err")%>
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
										<a href="./editGeoLevel.do?id=<%=((GeoLevelValueObject) iterSection).getId()%>&level=<%=((GeoLevelValueObject) iterSection).getLevel()%>"><img src="boImages/editar.png" alt="Editar"></a>
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