<%@ page info="index"%><!--
--><%@page import="com.tdil.lojack.struts.forms.LoginForm"%><!--
--><%@page import="com.tdil.thalamus.client.facade.json.beans.DocumentTypeBean"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><% if (websiteUser != null && websiteUser.isLogged()) { %>
	<jsp:forward page="home.jsp"></jsp:forward>
<% 	return;
	} %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
</head>
<body>
<html:form method="POST" action="/loginMobile">
				<h3>Ingresar</h3>
				
				<%=com.tdil.lojack.web.LoJackMobileErrorFormatter.getErrorFrom(request, "principal.err")%>
					<fieldset>
						<label>Tipo doc</label>
						<% LoginForm loginForm = (LoginForm)session.getAttribute("LoginFormMobile"); %>
						<html:select name="LoginFormMobile" property="documentType" >
								<option value="">Seleccione...</option>
								<% for (DocumentTypeBean codBean : LoginForm.getDocumentTypes()) { %>
									<option value="<%=codBean.getId()%>">
										<%=codBean.getName()%></option>
								<% } %>
							</html:select>
					</fieldset>
					<fieldset>
						<label>Número</label>
						<html:text name="LoginFormMobile" property="username"/>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<label>CLAVE</label>
						<html:password name="LoginFormMobile" property="password"/>
					</fieldset>
					<div class="errorInForm"></div>
					<fieldset>
						<a href="./recordarPasswordModal.jsp">No recuerdo mi contrasenia</a>
					</fieldset>
					<fieldset>
						<div style="float:left;"><input type="submit" id="submitlogin" value="Login" class="indexLogin"></div>
						<a href="./goToMobileRegistration.do">Registrarse</a>
					</fieldset>
			</html:form>
</body>
</html>