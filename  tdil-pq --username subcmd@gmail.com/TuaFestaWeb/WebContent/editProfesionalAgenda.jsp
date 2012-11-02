<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.tuafesta.model.ProfesionalAgenda"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalAgendaForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalServiceAreaForm"%>
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalSellForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@page import="com.tdil.tuafesta.struts.forms.EditProfesionalPersonalDataForm"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="editProfesionalServiceArea"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Mi cuenta (Editar mi agenda)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.ui.datepicker-es.js"></script>
<script>
$(document).ready(
	function(){
		$("input[name=eventdate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
			changeYear: true, yearRange: "1900:2015"});
		
});


</script>
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Editar agenda</h1>
		</div>
		<div id="formContent">
		<html:form method="POST" action="/saveAgenda">
			<div class="myRow">
				<div class="myLabel width80">Fecha:</div>
				<div class="myLabel width250" id="Fecha Nac."><html:text name="EditProfesionalAgendaForm" property="eventdate" styleClass="normalField width200"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, EditProfesionalAgendaForm.eventdate_key + ".err")%></div>
			</div>
			<div class="myRow" align="center"><input type="submit" value="Grabar" /></div>
			
			<% EditProfesionalAgendaForm editProfesionalAgendaForm = (EditProfesionalAgendaForm)session.getAttribute("EditProfesionalAgendaForm"); %>
			<div id="formSection" style="width:920px;">
					<h2>Agenda</h2>
					<div class="myRow">
						<%
						java.util.List source = editProfesionalAgendaForm.getAgendaList();
						com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
						request.setAttribute( "agenda",  paginated);
						%>
						<display:table name="agenda" sort="external" pagesize="10" id="agenda" requestURI="./editProfesionalAgenda.jsp">
							<display:column title="Dia" sortable="true" sortName="Dia" headerClass="sortable width250">
								<%= DateUtils.formatDateSp(((ProfesionalAgenda)pageContext.getAttribute("agenda")).getDate())%>
							</display:column>
							<display:column title="Acciones" headerClass="sortable width50">
								<a class="nonelyLink" href="./deleteAgenda.do?id=<%= ((ProfesionalAgenda)pageContext.getAttribute("agenda")).getId()%>">Borrar</a>
							</display:column>
						</display:table>
						<%=DisplayTagParamHelper.getFields(request)%>
					</div>
					<div class="myRow width920" align="center"><a href="#">Volver a mi cuenta sin grabar</a></div>
				</div>
			</div>
		</html:form>
		</div>
		<!-- aca Termina el formulario -->
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>