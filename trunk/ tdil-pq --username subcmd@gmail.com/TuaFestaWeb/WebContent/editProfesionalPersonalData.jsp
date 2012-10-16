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
	
			$("form[name='EditProfesionalPersonalDataForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'phoneAreaCode': {required: false, digits: true},
							'phoneNumber': {required: false, digits: true},
							'phoneExtension': {required: false, digits: true}
					},
					messages: {
						'phoneAreaCode': {digits: "<img id='phoneAreaCodeerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"} ,
						'phoneNumber': { igits: "<img id='phoneNumbererrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"},
						'phoneExtension': {digits: "<img id='phoneExtensionerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"}
					}
				});

			
			$("input[name=birthdate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});

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
			<h1>Editar datos personales</h1>
			<h2>Modific&aacute; o actualiz&aacute; tus datos personales</h2>
		</div>
		<div id="formContent">
			<html:form method="POST" action="/saveProfesionalPersonalData">
				<% EditProfesionalPersonalDataForm profesionalForm = (EditProfesionalPersonalDataForm)session.getAttribute("EditProfesionalPersonalDataForm"); %>
				<div id="formSection">
					<div class="myRow">
						<div class="myLabel width80">Nombre</div>
						<div class="myLabel width250" id="Nombre"><html:text name="EditProfesionalPersonalDataForm" property="firstname" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.firstname_key + ".err")%></div>
						<div class="myLabel width50">&nbsp;</div>
						<div class="myLabel width80">Apellido</div>
						<div class="myLabel width250" id="Apellido"><html:text name="EditProfesionalPersonalDataForm" property="lastname" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.lastname_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Cod. &aacute;rea</div>
						<div class="myLabel width60" id="Telefono"><html:text name="EditProfesionalPersonalDataForm" property="phoneAreaCode" styleClass="normalField width40"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneareacode_key + ".err")%></div>
						<div class="myLabel width50">N&uacute;mero</div>
						<div class="myLabel width120" id="Telefono"><html:text name="EditProfesionalPersonalDataForm" property="phoneNumber" styleClass="normalField width90"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonenumber_key + ".err")%></div>
						<div class="myLabel width20">Int.</div>
						<div class="myLabel width100" id="Telefono"><html:text name="EditProfesionalPersonalDataForm" property="phoneExtension" styleClass="normalField width40"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneextension_key + ".err")%></div>
						<div class="myLabel width30">Tipo</div>
						<div class="myLabel width250" id="Telefono"><html:select name="EditProfesionalPersonalDataForm" property="phoneType" styleClass="normalField width200">
								<option value="">Seleccione</option><%-- 
								--%><% for (String type : PhoneType.getPhoneTypes()) { %><%--
									--%><option <%=	type.equals(profesionalForm.getPhoneType()) ? "selected" : ""%> value="<%=type%>"><%--
											--%><%=type%></option>
								<% } %>
							</html:select>
							&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonetype_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Sexo</div>
						<div class="myLabel width300" id="Sexo"><html:radio name="EditProfesionalPersonalDataForm" property="sex" value="m" /> Masculino&nbsp;&nbsp;&nbsp;<html:radio name="EditProfesionalPersonalDataForm" property="sex" value="f" /> Femenino&nbsp;&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.sex_key + ".err")%></div>
						<div class="myLabel width80">Fecha nacim.</div>
						<div class="myLabel width250" id="Fecha Nac."><html:text name="EditProfesionalPersonalDataForm" property="birthdate" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.birthdate_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">E-Mail</div>
						<div class="myLabel width800" id="Email"><strong><%=profesionalForm.getEmail() %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width80">Clave</div>
						<div class="myLabel width150" id="Password"><html:password name="EditProfesionalPersonalDataForm" property="password" styleClass="normalField width150"/></div>
						<div class="myLabel width150">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.password_key + ".err")%></div>
						<div class="myLabel width80">Repetir clave</div>
						<div class="myLabel width150" id="Password"><html:password name="EditProfesionalPersonalDataForm" property="retypepassword" styleClass="normalField width150"/></div>
						<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.retypepassword_key + ".err")%></div>
					</div>
					<div class="myRow" align="center"><input type="submit" value="Grabar y volver a mi cuenta" /></div>
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