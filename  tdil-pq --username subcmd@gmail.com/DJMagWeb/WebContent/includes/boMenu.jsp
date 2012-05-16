<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script type="text/javascript">
  jQuery(function(){ 
    jQuery('.jbar').jbar();
  });
</script>

<ul class="jbar">
   <li>
    <a href="#">Administraci&oacute;n</a>
    <ul>
      <li><html:link action="/goToCountryABM" >Manejo de paises</html:link></li>
      <li><html:link action="/goToSectionABM" >Manejo de secciones</html:link></li>
      <li><html:link action="/goToBannerABM" >Manejo de banners</html:link></li>
    </ul>
  </li>
  <li>
    <a href="#">Artículos</a>
    <ul>
      <li><html:link action="/goToRankingABM" >Manejo de rankings</html:link></li>
      <li><html:link action="/goToNoteABM" >Manejo de notas</html:link></li>
      <li><html:link action="/goToVideoABM" >Manejo de videos</html:link></li>
      <li><html:link action="/goToImageGalleryABM" >Manejo de galer&iacute;as de fotos</html:link></li>
      <li><html:link action="/goToBannerPositionABM" >Asignaci&oacute;n de banners a secciones</html:link></li>
      <li><html:link action="/goToMagazineABM" >Manejo de magazine</html:link></li>
      <li><html:link action="/goToTwitterFeedABM" >Manejo de feeds de Twitter</html:link></li>
      <li><html:link action="/goToFacebookFeedABM" >Manejo de feeds de Facebook</html:link></li>
       <li><html:link action="/goToFooterABM" >Manejo de footers</html:link></li>
    </ul>
  </li>
  <li>
    <a href="#">Reportes</a>
    <ul>
      <li><html:link action="/goToNewsletterReport" >Subscriptos al newsletter</html:link></li>
    </ul>
  </li>
  <li>
    <html:link action="/logout" >Salir</html:link>
  </li>
</ul>