<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="w3-card-2 w3-round">
    <div class="w3-white">
        <c:forEach var="kind" items="${requestScope.get('kindsOfSport')}">
            <button onclick="myFunction('${kind.key}')" class="w3-button w3-block w3-theme-l1 w3-center">
                    ${kind.key}
            </button>
            <div id="${kind.key}" class="w3-hide w3-container">
                <c:forEach var="competition" items="${kind.value}">
                    <form action="${pageContext.request.contextPath}/generalController">
                        <input type="hidden" name="open_competition_type" value="${competition.value}">
                        <input type="submit" class="w3-button w3-block w3-theme-l3 w3-center"
                               name="submit" value="${competition.key}">
                    </form>
                </c:forEach>
            </div>
        </c:forEach>

    </div>
</div>
