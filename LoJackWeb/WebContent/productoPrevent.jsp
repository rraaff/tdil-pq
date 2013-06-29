<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.lojack.utils.LoJackConfig"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.lojack.utils.SystemPropertyUtils"%><%--
--%><%@page import="com.tdil.lojack.struts.forms.CameraForm"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientBeanFacade"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.json.beans.URLHolder"%><%--
--%><%@page import="com.tdil.thalamus.client.facade.ThalamusClientFacade"%><%--
--%><%@ page info="home"%><%--
--%><%@ page contentType="text/html; charset=ISO-8859-1" %><%--
--%><%@ taglib uri="/WEB-INF/struts-bean" prefix="bean" %><%--
--%><%@ taglib uri="/WEB-INF/struts-logic" prefix="logic" %><%--
--%><%@ taglib uri="/WEB-INF/struts-html" prefix="html" %><%--
--%><%@ include file="includes/checkThalamusUp.jspf" %><%--
--%><%@ include file="includes/userLogged.jspf" %><%--
--%><%@ include file="includes/mustBeLogged.jspf" %><%--
--%><%@ include file="includes/mustBePreventUser.jspf" %><%--
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
	}
	#docs {
		font-size:12px;
	}
}
</style>
<% if (usingMobile || isAndroid) { %>
	<link type="text/css" href="css/index_modales.css" rel="stylesheet" media="screen" />
	<link type="text/css" href="css/unified_mobile.css" rel="stylesheet" media="screen" />
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
			button.iconMaxSpeed,
			button.iconZSeguras,
			button.iconGetPosit,
			button.iconPhoneAdm,
			button.iconHome {
				background: url(mobile/images/webApp/car/control_maxSpeed_16x16.png);
				background-repeat: no-repeat;
				background-position: 0 0;
				width:16px;
				height:16px;
				margin:2px 6px;
			}
			button.iconZSeguras { background: url(mobile/images/webApp/car/control_zSeguras_16x16.png); }
			button.iconGetPosit { background: url(mobile/images/webApp/car/control_getPosit_16x16.png); }
			button.iconPhoneAdm { background: url(mobile/images/webApp/car/control_phoneAdm_16x16.png); }
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
#productsMenu ul li.tabCar {
	background:#f05224;
}
</style>

<%@ include file="includes/headLogged.jsp" %>
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
        function resizeIcons(e){
        }
        
        <%@ include file="includes/errorAjaxJS.jspf" %>
        <%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
        $(function () {
        	<%@ include file="includes/closeLayers.jspf" %>
        	<%@ include file="includes/externalLogins.jspf" %>
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
		<%@ include file="includes/blockUI.jspf" %>
		$('#editMaxSpeed').load('goToVehiculesSpeedLimits.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#editMaxSpeedLayer" ));
				centerLayer($(window), $( "#centradorModalesMaxSpeed" ));
			}
		});
	}

	function editSecureZones() {
		<%@ include file="includes/blockUI.jspf" %>
		$('#editSecureZones').load('goToVehiculesSecureZones.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#editSecureZonesLayer" ));
				centerLayer($(window), $( "#centradorModalesSecureZones" ));
			}
		});
	}

	function selectVehiclesPhones() {
		<%@ include file="includes/blockUI.jspf" %>
		$('#selectVehiclesPhones').load('goToVehiculesForPhone.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#selectVehiclesPhonesLayer" ));
				centerLayer($(window), $( "#centradorModalesVehiclesPhones" ));
			}
		});
	}

	function selectVehiclesForMap() {
		<%@ include file="includes/blockUI.jspf" %>
		$('#selectVehiclesForMap').load('goToVehiculesForMap.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#selectVehiclesForMapLayer" ));
				centerLayer($(window), $( "#centradorModalesVehiclesForMap" ));
			}
		});
	}

<%@ include file="includes/centerLayerJS.jspf" %>
</script>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
		<div id="mapContainer" class="smallmap"></div>
		<section id="controls">
			<div class="basicControls">
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
	</div>
</section>
<!-- edit max speed -->
<div id="editMaxSpeedLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesMaxSpeed" class="defaultLayerStyles">
		<div id="editMaxSpeed" class="modalStyle">
			Consultando datos...
		</div>
	</div>
</div>
<div id="editSecureZonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesSecureZones" class="defaultLayerStyles">
		<div id="editSecureZones" class="modalStyle">
			Consultando datos...
		</div>
	</div>
</div>
<div id="selectVehiclesPhonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesVehiclesPhones" class="defaultLayerStyles">
		<div id="selectVehiclesPhones" class="modalStyle">
			Consultando datos...
		</div>
	</div>
</div>
<div id="editVehiclesPhonesLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesEditPhones" class="defaultLayerStyles">
		<div id="editVehiclesPhones" class="modalStyle">
			Consultando datos...
		</div>
	</div>
</div>
<div id="selectVehiclesForMapLayer" class="layerOnTop" style="display: none; z-index: 1500;">
	<div id="centradorModalesVehiclesForMap" class="defaultLayerStyles">
		<div id="selectVehiclesForMap" class="modalStyle">
			Consultando datos...
		</div>
	</div>
</div>

<%@ include file="includes/footerProductoHome.jsp" %>
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/videoLayers.jsp" %>
<%@ include file="includes/version.jspf" %></body>
</html>