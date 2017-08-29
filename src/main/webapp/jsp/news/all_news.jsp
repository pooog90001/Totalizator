<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>


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
        <%@include file="../bar/left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <div class="w3-container">
                <button type="button" class="w3-xlarge w3-button w3-hover-none">
                    Новости
                </button>
            </div>

            <c:forEach var="news" items="${newsList}">
                <div class="w3-half w3-container">
                    <div class="w3-card-2 w3-round">
                        <form action="${pageContext.request.contextPath}/generalController">
                            <button type="submit" class="w3-button w3-hover-none" style="padding: 0;">
                                <div class="w3-display-container">
                                    <img src="${newsImagePath}${news.imageUrl}"
                                         alt="<c:out value="${news.title}"/>" style="width: 100%;">
                                    <div class="w3-display-topright w3-display-hover">
                                        <p class="w3-black w3-padding w3-small">
                                            <ctg:date-presenter date="${news.dateCreation}"/>
                                        </p>
                                    </div>
                                </div>
                            </button>
                            <input type="hidden" name="command" value="open_concrete_news">
                            <input type="hidden" name="newsId" value="${news.id}">
                            <button type="submit" class="w3-hover-none w3-button w3-hover-text-gray">
                                <b> <c:out value="${news.title}"/> </b>
                            </button>
                            <p class="w3-small w3-padding"><c:out value="${news.text}"/></p>
                        </form>
                    </div>
                </div>
            </c:forEach>
            <div class="pagination">
                <ctg:pagination total="${newsCount}" limit="${limit}" command="open_all_news"/>
            </div>
        </div>
    </div>
</div>
</body>

<%@include file="/jsp/partial/footer.jsp" %>


