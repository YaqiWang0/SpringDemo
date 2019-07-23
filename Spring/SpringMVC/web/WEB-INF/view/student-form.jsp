<%--
  Created by IntelliJ IDEA.
  User: yaqiwang
  Date: 2019/7/23
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Student Form</title>
</head>
<body>



<form:form action="processForm" modelAttribute="student">
    First name:<form:input path="firstName"></form:input>
    <br><br>
    Last name:<form:input path="lastName"></form:input>
    <br><br>
    <form:select path="country">
        <form:options items="${student.countryOptions}"/>
    </form:select>
    <br><br>
    Favorite Language:
    <br>
    Java <form:radiobutton path="favoriteLanguage" value="Java"/>
    C# <form:radiobutton path="favoriteLanguage" value="C#"/>
    PHP <form:radiobutton path="favoriteLanguage" value="PHP"/>
    Ruby <form:radiobutton path="favoriteLanguage" value="Ruby"/>
    <br><br>
    Operating Systems:
    <br>
    <form:checkboxes path="operatingSystems"
                     items="${student.operatingSystemsOptions}"/>
<%--    Linux <form:checkbox path="operatingSystems" value="Linux"/>--%>
<%--    Mac <form:checkbox path="operatingSystems" value="Mac OS"/>--%>
<%--    Windows <form:checkbox path="operatingSystems" value="MS Windows"/>--%>
    <br><br>
    <input type="submit" value="submit">



</form:form>



</body>
</html>
