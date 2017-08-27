<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_in.js"></script>

<fmt:message key="lbl.ForgotPassword" bundle="${rb}" var="forgotPassword"/>
<fmt:message key="lbl.SignIn" bundle="${rb}" var="signIn"/>
<fmt:message key="lbl.Authorization" bundle="${rb}" var="authorization"/>
<fmt:message key="lbl.Password" bundle="${rb}" var="password"/>
<fmt:message key="lbl.Email" bundle="${rb}" var="email"/>
<fmt:message key="warn.wrongEmailorPassword" bundle="${rb}" var="wrongData"/>

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
                <h1>Change avatar</h1>
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
                        Image:
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
                    <input type="button" id="createNews" value="Accept" class="w3-button w3-theme w3">
                </div>
            </form>
            <div id="wrongData" class="wrong w3-row w3-text-red" style="display: none">
                Please, fill all fields. And select region on image too.
            </div>
            <div id="wrongDB" class="wrong w3-row w3-text-red" style="display: none">
                Data base error;
            </div>
            <div id="wrongUpload" class="wrong w3-row w3-text-red" style="display: none">
                Upload error.
            </div>
            <div id="accessDenied" class="wrong w3-row w3-text-red" style="display: none">
                You session is empty. Reload page, and try again.
            </div>
            <div id="errorResponse" class="wrong w3-row w3-text-red" style="display: none">
                Database or upload image error. Max image size 10 MB.
            </div>
        </div>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
    <form id="openProfile" action="${pageContext.request.contextPath}/generalController" method="post">
        <input type="hidden" name="command" value="OPEN_PROFILE">
    </form>
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
