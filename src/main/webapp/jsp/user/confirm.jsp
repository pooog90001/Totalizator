<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/partial/header.jsp"/>
<body>
<div class="w3-row">
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col m8 w3-margin-bottom">
        <h1>Your email is not conformed </h1>
        <div class="w3-container w3-card-2">
            <h3 class="w3-container w3-center">
                <p>
                <span> An email has been sent to your email.</span>
                <span> Click the link in the email to complete the registration.</span>
                </p>
            </h3>

            <form name="form" action="${pageContext.request.contextPath}/generalController" method="post"
                  class="w3-container w3-center">
                <input type="hidden" name="command" value="CONFIRM_EMAIL">
                <input type="submit" value="Повторить отправку" class="w3-button w3-theme-d2">
                <a class=" w3-button w3-theme-l2 w3-hover-border-black"
                   href="${pageContext.request.contextPath}/index.jsp">Back on main page</a>
            </form>
            <br>

        </div>
    </div>
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
</div>
</body>

<jsp:include page="/jsp/partial/footer.jsp"/>