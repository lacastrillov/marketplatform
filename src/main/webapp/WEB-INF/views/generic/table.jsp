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
        <title>${viewConfig.pluralEntityTitle} - Administracion MERCANDO</title>
        <link rel="icon" type="image/icon" href="/img/favicon.png" /> 
        
        <script type="text/javascript">
            var ExtJSLib="<%=ExtJSLib%>";
        </script>
        
        <script src="<%=ExtJSLib%>/examples/shared/include-ext.js"></script>
        <!--<script src="<%=ExtJSLib%>/examples/shared/options-toolbar.js"></script>-->
        
        <style>
            .x-html-editor-input textarea{white-space: pre !important;}
        </style>
        
        <!-- ############################ IMPORT LAYOUTS ################################ -->
        
        <c:set var="basePath" value="/vista" ></c:set>
        
        <!-- ############################ IMPORT MODELS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/${tableName}/entityExtModel.htm"/>
        
        <!-- ############################ IMPORT STORES ################################### -->
        
        <c:import url="${basePath}/${entityRef}/${tableName}/entityExtStore.htm"/>
        
        <!-- ############################ IMPORT VIEWS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/${tableName}/entityExtView.htm"/>
        
        <!-- ############################ IMPORT CONTROLLERS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/${tableName}/entityExtController.htm"/>
        
        <!-- ############################ IMPORT BASE ELEMENTES ################################### -->
        
        <c:import url="${basePath}/${entityRef}/${tableName}/entityViewportExtView.htm"/>
        
        <c:import url="${basePath}/${entityRef}/${tableName}/entityExtInit.htm"/>
        
        <!-- ############################ IMPORT COMPONENTS ################################### -->
        
        <jsp:include page="/WEB-INF/views/generic/scripts/components/CommonExtView.jsp" />
        
        <!-- ############################ IMPORT CONFIG ################################### -->
        
        <jsp:include page="/WEB-INF/views/generic/scripts/config/MVCExtController.jsp" />
        
        
        <script src="<%=request.getContextPath()%>/js/util/Util.js"></script>
        <script type="text/javascript" src=http://maps.google.com/maps?file=api&amp;v=3&amp;key=AIzaSyD_IP-Js3_ETbJ9psH605u-4iqZihp_-Jg&sensor=true"></script>
        <script src="<%=request.getContextPath()%>/js/util/GoogleMaps.js"></script>
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navegador.css">
        
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
