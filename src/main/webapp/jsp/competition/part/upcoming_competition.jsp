<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="competition" items="${upcomingGames}">
    <c:choose>
        <c:when test="${user != null}">
            <form action="${pageContext.request.contextPath}/generalController" method="post" onclick="callSubmit(this)">
        </c:when>
        <c:otherwise>
            <form>
        </c:otherwise>
    </c:choose>
        <input type="hidden" name="command" value="OPEN_CONCRETE_COMPETITION"/>
        <input type="hidden" name="competitionId" value="${competition['competition_id']}"/>
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
                        <div class='w3-col s2 w3-text-gray'>T:</div>
                        <div class='w3-col s3'>
                            <span name="total">
                            <ctg:decimal-presenter number="${competition['competition_total']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>L:</div>
                        <div class='w3-col s3'>
                            <span name="lessTotalCoeff">
                            <ctg:decimal-presenter number="${competition['competition_less_total_coeff']}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>M:</div>
                        <div class='w3-col s3'>
                           <span name="moreTotalCoeff">
                               <ctg:decimal-presenter number="${competition['competition_more_total_coeff']}"/>
                           </span>
                        </div>
                    </div>
                    <div class='w3-col m2 s3'>
                        <div class='w3-col s2 w3-text-gray'>X:</div>
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

                        <div class='w3-col s1 w3-text-gray'>W:</div>
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