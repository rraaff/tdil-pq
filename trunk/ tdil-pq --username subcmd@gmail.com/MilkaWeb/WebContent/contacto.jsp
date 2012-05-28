<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page info="index"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Milka</title>
<link href="css/home-styles.css" rel="stylesheet" type="text/css" />
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
					positionRight: true ,
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
			
			$('form').mouseover(function(){
				      generateTooltips();
				    });
		
			$("form[name='ContactForm']").validate({
					errorPlacement: function(error, element) {
						error.appendTo( element.parent("td").next("td") );
					},
					rules: { 'name': {required: true},
							'email': {required: true, email: true},
							'content': {required: true}
					},
					messages: {
						'name': {required: "<img id='nameerror' src='images/unchecked.gif' hovertext='Ingrese el nombre.' />"}, 
						'email': {required: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese el email.' />",
								email: "<img id='emailerror' src='images/unchecked.gif' hovertext='Ingrese un email valido.' />"},
						'content': {required: "<img id='politicserror' src='images/unchecked.gif' hovertext='Ingrese el contenido.' />"}
					},
					submitHandler: function() {
			            $("form[name='ContactForm']").ajaxSubmit({
			    			type: "POST",
			    			url: "./contact.do",
			    			dataType: "json",
			    			success: postContact
			    			});
			        }
				});
		}
	);

function postContact(data) {
	if (data.result == 'OK') {
		$( "#dialog-modal" ).dialog({
				height: 140,
				modal: true,
				close: function(event, ui) {  document.location.href='./index.jsp'; }
			});
	} else {
		$( "#dialog-modal-err" ).dialog({
				height: 140,
				modal: true
			});
	}
}
</script>
</head>

<body>

<div id="content">
	<div id="header">
		<div id="logo"><a href="index.jsp" title="Milka"></a></div>
		<div id="box">            
		<div id="subi"><a href="#" id="subifotomilka" title="SUB&Iacute; tu foto con chocolate"></a></div>
		<div id="social">
			<ul>
				<li class="faqs"><a href="faqs.jsp" title="FAQS"></a></li>
				<li class="t"><a href="#" title="t"><img src="images/t.png" width="22" height="22" alt="t" border="none" /></a></li>
				<li class="facebook"><a href="#" title="Facebook"><img src="images/facebook.png" width="22" height="22" alt="Facebook" border="none" /></a></li>
				<li class="twitter"><a href="#" title="Twitter"><img src="images/twitter.png" width="22" height="22" alt="Twitter" border="none" /></a></li>
				<li class="youtube"><a href="#" title="Youtube"><img src="images/youtube.png" width="22" height="22" alt="Youtube" border="none" /></a></li>
			</ul>
		</div>
		<!-- end social-->
	</div>
	<!-- end box-->
</div>
<!-- end header-->
<div id="menu-wrapper">
	<div id="menu">
		<ul>
			<li><a href="productos_alfajores.jsp" title="Productos" class="">Productos</a></li>
			<li><a href="historia.jsp" title="Historia" class="">Historia</a></li>
			<li class="fin"><a href="#" title="Contacto" class="activo">Contacto</a></li>
		</ul>
	</div>
	<!-- end menu -->
</div>
<!-- end menu-wrapper-->
    
<div id="wrapper2-internas">
	 
    <div id="col_derecha_contacto">
    	<h2>Seguinos en...</h2>
        <ul>        
            <li class=""><a href="#" title="Twitter" class="activo"><img src="images/twitter-bloque.png" width="209" height="152" alt="Twitter" /></a>
            </li>
          	<li class=""><a href="#" title="Facebook" class="activo"><img src="images/facebook-bloque.png" width="209" height="152" alt="Facebook" /></a>
            </li>           
            
      </ul>
    </div>
    <!-- end col_derecha-->
    

	<div id="titulo-historia">
	<h2>Contacto <span class="violeta">Milka</span></h2>	
    </div>
    <!-- end titulo-->
    
    <div id="formulario">
    	<html:form method="POST" action="/contact">
		<table border="0" align="center" cellpadding="0" cellspacing="0" id="formulario_contacto">
		  <tr>
		    <td width="57" align="left"><label for="nombre">Nombre</label></td>
		    <td width="450" align="left">
		     <html:text name="ContactForm" property="name" styleClass="input_form"/> </td>
		  </tr>
		  
		  <tr>
		    <td align="left"><label for="email">E-Mail</label></td>
		    <td align="left">
		      <html:text name="ContactForm" property="email" styleClass="input_form"/></td>
		  </tr>
		 
		  <tr>
		    <td align="left" valign="top"><label for="consulta">Consulta</label></td>
		    <td align="left"> <html:textarea name="ContactForm" property="content" styleClass="comment_form" cols="45" rows="5" /></td>
		  </tr>
		 
		  <tr>
		    <td height="50" align="right"></td>
		    <td height="50" align="right"><html:submit property="operation">ENVIAR</html:submit></td>
		  </tr>
		  </table>
       </html:form>
    </div>
    <!-- end formulario-->
    
</div>

<div id="dialog-modal" class="hide" title="Contacto">
	Gracias por su mensaje.
</div>
<div id="dialog-modal-err" class="hide" title="Contacto">
	Ha ocurrido un error, intentelo nuevamente.
</div>
<!-- end wrapper2-->
	<%@ include file="includes/footer.jsp" %>