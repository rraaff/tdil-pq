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
--><%@ include file="../includes/checkThalamusUp.jspf" %><!--
--><%@ include file="../includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBeHomeUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
</head>
<body>

				<h1><% AlarmAgendaForm alarmAgendaForm = (AlarmAgendaForm)session.getAttribute("AlarmAgendaFormMobile"); %>Configurar agenda para la alarma <%=alarmAgendaForm.getIdEntidad()%></h1>
				<html:form method="POST" action="/mobile/saveAlarmAgendaMobile">
					<fieldset>
						<label>Nombre</label>
						<html:text name="AlarmAgendaFormMobile" property="description" styleClass="width390" />
					</fieldset>
					<fieldset>
						<label>Desde</label>
						<div style="float:left;"><html:text name="AlarmAgendaFormMobile" property="from" /></div>
						<%=LoJackErrorFormatter.getErrorFrom(request, "from.err")%>
						<label>Hasta</label>
						<div style="float:left;"><html:text name="AlarmAgendaFormMobile" property="to" /></div>
						<%=LoJackErrorFormatter.getErrorFrom(request, "to.err")%>
					</fieldset>
					<h4>Horarios</h4>
					<fieldset>
						<label>Desde</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaFormMobile" property="activateTimeHour" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_HOURS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>hs y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaFormMobile" property="activateTimeMinute" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_MINUTES) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>min y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaFormMobile" property="activateTimeSeconds" styleClass="width80">
								<% for (String hour : DateUtils.ALL_SECONDS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getActivateTimeSeconds()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
					</fieldset>
					<fieldset>
						<label>Desde</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaFormMobile" property="deactivateTimeHour" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_HOURS) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeHour()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>hs y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaFormMobile" property="deactivateTimeMinute" styleClass="width80 mRight20">
								<% for (String hour : DateUtils.ALL_MINUTES) { %>
									<option value="<%=hour%>" <%=hour.equals(alarmAgendaForm.getDeactivateTimeMinute()) ? "selected" : ""%>><%=hour%></option>
								<% } %>
							</html:select>
						</div>
						<label>min y</label>
						<div style="float:left;">
							<html:select name="AlarmAgendaFormMobile" property="deactivateTimeSeconds" styleClass="width80">
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
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="monday"/>Lu</label>
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="tuesday"/>Ma</label> 
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="wednesday"/>Mi</label>
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="thursday"/>Ju</label>
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="friday"/>Vi</label>
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="saturday"/>Sa</label>
						<label class="days"><html:checkbox name="AlarmAgendaFormMobile" property="sunday"/>Do</label>
					</fieldset>
					<fieldset>
						<logic:equal name="AlarmAgendaFormMobile" property="edition" value="true">
							<input type="submit" value="Modificar" />
						</logic:equal>
						<logic:equal name="AlarmAgendaFormMobile" property="edition" value="false">
							<input type="submit" value="Salvar" />
						</logic:equal>
						<input type="button" id="" onclick="this.form.action='./resetAlarmAgendaMobile.do';this.form.submit();" value="Reset" class="indexButtonBase">
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
							<a class="nonelyLink" href="./editAlarmAgendaMobile.do?id=<%= ((AlarmAgenda)pageContext.getAttribute("alarmAgendas")).getId()%>">Editar</a> 
							<a class="nonelyLink" href="./toggleActivationAlarmAgendaMobile.do?agendaId=<%= ((AlarmAgenda)pageContext.getAttribute("alarmAgendas")).getId()%>">
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
			
</body>
</html>