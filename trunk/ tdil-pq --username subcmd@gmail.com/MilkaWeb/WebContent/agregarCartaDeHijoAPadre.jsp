<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.tdil.milka.model.ClickCounter"%>
<%@page import="com.tdil.milka.web.MeltButton"%>
<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
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
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>

Agregar carta
<html:form method="POST" action="/uploadMailToParent" enctype="multipart/form-data">
	Nombre:<html:text name="MailToParentForm" property="authorBean.name" styleClass="width180"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.name.err")%><br>
	Email:<html:text name="MailToParentForm" property="authorBean.email" styleClass="width180"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.email.err")%><br>
	Politicas:<html:checkbox name="MailToParentForm" property="authorBean.acceptPolitics" styleClass="width180"/><%=MilkaErrorFormatter.getErrorFrom(request, "Author.politics.err")%><br>
	<html:file name="MailToParentForm" property="photoFormFile" />
	<html:submit property="operation">
		Upload
	</html:submit><%=MilkaErrorFormatter.getErrorFrom(request, "MailToParentForm.photo.err")%>
</html:form>
</body>
</html>