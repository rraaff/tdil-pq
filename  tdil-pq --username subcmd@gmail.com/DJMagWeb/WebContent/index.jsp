<%@page import="com.tdil.djmag.model.SectionType"%>
<%@page import="com.tdil.djmag.model.Country"%>
<%@page import="com.tdil.djmag.model.Section"%>
<jsp:useBean id="publicHomeBean" scope="session" class="com.tdil.djmag.web.beans.PublicHomeBean"/>
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>

<html>
<head>
<link href="css/style.css" rel="stylesheet" type="text/css">
<%@ include file="includes/head.jsp" %>
<script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/nivo_theme/default.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
<script src="js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>
<script>

	function setAsTopVideo(divId) {
		$("#topvideo").prop("innerHTML", $("#"+divId).prop("innerHTML"));
	}
$(document).ready(
	function(){
	
	function generateTooltips() {
	  //make sure tool tip is enabled for any new error label
		$("img[id*='error']").tooltip({
			showURL: false,
			opacity: 0.99,
			fade: 150,
			positionRight: true,
				bodyHandler: function() {
					return $("#"+this.id).attr("hovertext");
				}
		});
		//make sure tool tip is enabled for any new valid label
		$("img[src*='tick.gif']").tooltip({
			showURL: false,
				bodyHandler: function() {
					return "OK";
				}
		});
	}
	
	$("#newsletterForm").mouseover(function(){
		      generateTooltips();
		    });
	
	$("#newsletterForm").validate({
			errorPlacement: function(error, element) {
				error.appendTo( element.parent("td").next("td") );
			},
			rules: { email: {required: true}
			},
			messages: { email: {required: "<img id='codigoerror' src='img/unchecked.gif' hovertext='Ingrese el email.' />"}
			},
			submitHandler: function() {
	            $('#newsletterForm').ajaxSubmit({
	    			type: "POST",
	    			url: "./subscribeToNewsletter.do",
	    			dataType: "json",
	    			success: postSubscrition
	    			});
	        }
		});
		
		function postSubscrition(jsonData) {
			if (jsonData.result == 'OK') {
				$.jGrowl('Gracias por subscribirte');
			} else {
				$.jGrowl('Ha ocurrido un error, intentelo nuevamente');
			}
		}
	}
);
</script>
</head>
<% if (publicHomeBean.hasCountrySelected()) { %>
<body>
<a name="top"></a>
<div id="portaHeader">
	<div id="header">
		<div id="logo"></div>
		<div id="menu">
			<ul>
				<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
					<li>
						<% if (SectionType.RANKING_100.equals(section.getSectiontype())) { %>
							<a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[ranking_menu]"><%= section.getName() %></a>
						<% } else { %>
							<% if (SectionType.VIDEOS.equals(section.getSectiontype())) { %>
								<a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[videos_menu]"><%= section.getName() %></a>
							<% } else { %>
								<a href="#"><%= section.getName() %></a>
							<% } %>
						<% } %>
					</li>
				<% } %>
			</ul>
		</div>
	</div>
</div>
<%@ include file="includes/homeBannerTop.jsp" %>
<div id="BlockMain">
<%@ include file="includes/homeFrontCovers.jsp" %>
<%@ include file="includes/homeRanking.jsp" %>
	<div id="BlockMainRight">
		<div id="selectedCountry"><%= publicHomeBean.getCountry().getName() %><a href="#changeCountryDiv" rel="prettyPhoto[change_country]"><img id="changeCountryLink" src="images/buttons/closeCountry.gif" width="18" height="18" align="absbottom"></a></div>
		<div id="socialAtHome"><img src="images/demo/social.png" width="251" height="23"></div>
		<%@ include file="includes/homeNewsletter.jsp" %>
		<%@ include file="includes/homeMagazine.jsp" %>
		<%@ include file="includes/homeBannerRight.jsp"%>
	</div>
</div>
<div id="BlockSecondaryContent">
	<div id="leftContent">
		<%@ include file="includes/homeLastNotesCover.jsp" %>
		<%@ include file="includes/homeLastNotes.jsp" %>
		<h2>ultimos videos</h2>
		<%@ include file="includes/homeVideos.jsp" %> 
	</div>
	<div id="rightContent">
		<%@ include file="includes/homeAgenda.jsp" %>
		<%@ include file="includes/homeTwitter.jsp" %>
		<%@ include file="includes/homeFacebook.jsp" %>
	</div>
</div>
<%@ include file="includes/homeFooter.jsp" %> 
<div id="changeCountryDiv" class="hide">
	<% for (Country country : publicHomeBean.getAllCountries()) { %>
			<a href="./selectCountry.st?id=<%=country.getId() %>")"><%=country.getName()%></a><br>
	<% } %>
</div>
	<!-- cargo el slider -->
	<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider({
			effect: 'fold' // Specify sets like: 'fold,fade,sliceDown');
		});
    });
	  $(document).ready(function(){
	    $("a[rel^='prettyPhoto']").prettyPhoto({
	    	hideflash: true,
	    	social_tools: false,
	    	theme: 'dark_rounded'
	    });
	    
		<% String action = request.getParameter("action");
		if ("viewNote".equals(action)) { %>
			$.prettyPhoto.open('<%=publicHomeBean.getExternalLink(request.getParameter("date"), request.getParameter("webTitle"))%><%=PublicHomeBean.LIGTH_BOX_PARAMS%>','<%=request.getParameter("webTitle")%>','<%=request.getParameter("webTitle")%>');
		<% } else { 
			if ("viewRanking".equals(action)) { %>
				$.prettyPhoto.open('./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>','Ranking','Ranking');
			<% } else { 
				if ("viewVideos".equals(action)) { %>
					$.prettyPhoto.open('./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>','Videos','Videos');
				<% }
			}
		}
		%>
	  });
    </script>
<% } else { %>
<body style="background-image:none;">
	<div id="BlockCoutrySelection">
		<div id="labelCountrySelection">Seleccione su pa&iacute;s / Selecione o Pa&iacute;s<br>Choisissez un pays / Select Country</div>
		<div id="countryCombo">
			<form action="./selectCountry.st">
			<select name="id"><option>Seleccione</option>
			<% 	publicHomeBean.initCountries();
				for (Country country : publicHomeBean.getAllCountries()) {%>
					<option value="<%= country.getId() %>"><%= country.getName() %></option>
			<% } %>
			</select>&nbsp;&nbsp;&nbsp;
			<input type="submit" value="ok" class="okCircle">
			</form>
		</div>
	</div>
<% } %>     
</body>
</html>