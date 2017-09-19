<%-- 
    Document   : configobj
    Created on : 19/09/2017, 02:12:09 PM
    Author     : grupot
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestionar Configuraci&oacute;n General - Administraci&oacute;n MERCANDO</title>
        <link rel="icon" type="image/icon" href="/img/habitares.png" /> 
        
        



    <script type="text/javascript">
        var ExtJSVersion=4;
        var ExtJSLib="http://localhost:8080/ext-4.2.1";
    </script>
    <script src="http://localhost:8080/ext-4.2.1/examples/shared/include-ext.js"></script>


        
        <style>
            .x-html-editor-input textarea{white-space: pre !important;}
            .x-tree-icon-leaf {background-image: url("http://jsonviewer.stack.hu/blue.gif") !important; }
            .x-tree-icon-parent, .x-tree-icon-parent-expanded {background-image: url("http://jsonviewer.stack.hu/object.gif") !important;background-repeat:no-repeat;}
        </style>
        
        <!-- ############################ IMPORT LAYOUTS ################################ -->
        
        
        
        <!-- ############################ IMPORT MODELS ################################### -->
        
        
            
<script>

function GeneralConfigExtModel(){
    
    var Instance = this;
    
    
    Instance.defineportalConfigModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"language","type":"string"},{"name":"name","type":"string"}],
            validations: []
        });
    };
    
    Instance.definecontactConfigModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: [{"name":"cellPhone","type":"string"},{"name":"comments","type":"string"},{"name":"mail","type":"string"},{"name":"userName","type":"string"}],
            validations: [{"min":1,"field":"mail","type":"length"},{"min":1,"field":"userName","type":"length"}]
        });
    };
    
    
}
</script>
        
        
        <!-- ############################ IMPORT STORES ################################### -->
        
        
            
<script>

function GeneralConfigExtStore(){
    
    var Instance = this;
    
    var commonExtView= new CommonExtView();
    
    
    Instance.saveConfig= function(configurationObjectRef, data, func){
        Ext.MessageBox.show({
            msg: 'Guardando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/generalConfig/saveConfig.htm",
            method: "POST",
            headers: {
                'Content-Type' : 'application/json'
            },
            jsonData: {'configurationObjectRef': configurationObjectRef, 'data': util.remakeJSONObject(data)},
            success: function(response){
                Ext.MessageBox.hide();
                var result= Ext.decode(response.responseText);
                func(result);
            },
            failure: function(response){
                commonExtView.processFailure(response);
            }
        });
    };
    
    Instance.loadConfig= function(configurationObjectRef, func){
        Ext.MessageBox.show({
            msg: 'Cargando...',
            width:200,
            wait:true,
            waitConfig: {interval:200}
        });
        Ext.Ajax.request({
            url: Ext.context+"/rest/generalConfig/loadConfig/"+configurationObjectRef+".htm",
            method: "GET",
            success: function(response){
                var result= Ext.decode(response.responseText);
                func(result.data);
                Ext.MessageBox.hide();
            },
            failure: function(response){
                commonExtView.processFailure(response);
            }
        });
    };

}
</script>
        
        
        <!-- ############################ IMPORT VIEWS ################################### -->
        
        

<script>

function GeneralConfigExtView(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/generalConfig";
    
    var util= new Util();
    
    // MODELS **********************************************
    
    Instance.entityExtModel= new GeneralConfigExtModel();
    
    // STORES **********************************************
    
    Instance.entityExtStore= new GeneralConfigExtStore();
    
    // COMPONENTS *******************************************
    
    Instance.commonExtView= new CommonExtView(parentExtController, Instance, 'GeneralConfig');
    
    //*******************************************************
    
    
    Instance.init= function(){
        
        Instance.entityExtModel.defineportalConfigModel("portalConfigModel");
        
        Instance.entityExtModel.definecontactConfigModel("contactConfigModel");
        
        
        Instance.createMainView();
    };
    
    
        
    function getTreeMenuConfigurationObjects(){
        var store1 = {
            //model: 'Item',
            root: {
                text: 'Root 1',
                expanded: true,
                children: [
                    
                    {
                        id: 'form-portalConfig',
                        text: 'Configuraci&oacute;n del Portal',
                        leaf: true
                    },
                    
                    {
                        id: 'form-contactConfig',
                        text: 'Configuraci&oacute;n de Contacto',
                        leaf: true
                    },
                    
                ]
            }
        };
        
        var treePanelConfigurationObjects = Ext.create('Ext.tree.Panel', {
            id: 'tree-panel-process',
            title: 'Configuraci&oacute;n',
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
        
        treePanelConfigurationObjects.getSelectionModel().on('select', function(selModel, record) {
            if (record.get('leaf')) {
                Ext.getCmp('content-configurationObjects').layout.setActiveItem(record.getId());
            }
        });
        
        return treePanelConfigurationObjects;
    }
    
    
    function getFormContainerportalConfig(){
        var formFields= [
            {
              "fieldLabel": "Nombre",
              "name": "name"
            },
            {
              "fieldLabel": "Lenguaje",
              "name": "language"
            },
            {
              "itemTop": 0,
              "layout": "anchor",
              "xtype": "fieldset",
              "fieldDefaults": {
                "labelAlign": "right",
                "anchor": "100%"
              },
              "minWidth": 300,
              "id": "locations",
              "title": "Ubicaciones:",
              "collapsible": true,
              "items": [
                {
                  "fieldLabel": "Item 0",
                  "name": "locations[0]",
                  "id": "locations[0]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 1",
                  "name": "locations[1]",
                  "disabled": true,
                  "id": "locations[1]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 2",
                  "name": "locations[2]",
                  "disabled": true,
                  "id": "locations[2]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 3",
                  "name": "locations[3]",
                  "disabled": true,
                  "id": "locations[3]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 4",
                  "name": "locations[4]",
                  "disabled": true,
                  "id": "locations[4]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 5",
                  "name": "locations[5]",
                  "disabled": true,
                  "id": "locations[5]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 6",
                  "name": "locations[6]",
                  "disabled": true,
                  "id": "locations[6]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 7",
                  "name": "locations[7]",
                  "disabled": true,
                  "id": "locations[7]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 8",
                  "name": "locations[8]",
                  "disabled": true,
                  "id": "locations[8]"
                },
                {
                  "hidden": true,
                  "fieldLabel": "Item 9",
                  "name": "locations[9]",
                  "disabled": true,
                  "id": "locations[9]"
                },
                {
                  "handler": function(){
                    var itemsGroup=Ext.getCmp('locations');
                    if(itemsGroup.itemTop<9){
                      itemsGroup.itemTop+=1;
                      var itemEntity=Ext.getCmp('locations['+itemsGroup.itemTop+']');
                      itemEntity.setVisible(true);
                      itemEntity.setDisabled(false);
                      if(itemEntity.query){
                        itemEntity.query('.field').forEach(function(c){
                          c.setDisabled(false);
                        });
                      }
                    }
                  },
                  "xtype": "button",
                  "width": 100,
                  "style": "margin:5px",
                  "text": "Agregar"
                },
                {
                  "handler": function(){
                    var itemsGroup=Ext.getCmp('locations');
                    if(itemsGroup.itemTop>=0){
                      var itemEntity=Ext.getCmp('locations['+itemsGroup.itemTop+']');
                      itemsGroup.itemTop-=1;
                      itemEntity.setVisible(false);
                      itemEntity.setDisabled(true);
                      if(itemEntity.query){
                        itemEntity.query('.field').forEach(function(c){
                          c.setDisabled(true);
                        });
                      }
                    }
                  },
                  "xtype": "button",
                  "width": 100,
                  "style": "margin:5px",
                  "text": "Quitar"
                }
              ],
              "defaultType": "textfield"
            }
        ];

        Instance.defineWriterForm("portalConfigModel", formFields);
        
        return Ext.create('Ext.container.Container', {
            id: 'form-portalConfig',
            type: 'fit',
            align: 'stretch',
            items: [{
                itemId: 'formportalConfigItem',
                xtype: 'writerformportalConfigModel',
                title: 'Configuraci&oacute;n del Portal',
                border: false,
                width: '60%',
                minWidth: 300,
                listeners: {
                    saveConfig: function(form, data){
                        Instance.entityExtStore.saveConfig('portalConfig', data, parentExtController.formSavedResponse);
                    },
                    cancelConfig: function(form){
                        parentExtController.loadFormData('portalConfig');
                    },
                    render: function(panel) {
                        Instance.commonExtView.enableManagementTabHTMLEditor();
                    }
                }
            }],
            listeners:{
                activate: function(panel) {
                    parentExtController.loadFormData('portalConfig');
                    
                    //show visible list
                    var itemsGroup=Ext.getCmp('locations');
                    setTimeout(function(){
                        for(var i=0; i<9; i++){
                            var itemEntity=Ext.getCmp('locations['+i+']');
                            if(itemEntity.query){
                              itemEntity.query('.field').forEach(function(c){
                                var text=c.getValues();
                                console.log("##1. ");
                                console.log(text);
                              });
                            }else{
                                var text=itemEntity.getValue();
                                if(text!==""){
                                    itemEntity.setVisible(true);
                                    itemEntity.setDisabled(false);
                                    console.log("##2. ");
                                    console.log(text);
                                    itemsGroup.itemTop=i;
                                }
                            }
                        }
                    },1000);
                }
            }
        });
    };
    
    function getFormContainercontactConfig(){
        var formFields= [{"allowBlank":false,"fieldLabel":"Nombre de Usuario","name":"userName"},{"allowBlank":false,"vtype":"email","fieldLabel":"Correo","name":"mail"},{"fieldLabel":"Celular","name":"cellPhone"},{"xtype":"textarea","fieldLabel":"Comentarios","name":"comments","height":200}];

        Instance.defineWriterForm("contactConfigModel", formFields);
        
        return Ext.create('Ext.container.Container', {
            id: 'form-contactConfig',
            type: 'fit',
            align: 'stretch',
            items: [{
                itemId: 'formcontactConfigItem',
                xtype: 'writerformcontactConfigModel',
                title: 'Configuraci&oacute;n de Contacto',
                border: false,
                width: '60%',
                minWidth: 300,
                listeners: {
                    saveConfig: function(form, data){
                        Instance.entityExtStore.saveConfig('contactConfig', data, parentExtController.formSavedResponse);
                    },
                    cancelConfig: function(form){
                        parentExtController.loadFormData('contactConfig');
                    },
                    render: function(panel) {
                        Instance.commonExtView.enableManagementTabHTMLEditor();
                    }
                }
            }],
            listeners:{
                activate: function(panel) {
                    parentExtController.loadFormData('contactConfig');
                }
            }
        });
    };
    
    
    Instance.defineWriterForm= function(modelName, fields){
        Ext.define('WriterForm'+modelName, {
            extend: 'Ext.form.Panel',
            alias: 'widget.writerform'+modelName,

            requires: ['Ext.form.field.Text'],

            initComponent: function(){
                //this.addEvents('create');
                
                var buttons= [];
                
                buttons= [{
                    itemId: 'save'+modelName,
                    text: 'Guardar',
                    scope: this,
                    handler: this.onSave
                }, {
                    //iconCls: 'icon-reset',
                    text: 'Cancelar',
                    scope: this,
                    handler: this.onCancel
                },'|'];
                
                Ext.apply(this, {
                    activeRecord: null,
                    //iconCls: 'icon-user',
                    frame: false,
                    defaultType: 'textfield',
                    bodyPadding: 15,
                    fieldDefaults: {
                        minWidth: 300,
                        anchor: '100%',
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
                this.getForm().reset();
                if (this.activeRecord) {
                    this.getForm().setValues(this.activeRecord.data);
                }
            },
                    
            getActiveRecord: function(){
                return this.activeRecord;
            },
            
            onSave: function(){
                var form = this.getForm();
                
                if (form.isValid()) {
                    this.fireEvent('saveConfig', this, form.getValues());
                }
            },
            
            onCancel: function(){
                this.fireEvent('cancelConfig', this);
            }
    
        });
        
    };
    
    
    
    Instance.createMainView= function(){
        
        Instance.menuConfigurationObjects= getTreeMenuConfigurationObjects();
        

        Instance.tabsContainer= Ext.widget('tabpanel', {
            region: 'center',
            activeTab: 0,
            style: 'background-color:#dfe8f6; margin:0px',
            defaults: {bodyStyle: 'padding:15px', autoScroll:true},
            items:[
                
                {
                    title: 'Gestionar Objetos de Configuraci&oacute;n',
                    layout: 'border',
                    bodyStyle: 'padding:0px',
                    items:[
                        Instance.menuConfigurationObjects,
                        {
                            id: 'content-configurationObjects',
                            region: 'center',
                            layout: 'card',
                            margins: '0 0 0 0',
                            autoScroll: true,
                            activeItem: 0,
                            border: false,
                            items: [
                                
                                getFormContainerportalConfig(),
                                
                                getFormContainercontactConfig(),
                                
                            ]
                       }
                    ]
                },
                
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
        Instance.tabsContainer.getTabBar().hide();
        
        Instance.mainView= {
            id: Instance.id,
            title: 'Gestionar Configuraci&oacute;n General',
            frame: false,
            layout: 'border',
            items: [
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

function GeneralConfigExtController(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/generalConfig";
    
    Instance.modelName="GeneralConfigModel";
    
    Instance.services= {};
    
    var util= new Util();
    
    // VIEWS *******************************************
    
    Instance.entityExtView= new GeneralConfigExtView(Instance, null);
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.entityRef= "generalConfig";
        Instance.typeController= "";
        mvcExt.mappingController(Instance.id, Instance);
        Instance.initFilter();
    };
    
    Instance.initFilter= function(){
        Instance.filter={};
    };
    
    Instance.services.index= function(request){
        var configObj= util.getParameter(request,"configObj");
        Instance.loadFormData(configObj);
    };
    
    Instance.loadFormData= function(configObj){
        if(configObj!==""){
            Instance.entityExtView.entityExtStore.loadConfig(configObj, function(data){
                //Show Process
                Ext.getCmp('content-configurationObjects').layout.setActiveItem('form-'+configObj);
                
                //Populate Form
                var record= Ext.create(configObj+"Model");
                record.data= util.unremakeJSONObject(data);
                var formComponent= Ext.getCmp('form-'+configObj).child('#form'+configObj+'Item');
                formComponent.setActiveRecord(record);
                
                Instance.showListItems(formComponent);
            });
        }
    };
    
    Instance.formSavedResponse= function(result){
        Ext.MessageBox.alert('Status', result.message);
    };
    
    Instance.showListItems= function(formComponent){
        formComponent.query('.fieldset').forEach(function(c){
            c.setDisabled(true);
        });
    };

    Instance.init();
}
</script>
        
        <!-- ############################ IMPORT BASE ELEMENTES ################################### -->
        
        
<script>

function GeneralConfigExtViewport(){
    
    Ext.context= "";
    
    var Instance= this;
    
    var util= new Util();
    
    Instance.entityExtController= new GeneralConfigExtController(null, Instance);
    
    
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
            items: [{"text":"Seguridad","menu":{"items":[{"text":"Gestionar Roles","href":"/vista/role/entity.htm"},{"text":"Gestionar Usuarios","href":"/vista/user/entity.htm"},{"text":"Gestionar Autorizaciones","href":"/vista/authorization/entity.htm"},{"text":"Gestionar Roles de Usuario","href":"/vista/userRole/entity.htm"},{"text":"Gestionar Recursos Web","href":"/vista/webResource/entity.htm"}]}},{"text":"Configuraci&oacute;n","menu":{"items":[{"text":"Gestionar Propiedades","href":"/vista/property/entity.htm"},{"text":"Gestionar Configuraci&oacute;n General","href":"/vista/generalConfig/configurationObject.htm"},{"text":"Mis datos","href":"/vista/myAccount/entity.htm"}]}},{"text":"Gestor de Contenidos","menu":{"items":[{"text":"Explorador de Archivos","href":"/vista/webFile/fileExplorer.htm"}]}},{"text":"Procesos","menu":{"items":[{"text":"Gestionar Servicios Externos","href":"/vista/externalService/process.htm"},{"text":"Gestionar Proceso Main Location","href":"/vista/processMainLocation/process.htm"},{"text":"Gestionar Procesos de Producto","href":"/vista/processProduct/process.htm"},{"text":"Gestionar Procesos de Ordenes de Compra","href":"/vista/processPurchaseOrder/process.htm"},{"text":"Gestionar Procesos de Usuario","href":"/vista/processUser/process.htm"}]}},{"text":"Correos","menu":{"items":[{"text":"Gestionar Plantillas de Correo","href":"/vista/mailTemplate/entity.htm"},{"text":"Gestionar Correos","href":"/vista/mail/entity.htm"}]}},{"text":"Comercios","menu":{"items":[{"text":"Gestionar Comercios","href":"/vista/commerce/entity.htm"},{"text":"Gestionar Ubicaciones Principales","href":"/vista/mainLocation/entity.htm"}]}},{"text":"Productos","menu":{"items":[{"text":"Gestionar Categorias","href":"/vista/category/entity.htm"},{"text":"Gestionar Productos","href":"/vista/product/entity.htm"},{"text":"Reporte de Productos","href":"/vista/product/report/reporteProductos.htm"}]}},{"text":"Pedidos","menu":{"items":[{"text":"Gestionar Ordenes de Inventario","href":"/vista/inventoryOrder/entity.htm"},{"text":"Gestionar Proveedores","href":"/vista/supplier/entity.htm"}]}},{"text":"Ordenes de Compra","menu":{"items":[{"text":"Gestionar Ordenes de Compra","href":"/vista/purchaseOrder/entity.htm"},{"text":"Mis Compras","href":"/vista/myShopping/entity.htm"}]}},{"text":"Pagos","menu":{"items":[{"text":"Gestionar Pagos","href":"/vista/payment/entity.htm"}]}},{"text":"Tablas Lead","menu":{"items":[{"text":"Gestionar Tablas Lead","href":"/vista/leadTable/entity.htm"}]}}]
        });
        
        
    };
    
    
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

            var homeExtViewport= new GeneralConfigExtViewport();

            homeExtViewport.renderViewport();

            //Debe ser siempre la ultima linea**************************
            mvcExt.setHomeRequest("/generalConfig");
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
            Instance.errorGeneral= "Error de servidor";
            Instance.error403= "Usted no tiene permisos para realizar esta operaci&oacute;n";
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
            //id: component+'Combobox'+fieldName+'In'+model,
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
    
    Instance.audioGridRender= function(value, p, record){
        if(value){
            return '<audio style="width:100%" src="'+value+'" preload="auto" controls>'+
                   '    Your browser does not support the video tag.'+
                   '</audio>';
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
            return '<a id="linkFile" href="'+value+'" target="_blank">'+value+'</a><br>'+
                   '<img style="max-width:150%" src="'+value+'">';
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
    
    Instance.processFailure= function(response){
        if(response.status===403){
            Instance.showErrorMessage(Instance.error403);
            Instance.loadUserInSession(function(userData){
                if(!userData.session){
                    location.reload(); 
                }
            });
        }else{
            Instance.showErrorMessage(Instance.errorGeneral);
        }
    };
    
    Instance.showErrorMessage= function(errorMsg){
        Ext.MessageBox.show({
            title: 'REMOTE EXCEPTION',
            msg: errorMsg,
            icon: Ext.MessageBox.ERROR,
            buttons: Ext.Msg.OK
        });
    };
    
    Instance.loadUserInSession= function(func){
        Ext.Ajax.request({
            url: Ext.context+'/account/ajax/userInSession',
            method: "GET",
            success: function(response){
                func(Ext.decode(response.responseText));
            },
            failure: function(response){
                func({"session":true});
            }
        });
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
        <script src="/js/util/vkbeautify.0.99.00.beta.js"></script>
        
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="/css/navegador.css">
        <link rel="stylesheet" type="text/css" href="/css/gridTemplateStyles.css">
        
    </head>
    <body>
        




<div id="headerHtml" style="display:none;">
    <a href="/"><img src="/img/habitares.png" class="logoAdmin"></a>
    <h1>Administraci&oacute;n MERCANDO</h1>
    <a class="logout" href="/security_logout">&nbsp;Cerrar sesi&oacute;n&nbsp;</a>
    <a class="home" href="/account/home?redirect=user">&nbsp;Inicio&nbsp;</a>
    
    
        <p class="userSession"><b>lcastrillo</b> - Luis Alberto Castrillo</p>
    
</div>
<script type="text/javascript">
    var navegadorExtInit= new EntityExtInit();
</script>

    </body>
</html>

