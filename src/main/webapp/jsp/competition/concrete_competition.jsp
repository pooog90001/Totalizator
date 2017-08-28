<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>


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
            <form class="w3-row w3-container" id="doBetForm">
                <input type="hidden" name="competitionId" value="${competition['competition_id']}">
                <input type="hidden" name="command" value="CREATE_BET">
                <div class="w3-col m6 w3-container">
                    <div class=" w3-card-2  w3-margin-bottom w3-small w3-display-container w3-medium">
                        <div class="w3-theme w3-padding">
                            № ${competition['competition_id']}
                            <c:out value="${competition['kind_of_sport_name']}"/>
                        </div>
                        <div class="w3-padding-small">
                            Тип: <b><c:out value="${competition['competition_type_name']}"/></b>
                        </div>
                        <div class="w3-padding-small">
                            Дата проведения: <b><ctg:date-presenter
                                date="${competition['competition_date_start']}"/></b>
                        </div>
                        <div class="w3-row  w3-padding-small">

                            <c:if test="${competition['competitors'].size() == 2}">
                                <div class='w3-col l5'>
                                    <label class="w3-padding-small">
                                        Total: <ctg:decimal-presenter number="${competition['competition_total']}"/>
                                    </label>
                                </div>
                                <div class='w3-col l5'>
                                    <label class="w3-padding-small">
                                        Less total: <ctg:decimal-presenter
                                            number="${competition['competition_less_total_coeff']}"/>
                                        <input type="radio" onCommand="false" value="LESS" name="bet">
                                    </label>
                                </div>
                                <div class='w3-col l5'>
                                    <label class="w3-padding-small">
                                        More total: <ctg:decimal-presenter
                                            number="${competition['competition_more_total_coeff']}"/>
                                        <input type="radio" onCommand="false" value="MORE" name="bet">
                                    </label>
                                </div>
                                <div class='w3-col l5 '>
                                    <label class="w3-padding-small">
                                        Standdoff: <ctg:decimal-presenter
                                            number="${competition['competition_standoff_coeff']}"/>
                                        <input type="radio" name="bet" onCommand="false" value="EQUALS" required>
                                    </label>
                                </div>
                            </c:if>
                            <c:forEach var="competitor" items="${competition['competitors']}">
                                <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
                                <div class='w3-col s12 w3-left-align'>
                                    <hr style="margin: 5px;">
                                    <div class="w3-row">
                                        <div class='w3-col s5'> ${competitor['command_name']} </div>

                                        <div class='w3-col s5 w3-right-align'>
                                            <label class="w3-padding-small">
                                                Win: <ctg:decimal-presenter
                                                    number="${competitor['competitor_win_coeff']}"/>
                                                <input type="radio" name="bet" onCommand="true"
                                                       value="${competitor['competitor_id']}">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>

                    </div>
                </div>
                <div class="w3-col m6 w3-container">
                    <div class="w3-card-2">
                        <div class="w3-padding">
                            Bet size ($)
                            <input type="number" id="cash" name="cash" class="w3-input w3-border" min="1"  step="1"
                                   value="0" />
                            <input type="button" class="w3-button w3-theme-d2 w3-small w3-margin-top" value="Do bet"
                                   onclick="checkFormFields(this, 'doBetForm')">
                        </div>
                        <div>
                            <div class="w3-text-red" id="dataEmpty" style="display: none;">Please, fill all fields</div>
                            <div class="w3-text-red" id="wrongCash" style="display: none;">bet size 1-1000 </div>
                            <div class="w3-text-red" id="littleMoney" style="display: none;">You do not have so much money</div>
                            <div class="w3-text-red" id="wrongCompetition" style="display: none;" >Please, fill all fields </div>
                            <div class="w3-text-red" id="wrongCreation" style="display: none;" >Please, fill all fields </div>
                            <div class="w3-text-red" id="accessDenied" style="display: none;" >You have not access for this operation</div>
                            <div class="w3-text-red" id="ServerError" style="display: none;" >Server error, check conection</div>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </div>
</div>

<div id="modalBeforeDoBet" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <span onclick="(modalBeforeDoBet).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>
                Eсли вы сделаете ставку, то не сможете её отменить
                If you make a bet, you will not be able to cancel it
                Сделать ставку?
            </p>
            <input type="button" class="w3-button" value="Ok" onclick="doBet(this, 'doBetForm')">
            <input type="button" class="w3-button" value="Cancel" onclick="(modalBeforeDoBet).style.display='none'">
        </div>
    </div>
</div>
<div id="modalBetSuccess" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container w3-center">
            <p>Bet success. You can see bet status in your profile.</p>
            <input type="button" class="w3-button w3-theme-d2 w3-margin-top" value="Ok" onclick="window.location.reload();">
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
