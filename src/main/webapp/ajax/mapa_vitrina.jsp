<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Google Maps</title>
        <script type="text/javascript" src=http://maps.google.com/maps?file=api&amp;v=3&amp;key=AIzaSyD_IP-Js3_ETbJ9psH605u-4iqZihp_-Jg&sensor=true"></script>

        <script type="text/javascript">

            function load(mapId, latitud, longitud) {
                if (GBrowserIsCompatible()) {
                    var map = new GMap2(document.getElementById(mapId));
                    map.addControl(new GSmallMapControl());
                    map.addControl(new GMapTypeControl());
                    var center;
                    if (latitud !== 0 && longitud !== 0) {
                        center = new GLatLng(latitud, longitud);
                        document.getElementById("lat").value = latitud;
                        document.getElementById("lng").value = longitud;
                    } else {
                        center = new GLatLng(4.668912, -74.08287);
                        document.getElementById("lat").value = center.lat().toFixed(5);
                        document.getElementById("lng").value = center.lng().toFixed(5);
                    }

                    map.setCenter(center, 12);
                    geocoder = new GClientGeocoder();
                    var marker = new GMarker(center, {draggable: true});
                    map.addOverlay(marker);

                    GEvent.addListener(marker, "dragend", function () {
                        var point = marker.getPoint();
                        map.panTo(point);
                        document.getElementById("lat").value = point.lat().toFixed(5);
                        document.getElementById("lng").value = point.lng().toFixed(5);
                    });

                    GEvent.addListener(map, "moveend", function () {
                        map.clearOverlays();
                        var center = map.getCenter();
                        var marker = new GMarker(center, {draggable: true});
                        map.addOverlay(marker);
                        document.getElementById("lat").value = center.lat().toFixed(5);
                        document.getElementById("lng").value = center.lng().toFixed(5);

                        GEvent.addListener(marker, "dragend", function () {
                            var point = marker.getPoint();
                            map.panTo(point);
                            document.getElementById("lat").value = point.lat().toFixed(5);
                            document.getElementById("lng").value = point.lng().toFixed(5);

                        });
                    });
                }
            }

            function showAddress(mapId, address) {
                var map = new GMap2(document.getElementById(mapId));
                map.addControl(new GSmallMapControl());
                map.addControl(new GMapTypeControl());
                if (geocoder) {
                    geocoder.getLatLng(address, function (point) {
                        if (!point) {
                            alert(address + " not found");
                        } else {
                            document.getElementById("lat").value = point.lat().toFixed(5);
                            document.getElementById("lng").value = point.lng().toFixed(5);
                            map.clearOverlays();
                            map.setCenter(point, 14);
                            var marker = new GMarker(point, {draggable: true});
                            map.addOverlay(marker);

                            GEvent.addListener(marker, "dragend", function () {
                                var pt = marker.getPoint();
                                map.panTo(pt);
                                document.getElementById("lat").value = pt.lat().toFixed(5);
                                document.getElementById("lng").value = pt.lng().toFixed(5);
                            });

                            GEvent.addListener(map, "moveend", function () {
                                map.clearOverlays();
                                var center = map.getCenter();
                                var marker = new GMarker(center, {draggable: true});
                                map.addOverlay(marker);
                                document.getElementById("lat").value = center.lat().toFixed(5);
                                document.getElementById("lng").value = center.lng().toFixed(5);

                                GEvent.addListener(marker, "dragend", function () {
                                    var pt = marker.getPoint();
                                    map.panTo(pt);
                                    document.getElementById("lat").value = pt.lat().toFixed(5);
                                    document.getElementById("lng").value = pt.lng().toFixed(5);
                                });

                            });
                        }
                    });
                }
            }
        </script>
    </head>
    <body onload="load('map1', 0, 0)" onunload="GUnload()" >
        <form action="#" onsubmit="showAddress('map1', this.address.value); return false">
            <input type="text" size="50" name="address" value="Bogot&aacute; Colombia" />
            <input type="submit" value="Buscar" />
        </form>
        <form id="datosCoordenadasVitrina" action="${pageContext.request.contextPath}/guardarCoordenadas.do">
            <label>Latitud</label>
            <input id="lat" type="text" name="latitud" value="" readonly/>

            <label>Longitud</label>
            <input id="lng" type="text" name="longitud" value="" readonly/>

            <input type="submit" id="editar_coordenadas" value="Guardar Coordenadas" />
        </form>
        <div align="center" id="map1" style="width: 600px; height: 400px"></div>
        <script type="text/javascript">
            //<![CDATA[
            if (typeof _gstat !== "undefined")
                _gstat.audience('', 'pagesperso-orange.fr');
            //]]>
        </script>
    </body>
</html>