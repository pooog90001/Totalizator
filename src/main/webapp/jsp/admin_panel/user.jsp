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
                USERS
            </div>

            <div id="createField" class="w3-col s12 w3-container w3-padding-small" style="display: none;">
                <div class="w3-row w3-hover-light-grey w3-card-2">
                    <form action="${pageContext.request.contextPath}/generalController"
                          class="w3-card-2 w3-white w3-round w3-small" method="post">
                        <input type="hidden" name="command" value="create_kind_of_sport">
                        <div class="w3-col s4 w3-padding-small">
                            <label>
                                Kind of sport name
                                <input type="text" required name="name" class="w3-input">
                            </label>
                        </div>
                        <div class="w3-col s3 w3-padding-small">
                            <label>
                                Competitors count
                                <input type="number" required min="2" max="1000" step="1" value="2" name="count"
                                       class="w3-input">
                            </label>
                        </div>
                        <div class="w3-col s2 w3-padding-small w3-margin-top">
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

            <div class=" w3-container w3-padding-small w3-card-2 w3-center">
                <div class="w3-col s12 w3-container w3-padding-small">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round w3-medium">
                            <div class="w3-col s4 w3-padding-small">
                                <div class="w3-center">Name</div>
                            </div>
                            <div class="w3-col s3 w3-padding-small">
                                <div class="w3-center">Competitors count</div>
                            </div>
                            <div class="w3-col s3 w3-padding-small">
                            </div>
                            <div class="w3-col s2 w3-padding-small">
                                <div class="w3-center">Action</div>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
                <div id="ajaxResponse">
                    <c:forEach var="kindOfSport" items="${kindOfSportList}">
                        <div id="${kindOfSport.id}" class="w3-col s12 w3-container w3-padding-small">
                            <div class="w3-row w3-hover-light-grey">
                                <div class="w3-card-2 w3-white w3-round w3-small">
                                    <div class="w3-col s4 w3-padding-small w3-center">
                                        <div class="name">
                                                ${kindOfSport.name}
                                        </div>
                                    </div>
                                    <div class="w3-col s3 w3-padding-small w3-center">
                                        <div class="count">
                                                ${kindOfSport.competitorCount}
                                        </div>
                                    </div>
                                    <div class="w3-col s3 w3-padding-small">
                                        <div class="w3-center">

                                        </div>
                                    </div>
                                    <div class="w3-col s2">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <button onclick="edit(this,${kindOfSport.id})" type="button"
                                                    class="w3-center w3-button w3-small w3-padding-small">
                                                <i class="fa fa-edit"></i>
                                            </button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <button onclick="(modal${kindOfSport.id}).style.display='block'"
                                                    type="button" class="w3-center w3-button w3-small w3-padding-small">
                                                <i class="fa fa-remove"></i>
                                            </button>

                                            <div id="modal${kindOfSport.id}" class="w3-modal">
                                                <div class="w3-modal-content">
                                                    <div class="w3-container">
                                                        <span onclick="(modal${kindOfSport.id}).style.display='none'"
                                                              class="w3-button w3-display-topright">&times;</span>
                                                        <p>Do you really want delete?</p>
                                                        <div class="w3-row">
                                                            <div class="w3-half">
                                                                <input onclick="del(this, ${kindOfSport.id})"
                                                                       type="button" class="w3-button" value="Yes">
                                                            </div>
                                                            <div class="w3-half">
                                                                <input type="button" class="w3-button" value="NO"
                                                                       onclick="(modal${kindOfSport.id}).style.display='none'">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
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
<script src="${pageContext.request.contextPath}/js/user.js"></script>
<%@include file="../partial/footer.jsp" %>



