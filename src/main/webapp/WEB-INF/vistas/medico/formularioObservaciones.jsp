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
                            <div class="card border-default my-2">
                                <div class="card-body">
                                    <c:if test="${historia.estado == 'PENDIENTE'}">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-info">${historia.estado}</span></h5>
                                    </c:if>
                                    <c:if test="${historia.estado == 'OBSERVADO'}">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-warning">${historia.estado}</span></h5>
                                    </c:if>
                                    <c:if test="${historia.estado == 'FINALIZADO'}">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-success">${historia.estado}</span></h5>
                                    </c:if>
                                    <c:if test="${historia.estado == 'CANCELADO'}">
                                        <h5 class="card-title"><span class="badge-md badge-pill badge-danger">${historia.estado}</span></h5>
                                    </c:if>
                                    <p class="card-text">${historia.observacion}</p>
                                    <p><a href="${pageContext.request.contextPath}/cita/file/${historia.archivo}/descarga">${historia.archivo}</a></p>
                                    <small class="text-muted">${historia.fechaFormato()}</small>
                                </div>
                            </div>
                        </c:forEach>


                        <sec:authorize access="hasRole('Paciente')">
                            <c:if test="${cita.ultimaHistoria.estado == 'PENDIENTE'}">
                                <form action="${pageContext.request.contextPath}/cita/consultorio/${idCita}/cancelar" method="post">
                                    <div>
                                        <button type="submit" class="btn btn-danger">
                                            <i class="ni ni-scissors"></i> Cancelar turno
                                        </button>
                                    </div>
                                </form>
                            </c:if>
                        </sec:authorize>

                        <sec:authorize access="hasRole('Medico')">
                            <form:form action="${pageContext.request.contextPath}/cita/consultorio/${idCita}/accion" method="post" enctype="multipart/form-data" modelAttribute="datosCitaHistoria" cssClass="my-4">
                                <div class="form-group">
                                    <label class="form-control-label" for="observacion">Observacion</label>
                                    <form:textarea  id="observacion" rows="5" cols="30" class="form-control" path="observacion"></form:textarea>
                                </div>
                                <div class="d-flex">
                                    <div class="form-group w-25 mr-2">
                                        <label class="form-control-label" for="estado">Estado</label>
                                        <form:select path="estado" id="estado" class="form-control">
                                            <form:option value="observado" selected="">Observar</form:option>
                                            <form:option value="finalizado">Finalizar</form:option>
                                            <form:option value="cancelado">Cancelar</form:option>
                                        </form:select>
                                    </div>
                                    <div class="form-group w-50">
                                        <label class="form-control-label" for="file">Archivo</label>
                                        <form:input type="file" id="file" class="form-control" path="file" />
                                    </div>
                                </div>
                                <div>
                                    <button type="submit" class="btn btn-primary">
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