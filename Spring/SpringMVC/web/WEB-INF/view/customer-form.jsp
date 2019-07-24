<%--
  Created by IntelliJ IDEA.
  User: yaqiwang
  Date: 2019/7/24
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Customer Registration Form</title>
    <style>
        .error{color: red}
    </style>
</head>
<body>
<i>Fill out the form. Asterist (*) means required.</i>
<form:form action="processForm" modelAttribute="customer">
    First Name: <form:input path="firstName"/>
    <br><br>

    Last Name: <form:input path="lastName"/>
    <form:errors path="lastName" cssClass="error"/>
    <br><br>
    <input type="submit" value="submit"/>
</form:form>


</body>
</html>
