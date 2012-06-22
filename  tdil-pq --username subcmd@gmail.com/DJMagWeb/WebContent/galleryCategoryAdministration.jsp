<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
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
$(document).ready(
	function(){
		$('#upload_cover').ajaxfileupload({
		  	'action': './uploadGalleryCategoryCover.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#cover_img').attr('src', './viewGalleryCategoryCover.do');	  		
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
	<div style="height:50px; display:block;"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<html:form method="POST" action="/saveGalleryCategory">
			<h1>Administraci&oacute;n de categorias</h1>
			<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
			<div class="renglon width800 height80">
				<div class="label width50">T&iacute;tulo</div>
				<div class="label width200"><html:text name="GalleryCategoryForm" property="title" styleClass="width180"/></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "GalleryCategory.title.err")%></div>
				<div class="label width50"></div>
				<div class="label width100 height80">
					<logic:notEqual name="GalleryCategoryForm" property="imageId" value="0">
						<img id="cover_img" src="./viewGalleryCategoryCover.do" width="78" height="78" align="absmiddle" border="1">
					</logic:notEqual>
				</div>
				<div class="label width100 height80">
					<logic:equal name="GalleryCategoryForm" property="imageId" value="0">
						<img id="cover_img" src="boImages/na.gif" width="78" height="78" align="absmiddle" border="1">
					</logic:equal>
				</div>
				<div class="label width200"><input type="file" name="upload_cover" id="upload_cover"></div>
				<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "GalleryCategory.frontCover.err")%></div>
			</div>
			<div class="renglon width600 height80" align="center">
				<logic:equal name="GalleryCategoryForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="save" />
					</html:submit>
				</logic:equal>
				<logic:notEqual name="GalleryCategoryForm" property="objectId" value="0">
					<html:submit property="operation">
						<bean:message key="modify" />
					</html:submit>
				</logic:notEqual>
				<html:submit property="operation">
					<bean:message key="reset" />
				</html:submit>
			</div>
			<h2>Listado de Categorias</h2>
			<div class="renglon width920 height200" style="overflow:auto;">
				<table>
					<tr>
						<td class="headerTablas">T&iacute;tulo</td>
						<td class="headerTablas" width="80">Acciones</td>
					</tr>
					<logic:iterate name="GalleryCategoryForm" property="allCategories"
						id="iterGalleryCategory" indexId="iterIndex">
						<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
							<td
								<%=((com.tdil.ibatis.PersistentObject) iterGalleryCategory).getDeleted() == 1 ? "class=\"notActive\""
									: ""%>
								align="left"><bean:write name="iterGalleryCategory" property="title" />
							</td>
							<td>
								<html:link action="editGalleryCategory.st?" paramName="iterGalleryCategory" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
								<html:link action="/toggleDeletedGalleryCategory" paramName="iterGalleryCategory"
									paramProperty="id" paramId="id">
									<% if (((com.tdil.ibatis.PersistentObject) iterGalleryCategory).getDeleted() == 1) { %>
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
		</html:form>
	</div>
</div>
</body>
</html>