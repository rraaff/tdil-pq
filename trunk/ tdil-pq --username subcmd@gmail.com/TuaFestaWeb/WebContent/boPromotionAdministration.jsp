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
		$("input[name=startdate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
			changeYear: true, yearRange: "1900:2012"});
		$("input[name=enddate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
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
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Administraci&oacute;n de Promotions</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde aquí podrá configurar las promociones.</span></div>
	</div>
	<html:form method="POST" action="/savePromotion">
	<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span>
		<div class="renglon width860">
			<div class="label width80">Nombre</div>
			<div class="label width200"><html:text name="PromotionForm" property="name"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.name_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width80">Descripcion</div>
			<div class="label width200"><html:text name="PromotionForm" property="description"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.description_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width80">Precio</div>
			<div class="label width200"><html:text name="PromotionForm" property="price"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.price_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width50">Inicio</div>
			<div class="label width200"><html:text name="PromotionForm" property="startdate"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.startdate_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width80">Fin</div>
			<div class="label width100"><html:text name="PromotionForm" property="enddate"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.enddate_key + ".err")%></div>
		</div>
		
		<div id="conteinerScrollable" style="float:left; width:950px; height:335px; overflow:auto; border:#FF0000;">
			<h2>Fotos</h2>
			<div class="width420 height250" style="float:left; overflow:auto;">
				<table width="380" id="image_gal_tab">
					<tr>
						<td class="headerTablas" width="50">Posici&oacute;n</td>
						<td class="headerTablas">Foto</td>
						<td class="headerTablas">Acciones</td>
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
			<div class="width500 height250" style="float:right;">
				<div class="label width500 height25"></div>
				<div class="label width200 height60"><input type="file" name="upload_img" id="upload_img"></div>
				<div class="label width50 height60"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.photo_key + ".err")%></div>
			</div>
		</div>
		
		<div id="conteinerScrollable" style="width:300px; height:335px; overflow:auto; border:#FF0000;">
			<table>
				<tr>
					<td colspan="4">Productos/Servicios Agregados</td>
				</tr>
				<tr>
					<td class="headerTablas">Nombre</td>
					<td class="headerTablas">Categoria</td>
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
		
		<div id="conteinerScrollable" style="width:300px; height:335px; overflow:auto; border:#FF0000;">
			<html:radio name="PromotionForm" property="searchForm.type" value="1" />Productos
			<html:radio name="PromotionForm" property="searchForm.type" value="2" />Servicios<br>
			Nombre:<html:text name="PromotionForm" property="searchForm.name"/><br>
			Profesional:<html:text name="PromotionForm" property="searchForm.profesionalBusinessname"/><br>
			<a class="nonelyLink" href="javascript:document.PromotionForm.action='./searchSellsForPromotion.do';document.PromotionForm.submit();">Buscar</a>
			<table>
				<td colspan="4">Productos/Servicios a agregar</td>
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
		
		<div class="renglon width860" style="margin-bottom:20px;" align="center">
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
	<div class="renglon width860" style="margin-bottom:20px;">
		<table>
			<tr>
				<td class="headerTablas">Nombre</td>
				<td class="headerTablas">Inicio</td>
				<td class="headerTablas">Fin</td>
				<td class="headerTablas" width="60">Acciones</td>
			</tr>
			<logic:iterate name="PromotionForm" property="allPromotion"
				id="iterSystemProperty" indexId="iterIndex">
				<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
					<td
						<%=((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1 ? "class=\"notActive\""
							: ""%>
						align="left"><bean:write name="iterSystemProperty" property="name" />
					</td>
					<td><%=DateUtils.formatDate(((Promotion) iterSystemProperty).getStartdate()) %></td>
					<td><%=DateUtils.formatDate(((Promotion) iterSystemProperty).getEnddate()) %></td>
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
</body>
</html>