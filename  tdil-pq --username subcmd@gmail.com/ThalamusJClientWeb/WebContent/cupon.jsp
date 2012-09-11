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
	width:1100px;
	height:207px;
	margin:0 auto;
}
#central {
	width:1100px;
	height:162px;
	margin:0 auto;
}
#left {
	width:327px;
	height:162px;
	float:left;
}
#form {
	background-image: url(images/skin_nrg/cupon_03.jpg);
	background-repeat: no-repeat;
	width:446px;
	height:162px;
	float:left;
	overflow: hidden;
	font-family: 'Gill Sans MT', Genova, Arial, Helvetica, sans-serif;
	font-size: 18px;
	color: #2d0703;
	margin:0;
	padding:0;
}
#right {
	width:327px;
	height:162px;
	float:left;
}
#footer {
	width:1100px;
	height:211px;
	margin:0 auto;
}
.renglon {
	padding-bottom:15px;
}
-->
</style>
</head>

<body>
<div id="header"><img src="images/skin_nrg/cupon_01.jpg" width="1100" height="207"> </div>
<div id="central">
	<div id="left"><img src="images/skin_nrg/cupon_02.jpg" width="327" height="162"></div>
	<div id="form">
		<div class="renglon width440" style="margin-top:30px;">
			<div class="label width20">&nbsp;</div>
			<div class="label width100" style="padding-top:5px;">Code</div>
			<div class="label width300"><input type="text" class="normalField width300"/></div>
		</div>
		<div class="renglon width440">
			<div class="label width20">&nbsp;</div>
			<div class="label width180" style="padding-top:30px;"><a href="index.jsp" title="Cancel registration and back home">Back home</a></div>
			<div class="label width60" style="padding-top:30px;">or</div>
			<div class="label width180 "><a href="cupon_approved.jsp" title="Send your code"><img src="images/skin_nrg/btn_send_at_form.png" width="174" height="76"/></a></div>
		</div>
	</div>
	<div id="left"><img src="images/skin_nrg/cupon_04.jpg" width="327" height="162"></div>
</div>
<div id="footer"><img src="images/skin_nrg/cupon_05.jpg" width="1100" height="211"></div>
<!--
<a href="registro.jsp">Registrate</a>
<a href="login.jsp">Ingresa</a>
<a href="catalogo.jsp">Catalogo</a>
<a href="legal.jsp">Legal</a>
-->
</body>
</html>