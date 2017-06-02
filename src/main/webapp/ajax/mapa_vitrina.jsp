<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <title>Actualizar latitud y longitud de vitrina</title>
        <script type="text/javascript" src="/web/jsseguro/jquery/jquery1.7.js"></script>
        <script type="text/javascript" src=http://maps.google.com/maps?file=api&amp;v=2&amp;key=AIzaSyD_IP-Js3_ETbJ9psH605u-4iqZihp_-Jg&sensor=true"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/publicacion/vitrina.js"></script>
        <link href="<%=request.getContextPath()%>/css/publication_styles.css" rel="stylesheet" type="text/css" />

        <c:set var="latitud" value="0"></c:set>
        <c:set var="longitud" value="0"></c:set>

        <script type="text/javascript">

            function load() {
                if (GBrowserIsCompatible()) {
                    var map = new GMap2(document.getElementById("map"));
                    map.addControl(new GSmallMapControl());
                    map.addControl(new GMapTypeControl());
                    var center;
                    <c:if test="${latitud != '0' && longitud !='0'}">
                    center = new GLatLng(${latitud},${longitud});
                    </c:if>
                    <c:if test="${latitud == '0' and longitud == '0' }">
                    center = new GLatLng(4.668912, -74.08287);
                    </c:if>

                    map.setCenter(center, 13);
                    geocoder = new GClientGeocoder();
                    var marker = new GMarker(center, {draggable: true});
                    map.addOverlay(marker);

                    <c:if test="${latitud != '0' && longitud !='0'}">
                    document.getElementById("lat").value = ${latitud};
                    document.getElementById("lng").value = ${longitud};
                    </c:if>

                    <c:if test="${latitud == '0' and longitud == '0' }">
                    document.getElementById("lat").value = center.lat().toFixed(5);
                    document.getElementById("lng").value = center.lng().toFixed(5);
                    </c:if>

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

            function showAddress(address) {
                var map = new GMap2(document.getElementById("map"));
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


    <body onload="load()" onunload="GUnload()" >

        <form action="#" onsubmit="showAddress(this.address.value); return false">
            <p>        
                <input type="text" size="60" name="address" value="Bogot&aacute; Colombia" />
                <input type="submit" value="Buscar" />
            </p>
        </form>

        <p align="left">

        <form id="datosCoordenadasVitrina" action="${pageContext.request.contextPath}/vitrina/lightbox/guardarCoordenadas.do">
            <label>Latitud</label>
            <input id="lat" type="text" name="latitud" value ="${latitud}" readonly/>

            <label>Longitud</label>
            <input id="lng" type="text" name="longitud" value ="${longitud}"readonly/>

            <input type="hidden"  value="${idVitrina}" name="idVitrina"/>
            <input type="submit" id="editar_coordenadas_vitrina" value="Guardar Coordenadas" />
        </form>
    </p>
    <p>
    <div align="center" id="map" style="width: 600px; height: 400px"><br/></div>
</p>
</div>
<script type="text/javascript">
//<![CDATA[
    if (typeof _gstat !== "undefined")
        _gstat.audience('', 'pagesperso-orange.fr');
//]]>
</script>
</body>

</html>