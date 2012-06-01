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
<div id="formContent">
<html:form method="POST" action="/addLoveNick">
	<div id="Nombre">Apodo<html:text name="LoveNicknameForm" property="text" styleClass="normalField"/></div>
	<div id="Sexo">Sexo (f,m)<html:text name="LoveNicknameForm" property="sex" styleClass="normalField"/></div>
	<div id="Posicion">Posicion<html:text name="LoveNicknameForm" property="position" styleClass="normalField normalTextArea"/></div>
	<div id="buttonHolder"><html:submit property="operation" styleClass="okCircle"></html:submit></div>
</html:form>
</div>
</body>
</html>