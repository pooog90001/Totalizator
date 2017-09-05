<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>

<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.error.check.connection" var="txtError"/>
<fmt:message bundle="${rb}" key="txt.less.total" var="txtLessTotal"/>
<fmt:message bundle="${rb}" key="txt.more.total" var="txtMoreTotal"/>
<fmt:message bundle="${rb}" key="txt.total" var="txtTotal"/>
<fmt:message bundle="${rb}" key="txt.standoff" var="txtStandoff"/>
<fmt:message bundle="${rb}" key="txt.type" var="txtType"/>
<fmt:message bundle="${rb}" key="txt.win" var="txtWin"/>
<fmt:message bundle="${rb}" key="txt.bet.size" var="txtBetSize"/>
<fmt:message bundle="${rb}" key="txt.dont.have.money" var="txtDontHaveMoney"/>
<fmt:message bundle="${rb}" key="txt.wrong.access" var="txtWrongAccess"/>
<fmt:message bundle="${rb}" key="txt.fill.all.fields" var="txtFillAllFields"/>
<fmt:message bundle="${rb}" key="txt.date.start" var="txtDateStart"/>
<fmt:message bundle="${rb}" key="txt.do.bet" var="txtDoBet"/>
<fmt:message bundle="${rb}" key="txt.before.do.bet" var="txtBeforeDoBet"/>
<fmt:message bundle="${rb}" key="txt.bet.success" var="txtBetSuccess"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.no" var="txtNo"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>

<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-small">&cross;</span>
        <br>
    </div>
    <%@include file="/jsp/bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container ">

    <div class="w3-col m3 w3-hide-small">
        <%@include file="/jsp/bar/left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <div class="w3-row-padding">
            <div class="w3-container w3-margin-bottom w3-display-container">
                <div class="w3-display-topright">
                    <button type="button" class="w3-button w3-card-4 w3-circle w3-theme-l3"
                            style="padding: 15px 17px;" onclick="history.back(); return false;">
                        <i class='fa fa-long-arrow-left'></i>
                    </button>
                </div>
                <h2><c:out value="${competition['competition_name']}"/></h2>
            </div>
            <div class="w3-row w3-container" id="doBetForm">
                <input type="hidden" name="competitionId" value="${competition['competition_id']}">
                <input type="hidden" name="command" value="CREATE_BET">
                <div class="w3-col m8 w3-container">
                    <div class=" w3-card-2  w3-margin-bottom w3-small w3-display-container w3-medium">
                        <div class="w3-theme w3-padding">
                            № ${competition['competition_id']}
                            <c:out value="${competition['kind_of_sport_name']}"/>
                        </div>
                        <div class="w3-padding-small">
                            ${txtType}: <b><c:out value="${competition['competition_type_name']}"/></b>
                        </div>
                        <div class="w3-padding-small">
                            ${txtDateStart}: <b><ctg:date-presenter
                                date="${competition['competition_date_start']}"/></b>
                        </div>
                        <div class="w3-row  w3-padding-small">

                            <c:if test="${competition['competitors'].size() == 2}">
                                <div class='w3-col l5'>
                                    <label class="w3-padding-small">
                                            ${txtTotal}: <ctg:decimal-presenter
                                            number="${competition['competition_total']}"/>
                                    </label>
                                </div>
                                <div class='w3-col l5'>
                                    <label class="w3-padding-small">
                                            ${txtLessTotal}: <ctg:decimal-presenter
                                            number="${competition['competition_less_total_coeff']}"/>
                                        <input type="radio" onTeam="false" value="LESS" name="bet">
                                    </label>
                                </div>
                                <div class='w3-col l5'>
                                    <label class="w3-padding-small">
                                            ${txtMoreTotal}: <ctg:decimal-presenter
                                            number="${competition['competition_more_total_coeff']}"/>
                                        <input type="radio" onTeam="false" value="MORE" name="bet">
                                    </label>
                                </div>
                                <div class='w3-col l5 '>
                                    <label class="w3-padding-small">
                                            ${txtStandoff}: <ctg:decimal-presenter
                                            number="${competition['competition_standoff_coeff']}"/>
                                        <input type="radio" name="bet" onTeam="false" value="EQUALS" required>
                                    </label>
                                </div>
                            </c:if>
                            <c:forEach var="competitor" items="${competition['competitors']}">
                                <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
                                <div class='w3-col s12 w3-left-align'>
                                    <hr style="margin: 5px;">
                                    <div class="w3-row">
                                        <div class='w3-col s5'> ${competitor['team_name']} </div>

                                        <div class='w3-col s5 w3-right-align'>
                                            <label class="w3-padding-small">
                                                    ${txtWin}: <ctg:decimal-presenter
                                                    number="${competitor['competitor_win_coeff']}"/>
                                                <input type="radio" name="bet" onTeam="true"
                                                       value="${competitor['competitor_id']}">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>
                <div class="w3-col m4 w3-container">
                    <div class="w3-card-2">
                        <div class="w3-padding">
                            ${txtBetSize}($)
                            <input type="number" id="cash" name="cash" class="w3-input w3-border" min="1"  step="1"
                                   value="0" />
                            <input type="button" class="w3-button w3-theme-d2 w3-small w3-margin-top"
                                   value="${txtDoBet}"
                                   onclick="checkFormFields(this, 'doBetForm')">
                        </div>
                        <div>
                            <div class="w3-text-red" id="dataEmpty" style="display: none;">${txtFillAllFields}</div>
                            <div class="w3-text-red" id="wrongCash" style="display: none;">${txtBetSize} 1-1000$</div>
                            <div class="w3-text-red" id="littleMoney" style="display: none;">${txtDontHaveMoney}</div>
                            <div class="w3-text-red" id="wrongCompetition"
                                 style="display: none;">${txtFillAllFields}</div>
                            <div class="w3-text-red" id="wrongCreation" style="display: none;">${txtFillAllFields}</div>
                            <div class="w3-text-red" id="accessDenied" style="display: none;">${txtWrongAccess}</div>
                            <div class="w3-text-red" id="ServerError" style="display: none;">${txtError}</div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<div id="modalBeforeDoBet" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modalBeforeDoBet).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>
                ${txtBeforeDoBet}
                ${txtDoBet}?
            </p>
            <input type="button" class="w3-button" value="${txtYes}" onclick="doBet(this, 'doBetForm')">
            <input type="button" class="w3-button" value="${txtNo}" onclick="(modalBeforeDoBet).style.display='none'">
        </div>
    </div>
</div>
<div id="modalBetSuccess" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <p>${txtBetSuccess}</p>
            <input type="button" class="w3-button w3-theme-d2 w3-margin-top" value="${txtOk}"
                   onclick="window.location.reload();">
        </div>
    </div>
</div>
</body>
<script src="${pageContext.request.contextPath}/js/competition/concrete_competition.js"></script>
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
