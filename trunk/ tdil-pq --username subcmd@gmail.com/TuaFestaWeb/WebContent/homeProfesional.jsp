<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.utils.StringUtils"%>
<%@page import="com.tdil.tuafesta.utils.LocalizationUtils"%>
<%@page import="com.tdil.tuafesta.model.valueobjects.SellValueObject"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Profesional"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalHomeForm"%>
<%@page import="com.tdil.tuafesta.struts.forms.ClientHomeForm"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="homeCliente"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | Profesionales (Mi cuenta)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<style>
#formSection {
	width:920px;
}
</style>
</head>
<body>
<%@ include file="includes/designHeader.jspf" %>
<% ProfesionalHomeForm profesionalHomeForm = (ProfesionalHomeForm)session.getAttribute("ProfesionalHomeForm"); 
	Profesional profesional = profesionalHomeForm.getProfesional();
%>
<div id="divisionHeaderBody"></div>
<div id="preContainer">
	<div id="content">
		<!-- aca arranca el formulario -->
		<div id="titleArea">
			<h1>Datos de <%=profesionalHomeForm.getProfesional().getFirstname() %></h1>
			<h2>Desde esta secci&oacute;n podr&aacute;s ver y acceder a modificar todos tus datos, agregar productos o servicios, mantener contacto con los cliente a trav&eacute;s del muro y determinar tu nivel de disponibilidad para prestar servicios en tu agenda.</h2>
		</div>
		<div id="formContent">
			<div id="formSection">
				<div style="width:450px; margin-right:20px; float:left;">
					<h2><legend>Datos personales <%= profesionalHomeForm.isPersonalDataChanged() ? "*" : "" %></legend></h2>
					<div class="myRow">
						<div class="myLabel width450"><strong><%= profesional.getFirstname() %> <%= profesional.getLastname() %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width450">Tel&eacute;fono <strong>[<%= profesional.getPhonetype() %>] (<%= profesional.getPhoneareacode() %>) <%= profesional.getPhonenumber() %> <%= profesional.getPhoneextension() == null ? "" : "- " + profesional.getPhoneextension() %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width450">Sexo <strong><%= profesional.getSex().equals("m") ? "Masculino" : "Femenino" %></strong></div>
					</div>
					<div class="myRow"><a href="./goToEditProfesionalPersonalData.do?id=<%=profesional.getId()%>">Editar Datos personales</a></div>
					<div class="myRow"><a href="./goToChangePassword.do?id=<%=profesional.getId()%>">Cambiar clave de acceso</a></div>
				</div>
				<div style="width:450px; float:left;">
					<h2><legend>Datos profesionales <%= profesionalHomeForm.isBusinessDataChanged() ? "*" : "" %></legend></h2>
					<div class="myRow">
						<div class="myLabel width450"><strong><%= profesional.getBusinessname() %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width210">CUIT <strong><%= profesional.getCuit() != null ? profesional.getCuit() : "" %></strong></div>
						<div class="myLabel width20">&nbsp;</div>
						<div class="myLabel width210">IIBB <strong><%= profesional.getIibb() != null ? profesional.getIibb() : "" %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width210">Facebook 
							<% if (profesional.getFacebook() != null) { %>
								<a href="<%=StringUtils.asURL(profesional.getFacebook())%>"><strong><%= profesional.getFacebook() != null ? profesional.getFacebook() : "" %></strong></a>
							<% } %>
						</div>
					</div>
					<div class="myRow">
						<div class="myLabel width210">Website <strong><%= profesional.getWebsite() != null ? profesional.getWebsite() : "" %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width210">Horario <strong><%= profesional.getBusinesshours() != null ? profesional.getBusinesshours() : "" %></strong></div>
					</div>
					<div class="myRow">
						<div class="myLabel width450">Localizado en <strong><%= profesionalHomeForm.getLocation().getNombre4()%>, <%= profesionalHomeForm.getLocation().getNombre3()%>, <%= profesionalHomeForm.getLocation().getNombre2()%></strong></div>
					</div>
					<div class="myRow"><a href="./goToEditProfesionalBusinessData.do?id=<%=profesional.getId()%>">Editar Datos Profesionales</a></div>
				</div>
				<div class="width100per" style="float:left;">
					<h2><legend>Productos / Servicios</legend></h2>
					<div class="myRow">
						<div class="myLabel width450">Agrega todos los productos/servicios que quieras, luego desde ac&aacute; podr&aacute;s editarlos o eliminarlos. <br /><a href="./goToEditProfesionalProducts.do?id=<%=profesional.getId()%>">Editar Productos</a> - <a href="./goToEditProfesionalServices.do?id=<%=profesional.getId()%>">Editar Servicios</a></div>
						<div class="myLabel width450">En &aacute;reas de cobertura, agrega las zonas donde brindas servicios, as&iacute; te ahorraras tiempo en contestarle a clientes de otras zonas.<br /><a href="./goToEditProfesionalServiceArea.do?id=<%=profesional.getId()%>">Ver/Editar &aacute;reas de cobertura</a></div>
					</div>
					<div class="myRow">
						<%
						java.util.List source = profesionalHomeForm.getSells();
						com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
						request.setAttribute( "sells",  paginated);
						%>
						<display:table name="sells" sort="external" pagesize="10" id="sells" requestURI="./homeProfesional.jsp">
							<display:column title="Nombre" sortable="true" sortName="Producto" headerClass="sortable width350" property="name"></display:column>
							<display:column title="Tipo" sortable="true" sortName="Tipo" headerClass="sortable width50" property="sellTypeDescription"></display:column>
							<display:column title="Categoria" sortable="true" sortName="Categoria" headerClass="sortable width350" property="categoryText"></display:column>
							<display:column title="Precio" sortable="true" sortName="precio" headerClass="sortable width50">
								<%= LocalizationUtils.formatPrice(((SellValueObject)pageContext.getAttribute("sells")).getReferenceprice())%>
							</display:column>
							<display:column title="Estado" sortable="true" sortName="Estado" headerClass="sortable width50">
								<%= ((SellValueObject)pageContext.getAttribute("sells")).getStatusText()%>
							</display:column>
						</display:table>
						<%=DisplayTagParamHelper.getFields(request)%>
					</div>
				</div>
				<div style="width:450px; margin-right:20px; float:left;">
					<h2><legend>Muro</legend></h2>
					<div class="myRow">A trav&eacute;s del muro podr&aacute;s interactuar con tus clientes, o simplemente subir alg&uacute;n tipo de informaci&oacute;n que te interese. Siempre que alguien te escriba te aparecer&aacute; en "Mi cuenta" (en la secci&oacute;n Muro), pero hasta que no le contestes no aparecer&aacute; para que los dem&aacute;s lo vean. Si te escriben algo que no corresponde simplemente no le contestes, o borra el comentario.<br /><br />Mensajes pendientes de moderaci&oacute;n: <%=profesionalHomeForm.getWallModerationPendingCount() %> <a href="./goToModerateProfesionalWall.do?id=<%=profesional.getId()%>">Ir al muro</a></div>
				</div>
				<div style="width:450px; float:left;">
					<h2><legend>Agenda</legend></h2>
					<div class="myRow"><a href="./goToEditAgenda.do?id=<%=profesional.getId()%>">Editar agenda</a></div>
				</div>
				<!-- aca Termina el formulario -->
			</div>
		</div>
	</div>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>