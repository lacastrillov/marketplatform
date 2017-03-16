<%-- 
    Document   : products_list
    Created on : 15/03/2017, 09:34:55 PM
    Author     : lacastrillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="thumbnails">
    <c:forEach items="${products}" var="product">
        <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
        <li class="span3">
            <div class="thumbnail">
                <a href="/tienda/detalle-producto?code=${product.code}" style="height: 200px;">
                    <c:if test="${numImages>0}">
                        <img src="${product.productImageList[0].image}" alt="${product.name}" style="max-width: 250px; max-height: 200px;"/>
                    </c:if>
                    <c:if test="${numImages==0}">
                        <img src="/image/imagen_no_disponible.png" alt="Imagen no disponible"/>
                    </c:if>
                </a>
                <div class="caption">
                    <h5 style="height: 40px;">${product.name}</h5>
                    <p style="height: 40px;"> 
                        ${fn:substring(product.description,0,60)}...
                    </p>
                    <h4 style="text-align:center">
                        <a class="btn" href="/tienda/detalle-producto?code=${product.code}">
                            <i class="icon-zoom-in"></i>
                        </a>
                        <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a>
                        <a class="btn btn-primary" href="#">$ <fmt:formatNumber type="currency" value="${product.buyUnitPrice}" pattern="###,##0"/></a></h4>
                </div>
            </div>
        </li>
    </c:forEach>
</ul>

