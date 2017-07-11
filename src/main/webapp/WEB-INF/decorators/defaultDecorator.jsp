<%-- 
    Document   : defaultDecorator
    Created on : 8/08/2016, 12:00:48 PM
    Author     : desarrollador
--%>


<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/icon" href="/img/favicon.png" />
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title><sitemesh:write property='title'>Mercando</sitemesh:write></title>

        <script type="text/javascript" src="/js/libs/jquery/jquery-3.1.0.min.js"></script>
        <script type="text/javascript" src="/js/util/Util.js"></script>
        
        <!-- Bootstrap -->
        <link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/css/styles.css">
        
        <sitemesh:write property='head'/>
    </head>
    <body>

        <header>
            <div class="container">
                <a style="display: inline-block;width: 300px" class="brand col-xs-6 col-md-2" href="/">
                    <img style="float:left" src="/img/logo.png" class="img-responsive">
                    <p style="float:left; color: white; font-size: 30px; font-weight: bold; margin: 14px;">MERCANDO</p>
                </a>
                <a href="#" class="col-xs-6 col-md-2 pull-right registro">
                    Registrate aqu&iacute;
                </a>
            </div>
        </header>
        
        <sitemesh:write property='body'/>

        <footer>
            <div class="container">
                <div class="col-md-4">
                    <p>Legales | <a href="#">T&eacute;rminos de  uso</a></p>
                </div>
            </div>
        </footer>
        
    </body>
</html>