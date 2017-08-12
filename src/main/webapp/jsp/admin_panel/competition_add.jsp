<%@ page contentType="text/html; charset=UTF-8" language="java" %>


<jsp:forward page="/generalController">
    <jsp:param name="command" value="OPEN_COMPETITION_SETTINGS"/>
    <jsp:param name="wrongName" value="${temporary['wrongName']}"/>
    <jsp:param name="wrongDate" value="${temporary['wrongDate']}"/>
    <jsp:param name="wrongDateFormat" value="${temporary['wrongDateFormat']}"/>
    <jsp:param name="wrongActive" value="${temporary['wrongActive']}"/>
    <jsp:param name="createError" value="${temporary['createError']}"/>
    <jsp:param name="deactivateError" value="${temporary['deactivateError']}"/>
</jsp:forward>



