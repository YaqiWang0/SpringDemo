<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yaqiwang
  Date: 2019-08-15
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Company Home Page</title>
</head>
<body>
    <h2>Company home page</h2>
<hr>
    <p>
        welcome to the whole page</p>
    <hr>
    <!-- display username and role-->
    <p>
        User: <security:authentication property="principal.username"/>
    <br><br>
        Roles:<security:authentication property="principal.authorities"/>
    </p>

    <!--Add a link to point to /leaders ... this is for teh managers-->
    <security:authorize access="hasRole('MANAGER')">
    <p>
        <a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
        (Only for Manager peeps)
    </p>
    </security:authorize>
    <!--Add a link to point to /systems ... this is for the admins-->
    <security:authorize access="hasRole('ADMIN')">
    <p>
        <a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
        (Only for Admin peeps)
    </p>
    </security:authorize>

    <hr>
    <!--Add a logout button-->


    <hr>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">

    <input type="submit" value="Logout"/>
</form:form>
</body>
</html>
