<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Carrito de Compras</title>
        <script src="/js/web/ShoppingCart.js"></script>
        <script>
            var shoppingCart= new ShoppingCart();
        </script>
    </head>
    <body>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    
                    <jsp:include page="/WEB-INF/views/market/filter_panel.jsp"></jsp:include>
                    
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="/tienda/">Home</a> <span class="divider">/</span></li>
                            <li class="active"> CARRITO DE COMPRAS</li>
                        </ul>
                        <h3> CARRITO DE COMPRAS [ <small id="numItemsSC">0 Item(s)</small> ]
                            <a href="/tienda/productos" class="btn btn-large pull-right">
                                <i class="icon-arrow-left"></i> Seguir Comprando
                            </a>
                        </h3>	
                        <hr class="soft"/>
                        <table id="loginUserTable" class="table table-bordered" <sec:authorize access="isAuthenticated()">style="display: none;"</sec:authorize>>
                            <tr><th> YA ESTOY REGISTRADO </th></tr>
                            <tr> 
                                <td>
                                    <form id="ajaxFormLogin" class="form-horizontal" action="<c:url value='/ajax/authenticate'/>">
                                        <div class="control-group">
                                            <label class="control-label" for="username">Usuario</label>
                                            <div class="controls">
                                                <input type="text" id="username" name="j_username" placeholder="usuario">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="password">Contrase&ntilde;a</label>
                                            <div class="controls">
                                                <input type="password" id="password" name="j_password" placeholder="contrase&ntilde;a">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button id="ajaxLoginBtn" type="button" class="btn">Ingresar</button> O
                                                <a href="/tienda/registro" class="btn">Registrarme ahora!</a>
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <a href="/tienda/recuperar-clave" style="text-decoration:underline">Recuperar clave</a>
                                            </div>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </table>
                        <table id="userInSessionTable" class="table table-bordered" <sec:authorize access="!isAuthenticated()">style="display: none;"</sec:authorize>>
                            <sec:authentication var="userSession" property="principal" />
                            <input type="hidden" id="idUserInSession" value="<sec:authorize access="isAuthenticated()">${userSession.user.id}</sec:authorize>" />
                            <tr><th> USUARIO EN SESI&Oacute;N </th></tr>
                            <tr> 
                                <td class="form-horizontal">
                                    <div class="control-group">
                                        <label class="control-label">Usuario:&nbsp;</label>
                                        <label id="userNameData" class="control-label" style="color: #167231">
                                            <sec:authorize access="isAuthenticated()">
                                                ${userSession.username} - ${userSession.nombre} ${userSession.apellidos}
                                            </sec:authorize>
                                        </label>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Correo:&nbsp;&nbsp;</label>
                                        <label id="userEmail" class="control-label" style="color: #167231">
                                            <sec:authorize access="isAuthenticated()">
                                                ${userSession.user.email}
                                            </sec:authorize>
                                        </label>
                                    </div>
                                    <div class="control-group">
                                        <div class="controls">
                                            <a href="<%=request.getContextPath()%>/security_logout" class="btn">Cerrar Sesi&oacute;n</a>
                                            <a href="/home?redirect=user" class="btn">Entrar a mi cuenta</a>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Nombre</th>
                                    <th>Cantidad/Actualizar</th>
                                    <th>Precio</th>
                                    <th>Descuento</th>
                                    <th>IVA</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody id="generalSummaryTemplate">
                                <tr id="productSummaryTemplate" style="display:none;">
                                    <td><img width="60" src="={productImage}" alt=""/></td>
                                    <td>={product.name}</td>
                                    <td>
                                        <div class="input-append">
                                            <input class="span1" style="max-width:34px" placeholder="1" value="={quantity}" size="16" type="text">
                                            <button class="btn" type="button" onclick="shoppingCart.lessFromCart('={product.code}')">
                                                <i class="icon-minus"></i>
                                            </button>
                                            <button class="btn" type="button" onclick="shoppingCart.addToCart('={product.code}')">
                                                <i class="icon-plus"></i>
                                            </button>
                                            <button class="btn btn-danger" type="button" onclick="shoppingCart.removeFromCart('={product.code}')">
                                                <i class="icon-remove icon-white"></i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>={subTotal}</td>
                                    <td>={discount}</td>
                                    <td>={iva}</td>
                                    <td>={total}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right">Precio Total:	</td>
                                    <td> $={subTotal}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right">Descuento Total:	</td>
                                    <td> $={discount}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right">IVA Total:	</td>
                                    <td> $={iva}</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right"><strong>TOTAL ($ ={subTotal} - $ ={discount} + $ ={iva}) =</strong></td>
                                    <td class="label label-important" style="display:block"> <strong> $ ={total} </strong></td>
                                </tr>
                            </tbody>
                        </table>


                        <!--<table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <td> 
                                        <form class="form-horizontal">
                                            <div class="control-group">
                                                <label class="control-label"><strong> VOUCHERS CODE: </strong> </label>
                                                <div class="controls">
                                                    <input type="text" class="input-medium" placeholder="CODE">
                                                    <button type="submit" class="btn"> ADD </button>
                                                </div>
                                            </div>
                                        </form>
                                    </td>
                                </tr>

                            </tbody>
                        </table>

                        <table class="table table-bordered">
                            <tr><th>ESTIMATE YOUR SHIPPING </th></tr>
                            <tr> 
                                <td>
                                    <form class="form-horizontal">
                                        <div class="control-group">
                                            <label class="control-label" for="inputCountry">Country </label>
                                            <div class="controls">
                                                <input type="text" id="inputCountry" placeholder="Country">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="inputPost">Post Code/ Zipcode </label>
                                            <div class="controls">
                                                <input type="text" id="inputPost" placeholder="Postcode">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button type="submit" class="btn">ESTIMATE </button>
                                            </div>
                                        </div>
                                    </form>				  
                                </td>
                            </tr>
                        </table>-->
                        <a href="/tienda/productos" class="btn btn-large"><i class="icon-arrow-left"></i> Seguir Comprando </a>
                        <a href="javascript:void(0)" onclick="shoppingCart.generatePurchaseOrder()" class="btn btn-large pull-right">Enviar Orden <i class="icon-arrow-right"></i></a>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>