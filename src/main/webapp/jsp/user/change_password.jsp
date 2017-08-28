<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/change_password.js"></script>

<fmt:message bundle="${rb}" key="warn.empty" var="emptyFeild"/>
<fmt:message bundle="${rb}" key="warn.existEmail" var="existEmail"/>
<fmt:message bundle="${rb}" key="warn.notEqualsPasswords" var="notEqualsPasswords"/>
<fmt:message bundle="${rb}" key="warn.wrongEmail" var="wrongEmail"/>
<fmt:message bundle="${rb}" key="warn.wrongPassword" var="wrongPassword"/>
<fmt:message bundle="${rb}" key="warn.wrongName" var="wrongName"/>
<fmt:message bundle="${rb}" key="lbl.Registration" var="registration"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="name"/>
<fmt:message bundle="${rb}" key="lbl.Email" var="email"/>
<fmt:message bundle="${rb}" key="lbl.Password" var="password"/>
<fmt:message bundle="${rb}" key="lbl.RepeatPassword" var="repeatPassword"/>

<body>
<div class="w3-row-padding">
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <div class="w3-third">
        <div class="w3-row">
            <div class="w3-col s12 w3-display-container">
                <div class="w3-display-right">
                    <button type="button" class="w3-button w3-card-4 w3-circle w3-theme-l3"
                            style="padding: 15px 17px;" onclick="history.back(); return false;">
                        <i class='fa fa-long-arrow-left'></i>
                    </button>
                </div>
                <h1>Change password</h1>
            </div>
        </div>

        <form class="w3-container w3-card-2" name="form" method="post" id="form">
            <input type="hidden" name="command" value="CHANGE_PASSWORD">

            <p><c:out value="${password}"/>:*</p>
            <input title="${password}" class="w3-input w3-border" type="password" id="oldPassword" name="oldPassword">
            <span class="wrong w3-text-red" id="emptyOldPassword"><c:out value="${emptyFeild}"/></span>
            <br>

            <p>New password:*</p>
            <input title="${password}" class="w3-input w3-border" type="password" id="password" name="password">

            <span class="wrong w3-text-red" id="wrongPassword"><c:out value="${wrongPassword}"/></span>
            <span class="wrong w3-text-red" id="emptyPassword"><c:out value="${emptyFeild}"/></span>
            <br>

            <p>Repeat new password:*</p>
            <input title="${repeatPassword}" class="w3-input w3-border" type="password" id="repeatPassword"
                   name="repeatPassword">

            <span class="wrong w3-text-red" id="wrongRepeatPassword">${notEqualsPasswords}</span>
            <span class="wrong w3-text-red" id="emptyRepeatPassword">${emptyFeild}</span>
            <span class="wrong w3-text-red" id="equalsPasswords">Old and new passwords equals</span>
            <span class="wrong w3-text-red" id="wrongOldPassword">Wrong old password</span>
            <span class="wrong w3-text-red" id="wrongDB">Database error</span>
            <span class="wrong w3-text-red" id="errorResponse">Server Error</span>
            <br>
            <input class="w3-button w3-black" type="button" id="submit" value="Change password">

        </form>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <form id="openProfile" action="${pageContext.request.contextPath}/generalController" method="post">
        <input type="hidden" name="command" value="OPEN_PROFILE">
    </form>
</div>

</body>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<%@include file="/jsp/partial/footer.jsp" %>
