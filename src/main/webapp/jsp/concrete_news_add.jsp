<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:forward page="/generalController">
    <jsp:param name="command" value="open_concrete_news"/>
    <jsp:param name="newsId" value="${temporary['newsId']}"/>
    <jsp:param name="accessDenied" value="${temporary['accessDenied']}"/>
    <jsp:param name="invalidText" value="${temporary['invalidText']}"/>
</jsp:forward>

