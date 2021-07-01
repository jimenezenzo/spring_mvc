<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        Paciente
    </jsp:attribute>
    <jsp:attribute name="style">
        <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
              integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
              crossorigin=""/>
        <link rel="stylesheet" href="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.css" />
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
                integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
                crossorigin=""></script>
        <script src="https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine.js"></script>
        <script src="${pageContext.request.contextPath}/js/maps/mapaCita.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                    <%--    <h3 class="mb-0">Cita ${cita.id}</h3> --%>
                        <div class="col-12 col-md-8 col-lg-10 mb-2">
                            <div class="card card-stats mb-4 mb-xl-0">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col">
                                            <h3 class="card-title text-uppercase mb-0">${cita.especialidad.descripcion}</h3>
                                            <p class="font-weight-bold text-muted mb-0">
                                                Medico: ${cita.medico.persona.apellido} ${cita.medico.persona.nombre} <br>
                                                Fecha y hora: ${cita.fechaHoraFormateada()}hs. <br>
                                                Estado: ${cita.getUltimaHistoria().estado.descripcion}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <div id="mapaPaciente" class="h-75vh"></div>
                    </div>
                </div>
            </div>
        </div>

        <div id="IdCitaHidden" hidden>${cita.id}</div>
        <div id="nombreMedicoHidden" hidden>${cita.medico.persona.nombre}</div>
        <div id="apellidoMedicoHidden" hidden>${cita.medico.persona.apellido}</div>
        <div id="especialidadHidden" hidden>${cita.especialidad.descripcion}</div>
        <div id="latitudHidden" hidden>${cita.medico.ubicacion.lat_actual}</div>
        <div id="longitudHidden" hidden>${cita.medico.ubicacion.long_actual}</div>

    </jsp:body>
</t:layout>
