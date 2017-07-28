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
                                <i class="icon-arrow-left"></i> Continuar Comprando
                            </a>
                        </h3>	
                        <hr class="soft"/>
                        <table class="table table-bordered">
                            <tr><th> YA ESTOY REGISTRADO </th></tr>
                            <tr> 
                                <td>
                                    <form class="form-horizontal">
                                        <div class="control-group">
                                            <label class="control-label" for="username">Usuario</label>
                                            <div class="controls">
                                                <input type="text" id="username" placeholder="usuario">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <label class="control-label" for="password">Contrase&ntilde;a</label>
                                            <div class="controls">
                                                <input type="password" id="password" placeholder="contrase&ntilde;a">
                                            </div>
                                        </div>
                                        <div class="control-group">
                                            <div class="controls">
                                                <button type="submit" class="btn">Ingresar</button> O <a href="/tienda/registro" class="btn">Registrarme ahora!</a>
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

                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <th>Producto</th>
                                    <th>Descripci&oacute;n</th>
                                    <th>Cantidad/Actualizar</th>
                                    <th>Precio</th>
                                    <th>Descuento</th>
                                    <th>IVA</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody id="generalSummaryTemplate">
                                <tr id="productSummaryTemplate" style="display:none">
                                    <td><img width="60" src="[item.productImage]" alt=""/></td>
                                    <td>[item.product.name]<br/>[item.product.description]</td>
                                    <td>
                                        <div class="input-append">
                                            <input class="span1" style="max-width:34px" placeholder="1" value="[item.quantity]" size="16" type="text">
                                            <button class="btn" type="button">
                                                <i class="icon-minus"></i>
                                            </button>
                                            <button class="btn" type="button">
                                                <i class="icon-plus"></i>
                                            </button>
                                            <button class="btn btn-danger" type="button">
                                                <i class="icon-remove icon-white"></i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>[item.subTotal]</td>
                                    <td>[item.discount]</td>
                                    <td>[item.iva]</td>
                                    <td>[item.total]</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right">Total Price:	</td>
                                    <td> $228.00</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right">Total Discount:	</td>
                                    <td> $50.00</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right">Total Tax:	</td>
                                    <td> $31.00</td>
                                </tr>
                                <tr>
                                    <td colspan="6" style="text-align:right"><strong>TOTAL ($228 - $50 + $31) =</strong></td>
                                    <td class="label label-important" style="display:block"> <strong> $155.00 </strong></td>
                                </tr>
                            </tbody>
                        </table>


                        <table class="table table-bordered">
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
                        </table>		
                        <a href="products.html" class="btn btn-large"><i class="icon-arrow-left"></i> Continue Shopping </a>
                        <a href="login.html" class="btn btn-large pull-right">Next <i class="icon-arrow-right"></i></a>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>