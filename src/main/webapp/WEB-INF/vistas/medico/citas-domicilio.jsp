<%--
  Created by IntelliJ IDEA.
  User: enzo-
  Date: 21/5/2021
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:layout>
    <jsp:attribute name="title">
        Mis citas
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
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <h3 class="mb-0">Mis citas</h3>
                    </div>
                    <div class="card-body">

                        <c:if test="${citasDelDia}">
                            <h2>Citas del dia a domicilio</h2>
                        </c:if>
                        <c:if test="${!citasDelDia}">
                            <ul class="nav nav-pills nav-fill flex-column flex-sm-row mb-4" id="tabs-text" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link mb-sm-3 mb-md-0 active" href="${pageContext.request.contextPath}/medico/citas-domicilio" role="tab">Citas a domicilio</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link mb-sm-3 mb-md-0 active" href="${pageContext.request.contextPath}/medico/citas-consultorio" role="tab">Citas en consultorio</a>
                                </li>
                            </ul>
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty citas}">
                                <c:forEach items="${citas}" var="cita">

                                        <div class="col-12 col-md-8 col-lg-10 mb-2">
                                         <div class="card card-stats mb-4 mb-xl-0">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col">
                                                        <p class="font-weight-bold text-muted mb-0">
                                                            Paciente: ${cita.paciente.persona.apellido} ${cita.paciente.persona.nombre} <br>
                                                            Sintomas: ${cita.sintomas} <br>
                                                            Estado: ${cita.getUltimaHistoria().observacion} <br>
                                                                ${cita.getFechaRegistroFormateada()}
                                                        </p>
                                                    </div>
                                                    <div class="col-auto">
                                                        <div class="icon icon-shape bg-danger text-white rounded-circle shadow">
                                                            <a style="color:inherit" href="${pageContext.request.contextPath}/medico/mapa/${cita.id}"><i class="ni ni-pin-3"></a></i>
                                                        </div>
                                                        <div class="icon icon-shape bg-danger text-white rounded-circle shadow">
                                                            <a style="color:inherit" href="${pageContext.request.contextPath}/medico/formulario-observaciones/${cita.id}"><i class="ni ni-ruler-pencil"></a></i>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </c:forEach>
                                <ul class="nav nav-pills nav-fill flex-column flex-sm-row mb-4" id="tabs-text" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link mb-sm-3 mb-md-0 active" href="${pageContext.request.contextPath}/medico/mapa-citas-domicilio-todas" role="tab">Ver todas en el mapa</a>
                                    </li>
                                </ul>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-warning" role="alert">
                                    No tienes citas a domicilio.
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>

