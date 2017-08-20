<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="../bar/admin_left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <%@include file="../bar/admin_left_bar.jsp" %>
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
                NEWS
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
                                Title:
                            </div>
                            <div class="w3-col s6">
                                <input type="text" name="title" required class="w3-input w3-border"
                                       placeholder="Fill this field">
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small">

                            <div class="w3-col s2 w3-padding">
                                Image:
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
                                Text
                            </div>
                            <div class="w3-col s8 ">
                                <textarea name="text" id="text" cols="70" rows="10" required
                                          placeholder="Fill this field"></textarea>
                            </div>
                        </div>
                        <div class="w3-row w3-padding-small w3-margin-top">
                            <input type="button" id="createNews" value="Create" class="w3-button w3-theme w3">
                        </div>
                    </form>
                    <div id="wrong" class="w3-row w3-text-red" style="display: none">
                        All field must be filled, and field on image selected. Images only with JPEG, JPG, PNG
                        extension.
                    </div>
                    <div id="errorResponse" class="w3-row w3-text-red" style="display: none">
                        Error of creation.
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
                                                Do you really want delete?
                                            </p>
                                            <div class="w3-row">
                                                <div class="w3-half">
                                                    <input onclick="(modal_del${news.id}).style.display='none';
                                                            delNews(this, '${news.id}', '${news.imageUrl}');"
                                                           type="button" class="w3-button" value="Yes">
                                                </div>
                                                <div class="w3-half">
                                                    <input type="button" class="w3-button" value="NO"
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
                                                    <b> ${news.title} </b>
                                                </button>
                                            </form>
                                        </div>
                                        <div class="w3-col s12">
                                            <p class="w3-small">${news.text}</p>
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
            <p>Delete error. try again</p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_del_error).style.display='none'">
        </div>
    </div>
</div>
<div id="modal_del_wrong" class="w3-modal">
    <div class="w3-modal-content">
        <div class="w3-container">
            <span onclick="(modal_del_wrong).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>
                This news already deleted or another cause.
            </p>
            <input type="button" class="w3-button" value="Ok" onclick="(modal_del_wrong).style.display='none'">
        </div>
    </div>
</div>


</body>
<script src="${pageContext.request.contextPath}/js/news.js"></script>
<link rel="stylesheet" type="text/css"
      href="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/css/imgareaselect-default.css"/>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/js/jquery.imgareaselect-0.9.10/scripts/jquery.imgareaselect.pack.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<%@include file="../partial/footer.jsp" %>




