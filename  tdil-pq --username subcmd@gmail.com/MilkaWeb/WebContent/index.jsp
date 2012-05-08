<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<%@ include file="includes/head.jsp" %>
<script type='text/javascript' src='js/jquery.cookie.js'></script>
<script type='text/javascript' src='js/jquery.melt-button.js'></script>
<script>
$(document).ready(
	function(){
		$("div[id^='mb-']").each(function(indice,valor) {
		   $(valor).meltbutton();
		});
	}
	
);
</script>
</head>
<body>
<!-- test barra -->
<div id="content">
<div id="header">
	<div id="logo"><a href="index.html" title="Milka"></a></div>
	<!-- end logo-->
	<div id="social"><a href="#" class="facebook" title="Facebook"><img src="images/barra/facebook.png" width="17" height="17" alt="Facebook" /></a><img src="images/barra/separador.gif" width="1" height="21" /><a href="#" class="twitter" title="Twitter"><img src="images/barra/twitter.png" width="17" height="17" alt="Twitter" /></a></div>
	<!-- end social-->
	<div id="controlador"><a href="#" class="left"></a><span>PROB&Aacute; M&Aacute;S</span><a href="#" class="right"></a></div>
	<!-- end controlador-->
	<div id="personas">A <span>2356 personas</span> les derrite esto.</div>
	<!-- end personas-->
	<div id="me_derrite"><a href="#" title="me derrite"></a></div>
	<!-- end me_derrite-->
</div>
<!-- end header-->
<div id="contenido">
  <img src="images/barra/imagen-fondo.jpg" width="900" height="716" alt="imagen fondo" />
</div>
<!-- end contenido-->
</div>
<!-- end content-->
<%= MeltButton.meltButton(1) %>

<%= MeltButton.meltButton(2) %>

Milka Photo<br>
<html:form method="POST" action="/uploadMilkaPhoto" enctype="multipart/form-data">
		NOmbre:<html:text name="MilkaPhotoForm" property="authorBean.name" styleClass="width180"/><br>
		email:<html:text name="MilkaPhotoForm" property="authorBean.email" styleClass="width180"/><br>
		Politicas:<html:checkbox name="MilkaPhotoForm" property="authorBean.acceptPolitics" styleClass="width180"/><br>
		<html:file name="MilkaPhotoForm" property="photoFormFile" />
		<html:submit property="operation">
			Upload
		</html:submit>
	</html:form>
	
Post-It<br>
<html:form method="POST" action="/addPostIt">
		NOmbre:<html:text name="PostItForm" property="authorBean.name" styleClass="width180"/><br>
		email:<html:text name="PostItForm" property="authorBean.email" styleClass="width180"/><br>
		Politicas:<html:checkbox name="PostItForm" property="authorBean.acceptPolitics" styleClass="width180"/><br>
		Texto:<html:text name="PostItForm" property="text" styleClass="width180"/><br>
		<html:submit property="operation">
			Salvar
		</html:submit>
	</html:form>
</body>
</html>			