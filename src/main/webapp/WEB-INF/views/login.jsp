<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Iniciar Sesion - Mercando</title>
    </head>
    <body>
        
        <section class="login">
            <div class="container">
                <div class="col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <div style="height: 45px; padding-top: 35px;">
                        <c:if test="${!empty param.login_error}">
                            <div class="alert alert-error">
                                <button type="button" class="close" data-dismiss="alert">×</button>
                                <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="loginDiv col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
                    <h3>Iniciar Sesi&oacute;n</h3>
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
                <div class="changePasswordDiv col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4" style="display:none;">
                    <h3>Recuperar Contrase&ntilde;a</h3>
                    <form id="changePasswordForm" action="<%=request.getContextPath()%>/web/usuario/ajax/recuperarContrasena" method="post">
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
        </section>
        
    </body>
</html>
