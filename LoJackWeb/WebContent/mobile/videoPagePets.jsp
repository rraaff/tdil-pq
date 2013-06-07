<%@ page info="videoPage"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" type="text/css">
<link href="css/index_menu.css" rel="stylesheet" type="text/css">
<link href="css/videoPages.css" rel="stylesheet" type="text/css">
<!--
		$('#videoIframe').prop('src', 'http://www.youtube.com/embed/G18ElDg9s-o');
		$('#videoTitle').prop('innerHTML', 'Lo App');
		$('#videoDescription').prop('innerHTML', '');
-->
</head>
<body>
<div class="videoLayer">
	<div id="videoWrapper">
		<iframe id="videoIframe" src="http://www.youtube.com/embed/M8VhrMM0j-Q" frameborder="0" allowscale="false" allowfullscreen></iframe>
	</div>
	<div id="footerizer">
		<h3 id="videoTitle">Lo Jack Pets</h3>
		<p id="videoDescription">Sab� d�nde est�n tus mascotas.</p>
		<a href="home.jsp" title="Volver">< Volver</a>
	</div>
</div>
</body>
</head>