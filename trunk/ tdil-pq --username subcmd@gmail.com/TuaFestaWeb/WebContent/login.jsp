<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Ingresar con tu usuario | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
</head>

<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Ingres&aacute; con tu usuario</h1>
			<h2>Ingres&aacute; el E-Mail con el que te registraste y tu clave.</h2>
		</div>
		<div id="formContent">
			<div id="loginBase" style="width:622px; height:170px; margin:60px auto;">
				<html:form method="POST" action="/websiteLogin">
					<div class="width300 fleft">
						<div class="myRow" align="center">&nbsp;<span class="errorText"><html:errors property="general" /></span></div>
						<div class="myRow width300">
							<html:hidden name="WebsiteLoginForm" property="operation" value=""/>
							<div class="myLabel width80">E-mail</div>
							<div class="myLabel width210"><html:text name="WebsiteLoginForm" property="email" styleClass="normalField width200"/></div>
						</div>
						<div class="myRow width300">
							<div class="myLabel width80">Contrase&ntilde;a</div>
							<div class="myLabel width210"><html:password name="WebsiteLoginForm" property="password" styleClass="normalField width200"/></div>
						</div>
						<div class="myRow">
							<div class="myLabel width300" align="center"><input type="submit" value="Ingresar con tu usuario" class="inputButtonHelper" /></div>
						</div>
					</div>
					<div class="width280 fleft" style="margin-left:10px; padding-top:13px; padding-left:20px; border-left:dotted 1px #CCC;">
						<div class="myRow">
							<div class="myLabel width280" align="center">&#191;No est&aacute;s registrado? <a href="./registroPreRegistro.jsp" title="recuper&aacute; tu clave">Registrate ahora</a></div>
						</div>
						<div class="myRow">
							<div class="myLabel width280" align="center"><a href="./goToPasswordReset.do" title="recuper&aacute; tu clave">&#191;Olvidaste tu clave?</a></div>
						</div>
						<div class="myRow">
							<div class="myLabel width280" align="center"><a href="./goToResendEmail.do" title="Ped&iacute; un nuevo email">&#191;No recibiste el E-Mail confirmando tu registraci&oacute;n?</a></div>
						</div>
						<div class="myRow">
							<div class="myLabel width280" align="center"><a href="index.jsp">Volver</a></div>
						</div>
					</div>
				</html:form>
				<div class="myRow" align="center"><br/>Si no recib&iacute;s los E-Mail de TuaFesta, te recomendamos revisar el correo no deseado o <a href="contacto.jsp" title="Contanos si no recibis los E-Mails del sistema">contactanos ahora</a>.</div>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>