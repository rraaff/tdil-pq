<%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%>
<%@page import="com.tdil.lojack.prevent.model.Vehicle"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%>
<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script>

$("form[name='VehiclesSpeedLimitForm']").validate({
	errorPlacement: function(error, element) {
		error.appendTo( element.parent("div"));
	},
	rules: { 			},
	messages: {			},
	submitHandler: function() {
		clearErrors();
           $("form[name='VehiclesSpeedLimitForm']").ajaxSubmit({
   			type: "POST",
   			url: "./saveVehiculesSpeedLimits.do",
   			dataType: "json",
   			success: postSaveSpeedLimits
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
	if (data.result == 'OK') {
		$( "#editMaxSpeedLayer" ).fadeOut();
	} else {
		$.each(data, function(key, value) {
			var obj = document.getElementById('err.' + key);
			if (obj) {
				obj.innerHTML = value;
			}
        });
	}
}

</script>
<div id="changePassLayer" class="layerOnTop" style="z-index: 1500;">
<div class="registerLayerStyles editProfileLayer">
		<div class="registerLayerContent">
			<div id="xContainer"><button id="closeeditMaxSpeedLayer" style="margin-left:110px;">X</button></div>
				<html:form method="POST" action="/saveVehiculesSpeedLimits">
					<table>
							<logic:iterate id="selectedSpeedLimit" name="VehiclesSpeedLimitForm" property="speedLimits">
								<tr>
									<td><bean:write name="selectedSpeedLimit" property="vehicle.description" /></td>
									<td><html:select name="selectedSpeedLimit" property="speedLimitId" indexed="true">
										<option	value="">-</option>
										<% SpeedSelectionBean ssb = (SpeedSelectionBean)selectedSpeedLimit;
											SpeedLimit selected = ssb.getLimits().getActiveSpeedLimit();
											for (SpeedLimit sl : ssb.getLimits().getLimits()) { %>	
												<option	<%=	selected == null ? "" : (selected.getId().equals(sl.getId())) ? "selected" : ""%>
												value="<%=sl.getId()%>">
												<%=sl.getDescription()%></option>
										<% } %>
									</html:select></td>
								</tr>
							</logic:iterate>
					</table>
					<div class="myRow">
						<div class="myLabel width100per" align="center"><input type="submit" id="submitregister" value="Submit"></div>
					</div>
				</html:form>
			</div>
		</div>
	</div>
</div>