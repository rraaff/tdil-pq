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
		  		var tdimg = $('<td align="center" width="250"><img id="ranking_' + maxindex + '" src="./viewImageGalleryPhoto.do?pos=' + maxindex + '" width="100" height="100" align="absmiddle"></td>').appendTo( tr );
		  		var tdops = $('<td align="center"><a href="javascript:document.ImageGalleryForm.action=\'./moveImageInGalleryUp.do?index=\'' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/subir.png" alt="Subir"></a>' +
								'<a href="javascript:document.ImageGalleryForm.action=\'./moveImageInGalleryDown.do?index=' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/bajar.png" alt="Subir"></a>' +
								'<a href="javascript:document.ImageGalleryForm.action=\'./deleteImageFromGallery.do?index=' + maxindex + '\';document.ImageGalleryForm.submit();"><img src="boImages/borrar.png" alt="Borrar"></a></td>').appendTo( tr );
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
<div id="header"></div>
<div id="container">
	<html:form method="POST" action="/saveImageGallery">
	<div id="portaMenu"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<h1>Contenido tipo ImageGallery</h1>
		<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
		<div class="renglon width350">
			<div class="label width50"> </div>
			<div class="label width100">T&iacute;tulo</div>
			<div class="label width150"><html:text name="ImageGalleryForm" property="title" styleClass="width120"/></div>
			<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.title.err")%></div>
			<div class="label width100">Descripci&oacute;n</div>
			<div class="label width150"><html:text name="ImageGalleryForm" property="description" styleClass="width120"/></div>
			<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.description.err")%></div>
		</div>
		<div id="conteinerScrollable" style="width:950px; height:400px; overflow:auto; border:#FF0000;">
			<h2>Fotos</h2>
			<div class="renglon width920">
				<div class="label width920 comment">Complete con las fotos. Una vez cargadas, pordr&aacute; subir y bajar cada una de las mismas con los links en las acciones.</div>
			</div>
			<div class="renglon width920 height200" style="overflow:auto;">
				<table id="image_gal_tab">
					<tr>
						<td class="headerTablas" width="50">Posici&oacute;n</td>
						<td class="headerTablas">Foto</td>
						<td class="headerTablas" width="50">Acciones</td>
					</tr>
					<logic:iterate id="selectedPosition" name="ImageGalleryForm" property="positions" indexId="iterIndexPositions">  
						<tr>
							<td align="center"><%=iterIndexPositions + 1%></td>
							<td align="center" width="250">
								<img id="ranking_<%=iterIndexPositions%>" src="./viewImageGalleryPhoto.do?pos=<%=iterIndexPositions%>" width="100" height="100" align="absmiddle"></td> 
								<td align="center"><a href="javascript:document.ImageGalleryForm.action='./moveImageInGalleryUp.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/subir.png" alt="Subir"></a>
								<a href="javascript:document.ImageGalleryForm.action='./moveImageInGalleryDown.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/bajar.png" alt="Subir"></a>
								<a href="javascript:document.ImageGalleryForm.action='./deleteImageFromGallery.do?index=<%=iterIndexPositions%>';document.ImageGalleryForm.submit();"><img src="boImages/borrar.png" alt="Borrar"></a>
								</td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<input type="file" name="upload_img" id="upload_img">
			<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.photo.err")%></div>
			<div class="renglon width860">
				<div class="label width80">Pa&iacute;s</div>
				<div class="label width200">
					<html:select name="ImageGalleryForm" property="countryId" styleClass="width180">
						<logic:iterate name="ImageGalleryForm" property="selectedCountries"
							id="iterCountry">
							<option
								<%=(((CountrySelectionVO) iterCountry).isSelected()) ? "selected" : ""%>
								value="<%=((CountrySelectionVO) iterCountry).getCountryId()%>">
								&nbsp;&nbsp;&nbsp;<%=((CountrySelectionVO) iterCountry).getCountryName()%></option>
						</logic:iterate>
					</html:select>
				</div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "ImageGallery.country.err")%></div>
			</div>
			<div class="renglon width860 height50">
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
			<h2>Listado de ImageGallerys</h2>
			<div class="renglon width860 height200" style="overflow:auto;">
				<table>
					<tr>
						<td class="headerTablas" width="300">T&iacute;tulo</td>
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