<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.before.delete.competition" var="txtBeforeDelete"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.symbols" var="txtSymbols"/>
<fmt:message bundle="${rb}" key="txt.all.fields.be.filled" var="txtAllFieldsMustFilled"/>
<fmt:message bundle="${rb}" key="txt.competition.type.not.correct" var="txtTypeNotCorrect"/>
<fmt:message bundle="${rb}" key="txt.delete.error" var="txtDeleteError"/>
<fmt:message bundle="${rb}" key="txt.try.again" var="txtTryAgain"/>
<fmt:message bundle="${rb}" key="txt.wrong.access" var="txtWrongAccess"/>
<fmt:message bundle="${rb}" key="txt.delete.failed" var="txtDeleteFailed"/>
<fmt:message bundle="${rb}" key="txt.edit.error" var="txtEditError"/>
<fmt:message bundle="${rb}" key="txt.wrong.number.format" var="txtWrongNumberFormat"/>
<fmt:message bundle="${rb}" key="txt.some.kind.mistake" var="txtSomeKindMistake"/>
<fmt:message bundle="${rb}" key="txt.check.input.data" var="txtCheckInputData"/>
<fmt:message bundle="${rb}" key="lbl.or" var="txtOr"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.no" var="txtNo"/>
<fmt:message bundle="${rb}" key="txt.invalid.value" var="txtInvalidValue"/>
<fmt:message bundle="${rb}" key="txt.without" var="txtWithout"/>
<fmt:message bundle="${rb}" key="txt.name.already.exist" var="txtNameAlreadyExist"/>
<fmt:message bundle="${rb}" key="txt.users" var="txtUsers"/>
<fmt:message bundle="${rb}" key="txt.user" var="txtUser"/>
<fmt:message bundle="${rb}" key="txt.admin" var="txtAdmin"/>
<fmt:message bundle="${rb}" key="txt.bookmaker" var="txtBookmaker"/>
<fmt:message bundle="${rb}" key="txt.cash" var="txtCash"/>
<fmt:message bundle="${rb}" key="txt.role" var="txtRole"/>
<fmt:message bundle="${rb}" key="txt.email" var="txtEmail"/>
<fmt:message bundle="${rb}" key="txt.blocked" var="txtBlocked"/>
<fmt:message bundle="${rb}" key="txt.blocked.text" var="txtBlockedText"/>
<fmt:message bundle="${rb}" key="txt.before.unlock.user" var="txtBeforeUnlockUser"/>
<fmt:message bundle="${rb}" key="txt.before.block.user" var="txtBeforeBlockUser"/>
<fmt:message bundle="${rb}" key="txt.blocking.reason" var="txtBlockingReason"/>
<fmt:message bundle="${rb}" key="txt.select.role" var="txtSelectRole"/>
<fmt:message bundle="${rb}" key="txt.change.role.wrong" var="txtChangeWrongRole"/>
<fmt:message bundle="${rb}" key="txt.change.lock.wrong" var="txtChangeWrongLock"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="txtName"/>
<fmt:message bundle="${rb}" key="txt.error.check.connection" var="txtErrorCheckConnection"/>

<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="/jsp/bar/admin_left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="/jsp/bar/admin_left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge">
                ${txtUsers}
            </div>

            <div class="w3-col s12">
                <div class="w3-container w3-padding-small w3-card-2 w3-center">
                    <c:forEach var="userEntity" items="${userList}">
                        <div id='${userEntity.id}' class="w3-container w3-margin-top w3-half ">
                            <div class="w3-row w3-card-2 w3-display-container">


                                <c:if test="${userEntity.id != user.id}">
                                    <div class="w3-display-topright w3-display-hover w3-small">
                                        <c:if test="${user.type.toString().equals('BOOKMAKER')}">
                                            <button class="w3-button w3-black w3-padding-small"
                                                    onclick="(modal_change_role${userEntity.id}).style.display='block'">
                                                <i class="fa fa-users"></i>
                                            </button>
                                            <c:choose>
                                                <c:when test="${userEntity.isBlocked}">
                                                    <button class="w3-button w3-black w3-padding-small"
                                                            onclick="(modal_change_lock${userEntity.id}).style.display='block'">
                                                        <i class="fa fa-plus-square w3-text-green"></i>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="w3-button w3-black w3-padding-small"
                                                            onclick="(modal_change_lock${userEntity.id}).style.display='block'">
                                                        <i class="fa fa-minus-square w3-text-red"></i>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                    </div>
                                </c:if>


                                <div id="modal_change_lock${userEntity.id}" class="w3-modal w3-center">
                                    <div class="w3-modal-content">
                                        <div class="w3-container">
                                            <span onclick="(modal_change_lock${userEntity.id}).style.display='none'"
                                                  class="w3-button w3-display-topright">
                                                &times;
                                            </span>
                                            <form id="lockForm${userEntity.id}" method="post" action="/ajaxController"
                                                  onsubmit="return false">
                                                <input type="hidden" name="command" value="CHANGE_USER_LOCK">
                                                <input type="hidden" name="userId" value="${userEntity.id}">
                                                <input type="hidden" name="blockState" value="${userEntity.isBlocked}">
                                                <c:choose>
                                                    <c:when test="${userEntity.isBlocked}">
                                                        <p> ${txtBeforeUnlockUser}</p>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <p> ${txtBeforeBlockUser}</p>
                                                        <p>
                                                            <span>${txtBlockingReason}:</span>
                                                            <input type="text" name="textBlock" required>
                                                        </p>
                                                    </c:otherwise>
                                                </c:choose>
                                            </form>
                                            <div class="w3-row">
                                                <div class="w3-half">
                                                    <input onclick="
                                                            changeLock(this,'lockForm${userEntity.id}', 'modal_change_lock${userEntity.id}');"
                                                           type="button"
                                                           class="w3-button" value="${txtYes}">
                                                </div>
                                                <div class="w3-half">
                                                    <input type="button" class="w3-button" value="${txtNo}"
                                                           onclick="(modal_change_lock${userEntity.id}).style.display='none'">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div id="modal_change_role${userEntity.id}" class="w3-modal w3-center">
                                    <div class="w3-modal-content">
                                        <div class="w3-container">
                                            <span onclick="(modal_change_role${userEntity.id}).style.display='none'"
                                                  class="w3-button w3-display-topright">
                                                &times;
                                            </span>
                                            <p>${txtSelectRole}:</p>
                                            <p>
                                            <form method="post" id="roleForm${userEntity.id}" action="/ajaxController"
                                                  onsubmit="return false">
                                                <input type="hidden" name="command" value="CHANGE_USER_ROLE">
                                                <input type="hidden" name="userId" value="${userEntity.id}">
                                                <label class="w3-padding-small">${txtUser}
                                                    <input type="radio" class="w3-radio" name="userType" value="USER"
                                                           required>
                                                </label>
                                                <label class="w3-padding-small">${txtAdmin}
                                                    <input type="radio" class="w3-radio" name="userType" value="ADMIN">
                                                </label>
                                                <label class="w3-padding-small">${txtBookmaker}
                                                    <input type="radio" class="w3-radio" name="userType"
                                                           value="BOOKMAKER">
                                                </label>
                                            </form>
                                            </p>
                                            <div class="w3-row">
                                                <div class="w3-half">
                                                    <input onclick="changeRole(this, 'roleForm${userEntity.id}', 'modal_change_role${userEntity.id}')"
                                                           type="button"
                                                           class="w3-button" value="${txtYes}">
                                                </div>
                                                <div class="w3-half">
                                                    <input type="button" class="w3-button" value="${txtNo}"
                                                           onclick="(modal_change_role${userEntity.id}).style.display='none'">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="w3-col s4">
                                    <div class=" w3-padding">
                                        <img src="${userImagePath}${userEntity.avatarURL}" alt="${userEntity.name}"
                                             class="w3-image w3-circle" style="width: 100%">
                                    </div>
                                </div>
                                <div class="w3-col s8">
                                    <div class="w3-row">
                                        <div class="w3-col s12 w3-left-align w3-small ">
                                            <div style="margin: 4px">
                                                <i>${txtName}: </i>
                                                <b> <c:out value="${userEntity.name}"/> </b>
                                                <i>${txtCash}: </i>
                                                <b> <ctg:decimal-presenter number="${userEntity.cash}"/>$ </b>
                                            </div>
                                            <div style="margin: 4px">
                                                <i>${txtRole}: </i>
                                                <b> <c:out value="${userEntity.type}"/> </b>
                                            </div>
                                            <div style="margin: 4px">
                                                <i>${txtEmail}: </i>
                                                <b> <c:out value="${userEntity.email}"/> </b>
                                            </div>
                                            <div style="margin: 4px">
                                                <i>${txtBlocked}: </i>
                                                <b> <c:out value="${userEntity.isBlocked}"/> </b>
                                            </div>
                                            <c:if test="${userEntity.isBlocked}">
                                                <div style="margin: 4px">
                                                    <i>${txtBlockedText}: </i>
                                                    <b> <c:out value="${userEntity.blockedText}"/> </b>
                                                </div>
                                            </c:if>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="pagination  w3-margin-top w3-row w3-container w3-center">
                    <ctg:pagination total="${usersCount}" limit="${limit}" command="open_user_settings"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="modal_role_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_role_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtErrorCheckConnection}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_role_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_role_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_role_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtChangeWrongRole}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_role_wrong).style.display='none'">
        </div>
    </div>
</div>


<div id="modal_lock_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_lock_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtErrorCheckConnection}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_lock_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_lock_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_lock_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtChangeWrongLock}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_lock_wrong).style.display='none'">
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/user.js"></script>
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



