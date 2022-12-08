package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.AvoirDroitsCrudPlanningAutreUtilisateur;

import java.util.List;
import java.util.Optional;

public class AvoirDroitsCrudPlanningAutreUtilisateurJPADAO {

    public List<AvoirDroitsCrudPlanningAutreUtilisateur> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<AvoirDroitsCrudPlanningAutreUtilisateur> query = em.createQuery("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad", AvoirDroitsCrudPlanningAutreUtilisateur.class);
        List<AvoirDroitsCrudPlanningAutreUtilisateur> ads = query.getResultList();
        em.close();
        return ads;
    }


    public Optional<AvoirDroitsCrudPlanningAutreUtilisateur> findByIds(Long idProprietaire, Long idAyantDroit) {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<AvoirDroitsCrudPlanningAutreUtilisateur> query = em.createQuery("select ad from AvoirDroitsCrudPlanningAutreUtilisateur ad where ad.proprietaire.idUtilisateur = " + idProprietaire + " and ad.ayantDroit.idUtilisateur = " + idAyantDroit, AvoirDroitsCrudPlanningAutreUtilisateur.class);
        List<AvoirDroitsCrudPlanningAutreUtilisateur> ads = query.getResultList();
        em.close();
        return ads.stream().findAny();
    }
    /*public void removeIfUseless(Long idProprietaire, Long idAyantDroit){
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> relation = findByIds(idProprietaire, idAyantDroit);
        if(relation.isPresent() && !relation.get().getPeutModifier() && !relation.get().getPeutCreer() && !relation.get().getPeutLire() && ! relation.get().getPeutSupprimer()){
            delete(relation.get());
        }
    }*/


    public boolean update(AvoirDroitsCrudPlanningAutreUtilisateur element) {
        if(!element.getPeutSupprimer() && ! element.getPeutSupprimer() && !element.getPeutLire() && !element.getPeutCreer()){
            return delete(element);
        }
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long idProprietaire = element.getProprietaire().getIdUtilisateur();
        Long idAyantDroit = element.getAyantDroit().getIdUtilisateur();
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> relation = findByIds(idProprietaire, idAyantDroit);
        if(relation.isPresent()){
            trans.begin();
            relation.get().setPeutCreer((element.getPeutCreer()));
            relation.get().setPeutModifier((element.getPeutModifier()));
            relation.get().setPeutSupprimer((element.getPeutSupprimer()));
            relation.get().setPeutLire((element.getPeutLire()));
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
    public boolean delete(AvoirDroitsCrudPlanningAutreUtilisateur element){
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long idProprietaire = element.getProprietaire().getIdUtilisateur();
        Long idAyantDroit = element.getAyantDroit().getIdUtilisateur();
        Optional<AvoirDroitsCrudPlanningAutreUtilisateur> relation = findByIds(idProprietaire, idAyantDroit);
        if(relation.isPresent()){
            trans.begin();
            em.remove(relation.get());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
}