<%--
  Created by IntelliJ IDEA.
  User: enzo-
  Date: 3/4/2021
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
    <div class=" dropdown-header noti-title">
        <h6 class="text-overflow m-0">Opciones</h6>
    </div>
    <div class="dropdown-divider"></div>
    <a href="${pageContext.request.contextPath}/logout" class="dropdown-item">
        <i class="ni ni-user-run"></i>
        <span>Cerrar Sesión</span>
    </a>
</div>
