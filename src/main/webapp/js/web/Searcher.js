/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function Searcher(){
    
    var Instance = this;
    

    Instance.init = function () {
        $(document).ready(function () {
            
        });
    };

    Instance.search = function (query) {
        if(query!==""){
            location.href='/productos/listado?filter=(lk:(name:"'+query+'"))';
        }
    };

    Instance.init();
}