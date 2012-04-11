<%@ taglib uri="/WEB-INF/struts-html" prefix="html" %>
<script type="text/javascript">
  jQuery(function(){ 
    jQuery('.jbar').jbar();
  });
</script>

<ul class="jbar">
   <li>
    <a href="#">App 10x10=17</a>
    <ul>
      <li><a href="boApp1Config.php">Configuracion de la applicacion</a></li>
      <li><a href="boApp1Stats.php">Estado de la applicacion</a></li>
      <li><a href="boApp1Search.php">Consultas</a></li>
      <li><html:link action="/goToCountryABM" >goToCountryABM</html:link>
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