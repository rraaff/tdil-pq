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
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<%@ include file="includes/boErrorJS.jsp"%>

<style>
#formContent {
	min-height:290px;
	padding-top:20px;
	margin-bottom:20px;
}
</style>
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
			<div class="width300" align="center" style="margin:0 auto;">
				<html:form method="POST" action="/websiteLogin">
					<div class="myRow width300" align="center">&nbsp;<span class="errorText"><html:errors property="general" /></span></div>
					<div class="myRow width300">
						<html:hidden name="WebsiteLoginForm" property="operation" value=""/>
						<div class="myLabel width30">&nbsp;</div>
						<div class="myLabel width80">E-mail</div>
						<div class="myLabel width150"><html:text name="WebsiteLoginForm" property="email" styleClass="width150"/></div>
					</div>
					<div class="myRow width300">
						<div class="myLabel width30">&nbsp;</div>
						<div class="myLabel width80">Contrase&ntilde;a</div>
						<div class="myLabel width150"><html:password name="WebsiteLoginForm" property="password" styleClass="width150"/></div>
					</div>
					<div class="myRow width300">&nbsp;</div>
					<div class="myRow width300">
						<div class="myLabel width300" align="center"><input type="image" value=" " class="" src="images/skin_basic/buttons/ingresarClientes.png" /><!-- h t m l : s u b  m i t   p r o  p e r t y =  " o p e r a  t i o n"  > I ng r e s a r < / h t m l : s  u b m i t - --></div>
					</div>
					<div class="myRow width300">
						<div class="myLabel width150"><a href="#" title="recuper&aacute; tu clave">&#191;Olvidaste tu clave?</a></div>
						<div class="myLabel width150"><a href="#" title="recuper&aacute; tu usuario">&#191;No record&aacute;s con qu&eacute; E-Mail te registraste?</a></div>
					</div>
				</html:form>
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>