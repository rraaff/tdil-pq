<%@ include file="includes/tryPage.jspf" %>
<%@ include file="includes/agentInfo.jspf" %>
<%@page import="com.tdil.lojack.web.LoJackErrorFormatter"%>
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
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<%@ include file="includes/headLogged.jsp" %>

<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
<link href="css/index_modales.css" rel="stylesheet"  type="text/css"/>
<link href="css/index_social.css" rel="stylesheet"  type="text/css"/>
<link href="css/copyright.css" rel="stylesheet"  type="text/css"/>

<script>
	  <%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
  $(function() {

	  <%@ include file="includes/datePickerES.jspf" %>
	  
	  <%@ include file="includes/closeLayers.jspf" %>
	  <%@ include file="includes/externalLogins.jspf" %>

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
  <%@ include file="includes/panicJS.jspf" %>
  <%@ include file="includes/errorAjaxJS.jspf" %>
  <%@ include file="includes/centerLayerJS.jspf" %>

  function append(st) {
		$('#password').attr('value', $('#password').attr('value') + st);
	}
</script>
<style type="text/css">
#productsMenu ul li.tabHome {
	background:#f05224;
}
</style>
<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
		<div id="productHomeMenu" class="col1_170">
			<div id="tab"></div>
			<ul class="tabServices">
				<li class="tabAlarms active" ><a href="./goToHomeAlarms.do">Mis Alarmas</a></li>
				<li class="tabLights" ><a href="./goToHomeLights.do">Mis Luces</a></li>
				<li class="tabCameras"><a href="./goToHomeCamera.do">Mi Camara</a></li>
			</ul>
		</div>
		<div id="productHomeContent" class="col1_798 alarmasBG">
			<div id="agendaWrapper">
				<h1><% AlarmAgendaForm alarmAgendaForm = (AlarmAgendaForm)session.getAttribute("AlarmAgendaForm"); %>Configurar agenda para la alarma <%=alarmAgendaForm.getIdEntidad()%></h1>
				<html:form method="POST" action="/saveAlarmAgenda">
					<fieldset>
						<label>Nombre</label>
						<html:text name="AlarmAgendaForm" property="description" styleClass="width390" />
					</fieldset>
					<fieldset>
						<label>Desde</label>
						<div style="float:left;"><html:text name="AlarmAgendaForm" property="from" /></div>
						<%=LoJackErrorFormatter.getErrorFrom(request, "from.err")%>
						<label>Hasta</label>
						<div style="float:left;"><html:text name="AlarmAgendaForm" property="to" /></div>
						<%=LoJackErrorFormatter.getErrorFrom(request, "to.err")%>
					</fieldset>
					<h4>Horarios</h4>
					<fieldset>
						<label>Desde</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaForm" property="activateTimeHour" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_HOURS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>hs y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaForm" property="activateTimeMinute" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_MINUTES) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>min y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaForm" property="activateTimeSeconds" styleClass="width80">
								<% for (String hour : DateUtils.ALL_SECONDS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
					</fieldset>
					<fieldset>
						<label>Desde</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaForm" property="deactivateTimeHour" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_HOURS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>hs y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaForm" property="deactivateTimeMinute" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_MINUTES) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>min y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaForm" property="deactivateTimeSeconds" styleClass="width80">
								<% for (String hour : DateUtils.ALL_SECONDS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
					</fieldset>
					<h4>Frecuencia</h4>
					<fieldset style="border-bottom:dotted 1px #f0ece4;">
						<label class="radiosFreq"><html:radio property="type" value="ONE_DAY">Una vez</html:radio></label>
						<label class="radiosFreq"><html:radio property="type" value="ALL_DAYS">Todos los dias</html:radio></label>
						<label class="radiosFreq"><html:radio property="type" value="BUSINESS_DAYS">Dias habiles</html:radio></label>
						<label class="radiosFreq"><html:radio property="type" value="CUSTOM">Personalizado</html:radio></label>
					</fieldset>
					<fieldset>
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="monday"/>Lu</label>
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="tuesday"/>Ma</label> 
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="wednesday"/>Mi</label>
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="thursday"/>Ju</label>
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="friday"/>Vi</label>
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="saturday"/>Sa</label>
						<label class="days"><html:checkbox name="AlarmAgendaForm" property="sunday"/>Do</label>
					</fieldset>
					<fieldset>
						<logic:equal name="AlarmAgendaForm" property="edition" value="true">
							<input type="button" onclick="save()" value="Modificar" class="indexButtonBase">
						</logic:equal>
						<logic:equal name="AlarmAgendaForm" property="edition" value="false">
							<input type="button" onclick="save()" value="Agregar" class="indexButtonBase">
						</logic:equal>
					</fieldset>
				</html:form>
				<fieldset>
					<%
					java.util.List source = alarmAgendaForm.getAlarmAgendas();
					com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
					request.setAttribute( "alarmAgendas",  paginated);
					%>
				</fieldset>
				<fieldset>
					<display:table name="alarmAgendas" sort="external" pagesize="10" id="alarmAgendas" requestURI="./homeAlarmaAgenda.jsp">
						<display:column title="Descripcion" sortable="true" sortName="Descripcion" headerClass="sortable" property="description"></display:column>
						<display:column title="Desde" sortable="true" sortName="Desde" headerClass="sortable" property="from"></display:column>
						<display:column title="Hasta" sortable="true" sortName="Hasta" headerClass="sortable" property="activateTime"></display:column>
						<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable" property="typeDescription"></display:column>
						<display:column title="Activar" sortable="true" sortName="Acivar" headerClass="sortable" property="activateTime"></display:column>
						<display:column title="Desactivar" sortable="true" sortName="Desactivar" headerClass="sortable" property="deactivateTime"></display:column>
						<display:column title="Activa" sortable="true" sortName="Activa" headerClass="sortable" property="active"></display:column>
						<display:column title="Acciones" headerClass="sortable">
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
				</fieldset>
			</div>
		</div>
	</div>
</section>
<%@ include file="includes/panicButton.jspf" %>
<%@ include file="includes/footerProductoHome.jsp" %>

<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/passwordLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/panicLayers.jspf" %>
</body>
</html>
<%@ include file="includes/catchPage.jspf" %>