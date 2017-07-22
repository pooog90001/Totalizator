
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<jsp:include page="../partial/header.jsp"/>
<body>
<div class="w3-row">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <h1>500</h1>
        <h2><a class=" w3-button w3-hover-none w3-border-white w3-bottombar w3-hover-border-black"
               href="${pageContext.request.contextPath}/index.jsp">Back on main page</a>
        </h2>
        Request from ${pageContext.errorData.requestURI} is failed <br>
        Servlet name: ${pageContext.errorData.servletName} <br>
        Status code: ${pageContext.errorData.statusCode} <br>
        Exception: ${pageContext.exception} <br>
        Message from exception: ${pageContext.exception.message}
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>
</body>

<jsp:include page="../partial/footer.jsp"/>
