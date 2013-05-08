<!--
   openlayers.html

   Copyright 2013 mgodoy <mgodoy@mgodoy-Satellite-P755>

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
   MA 02110-1301, USA.


-->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.tdil.lojack.utils.ParkingUtils"%>
<%@page import="com.tdil.lojack.model.PointOfInterest"%>
<%@page import="java.util.List"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
	<title>untitled</title>
	<meta http-equiv="content-type" content="text/html;charset=utf-8" />
	<meta name="generator" content="Geany 0.21" />
<style>
/**
 * Map Examples Specific
 */
.smallmap {
    width: 512px;
    height: 256px;
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
	<script src="js/jquery-1.8.2.min.js" type="text/javascript"></script>
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

		function init() {
			 var mapOptions = {
                DataProjection: "EPSG:4326"
            };
            Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
            Mapa.UpdateConfig({ title: "Prevent" });

		}

        $(function () {
            var mapOptions = {
                DataProjection: "EPSG:4326"
            };
            Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
            Mapa.UpdateConfig({ title: "Prevent" });
            Mapa.SetParameters("toolbar=off&Lat=-34.655504&Lon=-58.471677&Width=84&LayersViewWidth=0");
        });


        function removeParkings() {
            if (parkings) {
        		Mapa.map.removeLayer(parkings);
        		parkings = null;
            }
        }
        var currentPopup;
        var popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
            "autoSize": true,
            "minSize": new OpenLayers.Size(300, 50),
            "maxSize": new OpenLayers.Size(500, 300),
            "keepInMap": true
        });

        function addParkings() {
            if (!parkings) {
	        	parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(21,25);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('http://www.openlayers.org/dev/img/marker.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				<% List<PointOfInterest> parkings = ParkingUtils.getParkings(); %>
				<% for (PointOfInterest poi : parkings) { %>
					parkings.addMarker(createMarker(<%=poi.getLon()%>,<%=poi.getLat()%>, '<%=poi.getName()%>', '<%=poi.getDescription()%>', proj, icon.clone()));
				<% } %>
				parkings.addMarker(createMarker(-58.471677,-34.655504, 'Estacionamiento sin datos', 'Arenales 1500', proj, icon.clone()));
            }
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
	<button onclick="addParkings()">Mostrar Estacionamientos</button>
	<button onclick="removeParkings()">Quitar Estacionamientos</button>
	<input type="button" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">
	<input type="button" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">
	<div id="mapContainer" class="smallmap">
                </div>


<p id="demo">Click the button to get your coordinates:</p>
<button onclick="getLocation()">Mi posicion</button>
<script>
var x=document.getElementById("demo");
function getLocation()
  {
  if (navigator.geolocation)
    {
    navigator.geolocation.getCurrentPosition(showPosition,showError);
    }
  else{x.innerHTML="Geolocation is not supported by this browser.";}
  }
function showPosition(position)
  {
  x.innerHTML="Latitude: " + position.coords.latitude +
  "<br>Longitude: " + position.coords.longitude;
  if (position.coords.latitude < Math.min(startLat,endLat) || position.coords.latitude > Math.max(startLat,endLat)) {
	alert('latitud no caba');
  }
  if (position.coords.longitude < Math.min(startLon,endLon) || position.coords.longitude > Math.max(startLon,endLon)) {
	alert('longitud no caba');
  }
  Mapa.SetParameters("LayersViewWidth=1&toolbar=off&Lat="+position.coords.latitude+"&Lon="+position.coords.longitude+"&Width=84&LayersViewWidth=0");
  var markers = new OpenLayers.Layer.Markers( "Markers1" );
  Mapa.map.addLayer(markers);
  var size = new OpenLayers.Size(21,25);
  var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
  var icon = new OpenLayers.Icon('http://www.openlayers.org/dev/img/marker.png',size,offset);
  //markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(0,0),icon.clone()));
	//markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(0,90),icon.clone()));
	//markers.addMarker(new OpenLayers.Marker(new OpenLayers.LonLat(55,-34),icon));

	var proj = new OpenLayers.Projection("EPSG:4326");
	var point = new OpenLayers.LonLat(position.coords.longitude,position.coords.latitude);
	point.transform(proj, Mapa.map.getProjectionObject());
	markers.addMarker(new OpenLayers.Marker(point,icon.clone()));
  }
function showError(error)
  {
  switch(error.code)
    {
    case error.PERMISSION_DENIED:
      x.innerHTML="User denied the request for Geolocation."
      break;
    case error.POSITION_UNAVAILABLE:
      x.innerHTML="Location information is unavailable."
      break;
    case error.TIMEOUT:
      x.innerHTML="The request to get user location timed out."
      break;
    case error.UNKNOWN_ERROR:
      x.innerHTML="An unknown error occurred."
      break;
    }
  }
</script>
</body>

</html>
