<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.model.Footer"%>
<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.struts.forms.FooterForm"%>
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
		<h1>Administraci&oacute;n de Footers</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveFooter">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width860">
					<div class="label width50">Pa&iacute;s</div>
					<div class="label width300">
						<html:select name="FooterForm" property="countryId" styleClass="width280">
							<logic:iterate name="FooterForm" property="selectedCountries"
								id="iterCountry">
								<option
									<%=((CountrySelectionVO) iterCountry).isSelected() ? "selected" : ""%>
									value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
									&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
							</logic:iterate>
						</html:select>
					</div>
					<div class="label width100"><a href="javascript:document.FooterForm.action='./resetFooter.do';document.FooterForm.submit();">Resetear</a></div>
					<div class="label width100"><%=DJMagErrorFormatter.getErrorFrom(request, "Footer.country.err")%></div>
				</div>
				<div class="renglon width920 height120">
					<div class="label width50">HTML</div>
					<div class="label width800 height120"><html:textarea name="FooterForm" property="htmlContent" styleClass="width800 height120" /></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Footer.htmlContent.err")%></div>
				</div>
				<div class="renglon width860">
					<div class="label width100">Reemplazos:</div>
					<div class="label width150">[NOTAS_POPULARES]</div>
					<div class="label width150">[SECCIONES]</div>
				</div>
				<logic:equal name="FooterForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="FooterForm" property="objectId" value="0">
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
				<logic:iterate name="FooterForm" property="allFooters"
					id="iterFooter" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterFooter).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% 	Country country = FooterForm.getCountryWithId(((Footer) iterFooter).getIdCountry()); %>
								<%= country.getName() %>&nbsp;
						</td>
						<td align="center"><html:link action="/editFooter" paramName="iterFooter" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
							<html:link action="/toggleDeletedFooter" paramName="iterFooter"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterFooter).getDeleted() == 1) { %>
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