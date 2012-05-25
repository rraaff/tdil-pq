<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<%@ include file="includes/head.jsp" %>
<script type='text/javascript' src='./js/jquery.cookie.js'></script>
<script type='text/javascript' src='./js/jquery.melt-button.js'></script>
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

uploadMailToParent
<html:form method="POST" action="/uploadMailToParent" enctype="multipart/form-data">
	NOmbre:<html:text name="MailToParentForm" property="authorBean.name" styleClass="width180"/><br>
	email:<html:text name="MailToParentForm" property="authorBean.email" styleClass="width180"/><br>
	Politicas:<html:checkbox name="MailToParentForm" property="authorBean.acceptPolitics" styleClass="width180"/><br>
	<html:file name="MailToParentForm" property="photoFormFile" />
	<html:submit property="operation">
		Upload
	</html:submit>
</html:form>
</body>
</html>