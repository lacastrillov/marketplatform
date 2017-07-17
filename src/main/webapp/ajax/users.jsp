



<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tablas Lead - Administracion MERCANDO</title>
        <link rel="icon" type="image/icon" href="/img/favicon.png" /> 
        
        <script type="text/javascript">
            var ExtJSLib="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext";
        </script>
        
        <script src="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext/examples/shared/include-ext.js"></script>
        <!--<script src="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext/examples/shared/options-toolbar.js"></script>-->
        
        <style>
            .x-html-editor-input textarea{white-space: pre !important;}
        </style>
        
        <!-- ############################ IMPORT LAYOUTS ################################ -->
        
        
        
        <!-- ############################ IMPORT MODELS ################################### -->
        
        
            
<script>

function LeadTableExtModel(){
    
    var Instance = this;
    
    
    Instance.defineModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"description","type":"string"},{"useNull":true,"name":"id","type":"int"},{"name":"name","type":"string"},{"name":"status","type":"string"},{"name":"tableAlias","type":"string"}],
            validations: [{"min":0,"field":"name","max":100,"type":"length"},{"min":0,"field":"description","max":200,"type":"length"},{"min":0,"field":"tableAlias","max":100,"type":"length"},{"min":0,"field":"status","max":45,"type":"length"}]
        });
    };
    
    
    
}
</script>
        
            
<script>

function TableColumnExtModel(){
    
    var Instance = this;
    
    
    Instance.defineModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"columnAlias","type":"string"},{"name":"columnOrder","type":"int"},{"name":"columnSize","type":"int", defaultValue: '99'},{"name":"dataType","type":"string"},{"name":"defaultValue","type":"string"},{"name":"fieldType","type":"string"},{"useNull":true,"name":"id","type":"int"},{"name":"leadTable"},{"name":"name","type":"string"},{"name":"options","type":"string"},{"name":"width","type":"int"}],
            validations: [{"min":1,"field":"leadTable","type":"length"},{"min":0,"field":"dataType","max":45,"type":"length"},{"min":0,"field":"name","max":100,"type":"length"},{"min":0,"field":"fieldType","max":45,"type":"length"},{"min":0,"field":"columnAlias","max":100,"type":"length"}]
        });
    };
    
    
    
}
</script>
        
        
        <!-- ############################ IMPORT STORES ################################### -->
        
        
            
<script>

function LeadTableExtStore(){
    
    var Instance = this;
    
    var errorGeneral= "Error de servidor";
    var error403= "Usted no tiene permisos para realizar esta operaci&oacute;n";
    
    
    Instance.getStore= function(modelName){
        var store = Ext.create('Ext.data.Store', {
            model: modelName,
            autoLoad: false,
            autoSync: true,
            pageSize: 50,
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
                    read: Ext.context+'/rest/leadTable/find.htm',
                    create: Ext.context+'/rest/leadTable/create.htm',
                    update: Ext.context+'/rest/leadTable/update.htm',
                    destroy: Ext.context+'/rest/leadTable/delete.htm'
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
                property: 'id',
                direction: 'DESC'
            }],
            formContainer:null,
            gridContainer:null
        });
        
        return store;
    };
    
    

    Instance.find= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/leadTable/find.htm",
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
            url: Ext.context+"/rest/leadTable/"+operation+".htm",
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
            url: Ext.context+"/rest/leadTable/load.htm",
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
            url: Ext.context+"/rest/leadTable/diskupload/"+idEntity+".htm",
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
            url: Ext.context+"/rest/leadTable/delete/byfilter.htm",
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
        
            
<script>

function TableColumnExtStore(){
    
    var Instance = this;
    
    var errorGeneral= "Error de servidor";
    var error403= "Usted no tiene permisos para realizar esta operaci&oacute;n";
    
    
    Instance.getStore= function(modelName){
        var store = Ext.create('Ext.data.Store', {
            model: modelName,
            autoLoad: false,
            autoSync: true,
            pageSize: 50,
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
                    read: Ext.context+'/rest/tableColumn/find.htm',
                    create: Ext.context+'/rest/tableColumn/create.htm',
                    update: Ext.context+'/rest/tableColumn/update.htm',
                    destroy: Ext.context+'/rest/tableColumn/delete.htm'
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
                property: 'id',
                direction: 'DESC'
            }],
            formContainer:null,
            gridContainer:null
        });
        
        return store;
    };
    
    

    Instance.find= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/tableColumn/find.htm",
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
            url: Ext.context+"/rest/tableColumn/"+operation+".htm",
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
            url: Ext.context+"/rest/tableColumn/load.htm",
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
            url: Ext.context+"/rest/tableColumn/diskupload/"+idEntity+".htm",
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
            url: Ext.context+"/rest/tableColumn/delete/byfilter.htm",
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
        
        
        <!-- ############################ IMPORT VIEWS ################################### -->
        
        
             
        

<script>

function LeadTableExtView(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/leadTable";
    
    Instance.modelName="LeadTableModel";
    
    var util= new Util();
    
    // MODELS **********************************************
    
    Instance.entityExtModel= new LeadTableExtModel();
    
    // STORES **********************************************
    
    Instance.entityExtStore= new LeadTableExtStore();
    
    // COMPONENTS *******************************************
    
    Instance.commonExtView= new CommonExtView(parentExtController, Instance, 'LeadTable');
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.typeView= "Parent";
        Instance.pluralEntityTitle= 'Tablas Lead';
        Instance.entityExtModel.defineModel(Instance.modelName);
        Instance.store= Instance.entityExtStore.getStore(Instance.modelName);
        
        Instance.createMainView();
    };
    
    Instance.setFilterStore= function(filter){
        
            Instance.store.getProxy().extraParams.filter= filter;
        
        
    };
    
    Instance.reloadPageStore= function(page){
        
            Instance.store.loadPage(page);
        
        
    };
    
    
    
    
    function getFormContainer(modelName, store, childExtControllers){
        var formFields= [{"xtype":"numberfield","fieldLabel":"Id","name":"id","readOnly":true},{"allowBlank":false,"fieldLabel":"Nombre","name":"name"},{"allowBlank":false,"fieldLabel":"Alias","name":"tableAlias"},{"xtype":"textarea","fieldLabel":"Descripci&oacute;n","name":"description","height":200},Instance.commonExtView.getSimpleCombobox('status','Estado','form',['Active','Inactive'])];

        var renderReplacements= [];

        var additionalButtons= [];

        Instance.defineWriterForm(Instance.modelName, formFields, renderReplacements, additionalButtons, childExtControllers, Instance.typeView);
        
        var itemsForm= [{
            itemId: 'form'+modelName,
            xtype: 'writerform'+modelName,
            border: false,
            width: '100%',
            listeners: {
                create: function(form, data){
                    Instance.entityExtStore.save('create', JSON.stringify(data), parentExtController.formSavedResponse);
                },
                update: function(form, data){
                    Instance.entityExtStore.save('update', JSON.stringify(data), parentExtController.formSavedResponse);
                },
                render: function(panel) {
                    Instance.commonExtView.enableManagementTabHTMLEditor();
                }
            }
        }];
        
        if(Instance.typeView==="Parent"){
            itemsForm.push(getChildsExtViewTabs(childExtControllers));
        }
        
        return Ext.create('Ext.container.Container', {
            id: 'formContainer'+modelName,
            title: 'Formulario',
            type: 'fit',
            align: 'stretch',
            items: itemsForm
        });
    };
    
    function getChildsExtViewTabs(childExtControllers){
        var items=[];
        var jsonTypeChildExtViews= {"tableColumn":"tcv_standard"};
        childExtControllers.forEach(function(childExtController) {
            var itemTab= null;
            if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv_standard"){
                itemTab= {
                    xtype:'tabpanel',
                    title: childExtController.entityExtView.pluralEntityTitle,
                    plain:true,
                    activeTab: 0,
                    style: 'background-color:#dfe8f6; padding:10px;',
                    defaults: {bodyStyle: 'padding:15px', autoScroll:true},
                    items:[
                        childExtController.entityExtView.gridContainer,

                        childExtController.entityExtView.formContainer

                    ]
                };
            }else if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv-n-n-multicheck"){
                itemTab= childExtController.entityExtView.checkboxGroupContainer;
            }
            
            items.push(itemTab);
        });
        
        var tabObect= {
            xtype:'tabpanel',
            plain:true,
            activeTab: 0,
            style: 'padding:25px 15px 45px 15px;',
            items:items
        };
        
        return tabObect;
    };
    
    Instance.setFormActiveRecord= function(record){
        var formComponent= Instance.formContainer.child('#form'+Instance.modelName);
        formComponent.setActiveRecord(record || null);
    };
    
    Instance.defineWriterForm= function(modelName, fields, renderReplacements, additionalButtons){
        Ext.define('WriterForm'+modelName, {
            extend: 'Ext.form.Panel',
            alias: 'widget.writerform'+modelName,

            requires: ['Ext.form.field.Text'],

            initComponent: function(){
                this.addEvents('create');
                
                var buttons= [];
                
                buttons= [{
                    iconCls: 'icon-save',
                    itemId: 'save'+modelName,
                    text: 'Actualizar',
                    disabled: true,
                    scope: this,
                    handler: this.onSave
                }, {
                    //iconCls: 'icon-user-add',
                    text: 'Crear',
                    scope: this,
                    handler: this.onCreate
                }, {
                    //iconCls: 'icon-reset',
                    text: 'Limpiar',
                    scope: this,
                    handler: this.onReset
                },'|'];
                
                if(additionalButtons){
                    for(var i=0; i<additionalButtons.length; i++){
                        buttons.push(additionalButtons[i]);
                    }
                }
                Ext.apply(this, {
                    activeRecord: null,
                    //iconCls: 'icon-user',
                    frame: false,
                    defaultType: 'textfield',
                    bodyPadding: 15,
                    fieldDefaults: {
                        minWidth: 300,
                        anchor: '50%',
                        labelAlign: 'right'
                    },
                    items: fields,
                    dockedItems: [{
                        xtype: 'toolbar',
                        dock: 'bottom',
                        ui: 'footer',
                        items: buttons
                    }]
                });
                this.callParent();
            },

            setActiveRecord: function(record){
                this.activeRecord = record;
                if (this.activeRecord) {
                    if(this.down('#save'+modelName)!==null){
                        this.down('#save'+modelName).enable();
                    }
                    this.getForm().loadRecord(this.activeRecord);
                    this.renderReplaceActiveRecord(this.activeRecord);
                } else {
                    if(this.down('#save'+modelName)!==null){
                        this.down('#save'+modelName).disable();
                    }
                    this.getForm().reset();
                }
            },
                    
            getActiveRecord: function(){
                return this.activeRecord;
            },
            
            onSave: function(){
                var active = this.activeRecord,
                    form = this.getForm();
            
                if (!active) {
                    return;
                }
                if (form.isValid()) {
                    this.fireEvent('update', this, form.getValues());
                    //form.updateRecord(active);
                    //this.onReset();
                }
            },

            onCreate: function(){
                var form = this.getForm();

                if (form.isValid()) {
                    this.fireEvent('create', this, form.getValues());
                    //form.reset();
                }

            },

            onReset: function(){
                this.getForm().reset();
                parentExtController.loadFormData("");
            },
            
            renderReplaceActiveRecord: function(record){
                if(renderReplacements){
                    for(var i=0; i<renderReplacements.length; i++){
                        var renderReplace= renderReplacements[i];
                        var replaceField= renderReplace.replace.field;
                        var replaceAttribute= renderReplace.replace.attribute;
                        var value="ND";
                        
                        if (typeof record.data[replaceField] === "object" && Object.getOwnPropertyNames(record.data[replaceField]).length === 0){
                            value= "";
                        }else if(replaceAttribute.indexOf(".")===-1){
                            value= record.data[replaceField][replaceAttribute];
                        }else{
                            var niveles= replaceAttribute.split(".");
                            try{
                                switch(niveles.length){
                                    case 2:
                                        value= record.data[replaceField][niveles[0]][niveles[1]];
                                        break;
                                    case 3:
                                        value= record.data[replaceField][niveles[0]][niveles[1]][niveles[2]];
                                        break;
                                    case 4:
                                        value= record.data[replaceField][niveles[0]][niveles[1]][niveles[2]][niveles[3]];
                                        break;
                                    case 5:
                                        value= record.data[replaceField][niveles[0]][niveles[1]][niveles[2]][niveles[3]][niveles[4]];
                                        break;
                                }
                            }catch(err){
                                console.log(err);
                            }
                            
                        }
                        if(typeof(value) !== 'undefined'){
                            //value= util.htmlEntitiesDecode(value);
                            renderReplace.component.setValue(value);
                        }
                    }
                }
                return record;
            }
    
        });
        
    };
    
    
    
    
    function getGridContainer(modelName, store, formContainer){
        var idGrid= 'grid'+modelName;
        var gridColumns= [{"dataIndex":"id","width":100,"header":"Id","sortable":true},{"renderer":nameEntityRender,"field":{"type":"textfield"},"dataIndex":"name","width":200,"header":"Nombre","sortable":true},{"field":{"type":"textfield"},"dataIndex":"tableAlias","width":200,"header":"Alias","sortable":true},{"field":{"type":"textfield"},"dataIndex":"description","width":200,"header":"Descripci&oacute;n","sortable":true},{"editor":Instance.commonExtView.getSimpleCombobox('status','Estado','grid',['Active','Inactive']),"dataIndex":"status","width":200,"header":"Estado","sortable":true}];
        
        var getEmptyRec= function(){
            return new LeadTableModel({"name":"","description":"","tableAlias":"","status":""});
        };
        
        

        Instance.defineWriterGrid(modelName, 'Tablas Lead', gridColumns, getEmptyRec, Instance.typeView);
        
        return Ext.create('Ext.container.Container', {
            id: 'gridContainer'+modelName,
            title: 'Listado',
            
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [{
                itemId: idGrid,
                xtype: 'writergrid'+modelName,
                style: 'border: 0px',
                flex: 1,
                store: store,
                disableSelection: false,
                trackMouseOver: !false,
                listeners: {
                    selectionchange: function(selModel, selected) {
                        /*if(selected[0]){
                            parentExtController.loadFormData(selected[0].data.id)
                        }*/
                        if(formContainer!==null && selected[0]){
                            formContainer.child('#form'+modelName).setActiveRecord(selected[0]);
                        }
                    },
                    export: function(typeReport){
                        var data= "?filter="+JSON.stringify(parentExtController.filter);
                        data+="&limit="+store.pageSize+"&page="+store.currentPage;
                        data+="&sort="+store.sorters.items[0].property+"&dir="+store.sorters.items[0].direction;
                        
                        switch(typeReport){
                            case "json":
                                var urlFind= store.proxy.api.read;
                                window.open(urlFind+data,'_blank');
                                break;
                            case "xml":
                                var urlFind= store.proxy.api.read.replace("/find.htm","/find/xml.htm");
                                window.open(urlFind+data,'_blank');
                                break;
                            case "xls":
                                var urlFind= store.proxy.api.read.replace("/find.htm","/find/xls.htm");
                                window.open(urlFind+data,'_blank');
                                break;
                        }
                    }
                    
                }
            }],
            listeners: {
                activate: function(panel) {
                    //store.loadPage(1);
                }
            }
        });
    };
    
    Instance.setGridEmptyRec= function(obj){
        var gridComponent= Instance.gridContainer.child('#grid'+Instance.modelName);
        gridComponent.getEmptyRec= function(){
            return new LeadTableModel(obj);
        };
    };
    
    function getComboboxLimit(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('limit', 'L&iacute;mite', 'config', [50, 100, 200, 500]);
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.pageSize!==record.getValue()){
                store.pageSize=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 46;
        combobox.width= 125;
        combobox.setValue(50);
        
        return combobox;
    }
    
    function getComboboxOrderBy(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('sort', 'Ordenar por', 'config', ["id:Id","name:Nombre","tableAlias:Alias","description:Descripci&oacute;n","status:Estado"]);
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.sorters.items[0].property!==record.getValue()){
                store.sorters.items[0].property=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 80;
        combobox.width= 250;
        combobox.setValue("id");
        
        return combobox;
    }
    
    function getComboboxOrderDir(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('dir', 'Direcci&oacute;n', 'config', ["ASC", "DESC"]);
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.sorters.items[0].direction!==record.getValue()){
                store.sorters.items[0].direction=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 60;
        combobox.width= 150;
        combobox.setValue("DESC");
        
        return combobox;
    }
    
    Instance.defineWriterGrid= function(modelName, modelText, columns, getEmptyRec, typeView){
        Ext.define('WriterGrid'+modelName, {
            extend: 'Ext.grid.Panel',
            alias: 'widget.writergrid'+modelName,

            requires: [
                'Ext.grid.plugin.CellEditing',
                'Ext.form.field.Text',
                'Ext.toolbar.TextItem'
            ],

            initComponent: function(){

                this.editing = Ext.create('Ext.grid.plugin.CellEditing');
                

                Ext.apply(this, {
                    //iconCls: 'icon-grid',
                    hideHeaders:false,
                    frame: false,
                    plugins: [this.editing],
                    dockedItems: [{
                        weight: 2,
                        xtype: 'toolbar',
                        dock: 'bottom',
                        items: [{
                            xtype: 'tbtext',
                            text: '<b>@lacv</b>'
                        }, '|',
                        
                        {
                            //iconCls: 'icon-add',
                            text: 'Agregar',
                            scope: this,
                            hidden: (typeView==="Child"),
                            handler: this.onAddClick
                        },
                        
                        
                        {
                            //iconCls: 'icon-delete',
                            text: 'Eliminar',
                            
                            disabled: true,
                            
                            itemId: 'delete',
                            scope: this,
                            handler: this.onDeleteClick
                        },
                        
                        
                        
                        {
                            text: 'Exportar',
                            hidden: (typeView==="Child"),
                            //iconCls: 'add16',
                            menu: [
                                {text: 'A xls', handler: function(){this.exportTo('xls');}, scope: this},
                                {text: 'A json', handler: function(){this.exportTo('json');}, scope: this},
                                {text: 'A xml', handler: function(){this.exportTo('xml');}, scope: this}]
                        },
                        
                        
                        {
                            text: 'Auto-Guardar',
                            enableToggle: true,
                            hidden: (typeView==="Child"),
                            pressed: true,
                            tooltip: 'When enabled, Store will execute Ajax requests as soon as a Record becomes dirty.',
                            scope: this,
                            toggleHandler: function(btn, pressed){
                                this.store.autoSync = pressed;
                            }
                        }, {
                            iconCls: 'icon-save',
                            text: 'Guardar',
                            scope: this,
                            handler: this.onSync
                        },
                        
                        getComboboxLimit(this.store),
                        getComboboxOrderBy(this.store),
                        getComboboxOrderDir(this.store)
                        ]
                    }, {
                        weight: 1,
                        xtype: 'pagingtoolbar',
                        dock: 'bottom',
                        ui: 'footer',
                        store: this.store,
                        displayInfo: true,
                        displayMsg: modelText+' {0} - {1} de {2}',
                        emptyMsg: "No hay "+modelText
                    }],
                    columns: columns,
                    getEmptyRec: getEmptyRec
                });
                this.callParent();
                this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
            },

            onSelectChange: function(selModel, selections){
                if(this.down('#delete')!==null){
                    this.down('#delete').setDisabled(selections.length === 0);
                }
            },

            onSync: function(){
                this.store.sync();
            },

            onDeleteClick: function(){
                var selection = this.getView().getSelectionModel().getSelection()[0];
                if (selection) {
                    this.store.remove(selection);
                    parentExtController.loadFormData("");
                }else{
                    var check_items= document.getElementsByClassName("item_check");
                    var filter={"in":{"id":[]}};
                    for(var i=0; i<check_items.length; i++){
                        if(check_items[i].checked){
                            filter.in.id.push(check_items[i].value);
                        }
                    }
                    if(filter.in.id.length>0){
                        Instance.entityExtStore.deleteByFilter(JSON.stringify(filter), function(responseText){
                            console.log(responseText.data);
                            Instance.reloadPageStore(Instance.store.currentPage);
                        });
                    }
                }
            },

            onAddClick: function(){
                var rec = this.getEmptyRec(), edit = this.editing;
                edit.cancelEdit();
                this.store.insert(0, rec);
                edit.startEditByPosition({
                    row: 0,
                    column: 0
                });
            },
            
            exportTo: function(type){
                this.fireEvent('export', type);
            }

        });
    };
    

    
    
    function getPropertyGrid(){
        var renderers= {
            
        }
        var pg= Ext.create('Ext.grid.property.Grid', {
            id: 'propertyGridLeadTable',
            region: 'north',
            hideHeaders: true,
            resizable: false,
            defaults: {
                sortable: false
            },
            customRenderers: renderers,
            disableSelection:true,
            listeners: {
                'beforeedit':{
                    fn:function(){
                        return false;
                    }
                }
            }
        });
        pg.getStore().sorters.items= [];
        
        return pg;
    };
    
    
    
    Instance.showProcessForm= function(processName, sourceByDestinationFields, rowIndex){
        var initData={};
        if(rowIndex!==-1){
            var rec = Instance.gridContainer.child('#grid'+Instance.modelName).getStore().getAt(rowIndex);
            for (var source in sourceByDestinationFields) {
                var destination = sourceByDestinationFields[source];
                initData[destination]=rec.get(source);
            }
            
        }else{
            var formData= Instance.formContainer.child('#form'+Instance.modelName).getForm().getValues();
            for (var source in sourceByDestinationFields) {
                var destination = sourceByDestinationFields[source];
                initData[destination]=formData[source];
            }
            
        }
        Instance.processForms[processName].show(null, function() {});
        var processForm= Instance.processForms[processName].child('#form'+processName+'Process');
        processForm.getForm().reset();
        processForm.getForm().setValues(initData);
    };
    
    function nameEntityRender(value, p, record){
        if(record){
            if(Instance.typeView==="Parent"){
                return "<a style='font-size: 15px;' href='#?id="+record.data.id+"&tab=1'>"+value+"</a>";
            }else{
                return value;
            }
        }else{
            return value;
        }
    };
    
    Instance.hideParentField= function(entityRef){
        if(Instance.formContainer!==null){
            var fieldsForm= Instance.formContainer.child('#form'+Instance.modelName).items.items;
            fieldsForm.forEach(function(field) {
                if(field.name===entityRef){
                    field.hidden= true;
                }
            });
        }
        if(Instance.gridContainer!==null){
            var columnsGrid= Instance.gridContainer.child('#grid'+Instance.modelName).columns;
            columnsGrid.forEach(function(column) {
                if(column.dataIndex===entityRef){
                    column.hidden= true;
                }
            });
        }
    };
    
    Instance.createMainView= function(){
        
            
        
        
        Instance.childExtControllers= [];
        
        if(Instance.typeView==="Parent"){
        
            
            var tableColumnExtController= new TableColumnExtController(parentExtController, Instance);
            tableColumnExtController.entityExtView.hideParentField("leadTable");
            Instance.childExtControllers.push(tableColumnExtController);
        
        }
        
        Instance.formContainer= null;
        
        Instance.formContainer = getFormContainer(Instance.modelName, Instance.store, Instance.childExtControllers);
        Instance.store.formContainer= Instance.formContainer;
        
        
        
        Instance.gridContainer = getGridContainer(Instance.modelName, Instance.store, Instance.formContainer);
        Instance.store.gridContainer= Instance.gridContainer;
        
            
        
        
        Instance.processForms={};
        
        
        Instance.propertyGrid= getPropertyGrid();

        Instance.tabsContainer= Ext.widget('tabpanel', {
            region: 'center',
            activeTab: 0,
            style: 'background-color:#dfe8f6; margin:0px',
            defaults: {bodyStyle: 'padding:15px', autoScroll:true},
            items:[
                
                Instance.gridContainer,
                
                
                Instance.formContainer
                
            ],
            listeners: {
                tabchange: function(tabPanel, tab){
                    var idx = tabPanel.items.indexOf(tab);
                    var url= util.addUrlParameter(parentExtController.request,"tab", idx);
                    if(url!==""){
                        mvcExt.navigate(url);
                    }
                }
            }
        });
        
        Instance.mainView= {
            id: Instance.id,
            title: 'Gestionar Tablas Lead',
            frame: false,
            layout: 'border',
            items: [
                Instance.propertyGrid,
                Instance.tabsContainer
            ]
        };
        
    };
    
    Instance.getMainView= function(){
        return Instance.mainView;
    };

    Instance.init();
}
</script>
        
            
                
            

<script>

function TableColumnExtView(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/tableColumn";
    
    Instance.modelName="TableColumnModel";
    
    var util= new Util();
    
    // MODELS **********************************************
    
    Instance.entityExtModel= new TableColumnExtModel();
    
    // STORES **********************************************
    
    Instance.entityExtStore= new TableColumnExtStore();
    
    // COMPONENTS *******************************************
    
    Instance.commonExtView= new CommonExtView(parentExtController, Instance, 'TableColumn');
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.typeView= "Child";
        Instance.pluralEntityTitle= 'Columnas de la tabla';
        Instance.entityExtModel.defineModel(Instance.modelName);
        Instance.store= Instance.entityExtStore.getStore(Instance.modelName);
        
        Instance.createMainView();
    };
    
    Instance.setFilterStore= function(filter){
        
            Instance.store.getProxy().extraParams.filter= filter;
        
        
    };
    
    Instance.reloadPageStore= function(page){
        
            Instance.store.loadPage(page);
        
        
    };
    
    
    
    
    function getFormContainer(modelName, store, childExtControllers){
        var formFields= [{"xtype":"numberfield","fieldLabel":"Id","name":"id","readOnly":true},{"allowBlank":false,"fieldLabel":"Nombre","name":"name"},{"allowBlank":false,"fieldLabel":"Alias","name":"columnAlias"},Instance.commonExtView.getSimpleCombobox('dataType','Tipo Dato','form',['String','int','java.util.Date','java.sql.Time','boolean']),Instance.commonExtView.getSimpleCombobox('fieldType','Tipo Campo','form',['EMAIL','PASSWORD','TEXT_AREA','LIST','URL','HTML_EDITOR','FILE_UPLOAD','IMAGE_FILE_UPLOAD','VIDEO_YOUTUBE','VIDEO_FILE_UPLOAD','AUDIO_FILE_UPLOAD','GOOGLE_MAP','MULTI_FILE_TYPE']),{"xtype":"numberfield","fieldLabel":"Tama&ntilde;o","name":"columnSize"},{"xtype":"numberfield","fieldLabel":"Ancho","name":"width","value":45},{"xtype":"numberfield","fieldLabel":"Orden","name":"columnOrder","value":1},{"fieldLabel":"Valor por defecto","name":"defaultValue"},{"fieldLabel":"Opciones","name":"options"},Instance.formComboboxLeadTable];

        var renderReplacements= [{"component":Instance.formComboboxLeadTable,"replace":{"field":"leadTable","attribute":"id"}}];

        var additionalButtons= [];

        Instance.defineWriterForm(Instance.modelName, formFields, renderReplacements, additionalButtons, childExtControllers, Instance.typeView);
        
        var itemsForm= [{
            itemId: 'form'+modelName,
            xtype: 'writerform'+modelName,
            border: false,
            width: '100%',
            listeners: {
                create: function(form, data){
                    Instance.entityExtStore.save('create', JSON.stringify(data), parentExtController.formSavedResponse);
                },
                update: function(form, data){
                    Instance.entityExtStore.save('update', JSON.stringify(data), parentExtController.formSavedResponse);
                },
                render: function(panel) {
                    Instance.commonExtView.enableManagementTabHTMLEditor();
                }
            }
        }];
        
        if(Instance.typeView==="Parent"){
            itemsForm.push(getChildsExtViewTabs(childExtControllers));
        }
        
        return Ext.create('Ext.container.Container', {
            id: 'formContainer'+modelName,
            title: 'Formulario',
            type: 'fit',
            align: 'stretch',
            items: itemsForm
        });
    };
    
    function getChildsExtViewTabs(childExtControllers){
        var items=[];
        var jsonTypeChildExtViews= {};
        childExtControllers.forEach(function(childExtController) {
            var itemTab= null;
            if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv_standard"){
                itemTab= {
                    xtype:'tabpanel',
                    title: childExtController.entityExtView.pluralEntityTitle,
                    plain:true,
                    activeTab: 0,
                    style: 'background-color:#dfe8f6; padding:10px;',
                    defaults: {bodyStyle: 'padding:15px', autoScroll:true},
                    items:[
                        childExtController.entityExtView.gridContainer,

                        childExtController.entityExtView.formContainer

                    ]
                };
            }else if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv-n-n-multicheck"){
                itemTab= childExtController.entityExtView.checkboxGroupContainer;
            }
            
            items.push(itemTab);
        });
        
        var tabObect= {
            xtype:'tabpanel',
            plain:true,
            activeTab: 0,
            style: 'padding:25px 15px 45px 15px;',
            items:items
        };
        
        return tabObect;
    };
    
    Instance.setFormActiveRecord= function(record){
        var formComponent= Instance.formContainer.child('#form'+Instance.modelName);
        formComponent.setActiveRecord(record || null);
    };
    
    Instance.defineWriterForm= function(modelName, fields, renderReplacements, additionalButtons){
        Ext.define('WriterForm'+modelName, {
            extend: 'Ext.form.Panel',
            alias: 'widget.writerform'+modelName,

            requires: ['Ext.form.field.Text'],

            initComponent: function(){
                this.addEvents('create');
                
                var buttons= [];
                
                buttons= [{
                    iconCls: 'icon-save',
                    itemId: 'save'+modelName,
                    text: 'Actualizar',
                    disabled: true,
                    scope: this,
                    handler: this.onSave
                }, {
                    //iconCls: 'icon-user-add',
                    text: 'Crear',
                    scope: this,
                    handler: this.onCreate
                }, {
                    //iconCls: 'icon-reset',
                    text: 'Limpiar',
                    scope: this,
                    handler: this.onReset
                },'|'];
                
                if(additionalButtons){
                    for(var i=0; i<additionalButtons.length; i++){
                        buttons.push(additionalButtons[i]);
                    }
                }
                Ext.apply(this, {
                    activeRecord: null,
                    //iconCls: 'icon-user',
                    frame: false,
                    defaultType: 'textfield',
                    bodyPadding: 15,
                    fieldDefaults: {
                        minWidth: 300,
                        anchor: '50%',
                        labelAlign: 'right'
                    },
                    items: fields,
                    dockedItems: [{
                        xtype: 'toolbar',
                        dock: 'bottom',
                        ui: 'footer',
                        items: buttons
                    }]
                });
                this.callParent();
            },

            setActiveRecord: function(record){
                this.activeRecord = record;
                if (this.activeRecord) {
                    if(this.down('#save'+modelName)!==null){
                        this.down('#save'+modelName).enable();
                    }
                    this.getForm().loadRecord(this.activeRecord);
                    this.renderReplaceActiveRecord(this.activeRecord);
                } else {
                    if(this.down('#save'+modelName)!==null){
                        this.down('#save'+modelName).disable();
                    }
                    this.getForm().reset();
                }
            },
                    
            getActiveRecord: function(){
                return this.activeRecord;
            },
            
            onSave: function(){
                var active = this.activeRecord,
                    form = this.getForm();
            
                if (!active) {
                    return;
                }
                if (form.isValid()) {
                    this.fireEvent('update', this, form.getValues());
                    //form.updateRecord(active);
                    //this.onReset();
                }
            },

            onCreate: function(){
                var form = this.getForm();

                if (form.isValid()) {
                    this.fireEvent('create', this, form.getValues());
                    //form.reset();
                }

            },

            onReset: function(){
                this.getForm().reset();
                parentExtController.loadFormData("");
            },
            
            renderReplaceActiveRecord: function(record){
                if(renderReplacements){
                    for(var i=0; i<renderReplacements.length; i++){
                        var renderReplace= renderReplacements[i];
                        var replaceField= renderReplace.replace.field;
                        var replaceAttribute= renderReplace.replace.attribute;
                        var value="ND";
                        
                        if (typeof record.data[replaceField] === "object" && Object.getOwnPropertyNames(record.data[replaceField]).length === 0){
                            value= "";
                        }else if(replaceAttribute.indexOf(".")===-1){
                            value= record.data[replaceField][replaceAttribute];
                        }else{
                            var niveles= replaceAttribute.split(".");
                            try{
                                switch(niveles.length){
                                    case 2:
                                        value= record.data[replaceField][niveles[0]][niveles[1]];
                                        break;
                                    case 3:
                                        value= record.data[replaceField][niveles[0]][niveles[1]][niveles[2]];
                                        break;
                                    case 4:
                                        value= record.data[replaceField][niveles[0]][niveles[1]][niveles[2]][niveles[3]];
                                        break;
                                    case 5:
                                        value= record.data[replaceField][niveles[0]][niveles[1]][niveles[2]][niveles[3]][niveles[4]];
                                        break;
                                }
                            }catch(err){
                                console.log(err);
                            }
                            
                        }
                        if(typeof(value) !== 'undefined'){
                            //value= util.htmlEntitiesDecode(value);
                            renderReplace.component.setValue(value);
                        }
                    }
                }
                return record;
            }
    
        });
        
    };
    
    
    
    
    function getGridContainer(modelName, store, formContainer){
        var idGrid= 'grid'+modelName;
        var gridColumns= [{"dataIndex":"id","width":100,"header":"Id","sortable":true},{"renderer":nameEntityRender,"field":{"type":"textfield"},"dataIndex":"name","width":200,"header":"Nombre","sortable":true},{"field":{"type":"textfield"},"dataIndex":"columnAlias","width":200,"header":"Alias","sortable":true},{"editor":Instance.commonExtView.getSimpleCombobox('dataType','Tipo Dato','grid',['String','int','java.util.Date','java.sql.Time','boolean']),"dataIndex":"dataType","width":150,"header":"Tipo Dato","sortable":true},{"editor":Instance.commonExtView.getSimpleCombobox('fieldType','Tipo Campo','grid',['EMAIL','PASSWORD','TEXT_AREA','LIST','URL','HTML_EDITOR','FILE_UPLOAD','IMAGE_FILE_UPLOAD','VIDEO_YOUTUBE','VIDEO_FILE_UPLOAD','AUDIO_FILE_UPLOAD','GOOGLE_MAP','MULTI_FILE_TYPE']),"dataIndex":"fieldType","width":200,"header":"Tipo Campo","sortable":true},{"editor":{"xtype":"numberfield"},"dataIndex":"columnSize","width":100,"header":"Tama&ntilde;o","sortable":true},{"editor":{"xtype":"numberfield"},"dataIndex":"width","width":100,"header":"Ancho","sortable":true},{"editor":{"xtype":"numberfield"},"dataIndex":"columnOrder","width":100,"header":"Orden","sortable":true},{"field":{"type":"textfield"},"dataIndex":"defaultValue","width":200,"header":"Valor por defecto","sortable":true},{"field":{"type":"textfield"},"dataIndex":"options","width":200,"header":"Opciones","sortable":true},{"editor":Instance.gridComboboxLeadTable,"renderer":Instance.comboboxLeadTableRender,"dataIndex":"leadTable","width":200,"header":"Tabla Lead"}];
        
        var getEmptyRec= function(){
            return new TableColumnModel({"leadTable":"","columnOrder":1,"defaultValue":"","dataType":"","columnSize":"","name":"","width":45,"options":"","fieldType":"","columnAlias":""});
        };
        
        

        Instance.defineWriterGrid(modelName, 'Columnas de la tabla', gridColumns, getEmptyRec, Instance.typeView);
        
        return Ext.create('Ext.container.Container', {
            id: 'gridContainer'+modelName,
            title: 'Listado',
            
            layout: {
                type: 'vbox',
                align: 'stretch'
            },
            items: [{
                itemId: idGrid,
                xtype: 'writergrid'+modelName,
                style: 'border: 0px',
                flex: 1,
                store: store,
                disableSelection: false,
                trackMouseOver: !false,
                listeners: {
                    selectionchange: function(selModel, selected) {
                        /*if(selected[0]){
                            parentExtController.loadFormData(selected[0].data.id)
                        }*/
                        if(formContainer!==null && selected[0]){
                            formContainer.child('#form'+modelName).setActiveRecord(selected[0]);
                        }
                    },
                    export: function(typeReport){
                        var data= "?filter="+JSON.stringify(parentExtController.filter);
                        data+="&limit="+store.pageSize+"&page="+store.currentPage;
                        data+="&sort="+store.sorters.items[0].property+"&dir="+store.sorters.items[0].direction;
                        
                        switch(typeReport){
                            case "json":
                                var urlFind= store.proxy.api.read;
                                window.open(urlFind+data,'_blank');
                                break;
                            case "xml":
                                var urlFind= store.proxy.api.read.replace("/find.htm","/find/xml.htm");
                                window.open(urlFind+data,'_blank');
                                break;
                            case "xls":
                                var urlFind= store.proxy.api.read.replace("/find.htm","/find/xls.htm");
                                window.open(urlFind+data,'_blank');
                                break;
                        }
                    }
                    
                }
            }],
            listeners: {
                activate: function(panel) {
                    //store.loadPage(1);
                }
            }
        });
    };
    
    Instance.setGridEmptyRec= function(obj){
        var gridComponent= Instance.gridContainer.child('#grid'+Instance.modelName);
        gridComponent.getEmptyRec= function(){
            return new TableColumnModel(obj);
        };
    };
    
    function getComboboxLimit(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('limit', 'L&iacute;mite', 'config', [50, 100, 200, 500]);
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.pageSize!==record.getValue()){
                store.pageSize=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 46;
        combobox.width= 125;
        combobox.setValue(50);
        
        return combobox;
    }
    
    function getComboboxOrderBy(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('sort', 'Ordenar por', 'config', ["id:Id","name:Nombre","columnAlias:Alias","dataType:Tipo Dato","fieldType:Tipo Campo","columnSize:Tama&ntilde;o","width:Ancho","columnOrder:Orden","defaultValue:Valor por defecto","options:Opciones","leadTable:Tabla Lead"]);
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.sorters.items[0].property!==record.getValue()){
                store.sorters.items[0].property=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 80;
        combobox.width= 250;
        combobox.setValue("id");
        
        return combobox;
    }
    
    function getComboboxOrderDir(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('dir', 'Direcci&oacute;n', 'config', ["ASC", "DESC"]);
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.sorters.items[0].direction!==record.getValue()){
                store.sorters.items[0].direction=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 60;
        combobox.width= 150;
        combobox.setValue("DESC");
        
        return combobox;
    }
    
    Instance.defineWriterGrid= function(modelName, modelText, columns, getEmptyRec, typeView){
        Ext.define('WriterGrid'+modelName, {
            extend: 'Ext.grid.Panel',
            alias: 'widget.writergrid'+modelName,

            requires: [
                'Ext.grid.plugin.CellEditing',
                'Ext.form.field.Text',
                'Ext.toolbar.TextItem'
            ],

            initComponent: function(){

                this.editing = Ext.create('Ext.grid.plugin.CellEditing');
                

                Ext.apply(this, {
                    //iconCls: 'icon-grid',
                    hideHeaders:false,
                    frame: false,
                    plugins: [this.editing],
                    dockedItems: [{
                        weight: 2,
                        xtype: 'toolbar',
                        dock: 'bottom',
                        items: [{
                            xtype: 'tbtext',
                            text: '<b>@lacv</b>'
                        }, '|',
                        
                        {
                            //iconCls: 'icon-add',
                            text: 'Agregar',
                            scope: this,
                            hidden: (typeView==="Child"),
                            handler: this.onAddClick
                        },
                        
                        
                        {
                            //iconCls: 'icon-delete',
                            text: 'Eliminar',
                            
                            disabled: true,
                            
                            itemId: 'delete',
                            scope: this,
                            handler: this.onDeleteClick
                        },
                        
                        
                        
                        {
                            text: 'Exportar',
                            hidden: (typeView==="Child"),
                            //iconCls: 'add16',
                            menu: [
                                {text: 'A xls', handler: function(){this.exportTo('xls');}, scope: this},
                                {text: 'A json', handler: function(){this.exportTo('json');}, scope: this},
                                {text: 'A xml', handler: function(){this.exportTo('xml');}, scope: this}]
                        },
                        
                        
                        {
                            text: 'Auto-Guardar',
                            enableToggle: true,
                            hidden: (typeView==="Child"),
                            pressed: true,
                            tooltip: 'When enabled, Store will execute Ajax requests as soon as a Record becomes dirty.',
                            scope: this,
                            toggleHandler: function(btn, pressed){
                                this.store.autoSync = pressed;
                            }
                        }, {
                            iconCls: 'icon-save',
                            text: 'Guardar',
                            scope: this,
                            handler: this.onSync
                        },
                        
                        getComboboxLimit(this.store),
                        getComboboxOrderBy(this.store),
                        getComboboxOrderDir(this.store)
                        ]
                    }, {
                        weight: 1,
                        xtype: 'pagingtoolbar',
                        dock: 'bottom',
                        ui: 'footer',
                        store: this.store,
                        displayInfo: true,
                        displayMsg: modelText+' {0} - {1} de {2}',
                        emptyMsg: "No hay "+modelText
                    }],
                    columns: columns,
                    getEmptyRec: getEmptyRec
                });
                this.callParent();
                this.getSelectionModel().on('selectionchange', this.onSelectChange, this);
            },

            onSelectChange: function(selModel, selections){
                if(this.down('#delete')!==null){
                    this.down('#delete').setDisabled(selections.length === 0);
                }
            },

            onSync: function(){
                this.store.sync();
            },

            onDeleteClick: function(){
                var selection = this.getView().getSelectionModel().getSelection()[0];
                if (selection) {
                    this.store.remove(selection);
                    parentExtController.loadFormData("");
                }else{
                    var check_items= document.getElementsByClassName("item_check");
                    var filter={"in":{"id":[]}};
                    for(var i=0; i<check_items.length; i++){
                        if(check_items[i].checked){
                            filter.in.id.push(check_items[i].value);
                        }
                    }
                    if(filter.in.id.length>0){
                        Instance.entityExtStore.deleteByFilter(JSON.stringify(filter), function(responseText){
                            console.log(responseText.data);
                            Instance.reloadPageStore(Instance.store.currentPage);
                        });
                    }
                }
            },

            onAddClick: function(){
                var rec = this.getEmptyRec(), edit = this.editing;
                edit.cancelEdit();
                this.store.insert(0, rec);
                edit.startEditByPosition({
                    row: 0,
                    column: 0
                });
            },
            
            exportTo: function(type){
                this.fireEvent('export', type);
            }

        });
    };
    

    
    
    function getPropertyGrid(){
        var renderers= {
            
                
            LeadTable: function(entity){
                var res = entity.split("__");
                return '<a href="/vista/leadTable/table.htm#?tab=1&id='+res[0]+'">'+res[1]+'</a>';
            },
            
        }
        var pg= Ext.create('Ext.grid.property.Grid', {
            id: 'propertyGridTableColumn',
            region: 'north',
            hideHeaders: true,
            resizable: false,
            defaults: {
                sortable: false
            },
            customRenderers: renderers,
            disableSelection:true,
            listeners: {
                'beforeedit':{
                    fn:function(){
                        return false;
                    }
                }
            }
        });
        pg.getStore().sorters.items= [];
        
        return pg;
    };
    
    
    
    Instance.showProcessForm= function(processName, sourceByDestinationFields, rowIndex){
        var initData={};
        if(rowIndex!==-1){
            var rec = Instance.gridContainer.child('#grid'+Instance.modelName).getStore().getAt(rowIndex);
            for (var source in sourceByDestinationFields) {
                var destination = sourceByDestinationFields[source];
                initData[destination]=rec.get(source);
            }
            
        }else{
            var formData= Instance.formContainer.child('#form'+Instance.modelName).getForm().getValues();
            for (var source in sourceByDestinationFields) {
                var destination = sourceByDestinationFields[source];
                initData[destination]=formData[source];
            }
            
        }
        Instance.processForms[processName].show(null, function() {});
        var processForm= Instance.processForms[processName].child('#form'+processName+'Process');
        processForm.getForm().reset();
        processForm.getForm().setValues(initData);
    };
    
    function nameEntityRender(value, p, record){
        if(record){
            if(Instance.typeView==="Parent"){
                return "<a style='font-size: 15px;' href='#?id="+record.data.id+"&tab=1'>"+value+"</a>";
            }else{
                return value;
            }
        }else{
            return value;
        }
    };
    
    Instance.hideParentField= function(entityRef){
        if(Instance.formContainer!==null){
            var fieldsForm= Instance.formContainer.child('#form'+Instance.modelName).items.items;
            fieldsForm.forEach(function(field) {
                if(field.name===entityRef){
                    field.hidden= true;
                }
            });
        }
        if(Instance.gridContainer!==null){
            var columnsGrid= Instance.gridContainer.child('#grid'+Instance.modelName).columns;
            columnsGrid.forEach(function(column) {
                if(column.dataIndex===entityRef){
                    column.hidden= true;
                }
            });
        }
    };
    
    Instance.createMainView= function(){
        
            
            
        Instance.leadTableExtInterfaces= new LeadTableExtInterfaces(parentExtController, Instance);
        Instance.formComboboxLeadTable= Instance.leadTableExtInterfaces.getCombobox('form', 'TableColumn', 'leadTable', 'Tabla Lead');
        Instance.gridComboboxLeadTable= Instance.leadTableExtInterfaces.getCombobox('grid', 'TableColumn', 'leadTable', 'Tabla Lead');
        Instance.filterComboboxLeadTable= Instance.leadTableExtInterfaces.getCombobox('filter', 'TableColumn', 'leadTable', 'Tabla Lead');
        Instance.comboboxLeadTableRender= Instance.leadTableExtInterfaces.getComboboxRender('grid');
        
            
        
        
        Instance.childExtControllers= [];
        
        if(Instance.typeView==="Parent"){
        
        }
        
        Instance.formContainer= null;
        
        Instance.formContainer = getFormContainer(Instance.modelName, Instance.store, Instance.childExtControllers);
        Instance.store.formContainer= Instance.formContainer;
        
        
        
        Instance.gridContainer = getGridContainer(Instance.modelName, Instance.store, Instance.formContainer);
        Instance.store.gridContainer= Instance.gridContainer;
        
            
        
        
        Instance.processForms={};
        
        
        Instance.propertyGrid= getPropertyGrid();

        Instance.tabsContainer= Ext.widget('tabpanel', {
            region: 'center',
            activeTab: 0,
            style: 'background-color:#dfe8f6; margin:0px',
            defaults: {bodyStyle: 'padding:15px', autoScroll:true},
            items:[
                
                Instance.gridContainer,
                
                
                Instance.formContainer
                
            ],
            listeners: {
                tabchange: function(tabPanel, tab){
                    var idx = tabPanel.items.indexOf(tab);
                    var url= util.addUrlParameter(parentExtController.request,"tab", idx);
                    if(url!==""){
                        mvcExt.navigate(url);
                    }
                }
            }
        });
        
        Instance.mainView= {
            id: Instance.id,
            title: 'Gestionar Columnas de la tabla',
            frame: false,
            layout: 'border',
            items: [
                Instance.propertyGrid,
                Instance.tabsContainer
            ]
        };
        
    };
    
    Instance.getMainView= function(){
        return Instance.mainView;
    };

    Instance.init();
}
</script>
        
        
        <!-- ############################ IMPORT CONTROLLERS ################################### -->
        
        
            
        

<script>

function LeadTableExtController(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/leadTable";
    
    Instance.modelName="LeadTableModel";
    
    Instance.services= {};
    
    var util= new Util();
    
    // VIEWS *******************************************
    
    Instance.entityExtView= new LeadTableExtView(Instance, null);
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.entityRef= "leadTable";
        Instance.typeController= "Parent";
        mvcExt.mappingController(Instance.id, Instance);
        Instance.initFilter();
    };
    
    Instance.initFilter= function(){
        Instance.filter={};
        Instance.filter.eq={};
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
        
        
        
        if(activeTab==="1"){
            Instance.loadFormData(id);
        }
        
        if(activeTab==="" || activeTab==="0"){
            Instance.loadGridData();
        }
    };
    
    Instance.loadGridData= function(){
        Instance.entityExtView.setFilterStore(JSON.stringify(Instance.filter));
        Instance.entityExtView.reloadPageStore(1);
    };
    
    Instance.loadFormData= function(id){
        if(Instance.entityExtView.formContainer!==null){
            var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
            if(id!==""){
                Instance.idEntitySelected= id;
                var activeRecord= formComponent.getActiveRecord();

                if(activeRecord===null){
                    Instance.entityExtView.entityExtStore.load(id, function(data){
                        var record= Ext.create(Instance.modelName);
                        record.data= data;
                        formComponent.setActiveRecord(record || null);
                    });
                }
                Instance.loadChildExtControllers(Instance.idEntitySelected);
            }else{
                Instance.idEntitySelected= "";
                if(Object.keys(Instance.filter.eq).length !== 0){
                    var record= Ext.create(Instance.modelName);
                    for (var key in Instance.filter.eq) {
                        record.data[key]= Instance.filter.eq[key];
                    }
                    formComponent.setActiveRecord(record || null);
                }
            }
        }
    };
    
    Instance.loadNNMulticheckData= function(){
        Instance.entityExtView.clearNNMultichecks();
        Instance.entityExtView.findAndLoadNNMultichecks(JSON.stringify(Instance.filter));
    };
    
    Instance.loadChildExtControllers= function(idEntitySelected){
        if(Instance.typeController==="Parent"){
            var jsonTypeChildExtViews= {"tableColumn":"tcv_standard"};
            Instance.entityExtView.childExtControllers.forEach(function(childExtController) {
                childExtController.filter= {"eq":{"leadTable":idEntitySelected}};
                if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv_standard"){
                    childExtController.loadGridData();
                    childExtController.loadFormData("");
                }else if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv-n-n-multicheck"){
                    childExtController.loadNNMulticheckData();
                }
            });
        }
    };
    
    Instance.formSavedResponse= function(responseText){
        if(responseText.success){
            var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
            
            
            var record= Ext.create(Instance.modelName);
            record.data= responseText.data;
            formComponent.setActiveRecord(record || null);
            Ext.MessageBox.alert('Status', responseText.message);
            
            Instance.loadChildExtControllers(record.data.id);
            
        }else{
            Ext.MessageBox.alert('Status', responseText.message);
        }
    };
    
    Instance.doFilter= function(){
        var url= "?filter="+JSON.stringify(Instance.filter);
        console.log(url);
        mvcExt.navigate(url);
    };
    
    Instance.viewInternalPage= function(path){
        var urlAction= path;
        if(Instance.idEntitySelected!==""){
            urlAction+='#?filter={"eq":{"leadTable":'+Instance.idEntitySelected+'}}';
        }
        mvcExt.redirect(urlAction);
    };

    Instance.init();
}
</script>
        
            
                
            

<script>

function TableColumnExtController(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/tableColumn";
    
    Instance.modelName="TableColumnModel";
    
    Instance.services= {};
    
    var util= new Util();
    
    // VIEWS *******************************************
    
    Instance.entityExtView= new TableColumnExtView(Instance, null);
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.entityRef= "tableColumn";
        Instance.typeController= "Child";
        mvcExt.mappingController(Instance.id, Instance);
        Instance.initFilter();
    };
    
    Instance.initFilter= function(){
        Instance.filter={};
        Instance.filter.eq={};
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
        
        
            
        if(Instance.filter.eq.leadTable!==undefined && Instance.filter.eq.leadTable!==''){
            Instance.entityExtView.leadTableExtInterfaces.entityExtStore.load(Instance.filter.eq.leadTable, Instance.entityExtView.leadTableExtInterfaces.addLevel);
        }else{
            Instance.entityExtView.leadTableExtInterfaces.addLevel(null);
        }
        
        
        if(activeTab==="1"){
            Instance.loadFormData(id);
        }
        
        if(activeTab==="" || activeTab==="0"){
            Instance.loadGridData();
        }
    };
    
    Instance.loadGridData= function(){
        Instance.entityExtView.setFilterStore(JSON.stringify(Instance.filter));
        Instance.entityExtView.reloadPageStore(1);
    };
    
    Instance.loadFormData= function(id){
        if(Instance.entityExtView.formContainer!==null){
            var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
            if(id!==""){
                Instance.idEntitySelected= id;
                var activeRecord= formComponent.getActiveRecord();

                if(activeRecord===null){
                    Instance.entityExtView.entityExtStore.load(id, function(data){
                        var record= Ext.create(Instance.modelName);
                        record.data= data;
                        formComponent.setActiveRecord(record || null);
                    });
                }
                Instance.loadChildExtControllers(Instance.idEntitySelected);
            }else{
                Instance.idEntitySelected= "";
                if(Object.keys(Instance.filter.eq).length !== 0){
                    var record= Ext.create(Instance.modelName);
                    for (var key in Instance.filter.eq) {
                        record.data[key]= Instance.filter.eq[key];
                    }
                    formComponent.setActiveRecord(record || null);
                }
            }
        }
    };
    
    Instance.loadNNMulticheckData= function(){
        Instance.entityExtView.clearNNMultichecks();
        Instance.entityExtView.findAndLoadNNMultichecks(JSON.stringify(Instance.filter));
    };
    
    Instance.loadChildExtControllers= function(idEntitySelected){
        if(Instance.typeController==="Parent"){
            var jsonTypeChildExtViews= {};
            Instance.entityExtView.childExtControllers.forEach(function(childExtController) {
                childExtController.filter= {"eq":{"tableColumn":idEntitySelected}};
                if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv_standard"){
                    childExtController.loadGridData();
                    childExtController.loadFormData("");
                }else if(jsonTypeChildExtViews[childExtController.entityRef]==="tcv-n-n-multicheck"){
                    childExtController.loadNNMulticheckData();
                }
            });
        }
    };
    
    Instance.formSavedResponse= function(responseText){
        if(responseText.success){
            var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
            
            
            var record= Ext.create(Instance.modelName);
            record.data= responseText.data;
            formComponent.setActiveRecord(record || null);
            Ext.MessageBox.alert('Status', responseText.message);
            
            Instance.loadChildExtControllers(record.data.id);
            
        }else{
            Ext.MessageBox.alert('Status', responseText.message);
        }
    };
    
    Instance.doFilter= function(){
        var url= "?filter="+JSON.stringify(Instance.filter);
        console.log(url);
        mvcExt.navigate(url);
    };
    
    Instance.viewInternalPage= function(path){
        var urlAction= path;
        if(Instance.idEntitySelected!==""){
            urlAction+='#?filter={"eq":{"tableColumn":'+Instance.idEntitySelected+'}}';
        }
        mvcExt.redirect(urlAction);
    };

    Instance.init();
}
</script>
        
        
        <!-- ############################ IMPORT INTERFACES ################################### -->
        
        
        
        
            

<script>

function LeadTableExtInterfaces(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.modelName="LeadTableModel";
    
    // MODELS **********************************************
    
    Instance.entityExtModel= new LeadTableExtModel();
    
    // STORES **********************************************
    
    Instance.entityExtStore= new LeadTableExtStore();
    
    //*******************************************************
    
    var util= new Util();
    
    
    Instance.init= function(){
        Instance.pluralEntityTitle= 'Tablas Lead';
        Instance.entityExtModel.defineModel(Instance.modelName);
        Instance.store= Instance.entityExtStore.getStore(Instance.modelName);
        Instance.combobox={};
        Instance.comboboxRender={};
    };
    
    Instance.addLevel= function(entity){
        var source= parentExtView.propertyGrid.getSource();
        
        if(entity!==null && typeof(entity)!=='undefined'){
            source['LeadTable']= entity.id+"__"+entity.name;
        }else{
            delete source['LeadTable'];
        }
            
        parentExtView.propertyGrid.setSource(source);
    };
    
    Instance.getCombobox= function(component, entityDestination, fieldName, fieldTitle){
        Instance.store.pageSize= 1000;
        Instance.store.sorters.items[0].property='name';
        Instance.store.sorters.items[0].direction='ASC';
        Instance.combobox[component]= new Ext.form.ComboBox({
            id: component+'Combobox'+fieldName+'In'+entityDestination,
            name: fieldName,
            allowBlank: true,
            editable: true,
            store: Instance.store,
            displayField: 'name',
            valueField: 'id',
            queryMode: 'remote',
            optionAll: true,
            comboboxDependent: [],
            reloadData: false,
            listenerLoad: false,
            listeners: {
                change: function(record){
                    if(component==='filter'){
                        if(record.getValue()!==0){
                            parentExtController.filter.eq[fieldName]= record.getValue();
                        }else{
                            delete parentExtController.filter.eq[fieldName];
                        }
                    }
                    
                    this.comboboxDependent.forEach(function(combobox) {
                        var filter= {"eq":{"leadTable":record.getValue()}};
                        combobox.store.getProxy().extraParams.filter= JSON.stringify(filter);
                        combobox.reloadData= true;
                        if(!combobox.listenerLoad){
                            combobox.store.addListener('load',function(){
                                combobox.reloadData=false;
                            }, this);
                            combobox.listenerLoad= true;
                        }
                    });
                },
                el: {
                    click: function() {
                        if(this.combobox[component].reloadData){
                            this.combobox[component].store.loadPage(1);
                        }
                    },
                    scope: this
                }
            }/*,
            getDisplayValue: function() {
                var me = this,
                value = me.value,
                record = null;
                if(value) {
                    record = me.getStore().findRecord(me.valueField, value);
                }
                if(record) {
                    console.log(record.get(me.displayField));
                    return util.htmlEntitiesDecode(record.get(me.displayField));
                }
                return value;
            }*/
        });
        
        if(component!=='grid'){
            Instance.combobox[component].fieldLabel= fieldTitle;
        }
        
        if(component==='filter'){
            Instance.store.addListener('load', function(){
                var rec = { id: 0, name: '-' };
                Instance.store.insert(0,rec);
            });
        }
        
        return Instance.combobox[component];
    };
    
    Instance.getComboboxRender= function(component){
        Instance.comboboxRender[component]= function (value, p, record){
            var displayField= Instance.combobox[component].displayField;
            var valueField= Instance.combobox[component].valueField;

            if (typeof value === "object" && Object.getOwnPropertyNames(value).length === 0){
                return "";
            }else if(typeof(value[displayField]) !== 'undefined'){
                return value[displayField];
            }else{
                if(typeof(value[valueField]) !== 'undefined'){
                    value= value[valueField];
                }
                var record = Instance.combobox[component].findRecord(valueField, value);
                if(record){
                    return record.get(Instance.combobox[component].displayField);
                }else{
                    return value;
                }
            }
        };
        
        return Instance.comboboxRender[component];
    };
    
    Instance.getCheckboxGroup= function(entityDestination, fieldName, callback){
        
        Instance.checkboxGroup=  new Ext.form.CheckboxGroup({
            id: 'checkboxGroup'+fieldName+'In'+entityDestination,
            fieldLabel: 'Listado '+Instance.pluralEntityTitle,
            allowBlank: true,
            columns: 3,
            vertical: true,
            items: []
        });
        
        Instance.entityExtStore.find("", function(responseText){
            if(responseText.success){
                responseText.data.forEach(function(item){
                    var cb = Ext.create('Ext.form.field.Checkbox', {
                        id: 'checkNN'+fieldName+item.id,
                        boxLabel: item.name,
                        name: fieldName,
                        inputValue: item.id,
                        checked: false,
                        activeChange: true,
                        listeners: {
                            change: callback
                        }
                    });
                    Instance.checkboxGroup.add(cb);
                });
            }
        });
        
        return Instance.checkboxGroup;
    };

    Instance.init();
}
</script>
        
        
        <!-- ############################ IMPORT BASE ELEMENTES ################################### -->
        
        
<script>

function LeadTableViewportExtView(){
    
    Ext.context= "";
    
    var Instance= this;
    
    var util= new Util();
    
    Instance.entityExtController= new LeadTableExtController(null, Instance);
    
    
    Instance.init= function(){
        var views = [];
        
        views.push(Instance.entityExtController.entityExtView.getMainView());

        Instance.contentViews = {
             id: 'content-panel',
             region: 'center',
             layout: 'card',
             margins: '0 0 0 0',
             //margins: '5 5 5 5',
             activeItem: 0,
             border: false,
             items: views
        };
        
        Instance.border= {
            region: 'center',
            layout: 'border',
            bodyBorder: false,
            defaults: {
                split: true
            },
            items: [Instance.contentViews]
        };
        
        
        Instance.filters = Ext.create('Ext.form.Panel', {
            id: 'filters-panel',
            title: 'Filtros',
            region: 'west',
            plain: true,
            bodyBorder: false,
            bodyPadding: 10,
            margins: '0 0 0 0',
            //margins: '5 0 5 5',
            split: true,
            collapsible: true,
            collapsed: true,
            height: '100%',
            autoScroll: true,
            width: 400,
            minSize: 200,

            fieldDefaults: {
                labelWidth: "51%",
                anchor: '100%',
                labelAlign: 'right'
            },

            layout: {
                type: 'vbox',
                align: 'stretch'  // Child items are stretched to full width
            },

            items: [{"xtype":"numberfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       Instance.entityExtController.filter.eq.id= this.getValue();   }else{       delete Instance.entityExtController.filter.eq.id;   }}},"fieldLabel":"Id","name":"id"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       Instance.entityExtController.filter.lk.name= this.getValue();   }else{       delete Instance.entityExtController.filter.lk.name;   }}},"fieldLabel":"Nombre","name":"name"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       Instance.entityExtController.filter.lk.tableAlias= this.getValue();   }else{       delete Instance.entityExtController.filter.lk.tableAlias;   }}},"fieldLabel":"Alias","name":"tableAlias"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       Instance.entityExtController.filter.lk.description= this.getValue();   }else{       delete Instance.entityExtController.filter.lk.description;   }}},"fieldLabel":"Descripci&oacute;n","name":"description"},Instance.entityExtController.entityExtView.commonExtView.getSimpleCombobox('status','Estado','filter',['Active','Inactive'])],
            
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                ui: 'footer',
                items: ['->',{
                    text: 'Filtrar',
                    scope: this,
                    handler: function(){
                        Instance.entityExtController.doFilter();
                    }
                },{
                    text: 'Limpiar Filtros',
                    scope: this,
                    handler: function(){
                        Instance.filters.getForm().reset();
                        Instance.entityExtController.initFilter();
                        Instance.entityExtController.doFilter();
                    }
                }]
            }]
        });
        
        
        
        Instance.menuBar= Ext.create('Ext.toolbar.Toolbar', {
            region: 'north',
            items: [{"text":"Seguridad","menu":{"items":[{"text":"Gestionar Roles","href":"/vista/role/table.htm"},{"text":"Gestionar Usuarios","href":"/vista/user/table.htm"},{"text":"Gestionar Autorizaciones","href":"/vista/authorization/table.htm"},{"text":"Gestionar Roles de Usuario","href":"/vista/userRole/table.htm"},{"text":"Gestionar Recursos Web","href":"/vista/webResource/table.htm"}]}},{"text":"Configuraci&oacute;n","menu":{"items":[{"text":"Gestionar Propiedades","href":"/vista/property/table.htm"}]}},{"text":"Gestor de Contenidos","menu":{"items":[{"text":"Explorador de Archivos","href":"/vista/webFile/fileExplorer.htm"}]}},{"text":"Procesos","menu":{"items":[{"text":"Gestionar Servicios Externos","href":"/vista/externalService/process.htm"},{"text":"Gestionar Proceso Main Location","href":"/vista/processMainLocation/process.htm"},{"text":"Gestionar Procesos de Producto","href":"/vista/processProduct/process.htm"},{"text":"Gestionar Procesos de Usuario","href":"/vista/processUser/process.htm"}]}},{"text":"Correos","menu":{"items":[{"text":"Gestionar Plantillas de Correo","href":"/vista/mailTemplate/table.htm"},{"text":"Gestionar Correos","href":"/vista/mail/table.htm"}]}},{"text":"Comercios","menu":{"items":[{"text":"Gestionar Comercios","href":"/vista/commerce/table.htm"},{"text":"Gestionar Ubicaciones Principales","href":"/vista/mainLocation/table.htm"}]}},{"text":"Productos","menu":{"items":[{"text":"Gestionar Productos","href":"/vista/product/table.htm"},{"text":"Reporte de Productos","href":"/vista/product/report/reporteProductos.htm"},{"text":"Gestionar Categorias","href":"/vista/category/table.htm"}]}},{"text":"Pedidos","menu":{"items":[{"text":"Gestionar Ordenes de Inventario","href":"/vista/inventoryOrder/table.htm"},{"text":"Gestionar Proveedores","href":"/vista/supplier/table.htm"}]}},{"text":"Ordenes de Compra","menu":{"items":[{"text":"Gestionar Ordenes de Compra","href":"/vista/purchaseOrder/table.htm"}]}},{"text":"Pagos","menu":{"items":[{"text":"Gestionar Pagos","href":"/vista/payment/table.htm"}]}},{"text":"Tablas Lead","menu":{"items":[{"text":"Gestionar Tablas Lead","href":"/vista/leadTable/table.htm"}]}}]
        });
        
        
    };
    
    /*mvcExt.loadView= function(idView){
        console.log(idView);
        Ext.getCmp('content-panel').layout.setActiveItem(idView);
        var record = Instance.treePanel.getStore().getNodeById(idView);
        if(typeof(record)!=='undefined'){
            Instance.treePanel.getSelectionModel().select(record);
        }
    };*/
    
    Instance.renderViewport= function(){
        Ext.create('Ext.Viewport', {
            layout: 'border',
            title: 'Toures Balon',
            items: [
            
            {
                xtype: 'box',
                id: 'header',
                region: 'north',
                html: util.getHtml("headerHtml"),
                height: 30
            },
            
            
            Instance.menuBar,
            
            
            Instance.filters,
            
            Instance.border
            ],
            renderTo: Ext.getBody()
        });
    };
    
    Instance.init();
}
</script>
        
        <script>

function EntityExtInit(){
    
    var Instance= this;
    
    Instance.init= function(){
        Ext.Loader.setConfig({enabled: true});

        Ext.Loader.setPath('Ext.ux', ExtJSLib+'/examples/ux');
        
        Ext.Ajax.timeout = 60000;

        Ext.require([
            'Ext.tip.QuickTipManager',
            'Ext.container.ButtonGroup',
            'Ext.container.Viewport',
            'Ext.layout.*',
            'Ext.form.Panel',
            'Ext.form.Label',
            'Ext.grid.*',
            'Ext.data.*',
            'Ext.menu.*',
            'Ext.tree.*',
            'Ext.selection.*',
            'Ext.tab.Panel',
            'Ext.util.History',
            'Ext.ux.layout.Center',
            'Ext.ux.GroupTabPanel',
            'Ext.window.MessageBox'
        ]);
        
        //
        // This is the main layout definition.
        //
        Ext.onReady(function(){

            Ext.tip.QuickTipManager.init();

            Ext.History.init();

            var homeViewportExtView= new LeadTableViewportExtView();

            homeViewportExtView.renderViewport();

            //Debe ser siempre la ultima linea**************************
            mvcExt.setHomeRequest("/leadTable");
            mvcExt.processFirtsRequest();
        });
    };
    
    Instance.init();
}
</script>
        
        <!-- ############################ IMPORT COMPONENTS ################################### -->
        
        <script>

function CommonExtView(parentExtController, parentExtView, model){
    
    var Instance= this;
    
    var util= new Util();
    
    
    Instance.init= function(){
        if(model!==null){
            Instance.modelNameCombobox= "ComboboxModelIn"+model;
            Instance.combobox={};
            Ext.define(Instance.modelNameCombobox, {
                extend: 'Ext.data.Model',
                fields: [
                    'value',
                    'text'
                ]
            });
        }
    };
    
    Instance.getSimpleCombobox= function(fieldName, fieldTitle, component, dataArray){
        var data=[];
        data.push({value:"",text:"-"});
        dataArray.forEach(function(item) {
            if((item+"").indexOf(':')!==-1){
                var itemValue= item.split(':');
                data.push({value:itemValue[0],text:itemValue[1]});
            }else{
                data.push({value:item,text:item});
            }
        });
        var store = Ext.create('Ext.data.Store', {
            autoDestroy: false,
            model: Instance.modelNameCombobox,
            data: data
        });
        Instance.combobox[component+'_'+fieldName]= new Ext.form.ComboBox({
            id: component+'Combobox'+fieldName+'In'+model,
            name: fieldName,
            editable: false,
            store: store,
            displayField: 'text',
            valueField: 'value',
            queryMode: 'local',
            listeners: {
                change: function(record){
                    if(component==='filter'){
                        if(record.getValue()!==0){
                            parentExtController.filter.eq[fieldName]= record.getValue();
                        }else{
                            delete parentExtController.filter.eq[fieldName];
                        }
                    }
                }
            }
        });
        if(component!=='grid'){
            Instance.combobox[component+'_'+fieldName].fieldLabel=fieldTitle;
        }
        
        return Instance.combobox[component+'_'+fieldName];
    };
    
    Instance.enableManagementTabHTMLEditor= function(){
        var htmlEditors = document.getElementsByClassName('x-html-editor-input');
        if(htmlEditors!==null){
            for(var i=0; i<htmlEditors.length; i++){
                var divHtmlEditor= htmlEditors[i];
                var textareaEditors = divHtmlEditor.getElementsByTagName('textarea');
                if(textareaEditors!==null){
                    textareaEditors[0].onkeydown= function(e){
                        if(e.keyCode===9 || e.which===9){
                            e.preventDefault();
                            var s = this.selectionStart;
                            this.value = this.value.substring(0,this.selectionStart) + "\t" + this.value.substring(this.selectionEnd);
                            this.selectionEnd = s+1; 
                        }
                    };
                }
            }
        }
    };
    
    Instance.defineMultiFilefield= function(){
        Ext.define('Ext.ux.form.MultiFile', {
            extend: 'Ext.form.field.File',
            alias: 'widget.multifilefield',

            initComponent: function () {
                var me = this;

                me.on('render', function () {
                    me.fileInputEl.set({ multiple: true });
                });

                me.callParent(arguments);
            },

            onFileChange: function (button, e, value) {
                this.duringFileSelect = true;

                var me = this,
                    upload = me.fileInputEl.dom,
                    files = upload.files,
                    names = [];

                if (files) {
                    for (var i = 0; i < files.length; i++)
                        names.push(files[i].name);
                    value = names.join(', ');
                }

                Ext.form.field.File.superclass.setValue.call(this, value);

                delete this.duringFileSelect;
            }
        });
    };
    
    Instance.urlRender= function(value, p, record){
        if(value){
            return "<a target='_blank' href='"+value+"'>"+value+"</a>";
        }else{
            return value;
        }
    };
    
    Instance.imageGridRender= function(value, p, record){
        if(value){
            return '<img style="max-height: 200px;" src="'+value+'" />';
        }else{
            return value;
        }
    };
    
    Instance.fileRender= function(value, field){
        if(value){
            return "<a target='_blank' href='"+value+"'>"+value+"</a>";
        }else{
            return value;
        }
    };
    
    Instance.pdfRender= function(value, field){
        if(value){
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a>'+
                   '<iframe src="'+value+'" frameborder="0" width="100%" height="100%"></iframe>';
        }else{
            return value;
        }
    };
    
    Instance.textEditorRender= function(value, field){
        if(value){
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a>'+
                   '<iframe src="/vista/webFile/ajax/plainTextEditor.htm?fileUrl='+value+'" frameborder="0" width="100%" height="100%"></iframe>';
        }else{
            return value;
        }
    };
    
    Instance.imageRender= function(value, field) {
        if(value){
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a>'+
                   '<img style="max-width:150%" src="'+value+'" />';
        }else{
            return "";
        }
    };
    
    Instance.downloadRender= function(value, field) {
        var fileName= value.split('/').pop();
        if(value){
            return '<h2>'+fileName+'</h2>'+
                   '<a href="'+value+'" target="_blank">'+
                   '<img title="Descargar" style="max-width:150%" src="/img/icon_types/download.png" />'+
                   '</a>';
        }else{
            return "";
        }
    };
    
    Instance.videoYoutubeRender= function(value, field) {
        var videoId= util.getParameter(value, "v");
        if(videoId!==""){
            try{
                setTimeout(function(){
                    document.getElementsByName(field.name)[0].value= value;
                },1000);
            }catch(e){
                console.log(e);
            }
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a>'+
                   '<iframe width="528" height="287" src="https://www.youtube.com/embed/'+videoId+'" frameborder="0" allowfullscreen></iframe>';
        }else{
            return "";
        }
    };
    
    Instance.videoFileUploadRender= function(value, field) {
        if(value){
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a>'+
                   '<video style="width:528px;height:297px" controls>'+
                   '    <source src="'+value+'" type="video/'+value.split('.').pop()+'">'+
                   '    Your browser does not support the video tag.'+
                   '</video>';
        }else{
            return "";
        }
    };
    
    Instance.audioFileUploadRender= function(value, field) {
        if(value){
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a>'+
                   '<audio style="width:500px" src="'+value+'" preload="auto" controls>'+
                   '    Your browser does not support the video tag.'+
                   '</audio>';
        }else{
            return "";
        }
    };
    
    Instance.googleMapsRender= function(value, field) {
        try{
            setTimeout(function(){
                googleMaps.load(field.name, value);
            },1000);
        }catch(e){
            console.log(e);
        }
        return '<div class="googleMaps">'+
               '    <input id="'+field.name+'Address" type="text" size="50" placeholder="Bogot&aacute; Colombia" />'+
               '    <input type="button" value="Buscar" onclick="googleMaps.showAddress(\''+field.name+'\')" />'+
               '    <div id="'+field.name+'Map" style="width: 100%; height: 400px"></div>'+
               '</div>';
    };
    
    Instance.multiFileRender= function(value, field) {
        if(value){
            var extension= value.split('.').pop().toLowerCase();
            var htmlView= "";
            switch(extension){
                case "mp4":
                case "ogg":
                    htmlView= Instance.videoFileUploadRender(value, field);
                    break;
                case "mp3":
                    htmlView= Instance.audioFileUploadRender(value, field);
                    break;
                case "gif":
                case "jpg":
                case "jpeg":
                case "png":
                    htmlView= Instance.imageRender(value, field);
                    break;
                case "pdf":
                    htmlView= Instance.pdfRender(value, field);
                    break;
                case "html":
                case "vm":
                case "php":
                case "java":
                case "jsp":
                case "js":
                case "txt":
                case "properties":
                case "css":
                case "csv":
                case "xml":
                case "json":
                case "conf":
                    htmlView= Instance.textEditorRender(value, field);
                    break;
                default:
                    htmlView= Instance.downloadRender(value, field);
                    break;
            }
            try{
                setTimeout(function(){
                    htmlView= "<style>#linkFile{display:none}</style>"+
                              "<div style='margin:0px;text-align: center; height: 99%;'>"+
                                htmlView+
                              "</div>";
                    util.setHtml("webFileDetail-innerCt", htmlView);
                },10);
            }catch(e){
                console.log(e);
            }
            return Instance.fileRender(value, field);
        }else{
            return "";
        }
    };
    
    Instance.getLoadingContent= function(){
        var loadingDiv=
        '<div class="x-mask-msg x-layer x-mask-msg-default x-border-box" style="right: auto; z-index: 19001; top: 35%; left: 44%;">'+
        '    <div class="x-mask-msg-inner">'+
        '      <div class="x-mask-msg-text">Loading...</div>'+
        '    </div>'+
        '</div>';

        return loadingDiv;
    };
    
    
    Instance.init();
}
</script>
        
        <!-- ############################ IMPORT CONFIG ################################### -->
        
        <script>

function MVCExtController(){
    
    var Instance= this;
    
    var util= new Util;
    
    Instance.requestMapConfig={};
    
    Instance.init= function(){
        Instance.requestMapConfig={};
        Instance.lastRequest= "";
        Instance.request= "";
        
        Ext.History.on('change', function(request) {
            Instance.processRequest(request);
        });
    };
    
    Instance.mappingController= function(requestMapping, newController){
        if(!(requestMapping in Instance.requestMapConfig)){
            console.log("MapController: "+requestMapping);
            Instance.requestMapConfig[requestMapping]={controller:newController};
        }else{
            console.log("MVCExtController ERROR: requestMapping "+requestMapping+" duplicado!!!");
        }
    };
    
    Instance.processRequest= function(request){
        var path= util.getPath(request);
        var parameters= util.getParameters(request);
        var method= "index";
        if(path===""){
            path= Instance.homeRequest;
        }
        
        if(path.indexOf("|")!==-1){
            method=path.split("|")[1];
            path= path.split("|")[0];
            if(method===""){
                method="index";
            }
        }
        if(path in Instance.requestMapConfig){
            Instance.requestMapConfig[path].controller.request= util.removeUrlParameter(request,"tab");
            Instance.requestMapConfig[path].controller.services[method](parameters);
            Instance.loadView(path);
        }else{
            console.log("MVCExtController ERROR: Path "+path+" no encontrado!!!");
        }
    };
    
    Instance.setHomeRequest= function(homeRequest){
        Instance.homeRequest= homeRequest;
    };
    
    Instance.processFirtsRequest= function(){
        var url= document.URL;
        if(url.indexOf("#")!==-1){
            var request= url.substr(url.indexOf("#")+1, url.length);
            Instance.processRequest(request);
        }else{
            Instance.processRequest("");
        }
    };
    
    Instance.navigate= function(request){
        Instance.lastRequest= Instance.request;
        Instance.request= request;
        location.href= '#'+request;
    };
    
    Instance.redirect= function(url){
        location.href= ""+url;
    };
    
    Instance.loadView= function(idView){
        //Abstract method
    };
    
    Instance.init();
}

Ext.onReady(function(){
    mvcExt= new MVCExtController();
});
</script>
        
        
        <script src="/js/util/Util.js"></script>
        <script type="text/javascript" src=http://maps.google.com/maps?file=api&amp;v=3&amp;key=AIzaSyD_IP-Js3_ETbJ9psH605u-4iqZihp_-Jg&sensor=true"></script>
        <script src="/js/util/GoogleMaps.js"></script>
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/css/navegador.css">
        <link rel="stylesheet" type="text/css" href="/css/gridTemplateStyles.css">
        
    </head>
    <body>
        <div id="headerHtml" style="display:none;">
            <h1>Administraci&oacute;n MERCANDO</h1>
            <a class="logout" href="/security_logout">&nbsp;Cerrar sesi&oacute;n&nbsp;</a>
            <a class="home" href="/home?redirect=user">&nbsp;Inicio&nbsp;</a>
            
            
                <p class="userSession"><b>lcastrillo</b> - Luis Castrillo </p>
            
        </div>
        <script type="text/javascript">
            var navegadorExtInit= new EntityExtInit();
        </script>
    </body>
</html>
