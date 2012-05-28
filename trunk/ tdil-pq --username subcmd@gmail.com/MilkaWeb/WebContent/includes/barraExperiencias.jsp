<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style>
/* END RESET */
html, body {
	height: 100%;
}
body {
	margin:0;
}
a {color:#535353; text-decoration:none}
#bm_BarraContainer {
	width:100%;
	background:url(images/barra/barra.png) no-repeat center top;
}
#bm_content{
	width:1000px;
	margin:0 auto;
	position:relative;
}
#bm_header {
	clear:both;
	width:1000px;
	height:56px;
	margin:0 auto;
	position:relative;
	z-index:100px;
}
#bm_header #bm_logo a{
	float:left;
	width:134px;
	height:56px;
	background:url(images/barra/milka-logo.png) no-repeat;
	position:relative;
	top:0px;
	left:0px;
	margin-left: 50px;
}
#bm_header #bm_controlador{
	float:left;
	width:150px;
	height:29px;
	position:relative;
	top:8px;
	left:185px;
}
#bm_header #bm_controlador a.left{
		float:left; 
		width:20px;
		height:29px;
		background:url(images/barra/arrow-left.png) no-repeat;
		position:relative;
		top:0;
		margin:0;
		left:0px;
}
#bm_header #bm_controlador span{
		float:left; 
		width:110px;
		height:29px;
		position:relative;
		margin:0;
		font-family: 'Sue Ellen Francisco', cursive;
		font-size:14px;
		color:#FFF;
		text-align:center;
}
#bm_header #bm_controlador a.right{
		float:right; 
		width:20px;
		height:29px;
		background:url(images/barra/arrow-right.png) no-repeat;
		position:relative;
		top:0;
		margin:0;
		left:0px;
}
#bm_header #bm_social{
	float:left;
	width:65px;
	height:19px;
	position:relative;
	top:10px;
	margin: 0px;
	padding: 0px;
}
#bm_header  #bm_social  img  {
	margin-right: 2px;
	margin-left: 2px;
}
#bm_header #bm_me_derrite a{
	float:right;
	width:108px;
	height:41px;
	background:url(images/barra/me-derrite.png) no-repeat;
	position:relative;
	margin-right: 50px;
}
#bm_header #bm_personas{
	float:left;
	width:180px;
	height:20px;
	position:relative;
	color:#FFF;
	font-family:Arial, Helvetica, sans-serif;
	font-size:11px;
	top:14px;
	left: 280px;
}
#bm_header #bm_personas span{
	color:#b398ff;
}
</style>
<div id="bm_BarraContainer">
	<div id="bm_content">
		<div id="bm_header">
			<div id="bm_logo"><a href="index.jsp" title="Volver a la home de Milka"></a></div>
			<!-- end logo-->
			<div id="bm_social"><a href="#" class="facebook" title="Facebook"><img src="images/barra/facebook.png" width="17" height="17" alt="Facebook" /></a><img src="images/barra/separador.gif" width="1" height="21" /><a href="#" class="twitter" title="Twitter"><img src="images/barra/twitter.png" width="17" height="17" alt="Twitter" /></a></div>
			<!-- end social-->
			<div id="bm_controlador"><a href="<%out.println(prevPage);%>" class="left"></a><span>PROB&Aacute; M&Aacute;S</span><a href="<%out.println(nextPage);%>" class="right"></a></div>
			<!-- end controlador-->
			<%= MeltButton.meltButton(barClickCounter) %>
			<!-- end me_derrite-->
		</div>
		<!-- end header-->
	</div>
</div>