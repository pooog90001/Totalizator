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
        var value = password.val();

        if (value.toString().trim() === "") {
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

        if (value.toString().trim() === "") {
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

        if (value2.toString().trim() === "") {
            emptyRepeatPassword.show();
            result = false;

        } else if (value1 !== value2) {
            wrongRepeatPassword.show();
            result = false;
        }
        return result;
    }


    $("#submit").click(function (e) {
        if (!(checkOldPassword() && checkPassword() && checkRepeatPassword())) {
            e.preventDefault();

        } else {

        }
    });

    //checks for the button click event
    $("#myButton").click(function (e) {

        //get the form data and then serialize that
        dataString = $("#myAjaxRequestForm").serialize();

        //get the form data using another method
        var countryCode = $("input#countryCode").val();
        dataString = "countryCode=" + countryCode;

        //make the AJAX request, dataType is set to json
        //meaning we are expecting JSON data in response from the server
        $.ajax({
            type: "POST",
            url: "CountryInformation",
            data: dataString,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if (data.success) {
                    $("#ajaxResponse").html("");
                    $("#ajaxResponse").append("<b>Country Code:</b> " + data.countryInfo.code + "<br/>");
                    $("#ajaxResponse").append("<b>Country Name:</b> " + data.countryInfo.name + "<br/>");
                    $("#ajaxResponse").append("<b>Continent:</b> " + data.countryInfo.continent + "<br/>");
                    $("#ajaxResponse").append("<b>Region:</b> " + data.countryInfo.region + "<br/>");
                    $("#ajaxResponse").append("<b>Life Expectancy:</b> " + data.countryInfo.lifeExpectancy + "<br/>");
                    $("#ajaxResponse").append("<b>GNP:</b> " + data.countryInfo.gnp + "<br/>");
                }
                //display error message
                else {
                    $("#ajaxResponse").html("<div><b>Country code in Invalid!</b></div>");
                }
            },

            //If there was no resonse from the server
            error: function (jqXHR, textStatus, errorThrown) {
                console.log("Something really bad happened " + textStatus);
                $("#ajaxResponse").html(jqXHR.responseText);
            },

            //capture the request before it was sent to server
            beforeSend: function (jqXHR, settings) {
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
            },

            //this is called after the response or error functions are finsihed
            //so that we can take some action
            complete: function (jqXHR, textStatus) {
                //enable the button
                $('#myButton').attr("disabled", false);
            }

        });
    });

});

