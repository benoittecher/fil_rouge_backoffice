<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zoran
  Date: 07/12/2022
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp">
    <jsp:param name="errorMsg" value="${error}"/>
    <jsp:param name="title" value="Liste des utilisateurs"/>
</jsp:include>
<h1 >Login</h1>
<c:if test="${loginFail}">
    <div class="alert alert-danger" role="alert">Mauvais login</div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/login">
    <div><input type="text" name="email" placeholder="email"></div>
    <div><input type="password" name="password" placeholder="mot de passe"></div>
    <div><input class="btn btn-primary" type="submit" value="Se connecter"></div>
</form>

<jsp:include page="footer.jsp"/>
