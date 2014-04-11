<%@page import="com.tdil.web.breadcrum.BreadcrumItem"%>
<%@page import="com.tdil.web.breadcrum.Breadcrum"%>
<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.utils.LJPeugeotConfig"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.LJPeugeotWebUtils"%><%--
--%><%@page import="java.util.List"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.ParkingUtils"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.ljpeugeot.model.PointOfInterest"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Points of Interest</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_reset-styles.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_sizers.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website_logged.css" />
<link type="text/css" rel="stylesheet" media="screen" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_website_maps.css" />
<!--[if lt IE 9]>
	<link type="text/css" rel="stylesheet" href="css/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_ie8-fixes.css" />
<![endif]-->
<%@ include file="includes/headLogged.jsp" %>
<script src="js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_OpenLayers.js" type="text/javascript"></script>
<script src="js/<%=com.tdil.utils.SystemConfig.STATIC_RESOURCES_VERSION%>_MapaOSM.js" type="text/javascript"></script>
<script type="text/javascript">
	var startLat = -34.53483581543;
	var startLon = -58.548202514648;
	var endLat = -34.685211181641;
	var endLon = -58.344268798828;
	var ZOOM_ALL = 12;
	var ZOOM_1000 = 14;
	var ZOOM_500 = 15;
	var ZOOM_CAR = 20;
	var Mapa;
	var parkings = new Array();
	var origenGeoRef;
	var POI;
	var currentPopup;
	var MyPos;
	var poiTypeToShow;
	var poiTypeToggle = new Array();
	poiTypeToggle[0] = false;
	poiTypeToggle[1] = false;
	poiTypeToggle[2] = false;
	poiTypeToggle[3] = false;
	poiTypeToggle[4] = false;
	poiTypeToggle[5] = false;
	var currPoints = new Array(); 
	var IconSizeForZoom = new Array(); 
	IconSizeForZoom[0] = 18;
	IconSizeForZoom[1] = 18;
	IconSizeForZoom[2] = 18;
	IconSizeForZoom[3] = 18;
	IconSizeForZoom[4] = 18;
	IconSizeForZoom[5] = 18;
	IconSizeForZoom[6] = 18;
	IconSizeForZoom[7] = 20;
	IconSizeForZoom[8] = 20;
	IconSizeForZoom[9] = 20;
	IconSizeForZoom[10] = 24;
	IconSizeForZoom[11] = 24;
	IconSizeForZoom[12] = 24;
	IconSizeForZoom[13] = 26;
	IconSizeForZoom[14] = 26;
	IconSizeForZoom[15] = 28;
	IconSizeForZoom[16] = 30;
	IconSizeForZoom[17] = 32;
	IconSizeForZoom[18] = 38;
	IconSizeForZoom[19] = 40;
	IconSizeForZoom[20] = 64;
        
	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>

	$(function () {
		<%@ include file="includes/closeLayers.jspf" %>
		<%@ include file="includes/externalLogins.jspf" %>

		var mapOptions = {
			DataProjection: "EPSG:4326",
			tilesetUrl: "<%=com.tdil.ljpeugeot.utils.LJPeugeotConfig.getMapsUrl()%>"
		};
		Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
		Mapa.UpdateConfig({ title: "Parkings" });
		Mapa.SetParameters("toolbar=off&Lat=-34.605004&Lon=-58.451677&Width=84&LayersViewWidth=0&zoom=" + ZOOM_ALL);

		$("input[cl]").each(function(indice,valor) {
			$(valor).click(function() {
				$( "#" + $(this).attr('cl') ).fadeOut();
			});
		});
	});

	function resizeIcons(e){
		if (Mapa.map) {
			if (currPoints.length > 0) {
				var actualSize = currPoints[0].icon.size.w;
				var newSize = IconSizeForZoom[Mapa.map.zoom];
				var inflateFactor = newSize / actualSize;
				//alert('zoom ' + Mapa.map.zoom + ', old size' + actualSize + ', new size ' + newSize + ', factor ' + inflateFactor);
				if (inflateFactor != 1) {
					for (var i=0;i<currPoints.length;i++) {
						currPoints[i].inflate(inflateFactor);
					}
				}
			}
		}
	}

	function removeParkings(poiType) {
		if (currentPopup != null && currentPopup.visible()) {
			currentPopup.hide();
		}
		currentPopup = null;
		if (parkings[poiType]) {
			Mapa.map.removeLayer(parkings[poiType]);
		}
	}
        var popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {
            "autoSize": true,
            "minSize": new OpenLayers.Size(300, 50),
            "maxSize": new OpenLayers.Size(500, 300),
            "keepInMap": true
        });
        
        function goHome(){
        	window.location = 'home.jsp';
        }

        function toggleParkings(poiType) {
        	poiTypeToShow = poiType;
        	if (poiTypeToggle[poiType]) {
        		poiTypeToggle[poiType] = false;
        		removeParkings(poiType);
        	} else {
        		poiTypeToggle[poiType] = true;
	            if (!MyPos) { // si no le pedi la posicion, voy por el circuito de pedido de posicion
					getLocationAndShowParkings();
	            } else {
	            	showParkingsForPos(MyPos);
	            }
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
        	  showErrorLayer("Ha ocurrido un error. Intentelo nuevamente.");
            break;
          }
        }

        function showParkingsForPos(position) {
        	MyPos = position;
       		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=" + + ZOOM_500);
       		searchParkings(poiTypeToShow);
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

		function searchParkings(poiTypeToShow) {
			$.ajax({
	            type: "GET",
	            cache: false,
	            url: "./searchPois",
	            data: {poiType: poiTypeToShow },
	            contentType: "application/json; charset=utf-8",
	            success: function(msg) {
            		parkings[poiTypeToShow] = new OpenLayers.Layer.Markers( poiTypeToShow );
    	            Mapa.map.addLayer(parkings[poiTypeToShow]);
    	            var size;
    	            var sizeCar;
   	        		size = new OpenLayers.Size(IconSizeForZoom[ZOOM_1000],IconSizeForZoom[ZOOM_1000]);
   	        		sizeCar = new OpenLayers.Size(IconSizeForZoom[ZOOM_CAR],IconSizeForZoom[ZOOM_CAR]);
    	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    	            var icon = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_parking.png',size,offset);
    				var proj = new OpenLayers.Projection("EPSG:4326");
    				var iconCar = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_carinmap.png',sizeCar,offset);
    				parkings[poiTypeToShow].addMarker(createMarker(MyPos.lon,MyPos.lat, 'Mi posición', '', proj, iconCar.clone()));
    				currPoints = new Array(); 
	            	$.each(msg, function(index, item) {
	    				var cloned = createMarker(item.lon,item.lat, item.name, item.desc, proj, icon.clone());
						currPoints.push(cloned);
	    				parkings[poiTypeToShow].addMarker(cloned);
	                });
	            },
	            error: function() {
	            	showErrorLayer("Error consultando los pois.");
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
            feature.data.popupContentHTML = '<div class="sign_at_map"><h5>' + title + '</h5><span class="sign_content">' + desc +'</span></div>';
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
		
	<%@ include file="includes/openLegalesLayer.jsp" %>
	<%@ include file="includes/contactJS.jspf" %>
</script>

</head>
<%@ include file="includes/version.jspf" %>
<body>
<%
	Breadcrum breadcrums = new Breadcrum()
	.titles("Inicio","Peugeot App","Points of Interest")
	.pages("home.jsp","home.jsp", "");
%>
<% MENU_ACTIVE_SECTION = "POIS"; %>
<!-- WEBSITE CONTENT -->
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/action_bar.jspf" %>
<%@ include file="includes/service_section_menu.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="map_insert">
	<div class="pageWrapper">
		<div id="mapContainer" class="smallmap"></div>
		<section id="controls">
			<div class="basicControls">
				<button class="iconEall" onclick="toggleParkings(0);">&nbsp;</button>
				<button class="icon100mts" onclick="toggleParkings(1);">&nbsp;</button>
				<button class="icon500mts" onclick="toggleParkings(2);">&nbsp;</button>
				<button class="icon1mks" onclick="toggleParkings(3);">&nbsp;</button>
				<button class="iconClear" onclick="toggleParkings(4);">&nbsp;</button>	
			</div>
			<section id="zoomSection">
				<div class="zoomControls">
					<button class="icon_zoom_in" onclick="javascript:Mapa.ZoomIn();">&nbsp;</button><!-- value="ZoomIn" -->
					<button class="icon_zoom_out" onclick="javascript:Mapa.ZoomOut();">&nbsp;</button><!--  value="ZoomOut" -->
				</div>
			</section>
		</section>
	</div>
</section>
<%@ include file="includes/emergency_button.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

<div id="showErrorLayer" class="layerOnTop" style="display:none; z-index:1500;">
	<div id="centradorModalesShowErrorsParking" class="layerModal">
		<section class="modal_header">
			<h2>¡Atención!</h2>
			<button class="close" cl="showErrorLayer">Cerrar <span></span></button>
		</section>
		<section class="modal_content">
			<span class="modal_subtitle">Descripción del error</span>
			<div class="alert alert-error" style="margin:20px 0;">
				<div>
					<div id="showErrorLayerMessage">
						...
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
</body>
</html>
