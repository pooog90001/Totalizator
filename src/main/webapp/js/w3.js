closeSidebar();

function dropDown(className) {
    var x = document.getElementsByClassName(className);
    for (var i = 0; i < x.length; i++) {

        if (x[i].className.indexOf("w3-show") === -1) {
            x[i].className += " w3-show";
            x[i].previousElementSibling.className += " w3-theme-d1";

        } else {
            x[i].className = x[i].className.replace("w3-show", "");
            x[i].previousElementSibling.className =
                x[i].previousElementSibling.className.replace(" w3-theme-d1", "");
        }
    }
}

// Used to toggle the menu on smaller screens when clicking on the menu button
function openNav() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
}


function openSidebar() {
    var bar = document.getElementById("mySidebar");
    if (bar !== null) {
        bar.style.display = "block";
    }
}

function closeSidebar() {
    var bar = document.getElementById("mySidebar");
    if (bar !== null) {
        bar.style.display = "none";
    }
}


function changeLanguage(element) {

    var dataString = "locale=" + element.value + "&command=change_locale";

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            window.location.reload();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}

/*document.onload = function () {

    var windowHeight = window.innerHeight(true);

    if (bodyHeight < windowHeight) {
        var marginTop = (windowHeight - bodyHeight).toString();
        $("footer").css('marginTop', marginTop + "px");
    }

 };*/

/*$(document).ready(function () {
    var bodyHeight = $("body").height();
 $("footer").css('top', bodyHeight);


 document.getElementsByTagName("body").onresize = function(event) {
 var bodyHeight = $("body").height();
 $("footer").css('top', bodyHeight);
 };

 var body = document.getElementsByTagName("body");

 [].forEach.call(body, function(el){
 el.addEventListener('onresize', function (e) {
 var bodyHeight = $("body").height();
 $("footer").css('top', bodyHeight);
 })
 });

 });

 $("body").resize(function() {
 var bodyHeight = $("body").height();
 $("footer").css('top', bodyHeight);
 });

 document.getElementsByTagName("body").onresize = function(event) {
 var bodyHeight = $("body").height();
 $("footer").css('top', bodyHeight);
 };*/
