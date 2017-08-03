<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/17/2017
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../partial/header.jsp"/>
<body>
<div class="w3-row">
    <div class="w3-third">
        <h1>Unknown runtime error</h1>
        <h2><a class=" w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-black"
               href="${pageContext.request.contextPath}/index.jsp">Back on main page</a>
        </h2>
    </div>
</div>
</body>
<jsp:include page="../partial/footer.jsp"/>