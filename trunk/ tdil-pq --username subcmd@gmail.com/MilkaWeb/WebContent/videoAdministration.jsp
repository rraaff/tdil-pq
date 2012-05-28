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
		  		var tdtitle = $('<td><input type="text" name="selectedPosition['+(maxindex)+'].title" value=""></td>').appendTo( tr );
		  		var tdurl = $('<td><input type="text" name="selectedPosition['+(maxindex)+'].url" value=""></td>').appendTo( tr );
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
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<html:form method="POST" action="/saveVideo">
	<h1>Contenido tipo Galer&iacute;a de Playlist de Youtube</h1>
	<span class="errorText"><%=MilkaErrorFormatter.getErrorFrom(request, "general")%></span>
	<div style="float:left; width:920px; border:#FF0000;">
		<div class="renglon width860">
			<div class="label width860"><span class="comment">Podr&aacute; crear todos los links a playlist de YOUTUBE que quiera. Cargue todas las fotos representativa del playlist a 133px x 83px. Luego cargue la URL del playlist y un t&iacute;tulo.</span></div>
		</div>
		<div class="renglon width860 height60">
			<div class="label width200 height60"><input type="file" name="upload_img" id="upload_img"></div>
			<div class="label width50 height60"><%=MilkaErrorFormatter.getErrorFrom(request, "ImageGallery.photo.err")%></div>
			<div class="label width200 height50">
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
		<div class="width920">
			<table width="920" id="image_gal_tab">
				<tr>
					<td class="headerTablas" width="50">Posici&oacute;n</td>
					<td class="headerTablas">Titulo <%=MilkaErrorFormatter.getErrorFrom(request, "Video.title.err")%></td>
					<td class="headerTablas">URL <%=MilkaErrorFormatter.getErrorFrom(request, "Video.url.err")%></td>
					<td class="headerTablas">Foto</td>
					<td class="headerTablas">Acciones</td>
				</tr>
				<logic:iterate id="selectedPosition" name="VideoForm" property="positions" indexId="iterIndexPositions">  
					<tr>
						<td align="center"><%=iterIndexPositions + 1%></td>
						<td><html:text name="selectedPosition" property="title" indexed="true" styleClass="width150"/></td>
						<td><html:text name="selectedPosition" property="url" indexed="true" styleClass="width400"/></td>
						<td align="center"><img id="ranking_<%=iterIndexPositions%>" src="./viewVideoCover.do?pos=<%=iterIndexPositions%>" width="66" height="40" align="absmiddle"></td> 
						<td align="center" width="100">
							<a href="javascript:document.VideoForm.action='./moveVideoUp.do?index=<%=iterIndexPositions%>';document.VideoForm.submit();"><img src="boImages/subir.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>
							<a href="javascript:document.VideoForm.action='./moveVideoDown.do?index=<%=iterIndexPositions%>';document.VideoForm.submit();"><img src="boImages/bajar.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a>
							<a href="javascript:document.VideoForm.action='./deleteVideo.do?index=<%=iterIndexPositions%>';document.VideoForm.submit();"><img src="boImages/borrar.png" alt="Borrar" width="20" height="20" hspace="5" border="0"></a></td>
					</tr>
				</logic:iterate>
			</table>
		</div>
	</div>
	</html:form>
</div>
</body>
</html>