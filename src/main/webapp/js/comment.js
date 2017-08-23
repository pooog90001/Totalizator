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
