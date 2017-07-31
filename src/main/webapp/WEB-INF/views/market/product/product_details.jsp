<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">
    <head>
        <title>Detalle Producto - ${product.name} - ${product.brand}</title>
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
                            <li><a href="/">Home</a> <span class="divider">/</span></li>
                            <li><a href="/tienda/productos?filter={eq:{subCategory:${product.subCategory.id}}}">${product.subCategory.name}</a> <span class="divider">/</span></li>
                            <li class="active">${product.name}</li>
                        </ul>	
                        <div class="row">
                            <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                            <div id="gallery" class="span3">
                                <c:if test="${numImages>0}">
                                    <a href="${product.productImageList[0].image}" title="${product.name}">
                                        <img src="${product.productImageList[0].image}" alt="${product.name}" style="max-width: 250px; max-height: 200px;"/>
                                    </a>
                                </c:if>
                                <c:if test="${numImages==0}">
                                    <img src="/img/imagen_no_disponible.png" alt="Imagen no disponible"/>
                                </c:if>
                                <div id="differentview" class="moreOptopm carousel slide">
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <a href="/themes/images/products/large/f1.jpg"> <img style="width:29%" src="/themes/images/products/large/f1.jpg" alt=""/></a>
                                            <a href="/themes/images/products/large/f2.jpg"> <img style="width:29%" src="/themes/images/products/large/f2.jpg" alt=""/></a>
                                            <a href="/themes/images/products/large/f3.jpg" > <img style="width:29%" src="/themes/images/products/large/f3.jpg" alt=""/></a>
                                        </div>
                                        <!--<div class="item">
                                            <a href="/themes/images/products/large/f3.jpg" > <img style="width:29%" src="/themes/images/products/large/f3.jpg" alt=""/></a>
                                            <a href="/themes/images/products/large/f1.jpg"> <img style="width:29%" src="/themes/images/products/large/f1.jpg" alt=""/></a>
                                            <a href="/themes/images/products/large/f2.jpg"> <img style="width:29%" src="/themes/images/products/large/f2.jpg" alt=""/></a>
                                        </div>-->
                                    </div>
                                    <!--  
                                                <a class="left carousel-control" href="#myCarousel" data-slide="prev">â¹</a>
                                    <a class="right carousel-control" href="#myCarousel" data-slide="next">âº</a> 
                                    -->
                                </div>

                                <div class="btn-toolbar">
                                    <div class="btn-group">
                                        <span class="btn"><i class="icon-envelope"></i></span>
                                        <span class="btn" ><i class="icon-print"></i></span>
                                        <span class="btn" ><i class="icon-zoom-in"></i></span>
                                        <span class="btn" ><i class="icon-star"></i></span>
                                        <span class="btn" ><i class=" icon-thumbs-up"></i></span>
                                        <span class="btn" ><i class="icon-thumbs-down"></i></span>
                                    </div>
                                </div>
                            </div>
                            <div class="span6">
                                <h3>${product.name} - ${product.brand}</h3>
                                <small>- ${product.category.name} - ${product.subCategory.name} </small>
                                <hr class="soft"/>
                                <form class="form-horizontal qtyFrm">
                                    <div class="control-group">
                                        <label class="control-label"><span>$<fmt:formatNumber type="currency" value="${product.buyUnitPrice}" pattern="###,##0"/></span></label>
                                        <div class="controls">
                                            <input type="number" class="span1" placeholder="Qty."/>
                                            <button type="button" class="btn btn-large btn-primary pull-right" onclick="shoppingCart.addToCart('${product.code}')"> Add to cart <i class=" icon-shopping-cart"></i></button>
                                        </div>
                                    </div>
                                </form>

                                <hr class="soft"/>
                                <h4>${product.unitsInStock} unidades en stock</h4>
                                <hr class="soft clr"/>
                                <p>
                                    ${product.description}
                                </p>
                                <a class="btn btn-small pull-right" href="#detail">M&aacute;s detalles</a>
                                <br class="clr"/>
                                <a href="#" name="detail"></a>
                                <hr class="soft"/>
                            </div>

                            <div class="span9">
                                <ul id="productDetail" class="nav nav-tabs">
                                    <li class="active"><a href="#home" data-toggle="tab">Detalle Producto</a></li>
                                    <li><a href="#profile" data-toggle="tab">Productos Relacionados</a></li>
                                </ul>
                                <div id="myTabContent" class="tab-content">
                                    <div class="tab-pane fade active in" id="home">
                                        <h4>Informaci&oacute;n producto</h4>
                                        <table class="table table-bordered">
                                            <tbody>
                                                <tr class="techSpecRow"><th colspan="2">Detalle Producto</th></tr>
                                                <tr class="techSpecRow"><td class="techSpecTD1">C&oacute;digo: </td><td class="techSpecTD2">${product.code}</td></tr>
                                                <tr class="techSpecRow"><td class="techSpecTD1">Marca: </td><td class="techSpecTD2">${product.brand}</td></tr>
                                                <tr class="techSpecRow"><td class="techSpecTD1">Fecha Registro:</td><td class="techSpecTD2"><fmt:formatDate type="date" value="${product.registerDate}" /></td></tr>
                                                <tr class="techSpecRow"><td class="techSpecTD1">Categor&iacute;a:</td><td class="techSpecTD2">${product.category.name}</td></tr>
                                                <tr class="techSpecRow"><td class="techSpecTD1">Sub Categor&iacute;a</td><td class="techSpecTD2">${product.subCategory.name}</td></tr>
                                            </tbody>
                                        </table>

                                        <h5>Rese&ntilde;a</h5>
                                        <p>
                                            ${product.description}
                                        </p>
                                    </div>
                                    <div class="tab-pane fade" id="profile">
                                        <div id="myTab" class="pull-right">
                                            <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
                                            <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
                                        </div>
                                        <br class="clr"/>
                                        <hr class="soft"/>
                                        <div class="tab-content">
                                            <div class="tab-pane" id="listView">
                                                <div class="row">	  
                                                    <div class="span2">
                                                        <img src="/themes/images/products/4.jpg" alt=""/>
                                                    </div>
                                                    <div class="span4">
                                                        <h3>New | Available</h3>				
                                                        <hr class="soft"/>
                                                        <h5>Product Name </h5>
                                                        <p>
                                                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
                                                            that is why our goods are so popular..
                                                        </p>
                                                        <a class="btn btn-small pull-right" href="product_details.html">View Details</a>
                                                        <br class="clr"/>
                                                    </div>
                                                    <div class="span3 alignR">
                                                        <form class="form-horizontal qtyFrm">
                                                            <h3> $222.00</h3>
                                                            <label class="checkbox">
                                                                <input type="checkbox">  Adds product to compair
                                                            </label><br/>
                                                            <div class="btn-group">
                                                                <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
                                                                <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <hr class="soft"/>
                                                <div class="row">	  
                                                    <div class="span2">
                                                        <img src="/themes/images/products/5.jpg" alt=""/>
                                                    </div>
                                                    <div class="span4">
                                                        <h3>New | Available</h3>				
                                                        <hr class="soft"/>
                                                        <h5>Product Name </h5>
                                                        <p>
                                                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
                                                            that is why our goods are so popular..
                                                        </p>
                                                        <a class="btn btn-small pull-right" href="product_details.html">View Details</a>
                                                        <br class="clr"/>
                                                    </div>
                                                    <div class="span3 alignR">
                                                        <form class="form-horizontal qtyFrm">
                                                            <h3> $222.00</h3>
                                                            <label class="checkbox">
                                                                <input type="checkbox">  Adds product to compair
                                                            </label><br/>
                                                            <div class="btn-group">
                                                                <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
                                                                <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <hr class="soft"/>
                                                <div class="row">	  
                                                    <div class="span2">
                                                        <img src="/themes/images/products/6.jpg" alt=""/>
                                                    </div>
                                                    <div class="span4">
                                                        <h3>New | Available</h3>				
                                                        <hr class="soft"/>
                                                        <h5>Product Name </h5>
                                                        <p>
                                                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
                                                            that is why our goods are so popular..
                                                        </p>
                                                        <a class="btn btn-small pull-right" href="product_details.html">View Details</a>
                                                        <br class="clr"/>
                                                    </div>
                                                    <div class="span3 alignR">
                                                        <form class="form-horizontal qtyFrm">
                                                            <h3> $222.00</h3>
                                                            <label class="checkbox">
                                                                <input type="checkbox">  Adds product to compair
                                                            </label><br/>
                                                            <div class="btn-group">
                                                                <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
                                                                <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <hr class="soft"/>
                                                <div class="row">	  
                                                    <div class="span2">
                                                        <img src="/themes/images/products/7.jpg" alt=""/>
                                                    </div>
                                                    <div class="span4">
                                                        <h3>New | Available</h3>				
                                                        <hr class="soft"/>
                                                        <h5>Product Name </h5>
                                                        <p>
                                                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
                                                            that is why our goods are so popular..
                                                        </p>
                                                        <a class="btn btn-small pull-right" href="product_details.html">View Details</a>
                                                        <br class="clr"/>
                                                    </div>
                                                    <div class="span3 alignR">
                                                        <form class="form-horizontal qtyFrm">
                                                            <h3> $222.00</h3>
                                                            <label class="checkbox">
                                                                <input type="checkbox">  Adds product to compair
                                                            </label><br/>
                                                            <div class="btn-group">
                                                                <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
                                                                <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>

                                                <hr class="soft"/>
                                                <div class="row">	  
                                                    <div class="span2">
                                                        <img src="/themes/images/products/8.jpg" alt=""/>
                                                    </div>
                                                    <div class="span4">
                                                        <h3>New | Available</h3>				
                                                        <hr class="soft"/>
                                                        <h5>Product Name </h5>
                                                        <p>
                                                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
                                                            that is why our goods are so popular..
                                                        </p>
                                                        <a class="btn btn-small pull-right" href="product_details.html">View Details</a>
                                                        <br class="clr"/>
                                                    </div>
                                                    <div class="span3 alignR">
                                                        <form class="form-horizontal qtyFrm">
                                                            <h3> $222.00</h3>
                                                            <label class="checkbox">
                                                                <input type="checkbox">  Adds product to compair
                                                            </label><br/>
                                                            <div class="btn-group">
                                                                <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
                                                                <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <hr class="soft"/>
                                                <div class="row">	  
                                                    <div class="span2">
                                                        <img src="/themes/images/products/9.jpg" alt=""/>
                                                    </div>
                                                    <div class="span4">
                                                        <h3>New | Available</h3>				
                                                        <hr class="soft"/>
                                                        <h5>Product Name </h5>
                                                        <p>
                                                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
                                                            that is why our goods are so popular..
                                                        </p>
                                                        <a class="btn btn-small pull-right" href="product_details.html">View Details</a>
                                                        <br class="clr"/>
                                                    </div>
                                                    <div class="span3 alignR">
                                                        <form class="form-horizontal qtyFrm">
                                                            <h3> $222.00</h3>
                                                            <label class="checkbox">
                                                                <input type="checkbox">  Adds product to compair
                                                            </label><br/>
                                                            <div class="btn-group">
                                                                <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
                                                                <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                                <hr class="soft"/>
                                            </div>
                                            <div class="tab-pane active" id="blockView">
                                                <ul class="thumbnails">
                                                    <li class="span3">
                                                        <div class="thumbnail">
                                                            <a href="product_details.html"><img src="/themes/images/products/10.jpg" alt=""/></a>
                                                            <div class="caption">
                                                                <h5>Manicure &amp; Pedicure</h5>
                                                                <p> 
                                                                    Lorem Ipsum is simply dummy text. 
                                                                </p>
                                                                <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="span3">
                                                        <div class="thumbnail">
                                                            <a href="product_details.html"><img src="/themes/images/products/11.jpg" alt=""/></a>
                                                            <div class="caption">
                                                                <h5>Manicure &amp; Pedicure</h5>
                                                                <p> 
                                                                    Lorem Ipsum is simply dummy text. 
                                                                </p>
                                                                <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="span3">
                                                        <div class="thumbnail">
                                                            <a href="product_details.html"><img src="/themes/images/products/12.jpg" alt=""/></a>
                                                            <div class="caption">
                                                                <h5>Manicure &amp; Pedicure</h5>
                                                                <p> 
                                                                    Lorem Ipsum is simply dummy text. 
                                                                </p>
                                                                <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="span3">
                                                        <div class="thumbnail">
                                                            <a href="product_details.html"><img src="/themes/images/products/13.jpg" alt=""/></a>
                                                            <div class="caption">
                                                                <h5>Manicure &amp; Pedicure</h5>
                                                                <p> 
                                                                    Lorem Ipsum is simply dummy text. 
                                                                </p>
                                                                <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="span3">
                                                        <div class="thumbnail">
                                                            <a href="product_details.html"><img src="/themes/images/products/1.jpg" alt=""/></a>
                                                            <div class="caption">
                                                                <h5>Manicure &amp; Pedicure</h5>
                                                                <p> 
                                                                    Lorem Ipsum is simply dummy text. 
                                                                </p>
                                                                <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                    <li class="span3">
                                                        <div class="thumbnail">
                                                            <a href="product_details.html"><img src="/themes/images/products/2.jpg" alt=""/></a>
                                                            <div class="caption">
                                                                <h5>Manicure &amp; Pedicure</h5>
                                                                <p> 
                                                                    Lorem Ipsum is simply dummy text. 
                                                                </p>
                                                                <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
                                                            </div>
                                                        </div>
                                                    </li>
                                                </ul>
                                                <hr class="soft"/>
                                            </div>
                                        </div>
                                        <br class="clr">
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>