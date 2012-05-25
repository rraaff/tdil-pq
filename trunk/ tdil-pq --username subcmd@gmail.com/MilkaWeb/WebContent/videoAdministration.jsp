<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
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
var maxindex = <bean:write name="VideoForm" property="maxImages" />;
$(document).ready(
	function(){
		$('#upload_img').ajaxfileupload({
		  	'action': './uploadVideoCover.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		var tr = $('<tr></tr>').appendTo( $('#image_gal_tab') );
		  		var tdpos = $('<td align="center">' + (maxindex + 1) + '</td>').appendTo( tr );
		  		var tdimg = $('<td align="center"><img id="ranking_' + maxindex + '" src="./viewVideoCover.do?pos=' + maxindex + '" width="66" height="40" align="absmiddle"></td>').appendTo( tr );
		  		var tdops = $('<td align="center" width="100"><a href="javascript:document.VideoForm.action=\'./moveVideoUp.do?index=\'' + maxindex + '\';document.VideoForm.submit();"><img src="boImages/subir.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>' + '<a href="javascript:document.VideoForm.action=\'./moveVideoDown.do?index=' + maxindex + '\';document.VideoForm.submit();"><img src="boImages/bajar.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>' + '<a href="javascript:document.VideoForm.action=\'./deleteVideo.do?index=' + maxindex + '\';document.VideoForm.submit();"><img src="boImages/borrar.png" alt="Borrar" width="20" height="20" hspace="5" border="0"></a></td>').appendTo( tr );
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
	<html:form method="POST" action="/saveVideo">
	<div id="portaMenu"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<h1>Contenido tipo Galer&iacute;a de im&aacute;genes</h1>
		<span class="errorText"><%=MilkaErrorFormatter.getErrorFrom(request, "general")%></span>
		<div id="conteinerScrollable" style="float:left; width:950px; height:380px; overflow:auto; border:#FF0000;">
			<h2>Fotos</h2>
			<div class="renglon width920">
				<div class="label width920 comment">Podr&aacute; crear todas las galer&iacute;as que quiera. Los usuarios accederan a la secci&oacute;n de galer&iacute;as a trav&eacute;s de men&uacute; de galer&iacute;as. Cargue todas las ftos que desee para la galer&iacute;a y una vez cargadas, pordr&aacute; subir y bajar cada una de las mismas con los links en las acciones para modificar el &oacute;rden de aparici&oacute;n. Medida ideal de las imágenes 660 pixels x 400 pixels a 72dpi de resoluci&oacute;n</div>
			</div>
			<div class="width420 height250" style="float:left; overflow:auto;">
				<table width="380" id="image_gal_tab">
					<tr>
						<td class="headerTablas" width="50">Posici&oacute;n</td>
						<td class="headerTablas">Foto</td>
						<td class="headerTablas">Acciones</td>
					</tr>
					<logic:iterate id="selectedPosition" name="VideoForm" property="positions" indexId="iterIndexPositions">  
						<tr>
							<td align="center"><%=iterIndexPositions + 1%></td>
							<td align="center"><img id="ranking_<%=iterIndexPositions%>" src="./viewVideoCover.do?pos=<%=iterIndexPositions%>" width="66" height="40" align="absmiddle"></td> 
							<td align="center" width="100">
								<a href="javascript:document.VideoForm.action='./moveVideoUp.do?index=<%=iterIndexPositions%>';document.VideoForm.submit();"><img src="boImages/subir.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>
								<a href="javascript:document.VideoForm.action='./moveVideoDown.do?index=<%=iterIndexPositions%>';document.VideoForm.submit();"><img src="boImages/bajar.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>
								<a href="javascript:document.VideoForm.action='./deleteVideo.do?index=<%=iterIndexPositions%>';document.VideoForm.submit();"><img src="boImages/borrar.png" alt="Borrar" width="20" height="20" hspace="5" border="0"></a></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<div class="width500 height250" style="float:right;">
				<div class="label width500 height25"></div>
				<div class="label width200 height60"><input type="file" name="upload_img" id="upload_img"></div>
				<div class="label width50 height60"><%=MilkaErrorFormatter.getErrorFrom(request, "ImageGallery.photo.err")%></div>
				<div class="label width500 height50">
					<logic:equal name="VideoForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="VideoForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
				</div>
			</div>
		</div>
	</div>
	</html:form>
</div>
</body>
</html>