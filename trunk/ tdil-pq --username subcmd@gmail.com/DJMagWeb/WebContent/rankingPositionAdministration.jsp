<%@page import="com.tdil.djmag.struts.forms.CountrySelectionVO"%>
<%@page import="com.tdil.djmag.model.RankingPosition"%>
<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.struts.forms.RankingPositionForm"%>
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
<script type="text/javascript" src="./ckeditor.js"></script>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
var maxindex = <bean:write name="RankingPositionForm" property="maxImages" />;
$(document).ready(
	function(){
		$('#upload_img').ajaxfileupload({
		  	'action': './uploadRankingPositionPhoto.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		var tr = $('<tr></tr>').appendTo( $('#image_gal_tab') );
		  		var tdpos = $('<td align="center">' + (maxindex + 1) + '</td>').appendTo( tr );
		  		var tdimg = $('<td align="center"><img id="ranking_' + maxindex + '" src="./viewRankingPositionGalleryPhoto.do?pos=' + maxindex + '" width="66" height="40" align="absmiddle"></td>').appendTo( tr );
		  		var tdops = $('<td align="center"><a href="javascript:document.RankingPositionForm.action=\'./moveImageInRankingPositionUp.do?index=\'' + maxindex + '\';document.RankingPositionForm.submit();"><img src="boImages/subir.png" alt="Subir"></a>' + '<a href="javascript:document.RankingPositionForm.action=\'./moveImageInRankingPositionDown.do?index=' + maxindex + '\';document.RankingPositionForm.submit();"><img src="boImages/bajar.png" alt="Subir"></a>' + '<a href="javascript:document.RankingPositionForm.action=\'./deleteImageInRankingPosition.do?index=' + maxindex + '\';document.RankingPositionForm.submit();"><img src="boImages/borrar.png" alt="Borrar"></a></td>').appendTo( tr );
		  		maxindex = maxindex + 1;
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		
		$('#upload_cover').ajaxfileupload({
		  	'action': './uploadRankingPositionCover.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#cover_img').attr('src', './viewRankingPositionCover.do');
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
div { /*border: dotted 1px #330099; */}
</style>
</head>
<body>
<div id="header"></div>
<div id="container">
	<html:form method="POST" action="/saveRankingPosition">
	<div id="portaMenu"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<div id="conteinerScrollable" style="width:950px; height:480px; overflow:auto; border:#FF0000;">
			<h1>Posici&oacute;n en el ranking <bean:write name="RankingPositionForm" property="rankingPos" /></h1>
			<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
			<div class="renglon width850 height80">
				<div class="label width50">Artista</div>
				<div class="label width400"><html:text name="RankingPositionForm" property="title" styleClass="width400"/></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "RankingPosition.title.err")%></div>
				<div class="label width100 height80">
					<logic:notEqual name="RankingPositionForm" property="imageId" value="0">
						<img id="cover_img" src="./viewRankingPositionCover.do" width="78" height="78" align="absmiddle" border="1">
					</logic:notEqual>
					<logic:equal name="RankingPositionForm" property="imageId" value="0">
						<img id="cover_img" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
					</logic:equal>
				</div>
				<div class="label width200"><input type="file" name="upload_cover" id="upload_cover"></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "RankingPosition.upload_cover.err")%></div>
			</div>
			<div class="renglon width860 height80">
				<div class="label width50">Bajada</div>
				<div class="label width760 height80"><html:textarea name="RankingPositionForm" property="summary" styleClass="width740 height80" /></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "RankingPosition.summary.err")%></div>
			</div>
			<div class="renglon width860 height180">
				<div class="label width50">Texto</div>
				<div class="label width760 height180"><html:textarea name="RankingPositionForm" property="content" styleClass="width740 height200" /></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "RankingPosition.content.err")%></div>
			</div>
			<h2>Fotos</h2>
			<div class="renglon width920">
				<div class="label width920 comment">Cargue todas las fotos que quiera en la nota del artista. No se mostrar&aacute; en el slider, la foto de avatar que se ve en la lista de artistas. El tama&ntilde;o ideal de las im&aacute;genes de esa galer&iacute;a es de 660 pixels x 400 pixels</div>
			</div>
			<div class="width450 height200" style="float:left; overflow:auto;">
				<table id="image_gal_tab">
					<tr>
						<td class="headerTablas" width="50">Posici&oacute;n</td>
						<td class="headerTablas">Foto</td>
						<td class="headerTablas">Acciones</td>
					</tr>
					<logic:iterate id="selectedPosition" name="RankingPositionForm" property="positions" indexId="iterIndexPositions">  
						<tr>
							<td align="center"><%=iterIndexPositions + 1%></td>
							<td align="center"><img id="ranking_<%=iterIndexPositions%>" src="./viewRankingPositionGalleryPhoto.do?pos=<%=iterIndexPositions%>" width="66" height="40" align="absmiddle"></td> 
							<td align="center" width="100"><a href="javascript:document.RankingPositionForm.action='./moveImageInRankingPositionUp.do?index=<%=iterIndexPositions%>';document.RankingPositionForm.submit();"><img src="boImages/subir.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a><a href="javascript:document.RankingPositionForm.action='./moveImageInRankingPositionDown.do?index=<%=iterIndexPositions%>';document.RankingPositionForm.submit();"><img src="boImages/bajar.png" alt="Subir" width="20" height="20" hspace="5" border="0"></a><a href="javascript:document.RankingPositionForm.action='./deleteImageInRankingPosition.do?index=<%=iterIndexPositions%>';document.RankingPositionForm.submit();"><img src="boImages/borrar.png" alt="Borrar" width="20" height="20" hspace="5" border="0"></a></td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<div class="width450 height200" style="float:left;">
				<div class="label width400"><input type="file" name="upload_img" id="upload_img"></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "RankingPosition.photo.err")%></div>
				<logic:equal name="RankingPositionForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="RankingPositionForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="modify" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="cancel" />
				</html:submit>
			</div>
		</div>
	</div>
	</html:form>
</div>
<script type="text/javascript">
//<![CDATA[
	// Replace the <textarea id="content"> with an CKEditor instance.
	var editor = CKEDITOR.replace( 'content',
		{
			// Defines a simpler toolbar to be used in this sample.
			// Note that we have added out "MyButton" button here.
			toolbar : [ ['Format'] ],
			height:"100", width:"740"
			
		});
//]]>
</script>
</body>
</html>