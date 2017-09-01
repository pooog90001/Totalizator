<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.T" var="txtT"/>
<fmt:message bundle="${rb}" key="txt.bets" var="txtBets"/>
<fmt:message bundle="${rb}" key="txt.team" var="txtTeam"/>
<fmt:message bundle="${rb}" key="txt.score" var="txtScore"/>
<fmt:message bundle="${rb}" key="txt.place" var="txtPlace"/>
<fmt:message bundle="${rb}" key="txt.winner" var="txtWinner"/>

<c:forEach var="competition" items="${pastGames}">

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
            </c:if>
        </div>
        <c:forEach var="competitor" items="${competition['competitors']}">
            <input type="hidden" name="competitorId" value="${competitor['competitor_id']}">
            <div class='w3-col s6 w3-left-align'>
                <hr style="margin: 5px;">
                <div class="w3-row">
                    <div class='w3-col s5'><c:out value="${competitor['team_name']}"/></div>

                    <div class='w3-col s2 w3-text-gray'>
                        <c:choose>
                            <c:when test="${competition['competitors'].size() == 2}">
                                ${txtScore}:
                            </c:when>
                            <c:otherwise>
                                ${txtPlace}:
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class='w3-col s2'>
                            <span >
                                <c:out value="${competitor['competitor_result']}"/>
                                <c:if test="${competitor['competitor_is_win']}">
                                    <i class="w3-tiny">${txtWinner}</i>
                                </c:if>
                            </span>
                    </div>
                </div>
            </div>

        </c:forEach>
    </div>


</c:forEach>

