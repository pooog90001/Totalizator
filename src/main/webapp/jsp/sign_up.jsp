<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/12/2017
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_up.js"></script>

<fmt:message key="warn.empty" bundle="${rb}" var="emptyFeild"/>
<fmt:message key="warn.existEmail" bundle="${rb}" var="existEmail"/>
<fmt:message key="warn.notEqualsPasswords" bundle="${rb}" var="notEqualsPasswords"/>
<fmt:message key="warn.wrongEmail" bundle="${rb}" var="wrongEmail"/>
<fmt:message key="warn.wrongPassword" bundle="${rb}" var="wrongPassword"/>
<fmt:message key="warn.wrongName" bundle="${rb}" var="wrongName"/>
<fmt:message key="lbl.Registration" bundle="${rb}" var="registration"/>
<fmt:message key="btn.Register" bundle="${rb}" var="register"/>

<body>

<div class="w3-row">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1>${registration}</h1>
        <form class="w3-container w3-card-2" name="form" action="/generalController" method="post">
            <input type="hidden" name="command" value="Sign_Up">

            <p><fmt:message key="lbl.Name" bundle="${rb}"/>:</p>
            <input class="w3-input w3-border" type="text" id="name" name="name" value="${requestScope.get('name')}">
            <c:if test="${requestScope.containsKey('wrongName')}">
            <span id="serverWrongName">${wrongName}</span>
            </c:if>
            <span id="wrongName">${wrongName}</span>
            <span id="emptyName">${emptyFeild}</span>

            <p><fmt:message key="lbl.Email" bundle="${rb}"/>:</p>
            <input class="w3-input w3-border" type="email" id="email" name="email" value="${requestScope.get('email')}">
            <c:if test="${requestScope.containsKey('wrongEmail')}">
            <span id="serverWrongEmail">${wrongEmail}</span>
            </c:if>
            <c:if test="${requestScope.containsKey('emailExists')}">
            <span id="serverWrongEmail">${existEmail}</span>
            </c:if>
            <span id="wrongEmail">${wrongEmail}</span>
            <span id="emptyEmail">${emptyFeild}</span>
            <br>

            <p><fmt:message key="lbl.Password" bundle="${rb}"/>:</p>
            <input class="w3-input w3-border" type="password" id="password" name="password"
                   value="${requestScope.get('password')}">
            <c:if test="${requestScope.containsKey('wrongPassword')}">
            <span id="serverWrongPassword">${wrongPassword}</span>
            </c:if>
            <span id="wrongPassword">${wrongPassword}</span>
            <span id="emptyPassword">${emptyFeild}</span>
            <br>

            <p><fmt:message key="lbl.RepeatPassword" bundle="${rb}"/>:</p>
            <input class="w3-input w3-border" type="password" id="repeatPassword" name="repeatPassword"
                   value="${requestScope.get('repeatPassword')}">
            <c:if test="${requestScope.containsKey('wrongRepeatPassword')}">
            <span id="serverWrongRepeatPassword">${notEqualsPasswords}</span>
            </c:if>
            <span id="wrongRepeatPassword">${notEqualsPasswords}</span>
            <span id="emptyRepeatPassword">${emptyFeild}</span>
            <br>
            <p><input class="w3-button w3-black" type="submit" id="submit" value="${register}"></p>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>

</form>
</body>
<%@include file="partial/footer.jsp" %>
