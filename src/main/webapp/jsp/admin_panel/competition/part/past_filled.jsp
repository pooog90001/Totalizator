<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="PastFilled" class="past">

    <c:forEach var="competition" items="${pastFilledCompetitions}">
        <div id="pastFilledGame${competition['competition_id']}"
             class="w3-container w3-card-2 w3-display-container w3-margin-top">

            <input type="hidden" name="competitionId" value="${competition['competition_id']}">

            <c:if test="${user.type.toString().equals('BOOKMAKER')}">
                <div class="w3-display-topright w3-display-hover w3-small">
                    <button class="w3-button w3-black w3-padding-small"
                            onclick="(pfmodal_del${competition['competition_id']}).style.display='block'">
                        <i class="fa fa-remove"></i>
                    </button>
                </div>
            </c:if>

            <div id="pfmodal_del${competition['competition_id']}" class="w3-modal w3-center">
                <div class="w3-modal-content">
                    <div class="w3-container">
                        <span onclick="(pfmodal_del${competition['competition_id']}).style.display= 'none' "
                              class="w3-button w3-display-topright">&times;
                        </span>
                        <p>
                            Do you really want delete?
                        </p>
                        <div class="w3-row">
                            <div class="w3-half">
                                <input onclick="(pfmodal_del${competition['competition_id']}).style.display='none';
                                        delFilled(this, 'pastFilledGame'+${competition['competition_id']});"
                                       type="button" class="w3-button" value="Yes">
                            </div>
                            <div class="w3-half">
                                <input type="button" class="w3-button" value="NO"
                                       onclick="(pfmodal_del${competition['competition_id']}).style.display='none'">
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
                        ${competition['kind_of_sport_name']}
                </div>
                <div class="w3-col s4">
                    <i class="w3-tiny">Date start:</i>
                    <ctg:date-presenter date="${competition['competition_date_start']}"/>
                </div>
                <div class="w3-col s4">
                    <i class="w3-tiny">Date finish:</i>
                    <ctg:date-presenter date="${competition['competition_date_finish']}"/>
                </div>
            </div>
            <div class="w3-row  w3-padding-small">
                <div class="w3-col s6">
                        ${competition['competition_name']}
                </div>
                <div class="w3-col s6">
                        ${competition['competition_type_name']}
                </div>
            </div>
            <c:if test="${competition['competitors'].size() == 2}">
                <div class='w3-row w3-small'>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>T:</div>
                        <div class='w3-col s10'>
                                                <span>
                                                <ctg:decimal-presenter
                                                        number="${competition['competition_total']}"/>
                                                </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>L:</div>
                        <div class='w3-col s10'>
                            <span name="lessTotalCoeff">
                            <ctg:decimal-presenter
                                    number="${competition['competition_less_total_coeff']}"/>
                            </span>

                            <span class="w3-tiny">
                                                (${competition['betsLessTotalCount']} bets
                                                </span>
                            <span class="w3-tiny">
                             <ctg:decimal-presenter
                                     number="${competition['lessAmountOfMoney']}"/>$)
                             </span>

                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>M:</div>
                        <div class='w3-col s10'>
                                               <span name="moreTotalCoeff">
                                                   <ctg:decimal-presenter
                                                           number="${competition['competition_more_total_coeff']}"/>
                                               </span>
                            <span class="w3-tiny">
                                                   (${competition['betsMoreTotalCount']} bets

                                                   <ctg:decimal-presenter
                                                           number="${competition['moreAmountOfMoney']}"/>$)
                                               </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>X:</div>
                        <div class='w3-col s10'>
                                                <span name="standoffCoeff">
                                                    <ctg:decimal-presenter
                                                            number="${competition['competition_standoff_coeff']}"/>
                                                </span>
                            <span class="w3-tiny">
                             (${competition['betsStandoffTotalCount']} bets

                             <ctg:decimal-presenter
                                     number="${competition['standoffAmountOfMoney']}"/>$)
                                                </span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:forEach var="competitor" items="${competition['competitors']}">
                <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
                <div class='w3-row w3-small'>
                    <div class='w3-col s5'>
                        <div class='w3-col s4 '>Command:</div>
                        <div class='w3-col s8'> ${competitor['command_name']} </div>
                    </div>
                    <div class='w3-col s4'>
                        <div class='w3-col s6 '>Win coefficient:</div>
                        <div class='w3-col s6'>
                                                <span name="competitorCoeff">
                                                    <ctg:decimal-presenter
                                                            number="${competitor['competitor_win_coeff']}"/>
                                                </span>
                            <span class="w3-tiny">
                                (${competitor['betsCount']}
                                bets
                                <ctg:decimal-presenter number="${competitor['amountOfMoney']}"/>$)
                            </span>
                        </div>
                    </div>
                    <div class="w3-col s3">
                        <form id="resultForm" action="/generalController" method="post">
                            <input type="hidden" name="command" value="fill_results_competition">
                            <input type="hidden" name="competitionId" value="${competition['competition_id']}">

                            <c:choose>
                                <c:when test="${competition['competitors'].size() == 2}">
                                    <div class='w3-col s6'>Score:</div>
                                </c:when>
                                <c:otherwise>
                                    <div class='w3-col s6 '>Place:</div>
                                </c:otherwise>
                            </c:choose>

                            <div class='w3-col s6'>
                                    ${competitor['competitor_result']}
                                <c:if test="${competitor['competitor_is_win']}">
                                    <i class="w3-tiny">winner</i>
                                </c:if>
                            </div>
                        </form>

                    </div>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>
<script src="${pageContext.request.contextPath}/js/competition/competition_past.js"></script>
