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
        <div class="w3-row-padding">
            <div class="w3-col s12 w3-container w3-display-container">
                <div class="w3-display-topright">
                    <button type="button" class="w3-button w3-card-4 w3-circle w3-theme-l3"
                            style="padding: 15px 17px;" onclick="history.back(); return false;">
                        <i class='fa fa-long-arrow-left'></i>
                    </button>
                </div>
                <h2>${attrNews.title}</h2>
            </div>
            <div class="w3-col s12 w3-container">
                <div class="w3-card-2 w3-round">
                    <div class="w3-display-container">
                        <img src="${pageContext.request.contextPath}${newsImagePath}${attrNews.imageUrl}"
                             alt="${attrNews.title}" style="width: 100%;">
                        <div class="w3-display-topright w3-display-hover">
                            <p class="w3-black w3-padding">${attrNews.dateCreation}</p>
                        </div>
                    </div>
                </div>
                <div class="w3-coll s12">
                    <div class="w3-container w3-padding-large">
                        <p class="w3-small">${attrNews.text}

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
                                    <img src="${userImagePath}${user.avatarURL}"
                                         alt="${attrNews.title}" style="width: 60px;" class="w3-circle">
                                </div>
                                <div class="w3-col s10 w3-padding-large">
                                    <div class="w3-container">
                                        <form id="createForm" method="post">
                                            <input type="hidden" name="command" value="create_comment"/>
                                            <input type="hidden" name="newsId" value="${attrNews.id}"/>
                                            <input type="text" name="text" class="w3-input w3-border" required
                                                      maxlength="300" placeholder="Оставьте ваш комментарий"/>
                                            <button type="button" onclick="createCommentt(this, 'createForm');"
                                                    class="w3-padding-small w3-button w3-theme w3-right w3-margin-top">
                                                <a href="#lastComment">post comment</a>
                                            </button>
                                             <div id="createWarn" style="display: none;" class="w3-row w3-text-red">
                                                 Must be 1-300 symbols
                                             </div>
                                             <div id="createError" style="display: none;" class="w3-row w3-text-red">
                                                 Server error or you haven't access
                                             </div>
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
                                            <p>Войдите или зарегестрируйтесь, чтобы оставить комментарий</p>
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
                        <c:if test="${user.type.toString().equals('ADMIN') || user.type.toString().equals('BOOKMAKER')}">
                            <div class="w3-display-topright w3-display-hover w3-tiny">
                                <form id="${comment['comment_id']}" method="post">
                                    <input type="hidden" name="command" value="change_lock_comment"/>
                                    <input type="hidden" name="isBlocked" value="${comment['comment_is_blocked']}"/>
                                    <input type="hidden" name="commentId" value="${comment['comment_id']}"/>
                                    <a name="lastComment"></a>
                                    <a href="">
                                        <c:choose>
                                            <c:when test="${comment['comment_is_blocked']}">
                                                <button type="button" onclick="changeLockComment(this, '${comment['comment_id']}')"
                                                        class="w3-padding-small w3-button w3-theme w3-right w3-margin-top">
                                                    Разблокировать
                                                </button>
                                            </c:when>
                                            <c:otherwise>
                                                <button type="button" onclick="changeLockComment(this, '${comment['comment_id']}')"
                                                        class="w3-padding-small w3-button w3-theme w3-right w3-margin-top">
                                                    Заблокировать
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </form>
                            </div>
                        </c:if>

                        <div class="w3-row">
                            <div class="w3-col s2 w3-padding-large">
                                <img src="${userImagePath}${comment.user_avatar_url}"
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
                                    <hr style="margin: 5px">
                                        <%--Check for blocked comment--%>
                                    <c:choose>
                                        <c:when test="${comment['comment_is_blocked']}">
                                            <div class="w3-col s12">
                                                <div class="w3-display-container">
                                                    <p class="w3-display-middle w3-small">
                                                        Данный комментарий заблокирован
                                                    </p>
                                                </div>
                                                <c:if test="${user.type.toString().equals('ADMIN') || user.type.toString().equals('BOOKMAKER')}">
                                                    <div>
                                                        <p class="w3-small">${comment.comment_text}</p>
                                                    </div>
                                                </c:if>

                                            </div>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="w3-col s12">
                                                <span>${comment.comment_text} </span>
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
<a name="lastComment"></a>
<div id="accessDenied" class="w3-modal" style="display: none;">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <p>Access denied. </p>
            <input type="button" class="w3-button w3-theme-d2 w3-margin-top" value="Ok" onclick="(accessDenied).style.display='none'">
        </div>
    </div>
</div>
<div id="serverError" class="w3-modal" style="display: none;">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <p>Server error. Check connection.</p>
            <input type="button" class="w3-button w3-theme-d2 w3-margin-top" value="Ok" onclick="(serverError).style.display='none'">
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/comment.js"></script>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<%@include file="/jsp/partial/footer.jsp" %>


