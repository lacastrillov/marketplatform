/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
util.importJS('/js/web/stores/ProductExtStore.js');

function ShoppingCart() {

    var Instance = this;
    
    var productExtStore;

    Instance.init = function () {
        $(document).ready(function () {
            productExtStore= new ProductExtStore();
        });
    };
    
    Instance.getCart= function(){
        var scart= localStorage.getItem("scart");
        console.log("GET: "+scart);
        if(scart!==null){
            return JSON.parse(scart);
        }else{
            return {"items":[], "subTotal":0, "discount":0, "total":0};
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
            cart.items[index].subTotal+= cart.items[index].buyUnitPrice;
            cart.items[index].discount+= (cart.items[index].buyUnitPrice * cart.items[index].productDiscount)/100;
            cart.items[index].total= cart.items[index].subTotal - cart.items[index].discount;
            cart.subTotal+= cart.items[index].buyUnitPrice;
            cart.discount+= (cart.items[index].buyUnitPrice * cart.items[index].productDiscount)/100;
            cart.total= cart.subTotal - cart.discount;
            
            Instance.setCart(cart);
            
            Instance.updateProductSummary();
        }else{
            productExtStore.find('{"eq":{"code":"'+productCode+'"}}', function(responseText){
                if(responseText.success && responseText.totalCount===1){
                    var product= responseText.data[0];
                    var index= cart.items.length;
                    cart.items[index]={};
                    cart.items[index].productCode= product.code;
                    cart.items[index].buyUnitPrice= product.buyUnitPrice;
                    cart.items[index].productDiscount= product.discount;
                    
                    cart.items[index].quantity=1;
                    cart.items[index].subTotal= product.buyUnitPrice;
                    cart.items[index].discount= (product.buyUnitPrice * cart.items[index].productDiscount)/100;
                    cart.items[index].total= cart.items[index].subTotal - cart.items[index].discount;
                    cart.subTotal+= product.buyUnitPrice;
                    cart.discount+= (product.buyUnitPrice * product.discount)/100;
                    cart.total= cart.subTotal - cart.discount;
                    
                    Instance.setCart(cart);
                    
                    Instance.updateProductSummary();
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
                cart.items[index].totalProduct-= cart.items[index].buyUnitPrice;
                cart.items[index].discount-= (cart.items[index].buyUnitPrice * cart.items[index].productDiscount)/100;
                
                cart.subTotal-= cart.items[index].buyUnitPrice;
                cart.discount-= (cart.items[index].buyUnitPrice * cart.items[index].productDiscount)/100;
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
            cart.subTotal-= cart.items[index].buyUnitPrice * cart.items[index].quantity;
            cart.discount-= ((cart.items[index].buyUnitPrice * cart.items[index].productDiscount)/100) * cart.items[index].quantity;
            cart.total= cart.subTotal - cart.discount;
            
            delete cart.items[index];
            
            Instance.setCart(cart);
            
            Instance.updateProductSummary();
        }
    };
    
    Instance.getProductIndex= function(productCode, cart){
        for(var i=0; i<cart.items.length; i++){
            var product= cart.items[i];
            if(product.productCode===productCode){
                return i;
            }
        };
        return -1;
    };
    
    Instance.updateProductSummary= function(){
        
    };

    Instance.init();
}