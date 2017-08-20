<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="">
    <div class="w3-white">
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_kind_of_sport_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                Виды спорта
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_competition_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                Соревнования
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_competition_type_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                Типы соревнований
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_command_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                Команды
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="OPEN_USER_SETTINGS">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
               Пользователи
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="open_news_settings">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                Новости
            </button>
        </form>
        <form action="${pageContext.request.contextPath}/generalController" class="w3-margin-bottom w3-card-2">
            <input type="hidden" name="command" value="OPEN_ADMIN_STATISTIC">
            <button type="submit" class="w3-button w3-block w3-theme-l2 w3-center">
                Статистика
            </button>
        </form>

    </div>
</div>
