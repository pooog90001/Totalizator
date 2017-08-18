function deactivatedEdit(e, id) {
    var numberNames = ['lessTotalCoeff', 'moreTotalCoeff', 'standoffCoeff', 'competitorCoeff', 'total'];
    var stringNames = ['competitionName'];
    var $this = $(e);
    var $contanier = $("#" + id);

    if ($this.attr('editing') != '1') {
        $this.attr('editing', 1);

        $this.html("");
        $this.html("<i class='fa fa-save'></i>");

        for (var i = 0; i < numberNames.length; i++) {
            $contanier.find("[name*='" + numberNames[i] + "']").each(function () {
                var value = $(this).text().toString().trim();
                var $input = $('<input type="number" name= "' + numberNames[i] + '" step="0.01"  min="1" max="20000" onkeypress="return false;">').val(value);
                $input.attr('oldCoeff', value);
                $(this).replaceWith($input);
            });
        }

        for (var i = 0; i < stringNames.length; i++) {
            $contanier.find("[name*='" + stringNames[i] + "']").each(function () {
                var value = $(this).text().toString().trim();
                var $input = $('<input type="text" name= "' + stringNames[i] + '">').val(value);
                $input.attr('oldCoeff', value);
                $(this).replaceWith($input);
            });
        }
    }
    else {
        saveDeactivated(e, id, numberNames, stringNames);
    }
}

function saveDeactivated(e, id , numberNames, stringNames) {
    var paramNames = ['lessTotalCoeff', 'moreTotalCoeff', 'standoffCoeff', 'total', 'competitorCoeff', 'competitorId', 'competitionId', 'competitionName'];
    var dataString = 'command=EDIT_UPCOMING_DEACTIVATED_COMPETITION';
    var $container = $("#" + id);
    var $this = $(e);

    for (var i = 0; i < paramNames.length; i++) {
        $container.find("[name*='" + paramNames[i] + "']").each(function () {
            dataString += '&';
            dataString += paramNames[i];
            dataString += '=';
            dataString += $(this).val().toString().trim();
        });
    }

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {

            $this.removeAttr('editing');
            $this.html("");
            $this.html("<i class='fa fa-edit'></i>");

            if (data.success === true) {
                updateValues($container, numberNames, stringNames);

            } else {
                console.log("Something really bad happened " + textStatus);
                returnOldValues($container, numberNames, stringNames);

                modal_edit_wrong.style.display = 'block';
            }
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $this.removeAttr('editing');
            $this.html("");
            $this.html("<i class='fa fa-edit'></i>");

            returnOldValues($container, numberNames, stringNames);

            modal_edit_error.style.display = 'block';
        },

        beforeSend: function (jqXHR, settings) {},

        complete: function (jqXHR, textStatus) {}
    });

}

function returnOldValues($container, numberNames, stringNames) {
    for (var i = 0; i < numberNames.length; i++) {
        $container.find("[name*='" + numberNames[i] + "']").each(function () {
            var oldValue = $(this).attr("oldCoeff");
            var span = $('<span name="' + numberNames[i] + '" />').text(oldValue);
            $(this).replaceWith(span);
        });
    }

    for (var i = 0; i < stringNames.length; i++) {
        $container.find("[name*='" + stringNames[i] + "']").each(function () {
            var oldValue = $(this).attr("oldCoeff");
            var span = $('<span name="' + stringNames[i] + '" />').text(oldValue);
            $(this).replaceWith(span);
        });
    }
}

function updateValues($container, numberNames, stringNames) {
    for (var i = 0; i < numberNames.length; i++) {
        $container.find("[name*='" + numberNames[i] + "']").each(function () {
            var newValue = $(this).val().toString().trim();
            var span = $('<span name="' + numberNames[i] + '" />').text(newValue);
            $(this).replaceWith(span);

        });
    }

    for (var i = 0; i < stringNames.length; i++) {
        $container.find("[name*='" + stringNames[i] + "']").each(function () {
            var newValue = $(this).val().toString().trim();
            var span = $('<span name="' + stringNames[i] + '" />').text(newValue);
            $(this).replaceWith(span);

        });
    }
}


