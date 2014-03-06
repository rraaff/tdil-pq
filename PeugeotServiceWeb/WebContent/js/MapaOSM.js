function MapaOSM(elementId, containerId, options) {
    this.minLat = 0;
    this.maxLat = 0;
    this.minLon = 0;
    this.maxLon = 0;

    this.minDeltaX = 0;
    this.maxDeltaX = 0;
    this.minDeltaY = 0;
    this.maxDeltaY = 0;
    this.localCopy = false;

    this.isReady = false;
    this.dataProjection = null;
    this.translateData = false;
    this.markerTooltip = null;
    this.urlCarMarker = "http://maps.google.com/mapfiles/kml/shapes/cabs.png";
    this.urlFlagMarker = "http://maps.google.com/mapfiles/kml/shapes/flag.png";

    this.iconWidth = 20;
    this.iconHeight = 20;

    this.baseZoomLevel = 11;
    if (!options) options = {};

    // Map
    this.mapElementId = elementId;
    var mapContainer = document.getElementById(containerId);
    while (mapContainer.hasChildNodes()) {
        mapContainer.removeChild(mapContainer.firstChild);
    }
    this.mapElement = document.createElement("div");
    this.mapElement.id = elementId;
    this.mapElement.style.height = "100%";
    this.mapElement.style.width = "100%";
    mapContainer.appendChild(this.mapElement);

    this.map = new OpenLayers.Map(elementId, { controls: [] });
    this.printConfig = {
        title: "",
        comment: "",
        showTitle: true,
        showComment: true,
        showScale: true,
        showNorth: true
    };

    // Layers
    var tilesetUrl = "http://a.tile.openstreetmap.org/${z}/${x}/${y}.png";
    if (options.tilesetUrl) {
    	tilesetUrl = options.tilesetUrl;
    }
    this.mainLayer = new OpenLayers.Layer.OSM("MainLayer", tilesetUrl, { transitionEffect: "resize", tileOptions: { crossOriginKeyword: null }});
    this.marcasLayer = new OpenLayers.Layer.Markers("Marcas");
    this.recorridosLayer = new OpenLayers.Layer.Markers("Recorrido");
    this.ultimaPosicionLayer = new OpenLayers.Layer.Markers("Ultima_Pos");
    this.areasControlLayer = new OpenLayers.Layer.Vector('Areas de Control');

    this.map.addLayer(this.mainLayer);
    this.map.addLayer(this.areasControlLayer);
    this.map.addLayer(this.ultimaPosicionLayer);
    this.map.addLayer(this.recorridosLayer);
    this.map.addLayer(this.marcasLayer);

    // Controls
    this.dragControl = new OpenLayers.Control.DragPan();
    this.navigationControl = new OpenLayers.Control.Navigation();
    this.navHistoryControl = new OpenLayers.Control.NavigationHistory();
    this.measureControl = new OpenLayers.Control.Measure(OpenLayers.Handler.Path);
    this.selectControl = new OpenLayers.Control.SelectFeature(this.areasControlLayer, { clickout: true, toggle: true, multiple: false, hover: false, box: false });
    this.highlightControl = new OpenLayers.Control.SelectFeature(this.areasControlLayer, { hover: true, highlightOnly: true, renderIntent: "temporary" });

    //this.map.addControl(this.dragControl);
    this.map.addControl(this.navigationControl);
    this.map.addControl(this.navHistoryControl);
    this.map.addControl(this.measureControl);
    this.map.addControl(this.selectControl);
    this.map.addControl(this.highlightControl);

    if (options.DataProjection) {
        this.dataProjection = new OpenLayers.Projection(options.DataProjection);
    } else {
        this.dataProjection = this.map.getProjectionObject();
    }
    this.translateData = (this.dataProjection.projCode != this.map.getProjectionObject().projCode);

    // Digitizers
    this.pointDigitizer = new OpenLayers.Control.DrawFeature(this.areasControlLayer,  OpenLayers.Handler.RegularPolygon,{handlerOptions: {sides: 40}});
    this.polygonDigitizer = new OpenLayers.Control.DrawFeature(this.areasControlLayer, OpenLayers.Handler.Polygon);

    this.map.addControl(this.pointDigitizer);
    this.map.addControl(this.polygonDigitizer);

    // Events
    this.map.events.on({
        scope: this,
        "movestart": function (evt) {
            this.isReady = false;
        },
        "moveend": function (evt) {
            this.isReady = true;
        },
        "zoomend": function (evt) {
            resizeIcons();
        },
    });

    this.measureControl.events.register("measure", this, function (evt) {
        alert("Distancia: " + OpenLayers.Number.format(evt.measure, 2, "", ".") + " " + evt.units);
        this.measureControl.deactivate();
    });

    this.pointDigitizer.events.register("featureadded", this, function (evt) {
        this.pointDigitizer.deactivate();
        //var center = evt.feature.geometry.getCentroid();
    	//var point1 = evt.feature.geometry.components[0].components[1];
    	//alert(center);
    	//alert(point1);
    	//var p = new OpenLayers.Geometry.Point(center.x, center.y);
    	//alert(p.transform(new OpenLayers.Projection("EPSG:900913"),new OpenLayers.Projection("EPSG:4326"))); 
        //var point = this.GetDataPoint(evt.feature.geometry.x, evt.feature.geometry.y);
        //if (options.PointDigitizedHandler) {
        //    options.PointDigitizedHandler(point.x, point.y);
        //}
    });

    this.polygonDigitizer.events.register("featureadded", this, function (evt) {
        this.polygonDigitizer.deactivate();

        var vertices = evt.feature.geometry.getVertices();
        var points = [];

        for (var i = 0; i < vertices.length; i++) {
            var point = this.GetDataPoint(vertices[i].x, vertices[i].y);
            points.push({ X: point.x, Y: point.y });
        }
        if (options.PolygonDigitizedHandler) {
            options.PolygonDigitizedHandler(points);
        }
    });
}

MapaOSM.prototype.GetMap = function () {
    return this.map; //document.getElementById(this.mapElementId);
}

MapaOSM.prototype.GetMapPoint = function (lon, lat) {
    var point = new OpenLayers.Geometry.Point(lon, lat);
    if (this.translateData) {
        point = OpenLayers.Projection.transform(point, this.dataProjection, this.map.getProjectionObject());
    }
    return point;
}

MapaOSM.prototype.GetDataPoint = function (x, y) {
    var point = new OpenLayers.Geometry.Point(x, y);
    if (this.translateData) {
        point = OpenLayers.Projection.transform(point, this.map.getProjectionObject(), this.dataProjection);
    }
    return point;
}

MapaOSM.prototype.GetLonLat = function (lon, lat) {
    var lonlat = new OpenLayers.LonLat(lon, lat);
    if (this.translateData) {
        return lonlat.transform(this.dataProjection, this.map.getProjectionObject());
    } else {
        return lonlat;
    }
}

MapaOSM.prototype.SetParameters = function (parameters) {
    var lon = null;
    var lat = null;
    var zoomLevel = this.baseZoomLevel;

    this.ResetLayers();

    var index = parameters.indexOf("?");
    if (index >= 0) {
        parameters = parameters.substring(index + 1);
    }
    var paramArray = parameters.split("&");
    for (var i = 0; i < paramArray.length; i++) {
        index = paramArray[i].indexOf("=");
        if (index > 0) {
            var key = paramArray[i].substring(0, index);
            var value = paramArray[i].substring(index + 1);
            switch (key.toLowerCase()) {
                case "lon":
                    lon = parseFloat(value);
                    break;
                case "lat":
                    lat = parseFloat(value);
                    break;
                case "zoom":
                	zoomLevel = parseFloat(value);
                    break;
            }
        }
    }
    if (lon && lat) {
        this.map.setCenter(this.GetLonLat(lon, lat), zoomLevel, false, false);
    }
    this.map.updateSize();
}

MapaOSM.prototype.SeleccionObjetos = function () {
    this.mapElement.style.cursor = ["default", "arrow"];
    this.highlightControl.activate();
    this.selectControl.activate();
    this.dragControl.deactivate();
}

MapaOSM.prototype.Reportes = function () {
    var Seleccion = this.GetMap().getSelection();
    var arrRep = new Array;
    arrRep[0] = "Bancos";
    arrRep[1] = "Bomberos";
    arrRep[2] = "Cines";
    arrRep[3] = "Comisarias";
    arrRep[4] = "Embajadas";
    arrRep[5] = "Estaciones de Servicio";
    arrRep[6] = "Hipermercados";
    arrRep[7] = "Supermercados";
    arrRep[8] = "Hospitales";
    arrRep[9] = "Hoteles";
    arrRep[10] = "Ministerios";
    arrRep[11] = "Museos";
    arrRep[12] = "Shoppings";
    arrRep[13] = "Universidades";
    arrRep[14] = "Subtes";

    var Flag = false;
    if (Seleccion.getNumObjects() == 0) {
        //Alerter.showAlert("Debe Seleccionar Objetos en el mapa");
    }
    else {
        var Coleccion = Seleccion.getMapObjects(null);
        for (var j = 0; j < Coleccion.size(); j++) {
            var objeto = Coleccion.item(j);
            var layer = objeto.getMapLayer();
            var nombre = layer.getName();
            for (var i = 0; i < 15; i++) {
                if (nombre == arrRep[i]) {
                    Flag = true;
                }
            }
        }
        if (Flag == true) {
            this.GetMap().viewReportsDlg();
        }
        else {
            //Alerter.showError("Los objetos seleccionados no tienen reporte asociado");
        }
    }
}

MapaOSM.prototype.Distancia = function () {
    this.measureControl.activate();
    this.selectControl.deactivate();
    this.highlightControl.deactivate();
}

MapaOSM.prototype.Imprimir = function () {
    window.open("ImprimirMapa.aspx", "_blank");
}

MapaOSM.prototype.Configurar = function () {
    var dlgPrintSetupID = "dlgPrintSetup";
    var dlgPrintSetup = $("#" + dlgPrintSetupID);
    if (dlgPrintSetup.length > 0) {
        dlgPrintSetup.center();
        dlgPrintSetup.show();
        setPrintSetup(this.printConfig);
    } else {
        dlgPrintSetup = $("<div>");
        dlgPrintSetup.attr("id", dlgPrintSetupID);
        dlgPrintSetup.css("z-index", "99999");
        $("body").append(dlgPrintSetup);
        var self = this;
        $.ajax({
            type: "GET",
            url: "PrintSetup.htm",
            success: function (data) {
                dlgPrintSetup.html(data);
                dlgPrintSetup.center();
                dlgPrintSetup.show();
                setPrintSetup(self.printConfig);
            },
            error: function (xhr) {
                alert("Error: " + xhr.responseText);
            }
        });
    }
}

MapaOSM.prototype.GetConfig = function () {
    return this.printConfig;
}

MapaOSM.prototype.UpdateConfig = function (config) {
    $.extend(this.printConfig, config);
    //this.printConfig.title = config.title;
    //this.printConfig.comment = config.comment;
    //this.printConfig.showTitle = config.showTitle;
    //this.printConfig.showComment = config.showComment;
    //this.printConfig.showScale = config.showScale;
    //this.printConfig.showNorth = config.showNorth;
}

MapaOSM.prototype.DibujarEscala = function (contenedor) {
    var scaleLine = new OpenLayers.Control.ScaleLine();
    this.map.addControl(scaleLine);
    contenedor.innerHTML = scaleLine.div.innerHTML;
    this.map.removeControl(scaleLine);
}

MapaOSM.prototype.ZoomIn = function () {
    this.map.zoomIn();
}

MapaOSM.prototype.ZoomOut = function () {
    this.map.zoomOut();
}

MapaOSM.prototype.ZoomPrevio = function () {
    this.navHistoryControl.previousTrigger();
}

MapaOSM.prototype.UnZoom = function () {
    this.map.zoomToMaxExtent();
}

MapaOSM.prototype.Copiar = function (options) {

    if (!options) options = { save: true };
    var result = { success: true, data: "", errorMessage: "" };

    try {
        var mapDiv = $("#" + this.mapElementId);
        var basePosition = null;

        var imageData = {
            BaseX: 0,
            BaseY: 0,
            TotalWidth: mapDiv.width(),
            TotalHeight: mapDiv.height()
        };

        var imageItem = {};
        var imageItems = [];
        var orden = 0;
        var position;

        var imageTags = $("#" + this.mapElementId + " img[src*='osm_tiles']");
        imageTags.each(function (index) {
            if (!basePosition) {
                var parentDivs = $(this).parents("div");
                parentDivs.each(function () {
                    var parentPosition = $(this).position();
                    if (parentPosition.left != 0 || parentPosition.top != 0) {
                        basePosition = parentPosition;
                        return false;
                    }
                });
            }
            position = $(this).position();
            imageItem = {
                Orden: orden,
                URL: $(this).attr("src"),
                Height: $(this).height(),
                Width: $(this).width(),
                X: Math.round(position.left),
                Y: Math.round(position.top),
                Z: 0
            }
            imageItems.push(imageItem);
            orden++;
        });

        //        imageTags = $("#" + this.mapElementId + " img").filter(function (index) {
        //            return ($(this).css("z-index").length >= 9 && $(this).css("display") != "none");
        //        });

        imageTags = $("#" + this.mapElementId + " img.olAlphaImg");

        orden = 0;
        imageTags.each(function (index) {
            if ($(this).css("position") == "absolute") {
                position = $(this).position();
            } else {
                position = $(this).parent().position();
            }
            imageItem = {
                Orden: orden,
                URL: $(this).attr("src"),
                Height: $(this).height(),
                Width: $(this).width(),
                X: Math.round(position.left),
                Y: Math.round(position.top),
                Z: 1
            }
            imageItems.push(imageItem);
            orden++;
        });

        imageData.Items = imageItems;
        if (basePosition) {
            imageData.BaseX = basePosition.left;
            imageData.BaseY = basePosition.top;
        }

        result.data = JSON.stringify(imageData);

        if (options.save) {
            var mapHandlerFormID = "frm_MapHandler"
            var mapDataFieldID = "hf_MapHandler_Data";
            var mapOperationFieldID = "hf_MapHandler_Operation";

            var mapHandlerForm = document.getElementById(mapHandlerFormID);
            if (!mapHandlerForm) {
                mapHandlerForm = document.createElement("form");
                mapHandlerForm.id = mapHandlerFormID;
                mapHandlerForm.action = "MapHandler.ashx";
                mapHandlerForm.method = "post";
                mapHandlerForm.style.visibility = "hidden";
                mapHandlerForm.style.height = "1px";
                mapHandlerForm.style.width = "1px";
                document.body.appendChild(mapHandlerForm);
            }
            var mapDataField = document.getElementById(mapDataFieldID)
            if (!mapDataField) {
                mapDataField = document.createElement("input");
                mapDataField.id = mapDataFieldID
                mapDataField.name = "mapData"
                mapDataField.type = "hidden";
                mapHandlerForm.appendChild(mapDataField);
            }
            var mapOperationField = document.getElementById(mapOperationFieldID);
            if (!mapOperationField) {
                mapOperationField = document.createElement("input");
                mapOperationField.id = mapOperationFieldID
                mapOperationField.name = "mapOperation"
                mapOperationField.type = "hidden";
                mapHandlerForm.appendChild(mapOperationField);
            }
            mapDataField.value = result.data;
            mapOperationField.value = "S";
            mapHandlerForm.submit();
        }

    } catch (ex) {
        result.errorMessage = ex.message;
        result.success = false;
    }
    if (options.callback) {
        options.callback(result);
    }
}

MapaOSM.prototype.Pan = function () {
    this.dragControl.activate();
    this.selectControl.deactivate();
    this.highlightControl.deactivate();
    this.mapElement.style.cursor = ["url('http://www.google.com/intl/en_ALL/mapfiles/openhand.cur')", "grab", "-moz-grab", "move"];
}

MapaOSM.prototype.HideLayer = function (layerName) {

}

MapaOSM.prototype.ClearLayer = function (layerName) {
    var layers = this.map.getLayersByName(layerName);
    if (layers && layers.length > 0) {
        var layer = layers[0];
        if (layer.clearMarkers) {
            layer.clearMarkers();
        } else if (layer.removeAllFeatures) {
            layer.removeAllFeatures();
        }
    }
}

MapaOSM.prototype.ResetLayers = function () {
    this.areasControlLayer.removeAllFeatures();
    this.ultimaPosicionLayer.clearMarkers();
    this.recorridosLayer.clearMarkers();
    this.marcasLayer.clearMarkers();
}

MapaOSM.prototype.DigitalizarPunto = function () {
    this.pointDigitizer.activate();
}

MapaOSM.prototype.DigitalizarPoligono = function () {
    this.polygonDigitizer.activate();
}

MapaOSM.prototype.CrearPoligono = function (puntos, colorArea, opacityArea, colorBorde, opacityBorde, anchoBorde) {
    if (!colorArea) colorArea = "#ee9900";
    if (!opacityArea) opacityArea = 0.4;
    if (!colorBorde) colorBorde = "#ee9900";
    if (!opacityBorde) opacityBorde = 1;
    if (!anchoBorde) anchoBorde = 1;

    var outerRing = new OpenLayers.Geometry.LinearRing(puntos);
    var components = new Array(outerRing);
    var polygon = new OpenLayers.Geometry.Polygon(components);
    var featStyle = {
        strokeColor: colorBorde,
        strokeOpacity: opacityBorde,
        strokeWidth: anchoBorde,
        fillColor: colorArea,
        fillOpacity: opacityArea
    };
    var feature = new OpenLayers.Feature.Vector(polygon); //, null, featStyle);
    return feature;
}

MapaOSM.prototype.MostrarBusqueda = function (busqueda) {

    var lon = eval(busqueda.Lon);
    var lat = eval(busqueda.Lat);

    this.marcasLayer.clearMarkers();
    switch (busqueda.GeometryType) {
        case 1:
            this.InsertMarker(this.marcasLayer, busqueda.NombreMarca, lat, lon, "", this.urlFlagMarker, "", 30, 30, true);
            var zoomLevel = (busqueda.ZoomScale ? busqueda.Escala : 16);
            this.map.setCenter(this.GetLonLat(lon, lat), zoomLevel);
            break;
        case 3:
            var zoomBounds = null;
            if (busqueda.Boundary && busqueda.Boundary.length > 0) {
                var puntos = [];
                for (var i = 0; i < cantVertices; i += 2) {
                    puntos.push(this.GetMapPoint(busqueda.Boundary[i], busqueda.Boundary[i + 1]));
                }
                var feature = this.CrearPoligono(puntos);
                this.areasControlLayer.addFeatures([feature]);
                zoomBounds = feature.geometry.getBounds();
            } else if (busqueda.Extent && busqueda.Extent.length >= 4) {
                zoomBounds = new OpenLayers.Bounds();
                zoomBounds.extend(this.GetMapPoint(busqueda.Extent[0], busqueda.Extent[1]));
                zoomBounds.extend(this.GetMapPoint(busqueda.Extent[2], busqueda.Extent[3]));
            }
            if (zoomBounds) {
                this.map.zoomToExtent(zoomBounds);
            }
            break;
    }

    var pais = document.getElementById("BusquedaGeografica1_dd_busqueda_pais").value;
    var provincia = document.getElementById("BusquedaGeografica1_dd_busqueda_provincia").value;
    var partido = document.getElementById("BusquedaGeografica1_dd_busqueda_partido").value;
    var localidad = document.getElementById("BusquedaGeografica1_dd_busqueda_localidad").value;
    var query = pais + "_" + provincia + "_" + partido;
    //alert(query);

    //        { X: -58.5125584, Y: -34.474333 },
    //        { X: -58.51187, Y: -34.4739456 },
    //        { X: -58.51158, Y: -34.4737989 },
    //        { X: -58.5109119, Y: -34.473438 },
    //        { X: -58.5104916, Y: -34.4732256 },
    //        { X: -58.5091305, Y: -34.4725455 },
    //        { X: -58.5081706, Y: -34.4720153 },
    //        { X: -58.5073638, Y: -34.471654 },
    //        { X: -58.5065511, Y: -34.4712664 },
    //        { X: -58.5049111, Y: -34.4704135 },
    //        { X: -58.5039842, Y: -34.4699063 },
    //        { X: -58.5024057, Y: -34.4689922 },
    //        { X: -58.5022775, Y: -34.4689051 },
    //        { X: -58.5021174, Y: -34.4688224 },
    //        { X: -58.5009748, Y: -34.4681007 },
    //        { X: -58.4996023, Y: -34.467299 },
    //        { X: -58.4993997, Y: -34.4671795 },
    //        { X: -58.4984982, Y: -34.4655993 },
    //        { X: -58.4975101, Y: -34.4638828 },
    //        { X: -58.49756, Y: -34.4638268 },
    //        { X: -58.497546, Y: -34.4637601 },
    //        { X: -58.4974798, Y: -34.4637216 },
    //        { X: -58.4974048, Y: -34.4637304 },
    //        { X: -58.4958356, Y: -34.4634279 },
    //        { X: -58.4955264, Y: -34.4634051 },
    //        { X: -58.4954116, Y: -34.4634908 },
    //        { X: -58.4949803, Y: -34.4636183 },
    //        { X: -58.4944508, Y: -34.4637483 },
    //        { X: -58.4940531, Y: -34.4639246 },
    //        { X: -58.4939749, Y: -34.4640524 },
    //        { X: -58.4940314, Y: -34.4642388 },
    //        { X: -58.4939808, Y: -34.4645159 },
    //        { X: -58.4939302, Y: -34.464641 },
    //        { X: -58.4939124, Y: -34.464847 },
    //        { X: -58.4939243, Y: -34.4650824 },
    //        { X: -58.4939627, Y: -34.4652025 },
    //        { X: -58.4933321, Y: -34.4653958 },
    //        { X: -58.4932846, Y: -34.4654828 },
    //        { X: -58.4935256, Y: -34.4657347 },
    //        { X: -58.4940226, Y: -34.4658779 },
    //        { X: -58.4940032, Y: -34.4660217 },
    //        { X: -58.493856, Y: -34.4661076 },
    //        { X: -58.4932689, Y: -34.466039 },
    //        { X: -58.4931559, Y: -34.4660693 },
    //        { X: -58.4930373, Y: -34.4661671 },
    //        { X: -58.4929747, Y: -34.4663174 },
    //        { X: -58.492926, Y: -34.4664762 },
    //        { X: -58.493083, Y: -34.4666469 },
    //        { X: -58.4927904, Y: -34.4667352 },
    //        { X: -58.492876, Y: -34.4673238 },
    //        { X: -58.4932717, Y: -34.4674607 },
    //        { X: -58.4932068, Y: -34.4675154 },
    //        { X: -58.4930119, Y: -34.4675108 },
    //        { X: -58.4928819, Y: -34.467562 },
    //        { X: -58.492656, Y: -34.4676668 },
    //        { X: -58.4926588, Y: -34.4677134 },
    //        { X: -58.4928, Y: -34.4677204 },
    //        { X: -58.4929017, Y: -34.4677506 },
    //        { X: -58.492913, Y: -34.4678135 },
    //        { X: -58.4923792, Y: -34.468086 },
    //        { X: -58.4923679, Y: -34.468221 },
    //        { X: -58.4924969, Y: -34.4682864 },
    //        { X: -58.492608, Y: -34.4683235 },
    //        { X: -58.4924131, Y: -34.4684516 },
    //        { X: -58.4923707, Y: -34.4686751 },
    //        { X: -58.4923396, Y: -34.4688521 },
    //        { X: -58.4922323, Y: -34.4690081 },
    //        { X: -58.492297, Y: -34.4691602 },
    //        { X: -58.4923933, Y: -34.4691921 },
    //        { X: -58.4924408, Y: -34.4692775 },
    //        { X: -58.4924241, Y: -34.4693691 },
    //        { X: -58.4924978, Y: -34.4695414 },
    //        { X: -58.4923396, Y: -34.46973 },
    //        { X: -58.4922775, Y: -34.4698674 },
    //        { X: -58.4923057, Y: -34.4700513 },
    //        { X: -58.4922831, Y: -34.4702167 },
    //        { X: -58.4923207, Y: -34.470355 },
    //        { X: -58.4922472, Y: -34.4705405 },
    //        { X: -58.4920157, Y: -34.4711739 },
    //        { X: -58.4917481, Y: -34.4714553 },
    //        { X: -58.491484, Y: -34.4719026 },
    //        { X: -58.4915839, Y: -34.4708256 },
    //        { X: -58.4913983, Y: -34.4707373 },
    //        { X: -58.4913626, Y: -34.4711257 },
    //        { X: -58.4912341, Y: -34.4708903 },
    //        { X: -58.4910199, Y: -34.4709551 },
    //        { X: -58.4908272, Y: -34.4707667 },
    //        { X: -58.4907358, Y: -34.470714 },
    //        { X: -58.49061, Y: -34.4707468 },
    //        { X: -58.4904477, Y: -34.4708417 },
    //        { X: -58.4903859, Y: -34.4708087 },
    //        { X: -58.4902857, Y: -34.4707486 },
    //        { X: -58.4901618, Y: -34.4707741 },
    //        { X: -58.4900798, Y: -34.4708415 },
    //        { X: -58.4901745, Y: -34.4708306 },
    //        { X: -58.4902602, Y: -34.4708561 },
    //        { X: -58.4901108, Y: -34.4709162 },
    //        { X: -58.4899741, Y: -34.4710456 },
    //        { X: -58.4900097, Y: -34.4711765 },
    //        { X: -58.4900543, Y: -34.4713608 },
    //        { X: -58.4901399, Y: -34.4714738 },
    //        { X: -58.4902237, Y: -34.4716615 },
    //        { X: -58.4902875, Y: -34.4717763 },
    //        { X: -58.4902638, Y: -34.4718182 },
    //        { X: -58.4903349, Y: -34.4719239 },
    //        { X: -58.490375, Y: -34.4720788 },
    //        { X: -58.4904643, Y: -34.4721589 },
    //        { X: -58.4903901, Y: -34.4722382 },
    //        { X: -58.4902104, Y: -34.4721239 },
    //        { X: -58.4902143, Y: -34.4720856 },
    //        { X: -58.4900816, Y: -34.4719184 },
    //        { X: -58.4899686, Y: -34.4718127 },
    //        { X: -58.4897919, Y: -34.4716633 },
    //        { X: -58.4896989, Y: -34.4716797 },
    //        { X: -58.4897627, Y: -34.4718054 },
    //        { X: -58.4898557, Y: -34.4719476 },
    //        { X: -58.4898156, Y: -34.4719895 },
    //        { X: -58.4898429, Y: -34.4720441 },
    //        { X: -58.4898374, Y: -34.4720879 },
    //        { X: -58.4897718, Y: -34.4720387 },
    //        { X: -58.4896941, Y: -34.4720604 },
    //        { X: -58.4896024, Y: -34.4719166 },
    //        { X: -58.4894803, Y: -34.4717362 },
    //        { X: -58.4893272, Y: -34.4716742 },
    //        { X: -58.4892452, Y: -34.4716998 },
    //        { X: -58.489227, Y: -34.471789 },
    //        { X: -58.4893254, Y: -34.471871 },
    //        { X: -58.4892762, Y: -34.4719075 },
    //        { X: -58.4893126, Y: -34.4719621 },
    //        { X: -58.489329, Y: -34.4720223 },
    //        { X: -58.4893946, Y: -34.472179 },
    //        { X: -58.4894919, Y: -34.4722865 },
    //        { X: -58.4895969, Y: -34.4724778 },
    //        { X: -58.4896552, Y: -34.4725293 },
    //        { X: -58.4894967, Y: -34.4727293 },
    //        { X: -58.4894059, Y: -34.4727424 },
    //        { X: -58.4893138, Y: -34.4726289 },
    //        { X: -58.4892355, Y: -34.4725876 },
    //        { X: -58.4891881, Y: -34.4725038 },
    //        { X: -58.4891389, Y: -34.4725056 },
    //        { X: -58.4890619, Y: -34.4724651 },
    //        { X: -58.4890103, Y: -34.4723534 },
    //        { X: -58.4890055, Y: -34.472343 },
    //        { X: -58.4887941, Y: -34.4720988 },
    //        { X: -58.4886192, Y: -34.4720587 },
    //        { X: -58.4882839, Y: -34.472066 },
    //        { X: -58.4881669, Y: -34.4722623 },
    //        { X: -58.4880579, Y: -34.4724742 },
    //        { X: -58.4879595, Y: -34.4724414 },
    //        { X: -58.4877227, Y: -34.4726017 },
    //        { X: -58.4876716, Y: -34.472773 },
    //        { X: -58.4876097, Y: -34.4729443 },
    //        { X: -58.4873837, Y: -34.4729443 },
    //        { X: -58.4873509, Y: -34.4730791 },
    //        { X: -58.4874238, Y: -34.4731739 },
    //        { X: -58.4875754, Y: -34.4731139 },
    //        { X: -58.4876826, Y: -34.4731666 },
    //        { X: -58.4876258, Y: -34.4732676 },
    //        { X: -58.4874238, Y: -34.4733051 },
    //        { X: -58.4872125, Y: -34.4732832 },
    //        { X: -58.4872601, Y: -34.473407 },
    //        { X: -58.4871833, Y: -34.4734982 },
    //        { X: -58.4870849, Y: -34.4735092 },
    //        { X: -58.4868881, Y: -34.473531 },
    //        { X: -58.4867861, Y: -34.4736331 },
    //        { X: -58.4866454, Y: -34.4737394 },
    //        { X: -58.4866221, Y: -34.473757 },
    //        { X: -58.4866797, Y: -34.4738088 },
    //        { X: -58.486695, Y: -34.4738226 },
    //        { X: -58.486746, Y: -34.4738882 },
    //        { X: -58.48652, Y: -34.4740667 },
    //        { X: -58.486531, Y: -34.474125 },
    //        { X: -58.48668, Y: -34.4742875 },
    //        { X: -58.4865237, Y: -34.4742125 },
    //        { X: -58.4863998, Y: -34.4742344 },
    //        { X: -58.4862576, Y: -34.4743401 },
    //        { X: -58.4862827, Y: -34.4744896 },
    //        { X: -58.4863198, Y: -34.4745452 },
    //        { X: -58.4862148, Y: -34.4745761 },
    //        { X: -58.4861913, Y: -34.4746725 },
    //        { X: -58.4862395, Y: -34.4747355 },
    //        { X: -58.4863186, Y: -34.4747751 },
    //        { X: -58.4863989, Y: -34.4747639 },
    //        { X: -58.4863864, Y: -34.4748246 },
    //        { X: -58.4863606, Y: -34.4748541 },
    //        { X: -58.4862735, Y: -34.4748702 },
    //        { X: -58.4862475, Y: -34.4749026 },
    //        { X: -58.4861705, Y: -34.4749618 },
    //        { X: -58.4861664, Y: -34.4749391 },
    //        { X: -58.4861461, Y: -34.4749059 },
    //        { X: -58.4861794, Y: -34.4748815 },
    //        { X: -58.4861761, Y: -34.474854 },
    //        { X: -58.4861015, Y: -34.4748507 },
    //        { X: -58.4859572, Y: -34.4748799 },
    //        { X: -58.4858663, Y: -34.4749648 },
    //        { X: -58.4857901, Y: -34.4750502 },
    //        { X: -58.4856668, Y: -34.4751346 },
    //        { X: -58.4856032, Y: -34.4751733 },
    //        { X: -58.4855813, Y: -34.4751562 },
    //        { X: -58.4854644, Y: -34.4752146 },
    //        { X: -58.4853933, Y: -34.4752479 },
    //        { X: -58.485383, Y: -34.4752926 },
    //        { X: -58.4853726, Y: -34.4753316 },
    //        { X: -58.4854071, Y: -34.4753694 },
    //        { X: -58.4854667, Y: -34.4753832 },
    //        { X: -58.4855034, Y: -34.4753818 },
    //        { X: -58.4855043, Y: -34.4754092 },
    //        { X: -58.489312, Y: -34.4770916 },
    //        { X: -58.4908601, Y: -34.4778496 },
    //        { X: -58.4924559, Y: -34.4786311 },
    //        { X: -58.4936326, Y: -34.4792072 },
    //        { X: -58.4940949, Y: -34.4794341 },
    //        { X: -58.494984, Y: -34.4798692 },
    //        { X: -58.495938, Y: -34.4803369 },
    //        { X: -58.4968752, Y: -34.4807956 },
    //        { X: -58.4978242, Y: -34.4812585 },
    //        { X: -58.4987408, Y: -34.4817089 },
    //        { X: -58.4997893, Y: -34.4822231 },
    //        { X: -58.5007942, Y: -34.4826976 },
    //        { X: -58.5009075, Y: -34.4827499 },
    //        { X: -58.5010887, Y: -34.4828365 },
    //        { X: -58.5020033, Y: -34.4833093 },
    //        { X: -58.5029644, Y: -34.4837801 },
    //        { X: -58.5037691, Y: -34.4841772 },
    //        { X: -58.504361, Y: -34.4835413 },
    //        { X: -58.5048615, Y: -34.4828757 },
    //        { X: -58.505655, Y: -34.4818143 },
    //        { X: -58.5062339, Y: -34.4810804 },
    //        { X: -58.5065335, Y: -34.4806892 },
    //        { X: -58.5068029, Y: -34.4804132 },
    //        { X: -58.5071516, Y: -34.4800079 },
    //        { X: -58.5076036, Y: -34.47955 },
    //        { X: -58.5083958, Y: -34.4787472 },
    //        { X: -58.5091161, Y: -34.4780173 },
    //        { X: -58.5092081, Y: -34.4779105 },
    //        { X: -58.5097703, Y: -34.4772579 },
    //        { X: -58.5103992, Y: -34.4765983 },
    //        { X: -58.5110791, Y: -34.4758855 },
    //        { X: -58.5118631, Y: -34.4750635 },
    //        { X: -58.5125584, Y: -34.474333 }
}

MapaOSM.prototype.IniciarDibujoAreaCircular = function () {
    // Compatibilidad con MapGuide 6.5
}
MapaOSM.prototype.AgregarAreaCircular = function (lat, lon, radio) {
    // Compatibilidad con MapGuide 6.5
}
MapaOSM.prototype.ZoomAreasCirculares = function (cantAreas) {
    var dataExtent = this.areasControlLayer.getDataExtent();
    if (dataExtent) {
        this.map.zoomToExtent(dataExtent);
    }
}

MapaOSM.prototype.DibujarAreaCircular = function (pIdZona, pNombreZona, pRadio, pX, pY, colorArea, opacityArea, colorBorde, opacityBorde, anchoBorde) {
    var point = this.GetMapPoint(pX, pY);
    var circle = OpenLayers.Geometry.Polygon.createRegularPolygon(point, pRadio, 50, 0);

    if (!colorArea) colorArea = "#ee9900";
    if (!opacityArea) opacityArea = 0.4;
    if (!colorBorde) colorBorde = "#ee9900";
    if (!opacityBorde) opacityBorde = 1;
    if (!anchoBorde) anchoBorde = 1;

    var featStyle = {
        strokeColor: colorBorde,
        strokeOpacity: opacityBorde,
        strokeWidth: anchoBorde,
        fillColor: colorArea,
        fillOpacity: opacityArea
    };
    var feature = new OpenLayers.Feature.Vector(circle);
    this.areasControlLayer.addFeatures(feature);
}

MapaOSM.prototype.DibujarAreaPoligonal = function (obj, colorArea, opacityArea, colorBorde, opacityBorde, anchoBorde) {
    var cantAreas = obj.length - 1;
    var zoomBounds = new OpenLayers.Bounds();

    for (var i = 0; i <= cantAreas; i++) {
        var cantVertices = obj[i].Vertices;
        var idZona = obj[i].Id;
        var puntos = [];

        for (var j = 0; j < cantVertices; j++) {
            puntos.push(this.GetMapPoint(obj[i].points[j].X, obj[i].points[j].Y));
        }
        var feature = this.CrearPoligono(puntos, colorArea, opacityArea, colorBorde, opacityBorde, anchoBorde);
        this.areasControlLayer.addFeatures([feature]);
        zoomBounds.extend(feature.geometry.getBounds());
    }
    this.map.zoomToExtent(zoomBounds);
}

MapaOSM.prototype.CreateMarker = function (point, name, description, iconUrl, iconShadowUrl, iconWidth, iconHeight, addTooltip) {
    var size = new OpenLayers.Size(iconWidth, iconHeight);
    var offset = new OpenLayers.Pixel(-(size.w / 2), -(size.h / 2));
    var icon = new OpenLayers.Icon(iconUrl, size, offset);
    var marker = new OpenLayers.Marker(point, icon);

    if (addTooltip) {
        var self = this;
        marker.name = name;
        marker.tooltip = description;
        marker.events.register("mouseover", marker, function (evt) {
            if (!self.markerTooltip) {
                var content = "<div style='text-align: left; padding: 5px; color: black;'>";
                if (this.name != "") {
                    content += "<span style='font-weight: bold;'>" + this.name + "</span><br />";
                }
                content += this.tooltip + "</div>";

                var tooltipID = "MarkerTooltip";
                self.markerTooltip = new OpenLayers.Popup(tooltipID, this.lonlat, null, content, false, null);
                self.markerTooltip.panMapIfOutOfView = true;

                self.markerTooltip.events.register("mouseout", self.markerTooltip, function (evt) {
                    self.UpdateTooltipDisplay(marker.icon.imageDiv, evt.clientX, evt.clientY, marker.icon.size);
                });
            }
            self.markerTooltip.autoSize = true;
            self.markerTooltip.setBorder("1px solid #ffcc33");
            self.map.addPopup(self.markerTooltip);
            self.markerTooltip.show();
            OpenLayers.Event.stop(evt);
        });
        marker.events.register("mouseout", marker, function (evt) {
            self.UpdateTooltipDisplay(self.markerTooltip.div, evt.clientX, evt.clientY, self.markerTooltip.size);
        });
    }
    return marker;
}

MapaOSM.prototype.UpdateTooltipDisplay = function (elem, x, y, size) {
    if (this.markerTooltip) {
        var left = elem.offsetLeft;
        var top = elem.offsetTop;
        var prevOffsetParent = elem.offsetParent;

        while (prevOffsetParent) {
            left += prevOffsetParent.offsetLeft;
            top += prevOffsetParent.offsetTop;
            prevOffsetParent = prevOffsetParent.offsetParent;
        }
        var right = left + size.w;
        var bottom = top + size.h;

        if (x < left || x > right || y < top || y > bottom) {
            this.markerTooltip.destroy();
            this.markerTooltip = null;
        }
    }
}

MapaOSM.prototype.InsertMarker = function (layer, name, latitud, longitud, description, iconUrl, iconShadowUrl, iconWidth, iconHeight, addClickListener) {
    var lonlat = this.GetLonLat(longitud, latitud);
    var marker = this.CreateMarker(lonlat, name, description, iconUrl, iconShadowUrl, iconWidth, iconHeight, addClickListener);
    layer.addMarker(marker);
}

MapaOSM.prototype.UbicarEntidadEnMapa = function (posicion, opciones) {

    if (!this.IsBusy()) {
        if (!opciones) {
            opciones = { eliminarMarcas: true, ubicarPunto: true };
        }
        if (opciones.eliminarMarcas) {
            this.ultimaPosicionLayer.clearMarkers();
        }
        var tooltip = "<table>"
                    + "<tr><td nowrap='nowrap'>Entidad:</td><td nowrap='nowrap'><strong>" + posicion.nombreEntidad + "</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Dominio:</td><td nowrap='nowrap'><strong>" + posicion.dominio + "</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Fecha GPS:</td><td nowrap='nowrap'><strong>" + posicion.fechaPosicion + " (GMT " + posicion.husoHorario + ")</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Velocidad:</td><td nowrap='nowrap'><strong>" + posicion.velocidad + " Km/h</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Rumbo:</td><td nowrap='nowrap'><strong>" + posicion.rumbo + "</strong></td></tr>"
                    + "</table>";
        this.InsertMarker(this.ultimaPosicionLayer, "", posicion.latitud, posicion.longitud, tooltip,
            this.urlCarMarker, "", this.iconWidth, this.iconHeight, true);

        if (opciones.ubicarPunto) {
            var zoom = opciones.zoom || this.map.getScale();
            this.map.setCenter(this.GetLonLat(posicion.longitud, posicion.latitud), zoom);
        }
    }
}

MapaOSM.prototype.UbicarFlotaEnMapa = function (posiciones, opciones) {
    try {
        if (!this.IsBusy()) {
            if (this.tUbicarFlota != 0) {
                window.clearTimeout(this.tUbicarFlota);
            }
            this.ResetLayers();
            var zoomBounds = new OpenLayers.Bounds();

            for (var i = 0; i < posiciones.length; i++) {
                this.UbicarEntidadEnMapa(posiciones[i], { eliminarMarcas: false, ubicarPunto: false });
                zoomBounds.extend(this.GetLonLat(posiciones[i].longitud, posiciones[i].latitud));
            }
            this.map.zoomToExtent(zoomBounds);
        } else {
            var self = this;
            this.tUbicarFlota = window.setTimeout(function () {
                MapaOSM.prototype.UbicarFlotaEnMapa.call(self, posiciones, usarZoom, valorZoom);
            }, 600);
        }
    }
    catch (ex) {
        alert('Error al ubicar las entidades.\nError:' + ex.message);
    }
}

MapaOSM.prototype.SetRecorrido = function (latitud, longitud, ancho, sqlWhere, posiciones) {
    try {
        this.recorridosLayer.clearMarkers();
        var zoomBounds = new OpenLayers.Bounds();

        for (var i = 0; i < posiciones.length; i++) {
            var posicion = posiciones[i];
            var tooltip = "<table>"
                    + "<tr><td nowrap='nowrap'>Orden:</td><td nowrap='nowrap'><strong>" + posicion.Orden + "</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Fecha GPS:</td><td nowrap='nowrap'><strong>" + posicion.FechaPosicion + " (GMT " + posicion.HusoHorario + ")</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Velocidad:</td><td nowrap='nowrap'><strong>" + posicion.Velocidad + " Km/h</strong></td></tr>"
                    + "<tr><td nowrap='nowrap'>Rumbo:</td><td nowrap='nowrap'><strong>" + posicion.Rumbo + "</strong></td></tr>"
                    + "</table>";

            this.InsertMarker(this.recorridosLayer, "", posicion.Latitud, posicion.Longitud, tooltip, posicion.UrlIcono, "", this.iconWidth, this.iconHeight, true);

            zoomBounds.extend(this.GetLonLat(posicion.Longitud, posicion.Latitud));
        }
        this.map.zoomToExtent(zoomBounds);
    }
    catch (ex) {
        alert('Error al visualizar el recorrido.\nError:' + ex.message);
    }
}

MapaOSM.prototype.IsBusy = function () {
    return !this.isReady;
}
MapaOSM.prototype.Refresh = function () {
    if (!this.IsBusy()) {
        if (this.tRefresh != 0) {
            window.clearInterval(this.tRefresh);
        }
        this.map.updateSize();
    } else {
        if (this.tRefresh == 0) {
            this.tRefresh = window.setInterval(function () { this.Refresh(); }, 600);
        }
    }
}
MapaOSM.prototype.MGZoomWidth = function (y, x, width, unit) {
    try {
        this.GetMap().zoomWidth(y, x, width, unit);

        //Ajuste vertical del zoom
        //Si no refresco no actualiza MapHeight
        this.GetMap().refresh();

        this.CheckMapReadyForMGZoomWidth(y, x, width, unit);

    }
    catch (ex) {
        alert('Error al ajustar zoom del this.\nError:' + ex.message);
    }
}
MapaOSM.prototype.VerPuntosInteres = function (latitud, longitud, ancho, sqlWhere) {
    try {
        if (!this.IsBusy()) {
            if (this.tPI != 0) {
                window.clearTimeout(this.tPI);
            }

            var layer = this.GetMap().getMapLayer(this.puntoInteresLayer);

            if (!layer.getRebuild())
                layer.setRebuild(true);

            layer.setVisibility(false);

            if (layer && layer != null) {
                layer.setSQLWhere(sqlWhere);
                layer.setVisibility(true);

                this.GetMap().zoomWidth(latitud, longitud, ancho, "KM");
            }
        }
        else {
            var self = this;
            this.tPI = window.setTimeout(function () {
                MapaOSM.prototype.VerPuntosInteres.call(self, latitud, longitud, ancho, sqlWhere);
            }, 600);
        }
    }
    catch (ex) {
        alert('Error al Visualizar Puntos de Interes en el mapa.\nError:' + ex.message);
    }
}
MapaOSM.prototype.SwitchPanelCapas = function () {
    if (this.GetMap().layersViewWidth == 1) {
        this.GetMap().layersViewWidth = 180;
    } else {
        this.GetMap().layersViewWidth = 1;
    }

}
MapaOSM.prototype.OcultarEntidades = function () {
    if (!this.IsBusy()) {
        //Recupero el layer de ultimas posiciones
        var layer = this.GetMap().getMapLayer(this.ultimaPosicionLayer);
        if (layer && layer != null) {
            layer.setVisibility(false);
            layer.setSQLWhere("");
            this.GetMap().Refresh();
        }
    }
}
MapaOSM.prototype.OcultarPuntosInteres = function () {

    if (!this.IsBusy()) {
        var layer = this.GetMap().getMapLayer(this.puntoInteresLayer);
        if (layer && layer != null) {
            layer.setVisibility(false);
            this.GetMap().Refresh();
        }
    }
}
MapaOSM.prototype.PintarEstrellaEnPunto = function (lon, lat, size) {
    try {
        if (!this.IsBusy()) {
            if (this.tPE != 0) {
                window.clearTimeout(this.tPE);
            }
            var redLineLayer = this.GetMap().getMapLayer("AutoTrack");
            var id = "InformeDistancia_CentroArea";

            if (redLineLayer == null) {
                redLineLayer = this.GetMap().createLayer("redline", "AutoTrack");
                redLineLayer.setSelectability(false);
                redLineLayer.setVisibility(true);

                var redLineSetup = new this.GetMap().getRedlineSetup();
                var redLineSymbolAttr = redLineSetup.getSymbolAttr();
                redLineSymbolAttr.setSymbol("Stars - Red");
                redLineSymbolAttr.setHeight(size, "M");
                redLineSymbolAttr.setWidth(size, "M");
            }
            var obj = redLineLayer.getMapObject(id);
            if (obj != null) redLineLayer.removeObject(obj);

            obj = redLineLayer.createMapObject(id, "", "");
            var pointObj = this.GetMap().createObject("mgpoint");
            pointObj.X = lon;
            pointObj.Y = lat;
            obj.addSymbolPrimitive(pointObj, false);
            this.GetMap().refresh();
        } else {
            var self = this;
            this.tPE = window.setTimeout(function () {
                MapaOSM.prototype.PintarEstrellaEnPunto.call(self, lon, lat, size);
            }, 600);
        }
    } catch (ex) {
        alert('Error al pintar estrella en el mapa.\nError:' + ex.message);
    }
}
MapaOSM.prototype.CheckMapReadyForMGZoomWidth = function (y, x, width, unit) {
    if (!this.IsBusy()) {
        if (this.tCheckMapReady != 0) {
            window.clearTimeout(this.tCheckMapReady);
        }
        this.GetMap().MapUnits = unit;
        if (width > this.GetMap().MapHeight) {
            width = width * (width / this.GetMap().MapHeight);
            this.GetMap().zoomWidth(y, x, width, unit);
        }
    }
    else {
        var self = this;
        this.tCheckMapReady = window.setTimeout(function () {
            MapaOSM.prototype.CheckMapReadyForMGZoomWidth.call(self, y, x, width, unit);
        }, 600);
    }
}
