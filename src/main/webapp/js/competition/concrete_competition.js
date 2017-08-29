$("input[name='bet']").click(function () {
    $("#dataEmpty").each(function (index, node) {
        node.style.display = "none";
    });
});

$("#cash").click(function () {
    $("#wrongCash").each(function (index, node) {
        node.style.display = "none";
    });
});

document.getElementById("cash").onchange = function () {

    var cash = parseInt( $("#cash")[0].value);

    if (isNaN(cash)){
        cash = 0;
    }

    $("#cash")[0].value = cash;
};

function checkFormFields(e, formId) {

    $(".w3-text-red").each(function (index, node) {
        node.style.display = "none";
    });


    var $from = $('#' + formId);

    var isFilled = true;

    var isBetChecked = false;
    $from.find("input[name]").each(function (index, node) {
        if (node.name === "bet" && node.checked) {
            isBetChecked = true;
        }
    });

    isFilled = isBetChecked;

    $from.find("input[name]").each(function (index, node) {
        if (node.name === "cash" &&
            node.value.toString().trim() === "") {
            isFilled = false;
        }
    });

    var cash = parseInt($("#cash")[0].value);

    if ( isNaN(cash) || cash < 1 || cash > 1000 ) {
        document.getElementById("wrongCash").style.display = "inherit";
        return;
    }

    if (isFilled) {
        document.getElementById("modalBeforeDoBet").style.display = "inherit";

    } else {
        document.getElementById("dataEmpty").style.display = "inherit";

    }

}

function doBet(e, formId) {

    document.getElementById("modalBeforeDoBet").style.display = "none";

    var $from = $('#' + formId);

    var dataPost = "";
    $from.find("input[name]").each(function (index, node) {
        if (node.name !== "bet") {
            dataPost += node.name;
            dataPost += "=";
            dataPost += node.value;
            dataPost += "&";
        }
    });

    $from.find("input[name]").each(function (index, node) {
        if (node.name === "bet" && node.checked) {
            dataPost += "onTeam=";
            dataPost += $(node).attr('onTeam');
            dataPost += "&";
            dataPost += "value";
            dataPost += "=";
            dataPost += node.value;
        }
    });

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataPost,
        dataType: "json",


        success: function (data, textStatus, jqXHR) {
            if (data.success === false) {
                if (data.dataEmpty  !== undefined) {
                    document.getElementById("dataEmpty").style.display = 'inherit';
                }
                if (data.littleMoney  !== undefined) {
                    document.getElementById("littleMoney").style.display = 'inherit';
                }
                if (data.wrongCompetition  !== undefined) {
                    document.getElementById("wrongCompetition").style.display = 'inherit';
                }
                if (data.wrongCreation  !== undefined) {
                    document.getElementById("wrongCreation").style.display = 'inherit';
                }
                if (data.wrongCash  !== undefined) {
                    document.getElementById("wrongCash").style.display = 'inherit';
                }
                if (data.accessDenied  !== undefined) {
                    document.getElementById("accessDenied").style.display = 'inherit';
                }

            } else {
                document.getElementById("modalBetSuccess").style.display = 'inherit';
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("ServerError").style.display = 'inherit';

        },
        beforeSend: function (jqXHR, settings) {
        },
        complete: function (jqXHR, textStatus) {
        }
    });
}

