<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/partial/header.jsp" %>
<script src="${pageContext.request.contextPath}/js/sign_in.js"></script>

<fmt:message key="lbl.ForgotPassword" bundle="${rb}" var="forgotPassword"/>
<fmt:message key="lbl.SignIn" bundle="${rb}" var="signIn"/>
<fmt:message key="lbl.Authorization" bundle="${rb}" var="authorization"/>
<fmt:message key="lbl.Password" bundle="${rb}" var="password"/>
<fmt:message key="lbl.Email" bundle="${rb}" var="email"/>
<fmt:message key="warn.wrongEmailorPassword" bundle="${rb}" var="wrongData"/>

<body>
<div class="w3-row-padding">
    <div class="w3-col l2 m1">
        <div class="w3-container"></div>
    </div>
    <div class="w3-col l8 m10">

        <div class="w3-display-container">
            <div class="w3-display-topright">
                <button type="button" class="w3-button w3-card-4 w3-circle w3-theme-l3"
                        style="padding: 15px 17px;" onclick="history.back(); return false;">
                    <i class='fa fa-long-arrow-left'></i>
                </button>
            </div>
            <h1>Profile</h1>
        </div>


        <div class=" w3-margin-top">

            <%--User info section--%>
            <div class="w3-col m4 w3-container w3-card-2 w3-display-container">
                <div class="w3-display-topright">
                    <a href="${pageContext.request.contextPath}/jsp/user/change_avatar.jsp"
                       class="w3-button w3-tiny w3-black w3-padding-small w3-display-hover">Change avatar</a>
                    <a href="${pageContext.request.contextPath}/jsp/user/change_password.jsp"
                       class="w3-button w3-tiny w3-black w3-padding-small w3-display-hover">Change Password</a>
                </div>
                <div class="w3-col s12 w3-center">
                    <div class=" w3-padding">
                        <img src="${userImagePath}${user.avatarURL}" alt="${user.name}"
                             class="w3-image w3-circle" style="max-height: 150px">
                    </div>
                </div>
                <div class="w3-col s12 w3-container w3-center w3-medium">
                    <div class="w3-col s12">
                        <button onclick="(modalAddMoney).style.display = 'inherit'"
                                class="w3-button w3-input w3-black w3-small w3-margin-bottom w3-padding-small">
                            Пополнить счёт
                        </button>
                    </div>
                    <div class="w3-col s12">
                        <button onclick="(modalWithdrawMoney).style.display = 'inherit'"
                                class="w3-button w3-input w3-black w3-small w3-margin-bottom w3-padding-small">
                            Снять деньги
                        </button>
                    </div>
                </div>
                <div class="w3-col s12 w3-container w3-left-align w3-medium">
                    <span>
                        <i>Name: </i>
                        <b> <c:out value="${user.name}"/> </b>
                    </span>
                    <br>
                    <span>
                        <i>Cash: </i>
                        <b> <ctg:decimal-presenter number="${user.cash}"/>$ </b>
                    </span>
                    <br>
                    <span>
                        <i>Role: </i>
                        <b> <c:out value="${user.type}"/> </b>
                    </span>
                    <br>
                    <span>
                        <i>Email: </i>
                        <b> <c:out value="${user.email}"/> </b>
                    </span>
                    <br>
                </div>
            </div>

            <%--Bet info section--%>
            <div class="w3-col m8 w3-container">
                <div class="w3-bar w3-black w3-card-2 w3-margin-bottom">
                    <button class="w3-bar-item w3-button generalLink w3-white"
                            onclick="openTab(event,'Upcoming', 'general', 'generalLink')">
                        Expected rates
                    </button>
                    <button class="w3-bar-item w3-button generalLink"
                            onclick="openTab(event,'Past', 'general', 'generalLink')">
                        Betting result
                    </button>
                </div>

                <div id="Upcoming" class="general">
                    <%--Incude upcoming bets part--%>
                    <div class="scroll" id="style-2">
                        <%@include file="/jsp/user/part/upcoming_bet.jsp" %>
                    </div>
                </div>

                <div id="Past" class="general" style="display:none">
                    <%--Incude upcoming bets part--%>
                    <div class="scroll" id="style-2">
                        <%@include file="/jsp/user/part/past_bet.jsp" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="w3-col l2 m1">
    <div class="w3-container"></div>
</div>

<div id="modalAddMoney" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <form id="addCashForm">
                <input type="hidden" name="command" value="ADD_MONEY"/>
                <span onclick="(modalAddMoney).style.display='none'"
                      class="w3-button w3-display-topright">&times;</span>
                <p>Введите сумму ($) : </p>
                <p>
                    <input type="number" name="cash" id="addCash" step="1" class="w3-input w3-border" min="1" max="1000"
                           onkeypress="return false;"/>
                </p>
                <input type="button" class="w3-button" value="Пополнить" onclick="addMoney(this, 'modalAddMoney');">
                <input type="button" class="w3-button" value="Cancel" onclick="(modalAddMoney).style.display='none'">
            </form>
        </div>
    </div>
</div>
<div id="modalWithdrawMoney" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <form id="withdrawCashForm">
                <input type="hidden" name="command" value="WITHDRAW_MONEY"/>
                <span onclick="(modalWithdrawMoney).style.display='none'"
                      class="w3-button w3-display-topright">&times;</span>
                <p>Введите сумму, которую хотите снять ($) : </p>
                <p>
                    <input type="number" name="cash" id="withdrawCash" step="1" class="w3-input w3-border" min="1"
                           max="<:ctg:decimal-presenter number='${user.cash}' scale='0'/>" onkeypress="return false;"/>
                </p>
                <input type="button" class="w3-button" value="Вывести"
                       onclick="withdrawMoney(this, 'modalWithdrawMoney')">
                <input type="button" class="w3-button" value="Cancel"
                       onclick="(modalWithdrawMoney).style.display='none'">
                <div class="w3-text-red" id="littleMoney" style="display: none">You haven't so mach money.</div>
            </form>
        </div>
    </div>
</div>

<div id="wrongData" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(wrongData).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Wrong data. Must be posititve number format 1-1000$</p>
            <input type="button" class="w3-button" value="Ok" onclick="(wrongData).style.display='none'">
        </div>
    </div>
</div>

<div id="serverError" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(serverError).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Server error. Check conection.</p>
            <input type="button" class="w3-button" value="Ok" onclick="(serverError).style.display='none'">
        </div>
    </div>
</div>

<div id="accessDenied" class="w3-modal">
    <div class="w3-modal-content" style="max-width: 50%;">
        <div class="w3-container">
            <span onclick="(accessDenied).style.display='none'" class="w3-button w3-display-topright">&times;</span>
            <p>Access Denied. Reload page and try again.</p>
            <input type="button" class="w3-button" value="Ok" onclick="(accessDenied).style.display='none'">
        </div>
    </div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/profile.js"></script>

<%@include file="/jsp/partial/footer.jsp" %>
