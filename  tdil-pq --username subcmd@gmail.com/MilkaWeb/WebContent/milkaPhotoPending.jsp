<%@page import="com.tdil.milka.model.valueobjects.MilkaPhotoValueObject"%>
<%@page import="com.tdil.milka.struts.forms.MilkaPhotoAdministrationForm"%>
<%@page import="com.tdil.milka.web.MilkaErrorFormatter"%>
<%@ page info="index"%>
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

	<style>
	.ui-autocomplete-loading { background: white url('css/images/ui-anim_basic_16x16.gif') right center no-repeat; }
	</style>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
	$(document).ready(
		function(){
			$('#upload_email').ajaxfileupload({
			  	'action': './uploadMilkaPhotoReplacement.do',
			  'onComplete': function(response) {
			  	if (response.result == 'OK') {
			  		$('#img_to_approve').attr('src', './viewMilkaPhotoReplacement.do');
			  		// puedo capturar el fin?
			  	} else {
			  		alert("Ha ocurrido un error");
			  	}
			  },
			  'onCancel': function() {
			    console.log('no file selected');
			  }
			});
		}
	);
	
	$(function() {
		function split( val ) {
			return val.split( /,\s*/ );
		}
		function extractLast( term ) {
			return split( term ).pop();
		}

		$("input[name=tags]")
			// don't navigate away from the field on tab when selecting an item
			.bind( "keydown", function( event ) {
				if ( event.keyCode === $.ui.keyCode.TAB &&
						$( this ).data( "autocomplete" ).menu.active ) {
					event.preventDefault();
				}
			})
			.autocomplete({
				source: function( request, response ) {
					$.getJSON( "./searchTags.do", {
						term: extractLast( request.term )
					}, response );
				},
				search: function() {
					// custom minLength
					var term = extractLast( this.value );
					if ( term.length < 2 ) {
						return false;
					}
				},
				focus: function() {
					// prevent value inserted on focus
					return false;
				},
				select: function( event, ui ) {
					var terms = split( this.value );
					// remove the current input
					terms.pop();
					// add the selected item
					terms.push( ui.item.value );
					// add placeholder to get the comma-and-space at the end
					terms.push( "" );
					this.value = terms.join( "," );
					return false;
				}
			});
	});
	</script>
</head>

<%
	MilkaPhotoAdministrationForm milkaPhotoAdministrationForm = (MilkaPhotoAdministrationForm)session.getAttribute("MilkaPhotoAdministrationForm");
java.util.List source = milkaPhotoAdministrationForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<display:table name="test" sort="external" pagesize="10" id="testit">
  <display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable" property="creationDateAsString"></display:column>
  <display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
  <display:column title="email" sortable="true" sortName="email" headerClass="sortable" property="email"></display:column>
  <display:column title="estado" sortable="true" sortName="estado" headerClass="sortable" property="statusRB"></display:column>
  <display:column title="acciones">
  	<a href="./reviewMilkaPhoto.do?id=<%= ((MilkaPhotoValueObject)pageContext.getAttribute("testit")).getId()%>">Revisar</a>
  </display:column>
</display:table>
<logic:notEqual name="MilkaPhotoAdministrationForm" property="idBlobData" value="0">
<img id="img_to_review" width="200" height="200" src="./download.st?id=<bean:write name="MilkaPhotoAdministrationForm" property="idBlobData"/>&type=PUBLIC&ext=<bean:write name="MilkaPhotoAdministrationForm" property="extBlobData"/>" alt="">
<img id="img_to_approve" src="./viewMilkaPhotoReplacement.do" width="200" height="200" align="absmiddle"> 
<input type="file" name="upload_email" id="upload_email">Reemplazar<br>
<html:form method="POST" action="/approveDisapproveMilkaPhoto">
	<html:checkbox name="MilkaPhotoAdministrationForm" property="frontcover" /> Portada<br>
	Tags: <html:text name="MilkaPhotoAdministrationForm" property="tags" style="width: 300px;"/><br>
	<html:submit property="operation">
		<bean:message key="approve" />
	</html:submit>
	<html:submit property="operation">
		<bean:message key="disapprove" />
	</html:submit>
</html:form>
<script>
$(document).ready(
	function(){
		//alert($('#img_to_review').prop('width'));
		//$('#img_to_review').resize({maxWidth: '200', maxHeight: '200'});
	}
	
);

$(window).load(function() {
      $('#img_to_review').resize({maxWidth: '200', maxHeight: '200'});
      $('#img_to_approve').resize({maxWidth: '200', maxHeight: '200'});
});
</script>
</logic:notEqual>

</body>
</html>