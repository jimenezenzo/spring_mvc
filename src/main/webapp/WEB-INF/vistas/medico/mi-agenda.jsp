<%--
  Created by IntelliJ IDEA.
  User: enzo-
  Date: 3/7/2021
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:layout>
    <jsp:attribute name="title">
        Mi agenda
    </jsp:attribute>
    <jsp:attribute name="script">
         <script src="${pageContext.request.contextPath}/js/agenda/agenda.js"></script>
    </jsp:attribute>
    <jsp:body>
        <!-- Table -->
        <div class="row">
            <div class="col">
                <div class="card shadow">
                    <div class="card-header border-0">
                        <h3 class="mb-0">Mi agenda</h3>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty estado}">
                            <div class="card-header border-0">
                                <div class="alert alert-success" role="alert">
                                        ${estado}
                                </div>
                            </div>
                        </c:if>
                        <c:choose>
                            <c:when test="${not empty agendas}">
                                <div class="table-responsive">
                                    <!-- Projects table -->
                                    <table class="table align-items-center table-flush">
                                        <thead class="thead-light">
                                        <tr>
                                            <th scope="col">Dia</th>
                                            <th scope="col">Activo</th>
                                            <th scope="col">Hora de inicio</th>
                                            <th scope="col">Hora de fin</th>
                                            <th scope="col">Accion</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${agendas}" var="agenda">
                                            <tr>
                                                <form:form action="${pageContext.request.contextPath}/medico/actualizar-agenda" modelAttribute="objAgenda" method="post">
                                                    <th>${agenda.dia} <form:hidden path="id" value="${agenda.id}" /> <form:hidden path="dia" value="${agenda.dia}" /></th>
                                                    <td>
                                                        <label class="custom-toggle">
                                                            <input type="checkbox" id="activo" name="activo" <c:if test="${agenda.activo}">checked</c:if> >
                                                            <span class="custom-toggle-slider rounded-circle"></span>
                                                        </label>
                                                    </td>
                                                    <td>
                                                        <form:input path="horaDesde" type="time" class="form-control" value="${agenda.horaDesde}" />
                                                    </td>
                                                    <td>
                                                        <form:input path="horaHasta" type="time" class="form-control" value="${agenda.horaHasta}" />
                                                    </td>
                                                    <td>
                                                        <button class="btn btn-primary" type="submit">
                                                            Actualizar
                                                        </button>
                                                    </td>
                                                </form:form>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-warning" role="alert">
                                    No tienes cargado ningun dia en la agenda.
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
