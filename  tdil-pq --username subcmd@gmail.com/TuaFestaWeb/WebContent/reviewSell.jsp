<%@page import="com.tdil.tuafesta.struts.forms.beans.PublicImageBlobBean"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
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
		<h1>Revisar datos de las ventas del profesional</h1>
		<% ReviewProfesionalForm reviewProfesionalForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");
			Profesional profesional = reviewProfesionalForm.getProfesional(); 
			ProfesionalChange change = reviewProfesionalForm.getProfesionalChange(); %>
				<div id="formContent">
			<% ReviewProfesionalForm sellDetailsForm = (ReviewProfesionalForm)session.getAttribute("ReviewProfesionalForm");  %>
			<div id="formSection" style="width:920px;">
				<h2><bean:write name="ReviewProfesionalForm" property="sellValueObject.name"/></h2>
				<h3><bean:write name="ReviewProfesionalForm" property="sellValueObject.categoryText"/></h3>
				<div class="myRow">
					<% if (sellDetailsForm.hasMedia()) { %>
						<% for (PublicImageBlobBean publicImageBlobBean : sellDetailsForm.getMedia()) { %>
						<div class="fotoHelper" style="width:150px; height:150px; background-image:url(./downloadThumb.st?id=<%=publicImageBlobBean.getBlobid()%>&width=150&height=150&type=PUBLIC&ext=<%=publicImageBlobBean.getBlobExt()%>);"><a href="./downloadThumb.st?id=<%=publicImageBlobBean.getBlobid()%>&width=800&height=600&type=PUBLIC&ext=<%=publicImageBlobBean.getBlobExt()%>" rel="lightbox[gal]" title="<%=sellDetailsForm.getSellValueObject().getName()%>"><img src="images/null.gif" width="150" height="150" /></a></div>
						<% } %>
					<% } %>
				</div>
			</div>
		</div>
	</div>
	
	
</div>
</body>
</html>