<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.before.delete.competition" var="txtBeforeDelete"/>
<fmt:message bundle="${rb}" key="txt.competition.type" var="txtCompetitionType"/>
<fmt:message bundle="${rb}" key="txt.choose.competition.type" var="txtChooseCompetitionType"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.competition.name" var="txtCompetitionName"/>
<fmt:message bundle="${rb}" key="txt.active" var="txtActive"/>
<fmt:message bundle="${rb}" key="txt.in.name.must.be" var="txtInNameMustBe"/>
<fmt:message bundle="${rb}" key="txt.symbols" var="txtSymbols"/>
<fmt:message bundle="${rb}" key="txt.all.fields.be.filled" var="txtAllFieldsMustFilled"/>
<fmt:message bundle="${rb}" key="txt.competition.type.not.correct" var="txtTypeNotCorrect"/>
<fmt:message bundle="${rb}" key="txt.activated" var="txtActivated"/>
<fmt:message bundle="${rb}" key="txt.deactivated" var="txtDeactivated"/>
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
<fmt:message bundle="${rb}" key="txt.sport.name" var="txtSportName"/>
<fmt:message bundle="${rb}" key="txt.types.competition" var="txtTypeCompetition"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="txtName"/>
<fmt:message bundle="${rb}" key="txt.actions" var="txtActions"/>
<fmt:message bundle="${rb}" key="txt.wrong.delete.team" var="txtWrongDeleteTeam"/>
<fmt:message bundle="${rb}" key="txt.sport.already.exist" var="txtSportExist"/>
<fmt:message bundle="${rb}" key="txt.kinds.of.sport" var="txtKindOfSport"/>
<fmt:message bundle="${rb}" key="txt.competitors.count" var="txtCompetitorsCount"/>
<fmt:message bundle="${rb}" key="txt.number.competitors.must.be" var="txtNumberOfCompetitorsMustBe"/>
<fmt:message bundle="${rb}" key="txt.invalid.value" var="txtInvalidValue"/>
<fmt:message bundle="${rb}" key="txt.kind.of.sport" var="txtKindOfSport"/>
<fmt:message bundle="${rb}" key="txt.kind.of.sport" var="txtKindOfSport"/>
<fmt:message bundle="${rb}" key="txt.teams" var="txtTeams"/>
<fmt:message bundle="${rb}" key="txt.choose.sport" var="txtChooseSport"/>
<fmt:message bundle="${rb}" key="txt.without" var="txtWithout"/>
<fmt:message bundle="${rb}" key="txt.name.already.exist" var="txtNameAlreadyExist"/>



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
                ${txtTeams}
            </div>

            <div id="createField" class="w3-col s12 w3-container w3-padding-small" style="display: none;">
                <div class="w3-row w3-hover-light-grey w3-card-2">
                    <form action="${pageContext.request.contextPath}/generalController"
                          class="w3-card-2 w3-white w3-round w3-small" method="post">
                        <input type="hidden" name="command" value="create_team">
                        <div class="w3-col s4 w3-padding-small">
                            <label>
                                ${txtName}
                                <input type="text" required name="name" class="w3-input">
                            </label>
                        </div>
                        <div class="w3-col s3 w3-padding-small">
                            <label>
                                ${txtKindOfSport}
                                <select class="w3-select" required name="kindOfSportId">
                                    <option value="" selected disabled>${txtChooseSport}</option>

                                    <c:forEach var="kind" items="${kindsOfSport}">
                                        <option value="${kind.id}"><c:out value="${kind.name}"/></option>
                                    </c:forEach>
                                </select>

                            </label>
                        </div>
                        <div class="w3-col s2 w3-padding-small w3-margin-top">
                            <input type="submit" value="Create" class="w3-button w3-theme w3">
                        </div>
                    </form>
                    <c:if test="${requestScope.get('wrongData') != null}">
                        <div id="wrong" class="w3-row w3-text-red">${txtInNameMustBe} 1-80 ${txtSymbols}. ${txtWithout}
                            '<,>'
                            symbols
                        </div>
                    </c:if>
                    <c:if test="${requestScope.get('duplicateName') != null}">
                        <div id="wrong" class="w3-row w3-text-red">${txtNameAlreadyExist}</div>
                    </c:if>
                </div>
            </div>

            <div class=" w3-container w3-padding-small w3-card-2 w3-center">
                <div class="w3-col s12 w3-container w3-padding-small">
                    <div class="w3-row">
                        <div class="w3-card-2 w3-white w3-round w3-medium">
                            <div class="w3-col s4 w3-padding-small">
                                <div class="w3-center">${txtName}</div>
                            </div>
                            <div class="w3-col s3 w3-padding-small">
                                <div class="w3-center">${txtKindOfSport}</div>
                            </div>
                            <div class="w3-col s3 w3-padding-small">
                            </div>
                            <div class="w3-col s2 w3-padding-small">
                                <div class="w3-center">${txtActions}</div>
                            </div>
                        </div>
                    </div>
                    <hr>
                </div>
                <div id="ajaxResponse">
                    <c:forEach var="team" items="${teams}">
                        <div id="${team['team_id']}" class="w3-col s12 w3-container w3-padding-small">
                            <div class="w3-row w3-hover-light-grey">
                                <div class="w3-card-2 w3-white w3-round w3-small">
                                    <div class="w3-col s4 w3-padding-small w3-center">
                                        <div class="teamName">
                                            <c:out value="${team['team_name']}"/>
                                        </div>
                                    </div>
                                    <div class="w3-col s3 w3-padding-small w3-center">
                                        <div class="kindOfSportName">
                                            <c:out value="${team['kind_of_sport_name']}"/>
                                        </div>
                                    </div>
                                    <div class="w3-col s3 w3-padding-small">
                                        <div class="w3-center">

                                        </div>
                                    </div>
                                    <div class="w3-col s2">
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <button onclick="edit(this, ${team['team_id']})" type="button"
                                                    class="w3-center w3-button w3-small w3-padding-small">
                                                <i class="fa fa-edit"></i>
                                            </button>
                                        </form>
                                        <form action="${pageContext.request.contextPath}/generalController">
                                            <button onclick="(modal${team['team_id']}).style.display='block'"
                                                    type="button" class="w3-center w3-button w3-small w3-padding-small">
                                                <i class="fa fa-remove"></i>
                                            </button>

                                            <div id="modal${team['team_id']}" class="w3-modal">
                                                <div class="w3-modal-content">
                                                    <div class="w3-container">
                                                        <span onclick="(modal${team['team_id']}).style.display='none'"
                                                              class="w3-button w3-display-topright">&times;</span>
                                                        <p>${txtBeforeDelete}</p>
                                                        <div class="w3-row">
                                                            <div class="w3-half">
                                                                <input onclick="del(this, ${team['team_id']})"
                                                                       type="button" class="w3-button"
                                                                       value="${txtYes}">
                                                            </div>
                                                            <div class="w3-half">
                                                                <input type="button" class="w3-button" value="${txtNo}"
                                                                       onclick="(modal${team['team_id']}).style.display='none'">
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
            <p>${txtDeleteError}. ${txtTryAgain}.</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_del_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_del_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtWrongDeleteTeam}</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_del_wrong).style.display='none'">
        </div>
    </div>
</div>


<div id="modal_edit_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_edit_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtEditError}. ${txtTryAgain}.</p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_edit_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_edit_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_edit_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtInvalidValue}. ${txtInNameMustBe} 1-80 ${txtSymbols} </p>
            <input type="button" class="w3-button" value="${txtOk}" onclick="(modal_edit_wrong).style.display='none'">
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/team.js"></script>
<%@include file="/jsp/partial/footer.jsp" %>


