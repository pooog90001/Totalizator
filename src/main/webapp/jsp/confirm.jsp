
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1> На  вашу электронную почту отправлено письмо. Перейдите по ссылке в письме для завершения регистрации. </h1>
    <h1> An email has been sent to your email. Click the link in the email to complete the registration. </h1>
    <form name="form" action="/generalController" method="post">
        <input type="hidden" name="command" value="confirmEmail">
        <input type="submit" value="Повторить отправку">
    </form>

</body>
</html>
