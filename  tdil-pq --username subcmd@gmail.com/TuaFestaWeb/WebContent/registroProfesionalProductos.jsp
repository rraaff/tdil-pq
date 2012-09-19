<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.tdil.tuafesta.model.PhoneType"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.SellBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ServiceAreaBean"%>
<%@page import="com.tdil.tuafesta.struts.forms.beans.ProductBean"%>
<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.tuafesta.model.Geo4"%>
<%@page import="com.tdil.tuafesta.model.Geo3"%>
<%@page import="com.tdil.tuafesta.model.Geo2"%>
<%@page import="com.tdil.tuafesta.struts.forms.ProfesionalForm"%>
<%@page import="com.tdil.tuafesta.web.TuaFestaErrorFormatter"%>
<%@ include file="includes/userLogged.jspf" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page info="registroProfesional"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Tua Festa | R008-M1- Registro - Registro Profesional Normal (paso 1)</title>
<meta name="keywords" content="Tua Festa">
<meta name="description" content="Bienvenidos a Tua Festa" />
<%@ include file="includes/head.jsp" %>
<link href="images/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script>
$(document).ready(
	function(){

		var prodcats = new Array();

		var selectChange = function() {
			var selValue = Number($(this).attr('value'));
			var changedLevel = Number($(this).attr('level'));
			var i;
			for (i = changedLevel; i < prodcats.length; i++) {
				if (prodcats[i] != null) {
					prodcats[i].remove();
				}
			}
			for (i = prodcats.length - 1; i > changedLevel; i--) {
				prodcats[i] = null;
			}
			var tdnextlevel = $('<td></td>').appendTo( $('#prod_cat_tr') );

			$.ajax({
	            type: "GET",
	            cche: false,
	            url: "./searchProductCategories.do",
	            data: {parent: selValue },
	            contentType: "application/json; charset=utf-8",
	            success: function(msg) {
					if (msg.length > 0) {
						//alert(msg.length);
		               var select = $('<select id="pcl-'+ (changedLevel + 1)+'" size="10" style="width: 120px;" level="'+ (changedLevel + 1)+'"></select>').appendTo(tdnextlevel);
			   			/*$('<option value="1">uno</option>').appendTo(select);
			   			$('<option value="2">dos</option>').appendTo(select);
			   			$('<option value="0">otros</option>').appendTo(select);*/
			   			prodcats[changedLevel] = tdnextlevel;
			   			select.change(selectChange);
		                $.each(msg, function(index, item) {
			                $('<option value="'+item.id+'">'+item.name+'</option>').appendTo(select);
		                	//select.options[select.options.length] = new Option(item.id, );
		                });
					}
	            },
	            error: function() {
	                alert("error consultando los productos");
	            }
	        });
			
			
		}
			
		$('#pcl-0').change(selectChange);
		
		/*var tr = $('<tr></tr>').appendTo( $('#image_gal_tab') );
  		var tdpos = $('<td align="center">' + (maxindex + 1) + '</td>').appendTo( tr );
  		if (maxindex == 0) {
	  		var tdportada = $('<td align="center">SI</td>').appendTo( tr );
  		} else {
  			var tdportada = $('<td align="center"></td>').appendTo( tr );
  		}*/
			
		}
	);
</script>
<%@ include file="includes/boErrorJS.jsp" %>
</head>
<body>
<div style="width: 100%; overflow-x:auto;">
	<table id="prod_cat_tbl">
		<tr id="prod_cat_tr">
			<td>
				<select id="pcl-0" size="10" style="width: 120px;" level="0">
					<option value="1">Consumibles</option>
					<option value="0">Otros</option>
				</select>
			</td>
		</tr>
	</table>
</div>
<!-- % @ include file="includes/fbShare.jsp" %-->
<%@ include file="includes/footer.jsp" %>
</body>
</html>