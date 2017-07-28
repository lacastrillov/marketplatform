/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
util.importJS('/js/web/stores/ProductExtStore.js');
util.importJS('/js/web/stores/ProductImageExtStore.js');

function ShoppingCart() {

    var Instance = this;
    
    var productExtStore;
    
    var productImageExtStore;

    Instance.init = function () {
        Instance.productSummaryTemplate= null;
        Instance.generalSummaryTemplate= null;
        $(document).ready(function () {
            productExtStore= new ProductExtStore();
            productImageExtStore= new ProductImageExtStore();
            Instance.updateProductSummary();
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
        console.log("SET: "+JSON.stringify(scart));
        localStorage.setItem("scart", JSON.stringify(scart));
    };
    
    
    Instance.addToCart = function (productCode) {
        var cart= Instance.getCart();
        var index= Instance.getProductIndex(productCode, cart);
        console.log(index);
        if (index!==-1){
            cart.items[index].quantity+= 1;
            cart.items[index].subTotal+= cart.items[index].product.buyUnitPrice;
            cart.items[index].discount+= (cart.items[index].product.buyUnitPrice * cart.items[index].product.discount)/100;
            cart.items[index].total= cart.items[index].subTotal - cart.items[index].discount;
            cart.subTotal+= cart.items[index].product.buyUnitPrice;
            cart.discount+= (cart.items[index].product.buyUnitPrice * cart.items[index].product.discount)/100;
            cart.total= cart.subTotal - cart.discount;
            
            Instance.setCart(cart);
            Instance.updateProductSummary();
        }else{
            productExtStore.find('{"eq":{"code":"'+productCode+'"}}', function(responseText){
                if(responseText.success && responseText.totalCount===1){
                    var product= responseText.data[0];
                    var index= cart.items.length;
                    cart.items[index]={};
                    cart.items[index].product= product;
                    
                    cart.items[index].quantity=1;
                    cart.items[index].subTotal= product.buyUnitPrice;
                    cart.items[index].discount= (product.buyUnitPrice * cart.items[index].product.discount)/100;
                    cart.items[index].iva= 0;
                    cart.items[index].total= cart.items[index].subTotal - cart.items[index].discount;
                    cart.subTotal+= product.buyUnitPrice;
                    cart.discount+= (product.buyUnitPrice * product.discount)/100;
                    cart.total= cart.subTotal - cart.discount;
                    
                    productImageExtStore.find('{"eq":{"product":"'+product.id+'"}}',"&sort=order&dir=ASC&limit=1", function(responseText){
                        if(responseText.success && responseText.data.length===1){
                            var images= responseText.data;
                            cart.items[index].productImage= images[0].image;
                        }else{
                            cart.items[index].productImage= "/img/imagen_no_disponible.png";
                        }
                        
                        Instance.setCart(cart);
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
                cart.items[index].totalProduct-= cart.items[index].product.buyUnitPrice;
                cart.items[index].discount-= (cart.items[index].product.buyUnitPrice * cart.items[index].product.discount)/100;
                
                cart.subTotal-= cart.items[index].product.buyUnitPrice;
                cart.discount-= (cart.items[index].product.buyUnitPrice * cart.items[index].product.discount)/100;
                cart.total= cart.subTotal - cart.discount;
                
                if(cart.items[index].quantity===0){
                    delete cart.items[index];
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
            cart.subTotal-= cart.items[index].subTotal;
            cart.discount-= cart.items[index].discount;
            cart.total= cart.subTotal - cart.discount;
            
            delete cart.items[index];
            
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
        $("#totalOrderFP").html("$"+util.priceFormat(cart.total));
        Instance.generateTemplateTag("productSummaryTemplate", "tr");
        Instance.generateTemplate("productSummaryTemplate", "tr");
    };
    
    Instance.generateTemplateTag= function(id, tag){
        if($("#"+id).val()!==undefined){
            var template="<"+tag+">"+$("#"+id).html()+"</"+tag+">";
            
            return template;
        }
        return null;
    };
    
    Instance.generateTemplate= function(id, tag){
        if($("#"+id).val()!==undefined){
            var template="<"+tag+">"+$("#"+id).html()+"</"+tag+">";
            
            return template;
        }
        return null;
    };

    Instance.init();
}