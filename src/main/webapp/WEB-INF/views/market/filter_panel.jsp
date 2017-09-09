<%-- 
    Document   : filter_panel
    Created on : 13/03/2017, 10:26:46 PM
    Author     : lacastrillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Sidebar ================================================== -->
<div id="sidebar" class="span3">
    
    <c:import url="/vista/category/component/menu-categories-subcategories"></c:import>
    
    <br/>
    
    <table id="simpleSummary" class="table table-bordered" style="display:none">
        <thead>
            <tr>
                <th colspan="3">CARRITO DE COMPRAS</th>
            </tr>
        </thead>
        <tbody id="currentItems">
            <tr id="simpleProductTemplate" style="display:none;">
                <td>
                    <a class="btn btn-danger small-close-btn" onclick="shoppingCart.removeFromCart('={product.code}')" href="javascript:void(0)">
                        <i class="icon-remove icon-white"></i>
                    </a>
                    <img src="={productImage}" alt="" width="60">
                </td>
                <td><a href="/productos/detalle/={product.code}">={product.name}</a><br><span class="badge badge-inverse">={quantity}</span></td>
                <td>={total}</td>
            </tr>
        </tbody>
    </table>
    <div class="well well-small">
        <a id="myCart" href="/tienda/carrito-de-compras">
            <img src="/themes/images/ico-cart.png" alt="cart">
            <span id="numItemsFP">0 Items</span>
            <span id="totalOrderFP" class="badge badge-warning pull-right">$0.00</span>
        </a>
    </div>
    
    <!--<div class="thumbnail">
        <img src="/themes/images/products/panasonic.jpg" alt="Bootshop panasonoc New camera"/>
        <div class="caption">
            <h5>Panasonic</h5>
            <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
        </div>
    </div><br/>
    <div class="thumbnail">
        <img src="/themes/images/products/kindle.png" title="Bootshop New Kindel" alt="Bootshop Kindel">
        <div class="caption">
            <h5>Kindle</h5>
            <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
        </div>
    </div><br/>
    <div class="thumbnail">
        <img src="/themes/images/payment_methods.png" title="Bootshop Payment Methods" alt="Payments Methods">
        <div class="caption">
            <h5>Payment Methods</h5>
        </div>
    </div>-->
</div>
<!-- Sidebar end=============================================== -->