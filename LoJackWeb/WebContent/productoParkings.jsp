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
<style type="text/css">
	.smallmap {
		width:968px;
		height:450px;
	}
	#tags { display: none; }
	#docs p {
		font-size:12px;
	    margin-bottom:0.5em;
	}
@media only screen and (orientation: landscape) and (max-width: 600px) {
	#shortdesc {
    	float:right;
    	width:25%;
    }
	#map {
		width:100%;
		height:100%;
	}
	#docs {
		font-size:12px;
	}
}
</style>
<% if (usingMobile || isAndroid) { %>
	<link type="text/css" href="css/index_modales.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/unified_mobile.css" rel="stylesheet" media="screen" />
	<style type="text/css">
		@media all and (orientation:landscape) {
			#productsMenu { position:fixed; z-index:1500; } 
		}
		@media all and (orientation:landscape) and (max-height:350px) {
			#productsMenu ul li.logoContainer { display:none; }
		}
		#content { position:fixed; }
	</style>
<% } else { %>
	<link type="text/css" href="css/reset-styles.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/sizers.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/tdil.bootstrap.modifier.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/index_menu.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/index_modales.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/index_social.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/copyright.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/mediaQueries.css" rel="stylesheet" media="screen" />
	<style type="text/css">
		@media only screen and (max-width: 968px) {
			body { background: #e51b24; overflow: hidden; }
			#productsMenu ul li.logoContainer { line-height: 14px; }
			#controls { width: 100%; margin: 0px auto; top:auto; bottom: 0px; position: fixed; }
			#controls .basicControls { text-align: center; width:100%; margin: 0 auto; }
			footer { display:none; }
			.pageWrapper { width: 100%; }
			#content { width:100%; height:90%; text-align:center; display:inline-block; overflow:hidden; left:0px; top:127px; position:absolute; z-index:1; }
			.smallmap, .pageWrapper { width: 100%; height: 100%; }
			#zoomSection { width: 100%; margin: 0; }
			#zoomSection .zoomControls { top: 40%; left: 20px; margin: 0 auto; position: fixed; }
		}
		@media only screen and (max-height: 800px) and (max-width: 480px) {
			button.iconEall,
			button.icon100mts,
			button.icon500mts,
			button.icon1mks,
			button.iconClear,
			button.iconHome {
				background: url(mobile/images/webApp/parkings/control_100mts.png);
				background-repeat: no-repeat;
				background-position: 0 0;
				width:50px;
				height:42px;
				margin:2px 4px;
			}
			button.iconEall { background: url(mobile/images/webApp/parkings/control_E_all.png); }
			button.icon500mts { background: url(mobile/images/webApp/parkings/control_500mts.png); }
			button.icon1mks { background: url(mobile/images/webApp/parkings/control_1000mts.png); }
			button.iconClear { background: url(mobile/images/webApp/parkings/control_clear.png); }
			button.iconHome { background: url(mobile/images/webApp/parkings/back_home.png }
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
		}
	</style>
<% } %>
<style type="text/css">
	#productsMenu ul li.tabParking {
		background:#f05224;
	}
</style>
<%@ include file="includes/headLogged.jsp" %>
<script src="js/OpenLayers.js" type="text/javascript"></script>
<script src="js/MapaOSM.js" type="text/javascript"></script>
<script type="text/javascript">

		var startLat = -34.53483581543;
		var startLon = -58.548202514648;
		var endLat = -34.685211181641;
		var endLon = -58.344268798828;

		var ZOOM_ALL = 12;
		var ZOOM_1000 = 14;
		var ZOOM_500 = 15;
		
        var Mapa;
        var parkings;
        var origenGeoRef;
        var POI;
        var currentPopup;
        var MyPos;
        var SearchMeters;
        var currPoints = new Array(); 
        var IconSizeForZoom = new Array(); 
        IconSizeForZoom[0] = 8;
        IconSizeForZoom[1] = 8;
        IconSizeForZoom[2] = 8;
        IconSizeForZoom[3] = 8;
        IconSizeForZoom[4] = 8;
        IconSizeForZoom[5] = 8;
        IconSizeForZoom[6] = 8;
        IconSizeForZoom[7] = 10;
        IconSizeForZoom[8] = 10;
        IconSizeForZoom[9] = 10;
        IconSizeForZoom[10] = 12;
        IconSizeForZoom[11] = 12;
        IconSizeForZoom[12] = 12;
        IconSizeForZoom[13] = 14;
        IconSizeForZoom[14] = 14;
        IconSizeForZoom[15] = 20;
        IconSizeForZoom[16] = 24;
        IconSizeForZoom[17] = 28;
        IconSizeForZoom[18] = 30;
        IconSizeForZoom[19] = 32;
        
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
            Mapa.SetParameters("toolbar=off&Lat=-34.655504&Lon=-58.471677&Width=84&LayersViewWidth=0&zoom=" + ZOOM_ALL);

            $("input[cl]").each(function(indice,valor) {
         	   $(valor).click(function() {
         		   $( "#" + $(this).attr('cl') ).fadeOut();
         		});
         	});
            
        	var checkHeight = function(){
        		//var elemToChange = document.getElementById("mapContainer");
        		//elemToChange.style.height = $(window).height() - 125 + "px"
        		//setTimeout(checkHeight,500);

				var elemToChange  = document.getElementById("mapContainer");
				var elemToChange1 = document.getElementById("content");
				var elemToChange2 = document.getElementById("controls");
			
				var winW = $(window).width();
				var winH = $(window).height();
				
				if (winW > winH && winW < 968) {
					var testervar = document.getElementById("testerDeAltura").innerHTML="LANDSCAPE > WW: " + winW + " - WH " + winH;
					elemToChange.style.width = winW + "px"
					elemToChange.style.height = winH + "px"
					
					elemToChange1.style.width = winW + "px"
					elemToChange1.style.height = winH + "px"
					
					elemToChange2.style.top = winH - 70 + "px"

				} else if (winW < winH && winW < 968) {
					var testervar = document.getElementById("testerDeAltura").innerHTML="PORTRAIT > WW: " + winW + " - WH " + winH;
					elemToChange.style.width = winW + "px"
					elemToChange.style.height = winH + "px"
					
					elemToChange1.style.width = winW + "px"
					elemToChange1.style.height = winH + "px"
				
					elemToChange2.style.top = winH - 70 + "px"
				} else if (winW > 968) {
					var testervar = document.getElementById("testerDeAltura").innerHTML="DEFAULTED > WW: " + winW + " - WH " + winH;
					elemToChange.style.width = "968px"
					elemToChange.style.height = "450px"
					
					elemToChange1.style.width = "100%"
					elemToChange1.style.height = "auto"
				
					elemToChange2.style.top = "403px"
				}
			}
        	
        	window.onload=function() {
        		checkHeight();
			}

			window.onresize=function() {
				checkHeight();
			}
        	
        	
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
        	if (MyPos) {
        		Mapa.SetParameters("toolbar=off&Lat="+MyPos.coords.latitude+"&Lon="+MyPos.coords.longitude+"&Width=84&LayersViewWidth=0&zoom=" + ZOOM_ALL);
            }
        	currPoints = new Array(); 
            if (!parkings) {
	        	parkings = new OpenLayers.Layer.Markers( "Parkings" );
	            Mapa.map.addLayer(parkings);
	            var size = new OpenLayers.Size(IconSizeForZoom[ZOOM_ALL],IconSizeForZoom[ZOOM_ALL]);
	            var offset = new OpenLayers.Pixel(-(size.w/1.5), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/icon_e.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				currPoints = new Array(); 
				<% List<PointOfInterest> parkings = ParkingUtils.getParkings(); %>
				<% for (PointOfInterest poi : parkings) { %>
					var cloned = createMarker(<%=poi.getLon()%>,<%=poi.getLat()%>, '<%=poi.getName()%>', '<%=poi.getDescription()%>', proj, icon.clone());
					currPoints.push(cloned);
					parkings.addMarker(cloned);
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
	            var size = new OpenLayers.Size(32,32);
	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/myPosition.png',size,offset);
				var proj = new OpenLayers.Projection("EPSG:4326");
				var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/car.png',size,offset);
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
    	            if (SearchMeters == 1000) {
    	        		size = new OpenLayers.Size(IconSizeForZoom[ZOOM_1000],IconSizeForZoom[ZOOM_1000]);
    	            } else {
    	            	size = new OpenLayers.Size(IconSizeForZoom[ZOOM_500],IconSizeForZoom[ZOOM_500]);
    	            }
    	            var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
    	            var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/icon_e.png',size,offset);
    				var proj = new OpenLayers.Projection("EPSG:4326");
    				var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/skin_lj_rl/webApp/parkings/car.png',size,offset);
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
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<div id="testerDeAltura" style="display:none;">not set yet</div>
<section id="content">
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
		</section>
		<section id="zoomSection">
			<div class="zoomControls">
				<button class="icon_zoom_in" onclick="javascript:Mapa.ZoomIn();">&nbsp;</button><!-- value="ZoomIn" -->
				<button class="icon_zoom_out" onclick="javascript:Mapa.ZoomOut();">&nbsp;</button><!--  value="ZoomOut" -->
			</div>
		</section>
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
<%@ include file="includes/version.jspf" %>
</body>
</html>
