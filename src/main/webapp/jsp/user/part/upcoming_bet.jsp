<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="betAndGame" items="${upcomingBetsAndGames}">
    <div>
        <div id="upcomingGame${betAndGame['competition_id']}"
             class="w3-card-2  w3-margin-bottom w3-small w3-hover-light-grey">
            <div class=" w3-row w3-bar w3-black w3-tiny ">
                <div class="w3-bar-item w3-padding-small">
                    № ${betAndGame.competition.id}
                </div>
                <div class="w3-bar-item w3-padding-small">
                    <c:out value="${betAndGame['kind_of_sport_name']}"/>
                </div>
                <div class="w3-bar-item w3-padding-small">
                    Bet #<c:out value="${betAndGame.bet.id}"/>
                </div>
                <div class="w3-bar-item w3-padding-small">
                    Size:
                    <span class="w3-text-yellow">
                    <ctg:decimal-presenter number="${betAndGame.bet.cash}"/>$
                    </span>
                </div>
                <div class="w3-bar-item w3-right w3-padding-small">
                    <ctg:date-presenter date="${betAndGame.competition.dateStart}"/>
                </div>
            </div>
            <div class="w3-row  w3-padding-small ">
                <div class="w3-col s12 w3-center w3-padding">
                    <div class="w3-col s4">
                        <c:out value="${betAndGame.competition.name}"/>
                    </div>
                    <div class="w3-col s4">
                        <c:out value="${betAndGame['competition_type_name']}"/>
                    </div>
                </div>
                <c:if test="${betAndGame['competitors'].size() == 2}">
                    <div class='w3-col s3'>
                        <div class='w3-col s2 w3-text-gray'>T:</div>
                        <div class='w3-col s3'>
                            <span name="total">
                                <ctg:decimal-presenter number="${betAndGame.competition.total}"/>
                            </span>
                        </div>
                    </div>
                    <div class='w3-col s3'>
                        <c:choose>
                            <c:when test="${'LESS' eq betAndGame.bet.expectedResult.toString()}">
                                <span class='w3-col s2 w3-text-gray'>L:</span>
                                <span>
                                    <ctg:decimal-presenter number="${betAndGame.competition.lessTotal}"/>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class='w3-col s2 w3-text-gray'>L:</span>
                                <span>
                                    <ctg:decimal-presenter number="${betAndGame.competition.lessTotal}"/>
                                </span>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class='w3-col s3'>
                        <c:choose>
                            <c:when test="${'MORE' eq betAndGame.bet.expectedResult.toString()}">
                                <span class='w3-col s2 w3-text-green'>M:</span>
                                <span class="w3-text-green">
                                    <ctg:decimal-presenter number="${betAndGame.competition.moreTotal}"/>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class='w3-col s2 w3-text-gray'>M:</span>
                                <span>
                                    <ctg:decimal-presenter number="${betAndGame.competition.moreTotal}"/>
                                </span>
                            </c:otherwise>
                        </c:choose>

                    </div>
                    <div class='w3-col m2 s3'>
                        <c:choose>
                            <c:when test="${'EQUALS' eq betAndGame.bet.expectedResult.toString()}">
                                <span class='w3-col s2 w3-text-green'>X:</span>
                                <span class="w3-text-green">
                                    <ctg:decimal-presenter number="${betAndGame.competition.standoff}"/>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class='w3-col s2 w3-text-gray'>X:</span>
                                <span>
                                  <ctg:decimal-presenter number="${betAndGame.competition.standoff}"/>
                                </span>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </c:if>
            </div>
            <div class='w3-row'>
                <c:forEach var="competitor" items="${betAndGame['competitors']}">

                    <div class='w3-col s6 w3-left-align'>
                        <hr style="margin: 5px;">
                        <div class="w3-row">
                            <div class='w3-col s5'><c:out value="${competitor['team_name']}"/></div>
                            <c:choose>
                                <c:when test="${betAndGame.bet.competitorId eq competitor['competitor_id']}">
                                    <span class='w3-col s1 w3-text-green'>W:</span>
                                    <span class="w3-text-green">
                                    <ctg:decimal-presenter number="${competitor['competitor_win_coeff']}"/>
                                </span>
                                </c:when>
                                <c:otherwise>
                                    <div class='w3-col s1 w3-text-gray'>W:</div>
                                    <div class='w3-col s2'>
                                    <span>
                                        <ctg:decimal-presenter number="${competitor['competitor_win_coeff']}"/>
                                    </span>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>

                </c:forEach>
            </div>
        </div>
    </div>
</c:forEach>
