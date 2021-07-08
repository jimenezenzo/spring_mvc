<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <script src="${pageContext.request.contextPath}/js/maps/mapaMedicoTodos.js"></script>
    </jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <c:choose>
                        <c:when test="${not empty citas}">
                            <c:set var="count" value="0" scope="page" />
                            <c:forEach items="${citas}" var="cita">
                                <c:set var="count" value="${count + 1}" scope="page"/>
                                <div id="latitud_${count}" style="display: none" >
                                        ${cita.latitud}
                                </div>
                                <div id="longitud_${count}" style="display: none">
                                        ${cita.longitud}
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-warning" role="alert">
                                No tienes citas a domicilio.
                            </div>
                        </c:otherwise>
                    </c:choose>
                    <div id="cantidad" style="display: none">
                            ${cantidad}
                    </div>
                    <div class="card-body">
                        <div id="mapaMedico" class="h-100vh"></div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>