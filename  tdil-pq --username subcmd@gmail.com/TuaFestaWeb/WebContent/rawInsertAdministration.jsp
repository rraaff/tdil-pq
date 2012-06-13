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
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Administración de Inserts</h1>
	<div class="renglon width860">
		<div class="label width860"><span class="comment">Desde esta sección podrá editar el contenido hardcodeado de los XML de las Experiencias y otras variables de contenidos estático.</span></div>
	</div>
	<html:form method="POST" action="/saveRawInsert">
		<div class="renglon width860" style="margin-bottom:20px;">
			<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span><br>
			<div class="label width50">Tipo</div>
			<div class="label width200"><bean:write name="RawInsertForm" property="inserttype" /></div>
			<div class="label width100">Descripci&oacute;n</div>
			<div class="label width200"><bean:write name="RawInsertForm" property="description" /></div>
		</div>
		<div class="renglon width860" style="margin-bottom:20px;">
			<div class="label width80">Valor</div>
			<div class="label width700 height120"><html:textarea name="RawInsertForm" property="htmlcontent" styleClass="width700 height120" /></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "RawInsertForm.htmlcontent.err")%></div>
		</div>
		<div class="renglon width860" style="margin-bottom:20px;" align="center">
			<logic:equal name="RawInsertForm" property="objectId" value="0">
				<html:submit property="operation" disabled="true">
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
	<div class="renglon width860">
		<table>
			<tr>
				<td class="headerTablas">Tipo</td>
				<td class="headerTablas">Descripcion</td>
				<td class="headerTablas" width="60">Acciones</td>
			</tr>
			<logic:iterate name="RawInsertForm" property="allRawInserts"
				id="iterRawInsert" indexId="iterIndex">
				<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
					<td
						<%=((com.tdil.ibatis.PersistentObject) iterRawInsert).getDeleted() == 1 ? "class=\"notActive\""
							: ""%>
						align="left"><bean:write name="iterRawInsert" property="inserttype" />
					</td>
					<td><bean:write name="iterRawInsert" property="description" /></td>
					<td align="center"><html:link action="/editRawInsert" paramName="iterRawInsert" paramProperty="id" paramId="id" title="editar"><img src="boImages/editar.png" alt="Editar"></html:link>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</div>
</div>
</body>
</html>