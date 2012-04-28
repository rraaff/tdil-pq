<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.struts.forms.TwitterFeedForm"%>
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
		<h1>Administraci&oacute;n de Feeds de Twitter</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveTwitterFeed">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width760 height150">
					<div class="label width50">Pa&iacute;s</div>
					<div class="label width150">
						<html:select name="TwitterFeedForm" property="countryId" styleClass="width100">
							<logic:iterate name="TwitterFeedForm" property="selectedCountries"
								id="iterCountry">
								<option
									<%=((CountrySelectionVO) iterCountry).isSelected() ? "selected" : ""%>
									value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
									&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
							</logic:iterate>
						</html:select>
					</div>
					<div class="label width50">HTML</div>
					<div class="label width500 height120"><html:textarea name="TwitterFeedForm" property="htmlContent" styleClass="width500 height120" /><br/><span class="comment">w:309px x h:261px</span></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "TwitterFeed.htmlContent.err")%></div>
				</div>
				<logic:equal name="TwitterFeedForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="TwitterFeedForm" property="objectId" value="0">
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
					<td class="headerTablas" width="60">Acciones</td>
				</tr>
				<logic:iterate name="TwitterFeedForm" property="allTwitterFeeds"
					id="iterTwitterFeed" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterTwitterFeed).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% 	Country country = TwitterFeedForm.getCountryForTwitterFeedId(((com.tdil.ibatis.PersistentObject) iterTwitterFeed).getId()); %>
								<%= country.getName() %>&nbsp;
						</td>
						<td align="center"><html:link action="/editTwitterFeed" paramName="iterTwitterFeed" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
							<html:link action="/toggleDeletedTwitterFeed" paramName="iterTwitterFeed"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterTwitterFeed).getDeleted() == 1) { %>
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
</body>
</html>