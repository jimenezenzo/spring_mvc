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
    <jsp:body>
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <h3 class="mb-0">Mis citas</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12 d-flex">
                                <div class="col-6 col-md-3 mb-2">
                                    <a href="${pageContext.request.contextPath}/paciente/citas/create" class="btn btn-primary">
                                        Nueva cita en consultorio
                                    </a>
                                </div>
                                <div class="col-6 col-md-3">
                                    <a href="${pageContext.request.contextPath}/paciente/citas/createDomicilio" class="btn btn-primary">
                                        Solicitar Médico a domicilio
                                    </a>
                                </div>
                            </div>
                        </div>

                        <c:choose>
                            <c:when test="${not empty citas}">
                                <c:forEach items="${citas}" var="cita">
                                    <div class="col-12 col-md-8 col-lg-10 mb-2">
                                        <div class="card card-stats mb-4 mb-xl-0">
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="col">
                                                        <h3 class="card-title text-uppercase mb-0">${cita.especialidad.descripcion}</h3>
                                                        <p class="font-weight-bold text-muted mb-0">
                                                            Medico: ${cita.medico.persona.apellido} ${cita.medico.persona.nombre} <br>
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
                                                    <%--<c:if test="${cita.tipoCita.id == 1}">
                                                        <div class="col-auto">
                                                            <div class="icon icon-shape bg-danger text-white rounded-circle shadow">
                                                                <a style="color:inherit" href="${pageContext.request.contextPath}/paciente/mapa_cita/${cita.id}"><i class="ni ni-pin-3"></a></i>
                                                            </div>
                                                        </div>
                                                    </c:if>--%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
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
