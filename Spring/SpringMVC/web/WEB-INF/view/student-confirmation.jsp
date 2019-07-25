<%--
  Created by IntelliJ IDEA.
  User: yaqiwang
  Date: 2019/7/23
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Student confirmation</title>
</head>
<body>
First name :${student.firstName}
<br><br>
Last name: ${student.lastName}
<br><br>
Country:  ${student.country}
<br><br>
Favorite language: ${student.favoriteLanguage}
<br><br>
Operating system:

<ul>
    <c:forEach var="temp" items="${student.operatingSystems}">
        <li>${temp}</li>
    </c:forEach>

</ul>

</body>
</html>
