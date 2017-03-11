<%-- 
    Document   : navegador
    Created on : 21/11/2013, 12:06:14 AM
    Author     : lacastrillov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String ExtJSLib="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion MERCANDO</title>
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
        
        <c:forEach var="modelER" items="${modelsEntityRef}">
            <c:import url="${basePath}/${modelER}/entityExtModel.htm"/>
        </c:forEach>
        
        <!-- ############################ IMPORT STORES ################################### -->
        
        <c:forEach var="modelER" items="${modelsEntityRef}">
            <c:import url="${basePath}/${modelER}/entityExtStore.htm"/>
        </c:forEach>
        
        <!-- ############################ IMPORT VIEWS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/entityExtView.htm">
             <c:param name="typeView" value="Parent"/>
        </c:import>
        <c:forEach var="viewsChildER" items="${viewsChildEntityRef}">
            <c:import url="${basePath}/${viewsChildER}/entityExtView.htm">
                <c:param name="typeView" value="Child"/>
            </c:import>
        </c:forEach>
        
        <!-- ############################ IMPORT CONTROLLERS ################################### -->
        
        <c:import url="${basePath}/${entityRef}/entityExtController.htm">
            <c:param name="typeController" value="Parent"/>
        </c:import>
        <c:forEach var="controllerChildER" items="${viewsChildEntityRef}">
            <c:import url="${basePath}/${controllerChildER}/entityExtController.htm">
                <c:param name="typeController" value="Child"/>
            </c:import>
        </c:forEach>
        
        <!-- ############################ IMPORT INTERFACES ################################### -->
        
        <c:forEach var="interfacesER" items="${interfacesEntityRef}">
            <c:import url="${basePath}/${interfacesER}/entityExtInterfaces.htm"/>
        </c:forEach>
        
        <c:forEach var="interfacesChildER" items="${interfacesChildEntityRef}">
            <c:import url="${basePath}/${interfacesChildER}/entityExtInterfaces.htm"/>
        </c:forEach>
        
        <!-- ############################ IMPORT BASE ELEMENTES ################################### -->
        
        <c:import url="${basePath}/${entityRef}/entityViewportExtView.htm"/>
        
        <c:import url="${basePath}/${entityRef}/entityExtInit.htm"/>
        
        <!-- ############################ IMPORT COMPONENTS ################################### -->
        
        <jsp:include page="/WEB-INF/views/generic/scripts/components/CommonExtView.jsp" />
        
        <!-- ############################ IMPORT CONFIG ################################### -->
        
        <jsp:include page="/WEB-INF/views/generic/scripts/config/MVCExtController.jsp" />
        
        
        <script src="<%=request.getContextPath()%>/js/util/Util.js"></script>
        <script src="<%=request.getContextPath()%>/js/util/FileUploader.js"></script>
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navegador.css">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/gridTemplateStyles.css">
        
    </head>
    <body>
        <div id="headerHtml" style="display:none;">
            <h1 >
                Administraci&oacute;n MERCANDO
                <a class="logout" href="<%=request.getContextPath()%>/j_spring_security_logout">&nbsp;Cerrar sesi&oacute;n&nbsp;</a>
                <a class="home" href="<%=request.getContextPath()%>/home">&nbsp;Home&nbsp;</a>
            </h1>
        </div>
        <script type="text/javascript">
            var navegadorExtInit= new EntityExtInit();
        </script>
    </body>
</html>
