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
        }else{
            Instance.entityExtView.tabsContainer.setActiveTab(0);
        }
        
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
            Instance.entityExtView.${associatedER}ExtInterfaces.entityExtStore.cargar${associatedEntityName}(Instance.filter.eq.${associatedER}, Instance.entityExtView.${associatedER}ExtInterfaces.agregarNivel);
        }else{
            Instance.entityExtView.${associatedER}ExtInterfaces.agregarNivel(null);
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
        if(Instance.entityExtView.formContainer!==null){
            var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
            formComponent.setActiveRecord(null);
        }
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
                var rootMenu= util.objectToJSONMenu(JSON.parse(data.dataOut));
                var treePanel = Ext.getCmp('tree-result-'+data.processName);
                treePanel.getStore().setRootNode(rootMenu);
            });
        }
    };
    
    Instance.formSavedResponse= function(processName, responseText){
        //var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
        <c:if test="${viewConfig.multipartFormData}">
        Instance.entityExtView.entityExtStore.upload${entityName}(formComponent, responseText.data.id, function(responseUpload){
            Ext.MessageBox.alert('Status', responseText.message+"<br>"+responseUpload.message);
            if(responseUpload.success){
                var record= Ext.create(Instance.modelName);
                record.data= responseUpload.data;
                formComponent.setActiveRecord(record || null);
            }
        });
        </c:if>
        <c:if test="${not viewConfig.multipartFormData}">
        var rootMenu= util.objectToJSONMenu(responseText);
        var treePanel = Ext.getCmp('tree-result-'+processName);
        treePanel.getStore().setRootNode(rootMenu);
        var textMenu= JSON.stringify(responseText);
        Ext.MessageBox.alert('Status', textMenu);
        </c:if>
    };
    
    Instance.doFilter= function(){
        var url= "?filter="+JSON.stringify(Instance.filter);
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