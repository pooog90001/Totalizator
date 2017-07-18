<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/5/2017
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="ru" scope="session" />
<fmt:setBundle basename="text" var="rb" />
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<head>
    <title>$Title$</title>
</head>
<body>

<h2><fmt:message key="lbl.SignIn" bundle="${rb}"/></h2>

<form name="form" action="/generalController" method="post">
    <input type="hidden" name="command" value="Sign_in">

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

    <c:if test="${requestScope.containsKey('wrongData')}">
        <p>this email already exists</p>
    </c:if>

    <input type="submit" value="<fmt:message key="lbl.SignIn" bundle="${rb}"/>">
</form>

<script>
    var app = angular.module('app', []);
    app.controller('validateCtrl', function($scope) {
        // $scope.email = 'john.doe@gmail.com';
    });
</script>

</body>
</html>
