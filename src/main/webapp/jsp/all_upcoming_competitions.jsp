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
                Upcoming
            </div>
            <div class="container">
                <div class="w3-card-2">
                    <form class="w3-container w3-card-4" action="${pageContext.request.contextPath}/generalController">
                        <input type="hidden" name="command" value="open_all_live_competitions">
                        <label>Do filter by kind of sport
                            <div class="w3-row">
                                <div class="w3-half">
                                    <select class="w3-select w3-border" name="kindOfSportId">
                                        <c:choose>
                                            <c:when test="${currentId == 0}">
                                                <option value="0" selected>All</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="0">All</option>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:forEach var="kindOfSport" items="${kindsOfSportList}">
                                            <c:choose>
                                                <c:when test="${currentId == kindOfSport.id}">
                                                    <option value="${kindOfSport.id}"
                                                            selected>${kindOfSport.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${kindOfSport.id}">${kindOfSport.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>

                                </div>
                                <div class="w3-half">
                                    <button class="w3-button w3-theme" type="submit">Filter</button>
                                </div>
                            </div>
                        </label>
                    </form>
                </div>
            </div>
            <div class="w3-container">
                <%@include file="partial/upcoming_competiton.jsp"%>
            </div>
        </div>
    </div>
</div>

</body>

<%@include file="partial/footer.jsp" %>




