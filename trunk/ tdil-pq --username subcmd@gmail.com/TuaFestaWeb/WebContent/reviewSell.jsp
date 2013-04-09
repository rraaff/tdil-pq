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
<script>
$(document).ready(
	function(){
		$( "#canceldisapprove" ).click(function() {
			$( "#disapprovelayer" ).fadeOut();
		});
	}
);
function disapprove() {
	$window = $(window);
    var top = ($window.height() / 2) - ($( "#disapprovelayer" ).height() / 2);
    var left = ($window.width() / 2) - ($( "#disapprovelayer" ).width() / 2);
	$( "#disapprovelayer" ).css({
		position: 'absolute',
		top: top + 'px',
		left: left + 'px'
	}).fadeIn(500);
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
	<html:form method="POST" action="/approveSell">
		<div class="myRow width350" style="margin:0 auto; float:none;">
			<div class="myLabel width50" style="padding-top:16px;"><a href="reviewSellData.jsp">Volver</a></div>
			<div class="myLabel width300">
				<% if (!reviewProfesionalForm.isCurrentSellApproved()) { %>
				<html:submit property="operation">
					<bean:message key="Approve" />
				</html:submit>
				<input type="button" value="Desaprobar" onClick="disapprove();">
				<% } %>
				<html:submit property="operation">
					<bean:message key="Block" />
				</html:submit>
			</div>
		</div>
	</html:form>
	
</div>

<div id="disapprovelayer" class="hide" style="z-index: 500;">
	<html:form method="POST" action="/disapproveSell">
		<div id="Motivo"><html:text name="ReviewProfesionalForm" property="disapproveMotive" styleClass="normalField"/></div>
		<div id="buttonHolder" align="center"><input type="submit" value="Desaprobar"/><input type="button" id="canceldisapprove" value="Cancelar"></div>
		<!-- div id="buttonMeArrepentiHolder"><a href="postits.jsp" title="Volver a Postits"><img src="images/experiencias/postits/boton_me-arrepenti_out.png" width="160" height="52" /></a></div-->
	</html:form>
</div>
</body>
</html>