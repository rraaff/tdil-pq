<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.web.ErrorFormatter"%>
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
		<h1>Administraci&oacute;n de banners</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveBanner">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width860">
					<div class="label width80">Descripci&oacute;n</div>
					<div class="label width200"><html:text name="BannerForm" property="description" styleClass="width200"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Banner.description.err")%></div>
				</div>
				<div class="renglon width860 height150">
					<div class="label width50 height150">HTML</div>
					<div class="label width740 height150"><html:textarea name="BannerForm" property="htmlContent" styleClass="width720 height120"/></div>
					<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "Banner.htmlContent.err")%></div>
				</div>
				<logic:equal name="BannerForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="BannerForm" property="objectId" value="0">
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
					<td class="headerTablas">Descripci&oacute;n</td>
					<td class="headerTablas">Acciones</td>
				</tr>
				<logic:iterate name="BannerForm" property="allBanners"
					id="iterBanner" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterBanner).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left"><bean:write name="iterBanner" property="description" />
						</td>
						<td><html:link action="/editBanner" paramName="iterBanner"
								paramProperty="id" paramId="id">
							Editar
							</html:link>
							<html:link action="/toggleDeletedBanner" paramName="iterBanner"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterBanner).getDeleted() == 1) { %>
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