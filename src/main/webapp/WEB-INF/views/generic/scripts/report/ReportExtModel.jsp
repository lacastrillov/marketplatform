<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function ${reportName}ExtModel(){
    
    var Instance = this;
    
    
    Instance.define${reportName}Model= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: ${jsonModel}
        });
    };
    
    <c:if test="${reportConfig.activeGridTemplate}">
    Instance.define${reportName}TemplateModel= function(modelName){
        Ext.define(modelName, {
            extend: 'Ext.data.Model',
            fields: ${jsonTemplateModel}
        });
    };
    </c:if>
    
}
</script>