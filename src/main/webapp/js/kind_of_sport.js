

function edit(e, id) {
    var $this = $(e);
    var $contanier = $("#" + id);

    if ($this.attr('editing') != '1') {
        $this.attr('editing', 1);

        $this.html("");
        $this.html("<i class='fa fa-save'></i>");
        $contanier.find('.name').each(function () {
            var value = $(this).text().toString().trim();
            $this.attr('oldName', value);
            var input = $('<input class="name" />').val(value);
            $(this).replaceWith(input);
        });
    }
    else {
        $this.removeAttr('editing');
        $this.html("");
        $this.html("<i class='fa fa-edit'></i>");
        $contanier.find('.name').each(function () {
            var name = $(this).val().toString().trim();
            var div = $('<div class="name" />').text(name);
            $(this).replaceWith(div);
            save(e, id, name);
        });
    }
}

function save(e, id, name) {

    //get the form data using another method
    var newName = $("input#countryCode").val();
    var dataString = "kindOfSportId=" + id + "&newName="+ name + "&command=update_kind_of_sport";

    var $this = $(e);
    var $contanier = $("#" + id);
    //make the AJAX request, dataType is set to json
    //meaning we are expecting JSON data in response from the server
    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            if (data.success === false) {
                console.log("Something really bad happened " + textStatus);

                $contanier.find('.name').each(function () {
                    $(this).text($this.attr("oldName"));
                });
                modal_edit_wrong.style.display = 'block';
            }
        },

        //If there was no resonse from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $contanier.find('.name').each(function () {
                $(this).text($this.attr("oldName"));
            });
            modal_edit_error.style.display = 'block';
        },

        //capture the request before it was sent to server
        beforeSend: function (jqXHR, settings) {},

        //this is called after the response or error functions are finsihed
        //so that we can take some action
        complete: function (jqXHR, textStatus) {}
    });
}

function del(e, id) {

    var $this = $(e);
    var $contanier = $("#" + id);
    var $modal = $("#modal" + id);

    $modal.css("display","none");
    $contanier.css("display","none");
    //get the form data using another method
    var dataString = "kindOfSportId=" + id + "&command=delete_kind_of_sport";
    //make the AJAX request, dataType is set to json
    //meaning we are expecting JSON data in response from the server
    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            if (data.success === false) {
                console.log("Something really bad happened " + textStatus);

                $contanier.css("display","inherit");
                modal_del_wrong.style.display = 'block';
            }
        },

        //If there was no resonse from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $contanier.css("display","inherit");
            modal_del_error.style.display = 'block';

        },

        //capture the request before it was sent to server
        beforeSend: function (jqXHR, settings) {},

        //this is called after the response or error functions are finsihed
        //so that we can take some action
        complete: function (jqXHR, textStatus) {}
    });
}

document.getElementById('create').onclick = function (e) {
   var createField =  document.getElementById('createField');
   var createBtn =  document.getElementById('create');

   if (createField.style.display === 'inherit') {
       createField.style.display = 'none';
       createBtn.innerHTML = "<i class='fa fa-plus-circle'></i>";
   } else {
       createField.style.display = 'inherit';
       createBtn.innerHTML = "<i class='fa fa-times-circle'></i>";

   }
};

if (document.getElementById('wrong') !== null) {
    var event = new Event("click");
    create.dispatchEvent(event);
}

