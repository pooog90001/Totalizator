<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-small">&cross;</span>
        <br>
    </div>
    <%@include file="bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container ">

    <div class="w3-col m3 w3-hide-small">
        <%@include file="bar/left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <div class="w3-row-padding">
            <div class="w3-container w3-margin-bottom w3-display-container">
                <div class="w3-display-topright">
                    <button type="button" class="w3-button w3-card-4 w3-circle w3-theme-l3"
                            style="padding: 15px 17px;" onclick="history.back(); return false;">
                        <i class='fa fa-long-arrow-left'></i>
                    </button>
                </div>
                <h2>${competition['competition_name']}</h2>
            </div>
            <div class="w3-row w3-container">
                <div class="w3-col m6 w3-container">
                    <h1> На  вашу электронную почту отправлено письмо. Перейдите по ссылке в письме для завершения регистрации. </h1>
                    <h1> An email has been sent to your email. Click the link in the email to complete the registration. </h1>
                    <form name="form" action="/generalController" method="post">
                        <input type="hidden" name="command" value="confirmEmail">
                        <input type="submit" value="Повторить отправку">
                    </form>
                </div>
            </div>

        </div>
    </div>
</div>

</body>

<%@include file="partial/footer.jsp" %>