<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.utils.LocalizationUtils"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalServiceAreaForm"%>
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalSellForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="editProfesionalServiceArea"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Mi cuenta (Editar mis productos)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){

		$('#upload0').ajaxfileupload({
		  	'action': './uploadProductImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#image0').attr('src', './viewProductImage.do?index=0');	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		$('#upload1').ajaxfileupload({
		  	'action': './uploadProductImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#image1').attr('src', './viewProductImage.do?index=1');	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		$('#upload2').ajaxfileupload({
		  	'action': './uploadProductImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#image2').attr('src', './viewProductImage.do?index=2');	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		$('#upload3').ajaxfileupload({
		  	'action': './uploadProductImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#image3').attr('src', './viewProductImage.do?index=3');	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		$('#upload4').ajaxfileupload({
		  	'action': './uploadProductImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#image4').attr('src', './viewProductImage.do?index=4');	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		

		<%@ include file="includes/add_sell_js.jspf"%>
});


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
			<h1>Editar productos</h1>
			<h2>Agrega todos los productos que quieras, luego desde ac&aacute; podr&aacute;s editarlos o eliminarlos</h2>
		</div>
		<div id="formContent">
		<html:form method="POST" action="/saveProfesionalProducts">
			<% EditProfesionalSellForm EditProfesionalSellForm = (EditProfesionalSellForm)session.getAttribute("EditProfesionalSellForm"); %>
			<div id="formSection" style="width:920px;">
				<% if (EditProfesionalSellForm.isEdition()) { %>
					<h2>Modificar Producto</h2>
				<% } else { %>
					<h2>Agregar Producto</h2>
				<% } %>
				<div class="myRow mTop20" id="addproductlayer" style="<%=EditProfesionalSellForm.isEdition() ? "display: none;" : ""%>">
					<div class="myLabel width100per">1) Primer paso: <a class="inputButtonHelper" style="color:#000000; text-decoration:none;" id="addProduct">Seleccion&aacute; la categor&iacute;a</a></div>
				</div>
				<%@ include file="includes/add_sell_layers.jspf"%>
				<div id="addSellLayer" style="<%=EditProfesionalSellForm.isEdition() ? "" : "display: none;"%>">
					<div class="myRow">
						<div class="myLabel width100per"><span id="categoryPath" class="prodServCategoryPath"><%=EditProfesionalSellForm.isEdition() ? EditProfesionalSellForm.getCategorySelected() : ""%></span></div>
					</div>
					<html:text name="EditProfesionalSellForm" property="categoryId" styleClass="hidden"/>
					<html:text name="EditProfesionalSellForm" property="categorySelected" styleClass="hidden"/>
					<div class="myRow">
						<div class="myLabel width70">Producto</div>
						<div class="myLabel width850"><html:text name="EditProfesionalSellForm" property="sellName" styleClass="normalField width800"/></div>
					</div>
					<div class="myRow">
						<div class="myLabel width70">Descripci&oacute;n</div>
						<div class="myLabel width600 height60"><html:textarea name="EditProfesionalSellForm" property="sellDescription" styleClass="normalField width600 height50"/></div>
						<div class="myLabel width50">&nbsp;</div>
						<div class="myLabel width90">Precio Unitario</div>
						<div class="myLabel width70"><html:text name="EditProfesionalSellForm" property="referenceprice" styleClass="normalField width60"/></div>
					</div>
					<div class="myRow">
						<div class="myLabel width920 subAtForm"><span>Agregar fotos al producto</span></div>
					</div>
					<div class="myRow">
						<div class="label width80 height80">
							<logic:notEqual name="EditProfesionalSellForm" property="imageId0" value="0">
								<img id="image0" src="./viewProductImage.do?index=0" width="78" height="78" align="absmiddle" border="1">
							</logic:notEqual>
						</div>
						<div class="label width80 height80">
							<logic:equal name="EditProfesionalSellForm" property="imageId0" value="0">
								<img id="image0" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
							</logic:equal>
						</div>
						<div class="label width300"><input type="file" name="upload0" id="upload0"><br/><a href="./deleteProductImage.do?index=1">Borrar</a><br/><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalSellForm.image_key + ".err")%></div>
						<div class="label width80 height80">
							<logic:notEqual name="EditProfesionalSellForm" property="imageId1" value="0">
								<img id="image1" src="./viewProductImage.do?index=1" width="78" height="78" align="absmiddle" border="1">
							</logic:notEqual>
						</div>
						<div class="label width80 height80">
							<logic:equal name="EditProfesionalSellForm" property="imageId1" value="0">
								<img id="image1" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
							</logic:equal>
						</div>
						<div class="label width300"><input type="file" name="upload1" id="upload1"><br/><a href="./deleteProductImage.do?index=2">Borrar</a><br/><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalSellForm.image_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="label width80 height80">
							<logic:notEqual name="EditProfesionalSellForm" property="imageId2" value="0">
								<img id="image2" src="./viewProductImage.do?index=2" width="78" height="78" align="absmiddle" border="1">
							</logic:notEqual>
						</div>
						<div class="label width80 height80">
							<logic:equal name="EditProfesionalSellForm" property="imageId2" value="0">
								<img id="image2" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
							</logic:equal>
						</div>
						<div class="label width300"><input type="file" name="upload2" id="upload2"><br/><a href="./deleteProductImage.do?index=3">Borrar</a><br/><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalSellForm.image_key + ".err")%></div>
						<div class="label width80 height80">
							<logic:notEqual name="EditProfesionalSellForm" property="imageId3" value="0">
								<img id="image3" src="./viewProductImage.do?index=3" width="78" height="78" align="absmiddle" border="1">
							</logic:notEqual>
						</div>
						<div class="label width80 height80">
							<logic:equal name="EditProfesionalSellForm" property="imageId3" value="0">
								<img id="image3" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
							</logic:equal>
						</div>
						<div class="label width300"><input type="file" name="upload3" id="upload3"><br/><a href="./deleteProductImage.do?index=4">Borrar</a><br/><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalSellForm.image_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="label width80 height80">
							<logic:notEqual name="EditProfesionalSellForm" property="imageId4" value="0">
								<img id="image4" src="./viewProductImage.do?index=4" width="78" height="78" align="absmiddle" border="1">
							</logic:notEqual>
						</div>
						<div class="label width80 height80">
							<logic:equal name="EditProfesionalSellForm" property="imageId4" value="0">
								<img id="image4" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
							</logic:equal>
						</div>
						<div class="label width300"><input type="file" name="upload4" id="upload4"><br/><a href="./deleteProductImage.do?index=5">Borrar</a><br/><%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalSellForm.image_key + ".err")%></div>
					</div>
					<div class="myRow">
						<div class="myLabel width100per" align="center"><a href="javascript:document.EditProfesionalSellForm.action='./cancelProfesionalProductsAddSell.do';document.EditProfesionalSellForm.submit();" id="cancelAddSell">Cancelar</a>&nbsp;<input type="button" value="Guardar" onclick="javascript:document.EditProfesionalSellForm.action='./editProfesionalProductsAddSell.do';document.EditProfesionalSellForm.submit();"/></div>
					</div>
					<div class="myRow">
						<div class="myLabel width920 subAtForm">&nbsp;</div>
					</div>
					</div>
					<h2>Mis Productos</h2>
					<div class="myRow">
						<%
						java.util.List source = EditProfesionalSellForm.getSells();
						com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
						request.setAttribute( "sells",  paginated);
						%>
						<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./registroProfesional.jsp">
							<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width350" property="name"></display:column>
							<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width50" property="sellTypeDescription"></display:column>
							<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width350" property="categoryText"></display:column>
							<display:column title="Precio U" sortable="true" sortName="precio" headerClass="sortable width80">
								<%= LocalizationUtils.formatPrice(((SellBean)pageContext.getAttribute("sells")).getReferencePrice())%>
							</display:column>
							<display:column title="Acciones" headerClass="sortable width100"><a class="nonelyLink" href="./editProfesionalProduct.do?index=<%= ((SellBean)pageContext.getAttribute("sells")).getIndex()%>">Editar</a> <a class="nonelyLink" href="javascript:document.EditProfesionalSellForm.action='./editRemoveProduct.do?index=<%= ((SellBean)pageContext.getAttribute("sells")).getIndex()%>';document.EditProfesionalSellForm.submit();">Quitar</a></display:column>
						</display:table>
						<%=DisplayTagParamHelper.getFields(request)%>
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