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

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                    Live
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

            <div class=" w3-container w3-padding-small w3-card-2 w3-center">
                <div class="w3-col s12 w3-container w3-padding-small">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round w3-medium">
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">#</div>
                            </div>
                            <div class="w3-col s2 w3-padding-small">
                                <div class="w3-center">kind of sport</div>
                            </div>
                            <div class="w3-col s3 w3-padding-small">
                                <div class="w3-center">command</div>
                            </div>
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">T</div>
                            </div>
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">L</div>
                            </div>
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">M</div>
                            </div>
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">W1</div>
                            </div>
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">X</div>
                            </div>
                            <div class="w3-col s1 w3-padding-small">
                                <div class="w3-center">W2</div>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
                <div id="ajaxResponse">
                    <c:forEach var="liveGame" items="${requestScope.get('liveGames')}">
                        <div class="w3-col s12 w3-container w3-padding-small">
                            <div class="w3-row w3-hover-light-grey">
                                <div class="w3-card-2 w3-white w3-round w3-small">
                                    <div class="w3-col s1 w3-padding-small">
                                        <div class="w3-center">
                                                ${liveGame['competition_id']}
                                        </div>
                                    </div>
                                    <div class="w3-col s2 w3-padding-small">
                                        <div class="w3-center">
                                                ${liveGame['kind_of_sport_name']}
                                        </div>
                                    </div>
                                    <div class="w3-col s3 w3-padding-small">
                                        <div class="w3-center">
                                                ${liveGame['command_name'][0]}
                                            - ${liveGame['command_name'][1]}
                                        </div>
                                    </div>
                                    <div class="w3-col s1 w3-padding-small">
                                        <div class="w3-center">
                                            <ctg:decimal-presenter number="${liveGame['competition_total']}"/>
                                        </div>
                                    </div>
                                    <div class="w3-col s1">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <input type="hidden" name="command" value="open_do_bet_page">
                                            <input type="hidden" name="competitionId"
                                                   value="${liveGame['competition_id']}">
                                            <button type="submit" class="w3-center w3-button w3-small w3-padding-small">
                                                <ctg:decimal-presenter
                                                        number="${liveGame['competition_less_total_coeff']}"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="w3-col s1">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <input type="hidden" name="command" value="open_do_bet_page">
                                            <input type="hidden" name="competitionId"
                                                   value="${liveGame['competition_id']}">
                                            <button type="submit" class="w3-center w3-button w3-small w3-padding-small">
                                                <ctg:decimal-presenter
                                                        number="${liveGame['competition_more_total_coeff']}"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="w3-col s1">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <input type="hidden" name="command" value="open_do_bet_page">
                                            <input type="hidden" name="competitorId"
                                                   value="${liveGame['competitor_id'][0]}">
                                            <button type="submit" class="w3-center w3-button w3-small w3-padding-small">
                                                <ctg:decimal-presenter number="${liveGame['competitor_win_coeff'][0]}"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="w3-col s1">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <input type="hidden" name="command" value="open_do_bet_page">
                                            <input type="hidden" name="competitionId"
                                                   value="${liveGame['competition_id']}">
                                            <button type="submit" class="w3-center w3-button w3-small w3-padding-small">
                                                <ctg:decimal-presenter
                                                        number="${liveGame['competition_standoff_coeff']}"/>
                                            </button>
                                        </form>
                                    </div>
                                    <div class="w3-col s1">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <input type="hidden" name="command" value="open_do_bet_page">
                                            <input type="hidden" name="competitorId"
                                                   value="${liveGame['competitor_id'][1]}">
                                            <button type="submit" class="w3-center w3-button w3-small w3-padding-small">
                                                <ctg:decimal-presenter number="${liveGame['competitor_win_coeff'][1]}"/>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<%@include file="partial/footer.jsp" %>


