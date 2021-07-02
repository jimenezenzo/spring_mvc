<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        Medico
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
              integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
              crossorigin=""/>
        <link rel="stylesheet"
              href="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.css"/>
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
                integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
                crossorigin=""></script>
        <script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.js"></script>
        <script src="${pageContext.request.contextPath}/js/maps/mapaMedico.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col">
                <div class="card shadow">

                    <div id="latitud" >
                            ${latitud}
                    </div>
                    <div id="longitud">
                            ${longitud}
                    </div>

                    <div class="card-header border-0">
                        <h3 class="mb-0">Hoja de ruta medico</h3>
                    </div>
                    <div class="card-body">
                        <div id="mapaMedico" class="h-100vh"></div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>