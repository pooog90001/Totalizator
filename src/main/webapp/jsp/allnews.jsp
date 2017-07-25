<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-small">&cross;</span>
        <br>
    </div>
    <%@include file="bar/userleftbar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="bar/userleftbar.jsp" %>
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
                <div class="w3-half w3-container">
                    <div class="w3-card-2 w3-round">
                        <div class="w3-display-container w3-hover-opacity">
                            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSphHHzJNUkLhSlHBDw2EUiPOmwedQt44L5GQ8d0yFjA4_BGPTm"
                                 alt="${news.title}" style="width: 100%;"
                                 class="w3-hover-opacity">
                            <div class="w3-display-topright w3-display-hover">
                                <p class="w3-black w3-padding">${news.dateCreation}</p>
                            </div>
                        </div>
                        <form action="${pageContext.request.contextPath}/generalController">
                            <input type="hidden" name="open_concrete_news_page">
                            <button type="submit" class="w3-hover-none w3-button w3-hover-text-gray">
                                <b> ${news.title} </b>
                            </button>
                            <p class="w3-small">${news.text}</p>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>

<%@include file="partial/footer.jsp" %>


