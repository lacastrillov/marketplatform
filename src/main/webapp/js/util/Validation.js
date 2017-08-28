/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function Validation(){
    
    var Instance = this;
    
    Instance.init = function () {
        
    };
    
    this.priceFormat= function(n){
        return n.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,");
    };
    
    this.isNumeric= function(text){
        return /^\d+$/.test(text);
    };
    
    this.isValidEmail= function (email) {
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    };
    
    this.isAbcText= function (e) {
        return tecla = document.all ? e.keyCode : e.which, 8 === tecla || (0 === tecla || (patron = /[A-Za-z\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA\u00D1\u00F1\s]/, te = String.fromCharCode(tecla), patron.test(te)));
    };
    
    this.isNumValue= function(e) {
        return tecla = document.all ? e.keyCode : e.which, 8 === tecla || (0 === tecla || (patron = /[1234567890]/, te = String.fromCharCode(tecla), patron.test(te)));
    };
    
    this.isNumOrAbcText= function(e) {
        return tecla = document.all ? e.keyCode : e.which, 8 === tecla ||
                (0 === tecla ||
                (patron = /[A-Za-z\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA\u00D1\u00F1\s]/, te = String.fromCharCode(tecla), patron.test(te)) ||
                (patron = /[1234567890]/, te = String.fromCharCode(tecla), patron.test(te))
                );
    };
    
    Instance.init();
}
var validation= new Validation();