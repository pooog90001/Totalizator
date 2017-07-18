<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/12/2017
  Time: 7:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="ru" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<%--<c:set var="request" value="${pageContext.request}" scope="request"/>--%>

<html>
<head>
    <title>Title</title>
    <%--<script src="javascript/jquery.js"></script>--%>
</head>
<body>
<h1><fmt:message key="lbl.Registration" bundle="${rb}"/></h1>
    <form name="form" action="/generalController" method="post">
        <input type="hidden" name="command" value="Sign_Up">

        <p><fmt:message key="lbl.Name" bundle="${rb}"/>:</p>
        <input type="text" name="name" value="${requestScope.get('name')}">
        <c:if test="${requestScope.containsKey('wrongName')}">
            <p>Bad name</p>
        </c:if>

        <p><fmt:message key="lbl.Email" bundle="${rb}"/>:</p>
        <input type="email" name="email" value="${requestScope.get('email')}">
        <c:if test="${requestScope.containsKey('wrongEmail')}">
            <p>Bad email</p>
        </c:if>

        <p><fmt:message key="lbl.Password" bundle="${rb}"/>:</p>
        <input type="password" name="password" value="${requestScope.get('password')}" >
        <c:if test="${requestScope.containsKey('wrongPassword')}">
            <p>Bad password</p>
        </c:if>


        <p><fmt:message key="lbl.RepeatPassword" bundle="${rb}"/>:</p>
        <input type="password" name="repeatPassword" value="${requestScope.get('repeatPassword')}">
        <c:if test="${requestScope.containsKey('wrongRepeatPassword')}">
            <p>Bad repeat password</p>
        </c:if>

        <c:if test="${requestScope.containsKey('emailExists')}">
            <p>this email already exists</p>
        </c:if>

        <input type="submit" value="<fmt:message key="btn.Register" bundle="${rb}"/>">
    </form>
</body>
</html>
