<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>


<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar" style="display: none;">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-hover-theme w3-display-topright w3-padding-small">
            <i class="fa fa-remove w3-small"></i>
        </span>
        <br>
    </div>
    <%@include file="/jsp/bar/left_bar.jsp" %>
</nav>

<body>

<div class="w3-container w3-content main-container">
    <div class="w3-col m3 w3-hide-small">
        <!-- Accordion -->
        <%@include file="/jsp/bar/left_bar.jsp" %>
        <!-- End Left Column -->
    </div>
    <div class="w3-container w3-left w3-content w3-hide-large w3-hide-medium">
        <button class="w3-button w3-large w3-hover-theme " onclick="openSidebar()">&#8694;</button>
    </div>
    <div class="w3-col m9 w3-container">

        <!-- First Photo Grid-->
        <div class=" w3-container">
            <span class="w3-xlarge w3-padding w3-hover-none">
                Help
            </span>
        </div>
        <div class="w3-card-2 w3-container">
            <h3>If you have a problem you can write to us and we try solve your problem.</h3>

            <form class="w3-container">
                <input class="w3-input w3-border" type="hidden" name="command" value="PROBLEM_EMAIL"/>
                <label>
                    Your email:
                    <input class="w3-input w3-border" type="email" name="email" required style="max-width: 250px;">
                </label>
                <br>
                <label>
                    Problem:
                    <textarea class="w3-input w3-border" name="text" id="text" cols="30" rows="3" required
                              style="resize: none;">

                    </textarea>
                </label>
                <br>
                <input type="submit" value="Send" class="w3-button w3-theme-l3"/>
            </form>
        </div>

    </div>
</div>
</body>

<%@include file="/jsp/partial/footer.jsp" %>
