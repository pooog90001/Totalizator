<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/partial/header.jsp"/>
<body>
<div class="w3-row">
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col m8 w3-margin-bottom">
        <h1>Your account is blocked :( </h1>
        <div class="w3-container w3-card-2">
            <h3 class="w3-container w3-center">
                <p>
                    <span>Blocked reason: ${text} </span>
                </p>
            </h3>

            <div class="w3-container w3-center">
                <a class=" w3-button w3-theme-l2 w3-hover-border-black"
                   href="${pageContext.request.contextPath}/index.jsp">Back on main page</a>
            </div>
            <br>

        </div>
    </div>
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
</div>
</body>

<jsp:include page="/jsp/partial/footer.jsp"/>
