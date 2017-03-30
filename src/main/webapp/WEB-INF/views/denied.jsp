<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="<c:url value="/css/springsource.css"/>" type="text/css"/>
        <title>Registro Celular: Acceso Denegado</title>
        <style type="text/css">
            .secureTableTd { padding:10px; }
            .headerTable {background-color: #e5e5e5; color: #203231; }
        </style>
    </head>

    <body>
        
        <table class="secureTable" border="1" align="center" width="85%">
            
            <tr>
                <td class="secureTableTd headerTable" align="center" colspan="10">
                    <h2>.:| Acceso Denegado |:.</h2>
                </td>
            </tr>
            <tr>
                <td class="secureTableTd" width="100%" valign="top" colspan="10">
                    <h3>Se&ntilde;or usuario, usted no tiene acceso a &eacute;sta secci&oacute;n</h3><br />
                    
                    <p><a href="<%=request.getContextPath()%>/home">Regresar al Home</a></p><br />
                    
                    <p><a href="<%=request.getContextPath()%>/logout">Cerrar sesi&oacute;n</a></p><br />
                </td>
            </tr>
        </table>
    </body>

</html>
