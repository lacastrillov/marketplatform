/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Products() {

    var Instance = this;
    

    Instance.init = function () {
        $(document).ready(function () {
            
        });
    };

    Instance.changeOrderBy = function (newOrder) {
        if(newOrder!==""){
            var order= newOrder.split(",");
            var url= util.addUrlParameter(document.URL, "sort", order[0]);
            url= util.addUrlParameter(url, "dir", order[1]);
            url= util.removeUrlParameter(url, "page");
            location.href= url;
        }
    };
    
    Instance.changeLimit = function (limit) {
        if(limit!==""){
            var url= util.addUrlParameter(document.URL, "limit", limit);
            url= util.removeUrlParameter(url, "page");
            location.href= url;
        }
    };

    Instance.init();
}