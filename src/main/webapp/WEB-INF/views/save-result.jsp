<%-- 회원 저장 - 뷰
  Created by IntelliJ IDEA.
  User: sypark
  Date: 24. 6. 20.
  Time: 오전 9:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <%--<li>id=<%=((Member)request.getAttribute("mbmer")).getId()%></li>--%>  <!-- request.getAttribute로 모델에 저장한 member 객체를 꺼낼 수 있지만, 너무 복잡해짐-->
    <li>id=${member.id}</li>            <!-- 이 문법을 사용하면 request의 attribute에 담긴 데이터 편리하게 조회 가능 -->
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>