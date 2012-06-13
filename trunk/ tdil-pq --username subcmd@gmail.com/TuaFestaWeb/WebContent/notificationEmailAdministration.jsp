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
	<h1 align="center">Administraci&oacute;n de notificaciones por E-Mail</h1>
	<div class="renglon width860 height40" style="margin-bottom:20px;">
		<div class="label width860 height40"><span class="comment">Desde esta sección podrá cargar el contenido de los E-Mails que sirven como notificaciones de las Experiencias y los contenidos del sitio. También podrá testear los contenidos enviando pruebas a su propia casilla. Los emails soportan las 
		siguientes variables: AUTHOR_NAME (nombre del autor), EXPERIENCE_LINK (link a la experiencia) y SERVER_NAME (server donde esta ejecutando la aplicacion).</span></div>
	</div>
	<html:form method="POST" action="/saveNotificationEmail">
		<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span><br>
		<div class="renglon width860">
			<div class="label width100">Descripci&oacute;n: </div>
			<div class="label width300"><b><bean:write name="NotificationEmailForm" property="description" /></b></div>
			<div class="label width50">Tipo: </div>
			<div class="label width300"><b><bean:write name="NotificationEmailForm" property="notificationtype" /></b></div>
		</div>
		<div class="renglon width860 height120" style="margin-bottom:20px;">
			<div class="label width100">Valor</div>
			<div class="label width700 height120"><html:textarea name="NotificationEmailForm" property="content" styleClass="width700 height120"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "NotificationEmailForm.content.err")%></div>
		</div>
		<logic:notEqual name="NotificationEmailForm" property="objectId" value="0">
			<div class="renglon width860" style="margin-bottom:20px;">
				<div class="label width100">Testear: </div>
				<div class="label width230"><html:text name="NotificationEmailForm" property="email" styleClass="width200"/></div>
				<div class="label width120"><a href="javascript:document.NotificationEmailForm.action='./testEmailNotification.do';document.NotificationEmailForm.submit();">Enviar prueba</a></div>
			</div>
		</logic:notEqual>
		<logic:equal name="NotificationEmailForm" property="emailTest" value="true">
			<logic:equal name="NotificationEmailForm" property="errorsSending" value="true">
				<div class="renglon width860" style="margin-bottom:20px;">
					<div class="label width860 errorText">El email no pudo enviarse: <bean:write name="NotificationEmailForm" filter="false" property="errorText" /></div>
			</div>
			</logic:equal>
			<logic:equal name="NotificationEmailForm" property="errorsSending" value="false">
				<div class="renglon width860" style="margin-bottom:20px;">
					<div class="label width860 errorText">El email se envio correctamente.</div>
				</div>
			</logic:equal>
		</logic:equal>
		<div class="renglon width860" style="margin-bottom:20px;" align="center">
			<logic:equal name="NotificationEmailForm" property="objectId" value="0">
				<html:submit property="operation" disabled="true">
					<bean:message key="save" />
				</html:submit>
			</logic:equal>
			<logic:notEqual name="NotificationEmailForm" property="objectId" value="0">
				<html:submit property="operation">
					<bean:message key="modify" />
				</html:submit>
			</logic:notEqual>
			<html:submit property="operation">
				<bean:message key="reset" />
			</html:submit>
		</div>
	</html:form>
	<div class="renglon width860" style="margin-bottom:20px;">
		<table>
			<tr>
				<td class="headerTablas">Clave</td>
				<td class="headerTablas" width="60">Acciones</td>
			</tr>
			<logic:iterate name="NotificationEmailForm" property="allNotificationEmails"
				id="iterNotificationEmail" indexId="iterIndex">
				<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
					<td
						<%=((com.tdil.ibatis.PersistentObject) iterNotificationEmail).getDeleted() == 1 ? "class=\"notActive\""
							: ""%>
						align="left"><bean:write name="iterNotificationEmail" property="notificationtype" />
					</td>
					<td><html:link action="/editNotificationEmail" paramName="iterNotificationEmail" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
						<html:link action="/toggleDeletedNotificationEmail" paramName="iterNotificationEmail"
							paramProperty="id" paramId="id">
							<% if (((com.tdil.ibatis.PersistentObject) iterNotificationEmail).getDeleted() == 1) { %>
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
</body>
</html>