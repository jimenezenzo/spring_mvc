<%--
  Created by IntelliJ IDEA.
  User: enzo-
  Date: 26/6/2021
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:attribute name="title">
        Nueva alta
    </jsp:attribute>
    <jsp:body>
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <h3 class="mb-0">Ingresar nueva alta persona</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty exito}">
                            <div class="alert alert-success" role="alert">
                                    ${exito}
                            </div>
                        </c:if>
                        <c:if test="${not empty errores}">
                            <div class="alert alert-danger" role="alert">
                                <p>Corrija los siguientes errores:</p>
                                <ul>
                                    <c:forEach items="${errores}" var="error">
                                        <li>${error}</li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </c:if>
                        <div class="text-center text-muted my-2">
                            <small>Ingrese los datos personales</small>
                        </div>
                        <form:form role="form" action="${pageContext.request.contextPath}/administrador/registrar-paciente" method="post" modelAttribute="datosAltaUsuario">
                            <div class="d-flex">
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative mb-3">
                                        <form:input path="nombre" class="form-control" cssErrorClass="form-control border-danger" placeholder="Nombre" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative mb-3">
                                        <form:input path="apellido" cssErrorClass="form-control border-danger" class="form-control" placeholder="Apellido" type="text"/>
                                    </div>
                                </div>
                            </div>

                            <div class="d-flex">
                                <div class="form-group col-6">
                                    <form:select path="tipoDocumento" class="form-control">
                                        <form:option value="DNI">DNI</form:option>
                                        <form:option value="PASAPORTE">Pasaporte</form:option>
                                    </form:select>
                                </div>
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative">
                                        <form:input path="numeroDocumento" class="form-control" placeholder="Número de documento" type="text"/>
                                    </div>
                                </div>
                            </div>

                            <div class="d-flex">
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative">
                                        <form:input path="fechaNacimiento" name="fechaNacimiento" class="form-control" type="date"/>
                                    </div>
                                </div>
                                <div class="form-group col-6">
                                    <form:select path="sexo" class="form-control">
                                        <form:option value="Masculino">Masculino</form:option>
                                        <form:option value="Femenino">Femenino</form:option>
                                        <form:option value="Otre">Otre</form:option>
                                    </form:select>
                                </div>
                            </div>

                            <div class="d-flex">
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative mb-3">
                                        <form:input path="email" cssErrorClass="form-control border-danger" class="form-control" placeholder="email" type="email"/>
                                    </div>
                                </div>
                                <div class="form-group col-6">
                                    <form:select path="rol" class="form-control">
                                        <form:option value="Paciente">Paciente</form:option>
                                        <form:option value="Medico">Medico</form:option>
                                    </form:select>
                                </div>
                            </div>

                            <div class="d-flex">
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative">
                                        <form:input path="numeroAfiliado" class="form-control" placeholder="Número de afiliado" type="text"/>
                                    </div>
                                </div>
                                <div class="form-group col-6">
                                    <div class="input-group input-group-alternative">
                                        <form:input path="matricula" class="form-control" placeholder="Número de matricula" type="text"/>
                                    </div>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary col-6 mt-4">
                                Registrar
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
