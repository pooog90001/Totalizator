<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="lbl.News" var="txtNews"/>
<fmt:message bundle="${rb}" key="txt.kinds.of.sport" var="txtKindsOfSport"/>
<fmt:message bundle="${rb}" key="txt.teams" var="txtTeams"/>
<fmt:message bundle="${rb}" key="txt.users" var="txtUsers"/>
<fmt:message bundle="${rb}" key="txt.types.competition" var="txtTypesCompetition"/>
<fmt:message bundle="${rb}" key="txt.competitions" var="txtCompetitions"/>
<fmt:message bundle="${rb}" key="txt.statistic" var="txtStatistic"/>

<div class="">
    <div class="w3-white">
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_kind_of_sport_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtKindsOfSport}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_competition_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtCompetitions}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_competition_type_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtTypesCompetition}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_team_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtTeams}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="OPEN_USER_SETTINGS">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtUsers}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_news_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtNews}
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="OPEN_ADMIN_STATISTIC">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                ${txtStatistic}
            </button>
        </form>

    </div>
</div>
