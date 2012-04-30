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
		<h1>Administraci&oacute;n de notificaciones por email</h1>
		<div id="conteinerScrollable" style="overflow:hidden;">
			<html:form method="POST" action="/saveNotificationEmail">
				<span class="errorText"><%=MilkaErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width700">
					<div class="label width50">Descripcion</div>
					<div class="label width200"><bean:write name="NotificationEmailForm" property="description" /></div>
					<div class="label width50">Tipo</div>
					<div class="label width200"><bean:write name="NotificationEmailForm" property="notificationtype" /></div>
					<div class="label width80">Valor</div>
					<div class="label width80"><html:textarea name="NotificationEmailForm" property="content" styleClass="width50"/></div>
					<div class="label width50"><%=MilkaErrorFormatter.getErrorFrom(request, "NotificationEmailForm.content.err")%></div>
				</div>
				<logic:notEqual name="NotificationEmailForm" property="objectId" value="0">
					Testear: <html:text name="NotificationEmailForm" property="email" styleClass="width50"/>
					<a href="javascript:document.NotificationEmailForm.action='./testEmailNotification.do';document.NotificationEmailForm.submit();">Testear email</a>
				</logic:notEqual>
				<logic:equal name="NotificationEmailForm" property="emailTest" value="true">
					<logic:equal name="NotificationEmailForm" property="errorsSending" value="true">
						El email no pudo enviarse:
						<bean:write name="NotificationEmailForm" filter="false" property="errorText" />
					</logic:equal>
					<logic:equal name="NotificationEmailForm" property="errorsSending" value="false">
						El email se envio correctamente.
					</logic:equal>
				</logic:equal>
				
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
			</html:form>
			<div class="renglon width920 height300" style="overflow:auto;">
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
	</div>
</div>
</body>
</html>