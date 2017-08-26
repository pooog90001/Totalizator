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
        <h1>Change avatar</h1>

        <div class="w3-row w3-hover-light-grey w3-card-2">
            <form id='form' action="${pageContext.request.contextPath}/uploadController"
                  class="w3-card-2 w3-white w3-round w3-small" method="post" enctype="multipart/form-data">
                <input type="hidden" name="command" value="create_news">
                <input type="hidden" name="width" id="width" value="0">
                <input type="hidden" name="height" id="height" value="0">
                <input type="hidden" name="x1" id="x1" value="0">
                <input type="hidden" name="x2" id="x2" value="0">
                <input type="hidden" name="y1" id="y1" value="0">
                <input type="hidden" name="y2" id="y2" value="0">
                <div class="w3-row w3-padding-small">
                    <div class="w3-col s2 w3-padding">
                        Title:
                    </div>
                    <div class="w3-col s6">
                        <input type="text" name="title" required class="w3-input w3-border"
                               placeholder="Fill this field">
                    </div>
                </div>
                <div class="w3-row w3-padding-small">

                    <div class="w3-col s2 w3-padding">
                        Image:
                    </div>
                    <div class="w3-col s6">
                        <input type="file" required id="imgInp" name="image" accept="image/*" class="w3-button">
                    </div>

                    <div class="w3-col s12 w3-center w3-padding-large">
                        <img id="blah" src="#" class="" alt="image" style="max-width: 100%;"/>
                    </div>
                    <div class="w3-col s12" id="result">
                    </div>
                </div>
                <div class="w3-row w3-padding-small">
                    <div class="w3-col s2 w3-padding">
                        Text
                    </div>
                    <div class="w3-col s8 ">
                                <textarea name="text" id="text" cols="70" rows="10" required
                                          placeholder="Fill this field"></textarea>
                    </div>
                </div>
                <div class="w3-row w3-padding-small w3-margin-top">
                    <input type="button" id="createNews" value="Create" class="w3-button w3-theme w3">
                </div>
            </form>
            <div id="wrong" class="w3-row w3-text-red" style="display: none">
                All field must be filled, and field on image selected. Images only with JPEG, JPG, PNG
                extension.
            </div>
            <div id="errorResponse" class="w3-row w3-text-red" style="display: none">
                Database or upload image error :(
            </div>
        </div>
    </div>
    <div class="w3-third">
        <div class="w3-container"></div>
    </div>
</div>

</body>

<%@include file="/jsp/partial/footer.jsp" %>
