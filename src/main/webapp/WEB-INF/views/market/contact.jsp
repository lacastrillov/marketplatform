<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Contactanos</title>
        <script src="/js/web/usuario/UserClient.js"></script>
        <script>
            var userClient= new UserClient();
        </script>
    </head>
    <body>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="/">Inicio</a> <span class="divider">/</span></li>
                            <li class="active">Contactanos</li>
                        </ul>
                        <h3> Cont&aacute;ctenos, Quejas y Reclamaciones</h3>
                        <div class="well">
                            <form id="userContactForm" class="form-horizontal">
                                <div class="control-group">
                                    <div class="controls form-inline">
                                        <input type="text" id="userName" name="userName" placeholder="Nombres" class="input-xlarge" onKeyPress="return validation.isAbcText(event)"/>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input type="text" id="cellPhone" name="cellPhone" placeholder="Tel&eacute;fono celular" class="input-xlarge" onKeyPress="return validation.isNumValue(event)"/> 
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input type="text" id="mail" name="mail" placeholder="Correo electr&oacute;nico" class="input-xlarge"/>
                                    </div>
                                </div>	  
                                <div class="control-group">
                                    <div class="controls">
                                        <textarea rows="3" id="comments" name="comments" class="input-xlarge" placeholder="Comentarios"></textarea>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls">
                                        <input class="btn btn-large btn-success" type="button" value="Enviar" onclick="userClient.contactUser()" />
                                    </div>
                                </div>
                                <div class="alert alert-block alert-info fade in">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    Al contactarnos, aceptas nuestras <strong><a href="/tienda/terminos-y-condiciones">Condiciones</a></strong> y la <strong><a href="/tienda/politicas-de-privacidad">Pol&iacute;tica de privacidad</a></strong>.
                                </div>	
                            </form>
                        </div>

                    </div>
                </div>
                
            </div>
        </div>
    </body>
</html>