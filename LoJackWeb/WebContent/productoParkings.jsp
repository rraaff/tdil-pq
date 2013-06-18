<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.LoJackConfig"%><%--
--%><%@page import="com.tdil.lojack.utils.LoJackWebUtils"%><%--
--%><%@page import="java.util.List"%><%--
--%><%@page import="com.tdil.lojack.utils.ParkingUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.lojack.model.PointOfInterest"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/reset-styles.css" rel="stylesheet" media="screen">
<link href="css/sizers.css" rel="stylesheet" media="screen">
<!-- link href="css/bootstrap.min.css" type="text/css" rel="stylesheet" /> -->
<link type="text/css" href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" />
<link type="text/css" href="css/index_menu.css" rel="stylesheet" />
<link type="text/css" href="css/index_modales.css" rel="stylesheet" />
<link type="text/css" href="css/index_social.css" rel="stylesheet" />
<link type="text/css" href="css/copyright.css" rel="stylesheet" />
<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" />
<%@ include file="includes/headLogged.jsp" %>

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
@media only screen and (max-width: 968px) {
    body {
        height           : 100%;
        margin           : 0;
        padding          : 0;
        width            : 100%;
    }
    #map {
        background : #FFF;
        width      : 100%;
    }
    #map {
        border : 0;
        height : 200px;
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
        width: 100%;
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
        <%@ include file="includes/errorAjaxJS.jspf" %>
        <%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
        $(function () {
        	<%@ include file="includes/closeLayers.jspf" %>
        	<%@ include file="includes/externalLogins.jspf" %>
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
	            var size = new OpenLayers.Size(32,32);
	            var offset = new OpenLayers.Pixel(-(size.w/1.5), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/icon_e.png',size,offset);
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
       		showErrorLayer("No está disponible la Geolocalización en su navegador.<br/>Inténtelo con otro.");
       	  }
       	}

		function showErrorLayer(message) {
			$('#showErrorLayerMessage').prop('innerHTML', message);
				centerLayer($(window), $( "#showErrorLayer" ));
				centerLayer($(window), $( "#centradorModalesShowErrorsParking" ));
		}
        <%@ include file="includes/centerLayerJS.jspf" %>

        function showError(error)
        {
        switch(error.code)
          {
          case error.PERMISSION_DENIED:
        	  showErrorLayer("El usuario denego el pedido de Geolocalización.");
            break;
          case error.POSITION_UNAVAILABLE:
        	  showErrorLayer("La informacción de localización inaccesible.");
            break;
          case error.TIMEOUT:
        	  showErrorLayer("Se ha acabado el tiempo para obtener los datos.");
            break;
          case error.UNKNOWN_ERROR:
        	  showErrorLayer("Ha ocurrido un error.");
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
        		showErrorLayer("No hay datos fuera de de la Ciudad Autónoma de Buenos Aires.");
				removeParkings();
				parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(32,32);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/myPosition.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/car.png',size,offset);
				parkings.addMarker(createMarker(MyPos.coords.longitude,MyPos.coords.latitude, 'Mi posición', '', proj, iconCar.clone()));
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
    	            var size = new OpenLayers.Size(32,32);
    	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/icon_e.png',size,offset);
    				var proj = new OpenLayers.Projection("EPSG:4326");
    				var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/car.png',size,offset);
    				parkings.addMarker(createMarker(lon,lat, 'Mi posición', '', proj, iconCar.clone()));
	            	$.each(msg, function(index, item) {
	    				parkings.addMarker(createMarker(item.lon,item.lat, item.name, item.desc, proj, icon.clone()));
	                });
	            },
	            error: function() {
	            	showErrorLayer("Error consultando los estacionamientos.");
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
            feature.data.popupContentHTML = '<div style="background-color:#000; color:#fff; padding:20px;"><span style="color:#ee5222; font-weight:bold;">' + title + '</span><br><span style="color:#fff; font-size:12px; font-weight:lighter; padding:10px 0;">' + desc +'</span></div>';
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

<style type="text/css">
@media only screen and (max-width: 968px) {
	body { background: #e51b24; overflow: hidden; }
	#productsMenu ul li.logoContainer { line-height: 14px; }
	#controls { width: 100%; margin: 0px auto; top:auto; bottom: 0px; position: fixed; }
	#controls .basicControls { text-align: center; width:100%; margin: 0 auto; }
	footer { visibility: hidden; }
	.pageWrapper { width: 100%; }
	#content { width: 100%; height: 100%; padding: 0px; margin: 0px; text-align:center; display:inline-block; overflow:hidden; left:0px; top:83px; position:absolute; z-index:1; }
	.smallmap, .pageWrapper { width: 100%; height: 100%; }
	#zoomSection { width: 100%; margin: 0; }
	#zoomSection .zoomControls { top: 40%; left: 20px; margin: 0 auto; position: fixed; }
}
@media only screen and (max-height: 800px) and (max-width: 480px) {
	button.iconEall,
	button.icon100mts,
	button.icon500mts,
	button.icon1mks,
	button.iconClear {
		background: url(mobile/images/webApp/parkings/control_100mts.png);
		background-repeat: no-repeat;
		background-position: 0 0;
		width:31px;
		height:24px;
		margin:2px 4px;
	}
	button.iconEall { background: url(mobile/images/webApp/parkings/control_E_all.png); }
	button.icon500mts { background: url(mobile/images/webApp/parkings/control_500mts.png); }
	button.icon1mks { background: url(mobile/images/webApp/parkings/control_1000mts.png); }
	button.iconClear { background: url(mobile/images/webApp/parkings/control_clear.png); }
	a.iconHome {
		width:16px;
		height:16px;
		margin:2px 4px;
	}
	button.icon_zoom_in,
	button.icon_zoom_out {
		background: url(mobile/images/webApp/parkings/icon_ZoomIn.png);
		background-repeat: no-repeat;
		background-position: 0 0;
		width:32px;
		height:32px;
		padding:5px;
		margin:5px;
	}
	button.icon_zoom_out {
		background: url(mobile/images/webApp/parkings/icon_ZoomOut.png);
		background-repeat: no-repeat;
		background-position: 0 0;
}
</style>
<% if (usingMobile || isAndroid) { %>
	<style type="text/css">
		#productsMenu ul li.logoContainer {
			display:none;
		}
		#productsMenu {
			height:17px;
		}
		#content {
			height:100%;
			top:0px;
			overflow: hidden;
		}
		#productsMenu ul li,
		#productsMenu ul li.toRight {
			line-height: 16px;
		}
		#productsMenu ul li a {
			font-size: 60%;
		}
		#productHomeMenu ul li a {
			font-size: 60%;
		}
		#zoomSection .zoomControls {
			top:40%;
			left:5px;
		}
	</style>
<% } %>
</head>
<body onload="getSize();">
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
		<div id="mapContainer" class="smallmap"></div>
	</div>
</section>
<section id="controls">
	<div class="basicControls">
		<% if (usingMobile || isAndroid) { %>
			<a href="mobile/home.jsp" class="iconHome" title="Volver al inicio"><img src="mobile/images/webApp/car/control_home_16x16.png" /></a>
		<% } %>
		<button class="iconEall" onclick="showAllParkings()">&nbsp;</button>
		<button class="icon100mts" onclick="showParkings(100)">&nbsp;</button>
		<button class="icon500mts" onclick="showParkings(500)">&nbsp;</button>
		<button class="icon1mks" onclick="showParkings(1000)">&nbsp;</button>
		<button class="iconClear" onclick="removeParkings()">&nbsp;</button>	
	</div>
</section>
<section id="zoomSection">
	<div class="zoomControls">
		<button class="icon_zoom_in" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">&nbsp;</button>
		<button class="icon_zoom_out" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">&nbsp;</button>
	</div>
</section>

<%@ include file="includes/footerProductoHome.jsp" %>

<div id="showErrorLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesShowErrorsParking" class="defaultLayerStyles">
		<div class="modalStyle">
			<div class="modalWrapper">
				<h3>Atención</h3>
				<div class="alert alert-error" style="margin:20px 0;">
					<div>
						<div id="showErrorLayerMessage">
							...
						</div>
					</div>
				</div>
				<input type="button" id="closeshowErrorLayer" cl="showErrorLayer" value="Cerrar" class="indexButtonBase"/>
			</div>
		</div>
	</div>
</div>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/version.jspf" %></body>
</html>
