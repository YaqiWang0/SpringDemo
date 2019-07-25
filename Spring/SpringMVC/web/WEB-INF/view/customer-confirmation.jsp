<%--
  Created by IntelliJ IDEA.
  User: yaqiwang
  Date: 2019/7/24
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer Confirmation</title>
</head>
<body>
The customer is confirmed:${customer.firstName} ${customer.lastName}

<br><br>
Free Pass :${customer.freePasses}
<br><br>
Postal Code :${customer.postalCode}
</body>
</html>
