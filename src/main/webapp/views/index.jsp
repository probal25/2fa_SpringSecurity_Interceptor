<%--
  Created by IntelliJ IDEA.
  User: proba
  Date: 14-Dec-20
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>HOME</title>
</head>

<h3>HOME PAGE</h3>
<button><a href="<c:url value="/admin" />">Admin</a></button>
<button><a href="<c:url value="/user" />">User</a></button>
<c:if test="${not empty message}">
    <h1>${message}</h1>
</c:if>

<button><a href="<c:url value="/logout" />">Logout</a></button>
</body>
</html>
