<%@page import="com.tdil.djmag.struts.forms.BannerInsertPointSelectionVO"%>
<%@page import="com.tdil.djmag.struts.forms.BannerSelectionVO"%>
<%@page import="com.tdil.djmag.model.Banner"%>
<%@page import="com.tdil.djmag.model.BannerPosition"%>
<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.model.Video"%>
<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.struts.forms.BannerPositionForm"%>
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
		<h1>Asignaci&oacute;n de banners</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveBannerPosition">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width860">
					<div class="label width80">Pa&iacute;s</div>
					<div class="label width300">
						<html:select name="BannerPositionForm" property="countryId" styleClass="textfield_effect">
							<logic:iterate name="BannerPositionForm" property="selectedCountries"
								id="iterCountry">
								<option
									<%=((CountrySelectionVO) iterCountry).isSelected() ? "selected" : ""%>
									value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
									&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
							</logic:iterate>
						</html:select><%=DJMagErrorFormatter.getErrorFrom(request, "BannerPosition.country.err")%></div>
					<div class="label width300">
						Banner<html:select name="BannerPositionForm" property="bannerId" styleClass="textfield_effect">
							<logic:iterate name="BannerPositionForm" property="selectedBanners"
								id="iterBanner">
								<option
									<%=((BannerSelectionVO) iterBanner).isSelected() ? "selected" : ""%>
									value="<%=((BannerSelectionVO) iterBanner).getBannerId()%>">
									&nbsp;&nbsp;&nbsp;<%=((BannerSelectionVO) iterBanner).getDescription()%></option>
							</logic:iterate>
						</html:select><%=DJMagErrorFormatter.getErrorFrom(request, "BannerPosition.banner.err")%></div>
					<div class="label width300">
						<html:select name="BannerPositionForm" property="insertPoint" styleClass="textfield_effect">
							<logic:iterate name="BannerPositionForm" property="selectedPoints"
								id="iterPosition">
								<option
									<%=((BannerInsertPointSelectionVO) iterPosition).isSelected() ? "selected" : ""%>
									value="<%=((BannerInsertPointSelectionVO) iterPosition).getInsertPoint()%>">
									&nbsp;&nbsp;&nbsp;<%=((BannerInsertPointSelectionVO) iterPosition).getInsertPoint()%></option>
							</logic:iterate>
						</html:select><%=DJMagErrorFormatter.getErrorFrom(request, "BannerPosition.insertPoint.err")%></div>
				</div>
				<logic:equal name="BannerPositionForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="BannerPositionForm" property="objectId" value="0">
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
					<td class="headerTablas">Banner</td>
					<td class="headerTablas">Posicion</td>
					<td class="headerTablas">Acciones</td>
				</tr>
				<logic:iterate name="BannerPositionForm" property="allBannerPositions"
					id="iterBannerPosition" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterBannerPosition).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% 	Country country = BannerPositionForm.getCountryWithId(((BannerPosition) iterBannerPosition).getIdCountry()); %>
								<%= country.getName() %>&nbsp;
						</td>
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterBannerPosition).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% 	Banner banner = BannerPositionForm.getBannerWithId(((BannerPosition) iterBannerPosition).getIdBanner()); %>
								<%= banner.getDescription() %>&nbsp;
						</td>
						<td>
							<bean:write name="iterBannerPosition" property="position" />
						</td>
						<td><html:link action="/editBannerPosition" paramName="iterBannerPosition"
								paramProperty="id" paramId="id">
							Editar
							</html:link>
							<html:link action="/toggleDeletedBannerPosition" paramName="iterBannerPosition"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterBannerPosition).getDeleted() == 1) { %>
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