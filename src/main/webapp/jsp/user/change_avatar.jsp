<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_in.js"></script>

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
<fmt:message bundle="${rb}" key="txt.avatar.changed" var="txtAvatarChanged"/>

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
                <h1>${txtChangeAvatar}</h1>
            </div>
        </div>
        <div class="w3-row w3-hover-light-grey w3-card-2">
            <form id="form" action="${pageContext.request.contextPath}/uploadController"
                  class="w3-card-2 w3-white w3-round w3-small" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="change_avatar">
                <input type="hidden" name="width" id="width" value="0">
                <input type="hidden" name="height" id="height" value="0">
                <input type="hidden" name="x1" id="x1" value="0">
                <input type="hidden" name="x2" id="x2" value="0">
                <input type="hidden" name="y1" id="y1" value="0">
                <input type="hidden" name="y2" id="y2" value="0">

                <div class="w3-row w3-padding-small">

                    <div class="w3-col s2 w3-padding">
                        ${txtImage}:
                    </div>
                    <div class="w3-col s6">
                        <input type="file" required id="imgInp" name="image" accept="image/*" class="w3-button">
                    </div>

                    <div class="w3-col s12 w3-center w3-padding-large">
                        <div class="w3-border" style="min-height: 300px;">
                            <img id="blah" src="#" class="" style="max-width: 100%;"/>
                        </div>
                    </div>
                    <div class="w3-col s12" id="result">
                    </div>
                </div>
                <div class="w3-row w3-padding-small w3-margin-top">
                    <input type="button" id="createNews" value="${txtOk}" class="w3-button w3-theme w3">
                </div>
            </form>
            <div id="wrongData" class="wrong w3-row w3-text-red" style="display: none">
                ${txtFillAllFields}. ${txtSelectRegion}.
            </div>
            <div id="wrongDB" class="wrong w3-row w3-text-red" style="display: none">
                ${txtDatabaseError}.
            </div>
            <div id="wrongUpload" class="wrong w3-row w3-text-red" style="display: none">
                ${txtUploadError}.
            </div>
            <div id="accessDenied" class="wrong w3-row w3-text-red" style="display: none">
                ${txtSessionError}.
            </div>
            <div id="errorResponse" class="wrong w3-row w3-text-red" style="display: none">
                ${txtDatabaseOrUploadError}.
            </div>
        </div>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>

<div id="success" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(success).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtAvatarChanged}</p>
            <form id="openProfile" action="${pageContext.request.contextPath}/generalController" method="get">
                <input type="hidden" name="command" value="OPEN_PROFILE">
                <input type="submit" class="w3-button" value="${txtOk}">
            </form>
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/change_avatar.js"></script>
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
