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
    <div class="w3-col m3 w3-hide-small">
        <%@include file="bar/left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">
        <div class="w3-row-padding">
            <div class="w3-col s12 w3-container">
                <h2>${attrNews.title}</h2>
            </div>
            <div class="w3-col s12 w3-container">
                <div class="w3-card-2 w3-round">
                    <div class="w3-display-container">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSphHHzJNUkLhSlHBDw2EUiPOmwedQt44L5GQ8d0yFjA4_BGPTm"
                             alt="${attrNews.title}" style="width: 100%;">
                        <div class="w3-display-topright w3-display-hover">
                            <p class="w3-black w3-padding">${attrNews.dateCreation}</p>
                        </div>
                    </div>
                </div>
                <div class="w3-coll s12">
                    <div class="w3-container w3-padding-large">
                        <p class="w3-small">${attrNews.text}
                            sdfsdfsdf asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasdsdfsdfsdf asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasdsdfsdfsdf asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasdsdfsdfsdf asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasdsdfsdfsdf asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                            asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                        </p>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${user != null}">
                    <div class="w3-container w3-padding-large">
                        <div class="w3-card-2">
                            <div class="w3-row">
                                <div class=" w3-col s2 w3-padding-large">
                                    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSphHHzJNUkLhSlHBDw2EUiPOmwedQt44L5GQ8d0yFjA4_BGPTm"
                                         alt="${attrNews.title}" style="width: 60px;" class="w3-circle">
                                </div>
                                <div class="w3-col s10 w3-padding-large">
                                    <div class="w3-container">
                                        <form action="${pageContext.request.contextPath}/generalController" method="post">
                                            <input type="hidden" name="command" value="add_comment"/>
                                            <input type="hidden" name="newsId" value="${attrNews.id}"/>
                                            <input type="hidden" name="userId" value="${user.id}"/>
                                            <textarea name="text" class="w3-input w3-border" required
                                                      maxlength="200" placeholder="Оставьте ваш комментарий"></textarea>
                                            <button type="submit" class="w3-button w3-theme w3-right w3-margin-top">
                                                <b> post comment</b>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="w3-container">
                        <div class="w3-row">
                            <div class="w3-col s12">
                                <div class="w3-container">
                                    <div class="w3-card-2">
                                        <div class="w3-center w3-padding-24">
                                            <p>Войдите или зарегестрируйтесь, чтобы оставить окмментарий</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
            <c:forEach var="comment" items="${newsCommentList}">
                <div class="w3-container w3-padding-large ">
                    <div class="w3-card-2 w3-display-container">
                        <c:if test="${!user.type.toString().equals('USER')}">
                            <div class="w3-display-topright w3-display-hover w3-tiny">Заблокировать</div>
                        </c:if>

                        <div class="w3-row">
                            <div class="w3-col s2 w3-padding-large">
                                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSphHHzJNUkLhSlHBDw2EUiPOmwedQt44L5GQ8d0yFjA4_BGPTm"
                                     class="w3-circle" style="width: 60px;">
                            </div>
                            <div class="w3-col s10">
                                <div class="w3-row">
                                    <div class="w3-col s6">
                                        <div class="w3-container w3-small">

                                                ${comment.user_name}
                                        </div>
                                    </div>
                                    <div class="w3-col s6">
                                        <div class="w3-container w3-small w3-text-dark-gray">

                                                ${comment.comment_post_date}
                                        </div>
                                    </div>
                                        <%--Check for blocked comment--%>
                                    <c:choose>
                                        <c:when test="${comment['comment_is_blocked']}">
                                            <div class="w3-col s12">
                                                <div class="w3-display-container">
                                                    <p class="w3-display-middle w3-small">
                                                        Данный комментарий заблокирован
                                                    </p>
                                                </div>

                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="w3-col s12">
                                                <p class="w3-small">${comment.comment_text} sdf
                                                    sdfsdfsdf asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                                                    asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                                                    asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                                                    asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                                                    asdasd asdasd asdasd asdasd asdasd asdasd asdasd
                                                </p>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>

<%@include file="partial/footer.jsp" %>


