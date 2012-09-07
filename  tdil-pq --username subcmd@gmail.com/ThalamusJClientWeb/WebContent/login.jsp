<%@page import="com.tdil.thalamusweb.utils.ThalamusJClientWebErrorFormatter"%>
<%@ page info="login"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html>
<head>
<%@ include file="includes/head.jsp"%>
<%@ include file="includes/errorJS.jsp"%>
<style type="text/css">
<!--
#header {
	width:990px;
	height:237px;
	margin:0 auto;
}
#central {
	width:990px;
	height:164px;
	margin:0 auto;
}
#left {
	width:273px;
	height:164px;
	float:left;
}
#form {
	background-image: url(images/skin_nrg/login__03.png);
	background-repeat: no-repeat;
	width:444px;
	height:164px;
	float:left;
	overflow: hidden;
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #2d0703;	
	
}
#right {
	width:273px;
	height:164px;
	float:left;
}
#footer {
	width:990px;
	height:92px;
	margin:0 auto;
}
.renglon {
	padding-bottom:15px;
}
-->
</style>
</head>

<body>
<div id="header"><img src="images/skin_nrg/login__01.png" width="990" height="237"></div>
<div id="central">
	<div id="left"><img src="images/skin_nrg/login__02.png" width="273" height="164"></div>
	<div id="form">
		<html:form method="POST" action="/login">
		<div class="renglon width440">
			<div class="label width440">&nbsp;<span class="errorText"><%=ThalamusJClientWebErrorFormatter.getErrorFrom(request, "general")%></span></div>
		</div>
		<div class="renglon width440">
			<html:hidden name="LoginForm" property="operation" value=""/>
			<div class="label width70" style="padding-top:5px;">Usuario</div>
			<div class="label width120"><html:text name="LoginForm" property="username" styleClass="normalField width120"/></div>
			<div class="label width30">&nbsp;</div>
			<div class="label width100" style="padding-top:5px;">Contrase&ntilde;a</div>
			<div class="label width120"><html:password name="LoginForm" property="password" styleClass="normalField width120"/></div>
		</div>
		<div class="renglon width440 height10" style="margin:0; padding:0;">&nbsp;</div>
		<div class="renglon width440">
			<div class="label width210" style="padding-top:20px;"><a href="index.jsp" title="Cancel registration and back home">Back home</a></div>
			<div class="label width70" style="padding-top:20px;">or</div>
			<div class="label width160 "><input type="image" src="images/skin_nrg/btn_login_at_form.png"/></div>
		</div>
		<!-- ht m l : s u b m i t property="operation">Login< / h t ml:s u b mit  -->
		</html:form>
	</div>
	<div id="left"><img src="images/skin_nrg/login__04.png" width="273" height="164"></div>
</div>
<div id="footer"><img src="images/skin_nrg/login__05.jpg" width="990" height="179"></div>

<a href="registro.jsp">Registrate</a>
<a href="login.jsp">Ingresa</a>
<a href="catalogo.jsp">Catalogo</a>
<a href="legal.jsp">Legal</a>

</body>
</html>