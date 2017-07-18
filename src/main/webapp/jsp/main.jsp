<html>
    <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"  %>
    <%@ taglib prefix="ctg" uri="customtags" %>
    <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <fmt:setLocale value="ru" scope="session"/>
    <fmt:setBundle basename="text" var="rb"/>


    <head>
        <meta charset="utf-8">
        <title>Maxim - Modern One Page Bootstrap Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap.min.css" rel="stylesheet">
        <link href="../css/bootstrap.css.map" rel="stylesheet">
        <link href="../css/style.css" rel="stylesheet">
        <link href="../color/default.css" rel="stylesheet">
        <link rel="shortcut icon" href="../img/favicon.ico">
    </head>
    <header>
        <div class="navbar-wrapper">
            <div class="row">
                <div class="container">
                    <div class="col-lg-3">

                        <!-- Responsive navbar -->
                        <%--<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>--%>
                        <h1 class="brand"><a href="index.jsp">Totalizator</a></h1>
                        <!-- navigation -->

                    </div>
                    <div class="col-lg-6">
                        <nav class=" nav-collapse">
                            <ul id="pull-left menu-main" class="pull-left nav">
                                <li><a title="team" href="#about">Главная</a></li>
                                <li><a title="works" href="#works">LIVE</a></li>
                                <li><a title="services" href="#services">Предстоящие</a></li>
                                <li><a title="blog" href="#blog">Результаты</a></li>
                                <li><a title="contact" href="#contact">О компании</a></li>
                                <li><a title="contact" href="#contact">Правила</a></li>
                                <li><a title="contact" href="#contact">Помощь</a></li>
                            </ul>
                        </nav>
                    </div>
                    <div class="col-lg-3">

                        <a href="jsp/sign_up.jsp"> <fmt:message key="lbl.SignUp" bundle="${rb}"/></a>
                        <fmt:message key="lbl.or" bundle="${rb}"/>
                        <a href="jsp/sign_in.jsp"> <fmt:message key="lbl.SignIn" bundle="${rb}"/> </a>

                    </div>
                </div>
            </div>
        </div>

    </header>
    <body>
        <ctg:info-time/>
        <fmt:message key="lbl.title" bundle="${rb}"/> <br>
        <a href="jsp/sign_up.jsp"> <fmt:message key="lbl.SignUp" bundle="${rb}"/> </a>
        <p><fmt:message key="lbl.or" bundle="${rb}"/></p>
        <a href="jsp/sign_in.jsp"> <fmt:message key="lbl.SignIn" bundle="${rb}"/> </a>
    </body>
    <footer>
        <div class="container">
            <div class="row">
                <div class="span6 offset3">
                    <ul class="social-networks">
                        <li><a href="#"><i class="icon-circled icon-bgdark icon-instagram icon-2x"></i></a></li>
                        <li><a href="#"><i class="icon-circled icon-bgdark icon-twitter icon-2x"></i></a></li>
                        <li><a href="#"><i class="icon-circled icon-bgdark icon-dribbble icon-2x"></i></a></li>
                        <li><a href="#"><i class="icon-circled icon-bgdark icon-pinterest icon-2x"></i></a></li>
                    </ul>
                    <p class="copyright">
                        &copy; Totalizator. All rights reserved.
                    </p>
                </div>
            </div>
        </div>
        <!-- ./container -->
    </footer>
    <%--<a href="#" class="scrollup"><i class="icon-angle-up icon-square icon-bgdark icon-2x"></i></a>--%>
    <script src="../js/jquery.js"></script>
    <script src="../js/jquery.scrollTo.js"></script>
    <script src="../js/jquery.nav.js"></script>
    <script src="../js/jquery.localscroll-1.2.7-min.js"></script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/jquery.prettyPhoto.js"></script>
    <script src="../js/isotope.js"></script>
    <script src="../js/jquery.flexslider.js"></script>
    <script src="../js/inview.js"></script>
    <script src="../js/animate.js"></script>
    <script src="../js/validate.js"></script>
    <script src="../js/custom.js"></script>
</html>
