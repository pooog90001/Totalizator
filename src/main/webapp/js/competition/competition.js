
document.getElementById('create').onclick = function (e) {
    var createField = document.getElementById('createField');
    var createBtn = document.getElementById('create');

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

selectKind.onchange = function (e) {
    var selectedOption = selectKind.options[selectKind.selectedIndex];
    var count = selectedOption.getAttribute("count");
    var innerHtml = "";

    if (count == 2) {
        innerHtml += "<div class='w3-row'>";
        innerHtml += "<div class='w3-col s3'>";
        innerHtml += "<div class='w3-col s2 w3-padding'>T:</div>";
        innerHtml += "<div class='w3-col s10'> ";
        innerHtml += "<input class='w3-input w3' id='total' type='number' name='total' step='0.01' required value='' min='1' max='20000'>";
        innerHtml += "</div>";
        innerHtml += "</div>";
        innerHtml += "<div class='w3-col s3'>";
        innerHtml += "<div class='w3-col s2 w3-padding'>L:</div>";
        innerHtml += "<div class='w3-col s10'>";
        innerHtml += "<input class='w3-input w3' id='lessTotalCoeff' type='number' value='1.00' name='lessTotalCoeff' step='0.01' required value='' min='1' max='20000'>";
        innerHtml += "</div>";
        innerHtml += "</div>";
        innerHtml += "<div class='w3-col s3'>";
        innerHtml += "<div class='w3-col s2 w3-padding'>M:</div>";
        innerHtml += "<div class='w3-col s10'>";
        innerHtml += "<input class='w3-input w3' id='moreTotalCoeff' type='number' value='1.00' name='moreTotalCoeff' step='0.01' required value='' min='1' max='20000'>";
        innerHtml += "</div>";
        innerHtml += "</div>";
        innerHtml += "<div class='w3-col s3'>";
        innerHtml += "<div class='w3-col s2 w3-padding'>X:</div>";
        innerHtml += "<div class='w3-col s10'>";
        innerHtml += "<input class='w3-input w3' id='standoffCoeff' type='number' value='1.00' name='standoffCoeff' step='0.01' required value='' min='1' max='20000'>";
        innerHtml += "</div>";
        innerHtml += "</div>";
        innerHtml += "</div>";
    }

    var dataString = "kindOfSportId=" + selectedOption.value + "&command=find_teams";

    var teamOptions = "";
    //make the AJAX request, dataType is set to json
    //meaning we are expecting JSON data in response from the server
    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            teamOptions += "<option value='' selected disabled > Choose team </option>";

            for (var i = 0; i < data.teams.length; i++) {
                teamOptions += "<option required value='" + data.teams[i].id + "'><pre>" + data.teams[i].name + "</pre></option>";
            }
        },

        //If there was no resonse from the server
        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
        },

        //capture the request before it was sent to server
        beforeSend: function (jqXHR, settings) {
        },

        //this is called after the response or error functions are finsihed
        //so that we can take some action
        complete: function (jqXHR, textStatus) {
            for (var i = 0; i < count; i++) {
                innerHtml += "<div class='w3-row'>";
                innerHtml += "<div class='w3-col s6'>";
                innerHtml += "<div class='w3-col s4 w3-padding'>Teams:</div>";
                innerHtml += "<div class='w3-col s8'>";
                innerHtml += "<select id='teamName' required name='teamId' class='w3-select'>";
                innerHtml += teamOptions;
                innerHtml += "</select>";
                innerHtml += "</div>";
                innerHtml += "</div>";
                innerHtml += "<div class='w3-col s6'>";
                innerHtml += "<div class='w3-col s8 w3-padding'>Win coefficient:</div>";
                innerHtml += "<div class='w3-col s4'>";
                innerHtml += "<input class='w3-input w3' type='number' name='competitorCoeff' required value='1.00' step='0.01' min='1' max='20000'>";
                innerHtml += "</div>";
                innerHtml += "</div>";
                innerHtml += "</div>";
            }
            createAdd.innerHTML = innerHtml;
        }
    });

};
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

function checkBeforeCreate(element) {
    document.getElementById("wrongJS").style.display = 'none';
    var coeffs = document.getElementsByName("competitorCoeff");
    var teams = document.getElementsByName("teamId");
    var total = document.getElementById("total");
    var lessTotalCoeff = document.getElementById("lessTotalCoeff");
    var moreTotalCoeff = document.getElementById("moreTotalCoeff");
    var standoffCoeff = document.getElementById("standoffCoeff");
    var isActive = document.getElementById("isActive");
    var isValid = true;

    for (var r = 0; r < teams.length; r++) {
        for (var j = 0; j < teams.length; j++) {
            if (r !== j) {
                if (teams[j].value === teams[r].value) {
                    isValid = false;
                }
            }
        }
    }

    for (var i = 0; i < coeffs.length; i++) {
        if (coeffs[i].value === '') {
            isValid = false;
        }
    }

    if (total !== null) {
        if (total.value === '') {
            isValid = false;
        }
    }
    if (lessTotalCoeff !== null) {
        if (lessTotalCoeff.value === '') {
            isValid = false;
        }
    }
    if (moreTotalCoeff !== null) {
        if (moreTotalCoeff.value === '') {
            isValid = false;
        }
    }
    if (standoffCoeff !== null) {
        if (standoffCoeff.value === '') {
            isValid = false;
        }
    }

    if (!isValid) {
        document.getElementById("wrongJS").style.display = 'block';
    }

    return isValid;

}


window.onload = function (e) {
    var isActve = document.getElementById('isActive');

    if (isActve !== null) {
        document.getElementById('isActive').onchange = checkForActive(e);
    }
};

checkForActive = function (e) {
    var isValid = true;
    var competitorsCoeff = document.getElementsByTagName('competitorCoeff');
    var total = document.getElementById('total');
    var lessTotalCoeff = document.getElementById('lessTotalCoeff');
    var moreTotalCoeff = document.getElementById('moreTotalCoeff');
    var standoffCoeff = document.getElementById('standoffCoeff');
    var errorBox = document.getElementById('errorBox');
    var competitionType = document.getElementById('competitionType');
    var kindOfSport = document.getElementById('selectKind');
    var competitionName = document.getElementById('competitionName');
    var dateStart = document.getElementById('dateStart');
    var timeStart = document.getElementById('timeStart');
    var dateFinish = document.getElementById('dateFinish');
    var timeFinish = document.getElementById('timeFinish');
    var isActve = document.getElementById('isActive');
    var teamsId = document.getElementsByTagName('teamId');

    errorBox.style.display = "none";

    if (total !== null) {
        if (total.value === '' ||
                lessTotalCoeff.value === '' ||
                moreTotalCoeff.value === '' ||
                standoffCoeff.value === '' ) {
            isValid = false;
        }
    }

    if (competitionType.value === '') {
        isValid = false;

    } else if (kindOfSport.value === '') {
        isValid = false;

    } else if (dateStart.value === '') {
        isValid = false;

    } else if (dateFinish.value === '') {
        isValid = false;

    } else if (timeStart.value === '') {
        isValid = false;

    } else if (timeFinish.value === '') {
        isValid = false;

    } else if (!isTeamsNameValid(teamsId)) {
        isValid = false;
    }

    if (!isCompetitorsCoeffValid(competitorsCoeff)) {
        isValid = false;
    }

    if (!isValid) {
        if (isActve.checked) {
            isActve.checked = false;
           errorBox.style.display = "inherit";
        }
    }

};

function isTeamsNameValid(teamsId) {
    for (var i = 0; i < teamsId.size; i++) {
        if (teamsId[i].value === '') {
            return false;
        }
    }
    return true;
}

function isCompetitorsCoeffValid(competitorsCoeff) {
    for (var i = 0; i < competitorsCoeff.size; i++) {
        if (competitorsCoeff[i].value === '') {
            return false;
        }
    }
    return true;
}

function delFilled(e, id) {

    var $this = $(e);
    var $container = $("#" + id);
    var dataString = "&command=delete_filled_competition";
    var paramName = 'competitionId';

    $container.find("[name*='"+paramName+"']").each(function () {
        dataString += '&';
        dataString += paramName;
        dataString += '=';
        dataString += $(this).val().toString().trim();
    });

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            if (data.success === true) {
                console.log("Something really bad happened " + textStatus);
                $container.css("display", "none");
            } else {
                modal_del_wrong.style.display = 'block';
            }
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            modal_del_error.style.display = 'block';
        },

        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}

function delUnfilled(e, id) {

    var $this = $(e);
    var $container = $("#" + id);
    var dataString = "&command=delete_unfilled_competition";
    var paramName = 'competitionId';

    $container.find("[name*='"+paramName+"']").each(function () {
        dataString += '&';
        dataString += paramName;
        dataString += '=';
        dataString += $(this).val().toString().trim();
    });

    $.ajax({
        type: "POST",
        url: "/ajaxController",
        data: dataString,
        dataType: "json",

        success: function (data, textStatus, jqXHR) {
            if (data.success === true) {
                console.log("Something really bad happened " + textStatus);
                $container.css("display", "none");

            } else {
                modal_del_wrong.style.display = 'block';
            }
        },

        error: function (jqXHR, textStatus, errorThrown) {
            console.log("Something really bad happened " + textStatus);
            modal_del_error.style.display = 'block';
        },

        beforeSend: function (jqXHR, settings) {},
        complete: function (jqXHR, textStatus) {}
    });
}
