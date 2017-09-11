<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="webFunctions" class="com.dot.gcpbasedot.util.WebFunctions"/>

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
                    <div class="span12">
                        <ul class="breadcrumb">
                            <li><a href="/">Inicio</a> <span class="divider">/</span></li>
                            <li><a href="/productos/listado?filter=(eq:(subCategory:${product.subCategory.id}))">${product.subCategory.name}</a> <span class="divider">/</span></li>
                            <li class="active">${product.name}</li>
                        </ul>	
                        <div class="row">
                            <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                            <div id="gallery" class="span6">
                                <c:if test="${numImages>0}">
                                    <a href="${product.productImageList[0].image}" title="${product.name}">
                                        <img src="${webFunctions.getImageLinkByDimensions(product.productImageList[0].image, "600x600")}" alt="${product.name}" style="max-width: 585px; max-height: 600px;"/>
                                    </a>
                                </c:if>
                                <c:if test="${numImages==0}">
                                    <img src="/img/imagen_no_disponible.png" alt="Imagen no disponible"/>
                                </c:if>
                                <c:if test="${numImages>0}">
                                    <div id="imageCarousel" class="carousel slide">
                                        <div class="carousel-inner">
                                            <fmt:formatNumber var="numPagesImages" minFractionDigits="0" maxFractionDigits="0" value= "${fn:length(product.productImageList)/3}" />
                                            <c:set var="index" value="0"></c:set>
                                            <c:forEach var="i" begin="0" end="${numPagesImages-1}">
                                                <div class="item <c:if test="${i==0}">active</c:if>">
                                                    <c:forEach var="j" begin="0" end="3">
                                                        <c:if test="${product.productImageList[index]!=null}">
                                                            <a href="${webFunctions.getImageLinkByDimensions(product.productImageList[index].image, "800x800")}">
                                                                <img style="width:29%" src="${webFunctions.getImageLinkByDimensions(product.productImageList[index].image, "300x300")}" alt=""/>
                                                            </a>
                                                            <c:set var="index" value="${index+1}"></c:set>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <!--<a class="left carousel-control" href="#imageCarousel" data-slide="prev">&lsaquo;</a>
                                        <a class="right carousel-control" href="#imageCarousel" data-slide="next">&rsaquo;</a>-->
                                    </div>
                                </c:if>

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

                            <div class="span12">
                                <ul id="productDetail" class="nav nav-tabs">
                                    <li><a href="#profile" data-toggle="tab">Productos Relacionados</a></li>
                                    <li class="active"><a href="#home" data-toggle="tab">Detalle Producto</a></li>
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
                                        <br class="clr"/>
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="blockView">
                                                <ul class="thumbnails">
                                                    <c:forEach items="${relatedProducts}" var="product">
                                                        <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <a href="/productos/detalle/${product.code}" style="height: 200px;">
                                                                    <c:if test="${numImages>0}">
                                                                        <img src="${webFunctions.getImageLinkByDimensions(product.productImageList[0].image, "300x300")}" alt="${product.name}" style="max-width: 250px; max-height: 200px;"/>
                                                                    </c:if>
                                                                    <c:if test="${numImages==0}">
                                                                        <img src="/img/imagen_no_disponible.png" alt="Imagen no disponible"/>
                                                                    </c:if>
                                                                </a>
                                                                <div class="caption">
                                                                    <h5>${product.name}</h5>
                                                                    <p>${fn:substring(product.description,0,35)}...</p>
                                                                    <h4 style="text-align:center">
                                                                        <a class="btn" href="/productos/detalle/${product.code}">
                                                                            <i class="icon-zoom-in"></i>
                                                                        </a>
                                                                        <a class="btn" href="javascript:void(0)" onclick="shoppingCart.addToCart('${product.code}')">
                                                                            Add to <i class="icon-shopping-cart"></i>
                                                                        </a>
                                                                        <a class="btn btn-primary" href="/productos/detalle/${product.code}">
                                                                            $ <fmt:formatNumber type="currency" value="${product.buyUnitPrice}" pattern="###,##0"/>
                                                                        </a>
                                                                    </h4>
                                                                </div>
                                                            </div>
                                                        </li>
                                                    </c:forEach>
                                                    <!--<li class="span3">
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
                                                    </li>-->
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