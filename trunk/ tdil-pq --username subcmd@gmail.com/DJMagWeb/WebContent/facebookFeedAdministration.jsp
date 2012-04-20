<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.struts.forms.FacebookFeedForm"%>
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
		<h1>Administraci&oacute;n de Feeds de Facebook</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveFacebookFeed">
				<span class="errorText"><html:errors property="general" /></span><br>
				<div class="renglon">
					<div class="label">Pa&iacute;s</div>
					<html:select name="FacebookFeedForm" property="countryId" styleClass="textfield_effect">
							<logic:iterate name="FacebookFeedForm" property="selectedCountries"
								id="iterCountry">
								<option
									<%=((CountrySelectionVO) iterCountry).isSelected() ? "selected" : ""%>
									value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
									&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
							</logic:iterate>
						</html:select>
				</div>
				<div class="renglon">
					<div class="label">HTML:</div><html:textarea name="FacebookFeedForm" property="htmlContent" /><html:errors property="FacebookFeed.htmlContent.err" />
				</div>
				<logic:equal name="FacebookFeedForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="FacebookFeedForm" property="objectId" value="0">
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
				<logic:iterate name="FacebookFeedForm" property="allFacebookFeeds"
					id="iterFacebookFeed" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterFacebookFeed).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% 	Country country = FacebookFeedForm.getCountryForFacebookFeedId(((com.tdil.ibatis.PersistentObject) iterFacebookFeed).getId()); %>
								<%= country.getName() %>&nbsp;
						</td>
						<td><html:link action="/editFacebookFeed" paramName="iterFacebookFeed"
								paramProperty="id" paramId="id">
							Editar
							</html:link>
							<html:link action="/toggleDeletedFacebookFeed" paramName="iterFacebookFeed"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterFacebookFeed).getDeleted() == 1) { %>
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