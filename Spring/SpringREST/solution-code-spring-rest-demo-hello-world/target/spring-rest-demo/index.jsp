<%--
  Created by IntelliJ IDEA.
  User: yaqiwang
  Date: 2019-08-21
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Spring Rest Demo

<hr>

<a href="${pageContext.request.contextPath}/test/hello"> Hello </a>

<hr>

<a href="${pageContext.request.contextPath}/api/student">Get All Students</a>
</body>
</html>
