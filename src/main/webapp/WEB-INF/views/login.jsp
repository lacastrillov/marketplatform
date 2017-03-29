<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Mercando | Iniciar Sesion</title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/web/usuario/AutenticacionUsuario.js"></script>
        
        <script>
            var autenticacionUsuario = new AutenticacionUsuario();
        </script>
    </head>
    <body>
        
        <section id="login">
            <div class="container">
                <div class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <p id="message" style="padding-top: 15px; font-size: 20px;">
                        <c:if test="${!empty param.login_error}">
                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                        </c:if>
                    </p>
                </div>
                <div id="loginDiv" class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <form id="formLogin" action="<c:url value='/login'/>" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <div class="box-login">
                            <div class="box-input">
                                <img src="/img/email.png" width="40" />
                                <input placeholder="Correo electr&oacute;nico" id="j_username" type="text" class="validate" name="username" value="" maxlength="50" minlength="3" />
                            </div>

                            <div class="box-input">
                                <img src="/img/password.png" width="40">
                                <input placeholder="* * * * * *" id="j_password" type="password" class="validate" name="password" value="" maxlength="50" minlength="3" />
                            </div>
                        </div>
                        <input type="submit" value="Ingresar" /> 
                        <!--<a id="linkIngresar" href="#" class="btn-ingreso">Ingresar</a>-->
                        <a class="link-pass" onclick="autenticacionUsuario.changeForm('changePasswordDiv')" href="javascript:void(0);">&iquest;Olvidaste tu clave?</a>
                    </form>
                </div>
                <div id="changePasswordDiv" style="display:none;" class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <form id="changePasswordForm" action="<%=request.getContextPath()%>/web/usuario/ajax/recuperarContrasena" method="post">
                        <div class="box-login">
                            <div class="box-input">
                                <img src="/img/email.png" width="40" />
                                <input id="correoElectronico" name="correoElectronico" type="text" class="validate" placeholder="Correo electr&oacute;nico" />
                            </div>
                        </div>
                        <a href="javascript:autenticacionUsuario.resetPassword();" class="btn-ingreso">Enviar</a>
                        <a class="link-pass" onclick="autenticacionUsuario.changeForm('loginDiv')" href="javascript:void(0);">Volver</a>
                    </form>
                </div>
            </div>
        </section>
        
    </body>
</html>
