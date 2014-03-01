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
<link type="text/css" rel="stylesheet" media="screen" href="../css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/backdoor.css" />
<script type='text/javascript' src='../js/jquery-1.8.2.min.js'></script>
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
		
		<form action="./doUpdateEmail.jsp" class="width100per">
		<% String id =  request.getParameter("id");
			NotificationEmail notificationEmail = null;
			if (!StringUtils.isEmpty(id)) {
				notificationEmail = PeugeotService.getNotificationEmail(Integer.parseInt(id));
			}
			if (notificationEmail != null && !("1".equals(request.getParameter("test")))) { %>
			<input type="hidden" name="id" value="<%=id%>">
			<fieldset class="width50per fleft pRight25">
				<label class="width100per">Tipo de notificación</label>
				<input type="text" readonly="true" class="width100per" name="notificationtype" value="<%=notificationEmail.getNotificationtype()%>">
			</fieldset>
			<fieldset class="width50per fleft pLeft25">
				<label class="width100per">Subject</label>
				<input type="text" name="subject" id="subject" class="width100per" value="<%=notificationEmail.getSubject()%>">
			</fieldset>
			<fieldset class="width50per fleft pRight25">
				<label class="width100per">From</label>
				<input type="text" name="from" id="from" class="width100per" value="<%=notificationEmail.getFrom()%>">
			</fieldset>
			<fieldset class="width50per fleft pLeft25">
				<label class="width100per">Replacements</label>
				<label class="width100per" style="padding:8px 0;"><i><strong><%=notificationEmail.getReplacements()%></strong></i></label>
			</fieldset>
			<fieldset class="fleft">
				<label class="width100per">Contenido del E-Mail</label>
				<textarea name="content" id="emailBody" class="width100per height200"><%=notificationEmail.getContent()%></textarea>
			</fieldset>
			<fieldset class="botonera">
				<input type="submit" value="Guardar">
				
				<input type="text" name="testEmail" id="testEmail">
				<input type="button" value="Testear" onclick="javascript:doTestEmail()">
			</fieldset>
		</form>
		<% } %>

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