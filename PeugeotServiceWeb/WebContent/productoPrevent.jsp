<%@page import="com.tdil.web.breadcrum.BreadcrumItem"%>
<%@page import="com.tdil.web.breadcrum.Breadcrum"%>
<%@page import="com.tdil.ljpeugeot.prevent.model.SatellitePosition"%>
<%@ include file="includes/agentInfo.jspf" %><%--
--%><%@page import="com.tdil.ljpeugeot.utils.LJPeugeotConfig"%><%--
--%><%@page import="com.tdil.ljpeugeot.struts.forms.prevent.SelectVehiclesForm"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertiesKeys"%><%--
--%><%@page import="com.tdil.ljpeugeot.utils.SystemPropertyUtils"%><%--
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
--%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1"/>
<title>Peugeot AXS :: Car Security</title>
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
<%
	SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForMapForm");
%>
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
	function resizeIcons(e){ }

	<%@ include file="includes/errorAjaxJS.jspf" %>
	<%@ include file="includes/updatePersonChangePasswordJS.jspf" %>
	$(function () {
		<%@ include file="includes/closeLayers.jspf" %>
		<%@ include file="includes/externalLogins.jspf" %>
		<%if ("1".equals(request.getParameter("showinmap")) && selectVehiclesForm != null && !selectVehiclesForm.getSelectList().isEmpty()) {%>
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

			var mapOptions = {
				DataProjection: "EPSG:4326",
				tilesetUrl: "<%=com.tdil.ljpeugeot.utils.LJPeugeotConfig.getMapsUrl()%>"
			};

			Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
			Mapa.UpdateConfig({ title: "Prevent" });
			Mapa.SetParameters("toolbar=off&Lat=<%=selectVehiclesForm.getSelectedVehiclePosition().get(0).getLatitude()%>&Lon=<%=selectVehiclesForm.getSelectedVehiclePosition().get(0).getLongitude()%>&Width=84&LayersViewWidth=0&zoom=15");
			
			parkings = new OpenLayers.Layer.Markers( "Parkings" );
			Mapa.map.addLayer(parkings);
			var size = new OpenLayers.Size(54,54);
//			var border = new OpenLayers.Border(dotted 3px #ee5222);
	//		var background = new OpenLayers.Background();
		//	var border-radius = new OpenLayers.Border-radius(27);
			var sizeIcon = new OpenLayers.Size(48,48);
			var offset = new OpenLayers.Pixel(-(size.w/2), -size.h);
			var icon = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_carinmap.png',size,offset);
			var proj = new OpenLayers.Projection("EPSG:4326");
			var iconCar = new OpenLayers.Icon('<%=LJPeugeotConfig.getFRONT_SERVER()%>/images/skn_peugeot/icons/apps/icon_carinmap.png',size,offset);
			<%int index = 0;
				for (SatellitePosition pos : selectVehiclesForm.getSelectedVehiclePosition()) { 
					com.tdil.ljpeugeot.prevent.model.Vehicle ve1 = selectVehiclesForm.getSelectList().get(index++);%>
					parkings.addMarker(createMarker(<%=pos.getLongitude()%>,<%=pos.getLatitude()%>, '<%=ve1.getDescription()%>', '<%=pos.getStreet()%>-<%=pos.getNumber()%>', proj, iconCar.clone()));
			<%}%>
		<%} else {%>
			var mapOptions = {
				DataProjection: "EPSG:4326",
				tilesetUrl: "<%=com.tdil.ljpeugeot.utils.LJPeugeotConfig.getMapsUrl()%>"
			};
			Mapa = new MapaOSM("mapObject", "mapContainer", mapOptions);
			Mapa.UpdateConfig({ title: "Prevent" });
			Mapa.SetParameters("toolbar=off&Lat=-34.615504&Lon=-58.451677&Width=84&LayersViewWidth=0");

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
		<% if (selectVehiclesForm.hasOnlyOne()) { 
			SelectVehiclesForm selectVehiclesForPhone = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSecureZoneForm");
			if (selectVehiclesForPhone == null) {
				selectVehiclesForPhone = new SelectVehiclesForm();
			}
			selectVehiclesForPhone.initWith(websiteUser);
			session.setAttribute("SelectVehiclesForSecureZoneForm",selectVehiclesForPhone);
		%>
		$('#editSecureZone').load('goToVehiculeSecureZone.do?vehicleId=<%=selectVehiclesForm.getVehicleId()%>', function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		  		centerLayer($(window), $( "#editSecureZoneLayer" ));
		  		centerLayer($(window), $( "#centradorModalesSecureZone" ));
			}
	  });
		<% } else { %>
		$('#editSecureZone').load('goToVehiculesForSecureZone.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#editSecureZoneLayer" ));
				centerLayer($(window), $( "#centradorModalesSecureZones" ));
			}
		});
		<% } %>
	}

	function selectVehiclesPhones() {
		<%@ include file="includes/blockUI.jspf" %>
		<% if (selectVehiclesForm.hasOnlyOne()) { 
			SelectVehiclesForm selectVehiclesForPhone = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForPhonesForm");
			if (selectVehiclesForPhone == null) {
				selectVehiclesForPhone = new SelectVehiclesForm();
			}
			selectVehiclesForPhone.initWith(websiteUser);
			session.setAttribute("SelectVehiclesForPhonesForm",selectVehiclesForPhone);
		%>
			$('#editVehiclesPhones').load('editVehiculePhones.do?vehicleId=<%=selectVehiclesForm.getVehicleId()%>', function(response, status, xhr) {
			  	<%@ include file="includes/unblockUI.jspf" %>
				  if (status == "error") {
				    errorAjax();
				  } else {
			  		centerLayer($(window), $( "#editVehiclesPhonesLayer" ));
			  		centerLayer($(window), $( "#centradorModalesEditPhones" ));
				}
		  });
		<% } else { %>
		$('#selectVehiclesPhones').load('goToVehiculesForPhone.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#selectVehiclesPhonesLayer" ));
				centerLayer($(window), $( "#centradorModalesVehiclesPhones" ));
			}
		});
		<% } %>
	}

	function selectVehiclesSpeed() {
		<%@ include file="includes/blockUI.jspf" %>
		<% if (selectVehiclesForm.hasOnlyOne()) { 
			SelectVehiclesForm selectVehiclesForPhone = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSpeedForm");
			if (selectVehiclesForPhone == null) {
				selectVehiclesForPhone = new SelectVehiclesForm();
			}
			selectVehiclesForPhone.initWith(websiteUser);
			session.setAttribute("SelectVehiclesForSpeedForm",selectVehiclesForPhone);
		%>
		$('#editVehiclesSpeed').load('goToVehiculesSpeedLimits.do?vehicleId=<%=selectVehiclesForm.getVehicleId()%>', function(response, status, xhr) {
		  	<%@ include file="includes/unblockUI.jspf" %>
			  if (status == "error") {
			    errorAjax();
			  } else {
		  		centerLayer($(window), $( "#editVehiclesSpeedLayer" ));
		  		centerLayer($(window), $( "#centradorModalesEditSpeed" ));
			}
	 	 });
		<% } else { %>
		$('#selectVehiclesSpeed').load('goToVehiculesForSpeed.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#selectVehiclesSpeedLayer" ));
				centerLayer($(window), $( "#centradorModalesVehiclesSpeed" ));
			}
		});
		<% } %>
	}

	function selectVehiclesForMap() {
		<%@ include file="includes/blockUI.jspf" %>
		<% if (selectVehiclesForm.hasOnlyOne()) { 
			SelectVehiclesForm selectVehiclesForPhone = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForSpeedForm");
			if (selectVehiclesForPhone == null) {
				selectVehiclesForPhone = new SelectVehiclesForm();
			}
			selectVehiclesForPhone.initWith(websiteUser);
			session.setAttribute("SelectVehiclesForSpeedForm",selectVehiclesForPhone);
		%>
			window.location = './locateVehicleInMap.do?vehicleId=<%=selectVehiclesForm.getVehicleId()%>'
		<% } else { %>
		$('#selectVehiclesForMap').load('goToVehiculesForMap.do', function(response, status, xhr) {
			<%@ include file="includes/unblockUI.jspf" %>
			if (status == "error") {
				errorAjax();
			} else {
				centerLayer($(window), $( "#selectVehiclesForMapLayer" ));
				centerLayer($(window), $( "#centradorModalesVehiclesForMap" ));
			}
		});
		<% } %>
	}

<%@ include file="includes/centerLayerJS.jspf" %>
<%@ include file="includes/openLegalesLayer.jsp" %>
<%@ include file="includes/contactJS.jspf" %>
</script>
<style>
	div.basicControls { padding:60px 0 0; }
</style>
</head>
<%@ include file="includes/version.jspf" %>
<body>
<% if (usingMobile || isAndroid) { %>
	<div style="background:#99ECD6; line-height:20px; text-align:center; color:#000;">android or mobile</div>
<% } %>
<%
	Breadcrum breadcrums = new Breadcrum()
	.titles("Inicio","Peugeot App","Car Security")
	.pages("home.jsp","home.jsp", "");
%>
<% MENU_ACTIVE_SECTION = "PREVENT"; %>
<!-- WEBSITE CONTENT -->
<div id="testerDeAltura" style="display:none;">not set yet</div>
<div id="placaLoader">Cargando datos en el mapa. Aguarde por favor...</div>

<%@ include file="includes/header.jspf" %>
<%@ include file="includes/page_title.jspf" %>
<%@ include file="includes/service_section_menu.jspf" %>
<%@ include file="includes/under_shade.jspf" %>
<section id="map_insert">
	<div class="pageWrapper">
		<div id="mapContainer" class="smallmap"></div>
		<section id="controls">
			<div class="basicControls">
				<button class="iconGetPosit" onclick="selectVehiclesForMap();">&nbsp;</button>
				<button class="iconMaxSpeed" onclick="selectVehiclesSpeed();">&nbsp;</button>
				<button class="iconZSeguras" onclick="editSecureZones();">&nbsp;</button>
				<button class="iconPhoneAdm" onclick="selectVehiclesPhones();">&nbsp;</button>
				<!--  button class="iconPathTour" onclick="#">&nbsp;</button-->
			</div>
			<section id="zoomSection">
				<div class="zoomControls">
					<button class="icon_zoom_in" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">&nbsp;</button>
					<button class="icon_zoom_out" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">&nbsp;</button>
				</div>
			</section>
		</section>
	</div>
</section>
<!-- edit max speed -->
<div id="editMaxSpeedLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesMaxSpeed" class="layerModal width300">
		<div id="editMaxSpeed">
			Consultando datos...
		</div>
	</div>
</div>
<div id="editSecureZoneLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesSecureZone" class="layerModal width300">
		<div id="editSecureZone">
			Consultando datos...
		</div>
	</div>
</div>
<div id="selectVehiclesSpeedLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesVehiclesSpeed" class="layerModal width300">
		<div id="selectVehiclesSpeed">
			Consultando datos...
		</div>
	</div>
</div>

<div id="editVehiclesSpeedLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesEditSpeed" class="layerModal width300">
		<div id="editVehiclesSpeed">
			Consultando datos...
		</div>
	</div>
</div>
<div id="selectVehiclesPhonesLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesVehiclesPhones" class="layerModal width300">
		<div id="selectVehiclesPhones">
			Consultando datos...
		</div>
	</div>
</div>
<div id="editVehiclesPhonesLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesEditPhones" class="layerModal width300">
		<div id="editVehiclesPhones">
			Consultando datos...
		</div>
	</div>
</div>
<div id="selectVehiclesForMapLayer" class="layerOnTop" style="top:0; left:0; display:none; z-index:1500;">
	<div id="centradorModalesVehiclesForMap" class="layerModal width300">
		<div id="selectVehiclesForMap">
			Consultando datos...
		</div>
	</div>
</div>

<%@ include file="includes/copyright.jspf" %>
<%@ include file="includes/footer_web.jspf" %>

<!-- ALL LAYERS -->
<%@ include file="includes/updatePersonChangePasswordLayers.jspf" %>
<%@ include file="includes/errorAjaxLayer.jspf" %>
<%@ include file="includes/layer_contact.jspf" %>
<%@ include file="includes/layer_legales.jspf" %>

<% if (usingMobile || isAndroid) { %>
	<script>
		var checkHeight = function(){
			var elemToChange  = document.getElementById("mapContainer");
			var elemToChange1 = document.getElementById("content");
			var elemToChange2 = document.getElementById("controls");
			var elemToChangeX = document.getElementById("placaLoader");
			
			var winW = $(window).width();
			var winH = $(window).height();
			
			$('.smallmap').css({ width: winW + 'px', height: winH + 'px' });
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
				elemToChange.style.top = "0"
				elemToChange.style.left = "0"
				
				elemToChange1.style.width = winW + "px"
				elemToChange1.style.height = winH + "px"
				elemToChange1.style.top = "0"
				elemToChange1.style.left = "0"
			
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