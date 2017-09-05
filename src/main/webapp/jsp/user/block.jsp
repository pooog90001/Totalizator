<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>


<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.back.on.main.page" var="txtBackOnMainPage"/>
<fmt:message bundle="${rb}" key="txt.account.blocked" var="txtAccountBlocked"/>
<fmt:message bundle="${rb}" key="txt.blocking.reason" var="txtBlockingReason"/>

<body>
<div class="w3-row">
    <div class="w3-col m2">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col m8 w3-margin-bottom">
        <h1>${txtAccountBlocked} :( </h1>
        <div class="w3-container w3-card-2">
            <h3 class="w3-container w3-center">
                    <span>${txtBlockingReason}: <c:out value="${text}"/> </span>
            </h3>

            <div class="w3-container w3-center">
                <a class=" w3-button w3-theme-l2 w3-hover-border-black"
                   href="${pageContext.request.contextPath}/index.jsp">${txtBackOnMainPage}</a>
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
