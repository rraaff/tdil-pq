<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.ljpeugeot.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.ljpeugeot.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%----%>
<script>

$("form[name='VehiclesSpeedLimitForm']").validate({
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
           $("form[name='VehiclesSpeedLimitForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculesSpeedLimits.do",
   			dataType: "json",
   			success: postSaveSpeedLimits,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

$( "#closeeditMaxSpeedLayer" ).click(function() {
	$( "#editMaxSpeedLayer" ).fadeOut();
});
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveSpeedLimits(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editMaxSpeedLayer" ).fadeOut();
	} else {
		$('#savespeederr').prop('innerHTML', 'Ha ocurrido un error');
		$('#savespeederr').css('display', 'block');
	}
}

</script>
<div id="xContainer"><button id="closeeditMaxSpeedLayer">X</button></div>
<h3>Determinar velocidades m�ximas</h3>
<div class="alert alert-error" id="savespeederr" style="display: none;"></div>
<html:form method="POST" action="/saveVehiculesSpeedLimits">
	<div id="tableStyle">
		<fieldset class="tableHeader">
			<label class="w1">Patente</label>
			<label class="w2">Vel. m�xima</label>
		</fieldset>
		<logic:iterate id="selectedSpeedLimit" name="VehiclesSpeedLimitForm" property="speedLimits">
			<fieldset>
				<label class="w1"><bean:write name="selectedSpeedLimit" property="vehicle.description" /></label>
				<html:select name="selectedSpeedLimit" property="speedLimitId" indexed="true">
					<option	value="">-</option>
					<% SpeedSelectionBean ssb = (SpeedSelectionBean)selectedSpeedLimit;
						SpeedLimit selected = ssb.getLimits().getActiveSpeedLimit();
						for (SpeedLimit sl : ssb.getLimits().getLimits()) { %>	
							<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
							value="<%=sl.getId()%>">
							<%=sl.getDescription()%></option>
					<% } %>
				</html:select>
			</fieldset>
		</logic:iterate>
	</div>
	<fieldset><button id="submitregister" class="indexButtonBase">Grabar</button></fieldset>
</html:form>
<%@ include file="includes/catchModal.jspf" %>