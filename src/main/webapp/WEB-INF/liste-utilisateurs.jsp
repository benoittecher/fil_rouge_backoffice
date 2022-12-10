<%@ page import="model.Utilisateur" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des utilisateurs</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<jsp:include page="header.jsp"><jsp:param name="errorMsg" value="${error}"/></jsp:include>

<h1>Liste des utilisateurs</h1>

<a class="btn btn-primary" href="${pageContext.request.contextPath}/utilisateurs/ajouter">Ajouter un utilisateur</a>

<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Adresse email</th>
        <th scope="col">Prénom Nom</th>
        <th scope="col">Rôle</th>
        <th scope="col">Statut du compte</th>
        <th scope="col" colspan="2">Actions Administrateur</th>
    </tr>
    </thead>
    <tbody>
<c:forEach items="${utilisateurs}" var="utilisateur">
    <tr>
        <td>${utilisateur.mail}</td>
        <td>${utilisateur.prenom} ${utilisateur.nom}</td>
        <td>${utilisateur.role.intitule}</td>
        <td>${utilisateur.statutCompte.intitule}</td>
        <td>
            <form method="get" action="${pageContext.request.contextPath}/utilisateurs/modifier">
                <input type="hidden" value="${utilisateur.idUtilisateur}" name="id">
                <button class="btn btn-primary">Modifier</button>
            </form>
        </td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/utilisateurs/supprimer">
                <input type="hidden" value="${utilisateur.idUtilisateur}" name="idUtilisateur">

                <c:choose>
                    <c:when test="${utilisateur.role.intitule == 'superAdmin'}"></c:when>
                    <c:otherwise>
                        <button class="btn btn-danger">Supprimer</button>
                    </c:otherwise>
                </c:choose>

            </form>
        </td>

    </tr>
</c:forEach>
    </tbody>
</table>
</body>
</html>
