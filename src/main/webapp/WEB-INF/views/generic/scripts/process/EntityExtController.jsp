<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script>

function ${entityName}ExtController(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/${entityRef}";
    
    Instance.modelName="${entityName}Model";
    
    Instance.services= {};
    
    var util= new Util();
    
    // VIEWS *******************************************
    
    Instance.entityExtView= new ${entityName}ExtView(Instance, null);
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.entityRef= "${entityRef}";
        Instance.typeController= "${typeController}";
        mvcExt.mappingController(Instance.id, Instance);
        Instance.initFilter();
    };
    
    Instance.initFilter= function(){
        Instance.filter={};
        Instance.filter.eq={"mainProcessRef":"${viewConfig.mainProcessRef}"};
        Instance.filter.lk={};
        Instance.filter.btw={};
        Instance.filter.in={};
    };
    
    Instance.services.index= function(request){
        var activeTab= util.getParameter(request,"tab");
        var filter= util.getParameter(request,"filter");
        var id= util.getParameter(request,"id");
        
        if(activeTab!==""){
            Instance.entityExtView.tabsContainer.setActiveTab(Number(activeTab));
        }/*else{
            Instance.entityExtView.tabsContainer.setActiveTab(0);
        }*/
        
        if(filter!==""){
            Instance.initFilter();
            var currentFilter= JSON.parse(filter);
            for (var key in currentFilter) {
                Instance.filter[key]= currentFilter[key];
            }
        }
        
        <c:forEach var="associatedER" items="${interfacesEntityRef}">
            <c:set var="associatedEntityName" value="${fn:toUpperCase(fn:substring(associatedER, 0, 1))}${fn:substring(associatedER, 1,fn:length(associatedER))}"></c:set>
        if(Instance.filter.eq.${associatedER}!==undefined && Instance.filter.eq.${associatedER}!==''){
            Instance.entityExtView.${associatedER}ExtInterfaces.entityExtStore.cargar${associatedEntityName}(Instance.filter.eq.${associatedER}, Instance.entityExtView.${associatedER}ExtInterfaces.addLevel);
        }else{
            Instance.entityExtView.${associatedER}ExtInterfaces.addLevel(null);
        }
        </c:forEach>
        
        if(activeTab==="0"){
            Instance.loadFormData(id);
        }
        
        if(activeTab==="1"){
            Instance.loadGridData();
        }
    };
    
    Instance.loadGridData= function(){
        Instance.entityExtView.setFilterStore(JSON.stringify(Instance.filter));
        Instance.entityExtView.reloadPageStore(1);
    };
    
    Instance.loadFormData= function(id){
        if(id!==""){
            Instance.entityExtView.entityExtStore.cargar${viewConfig.entityNameLogProcess}(id, function(data){
                //Show Process
                Ext.getCmp('content-processes').layout.setActiveItem('formContainer'+data.processName+'Model');
                
                //Populate Form
                var record= Ext.create(data.processName+"Model");
                record.data= util.unremakeJSONObject(data.dataIn);
                var formComponent= Ext.getCmp('formContainer'+data.processName+'Model').child('#form'+data.processName+'Model');
                formComponent.setActiveRecord(record);
                
                //Populate tree result
                try{
                    var rootMenu= util.objectToJSONMenu(JSON.parse(data.dataOut), true);
                    var treePanel = Ext.getCmp('tree-result-'+data.processName);
                    treePanel.getStore().setRootNode(rootMenu);
                }catch(e){
                    var divPanel = Ext.getCmp('div-result-'+data.processName);
                    divPanel.update('<textarea readonly style="width:100%; height:400px; white-space: pre !important;">' + data.dataOut+ '</textarea>');
                }
            });
        }
    };
    
    Instance.formSavedResponse= function(processName, responseText){
        try{
            var responseObject= Ext.decode(responseText);
            var rootMenu= util.objectToJSONMenu(responseObject, true);
            var treePanel = Ext.getCmp('tree-result-'+processName);
            treePanel.getStore().setRootNode(rootMenu);
            Ext.MessageBox.alert('Status', responseText);
        }catch(e){
            var divPanel = Ext.getCmp('div-result-'+processName);
            divPanel.update('<textarea readonly style="width:100%; height:400px; white-space: pre !important;">' + responseText + '</textarea>');
        }
    };
    
    Instance.doFilter= function(){
        var url= "?filter="+JSON.stringify(Instance.filter)+"&tab=1";
        console.log(url);
        mvcExt.navigate(url);
    };
    
    Instance.viewInternalPage= function(path){
        var urlAction= path;
        if(Instance.idEntitySelected!==""){
            urlAction+='#?filter={"eq":{"${entityRef}":'+Instance.idEntitySelected+'}}';
        }
        mvcExt.redirect(urlAction);
    };

    Instance.init();
}
</script>