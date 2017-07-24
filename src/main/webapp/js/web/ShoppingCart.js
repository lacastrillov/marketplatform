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
        if(scart!==null){
            return JSON.parse(scart);
        }else{
            return {"items":{}, "total":0, "discount":0, "finalPrice":0};
        }
    };
    
    Instance.setCart= function(scart){
        console.log(scart);
        localStorage.setItem("scart", JSON.stringify(scart));
    };
    
    
    Instance.addToCart = function (productCode) {
        var cart= Instance.getCart();
        if (productCode in cart.items){
            var product= cart.items[productCode].product;
            cart.items[productCode].quantity+= 1;
            cart.items[productCode].totalProduct+= product.buyUnitPrice;
            cart.items[productCode].discount+= (product.buyUnitPrice*product.discount)/100;
            cart.total+= product.buyUnitPrice;
            cart.discount+= (product.buyUnitPrice*product.discount)/100;
            cart.finalPrice= cart.total - cart.discount;
            
            Instance.setCart(cart);
            
            Instance.updateProductSummary();
        }else{
            productExtStore.find('{"eq":{"code":"'+productCode+'"}}', function(responseText){
                if(responseText.success && responseText.totalCount===1){
                    var product= responseText.data[0];
                    cart.items[productCode].product= product;
                    cart.items[productCode].quantity=1;
                    cart.items[productCode].totalProduct= product.buyUnitPrice;
                    cart.items[productCode].discount= (product.buyUnitPrice*product.discount)/100;
                    cart.total+= product.buyUnitPrice;
                    cart.discount+= (product.buyUnitPrice*product.discount)/100;
                    cart.finalPrice= cart.total - cart.discount;
                    
                    Instance.setCart(cart);
                    
                    Instance.updateProductSummary();
                }
            });
        }
    };
    
    Instance.lessFromCart = function (productCode) {
        var cart= Instance.getCart();
        if (productCode in cart.items){
            if(cart.items[productCode].quantity>=1){
                var product= cart.items[productCode].product;
                cart.items[productCode].quantity-= 1;
                cart.items[productCode].totalProduct-= product.buyUnitPrice;
                cart.items[productCode].discount-= (product.buyUnitPrice*product.discount)/100;
                cart.total-= product.buyUnitPrice;
                cart.discount-= (product.buyUnitPrice*product.discount)/100;
                cart.finalPrice= cart.total - cart.discount;
            }
            Instance.setCart(cart);
            
            Instance.updateProductSummary();
        }
    };
    
    Instance.removeFromCart= function(productCode){
        var cart= Instance.getCart();
        if (productCode in cart.items){
            var product= cart.items[productCode].product;
            
            cart.total-= product.buyUnitPrice*cart.items[productCode].quantity;
            cart.discount-= ((product.buyUnitPrice*product.discount)/100)*cart.items[productCode].quantity;
            cart.finalPrice= cart.total - cart.discount;
            
            cart.items[productCode].quantity= 0;
            cart.items[productCode].totalProduct= 0;
            cart.items[productCode].discount= 0;
            
            
            Instance.setCart(cart);
            
            Instance.updateProductSummary();
        }
    };
    
    Instance.updateProductSummary= function(){
        
    };

    Instance.init();
}