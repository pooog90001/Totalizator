<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="w3-round">
    <div class="w3-white">
        <c:forEach var="kind" items="${kindsOfSportLeftBar}">
            <div class="w3-margin-top w3-card-2">
                <button onclick="myFunction('${kind.key}')" class="w3-button w3-block w3-theme-l1 w3-center">
                        ${kind.key}
                </button>
                <div id="" class="${kind.key} w3-hide ">
                    <c:forEach var="competition" items="${kind.value}">
                        <form action="${pageContext.request.contextPath}/generalController">
                            <input type="hidden" name="open_competition_type" value="${competition.value}">
                            <input type="submit" class="w3-button w3-block w3-theme-l2 w3-center w3-hover-dark-gray"
                                   name="submit" value="${competition.key}">
                        </form>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
