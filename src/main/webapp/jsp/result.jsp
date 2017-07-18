<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>RESULT</title>
</head>
    <body>
         <a href="index.jsp">Back</a> <br>
         <p> Parser type: ${parserType}. File contains ${tariffs.size()} tariffs</p>
        <c:forEach items = "${tariffs}" var = "tariff">
         <table border="1">
             <tr>
                <th colspan="3">
                    <p>Type: ${tariff.getClass().getSimpleName()};  name: ${tariff.getName()};   id: ${tariff.getId()}</p>
                </th>
             </tr>
             <tr>
                 <td>Operator</td>
                 <td colspan="2">${tariff.getOperatorName()}</td>
             </tr>
             <tr>
                 <td>Payroll</td>
                 <td colspan="2">${tariff.getPayroll()} BYR</td>
             </tr>
             <tr>
                 <td rowspan="3"> Call Prices: </td>
                 <td>Within network</td>
                 <td>${tariff.getCallPrices().getWithinNetwork()} BYR</td>
             </tr>
             <tr>
                 <td>Other network</td>
                 <td>${tariff.getCallPrices().getOtherNetwork()} BYR</td>
             </tr>
             <tr>
                 <td>Fixed phones</td>
                 <td>${tariff.getCallPrices().getFixedPhones()} BYR</td>
             </tr>
             <tr>
                 <td rowspan="2"> SMS Prices</td>
                 <td>Within network</td>
                 <td>${tariff.getSmsPrises().getWithinNetwork()} BYR</td>
             </tr>
             <tr>
                 <td>Other network</td>
                 <td>${tariff.getSmsPrises().getOtherNetwork()} BYR</td>
             </tr>
             <tr>
                 <td rowspan="3"> Parameters</td>
                 <td>Connection fee</td>
                 <td>${tariff.getParameters().getConnectionFee()} BYR</td>
             </tr>
             <tr>
                 <td>Count favorite numbers</td>
                 <td>${tariff.getParameters().getCountFavoriteNumbers()}</td>
             </tr>
             <tr>
                 <td>Tariffication</td>
                 <td>${tariff.getParameters().getTariffication()}</td>
             </tr>
             <c:if test="${tariff.getClass().getSimpleName() == 'InternetTariff'}">
                 <tr>
                     <td>Count free megabytes</td>
                     <td colspan="2">${tariff.getFreeMegabytes()}</td>
                 </tr>
                 <tr>
                     <td>Price for megabyte</td>
                     <td colspan="2">${tariff.getInternetPrice()}</td>
                 </tr>
             </c:if>
             <c:if test="${tariff.getClass().getSimpleName() == 'MMSTariff'}">
                 <tr>
                     <td>MMS price</td>
                     <td colspan="2">${tariff.getMmsPrice()}</td>
                 </tr>
                 <tr>
                     <td>Count free MMS</td>
                     <td colspan="2">${tariff.getFreeMms()}</td>
                 </tr>
             </c:if>
         </table><br>
        </c:forEach>

    </body>
</html>
