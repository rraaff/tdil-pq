<%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%----%>
<script>

$("form[name='SelectVehiclesForSpeedForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		clearErrors();
		$('#savespeederr').prop('innerHTML', '');
		$('#savespeederr').css('display', 'none');
           $("form[name='SelectVehiclesForSpeedForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculeSpeedLimit.do",
   			dataType: "json",
   			success: postSaveSpeedLimits,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

$( "#closeeditMaxSpeedLayer" ).click(function() {
	$( "#editVehiclesSpeedLayer" ).fadeOut();
});
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveSpeedLimits(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editVehiclesSpeedLayer" ).fadeOut();
	} else {
		$('#savespeederr').prop('innerHTML', 'Ha ocurrido un error');
		$('#savespeederr').css('display', 'block');
	}
}

</script>
<div id="xContainer"><button id="closeeditMaxSpeedLayer">X</button></div>
<h3>Velocidades máximas</h3>
<h4>Vehículo pantente: <bean:write name="SelectVehiclesForSpeedForm" property="selected.description" /></h4>
<% SelectVehiclesForm  selectVehiclesForSpeedForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSpeedForm"); %>
<div class="alert alert-error" id="savespeederr" style="display: none;"></div>
<html:form method="POST" action="/saveVehiculeSpeedLimit">
	<div id="tableStyle">
		<fieldset>
			<label class="w1"></label>
			<html:select name="SelectVehiclesForSpeedForm" property="speedSelectionBean.speedLimitId">
				<option	value="">-</option>
				<% SpeedSelectionBean ssb = ((SelectVehiclesForm)selectVehiclesForSpeedForm).getSpeedSelectionBean();
					SpeedLimit selected = ssb.getLimits().getActiveSpeedLimit();
					for (SpeedLimit sl : ssb.getLimits().getLimits()) { %>	
						<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
						value="<%=sl.getId()%>">
						<%=sl.getDescription()%></option>
				<% } %>
			</html:select>
		</fieldset>
	</div>
	<fieldset><button id="submitregister" class="indexButtonBase">Grabar</button></fieldset>
</html:form>
<%@ include file="includes/catchModal.jspf" %>