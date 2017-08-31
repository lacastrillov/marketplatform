<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Recuperar Contrase&ntilde;a</title>
    </head>
    <body>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    
                    <jsp:include page="/WEB-INF/views/market/filter_panel.jsp"></jsp:include>
                    
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="/">Inicio</a> <span class="divider">/</span></li>
                            <li class="active">Recuperar Contrase&ntilde;a</li>
                        </ul>
                        <h3> Recuperar Contrase&ntilde;a</h3>	
                        <hr class="soft"/>

                        <div class="row">
                            <div class="span9">
                                <div class="well">
                                    <h5>Reestablecer la contrase&ntilde;a</h5><br/>
                                    Por favor, ingresa la direcci&oacute;n de correo electr&oacute;nico para su cuenta. Se le enviar&aacute; un c&oacute;digo de verificaci&oacute;n. Una vez que haya recibido el c&oacute;digo de verificaci&oacute;n, podr&aacute; elegir una nueva contrase&ntilde;a para su cuenta.<br/><br/><br/>
                                    <form>
                                        <div class="control-group">
                                            <div class="controls">
                                                <input class="span3"  type="text" id="inputEmail1" placeholder="Correo electr&oacute;nico">
                                            </div>
                                        </div>
                                        <div class="controls">
                                            <button type="submit" class="btn block">Enviar</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>	

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>