<%
Integer ExtJSVersion=4;
String ExtJSLib4="http://localhost/ext-4.2.1";
String ExtJSLib6="http://localhost/ext-6.2.0/build";
if(ExtJSVersion==4){
%>
    <script type="text/javascript">
        var ExtJSVersion=4;
        var ExtJSLib="<%=ExtJSLib4%>";
    </script>
    <script src="<%=ExtJSLib4%>/examples/shared/include-ext.js"></script>
<%
}else{
%>
    <script type="text/javascript">
        var ExtJSVersion=6;
        var ExtJSLib="<%=ExtJSLib6%>";
    </script>
    <script src="<%=ExtJSLib6%>/examples/classic/shared/include-ext.js"></script>
    <!--<script src="<%=ExtJSLib6%>/examples/classic/shared/options-toolbar.js"></script>-->
<%
}
%>