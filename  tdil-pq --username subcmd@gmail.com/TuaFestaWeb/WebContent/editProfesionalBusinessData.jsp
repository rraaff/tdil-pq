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
<title>Tua Festa | Mi cuenta (Datos profesionales)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
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
<style>
#formSection {
	width:920px;
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
			<h1>Editar datos profesionales</h1>
			<h2>&#161;Completa los datos de profesional, agr&eacute;gale v&iacute;deos y fotos, esta secci&oacute;n ser&aacute; la que primero vean tus futuros clientes!</h2>
		</div>
		<div id="formContent">
			<div id="formSection">
				<html:form method="POST" action="/saveProfesionalBusinessData">
					<% EditProfesionalBusinessDataForm profesionalForm = (EditProfesionalBusinessDataForm)session.getAttribute("EditProfesionalBusinessDataForm"); %>
					<div class="myRow">
						<div class="myLabel width230">Nombre profesional o de la empresa</div>
						<div class="myLabel" style="width:690px;" id=""><html:text name="EditProfesionalBusinessDataForm" property="businessname" styleClass="normalField width650"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.businessname_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width40">CUIT</div>
						<div class="myLabel width130" id=""><html:text name="EditProfesionalBusinessDataForm" property="cuit" styleClass="normalField width80"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.cuit_key + ".err")%></div>
						<div class="myLabel width40">IIBB</div>
						<div class="myLabel width120" id=""><html:text name="EditProfesionalBusinessDataForm" property="iibb" styleClass="normalField width80"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.iibb_key + ".err")%></div>

						<div class="myLabel width60">Ubicaci&oacute;n</div>
						<div class="myLabel width150">
							<html:select name="EditProfesionalBusinessDataForm" property="geo2Id" onchange="this.form.action='./refreshGeoLevel2EditBusiness.do';this.form.submit()" styleClass="normalField width130">
								<option value="0">Seleccione</option><%-- 
								--%><% for (Geo2 geo2 : profesionalForm.getLevel2()) { %><%--
									--%><option <%=	geo2.getId() == profesionalForm.getGeo2Id() ? "selected" : ""%> value="<%=String.valueOf(geo2.getId())%>"><%--
											--%><%=geo2.getNombre()%></option>
								<% } %>
							</html:select>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo2_key + ".err")%>
						</div>
						<div class="myLabel width180">
							<html:select name="EditProfesionalBusinessDataForm" property="geo3Id" onchange="this.form.action='./refreshGeoLevel3EditBusiness.do';this.form.submit()" styleClass="normalField width150">
								<option value="0">Seleccione</option>
								<% for (Geo3 geo3 : profesionalForm.getLevel3()) { %>	
									<option	<%=	geo3.getId() == profesionalForm.getGeo3Id() ? "selected" : ""%>
										value="<%=String.valueOf(geo3.getId())%>">
										<%=geo3.getNombre()%></option>
								<% } %>
							</html:select>
							<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.geo3_key + ".err")%>
						</div>
						<div class="myLabel width180">
							<html:select name="EditProfesionalBusinessDataForm" property="geo4Id" styleClass="normalField width150">
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
						<div class="myLabel width80 height80">
							<logic:notEqual name="EditProfesionalBusinessDataForm" property="imageId" value="0">
								<img id="logo_img" src="./viewProfesionalLogo.do" width="78" height="78" align="absmiddle" border="1">
							</logic:notEqual>
						</div>
						<div class="myLabel width80 height80">
							<logic:equal name="EditProfesionalBusinessDataForm" property="imageId" value="0">
								<img id="logo_img" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
							</logic:equal>
						</div>
						<div class="myLabel width250 height100"><input type="file" name="upload_logo" id="upload_logo"><br/><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalBusinessDataForm.logo_key + ".err")%></div>
						<div class="myLabel width70 height100">Descripci&oacute;n</div>
						<div class="myLabel width440 height100"><html:textarea name="EditProfesionalBusinessDataForm" property="description" styleClass="normalField width400 height80"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.description_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width920 subAtForm"><span>Agregar videos a tu perfil</span></div>
					</div>
					<div class="myRow">
						<div class="myLabel width920 "><span class="comment">Agreg&aacute; hasta 5 direcciones de videos que ilustren tu perfil. Podes copiarlos de Youtube, por ejemplo. Los videos no son obligatorios.</span></div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Video 1</div>
						<div class="myLabel width400" id=""><html:text name="EditProfesionalBusinessDataForm" property="video1" styleClass="normalField width350"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video1_key + ".err")%></div>
						<div class="myLabel width60">Video 2</div>
						<div class="myLabel width400" id=""><html:text name="EditProfesionalBusinessDataForm" property="video2" styleClass="normalField width350"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video2_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Video 3</div>
						<div class="myLabel width400" id=""><html:text name="EditProfesionalBusinessDataForm" property="video3" styleClass="normalField width350"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video3_key + ".err")%></div>
						<div class="myLabel width60">Video 4</div>
						<div class="myLabel width400" id=""><html:text name="EditProfesionalBusinessDataForm" property="video4" styleClass="normalField width350"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video4_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Video 5</div>
						<div class="myLabel width400" id=""><html:text name="EditProfesionalBusinessDataForm" property="video5" styleClass="normalField width350"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, ProfesionalForm.video5_key + ".err")%></div>
					</div>
					<div class="myRow" align="center"><a href="./goToProfesionalHome.do?id=<%=websiteUser.getProfesional().getId()%>">Volver a mi cuenta sin grabar</a>&nbsp;<input type="submit" value="Grabar y volver a mi perfil" /></div>
				</html:form>
			</div>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>