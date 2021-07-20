<%--
  Created by IntelliJ IDEA.
  User: enzo-
  Date: 7/6/2021
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:layout>
    <jsp:attribute name="title">
        Mis citas
    </jsp:attribute>
    <jsp:attribute name="script">
        <script src="${pageContext.request.contextPath}/js/citas/createCitaDomicilio.js"></script>
    </jsp:attribute>
    <jsp:body>
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <h3 class="mb-0">${cita.paciente.persona.apellido} ${cita.paciente.persona.nombre}</h3>
                    </div>
                    <div class="col-12">

                        <div class="d-flex mb-4">
                            <div class="w-50">
                                <span class="font-weight-bold">Nombre y apellido:</span> ${cita.paciente.persona.nombre} ${cita.paciente.persona.apellido}<br>
                                <span class="font-weight-bold">Sexo:</span> ${cita.paciente.persona.sexo}<br>
                                <span class="font-weight-bold">Documento:</span> ${cita.paciente.persona.tipoDocumento} ${cita.paciente.persona.numeroDocumento}<br>
                                <span class="font-weight-bold">Fecha de nacimiento:</span> ${cita.paciente.persona.fechaFormato()}
                            </div>
                            <div class="w-50">
                                <span class="font-weight-bold">Fecha:</span> ${cita.fechaFormateada()}<br>
                                <span class="font-weight-bold">Hora:</span> ${cita.hora.toString()}<br>
                                <span class="font-weight-bold">Consultorio:</span> ${cita.medico.consultorio.descripcion}<br>
                                <span class="font-weight-bold">Direccion:</span> ${cita.medico.consultorio.domicilio}
                            </div>
                        </div>

                        <p class="badge-md badge-primary mb-2"><i class="ni ni-collection"></i> Historial </p>
                        <c:forEach items="${cita.citaHistoriaList}" var="historia">
                            <c:if test="${historia.estado == 'PENDIENTE'}">
                                <div class="card border-default my-2">
                                    <div class="card-body">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-info">${historia.estado}</span></h5>
                                        <p class="card-text">${historia.observacion}</p>
                                        <small class="text-muted">${historia.fechaFormato()}</small>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${historia.estado == 'OBSERVADO'}">
                                <div class="card border-default my-2">
                                    <div class="card-body">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-warning">${historia.estado}</span></h5>
                                        <p class="card-text">${historia.observacion}</p>
                                        <small class="text-muted">${historia.fechaFormato()}</small>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${historia.estado == 'FINALIZADO'}">
                                <div class="card border-default my-2">
                                    <div class="card-body">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-success">${historia.estado}</span></h5>
                                        <p class="card-text">${historia.observacion}</p>
                                        <small class="text-muted">${historia.fechaFormato()}</small>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${historia.estado == 'CANCELADO'}">
                                <div class="card border-default my-2">
                                    <div class="card-body">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-danger">${historia.estado}</span></h5>
                                        <p class="card-text">${historia.observacion}</p>
                                        <small class="text-muted">${historia.fechaFormato()}</small>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>

                        <sec:authorize access="hasRole('Medico')">
                            <form:form action="${pageContext.request.contextPath}/cita/consultorio/${idCita}/accion" method="post" cssClass="mb-4">
                                <div class="form-group">
                                    <textarea name="observacion" rows="5" cols="30" placeholder="Ingrese diagnostico" class="form-control"></textarea>
                                </div>
                                <div class="form-group w-25">

                                    <select name="estado" id="" class="form-control">
                                        <option value="observado" selected>Observar</option>
                                        <option value="finalizado">Finalizar</option>
                                        <option value="cancelado">Cancelar</option>
                                    </select>
                                </div>

                                <div class="d-flex">
                                    <button type="submit" value="observar" class="btn btn-primary">
                                        <i class="ni ni-send"></i> Enviar
                                    </button>
                                </div>
                            </form:form>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>