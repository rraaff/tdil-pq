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
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$('#upload_avatar').ajaxfileupload({
		  	'action': './uploadAvatar.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#avatar_img').attr('src', './viewAvatar.do');	  		
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
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="logout.do">Salir</a>
<hr> 
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr> 
Cambiar mi avatar<br><br>

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
			<div class="myRow" align="center"><a href="./home.jsp">Cancelar</a>&nbsp;<input type="submit" value="Grabar" /></div>
		</div>

	</html:form>

</body>
</html>