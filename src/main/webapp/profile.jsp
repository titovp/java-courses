<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
 Hi, ${user.login}
 <form action="/add" method="get">
     <input type="text" id="postname" name="postname">
     <label for="postname">имя поста</label>
     <label for="text">Содержание поста</label>
     <textarea name="text" id="text" cols="30" rows="10"></textarea>
     <input type="submit" value="Отправить">

 </form>
</body>
</html>
