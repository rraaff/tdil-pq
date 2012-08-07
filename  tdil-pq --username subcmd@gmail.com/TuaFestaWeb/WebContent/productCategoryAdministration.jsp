<%@page import="com.tdil.tuafesta.utils.ProductCategoryUtils"%>
<%@page import="com.tdil.tuafesta.utils.ProductCategoryTreeNode"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProductCategoryForm"%>
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
<div id="header"></div>
<div id="container">
	<div style="height:50px; display:block;"><%@ include file="includes/boMenu.jsp"%></div>
	<div id="formulariosBase">
		<html:form method="POST" action="/saveProductCategory">
			<h1>Administraci&oacute;n de categorias de Productos</h1>
			<div id="conteinerScrollable" style="overflow:hidden;">
				<span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span>
				<div class="renglon width500 height50">
					<div class="label width180">Nombre</div>
					<div class="label width200"><html:text name="ProductCategoryForm" property="name" styleClass="width180"/></div>
					<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProductCategory.name.err")%></div>
					<div class="label width180">Descripcion</div>
					<div class="label width200"><html:text name="ProductCategoryForm" property="description" styleClass="width180"/></div>
					<div class="label width50"><%=TuaFestaErrorFormatter.getErrorFrom(request, "ProductCategory.description.err")%></div>
				</div>
				
				<%
									ProductCategoryForm form = (ProductCategoryForm) session.getAttribute("ProductCategoryForm");
											List<ProductCategoryTreeNode> listtemp = ProductCategoryForm.getProductCategoryTree();
											List<ProductCategoryTreeNode> flatten = ProductCategoryTreeNode.tree2list(listtemp);
								%>
				<html:select name="ProductCategoryForm" property="parentId" styleClass="textfield_effect">
					<option <%=form.getParentId() == 0 ? "selected" : ""%> value="0"></option>
					<%
						for (ProductCategoryTreeNode node : flatten) {
					%>
						<option <%=node.getProductCategory().getId().equals(form.getParentId()) ? "selected" : ""%> value="<%=node.getProductCategory().getId()%>"><%=ProductCategoryUtils.getPrefixFor(node)%><%=node.getProductCategory().getName()%></option>
					<%
						}
					%>
				</html:select>
				<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProductCategory.parentId.err")%>
				<div class="renglon height40">
					<logic:equal name="ProductCategoryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="ProductCategoryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<!-- hr -->
				<div id="fiftyfiftyRight">
					<h2>Listado de Categorias de Productos</h2>
					<div class="renglon width450 height300" style="overflow:auto;">
						<table>
							<tr>
								<td class="headerTablas" width="140">Nombre</td>
								<td class="headerTablas" width="50">Acciones</td>
							</tr>
							<logic:iterate name="ProductCategoryForm" property="allProductCategory"
								id="iterSection" indexId="iterIndex">
								<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
									<td
										<%=((ProductCategoryTreeNode) iterSection).getProductCategory().getDeleted() == 1 ? "class=\"notActive\"" : ""%>
										align="left"><%=ProductCategoryUtils.getPrefixFor((ProductCategoryTreeNode) iterSection)%><bean:write name="iterSection" property="name" />
									</td>
									<td>
										<html:link action="editProductCategory.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
										<html:link action="/toggleDeletedProductCategory" paramName="iterSection"
											paramProperty="id" paramId="id">
											<%
												if (((ProductCategoryTreeNode) iterSection).getProductCategory().getDeleted() == 1) {
											%>
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
		</html:form>
	</div>
</div>
</body>
</html>