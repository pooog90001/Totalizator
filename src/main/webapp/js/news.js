$( function () {

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
            };
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#imgInp").change(function(){
        readURL(this);

        $('#blah').imgAreaSelect({
            aspectRatio: '4:3',
            x1: 0, y1: 0, x2: 120, y2: 90,
            movable: true,
            resizable: true,
            onSelectEnd: imgCrop,
            onInit: imgCrop,
            onSelectChange: imgCrop, // после выделения объекта срабатывает ф-ия imgCrop()
        });
    });

} );

$(document).ready(function () {
    $('#blah').imgAreaSelect({
        aspectRatio: '4:3',
        x1: 0, y1: 0, x2: 120, y2: 90,
        movable: true,
        resizable: true,
        onSelectEnd: imgCrop // после выделения объекта срабатывает ф-ия imgCrop()
    });
});


function imgCrop(img, selection) {
    $('#width').val($('#blah').width());
    $('#height').val($('#blah').height());
    $('#x1').val(selection.x1);
    $('#x2').val(selection.x2);
    $('#y1').val(selection.y1);
    $('#y2').val(selection.y2);
}


function save(e, id, name) {

    //get the form data using another method
    var newName = $("input#countryCode").val();
    var dataString = "kindOfSportId=" + id + "&newName="+ name + "&command=create_news";

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