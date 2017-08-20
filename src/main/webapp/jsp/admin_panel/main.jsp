<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="../bar/admin_left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <!-- Accordion -->
        <%@include file="../bar/admin_left_bar.jsp" %>
        <!-- End Left Column -->
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                Main admin panel
            </div>
        </div>
        <div class="w3-row-padding w3-container w3-margin-bottom">
            <div class="w3-col s12 w3-card-2 w3-large">
                <p>
                    <i>Count registered people:</i>
                    <b class="w3-xlarge">${statisticMap.countRegistered}</b>
                </p>
                <hr>
                <p>
                    <i>Count confirmed people:</i>
                    <b class="w3-xlarge" >${statisticMap.countConfirmed}</b>
                </p>
                <hr>
                <p>
                    <i>Count locked people:</i>
                    <b class="w3-xlarge" >${statisticMap.countLocked}</b>
                </p>
                <hr>
                <p>
                    <i>Count news:</i>
                    <b class="w3-xlarge" >${statisticMap.countNews}</b>
                </p>
                <hr>
                <p>
                    <i>Count kinds of sport:</i>
                    <b class="w3-xlarge" >${statisticMap.countSports}</b>
                </p>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="../partial/footer.jsp" %>
