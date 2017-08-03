<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:forward page="/generalController">
    <jsp:param name="command" value="open_competition_type_settings"/>
    <jsp:param name="wrongName" value="${temporary['wrongName']}"/>
    <jsp:param name="duplicateName" value="${temporary['duplicateName']}"/>
</jsp:forward>


