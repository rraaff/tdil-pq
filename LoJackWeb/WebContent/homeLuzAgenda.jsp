<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.lojack.gis.model.LightAgenda"%>
<%@page import="com.tdil.lojack.struts.forms.LightAgendaForm"%>
<%@page import="com.tdil.lojack.struts.forms.LightAgendaForm"%>
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
<%@ include file="includes/headLogged.jsp" %>
<script>
  $(function() {
    $( "#accordion" ).accordion();

    $("input[cl]").each(function(indice,valor) {
	   $(valor).click(function() {
		   $( "#" + $(this).attr('cl') ).fadeOut();
		});
	});

    $("input[name=from]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
		changeYear: true, minDate: "-0d", maxDate: "+10Y"});
    $("input[name=to]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
		changeYear: true, minDate: "-0d", maxDate: "+10Y"});

    $("input[name='type']").click(function () {
    	if ($(this).attr('value') == 'CUSTOM') {
    		$("input[name='monday']").removeAttr('disabled');
    		$("input[name='tuesday']").removeAttr('disabled');
    		$("input[name='wednesday']").removeAttr('disabled');
    		$("input[name='thursday']").removeAttr('disabled');
    		$("input[name='friday']").removeAttr('disabled');
    		$("input[name='saturday']").removeAttr('disabled');
    		$("input[name='sunday']").removeAttr('disabled');
       	} else {
       		$("input[name='monday']").attr('disabled','disabled');
       		$("input[name='tuesday']").attr('disabled','disabled');
       		$("input[name='wednesday']").attr('disabled','disabled');
       		$("input[name='thursday']").attr('disabled','disabled');
       		$("input[name='friday']").attr('disabled','disabled');
       		$("input[name='saturday']").attr('disabled','disabled');
       		$("input[name='sunday']").attr('disabled','disabled');
        }
    });

    if($("input[name='type']:checked").attr('value') == 'CUSTOM') {
    	$("input[name='monday']").removeAttr('disabled');
		$("input[name='tuesday']").removeAttr('disabled');
		$("input[name='wednesday']").removeAttr('disabled');
		$("input[name='thursday']").removeAttr('disabled');
		$("input[name='friday']").removeAttr('disabled');
		$("input[name='saturday']").removeAttr('disabled');
		$("input[name='sunday']").removeAttr('disabled');
    } else {
    	$("input[name='monday']").attr('disabled','disabled');
   		$("input[name='tuesday']").attr('disabled','disabled');
   		$("input[name='wednesday']").attr('disabled','disabled');
   		$("input[name='thursday']").attr('disabled','disabled');
   		$("input[name='friday']").attr('disabled','disabled');
   		$("input[name='saturday']").attr('disabled','disabled');
   		$("input[name='sunday']").attr('disabled','disabled');
    }
  });
</script>
</head>
<body>
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="./logout.do">Salir</a>
<hr> 
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr> 
<% LightAgendaForm lightAgendaForm = (LightAgendaForm)session.getAttribute("LightAgendaForm"); %>
Configurar agenda para la luz <%=lightAgendaForm.getLightId() %>

<html:form method="POST" action="/saveLightAgenda">
	Descripcion:<html:text name="LightAgendaForm" property="description" styleClass="normalField width120"/><br>
	Desde el dia <html:text name="LightAgendaForm" property="from" styleClass="normalField width120"/> hasta el dia <html:text name="LightAgendaForm" property="to" styleClass="normalField width120"/><br>
	
	Desde las: <html:select name="LightAgendaForm" property="activateTimeHour">
		<% for (String hour : DateUtils.ALL_HOURS) { %>
			<option value="<%=hour%>" <%=hour.equals(lightAgendaForm.getActivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> horas y <html:select name="LightAgendaForm" property="activateTimeMinute">
		<% for (String hour : DateUtils.ALL_MINUTES) { %>
			<option value="<%=hour%>" <%=hour.equals(lightAgendaForm.getActivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> minutos y <html:select name="LightAgendaForm" property="activateTimeSeconds">
		<% for (String hour : DateUtils.ALL_SECONDS) { %>
			<option value="<%=hour%>" <%=hour.equals(lightAgendaForm.getActivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> segundos <br>
	
	Hasta las: <html:select name="LightAgendaForm" property="deactivateTimeHour">
		<% for (String hour : DateUtils.ALL_HOURS) { %>
			<option value="<%=hour%>" <%=hour.equals(lightAgendaForm.getDeactivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select>horas y <html:select name="LightAgendaForm" property="deactivateTimeMinute">
		<% for (String hour : DateUtils.ALL_MINUTES) { %>
			<option value="<%=hour%>" <%=hour.equals(lightAgendaForm.getDeactivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> minutos y <html:select name="LightAgendaForm" property="deactivateTimeSeconds">
		<% for (String hour : DateUtils.ALL_SECONDS) { %>
			<option value="<%=hour%>" <%=hour.equals(lightAgendaForm.getDeactivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> segundos <br><br>
	
	<html:radio property="type" value="ALL_DAYS"></html:radio>Todos los dias<br>
	<html:radio property="type" value="BUSINESS_DAYS">Dias habiles</html:radio><br>
	<html:radio property="type" value="CUSTOM">Personalizado</html:radio><br>
	
	Lunes<html:checkbox name="LightAgendaForm" property="monday"/> | 
	Martes<html:checkbox name="LightAgendaForm" property="tuesday"/> | 
	Miercoles<html:checkbox name="LightAgendaForm" property="wednesday"/> | 
	Jueves<html:checkbox name="LightAgendaForm" property="thursday"/> | 
	Viernes<html:checkbox name="LightAgendaForm" property="friday"/> | 
	Sabado<html:checkbox name="LightAgendaForm" property="saturday"/> | 
	Domingo<html:checkbox name="LightAgendaForm" property="sunday"/><br>
	
	<logic:equal name="LightAgendaForm" property="edition" value="true">
		<html:submit property="operation">
			Grabar
		</html:submit>
	</logic:equal>
	<logic:equal name="LightAgendaForm" property="edition" value="false">
		<html:submit property="operation">
			Agregar
		</html:submit>
	</logic:equal>
	<input type="button" id="" onclick="this.form.action='./resetLightAgenda.do';this.form.submit();" value="Reset">
</html:form>

<div class="myRow">
	<%
	java.util.List source = lightAgendaForm.getLightAgendas();
	com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
	request.setAttribute( "lightAgendas",  paginated);
	%>
	<display:table name="lightAgendas" sort="external" pagesize="10" id="lightAgendas" requestURI="./homeLuzAgenda.jsp">
		<display:column title="Descripcion" sortable="true" sortName="Descripcion" headerClass="sortable width350" property="description"></display:column>
		<display:column title="Desde" sortable="true" sortName="Desde" headerClass="sortable width350" property="from"></display:column>
		<display:column title="Hasta" sortable="true" sortName="Hasta" headerClass="sortable width350" property="activateTime"></display:column>
		<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width50" property="typeDescription"></display:column>
		<display:column title="Activar" sortable="true" sortName="Acivar" headerClass="sortable width350" property="activateTime"></display:column>
		<display:column title="Desactivar" sortable="true" sortName="Desactivar" headerClass="sortable width350" property="deactivateTime"></display:column>
		<display:column title="Activa" sortable="true" sortName="Activa" headerClass="sortable width350" property="active"></display:column>
		<display:column title="Acciones" headerClass="sortable width100">
			<a class="nonelyLink" href="./editLightAgenda.do?id=<%= ((LightAgenda)pageContext.getAttribute("lightAgendas")).getId()%>">Editar</a> 
				<a class="nonelyLink" href="./toggleActivationLightAgenda.do?agendaId=<%= ((LightAgenda)pageContext.getAttribute("lightAgendas")).getId()%>">
					<% if (((LightAgenda)pageContext.getAttribute("lightAgendas")).isActive()) { %>
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