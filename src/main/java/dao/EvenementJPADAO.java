package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Evenement;

import java.util.List;
import java.util.Optional;

public class EvenementJPADAO implements CrudDAO<Evenement>{
    @Override
    public List<Evenement> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Evenement> query = em.createQuery("select e from Evenement e", Evenement.class);
        List<Evenement> evenements = query.getResultList();
        em.close();
        return evenements;
    }

    @Override
    public Optional<Evenement> findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        return Optional.of(em.find(Evenement.class, id));
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Optional<Evenement> evenement = findById(id);
        if(evenement.isPresent()){
            trans.begin();
            em.remove(evenement.get());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    @Override
    public boolean update(Evenement element) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long id = element.getIdEvenement();
        Optional<Evenement> evenement = findById(id);
        if(evenement.isPresent()){
            trans.begin();
            evenement.get().setDebut(element.getDebut());
            evenement.get().setTypeEvenement(element.getTypeEvenement());
            evenement.get().setDescription(element.getDescription());
            evenement.get().setFin(element.getFin());
            evenement.get().setIntitule(element.getIntitule());
            evenement.get().setUtilisateur(element.getUtilisateur());
            evenement.get().setLieu(element.getLieu());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
}

