<%-- 
    Document   : menu_categories_subcategories
    Created on : 13/03/2017, 10:45:47 PM
    Author     : lacastrillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

                        <ul id="sideManu" class="nav nav-tabs nav-stacked">
                            <c:forEach items="${categories}" var="category">
                                <li class="subMenu">
                                    <a> ${category.name} [230]</a>
                                    <ul style="display:none">
                                        <c:forEach items="${category.subCategoryList}" var="subCategory">
                                            <li><a href="/tienda/productos?subCategory=${subCategory.id}"><i class="icon-chevron-right"></i>${subCategory.name} (100) </a></li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:forEach>
                        </ul>