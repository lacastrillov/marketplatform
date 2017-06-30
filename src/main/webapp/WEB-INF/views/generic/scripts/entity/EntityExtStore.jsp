<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${entityName}ExtStore(){
    
    var Instance = this;
    
    var errorGeneral= "Error de servidor";
    var error403= "Usted no tiene permisos para realizar esta operaci&oacute;n";
    
    
    Instance.getStore= function(modelName){
        var store = Ext.create('Ext.data.Store', {
            model: modelName,
            autoLoad: false,
            autoSync: ${viewConfig.defaultAutoSave},
            pageSize: ${viewConfig.maxResultsPerPage},
            remoteSort: true,
            proxy: {
                type: 'ajax',
                batchActions: false,
                simpleSortMode: true,
                actionMethods : {
                    create : 'POST',
                    read   : 'GET',
                    update : 'POST',
                    destroy: 'GET'
                },
                api: {
                    read: Ext.context+'/rest/${entityRef}/find.htm',
                    create: Ext.context+'/rest/${entityRef}/create.htm',
                    update: Ext.context+'/rest/${entityRef}/update.htm',
                    destroy: Ext.context+'/rest/${entityRef}/delete.htm'
                },
                reader: {
                    type: 'json',
                    successProperty: 'success',
                    root: 'data',
                    totalProperty: 'totalCount',
                    messageProperty: 'message'
                },
                writer: {
                    type: 'json',
                    encode: true,
                    writeAllFields: false,
                    root: 'data'
                },
                extraParams: {
                    filter: null
                },
                listeners: {
                    exception: function(proxy, response, operation){
                        var errorMsg= operation.getError();
                        if(typeof errorMsg === "object"){
                            if(errorMsg.status===403){
                                errorMsg= error403;
                            }else{
                                errorMsg= errorGeneral;
                            }
                        }
                        showErrorMessage(errorMsg);
                    }
                }
            },
            listeners: {
                load: function() {
                    var gridComponent= null;
                    if(this.gridContainer){
                        gridComponent= this.gridContainer.child('#grid'+modelName);
                        gridComponent.getSelectionModel().deselectAll();
                    }
                },
                write: function(proxy, operation){
                    if (operation.action === 'destroy') {
                        Ext.MessageBox.alert('Status', operation.resultSet.message);
                    }
                }
            },
            sorters: [{
                property: '${viewConfig.defaultOrderBy}',
                direction: '${viewConfig.defaultOrderDir}'
            }],
            formContainer:null,
            gridContainer:null
        });
        
        return store;
    };
    
    <c:if test="${viewConfig.activeGridTemplateAsParent || viewConfig.activeGridTemplateAsChild}">
    Instance.getTemplateStore= function(modelName){
        var store = Ext.create('Ext.data.Store', {
            model: modelName,
            autoLoad: false,
            pageSize: ${viewConfig.maxResultsPerPage},
            remoteSort: true,
            proxy: {
                type: 'ajax',
                batchActions: false,
                simpleSortMode: true,
                actionMethods : {
                    read   : 'GET'
                },
                api: {
                    read: Ext.context+'/rest/${entityRef}/find.htm'
                },
                reader: {
                    type: 'json',
                    successProperty: 'success',
                    root: 'data',
                    totalProperty: 'totalCount',
                    messageProperty: 'message'
                },
                extraParams: {
                    filter: null,
                    templateName: '${viewConfig.gridTemplate.templateName}',
                    numColumns: ${viewConfig.gridTemplate.numColumns}
                },
                listeners: {
                    exception: function(proxy, response, operation){
                        var errorMsg= operation.getError();
                        if(typeof errorMsg === "object"){
                            if(errorMsg.status===403){
                                errorMsg= error403;
                            }else{
                                errorMsg= errorGeneral;
                            }
                        }
                        showErrorMessage(errorMsg);
                    }
                }
            },
            listeners: {
                load: function() {
                    var gridComponent= null;
                    if(this.gridContainer){
                        gridComponent= this.gridContainer.child('#grid'+modelName);
                        gridComponent.getSelectionModel().deselectAll();
                    }
                }
            },
            sorters: [{
                property: '${viewConfig.defaultOrderBy}',
                direction: '${viewConfig.defaultOrderDir}'
            }],
            formContainer:null,
            gridContainer:null
        });
        
        return store;
    };
    </c:if>

    Instance.find= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/find.htm",
            method: "GET",
            params: "filter="+encodeURIComponent(filter),
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText);
            },
            failure: function(response){
                console.log(response);
                if(response.status===403){
                    showErrorMessage(error403);
                }else{
                    showErrorMessage(errorGeneral);
                }
            }
        });
    };
    
    Instance.save= function(operation, data, func){
        Ext.MessageBox.show({
            msg: 'Guardando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/"+operation+".htm",
            method: "POST",
            params: "data="+encodeURIComponent(data),
            success: function(response){
                Ext.MessageBox.hide();
                var responseText= Ext.decode(response.responseText);
                func(responseText);
            },
            failure: function(response){
                console.log(response);
                if(response.status===403){
                    showErrorMessage(error403);
                }else{
                    showErrorMessage(errorGeneral);
                }
            }
        });
    };
    
    Instance.load= function(idEntity, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/load.htm",
            method: "GET",
            params: 'data='+encodeURIComponent('{"id":'+idEntity+'}'),
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText.data);
            },
            failure: function(response){
                console.log(response);
                if(response.status===403){
                    showErrorMessage(error403);
                }else{
                    showErrorMessage(errorGeneral);
                }
            }
        });
    };
    
    Instance.upload= function(form, idEntity, func){
        form.submit({
            url: Ext.context+"/rest/${entityRef}/diskupload/"+idEntity+".htm",
            waitMsg: 'Subiendo archivo...',
            success: function(form, action) {
                func(action.result);
            },
            failure: function(response){
                console.log(response);
                if(response.status===403){
                    showErrorMessage(error403);
                }else{
                    showErrorMessage(errorGeneral);
                }
            }
        });
    };
    
    Instance.doProcess= function(mainProcessRef, processName, data, func){
        Ext.MessageBox.show({
            msg: 'Ejecutando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/"+mainProcessRef+"/doProcess.htm",
            method: "POST",
            headers: {
                'Content-Type' : 'application/json'
            },
            jsonData: {'processName': processName, 'data': Ext.decode(util.remakeJSONObject(data))},
            success: function(response){
                Ext.MessageBox.hide();
                func(response.responseText);
            },
            failure: function(response){
                console.log(response.responseText);
                if(response.status===403){
                    showErrorMessage(error403);
                }else{
                    showErrorMessage(errorGeneral);
                }
            }
        });
    };
    
    Instance.deleteByFilter= function(filter, func){
        Ext.MessageBox.show({
            msg: 'Eliminando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/delete/byfilter.htm",
            method: "POST",
            params: "filter="+encodeURIComponent(filter),
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText);
                Ext.MessageBox.hide();
            },
            failure: function(response){
                console.log(response);
                if(response.status===403){
                    showErrorMessage(error403);
                }else{
                    showErrorMessage(errorGeneral);
                }
            }
        });
    };
    
    function showErrorMessage(errorMsg){
        Ext.MessageBox.show({
            title: 'REMOTE EXCEPTION',
            msg: errorMsg,
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK
        });
    }

}
</script>