<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/17/2017
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
    <head>
        <title>Error Page</title>
    </head>
    <body>
        Request from ${pageContext.errorData.requestURI} is failed <br>
        Servlet name: ${pageContext.errorData.servletName} <br>
        Status code: ${pageContext.errorData.statusCode} <br>
        Exception: ${pageContext.exception} <br>
        Message from exception: ${pageContext.exception.message}
    </body>
</html>
