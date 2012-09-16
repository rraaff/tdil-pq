<%@page import="com.tdil.tuafesta.struts.forms.PromotionForm"%>
<%@page import="com.tdil.utils.DateUtils"%>
<%@page import="com.tdil.tuafesta.model.Promotion"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="systemPropertyAdministration"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
<script>
$(document).ready(
	function(){
			$("input[name=startdate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2012"});
			$("input[name=enddate]").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true,
				changeYear: true, yearRange: "1900:2020"});
		}
	
	);

</script>
</head>

<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Administraci&oacute;n de Promotions</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde aquí podrá configurar las promociones.</span></div>
	</div>
	<html:form method="POST" action="/savePromotion">
	<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span>
		<div class="renglon width860">
			<div class="label width80">Nombre</div>
			<div class="label width200"><html:text name="PromotionForm" property="name"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.name_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width80">Descripcion</div>
			<div class="label width200"><html:text name="PromotionForm" property="description"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.description_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width50">Inicio</div>
			<div class="label width200"><html:text name="PromotionForm" property="startdate"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.startdate_key + ".err")%></div>
		</div>
		<div class="renglon width860">
			<div class="label width80">Fin</div>
			<div class="label width100"><html:text name="PromotionForm" property="enddate"/></div>
			<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, PromotionForm.enddate_key + ".err")%></div>
		</div>
		<div class="renglon width860" style="margin-bottom:20px;" align="center">
			<logic:equal name="PromotionForm" property="objectId" value="0">
				<html:submit property="operation">
					<bean:message key="save" />
				</html:submit>
			</logic:equal>
			<logic:notEqual name="PromotionForm" property="objectId" value="0">
				<html:submit property="operation">
					<bean:message key="modify" />
				</html:submit>
			</logic:notEqual>
			<html:submit property="operation">
				<bean:message key="reset" />
			</html:submit>
		</div>
	</html:form>
	<div class="renglon width860" style="margin-bottom:20px;">
		<table>
			<tr>
				<td class="headerTablas">Nombre</td>
				<td class="headerTablas">Inicio</td>
				<td class="headerTablas">Fin</td>
				<td class="headerTablas" width="60">Acciones</td>
			</tr>
			<logic:iterate name="PromotionForm" property="allPromotion"
				id="iterSystemProperty" indexId="iterIndex">
				<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
					<td
						<%=((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1 ? "class=\"notActive\""
							: ""%>
						align="left"><bean:write name="iterSystemProperty" property="name" />
					</td>
					<td><%=DateUtils.formatDate(((Promotion) iterSystemProperty).getStartdate()) %></td>
					<td><%=DateUtils.formatDate(((Promotion) iterSystemProperty).getEnddate()) %></td>
					<td align="center"><html:link action="/editPromotion" paramName="iterSystemProperty" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
						<html:link action="/toggleDeletedSystemProperty" paramName="iterSystemProperty"
							paramProperty="id" paramId="id">
							<% if (((com.tdil.ibatis.PersistentObject) iterSystemProperty).getDeleted() == 1) { %>
								<img src="boImages/activar.png" alt="Activar">
							<% } else { %>
								<img src="boImages/desactivar.png" alt="Desactivar">
							<% } %>
						</html:link>
					</td>
				</tr>
			</logic:iterate>
		</table>
	</div>
</div>
</body>
</html>