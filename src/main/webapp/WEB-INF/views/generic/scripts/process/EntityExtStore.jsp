<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${entityName}ExtStore(){
    
    var Instance = this;
    
    var util= new Util();
    
    
    Instance.get${entityNameLogProcess}Store= function(modelName){
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
                    read: Ext.context+'/rest/${entityRefLogProcess}/find.htm',
                    create: Ext.context+'/rest/${entityRefLogProcess}/create.htm',
                    update: Ext.context+'/rest/${entityRefLogProcess}/update.htm',
                    destroy: Ext.context+'/rest/${entityRefLogProcess}/delete.htm'
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
                            errorMsg= "Error de servidor";
                        }
                        Ext.MessageBox.show({
                            title: 'REMOTE EXCEPTION',
                            msg: errorMsg,
                            icon: Ext.MessageBox.ERROR,
                            buttons: Ext.Msg.OK
                        });
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

    Instance.find${entityName}= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRefLogProcess}/find.htm",
            method: "GET",
            params: "filter="+encodeURIComponent(filter),
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText);
            },
            failure: function(response){
                console.log(response.responseText);
            }
        });
    };
    
    Instance.save${entityName}= function(operation, processName, data, func){
        Ext.MessageBox.show({
            msg: 'Ejecutando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/"+operation+"/"+processName+".htm",
            method: "POST",
            params: "data="+encodeURIComponent(util.remakeJSONObject(data)),
            success: function(response){
                Ext.MessageBox.hide();
                var responseText= Ext.decode(response.responseText);
                func(processName, responseText);
            },
            failure: function(response){
                console.log(response.responseText);
            }
        });
    };
    
    Instance.doProcess${entityName}= function(processName, data, func){
        Ext.MessageBox.show({
            msg: 'Ejecutando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/doProcess.htm",
            method: "POST",
            headers: {
                'Content-Type' : 'application/json'
            },
            jsonData: {'processName': processName, 'data': Ext.decode(util.remakeJSONObject(data))},
            success: function(response){
                Ext.MessageBox.hide();
                var responseText= Ext.decode(response.responseText);
                func(processName, responseText);
            },
            failure: function(response){
                console.log(response.responseText);
            }
        });
    };
    
    Instance.cargar${entityNameLogProcess}= function(id${entityNameLogProcess}, func){
        Ext.MessageBox.show({
            msg: 'Cargando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRefLogProcess}/load.htm",
            method: "GET",
            params: 'data='+encodeURIComponent('{"id":'+id${entityNameLogProcess}+'}'),
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText.data);
                Ext.MessageBox.hide();
            },
            failure: function(response){
                console.log(response.responseText);
            }
        });
    };
    
    Instance.upload${entityName}= function(form, idEntity, func){
        form.submit({
            url: Ext.context+"/rest/${entityRef}/upload/"+idEntity+".htm",
            waitMsg: 'Subiendo archivo...',
            success: function(form, action) {
                func(action.result);
            }
        });
    };
    
    Instance.deleteByFilter${entityName}= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/${entityRef}/delete/byfilter.htm",
            method: "POST",
            params: "filter="+encodeURIComponent(filter),
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText);
            },
            failure: function(response){
                console.log(response.responseText);
            }
        });
    };

}
</script>