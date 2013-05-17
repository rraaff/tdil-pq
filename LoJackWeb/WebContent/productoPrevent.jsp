<%@page import="com.tdil.lojack.utils.LoJackConfig"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%>
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
<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForMapForm");%>
<script type="text/javascript">
		var startLat = -34.53483581543;
		var startLon = -58.548202514648;
		var endLat = -34.685211181641;
		var endLon = -58.344268798828;

		var Mapa;
		var parkings;
		var currentPopup;
        var POI;
        var MyPos;
        var SearchMeters;

        $(function () {
        	<% if ("1".equals(request.getParameter("showinmap")) && selectVehiclesForm != null) { %>
	        	var popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
	                "autoSize": true,
	                "minSize": new OpenLayers.Size(300, 50),
	                "maxSize": new OpenLayers.Size(500, 300),
	                "keepInMap": true
	            });
	
	        	function createMarker(lon, lat, title, desc, proj, icon) {
	        		var point = new OpenLayers.LonLat(lon,lat);
	        		point.transform(proj, Mapa.map.getProjectionObject());
	        		var marker = new OpenLayers.Marker(point,icon.clone());
	        		var feature = new OpenLayers.Feature(parkings, point);
	        	    feature.closeBox = true;
	        	    feature.popupClass = popupClass;
	        	    feature.data.popupContentHTML = '<div style="background-color:#003366;color: White">' + title + '<br>' + desc +'</div>';
	        	    feature.data.overflow = "auto";
	
	        	    var markerClick = function(evt) {
	        	        if (currentPopup != null && currentPopup.visible() && currentPopup != this.popup) {
	        	            currentPopup.hide();
	        	        }
	        	        if (this.popup == null) {
	        	            this.popup = this.createPopup(this.closeBox);
	        	            Mapa.map.addPopup(this.popup);
	        	            this.popup.show();
	        	        } else {
	        	            this.popup.toggle();
	        	        }
	        	        currentPopup = this.popup;
	        	        OpenLayers.Event.stop(evt);
	        	    };
	        	    marker.events.register("mousedown", feature, markerClick);
	        		return marker;
	        	}
	
	            var mapOptions = {
	                DataProjection: "EPSG:4326"
	            };
	            Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
	            Mapa.UpdateConfig({ title: "Prevent" });
	            Mapa.SetParameters("toolbar=off&Lat=<%=selectVehiclesForm.getSelectedVehiclePosition().getLatitude()%>&Lon=<%=selectVehiclesForm.getSelectedVehiclePosition().getLongitude()%>&Width=84&LayersViewWidth=0&zoom=15");
	
	            parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(21,25);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/car.png',size,offset);
	        	var proj = new OpenLayers.Projection("EPSG:4326");
	        	var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/car.png',size,offset);
	        	parkings.addMarker(createMarker(<%=selectVehiclesForm.getSelectedVehiclePosition().getLongitude()%>,<%=selectVehiclesForm.getSelectedVehiclePosition().getLatitude()%>, '<%=selectVehiclesForm.getSelected().getDescription()%>', '<%=selectVehiclesForm.getSelectedVehiclePosition().getStreet()%>-<%=selectVehiclesForm.getSelectedVehiclePosition().getNumber()%>', proj, iconCar.clone()));
        	<% } else { %>
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
	         <% } %>
        });

    	function editMaxSpeed() {
  		  $('#editMaxSpeed').load('goToVehiculesSpeedLimits.do', function() {
  			  centerLayer($(window), $( "#editMaxSpeedLayer" ));
  			});
  	  }

   	function editSecureZones() {
   		  $('#editSecureZones').load('goToVehiculesSecureZones.do', function() {
   			  centerLayer($(window), $( "#editSecureZonesLayer" ));
   			});
   	  }
    	

       function selectVehiclesPhones() {
   		  $('#selectVehiclesPhones').load('goToVehiculesForPhone.do', function() {
   			  centerLayer($(window), $( "#selectVehiclesPhonesLayer" ));
   			});
   	   }

   	   function selectVehiclesForMap() {
   		 $('#selectVehiclesForMap').load('goToVehiculesForMap.do', function() {
 			  centerLayer($(window), $( "#selectVehiclesForMapLayer" ));
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
		<button class="iconZSeguras" onclick="editSecureZones();">&nbsp;</button>
		<button class="iconGetPosit" onclick="selectVehiclesForMap();">&nbsp;</button>
		<button class="iconPhoneAdm" onclick="selectVehiclesPhones();">&nbsp;</button>
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
<div id="editSecureZonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="editSecureZones">
		Consultando datos...
	</div>
</div>
<div id="selectVehiclesPhonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="selectVehiclesPhones">
		Consultando datos...
	</div>
</div>
<div id="editVehiclesPhonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="editVehiclesPhones">
		Consultando datos...
	</div>
</div>
<div id="selectVehiclesForMapLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="selectVehiclesForMap">
		Consultando datos...
	</div>
</div>

<%@ include file="includes/footerProductoHome.jsp" %>

</body>
</html>