<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${reportName}ExtStore(){
    
    var Instance = this;
    
    
    Instance.get${reportName}Store= function(modelName){
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
    Instance.get${reportName}TemplateStore= function(modelName){
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
    
}
</script>