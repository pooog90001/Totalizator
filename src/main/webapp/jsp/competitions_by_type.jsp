<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-small">&cross;</span>
        <br>
    </div>
    <%@include file="bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="bar/left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                ${kindOfSport.name} ${competitionType.name}
            </div>
            <div class="w3-container">
                <div class="w3-bar w3-black w3-card-2 w3-margin-bottom">
                    <button class="w3-bar-item w3-button generalLink w3-white"
                            onclick="openTab(event,'Upcoming', 'general', 'generalLink')">
                        Upcoming
                    </button>
                    <button class="w3-bar-item w3-button generalLink"
                            onclick="openTab(event,'Past', 'general', 'generalLink')">
                        Results
                    </button>

                </div>

                <div id="Upcoming" class="general">
                    <%@include file="/jsp/partial/upcoming_competition.jsp" %>
                </div>

                <div id="Past" class="general" style="display:none">
                    <%@include file="/jsp/partial/past_competition.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/competitions_by_type.js"></script>

<%@include file="partial/footer.jsp" %>


