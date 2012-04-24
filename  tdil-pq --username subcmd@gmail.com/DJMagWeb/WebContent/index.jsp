<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.model.Section"%>
<jsp:useBean id="publicHomeBean" scope="session" class="com.tdil.djmag.web.beans.PublicHomeBean"/>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<html>
<body>
<% if (publicHomeBean.hasCountrySelected()) { %>
	Pais: <%= publicHomeBean.getCountry().getName() %>
	<br>
	Secciones<br>
	<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
		<%= section.getName() %><br>
	<% 	} %>
<% } else { %>
	<div id="BlockCoutrySelection">
		<div id="labelCountrySelection">Seleccione su pa&iacute;s / Selecione o Pa&iacute;s<br>Choisissez un pays / Select Country</div>
		<div id="countryCombo"><select><option>Seleccione</option>
			<% publicHomeBean.initCountries();	
				for (Country country : publicHomeBean.getAllCountries()) {%>
					<option value="./selectCountry.st?id=<%= country.getId() %>"><%= country.getName() %></option>
			<% } %>
			</select>&nbsp;&nbsp;&nbsp;
			<input type="submit" value="ok" class="okCircle">
		</div>
	</div>
<% } %>
</body>
</html>