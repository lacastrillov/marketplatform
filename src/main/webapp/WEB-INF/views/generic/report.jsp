<%-- 
    Document   : navegador
    Created on : 21/11/2013, 12:06:14 AM
    Author     : lacastrillov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String ExtJSLib="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${reportConfig.pluralReportTitle} - Administracion MERCANDO</title>
        <link rel="icon" type="image/icon" href="/img/favicon.png" /> 
        
        <script type="text/javascript">
            var ExtJSLib="<%=ExtJSLib%>";
        </script>
        
        <script src="<%=ExtJSLib%>/examples/shared/include-ext.js"></script>
        <!--<script src="<%=ExtJSLib%>/examples/shared/options-toolbar.js"></script>-->
        
        <!-- ############################ IMPORT LAYOUTS ################################ -->
        
        <c:set var="basePath" value="/vista" ></c:set>
        
        <!-- ############################ IMPORT MODELS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/reportExtModel/${reportName}.htm"/>
        <c:forEach var="childExtReport" items="${reportConfig.childExtReports}">
            <c:import url="${basePath}/${childExtReport.key}/reportExtModel/${childExtReport.value}.htm"/>
        </c:forEach>
        
        <!-- ############################ IMPORT STORES ################################### -->
        
        <c:import url="${basePath}/${entityRef}/reportExtStore/${reportName}.htm"/>
        <c:forEach var="childExtReport" items="${reportConfig.childExtReports}">
            <c:import url="${basePath}/${childExtReport.key}/reportExtStore/${childExtReport.value}.htm">
                <c:param name="restSession" value="${reportConfig.restSession}"/>
            </c:import>
        </c:forEach>
        
        <!-- ############################ IMPORT VIEWS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/reportExtView/${reportName}.htm">
            <c:param name="typeView" value="Parent"/>
        </c:import>
        <c:forEach var="childExtReport" items="${reportConfig.childExtReports}">
            <c:import url="${basePath}/${childExtReport.key}/reportExtView/${childExtReport.value}.htm">
                <c:param name="typeView" value="Child"/>
            </c:import>
        </c:forEach>
        
        <c:import url="${basePath}/${entityRef}/reportExtController/${reportName}.htm">
            <c:param name="typeController" value="Parent"/>
        </c:import>
        <c:forEach var="childExtReport" items="${reportConfig.childExtReports}">
            <c:import url="${basePath}/${childExtReport.key}/reportExtController/${childExtReport.value}.htm">
                <c:param name="typeController" value="Child"/>
            </c:import>
        </c:forEach>
        
        <c:import url="${basePath}/${entityRef}/reportViewportExtView/${reportName}.htm"/>
        
        <c:import url="${basePath}/${entityRef}/reportExtInit/${reportName}.htm"/>
        
        <!-- ############################ IMPORT COMPONENTS ################################### -->
        
        <jsp:include page="/WEB-INF/views/generic/scripts/components/CommonExtView.jsp" />
        
        <!-- ############################ IMPORT CONFIG ################################### -->
        
        <jsp:include page="/WEB-INF/views/generic/scripts/config/MVCExtController.jsp" />
        
        <script src="<%=request.getContextPath()%>/js/util/Util.js"></script>
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navegador.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/gridTemplateStyles.css">
        
    </head>
    <body>
        <div id="headerHtml" style="display:none;">
            <h1>Administraci&oacute;n MERCANDO</h1>
            <a class="logout" href="<%=request.getContextPath()%>/security_logout">&nbsp;Cerrar sesi&oacute;n&nbsp;</a>
            <a class="home" href="<%=request.getContextPath()%>/home?redirect=user">&nbsp;Inicio&nbsp;</a>
            <sec:authentication var="user" property="principal" />
            <sec:authorize access="isAuthenticated()">
                <p class="userSession"><b>${user.username}</b> - ${user.nombre} ${user.apellidos}</p>
            </sec:authorize>
        </div>
        <script type="text/javascript">
            var navegadorExtInit= new EntityExtInit();
        </script>
    </body>
</html>
