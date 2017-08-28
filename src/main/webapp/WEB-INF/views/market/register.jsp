﻿<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Registrarse</title>
        <script src="/js/web/usuario/UserRegistration.js"></script>
        <script>
            var userRegistration= new UserRegistration();
        </script>
    </head>
    <body>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="/">Inicio</a> <span class="divider">/</span></li>
                            <li class="active">Registrarse</li>
                        </ul>
                        <h3> Registrarse</h3>	
                        <div class="well">
                            <form id="userRegistrationForm" class="form-horizontal">
                                <div class="control-group">
                                    <div class="controls form-inline">
                                        <input type="text" id="firstName" name="firstName" placeholder="Nombres" class="input-medium" onKeyPress="return validation.isAbcText(event)"/>
                                        <input type="text" id="lastName" name="lastName" placeholder="Apellidos" class="input-medium" onKeyPress="return validation.isAbcText(event)"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input type="text" id="cell" name="cell" placeholder="Tel&eacute;fono celular" class="input-xlarge" onKeyPress="return validation.isNumValue(event)"/> 
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input type="text" id="email" name="email" placeholder="Correo electr&oacute;nico" class="input-xlarge"/>
                                    </div>
                                </div>	  
                                <div class="control-group">
                                    <div class="controls">
                                        <input type="password" id="password" name="password" placeholder="Contrase&ntilde;a" class="input-xlarge"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input class="btn btn-large btn-success" type="button" value="Crear cuenta" onclick="userRegistration.sendData()" />
                                    </div>
                                </div>
                                <div class="alert alert-block alert-info fade in">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    Al registrarte, aceptas nuestras <strong>Condiciones</strong> y la <strong>Política de privacidad</strong>.
                                </div>	
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>