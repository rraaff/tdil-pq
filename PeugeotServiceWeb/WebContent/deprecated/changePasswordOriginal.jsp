<%@page import="com.tdil.ljpeugeot.struts.forms.ChangePasswordForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.EditProfileForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.ljpeugeot.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/headLogged.jsp" %>
</head>
<body>
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="logout.do">Salir</a>
<hr> 
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr> 
Cambiar mi password<br><br>

	<html:form method="POST" action="/changePassword">
		<div class="myRow">
			<div class="myLabel width120">Clave actual</div>
			<div class="myLabel width270"><html:password name="ChangePasswordForm" property="oldPassword" styleClass="normalField width250"/></div>
			<%=LJPeugeotErrorFormatter.getErrorFrom(request, ChangePasswordForm.oldPassword_key + ".err")%>
		</div>
		<div class="myRow">
			<div class="myLabel width120">Nueva clave</div>
			<div class="myLabel width270"><html:password name="ChangePasswordForm" property="newPassword" styleClass="normalField width250"/></div>
			<%=LJPeugeotErrorFormatter.getErrorFrom(request, ChangePasswordForm.newPassword_key + ".err")%>
		</div>
		<div class="myRow">
			<div class="myLabel width120">Repetir claeve</div>
			<div class="myLabel width270"><html:password name="ChangePasswordForm" property="confirmNewPassword" styleClass="normalField width250"/></div>
			<%=LJPeugeotErrorFormatter.getErrorFrom(request, ChangePasswordForm.confirmNewPassword_key + ".err")%>
		</div>	
		<div class="myRow" align="center"><a href="./home.jsp">Cancelar</a>&nbsp;<input type="submit" value="Grabar" /></div>

	</html:form>

</body>
</html>