/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function UserAuthentication() {

    var Instance = this;

    Instance.init = function () {
        $(document).ready(function () {
            
            $("#j_username, #j_password").keypress(function(e) {
                if(e.which === 13) {
                    $("#formLogin").submit();
                }
            });
            
            var olvideContrasena = util.getParameter(document.URL, "olvideContrasena");
            if (olvideContrasena === "1") {
                Instance.changeForm("changePasswordDiv");
            }
        });
    };
    
    Instance.authenticate = function (callback) {
        $.ajax({
            url: $("#ajaxFormLogin").attr("action"),
            timeout: 20000,
            type: "POST",
            data: $("#ajaxFormLogin").serialize(),
            cache: false,
            dataType: "json",
            success: function (data, status) {
                callback(data);
            },
            error: function (xhr, status) {
                console.log(xhr.status);
            }
        });
    };

    Instance.changePassword = function () {
        $("#message").html("Enviando...");
        var contrasena = $("#contrasena").val();
        var contrasenaControl = $("#contrasenaControl").val();
        if (contrasena === contrasenaControl) {
            $.ajax({
                url: $("#changePasswordForm").attr("action"),
                timeout: 20000,
                type: "POST",
                data: $("#changePasswordForm").serialize(),
                cache: false,
                dataType: "html",
                success: function (data, status) {
                    $("#message").html(data);
                },
                error: function (xhr, status) {
                    console.log(xhr.status);
                }
            });
        } else {
            $("#message").html("Las contrase&ntilde;as no coinciden...");
            return false;
        }
    };

    Instance.resetPassword = function () {
        $("#message").html("Enviando...");
        var correoElectronico = $("#correoElectronico").val();
        if (correoElectronico !== "") {
            $.ajax({
                url: $("#changePasswordForm").attr("action"),
                timeout: 20000,
                type: "POST",
                data: $("#changePasswordForm").serialize(),
                cache: false,
                dataType: "html",
                success: function (data, status) {
                    $("#message").html(data);
                },
                error: function (xhr, status) {
                    console.log(xhr.status);
                }
            });
        } else {
            $("#message").html("Ingrese un correo electronico");
            console.log("ingrese");
        }
    };

    Instance.changeForm = function (classForm) {
        $(".loginDiv").hide();
        $(".changePasswordDiv").hide();
        $("." + classForm).show();
    };

    Instance.init();
}