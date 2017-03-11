


<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administracion MERCANDO</title>
        <link rel="icon" type="image/icon" href="/img/favicon.ico" /> 
        
        <script type="text/javascript">
            var ExtJSLib="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext";
        </script>
        
        <script src="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext/examples/shared/include-ext.js"></script>
        <!--<script src="https://ext-js-4-dot-proven-signal-88616.appspot.com/ext/examples/shared/options-toolbar.js"></script>-->
        
        <style>
            .x-html-editor-input textarea{white-space: pre !important;}
            .x-tree-icon-leaf {background-image: url("http://jsonviewer.stack.hu/blue.gif") !important; }
            .x-tree-icon-parent, .x-grid-tree-node-expanded  {background-image: url("http://jsonviewer.stack.hu/object.gif") !important;}
        </style>
        
        <!-- ############################ IMPORT LAYOUTS ################################ -->
        
        
        
        <!-- ############################ IMPORT MODELS ################################### -->
        
        
            
<script>

function ProcessMainLocationExtModel(){
    
    var Instance = this;
    
    
    Instance.defineactivarUsuarioModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"correo","type":"string"},{"dateFormat":"d/m/Y","name":"fechaRegistro","type":"date"},{"name":"nombre","type":"string"},{"name":"rol.estado","type":"string"},{"name":"rol.nombre","type":"string"},{"name":"telefono","type":"int"}],
            validations: [{"min":1,"field":"correo","type":"length"},{"min":1,"field":"nombre","type":"length"},{"min":1,"field":"rol.nombre","type":"length"}]
        });
    };
    
    Instance.definecrearMainLocationModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"id","type":"int"},{"name":"mlDescription","type":"string"},{"name":"mlLocation","type":"string"},{"name":"mlName","type":"string"},{"name":"producto.activo","type":"bool"},{"name":"producto.cantidad","type":"int"},{"name":"producto.codigoDeBarra","type":"int"},{"name":"producto.nombre","type":"string"},{"name":"producto.precio","type":"int"},{"name":"usuario.correo","type":"string"},{"dateFormat":"d/m/Y","name":"usuario.fechaRegistro","type":"date"},{"name":"usuario.nombre","type":"string"},{"name":"usuario.rol.estado","type":"string"},{"name":"usuario.rol.nombre","type":"string"},{"name":"usuario.telefono","type":"int"},{"name":"uuid","type":"string"}],
            validations: [{"min":0,"field":"mlDescription","max":500,"type":"length"},{"min":0,"field":"mlLocation","max":200,"type":"length"},{"min":0,"field":"mlName","max":100,"type":"length"},{"min":1,"field":"producto.precio","type":"length"},{"min":1,"field":"producto.nombre","type":"length"},{"min":1,"field":"usuario.correo","type":"length"},{"min":1,"field":"usuario.nombre","type":"length"},{"min":1,"field":"usuario.rol.nombre","type":"length"}]
        });
    };
    

    Instance.defineLogProcessDtoModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"clientId","type":"string"},{"name":"dataIn","type":"string"},{"name":"dataOut","type":"string"},{"name":"duration","type":"int"},{"name":"id","type":"int"},{"name":"mainProcessRef","type":"string"},{"name":"message","type":"string"},{"name":"processName","type":"string"},{"name":"recordTime"},{"dateFormat":"d/m/Y","name":"registrationDate","type":"date"},{"name":"success","type":"bool"}],
            validations: []
        });
    };
    
}
</script>
        
        
        <!-- ############################ IMPORT STORES ################################### -->
        
        
            
<script>

function ProcessMainLocationExtStore(){
    
    var Instance = this;
    
    var util= new Util();
    
    
    Instance.getLogProcessDtoStore= function(modelName){
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
                    read: Ext.context+'/rest/logProcess/find.htm',
                    create: Ext.context+'/rest/logProcess/create.htm',
                    update: Ext.context+'/rest/logProcess/update.htm',
                    destroy: Ext.context+'/rest/logProcess/delete.htm'
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
                property: 'id',
                direction: 'DESC'
            }],
            formContainer:null,
            gridContainer:null
        });
        
        return store;
    };

    Instance.findProcessMainLocation= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/logProcess/find.htm",
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
    
    Instance.saveProcessMainLocation= function(operation, processName, data, func){
        Ext.MessageBox.show({
            msg: 'Ejecutando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/processMainLocation/"+operation+"/"+processName+".htm",
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
    
    Instance.cargarLogProcessDto= function(idLogProcessDto, func){
        Ext.MessageBox.show({
            msg: 'Cargando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/logProcess/load.htm",
            method: "GET",
            params: 'data='+encodeURIComponent('{"id":'+idLogProcessDto+'}'),
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
    
    Instance.uploadProcessMainLocation= function(form, idEntity, func){
        form.submit({
            url: Ext.context+"/rest/processMainLocation/upload/"+idEntity+".htm",
            waitMsg: 'Subiendo archivo...',
            success: function(form, action) {
                func(action.result);
            }
        });
    };
    
    Instance.deleteByFilterProcessMainLocation= function(filter, func){
        Ext.Ajax.request({
            url: Ext.context+"/rest/processMainLocation/delete/byfilter.htm",
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
        
        
        <!-- ############################ IMPORT VIEWS ################################### -->
        
        
             
        

<script>

function ProcessMainLocationExtView(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/processMainLocation";
    
    Instance.modelName="LogProcessDtoModel";
    
    var util= new Util();
    
    // MODELS **********************************************
    
    Instance.entityExtModel= new ProcessMainLocationExtModel();
    
    // STORES **********************************************
    
    Instance.entityExtStore= new ProcessMainLocationExtStore();
    
    // COMPONENTS *******************************************
    
    Instance.commonExtView= new CommonExtView(parentExtController, Instance, 'ProcessMainLocation');
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.typeView= "Parent";
        
        Instance.entityExtModel.defineactivarUsuarioModel("activarUsuarioModel");
        
        Instance.entityExtModel.definecrearMainLocationModel("crearMainLocationModel");
        
        
        Instance.entityExtModel.defineLogProcessDtoModel(Instance.modelName);
        Instance.store= Instance.entityExtStore.getLogProcessDtoStore(Instance.modelName);
        
        Instance.createMainView();
    };
    
    Instance.setFilterStore= function(filter){
        
            Instance.store.getProxy().extraParams.filter= filter;
        
        
    };
    
    Instance.reloadPageStore= function(page){
        
            Instance.store.loadPage(page);
        
        
    };
    
    
        
    function getTreeMenuProcesses(){
        var store1 = {
            model: 'Item',
            root: {
                text: 'Root 1',
                expanded: true,
                children: [
                    
                    {
                        id: 'formContaineractivarUsuarioModel',
                        text: 'Activar Usuario',
                        leaf: true
                    },
                    
                    {
                        id: 'formContainercrearMainLocationModel',
                        text: 'Crear Main Location',
                        leaf: true
                    },
                    
                ]
            }
        };
        
        var treePanelProcess = Ext.create('Ext.tree.Panel', {
            id: 'tree-panel-process',
            title: 'Lista Procesos',
            region:'west',
            collapsible: true,
            split: true,
            width: 300,
            minSize: 200,
            height: '100%',
            rootVisible: false,
            autoScroll: true,
            store: store1
        });
        
        treePanelProcess.getSelectionModel().on('select', function(selModel, record) {
            if (record.get('leaf')) {
                Ext.getCmp('content-processes').layout.setActiveItem(record.getId());
            }
        });
        
        return treePanelProcess;
    }
    
    
    function getFormContaineractivarUsuario(modelName, store, childExtControllers){
        var formFields= [
                {
                  "allowBlank": false,
                  "fieldLabel": "Nombre",
                  "name": "nombre"
                },
                {
                  "allowBlank": false,
                  "vtype": "email",
                  "fieldLabel": "Correo",
                  "name": "correo"
                },
                {
                  "xtype": "numberfield",
                  "fieldLabel": "Telefono",
                  "name": "telefono"
                },
                {
                  "xtype": "datefield",
                  "fieldLabel": "Fecha Registro",
                  "name": "fechaRegistro",
                  "format": "d/m/Y",
                  "tooltip": "Seleccione la fecha"
                },
                {
                  "layout": "anchor",
                  "xtype": "fieldset",
                  "anchor": "50%",
                  "fieldDefaults": {
                    "labelAlign": "right",
                    "anchor": "100%"
                  },
                  "minWidth": 300,
                  "title": "Rol",
                  "collapsible": true,
                  "items": [
                    {
                      "allowBlank": false,
                      "fieldLabel": "Nombre",
                      "name": "rol.nombre"
                    },
                    Instance.commonExtView.getSimpleCombobox('rol.estado',
                    'Estado',
                    'form',
                    [
                      'Active',
                      'Inactive',
                      'Locked',
                      'Deleted'
                    ]),
                    {
                        "id": "rol.autorizaciones",
                        "itemTop": 0,
                      "layout": "anchor",
                      "xtype": "fieldset",
                      "fieldDefaults": {
                        "labelAlign": "right",
                        "anchor": "100%"
                      },
                      "title": "Autorizaciones",
                      "collapsible": true,
                      "items": [
                        {
                          "id": "rol.autorizaciones[0]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%"
                          },
                          "title": "Item 0",
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[0].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[1]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true,
                          },
                          "title": "Item 1",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[1].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[2]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true,
                          },
                          "title": "Item 2",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[2].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[3]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 3",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[3].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[4]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 4",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[4].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[5]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 5",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[5].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[6]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 6",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[6].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[7]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 7",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[7].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[8]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 8",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[8].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        {
                            "id": "rol.autorizaciones[9]",
                          "layout": "anchor",
                          "xtype": "fieldset",
                          "fieldDefaults": {
                            "labelAlign": "right",
                            "anchor": "100%",
                            "disabled": true
                          },
                          "title": "Item 9",
                          "hidden": true,
                          "collapsible": true,
                          "items": [
                            {
                              "fieldLabel": "Nombre",
                              "name": "rol.autorizaciones[9].nombre"
                            }
                          ],
                          "defaultType": "textfield"
                        },
                        
                        
                        {
                            xtype: 'button',
                            text: 'Agregar',
                            style: 'margin:5px',
                            width: 100,
                            handler: function(){
                                var itemsGroup= Ext.getCmp("rol.autorizaciones");
                                if(itemsGroup.itemTop<9){
                                    itemsGroup.itemTop+= 1;
                                    var itemEntity= Ext.getCmp("rol.autorizaciones["+itemsGroup.itemTop+"]");
                                    itemEntity.setVisible(true);
                                    itemEntity.query('.field').forEach(function(c){
                                        c.setDisabled(false);
                                    });
                                }
                            }
                        },
                        {
                            xtype: 'button',
                            text: 'Quitar',
                            style: 'margin:5px',
                            width: 100,
                            handler: function(){
                                var itemsGroup= Ext.getCmp("rol.autorizaciones");
                                if(itemsGroup.itemTop>=0){
                                    var itemEntity= Ext.getCmp("rol.autorizaciones["+itemsGroup.itemTop+"]");
                                    itemEntity.query('.field').forEach(function(c){
                                        c.setDisabled(true);
                                    });
                                    itemEntity.setVisible(false);
                                    itemsGroup.itemTop-= 1;
                                }
                            }
                        }
                        
                      ],
                      "defaultType": "textfield"
                    }
                  ],
                  "defaultType": "textfield"
                }
              ];

        var renderReplacements= [];

        var additionalButtons= [];

        Instance.defineWriterForm("activarUsuarioModel", formFields, renderReplacements, additionalButtons, childExtControllers, Instance.typeView);
        
        var itemsForm= [{
            itemId: 'formactivarUsuarioModel',
            xtype: 'writerformactivarUsuarioModel',
            title: 'Activar Usuario',
            border: false,
            width: '100%',
            listeners: {
                doProcess: function(form, data){
                    Instance.entityExtStore.saveProcessMainLocation('doProcess', 'activarUsuario',JSON.stringify(data), parentExtController.formSavedResponse);
                },
                render: function(panel) {
                    Instance.commonExtView.enableManagementTabHTMLEditor();
                }
            }
        }];
        
        itemsForm.push(getResultTree("activarUsuario"));
        
        return Ext.create('Ext.container.Container', {
            id: 'formContaineractivarUsuarioModel',
            type: 'fit',
            align: 'stretch',
            items: itemsForm
        });
    };
    
    function getFormContainercrearMainLocation(modelName, store, childExtControllers){
        var formFields= [{"xtype":"numberfield","fieldLabel":"Id","name":"id"},{"fieldLabel":"Ml Name","name":"mlName"},{"xtype":"textarea","fieldLabel":"Ml Description","name":"mlDescription","height":200},{"fieldLabel":"Ml Location","name":"mlLocation"},{"layout":"anchor","xtype":"fieldset","anchor":"50%","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"minWidth":300,"title":"Usuario","collapsible":true,"items":[{"allowBlank":false,"fieldLabel":"Nombre","name":"usuario.nombre"},{"allowBlank":false,"vtype":"email","fieldLabel":"Correo","name":"usuario.correo"},{"xtype":"numberfield","fieldLabel":"Telefono","name":"usuario.telefono"},{"xtype":"datefield","fieldLabel":"Fecha Registro","name":"usuario.fechaRegistro","format":"d/m/Y","tooltip":"Seleccione la fecha"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Rol","collapsible":true,"items":[{"allowBlank":false,"fieldLabel":"Nombre","name":"usuario.rol.nombre"},Instance.commonExtView.getSimpleCombobox('usuario.rol.estado','Estado','form',['Active','Inactive','Locked','Deleted']),{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Autorizaciones","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 0","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[0].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 1","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[1].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 2","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[2].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 3","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[3].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 4","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[4].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 5","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[5].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 6","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[6].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 7","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[7].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 8","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[8].nombre"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 9","collapsible":true,"items":[{"fieldLabel":"Nombre","name":"usuario.rol.autorizaciones[9].nombre"}],"defaultType":"textfield"}],"defaultType":"textfield"}],"defaultType":"textfield"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","anchor":"50%","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"minWidth":300,"title":"Producto","collapsible":true,"items":[{"allowBlank":false,"fieldLabel":"Nombre","name":"producto.nombre"},{"allowBlank":false,"xtype":"numberfield","fieldLabel":"Precio","name":"producto.precio"},{"xtype":"numberfield","fieldLabel":"Cantidad","name":"producto.cantidad"},{"xtype":"numberfield","fieldLabel":"C&oacute;digo de Barras","name":"producto.codigoDeBarra"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Activo","name":"producto.activo","inputValue":"true"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Tags","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 0","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[0].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 1","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[1].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 2","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[2].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 3","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[3].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 4","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[4].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 5","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[5].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 6","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[6].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 7","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[7].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 8","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[8].empty","inputValue":"true"}],"defaultType":"textfield"},{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Item 9","collapsible":true,"items":[{"layout":"anchor","xtype":"fieldset","fieldDefaults":{"labelAlign":"right","anchor":"100%"},"title":"Bytes","collapsible":true,"items":[],"defaultType":"textfield"},{"xtype":"checkbox","uncheckedValue":"false","fieldLabel":"Empty","name":"producto.tags[9].empty","inputValue":"true"}],"defaultType":"textfield"}],"defaultType":"textfield"}],"defaultType":"textfield"},{"fieldLabel":"Uuid","name":"uuid"}];

        var renderReplacements= [];

        var additionalButtons= [];

        Instance.defineWriterForm("crearMainLocationModel", formFields, renderReplacements, additionalButtons, childExtControllers, Instance.typeView);
        
        var itemsForm= [{
            itemId: 'formcrearMainLocationModel',
            xtype: 'writerformcrearMainLocationModel',
            title: 'Crear Main Location',
            border: false,
            width: '100%',
            listeners: {
                doProcess: function(form, data){
                    Instance.entityExtStore.saveProcessMainLocation('doProcess', 'crearMainLocation',JSON.stringify(data), parentExtController.formSavedResponse);
                },
                render: function(panel) {
                    Instance.commonExtView.enableManagementTabHTMLEditor();
                }
            }
        }];
        
        itemsForm.push(getResultTree("crearMainLocation"));
        
        return Ext.create('Ext.container.Container', {
            id: 'formContainercrearMainLocationModel',
            type: 'fit',
            align: 'stretch',
            items: itemsForm
        });
    };
    
    
    function getResultTree(processName){
        var store = {
            //id: 'store-result-'+processName,
            model: 'Item',
            root: {
                text: 'Root',
                expanded: true,
                children: []
            }
        };

        // Go ahead and create the TreePanel now so that we can use it below
         var treePanel = Ext.create('Ext.tree.Panel', {
            id: 'tree-result-'+processName,
            title: 'Resultado',
            //region:'north',
            split: true,
            width: '100%',
            autoHeight: true,
            minSize: 150,
            rootVisible: false,
            autoScroll: true,
            store: store
        });
        
        return treePanel;
    }
    
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
                    itemId: 'save'+modelName,
                    text: 'Ejecutar',
                    scope: this,
                    handler: this.onSave
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
                    this.getForm().setValues(this.activeRecord.data);
                } else {
                    this.getForm().reset();
                }
            },
                    
            getActiveRecord: function(){
                return this.activeRecord;
            },
            
            onSave: function(){
                var form = this.getForm();
                
                if (form.isValid()) {
                    this.fireEvent('doProcess', this, form.getValues());
                }
            },
            
            onReset: function(){
                this.setActiveRecord(null);
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
                            renderReplace.component.setValue(value);
                        }
                    }
                }
                return record;
            }
    
        });
        
    };
    
    
    
    
        
    function getFiltersPanel(){
        return Ext.create('Ext.form.Panel', {
            id: 'filters-panel',
            title: 'Filtros',
            region: 'west',
            plain: true,
            bodyBorder: false,
            bodyPadding: 10,
            margins: '0 0 0 0',
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

            items: [{"xtype":"numberfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.eq.id= this.getValue();   }else{       delete parentExtController.filter.eq.id;   }}},"fieldLabel":"Id","name":"id"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.lk.mainProcessRef= this.getValue();   }else{       delete parentExtController.filter.lk.mainProcessRef;   }}},"fieldLabel":"Main Process Ref","name":"mainProcessRef"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.lk.processName= this.getValue();   }else{       delete parentExtController.filter.lk.processName;   }}},"fieldLabel":"Process Name","name":"processName"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.lk.dataIn= this.getValue();   }else{       delete parentExtController.filter.lk.dataIn;   }}},"fieldLabel":"Data In","name":"dataIn"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.lk.dataOut= this.getValue();   }else{       delete parentExtController.filter.lk.dataOut;   }}},"fieldLabel":"Data Out","name":"dataOut"},{"layout":"column","xtype":"panel","bodyStyle":"padding-bottom: 5px;","items":[{"columnWidth":0.34,"html":"Registration Date:&nbsp;","style":"text-align: right"},{"xtype":"datefield","listeners":{"change":function(){   if( parentExtController.filter.btw.registrationDate === undefined){           parentExtController.filter.btw.registrationDate= [null,null];   }   if(this.getValue()!==null){       parentExtController.filter.btw.registrationDate[0]= Ext.Date.format(this.getValue(), 'd/m/Y');   }else{       parentExtController.filter.btw.registrationDate[0]= null;   }   if(parentExtController.filter.btw.registrationDate[0]===null && parentExtController.filter.btw.registrationDate[1]===null){       delete parentExtController.filter.btw.registrationDate;   }}},"name":"registrationDate_start","format":"d/m/Y","tooltip":"Seleccione la fecha","columnWidth":0.31},{"columnWidth":0.04,"html":"&nbsp;-&nbsp;"},{"xtype":"datefield","listeners":{"change":function(){   if( parentExtController.filter.btw.registrationDate === undefined){           parentExtController.filter.btw.registrationDate= [null,null];   }   if(this.getValue()!==null){       parentExtController.filter.btw.registrationDate[1]= Ext.Date.format(this.getValue(), 'd/m/Y');   }else{       parentExtController.filter.btw.registrationDate[1]= null;   }   if(parentExtController.filter.btw.registrationDate[0]===null && parentExtController.filter.btw.registrationDate[1]===null){       delete parentExtController.filter.btw.registrationDate;   }}},"name":"registrationDate_end","format":"d/m/Y","tooltip":"Seleccione la fecha","columnWidth":0.31}]},{"layout":"column","xtype":"panel","bodyStyle":"padding-bottom: 5px;","items":[{"columnWidth":0.34,"html":"Record Time:&nbsp;","style":"text-align: right"},{"xtype":"timefield","listeners":{"change":function(){   if( parentExtController.filter.btw.recordTime === undefined){           parentExtController.filter.btw.recordTime= [null,null];   }   if(this.getValue()!==null){       parentExtController.filter.btw.recordTime[0]= Ext.Date.format(this.getValue(), 'H:i:s');   }else{       parentExtController.filter.btw.recordTime[0]= null;   }   if(parentExtController.filter.btw.recordTime[0]===null && parentExtController.filter.btw.recordTime[1]===null){       delete parentExtController.filter.btw.recordTime;   }}},"name":"recordTime_start","tooltip":"Seleccione la hora","columnWidth":0.31},{"columnWidth":0.04,"html":"&nbsp;-&nbsp;"},{"xtype":"timefield","listeners":{"change":function(){   if( parentExtController.filter.btw.recordTime === undefined){           parentExtController.filter.btw.recordTime= [null,null];   }   if(this.getValue()!==null){       parentExtController.filter.btw.recordTime[1]= Ext.Date.format(this.getValue(), 'H:i:s');   }else{       parentExtController.filter.btw.recordTime[1]= null;   }   if(parentExtController.filter.btw.recordTime[0]===null && parentExtController.filter.btw.recordTime[1]===null){       delete parentExtController.filter.btw.recordTime;   }}},"name":"recordTime_end","tooltip":"Seleccione la hora","columnWidth":0.31}]},{"layout":"column","xtype":"panel","bodyStyle":"padding-bottom: 5px;","items":[{"columnWidth":0.34,"html":"Duration mls:&nbsp;","style":"text-align: right"},{"xtype":"numberfield","listeners":{"change":function(){   if( parentExtController.filter.btw.duration === undefined){           parentExtController.filter.btw.duration= [null,null];   }   if(this.getValue()!==null){       parentExtController.filter.btw.duration[0]= this.getValue();   }else{       parentExtController.filter.btw.duration[0]= null;   }   if(parentExtController.filter.btw.duration[0]===null && parentExtController.filter.btw.duration[1]===null){       delete parentExtController.filter.btw.duration;   }}},"name":"duration_start","columnWidth":0.31},{"columnWidth":0.04,"html":"&nbsp;-&nbsp;"},{"xtype":"numberfield","listeners":{"change":function(){   if( parentExtController.filter.btw.duration === undefined){           parentExtController.filter.btw.duration= [null,null];   }   if(this.getValue()!==null){       parentExtController.filter.btw.duration[1]= this.getValue();   }else{       parentExtController.filter.btw.duration[1]= null;   }   if(parentExtController.filter.btw.duration[0]===null && parentExtController.filter.btw.duration[1]===null){       delete parentExtController.filter.btw.duration;   }}},"name":"duration_end","columnWidth":0.31}]},{"xtype":"checkbox","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.eq.success= this.getValue();   }else{       delete parentExtController.filter.eq.success;   }}},"fieldLabel":"Success","name":"success"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.lk.message= this.getValue();   }else{       delete parentExtController.filter.lk.message;   }}},"fieldLabel":"Message","name":"message"},{"xtype":"textfield","listeners":{"change":function(){   if(this.getValue()!==null && this.getValue()!==''){       parentExtController.filter.lk.clientId= this.getValue();   }else{       delete parentExtController.filter.lk.clientId;   }}},"fieldLabel":"Client Id","name":"clientId"}],
            
            dockedItems: [{
                xtype: 'toolbar',
                dock: 'bottom',
                ui: 'footer',
                items: ['->',{
                    text: 'Filtrar',
                    scope: this,
                    handler: function(){
                        parentExtController.doFilter();
                    }
                },{
                    text: 'Limpiar Filtros',
                    scope: this,
                    handler: function(){
                        Instance.filters.getForm().reset();
                        parentExtController.initFilter();
                        parentExtController.doFilter();
                    }
                }]
            }]
        });
    }
        
    function getGridContainer(modelName, store, formContainer){
        var idGrid= 'grid'+modelName;
        var gridColumns= [{"editor":{"xtype":"numberfield"},"renderer":idEntityRender,"dataIndex":"id","width":100,"header":"Id","sortable":true},{"field":{"type":"textfield"},"dataIndex":"mainProcessRef","width":200,"header":"Main Process Ref","sortable":true},{"field":{"type":"textfield"},"dataIndex":"processName","width":200,"header":"Process Name","sortable":true},{"field":{"type":"textfield"},"dataIndex":"dataIn","width":200,"header":"Data In","sortable":true},{"field":{"type":"textfield"},"dataIndex":"dataOut","width":200,"header":"Data Out","sortable":true},{"editor":{"xtype":"datefield","format":"d/m/Y"},"xtype":"datecolumn","dataIndex":"registrationDate","width":200,"format":"d/m/Y","header":"Registration Date","sortable":true},{"editor":{"xtype":"timefield"},"dataIndex":"recordTime","width":200,"header":"Record Time","sortable":true},{"editor":{"xtype":"numberfield"},"dataIndex":"duration","width":200,"header":"Duration mls","sortable":true},{"editor":{"xtype":"checkbox","cls":"x-grid-checkheader-editor"},"dataIndex":"success","width":200,"header":"Success","sortable":true},{"field":{"type":"textfield"},"dataIndex":"message","width":200,"header":"Message","sortable":true},{"field":{"type":"textfield"},"dataIndex":"clientId","width":200,"header":"Client Id","sortable":true}];
        
        var getEmptyRec= function(){
            return new LogProcessDtoModel({"duration":"","dataOut":"","recordTime":"","clientId":"","processName":"","success":"","registrationDate":"","mainProcessRef":"","message":"","dataIn":""});
        };
        
        

        Instance.defineWriterGrid(modelName, 'LogProcessDto', gridColumns, getEmptyRec, Instance.typeView);
        
        return Ext.create('Ext.container.Container', {
            id: 'gridContainer'+modelName,
            region: 'center',
            margins: '0 0 0 0',
            
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
                        if(formContainer!==null){
                            formContainer.child('#form'+modelName).setActiveRecord(selected[0] || null);
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
            return new ProcessMainLocationModel(obj);
        };
    };
    
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
                
                var comboboxLimit= Instance.commonExtView.getSimpleCombobox('limit', 'L&iacute;mite', 'config', [50, 100, 200, 500]);
                comboboxLimit.addListener('change',function(record){
                    if(record.getValue()!=="" && this.store.pageSize!==record.getValue()){
                        this.store.pageSize=record.getValue();
                        Instance.reloadPageStore(1);
                    }
                }, this);
                comboboxLimit.labelWidth= 50;
                comboboxLimit.width= 130;
                comboboxLimit.setValue(50);

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
                        comboboxLimit
                        
                        ,{
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
                        }
                        
                        
                        
                        ,{
                            text: 'Exportar',
                            hidden: (typeView==="Child"),
                            //iconCls: 'add16',
                            menu: [
                                {text: 'A xls', handler: function(){this.exportTo('xls');}, scope: this},
                                {text: 'A json', handler: function(){this.exportTo('json');}, scope: this},
                                {text: 'A xml', handler: function(){this.exportTo('xml');}, scope: this}]
                        }
                        
                        
                        , {
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
                        }
                        
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
    

    
    
    function getTablaDePropiedades(renderers){
        var pg= Ext.create('Ext.grid.property.Grid', {
            id: 'propertyGridProcessMainLocation',
            region: 'north',
            hideHeaders: true,
            resizable: true,
            defaults: {
                sortable: false
            },
            customRenderers: renderers,
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
    
    function idEntityRender(value, p, record){
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
                
        Instance.formContainer= null;
        
        Instance.menuProcesses= getTreeMenuProcesses();
        
        
        
        Instance.gridContainer = getGridContainer(Instance.modelName, Instance.store, Instance.formContainer);
        Instance.store.gridContainer= Instance.gridContainer;
        Instance.filters= getFiltersPanel();
        
            
        
        
        Instance.propertyGrid= getTablaDePropiedades({
            
        });

        Instance.tabsContainer= Ext.widget('tabpanel', {
            region: 'center',
            activeTab: 0,
            style: 'background-color:#dfe8f6; margin:0px',
            defaults: {bodyStyle: 'padding:15px', autoScroll:true},
            items:[
                
                {
                    title: 'Solicitudes',
                    layout: 'border',
                    bodyStyle: 'padding:0px',
                    items: [
                        Instance.filters,
                        Instance.gridContainer
                    ]
                },
                
                
                {
                    title: 'Gestionar Procesos',
                    layout: 'border',
                    bodyStyle: 'padding:0px',
                    items:[
                        Instance.menuProcesses,
                        {
                            id: 'content-processes',
                            region: 'center',
                            layout: 'card',
                            margins: '0 0 0 0',
                            autoScroll: true,
                            activeItem: 0,
                            border: false,
                            items: [
                                
                                getFormContaineractivarUsuario("activarUsuarioModel", Instance.store, Instance.childExtControllers),
                                
                                getFormContainercrearMainLocation("crearMainLocationModel", Instance.store, Instance.childExtControllers),
                                
                            ]
                       }
                    ]
                }
                
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
            title: 'Gestionar Proceso Main Location',
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

function ProcessMainLocationExtController(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/processMainLocation";
    
    Instance.modelName="ProcessMainLocationModel";
    
    Instance.services= {};
    
    var util= new Util();
    
    // VIEWS *******************************************
    
    Instance.entityExtView= new ProcessMainLocationExtView(Instance, null);
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.entityRef= "processMainLocation";
        Instance.typeController= "Parent";
        mvcExt.mappingController(Instance.id, Instance);
        Instance.initFilter();
    };
    
    Instance.initFilter= function(){
        Instance.filter={};
        Instance.filter.eq={"mainProcessRef":"processMainLocation"};
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
        if(Instance.entityExtView.formContainer!==null){
            var formComponent= Instance.entityExtView.formContainer.child('#form'+Instance.modelName);
            formComponent.setActiveRecord(null);
        }
    };
    
    Instance.loadFormData= function(id){
        if(id!==""){
            Instance.entityExtView.entityExtStore.cargarLogProcessDto(id, function(data){
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
        
        
        var rootMenu= util.objectToJSONMenu(responseText);
        var treePanel = Ext.getCmp('tree-result-'+processName);
        treePanel.getStore().setRootNode(rootMenu);
        var textMenu= JSON.stringify(responseText);
        Ext.MessageBox.alert('Status', textMenu);
        
    };
    
    Instance.doFilter= function(){
        var url= "?filter="+JSON.stringify(Instance.filter);
        console.log(url);
        mvcExt.navigate(url);
    };
    
    Instance.viewInternalPage= function(path){
        var urlAction= path;
        if(Instance.idEntitySelected!==""){
            urlAction+='#?filter={"eq":{"processMainLocation":'+Instance.idEntitySelected+'}}';
        }
        mvcExt.redirect(urlAction);
    };

    Instance.init();
}
</script>
        
        
        <!-- ############################ IMPORT INTERFACES ################################### -->
        
        
        
        
        
        <!-- ############################ IMPORT BASE ELEMENTES ################################### -->
        
        
<script>

function ProcessMainLocationViewportExtView(){
    
    Ext.context= "";
    
    var Instance= this;
    
    var util= new Util();
    
    Instance.entityExtController= new ProcessMainLocationExtController(null, Instance);
    
    
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
        
        
        Instance.menuBar= Ext.create('Ext.toolbar.Toolbar', {
            region: 'north',
            items: [{"text":"Seguridad","menu":{"items":[{"text":"Gestionar Roles","href":"/vista/role/table.htm"},{"text":"Gestionar Usuarios","href":"/vista/user/table.htm"},{"text":"Gestionar Roles de Usuario","href":"/vista/userRole/table.htm"}]}},{"text":"Correos","menu":{"items":[{"text":"Gestionar Plantillas de Correo","href":"/vista/mailTemplate/table.htm"},{"text":"Gestionar Correos","href":"/vista/mail/table.htm"}]}},{"text":"Configuraci&oacute;n","menu":{"items":[{"text":"Gestionar Propiedades","href":"/vista/property/table.htm"}]}},{"text":"Gestor de Contenidos","menu":{"items":[{"text":"Explorador de Archivos","href":"/vista/webFile/table.htm"}]}},{"text":"Procesos","menu":{"items":[{"text":"Gestionar Procesos de Usuario","href":"/vista/processUser/table.htm"},{"text":"Gestionar Proceso Main Location","href":"/vista/processMainLocation/table.htm"}]}},{"text":"Productos","menu":{"items":[{"text":"Gestionar Categorias","href":"/vista/category/table.htm"},{"text":"Gestionar Productos","href":"/vista/product/table.htm"}]}},{"text":"Comercios","menu":{"items":[{"text":"Gestionar Comercios","href":"/vista/commerce/table.htm"},{"text":"Gestionar Ubicaciones Principales","href":"/vista/mainLocation/table.htm"}]}},{"text":"Pedidos","menu":{"items":[{"text":"Gestionar Proveedores","href":"/vista/supplier/table.htm"},{"text":"Gestionar Ordenes de Inventario","href":"/vista/inventoryOrder/table.htm"}]}},{"text":"Ordenes de Compra","menu":{"items":[{"text":"Gestionar Ordenes de Compra","href":"/vista/purchaseOrder/table.htm"}]}},{"text":"Pagos","menu":{"items":[{"text":"Gestionar Pagos","href":"/vista/payment/table.htm"}]}}]
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

            var homeViewportExtView= new ProcessMainLocationViewportExtView();

            homeViewportExtView.renderViewport();

            //Debe ser siempre la ultima linea**************************
            mvcExt.setHomeRequest("/processMainLocation");
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
        var fileName= value.split('/').pop().toLowerCase();
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
                   '<video width="528" height="297" controls>'+
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
                   '<audio width="500" src="'+value+'" preload="auto" controls>'+
                   '    Your browser does not support the video tag.'+
                   '</audio>';
        }else{
            return "";
        }
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
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/css/navegador.css">
        <link rel="stylesheet" type="text/css" href="/css/gridTemplateStyles.css">
        
    </head>
    <body>
        <div id="headerHtml" style="display:none;">
            <h1 >
                Administraci&oacute;n MERCANDO
                <a class="logout" href="/j_spring_security_logout">&nbsp;Cerrar sesi&oacute;n&nbsp;</a>
                <a class="home" href="/home">&nbsp;Home&nbsp;</a>
            </h1>
        </div>
        <script type="text/javascript">
            var navegadorExtInit= new EntityExtInit();
        </script>
    </body>
</html>
