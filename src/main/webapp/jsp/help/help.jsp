<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>

<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.wrong.data.check.fields" var="txtWrongDataCheckFields"/>
<fmt:message bundle="${rb}" key="txt.success.email" var="txtSuccessEmail"/>
<fmt:message bundle="${rb}" key="txt.your.email" var="txtYourEmail"/>
<fmt:message bundle="${rb}" key="txt.title.help" var="txtTitleHelp"/>
<fmt:message bundle="${rb}" key="txt.question" var="txtQuestion"/>
<fmt:message bundle="${rb}" key="txt.help" var="txtHelp"/>

<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="/jsp/bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <!-- Accordion -->
        <%@include file="/jsp/bar/left_bar.jsp" %>
        <!-- End Left Column -->
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9 w3-container">

        <!-- First Photo Grid-->
        <div class=" w3-container">
            <span class="w3-xlarge w3-padding w3-hover-none">
                ${txtHelp}
            </span>
        </div>
        <div class="w3-card-2 w3-container">
            <h3>${txtTitleHelp}</h3>

            <form class="w3-container" action="${pageContext.request.contextPath}/generalController" method="post">
                <input class="w3-input w3-border" type="hidden" name="command" value="SEND_QUESTION_EMAIL"/>
                <label>
                    ${txtYourEmail}:
                    <input class="w3-input w3-border" type="email" name="email" required style="max-width: 250px;">
                </label>
                <br>
                <label>
                    ${txtQuestion}:
                    <textarea class="w3-input w3-border" name="text" id="text" cols="30" rows="3" required
                              style="resize: none;" maxlength="1000"></textarea>
                </label>
                <br>
                <input type="submit" value="Send" class="w3-button w3-theme-l3"/>
                <c:if test="${temporary['wrongData']}">
                    <span class="w3-text-red"> ${txtWrongDataCheckFields} </span>
                </c:if>
                <c:if test="${temporary['success']}">
                    <span class="w3-text-green"> ${txtSuccessEmail}</span>
                </c:if>

            </form>
        </div>

    </div>
</div>
</body>

<%@include file="/jsp/partial/footer.jsp" %>
