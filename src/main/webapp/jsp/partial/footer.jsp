<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.totalizator" var="txtTotalizator"/>
<fmt:message bundle="${rb}" key="txt.all.right.reserved" var="txtAllRightReserved"/>

<footer class="footer w3-container w3-theme-d3 w3-padding-16 w3-display-container">
    <h5>&copy; ${txtTotalizator}. ${txtAllRightReserved}.</h5>
    <div class="w3-display-topright">
            <input type="button" class="w3-button" name="locale" value="RU" onclick="changeLanguage(this)">
            <input type="button" class="w3-button" name="locale" value="EN" onclick="changeLanguage(this)">
    </div>
</footer>

<script src="${pageContext.request.contextPath}/js/w3.js"></script>

</html>
