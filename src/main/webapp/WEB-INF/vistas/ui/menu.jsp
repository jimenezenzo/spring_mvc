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
    <sec:authorize access="hasRole('Admin')">
        <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/admin/altas-usuario">
                <i class="ni ni-tv-2 text-primary"></i> Alta usuario
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('Medico')">
        <li class="nav-item">
            <a class="nav-link" href="../index.html">
                <i class="ni ni-tv-2 text-primary"></i> Dashboard
            </a>
        </li>
    </sec:authorize>
    <sec:authorize access="hasRole('Paciente')">
        <li class="nav-item">
            <a class="nav-link" href="../index.html">
                <i class="ni ni-tv-2 text-primary"></i> Dashboard
            </a>
        </li>
    </sec:authorize>
</ul>
<!-- Divider -->
<hr class="my-3">
<!-- Heading -->
<h6 class="navbar-heading text-muted">Documentation</h6>
<!-- Navigation -->
<ul class="navbar-nav mb-md-3">
    <li class="nav-item">
        <a class="nav-link" href="https://demos.creative-tim.com/argon-dashboard/docs/getting-started/overview.html">
            <i class="ni ni-spaceship"></i> Getting started
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="https://demos.creative-tim.com/argon-dashboard/docs/foundation/colors.html">
            <i class="ni ni-palette"></i> Foundation
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="https://demos.creative-tim.com/argon-dashboard/docs/components/alerts.html">
            <i class="ni ni-ui-04"></i> Components
        </a>
    </li>
</ul>
