<%-- 
    Document   : uploadlf
    Created on : 15/02/2017, 09:27:53 PM
    Author     : lacastrillov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload File</title>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/libs/jquery/jquery-3.1.0.min.js"></script>
        <script>
            [
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
        "itemTop": 0,
        "layout": "anchor",
        "xtype": "fieldset",
        "fieldDefaults": {
          "labelAlign": "right",
          "anchor": "100%",
          "disabled": true
        },
        "id": "rol.autorizaciones",
        "title": "Autorizaciones:",
        "collapsible": true,
        "items": [
          {
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[0]",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[1]",
            "title": "Item 1",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[2]",
            "title": "Item 2",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[3]",
            "title": "Item 3",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[4]",
            "title": "Item 4",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[5]",
            "title": "Item 5",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[6]",
            "title": "Item 6",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[7]",
            "title": "Item 7",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[8]",
            "title": "Item 8",
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
            "layout": "anchor",
            "xtype": "fieldset",
            "fieldDefaults": {
              "labelAlign": "right",
              "anchor": "100%",
              "disabled": true
            },
            "id": "rol.autorizaciones[9]",
            "title": "Item 9",
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
            "handler": function(){
              varitemsGroup=Ext.getCmp(\"rol.autorizaciones\");                   if(itemsGroup.itemTop<9){                       itemsGroup.itemTop+= 1;                       var itemEntity= Ext.getCmp(\"rol.autorizaciones[\"+itemsGroup.itemTop+\"]\");                       itemEntity.setVisible(true);                       itemEntity.query('.field').forEach(function(c){                           c.setDisabled(false);                       });                   }               },"xtype":"button","width":100,"style":"margin: 5px","text":"Agregar"},{"handler":function(){                   var itemsGroup= Ext.getCmp(\"rol.autorizaciones\");                   if(itemsGroup.itemTop>=0){                       var itemEntity= Ext.getCmp(\"rol.autorizaciones[\"+itemsGroup.itemTop+\"]\");                       itemEntity.query('.field').forEach(function(c){                           c.setDisabled(true);                       });                       itemsGroup.itemTop-= 1;                       itemEntity.setVisible(false);                   }               },"xtype":"button","width":100,"style":"margin: 5px","text":"Quitar"}],"defaultType":"textfield"}],"defaultType":"textfield"}]
        </script>
    </head>
    <body>
        <H3>Upload File</H3>
        <form id="uploadFileForm" action="<%=request.getContextPath()%>/requestcontent/ajax/uploadFile.htm" method="POST" enctype="multipart/form-data">
            <input type="file" name="archivo" id="archivo" />
            <input type="button" value="Enviar" id="enviarArchivo" />
        </form>
        <div id="message"></div>
    </body>
</html>
