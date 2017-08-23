<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="w3-round">
    <div class="w3-white">
        <c:forEach var="kind" items="${kindsOfSportLeftBar}">
            <div class="w3-margin-top w3-card-2">
                <button onclick="dropDown('${kind.key.id}')" class="w3-button w3-block w3-theme-l1 w3-center">
                        ${kind.key.name}
                </button>
                <div id="" class="${kind.key.id} w3-hide ">
                    <c:forEach var="type" items="${kind.value}">
                        <form action="${pageContext.request.contextPath}/generalController">
                            <input type="hidden" name="command" value="OPEN_COMPETITIONS_BY_TYPE">
                            <input type="hidden" name="typeId" value="${type.id}">
                            <input type="hidden" name="sportId" value="${kind.key.id}">
                            <input type="submit" class="w3-button w3-block w3-theme-l2 w3-center w3-hover-dark-gray"
                                   name="submit" value="${type.name}">
                        </form>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
