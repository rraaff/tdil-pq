<%@page import="com.tdil.lojack.web.LoJackErrorFormatter"%>
<%@page import="com.tdil.lojack.struts.forms.EditProfileForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script>
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
	<div class="loginLayerStyles">
		<div class="loginLayerContent">
			<div id="xContainer"><button id="closeChangeAvatarLayer" style="margin-left:-40px;">X</button></div>
			<h3 style="padding-bottom:20px;">Cambiar avatar</h3>
			<html:form method="POST" action="/saveProfile">
				<div class="myRow">
					<div class="myLabel width80 height80">
						<logic:notEqual name="EditProfileForm"
							property="imageId" value="0">
							<img id="avatar_img" src="./viewAvatar.do" width="78"
								height="78" align="absmiddle" border="1">
						</logic:notEqual>
					</div>
					<div class="myLabel width80 height80">
						<logic:equal name="EditProfileForm"
							property="imageId" value="0">
							<img id="avatar_img" src="boImages/na.gif" width="78" height="78"
								align="absmiddle" border="1">
						</logic:equal>
					</div>
					<div class="myLabel width250 height100">
						<input type="file" name="upload_avatar" id="upload_avatar"><br /><%=LoJackErrorFormatter.getErrorFrom(request,
								EditProfileForm.avatar_key + ".err")%></div>
					<div class="myRow" align="center"><a href="./home.jsp">Cancelar</a>&nbsp;<a href="javascript:doSaveAvatar()">Guardar</a></div>
				</div>
			</html:form>
		</div>
	</div>
</div>