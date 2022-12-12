<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modifier un utilisateur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>

<h1>Modifier les informations de l'utilisateur :</h1>

<form method="post" action="/utilisateurs/modifier">
    <input type="hidden" value="${utilisateur.idUtilisateur}" name="id">
    <div>
        <label for="nomUtilisateur">Nom :</label>
        <input id="nomUtilisateur" type="text" name="nomUtilisateur" value="${utilisateur.nom}">
    </div>
    <div>
        <label for="prenomUtilisateur">Prénom :</label>
        <input id="prenomUtilisateur" type="text" name="prenomUtilisateur" value="${utilisateur.prenom}">
    </div>
    <div>
        <label for="mailUtilisateur">Adresse email :</label>
        <input id="mailUtilisateur" type="text" name="mailUtilisateur" value="${utilisateur.mail}">
    </div>
    <div>
        <label for="mdpUtilisateur">Mot de passe :</label>
        <input id="mdpUtilisateur" type="text" name="mdpUtilisateur" value="${utilisateur.motDePasse}">
    </div>
    <div>
        <label for="villeUtilisateur">Ville :</label>
        <input id="villeUtilisateur" type="text" name="villeUtilisateur" value="${utilisateur.ville}">
    </div>
    <div>
        <label for="paysUtilisateur">Pays :</label>
        <input id="paysUtilisateur" type="text" name="paysUtilisateur" value="${utilisateur.pays}">
    </div>
    <div>
        <label for="roleUtilisateur">Rôle :</label>
    <c:choose>
        <c:when test="${isSuperAdmin}">
        <select id="roleUtilisateur" name="roleUtilisateur">
            <c:forEach items="${roles}" var="role">
                <c:choose>
                <c:when test="${utilisateur.role.idRole == role.idRole}">
                    <option selected value="${role.idRole}">${role.intitule}</option>
                </c:when>
                <c:otherwise>
                    <option value="${role.idRole}">${role.intitule}</option>
                </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
        </c:when>
        <c:otherwise>
            <input type="text" value="${utilisateur.role.intitule}" id="roleUtilisateur" readonly>
            <input type="hidden" name="roleUtilisateur" value="${utilisateur.role.id}">
        </c:otherwise>
    </c:choose>
    </div>
    <div>
        <label for="statutCompteUtilisateur">Statut du compte :</label>
        <select id="statutCompteUtilisateur" name="statutCompteUtilisateur">
            <c:forEach items="${statuts}" var="statut">
                <c:choose>
                    <c:when test="${utilisateur.statutCompte.idStatutCompte == statut.idStatutCompte}">
                        <option selected value="${statut.idStatutCompte}">${statut.intitule}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${statut.idStatutCompte}">${statut.intitule}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>

    <button>Mettre à jour les informations de l'utilisateur</button>
</form>

</body>
</html>
