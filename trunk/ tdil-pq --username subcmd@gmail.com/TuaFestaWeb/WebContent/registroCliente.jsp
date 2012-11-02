<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroCliente"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script type="text/javascript" src="js/jquery.ui.datepicker-es.js"></script>
<script>
$(document).ready(
	function(){
		
			$("form[name='ClientForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'firstname': {required: true},
							'lastname': {required: true},
							'sex': {required: true},
							'email': {required: true, email: true},
							'birthdate': {required: true},
							'password': {required: true},
							'retypepassword': {required: true}
					},
					messages: {
						'firstname': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'lastname': {required: "<img id='lastnameerror' src='images/unchecked.gif' hovertext='Ingrese el apellido.' />"},
						'sex': {required: "<img id='sexerror' src='images/unchecked.gif' hovertext='Seleccione el sexo.' />"}, 
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'birthdate': {required: "<img id='birthdateerror' src='images/unchecked.gif' hovertext='Ingrese su fecha de nacimiento.' />"},
						'password': {required: "<img id='passworderror' src='images/unchecked.gif' hovertext='Ingrese el password.' />"},
						'retypepassword': {required: "<img id='retypepassworderror' src='images/unchecked.gif' hovertext='Reingrese el password.' />"}
					}
				});

			$("input[name=birthdate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});

		}

	
	);

</script>
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Registro de Clientes (Gratuito 100%)</h1>
			<h2>Complet&aacute; los datos del formulario y segu&iacute; los pasos de la registraci&oacute;n.</h2>
		</div>
		<% ClientForm clientForm = (ClientForm)session.getAttribute("ClientForm");%>
		<div id="formContent">
			<html:form method="POST" action="/addClient">
			<div id="formSection" style="margin-bottom:20px;">
				<div class="myRow" style="padding-top:20px;">
					<div class="myLabel width80">Nombre</div>
					<div class="myLabel width180" id="Nombre"><html:text name="ClientForm" property="firstname" styleClass="normalField width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.firstname.err")%></div>
					<div class="myLabel width60">Apellido</div>
					<div class="myLabel width180" id="Apellido"><html:text name="ClientForm" property="lastname" styleClass="normalField width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.lastname.err")%></div>
					<div class="myLabel width50">Sexo</div>
					<div class="myLabel width200" id="Sexo"><html:radio property="sex" value="m" /> Masculino&nbsp;&nbsp;&nbsp;<html:radio property="sex" value="f" /> Femenino&nbsp;&nbsp;&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.sex.err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Fecha Nac.</div>
					<div class="myLabel width180" id="Fecha Nac."><html:text name="ClientForm" property="birthdate" styleClass="normalField width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.birthdate.err")%></div>
					<div class="myLabel width60">E-Mail</div>
					<div class="myLabel width450" id="Email">
						<% if (clientForm.isFacebookRegister()) { %>
							<bean:write name="ClientForm" property="email" />
						<% } else {  %>
							<html:text name="ClientForm" property="email" styleClass="normalField width400"/>
						<% } %>
					&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.email.err")%></div>
				</div>
				<% if (!clientForm.isFacebookRegister()) { %>
					<div class="myRow">
						<div class="myLabel width80">Clave</div>
						<div class="myLabel width160" id="Password"><html:password name="ClientForm" property="password" styleClass="normalField width120"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.password.err")%></div>
	
						<div class="myLabel width80">Repetir clave</div>
						<div class="myLabel width250" id="Password"><html:password name="ClientForm" property="retypepassword" styleClass="normalField width120"/></div>
					</div>
				<% } %>
				<div class="myRow">
					<div class="myLabel width80">Ubicaci&oacute;n</div>
					<div class="myLabel width200">
						<html:select name="ClientForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Client.do';this.form.submit()" styleClass="normalField width150">
							<option value="0">Seleccione</option>
							<% for (Geo2 geo2 : clientForm.getLevel2()) { %>	
								<option	<%=	geo2.getId() == clientForm.getGeo2Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo2.getId())%>">
									<%=geo2.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
					<div class="myLabel width250">
						<html:select name="ClientForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3Client.do';this.form.submit()" styleClass="normalField width200">
							<option value="0">Seleccione</option>
							<% for (Geo3 geo3 : clientForm.getLevel3()) { %>	
								<option	<%=	geo3.getId() == clientForm.getGeo3Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo3.getId())%>">
									<%=geo3.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
					<div class="myLabel width250">
						<html:select name="ClientForm" property="geo4Id" styleClass="normalField width200">
							<option value="0">Seleccione</option>
							<% for (Geo4 geo4 : clientForm.getLevel4()) { %>	
								<option	<%=	geo4.getId() == clientForm.getGeo4Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo4.getId())%>">
									<%=geo4.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
				</div>
				<div class="myRow" align="center"><input type="submit" value="Enviar datos" class="buttonSubmit" /></div>
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