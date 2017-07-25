<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/22/2017
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="partial/header.jsp" %>



<nav class="w3-sidebar w3-bar-block w3-card " id="mySidebar">
    <div class="w3-container w3-theme-d2">
        <span onclick="closeSidebar()" class="w3-button w3-display-topright w3-large">X</span>
        <br>
    </div>
    <a class="w3-bar-item w3-button" href="#">Movies</a>
    <a class="w3-bar-item w3-button" href="#">Friends</a>
    <a class="w3-bar-item w3-button" href="#">Messages</a>
    <a class="w3-bar-item w3-button" href="#">Messages</a>
    <a class="w3-bar-item w3-button" href="#">Messages</a>
    <a class="w3-bar-item w3-button" href="#">Messages</a>
    <a class="w3-bar-item w3-button" href="#">Messages</a>
    <a class="w3-bar-item w3-button" href="#">Messages</a>

</nav>

<body>
<div class="w3-container w3-content main-container">
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <!-- Accordion -->
            <div class="w3-card-2 w3-round w3-hide-small">
                <div class="w3-white">
                    <button onclick="myFunction('Demo1')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Commands
                    </button>
                    <button onclick="myFunction('Demo2')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Kinds of competition
                    </button>
                    <button onclick="myFunction('Demo2')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Kinds of sports
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Competitions
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Users
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        News
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Statistic
                    </button>
                </div>
            </div>
        </div>

        <div class="w3-col m9 w3-container">
            <div class="w3-row-padding">
                <div class="w3-container w3-col s2 w3-hide-large w3-hide-medium">
                    <button class=" w3-padding-24 w3-button w3-large w3-hover-theme" onclick="openSidebar()">&#9776;</button>
                </div>
                <div class="w3-container w3-col s10">
                    <h1>Виды спорта</h1>
                </div>
            </div>
            <div class="w3-row">
                <div class="w3-col s3">
                    <p>Название</p>
                </div>
                <div class="w3-col s8">
                    <p>Кол-во комманд</p>
                </div>
            </div>

            <c:forEach var = "i" begin = "1" end = "15">
                <div class="w3-row  w3-hover-light-grey">
                    <div class="w3-container w3-card-2" style="margin: 3px;">
                        <div class="w3-col s6">
                            <p>фуутбол</p>
                        </div>
                        <div class="w3-col s2">
                            <p>2</p>
                        </div>
                        <div class="w3-col s2 w3-small">
                            <a href="#" class="w3-button">изменить</a>
                        </div>
                        <div class="w3-col s2 w3-small">
                            <button  id="deleteButton" class="w3-button w3-black">Delete</button>
                        </div>
                    </div>
                </div>
            </c:forEach>


            <div id="modalBeforeDelete" class="w3-modal">
                <div class="w3-modal-content w3-animate-top w3-card-4">
                    <header class="w3-container w3-teal">
                        <span id="closeModalBeforeDelete" class="w3-button w3-display-topright">
                            &times;
                        </span>
                        <h2>Modal Header</h2>
                    </header>
                    <div class="w3-container">
                        <button class="w3-button w3-theme">Delete</button>
                        <button class="w3-button w3-theme">Cancel</button>
                    </div>
                    <footer class="w3-container w3-teal">
                        <p>Modal Footer</p>
                    </footer>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
<%@include file="partial/footer.jsp" %>
