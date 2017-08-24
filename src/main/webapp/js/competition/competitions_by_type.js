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
