<%-- 
    Document   : products_list
    Created on : 15/03/2017, 09:34:55 PM
    Author     : lacastrillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="webFunctions" class="com.dot.gcpbasedot.util.WebFunctions"/>

<ul class="breadcrumb">
    <li><a href="/tienda/">Home</a> <span class="divider">/</span></li>
    <li class="active">${title}</li>
</ul>
<h3> ${title} <small class="pull-right"> <p><strong>${parameters.firstResult+1}</strong> al <strong>${parameters.lastResult}</strong> de <strong>${parameters.totalResults} Resultados</strong></p>   </small></h3>	
<hr class="soft"/>
<form class="form-horizontal span6">
    <div class="control-group">
        <label class="control-label alignL">Ordenar por </label>
        <select id="orderBy" onchange="products.changeOrderBy(this.value)">
            <option value="">-</option>
            <option value="name,ASC">Nombre A - Z</option>
            <option value="name,DESC">Nombre Z - A</option>
            <option value="buyUnitPrice,ASC">Precio Menor a Mayor</option>
            <option value="buyUnitPrice,DESC">Precio Mayor a Menor</option>
            <option value="brand,ASC">Marca A - Z</option>
            <option value="brand,DESC">Marca Z - A</option>
            <option value="registerDate,ASC">Publicado menos reciente</option>
            <option value="registerDate,DESC">Publicado m&aacute;s reciente</option>
        </select>
        <script type="text/javascript">
            $("#orderBy").val("${parameters.orderByParameters[0][0]},${parameters.orderByParameters[0][1]}");
        </script>
    </div>
</form>
<div id="myTab" class="pull-right">
    <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
    <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
</div>
<br class="clr"/>
<div class="tab-content">
    <div class="tab-pane  active" id="blockView">
        <ul class="thumbnails">
            <c:forEach items="${products}" var="product">
                <c:set var="numImages" value="${fn:length(product.productImageList)}"/>
                <li class="span3">
                    <div class="thumbnail">
                        <a href="/tienda/detalle-producto/${product.code}" style="height: 200px;">
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
        <hr class="soft"/>
    </div>
</div>
<a href="compair.html" class="btn btn-large pull-right">Compair Product</a>

<div class="pagination">
    <ul>
        <!-- ${queryString} -->
        <c:if test="${parameters.page>1}">
            <li><a href="/tienda/productos?${webFunctions.addParameterToQueryString(queryString, "page", parameters.page-1)}">&lsaquo;</a></li>
        </c:if>
        <c:set var="startPage" value="1"></c:set>
        <!-- ${parameters.page} -->
        <c:if test="${(parameters.page-3)>0}">
            <c:set var="startPage" value="${parameters.page-3}"></c:set>
        </c:if>
        <c:set var="endPage" value="${startPage+6}"></c:set>
        <c:if test="${parameters.totalPages<endPage}">
            <c:set var="endPage" value="${parameters.totalPages}"></c:set>
        </c:if>
        <c:forEach var="i" begin="${startPage}" end="${endPage}">
            <c:if test="${i == parameters.page}">
                <li class="active"><a href="javascript:void(0);"><b>${i}</b></a></li>
            </c:if>
            <c:if test="${i != parameters.page}">
                <li><a href="/tienda/productos?${webFunctions.addParameterToQueryString(queryString, "page", i)}">${i}</a></li>
            </c:if>
        </c:forEach>
        <c:if test="${parameters.page<parameters.totalPages}">
            <li><a href="/tienda/productos?${webFunctions.addParameterToQueryString(queryString, "page", parameters.page+1)}">&rsaquo;</a></li>
        </c:if>
    </ul>
</div>

<div class="limit">
    <label class="control-label alignL">L&iacute;mite:&nbsp;&nbsp;</label>
    <select id="pagesize" onchange="products.changeLimit(this.value)">
        <option value="">-</option>
        <option value="9">9</option>
        <option value="60">60</option>
        <option value="90">90</option>
        <option value="120">120</option>
    </select>
    <script type="text/javascript">
        $("#pagesize").val(${parameters.maxResults});
    </script>
</div>