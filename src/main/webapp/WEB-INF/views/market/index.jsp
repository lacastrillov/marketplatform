<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="webFunctions" class="com.dot.gcpbasedot.util.WebFunctions"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Inicio</title>
        <script src="/js/web/ShoppingCart.js"></script>
        <script>
            var shoppingCart= new ShoppingCart();
        </script>
    </head>
    <body>
        <div id="carouselBlk">
            <hr class="soften">
            <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                    <c:set var="index" value="0"></c:set>
                    <c:forEach items="${lastFeatured}" var="product">
                        <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                        <div class="item <c:if test="${index == 0}">active</c:if>">
                            <div class="container">
                                <div class="row">
                                    <div class="span6">
                                        <a href="/productos/detalle/${product.code}">
                                            <c:if test="${numImages>0}">
                                                <img src="${webFunctions.getImageLinkByDimensions(product.productImageList[0].image, "600x600")}" alt="${product.name}"/>
                                            </c:if>
                                            <c:if test="${numImages==0}">
                                                <img src="/img/imagen_no_disponible.png" alt="Imagen no disponible"/>
                                            </c:if>
                                        </a>
                                    </div>
                                    <div class="span6">
                                        <h1><a href="/productos/detalle/${product.code}">${product.name}</a></h1>
                                        <h4>${product.description}</h4>
                                    </div>
                                </div>    
                            </div>
                        </div>
                        <c:set var="index" value="${index+1}"></c:set>
                    </c:forEach>
                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
            </div>
        </div>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    
                    <jsp:include page="/WEB-INF/views/market/filter_panel.jsp"></jsp:include>
                    
                    <div class="span9">		
                        <div class="well well-small">
                            <h4>Productos destacados <small class="pull-right">${parametersFeatured.totalResults} productos destacados</small></h4>
                            <div class="row-fluid">
                                <div id="featured" class="carousel slide">
                                    <div class="carousel-inner">
                                        <fmt:formatNumber var="numPagesFeatured" minFractionDigits="0" maxFractionDigits="0" value= "${fn:length(lastFeatured)/4}" />
                                        <c:set var="index" value="0"></c:set>
                                        <!-- FFF ${numPagesFeatured}-->
                                        <c:forEach var="i" begin="0" end="${numPagesFeatured-1}">
                                            <div class="item <c:if test="${i==0}">active</c:if>">
                                                <ul class="thumbnails">
                                                <c:forEach var="j" begin="0" end="3">
                                                    <c:if test="${lastFeatured[index]!=null}">
                                                        <c:set var="product" value="${lastFeatured[index]}"/>
                                                        <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                                                        <li class="span3">
                                                            <div class="thumbnail">
                                                                <i class="tag"></i>
                                                                <a href="/productos/detalle/${product.code}">
                                                                    <c:if test="${numImages>0}">
                                                                        <img src="${product.productImageList[0].image}" alt="${product.name}" style="max-width: 190px; max-height: 200px;"/>
                                                                    </c:if>
                                                                    <c:if test="${numImages==0}">
                                                                        <img src="/img/imagen_no_disponible.png" alt="Imagen no disponible"/>
                                                                    </c:if>
                                                                </a>
                                                                <div class="caption">
                                                                    <h5>${product.name}</h5>
                                                                    <h4>
                                                                        <a class="btn" href="/productos/detalle/${product.code}">VIEW</a>
                                                                        <span class="pull-right">
                                                                            $ <fmt:formatNumber type="currency" value="${product.buyUnitPrice}" pattern="###,##0"/>
                                                                        </span>
                                                                    </h4>
                                                                </div>
                                                            </div>
                                                        </li>
                                                        <c:set var="index" value="${index+1}"></c:set>
                                                    </c:if>
                                                </c:forEach>
                                                </ul>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <a class="left carousel-control" href="#featured" data-slide="prev">&lsaquo;</a>
                                    <a class="right carousel-control" href="#featured" data-slide="next">&rsaquo;</a>
                                </div>
                            </div>
                        </div>
                        <h4>&Uacute;ltimos productos </h4>
                        <ul class="thumbnails">
                            <c:forEach items="${lastProducts}" var="product">
                                <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                                <li class="span3">
                                    <div class="thumbnail">
                                        <a  href="/productos/detalle/${product.code}">
                                            <c:if test="${numImages>0}">
                                                <img src="${product.productImageList[0].image}" alt="${product.name}" style="max-width: 250px; max-height: 200px;"/>
                                            </c:if>
                                            <c:if test="${numImages==0}">
                                                <img src="/img/imagen_no_disponible.png" alt="Imagen no disponible"/>
                                            </c:if>
                                        </a>
                                        <div class="caption">
                                            <h5>${product.name}</h5>
                                            <p> 
                                                ${fn:substring(product.description,0,35)}...
                                            </p>
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
                        </ul>	

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>