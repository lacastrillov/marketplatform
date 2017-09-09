<%-- 
    Document   : header
    Created on : 13/03/2017, 09:31:40 PM
    Author     : lacastrillov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script src="/js/web/usuario/UserAuthentication.js"></script>
<script src="/js/web/Searcher.js"></script>
<script>
    var userAuthentication = new UserAuthentication();
    var searcher= new Searcher();
</script>

    <div class="navbar-inner">
        <div class="container">
            <!-- Navbar ================================================== -->
            <div id="logoArea" class="navbar">
                <a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div>
                    <a class="brand" href="/"><img src="/themes/images/logo.png" style="height: 40px;" alt="Bootsshop"/></a>
                    <div class="span5">
                        <form class="form-inline form-search" method="post" action="products.html" >
                            <input id="searchQuery" class="search-query" placeholder="Buscar" type="text" />
                            <button type="button" id="submitButton" class="btn" onclick="searcher.search($('#searchQuery').val());">Buscar</button>
                        </form>
                    </div>
                    <ul id="topMenu" class="nav">
                        <li class="item-menu"><a href="/tienda/registro">Registrarse</a></li>
                        <li class="item-menu"><a href="/tienda/contactanos">Cont&aacute;ctanos</a></li>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication var="userSession" property="principal" />
                            <li class="item-menu dropdown">
                                <a class="dropdown-toggle" href="#" data-toggle="dropdown">
                                    ${userSession.nombre} ${userSession.apellidos}
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="/account/home">Entrar a mi cuenta</a></li>
                                    <li><a href="/vista/myAccount/entity.htm">Mis datos</a></li>
                                    <li class="divider"></li>
                                    <li><a href="/security_logout">Cerrar sesi&oacute;n</a></li>
                                </ul>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <li class="item-menu">
                                <a href="#login" role="button" data-toggle="modal" style="padding-right:0">
                                    <span class="btn btn-large btn-success">Iniciar Sesión</span>
                                </a>
                            </li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
            <div id="login" class="login modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
                <div class="loginDiv">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3>Iniciar Sesi&oacute;n</h3>
                    </div>
                    <div class="modal-body">
                        <form id="formLogin" action="<c:url value='/account/authenticate'/>" method="post">
                            <div class="box-login">
                                <div class="box-input">
                                    <img src="/img/email.png" width="40" />
                                    <input placeholder="Correo electr&oacute;nico" id="j_username" type="text" class="validate" name="j_username" value="" maxlength="50" minlength="3" />
                                </div>

                                <div class="box-input">
                                    <img src="/img/password.png" width="40">
                                    <input placeholder="* * * * * *" id="j_password" type="password" class="validate" name="j_password" value="" maxlength="50" minlength="3" />
                                </div>
                            </div>
                            <button type="submit" class="btn-ingreso">Ingresar</button>
                            <a class="link-pass" onclick="userAuthentication.changeForm('changePasswordDiv')" href="javascript:void(0);">&iquest;Olvidaste tu clave?</a>
                        </form>
                    </div>
                </div>
                <div class="changePasswordDiv" style="display:none;">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3>Recuperar Contrase&ntilde;a</h3>
                    </div>
                    <div class="modal-body">
                        <form id="changePasswordForm" action="<%=request.getContextPath()%>/account/ajax/recuperarContrasena" method="post">
                            <div class="box-login">
                                <div class="box-input">
                                    <img src="/img/email.png" width="40" />
                                    <input id="correoElectronico" name="correoElectronico" type="text" class="validate" placeholder="Correo electr&oacute;nico" />
                                </div>
                            </div>
                            <a onclick="userAuthentication.resetPassword();" href="javascript:void(0)" class="btn-ingreso">Enviar</a>
                            <a class="link-pass" onclick="userAuthentication.changeForm('loginDiv')" href="javascript:void(0);">Volver</a>
                        </form>
                    </div>    
                </div>
            </div>
        </div>
    </div>
