function createCommentt(e, formId) {
    var $from = $('#'+formId);

    $from.validate();

    if (!$from.valid()) {
        return;
    }

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {
        if (node.name !== "userType" || node.checked ) {
            dataPost += node.name;
            dataPost += "=";
            dataPost += node.value;
            dataPost += "&";
        }
    });

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                document.getElementById("createWarn").style.display = 'inherit';
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("createError").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}

function changeLockComment(e, formId) {
    var $from = $('#'+formId);

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {
        if (node.name !== "userType" || node.checked ) {
            dataPost += node.name;
            dataPost += "=";
            dataPost += node.value;
            dataPost += "&";
        }
    });

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === true) {
                window.location.reload();
            } else {
                if (data.accessDenied !== undefined) {
                    document.getElementById("accessDenied").style.display = 'inherit';
                }
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("serverError").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}