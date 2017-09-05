<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/change_password.js"></script>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.back.on.main.page" var="txtBackOnMainPage"/>
<fmt:message bundle="${rb}" key="txt.account.blocked" var="txtAccountBlocked"/>
<fmt:message bundle="${rb}" key="txt.change.avatar" var="txtChangeAvatar"/>
<fmt:message bundle="${rb}" key="txt.fill.all.fields" var="txtFillAllFields"/>
<fmt:message bundle="${rb}" key="txt.image" var="txtImage"/>
<fmt:message bundle="${rb}" key="txt.select.region" var="txtSelectRegion"/>
<fmt:message bundle="${rb}" key="txt.database.error" var="txtDatabaseError"/>
<fmt:message bundle="${rb}" key="txt.upload.error" var="txtUploadError"/>
<fmt:message bundle="${rb}" key="txt.session.is.empty" var="txtSessionError"/>
<fmt:message bundle="${rb}" key="txt.database.or.upload.error" var="txtDatabaseOrUploadError"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.old.new.password.equals" var="txtOldNewPasswordEquals"/>
<fmt:message bundle="${rb}" key="txt.wrong.old.password" var="txtWrongOldPassword"/>
<fmt:message bundle="${rb}" key="txt.error.check.connection" var="txtError"/>

<fmt:message bundle="${rb}" key="warn.empty" var="emptyFeild"/>
<fmt:message bundle="${rb}" key="warn.notEqualsPasswords" var="notEqualsPasswords"/>
<fmt:message bundle="${rb}" key="warn.wrongPassword" var="wrongPassword"/>
<fmt:message bundle="${rb}" key="warn.wrongName" var="wrongName"/>
<fmt:message bundle="${rb}" key="lbl.Password" var="password"/>
<fmt:message bundle="${rb}" key="lbl.RepeatPassword" var="repeatPassword"/>
<fmt:message bundle="${rb}" key="txt.repeat.new.password" var="txtRepeatNewPassword"/>
<fmt:message bundle="${rb}" key="txt.new.password" var="txtNewPassword"/>
<fmt:message bundle="${rb}" key="txt.change.password" var="txtChangePassword"/>
<fmt:message bundle="${rb}" key="txt.password.changed" var="txtPasswordChanged"/>

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
                <h1>${txtChangePassword}</h1>
            </div>
        </div>

        <form class="w3-container w3-card-2" name="form" method="post" id="form">
            <input type="hidden" name="command" value="CHANGE_PASSWORD">

            <p><c:out value="${password}"/>:*</p>
            <input title="${password}" class="w3-input w3-border" type="password" id="oldPassword" name="oldPassword">
            <span class="wrong w3-text-red" id="emptyOldPassword"><c:out value="${emptyFeild}"/></span>
            <br>

            <p>${txtNewPassword}:*</p>
            <input title="${password}" class="w3-input w3-border" type="password" id="password" name="password">

            <span class="wrong w3-text-red" id="wrongPassword"><c:out value="${wrongPassword}"/></span>
            <span class="wrong w3-text-red" id="emptyPassword"><c:out value="${emptyFeild}"/></span>
            <br>

            <p>${txtRepeatNewPassword}:*</p>
            <input title="${repeatPassword}" class="w3-input w3-border" type="password" id="repeatPassword"
                   name="repeatPassword">

            <span class="wrong w3-text-red" id="wrongRepeatPassword">${notEqualsPasswords}</span>
            <span class="wrong w3-text-red" id="emptyRepeatPassword">${emptyFeild}</span>
            <span class="wrong w3-text-red" id="equalsPasswords">${txtOldNewPasswordEquals}</span>
            <span class="wrong w3-text-red" id="wrongOldPassword">${txtWrongOldPassword}</span>
            <span class="wrong w3-text-red" id="wrongDB">${txtDatabaseError}</span>
            <span class="wrong w3-text-red" id="errorResponse">${txtError}</span>
            <br>
            <input class="w3-button w3-black" type="button" id="submit" value="Change password">

        </form>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>

</div>

<div id="success" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(success).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtPasswordChanged}</p>
            <form id="openProfile" action="${pageContext.request.contextPath}/generalController" method="get">
                <input type="hidden" name="command" value="OPEN_PROFILE">
                <input type="submit" class="w3-button" value="${txtOk}">
            </form>
        </div>
    </div>
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
