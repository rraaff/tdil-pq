<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="rawInsertAdministration"%>
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
		<h1 align="center">Administración de Inserts</h1>
		<div id="formulariosBase">
			<div class="renglon"><span class="comment">Desde esta sección podrá editar el contenido hardcodeado de los XML de las Experiencias y otras variables de contenidos estático.</span></div>
			<html:form method="POST" action="/saveRawInsert">
				<div class="renglon" style="margin-bottom:20px;">
					<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span><br>
					<div class="label width50">Nombre:</div>
					<div class="label width200"><html:text name="RawInsertForm" property="inserttype" /></div>
					<div class="label width100">Descripci&oacute;n</div>
					<div class="label width200"><html:text name="RawInsertForm" property="description" /></div>
				</div>
				<div class="renglon" style="margin-bottom:20px;">
					<div class="label width80">Valor</div>
					<div class="label height120"><html:textarea name="RawInsertForm" property="htmlcontent" styleClass="width700 height120" /></div>
					<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "RawInsertForm.htmlcontent.err")%></div>
				</div>
				<div class="renglon" style="margin-bottom:20px;" align="center">
					<logic:equal name="RawInsertForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="RawInsertForm" property="objectId" value="0">
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
				<div class="label width180">C&oacute;digo JSP para insertar: </div>
				<div class="label">
					<code>
						&lt;%=com.tdil.tuafesta.utils.Banner.banner([Insert id])%&gt;
					</code>
				</div>
			</div>
			<div class="renglon">
				<table width="100%">
					<tr>
						<td class="headerTablas">Insert id</td>
						<td class="headerTablas">Nombre</td>
						<td class="headerTablas">Descripcion</td>
						<td class="headerTablas" width="60">Acciones</td>
					</tr>
					<logic:iterate name="RawInsertForm" property="allRawInserts"
						id="iterRawInsert" indexId="iterIndex">
						<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
							<td
								<%=((com.tdil.ibatis.PersistentObject) iterRawInsert).getDeleted() == 1 ? "class=\"notActive\""
									: ""%>
								align="left"><bean:write name="iterRawInsert" property="id" />
							</td>
							<td
								<%=((com.tdil.ibatis.PersistentObject) iterRawInsert).getDeleted() == 1 ? "class=\"notActive\""
									: ""%>
								align="left"><bean:write name="iterRawInsert" property="inserttype" />
							</td>
							<td><bean:write name="iterRawInsert" property="description" /></td>
							<td align="center">
								<html:link action="/editRawInsert" paramName="iterRawInsert" paramProperty="id" paramId="id" title="editar"><img src="boImages/editar.png" alt="Editar"></html:link>
								<html:link action="/toggleDeletedRawInsert" paramName="iterRawInsert"
										paramProperty="id" paramId="id">
										<% if (((com.tdil.ibatis.PersistentObject) iterRawInsert).getDeleted() == 1) { %>
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