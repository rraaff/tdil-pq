<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
<%@ include file="includes/head.jsp" %>
<script src="js/jquery.nivo.slider.pack.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/nivo-slider.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/nivo_theme/default.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
<style>
#linkToChanche a, #linkToChanche a:hover, #linkToChanche a:active, #linkToChanche a:visited {
	color:#FFFF33;
	font-size:12px;
	line-height:24px;
}
</style>
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
				$('#email').attr('value', '');
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
		<div id="logo"><img src="images/logo.gif" width="197" height="123" /></div>
		<div id="menu">
			<ul>
				<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
					<li>
						<% if (SectionType.RANKING_100.equals(section.getSectiontype())) { %>
							<a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html" style="padding:0; cursor:hand;"><img src="images/logoTop100.gif" width="214" height="123" /></a>
						<% } else { %>
							<% if (SectionType.VIDEOS.equals(section.getSectiontype())) { %>
								<a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewVideos.html"><%= section.getName() %></a>
							<% } else { %>
								<a href="<%=publicHomeBean.getExternalLink(section)%>"><%= section.getName() %></a>
							<% } %>
						<% } %>
					</li>
				<% } %>
				<li><a href="#" style="padding:0; cursor:default;"><img src="images/pronto-top20.gif" width="74"></a></li>
				<li><a href="#" style="padding:0; cursor:default;"><img src="images/pronto-shop.gif" width="62"></a></li>
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
		<div id="socialAtHome"><img src="images/null.gif" width="251" height="23"></div>
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
		
<!-- Cambio de pais -->
<div id="changeCountryDiv" class="hide">
	<% for (Country country : publicHomeBean.getAllCountries()) { %>
		<div id="linkToChanche"><a href="./selectCountry.st?id=<%=country.getId() %>")"><%=country.getName()%></a></div>
	<% } %>
</div>
	<!-- cargo el slider -->
	<script type="text/javascript">
	  $(document).ready(function(){
	   $('#slider').nivoSlider({
			effect: 'fold',  // Specify sets like: 'fold,fade,sliceDown');
			pauseTime: 30000
		});
		
		$("a[rel^='prettyPhoto']").prettyPhoto({
	    	hideflash: true,
	    	social_tools: false,
	    	theme: 'dark_rounded',
			allow_resize: false,
	    	markup: '<div class="pp_pic_holder"> \
						<div class="ppt">&nbsp;</div> \
						<div class="pp_top"> \
							<div class="pp_left"></div> \
							<div class="pp_middle"></div> \
							<div class="pp_right"></div> \
						</div> \
						<div class="pp_content_container"> \
							<div class="pp_left"> \
							<div class="pp_right"> \
								<div class="pp_content"> \
									<div class="pp_loaderIcon"></div> \
									<div class="pp_fade"> \
										<!-- a href="#" class="pp_expand" title="Expand the image">Expand</a--> \
										<div class="pp_hoverContainer"> \
											<a class="pp_next" href="#">next</a> \
											<a class="pp_previous" href="#">previous</a> \
										</div> \
										<div id="pp_full_res"></div> \
										<div class="pp_details"> \
											<div class="pp_nav"> \
												<a href="#" class="pp_arrow_previous">Previous</a> \
												<p class="currentTextHolder">0/0</p> \
												<a href="#" class="pp_arrow_next">Next</a> \
											</div> \
											<p class="pp_description"></p> \
											{pp_social} \
											<a class="pp_close" href="#">Close</a> \
										</div> \
									</div> \
								</div> \
							</div> \
							</div> \
						</div> \
						<div class="pp_bottom"> \
							<div class="pp_left"></div> \
							<div class="pp_middle"></div> \
							<div class="pp_right"></div> \
						</div> \
					</div> \
					<div class="pp_overlay"></div>'
	    });
	  });
    </script>
<% } else { %>
<body style="background-image:none;">
	<div id="BlockCoutrySelection">
		<div id="labelCountrySelection">Seleccione su pa&iacute;s / Selecione o Pa&iacute;s<br>Choisissez un pays / Select Country</div>
		<form action="./selectCountry.st">
			<div id="countryCombo">
				<select name="id" class="specialMargin">
					<option>Seleccione</option>
				<% 	publicHomeBean.initCountries();
					for (Country country : publicHomeBean.getAllCountries()) {%>
						<option value="<%= country.getId() %>"><%= country.getName() %></option>
				<% } %>
				</select>
			<!-- /div>
			<div id="buttonOkContainer" --><input type="submit" value="ok" class="okCircle"></div>
		</form>
	</div>
<% } %>     
</body>
</html>