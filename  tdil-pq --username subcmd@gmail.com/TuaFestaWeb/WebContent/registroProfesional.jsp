<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.utils.LocalizationUtils"%>
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ProductBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroProfesional"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Registro Profesional (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script type="text/javascript" src="js/jquery.ui.datepicker-es.js"></script>
<script>
$(document).ready(
	function(){
	
			$("form[name='ProfesionalForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'firstname': {required: true},
							'lastname': {required: true},
							'birthdate': {required: true},
							'email': {required: true, email: true},
							'phoneAreaCode': {required: true, digits: true},
							'phoneNumber': {required: true, digits: true},
							'phoneExtension': {required: false, digits: true},
							'phoneType': {required: true},
							'sex': {required: true},
							'password': {required: true},
							'retypepassword': {required: true},
							'businessname': {required: true},
							'cuit': {required: true},
							'iibb': {required: true},
							'geo2Id': {required: true},
							'geo3Id': {required: true},
							'geo4Id': {required: true}
					},
					messages: {
						'firstname': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'lastname': {required: "<img id='lastnameerror' src='images/unchecked.gif' hovertext='Ingrese el apellido.' />"}, 
						'birthdate': {required: "<img id='birthdateerror' src='images/unchecked.gif' hovertext='Ingrese la fecha de nacimiento.' />"}, 
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'phoneAreaCode': {required: "<img id='phoneAreaCodeerrorreq' src='images/unchecked.gif' hovertext='Ingrese el codigo de area.' />",
							digits: "<img id='phoneAreaCodeerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"} ,
						'phoneNumber': {required: "<img id='phoneNumbererrorreq' src='images/unchecked.gif' hovertext='Ingrese el numero de telefono.' />",
							digits: "<img id='phoneNumbererrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"},
						'phoneExtension': {digits: "<img id='phoneExtensionerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"},
						'phoneType': {required: "<img id='phoneTypeerror' src='images/unchecked.gif' hovertext='Seleccione el tipo de telefono.' />"},
						'sex': {required: "<img id='sexerror' src='images/unchecked.gif' hovertext='Seleccione el sexo.' />"},
						'password': {required: "<img id='passworderror' src='images/unchecked.gif' hovertext='Ingrese el password.' />"},
						'retypepassword': {required: "<img id='retypepassworderror' src='images/unchecked.gif' hovertext='Reingrese el password.' />"},
						'businessname': {required: "<img id='businessnameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre comercial.' />"},
						'geo2Id': {required: "<img id='geo2iderror' src='images/unchecked.gif' hovertext='Seleccione la provincia.' />"},
						'geo3Id': {required: "<img id='geo3iderror' src='images/unchecked.gif' hovertext='Seleccione el partido.' />"},
						'geo4Id': {required: "<img id='geo4iderror' src='images/unchecked.gif' hovertext='Seleccione la localidad.' />"}
						
					}
				});

			
			$("input[name=birthdate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, minDate: "-100Y", maxDate: "+0D"});
			

			<%@ include file="includes/add_sell_js.jspf"%>
		}

	
	);

</script>
<%@ include file="includes/boErrorJS.jsp" %>
<style>
#formContent {
	margin-right:0px;
	padding-left:10px;
	width:710px;
	float:left;
	overflow:hidden;
}
#formSection {
	width:680px;
}
#formHelpers {
	margin:8px;
	width:210px;
	height:700px;
	float:left;
}
#formHelpers .helper {
	background-image: url(images/skin_basic/backgrounds/cartelitoRegistracion.png);
	background-repeat: no-repeat;
	background-position: center top;
	
	padding-left:36px;
	padding-top:24px;
	padding-right:10px;
	padding-bottom:20px;
}
.myRow {
	padding-left:40px;
}
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Registro de profesionales (Gratuito 100%)</h1>
			<h2>Complet&aacute; los datos del formulario y segu&iacute; los pasos de la registraci&oacute;n para poder publicar</h2>
		</div>
		<div id="formContent">
			<html:form method="POST" action="/addProfesional">
				<% ProfesionalForm profesionalForm = (ProfesionalForm)session.getAttribute("ProfesionalForm"); %>
				<div id="formSection">
					<h2>Datos personales</h2>
					<div class="myRow"><!-- 680 -->
						<div class="myLabel width70">Nombre</div>
						<div class="myLabel width250" id="Nombre"><html:text name="ProfesionalForm" property="firstname" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.firstname.err")%></div>
						<div class="myLabel width70">Apellido</div>
						<div class="myLabel width250" id="Apellido"><html:text name="ProfesionalForm" property="lastname" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.lastname.err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width70">Cod. &aacute;rea</div>
						<div class="myLabel width50" id="Telefono"><html:text name="ProfesionalForm" property="phoneAreaCode" styleClass="normalField width30"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneareacode_key + ".err")%></div>
						<div class="myLabel width50">N&uacute;mero</div>
						<div class="myLabel width150" id="Telefono"><html:text name="ProfesionalForm" property="phoneNumber" styleClass="normalField width100"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonenumber_key + ".err")%></div>
						<div class="myLabel width20">Int.</div>
						<div class="myLabel width60" id="Telefono"><html:text name="ProfesionalForm" property="phoneExtension" styleClass="normalField width40"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phoneextension_key + ".err")%></div>
						<div class="myLabel width40">Tipo</div>
						<div class="myLabel width200" id="Telefono"><html:select name="ProfesionalForm" property="phoneType" styleClass="normalField width150">
								<option value="">Seleccione</option><%-- 
								--%><% for (String type : PhoneType.getPhoneTypes()) { %><%--
									--%><option <%=	type.equals(profesionalForm.getPhoneType()) ? "selected" : ""%> value="<%=type%>"><%--
											--%><%=type%></option>
								<% } %>
							</html:select>
						&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.phonetype_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width70">Sexo</div>
						<div class="myLabel width250" id="Sexo"><html:radio property="sex" value="m" /> Masculino&nbsp;&nbsp;&nbsp;<html:radio property="sex" value="f" /> Femenino&nbsp;&nbsp;&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.sex.err")%></div>
						<div class="myLabel width70">Fecha Nac.</div>
						<div class="myLabel width240" id="Fecha Nac."><html:text name="ProfesionalForm" property="birthdate" styleClass="normalField width200"/><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.birthdate.err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width70">E-Mail</div>
						<div class="myLabel width300" id="Email">
							<% if (profesionalForm.isFacebookRegister()) { %>
								<bean:write name="ProfesionalForm" property="email" />
							<% } else { %>
								<html:text name="ProfesionalForm" property="email" styleClass="normalField width200"/>
							<% } %>
						&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.email.err")%></div>
					</div>
					<% if (!profesionalForm.isFacebookRegister()) { %>
						<div class="myRow">
							<div class="myLabel width70">Clave</div>
							<div class="myLabel width240" id="Password"><html:password name="ProfesionalForm" property="password" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.password.err")%></div>
							<div class="myLabel width80">Repetir clave</div>
							<div class="myLabel width250" id="Password"><html:password name="ProfesionalForm" property="retypepassword" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalForm.retypepassword.err")%></div>
						</div>
					<% } %>
					<div class="width700 height25" style="float:left;">&nbsp;</div>
					<h2>Datos profesionales</h2>
					<div class="myRow">
						<div class="myLabel width240">Nombre profesional o de tu empresa</div>
						<div class="myLabel width400" id=""><html:text name="ProfesionalForm" property="businessname" styleClass="normalField width350"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.businessname_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width70">Ubicaci&oacute;n</div>
						<div class="myLabel width160">
							<html:select name="ProfesionalForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2Profesional.do';this.form.submit()" styleClass="normalField width150">
								<option value="0">Seleccione</option><%-- 
								--%><% for (Geo2 geo2 : profesionalForm.getLevel2()) { %><%--
									--%><option <%=	geo2.getId() == profesionalForm.getGeo2Id() ? "selected" : ""%> value="<%=String.valueOf(geo2.getId())%>"><%--
											--%><%=geo2.getNombre()%></option>
								<% } %>
							</html:select>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo2_key + ".err")%>
						</div>
						<div class="myLabel width20"></div>
						<div class="myLabel width200">
							<html:select name="ProfesionalForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3Profesional.do';this.form.submit()" styleClass="normalField width180">
								<option value="0">Seleccione</option>
								<% for (Geo3 geo3 : profesionalForm.getLevel3()) { %>	
									<option	<%=	geo3.getId() == profesionalForm.getGeo3Id() ? "selected" : ""%>
										value="<%=String.valueOf(geo3.getId())%>">
										<%=geo3.getNombre()%></option>
								<% } %>
							</html:select>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo3_key + ".err")%>
						</div>
						<div class="myLabel width20"></div>
						<div class="myLabel width200">
							<html:select name="ProfesionalForm" property="geo4Id" styleClass="normalField width160">
								<option value="0">Seleccione</option>
								<% for (Geo4 geo4 : profesionalForm.getLevel4()) { %>	
									<option	<%=	geo4.getId() == profesionalForm.getGeo4Id() ? "selected" : ""%>
										value="<%=String.valueOf(geo4.getId())%>">
										<%=geo4.getNombre()%></option>
								<% } %>
							</html:select>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo4_key + ".err")%>
						</div>
					</div>
					<div class="width700 height120" style="float:left;">&nbsp;</div>
					<h2>Agregue productos y servicios que desee ofrecer</h2>
					<div class="myRow" align="center">
						<div class="myLabel width100per width20">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.sells_key + ".err")%></div>
					</div>
					<div class="myRow width600 comment">
						<p>Agreg&aacute; todos los productos o servicios que desees. No es obligatorio hacerlo ahora. Podes hacerlo una vez que tu perfil haya sido validado. Es importante que el producto o servicio que agregues est&eacute; en la categor&iacute;a correcta. Eso te beneficiar&aacute; en las b&uacute;squedas.</p>
					</div>
					<div class="myRow">
						<div class="myLabel width650">&#191;Desea incorporar producto o servicios a su perfil? Si,<a class="nonelyLink" id="addProduct">agregar producto,</a>o<a class="nonelyLink" id="addService">agregar servicio.</a></div>
					</div>
					<%@ include file="includes/add_sell_layers.jspf"%>
					<div id="addSellLayer" style="display: none;">
						<div class="myRow">
							<div class="myLabel width650"><span id="categoryPath" class="prodServCategoryPath"></span></div>
						</div>
						<html:text name="ProfesionalForm" property="categoryId" styleClass="hidden"/>
						<html:text name="ProfesionalForm" property="categorySelected" styleClass="hidden"/>
						<div class="myRow">
							<div class="myLabel width70">Art&iacute;culo<!-- meter un if si es servicio que diga "Servicio" --></div>
							<div class="myLabel width350"><html:text name="ProfesionalForm" property="sellName" styleClass="normalField width300"/></div>
							<div class="myLabel width90">Precio Unitario<!-- meter un if si es servicio que diga "Precio base" --></div>
							<div class="myLabel width70"><html:text name="ProfesionalForm" property="referenceprice" styleClass="normalField width60"/></div>
						</div>
						<div class="myRow">
							<div class="myLabel width70">Descripci&oacute;n</div>
							<div class="myLabel width500 height100"><html:textarea name="ProfesionalForm" property="sellDescription" styleClass="normalField width500 height80"/></div>
						</div>
						<div class="myRow" align="center"><a href="javascript:document.ProfesionalForm.action='./registroProfesionalAddSell.do';document.ProfesionalForm.submit();" id="doAddSell">Agregar</a> o <a href="#" id="cancelAddSell">Cancelar</a></div>
					</div>
					
					<div class="myRow width600">
						<%
						java.util.List source = profesionalForm.getSells();
						com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
						request.setAttribute( "sells",  paginated);
						%>
						<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./registroProfesional.jsp">
							<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width200" property="name"></display:column>
							<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width50" property="sellTypeDescription"></display:column>
							<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width250" property="categoryText"></display:column>
							<display:column title="Precio" sortable="true" sortName="precio" headerClass="sortable width50">
								<%= LocalizationUtils.formatPrice(((SellBean)pageContext.getAttribute("sells")).getReferencePrice())%>
							</display:column>
							<display:column title="acciones" headerClass="sortable width50"><a class="nonelyLink" href="javascript:document.ProfesionalForm.action='./registroProfesionalRemoveSell.do?index=<%= ((SellBean)pageContext.getAttribute("sells")).getIndex()%>';document.ProfesionalForm.submit();">Quitar</a>
							</display:column>
						</display:table>
						<div style="color:#00FF99;"><%=DisplayTagParamHelper.getFields(request)%></div>
					</div>
					<div class="myRow width650" align="center"><input type="submit" value="Enviar datos" class="" /></div>
				</div>
			</html:form>
		</div>
		<!-- aca Termina el formulario -->
		<div id="formHelpers">
			<div class="helper" style="margin-top:20px; height:180px;">Completa con tus datos b&aacute;sicos, luego de finalizar el registro <strong>podes editar tu perfil</strong> desde <strong>Mi Cuenta</strong>.<br /><br/><br/>El <strong>tel&eacute;fono</strong> que ingreses ser&aacute; tu <strong>tel&eacute;fono de contacto</strong>. </div>
			<div class="helper" style="margin-top:40px; height:180px;">Ingresa tu nombre o el <strong>nombre de tu empresa</strong> (el nombre que utilices para <strong>representar tu negocio</strong>).<br /><br /><br />En la <strong>ubicaci&oacute;n</strong> debes poner la <strong>ciudad o barrio</strong> donde se <strong>encuentre tu empresa</strong>.<br /><br />Luego en tu perfil <strong>podr&aacute;s completar tus &aacute;reas de cobertura para tus servicios</strong>.</div>
			<div class="helper" style="margin-top:20px; height:180px;">Podes agregar los productos/servicios que quieras, sino podes hacerlo en "Mi cuenta" cuando termines el registro.</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>