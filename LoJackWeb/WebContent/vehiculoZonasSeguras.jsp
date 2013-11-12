<%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%>
<%@ include file="includes/tryModal.jspf" %><%--
--%><%@page import="com.tdil.lojack.prevent.model.SecureZone"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.beans.SecureZoneSelectionBean"%><%--
--%><%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%><%--
--%><%@page import="com.tdil.lojack.prevent.model.Vehicle"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><script>

$("form[name='SelectVehiclesForSecureZoneForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		<%@ include file="includes/blockUI.jspf" %>
		clearErrors();
		$('#savesz').prop('innerHTML', '');
		$('#savesz').css('display', 'none');
           $("form[name='SelectVehiclesForSecureZoneForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculeSecureZones.do",
   			dataType: "json",
   			success: postSaveSpeedLimits,
   			<%@ include file="includes/openErrorLayerJS.jspf" %>
   			});
       }
});

$( "#closeeditSecureZonesLayer" ).click(function() {
	$( "#editSecureZoneLayer" ).fadeOut();
});
		

function clearErrors() {
	$("div[id^='err.']").each(function(index, valor) {
		$(valor).prop('innerHTML','');
	});
}

function postSaveSpeedLimits(data) {
	<%@ include file="includes/unblockUI.jspf" %>
	if (data.result == 'OK') {
		$( "#editSecureZoneLayer" ).fadeOut();
	} else {
		$('#savesz').prop('innerHTML', 'Ha ocurrido un error');
		$('#savesz').css('display', 'block');
	}
}

</script>
<div id="xContainer"><button id="closeeditSecureZonesLayer">X</button></div>
<h3>Zonas seguras</h3>
<h4>Vehículo pantente: <bean:write name="SelectVehiclesForSecureZoneForm" property="selected.description" /></h4>
<% SelectVehiclesForm  selectVehiclesForSpeedForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSecureZoneForm"); %>
<div class="alert alert-error" id="savesz" style="display: none;"></div>
<html:form method="POST" action="/saveVehiculeSecureZones">
	<div id="tableStyle">
		<fieldset>
			<label class="w100"><html:select name="SelectVehiclesForSecureZoneForm" property="secureZoneSelectionBean.secureZoneId">
				<option	value="">-</option>
				<% SecureZoneSelectionBean ssb = ((SelectVehiclesForm)selectVehiclesForSpeedForm).getSecureZoneSelectionBean();
					SecureZone selected = ssb.getZones().getActiveZone();
					for (SecureZone sl : ssb.getZones().getSecureZones()) { %>	
						<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
						value="<%=sl.getId()%>">
						<%=sl.getDescription()%></option>
				<% } %>
			</html:select></label>
		</fieldset>
	</div>
	<fieldset><button id="submitregister" class="indexButtonBase" >Aplicar</button></fieldset>
</html:form>
<%@ include file="includes/catchModal.jspf" %>