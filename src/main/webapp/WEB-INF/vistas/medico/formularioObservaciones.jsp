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
                        <h3 class="mb-0">Cargar Observaciones del paciente</h3>
                    </div>
                    <div class="card-body">

                        <form:form action="${pageContext.request.contextPath}/medico/store-observaciones/${idCita}" method="post" modelAttribute="datos">
                            <div class="form-group">
                                <textarea name="observacion" rows="5" cols="30" placeholder="Ingrese diagnostico" class="form-control"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Cargar</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>