<%@page import="com.tdil.lojack.utils.LoJackConfig"%>
<%@page import="com.tdil.lojack.utils.LoJackWebUtils"%>
<%@page import="java.util.List"%>
<%@page import="com.tdil.lojack.utils.ParkingUtils"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%>
<%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%>
<%@page import="com.tdil.lojack.model.PointOfInterest"%>
<%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><!--
--><%@ page info="home"%><!--
--><%@ page contentType="text/html; charset=ISO-8859-1" %><!--
--><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><!--
--><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><!--
--><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><!--
--><%@ include file="includes/checkThalamusUp.jspf" %><!--
--><%@ include file="includes/userLogged.jspf" %><!--
--><%@ include file="includes/mustBeLogged.jspf" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<!-- link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" /> -->

<%@ include file="includes/headLogged.jsp" %>

<link href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen">
<link href="css/index_modales.css" rel="stylesheet"  type="text/css"/>
<link href="css/index_social.css" rel="stylesheet"  type="text/css"/>
<link href="css/copyright.css" rel="stylesheet"  type="text/css"/>

<style>
/**
 * Map Examples Specific
 */
.smallmap {
    width: 968px;
    height: 450px;
    border: 1px solid #ccc;
}
#tags {
    display: none;
}

#docs p {
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
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>



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


        function removeParkings() {
        	if (currentPopup != null && currentPopup.visible()) {
                currentPopup.hide();
            }
        	currentPopup = null;
            if (parkings) {
        		Mapa.map.removeLayer(parkings);
        		parkings = null;
            }
        }
        var popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
            "autoSize": true,
            "minSize": new OpenLayers.Size(300, 50),
            "maxSize": new OpenLayers.Size(500, 300),
            "keepInMap": true
        });

        function showAllParkings() {
        	removeParkings();
        	if (MyPos) {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=11");
            }
            if (!parkings) {
	        	parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(21,25);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/marker.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				<% List<PointOfInterest> parkings = ParkingUtils.getParkings(); %>
				<% for (PointOfInterest poi : parkings) { %>
					parkings.addMarker(createMarker(<%=poi.getLon()%>,<%=poi.getLat()%>, '<%=poi.getName()%>', '<%=poi.getDescription()%>', proj, icon.clone()));
				<% } %>
            }
		}

        function showParkings(meters) {
        	SearchMeters = meters;
        	removeParkings();
            if (!MyPos) { // si no le pedi la posicion, voy por el circuito de pedido de posicion
				getLocationAndShowParkings();
            } else {
            	showParkingsForPos(MyPos);
            }
		}

        function getLocationAndShowParkings() {
       	  if (navigator.geolocation) {
       	    navigator.geolocation.getCurrentPosition(showParkingsForPos,showError);
       	  } else{
       		showErrorLayer("Geolocation is not supported by this browser.");
       	  }
       	}

        function showErrorLayer(message) {
      	  $('#showErrorLayerMessage').prop('innerHTML', message);
      	  centerLayer($(window), $( "#showErrorLayer" ));
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

        function showError(error)
        {
        switch(error.code)
          {
          case error.PERMISSION_DENIED:
        	  showErrorLayer("User denied the request for Geolocation.");
            break;
          case error.POSITION_UNAVAILABLE:
        	  showErrorLayer("Location information is unavailable.");
            break;
          case error.TIMEOUT:
        	  showErrorLayer("The request to get user location timed out.");
            break;
          case error.UNKNOWN_ERROR:
        	  showErrorLayer("An unknown error occurred.");
            break;
          }
        }

        function showParkingsForPos(position) {
        	MyPos = position;
        	if (SearchMeters == 1000) {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=14");
            } else {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=15");
            }
        	if (isOutSideCABA(MyPos)) {
        		showErrorLayer("NO hay datos fuera de caba");
				removeParkings();
				parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(21,25);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/marker.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/car.png',size,offset);
				parkings.addMarker(createMarker(MyPos.coords.longitude,MyPos.coords.latitude, 'usted', '', proj, iconCar.clone()));
            } else {
        		searchParkings(MyPos.coords.longitude, MyPos.coords.latitude, SearchMeters);
            }
        }

		function isOutSideCABA(position) {
			if (position.coords.latitude < Math.min(startLat,endLat) || position.coords.latitude > Math.max(startLat,endLat)) {
				return true;
			  } else {
				  if (position.coords.longitude < Math.min(startLon,endLon) || position.coords.longitude > Math.max(startLon,endLon)) {
					  return true;
				  }
			}
			return false;
		}

		function searchParkings(lon, lat, rad) {
			$.ajax({
	            type: "GET",
	            cache: false,
	            url: "./searchParkings",
	            data: {lon: lon, lat: lat, rad: rad },
	            contentType: "application/json; charset=utf-8",
	            success: function(msg) {
            		parkings = new OpenLayers.Layer.Markers( "Parkings" );
    	            Mapa.map.addLayer(parkings);
    	            var size = new OpenLayers.Size(21,25);
    	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/marker.png',size,offset);
    				var proj = new OpenLayers.Projection("EPSG:4326");
    				var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/car.png',size,offset);
    				parkings.addMarker(createMarker(lon,lat, 'usted', '', proj, iconCar.clone()));
	            	$.each(msg, function(index, item) {
	    				parkings.addMarker(createMarker(item.lon,item.lat, item.name, item.desc, proj, icon.clone()));
	                });
	            },
	            error: function() {
	            	showErrorLayer("error consultando los estacionamientos");
	            }
	        });
		}

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


</script>
</head>

<body>
<section style="top:450px; position: absolute; z-index: 1300;">
	<button onclick="showAllParkings()">Mostrar Todos</button>
	<button onclick="showParkings(100)">100mts</button>
	<button onclick="showParkings(500)">500mts</button>
	<button onclick="showParkings(1000)">1000mts</button>
	<button onclick="removeParkings()">Quitar Todos</button>
	<input type="button" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">
	<input type="button" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">
</section>

	<div id="mapContainer" class="smallmap">
                </div>

<div id="showErrorLayer" style="display: none; z-index: 500;">
	<div id="showErrorLayerMessage">
		...
	</div>
	<input type="button" id="closeshowErrorLayer" cl="showErrorLayer" value="Cerrar">
</div>
</body>

</html>
