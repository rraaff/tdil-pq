<%@page import="com.tdil.ljpeugeot.services.PeugeotService"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="org.apache.poi.util.StringUtil"%>
<%@page import="com.tdil.ljpeugeot.model.NotificationEmail"%>
<%@page import="javax.management.Notification"%>
<%@page import="com.tdil.ljpeugeot.model.SystemProperty"%>
<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Peugeot :: Backdoor Application - E-Mails</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_backdoor.css" />
<script type='text/javascript' src='../js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_jquery-1.8.2.min.js'></script>
<script>
function doTestEmail() {
	var to = $('#testEmail').val();
	var from = $('#from').val();
	var subject = $('#subject').val();
	var content = $('#emailBody').val();
	$.ajax({
        type: "POST",
        cache: false,
        async: false,
        url: "../doTestEmail.do",
        data: {to: to, from: from, subject: subject, content: content },
        success: function(data) {
     	  if (data.result == 'OK') {
				alert("Se ha ejecutado el test");
			} else {
				alert("El test no ha sido ejecutado, revise los logs");
			}
          },
          error: function() {
        	  alert("El test no ha sido ejecutado, revise los logs");
          }
      });
}
</script>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Peugeot BackDoor Application</h1>
	<h2>Notificaciones por E-Mail</h2>
</section>
<section id="content">
	<article>
		<h3>Cambiar notificación</h3>
		
		<form action="./doUpdateEmail.jsp">
		<% String id =  request.getParameter("id");
			NotificationEmail notificationEmail = null;
			if (!StringUtils.isEmpty(id)) {
				notificationEmail = PeugeotService.getNotificationEmail(Integer.parseInt(id));
			}
			if (notificationEmail != null && !("1".equals(request.getParameter("test")))) { %>
			<input type="hidden" name="id" value="<%=id%>">
			<fieldset>
				<label>Tipo de notificación</label>
				<input type="text" readonly="true" name="notificationtype" value="<%=notificationEmail.getNotificationtype()%>">
			</fieldset>
			<fieldset>
				<label>Subject</label>
				<input type="text" name="subject" id="subject" value="<%=notificationEmail.getSubject()%>">
			</fieldset>
			<fieldset>
				<label>From</label>
				<input type="text" name="from" id="from" value="<%=notificationEmail.getFrom()%>">
			</fieldset>
			<fieldset>
				<label>Replacements</label>
				<label style="word-break: break-all;"><strong><%=notificationEmail.getReplacements()%></strong></label>
			</fieldset>
			<fieldset class="width100per">
				<label>Contenido del E-Mail</label>
				<textarea name="content" id="emailBody" class="width100per height200"><%=notificationEmail.getContent()%></textarea>
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Guardar">
			</fieldset>
			
			<hr/>
			
			<fieldset>
				<label>Enviar prueba a</label>
				<input type="text" name="testEmail" id="testEmail">
			</fieldset>
			<fieldset class="botonera">
				<button type="button" onclick="javascript:doTestEmail()">Testear</button>
			</fieldset>
		</form>
		<% } %>
		
		<hr/>
		
		<div class="portaTable">
			<ul class="thead">
				<li class="Thirtyper">Tipo</li>
				<li class="Thirtyper">Asunto</li>
				<li class="Thirtyper">Desde</li>
				<li class="Tenper">Editar</li>
			</ul>
			<% for (NotificationEmail ne : PeugeotService.getNotificationEmails()) { %>
				<ul class="tbody">
					<li class="Thirtyper"><%=ne.getNotificationtype()%></li>
					<li class="Thirtyper"><%=ne.getSubject()%></li>
					<li class="Thirtyper"><%=ne.getFrom()%></li>
					<li class="Tenper"><a href="./notificationEmails.jsp?id=<%=ne.getId()%>">Editar</a></li>
				</ul>
			<% } %>
		</div>
	</article>
</section>
</body>
</html>