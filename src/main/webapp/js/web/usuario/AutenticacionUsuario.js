/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function AutenticacionUsuario() {

    var Instance = this;

    Instance.init = function () {
        $(document).ready(function () {
            $("#linkIngresar").click(function (e) {
                e.preventDefault();
                $("#formLogin").submit();
            });
            
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
            error: function (xhr, status) {
                console.log(xhr.status);
            },
            success: function (data, status) {
                callback(data);
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
                error: function (xhr, status) {
                    console.log(xhr.status);
                },
                success: function (data, status) {
                    $("#message").html(data);
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
        console.log(correoElectronico);
        if (correoElectronico !== "") {
            console.log("diferente");
            $.ajax({
                url: $("#changePasswordForm").attr("action"),
                timeout: 20000,
                type: "POST",
                data: $("#changePasswordForm").serialize(),
                cache: false,
                dataType: "html",
                error: function (xhr, status) {
                    console.log(xhr.status);
                    console.log("error");
                },
                success: function (data, status) {
                    $("#message").html(data);
                    console.log(data);
                }
            });
        } else {
            $("#message").html("Ingrese un correo electronico");
            console.log("ingrese");
        }
    };

    Instance.changeForm = function (idForm) {
        $("#loginDiv").hide();
        $("#changePasswordDiv").hide();
        $("#" + idForm).show();
    };

    Instance.init();
}