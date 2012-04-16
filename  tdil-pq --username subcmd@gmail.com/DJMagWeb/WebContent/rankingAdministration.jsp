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
		Posicion1: <html:text name="RankingNoteForm" property="position1" /><html:errors property="RankingNote.position1.err" /><br>
		Posicion2: <html:text name="RankingNoteForm" property="position2" /><html:errors property="RankingNote.position2.err" /><br>
		Posicion3: <html:text name="RankingNoteForm" property="position3" /><html:errors property="RankingNote.position3.err" /><br>
		Posicion4: <html:text name="RankingNoteForm" property="position4" /><html:errors property="RankingNote.position4.err" /><br>
		Posicion5: <html:text name="RankingNoteForm" property="position5" /><html:errors property="RankingNote.position5.err" /><br>
		Posicion6: <html:text name="RankingNoteForm" property="position6" /><html:errors property="RankingNote.position6.err" /><br>
		Posicion7: <html:text name="RankingNoteForm" property="position7" /><html:errors property="RankingNote.position7.err" /><br>
		Posicion8: <html:text name="RankingNoteForm" property="position8" /><html:errors property="RankingNote.position8.err" /><br>
		Posicion9: <html:text name="RankingNoteForm" property="position9" /><html:errors property="RankingNote.position9.err" /><br>
		Posicion10: <html:text name="RankingNoteForm" property="position10" /><html:errors property="RankingNote.position10.err" /><br>
		Mostrar en
		<table>
		<tr>
			<td>Activa</td>
			<td>Pais</td>
		</tr>
		
		<html:select name="RankingNoteForm" property="sectionId" styleClass="textfield_effect">
			<logic:iterate name="RankingNoteForm" property="allSections" id="iterSection"> 
				<option <%=((SectionSelectionVO)iterSection).isSelected() ? "selected" : "" %> value="<%=((SectionSelectionVO)iterSection).getSection().getId()%>">&nbsp;&nbsp;&nbsp;<%=((SectionSelectionVO)iterSection).getSection().getName()%></option>
			</logic:iterate>
		</html:select><html:errors property="refDoc.category" />
		
		<logic:iterate id="selectedCountry" name="RankingNoteForm" property="selectedCountries">  
			<tr>
   				<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>  
   				<td><bean:write name="selectedCountry" property="countryName" /></td>
   			</tr>
		</logic:iterate>  
		</table>
		<br>
		<logic:equal name="RankingNoteForm" property="id" value="0">
			<html:submit property="operation">
				<bean:message key="save" />
			</html:submit>
		</logic:equal>
		<logic:notEqual name="RankingNoteForm" property="id" value="0">
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
			<td>Seccion</td>
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
						

			</td>
				<td><html:link action="editRanking.st?" paramName="iterRanking"
						paramProperty="id" paramId="id">
			Editar
		</html:link></td>
				<td width="10" bgcolor="#FFFFFF"><img src="images/null.gif"
					width="10" height="1"></td>
			</tr>
		</logic:iterate>
	</table>

</body>
</html>