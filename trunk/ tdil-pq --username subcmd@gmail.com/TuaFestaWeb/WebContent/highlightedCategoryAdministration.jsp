<%@page import="com.tdil.tuafesta.utils.ProductCategoryUtils"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProductCategoryForm"%>
<%@page import="com.tdil.tuafesta.utils.ProductCategoryTreeNode"%>
<%@page import="com.tdil.tuafesta.model.HighlightedCategory"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.HighlightedCategoryForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="highlightedCategoryAdministration"%>
<%@ page contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html"%>
<%@ include file="includes/checkBoLogin.jsp"%>
<html>
<head>
<%@ include file="includes/boHead.jsp"%>
<%@ include file="includes/boErrorJS.jsp"%>
</head>

<body>
<%@ include file="includes/boMenu.jsp"%>
<div id="boWrapper">
	<div id="boCentral">
		<div id="formulariosBase">
			<html:form method="POST" action="/saveHighlightedCategory">
				<h1>Administraci&oacute;n de Categorias destacadas</h1>
				<div class="renglon width950">
					<div class="label width950"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				</div>
				<div class="renglon width950">
					<div class="label width100">Categoria</div>
					<div class="label width250">To do</div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedCategory.v.err")%></div>
					<div class="label width100">Inicio</div>
					<div class="label width250"><html:text name="HighlightedCategoryForm" property="fromDate" styleClass="width250"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedCategory.fromDate.err")%></div>
					<div class="label width100">Fin</div>
					<div class="label width250"><html:text name="HighlightedCategoryForm" property="toDate" styleClass="width250"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "HighlightedCategory.toDate.err")%></div>
				</div>
				<div class="renglon width950 height50" align="center">
					<logic:equal name="HighlightedCategoryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="HighlightedCategoryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<h1>Listado de Categorias destacadas</h1>
				<div class="renglon width950" style="margin-bottom:50px;">
					<table width="100%">
						<tr>
							<td class="headerTablas" width="140">Categoria</td>
							<td class="headerTablas" width="140">Desde</td>
							<td class="headerTablas" width="140">Hasta</td>
							<td class="headerTablas" width="50">Acciones</td>
						</tr>
						<logic:iterate name="HighlightedCategoryForm" property="allHighlightedCategories"
							id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td
									<%=((HighlightedCategory) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="categoryName" />
								</td>
								<td
									<%=((HighlightedCategory) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="formatedfromdate" />
								</td>
								<td
									<%=((HighlightedCategory) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="formatedtodate" />
								</td>
								<td>
									<html:link action="editHighlightedCategory.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
									<html:link action="/toggleDeletedHighlightedCategory" paramName="iterSection"
										paramProperty="id" paramId="id">
										<% if (((HighlightedCategory) iterSection).getDeleted() == 1) { %>
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
			</html:form>
		</div>
	</div>
</div>
</body>
</html>