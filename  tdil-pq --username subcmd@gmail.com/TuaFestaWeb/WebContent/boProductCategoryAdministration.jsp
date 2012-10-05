<%@page import="com.tdil.tuafesta.utils.TreeCategoryUtils"%>
<%@page import="com.tdil.tuafesta.utils.CategoryUtils"%>
<%@page import="com.tdil.tuafesta.utils.CategoryTreeNode"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.tuafesta.struts.forms.CategoryForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ page info="productCategoryAdministration"%>
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
			<html:form method="POST" action="/saveProductCategory">
				<h1>Administraci&oacute;n de categorias de Productos</h1>
				<div class="renglon width950">
					<div class="label width950"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				</div>
				<div class="renglon width950">
					<div class="label width100">Nombre</div>
					<div class="label width300"><html:text name="CategoryForm" property="name" styleClass="width300"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, CategoryForm.name_key + ".err")%></div>
					<div class="label width80">Descripci&oacute;n</div>
					<div class="label width350"><html:text name="CategoryForm" property="description" styleClass="width350"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, CategoryForm.description_key + ".err")%></div>
				</div>
				<div class="renglon width950">
					<div class="label width100">Mostar en la home</div>
					<div class="label width300"><html:checkbox name="CategoryForm" property="showinhome" styleClass="width300"/></div>
					<div class="label width80">Indice</div>
					<div class="label width350"><html:text name="CategoryForm" property="homeindex" styleClass="width350"/></div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, CategoryForm.homeindex_key + ".err")%></div>
				</div>
				<div class="renglon width950">
					<div class="label width100">Categor&iacute;a padre</div>
					<div class="label width800">
						<%
							CategoryForm form = (CategoryForm) session.getAttribute("CategoryForm");
								List<CategoryTreeNode> listtemp = form.getCategoryTree();
								List<CategoryTreeNode> flatten = CategoryTreeNode.tree2list(listtemp);
						%>
						<html:select name="CategoryForm" property="parentId" styleClass="textfield_effect width800">
							<option <%=form.getParentId() == 0 ? "selected" : ""%> value="0"></option>
							<%
								for (CategoryTreeNode node : flatten) {
							%>
								<option <%=node.getCategory().getId().equals(form.getParentId()) ? "selected" : ""%> value="<%=node.getCategory().getId()%>"><%=TreeCategoryUtils.getPrefixFor(node)%><%=node.getCategory().getName()%></option>
							<%
								}
							%>
						</html:select>
					</div>
					<div class="label width50">&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProductCategory.parentId.err")%></div>
				</div>
				<div class="renglon width950 height50" align="center">
					<logic:equal name="CategoryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="save" />
						</html:submit>
					</logic:equal>
					<logic:notEqual name="CategoryForm" property="objectId" value="0">
						<html:submit property="operation">
							<bean:message key="modify" />
						</html:submit>
					</logic:notEqual>
					<html:submit property="operation">
						<bean:message key="reset" />
					</html:submit>
				</div>
				<h1>Listado de Categorias de Productos</h1>
				<div class="renglon width950" style="margin-bottom:50px;">
					<table width="100%">
						<tr>
							<td class="headerTablas" width="140">Nombre</td>
							<td class="headerTablas" width="50">Acciones</td>
						</tr>
						<logic:iterate name="CategoryForm" property="allCategory"
							id="iterSection" indexId="iterIndex">
							<tr class="<%=(iterIndex % 2 == 0) ? "d0" : "d1"%>">
								<td
									<%=((CategoryTreeNode) iterSection).getCategory().getDeleted() == 1 ? "class=\"notActive\"" : ""%>
									align="left"><%=TreeCategoryUtils.getPrefixFor((CategoryTreeNode) iterSection)%><bean:write name="iterSection" property="name" />
								</td>
								<td>
									<html:link action="editProductCategory.st?" paramName="iterSection" paramProperty="id" paramId="id"><img src="boImages/editar.png" alt="Editar"></html:link>
									<html:link action="/toggleDeletedProductCategory" paramName="iterSection"
										paramProperty="id" paramId="id">
										<%
											if (((CategoryTreeNode) iterSection).getCategory().getDeleted().equals(1)) {
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