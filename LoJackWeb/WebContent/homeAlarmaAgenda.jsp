<%@page import="com.tdil.utils.DateUtils"%>
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

    $('#passwordLayerButton').click(function() {
    	$("form[name='AlarmAgendaForm']").submit();
	});
  });

  function save() {
	  $('#password').attr('value','');
	  centerLayer($(window), $( "#passwordLayer" ));
  }

  function centerLayer(objWin, objLayer) {
		var top = (objWin.height() / 2) - (objLayer.height() / 2);
		var left = (objWin.width() / 2) - (objLayer.width() / 2);
		objLayer.css({
			position: 'absolute',
			top: top + 'px',
			left: left + 'px'
		}).fadeIn(500);
	}

  function append(st) {
		$('#password').attr('value', $('#password').attr('value') + st);
	}
</script>
</head>
<body>
Cambiar mis datos | Cambiar mi password | hola <%=websiteUser.getName()%> | <a href="./logout.do">Salir</a>
<hr> 
Producto Home | Producto Prevent | Producto Pet | Producto Otro | Boton de panico
<hr> 

<% AlarmAgendaForm alarmAgendaForm = (AlarmAgendaForm)session.getAttribute("AlarmAgendaForm"); %>
Configurar agenda para la alarma <%=alarmAgendaForm.getAlarmId() %>

<html:form method="POST" action="/saveAlarmAgenda">
	Descripcion:<html:text name="AlarmAgendaForm" property="description" styleClass="normalField width120"/><br>
	Desde el dia <html:text name="AlarmAgendaForm" property="from" styleClass="normalField width120"/> hasta el dia <html:text name="AlarmAgendaForm" property="to" styleClass="normalField width120"/><br>
	
	Desde las: <html:select name="AlarmAgendaForm" property="activateTimeHour">
		<% for (String hour : DateUtils.ALL_HOURS) { %>
			<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> horas y <html:select name="AlarmAgendaForm" property="activateTimeMinute">
		<% for (String hour : DateUtils.ALL_MINUTES) { %>
			<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> minutos y <html:select name="AlarmAgendaForm" property="activateTimeSeconds">
		<% for (String hour : DateUtils.ALL_SECONDS) { %>
			<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> segundos <br>
	
	Hasta las: <html:select name="AlarmAgendaForm" property="deactivateTimeHour">
		<% for (String hour : DateUtils.ALL_HOURS) { %>
			<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select>horas y <html:select name="AlarmAgendaForm" property="deactivateTimeMinute">
		<% for (String hour : DateUtils.ALL_MINUTES) { %>
			<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> minutos y <html:select name="AlarmAgendaForm" property="deactivateTimeSeconds">
		<% for (String hour : DateUtils.ALL_SECONDS) { %>
			<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
		<% } %>
	</html:select> segundos <br><br>
	
	<html:radio property="type" value="ALL_DAYS"></html:radio>Todos los dias<br>
	<html:radio property="type" value="BUSINESS_DAYS">Dias habiles</html:radio><br>
	<html:radio property="type" value="CUSTOM">Personalizado</html:radio><br>
	
	Lunes<html:checkbox name="AlarmAgendaForm" property="monday"/> | 
	Martes<html:checkbox name="AlarmAgendaForm" property="tuesday"/> | 
	Miercoles<html:checkbox name="AlarmAgendaForm" property="wednesday"/> | 
	Jueves<html:checkbox name="AlarmAgendaForm" property="thursday"/> | 
	Viernes<html:checkbox name="AlarmAgendaForm" property="friday"/> | 
	Sabado<html:checkbox name="AlarmAgendaForm" property="saturday"/> | 
	Domingo<html:checkbox name="AlarmAgendaForm" property="sunday"/><br>
	
	<%@ include file="includes/passwordLayer.jspf" %>
	
	<logic:equal name="AlarmAgendaForm" property="edition" value="true">
		<input type="button" onclick="save()" value="Modificar">
	</logic:equal>
	<logic:equal name="AlarmAgendaForm" property="edition" value="false">
		<input type="button" onclick="save()" value="Agregar">
	</logic:equal>
	<input type="button" id="" onclick="this.form.action='./resetAlarmAgenda.do';this.form.submit();" value="Reset">
</html:form>

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
				<a class="nonelyLink" href="./toggleActivationAlarmAgenda.do?agendaId=<%= ((AlarmAgenda)pageContext.getAttribute("alarmAgendas")).getId()%>">
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