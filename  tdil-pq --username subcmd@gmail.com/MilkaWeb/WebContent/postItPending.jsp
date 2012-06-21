<%@page import="com.tdil.web.DisplayTagParamHelper"%>
<%@page import="com.tdil.milka.struts.forms.UrlUtils"%>
<%@page import="com.tdil.milka.model.valueobjects.PostItValueObject"%>
<%@page import="com.tdil.milka.struts.forms.PostItAdministrationForm"%>
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
div { /* border:dotted 1px #00CC00; */ }
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
.miniThmb {
	background-image: url(boImages/baseMini.jpg);
	background-repeat: no-repeat;
	background-position: center center;
}
.height79 { height:79px; }
.bigDemo {
	background-image: url(boImages/postitEjemplo.jpg);
	background-repeat: no-repeat;
	background-position: center center;
}
.height324 { height:324px; }
</style>
<script src="js/jquery.ajaxfileupload.js" type="text/javascript"></script>
<script>
$(document).ready(
	function(){
		$('#upload_thumb').ajaxfileupload({
		  	'action': './uploadPostItImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#img_thumb').attr('src', './viewPostItThumb.do');
		  	} else {
		  		alert("Ha ocurrido un error");
		  	}
		  },
		  'onCancel': function() {
		    console.log('no file selected');
		  }
		});
		$('#upload_cover').ajaxfileupload({
		  	'action': './uploadPostItImage.do',
		  'onComplete': function(response) {
		  	if (response.result == 'OK') {
		  		$('#img_cover').attr('src', './viewPostItImage.do');
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
</script>
</head>

<%
	PostItAdministrationForm postItAdministrationForm = (PostItAdministrationForm)session.getAttribute("PostItAdministrationForm");
java.util.List source = postItAdministrationForm.getSourceList();
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated);
%>
<body>
<div id="header"><%@ include file="includes/boMenu.jsp" %></div>
<div id="container">
	<h1 align="center">Experiencia POST ITs</h1>
	<div class="renglon width860" style="margin-bottom:20px;">
		<div class="label width860"><span class="comment">Desde esta secci&oacute;n podr&aacute; aprobar, modificar o desaprobar los contenidos de la experiencia POST ITs.</span></div>
	</div>
	<div class="renglon width860">
		<display:table name="test" sort="external" pagesize="10" id="testit" requestURI="./postItPending.jsp">
			<display:column title="fecha" sortable="true" sortName="fecha" headerClass="sortable" property="creationDateAsString"></display:column>
			<display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
			<display:column title="email" sortable="true" sortName="email" headerClass="sortable" property="email"></display:column>
			<display:column title="estado" sortable="true" sortName="estado" headerClass="sortable" property="statusRB"></display:column>
			<display:column title="acciones">
			<a href="./reviewPostIt.do?id=<%= ((PostItValueObject)pageContext.getAttribute("testit")).getId()%><%=DisplayTagParamHelper.getParams(request)%>">Revisar</a>
			</display:column>
		</display:table>
	</div>
	<logic:notEqual name="PostItAdministrationForm" property="objectId" value="0">
		<html:form method="POST" action="/approveDisapprovePostIt">
			<div class="renglon width860">
				<div class="label width100">Texto original</div>
				<div class="label width200"><span class="black"><bean:write name="PostItAdministrationForm" property="originaltext"/></span></div>
				<div class="label width50"></div>
				<div class="label width50">T&iacute;tulo</div>
				<div class="label width300"><html:text name="PostItAdministrationForm" property="title" style="width: 300px;"/></div>
			</div>
			<div class="renglon width860">
				<div class="label width100">Descripci&oacute;n</div>
				<div class="label width200"><html:text name="PostItAdministrationForm" property="description" style="width:200px;"/></div>
				<div class="label width50"></div>
				<div class="label width50">Color</div>
				<div class="label width200">
					<html:select name="PostItAdministrationForm" property="color" styleClass="width120">
						<logic:iterate name="PostItAdministrationForm" property="allColors" id="iterColor">
							<option	<%=	((String) iterColor).equals( postItAdministrationForm.getColor()) ? "selected" : ""%> value="<%=iterColor%>">&nbsp;&nbsp;&nbsp;<%=iterColor%></option>
						</logic:iterate>
					</html:select>
				</div>
			</div>
			<div class="renglon width860">
				<div class="label width80">URL</div>
				<div class="label width200"><bean:write name="PostItAdministrationForm" property="urlLink"/><a href="./goToLinkTargetSelectionFromPostIt.do">Selecci&oacute;n de link a otra experiencia</a> - <a href="./clearLinkTargetPostIt.do"><b>Borrar link</b></a></div>
				<div class="label width50"></div>
				<div class="label width80">Target</div>
				<div class="label width300">
					<html:select name="PostItAdministrationForm" property="urlTarget" >
						<% for (String iterTarget : UrlUtils.getAllTargets()) { %>
							<option	<%=	(iterTarget).equals( postItAdministrationForm.getUrlTarget()) ? "selected" : ""%> value="<%=iterTarget%>">&nbsp;&nbsp;&nbsp;<%=iterTarget%></option>
						<% } %>
					</html:select>
				</div>
			</div>
			<div class="renglon width860 height79">
				<div class="label width80 height79">Miniatura </br><span class="comment">79px x 79px</span></div>
				<div class="label width80 height79 miniThmb">
					<logic:equal name="PostItAdministrationForm" property="hasThumb" value="true">
						<img id="img_thumb" src="./viewPostItThumb.do" width="79" height="79" align="absmiddle" title="ACTUAL">
					</logic:equal>
				</div>
				<div class="label width50"></div>
				<div class="label width80 height79 miniThmb">
					<logic:notEqual name="PostItAdministrationForm" property="hasThumb" value="true">
						<img id="img_thumb" src="boImages/na.gif" width="79" height="79" align="absmiddle" title="NUEVA">
					</logic:notEqual>
				</div>
				<div class="label width50"></div>
				<div class="label width200">
					<input type="file" name="upload_thumb" id="upload_thumb"><%=MilkaErrorFormatter.getErrorFrom(request, "PostItAdministrationForm.thumb.err")%>
				</div>
			</div>
			<div class="renglon width860 height324">
				<div class="label width100 height324">Postit</br><span class="comment">348px x 324px</span></div>
				<div class="label width180 height180">
					<logic:equal name="PostItAdministrationForm" property="hasCover" value="true">
						<img id="img_cover" src="./viewPostItImage.do" width="174" height="162" align="absmiddle"> 
					</logic:equal>
				</div>
				<div class="label width20"></div>
				<div class="label width350 height324 bigDemo">
					<logic:notEqual name="PostItAdministrationForm" property="hasCover" value="true">
						<img id="img_cover" src="boImages/na.gif" width="348" height="324" align="absmiddle"> 
					</logic:notEqual>
				</div>
				<div class="label width200">
					<input type="file" name="upload_cover" id="upload_cover">
					<br/><%=MilkaErrorFormatter.getErrorFrom(request, "PostItAdministrationForm.cover.err")%>
				</div>
			</div>
			<div class="renglon width860 height60" align="center">
				<html:submit property="operation">
					<bean:message key="approve" />
				</html:submit>
				<html:submit property="operation">
					<bean:message key="disapprove" />
				</html:submit>
			</div>
		</html:form>
	</logic:notEqual>
</div>
</body>
</html>