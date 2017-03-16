<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Productos</title>
    </head>
    <body>
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    
                    <jsp:include page="/WEB-INF/views/market/filter_panel.jsp"></jsp:include>
                    
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                            <li class="active">Products Name</li>
                        </ul>
                        <h3> Products Name <small class="pull-right"> 40 products are available </small></h3>	
                        <hr class="soft"/>
                        <p>
                            Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - that is why our goods are so popular and we have a great number of faithful customers all over the country.
                        </p>
                        <hr class="soft"/>
                        <form class="form-horizontal span6">
                            <div class="control-group">
                                <label class="control-label alignL">Sort By </label>
                                <select>
                                    <option>Priduct name A - Z</option>
                                    <option>Priduct name Z - A</option>
                                    <option>Priduct Stoke</option>
                                    <option>Price Lowest first</option>
                                </select>
                            </div>
                        </form>

                        <div id="myTab" class="pull-right">
                            <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
                            <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
                        </div>
                        <br class="clr"/>
                        <div class="tab-content">
                            
                            <div class="tab-pane  active" id="blockView">
                                
                                <c:import url="/vista/product/component/product-list"></c:import>
                                
                                <hr class="soft"/>
                            </div>
                        </div>

                        <a href="compair.html" class="btn btn-large pull-right">Compair Product</a>
                        <div class="pagination">
                            <ul>
                                <li><a href="#">&lsaquo;</a></li>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">...</a></li>
                                <li><a href="#">&rsaquo;</a></li>
                            </ul>
                        </div>
                        <br class="clr"/>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>