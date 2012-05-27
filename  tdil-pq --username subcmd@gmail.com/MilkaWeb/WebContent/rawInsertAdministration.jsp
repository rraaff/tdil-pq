<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
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
		<h1>Administraci&oacute;n de inserts</h1>
		<div id="conteinerScrollable" style="overflow:hidden;">
			<html:form method="POST" action="/saveRawInsert">
				<span class="errorText"><%=MilkaErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width700">
					<div class="label width50">Tipo</div>
					<div class="label width200"><bean:write name="RawInsertForm" property="inserttype" /></div>
					<div class="label width50">Descripcion</div>
					<div class="label width200"><bean:write name="RawInsertForm" property="description" /></div>
					<div class="label width80">Valor</div>
					<div class="label width80"><html:textarea name="RawInsertForm" property="htmlcontent"/></div>
					<div class="label width50"><%=MilkaErrorFormatter.getErrorFrom(request, "RawInsertForm.htmlcontent.err")%></div>
				</div>
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
			</html:form>
			<div class="renglon width920 height300" style="overflow:auto;">
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
							<td><html:link action="/editRawInsert" paramName="iterRawInsert" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
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