<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${reportName}ExtController(parentExtView){
    
    var Instance= this;
    
    Instance.id= "/${entityRef}";
    
    Instance.modelName="${reportName}Model";
    
    Instance.services= {};
    
    var util= new Util();
    
    // VIEWS **********************************************
    
    Instance.reportExtView= new ${reportName}ExtView(Instance, null);
    
    //*******************************************************
    
    
    
    Instance.init= function(){
        mvcExt.mappingController(Instance.id, Instance);
        Instance.initFilter();
    };
    
    Instance.initFilter= function(){
        Instance.filter={};
        Instance.filter.eq={};
        Instance.filter.lk={};
        Instance.filter.btw={};
        Instance.filter.vm={};
    };
    
    Instance.services.index= function(request){
        var filter= util.getParameter(request,"filter");
        
        if(filter!==""){
            Instance.initFilter();
            var currentFilter= JSON.parse(filter);
            for (var key in currentFilter) {
                Instance.filter[key]= currentFilter[key];
            }
            Instance.reportExtView.setFilterStore(filter);
        }
        var requireValueMap= ${reportConfig.visibleValueMapForm};
        
        if(!requireValueMap || Object.getOwnPropertyNames(Instance.filter.vm).length !== 0){
            Instance.reportExtView.reloadPageStore(1);
        }
    };
    
    Instance.doFilter= function(){
        var url= "?filter="+JSON.stringify(Instance.filter);
        mvcExt.navigate(url);
    };

    Instance.init();
}
</script>