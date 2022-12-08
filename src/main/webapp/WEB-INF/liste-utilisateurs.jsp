<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des utilisateurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<h1>Liste des utilisateurs</h1>

<a class="btn btn-primary" href="${pageContext.request.contextPath}/utilisateurs/ajouter">Ajouter un utilisateur</a>

<c:forEach items="${utilisateurs}" var="utilisateur">
<ul>
    <li>${utilisateur.mail}</li>
    <li>${utilisateur.prenom} ${utilisateur.nom}</li>
    <li>${utilisateur.statutCompte}</li>
</ul>

</c:forEach>

</body>
</html>
