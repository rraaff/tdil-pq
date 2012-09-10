<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalBusinessDataForm"%>
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
<title>Tua Festa | R008-M1- Registro - Registro Clientes (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$('#upload_logo').ajaxfileupload({
		  	'action': './uploadProfesionalLogo.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#logo_img').attr('src', './viewProfesionalLogo.do');	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
	}
);
</script>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Editar datos profesionales</h1>
			<h2></h2>
		</div>
		<div id="formContent">
		<html:form method="POST" action="/saveProfesionalBusinessData">
			<% EditProfesionalBusinessDataForm profesionalForm = (EditProfesionalBusinessDataForm)session.getAttribute("EditProfesionalBusinessDataForm"); %>
			<div id="formSection">
			<div id="formSection" class="width650">
				<div class="myRow">
					<div class="myLabel width200">Nombre profesional o de la empresa</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="businessname" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.businessname_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">CUIT</div>
					<div class="myLabel width100" id=""><html:text name="EditProfesionalBusinessDataForm" property="cuit" styleClass="normalField width100"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.cuit_key + ".err")%></div>
					<div class="myLabel width30">IIBB</div>
					<div class="myLabel width100" id=""><html:text name="EditProfesionalBusinessDataForm" property="iibb" styleClass="normalField"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.iibb_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width50">Ubicaci&oacute;n</div>
					<div class="myLabel width160">
						<html:select name="EditProfesionalBusinessDataForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2EditBusiness.do';this.form.submit()" styleClass="normalField width150">
							<option value="0">Seleccione</option><%-- 
							--%><% for (Geo2 geo2 : profesionalForm.getLevel2()) { %><%--
								--%><option <%=	geo2.getId() == profesionalForm.getGeo2Id() ? "selected" : ""%> value="<%=String.valueOf(geo2.getId())%>"><%--
										--%><%=geo2.getNombre()%></option>
							<% } %>
						</html:select>
						<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo2_key + ".err")%>
					</div>
					<div class="myLabel width20"></div>
					<div class="myLabel width210">
						<html:select name="EditProfesionalBusinessDataForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3EditBusiness.do';this.form.submit()" styleClass="normalField width200">
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
					<div class="myLabel width210">
						<html:select name="EditProfesionalBusinessDataForm" property="geo4Id" styleClass="normalField width200">
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
				<div class="myRow">
					<div class="label width100 height80">
						<logic:notEqual name="EditProfesionalBusinessDataForm" property="imageId" value="0">
							<img id="logo_img" src="./viewProfesionalLogo.do" width="78" height="78" align="absmiddle" border="1">
						</logic:notEqual>
					</div>
					<div class="label width100 height80">
						<logic:equal name="EditProfesionalBusinessDataForm" property="imageId" value="0">
							<img id="logo_img" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
						</logic:equal>
					</div>
					<div class="label width200"><input type="file" name="upload_logo" id="upload_logo"></div>
					<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalBusinessDataForm.logo_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Descripcion</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="description" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.description_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Video1</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="video1" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video1_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Video2</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="video2" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video2_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Video3</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="video3" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video3_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Video4</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="video4" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video4_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width200">Video5</div>
					<div class="myLabel width380" id=""><html:text name="EditProfesionalBusinessDataForm" property="video5" styleClass="normalField width350"/></div>
					<div class="myLabel width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video5_key + ".err")%></div>
				</div>
			</div>
			<div class="myRow width650" align="center"><input type="image" value=" " class="" src="images/skin_basic/buttons/registroClientes.png" /></div>
		</html:form>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>