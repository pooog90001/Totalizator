<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_in.js"></script>

<fmt:message key="lbl.ForgotPassword" bundle="${rb}" var="forgotPassword"/>
<fmt:message key="lbl.SignIn" bundle="${rb}" var="signIn"/>
<fmt:message key="lbl.Authorization" bundle="${rb}" var="authorization"/>
<fmt:message key="lbl.Password" bundle="${rb}" var="password"/>
<fmt:message key="lbl.Email" bundle="${rb}" var="email"/>
<fmt:message key="warn.wrongEmailorPassword" bundle="${rb}" var="wrongData"/>

<body>
<div class="w3-row-padding">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1>Do bet</h1>

        <form class="w3-container w3-card-2" name="form" action="${pageContext.request.contextPath}/generalController" method="post">
            <input type="hidden" name="command" value="Sign_in">

            <c:choose>
                <c:when test="${competitorList.size() == 2}">
                    <label>Total - 5</label>
                    <label>More total
                        <input class="w3-radio" type="radio" name="gender" value="male">
                    </label>
                    <label>Less total
                        <input class="w3-radio" type="radio" name="gender" value="male">
                    </label>
                    <label>Command One win
                        <input class="w3-radio" type="radio" name="gender" value="male">
                    </label>
                    <label>Command two win
                        <input class="w3-radio" type="radio" name="gender" value="male">
                    </label>
                    <label>Standoff
                        <input class="w3-radio" type="radio" name="gender" value="male">
                    </label>
                </c:when>
                <c:otherwise>
                    <c:forEach var="command" items="commandList">
                        <label>Command One win (1.30)
                            <input class="w3-radio" type="radio" name="gender" value="male">
                        </label>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            <label>
                bet size
                <input type="number" min="1" max="1000" step="1" name="betCash" value="1">
            </label>
            <p>
                <input class="w3-button w3-black" type="submit" id="submit" value="${signIn}">
                <a href="#" class="w3-margin-left w3-small w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-black" >
                    ${forgotPassword}?
                </a>
            </p>
        </form>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>

</body>

<%@include file="partial/footer.jsp" %>
