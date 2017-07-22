$(document).ready(function() {
    var emptyEmail = $("#emptyEmail");
    var wrongEmail = $("#wrongEmail");
    wrongEmail.hide();
    emptyEmail.hide();

    //Stops the submit request
    $("#submit").submit(function(e){
        e.preventDefault();
    });

    var email = $("#email");

    email.keyup(function(e){
        checkEmail();
    });
    email.change(function(e){
        checkEmail();
    });
    
     function checkEmail() {
         $("#serverWrongEmail").hide();
         wrongEmail.hide();
         emptyEmail.hide();
       var value = email.val();
       value.trim();

       if(value === "") {
           emptyEmail.show();

       } else if (!/([_\w-]+(\.[_\w-]+)*@[\w-]+(\.[\w-]+)*(\.[a-zA-Z]{1,6}))/.test(value)) {
           wrongEmail.show();
       }
     }

    //checks for the button click event
    $("#myButton").click(function(e){

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
            success: function( data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                if(data.success){
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
            error: function(jqXHR, textStatus, errorThrown){
                console.log("Something really bad happened " + textStatus);
                $("#ajaxResponse").html(jqXHR.responseText);
            },

            //capture the request before it was sent to server
            beforeSend: function(jqXHR, settings){
                //adding some Dummy data to the request
                settings.data += "&dummyData=whatever";
                //disable the button until we get the response
                $('#myButton').attr("disabled", true);
            },

            //this is called after the response or error functions are finsihed
            //so that we can take some action
            complete: function(jqXHR, textStatus){
                //enable the button
                $('#myButton').attr("disabled", false);
            }

        });
    });

});
