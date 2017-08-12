<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="Deactivated" class="upcoming" style="display:none">

    <c:forEach var="competition" items="${upcomingDeactivated}">
        <div class="w3-container w3-card-2 w3-display-container w3-hover-opacity">

            <div class="w3-display-topright w3-display-hover">
                <button class="w3-button w3-black"
                        onclick="(dmodal_active${competition['competition_id']}).style.display='block'">
                    Deactivate
                </button>
                <button class="w3-button w3-black">
                    Edit
                </button>
                <button class="w3-button w3-black"
                        onclick="(dmodal_del${competition['competition_id']}).style.display='block'">
                    Delete
                </button>

            </div>

            <div id="dmodal_active${competition['competition_id']}" class="w3-modal">
                <div class="w3-modal-content">
                    <div class="w3-container">
                                                        <span onclick="(dmodal_active${competition['competition_id']}).style.display='none'"
                                                              class="w3-button w3-display-topright">&times;</span>
                        <p>
                            Do you really want activate competition?
                        </p>
                        <div class="w3-row">
                            <div class="w3-half">
                                <form action="/generalController">
                                    <input type="hidden" name="command"
                                           value="deactivate_cimpetition">
                                    <input type="hidden" name="competitionId"
                                           value="${competition['competition_id']}">
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
                    <div class="w3-container">
                                            <span onclick="(modal_del${competition['competition_id']}).style.display='none'"
                                                  class="w3-button w3-display-topright">&times;</span>
                        <p>Do you really want delete?</p>
                        <div class="w3-row">
                            <div class="w3-half">
                                <input onclick="del(this, modal_del${competition['competition_id']})"
                                       type="button" class="w3-button" value="Yes">
                            </div>
                            <div class="w3-half">
                                <input type="button" class="w3-button" value="NO"
                                       onclick="(modal_del${competition['competition_id']}).style.display='none'">
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="w3-row">
                <div class="w3-col s1">
                        ${competition['competition_id']}
                </div>
                <div class="w3-col s3">
                        ${competition['kind_of_sport_name']}
                </div>
                <div class="w3-col s4">
                    Date start: ${competition['competition_date_start']}
                </div>
                <div class="w3-col s4">
                    Date finish: ${competition['competition_date_finish']}
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col s6">
                        ${competition['competition_name']}
                </div>
                <div class="w3-col s6">
                        ${competition['competition_type_name']}
                </div>
            </div>
            <c:if test="${competition['competition_total_coeff'] != null}">
                <div class='w3-row'>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 w3-padding'>T:</div>
                        <div class='w3-col s10'>
                            <span>${competition['competition_total_coeff']}</span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 w3-padding'>L:</div>
                        <div class='w3-col s10'>
                            <span>${competition['competition_less_total_coeff']}</span>
                            <span>${competition['betsLessTotalCount']}</span>
                            <span>${competition['lessAmountOfMoney']}$</span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 w3-padding'>M:</div>
                        <div class='w3-col s10'>
                            <span>${competition['competition_more_total_coeff']}</span>
                            <span>${competition['betsMoreTotalCount']}</span>
                            <span>${competition['moreAmountOfMoney']}$</span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <div class='w3-col s2 w3-padding'>X:</div>
                        <div class='w3-col s10'>
                            <span>${competition['competition_standoff_coeff']}</span>
                            <span>${competition['betsStandoffTotalCount']}</span>
                            <span>${competition['standoffAmountOfMoney']}$</span>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:forEach var="competitor" items="${competitors}">
                <div class='w3-row'>
                    <div class='w3-col s6'>
                        <div class='w3-col s4 w3-padding'>Command:</div>
                        <div class='w3-col s8'> ${competitor['command_name']} </div>
                    </div>
                    <div class='w3-col s6'>
                        <div class='w3-col s8 w3-padding'>Win coefficient:</div>
                        <div class='w3-col s4'>
                            <span>${competitor['competitor_win_coeff']}</span>
                            <span>${competitor['betsCount']}</span>
                            <span>${competitor['amountOfMoney']}$</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:forEach>
</div>
