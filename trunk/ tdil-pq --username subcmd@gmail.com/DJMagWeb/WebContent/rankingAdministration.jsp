<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
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
<div id="header"></div>
<div id="container">
	<html:form method="POST" action="/saveRanking">
	<div id="portaMenu"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<h1>Contenido tipo Ranking &quot;TOP 100&quot;</h1>
		<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span>
		<div class="renglon width350">
			<div class="label width50"> </div>
			<div class="label width100">T&iacute;tulo ranking</div>
			<div class="label width150"><html:text name="RankingNoteForm" property="description" styleClass="width120"/></div>
			<div class="label width50"><%=DJMagErrorFormatter.getErrorFrom(request, "RankingNote.description.err")%></div>
		</div>
		<div id="conteinerScrollable" style="width:950px; height:400px; overflow:auto; border:#FF0000;">
			<div class="width450" style="float:left;">
				<h2>Posiciones</h2>
				<div class="renglon width420 height40">
					<div class="label width400 height40 comment">Complete el Ranking con las 100 posiciones. Al no estar completo, se ver&aacute;n posiciones vac&iacute;as en el el TOP 100. Una vez cargado, pordr&aacute; subir y bajar cada uno de los rankeados con los links en las acciones.</div>
				</div>
				<div class="renglon width420 height200" style="overflow:auto;">
					<table>
						<tr>
							<td class="headerTablas" width="50">Posici&oacute;n</td>
							<td class="headerTablas">Nombre</td>
							<td class="headerTablas" width="50">Acciones</td>
						</tr>
						<logic:iterate id="selectedPosition" name="RankingNoteForm" property="positions" indexId="iterIndexPositions">  
							<tr>
								<td align="center"><%=iterIndexPositions + 1%></td>
								<td align="center"><html:text name="selectedPosition" property="position" indexed="true" styleClass="width180"/></td>  
								<td align="center"><a href="javascript:document.RankingNoteForm.action='./moveRankingPositionUp.do?index=<%=iterIndexPositions%>';document.RankingNoteForm.submit();"><img src="boImages/subir.png" alt="Subir"></a>
								<a href="javascript:document.RankingNoteForm.action='./moveRankingPositionDown.do?index=<%=iterIndexPositions%>';document.RankingNoteForm.submit();"><img src="boImages/bajar.png" alt="Subir"></a></td>
							</tr>
						</logic:iterate>
					</table>
				</div>
			</div>
			<div class="width450" style="float:left; margin-left:20px;">
				<h2>Mostrar en</h2>
				<div class="renglon width420 height40">
					<div class="label width400 height40 comment">Seleccione los pa&iacute;ses en los que se ver&aacute; el ranking que est&eacute; editando.<br/><%=DJMagErrorFormatter.getErrorFrom(request, "RankingNote.country.err")%></div>
				</div>
				<div class="renglon width420 height200" style="overflow:auto;">
					<table>
						<tr>
							<td class="headerTablas" width="40">Activa</td>
							<td class="headerTablas">Pa&iacute;s</td>
						</tr>
						<logic:iterate id="selectedCountry" name="RankingNoteForm" property="selectedCountries">  
							<tr>
								<td><html:checkbox name="selectedCountry" property="selected" indexed="true" /></td>  
								<td><bean:write name="selectedCountry" property="countryName" /></td>
							</tr>
							
						</logic:iterate>
					</table>
				</div>
			</div>
			<div class="renglon width860 height50">
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
			</div>
			<h2>Listado de Rankings</h2>
			<div class="renglon width860 height300" style="overflow:auto;">
				<table>
					<tr>
						<td class="headerTablas" width="300">T&iacute;tulo</td>
						<td class="headerTablas" width="500">Pa&iacute;ses</td>
						<td class="headerTablas" width="60">Acciones</td>
					</tr>
					<logic:iterate name="RankingNoteForm" property="allRankings" id="iterRanking" indexId="iterIndex">
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
							<td align="center">
								<html:link action="editRanking.st?" paramName="iterRanking" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
								<html:link action="/toggleDeletedRanking" paramName="iterRanking"
									paramProperty="id" paramId="id">
									<% if (((com.tdil.ibatis.PersistentObject) iterRanking).getDeleted() == 1) { %>
										<img src="boImages/activar.png" alt="Activar">
									<% } else { %>
										<img src="boImages/desactivar.png" alt="Desactivar">
									<% } %>
								</html:link>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</div>
		</div>
	</div>
	</html:form>
</div>
</body>
</html>