<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="text" var="rb"/>
<c:set var="user" value="${sessionScope.get('user')}"/>

<fmt:message bundle="${rb}" key="lbl.SignIn" var="signIn"/>
<fmt:message bundle="${rb}" key="lbl.SignUp" var="signUp"/>
<fmt:message bundle="${rb}" key="lbl.SignOut" var="signOut"/>
<fmt:message bundle="${rb}" key="lbl.title" var="totalizator"/>
<fmt:message bundle="${rb}" key="lbl.Settings" var="settings"/>
<fmt:message bundle="${rb}" key="lbl.Profile" var="profile"/>
<fmt:message bundle="${rb}" key="lbl.News" var="news"/>
<fmt:message bundle="${rb}" key="lbl.Live" var="live"/>
<fmt:message bundle="${rb}" key="lbl.Results" var="results"/>
<fmt:message bundle="${rb}" key="lbl.Help" var="help"/>
<fmt:message bundle="${rb}" key="lbl.AboutCompany" var="aboutCompany"/>
<fmt:message bundle="${rb}" key="lbl.Rules" var="rules"/>
<fmt:message bundle="${rb}" key="lbl.Upcoming" var="upcoming"/>
<fmt:message bundle="${rb}" key="lbl.Past" var="past"/>


<html>
<head>
    <title>${totalizator}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/w3-theme-black.css">
    <link rel='stylesheet' href='${pageContext.request.contextPath}/css/style.css'>
    <link rel='stylesheet' href='${pageContext.request.contextPath}/font/open-sans.css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
</head>

<header>
    <div>
        <div class="w3-bar w3-theme-d2 w3-left-align w3-medium">


            <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-16 w3-hover-white w3-large w3-theme-d2"
               href="javascript:void(0);" onclick="openNav()">
                <i class="fa fa-bars"></i>
            </a>
            <c:choose>
                <%--Present if user signed--%>
                <c:when test="${user != null}">

                    <div class="w3-bar-item w3-dropdown-hover w3-right w3-hide-small" style="padding: 0">
                        <a href="#" class="w3-button w3-padding-small">
                            <div class="w3-row" style="max-width: 200px">
                                <div class="w3-col s8">
                                    <div class="w3-row w3-right-align w3-small">
                                        <div class="w3-col s12 w3-padding-small">
                                            ${user.name}
                                        </div>
                                        <div class="w3-col s12 w3-padding-small">
                                            <ctg:decimal-presenter number="${user.cash}"/>$
                                        </div>
                                    </div>
                                </div>
                                <div class="w3-col s4">
                                    <img style="width:50px" class="w3-circle"
                                         src="https://organicthemes.com/demo/profile/files/2012/12/profile_img.png"/>
                                </div>
                            </div>
                        </a>
                        <div class="w3-dropdown-content w3-bar-block w3-border">
                            <c:if test="${!user.type.toString().equals('USER')}">
                                <a href="#" class="w3-bar-item w3-button">Admin panel</a>
                            </c:if>
                            <a href="#" class="w3-bar-item w3-button">${profile} </a>
                            <a href="#" class="w3-bar-item w3-button">${settings}</a>
                            <form action="${pageContext.request.contextPath}/generalController">
                                <input type="hidden" name="command" value="sign_out">
                                <input type="submit" class="w3-bar-item w3-button" value="Sign out"/>
                            </form>
                        </div>
                    </div>
                </c:when>
                <%--Present if user not signed--%>
                <c:otherwise>
                    <div class="w3-bar-item w3-hide-small w3-right w3-padding-16 w3-small">
                        <a href="${pageContext.request.contextPath}/jsp/sign_up.jsp" class="w3-hover-text-yellow">
                                ${signUp}
                        </a>
                        <fmt:message key="lbl.or" bundle="${rb}"/>
                        <a href="${pageContext.request.contextPath}/jsp/sign_in.jsp" class="w3-hover-text-yellow">
                                ${signIn}
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>

            <a href="${pageContext.request.contextPath}/index.jsp"
               class="w3-bar-item w3-button w3-padding w3-theme-d4 w3-xlarge w3-hover-none w3-hover-text-yellow">
                ${totalizator}
            </a>
            <div class="w3-left w3-hide-small w3-center w3-padding-small w3-medium">
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${news}">
                </form>
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${live}">
                </form>
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${upcoming}">
                </form>
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${results}">
                </form>
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${aboutCompany}">
                </form>
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${rules}">
                </form>
                <form action="${pageContext.request.contextPath}/generalController" class="w3-bar-item w3-padding-small">
                    <input type="hidden" name="command" value="open_all_news_page">
                    <input type="submit" class="w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                           value="${help}">
                </form>
            </div>
        </div>

    </div>
    <!-- Navbar on small screens -->
    <div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
            <div class="w3-row w3-bar-item w3-padding">
                <c:choose>
                    <%--Present if user signed--%>
                    <c:when test="${user != null}">
                        <div class="w3-col s8">
                            <div class="w3-row w3-right-align">
                                <div class="w3-col s12">
                                    <div class="w3-container">
                                        <p class="w3-padding-small"> ${user.name}</p>
                                    </div>
                                </div>
                                <div class="w3-col s12">
                                    <div class="w3-container">
                                        <p class="w3-padding-small">${user.cash.toPlainString()}$</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="w3-col s4">
                            <img style="width:80px" class="w3-circle"
                                 src="https://organicthemes.com/demo/profile/files/2012/12/profile_img.png"/>
                        </div>
                    </c:when>
                    <%--Present if user not signed--%>
                    <c:otherwise>
                        <div class="w3-half">
                            <a href="${pageContext.request.contextPath}/jsp/sign_in.jsp"
                               class="w3-bar-item w3-hover-text-yellow w3-center">
                                <p class="w3-center">${signIn}</p>
                            </a>
                        </div>
                        <div class="w3-half">
                            <a href="${pageContext.request.contextPath}/jsp/sign_up.jsp"
                               class="w3-bar-item w3-hover-text-yellow w3-center">
                                <p class="w3-center">${signUp}</p>
                            </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>


        <form>
            <input type="hidden" name="command" value="open_all_news_page">
            <input type="submit" class="w3-button w3-padding w3-bar-item" value="${news}">
        </form>

        <a href="#" class="w3-bar-item w3-button w3-padding">${live}</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">${upcoming}</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">${results}</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">${aboutCompany}</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">${rules}</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">${help}</a>
    </div>
</header>
