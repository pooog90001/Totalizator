$(document).ready(function () {

    $(".wrong").each(function () {
        this.style.display = "none";
    });

    var emptyOldPassword = $("#emptyOldPassword");

    var emptyPassword = $("#emptyPassword");
    var wrongPassword = $("#wrongPassword");

    var emptyRepeatPassword = $("#emptyRepeatPassword");
    var wrongRepeatPassword = $("#wrongRepeatPassword");

    var oldPassword = $("#oldPassword");
    var password = $("#password");
    var repeatPassword = $("#repeatPassword");

    oldPassword.keyup(function (e) {
        checkOldPassword();
    });
    oldPassword.change(function (e) {
        checkOldPassword();
    });

    password.keyup(function (e) {
        checkPassword();
    });
    password.change(function (e) {
        checkPassword();
    });

    repeatPassword.keyup(function (e) {
        checkRepeatPassword();
    });
    repeatPassword.change(function (e) {
        checkRepeatPassword();
    });

    function checkOldPassword() {
        emptyOldPassword.hide();
        var value = oldPassword.val();
        var result = true;

        if (value === "") {
            emptyOldPassword.show();
            result = false;
        }
        return result;
    }

    function checkPassword() {
        wrongPassword.hide();
        emptyPassword.hide();
        var value = password.val();
        var hasBigWord = /[A-ZА-Я]/g.test(value);
        var hasWord = /[a-zа-я]/g.test(value);
        var hasNumber = /\d/g.test(value);
        var hasSatisfactoryLength = (value.length >= 6 && value.length < 80);
        var result = true;
        checkRepeatPassword();
        if (value === "") {
            emptyPassword.show();
            result = false;

        } else if (!(hasBigWord && hasWord && hasNumber && hasSatisfactoryLength)) {
            wrongPassword.show();
            result = false;
        }
        return result;
    }


    function checkRepeatPassword() {
        wrongRepeatPassword.hide();
        emptyRepeatPassword.hide();
        var value1 = password.val();
        var value2 = repeatPassword.val();
        var result = true;

        if (value2 === "") {
            emptyRepeatPassword.show();
            result = false;

        } else if (value1 !== value2) {
            wrongRepeatPassword.show();
            result = false;
        }
        return result;
    }


    $("#submit").click(function (e) {
        $(".wrong").each(function () {
            this.style.display = "none";
        });
        if (!(checkOldPassword() && checkPassword() && checkRepeatPassword())) {
            e.preventDefault();

        } else {
            sendForm();
        }
    });

    function sendForm() {
        var $from = $('#form');

        var dataPost = "";
        $from.find("input[name]").each(function (index, node) {
            dataPost += node.name;
            dataPost += "=";
            dataPost += node.value;
            dataPost += "&";
        });

        $.ajax({
            type: "POST",
            url: "/ajaxController",
            data: dataPost,
            dataType: "json",

            success: function (data, textStatus, jqXHR) {
                if (data.success === false) {
                    if (data.wrongData !== undefined) {
                        document.getElementById("wrongData").style.display = 'inherit';
                    } else if (data.accessDenied !== undefined) {
                        document.getElementById("accessDenied").style.display = 'inherit';
                    } else if (data.wrongUpload !== undefined) {
                        document.getElementById("wrongRepeatPassword").style.display = 'inherit';
                    } else if (data.wrongOldPassword !== undefined) {
                        document.getElementById("wrongOldPassword").style.display = 'inherit';
                    } else if (data.equalsPasswords !== undefined) {
                        document.getElementById("equalsPasswords").style.display = 'inherit';
                    } else {
                        document.getElementById("wrongDB").style.display = 'inherit';
                    }

                } else {
                    document.getElementById("success").style.display = 'inherit';
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                document.getElementById("errorResponse").style.display = 'inherit';
            },
            beforeSend: function (jqXHR, settings) {
            },
            complete: function (jqXHR, textStatus) {
            }
        });
    }

});

