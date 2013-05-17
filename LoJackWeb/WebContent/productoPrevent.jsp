<%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%>
<%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%>
<%@page import="com.tdil.lojack.struts.forms.CameraForm"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %><!--
--><%@ include file="includes/mustBePreventUser.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/reset-styles" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="js/bootstrap.min.js"></script>

<%@ include file="includes/headLogged.jsp" %>


<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" />
<link href="css/index_modales.css" rel="stylesheet" type="text/css" />
<link href="css/index_social.css" rel="stylesheet" type="text/css" />
<link href="css/copyright.css" rel="stylesheet" type="text/css" />
<style type="text/css">
#productsMenu ul li.tabParking {
	background:#f05224;
}
.smallmap {
	width: 968px;
	height: 450px;
}
#tags { display: none; }
#docs p {
	font-size:12px;
    margin-bottom: 0.5em;
}
/* mobile specific */
@media only screen and (max-width: 600px) {
    body {
        height           : 100%;
        margin           : 0;
        padding          : 0;
        width            : 100%;
    }
    #map {
        background : #7391ad;
        width      : 100%;
    }
    #map {
        border : 0;
        height : 250px;
    }
    #title {
        font-size   : 1.3em;
        line-height : 2em;
        text-indent : 1em;
        margin      : 0;
        padding     : 0;
    }
    #docs {
        bottom     : 0;
        padding    : 1em;
    }
    #shortdesc {
        color      : #aaa;
        font-size  : 0.8em;
        padding    : 1em;
        text-align : right;
    }
    #tags {
        display : none;
    }
}
@media only screen and (orientation: landscape) and (max-width: 600px) {
    #shortdesc {
       float: right;
       width: 25%;
    }
    #map {
        width: 70%;
    }
    #docs {
        font-size: 12px;
    }
}
</style>
<script src="js/OpenLayers.js" type="text/javascript"></script>
<script src="js/MapaOSM.js" type="text/javascript"></script>
<script type="text/javascript">

		var startLat = -34.53483581543;
		var startLon = -58.548202514648;
		var endLat = -34.685211181641;
		var endLon = -58.344268798828;

        var Mapa;
        var parkings;
        var origenGeoRef;
        var POI;
        var currentPopup;
        var MyPos;
        var SearchMeters;

        $(function () {
            var mapOptions = {
                DataProjection: "EPSG:4326"
            };
            Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
            Mapa.UpdateConfig({ title: "Prevent" });
            Mapa.SetParameters("toolbar=off&Lat=-34.655504&Lon=-58.471677&Width=84&LayersViewWidth=0");

            $("input[cl]").each(function(indice,valor) {
         	   $(valor).click(function() {
         		   $( "#" + $(this).attr('cl') ).fadeOut();
         		});
         	});
        });

    	function editMaxSpeed() {
  		  $('#editMaxSpeed').load('goToVehiculesSpeedLimits.do', function() {
  			  centerLayer($(window), $( "#editMaxSpeedLayer" ));
  			});
  	  }

       function editVehiclesPhones() {
   		  $('#editVehiclesPhones').load('goToVehiculesForPhone.do', function() {
   			  centerLayer($(window), $( "#editVehiclesPhonesLayer" ));
   			});
   	   }
    	

  	  function centerLayer(objWin, objLayer) {
  			var top = (objWin.height() / 2) - (objLayer.height() / 2);
  			var left = (objWin.width() / 2) - (objLayer.width() / 2);
  			objLayer.css({
  				position: 'absolute',
  				top: top + 'px',
  				left: left + 'px'
  			}).fadeIn(500);
  		}
</script>

<style type="text/css">
#productsMenu ul li.tabCar {
	background:#f05224;
}
button.iconBackHome,
button.iconMaxSpeed,
button.iconZSeguras,
button.iconGetPosit,
button.iconPhoneAdm {
	border:none;
	background: transparent;
	background: url(images/skin_lj_rl/webApp/car/control_home_32x32.png);
	background-repeat: no-repeat;
	background-position: 0 0;
	width: 32px;
	height: 32px;
	margin: 0 10px;
	padding: 0;
	cursor: pointer;
}
button.iconMaxSpeed { background: url(images/skin_lj_rl/webApp/car/control_maxSpeed_32x32.png); }
button.iconZSeguras { background: url(images/skin_lj_rl/webApp/car/control_zSeguras_32x32.png); }
button.iconGetPosit { background: url(images/skin_lj_rl/webApp/car/control_getPosit_32x32.png); }
button.iconPhoneAdm { background: url(images/skin_lj_rl/webApp/car/control_phoneAdm_32x32.png); }
</style>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper" style="height:460px; background:#000;">
		<div id="mapContainer" class="smallmap"></div>
	</div>
</section>
<section id="controls">
	<div class="basicControls">
		<button class="iconBackHome" href="./goToVehiculesSpeedLimits.do">&nbsp;</button>
		<button class="iconMaxSpeed" onclick="editMaxSpeed();">&nbsp;</button>
		<button class="iconZSeguras" href="./goToVehiculesSecureZones.do">&nbsp;</button>
		<button class="iconGetPosit" href="./goToVehiculesForMap.do">&nbsp;</button>
		<button class="iconPhoneAdm" onclick="editVehiclesPhones();">&nbsp;</button>
	</div>
</section>
<section id="zoomSection">
	<div class="zoomControls">
		<button class="icon_zoom_in" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">&nbsp;</button>
		<button class="icon_zoom_out" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">&nbsp;</button>
	</div>
</section>

<!-- edit max speed -->
<div id="editMaxSpeedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="editMaxSpeed">
		Consultando datos...
	</div>
</div>
<div id="editVehiclesPhonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="editVehiclesPhones">
		Consultando datos...
	</div>
</div>

<%@ include file="includes/footerProductoHome.jsp" %>

</body>
</html>