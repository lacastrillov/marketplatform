<%-- 
    Document   : publicDecorator
    Created on : 13/03/2017, 09:32:30 PM
    Author     : lacastrillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title><sitemesh:write property='title'>Habitares</sitemesh:write> - Habitares</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--Less styles -->
        <!-- Other Less css file //different less files has different color scheam
             <link rel="stylesheet/less" type="text/css" href="/themes/less/simplex.less">
             <link rel="stylesheet/less" type="text/css" href="/themes/less/classified.less">
             <link rel="stylesheet/less" type="text/css" href="/themes/less/amelia.less">  MOVE DOWN TO activate
        -->
        <!--<link rel="stylesheet/less" type="text/css" href="/themes/less/bootshop.less">
        <script src="/themes/js/less.js" type="text/javascript"></script> -->

        <!-- Bootstrap style --> 
        <link id="callCss" rel="stylesheet" href="/themes/united/bootstrap.min.css" media="screen"/>
        <link href="/themes/css/base.css" rel="stylesheet" media="screen"/>
        <!-- Bootstrap style responsive -->	
        <link href="/themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
        <link href="/themes/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Google-code-prettify -->	
        <link href="/themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
        <!-- Bootstrap -->
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        <!-- fav and touch icons -->
        <!--<link rel="shortcut icon" href="/themes/images/ico/favicon.ico">-->
        <link rel="icon" type="image/icon" href="/img/habitares.png" />
        
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="/themes/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="/themes/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="/themes/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="/themes/images/ico/apple-touch-icon-57-precomposed.png">
        <style type="text/css" id="enject"></style>
        
        <!-- Placed at the end of the document so the pages load faster ============================================= -->
        <script src="http://localhost/ext-4.2.1/examples/shared/include-ext.js"></script>
        <script src="/themes/js/jquery.js" type="text/javascript"></script>
        <script src="/themes/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="/themes/js/google-code-prettify/prettify.js"></script>

        <script src="/themes/js/bootshop.js"></script>
        <script src="/themes/js/jquery.lightbox-0.5.js"></script>
        <script src="/js/util/Util.js"></script>
        <script src="/js/util/Validation.js"></script>
        <script>
            Ext.context= "";
        </script>
        
        <sitemesh:write property='head'/>
    </head>
    <body>
        
        <jsp:include page="/WEB-INF/components/header.jsp"></jsp:include>
        
        <sitemesh:write property='body'/>
        
        <jsp:include page="/WEB-INF/components/footer.jsp"></jsp:include>
        
        <jsp:include page="/WEB-INF/components/themeSwitcher.jsp"></jsp:include>
        
    </body>
</html>