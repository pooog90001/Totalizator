<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-small">&cross;</span>
        <br>
    </div>
    <%@include file="bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">

    <%--<ctg:info-time/>
    <fmt:message key="lbl.title" bundle="${rb}"/> <br>
    <a href="jsp/sign_up.jsp"> <fmt:message key="lbl.SignUp" bundle="${rb}"/> </a>
    <p><fmt:message key="lbl.or" bundle="${rb}"/></p>
    <a href="jsp/sign_in.jsp"> <fmt:message key="lbl.SignIn" bundle="${rb}"/> </a>--%>
    <div class="w3-col m3 w3-hide-small">
        <!-- Accordion -->
        <%@include file="bar/left_bar.jsp" %>
        <%--<div class="w3-card-2 w3-round w3-hide-small">
            <div class="w3-white">
                <c:forEach var="kind" items="${requestScope.get('kindsOfSport')}">
                    <button onclick="myFunction('${kind.key}')" class="w3-button w3-block w3-theme-l1 w3-center">
                            ${kind.key}
                    </button>
                    <div id="${kind.key}" class="w3-hide w3-container">
                        <c:forEach var="competition" items="${kind.value}">
                            <form action="${pageContext.request.contextPath}/generalController">
                                <input type="hidden" name="open_competition_type" value="${competition.value}">
                                <input type="submit" class="w3-button w3-block w3-theme-l3 w3-center"
                                       name="submit" value="${competition.key}">
                            </form>
                        </c:forEach>
                    </div>
                </c:forEach>

            </div>
        </div>--%>
        <!-- End Left Column -->
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <form class="w3-container" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value="open_all_news_page">
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
                            <input type="hidden" name="command" value="open_concrete_news_page">
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
                <input type="hidden" name="command" value="open_all_news_page">
                <button type="submit" class="w3-right w3-button w3-small">
                    Все новости
                </button>
            </form>

        </div>


        <div class="w3-row-padding">
            <div class="w3-container">
                <h3><a href="#">${live}</a></h3>
            </div>
            <div class=" w3-container w3-card-2">
                <div class="w3-col s12 w3-container">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round">
                            <div class="w3-col s1">#</div>
                            <div class="w3-col s2">kind of sport</div>
                            <div class="w3-col s2">command</div>
                            <div class="w3-col s1">T</div>
                            <div class="w3-col s1">L</div>
                            <div class="w3-col s1">M</div>
                            <div class="w3-col s1">W</div>
                            <div class="w3-col s1">X</div>
                        </div>
                    </div>
                    <hr>
                </div>
                <c:forEach var="liveGame" items="${requestScope.get('liveGames')}">
                    <div class="w3-col s12 w3-container">
                        <div class="w3-row w3-hover-light-grey">
                            <div class="w3-card-2 w3-white w3-round">
                                <div class="w3-col s1">${liveGame['competition_id']}</div>
                                <div class="w3-col s2">${liveGame['kind_of_sport_name']}</div>
                                <div class="w3-col s2">${liveGame['command_name']}</div>
                                <div class="w3-col s1">
                                    <ctg:decimal-presenter number="${liveGame['competition_total']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <ctg:decimal-presenter number="${liveGame['competition_less_total_coeff']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <ctg:decimal-presenter number="${liveGame['competition_more_total_coeff']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <ctg:decimal-presenter number="${liveGame['competitor_win_coeff']}"/>
                                </div>
                                <div class="w3-col s1">
                                    <ctg:decimal-presenter number="${liveGame['competition_standoff_coeff']}"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-container w3-margin-bottom">
                <a href="#" class="w3-right w3-opacity w3-small">Все LIVE</a>
            </div>
        </div>

        <div class="w3-row-padding">
            <div class="w3-container">
                <h3><a href="#">${upcoming}</a></h3>
            </div>
            <div class=" w3-container w3-card-2">
                <div class="w3-col s12 w3-container">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round">
                            <div class="w3-col s1">#</div>
                            <div class="w3-col s2">kind of sport</div>
                            <div class="w3-col s2">command</div>
                            <div class="w3-col s1">T</div>
                            <div class="w3-col s1">L</div>
                            <div class="w3-col s1">M</div>
                            <div class="w3-col s1">W</div>
                            <div class="w3-col s1">X</div>
                        </div>
                    </div>
                    <hr>
                </div>
                <c:forEach var="upcommingGame" items="${requestScope.get('upcommingGames')}">
                    <div class="w3-col s12 w3-container">
                        <div class="w3-row">
                            <div class="w3-card-2 w3-white w3-round">
                                <div class="w3-col s1">${upcommingGame['competition_id']}</div>
                                <div class="w3-col s2">${upcommingGame['kind_of_sport_name']}</div>
                                <div class="w3-col s2">${upcommingGame['command_name']}</div>
                                <div class="w3-col s1">${upcommingGame['competition_total']}</div>
                                <div class="w3-col s1">${upcommingGame['competition_less_total_coeff']}</div>
                                <div class="w3-col s1">${upcommingGame['competition_more_total_coeff']}</div>
                                <div class="w3-col s1">${upcommingGame['competitor_win_coeff']}</div>
                                <div class="w3-col s1">${upcommingGame['competition_standoff_coeff']}</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-container w3-margin-bottom">
                <a href="#" class="w3-right w3-opacity w3-small">Все ${upcoming}</a>
            </div>
        </div>

        <div class="w3-row-padding">
            <div class="w3-container">
                <h3><a href="#">${past}</a></h3>
            </div>
            <div class=" w3-container w3-card-2">
                <div class="w3-col s12 w3-container">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round">
                            <div class="w3-col s1">#</div>
                            <div class="w3-col s1">date</div>
                            <div class="w3-col s2">kind of sport</div>
                            <div class="w3-col s2">command</div>
                            <div class="w3-col s1">result</div>
                        </div>
                    </div>
                    <hr>
                </div>
                <c:forEach var="pastGame" items="${requestScope.get('pastGames')}">
                    <div class="w3-col s12 w3-container">
                        <div class="w3-row">
                            <div class="w3-card-2 w3-white w3-round">
                                <div class="w3-col s1">${pastGame['competition_id']}</div>
                                <div class="w3-col s1">${pastGame['competition_date_start']}</div>
                                <div class="w3-col s2">${pastGame['kind_of_sport_name']}</div>
                                <div class="w3-col s2">${pastGame['command_name']}</div>
                                <div class="w3-col s1">${pastGame['competitor_result']}</div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="w3-container w3-margin-bottom">
                <a href="#" class="w3-right w3-opacity w3-small">Все ${past}</a>
            </div>
        </div>


    </div>
</div>
</body>

<%@include file="partial/footer.jsp" %>
