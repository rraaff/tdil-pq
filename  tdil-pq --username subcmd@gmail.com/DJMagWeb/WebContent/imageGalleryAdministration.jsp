<%@page import="com.tdil.djmag.model.GalleryCategory"%>
<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.model.ImageGallery"%>
<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.struts.forms.ImageGalleryForm"%>
<%@page import="com.tdil.djmag.model.Country"%>
<%@ page info="index"%>
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
var maxindex = <bean:write name="ImageGalleryForm" property="maxImages" />;
$(document).ready(
	function(){
		$('#upload_img').ajaxfileupload({
		  	'action': './uploadImageGalleryPhoto.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		var tr = $('<tr></tr>').appendTo( $('#image_gal_tab') );
		  		var tdpos = $('<td align="center">' + (maxindex + 1) + '</td>').appendTo( tr );
		  		if (maxindex == 0) {
			  		var tdportada = $('<td align="center">SI</td>').appendTo( tr );
		  		} else {
		  			var tdportada = $('<td align="center"></td>').appendTo( tr );
		  		}
		  		var tdimg = $('<td align="center"><img id="ranking_' + maxindex + '" src="./viewImageGalleryPhoto.do?pos=' + maxindex + '" width="66" height="40" align="absmiddle"></td>').appendTo( tr );
		  		var tdops = $('<td align="center" width="100"><a href="javascript:document.ImageGalleryForm.action=\'./moveImageInGalleryUp.do?index=\'' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/subir.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>' + '<a href="javascript:document.ImageGalleryForm.action=\'./moveImageInGalleryDown.do?index=' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/bajar.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>' + '<a href="javascript:document.ImageGalleryForm.action=\'./deleteImageFromGallery.do?index=' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/borrar.png" alt="Borrar" width="20" height="20" hspace="5" border="0"></a>' +'<a href="javascript:document.ImageGalleryForm.action=\'./selectImageGalleryCover.do?index=' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/portada.png" alt="Portada" width="20" height="20" hspace="5" border="0"></a></td>').appendTo( tr );
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
<style>
div { /*border:dotted 1px #00CCFF; */}
</style>
</head>
<body>
<div id="header"></div>
<div id="container">
	<html:form method="POST" action="/saveImageGallery">
	<div id="portaMenu"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<%	ImageGalleryForm imageGalleryForm = (ImageGalleryForm)session.getAttribute("ImageGalleryForm"); %>
		<h1>Contenido tipo Galer&iacute;a de im&aacute;genes</h1>
		<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
		<div class="renglon width860 height50">
			<div class="label width80">T&iacute;tulo</div>
			<div class="label width150"><html:text name="ImageGalleryForm" property="title" styleClass="width150"/></div>
			<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.title.err")%></div>
			<div class="label width80">Descripci&oacute;n</div>
			<div class="label width450 height50"><html:textarea name="ImageGalleryForm" property="description" styleClass="width450 height50"/></div>
			<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.description.err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width80">Categor&iacute;a</div>
			<div class="label width420">
				<html:select name="ImageGalleryForm" property="galleryCategoryId" styleClass="width420">
					<logic:iterate name="ImageGalleryForm" property="allCategories" id="iterCategory">
						<option	<%=	((GalleryCategory) iterCategory).getId().equals( imageGalleryForm.getGalleryCategoryId()) ? "selected" : ""%> value="<%=((GalleryCategory) iterCategory).getId()%>">&nbsp;&nbsp;&nbsp;<%=((GalleryCategory) iterCategory).getTitle()%></option>
					</logic:iterate>
				</html:select>
			</div>
		</div>
		<div id="conteinerScrollable" style="float:left; width:950px; height:335px; overflow:auto; border:#FF0000;">
			<h2>Fotos</h2>
			<div class="renglon width920">
				<div class="label width920 comment">Podr&aacute; crear todas las galer&iacute;as que quiera. Los usuarios accederan a la secci&oacute;n de galer&iacute;as a trav&eacute;s de men&uacute; de galer&iacute;as. Cargue todas las ftos que desee para la galer&iacute;a y una vez cargadas, pordr&aacute; subir y bajar cada una de las mismas con los links en las acciones para modificar el &oacute;rden de aparici&oacute;n. Medida ideal de las imágenes 660 pixels x 400 pixels a 72dpi de resoluci&oacute;n</div>
			</div>
			<div class="width420 height250" style="float:left; overflow:auto;">
				<table width="380" id="image_gal_tab">
					<tr>
						<td class="headerTablas" width="50">Posici&oacute;n</td>
						<td class="headerTablas" width="50">Portada</td>
						<td class="headerTablas">Foto</td>
						<td class="headerTablas">Acciones</td>
					</tr>
					<logic:iterate id="selectedPosition" name="ImageGalleryForm" property="positions" indexId="iterIndexPositions">  
						<tr>
							<td align="center"><%=iterIndexPositions + 1%></td>
							<td align="center"><%=(iterIndexPositions == imageGalleryForm.getCoverPosition()) ? "SI" : ""%></td>
							<td align="center"><img id="ranking_<%=iterIndexPositions%>" src="./viewImageGalleryPhoto.do?pos=<%=iterIndexPositions%>" width="66" height="40" align="absmiddle"></td> 
							<td align="center" width="130">
								<a href="javascript:document.ImageGalleryForm.action='./moveImageInGalleryUp.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/subir.png" title="Subir" width="20" height="20" hspace="5" border="0"></a>
								<a href="javascript:document.ImageGalleryForm.action='./moveImageInGalleryDown.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/bajar.png" title="Subir" width="20" height="20" hspace="5" border="0"></a>
								<a href="javascript:document.ImageGalleryForm.action='./deleteImageFromGallery.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/borrar.png" title="Borrar" width="20" height="20" hspace="5" border="0"></a>
								<a href="javascript:document.ImageGalleryForm.action='./selectImageGalleryCover.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/portada.png" title="Portada" width="20" height="20" hspace="5" border="0"></a></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<div class="width500 height250" style="float:right;">
				<div class="label width500 height25"></div>
				<div class="label width200 height60"><input type="file" name="upload_img" id="upload_img"></div>
				<div class="label width50 height60"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.photo.err")%></div>
				<div class="label width50 height60">Pa&iacute;s</div>
				<div class="label width100 height60">
					<html:select name="ImageGalleryForm" property="countryId" styleClass="width100">
						<logic:iterate name="ImageGalleryForm" property="selectedCountries"
							id="iterCountry">
							<option
								<%=(((CountrySelectionVO) iterCountry).isSelected()) ? "selected" : ""%>
								value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
								&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
						</logic:iterate>
					</html:select>
				</div>
				<div class="label width50 height60"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.country.err")%></div>
				<div class="label width500 height50">
					<logic:equal name="ImageGalleryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="ImageGalleryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
			</div>
			<h2>Listado de Galer&iacute;as</h2>
			<div class="renglon width860 height200" style="overflow:auto;">
				<table>
					<tr>
						<td class="headerTablas" width="300">T&iacute;tulo</td>
						<td class="headerTablas" width="300">Categor&iacute;a</td>
						<td class="headerTablas" width="500">Pa&iacute;ses</td>
						<td class="headerTablas" width="60">Acciones</td>
					</tr>
					<logic:iterate name="ImageGalleryForm" property="allImageGalleries" id="iterImageGallery" indexId="iterIndex">
						<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
							<td
								<%=((com.tdil.ibatis.PersistentObject) iterImageGallery).getDeleted() == 1 ? "class=\"notActive\""
									: ""%>
								align="left"><bean:write name="iterImageGallery" property="title" />
							</td>
							<td	<%=((com.tdil.ibatis.PersistentObject) iterImageGallery).getDeleted() == 1 ? "class=\"notActive\""
										: ""%> align="left">
								<% GalleryCategory category = imageGalleryForm.getCategoryById(((ImageGallery) iterImageGallery).getCategoryId()); %>
								<%= category.getTitle() %>&nbsp;
							</td>
							<td	<%=((com.tdil.ibatis.PersistentObject) iterImageGallery).getDeleted() == 1 ? "class=\"notActive\""
										: ""%> align="left">
								<% Country country = ImageGalleryForm.getCountryWithId(((ImageGallery) iterImageGallery).getIdCountry()); %>
								<%= country.getName() %>&nbsp;
							</td>
							<td align="center">
								<html:link action="editImageGallery.st?" paramName="iterImageGallery" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
								<html:link action="/toggleDeletedImageGallery" paramName="iterImageGallery"
									paramProperty="id" paramId="id">
									<% if (((com.tdil.ibatis.PersistentObject) iterImageGallery).getDeleted() == 1) { %>
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
	</html:form>
</div>
</body>
</html>