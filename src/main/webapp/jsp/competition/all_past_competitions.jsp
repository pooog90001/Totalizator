<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.results" var="txtResults"/>

<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-small">&cross;</span>
        <br>
    </div>
    <%@include file="/jsp/bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="/jsp/bar/left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                ${txtResults}
            </div>
            <div class="w3-container">
                <%@include file="/jsp/competition/part/past_competition.jsp"%>
            </div>
            <div class="pagination  w3-margin-top w3-padding-small">
                <ctg:pagination total="${gamesCount}" limit="${limit}" command="OPEN_ALL_PAST_COMPETITIONS"/>
            </div>
        </div>
    </div>
</div>

</body>

<%@include file="/jsp/partial/footer.jsp" %>



