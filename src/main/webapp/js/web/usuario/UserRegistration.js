/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
util.importJS('/js/util/Message.js');
util.importJS('/js/web/stores/UserExtStore.js');

function UserRegistration() {

    var Instance = this;
    
    var message;
    
    var userExtStore;

    Instance.init = function () {
        $(document).ready(function () {
            message= new Message();
            userExtStore= new UserExtStore();
            
            $("#linkIngresar").click(function (e) {
                e.preventDefault();
                $("#formLogin").submit();
            });
        });
    };
    
    Instance.validate= function(){
        
        return true;
    };
    
    Instance.sendData= function(){
        if(Instance.validate()){
            var data = {};
            $("#userRegistrationForm").serializeArray().map(function(x){data[x.name] = x.value;});
            userExtStore.doProcess("processUser", "registerUser", data, function(responseText){
                message.showMessage("Registro de usuario", JSON.parse(responseText).message);
                $('#userRegistrationForm').trigger("reset");
            });
        }
    };

    Instance.init();
}