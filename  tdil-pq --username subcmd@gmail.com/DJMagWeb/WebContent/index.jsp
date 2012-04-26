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
<script>
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
			alert(jsonData.result);
			//alert("Gracias por subscribirte");
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
		<div id="logo" style="border-left: 1px solid #386061;"></div>
		<div id="menu">
			<ul>
				<% for (Section section : publicHomeBean.getSectionsForCountry()) { %>
					<li><a href="#"><%= section.getName() %></a></li>
				<% } %>
			</ul>
		</div>
	</div>
</div>
<div id="bannerHeader"><img src="images/banners/bannerHeader.gif" width="980" height="91"></div>
<div id="BlockMain">
<%@ include file="includes/homeFrontCovers.jsp" %>
<%@ include file="includes/homeRanking.jsp" %>
	<div id="BlockMainRight">
		<div id="selectedCountry"><%= publicHomeBean.getCountry().getName() %><a href="#"><img src="images/buttons/closeCountry.gif" width="18" height="18" align="absbottom"></a></div>
		<div id="socialAtHome"><img src="images/demo/social.png" width="251" height="23"></div>
		<%@ include file="includes/homeNewsletter.jsp" %>
		<div id="bannerRight"><img src="images/banners/bannerRight.gif" width="250" height="268"></div>
	</div>
</div>
<div id="BlockSecondaryContent">
	<div id="leftContent">
		<%@ include file="includes/homeLastNotes.jsp" %>
		<div id="BlockPopularNews">
			<div id="lastNews" style="float:left; margin-right:14px;">
				<img src="images/demo/popNoticias.jpg" width="200" height="143">
				<h3>DJ Mag Top 100 party 2012</h3>
				<div class="bajada"><p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto.</p><p>&nbsp;</p><p>sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, cov</p></div>
				<div class="date">03 de mayo 2012</div>
			</div>
			<div id="lastNews" style="float:left; margin-right:14px;">
				<img src="images/demo/popNoticias.jpg" width="200" height="143">
				<h3>DJ Mag Top 100 party 2012</h3>
				<div class="bajada"><p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto.</p><p>&nbsp;</p><p>sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, cov</p></div>
				<div class="date">03 de mayo 2012</div>
			</div>
			<div id="lastNews" style="float:left;">
				<img src="images/demo/popNoticias.jpg" width="200" height="143">
				<h3>DJ Mag Top 100 party 2012</h3>
				<div class="bajada"><p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto.</p><p>&nbsp;</p><p>sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, cov</p></div>
				<div class="date">03 de mayo 2012</div>
			</div>
		</div>
		<h2>ultimos videos</h2>
		<%@ include file="includes/homeVideos.jsp" %> 
		<div id="BlockVideos">
			<div id="left"><img src="images/demo/videoGrande.jpg" width="425" height="297"></div>
			<div id="right">
				<div id="thmbnVideo" style="background-image:url(images/demo/videoChico1.jpg);">
					<div id="base">
						<h4>Solomun</h4>
						<span>DJ Mag Recession Sessions</span>
					</div>
				</div>
				<div id="thmbnVideo" style="background-image:url(images/demo/videoChico2.jpg); margin-top:5px;">
					<div id="base">
						<h4>Solomun</h4>
						<span>DJ Mag Recession Sessions</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="rightContent">
		<%@ include file="includes/homeAgenda.jsp" %>
		<%@ include file="includes/homeTwitter.jsp" %>
		<%@ include file="includes/homeFacebook.jsp" %>
	</div>
</div>
<div id="footer">
	<div id="upButton"><a href="#top"><img src="images/buttons/up.png" width="67" height="67"></a></div>
	<div id="linksContainer">
		<div id="BlockLinks" style="width:200px;">
			<h4>LINKS</h4>
			<a href="#">DJ Mag ES</a>
			<a href="#">DJ Mag COM</a>
			<a href="#">Blog DJ Magazine</a>
		</div>
		<div id="BlockLinks" style="width:410px;">
			<h4>Temas populares</h4>
			<a href="#">Red Bull Thre3Style, ¿Quién hará arder la pista?</a>
			<a href="#">The White Passion - Club 25 Presenta: Space Ibiza Festival @ Aqualand Water Park (Mallorca)</a>
			<a href="#">New Wave Club</a>
			<a href="#">ECO en Matadero Madrid</a>
			<a href="#">Wally Lopez en directo desde room mate</a>
			<a href="#">Waldorf Towers</a>
		</div>
		<div id="BlockLinks">
			<h4>secciones</h4>
			<a href="#">Novedades</a>
			<a href="#">Musica</a>
			<a href="#">Top 100</a>
			<a href="#">Tecnologia</a>
			<a href="#">Guide</a>
			<a href="#">Shop</a>
			<a href="#">Forum</a>
			<a href="#">Agencia</a>
		</div>
		<div id="BlockLinks">
			<h4>SOCIALICEMOS</h4>
			<div id="iconLink">
				<a href="#"><img src="images/buttons/fb.gif" alt="facebook" width="35" height="35" align="absmiddle">Facebook</a>
				<a href="#"><img src="images/buttons/tw.gif" alt="Twitter" width="35" height="35" align="absmiddle">Twitter</a>
				<a href="#"><img src="images/buttons/yt.gif" alt="Youtube" width="35" height="35" align="absmiddle">Youtube</a>
				<a href="#"><img src="images/buttons/feed.gif" alt="feed" width="35" height="35" align="absmiddle">feed</a>
			</div>
		</div>
	</div>
	<div id="copyright">DJ Mag&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;Todos los derechos reservados&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;Aviso Legal&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;© DJ Mag - <a href="mailto:info@djmag.com.ar">info@djmag.com.ar</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;+54 11 4444-6666</div>
</div>
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