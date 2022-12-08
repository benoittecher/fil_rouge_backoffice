<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zoran
  Date: 07/12/2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1 >Login</h1>
<c:if test="${loginFail}">
    <div class="alert alert-danger" role="alert">Mauvais login</div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/login">
    <input type="text" name="email">
    <input type="password" name="password">
    <input type="submit" value="Se connecter">
</form>
</body>
</html>
