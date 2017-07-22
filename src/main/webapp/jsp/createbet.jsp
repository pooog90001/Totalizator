<%--
  Created by IntelliJ IDEA.
  User: vlad_
  Date: 7/22/2017
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="partial/header.jsp" %>
<body>
<div class="w3-container w3-content main-container">
    <%--<ctg:info-time/>
    <fmt:message key="lbl.title" bundle="${rb}"/> <br>
    <a href="jsp/sign_up.jsp"> <fmt:message key="lbl.SignUp" bundle="${rb}"/> </a>
    <p><fmt:message key="lbl.or" bundle="${rb}"/></p>
    <a href="jsp/sign_in.jsp"> <fmt:message key="lbl.SignIn" bundle="${rb}"/> </a>--%>
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <!-- Profile -->
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center">My Profile</h4>
                    <p class="w3-center"><img src="/w3images/avatar3.png" class="w3-circle" style="height:106px;width:106px" alt="Avatar"></p>
                    <hr>
                    <p><i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i> Designer, UI</p>
                    <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i> London, UK</p>
                    <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i> April 1, 1988</p>
                </div>
            </div>
            <br>
            <!-- Accordion -->
            <div class="w3-card-2 w3-round">
                <div class="w3-white">
                    <button onclick="myFunction('Demo1')" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-circle-o-notch fa-fw w3-margin-right"></i> My Groups</button>
                    <div id="Demo1" class="w3-hide w3-container">
                        <p>Some text..</p>
                    </div>
                    <button onclick="myFunction('Demo2')" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-calendar-check-o fa-fw w3-margin-right"></i> My Events</button>
                    <div id="Demo2" class="w3-hide w3-container">
                        <p>Some other text..</p>
                    </div>
                    <button onclick="myFunction('Demo3')" class="w3-button w3-block w3-theme-l1 w3-left-align"><i class="fa fa-users fa-fw w3-margin-right"></i> My Photos</button>
                    <div id="Demo3" class="w3-hide w3-container">
                        <div class="w3-row-padding">
                            <br>
                            <div class="w3-half">
                                <img src="/w3images/lights.jpg" style="width:100%" class="w3-margin-bottom">
                            </div>
                            <div class="w3-half">
                                <img src="/w3images/nature.jpg" style="width:100%" class="w3-margin-bottom">
                            </div>
                            <div class="w3-half">
                                <img src="/w3images/mountains.jpg" style="width:100%" class="w3-margin-bottom">
                            </div>
                            <div class="w3-half">
                                <img src="/w3images/forest.jpg" style="width:100%" class="w3-margin-bottom">
                            </div>
                            <div class="w3-half">
                                <img src="/w3images/nature.jpg" style="width:100%" class="w3-margin-bottom">
                            </div>
                            <div class="w3-half">
                                <img src="/w3images/fjords.jpg" style="width:100%" class="w3-margin-bottom">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <br>

            <!-- Interests -->
            <div class="w3-card-2 w3-round w3-white w3-hide-small">
                <div class="w3-container">
                    <p>Interests</p>
                    <p>
                        <span class="w3-tag w3-small w3-theme-d5">News</span>
                        <span class="w3-tag w3-small w3-theme-d4">W3Schools</span>
                        <span class="w3-tag w3-small w3-theme-d3">Labels</span>
                        <span class="w3-tag w3-small w3-theme-d2">Games</span>
                        <span class="w3-tag w3-small w3-theme-d1">Friends</span>
                        <span class="w3-tag w3-small w3-theme">Games</span>
                        <span class="w3-tag w3-small w3-theme-l1">Friends</span>
                        <span class="w3-tag w3-small w3-theme-l2">Food</span>
                        <span class="w3-tag w3-small w3-theme-l3">Design</span>
                        <span class="w3-tag w3-small w3-theme-l4">Art</span>
                        <span class="w3-tag w3-small w3-theme-l5">Photos</span>
                    </p>
                </div>
            </div>
            <br>

            <!-- Alert Box -->
            <div class="w3-container w3-display-container w3-round w3-theme-l4 w3-border w3-theme-border w3-margin-bottom w3-hide-small">
        <span onclick="this.parentElement.style.display='none'" class="w3-button w3-theme-l3 w3-display-topright">
          <i class="fa fa-remove"></i>
        </span>
                <p><strong>Hey!</strong></p>
                <p>People are looking at your profile. Find out who.</p>
            </div>
        </div>

        <div class="w3-col m9">
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
        </div>
    </div>
</div>
</body>
<%@include file="partial/footer.jsp" %>
