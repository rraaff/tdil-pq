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
			<div class="renglon"><span class="comment">Desde esta sección podrá revisar los datos del profesional.</span></div>
			
			<div class="renglon">
				<div class="label width50">Logo: </div>
				<div class="label" style="border:1px solid #333;">
					<% if (profesional.getIdProfilePicture() != null && profesional.getIdProfilePicture() != 0) { %>
						<img src="./downloadThumb.st?id=<%=profesional.getIdProfilePicture()%>&width=78&height=78&type=PUBLIC&ext=<%=profesional.getExtProfilePicture()%>"/>
					<% } else { %>
						-
					<% } %>
					<% if (reviewProfesionalForm.getChangeLogo() != null) { %>
						-> <img src="./viewProfesionalChangeLogo.do" width="78" height="78" />
					<% } else { %>
						
					<% } %>
				</div>
				<div class="label width20">&nbsp;</div>
				<div class="label">Nombre profesional o de la empresa: <strong><%=profesional.getBusinessname()%> <%=!StringUtils.isEmpty(change.getBusinessname()) ? " -> " + change.getBusinessname() : ""%></strong><br/>
				CUIT: <strong><%=!StringUtils.isEmpty(profesional.getCuit()) ? profesional.getCuit() : "-"%><%=!StringUtils.isEmpty(change.getCuit()) ? " -> " + change.getCuit() : ""%></strong> - IIBB: <strong><%=!StringUtils.isEmpty(profesional.getIibb()) ? profesional.getIibb() : "-"%> <%=!StringUtils.isEmpty(change.getIibb()) ? " -> " + change.getIibb() : ""%></strong>
				<br/>Ubicaci&oacute;n: <strong><%=reviewProfesionalForm.getLocation().getNombre()%> <%=reviewProfesionalForm.getChangeLocation() != null ? " -> " + reviewProfesionalForm.getChangeLocation().getNombre() : ""%></strong></div>
			</div>
			<div class="renglon">Descripci&oacute;n: 
				<strong><%=com.tdil.utils.StringUtils.nvl(profesional.getDescription(), "-")%> </strong>
					<% if (!StringUtils.isEmpty(change.getDescription())) { %>
						<strong> -> <%=change.getDescription()%></strong>
					<% } else { %>
					<% } %>
			</div>
			<div class="renglon">
				<div class="label">Facebook: <strong><%=com.tdil.utils.StringUtils.nvl(profesional.getFacebook(), "-")%> </strong>
					<% if (!StringUtils.isEmpty(change.getFacebook())) { %>
						-> <%=change.getFacebook()%>
					<% } else { %>
					<% } %></div>
			</div>
			<div class="renglon">
				<div class="label">Website: <strong><%=com.tdil.utils.StringUtils.nvl(profesional.getWebsite(), "-")%> </strong>
					<% if (!StringUtils.isEmpty(change.getWebsite())) { %>
						-> <%=change.getWebsite()%>
					<% } else { %>
					<% } %></div>
			</div>
			<div class="renglon">
				<div class="label">Horario: <strong><%=com.tdil.utils.StringUtils.nvl(profesional.getBusinesshours(), "-")%> </strong>
					<% if (!StringUtils.isEmpty(change.getBusinesshours())) { %>
						-> <%=change.getBusinesshours()%>
					<% } else { %>
					<% } %></div>
			</div>
			<div class="renglon">
				<div class="label"><%=com.tdil.utils.StringUtils.nvl("DELETE".equals(profesional.getVideo1()) ? "-" : profesional.getVideo1(), "-")%>Video 1:
				<% if (!StringUtils.isEmpty(change.getVideo1()) && !"DELETE".equals(change.getVideo1())) { %>
					-> <%="DELETE".equals(change.getVideo1()) ? "-" : change.getVideo1()%>
				<% } else { %>
				<% } %></div>
			</div>
			<div class="renglon">
				<div class="label"><%=com.tdil.utils.StringUtils.nvl("DELETE".equals(profesional.getVideo2()) ? "-" : profesional.getVideo2(), "-")%>Video 2:
				<% if (!StringUtils.isEmpty(change.getVideo2()) && !"DELETE".equals(change.getVideo2())) { %>
					-> <%="DELETE".equals(change.getVideo2()) ? "-" : change.getVideo2()%>
				<% } else { %>
				<% } %></div>
			</div>
			<div class="renglon">
				<div class="label"><%=com.tdil.utils.StringUtils.nvl("DELETE".equals(profesional.getVideo3()) ? "-" : profesional.getVideo3(), "-")%>Video 3:
				<% if (!StringUtils.isEmpty(change.getVideo3()) && !"DELETE".equals(change.getVideo3())) { %>
					-> <%="DELETE".equals(change.getVideo3()) ? "-" : change.getVideo3()%>
				<% } else { %>
				<% } %></div>
			</div>
			<div class="renglon">
				<div class="label"><%=com.tdil.utils.StringUtils.nvl("DELETE".equals(profesional.getVideo4()) ? "-" : profesional.getVideo4(), "-")%>Video 4:
				<% if (!StringUtils.isEmpty(change.getVideo4()) && !"DELETE".equals(change.getVideo4())) { %>
					-> <%="DELETE".equals(change.getVideo4()) ? "-" : change.getVideo4()%>
				<% } else { %>
				<% } %></div>
			</div>
			<div class="renglon">
				<div class="label"><%=com.tdil.utils.StringUtils.nvl("DELETE".equals(profesional.getVideo5()) ? "-" : profesional.getVideo5(), "-")%>Video 5:
				<% if (!StringUtils.isEmpty(change.getVideo5()) && !"DELETE".equals(change.getVideo5())) { %>
					-> <%="DELETE".equals(change.getVideo5()) ? "-" : change.getVideo5()%>
				<% } else { %>
				<% } %></div>
			</div>
			<html:form method="POST" action="/approveProfesionalBusinessDataChange">
				<div class="myRow" style="margin:0 auto; float:none;" align="center">
					<a href="reviewProfesional.jsp">Volver</a>
					<% if (reviewProfesionalForm.isBusinessDataModified()) { %>
						<html:submit property="operation">
							<bean:message key="Approve" />
						</html:submit>
						<input type="button" value="Desaprobar" onClick="disapprove();">
					<% } %>
					<html:submit property="operation">
						<bean:message key="Block" />
					</html:submit>
				</div>
			</html:form>
		</div>
	</div>
</div>
<div id="disapprovelayer" class="hide" style="z-index: 500;">
	<html:form method="POST" action="/disapproveProfesionalBusinessDataChange">
		<div id="Motivo"><html:text name="ReviewProfesionalForm" property="disapproveMotive" styleClass="normalField"/></div>
		<div id="buttonHolder" align="center"><input type="submit" value="Desaprobar"/><input type="button" id="canceldisapprove" value="Cancelar"></div>
		<!-- div id="buttonMeArrepentiHolder"><a href="postits.jsp" title="Volver a Postits"><img src="images/experiencias/postits/boton_me-arrepenti_out.png" width="160" height="52" /></a></div-->
	</html:form>
</div>
</body>
</html>