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
	<div id="boCentral" class="height450">
		<h1>Revisar datos personales del profesional</h1>
		<% ReviewProfesionalForm reviewProfesionalForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");
			Profesional profesional = reviewProfesionalForm.getProfesional(); 
			ProfesionalChange change = reviewProfesionalForm.getProfesionalChange(); %>
		<div id="formulariosBase" class="height350">
			<div class="renglon width100per">
				<div class="label width100per"><span class="comment">Desde esta sección podrá revisar los datos del profesional.</span></div>
			</div>
			Nombre profesiona o de la empresa: <%=profesional.getBusinessname()%> <%=!StringUtils.isEmpty(change.getBusinessname()) ? " -> " + change.getBusinessname() : ""%><br>
			CUIT: <%=!StringUtils.isEmpty(profesional.getCuit()) ? profesional.getCuit() : "-"%> <%=!StringUtils.isEmpty(change.getCuit()) ? " -> " + change.getCuit() : ""%><br>
			IIBB: <%=!StringUtils.isEmpty(profesional.getIibb()) ? profesional.getIibb() : "-"%> <%=!StringUtils.isEmpty(change.getIibb()) ? " -> " + change.getIibb() : ""%><br>
			Ubicacion: <%=reviewProfesionalForm.getLocation().getNombre()%> <%=reviewProfesionalForm.getChangeLocation() != null ? reviewProfesionalForm.getLocation().getNombre() : ""%><br>
			Logo: <% if (profesional.getIdProfilePicture() != null && profesional.getIdProfilePicture() != 0) { %>
						<img src="./downloadThumb.st?id=<%=profesional.getIdProfilePicture()%>&width=78&height=78&type=PUBLIC&ext=<%=profesional.getExtProfilePicture()%>"/>
					<% } else { %>
						-
					<% } %>
					<% if (reviewProfesionalForm.getChangeLogo() != null) { %>
						-> <img src="./viewProfesionalChangeLogo.do" width="78" height="78" />
					<% } else { %>
						
					<% } %><br>
			Descripcion: <%=com.tdil.utils.StringUtils.nvl(profesional.getDescription(), "-")%> 
				<% if (!StringUtils.isEmpty(change.getDescription())) { %>
					-> <%=change.getDescription()%>
				<% } else { %>
				<% } %><br>
			Video1: <%=com.tdil.utils.StringUtils.nvl(profesional.getVideo1(), "-")%> 
				<% if (!StringUtils.isEmpty(change.getVideo1())) { %>
					-> <%=change.getVideo1()%>
				<% } else { %>
				<% } %><br>
			Video2: <%=com.tdil.utils.StringUtils.nvl(profesional.getVideo2(), "-")%> 
				<% if (!StringUtils.isEmpty(change.getVideo2())) { %>
					-> <%=change.getVideo2()%>
				<% } else { %>
				<% } %><br>
			Video3: <%=com.tdil.utils.StringUtils.nvl(profesional.getVideo3(), "-")%> 
				<% if (!StringUtils.isEmpty(change.getVideo3())) { %>
					-> <%=change.getVideo3()%>
				<% } else { %>
				<% } %><br>
			Video4: <%=com.tdil.utils.StringUtils.nvl(profesional.getVideo4(), "-")%> 
				<% if (!StringUtils.isEmpty(change.getVideo4())) { %>
					-> <%=change.getVideo4()%>
				<% } else { %>
				<% } %><br>
			Video5: <%=com.tdil.utils.StringUtils.nvl(profesional.getVideo5(), "-")%> 
				<% if (!StringUtils.isEmpty(change.getVideo5())) { %>
					-> <%=change.getVideo5()%>
				<% } else { %>
				<% } %><br>
		</div>
	</div>
	<html:form method="POST" action="/approveProfesionalBusinessDataChange">
		<div class="myRow width350" style="margin:0 auto; float:none;">
			<div class="myLabel width50" style="padding-top:16px;"><a href="reviewProfesional.jsp">Volver</a></div>
			<div class="myLabel width300">
				<html:submit property="operation">
					<bean:message key="Approve" />
				</html:submit>
				<html:submit property="operation">
					<bean:message key="Block" />
				</html:submit>
			</div>
		</div>
	</html:form>
	
</div>
</body>
</html>