<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_up.js"></script>

<fmt:message bundle="${rb}" key="warn.empty" var="emptyFeild"/>
<fmt:message bundle="${rb}" key="warn.existEmail" var="existEmail"/>
<fmt:message bundle="${rb}" key="warn.notEqualsPasswords" var="notEqualsPasswords"/>
<fmt:message bundle="${rb}" key="warn.wrongEmail" var="wrongEmail"/>
<fmt:message bundle="${rb}" key="warn.wrongPassword" var="wrongPassword"/>
<fmt:message bundle="${rb}" key="warn.wrongName" var="wrongName"/>
<fmt:message bundle="${rb}" key="lbl.Registration" var="registration"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="name"/>
<fmt:message bundle="${rb}" key="lbl.Email" var="email"/>
<fmt:message bundle="${rb}" key="lbl.Password" var="password"/>
<fmt:message bundle="${rb}" key="lbl.RepeatPassword" var="repeatPassword"/>

<body>
<div class="w3-row">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1>${registration}</h1>
        <form class="w3-container w3-card-2" name="form" action="${pageContext.request.contextPath}/generalController" method="post">
            <input type="hidden" name="command" value="Sign_Up">

            <p>${name}:*</p>
            <input title="${name}" class="w3-input w3-border" type="text" id="name" name="name" value="${requestScope.get('name')}">
            <c:if test="${requestScope.containsKey('wrongName')}">
            <span class="w3-text-red" id="serverWrongName">${wrongName}</span>
            </c:if>
            <span class="w3-text-red" id="wrongName">${wrongName}</span>
            <span class="w3-text-red" id="emptyName">${emptyFeild}</span>

            <p>${email}:*</p>
            <input title="${email}" class="w3-input w3-border" type="email" id="email" name="email" value="${requestScope.get('email')}">
            <c:if test="${requestScope.containsKey('wrongEmail')}">
            <span class="w3-text-red" id="serverWrongEmail">${wrongEmail}</span>
            </c:if>
            <c:if test="${requestScope.containsKey('emailExists')}">
            <span class="w3-text-red" id="serverWrongEmail">${existEmail}</span>
            </c:if>
            <span class="w3-text-red" id="wrongEmail">${wrongEmail}</span>
            <span class="w3-text-red" id="emptyEmail">${emptyFeild}</span>
            <br>

            <p>${password}:*</p>
            <input title="${password}" class="w3-input w3-border" type="password" id="password" name="password"
                   value="${requestScope.get('password')}">
            <c:if test="${requestScope.containsKey('wrongPassword')}">
            <span class="w3-text-red" id="serverWrongPassword">${wrongPassword}</span>
            </c:if>
            <span class="w3-text-red" id="wrongPassword">${wrongPassword}</span>
            <span class="w3-text-red" id="emptyPassword">${emptyFeild}</span>
            <br>

            <p>${repeatPassword}:*</p>
            <input title="${repeatPassword}" class="w3-input w3-border" type="password" id="repeatPassword" name="repeatPassword"
                   value="${requestScope.get('repeatPassword')}">
            <c:if test="${requestScope.containsKey('wrongRepeatPassword')}">
            <span class="w3-text-red" id="serverWrongRepeatPassword">${notEqualsPasswords}</span>
            </c:if>
            <span class="w3-text-red" id="wrongRepeatPassword">${notEqualsPasswords}</span>
            <span class="w3-text-red" id="emptyRepeatPassword">${emptyFeild}</span>
            <br>
            <p><input class="w3-button w3-black" type="submit" id="submit" value="${signUp}"></p>
        </form>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>


</body>
<%@include file="partial/footer.jsp" %>
