<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="partial/header.jsp" %>

<div class="w3-container w3-content w3-hide-large w3-hide-medium">
<button class="w3-bar-item w3-button w3-xxxlarge w3-hover-theme" onclick="openSidebar()">&#9776;</button>
</div>

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

    <%--<ctg:info-time/>
    <fmt:message key="lbl.title" bundle="${rb}"/> <br>
    <a href="jsp/sign_up.jsp"> <fmt:message key="lbl.SignUp" bundle="${rb}"/> </a>
    <p><fmt:message key="lbl.or" bundle="${rb}"/></p>
    <a href="jsp/sign_in.jsp"> <fmt:message key="lbl.SignIn" bundle="${rb}"/> </a>--%>
        <div class="w3-col m3">
            <!-- Accordion -->
            <div class="w3-card-2 w3-round w3-hide-small">
                <div class="w3-white">
                    <button onclick="myFunction('Demo1')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Футбол
                    </button>
                    <button onclick="myFunction('Demo2')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Баскетбол
                    </button>
                    <button onclick="myFunction('Demo2')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Баскетбол
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Футбол
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Футбол
                    </button>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align">
                        Футбол
                    </button>
                </div>
            </div>
            <!-- End Left Column -->
        </div>

        <!-- Middle Column -->
        <div class="w3-col m9">

            <!-- First Photo Grid-->
            <div class="w3-row-padding">
                <div class="w3-container">
                    <h3><a href="#">Новости</a></h3>
                </div>
                <div class="w3-third w3-container">
                    <div class="w3-card-2 w3-white w3-round">
                        <img src="http://molbuk.ua/uploads/posts/2013-06/1371654936_futbol_turnir.jpg" alt="Norway" style="width: 100%;" class="w3-hover-opacity">
                        <div class="w3-container w3-white">
                            <a href="#" class="w3-hover-none w3-hover-text-gray"><p><b>Lorem Ipsum</b></p></a>
                            <p class="w3-small">Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
                        </div>
                    </div>
                </div>
                <div class="w3-third w3-container">
                    <div class="w3-card-2 w3-white w3-round">
                        <img src="http://molbuk.ua/uploads/posts/2013-06/1371654936_futbol_turnir.jpg" alt="Norway" style="width: 100%;" class="w3-hover-opacity">
                        <div class="w3-container w3-white">
                            <a href="#" class="w3-hover-none w3-hover-text-gray"><p><b>Lorem Ipsum</b></p></a>
                            <p class="w3-small">Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
                        </div>
                    </div>
                </div>
                <div class="w3-third w3-container">
                    <div class="w3-card-2 w3-white w3-round">
                        <img src="http://molbuk.ua/uploads/posts/2013-06/1371654936_futbol_turnir.jpg" alt="Norway" style="width: 100%;" class="w3-hover-opacity">
                        <div class="w3-container w3-white">
                            <a href="#" class="w3-hover-none w3-hover-text-gray"><p><b>Lorem Ipsum</b></p></a>
                            <p class="w3-small">Praesent tincidunt sed tellus ut rutrum. Sed vitae justo condimentum, porta lectus vitae, ultricies congue gravida diam non fringilla.</p>
                        </div>
                    </div>
                </div>
                <div class="w3-container w3-margin-bottom">
                    <a href="#" class="w3-right w3-opacity w3-small">Все новости</a>
                </div>
            </div>


            <div class="w3-row-padding">
                <div class="w3-container">
                    <h3><a href="#">LIVE</a></h3>
                </div>
                <div class="w3-col s12 w3-container">
                    <div class="w3-card-2 w3-white w3-round">
                        <div class="w3-row">
                            <div class="w3-col s1"> 1</div>
                            <div class="w3-col s2">футбол</div>
                            <div class="w3-col s1"></div>
                            <div class="w3-col s3"></div>
                            <div class="w3-col s12"></div>
                            <div class="w3-col s12"></div>
                        </div>
                    </div>
                </div>

                <div class="w3-container w3-margin-bottom">
                    <a href="#" class="w3-right w3-opacity w3-small">Все LIVE</a>
                </div>
            </div>

            <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                <img src="/w3images/avatar2.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
                <span class="w3-right w3-opacity">1 min</span>
                <h4>John Doe</h4><br>
                <hr class="w3-clear">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                <div class="w3-row-padding" style="margin:0 -16px">
                    <div class="w3-half">
                        <img src="/w3images/lights.jpg" style="width:100%" alt="Northern Lights" class="w3-margin-bottom">
                    </div>
                    <div class="w3-half">
                        <img src="/w3images/nature.jpg" style="width:100%" alt="Nature" class="w3-margin-bottom">
                    </div>
                </div>
                <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i>  Like</button>
                <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-comment"></i>  Comment</button>
            </div>

            <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                <img src="/w3images/avatar5.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
                <span class="w3-right w3-opacity">16 min</span>
                <h4>Jane Doe</h4><br>
                <hr class="w3-clear">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i>  Like</button>
                <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-comment"></i>  Comment</button>
            </div>

            <div class="w3-container w3-card-2 w3-white w3-round w3-margin"><br>
                <img src="/w3images/avatar6.png" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:60px">
                <span class="w3-right w3-opacity">32 min</span>
                <h4>Angie Jane</h4><br>
                <hr class="w3-clear">
                <p>Have you seen this?</p>
                <img src="/w3images/nature.jpg" style="width:100%" class="w3-margin-bottom">
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                <button type="button" class="w3-button w3-theme-d1 w3-margin-bottom"><i class="fa fa-thumbs-up"></i>  Like</button>
                <button type="button" class="w3-button w3-theme-d2 w3-margin-bottom"><i class="fa fa-comment"></i>  Comment</button>
            </div>

            <!-- End Middle Column -->
        </div>

        <!-- Right Column -->
        <%--<div class="w3-col m2">
            <div class="w3-card-2 w3-round w3-white w3-center">
                <div class="w3-container">
                    <p>Upcoming Events:</p>
                    <img src="/w3images/forest.jpg" alt="Forest" style="width:100%;">
                    <p><strong>Holiday</strong></p>
                    <p>Friday 15:00</p>
                    <p><button class="w3-button w3-block w3-theme-l4">Info</button></p>
                </div>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-center">
                <div class="w3-container">
                    <p>Friend Request</p>
                    <img src="/w3images/avatar6.png" alt="Avatar" style="width:50%"><br>
                    <span>Jane Doe</span>
                    <div class="w3-row w3-opacity">
                        <div class="w3-half">
                            <button class="w3-button w3-block w3-green w3-section" title="Accept"><i class="fa fa-check"></i></button>
                        </div>
                        <div class="w3-half">
                            <button class="w3-button w3-block w3-red w3-section" title="Decline"><i class="fa fa-remove"></i></button>
                        </div>
                    </div>
                </div>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-padding-16 w3-center">
                <p>ADS</p>
            </div>
            <br>

            <div class="w3-card-2 w3-round w3-white w3-padding-32 w3-center">
                <p><i class="fa fa-bug w3-xxlarge"></i></p>
            </div>

            <!-- End Right Column -->
        </div>--%>

        <!-- End Grid -->
    </div>
</div>
</body>

<%@include file="partial/footer.jsp" %>
