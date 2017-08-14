<%-- 
    Document   : header.jsp
    Created on : 13/08/2017, 11:59:56 AM
    Author     : lacastrillov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="headerHtml" style="display:none;">
    <h1>Administraci&oacute;n HABITARES</h1>
    <a class="logout" href="<%=request.getContextPath()%>/security_logout">&nbsp;Cerrar sesi&oacute;n&nbsp;</a>
    <a class="home" href="<%=request.getContextPath()%>/account/home?redirect=user">&nbsp;Inicio&nbsp;</a>
    <sec:authentication var="user" property="principal" />
    <sec:authorize access="isAuthenticated()">
        <p class="userSession"><b>${user.username}</b> - ${user.nombre} ${user.apellidos}</p>
    </sec:authorize>
</div>
<script type="text/javascript">
    var navegadorExtInit= new EntityExtInit();
</script>
