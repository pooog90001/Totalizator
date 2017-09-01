<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>

<fmt:setLocale value="${sessionScope.get('locale')}" scope="session"/>
<fmt:setBundle basename="locale/text" var="rb"/>
<fmt:message bundle="${rb}" key="txt.before.delete.competition" var="txtBeforeDelete"/>
<fmt:message bundle="${rb}" key="txt.competition.type" var="txtCompetitionType"/>
<fmt:message bundle="${rb}" key="txt.choose.competition.type" var="txtChooseCompetitionType"/>
<fmt:message bundle="${rb}" key="txt.yes" var="txtYes"/>
<fmt:message bundle="${rb}" key="txt.competition.name" var="txtCompetitionName"/>
<fmt:message bundle="${rb}" key="txt.active" var="txtActive"/>
<fmt:message bundle="${rb}" key="txt.in.name.must.be" var="txtInNameMustBe"/>
<fmt:message bundle="${rb}" key="txt.symbols" var="txtSymbols"/>
<fmt:message bundle="${rb}" key="txt.all.fields.be.filled" var="txtAllFieldsMustFilled"/>
<fmt:message bundle="${rb}" key="txt.competition.type.not.correct" var="txtTypeNotCorrect"/>
<fmt:message bundle="${rb}" key="txt.delete.error" var="txtDeleteError"/>
<fmt:message bundle="${rb}" key="txt.try.again" var="txtTryAgain"/>
<fmt:message bundle="${rb}" key="txt.wrong.access" var="txtWrongAccess"/>
<fmt:message bundle="${rb}" key="txt.delete.failed" var="txtDeleteFailed"/>
<fmt:message bundle="${rb}" key="txt.edit.error" var="txtEditError"/>
<fmt:message bundle="${rb}" key="txt.wrong.number.format" var="txtWrongNumberFormat"/>
<fmt:message bundle="${rb}" key="txt.some.kind.mistake" var="txtSomeKindMistake"/>
<fmt:message bundle="${rb}" key="txt.check.input.data" var="txtCheckInputData"/>
<fmt:message bundle="${rb}" key="lbl.or" var="txtOr"/>
<fmt:message bundle="${rb}" key="txt.ok" var="txtOk"/>
<fmt:message bundle="${rb}" key="txt.no" var="txtNo"/>
<fmt:message bundle="${rb}" key="txt.sport.name" var="txtSportName"/>
<fmt:message bundle="${rb}" key="txt.types.competition" var="txtTypeCompetition"/>
<fmt:message bundle="${rb}" key="lbl.Name" var="txtName"/>
<fmt:message bundle="${rb}" key="txt.actions" var="txtActions"/>
<fmt:message bundle="${rb}" key="txt.wrong.delete.team" var="txtWrongDeleteTeam"/>
<fmt:message bundle="${rb}" key="txt.sport.already.exist" var="txtSportExist"/>
<fmt:message bundle="${rb}" key="txt.kinds.of.sport" var="txtKindOfSport"/>
<fmt:message bundle="${rb}" key="txt.competitors.count" var="txtCompetitorsCount"/>
<fmt:message bundle="${rb}" key="txt.number.competitors.must.be" var="txtNumberOfCompetitorsMustBe"/>
<fmt:message bundle="${rb}" key="txt.invalid.value" var="txtInvalidValue"/>
<fmt:message bundle="${rb}" key="txt.kind.of.sport" var="txtKindOfSport"/>
<fmt:message bundle="${rb}" key="txt.kind.of.sport" var="txtKindOfSport"/>
<fmt:message bundle="${rb}" key="txt.teams" var="txtTeams"/>
<fmt:message bundle="${rb}" key="txt.choose.sport" var="txtChooseSport"/>
<fmt:message bundle="${rb}" key="txt.without" var="txtWithout"/>
<fmt:message bundle="${rb}" key="txt.name.already.exist" var="txtNameAlreadyExist"/>
<fmt:message bundle="${rb}" key="lbl.News" var="txtNews"/>
<fmt:message bundle="${rb}" key="txt.text" var="txtText"/>
<fmt:message bundle="${rb}" key="txt.title" var="txtTitle"/>
<fmt:message bundle="${rb}" key="txt.image" var="txtImage"/>
<fmt:message bundle="${rb}" key="txt.fill.news.fields" var="txtFillNewsFields"/>
<fmt:message bundle="${rb}" key="txt.database.error" var="txtDatabaseError"/>
<fmt:message bundle="${rb}" key="txt.upload.error" var="txtUploadError"/>
<fmt:message bundle="${rb}" key="txt.session.is.empty" var="txtSessionisEmpty"/>
<fmt:message bundle="${rb}" key="txt.database.or.upload.error" var="txtDatebaseOrUploadError"/>
<fmt:message bundle="${rb}" key="txt.news.already.deleted" var="txtNewsAlreadyDeleted"/>

<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="/jsp/bar/admin_left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="/jsp/bar/admin_left_bar.jsp" %>
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9">

        <!-- First Photo Grid-->
        <div class="w3-row-padding">
            <div class="w3-container w3-xlarge w3-display-container">
                <div class="w3-display-right w3-padding">
                    <button id="create" type="button" class="w3-button w3-card-4 w3-circle w3-theme-l1"
                            style="padding: 15px 17px;">
                        <i class='fa fa-plus-circle'></i>
                    </button>
                </div>
                ${txtNews}
            </div>

            <div id="createField" class="w3-col s12 w3-container w3-padding-small" style="display: none;">
                <div class="w3-row w3-hover-light-grey w3-card-2">
                    <form  id='form' action="${pageContext.request.contextPath}/uploadController"
                          class="w3-card-2 w3-white w3-round w3-small" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="command" value="create_news">
                        <input type="hidden" name="width" id="width" value="0">
                        <input type="hidden" name="height" id="height" value="0">
                        <input type="hidden" name="x1" id="x1" value="0">
                        <input type="hidden" name="x2" id="x2" value="0">
                        <input type="hidden" name="y1" id="y1" value="0">
                        <input type="hidden" name="y2" id="y2" value="0">
                        <div class="w3-row w3-padding-small">
                            <div class="w3-col s2 w3-padding">
                                ${txtTitle}:
                            </div>
                            <div class="w3-col s6">
                                <input type="text" name="title" required class="w3-input w3-border"
                                       placeholder="Fill this field">
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small">

                            <div class="w3-col s2 w3-padding">
                                ${txtImage}:
                            </div>
                            <div class="w3-col s6">
                                <input type="file" required id="imgInp" name="image" accept="image/*" class="w3-button">
                            </div>

                            <div class="w3-col s12 w3-center w3-padding-large">
                                <img id="blah" src="#" class="" alt="image" style="max-width: 100%;"/>
                            </div>
                            <div class="w3-col s12" id="result">
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small">
                            <div class="w3-col s2 w3-padding">
                                ${txtText}:
                            </div>
                            <div class="w3-col s8 ">
                                <textarea name="text" id="text" cols="70" rows="10" required
                                          style="resize: none;" placeholder="Fill this field"></textarea>
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small w3-margin-top">
                            <input type="button" id="createNews" value="Create" class="w3-button w3-theme w3">
                        </div>
                    </form>
                    <div id="wrongData" class="wrong w3-row w3-text-red" style="display: none">
                        ${txtFillNewsFields}
                    </div>
                    <div id="wrongDB" class="wrong w3-row w3-text-red" style="display: none">
                        ${txtDatabaseError}
                    </div>
                    <div id="wrongUpload" class="wrong w3-row w3-text-red" style="display: none">
                        ${txtUploadError}
                    </div>
                    <div id="accessDenied" class="wrong w3-row w3-text-red" style="display: none">
                        ${txtSessionisEmpty}
                    </div>
                    <div id="errorResponse" class="wrong w3-row w3-text-red" style="display: none">
                        ${txtDatebaseOrUploadError}
                    </div>

                </div>
            </div>
            <div class="w3-col s12">
                <div class="w3-container w3-padding-small w3-card-2 w3-center">
                    <c:forEach var="news" items="${newsList}">
                        <div  id='${news.id}' class="w3-container w3-margin-top ">
                            <div class="w3-row w3-card-2 w3-display-container">

                                <div class="w3-display-topright w3-display-hover w3-small">
                                    <button class="w3-button w3-black w3-padding-small"
                                            onclick="(modal_del${news.id}).style.display='block'">
                                        <i class="fa fa-remove"></i>
                                    </button>
                                </div>

                                <div id="modal_del${news.id}" class="w3-modal w3-center">
                                    <div class="w3-modal-content">
                                        <div class="w3-container">
                                            <span onclick="(modal_del${news.id}).style.display='none'"
                                                  class="w3-button w3-display-topright">
                                                &times;
                                            </span>
                                            <p>
                                                    ${txtBeforeDelete}
                                            </p>
                                            <div class="w3-row">
                                                <div class="w3-half">
                                                    <input onclick="(modal_del${news.id}).style.display='none';
                                                            delNews(this, '${news.id}', '${news.imageUrl}');"
                                                           type="button" class="w3-button" value="${txtYes}">
                                                </div>
                                                <div class="w3-half">
                                                    <input type="button" class="w3-button" value="${txtNo}"
                                                           onclick="(modal_del${news.id}).style.display='none'">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                                <div class="w3-col s3">
                                    <div class="w3-display-container w3-padding">
                                        <img src="${newsImagePath}${news.imageUrl}" alt="${news.title}"
                                             class="w3-image" style="width: 100%">
                                        <div class="w3-display-topright w3-tiny w3-padding">
                                            <i class="w3-black">
                                                <ctg:date-presenter date="${news.dateCreation}"/>
                                            </i>
                                        </div>
                                    </div>
                                </div>
                                <div class="w3-col s9">
                                    <div class="w3-row">
                                        <div class="w3-col s12">
                                            <form action="${pageContext.request.contextPath}/generalController">
                                                <input type="hidden" name="command" value="open_concrete_news">
                                                <input type="hidden" name="newsId" value="${news.id}">
                                                <button type="submit"
                                                        class="w3-hover-none w3-button w3-hover-text-gray">
                                                    <b> <c:out value="${news.title}"/> </b>
                                                </button>
                                            </form>
                                        </div>
                                        <div class="w3-col s12">
                                            <p class="w3-small">
                                                <c:out value="${news.text}"/>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="pagination  w3-margin-top w3-padding-small">
                        <ctg:pagination total="${newsCount}" limit="${limit}" command="open_news_settings"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="modal_del_error" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_error).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>${txtDeleteError}. ${txtTryAgain}.</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_del_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_del_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>
                ${txtNewsAlreadyDeleted}
            </p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_del_wrong).style.display='none'">
        </div>
    </div>
</div>


</body>
<script src="${pageContext.request.contextPath}/js/news.js"></script>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/vendors/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<%@include file="/jsp/partial/footer.jsp" %>




