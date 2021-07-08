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
<

<t:layout>
    <jsp:attribute name="title">
        Home
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
                        <h3 class="mb-0">Home</h3>
                    </div>
                    <div class="card-body">
                        <c:choose>
                            <c:when test="${not empty citas}">
                                    <c:if test="${modoGuardia}">
                                        <h2>Citas a domicilio</h2>
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
                                                                    <i class="ni ni-calendar-grid-58"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${!modoGuardia}">
                                        <h2>Citas del dia para consultorio</h2>
                                        <c:forEach items="${citas}" var="cita">
                                            <div class="col-12 col-md-8 col-lg-10 mb-2">
                                                <div class="card card-stats mb-4 mb-xl-0">
                                                    <div class="card-body">
                                                        <div class="row">
                                                            <div class="col">
                                                                <h3 class="card-title text-uppercase mb-0">${cita.especialidad.descripcion}</h3>
                                                                <p class="font-weight-bold text-muted mb-0">
                                                                    Paciente: ${cita.paciente.persona.apellido} ${cita.paciente.persona.nombre} <br>
                                                                    Fecha: ${cita.fechaFormateada()} <br>
                                                                    Hora: ${cita.hora.toString()}hs <br>
                                                                    Estado: ${cita.getUltimaHistoria().observacion}
                                                                </p>
                                                            </div>
                                                            <div class="col-auto">
                                                                <div class="icon icon-shape bg-danger text-white rounded-circle shadow">
                                                                    <i class="ni ni-calendar-grid-58"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-warning" role="alert">
                                    No tienes citas.
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>

