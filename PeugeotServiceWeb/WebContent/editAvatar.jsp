<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.web.LJPeugeotErrorFormatter"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.EditProfileForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><script>
		$('#upload_avatar').ajaxfileupload({
		  	'action': './uploadAvatar.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#avatar_img').attr('src', './viewAvatar.do?img=' + Math.random());	  		
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});

		
		function doSaveAvatar() {
			  <%@ include file="includes/blockUI.jspf" %>
			  $.ajax({
		          type: "GET",
		          cache: false,
		          url: "./saveProfile.do",
		          contentType: "application/json; charset=utf-8",
		          success: function(data) {
		        	  postChangeAvatar(data);
		          },
		          error: function() {
		        	  <%@ include file="includes/unblockUI.jspf" %>
		          }
		      });
		  }

$( "#closeChangeAvatarLayer" ).click(function() {
	$( "#changeAvatarLayer" ).fadeOut();
});

function clearErrors() {
$("div[id^='err.']").each(function(index, valor) {
$(valor).prop('innerHTML','');
});
}


function postChangeAvatar(data) {
<%@ include file="includes/unblockUI.jspf" %>
if (data.result == 'OK') {
$( "#changeAvatarLayer" ).fadeOut();
$( "#avatarImg" ).attr('src', './download.st?id='+data.idAvatar+'&type=PUBLIC&ext='+data.extAvatar);
} else {
$.each(data, function(key, value) {
	var obj = document.getElementById('err.' + key);
	if (obj) {
		obj.innerHTML = value;
	}
});
}
}
</script>
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
	<div id="centradorModalesAvatar" class="layerModal width600">
		<section class="modal_header">
			<h2>Editar avatar</h2>
			<button class="close" id="closeChangeAvatarLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Cambiar la imagen</span>
			<p class="pBottom25">Busque una imagen que lo represente dentro de su disco rígido</p>
			
			<html:form method="POST" action="/saveProfile" styleClass="modal_wrapper">
				<fieldset class="aligner">
					<logic:notEqual name="EditProfileForm" property="imageId" value="0">
						<label><img id="avatar_img" src="./viewAvatar.do" width="100" height="100" align="absmiddle"></label>
					</logic:notEqual>
					<logic:equal name="EditProfileForm" property="imageId" value="0">
						<label><img id="avatar_img" class="avatar_base" src="images/skn_peugeot/icons/avatarBase.png" width="100" height="100" align="absmiddle"></label>
					</logic:equal>
				</fieldset>
				<fieldset class="aligner">
					<label><input type="file" name="upload_avatar" id="upload_avatar"></label>
				</fieldset>
				<fieldset>
					<label><%=LJPeugeotErrorFormatter.getErrorFrom(request,EditProfileForm.avatar_key + ".err")%></label>
				</fieldset>
				<fieldset class="button_bar pOnlyTop25">
					<button class="botton_ahead" onclick="javascript:doSaveAvatar();">Guardar<span></span></button>
				</fieldset>
			</html:form>
		</section>
	</div>
</div>
<%@ include file="includes/catchModal.jspf" %>