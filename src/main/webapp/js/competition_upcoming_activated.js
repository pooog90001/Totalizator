function activeEdit(e, id) {
    var names = ['lessTotalCoeff', 'moreTotalCoeff', 'standoffCoeff', 'competitorCoeff'];
    var $this = $(e);
    var $contanier = $("#" + id);

    if ($this.attr('editing') != '1') {
        $this.attr('editing', 1);

        $this.html("");
        $this.html("<i class='fa fa-save'></i>");

        for (var i = 0; i < names.length; i++) {
            $contanier.find("[name*='" + names[i] + "']").each(function () {
                var value = $(this).text().toString().trim();
                var $input = $('<input type="number" name= "' + names[i] + '" step="0.01"  min="1" max="20000" onkeypress="return false;">').val(value);
                $input.attr('oldCoeff', value);
                $(this).replaceWith($input);
            });
        }
    }
    else {
        saveActive(e, id, names);
    }
}

function saveActive(e, id , names) {
    var paramNames = ['lessTotalCoeff', 'moreTotalCoeff', 'standoffCoeff', 'competitorCoeff', 'competitorId', 'competitionId'];
    var dataString = 'command=EDIT_UPCOMING_ACTIVATED_COMPETITION';
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
                for (var i = 0; i < names.length; i++) {
                    $container.find("[name*='" + names[i] + "']").each(function () {
                        var newValue = $(this).val().toString().trim();
                        var span = $('<span name="' + names[i] + '" />').text(newValue);
                        $(this).replaceWith(span);

                    });
                }

            } else {
                console.log("Something really bad happened " + textStatus);

                for (var i = 0; i < names.length; i++) {
                    $container.find("[name*='" + names[i] + "']").each(function () {
                        var oldValue = $(this).attr("oldCoeff");
                        var span = $('<span name="' + names[i] + '" />').text(oldValue);
                        $(this).replaceWith(span);
                    });
                }
                modal_edit_wrong.style.display = 'block';
            }
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $this.removeAttr('editing');
            $this.html("");
            $this.html("<i class='fa fa-edit'></i>");

            for (var i = 0; i < names.length; i++) {
                $container.find("[name*='" + names[i] + "']").each(function () {
                    var oldValue = $(this).attr("oldCoeff");
                    var span = $('<span name="' + names[i] + '" />').text(oldValue);
                    $(this).replaceWith(span);
                });
            }
            modal_edit_error.style.display = 'block';
        },

        beforeSend: function (jqXHR, settings) {},

        complete: function (jqXHR, textStatus) {}
    });

}



