function openTab(evt, elementId, group, linkGroup) {
    var i, x, tablinks;
    x = document.getElementsByClassName(group);
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName(linkGroup);
    for (i = 0; i < x.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" w3-white", "");
    }
    document.getElementById(elementId).style.display = "block";
    evt.currentTarget.className += " w3-white";
}

function addMoney(e, closableModal) {

    var $from = $('#addCashForm');
    var addCashValue = parseInt(document.getElementById("addCash").value);

    if (isNaN(addCashValue) || addCashValue < 1 || addCashValue > 1000) {
        document.getElementById("wrongData").style.display = 'inherit';
        return;
    }

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {

        dataPost += node.name;
        dataPost += "=";
        dataPost += node.value;
        dataPost += "&";

    });
    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                if (data.accessDenied !== undefined) {
                    document.getElementById("accessDenied").style.display = 'inherit';
                }
                if (data.wrongData !== undefined) {
                    document.getElementById("wrongData").style.display = 'inherit';
                }
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("serverError").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }
    });
}


function withdrawMoney(e, closableModal) {

    var $from = $('#withdrawCashForm');
    var addCashValue = parseInt(document.getElementById("withdrawCash").value);

    if (isNaN(addCashValue) || addCashValue < 1 || addCashValue > 1000) {
        document.getElementById("wrongData").style.display = 'inherit';
        return;
    }

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {

        dataPost += node.name;
        dataPost += "=";
        dataPost += node.value;
        dataPost += "&";

    });
    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                if (data.accessDenied !== undefined) {
                    document.getElementById("accessDenied").style.display = 'inherit';
                }
                if (data.wrongData !== undefined) {
                    document.getElementById("wrongData").style.display = 'inherit';
                }
                if (data.littleMoney !== undefined) {
                    document.getElementById("littleMoney").style.display = 'inherit';
                }
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("serverError").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }
    });
}