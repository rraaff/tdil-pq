<%@page import="com.tdil.djmag.model.RankingPosition"%>
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
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		<% for (int i = 0; i < 100; i++) { %>
		$('#upload_<%=i%>').ajaxfileupload({
		  	'action': './uploadRankingPhoto.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#ranking_<%=i%>').attr('src', './viewRankingPhoto.do?pos=<%=i%>');
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		<% } %>
	}
);
</script>
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
			<h2>Posiciones</h2>
			<div class="renglon width920">
				<div class="label width920 comment">Complete el Ranking con las 100 posiciones. Al no estar completo, se ver&aacute;n posiciones vac&iacute;as en el el TOP 100. Una vez cargado, pordr&aacute; subir y bajar cada uno de los rankeados con los links en las acciones. Las im&aacute;genes del ranking deberan ser de 78px x 78px.</div>
			</div>
			<div class="renglon width920 height200" style="overflow:auto;">
				<table>
					<tr>
						<td class="headerTablas" width="50">Posici&oacute;n</td>
						<td class="headerTablas" width="100">Nombre</td>
						<td class="headerTablas">Foto</td>
						<td class="headerTablas" width="50">Acciones</td>
					</tr>
					<logic:iterate id="selectedPosition" name="RankingNoteForm" property="positions" indexId="iterIndexPositions">  
						<% RankingPosition pos = (RankingPosition) selectedPosition;%>
						<tr>
							<td align="center"><%=iterIndexPositions + 1%></td>
							<td align="center"><bean:write name="selectedPosition" property="title" /></td>
							<td align="center" width="250">
								<% if (pos.getImageId() != null && !pos.getImageId().equals(0)) { %>
									<img src="./download.st?id=<%=pos.getImageId()%>&type=PUBLIC&ext=<%=pos.getImageext()%>" width="30" height="30" align="absmiddle"> 
								<% } else { %>
									<img src="boImages/na.gif" width="30" height="30" align="absmiddle"> 
								<% } %>	
							</td>
							<td align="center">
							<html:link action="editRankingPosition.st?" paramName="selectedPosition" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
							<a href="javascript:document.RankingNoteForm.action='./moveRankingPositionUp.do?index=<%=iterIndexPositions%>';document.RankingNoteForm.submit();"><img src="boImages/subir.png" alt="Subir"></a>
							<a href="javascript:document.RankingNoteForm.action='./moveRankingPositionDown.do?index=<%=iterIndexPositions%>';document.RankingNoteForm.submit();"><img src="boImages/bajar.png" alt="Bajar"></a>
							</td>
						</tr>
					</logic:iterate>
				</table>
			</div>
			<h2>Mostrar en</h2>
			<div class="renglon width920">
				<div class="label width920 comment">Seleccione los pa&iacute;ses en los que se ver&aacute; el ranking que est&eacute; editando.<br/><%=DJMagErrorFormatter.getErrorFrom(request, "RankingNote.country.err")%></div>
			</div>
			<div class="renglon width920 height100" style="overflow:auto;">
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
			<div class="renglon width860 height200" style="overflow:auto;">
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