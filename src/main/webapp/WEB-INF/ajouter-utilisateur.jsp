<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajouter un utilisateur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<h1>Ajout d'un nouvel utilisateur :</h1>

<form method="post" action="/utilisateurs/ajouter">
    <label for="nomUtilisateur">Nom :</label>
    <input id="nomUtilisateur" type="text" name="nomUtilisateur">

    <label for="prenomUtilisateur">Prénom :</label>
    <input id="prenomUtilisateur" type="text" name="prenomUtilisateur">

    <label for="mailUtilisateur">Adresse email :</label>
    <input id="mailUtilisateur" type="text" name="mailUtilisateur">

    <label for="mdpUtilisateur">Mot de passe :</label>
    <input id="mdpUtilisateur" type="text" name="mdpUtilisateur">

    <label for="villeUtilisateur">Ville :</label>
    <input id="villeUtilisateur" type="text" name="villeUtilisateur">

    <label for="paysUtilisateur">Pays :</label>
    <input id="paysUtilisateur" type="text" name="paysUtilisateur">

    <label for="roleUtilisateur">Rôle :</label>
    <select id="roleUtilisateur" name="roleUtilisateur">
        <option value="" selected disabled hidden>--choisir le rôle--</option>
        <c:forEach items="${roles}" var="role">
            <option value="${role.idRole}">${role.intitule}</option>
        </c:forEach>
    </select>

    <label for="statutCompteUtilisateur">Statut du compte :</label>
    <select id="statutCompteUtilisateur" name="statutCompteUtilisateur">
        <option value="" selected disabled hidden>--choisir le statut--</option>
        <c:forEach items="${statuts}" var="statut">
            <option value="${statut.idStatutCompte}">${statut.intitule}</option>
        </c:forEach>
    </select>

    <button>Créer l'utilisateur</button>
</form>

</body>
</html>
