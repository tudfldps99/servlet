<%-- 회원 등록 폼 - 뷰
  Created by IntelliJ IDEA.
  User: sypark
  Date: 24. 6. 20.
  Time: 오전 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">      <!--http://localhost:8080/servlet-mvc/members/new-form -> http://localhost:8080/servlet-mvc/members/save -->
    username: <input type="text" name="username" />
    age:      <input type="text" name="age" />
    <button type="submit">전송</button>
</form>

</body>
</html>
