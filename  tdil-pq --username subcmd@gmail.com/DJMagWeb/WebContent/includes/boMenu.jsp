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
      <li><html:link action="/goToCountryABM" >Manejo de paises</html:link>
      <li><html:link action="/goToSectionABM" >Manejo de secciones</html:link>
    </ul>
  </li>
  <li>
    <a href="#">App de a 2</a>
    <ul>
      <li><a href="boApp2Config.php">Configuracion de la applicacion</a></li>
      <li><a href="boApp2Stats.php">Estado de la applicacion</a></li>
      <li><a href="boApp2Search.php">Consultas</a></li>
    </ul>
  </li>
  <li>
    <html:link action="/logout" >Salir</html:link>
  </li>
</ul>