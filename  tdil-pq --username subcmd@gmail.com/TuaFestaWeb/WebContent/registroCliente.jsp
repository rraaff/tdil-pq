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
<title>Tua Festa | R008-M1- Registro - Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
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

			$("input[name=birthdate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
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
		<div id="formContent">
			<html:form method="POST" action="/addClient">
			<div id="formSection">
				<div class="myRow">
					<div class="myLabel width50">Nombre</div>
					<div class="myLabel width200" id="Nombre"><html:text name="ClientForm" property="firstname" styleClass="normalField width150"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.firstname.err")%></div>
					<div class="myLabel width60">&nbsp;</div>
					<div class="myLabel width60">Apellido</div>
					<div class="myLabel width160" id="Apellido"><html:text name="ClientForm" property="lastname" styleClass="normalField width150"/></div>
					<div class="myLabel width40">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.lastname.err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Sexo</div>
					<div class="myLabel width200" id="Sexo"><html:radio property="sex" value="m" /> Masculino&nbsp;&nbsp;&nbsp;<html:radio property="sex" value="f" /> Femenino</div>
					<div class="myLabel width110">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.sex.err")%></div>
					<div class="myLabel width60">Fecha Nac.</div>
					<div class="myLabel width150" id="Fecha Nac."><html:text name="ClientForm" property="birthdate" styleClass="normalField width150"/></div>
					<div class="myLabel width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.birthdate.err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">E-Mail</div>
					<div class="myLabel width280" id="Email"><html:text name="ClientForm" property="email" styleClass="normalField width250"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.email.err")%></div>
					<div class="myLabel width40">Clave</div>
					<div class="myLabel width150" id="Password"><html:password name="ClientForm" property="password" styleClass="normalField width150"/></div>
					<div class="myLabel width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ClientForm.password.err")%></div>
					<div class="myLabel width250">TODO If por facebook // Borrar este div...</div>
				</div>
				<div class="myRow">
					<div class="myLabel width40">Reingresar clave</div>
					<div class="myLabel width150" id="Password"><html:password name="ClientForm" property="retypepassword" styleClass="normalField width150"/></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Ubicaci&oacute;n</div>
					<div class="myLabel width160">
						<% ClientForm clientForm = (ClientForm)session.getAttribute("ClientForm"); %>
						<html:select name="ClientForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Client.do';this.form.submit()" styleClass="normalField width150">
							<option value="0">Seleccione</option>
							<% for (Geo2 geo2 : clientForm.getLevel2()) { %>	
								<option	<%=	geo2.getId() == clientForm.getGeo2Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo2.getId())%>">
									<%=geo2.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
						<html:select name="ClientForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3Client.do';this.form.submit()" styleClass="normalField width200">
							<option value="0">Seleccione</option>
							<% for (Geo3 geo3 : clientForm.getLevel3()) { %>	
								<option	<%=	geo3.getId() == clientForm.getGeo3Id() ? "selected" : ""%>
									value="<%=String.valueOf(geo3.getId())%>">
									<%=geo3.getNombre()%></option>
							<% } %>
						</html:select>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
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
				<div class="myRow">
					<div class="myLabel" align="center"><input type="submit" value="ENVIAR DATOS DE REGISTRO<br/>Le enviaremos un E-Mail para validar la casilla de correo" class="buttonSubmit" /></div>
				</div>
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