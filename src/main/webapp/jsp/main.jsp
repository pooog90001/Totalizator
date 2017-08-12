<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;" >
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <!-- Accordion -->
        <%@include file="bar/left_bar.jsp" %>
        <!-- End Left Column -->
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <form class="w3-container" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value= "open_all_news">
                <button type="submit" class="w3-xlarge w3-button w3-hover-none">
                    Новости
                </button>
            </form>

            <c:forEach var="news" items="${requestScope.get('newsList')}">
                <div class="w3-third w3-container">
                    <div class="w3-card-2 w3-round">
                        <div class="w3-display-container">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSphHHzJNUkLhSlHBDw2EUiPOmwedQt44L5GQ8d0yFjA4_BGPTm"
                                 alt="${news.title}" style="width: 100%;">
                            <div class="w3-display-topright w3-display-hover">
                                <p class="w3-black w3-padding">${news.dateCreation}</p>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/generalController">
                            <input type="hidden" name="command" value= "open_concrete_news">
                            <input type="hidden" name="newsId" value="${news.id}">
                            <button type="submit" class="w3-hover-none w3-button w3-hover-text-gray">
                                <b> ${news.title} </b>
                            </button>
                            <p class="w3-small">${news.text}</p>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <form class="w3-container w3-margin-bottom" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value= "open_all_news">
                <button type="submit" class="w3-right w3-button w3-small">
                    Все новости
                </button>
            </form>

        </div>

<%--Start upcoming comeptitions section--%>
        <div class="w3-row-padding">
            <form class="w3-container" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value="open_upcoming_competition_page">
                <button type="submit" class="w3-xlarge w3-button w3-hover-none">
                    ${upcoming}
                </button>
            </form>
            <div class=" w3-container w3-padding-small w3-card-2 w3-center">
                <div class="w3-col s12 w3-container w3-padding-small">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round w3-medium">
                            <div class="w3-col s1">#</div>
                            <div class="w3-col s1">Date</div>
                            <div class="w3-col s2">kind of sport</div>
                            <div class="w3-col s2">command</div>
                            <div class="w3-col s1">T</div>
                            <div class="w3-col s1">L</div>
                            <div class="w3-col s1">M</div>
                            <div class="w3-col s1">W1</div>
                            <div class="w3-col s1">X</div>
                            <div class="w3-col s1">W2</div>
                        </div>
                    </div>
                    <hr>
                </div>
                <c:forEach var="upcomingGame" items="${requestScope.get('upcomingGames')}">
                    <div class="w3-col s12 w3-container w3-padding-small">
                        <div class="w3-row w3-hover-light-grey">
                            <div class="w3-card-2 w3-white w3-round w3-small">
                                <div class="w3-col s1">${upcomingGame['competition_id']}</div>
                                <div class="w3-col s1">${upcomingGame['competition_date_start']}</div>
                                <div class="w3-col s2">${upcomingGame['kind_of_sport_name']}</div>
                                <div class="w3-col s3">${upcomingGame['command_name'][0]}
                                    - ${upcomingGame['command_name'][1]}</div>
                                <div class="w3-col s1">
                                    <ctg:decimal-presenter number="${upcomingGame['competition_total']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <form action="${pageContext.request.contextPath}/generalController">
                                        <input type="hidden" name="command" value="open_do_bet_page">
                                        <input type="hidden" name="competitionId"
                                               value="${upcomingGame['competition_id']}">
                                        <button type="submit" class="w3-right w3-button w3-small">
                                            <ctg:decimal-presenter number="${upcomingGame['competition_standoff_coeff']}"/>
                                        </button>
                                    </form>
                                    <ctg:decimal-presenter number="${upcomingGame['competition_less_total_coeff']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <form action="${pageContext.request.contextPath}/generalController">
                                        <input type="hidden" name="command" value="open_do_bet_page">
                                        <input type="hidden" name="competitionId"
                                               value="${upcomingGame['competition_id']}">
                                        <button type="submit" class="w3-right w3-button w3-small">
                                            <ctg:decimal-presenter number="${upcomingGame['competition_standoff_coeff']}"/>
                                        </button>
                                    </form>
                                    <ctg:decimal-presenter number="${upcomingGame['competition_more_total_coeff']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <form class="w3-container w3-margin-bottom"
                                          action="${pageContext.request.contextPath}/generalController">
                                        <input type="hidden" name="command" value="open_do_bet_page">
                                        <input type="hidden" name="competitorId"
                                               value="${upcomingGame['competitor_id'][0]}">
                                        <button type="submit" class="w3-right w3-button w3-small">
                                            <ctg:decimal-presenter number="${upcomingGame['competitor_win_coeff'][0]}"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="w3-col s1">
                                    <form action="${pageContext.request.contextPath}/generalController">
                                        <input type="hidden" name="command" value="open_do_bet_page">
                                        <input type="hidden" name="competitionId"
                                               value="${upcomingGame['competition_id']}">
                                        <button type="submit" class="w3-right w3-button w3-small">
                                            <ctg:decimal-presenter
                                                    number="${upcomingGame['competition_standoff_coeff']}"/>
                                        </button>
                                    </form>
                                </div>
                                <div class="w3-col s1">
                                    <form action="${pageContext.request.contextPath}/generalController">
                                        <input type="hidden" name="command" value="open_do_bet_page">
                                        <input type="hidden" name="competitorId"
                                               value="${upcomingGame['competitor_id'][1]}">
                                        <button type="submit" class="w3-right w3-button w3-small">
                                            <ctg:decimal-presenter number="${upcomingGame['competitor_win_coeff'][1]}"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <form class="w3-container w3-margin-bottom" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value="open_all_upcoming_competition_page">
                <button type="submit" class="w3-right w3-button w3-small">
                    Все ${upcoming}
                </button>
            </form>
        </div>
        <%--End upcoming comeptitions section--%>

        <%--Start past comeptitions section--%>
        <div class="w3-row-padding">
            <form class="w3-container" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value="open_upcoming_competition_page">
                <button type="submit" class="w3-xlarge w3-button w3-hover-none">
                    ${past}
                </button>
            </form>
            <div class=" w3-container w3-padding-small w3-card-2 w3-center">
                <div class="w3-col s12 w3-container w3-padding-small">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round w3-medium">
                            <div class="w3-col s1">#</div>
                            <div class="w3-col s2">Date</div>
                            <div class="w3-col s3">Kind of sport</div>
                            <div class="w3-col s4">Command</div>
                            <div class="w3-col s2">Result</div>
                        </div>
                    </div>
                    <hr>
                </div>
                <c:forEach var="pastGame" items="${requestScope.get('pastGames')}">
                <div class="w3-col s12 w3-container w3-padding-small">
                    <div class="w3-row w3-hover-light-grey">
                        <div class="w3-card-2 w3-white w3-round w3-small">
                            <div class="w3-col s1">${pastGame['competition_id']}</div>
                            <div class="w3-col s2">${pastGame['competition_date_start']}</div>
                            <div class="w3-col s3">${pastGame['kind_of_sport_name']}</div>
                            <div class="w3-col s4">${pastGame['command_name'][0]} - ${pastGame['command_name'][1]}</div>
                            <div class="w3-col s2">
                                <ctg:decimal-presenter number="${pastGame['competition_total']}"/>
                                </divpastGame
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                <form class="w3-container w3-margin-bottom"
                      action="${pageContext.request.contextPath}/generalController">
                    <input type="hidden" name="command" value="open_all_upcoming_competition_page">
                    <button type="submit" class="w3-right w3-button w3-small">
                        Все ${past}
                    </button>
                </form>
            </div>
        </div>
    </div>
    <%--End past comeptitions section--%>
</div>
</body>

<%@include file="partial/footer.jsp" %>
