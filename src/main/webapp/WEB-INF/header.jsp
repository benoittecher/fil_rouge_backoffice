<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><c:out value="${param.title}"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="/assets/style.css" type="text/css">

</head>
<body>
<div id="main-container">
<header>
    <nav>
        <div id="menu">
            <c:choose>
                <c:when test="${not empty sessionScope.utilisateur}">
                    <form method="post" action="${pageContext.request.contextPath}/logout">
                        <input type="submit" class="btn btn-dark" value="Déconnexion">
                    </form>
                </c:when>

                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/login" class="btn btn-dark">Connexion</a>
                </c:otherwise>

            </c:choose>
        </div>
    </nav>

</header>

<c:choose>
    <c:when test="${not empty param.errorMsg}">
        <div class="error-message">
            <p><c:out value="${param.errorMsg}"/></p>
        </div>
    </c:when>
</c:choose>

    <main>
