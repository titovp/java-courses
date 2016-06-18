<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>
<form action="/reg" method="post" accept-charset="UTF-8">
    <label for="login"> UserName
    </label>
    <input type="text" id="login" name="login">
    <br>
    <label for="password"> Your password
    </label>


    <input type="password" id="password" name="password">
    <input type="submit" value="ОТПРАВИТЬ">


</form>

</body>
</html>
