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
                        <h3 class="mb-0">Solicitud de médico a domicilio</h3>
                    </div>
                    <div class="card-body">
                        <div class="col-12">
                            <c:if test="${not empty errores}">
                                <div class="alert alert-danger" role="alert">
                                    <p>Errores:</p>
                                    <ul>
                                        <c:forEach items="${errores}" var="error">
                                            <li>${error}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:if>
                        </div>

                        <form:form action="${pageContext.request.contextPath}/paciente/citas/storeCitaDomicilio" method="post" modelAttribute="datos">
                            <div class="form-group">
                                <textarea name="sintomas" rows="5" cols="30" placeholder="Ingrese síntomas" class="form-control"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="urgente" id="label_urg">Urgencia </label>
                                <input type="checkbox" id="urgente" name="urgente">
                            </div>

                            <input type="text" id="latitud" name="latitud" hidden>
                            <input type="text" id="longitud" name="longitud" hidden>

                            <button class="btn btn-primary" type="submit">
                                Solicitar médico
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
