<%@page import="com.tdil.lojack.gis.model.AlarmAgenda"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.lojack.struts.forms.AlarmAgendaForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ taglib uri="http://displaytag.sf.net" prefix="display" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<html>
<head>
<%@ include file="includes/head.jsp" %>
<script>
</script>
</head>
<body>
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="./logout.do">Salir</a>
<hr> 
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr> 

<% AlarmAgendaForm alarmAgendaForm = (AlarmAgendaForm)session.getAttribute("AlarmAgendaForm"); %>
Configurar agenda para la alarma <%=alarmAgendaForm.getAlarmId() %>

<div class="myRow">
	<%
	java.util.List source = alarmAgendaForm.getAlarmAgendas();
	com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
	request.setAttribute( "alarmAgendas",  paginated);
	%>
	<display:table name="alarmAgendas" sort="external" pagesize="10" id="alarmAgendas" requestURI="./homeAlarmaAgenda.jsp">
		<display:column title="Descripcion" sortable="true" sortName="Descripcion" headerClass="sortable width350" property="description"></display:column>
		<display:column title="Desde" sortable="true" sortName="Desde" headerClass="sortable width350" property="from"></display:column>
		<display:column title="Hasta" sortable="true" sortName="Hasta" headerClass="sortable width350" property="activateTime"></display:column>
		<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width50" property="typeDescription"></display:column>
		<display:column title="Activar" sortable="true" sortName="Acivar" headerClass="sortable width350" property="activateTime"></display:column>
		<display:column title="Desactivar" sortable="true" sortName="Desactivar" headerClass="sortable width350" property="deactivateTime"></display:column>
		<display:column title="Activa" sortable="true" sortName="Activa" headerClass="sortable width350" property="active"></display:column>
		<display:column title="Acciones" headerClass="sortable width100">
			<a class="nonelyLink" href="./editAlarmAgenda.do?id=<%= ((AlarmAgenda)pageContext.getAttribute("alarmAgendas")).getId()%>">Editar</a> 
				<a class="nonelyLink" href="./toggleActivationAlarmAgenda.do?id=<%= ((AlarmAgenda)pageContext.getAttribute("alarmAgendas")).getId()%>">
					<% if (((AlarmAgenda)pageContext.getAttribute("alarmAgendas")).isActive()) { %>
						Desactivar
					<% } else { %>
						Activar
					<% } %>
				</a>
			</display:column>
	</display:table>
	<%=DisplayTagParamHelper.getFields(request)%>
</div>
</body>
</html>