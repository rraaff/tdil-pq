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
--%><%@ include file="includes/userLoggedApk.jspf" %><%--
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
	var parkings;
	var origenGeoRef;
	var POI;
	var currentPopup;
	var MyPos;
	var SearchMeters;
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
		if (parkings) {
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
	}

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
        
        function goHome(){
        	window.location = 'mobile/home.jsp';
        }
        function showAllParkings() {
        	removeParkings();
        	/*if (MyPos) {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=" + ZOOM_ALL);
            }*/
            Mapa.SetParameters("toolbar=off&Lat=-34.605004&Lon=-58.451677&Width=84&LayersViewWidth=0&zoom=" + ZOOM_ALL);
        	currPoints = new Array(); 
            if (!parkings) {
	        	parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(IconSizeForZoom[ZOOM_ALL],IconSizeForZoom[ZOOM_ALL]);
	            var offset = new OpenLayers.Pixel(-(size.w/1.5), -size.h);
	            var icon = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_parking.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				currPoints = new Array(); 
				<%List<PointOfInterest> parkings = ParkingUtils.getParkings();%>
				<%for (PointOfInterest poi : parkings) {%>
					var cloned = createMarker(<%=poi.getLon()%>,<%=poi.getLat()%>, '<%=poi.getName()%>', '<%=poi.getDescription()%>', proj, icon.clone());
					currPoints.push(cloned);
					parkings.addMarker(cloned);
				<%}%>
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
        	  showErrorLayer("Ha ocurrido un error. Intentelo nuevamente.");
            break;
          }
        }

        function showParkingsForPos(position) {
        	MyPos = position;
        	
        	if (SearchMeters == 1000) {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=" + ZOOM_1000);
            } else {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=" + + ZOOM_500);
            }
        	/*if (isOutSideCABA(MyPos)) {
        		showErrorLayer("No hay datos fuera de de la Ciudad Autónoma de Buenos Aires.");
				removeParkings();
				currPoints = new Array(); 
				parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(64,64);
	            var sizeCar = new OpenLayers.Size(IconSizeForZoom[ZOOM_CAR],IconSizeForZoom[ZOOM_CAR]);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_position.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				var iconCar = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_carinmap.png',sizeCar,offset);
				parkings.addMarker(createMarker(MyPos.coords.longitude,MyPos.coords.latitude, 'Mi posición', '', proj, iconCar));
            } else {*/
        		searchParkings(MyPos.coords.longitude, MyPos.coords.latitude, SearchMeters);
            /*}*/
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
    	            var size;
    	            var sizeCar;
    	            if (SearchMeters == 1000) {
    	        		size = new OpenLayers.Size(IconSizeForZoom[ZOOM_1000],IconSizeForZoom[ZOOM_1000]);
    	        		sizeCar = new OpenLayers.Size(IconSizeForZoom[ZOOM_CAR],IconSizeForZoom[ZOOM_CAR]);
    	            } else {
    	            	size = new OpenLayers.Size(IconSizeForZoom[ZOOM_500],IconSizeForZoom[ZOOM_500]);
    	            	sizeCar = new OpenLayers.Size(IconSizeForZoom[ZOOM_CAR],IconSizeForZoom[ZOOM_CAR]);
    	            }
    	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    	            var icon = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_parking.png',size,offset);
    				var proj = new OpenLayers.Projection("EPSG:4326");
    				var iconCar = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_carinmap.png',sizeCar,offset);
    				parkings.addMarker(createMarker(lon,lat, 'Mi posición', '', proj, iconCar.clone()));
    				currPoints = new Array(); 
	            	$.each(msg, function(index, item) {
	    				var cloned = createMarker(item.lon,item.lat, item.name, item.desc, proj, icon.clone());
						currPoints.push(cloned);
	    				parkings.addMarker(cloned);
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
<section id="map_insert">
	<div class="pageWrapper">
		<div id="mapContainer" class="smallmap"></div>
		<section id="controls">
			<div class="basicControls">
				<button class="iconEall" onclick="showAllParkings();">&nbsp;</button>
				<button class="icon100mts" onclick="showParkings(100);">&nbsp;</button>
				<button class="icon500mts" onclick="showParkings(500);">&nbsp;</button>
				<button class="icon1mks" onclick="showParkings(1000);">&nbsp;</button>
				<button class="iconClear" onclick="removeParkings();">&nbsp;</button>	
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
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/layer_parking_not_logged.jspf" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

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
<%@ include file="includes/version.jspf" %>
<% if (usingMobile || isAndroid) { %>
	<script>
		var checkHeight = function() {
			var elemToChange  = document.getElementById("mapContainer");
			var elemToChange1 = document.getElementById("content");
			var elemToChange2 = document.getElementById("controls");
			var elemToChangeX = document.getElementById("placaLoader");
		
			var winW = $(window).width();
			var winH = $(window).height();
			
			elemToChangeX.style.display = "inline-block"
			
			if (winW > winH) {
				var testervar = document.getElementById("testerDeAltura").innerHTML="LANDSCAPE > WW: " + winW + " - WH " + winH;
				elemToChange.style.width = winW + "px"
				elemToChange.style.height = winH + "px"
				
				elemToChange1.style.width = winW + "px"
				elemToChange1.style.height = winH + "px"
				
				elemToChange2.style.top = winH - 70 + "px"
				
			} else if (winW < winH) {
				var testervar = document.getElementById("testerDeAltura").innerHTML="PORTRAIT > WW: " + winW + " - WH " + winH;
				elemToChange.style.width = winW + "px"
				elemToChange.style.height = winH + "px"
				
				elemToChange1.style.width = winW + "px"
				elemToChange1.style.height = winH + "px"
			
				elemToChange2.style.top = winH - 70 + "px"
			}
			elemToChangeX.style.display = "none"
		}
		var recheckHeight = function() {
			var elemToChangeX = document.getElementById("placaLoader");
			
			var winW = $(window).width();
			var winH = $(window).height();
			
			elemToChangeX.style.display = "inline-block"
			elemToChangeX.style.width = winW + "px"
			elemToChangeX.style.height = winH + "px"

			setInterval( function(){ checkHeight(); }, 2000 );
		}
		
		window.onload=function() {
			checkHeight();
		}		
		window.onresize=function() {
			recheckHeight();
		}
	</script>
<% } %>
</body>
</html>
