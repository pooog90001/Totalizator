function checkBeforeFillResults(elementId) {
    var element  = document.getElementById(elementId);
    var results = element.getElementsByName("competitorResult");
    var isValid = true;

    for (var i = 0; i < results.length; i++) {
        if (!results[i].valid) {
            isValid = false;
        }
    }

    if (results.length === 2) {
        for (var i = 0; i < results.length; i++) {
            if (results[i].value < 0 || results[i].value > 20000) {
                isValid = false;
            }
        }

    } else {
        for (var i = 0; i < results.length; i++) {
            for (var j = 0; j < results.length; j++) {
                if (i !== j) {
                    if (results[i].value === results[j].value ||
                        results[j].value > results.length ||
                        results[j] < 1) {
                        isValid = false;
                    }
                }
            }
        }
    }

    if (!isValid) {
        document.getElementById('modal_fill_warning').style.display = 'block';
    }

    return isValid;
}


