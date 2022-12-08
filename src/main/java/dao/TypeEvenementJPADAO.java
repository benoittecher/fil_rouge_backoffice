package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.TypeEvenement;

import java.util.List;
import java.util.Optional;

public class TypeEvenementJPADAO implements CrudDAO<TypeEvenement> {
    @Override
    public List<TypeEvenement> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<TypeEvenement> query = em.createQuery("select te from TypeEvenement te", TypeEvenement.class);
        List<TypeEvenement> tes = query.getResultList();
        em.close();
        return tes;
    }

    @Override
    public Optional<TypeEvenement> findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        return Optional.of(em.find(TypeEvenement.class, id));
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Optional<TypeEvenement> te = findById(id);
        if(te.isPresent()){
            trans.begin();
            em.remove(te.get());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    @Override
    public boolean update(TypeEvenement element) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long id = element.getIdTypeEvenement();
        Optional<TypeEvenement> te = findById(id);
        if(te.isPresent()) {
            trans.begin();
            te.get().setIntitule(element.getIntitule());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
}
