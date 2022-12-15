<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp">
    <jsp:param name="title" value="Modifier un utilisateur"/>
</jsp:include>
<a href="${pageContext.request.contextPath}/utilisateurs" class="btn btn-backTo" >Retour à la liste des utilisateurs</a>

<section class="h-custom">
    <div class="container h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-lg-8 col-xl-6">
                <div class="card rounded-3">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 px-md-2">Modifier les informations de l'utilisateur</h3>

                        <form method="post" action="/utilisateurs/modifier" class="px-md-2">
                            <input type="hidden" value="${utilisateur.idUtilisateur}" name="id">
                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="nomUtilisateur">Nom :</label>
                                    <input type="text" id="nomUtilisateur" name="nomUtilisateur" class="form-control" value="${utilisateur.nom}"/>

                                </div>
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="prenomUtilisateur">Prénom :</label>
                                    <input class="form-control" id="prenomUtilisateur" type="text" name="prenomUtilisateur" value="${utilisateur.prenom}">

                                </div>
                            </div>

                            <div class="form-outline mb-4">
                                <label class="form-label" for="mailUtilisateur">Adresse email :</label>
                                <input class="form-control" id="mailUtilisateur" type="text" name="mailUtilisateur" value="${utilisateur.mail}">
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="villeUtilisateur">Ville :</label>
                                    <input class="form-control" id="villeUtilisateur" type="text" name="villeUtilisateur" value="${utilisateur.ville}">

                                </div>
                                <div class="col-md-6 mb-4">

                                    <label class="form-label" for="paysUtilisateur">Pays :</label>
                                    <input class="form-control" id="paysUtilisateur" type="text" name="paysUtilisateur" value="${utilisateur.pays}">

                                </div>
                            </div>

                            <div class="mb-4">
                                <c:choose>
                                    <c:when test="${isSuperAdmin}">
                                        <label class="form-label select" for="roleUtilisateur">Rôle :</label>
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
                                        <label class="form-label" for="roleUtilisateurAffiche">Rôle :</label>
                                        <input type="text" value="${utilisateur.role.intitule}" id="roleUtilisateurAffiche" readonly>
                                        <!-- test -->
                                        <input type="hidden" name="roleUtilisateur" value="${utilisateur.role.idRole}">
                                    </c:otherwise>
                                </c:choose>
                                </select>

                            </div>

                            <div class="mb-4">

                                <label class="form-label select" for="statutCompteUtilisateur">Statut du compte :</label>
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

                            <button type="submit" class="btn btn-primary btn-lg mb-1">Mettre à jour les informations de l'utilisateur</button>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<jsp:include page="footer.jsp"/>
