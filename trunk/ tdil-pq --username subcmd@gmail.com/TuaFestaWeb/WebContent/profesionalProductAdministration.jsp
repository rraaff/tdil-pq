<%@page import="com.tdil.tuafesta.utils.ProductCategoryUtils"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProductCategoryForm"%>
<%@page import="com.tdil.tuafesta.utils.ProductCategoryTreeNode"%>
<%@page import="com.tdil.tuafesta.model.ProfesionalProduct"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalProductForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="index"%>
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
			<html:form method="POST" action="/saveProfesionalProduct">
				<h1>Administraci&oacute;n de productos</h1>
				<div class="renglon width950">
					<div class="label width950"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				</div>
				<div class="renglon width950">
					<div class="label width100">Nombre</div>
					<div class="label width250"><html:text name="ProfesionalProductForm" property="name" styleClass="width250"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalProduct.name.err")%></div>
					<div class="label width80">Descripci&oacute;n</div>
					<div class="label width300"><html:text name="ProfesionalProductForm" property="description" styleClass="width300"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProfesionalProduct.description.err")%></div>
					<!-- div class="label width180">Precio promeio</div -->
					<div class="label width100"><html:checkbox name="ProfesionalProductForm" property="approved" />Aprobada</div>
				</div>
				<div class="renglon width950">
					<div class="label width100">Categor&iacute;a padre</div>
					<div class="label width800">
						<%
							ProfesionalProductForm form = (ProfesionalProductForm)session.getAttribute("ProfesionalProductForm");
							List<ProductCategoryTreeNode> listtemp = ProductCategoryForm.getProductCategoryTree();
							List<ProductCategoryTreeNode> flatten = ProductCategoryTreeNode.tree2list(listtemp);
						%>
						<html:select name="ProfesionalProductForm" property="categoryId" styleClass="textfield_effect width800">
							<option <%=form.getCategoryId() == 0 ? "selected" : ""%> value="0"></option>
							<%
								for (ProductCategoryTreeNode node : flatten) {
							%>
								<option <%=node.getProductCategory().getId().equals(form.getCategoryId()) ? "selected" : ""%> value="<%=node.getProductCategory().getId()%>"><%=ProductCategoryUtils.getPrefixFor(node)%><%=node.getProductCategory().getName()%></option>
							<%
								}
							%>
						</html:select>
					</div>
				</div>
				<div class="renglon width950 height50" align="center">
					<logic:equal name="ProfesionalProductForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="ProfesionalProductForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<h1>Listado de Productos</h1>
				<div class="renglon width950" style="margin-bottom:50px;">
					<table width="100%">
						<tr>
							<td class="headerTablas" width="140">Nombre</td>
							<td class="headerTablas" width="50">Acciones</td>
						</tr>
						<logic:iterate name="ProfesionalProductForm" property="allServices"
							id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td
									<%=((ProfesionalProduct) iterSection).getDeleted() == 1 ? "class=\"notActive\"" : "" %>
									align="left"><bean:write name="iterSection" property="name" />
								</td>
								<td>
									<html:link action="editProfesionalProduct.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
									<html:link action="/toggleDeletedProfesionalProduct" paramName="iterSection"
										paramProperty="id" paramId="id">
										<% if (((ProfesionalProduct) iterSection).getDeleted() == 1) { %>
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