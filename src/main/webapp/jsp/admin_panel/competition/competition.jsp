<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="/jsp/partial/header.jsp" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.before.delete.competition" var="txtBeforeDelete"/>
<fmt:message bundle="${rb}" key="txt.return.money.delete" var="txtReturnMoney"/>
<fmt:message bundle="${rb}" key="txt.date.start" var="txtDateStart"/>
<fmt:message bundle="${rb}" key="txt.date.finish" var="txtDateFinish"/>
<fmt:message bundle="${rb}" key="txt.T" var="txtT"/>
<fmt:message bundle="${rb}" key="txt.M" var="txtM"/>
<fmt:message bundle="${rb}" key="txt.L" var="txtL"/>
<fmt:message bundle="${rb}" key="txt.X" var="txtX"/>
<fmt:message bundle="${rb}" key="txt.W" var="txtW"/>
<fmt:message bundle="${rb}" key="txt.bets" var="txtBets"/>
<fmt:message bundle="${rb}" key="txt.team" var="txtTeam"/>
<fmt:message bundle="${rb}" key="txt.score" var="txtScore"/>
<fmt:message bundle="${rb}" key="txt.place" var="txtPlace"/>
<fmt:message bundle="${rb}" key="txt.winner" var="txtWinner"/>
<fmt:message bundle="${rb}" key="txt.before.activate" var="txtBeforeActivate"/>
<fmt:message bundle="${rb}" key="txt.competitions" var="txtCompetitions"/>
<fmt:message bundle="${rb}" key="txt.choose.sport" var="txtChooseSport"/>
<fmt:message bundle="${rb}" key="txt.competition.type" var="txtCompetitionType"/>
<fmt:message bundle="${rb}" key="txt.choose.competition.type" var="txtChooseCompetitionType"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.no" var="txtNo"/>
<fmt:message bundle="${rb}" key="txt.competition.name" var="txtCompetitionName"/>
<fmt:message bundle="${rb}" key="txt.time.start" var="txtTimeStart"/>
<fmt:message bundle="${rb}" key="txt.time.finish" var="txtTimeFinish"/>
<fmt:message bundle="${rb}" key="txt.active" var="txtActive"/>
<fmt:message bundle="${rb}" key="txt.in.name.must.be" var="txtInNameMustBe"/>
<fmt:message bundle="${rb}" key="txt.symbols" var="txtSymbols"/>
<fmt:message bundle="${rb}" key="txt.all.fields.be.filled" var="txtAllFieldsMustFilled"/>
<fmt:message bundle="${rb}" key="txt.wrong.date.format" var="txtWrongDateFormat"/>
<fmt:message bundle="${rb}" key="txt.finish.later.start" var="txtFinishLaterStart"/>
<fmt:message bundle="${rb}" key="txt.competitors.data.wrong" var="txtCompetitorsDataWrong"/>
<fmt:message bundle="${rb}" key="txt.for.active.competition" var="txtForActiveCompetition"/>
<fmt:message bundle="${rb}" key="txt.teams.must.not.duplicate" var="txtTeamsNotDuplicate"/>
<fmt:message bundle="${rb}" key="txt.competition.type.not.correct" var="txtTypeNotCorrect"/>
<fmt:message bundle="${rb}" key="txt.now" var="txtNow"/>
<fmt:message bundle="${rb}" key="txt.upcoming" var="txtUpcoming"/>
<fmt:message bundle="${rb}" key="txt.past" var="txtPast"/>
<fmt:message bundle="${rb}" key="txt.activated" var="txtActivated"/>
<fmt:message bundle="${rb}" key="txt.deactivated" var="txtDeactivated"/>
<fmt:message bundle="${rb}" key="txt.filled" var="txtFilled"/>
<fmt:message bundle="${rb}" key="txt.unfilled" var="txtUnfilled"/>
<fmt:message bundle="${rb}" key="txt.delete.error" var="txtDeleteError"/>
<fmt:message bundle="${rb}" key="txt.try.again" var="txtTryAgain"/>
<fmt:message bundle="${rb}" key="txt.wrong.access" var="txtWrongAccess"/>
<fmt:message bundle="${rb}" key="txt.delete.failed" var="txtDeleteFailed"/>
<fmt:message bundle="${rb}" key="txt.edit.error" var="txtEditError"/>
<fmt:message bundle="${rb}" key="txt.wrong.number.format" var="txtWrongNumberFormat"/>
<fmt:message bundle="${rb}" key="txt.some.kind.mistake" var="txtSomeKindMistake"/>
<fmt:message bundle="${rb}" key="txt.duplicate.place" var="txtDuplicatePlace"/>
<fmt:message bundle="${rb}" key="txt.deactivate.error" var="txtDeactivateError"/>
<fmt:message bundle="${rb}" key="txt.fill.result.error" var="txtFillResultError"/>
<fmt:message bundle="${rb}" key="txt.check.input.data" var="txtCheckInputData"/>
<fmt:message bundle="${rb}" key="lbl.or" var="txtOr"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.sport.name" var="txtSportName"/>

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
            <div class="w3-container w3-xlarge w3-display-container">
                <div class="w3-display-right w3-padding">
                    <button id="create" type="button" class="w3-button w3-card-4 w3-circle w3-theme-l1"
                            style="padding: 15px 17px;">
                        <i class='fa fa-plus-circle'></i>
                    </button>
                </div>
                <c:out value="${txtCompetitions}"/>
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
                                    ${txtSportName}
                                    <select id="selectKind" required name="sportId" class="w3-select">
                                        <option value='' selected disabled><c:out value="${txtChooseSport}"/></option>
                                        <c:forEach var="kind" items="${kindsOfSport}">
                                            <option value="${kind.id}" count="${kind.competitorCount}">
                                                <c:out value="${kind.name}"/>
                                            </option>
                                        </c:forEach>
                                    </select>

                                </label>
                            </div>
                            <div class="w3-col s4 w3-padding-small">
                                <label>
                                    <c:out value="${txtCompetitionType}"/>
                                    <select id="competitionType" required name="typeId" class="w3-select">
                                        <option value='' selected disabled> ${txtChooseCompetitionType}</option>
                                        <c:forEach var="type" items="${competitionTypes}">
                                            <option value="${type.id}">
                                                <c:out value="${type.name}"/>
                                            </option>
                                        </c:forEach>
                                    </select>

                                </label>
                            </div>
                            <div class="w3-col s4 w3-padding-small">
                                <label>
                                    ${txtCompetitionName}
                                    <input type="text" class="w3-input" id="competitionName" name="competitionName"
                                           maxlength="100" required >
                                </label>
                            </div>
                            <div class="w3-row">
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        ${txtDateStart}
                                        <input id='dateStart' type="date" class="w3-input" name="dateStart"
                                               required>
                                    </label>
                                </div>
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        ${txtTimeStart}
                                        <input id='timeStart' type="time" class="w3-input" name="timeStart"
                                               required>
                                    </label>
                                </div>
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        ${txtDateFinish}
                                        <input id='dateFinish' type="date" class="w3-input" name="dateFinish" required/>
                                    </label>
                                </div>
                                <div class="w3-col s3 w3-padding-small">
                                    <label>
                                        ${txtTimeFinish}
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
                                        ${txtActive}
                                    <input type="checkbox" class="w3-check" id="isActive" name="isActive">
                                </label>
                            </div>
                        </c:if>
                        <div id="errorBox" class="w3-text-red" style="display: none;">
                            ${txtAllFieldsMustFilled}
                        </div>
                    </form>
                    <c:if test="${requestScope.get('wrongName') != null}">
                        <div id="wrong" class="w3-row w3-text-red">
                                ${txtInNameMustBe} 1-45 ${txtSymbols}.
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongDate') != null}">
                        <div id="wrong" class="w3-row w3-text-red">
                                ${txtWrongDateFormat}. ${txtFinishLaterStart}.
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongCompetitors') != null}">
                        <div id="wrong" class="w3-row w3-text-red">
                                ${txtCompetitorsDataWrong}
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongActive') != null}">
                        <div id="wrong" class="w3-row w3-text-red">
                                ${txtForActiveCompetition}
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('createError') != null}">
                        <div id="wrong" class="w3-row w3-text-red">
                                ${txtAllFieldsMustFilled}. ${txtTeamsNotDuplicate}.
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('wrongType') != null}">
                        <div id="wrong" class="w3-row w3-text-red">
                                ${txtTypeNotCorrect}.
                        </div>
                    </c:if>
                    <div id="wrongJS" class="w3-row w3-text-red" style="display: none">
                        ${txtAllFieldsMustFilled}. ${txtTeamsNotDuplicate}.
                    </div>
                </div>
            </div>
            <%--End create block--%>


            <div class=" w3-container w3-padding-small w3-card-2">

                <%--Start main headers tab block--%>
                <div class="w3-bar w3-black">
                    <button class="w3-bar-item w3-button generalLink w3-white"
                            onclick="openTab(event,'Upcoming', 'general', 'generalLink')">
                        ${txtUpcoming}
                    </button>
                    <button class="w3-bar-item w3-button generalLink"
                            onclick="openTab(event,'Past', 'general', 'generalLink')">
                        ${txtPast}
                    </button>
                    <button class="w3-bar-item w3-button generalLink"
                            onclick="openTab(event,'Now', 'general', 'generalLink')">
                        ${txtNow}
                    </button>
                </div>
                <%--End main headers tab block--%>
                <hr>

                <%--Start upcoming  tab block--%>
                <div id="Upcoming" class="general">
                    <div class="w3-bar w3-black">
                        <button class="w3-bar-item w3-button upcomingLink w3-white"
                                onclick="openTab(event,'Activated', 'upcoming', 'upcomingLink')">
                            ${txtActivated}
                        </button>
                        <button class="w3-bar-item w3-button upcomingLink"
                                onclick="openTab(event,'Deactivated', 'upcoming', 'upcomingLink')">
                            ${txtDeactivated}
                        </button>
                    </div>

                    <%--Activated competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/upcoming_activated.jsp" %>

                    <%--Deactivated competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/upcoming_deactivated.jsp" %>

                </div>

                <div id="Past" class="general" style="display:none">
                    <div class="w3-bar w3-black">
                        <button class="w3-bar-item w3-button pastLink w3-white"
                                onclick="openTab(event,'PastFilled', 'past', 'pastLink')">
                            ${txtFilled}
                        </button>
                        <button class="w3-bar-item w3-button pastLink"
                                onclick="openTab(event,'PastUnfilled', 'past', 'pastLink')">
                            ${txtUnfilled}
                        </button>
                        <button class="w3-bar-item w3-button pastLink"
                                onclick="openTab(event,'PastDeactivated', 'past', 'pastLink')">
                            ${txtDeactivated}
                        </button>
                    </div>

                    <%--Filled competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/past_filled.jsp" %>

                    <%--Unfilled competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/past_unfilled.jsp" %>

                    <%--Deactivated competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/past_deactivated.jsp" %>
                </div>

                <div id="Now" class="general" style="display:none">
                    <div class="w3-bar w3-black">
                        <button class="w3-bar-item w3-button nowLink w3-white"
                                onclick="openTab(event,'NowActivated', 'now', 'nowLink')">
                            ${txtActivated}
                        </button>
                        <button class="w3-bar-item w3-button nowLink"
                                onclick="openTab(event,'NowDeactivated', 'now', 'nowLink')">
                            ${txtDeactivated}
                        </button>
                    </div>

                    <%--Activated competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/now_activated.jsp" %>

                    <%--Deactivated competitions--%>
                    <%@include file="/jsp/admin_panel/competition/part/now_deactivated.jsp" %>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="modal_del_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtDeleteError}. ${txtTryAgain}. </p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_del_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_del_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtWrongAccess}. ${txtDeleteFailed}.</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_del_wrong).style.display='none'">
        </div>
    </div>
</div>


<div id="modal_edit_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modal_edit_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtEditError}. ${txtEditError}.</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_edit_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_edit_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modal_edit_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtWrongNumberFormat} ${txtOr} ${txtSomeKindMistake}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_edit_wrong).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_fill_warning" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modal_fill_warning).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtWrongNumberFormat} ${txtOr} ${txtDuplicatePlace}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_fill_warning).style.display='none'">
        </div>
    </div>
</div>
<c:if test="${requestScope.get('deactivateError') != null}">
    <div id="modal_deactivate_error" class="w3-modal" style="display: inherit;">
        <div class="w3-modal-content">
            <div class="w3-container w3-center">
                <span onclick="(modal_deactivate_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <p>${txtDeactivateError}</p>
                <input type="button" class="w3-button" value="${txtOk}"
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
                <p>${txtFillResultError}.</p>
                <input type="button" class="w3-button" value="${txtOk}"
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
                <p>${txtFillResultError}. ${txtCheckInputData}.</p>
                <input type="button" class="w3-button" value="${txtOk}"
                       onclick="(modal_number_error).style.display='none'">
            </div>
        </div>
    </div>
</c:if>


</body>
<script src="${pageContext.request.contextPath}/js/competition/competition.js"></script>
<%@include file="/jsp/partial/footer.jsp" %>


