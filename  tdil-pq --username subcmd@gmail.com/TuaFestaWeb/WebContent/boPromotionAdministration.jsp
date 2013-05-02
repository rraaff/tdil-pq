<%@page import="com.tdil.tuafesta.struts.forms.PromotionForm"%>
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.tuafesta.model.Promotion"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="systemPropertyAdministration"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
var maxindex = <bean:write name="PromotionForm" property="maxImages" />;
$(document).ready(
	function(){
		$("input[name=startdate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
			changeYear: true, yearRange: "1900:2020"});
		$("input[name=enddate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
			changeYear: true, yearRange: "1900:2020"});

		$('#upload_img').ajaxfileupload({
		  	'action': './uploadPromotionImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		var tr = $('<tr></tr>').appendTo( $('#image_gal_tab') );
		  		var tdpos = $('<td align="center">' + (maxindex + 1) + '</td>').appendTo( tr );
		  		var tdimg = $('<td align="center"><img id="ranking_' + maxindex + '" src="./viewPromotionImage.do?index=' + maxindex + '" width="66" height="40" align="absmiddle"></td>').appendTo( tr );
		  		var tdops = $('<td align="center" width="100"><a href="javascript:document.PromotionForm.action=\'./movePromotionImageUp.do?index=' + maxindex + '\';document.PromotionForm.submit();"><img src="boImages/subir.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>' + '<a href="javascript:document.PromotionForm.action=\'./movePromotionImageDown.do?index=' + maxindex + '\';document.PromotionForm.submit();"><img src="boImages/bajar.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>' + '<a href="javascript:document.PromotionForm.action=\'./deletePromotionImage.do?index=' + maxindex + '\';document.PromotionForm.submit();"><img src="boImages/borrar.png" alt="Borrar" width="20" height="20" hspace="5" border="0"></a></td>').appendTo( tr );
		  		maxindex = maxindex + 1;
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
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Administraci&oacute;n de Promotions</h1>
		<div id="formulariosBase">
			<div class="myRow"><span class="comment">Desde aquí podrá configurar las promociones.</span></div>
			<html:form method="POST" action="/savePromotion">
			<div style="float:left;" class="width350">
				<h2>Datos de la promoci&oacute;n</h2>
				<div class="myRow"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				<div class="myRow">
					<div class="myLabel width80">Nombre</div>
					<div class="myLabel width250"><html:text name="PromotionForm" property="name" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.name_key + ".err")%></div>
				</div>
				<div class="myRow height100">
					<div class="myLabel width80">Descripci&oacute;n</div>
					<div class="myLabel width250 height100"><html:textarea name="PromotionForm" property="description" styleClass="normalField width200 height100"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.description_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Precio promo</div>
					<div class="myLabel width250"><html:text name="PromotionForm" property="price" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.price_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Inicio</div>
					<div class="myLabel width250"><html:text name="PromotionForm" property="startdate" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.startdate_key + ".err")%></div>
				</div>
				<div class="myRow">
					<div class="myLabel width80">Fin</div>
					<div class="myLabel width250"><html:text name="PromotionForm" property="enddate" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.enddate_key + ".err")%></div>
				</div>
			</div>
			<div style="float:left;" class="width580">
				<h2>Fotos de la promo</h2>
				<div class="myRow height60" style="margin-top:12px;">
					<div class="myLabel width580 height60"><input type="file" name="upload_img" id="upload_img">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.photo_key + ".err")%>
					<p><br/>Bajar PSD ejemplo para generar las im&aacute;genes de las promos <a href="images/files/imagenPromoCentral.rar">desde ac&aacute;</a></p>
					</div>
				</div>
				<div class="width580" style="height:190px; float:left; overflow:auto; border:solid 1px #CCCCCC;">
					<table width="580" id="image_gal_tab">
						<tr>
							<td class="headerTablas" width="50">Posici&oacute;n</td>
							<td class="headerTablas" width="430">Foto</td>
							<td class="headerTablas" width="100">Acciones</td>
						</tr>
						<logic:iterate id="selectedPosition" name="PromotionForm" property="photos" indexId="iterIndexPositions">  
							<tr>
								<td align="center"><%=iterIndexPositions + 1%></td>
								<td align="center"><img id="ranking_<%=iterIndexPositions%>" src="./viewPromotionImage.do?index=<%=iterIndexPositions%>" width="66" height="40" align="absmiddle"></td> 
								<td align="center" width="130">
									<a href="javascript:document.PromotionForm.action='./movePromotionImageUp.do?index=<%=iterIndexPositions%>';document.PromotionForm.submit();"><img src="boImages/subir.png" title="Subir" width="20" height="20" hspace="5" border="0"></a>
									<a href="javascript:document.PromotionForm.action='./movePromotionImageDown.do?index=<%=iterIndexPositions%>';document.PromotionForm.submit();"><img src="boImages/bajar.png" title="Subir" width="20" height="20" hspace="5" border="0"></a>
									<a href="javascript:document.PromotionForm.action='./deletePromotionImage.do?index=<%=iterIndexPositions%>';document.PromotionForm.submit();"><img src="boImages/borrar.png" title="Borrar" width="20" height="20" hspace="5" border="0"></a></td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			</div>
			<div style="width:100%; height:10px; margin-bottom:20px; border-bottom:dotted 1px #CCCCCC; float:left;">&nbsp;</div>
			<div style="width:460px; height:370px; overflow:auto; float:left;">
				<h2 style="margin-bottom:20px;">Productos/Servicios Agregados</h2>
				<div style="height:330px; overflow:auto; float:left;">
					<table width="100%">
						<tr>
							<td class="headerTablas">Nombre</td>
							<td class="headerTablas">Categor&iacute;a</td>
							<td class="headerTablas">Profesional</td>
							<td class="headerTablas" width="60">Acciones</td>
						</tr>
						<logic:iterate name="PromotionForm" property="sells"
							id="added" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td><bean:write name="added" property="name" /></td>
								<td><bean:write name="added" property="categoryText" /></td>
								<td><bean:write name="added" property="profesionalBusinessname" /></td>
								<td><a href="javascript:document.PromotionForm.action='./removeSellFromPromotion.do?index=<%=iterIndex%>';document.PromotionForm.submit();">Quitar</a></td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			</div>
			<div style="width:460px; height:360px;; margin-left:20px; float:left;">
				<h2 style="margin-bottom:20px;">Productos o servicios a agregar</h2>
				<div style="width:450px; height:317px; padding:5px; border:dotted 1px #CCCCCC;">
					<div class="myRow">
						<div class="myLabel width70">Buscar por:</div>
						<div class="myLabel width350"> <html:radio name="PromotionForm" property="searchForm.type" value="1" /> productos&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="PromotionForm" property="searchForm.type" value="2" />Servicios</div>
					</div>
					<div class="myRow">
						<div class="myLabel width60">Nombre</div>
						<div class="myLabel width150"><html:text name="PromotionForm" property="searchForm.name" styleClass="normalField width120"/></div>
						<div class="myLabel width80">Profesional</div>
						<div class="myLabel width120"><html:text name="PromotionForm" property="searchForm.profesionalBusinessname" styleClass="normalField width120"/></div>
					</div>
					<div class="myRow" align="center"><a class="nonelyLink" href="javascript:document.PromotionForm.action='./searchSellsForPromotion.do';document.PromotionForm.submit();">Buscar</a></div>
					<div class="myRow" style="height:200px; overflow:auto;">
						<table width="100%">
							<tr>
								<td class="headerTablas">Nombre</td>
								<td class="headerTablas">Categoria</td>
								<td class="headerTablas">Profesional</td>
								<td class="headerTablas" width="60">Acciones</td>
							</tr>
							<logic:iterate name="PromotionForm" property="searchForm.searchResult"
								id="iterSearch" indexId="iterIndex">
								<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
									<td><bean:write name="iterSearch" property="name" /></td>
									<td><bean:write name="iterSearch" property="categoryText" /></td>
									<td><bean:write name="iterSearch" property="profesionalbusinessname" /></td>
									<td><a href="javascript:document.PromotionForm.action='./addSellToPromotion.do?index=<%=iterIndex%>';document.PromotionForm.submit();">Agregar</a></td>
								</tr>
							</logic:iterate>
						</table>
					</div>
				</div>
			</div>
			<div class="myRow" style="margin-bottom:20px;" align="center">
				<logic:equal name="PromotionForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="PromotionForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="modify" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="reset" />
				</html:submit>
			</div>
		</html:form>
		<div class="myRow" style="margin-bottom:20px;">
			<table width="100%">
				<tr>
					<td class="headerTablas">Nombre</td>
					<td class="headerTablas">Inicio</td>
					<td class="headerTablas">Fin</td>
					<td class="headerTablas" width="60">Acciones</td>
				</tr>
				<logic:iterate name="PromotionForm" property="allPromotion"	id="iterSystemProperty" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left"><bean:write name="iterSystemProperty" property="name" />
						</td>
						<td><%=DateUtils.formatDateSp(((Promotion) iterSystemProperty).getStartdate()) %></td>
						<td><%=DateUtils.formatDateSp(((Promotion) iterSystemProperty).getEnddate()) %></td>
						<td align="center"><html:link action="/editPromotion" paramName="iterSystemProperty" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
							<html:link action="/toggleDeletedSystemProperty" paramName="iterSystemProperty"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1) { %>
									<img src="boImages/activar.png" alt="Activar">
								<% } else { %>
									<img src="boImages/desactivar.png" alt="Desactivar">
								<% } %>
							</html:link>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
	</div>
	</div>
</div>
</body>
</html>