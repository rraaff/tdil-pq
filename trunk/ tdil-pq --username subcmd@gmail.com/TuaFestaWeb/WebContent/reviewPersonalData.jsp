<%@page import="com.tdil.tuafesta.model.ProfesionalChange"%>
<%@page import="com.tdil.tuafesta.struts.forms.ReviewProfesionalForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalStatusHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalAdministrationForm"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page info="verifyProfesional"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<script>
$(document).ready(
	function(){
		$( "#canceldisapprove" ).click(function() {
			$( "#disapprovelayer" ).fadeOut();
		});
	}
);
jQuery.fn.center = function () {
    this.css("position","absolute");
    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + 
                                                $(window).scrollTop()) + "px");
    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
                                                $(window).scrollLeft()) + "px");
    return this;
}
function disapprove() {
	$( "#disapprovelayer" ).center().fadeIn(500);
}
</script>
<style>
th.sorted a,th.sortable a {
	background-position: right;
	display: block;
	width: 100%;
}
th.sortable a {
	background-image: url(img/displaytag/arrow_off.png);
}

th.order1 a {
	background-image: url(img/displaytag/arrow_down.png);
}

th.order2 a {
	background-image: url(img/displaytag/arrow_up.png);
}

tr.odd {
	background-color: #fff
}

tr.tableRowEven,tr.even {
	background-color: #fea
}
th.sorted {
	background-color: orange;
}
</style>
</head>

<body>
<%@ include file="includes/boMenu.jsp" %>
<div id="boWrapper">
	<div id="boCentral">
		<h1>Revisar datos personales del profesional</h1>
		<% ReviewProfesionalForm reviewProfesionalForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");
			Profesional profesional = reviewProfesionalForm.getProfesional(); 
			ProfesionalChange change = reviewProfesionalForm.getProfesionalChange(); %>
		<div id="formulariosBase">
			<div class="renglon"><span class="comment">Desde esta secci&oacute;n podr&aacute; revisar los datos del profesional.</span></div>
			<div class="renglon">Nombre: <strong><%=profesional.getFirstname()%> <%=!StringUtils.isEmpty(change.getFirstname()) ? " -> " + change.getFirstname() : ""%></strong><br/>Apellido: <strong><%=profesional.getLastname()%> <%=!StringUtils.isEmpty(change.getLastname()) ? " -> " + change.getLastname() : ""%></strong><br/>Sexo: <strong><%=profesional.getSex().equals("m") ? "Masculino" : "Femenino"%> <%=!StringUtils.isEmpty(change.getSex()) ? " -> " + (change.getSex().equals("m") ? "Masculino" : "Femenino") : ""%></strong><br/>C&oacute;digo de &aacute;rea: <strong><%=profesional.getPhoneareacode()%> <%=!StringUtils.isEmpty(change.getPhoneareacode()) ? " -> " + change.getPhoneareacode() : ""%></strong> - N&uacute;mero: <strong><%=profesional.getPhonenumber()%> <%=!StringUtils.isEmpty(change.getPhonenumber()) ? " -> " + change.getPhonenumber() : ""%></strong> - Interno: <strong><%=profesional.getPhoneextension()%> <%=!StringUtils.isEmpty(change.getPhoneextension()) ? " -> " + change.getPhoneextension() : ""%></strong> - Tipo: <strong><%=profesional.getPhonetype()%> <%=!StringUtils.isEmpty(change.getPhonetype()) ? " -> " + change.getPhonetype() : ""%></strong></div>
			<html:form method="POST" action="/approveProfesionalPersonalDataChange">
				<div class="renglon" align="center">
					<!--div class="myLabel width50" style="padding-top:16px;"--><a href="reviewProfesional.jsp">Volver</a><!--/div>
					<div class="myLabel width300"-->
						<% if (reviewProfesionalForm.isPersonalDataModified()) { %>
						<html:submit property="operation">
							<bean:message key="Approve" />
						</html:submit>
						<input type="button" value="Desaprobar" onClick="disapprove();">
						<% } %>
						<html:submit property="operation">
							<bean:message key="Block" />
						</html:submit>
					<!--/div-->
				</div>
			</html:form>
		</div>
	</div>
</div>
<div id="disapprovelayer" class="hide" style="z-index: 500;">
	<html:form method="POST" action="/disapproveProfesionalPersonalDataChange">
		<div id="Motivo"><html:text name="ReviewProfesionalForm" property="disapproveMotive" styleClass="normalField"/></div>
		<div id="buttonHolder" align="center"><input type="submit" value="Desaprobar"/><input type="button" id="canceldisapprove" value="Cancelar"></div>
		<!-- div id="buttonMeArrepentiHolder"><a href="postits.jsp" title="Volver a Postits"><img src="images/experiencias/postits/boton_me-arrepenti_out.png" width="160" height="52" /></a></div-->
	</html:form>
</div>
</body>
</html>