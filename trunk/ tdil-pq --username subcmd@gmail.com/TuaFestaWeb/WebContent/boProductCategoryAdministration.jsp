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
		<h1>Administraci&oacute;n de categor&iacute;as de Productos</h1>
		<div id="formulariosBase">
			<html:form method="POST" action="/saveProductCategory">
				<div class="renglon"><span class="errorText"><%=TuaFestaErrorFormatter.getErrorFrom(request, "general")%></span></div>
				<div class="renglon">
					<div class="label width130">Nombre de categor&iacute;a</div>
					<div class="label width780"><html:text name="CategoryForm" property="name" styleClass="normalField width740"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, CategoryForm.name_key + ".err")%></div>
				</div>
				<div class="renglon">
					<div class="label width130">Descripci&oacute;n</div>
					<div class="label width780 height100"><html:textarea name="CategoryForm" property="description" styleClass="normalField width740 height100"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, CategoryForm.description_key + ".err")%></div>
				</div>
				<div class="renglon">
					<div class="label width20"><html:checkbox name="CategoryForm" property="showinhome"/></div>
					<div class="label width130">Mostar en la home</div>
					<div class="label width20">&nbsp;</div>
					<div class="label width60">Posici&oacute;n</div>
					<div class="label width100"><html:text name="CategoryForm" property="homeindex" styleClass="normalField width50"/>&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, CategoryForm.homeindex_key + ".err")%></div>
					<div class="label width100">Categor&iacute;a padre</div>
					<div class="label width450">
						<%
							CategoryForm form = (CategoryForm) session.getAttribute("CategoryForm");
								List<CategoryTreeNode> listtemp = form.getCategoryTree();
								List<CategoryTreeNode> flatten = CategoryTreeNode.tree2list(listtemp);
						%>
						<html:select name="CategoryForm" property="parentId" styleClass="normalField width440">
							<option <%=form.getParentId() == 0 ? "selected" : ""%> value="0"></option>
							<%
								for (CategoryTreeNode node : flatten) {
							%>
								<option <%=node.getCategory().getId().equals(form.getParentId()) ? "selected" : ""%> value="<%=node.getCategory().getId()%>"><%=TreeCategoryUtils.getPrefixFor(node)%><%=node.getCategory().getName()%></option>
							<%
								}
							%>
						</html:select>
						&nbsp;<%=TuaFestaErrorFormatter.getErrorFrom(request, "ProductCategory.parentId.err")%></div>
				</div>
				<div class="renglon height50" align="center">
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
				<h2>Listado de Categorias de Productos</h2>
				<div class="renglon" style="overflow:auto; padding-top:15px;">
					<table width="100%">
						<tr>
							<td class="headerTablas" width="700">Nombre</td>
							<td class="headerTablas" width="150">Acciones</td>
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