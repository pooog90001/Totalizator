<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="Deactivated" class="upcoming" style="display:none">

    <c:forEach var="competition" items="${upcomingDeactiveCompetitions}">
        <div id="upcomingDeactivatedGame${competition['competition_id']}"
                class="w3-container w3-card-2 w3-display-container w3-margin-top">

            <div class="w3-display-topright w3-display-hover w3-small">
                <button class="w3-button w3-black w3-padding-small"
                        onclick="(dmodal_active${competition['competition_id']}).style.display='block'">
                    <i class="fa fa-check-square-o"></i>
                </button>
                <button class="w3-button w3-black w3-padding-small"
                        onclick="deactivatedEdit(this, 'upcomingDeactivatedGame'+${competition['competition_id']})">
                    <i class="fa fa-edit"></i>
                </button>
                <button class="w3-button w3-black w3-padding-small"
                        onclick="(dmodal_del${competition['competition_id']}).style.display='block'">
                    <i class="fa fa-remove"></i>
                </button>

            </div>

            <div id="dmodal_active${competition['competition_id']}" class="w3-modal">
                <div class="w3-modal-content">
                    <div class="w3-container w3-center" >
                                                        <span onclick="(dmodal_active${competition['competition_id']}).style.display='none'"
                                                              class="w3-button w3-display-topright">&times;</span>
                        <p>
                            Do you really want activate competition?
                        </p>
                        <div class="w3-row">
                            <div class="w3-half">
                                <form action="/generalController">
                                    <input type="hidden" name="command" value="change_state_competition">
                                    <input type="hidden" name="state" value="true">
                                    <input type="hidden" name="competitionId" value="${competition['competition_id']}">
                                    <input type="submit" class="w3-button" value="Yes">
                                </form>
                            </div>
                            <div class="w3-half">
                                <input type="button" class="w3-button" value="NO"
                                       onclick="(dmodal_active${competition['competition_id']}).style.display='none'">
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="dmodal_del${competition['competition_id']}" class="w3-modal">
                <div class="w3-modal-content">
                    <div class="w3-container w3-center">
                                            <span onclick="(dmodal_del${competition['competition_id']}).style.display='none'"
                                                  class="w3-button w3-display-topright">&times;</span>
                        <p>Do you really want delete?</p>
                        <div class="w3-row">
                            <div class="w3-half">
                                <input onclick="(dmodal_del${competition['competition_id']}).style.display='none';
                                        delDeactivated(this, 'upcomingDeactivatedGame'+${competition['competition_id']});"
                                       type="button" class="w3-button" value="Yes">
                            </div>
                            <div class="w3-half">
                                <input type="button" class="w3-button" value="NO"
                                       onclick="(dmodal_del${competition['competition_id']}).style.display='none'">
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
                    <span name="competitionName">
                            ${competition['competition_name']}
                    </span>
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
                            <span name="total">
                            <ctg:decimal-presenter number="${competition['competition_total']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>L:</div>
                        <div class='w3-col s10'>
                            <span name="lessTotalCoeff">
                            <ctg:decimal-presenter number="${competition['competition_less_total_coeff']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>M:</div>
                        <div class='w3-col s10'>
                           <span name="moreTotalCoeff">
                               <ctg:decimal-presenter number="${competition['competition_more_total_coeff']}"/>
                           </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 '>X:</div>
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
                        <div class='w3-col s4 '>Command:</div>
                        <div class='w3-col s8'> ${competitor['command_name']} </div>
                    </div>
                    <div class='w3-col s6'>
                        <div class='w3-col s6 '>Win coefficient:</div>
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
<script src="${pageContext.request.contextPath}/js/competition_upcoming_deactivated.js"></script>
