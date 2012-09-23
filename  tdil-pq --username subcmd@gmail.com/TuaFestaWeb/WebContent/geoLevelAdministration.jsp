<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.GeoLevelForm"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="geoLevelAdministration"%>
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
<div id="boWrapper">
	<div id="boCentral">
		<div id="formulariosBase">
			<html:form method="POST" action="/saveGeoLevel">
				<% GeoLevelForm geoLevelForm = (GeoLevelForm)session.getAttribute("GeoLevelForm"); %>
				<h1>Administraci&oacute;n de geo levels</h1>
				<div class="renglon width950">
					<div class="label width950"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				</div>
				<div class="renglon width950 height50">
					<!-- Si es creacion  -->
					<div class="label width950">
						<logic:equal name="GeoLevelForm" property="objectId" value="0">
							<html:select name="GeoLevelForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Administration.do';this.form.submit()">
								<option value="0">-</option>
								<% for (Geo2 geo2 : geoLevelForm.getLevel2()) { %>	
									<option	<%=	geo2.getId() == geoLevelForm.getGeo2Id() ? "selected" : ""%>
										value="<%=String.valueOf(geo2.getId())%>">
										<%=geo2.getNombre()%></option>
								<% } %>
							</html:select>
							
							<logic:notEqual name="GeoLevelForm" property="geo2Id" value="0">
								<html:select name="GeoLevelForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevelAdministration.do';this.form.submit()">
									<option value="0">-</option>
									<% for (Geo3 geo3 : geoLevelForm.getLevel3()) { %>	
										<option	<%=	geo3.getId() == geoLevelForm.getGeo3Id() ? "selected" : ""%>
											value="<%=String.valueOf(geo3.getId())%>">
											<%=geo3.getNombre()%></option>
									<% } %>
								</html:select>
							</logic:notEqual>
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
							<html:text name="GeoLevelForm" property="nombre" styleClass="width120"/>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, "GeoLevel.name.err")%>
						</logic:equal>
					
						<!-- Si es edicion -->			
						<logic:notEqual name="GeoLevelForm" property="objectId" value="0">
							<logic:equal name="GeoLevelForm" property="level" value="2">
								Provincia: <html:text name="GeoLevelForm" property="nombre" styleClass="width120"/>
								<%=TuaFestaErrorFormatter.getErrorFrom(request, "GeoLevel.name.err")%>
							</logic:equal>
							<logic:equal name="GeoLevelForm" property="level" value="3">
								Provincia:<html:select name="GeoLevelForm" property="geo2Id">
									<option value="0">-</option>
									<% for (Geo2 geo2 : geoLevelForm.getLevel2()) { %>	
										<option	<%=	geo2.getId() == geoLevelForm.getGeo2Id() ? "selected" : ""%>
											value="<%=String.valueOf(geo2.getId())%>">
											<%=geo2.getNombre()%></option>
									<% } %>
								</html:select>
								Partido: <html:text name="GeoLevelForm" property="nombre" styleClass="width120"/>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, "GeoLevel.name.err")%>
							</logic:equal>
							<logic:equal name="GeoLevelForm" property="level" value="4">
								Provincia:<html:select name="GeoLevelForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Administration.do';this.form.submit()">
									<option value="0">-</option>
									<% for (Geo2 geo2 : geoLevelForm.getLevel2()) { %>	
										<option	<%=	geo2.getId() == geoLevelForm.getGeo2Id() ? "selected" : ""%>
											value="<%=String.valueOf(geo2.getId())%>">
											<%=geo2.getNombre()%></option>
									<% } %>
								</html:select>
								Partido:<html:select name="GeoLevelForm" property="geo3Id">
									<option value="0">-</option>
									<% for (Geo3 geo3 : geoLevelForm.getLevel3()) { %>	
										<option	<%=	geo3.getId() == geoLevelForm.getGeo3Id() ? "selected" : ""%>
											value="<%=String.valueOf(geo3.getId())%>">
											<%=geo3.getNombre()%></option>
									<% } %>
								</html:select>
								Localidad: <html:text name="GeoLevelForm" property="nombre" styleClass="width120"/>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, "GeoLevel.name.err")%>
							</logic:equal>
						</logic:notEqual>
					</div>
					<div class="label width300 height50" align="center">
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
				</div>
				<h1>Listado de Geo Levels</h1>
				<div class="renglon width950 height50">
					<div class="label width100" style="margin-top:10px;">Buscar por nivel: </div>
					<div class="label width100" style="margin-top:10px;">
						<html:select name="GeoLevelForm" property="levelSearch">
						<% for (int i = 2; i <= 4; i++) { %>
							<option	<%=	i == geoLevelForm.getLevelSearch() ? "selected" : ""%>
								value="<%=String.valueOf(i)%>">
								&nbsp;&nbsp;&nbsp;<%=String.valueOf(i)%></option>
						<% } %>
						</html:select>
					</div>
					<div class="label width50" style="margin-top:10px;">Nombre</div>
					<div class="label width200" style="margin-top:10px;"><html:text name="GeoLevelForm" property="nombreSearch" styleClass="width200"/></div>
					<div class="label width350 height50"><html:button property="operation" onclick="this.form.action='./searchGeoLevel.do';this.form.submit();">Buscar</html:button></div>
				</div>
				<div class="renglon width950" style="margin-bottom:50px;">
					<table width="100%">
						<tr>
							<td class="headerTablas" width="140">Nombre</td>
							<td class="headerTablas" width="50">Nivel</td>
						</tr>
						<logic:iterate name="GeoLevelForm" property="search"
							id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td class="paddingTD"
									<%=((GeoLevelValueObject) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><%= ((GeoLevelValueObject) iterSection).getNombre() %>
								</td>
								<td class="paddingTD">
									<a href="./editGeoLevel.do?id=<%=((GeoLevelValueObject) iterSection).getId()%>&level=<%=((GeoLevelValueObject) iterSection).getLevel()%>"><img src="boImages/editar.png" alt="Editar"></a>
									<html:link action="/toggleDeletedGeoLevel" paramName="iterSection"
										paramProperty="id" paramId="id">
										<% if (((GeoLevelValueObject) iterSection).getDeleted() == 1) { %>
											<img src="boImages/activar.png" alt="Activar">
										<% } else { %>
											<img src="boImages/desactivar.png" alt="Desactivar">
										<% } %>
									</html:link>
									<html:link action="/deleteGeoLevelFromDatabase" paramName="iterSection"
										paramProperty="id" paramId="id">
											<img src="boImages/deleteFromDb.png" alt="Eliminar de la base">
									</html:link>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			</html:form>
		</div>
	</div>
</div>
</body>
</html>