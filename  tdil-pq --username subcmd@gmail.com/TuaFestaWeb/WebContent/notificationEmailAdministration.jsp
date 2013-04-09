<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="notificationEmailAdministration"%>
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
<%@ include file="includes/boMenu.jsp"%>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Administraci&oacute;n de notificaciones por E-Mail</h1>
		<div id="formulariosBase">
			<div class="renglon"><span class="comment">Desde esta sección podrá cargar el contenido de los E-Mails que sirven como notificaciones de las Experiencias y los contenidos del sitio. También podrá testear los contenidos enviando pruebas a su propia casilla. Los emails soportan las 
		siguientes variables: SERVER_NAME (server donde esta ejecutando la aplicacion).</span></div>
			<html:form method="POST" action="/saveNotificationEmail">
				<div class="renglon"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				<div class="renglon">
					<div class="label width100">Descripci&oacute;n: </div>
					<div class="label width300"><b><bean:write name="NotificationEmailForm" property="description" /></b></div>
					<div class="label width50">Tipo: </div>
					<div class="label width300"><b><bean:write name="NotificationEmailForm" property="notificationtype" /></b></div>
				</div>
				<div class="renglon" style="margin-bottom:20px;">
					<div class="label width100">Valor</div>
					<div class="label height250"><html:textarea name="NotificationEmailForm" property="content" styleClass="width800 height250"/></div>
					<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "NotificationEmailForm.content.err")%></div>
				</div>
				<div class="renglon">
					<div class="label width100">From: </div>
					<div class="label width300"><html:text name="NotificationEmailForm" property="from" styleClass="width280" /></div>
					<div class="label width50">Subject: </div>
					<div class="label width300"><html:text name="NotificationEmailForm" property="subject" styleClass="width280" /></div>
				</div>
				<logic:notEqual name="NotificationEmailForm" property="objectId" value="0">
					<div class="renglon" style="margin-bottom:20px;">
						<div class="label width100">Testear (Para): </div>
						<div class="label width650"><html:text name="NotificationEmailForm" property="email" styleClass="width630"/></div>
						<div class="label"><a href="javascript:document.NotificationEmailForm.action='./testEmailNotification.do';document.NotificationEmailForm.submit();">Enviar prueba</a></div>
					</div>
				</logic:notEqual>
				<logic:equal name="NotificationEmailForm" property="emailTest" value="true">
					<logic:equal name="NotificationEmailForm" property="errorsSending" value="true">
						<div class="renglon" style="margin-bottom:20px;">
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
			<div class="renglon">
				<table width="100%">
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