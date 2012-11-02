<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Inicio | El sitio #1 en contactos entre profesionales y clientes del rubro eventos</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script>
$(document).ready(
	function(){
			$("form[name='OrganizeWizardForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("div"));
					},
					rules: { 'product': {required: true},
							'maxPrice': {required: true, digits: true}
					},
					messages: {
						'product': {required: "<img id='firstnameerror' src='images/unchecked.gif' hovertext='Ingrese el producto o servicio.' />"}, 
						'maxPrice': {required: "<img id='phoneAreaCodeerrorreq' src='images/unchecked.gif' hovertext='Ingrese precio maximo.' />",
							digits: "<img id='phoneAreaCodeerrordig' src='images/unchecked.gif' hovertext='Ingrese solo numeros.' />"}
					}
				});
	}
);

</script>
<%@ include file="includes/boErrorJS.jsp" %>
</head>

<body>
<%@ include file="includes/designHeader.jspf" %>

<html:form method="POST" action="/addToSearch">
	<div style="float:left;" class="width350">
		<div class="myRow">
			<div class="myLabel width80">Producto/Servicio</div>
			<div class="myLabel width250"><html:text name="OrganizeWizardForm" property="product" styleClass="normalField width200"/>&nbsp;</div>
		</div>
		<div class="myRow">
			<div class="myLabel width80">Precio</div>
			<div class="myLabel width250"><html:text name="OrganizeWizardForm" property="maxPrice" styleClass="normalField width200"/>&nbsp;</div>
		</div>
		<html:submit property="operation">Agregar</html:submit>
	</div>
</html:form>

<html:form method="POST" action="/searchWizard">
	<div class="myRow" style="height:200px; overflow:auto;">
		<table width="100%">
			<tr>
				<td class="headerTablas">Producto o Servicio</td>
				<td class="headerTablas">Precio Maximo</td>
				<td class="headerTablas" width="60">Acciones</td>
			</tr>
			<logic:iterate name="OrganizeWizardForm" property="searchSellBeans"
				id="iterSearch" indexId="iterIndex">
				<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
					<td><bean:write name="iterSearch" property="product" /></td>
					<td><bean:write name="iterSearch" property="maxPrice" /></td>
					<td><a href="./removeFromSearch.do?index=<%=iterIndex%>">Quitar</a></td>
				</tr>
			</logic:iterate>
		</table>
	</div>

	<div style="float:left;" class="width350">
		<div class="myRow">
			<div class="myLabel width80">Ubicacion</div>
			<div class="myLabel width250"><html:text name="OrganizeWizardForm" property="geoLevel" styleClass="normalField width200"/>&nbsp;</div>
		</div>
		<html:submit property="operation">Search</html:submit>
	</div>
</html:form>
			
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>