<%@page import="com.tdil.lojack.utils.LoJackConfig"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.SelectVehiclesForm"%>
<%@page import="com.tdil.lojack.prevent.model.SpeedLimit"%>
<%@page import="com.tdil.lojack.struts.forms.beans.SpeedSelectionBean"%>
<%@page import="com.tdil.lojack.prevent.model.Vehicle"%>
<%@page import="com.tdil.lojack.struts.forms.prevent.VehiclesSpeedLimitForm"%>
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
<meta charset="utf-8"/>
<title>LoJack :: Lo tuyo es tuyo</title>
<link rel="icon" href="favicon.ico" type="icon"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<%@ include file="includes/headLogged.jsp" %>
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
    <script src="js/OpenLayers.js" type="text/javascript"></script>
    <script src="js/MapaOSM.js" type="text/javascript"></script>

<% SelectVehiclesForm selectVehiclesForm = (SelectVehiclesForm)session.getAttribute("SelectVehiclesForMapForm");%>
<script type="text/javascript">
var Mapa;
var parkings;
var currentPopup;
$(function () {

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
    var icon = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/marker.png',size,offset);
	var proj = new OpenLayers.Projection("EPSG:4326");
	var iconCar = new OpenLayers.Icon('<%=LoJackConfig.getFRONT_SERVER()%>/images/car.png',size,offset);
	parkings.addMarker(createMarker(<%=selectVehiclesForm.getSelectedVehiclePosition().getLongitude()%>,<%=selectVehiclesForm.getSelectedVehiclePosition().getLatitude()%>, '<%=selectVehiclesForm.getSelected().getDescription()%>', '<%=selectVehiclesForm.getSelectedVehiclePosition().getStreet()%>-<%=selectVehiclesForm.getSelectedVehiclePosition().getNumber()%>', proj, iconCar.clone()));
});


</script>
</head>
<body>
<%@ include file="includes/header.jsp" %>
<%@ include file="includes/clientMainManu.jsp" %>
<section id="content">
	<div class="pageWrapper">
Ubicacion<br>
<br>


Vehiculo: <%=selectVehiclesForm.getSelected().getDescription() %><br>
Calle: <%=selectVehiclesForm.getSelectedVehiclePosition().getStreet()%><br>
Numero: <%=selectVehiclesForm.getSelectedVehiclePosition().getNumber()%><br>

	<input type="button" onclick="javascript:Mapa.ZoomIn();" value="ZoomIn">
	<input type="button" onclick="javascript:Mapa.ZoomOut();" value="ZoomOut">
	<div id="mapContainer" class="smallmap">
                </div>

</div>
</section>

</body>
</html>