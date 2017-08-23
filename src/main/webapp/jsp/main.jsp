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
                            <img src="${newsImagePath}${news.imageUrl}"
                                 alt="${news.title}" style="width: 100%;">
                            <div class="w3-display-topright w3-display-hover">
                                <p class="w3-black w3-padding-small w3-small">
                                        <ctg:date-presenter date="${news.dateCreation}"/>
                                </p>
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
                <input type="hidden" name="command" value="OPEN_ALL_UPCOMING_COMPETITIONS">
                <button type="submit" class="w3-xlarge w3-button w3-hover-none w3-padding-small">
                    ${upcoming}
                </button>
            </form>
            <div class=" w3-container w3-padding-small w3-center">
                <%--Import upcoming competitions part--%>
                <%@include file="partial/upcoming_competition.jsp"%>
            </div>
            <form class="w3-container w3-margin-bottom" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value="OPEN_ALL_UPCOMING_COMPETITIONS">
                <button type="submit" class="w3-right w3-button w3-small">
                    Все ${upcoming}
                </button>
            </form>
        </div>
        <%--End upcoming comeptitions section--%>

        <%--Start past comeptitions section--%>
        <div class="w3-row-padding">
            <form class="w3-container" action="${pageContext.request.contextPath}/generalController">
                <input type="hidden" name="command" value="OPEN_ALL_PAST_COMPETITIONS">
                <button type="submit" class="w3-xlarge w3-button w3-hover-none">
                    ${past}
                </button>
            </form>
            <div class=" w3-container w3-padding-small w3-center">

                <%--Import past ompetition part--%>
                <%@include file="partial/past_competition.jsp"%>

                    <form class="w3-container w3-margin-bottom"
                      action="${pageContext.request.contextPath}/generalController">
                    <input type="hidden" name="command" value="OPEN_ALL_PAST_COMPETITIONS">
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
