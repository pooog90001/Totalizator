<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_up.js"></script>

<fmt:message bundle="${rb}" key="warn.empty" var="txtEmptyFeild"/>
<fmt:message bundle="${rb}" key="warn.existEmail" var="txtExistEmail"/>
<fmt:message bundle="${rb}" key="warn.notEqualsPasswords" var="txtNotEqualsPasswords"/>
<fmt:message bundle="${rb}" key="warn.wrongEmail" var="txtWrongEmail"/>
<fmt:message bundle="${rb}" key="warn.wrongPassword" var="txtWrongPassword"/>
<fmt:message bundle="${rb}" key="warn.wrongName" var="txtWrongName"/>
<fmt:message bundle="${rb}" key="lbl.Registration" var="txtRegistration"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="txtName"/>
<fmt:message bundle="${rb}" key="lbl.Email" var="txtEmail"/>
<fmt:message bundle="${rb}" key="lbl.Password" var="txtPassword"/>
<fmt:message bundle="${rb}" key="lbl.RepeatPassword" var="txtRepeatPassword"/>

<body>
<div class="w3-row">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1><c:out value="${txtRegistration}"/></h1>
        <form class="w3-container w3-card-2" name="form" action="${pageContext.request.contextPath}/generalController" method="post">
            <input type="hidden" name="command" value="Sign_Up">

            <p><c:out value="${txtName}"/>:*</p>
            <input title="${txtName}" class="w3-input w3-border" type="text" id="name" name="name" value="${name}">
            <c:if test="${requestScope.containsKey('wrongName')}">
                <span id="serverWrongName"><c:out value="${txtWrongName}"/></span>
            </c:if>
            <span id="wrongName"><c:out value="${txtWrongName}"/></span>
            <span id="emptyName"><c:out value="${txtEmptyFeild}"/></span>

            <p><c:out value="${txtEmail}"/>:*</p>
            <input title="${txtEmail}" class="w3-input w3-border" type="email" id="email" name="email" value="${email}">
            <c:if test="${requestScope.containsKey('wrongEmail')}">
                <span class="w3-text-red" id="serverWrongEmail"><c:out value="${txtWrongEmail}"/></span>
            </c:if>
            <c:if test="${requestScope.containsKey('emailExists')}">
                <span class="w3-text-red" id="serverWrongEmail">${txtExistEmail}</span>
            </c:if>
            <span class="w3-text-red" id="wrongEmail"><c:out value="${txtWrongEmail}"/></span>
            <span class="w3-text-red" id="emptyEmail"><c:out value="${txtEmptyFeild}"/></span>
            <br>

            <p><c:out value="${txtPassword}"/>:*</p>
            <input title="${txtPassword}" class="w3-input w3-border" type="password" id="password" name="password"
                   value="${password}">
            <c:if test="${requestScope.containsKey('wrongPassword')}">
                <span class="w3-text-red" id="serverWrongPassword"><c:out value="${txtWrongPassword}"/></span>
            </c:if>
            <span class="w3-text-red" id="wrongPassword"><c:out value="${txtWrongPassword}"/></span>
            <span class="w3-text-red" id="emptyPassword"><c:out value="${txtEmptyFeild}"/></span>
            <br>

            <p><c:out value="${txtRepeatPassword}"/>:*</p>
            <input title="${txtRepeatPassword}" class="w3-input w3-border" type="password" id="repeatPassword"
                   name="repeatPassword"
                   value="${repeatPassword}">
            <c:if test="${requestScope.containsKey('wrongRepeatPassword')}">
                <span class="w3-text-red" id="serverWrongRepeatPassword"><c:out
                        value="${txtNotEqualsPasswords}"/></span>
            </c:if>
            <span class="w3-text-red" id="wrongRepeatPassword"><c:out value="${txtNotEqualsPasswords}"/></span>
            <span class="w3-text-red" id="emptyRepeatPassword"><c:out value="${txtEmptyFeild}"/></span>
            <br>
            <p><input class="w3-button w3-black" type="submit" id="submit" value="${signUp}"></p>
        </form>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>


</body>
<%@include file="/jsp/partial/footer.jsp" %>
