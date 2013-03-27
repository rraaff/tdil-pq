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
<script>
$(document).ready(
	function(){

			$("input[name=fromDate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, minDate: "-100Y", maxDate: "+0D"});
			$("input[name=toDate]").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true,
				changeYear: true, minDate: "-100Y", maxDate: "+0D"});

			function categorySelected(categoryLabel, categoryValue) {
				$("input[name=categorySelectedText]").attr('value', categoryLabel);
				$("input[name=categoryAutocompleter]").attr('value','');
				$("#autocompleterdiv").css('display', 'none');
				$("#categorySelectedDiv").prop('innerHTML', categoryLabel);
				$("#categorySelectedDiv").css('display', 'block');
				$("input[name=categoryId]").attr('value', categoryValue);
				$("#addGeoLink").css('display', 'block');
			}
			$( "input[name=categoryAutocompleter]").autocomplete({
				source: function( request, response ) {
					$.ajax({
						url: "searchCategory.do",
						data: {
							name: request.term,
							type: function() {return $("input[name=categoryType]:checked").val(); }
						},
						dataType: "json",
						success: function( data ) {
							response( $.map( data, function( item ) {
								return {
									label: item.name,
									value: item.id
								}
							}));
						}
					});
				},
				minLength: 2,
				select: function( event, ui ) {
					if (ui.item) {
						categorySelected(ui.item.label, ui.item.value);
					}
					/*log( ui.item ?
						"Selected: " + ui.item.label :
						"Nothing selected, input was " + this.value);*/
				},
				open: function() {
					$( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
				},
				close: function() {
					$( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
				}
			});
		}

	
	);

function limpiarCategoria() {
	$("input[name=categoryAutocompleter]").attr('value','');
	$("input[name=categorySelectedText]").attr('value', '');
	$("#categorySelectedDiv").prop('innerHTML', '');
	$("#categorySelectedDiv").css('display', 'none');
	$("#autocompleterdiv").css('display', 'block');
	$("input[name=categoryId]").attr('value', '');
}

</script>
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
				<div class="myRow">
					<div class="myLabel width100">Categoria</div>
					<div class="ui-widget">
						<div class="myLabel width400">
						<html:hidden name="HighlightedCategoryForm" property="categoryId"/>
						<html:hidden name="HighlightedCategoryForm" property="categorySelectedText"/>
							<logic:equal name="HighlightedCategoryForm" property="categorySelected" value="false">
								<div id="autocompleterdiv">
								<html:radio name="HighlightedCategoryForm" property="categoryType" value="p" /> Producto&nbsp;&nbsp;&nbsp;<html:radio name="HighlightedCategoryForm" property="categoryType" value="s" /> Servicio
								<html:text name="HighlightedCategoryForm" property="categoryAutocompleter" styleClass="normalField width350" style="display: block;"/>
								</div>
								<div id="categorySelectedDiv" style="display: none;"></div>
							</logic:equal>
							<logic:equal name="HighlightedCategoryForm" property="categorySelected" value="true">
								<div id="autocompleterdiv" style="display: none;">
								<html:radio name="HighlightedCategoryForm" property="categoryType" value="p" /> Producto&nbsp;&nbsp;&nbsp;<html:radio name="HighlightedCategoryForm" property="categoryType" value="s" /> Servicio
								<html:text name="HighlightedCategoryForm" property="categoryAutocompleter" styleClass="normalField width350"/>
								</div>
								<div id="categorySelectedDiv" style="display: block;"><bean:write name="HighlightedCategoryForm" filter="false" property="categorySelectedText"/></div>
							</logic:equal>
						</div>
						<div class="myLabel width100">
							<a class="nonelyLink" id="cleanGeoLink" href="javascript:limpiarCategoria()">Limpiar</a>
						</div>
					</div>
				</div>
				<div class="renglon width950">					
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