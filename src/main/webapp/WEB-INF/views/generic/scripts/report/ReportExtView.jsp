<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${reportName}ExtView(parentExtController, parentExtView){
    
    var Instance= this;
    
    Instance.id= "/${entityRef}";
    
    Instance.modelName="${reportName}Model";
    
    var util= new Util();
    
    // MODELS **********************************************
    
    Instance.reportExtModel= new ${reportName}ExtModel();
    
    // STORES **********************************************
    
    Instance.reportExtStore= new ${reportName}ExtStore();
    
    // COMPONENTS *******************************************
    
    Instance.commonExtView= new CommonExtView(parentExtController, Instance, '${reportName}');
    
    //*******************************************************
    
    
    Instance.init= function(){
        Instance.typeView= "${typeView}";
        Instance.reportExtModel.define${reportName}Model(Instance.modelName);
        Instance.store= Instance.reportExtStore.get${reportName}Store(Instance.modelName);
        <c:if test="${reportConfig.activeGridTemplate}">
        Instance.gridModelName= "${reportName}TemplateModel";
        Instance.reportExtModel.define${reportName}TemplateModel(Instance.gridModelName);
        Instance.gridStore= Instance.reportExtStore.get${reportName}TemplateStore(Instance.gridModelName);
        </c:if>
        Instance.createMainView();
    };
    
    Instance.setFilterStore= function(filter){
        <c:if test="${not reportConfig.activeGridTemplate}">
            Instance.store.getProxy().extraParams.filter= filter;
        </c:if>
        <c:if test="${reportConfig.activeGridTemplate}">
            Instance.gridStore.getProxy().extraParams.filter= filter;
        </c:if>
    };
    
    Instance.reloadPageStore= function(page){
        <c:if test="${not reportConfig.activeGridTemplate}">
            Instance.store.loadPage(page);
        </c:if>
        <c:if test="${reportConfig.activeGridTemplate}">
            Instance.gridStore.loadPage(page);
        </c:if>
    };
    
    <c:if test="${reportConfig.visibleForm}">
    function getFormContainer(modelName, store, childExtControllers){
        var formFields= ${jsonFormFields};

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
                    Instance.entityExtStore.save${entityName}('create', JSON.stringify(data), parentExtController.formSavedResponse);
                },
                update: function(form, data){
                    Instance.entityExtStore.save${entityName}('update', JSON.stringify(data), parentExtController.formSavedResponse);
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
            title: 'Detalle',
            type: 'fit',
            align: 'stretch',
            items: itemsForm
        });
    };
    
    function getChildsExtViewTabs(childExtControllers){
        var items=[];
        
        childExtControllers.forEach(function(childExtController) {
            var itemTab= {
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
    
    </c:if>
    
    <c:if test="${reportConfig.visibleValueMapForm}">
    function getValueMapFormContainer(){
        return Ext.widget({
            xtype: 'form',
            layout: 'form',
            title: 'Establecer variables',
            id: 'simpleForm'+Instance.modelName,
            region: 'north',
            frame: false,
            collapsible: true,
            bodyPadding: '5 5 0',
            fieldDefaults: {
                labelWidth: 170,
                labelAlign: 'right'
            },
            defaultType: 'textfield',
            items: ${jsonFormFields},

            buttons: [{
                text: 'Consultar',
                handler: function() {
                    this.up('form').getForm().isValid();
                    parentExtController.filter.vm=this.up('form').getForm().getValues();
                    parentExtController.doFilter();
                }
            },{
                text: 'Limpiar',
                handler: function() {
                    this.up('form').getForm().reset();
                }
            }]
        });
    }
    </c:if>
    
    function getGridContainer(modelName, store, formContainer){
        var idGrid= 'grid'+modelName;
        var gridColumns= ${jsonGridColumns};

        var getEmptyRec= function(){
            return new ${reportName}Model(${jsonEmptyModel});
        };
        
        <c:if test="${reportConfig.activeGridTemplate}">
        modelName= Instance.gridModelName;
        store= Instance.gridStore;
        </c:if>

        Instance.defineGrid(modelName, '${reportConfig.pluralReportTitle}', gridColumns, getEmptyRec);
        
        return Ext.create('Ext.container.Container', {
            id: 'gridContainer'+modelName,
            title: 'Listado',
            region: 'center',
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
                disableSelection: ${reportConfig.activeGridTemplate},
                trackMouseOver: !${reportConfig.activeGridTemplate},
                listeners: {
                    selectionchange: function(selModel, selected) {
                        if(selected[0]){
                            parentExtController.loadFormData(selected[0].data.id)
                        }
                    },
                    export: function(typeReport){
                        var data= "?filter="+JSON.stringify(parentExtController.filter);
                        data+="&limit="+store.pageSize+"&page="+store.currentPage;
                        if(store.sorters.items.length>0){
                            data+="&sort="+store.sorters.items[0].property+"&dir="+store.sorters.items[0].direction;
                        }
                        data+="&dtoName=${reportConfig.dtoName}";
                        
                        switch(typeReport){
                            case "json":
                                var urlFind= store.proxy.api.read;
                                window.open(urlFind+data,'_blank');
                                break;
                            case "xml":
                                var urlFind= store.proxy.api.read.replace("/report/","/report/xml/");
                                window.open(urlFind+data,'_blank');
                                break;
                            case "xls":
                                var urlFind= store.proxy.api.read.replace("/report/","/report/xls/");
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
        combobox.setValue(${reportConfig.maxResultsPerPage});
        
        return combobox;
    }
    
    function getComboboxOrderBy(store){
        var combobox= Instance.commonExtView.getSimpleCombobox('sort', 'Ordenar por', 'config', ${sortColumns});
        combobox.addListener('change',function(record){
            if(record.getValue()!=="" && store.sorters.items[0].property!==record.getValue()){
                store.sorters.items[0].property=record.getValue();
                Instance.reloadPageStore(1);
            }
        }, this);
        combobox.labelWidth= 80;
        combobox.width= 250;
        combobox.setValue("${reportConfig.defaultOrderBy}");
        
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
        combobox.setValue("${reportConfig.defaultOrderDir}");
        
        return combobox;
    }
    
    Instance.defineGrid= function(modelName, modelText, columns, getEmptyRec){
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
                    hideHeaders:${reportConfig.hideHeadersGrid},
                    frame: false,
                    plugins: [this.editing],
                    dockedItems: [ {
                        weight: 2,
                        xtype: 'toolbar',
                        dock: 'bottom',
                        items: [{
                            xtype: 'tbtext',
                            text: '<b>@lacv</b>'
                        }, '|',
                        <c:if test="${reportConfig.visibleExportButton}">
                        {
                            xtype:'splitbutton',
                            text: 'Exportar',
                            //iconCls: 'add16',
                            menu: [
                                {text: 'A xls', handler: function(){this.exportTo('xls')}, scope: this},
                                {text: 'A json', handler: function(){this.exportTo('json')}, scope: this},
                                {text: 'A xml', handler: function(){this.exportTo('xml')}, scope: this}]
                        },
                        </c:if>
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
                if(this.down('#delete')!=null){
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

    Instance.createMainView= function(){
        Instance.childExtControllers= [];
    
        <c:if test="${reportConfig.visibleValueMapForm}">
        Instance.valueMapformContainer = getValueMapFormContainer();
        </c:if>
            
        Instance.formContainer= null;
        <c:if test="${reportConfig.visibleForm}">
        Instance.formContainer = getFormContainer(Instance.modelName, Instance.store, Instance.childExtControllers);
        Instance.store.formContainer= Instance.formContainer;
        </c:if>
            
        Instance.gridContainer = getGridContainer(Instance.modelName, Instance.store, null);
        Instance.store.gridContainer= Instance.gridContainer;
        
        Instance.tabsContainer= Ext.widget('tabpanel', {
            region: 'center',
            activeTab: 0,
            style: 'background-color:#dfe8f6; margin:0px',
            defaults: {bodyStyle: 'padding:15px', autoScroll:true},
            items:[
                Instance.gridContainer,
                <c:if test="${reportConfig.visibleForm}">
                Instance.formContainer
                </c:if>
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
            title: '${reportConfig.pluralReportTitle}',
            frame: false,
            layout: 'border',
            items: [
                <c:if test="${reportConfig.visibleValueMapForm}">
                Instance.valueMapformContainer,
                </c:if>
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