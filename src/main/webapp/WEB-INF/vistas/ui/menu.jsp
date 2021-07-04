<%--
  Created by IntelliJ IDEA.
  User: enzo-
  Date: 3/4/2021
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Navigation -->
<ul class="navbar-nav">
    <sec:authorize access="hasRole('Administrador')">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/administrador/altas-usuario">
                <i class="ni ni-tv-2 text-primary"></i> Alta usuario
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('Medico')">
        <li class="nav-item">
            <a class="nav-link" href="../index.html">
                <i class="ni ni-tv-2 text-primary"></i> Mis citas para hoy
            </a>
            <a class="nav-link" href="../index.html">
                <i class="ni ni-tv-2 text-primary"></i> Mis especialidades
            </a>
            <a class="nav-link" href="${pageContext.request.contextPath}/medico/mi-agenda">
                <i class="ni ni-tv-2 text-primary"></i> Mi agenda
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('Paciente')">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/paciente/citas/index">
                <i class="ni ni-tv-2 text-primary"></i> Mis citas
            </a>
        </li>
    </sec:authorize>

