
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="competition" items="${upcomingCompetitions}">
    <div id="upcomingGame${competition['competition_id']}"
         class="w3-container w3-card-2 w3-display-container w3-margin-top">

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
                <div class='w3-col s12'>
                    <div class='w3-col s8'> ${competitor['command_name']} </div>

                    <div class='w3-col s1 '>W:</div>
                    <div class='w3-col s3'>
                            <span name="competitorCoeff">
                                <ctg:decimal-presenter number="${competitor['competitor_win_coeff']}"/>
                            </span>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</c:forEach>
