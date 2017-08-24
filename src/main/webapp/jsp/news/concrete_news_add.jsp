<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:forward page="/generalController">
    <jsp:param name="command"           value="OPEN_CONCRETE_NEWS"/>
    <jsp:param name="newsId"            value="${temporary['newsId']}"/>
    <jsp:param name="invalidText"       value="${temporary['invalidText']}"/>
</jsp:forward>

