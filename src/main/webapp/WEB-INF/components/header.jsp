<%-- 
    Document   : header
    Created on : 13/03/2017, 09:31:40 PM
    Author     : lacastrillov
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                    <a class="brand" href="/"><img src="/themes/images/logo.jpg" style="height: 40px;" alt="Bootsshop"/></a>
                    <form class="form-inline navbar-search" method="post" action="products.html" >
                        <input id="srchFld" class="srchTxt" type="text" value="     " />
                        <!--<select class="srchTxt">
                            <option>All</option>
                            <option>CLOTHES </option>
                            <option>FOOD AND BEVERAGES </option>
                            <option>HEALTH & BEAUTY </option>
                            <option>SPORTS & LEISURE </option>
                            <option>BOOKS & ENTERTAINMENTS </option>
                        </select>-->
                        <button type="submit" id="submitButton" class="btn btn-primary">Go</button>
                    </form>
                    <ul id="topMenu" class="nav pull-right">
                        <li class=""><a href="/tienda/registro">Registrarse</a></li>
                        <li class=""><a href="/tienda/contactanos">Cont&aacute;ctanos</a></li>
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication var="userSession" property="principal" />
                            <li class=""><a href="/account/home?redirect=user">${userSession.nombre} ${userSession.apellidos}</a></li>
                            <li class="">
                                <a href="/account/home?redirect=user" role="button" style="padding-right:0">
                                    <span class="btn btn-large btn-success">Entrar a mi cuenta</span>
                                </a>
                            </li>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <li class="">
                                <a href="#login" role="button" data-toggle="modal" style="padding-right:0">
                                    <span class="btn btn-large btn-success">Iniciar Sesión</span>
                                </a>
                                <div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                        <h3>Iniciar Sesi&oacute;n</h3>
                                    </div>
                                    <div class="modal-body">
                                        <form id="formLogin" action="<c:url value='/account/authenticate'/>" method="post" class="form-horizontal loginFrm">
                                            <div class="control-group">
                                                <input placeholder="Correo electr&oacute;nico" id="j_username" type="text" class="validate" name="j_username" value="" maxlength="50" minlength="3" />
                                                <!--<input type="text" id="inputEmail" placeholder="Email">-->
                                            </div>
                                            <div class="control-group">
                                                <input placeholder="* * * * * *" id="j_password" type="password" class="validate" name="j_password" value="" maxlength="50" minlength="3" />
                                                <!--<input type="password" id="inputPassword" placeholder="Password">-->
                                            </div>
                                            <div class="control-group">
                                                <label class="checkbox">
                                                    <input type="checkbox"> Recordarme
                                                </label>
                                            </div>
                                            <input type="submit" class="btn btn-success" value="Iniciar Sesi&oacute;n" />
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">Cerrar</button>
                                        </form>
                                    </div>
                                </div>
                            </li>
                        </sec:authorize>
                    </ul>
                </div>
            </div>
        </div>
    </div>
