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
                Competitions
            </div>

            <%--Start create block--%>
            <div id="createField" class="w3-col s12 w3-container w3-padding-small" style="display: none;">
                <div class="w3-row w3-card-2 w3-margin-bottom">
                    <form action="${pageContext.request.contextPath}/generalController"
                          class=" w3-white w3-round w3-small" method="post" onsubmit="return checkBeforeCreate(this);">
                        <input type="hidden" name="command" value="create_competition">
                        <div class="w3-row">
                            <div class="w3-col s4 w3-padding-small">
                                <label>
                                    Sport name
                                    <select id="selectKind" required name="sportId" class="w3-select">
                                        <option value='' selected disabled> Choose sport</option>
                                        <c:forEach var="kind" items="${kindsOfSport}">
                                            <option value="${kind.id}" count="${kind.competitorCount}">${kind.name}</option>
                                        </c:forEach>
                                    </select>

                                </label>
                            </div>
                            <div class="w3-col s4 w3-padding-small">
                                <label>
                                    Competition type
                                    <select id="competitionType" required name="typeId" class="w3-select">
                                        <option value='' selected disabled> Choose competition type</option>
                                        <c:forEach var="type" items="${competitionTypes}">
                                            <option value="${type.id}">${type.name}</option>
                                        </c:forEach>
                                    </select>

                                </label>
                            </div>
                            <div class="w3-col s4 w3-padding-small">
                                <label>
                                    Competition name
                                    <input type="text" class="w3-input" id="competitionName" name="competitionName"
                                           maxlength="100" required >
                                </label>
                            </div>
                            <div class="w3-row">
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        Date start
                                        <input id='dateStart' type="date" class="w3-input" name="dateStart"
                                               required>
                                    </label>
                                </div>
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        Time start
                                        <input id='timeStart' type="time" class="w3-input" name="timeStart"
                                               required>
                                    </label>
                                </div>
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        Date finish
                                        <input id='dateFinish' type="date" class="w3-input" name="dateFinish" required/>

                                    </label>
                                </div>
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        Time finish
                                        <input id='timeFinish' type="time" class="w3-input" name="timeFinish"
                                               required>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div id="createAdd">

                        </div>
                        <div class="w3-col s2 w3-padding-small w3-margin-top">
                            <input type="submit" id="createSubmit" value="Create" class="w3-button w3-theme w3">
                        </div>

                        <c:if test="${user.type.toString().equals('BOOKMAKER')}">
                            <div class="w3-col s2 w3-padding-small w3-margin-top">
                                <label>
                                    Active
                                    <input type="checkbox" class="w3-check" id="isActive" name="isActive">
                                </label>
                            </div>
                        </c:if>
                        <div id="errorBox" class="w3-text-red" style="display: none;">All fields must be defined</div>
                    </form>
                    <c:if test="${requestScope.get('wrongName') != null}">
                        <div id="wrong" class="w3-row w3-text-red">Name must be 1-45 symbols</div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongDate') != null}">
                        <div id="wrong" class="w3-row w3-text-red">Date start can't be later date finish and earlier
                            now
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongDateFormat') != null}">
                        <div id="wrong" class="w3-row w3-text-red">Check Date format</div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongActive') != null}">
                        <div id="wrong" class="w3-row w3-text-red">For activate competition all fields must be filled
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('createError') != null}">
                        <div id="wrong" class="w3-row w3-text-red">Transaction error, check commands for duplicate</div>
                    </c:if>
                    <div id="wrongJS" class="w3-row w3-text-red" style="display: none">All fields must by filled and
                        commands mustn't duplicate
                    </div>
                </div>
            </div>
            <%--End create block--%>


            <div class=" w3-container w3-padding-small w3-card-2">

                <%--Start main headers tab block--%>
                <div class="w3-bar w3-black">
                    <button class="w3-bar-item w3-button generalLink w3-white"
                            onclick="openTab(event,'Upcoming', 'general', 'generalLink')">
                        Upcoming
                    </button>
                    <button class="w3-bar-item w3-button generalLink"
                            onclick="openTab(event,'Past', 'general', 'generalLink')">
                        Past
                    </button>
                    <button class="w3-bar-item w3-button generalLink"
                            onclick="openTab(event,'Now', 'general', 'generalLink')">
                        Now
                    </button>
                </div>
                <%--End main headers tab block--%>
                <hr>

                <%--Start upcoming  tab block--%>
                <div id="Upcoming" class="general">
                    <div class="w3-bar w3-black">
                        <button class="w3-bar-item w3-button upcomingLink w3-white"
                                onclick="openTab(event,'Activated', 'upcoming', 'upcomingLink')">
                            Activated
                        </button>
                        <button class="w3-bar-item w3-button upcomingLink"
                                onclick="openTab(event,'Deactivated', 'upcoming', 'upcomingLink')">
                            Deactivated
                        </button>
                    </div>

                    <%--Activated competitions--%>
                    <%@include file="competition_part/upcoming_activated.jsp" %>

                    <%--Deactivated competitions--%>
                    <%@include file="competition_part/upcoming_deactivated.jsp" %>

                </div>

                <div id="Past" class="general" style="display:none">
                    <div class="w3-bar w3-black">
                        <button class="w3-bar-item w3-button pastLink w3-white"
                                onclick="openTab(event,'PastFilled', 'past', 'pastLink')">
                            Filled
                        </button>
                        <button class="w3-bar-item w3-button pastLink"
                                onclick="openTab(event,'PastUnfilled', 'past', 'pastLink')">
                            Unfilled
                        </button>
                        <button class="w3-bar-item w3-button pastLink"
                                onclick="openTab(event,'PastDeactivated', 'past', 'pastLink')">
                            Deactivated
                        </button>
                    </div>

                    <%--Filled competitions--%>
                    <%@include file="competition_part/past_filled.jsp" %>

                    <%--Unfilled competitions--%>
                    <%@include file="competition_part/past_unfilled.jsp" %>

                    <%--Deactivated competitions--%>
                    <%@include file="competition_part/past_deactivated.jsp" %>
                </div>

                <div id="Now" class="general" style="display:none">
                    <div class="w3-bar w3-black">
                        <button class="w3-bar-item w3-button nowLink w3-white"
                                onclick="openTab(event,'NowActivated', 'now', 'nowLink')">
                            Activated
                        </button>
                        <button class="w3-bar-item w3-button nowLink"
                                onclick="openTab(event,'NowDeactivated', 'now', 'nowLink')">
                            Deactivated
                        </button>
                    </div>

                    <%--Activated competitions--%>
                    <%@include file="competition_part/now_activated.jsp" %>

                    <%--Deactivated competitions--%>
                    <%@include file="competition_part/now_deactivated.jsp" %>
                </div>
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
        <div class="w3-container w3-center">
            <span onclick="(modal_edit_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Edit error. Try again</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_edit_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_edit_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modal_edit_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Incorrect number format or just some kind of mistake</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_edit_wrong).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_fill_warning" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modal_fill_warning).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Incorrect number format or duplicate place</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_fill_warning).style.display='none'">
        </div>
    </div>
</div>
<c:if test="${requestScope.get('deactivateError') != null}">
    <div id="modal_deactivate_error" class="w3-modal" style="display: inherit;">
        <div class="w3-modal-content">
            <div class="w3-container w3-center">
                <span onclick="(modal_deactivate_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <p>Deactivate error</p>
                <input type="button" class="w3-button" value="Ok"
                       onclick="(modal_deactivate_error).style.display='none'">
            </div>
        </div>
    </div>
</c:if>
<c:if test="${requestScope.get('fillError') != null}">
    <div id="modal_fill_error" class="w3-modal" style="display: inherit;">
        <div class="w3-modal-content">
            <div class="w3-container w3-center">
                <span onclick="(modal_fill_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <p>Fill result database error.</p>
                <input type="button" class="w3-button" value="Ok"
                       onclick="(modal_fill_error).style.display='none'">
            </div>
        </div>
    </div>
</c:if>
<c:if test="${requestScope.get('wrongNumberFormat') != null}">
    <div id="modal_number_error" class="w3-modal" style="display: inherit;">
        <div class="w3-modal-content">
            <div class="w3-container w3-center">
                <span onclick="(modal_number_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <p>Fill result error. Please, check input date</p>
                <input type="button" class="w3-button" value="Ok"
                       onclick="(modal_number_error).style.display='none'">
            </div>
        </div>
    </div>
</c:if>


</body>
<script src="${pageContext.request.contextPath}/js/competition.js"></script>
<%@include file="../partial/footer.jsp" %>


