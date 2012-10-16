<%@ include file="includes/userLogged.jspf" %>
<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Inicio | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
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
			<div id="loginBase" style="width:300px; height:250px; margin:40px auto;">
				<html:form method="POST" action="/websiteLogin">
					<div class="myRow" align="center">&nbsp;<span class="errorText"><html:errors property="general" /></span></div>
					<div class="myRow">
						<html:hidden name="WebsiteLoginForm" property="operation" value=""/>
						<div class="myLabel width80">E-mail</div>
						<div class="myLabel width210"><html:text name="WebsiteLoginForm" property="email" styleClass="normalField width200"/></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Contrase&ntilde;a</div>
						<div class="myLabel width210"><html:password name="WebsiteLoginForm" property="password" styleClass="normalField width200"/></div>
					</div>
					<div class="myRow">
						<div class="myLabel width100" align="center" style="padding-top:20px;"><a href="index.jsp">Volver</a></div>
						<div class="myLabel width200" align="center"><input type="submit" value="Ingresar con tu usuario" /></div>
					</div>
					<div class="myRow"><a href="./resetearPassword.jsp" title="recuper&aacute; tu clave">&#191;Olvidaste tu clave?</a></div>
					<div class="myRow"><a href="#" title="recuper&aacute; tu usuario">&#191;No record&aacute;s con qu&eacute; E-Mail te registraste?</a></div>
				</html:form>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>