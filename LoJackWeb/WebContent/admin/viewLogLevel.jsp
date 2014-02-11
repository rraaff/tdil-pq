<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%>


<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Real Life :: Backdoor Application - VLU Config</title>
<link rel="icon" href="../favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="../css/reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/font_embeder.css" />
<link type="text/css" rel="stylesheet" media="screen" href="../css/backdoor.css" />
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/menu.jspf" %>
<section id="titles">
	<h1>LoJack Real Life BackDoor Application</h1>
	<h2>VLU</h2>
</section>
<% 
	String category = request.getParameter("category");
	String level[] = com.tdil.lojack.utils.LoggerUtils.getLevelFor(category);
%>
<section id="content">
	<article>
		<p><%=level[0] %> <%=level[1] %></p>
	</article>
</section>
</body>
</html>