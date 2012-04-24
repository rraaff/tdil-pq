<%@page import="com.tdil.djmag.web.DJMagErrorFormatter"%>
<%@page import="com.tdil.djmag.model.Magazine"%>
<%@page import="com.tdil.djmag.struts.forms.MagazineForm"%>
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
<script>
	$(function() {
		$("input[name=publishDate]").datepicker({dateFormat: 'yy-mm'});
	});
</script>
</head>

<body>
<div id="header"></div>
<div id="container">
	<div style="height:50px; display:block;"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<h1>Administraci&oacute;n de Magazine</h1>
		<div id="conteinerScrollable">
			<html:form method="POST" action="/saveMagazine" enctype="multipart/form-data">
				<span class="errorText"><%=DJMagErrorFormatter.getErrorFrom(request, "general")%></span><br>
				<div class="renglon width740 height80">
					<div class="label width50">Fecha</div>
					<div class="label width150"><html:text name="MagazineForm" property="publishDate" styleClass="width100" /><%=DJMagErrorFormatter.getErrorFrom(request, "Magazine.publish_date.err")%></div>
					<div class="label width100">Descripcion</div>
					<div class="label width400 height80"><html:textarea name="MagazineForm" property="description" styleClass="width400 height80" /><%=DJMagErrorFormatter.getErrorFrom(request, "Magazine.description.err")%></div>
				</div>
				<h2>Portada</h2>
				<div class="renglon width740 height150 border1 padding10 bgF2">
					<div class="label width300 height150">
						<logic:equal name="MagazineForm" property="hasFrontCover" value="true">
							<html:img action="/viewMagazineFrontCover" align="middle" width="112" height="150" alt="" styleClass="border1" />
							<bean:write name="MagazineForm" property="frontCover.fileName" />
							<a href="javascript:document.MagazineForm.action='./deleteMagazineFrontCover.do';document.MagazineForm.submit();">Borrar</a>
						</logic:equal>
					</div>
					<div class="label width80">
						<html:file name="MagazineForm" property="frontCoverFormFile" /><html:button property="operation" onclick="this.form.action='./uploadMagazineFrontCover.do';this.form.submit();">
							<bean:message key="uploadImage" />
						</html:button> <%=DJMagErrorFormatter.getErrorFrom(request, "Magazine.front_cover.err")%>
					</div>
				</div>
				<h2>Revista</h2>
				<div class="renglon width740 height150 border1 padding10">
					<div class="label width300 height150">
						<logic:equal name="MagazineForm" property="hasMagazineContent" value="true">
							<bean:write name="MagazineForm" property="magazineContent.fileName" />
							<a href="javascript:document.MagazineForm.action='./deleteMagazineContent.do';document.MagazineForm.submit();">Borrar</a>
						</logic:equal>
					</div>
					<div class="label width200">
						<html:file name="MagazineForm"
							property="magazineContentFormFile" /><html:button property="operation"
							onclick="this.form.action='./uploadMagazineContent.do';this.form.submit();">
							<bean:message key="uploadImage" />
						</html:button>
					</div>
					<div class="label width50">
						<logic:equal name="MagazineForm" property="objectId" value="0">
							<html:submit property="operation">
								<bean:message key="save" />
							</html:submit>
						</logic:equal>
					</div>
					<div class="label width50">
						<logic:notEqual name="MagazineForm" property="objectId" value="0">
							<html:submit property="operation">
								<bean:message key="modify" />
							</html:submit>
						</logic:notEqual>
					</div>
					<div class="label width50">
						<html:submit property="operation">
							<bean:message key="reset" />
						</html:submit>
					</div>
				</div>
			</html:form>
			<table>
				<tr>
					<td class="headerTablas">Fecha</td>
					<td class="headerTablas">Acciones</td>
				</tr>
				<logic:iterate name="MagazineForm" property="allMagazines"
					id="iterMagazine" indexId="iterIndex">
					<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
						<td
							<%=((com.tdil.ibatis.PersistentObject) iterMagazine).getDeleted() == 1 ? "class=\"notActive\""
								: ""%>
							align="left">
								<% Magazine magazine = (Magazine)iterMagazine; %>
								<%= MagazineForm.formatDate(magazine.getPublishDate())%>
						</td>
						<td><html:link action="/editMagazine" paramName="iterMagazine"
								paramProperty="id" paramId="id">
							Editar
							</html:link>
							<html:link action="/toggleDeletedMagazine" paramName="iterMagazine"
								paramProperty="id" paramId="id">
								<% if (((com.tdil.ibatis.PersistentObject) iterMagazine).getDeleted() == 1) { %>
									Activar
								<% } else { %>
									Desactivar
								<% } %>
							</html:link>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</div>
	</div>
</div>
</body>
</html>