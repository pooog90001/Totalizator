<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:forward page="/generalController">
    <jsp:param name="command" value="open_kind_of_sport_settings"/>
    <jsp:param name="wrongName" value="${temporary['wrongName']}"/>
    <jsp:param name="duplicateName" value="${temporary['duplicateName']}"/>
    <jsp:param name="wrongCount" value="${temporary['wrongCount']}"/>
</jsp:forward>

