<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.before.delete.competition" var="txtBeforeDelete"/>
<fmt:message bundle="${rb}" key="txt.date.start" var="txtDateStart"/>
<fmt:message bundle="${rb}" key="txt.date.finish" var="txtDateFinish"/>
<fmt:message bundle="${rb}" key="txt.T" var="txtT"/>
<fmt:message bundle="${rb}" key="txt.M" var="txtM"/>
<fmt:message bundle="${rb}" key="txt.L" var="txtL"/>
<fmt:message bundle="${rb}" key="txt.X" var="txtX"/>
<fmt:message bundle="${rb}" key="txt.W" var="txtW"/>
<fmt:message bundle="${rb}" key="txt.bets" var="txtBets"/>
<fmt:message bundle="${rb}" key="txt.team" var="txtTeam"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.no" var="txtNo"/>

<div id="PastDeactivated" class="past" style="display:none">

    <c:forEach var="competition" items="${pastDeactiveCompetitions}">
        <div id="pastDeactivatedGame${competition['competition_id']}"
             class="w3-container w3-card-2 w3-display-container w3-margin-top">

            <input type="hidden" name="competitionId" value="${competition['competition_id']}">

            <c:if test="${user.type.toString().equals('BOOKMAKER')}">
                <div class="w3-display-topright w3-display-hover w3-small">
                    <button class="w3-button w3-black w3-padding-small"
                            onclick="(pdmodal_del${competition['competition_id']}).style.display='block'">
                        <i class="fa fa-remove"></i>
                    </button>

                </div>
            </c:if>

            <div id="pdmodal_del${competition['competition_id']}" class="w3-modal">
                <div class="w3-modal-content">
                    <div class="w3-container w3-center">
                                            <span onclick="(pdmodal_del${competition['competition_id']}).style.display='none'"
                                                  class="w3-button w3-display-topright">&times;</span>
                        <p><c:out value="${txtBeforeDelete}"/></p>
                        <div class="w3-row">
                            <div class="w3-half">
                                <input onclick="(pdmodal_del${competition['competition_id']}).style.display='none';
                                        delUnfilled(this, 'pastDeactivatedGame'+${competition['competition_id']});"
                                       type="button" class="w3-button" value="<c:out value="${txtYes}"/>">
                            </div>
                            <div class="w3-half">
                                <input type="button" class="w3-button" value="<c:out value="${txtNo}"/>"
                                       onclick="(pdmodal_del${competition['competition_id']}).style.display='none'">
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="w3-row w3-small">
                <div class="w3-col s1">
                        ${competition['competition_id']}
                </div>
                <div class="w3-col s3">
                        <c:out value="${competition['kind_of_sport_name']}"/>
                </div>
                <div class="w3-col s4">
                    <i class="w3-tiny"><c:out value="${txtDateStart}"/>:</i>
                    <ctg:date-presenter date="${competition['competition_date_start']}"/>
                </div>
                <div class="w3-col s4">
                    <i class="w3-tiny"><c:out value="${txtDateFinish}"/>:</i>
                    <ctg:date-presenter date="${competition['competition_date_finish']}"/>
                </div>
            </div>
            <div class="w3-row  w3-padding-small">
                <div class="w3-col s6">
                    <span name="competitionName">
                            <c:out value="${competition['competition_name']}"/>
                    </span>
                </div>
                <div class="w3-col s6">
                        <c:out value="${competition['competition_type_name']}"/>
                </div>
            </div>
            <c:if test="${competition['competitors'].size() == 2}">
                <div class='w3-row w3-small'>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '><c:out value="${txtT}"/>:</div>
                        <div class='w3-col s10'>
                            <span name="total">
                            <ctg:decimal-presenter number="${competition['competition_total']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '><c:out value="${txtL}"/>:</div>
                        <div class='w3-col s10'>
                            <span name="lessTotalCoeff">
                            <ctg:decimal-presenter number="${competition['competition_less_total_coeff']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '><c:out value="${txtM}"/>:</div>
                        <div class='w3-col s10'>
                           <span name="moreTotalCoeff">
                               <ctg:decimal-presenter number="${competition['competition_more_total_coeff']}"/>
                           </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '><c:out value="${txtX}"/>:</div>
                        <div class='w3-col s10'>
                             <span name="standoffCoeff">
                                 <ctg:decimal-presenter number="${competition['competition_standoff_coeff']}"/>
                             </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:forEach var="competitor" items="${competition['competitors']}">
                <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
                <div class='w3-row w3-small'>
                    <div class='w3-col s6'>
                        <div class='w3-col s4 '><c:out value="${txtTeam}"/>:</div>
                        <div class='w3-col s8'><c:out value="${competitor['team_name']}"/></div>
                    </div>
                    <div class='w3-col s6'>
                        <div class='w3-col s6 '><c:out value="${txtW}"/>:</div>
                        <div class='w3-col s6'>
                            <span name="competitorCoeff">
                                <ctg:decimal-presenter number="${competitor['competitor_win_coeff']}"/>
                            </span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>
<script src="${pageContext.request.contextPath}/js/competition/competition_past.js"></script>

