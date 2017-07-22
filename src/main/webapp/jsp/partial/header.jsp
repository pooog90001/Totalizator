<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru" scope="session"/>
<fmt:setBundle basename="text" var="rb"/>

<html>
<head>
    <title><fmt:message key="lbl.title" bundle="${rb}"/></title>
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
             <div class="w3-bar-item w3-hide-small w3-right w3-padding-16 w3-small">
                 <a href="${pageContext.request.contextPath}/jsp/sign_up.jsp" class="w3-hover-text-yellow">
                     <fmt:message key="lbl.SignUp" bundle="${rb}"/>
                 </a>
                 <fmt:message key="lbl.or" bundle="${rb}"/>
                 <a href="${pageContext.request.contextPath}/jsp/sign_in.jsp" class="w3-hover-text-yellow">
                     <fmt:message key="lbl.SignIn" bundle="${rb}"/>
                 </a>

             </div>
            <%--<div class="w3-bar-item w3-dropdown-hover w3-right w3-hide-small" style="padding: 0">
                <a href="#" class="w3-button w3-padding-small">
                    <div class="w3-row" style="max-width: 200px">
                        <div class="w3-col s8">
                            <div class="w3-row w3-right-align w3-small">
                                <div class="w3-col s12">
                                    <p class="w3-padding-small"> Vasuan12345sf2 <- name</p>
                                </div>
                                <div class="w3-col s12">
                                    <p class="w3-padding-small">1012$ <- cash</p>
                                </div>
                            </div>
                        </div>
                        <div class="w3-col s4">
                            <img style="width:60px" class="w3-circle"
                                 src="https://organicthemes.com/demo/profile/files/2012/12/profile_img.png"/>
                        </div>

                    </div>

                </a>
                <div class="w3-dropdown-content w3-bar-block w3-border">
                    <a href="#" class="w3-bar-item w3-button">Profile </a>
                    <a href="#" class="w3-bar-item w3-button">Settings</a>
                    <a href="#" class="w3-bar-item w3-button">Sign out</a>
                </div>
            </div>--%>
            <ctg:profile-bar-tag/>
            <a href="#"
               class="w3-bar-item w3-button w3-padding w3-theme-d4 w3-xlarge w3-hover-none w3-hover-text-yellow">
                <fmt:message key="lbl.title" bundle="${rb}"/>
            </a>
            <div class="w3-bar-item w3-hide-small w3-center w3-padding-16 w3-medium">
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    Новости
                </a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    LIVE
                </a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    Предстоящие
                </a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    Результаты
                </a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    О компании
                </a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    <%--<i class="fa fa-globe"></i>--%>Правила
                </a>
                <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-small w3-hover-none w3-hover-text-yellow"
                   title="XXX">
                    <%--<i class="fa fa-globe"></i>--%>Помощь
                </a>
            </div>
        </div>

    </div>
    <!-- Navbar on small screens -->
    <div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
        <a href="#" class="w3-bar-item w3-button w3-padding">

            <div class="w3-row w3-padding">

                <%-- <div class="w3-container w3-cell">
                     <a href="${pageContext.request.contextPath}/jsp/sign_in.jsp" class="w3-hover-text-yellow w3-center">
                         <p class="w3-center"><fmt:message key="lbl.SignIn" bundle="${rb}"/></p>
                     </a>
                 </div>

                 <div class="w3-container w3-cell">
                     <a href="${pageContext.request.contextPath}/jsp/sign_up.jsp" class="w3-hover-text-yellow">
                         <p class="w3-center"><fmt:message key="lbl.SignUp" bundle="${rb}"/></p>
                     </a>
                 </div>--%>
                <div class="w3-col s8">
                    <div class="w3-row w3-right-align">
                        <div class="w3-col s12">
                            <div class="w3-container">
                                <p class="w3-padding-small"> Vasuan <- name</p>
                            </div>
                        </div>
                        <div class="w3-col s12">
                            <div class="w3-container">
                                <p class="w3-padding-small">10$ <- cash</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="w3-col s4">
                    <img style="width:80px" class="w3-circle"
                         src="https://organicthemes.com/demo/profile/files/2012/12/profile_img.png"/>
                </div>


            </div>
        </a>
        <a href="#" class="w3-bar-item w3-button w3-padding">Новости</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">LIVE</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">Предстоящие</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">Результаты</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">О компании</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">Правила</a>
        <a href="#" class="w3-bar-item w3-button w3-padding">Помощь</a>

    </div>
</header>
