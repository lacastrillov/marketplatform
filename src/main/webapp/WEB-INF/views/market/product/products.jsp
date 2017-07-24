<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Mercando - ${title}</title>
        <link href="/css/products.css" rel="stylesheet" type="text/css">
        <script src="/js/web/Products.js"></script>
        <script src="/js/web/ShoppingCart.js"></script>
        <script>
            var products= new Products();
            var shoppingCart= new ShoppingCart();
        </script>
    </head>
    <body>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    
                    <jsp:include page="/WEB-INF/views/market/filter_panel.jsp"></jsp:include>
                    
                    <div class="span9">
                        
                        <jsp:include page="/WEB-INF/views/market/product/product_list.jsp"></jsp:include>
                        
                        <br class="clr"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>