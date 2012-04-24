<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.model.Section"%>
<jsp:useBean id="publicHomeBean" scope="session" class="com.tdil.djmag.web.beans.PublicHomeBean"/>
<html>
<body>
<% if (publicHomeBean.hasCountrySelected()) { %>
	Pais: <%= publicHomeBean.getCountry().getName() %>
	<br>
	Secciones<br>
	<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
		<%= section.getName() %><br>
	<% 	} %>
<% } else {
	publicHomeBean.initCountries();	
	for (Country country : publicHomeBean.getAllCountries()) {%>
	<a href="./selectCountry.st?id=<%= country.getId() %>"><%= country.getName() %></a><br>
	<% } %>
<% } %>
</body>
</html>