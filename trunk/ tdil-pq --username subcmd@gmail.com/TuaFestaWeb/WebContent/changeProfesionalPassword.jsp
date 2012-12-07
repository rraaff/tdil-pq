<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="editProfesionalPersonalData"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Mi cuenta (Datos personales)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){
	
	}
);

</script>
<style>
#formSection {
	width:920px;
}
</style>
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Cambiar clave</h1>
			<h2>Cambio de clave</h2>
		</div>
		<div id="formContent">
			<html:form method="POST" action="/changePassword">
				<div id="formSection">
					<div class="myRow">
						<div class="myLabel width80">Clave</div>
						<div class="myLabel width150" id="Password"><html:password name="EditProfesionalPasswordDataForm" property="password" styleClass="normalField width150"/></div>
						<div class="myLabel width150">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.password_key + ".err")%></div>
						<div class="myLabel width80">Repetir clave</div>
						<div class="myLabel width150" id="Password"><html:password name="EditProfesionalPasswordDataForm" property="retypepassword" styleClass="normalField width150"/></div>
						<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.retypepassword_key + ".err")%></div>
					</div>
					<div class="myRow" align="center"><a href="./goToProfesionalHome.do?id=<%=websiteUser.getProfesional().getId()%>">Volver a mi cuenta sin grabar</a>&nbsp;<input type="submit" value="Grabar y volver a mi cuenta" /></div>
				</div>
			</html:form>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>