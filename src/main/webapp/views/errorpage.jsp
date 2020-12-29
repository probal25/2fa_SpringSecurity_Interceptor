<%--
  Created by IntelliJ IDEA.
  User: proba
  Date: 17-Dec-20
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
<h2>Error Page</h2>
<c:if test="${not empty errmessage}">
    <h3>${errmessage}</h3>
</c:if>
</body>
</html>
