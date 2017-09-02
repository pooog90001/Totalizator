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
        callImgAreaSelect();
    });

    $("#blah").load(function(){
        callImgAreaSelect();
    });

    window.onresize = function(){
        callImgAreaSelect();
    };

    function callImgAreaSelect() {
        $('#blah').imgAreaSelect({
            aspectRatio: '2:1',
            x1: 0, y1: 0, x2: 100, y2: 50,
            imageWidth : $('#blah').width(),
            imageHeight : $('#blah').height(),
            movable: true,
            resizable: true,
            onSelectEnd: imgCrop,
            onInit: imgCrop,
            onSelectChange: imgCrop
        });
    }


} );




function imgCrop(img, selection) {
    $('#width').val($('#blah').width());
    $('#height').val($('#blah').height());
    $('#x1').val(selection.x1);
    $('#x2').val(selection.x2);
    $('#y1').val(selection.y1);
    $('#y2').val(selection.y2);
}


$('#form').submit(function (e) {
    e.preventDefault();
     $('#form').validate();

    if (!$('#form').valid()) {
        return;
    }

    var dataPost = new FormData($('#form')[0]);
    var $this = $(e);

    $.ajax({
        type: "POST",
        url: "/uploadController",
        data: dataPost,
        processData: false,
        contentType: false,
        cache: false,

        success: function (data, textStatus, jqXHR) {
            var objData = JSON.parse(data);
            if (objData.success === false) {
                if (objData.wrongData !== undefined) {
                    document.getElementById("wrongData").style.display = 'inherit';
                } else if (objData.accessDenied !== undefined) {
                    document.getElementById("accessDenied").style.display = 'inherit';
                } else if (objData.wrongUpload !== undefined) {
                    document.getElementById("wrongUpload").style.display = 'inherit';
                } else {
                    document.getElementById("wrongDB").style.display = 'inherit';
                }
            } else {
                window.location.reload();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            document.getElementById("errorResponse").style.display = 'inherit';
        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
});

function delNews(e, id, newsImageUrl) {
    modal_del_error.style.display = 'none';
    modal_del_error.style.display = 'none';

    var $this = $(e);
    var $contanier = $("#" + id);
    var dataString = "newsId=" + id + "&newsImageUrl="+ newsImageUrl +"&command=delete_news";

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            if (data.success === true) {
                $contanier.css("display","none");
            } else {
                console.log("Something really bad happened " + textStatus);
                modal_del_wrong.style.display = 'block';
            }
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);

            $contanier.css("display","inherit");
            modal_del_error.style.display = 'block';

        },
        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}


document.getElementById('create').onclick = function (e) {
    var createField =  document.getElementById('createField');
    var createBtn =  document.getElementById('create');

    if (createField.style.display === 'inherit') {
        $('#blah').imgAreaSelect({hide : true});
        createField.style.display = 'none';
        createBtn.innerHTML = "<i class='fa fa-plus-circle'></i>";
    } else {
        createField.style.display = 'inherit';
        createBtn.innerHTML = "<i class='fa fa-times-circle'></i>";

    }
};

