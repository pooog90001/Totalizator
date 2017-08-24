<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:forEach var="competition" items="${pastGames}">

    <div id="upcomingGame${competition['competition_id']}"
         class="w3-container w3-card-2  w3-margin-bottom w3-small w3-display-container w3-hover-light-grey">

        <div class="w3-center w3-row w3-small w3-padding">
            <div class="w3-display-topleft w3-tiny w3-theme w3-padding-small">
                № ${competition['competition_id']}
            </div>
            <div class="w3-col s2"></div>
            <div class="w3-col s4">
                    ${competition['kind_of_sport_name']}
            </div>
            <div class="w3-col s4">
                    ${competition['competition_type_name']}
            </div>
            <div class="w3-display-topright w3-tiny w3-theme w3-padding-small">
                <ctg:date-presenter date="${competition['competition_date_start']}"/>
            </div>
            <div class="w3-col s2"></div>

        </div>
        <div class="w3-row  w3-padding-small">
            <div class="w3-col m4 s12 ">
                    <span name="competitionName">
                            ${competition['competition_name']}
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
            </c:if>
        </div>
        <c:forEach var="competitor" items="${competition['competitors']}">
            <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
            <div class='w3-col s6 w3-left-align'>
                <hr style="margin: 5px;">
                <div class="w3-row">
                    <div class='w3-col s5'> ${competitor['command_name']} </div>

                    <div class='w3-col s2 w3-text-gray'>
                        <c:choose>
                            <c:when test="${competition['competitors'].size() == 2}">
                                Score:
                            </c:when>
                            <c:otherwise>
                                Place:
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class='w3-col s2'>
                            <span >
                                ${competitor['competitor_result']}
                                <c:if test="${competitor['competitor_is_win']}">
                                   <i class="w3-tiny">winner</i>
                                </c:if>
                            </span>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>


</c:forEach>

