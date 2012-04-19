<%@page import="com.tdil.djmag.struts.forms.RankingNoteForm"%>
<%@page import="com.tdil.djmag.struts.forms.SectionSelectionVO"%>
<%@page import="com.tdil.djmag.model.Country"%>
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
</head>

<body>

	<%@ include file="includes/boMenu.jsp"%>

	<br>Ranking Note Administration


	<html:form method="POST" action="/saveRanking">
		<span class="errorText"><html:errors property="general" /> </span>
		<br>
		Titulo ranking: <html:text name="RankingNoteForm" property="description" /><html:errors property="RankingNote.description.err" />
		<br>
		Borrada: <html:checkbox name="RankingNoteForm" property="deleted" />
		<br>
		Posiciones
		<table>
		<tr>
			<td></td>
			<td>Nombre</td>
			<td></td>
			<td></td>
		</tr>
		<logic:iterate id="selectedPosition" name="RankingNoteForm" property="positions" indexId="iterIndexPositions">  
			<tr>
				<td><%=iterIndexPositions + 1%></td>
   				<td><html:text name="selectedPosition" property="position" indexed="true"/></td>  
   				<td><a href="javascript:document.RankingNoteForm.action='./moveRankingPositionUp.do?index=<%= iterIndexPositions%>';document.RankingNoteForm.submit();">Subir</a></td>
   				<td><a href="javascript:document.RankingNoteForm.action='./moveRankingPositionDown.do?index=<%= iterIndexPositions%>';document.RankingNoteForm.submit();">Bajar</a></td>
   			</tr>
		</logic:iterate>
		</table>
		Mostrar en <html:errors property="RankingNote.country.err" />
		<table>
		<tr>
			<td>Activa</td>
			<td>Pais</td>
		</tr>
		
		<logic:iterate id="selectedCountry" name="RankingNoteForm" property="selectedCountries">  
			<tr>
   				<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>  
   				<td><bean:write name="selectedCountry" property="countryName" /></td>
   			</tr>
		</logic:iterate>
		</table>
		
		<br>
		<logic:equal name="RankingNoteForm" property="objectId" value="0">
			<html:submit property="operation">
				<bean:message key="save" />
			</html:submit>
		</logic:equal>
		<logic:notEqual name="RankingNoteForm" property="objectId" value="0">
			<html:submit property="operation">
				<bean:message key="modify" />
			</html:submit>
		</logic:notEqual>
		<html:submit property="operation">
			<bean:message key="reset" />
		</html:submit>
	</html:form>
		<hr>
	Listado de Rankings
	<table>
		<tr>
			<td>Titulo</td>
			<td>Paises</td>
			<td>Editar</td>
		</tr>
		<logic:iterate name="RankingNoteForm" property="allRankings"
			id="iterRanking" indexId="iterIndex">
			<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
				<td
					<%=((com.tdil.ibatis.PersistentObject) iterRanking).getDeleted() == 1 ? "class=\"notActive\""
						: ""%>
					align="left"><bean:write name="iterRanking" property="description" />
				</td>
				<td	<%=((com.tdil.ibatis.PersistentObject) iterRanking).getDeleted() == 1 ? "class=\"notActive\""
							: ""%> align="left">
					<% for (Country country : RankingNoteForm.getAllCountriesForRankingId(((com.tdil.ibatis.PersistentObject) iterRanking).getId())) { %>
						<%= country.getName() %>&nbsp;
					<% } %>
				</td>
				<td><html:link action="editRanking.st?" paramName="iterRanking"
						paramProperty="id" paramId="id">
			Editar
		</html:link></td>
			</tr>
		</logic:iterate>
	</table>

</body>
</html>