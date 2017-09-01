<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
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
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.for.do.bet.sign.in" var="txtForDoBetSignIn"/>

<c:if test="${user == null}">
    <div id="needSignIn" class="w3-modal">
        <div class="w3-modal-content" style="max-width: 50%;">
            <div class="w3-container">
                <span onclick="(needSignIn).style.display='none'" class="w3-button w3-display-topright">&times;</span>
                <p>${txtForDoBetSignIn}</p>
                <input type="button" class="w3-button" value="${txtOk}" onclick="(needSignIn).style.display='none'">
            </div>
        </div>
    </div>
</c:if>
<c:forEach var="competition" items="${upcomingGames}">
    <c:choose>
        <c:when test="${user != null}">
            <form action="${pageContext.request.contextPath}/generalController" method="post" onclick="callSubmit(this)">
            <input type="hidden" name="command" value="OPEN_CONCRETE_COMPETITION"/>
            <input type="hidden" name="competitionId" value="${competition['competition_id']}"/>
        </c:when>
        <c:otherwise>
            <form onclick="(needSignIn).style.display='block'">
        </c:otherwise>
    </c:choose>
        <div id="upcomingGame${competition['competition_id']}"
             class="w3-container w3-card-2  w3-margin-bottom w3-small w3-display-container w3-hover-light-grey">
            <div class="w3-center w3-row w3-small w3-padding">
                <div class="w3-display-topleft w3-tiny w3-theme w3-padding-small">
                    № ${competition['competition_id']}
                </div>
                <div class="w3-col s2"></div>
                <div class="w3-col s4">
                        <c:out value="${competition['kind_of_sport_name']}"/>
                </div>
                <div class="w3-col s4">
                        <c:out value="${competition['competition_type_name']}"/>
                </div>
                <div class="w3-display-topright w3-tiny w3-theme w3-padding-small">
                    <ctg:date-presenter date="${competition['competition_date_start']}"/>
                </div>
                <div class="w3-col s2"></div>
            </div>
            <div class="w3-row  w3-padding-small">
                <div class="w3-col m4 s12 ">
                    <span name="competitionName">
                            <c:out value="${competition['competition_name']}"/>
                    </span>
                </div>

                <c:if test="${competition['competitors'].size() == 2}">
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>${txtT}:</div>
                        <div class='w3-col s3'>
                            <span name="total">
                            <ctg:decimal-presenter number="${competition['competition_total']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>${txtL}:</div>
                        <div class='w3-col s3'>
                            <span name="lessTotalCoeff">
                            <ctg:decimal-presenter number="${competition['competition_less_total_coeff']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>${txtM}:</div>
                        <div class='w3-col s3'>
                           <span name="moreTotalCoeff">
                               <ctg:decimal-presenter number="${competition['competition_more_total_coeff']}"/>
                           </span>
                        </div>
                    </div>
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>${txtX}:</div>
                        <div class='w3-col s3'>
                             <span name="standoffCoeff">
                                 <ctg:decimal-presenter number="${competition['competition_standoff_coeff']}"/>
                             </span>
                        </div>
                    </div>
                </c:if>
            </div>
            <c:forEach var="competitor" items="${competition['competitors']}">
                <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
                <div class='w3-col s6 w3-left-align'>
                    <hr style="margin: 5px;">
                    <div class="w3-row">
                        <div class='w3-col s5'><c:out value="${competitor['team_name']}"/></div>

                        <div class='w3-col s1 w3-text-gray'>${txtW}:</div>
                        <div class='w3-col s2'>
                            <span name="competitorCoeff">
                                <ctg:decimal-presenter number="${competitor['competitor_win_coeff']}"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </form>
</c:forEach>
<script src="${pageContext.request.contextPath}/js/competition/competition_upcoming.js"></script>