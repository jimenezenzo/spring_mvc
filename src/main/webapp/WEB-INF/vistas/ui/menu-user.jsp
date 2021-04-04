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
        <h6 class="text-overflow m-0">Welcome prueba!</h6>
    </div>
    <a href="../examples/profile.html" class="dropdown-item">
        <i class="ni ni-single-02"></i>
        <span>My profile</span>
    </a>
    <a href="../examples/profile.html" class="dropdown-item">
        <i class="ni ni-settings-gear-65"></i>
        <span>Settings</span>
    </a>
    <a href="../examples/profile.html" class="dropdown-item">
        <i class="ni ni-calendar-grid-58"></i>
        <span>Activity</span>
    </a>
    <a href="../examples/profile.html" class="dropdown-item">
        <i class="ni ni-support-16"></i>
        <span>Support</span>
    </a>
    <div class="dropdown-divider"></div>
    <a href="${pageContext.request.contextPath}/logout" class="dropdown-item">
        <i class="ni ni-user-run"></i>
        <span>Logout</span>
    </a>
</div>
