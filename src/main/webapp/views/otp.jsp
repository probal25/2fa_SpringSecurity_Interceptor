<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: probal
  Date: 27-Dec-20
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Otp</title>
</head>
<body>
<div class="container">

    <form action="/otp_validation" method="post">
        <label for="fname"><h4>Enter your otp</h4></label> <input type="text" id="otp" name="otp_value"><br>
        <input type="submit" name="submit" value="submit">
    </form>
</div>

<style>
    .container {
        display: flex;
        justify-content: center;
    }
</style>
</body>
</html>

