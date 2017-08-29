/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
util.importJS('/js/util/Message.js');
util.importJS('/js/web/stores/ProductExtStore.js');
util.importJS('/js/web/stores/ProductImageExtStore.js');
util.importJS('/js/web/stores/PropertyExtStore.js');
util.importJS('/js/web/usuario/UserAuthentication.js');

function ShoppingCart() {

    var Instance = this;
    
    var message;
    
    var productExtStore;
    
    var productImageExtStore;
    
    var propertyExtStore;
    
    var userAuthentication;

    Instance.init = function () {
        Instance.productSummaryTemplate= null;
        Instance.generalSummaryTemplate= null;
        $(document).ready(function () {
            message= new Message();
            productExtStore= new ProductExtStore();
            productImageExtStore= new ProductImageExtStore();
            propertyExtStore= new PropertyExtStore();
            userAuthentication= new UserAuthentication();
            
            Instance.setIVA();
            Instance.updateProductSummary();
            
            $("#ajaxLoginBtn").click(function (e) {
                Instance.login();
            });
        });
    };
    
    Instance.getCart= function(){
        var scart= localStorage.getItem("scart");
        if(scart!==null){
            return JSON.parse(scart);
        }else{
            return {"items":[], "subTotal":0, "discount":0, "iva":0, "total":0};
        }
    };
    
    Instance.setCart= function(scart){
        localStorage.setItem("scart", JSON.stringify(scart));
    };
    
    Instance.updateItemData= function(cart, index){
        var product= cart.items[index].product;
        var quantity= cart.items[index].quantity;
        cart.items[index].subTotal= product.buyUnitPrice * quantity;
        cart.items[index].discount= (product.buyUnitPrice * product.discount * quantity) / 100;
        cart.items[index].iva= (cart.items[index].subTotal - cart.items[index].discount) * Instance.IVA / 100;
        cart.items[index].total= cart.items[index].subTotal - cart.items[index].discount + cart.items[index].iva;
    };
    
    Instance.updateCartData= function(cart, index, operation, all){
        var quantity= cart.items[index].quantity;
        if(operation==="add"){
            cart.subTotal+= cart.items[index].subTotal / quantity;
            cart.discount+= cart.items[index].discount / quantity;
            cart.iva+= cart.items[index].iva / quantity;
        }else if(operation==="remove"){
            if(all){
                quantity= 1;
            }
            cart.subTotal-= cart.items[index].subTotal / quantity;
            cart.discount-= cart.items[index].discount / quantity;
            cart.iva-= cart.items[index].iva / quantity;
        }
        cart.total= cart.subTotal - cart.discount + cart.iva;
    };
    
    Instance.addToCart = function (productCode) {
        var cart= Instance.getCart();
        var index= Instance.getProductIndex(productCode, cart);
        
        if (index!==-1){
            cart.items[index].quantity+= 1;
            
            Instance.updateItemData(cart, index);
            
            Instance.updateCartData(cart, index, "add", false);
            
            Instance.setCart(cart);
            message.showMessage("Agregar al carrito", "Se agrego el producto "+cart.items[index].product.name);
            Instance.updateProductSummary();
        }else{
            productExtStore.find('{"eq":{"code":"'+productCode+'"}}', "", function(responseText){
                if(responseText.success && responseText.totalCount===1){
                    var product= responseText.data[0];
                    var index= cart.items.length;
                    cart.items[index]={};
                    cart.items[index].product= product;
                    cart.items[index].productId= product.id;
                    cart.items[index].quantity= 1;
                    
                    Instance.updateItemData(cart, index);
                    
                    Instance.updateCartData(cart, index, "add", false);
                    
                    productImageExtStore.find('{"eq":{"product":"'+product.id+'"}}',"&sort=order&dir=ASC&limit=1", function(responseText){
                        if(responseText.success && responseText.data.length===1){
                            var images= responseText.data;
                            cart.items[index].productImage= images[0].image;
                        }else{
                            cart.items[index].productImage= "/img/imagen_no_disponible.png";
                        }
                        
                        Instance.setCart(cart);
                        message.showMessage("Agregar al carrito", "Se agrego el producto "+product.name);
                        Instance.updateProductSummary();
                    });
                }
            });
        }
    };
    
    Instance.lessFromCart = function (productCode) {
        var cart= Instance.getCart();
        var index= Instance.getProductIndex(productCode, cart);
        if (index!==-1){
            if(cart.items[index].quantity>=1){
                cart.items[index].quantity-= 1;
                
                Instance.updateItemData(cart, index);
                
                Instance.updateCartData(cart, index, "remove", false);
                
                message.showMessage("Quitar del carrito", "Se resto el producto "+cart.items[index].product.name);
                if(cart.items[index].quantity===0){
                    delete cart.items[index];
                    cart.items = cart.items.filter(function(n){
                        return n !== undefined;
                    });
                }
            }
            Instance.setCart(cart);
            Instance.updateProductSummary();
        }
    };
    
    Instance.removeFromCart= function(productCode){
        var cart= Instance.getCart();
        var index= Instance.getProductIndex(productCode, cart);
        if (index!==-1){
            Instance.updateCartData(cart, index, "remove", true);
            
            message.showMessage("Quitar del carrito", "Se elimino el producto "+cart.items[index].product.name);
            delete cart.items[index];
            cart.items = cart.items.filter(function(n){
                return n !== undefined;
            });
            
            Instance.setCart(cart);
            Instance.updateProductSummary();
        }
    };
    
    Instance.getProductIndex= function(productCode, cart){
        for(var i=0; i<cart.items.length; i++){
            var item= cart.items[i];
            if(item.product.code===productCode){
                return i;
            }
        };
        return -1;
    };
    
    Instance.updateProductSummary= function(){
        var cart= Instance.getCart();
        $("#numItemsFP").html(cart.items.length+" Item(s)");
        $("#numItemsSC").html(cart.items.length+" Item(s)");
        $("#totalOrderFP").html("$"+validation.priceFormat(cart.total));
        if(Instance.productSummaryTemplate===null){
            Instance.productSummaryTemplate= "<tr>"+util.getHtml("productSummaryTemplate")+"</tr>";
            $("#productSummaryTemplate").remove();
        }
        if(Instance.generalSummaryTemplate===null){
            Instance.generalSummaryTemplate= util.getHtml("generalSummaryTemplate");
        }
        $("#generalSummaryTemplate").html("");
        if(Instance.productSummaryTemplate!==null){
            cart.items.forEach(function(item){
                if(item!==null){
                    var result= util.processTemplate(Instance.productSummaryTemplate, item);
                    $("#generalSummaryTemplate").append(result);
                }
            });
        }
        if(Instance.generalSummaryTemplate!==null){
            var result= util.processTemplate(Instance.generalSummaryTemplate, cart);
            $("#generalSummaryTemplate").append(result);
        }
    };
    
    Instance.generatePurchaseOrder= function(){
        var idUserInSession= $("#idUserInSession").val();
        if(idUserInSession!==""){
            var cart= Instance.getCart();
            productExtStore.doProcess("processPurchaseOrder", "generarOrdenCompra", cart,function(responseText){
                message.showMessage("Generar orden de compra", JSON.parse(responseText).message);
                Instance.setCart({"items":[], "subTotal":0, "discount":0, "iva":0, "total":0});
                Instance.updateProductSummary();
            });
        }else{
            message.showMessage("Acci&oacute;n inv&aacute;lida", "No se puede realizar la operaci&oacute;n si no se ha autenticado!!!");
        }
    };
    
    Instance.setIVA= function(){
        propertyExtStore.find('{"eq":{"key":"IVA"}}',"",function(responseText){
            if(responseText.success && responseText.totalCount===1){
                Instance.IVA= Number(responseText.data[0].value);
            }else{
                Instance.IVA= 0;
            }
        });
    };
    
    Instance.login= function(){
        userAuthentication.authenticate(function(data){
            if(data.success){
                $("#idUserInSession").val(data.user.id);
                $("#userNameData").html(data.user.username+" - "+data.user.name);
                $("#userEmail").html(data.user.email);
                $("#loginUserTable").hide();
                $("#userInSessionTable").show();
            }else{
                message.showMessage("Iniciar Sesi&oacute;n", data.message);
            }
        });
    };

    Instance.init();
}