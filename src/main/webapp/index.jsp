
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<fmt:requestEncoding value="UTF-8" />
<html>
  <head>
    <meta charset="UTF-8" >
    <title>$Title$</title>
  </head>
  <body>
  <form action="/login" method="post" accept-charset="UTF-8">
    <input type="text" name="login">
    <input type="text" name="password">
    <input type="submit" value="Отправить">
    </form>

  <a href="registration.jsp">
    Регистрация
    </a>
  </body>
</html>
