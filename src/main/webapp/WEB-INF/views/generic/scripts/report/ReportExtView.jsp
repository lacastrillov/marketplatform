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
    
    var gridExtComponent= new GridExtComponent();
    
    Instance.commonExtView= new CommonExtView(parentExtController, Instance, '${reportName}');
    
    //*******************************************************
    
    
    Instance.init= function(){
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
    
    <c:if test="${reportConfig.visibleValueMapForm}">
    function getFormContainer(){
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
            title: 'Listado ${reportConfig.pluralReportTitle}',
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
        combobox.setValue("${viewConfig.defaultOrderBy}");
        
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
        <c:if test="${reportConfig.visibleValueMapForm}">
        Instance.formContainer = getFormContainer();
        </c:if>
        Instance.gridContainer = getGridContainer(Instance.modelName, Instance.store, null);
        Instance.store.gridContainer= Instance.gridContainer;
        
        Instance.mainView= {
            id: Instance.id,
            title: '${reportConfig.pluralReportTitle}',
            frame: false,
            layout: 'border',
            items: [
                <c:if test="${reportConfig.visibleValueMapForm}">
                Instance.formContainer,
                </c:if>
                Instance.gridContainer
            ]
        };
        
    };
    
    Instance.getMainView= function(){
        return Instance.mainView;
    };

    Instance.init();
}
</script>