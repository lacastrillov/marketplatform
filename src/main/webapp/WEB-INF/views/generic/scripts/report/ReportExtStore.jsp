<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${reportName}ExtStore(){
    
    var Instance = this;
    
    var errorGeneral= "Error de servidor";
    var error403= "Usted no tiene permisos para realizar esta operaci&oacute;n";
    
    Instance.getStore= function(modelName){
        var store = Ext.create('Ext.data.Store', {
            model: modelName,
            autoLoad: false,
            pageSize: ${reportConfig.maxResultsPerPage},
            remoteSort: true,
            proxy: {
                type: 'ajax',
                batchActions: false,
                simpleSortMode: true,
                actionMethods : {
                    read   : 'GET'
                },
                api: {
                    read: Ext.context+'/rest/${entityRef}/report/${reportName}.htm'
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
                    dtoName: '${reportConfig.dtoName}'
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
            sorters: [
                <c:if test="${not empty reportConfig.defaultOrderBy && not empty reportConfig.defaultOrderDir}">
                {
                    property: '${reportConfig.defaultOrderBy}',
                    direction: '${reportConfig.defaultOrderDir}'
                }
                </c:if>
            ],
            gridContainer:null
        });
        
        return store;
    };
    
    <c:if test="${reportConfig.activeGridTemplate}">
    Instance.getTemplateStore= function(modelName){
        var store = Ext.create('Ext.data.Store', {
            model: modelName,
            autoLoad: false,
            pageSize: ${reportConfig.maxResultsPerPage},
            remoteSort: true,
            proxy: {
                type: 'ajax',
                batchActions: false,
                simpleSortMode: true,
                actionMethods : {
                    read   : 'GET'
                },
                api: {
                    read: Ext.context+'/rest/${entityRef}/report/${reportName}.htm'
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
                    dtoName: '${reportConfig.dtoName}',
                    templateName: '${reportConfig.gridTemplate.templateName}',
                    numColumns: ${reportConfig.gridTemplate.numColumns}
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
            sorters: [
                <c:if test="${not empty reportConfig.defaultOrderBy && not empty reportConfig.defaultOrderDir}">
                {
                    property: '${reportConfig.defaultOrderBy}',
                    direction: '${reportConfig.defaultOrderDir}'
                }
                </c:if>
            ],
            gridContainer:null
        });
        
        return store;
    };
    </c:if>

    Instance.load= function(idRecord, func){
        Ext.Ajax.request({
            url:  Ext.context+'/rest/${entityRef}/report/${reportName}.htm',
            method: "GET",
            params: 'filter='+encodeURIComponent('{"${reportConfig.idColumnName}":'+idRecord+'}')+'&dtoName=${reportConfig.dtoName}',
            success: function(response){
                var responseText= Ext.decode(response.responseText);
                func(responseText.data[0]);
            },
            failure: function(response){
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