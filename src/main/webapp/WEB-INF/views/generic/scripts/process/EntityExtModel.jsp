<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

function ${entityName}ExtModel(){
    
    var Instance = this;
    
    <c:forEach var="processName" items="${nameProcesses}">
    Instance.define${processName.key}Model= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: ${jsonModelMap[processName.key]},
            validations: ${jsonModelValidationsMap[processName.key]}
        });
    };
    </c:forEach>

    Instance.define${viewConfig.entityNameLogProcess}Model= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: ${jsonModelLogProcess},
            validations: ${jsonModelValidationsLogProcess}
        });
    };
    
}
</script>