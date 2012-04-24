<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.model.Video"%>
<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.struts.forms.VideoForm"%>
<%@page import="com.tdil.djmag.model.Country"%>
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
		<h1>Administraci&oacute;n de Videos</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveVideo">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width860">
					<div class="label width80">Pa&iacute;s</div>
					<div class="label width300">
						<html:select name="VideoForm" property="countryId" styleClass="textfield_effect">
							<logic:iterate name="VideoForm" property="selectedCountries"
								id="iterCountry">
								<option
									<%=((CountrySelectionVO) iterCountry).isSelected() ? "selected" : ""%>
									value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
									&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
							</logic:iterate>
						</html:select><%=DJMagErrorFormatter.getErrorFrom(request, "Video.country.err")%></div>
					<div class="label width100"><html:checkbox name="VideoForm" property="frontcover" />Portada</div>
					<div class="label width100"><html:checkbox name="VideoForm" property="popular" />Popular</div>
				</div>
				<div class="renglon width860 height50">
					<div class="label width80">Descripci&oacute;n</div>
					<div class="label width700 height50"><html:textarea name="VideoForm" property="description" styleClass="width700 height50" /><%=DJMagErrorFormatter.getErrorFrom(request, "Video.description.err")%></div>
				</div>
				<div class="renglon width860 height80">
					<div class="label width80">HTML</div>
					<div class="label width700 height80"><html:textarea name="VideoForm" property="htmlContent" styleClass="width700 height80" /><%=DJMagErrorFormatter.getErrorFrom(request, "Video.htmlContent.err")%></div>
				</div>
				<logic:equal name="VideoForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="VideoForm" property="objectId" value="0">
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
					<td class="headerTablas">Pais</td>
					<td class="headerTablas">Acciones</td>
				</tr>
				<logic:iterate name="VideoForm" property="allVideos"
					id="iterVideo" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterVideo).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% 	Country country = VideoForm.getCountryWithId(((Video) iterVideo).getIdCountry()); %>
								<%= country.getName() %>&nbsp;
						</td>
						<td><html:link action="/editVideo" paramName="iterVideo"
								paramProperty="id" paramId="id">
							Editar
							</html:link>
							<html:link action="/toggleDeletedVideo" paramName="iterVideo"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterVideo).getDeleted() == 1) { %>
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