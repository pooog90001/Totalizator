<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:forward page="/generalController">
    <jsp:param name="command" value="open_team_settings"/>
    <jsp:param name="wrongData" value="${temporary['wrongData']}"/>
    <jsp:param name="duplicateName" value="${temporary['duplicateName']}"/>
</jsp:forward>

