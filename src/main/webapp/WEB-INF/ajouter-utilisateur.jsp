<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Ajouter un utilisateur"/>
</jsp:include>

<a href="${pageContext.request.contextPath}/utilisateurs" class="btn btn-backTo">Retour à la liste des utilisateurs</a>

<section class="h-custom">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-8 col-xl-6">
                <div class="card rounded-3">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">Ajout d'un nouvel utilisateur</h3>
                        <c:if test="${badMailFormat}">
                            <div class="alert alert-danger" role="alert">L'email saisie est incorrecte</div>
                        </c:if>
                        <c:if test="${alreadyUsedMail}">
                            <div class="alert alert-danger" role="alert">L'email saisie est déjà utilisée</div>
                        </c:if>


                        <form method="post" action="/utilisateurs/ajouter" class="px-md-2">

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="nomUtilisateur">Nom :</label>
                                    <input type="text" id="nomUtilisateur" name="nomUtilisateur" class="form-control" />

                                </div>
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="prenomUtilisateur">Prénom :</label>
                                    <input class="form-control" id="prenomUtilisateur" type="text" name="prenomUtilisateur">

                                </div>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="mailUtilisateur">Adresse email :</label>
                                <input class="form-control" id="mailUtilisateur" type="text" name="mailUtilisateur">
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label" for="mdpUtilisateur">Mot de passe :</label>
                                <input class="form-control" id="mdpUtilisateur" type="password" name="mdpUtilisateur">
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="villeUtilisateur">Ville :</label>
                                    <input class="form-control" id="villeUtilisateur" type="text" name="villeUtilisateur">

                                </div>
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="paysUtilisateur">Pays :</label>
                                    <input class="form-control" id="paysUtilisateur" type="text" name="paysUtilisateur">

                                </div>
                            </div>

                            <div class="mb-4">

                                <label class="form-label select" for="roleUtilisateur">Rôle :</label>
                                <select id="roleUtilisateur" name="roleUtilisateur">
                                    <option value="" selected disabled hidden>--choisir le rôle--</option>
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.idRole}">${role.intitule}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <div class="mb-4">

                                <label class="form-label select" for="statutCompteUtilisateur">Statut du compte :</label>
                                <select id="statutCompteUtilisateur" name="statutCompteUtilisateur">
                                    <option value="" selected disabled hidden>--choisir le statut--</option>
                                    <c:forEach items="${statuts}" var="statut">
                                        <option value="${statut.idStatutCompte}">${statut.intitule}</option>
                                    </c:forEach>
                                </select>

                            </div>

                            <button type="submit" class="btn btn-primary btn-lg mb-1">Créer l'utilisateur</button>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp"/>
