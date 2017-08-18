<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../partial/header.jsp" %>



<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="../bar/admin_left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="../bar/admin_left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge w3-display-container">
                <div class="w3-display-right w3-padding">
                    <button id="create" type="button" class="w3-button w3-card-4 w3-circle w3-theme-l1"
                            style="padding: 15px 17px;">
                        <i class='fa fa-plus-circle'></i>
                    </button>
                </div>
                NEWS
            </div>

            <div id="createField" class="w3-col s12 w3-container w3-padding-small" style="display: none;">
                <div class="w3-row w3-hover-light-grey w3-card-2">
                    <form action="${pageContext.request.contextPath}/uploadController"
                          class="w3-card-2 w3-white w3-round w3-small" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="create_news" >
                        <input type="hidden" name="width" id="width" value="0" >
                        <input type="hidden" name="height" id="height" value="0" >
                        <input type="hidden" name="x1" id="x1" value="0" >
                        <input type="hidden" name="x2" id="x2" value="0" >
                        <input type="hidden" name="y1" id="y1" value="0" >
                        <input type="hidden" name="y2" id="y2" value="0" >
                        <div class="w3-row w3-padding-small">
                            <div class="w3-col s2 w3-padding">
                                Title:
                            </div>
                            <div class="w3-col s6">
                                <input type="text"  name="title" required class="w3-input w3-border" placeholder="Fill this field">
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small">

                            <div class="w3-col s2 w3-padding">
                                Image:
                            </div>
                            <div class="w3-col s6">
                                <input type="file" required id="imgInp" name="image"  accept="image/*" class="w3-button">
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
                                <textarea name="text" id="text" cols="70" rows="10" placeholder="Fill this field"></textarea>
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small w3-margin-top">
                            <input type="submit" value="Create" class="w3-button w3-theme w3">
                        </div>
                    </form>
                    <c:if test="${requestScope.get('wrongName') != null}">
                        <div id="wrong" class="w3-row w3-text-red">Name must be 1-45 symbols</div>
                    </c:if>
                    <c:if test="${requestScope.get('duplicateName') != null}">
                        <div id="wrong" class="w3-row w3-text-red">This competition already exist</div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongCount') != null}">
                        <div id="wrong" class="w3-row w3-text-red">Number of competitors must be 2-1000</div>
                    </c:if>
                </div>
            </div>
            <div class="w3-row">
                <div class=" w3-container w3-padding-small w3-card-2 w3-center">

                </div>
            </div>
        </div>
    </div>
</div>


<div id="modal_del_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Delete error. try again</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_del_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_del_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>This kind of sport use others tables.
                To be able to remove this sport, it is necessary that it is not used by anyone</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_del_wrong).style.display='none'">
        </div>
    </div>
</div>


<div id="modal_edit_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_edit_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Edit error. Try again</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_edit_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_edit_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_edit_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>This kind of sport already exist or invalid value. Must be 1-45 symbols </p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_edit_wrong).style.display='none'">
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/news.js"></script>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
<%@include file="../partial/footer.jsp" %>




