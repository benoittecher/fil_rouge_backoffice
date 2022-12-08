package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Role;
import model.StatutCompte;

import java.util.List;
import java.util.Optional;

public class StatutCompteJPADAO implements CrudDAO<StatutCompte>{
    @Override
    public List<StatutCompte> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<StatutCompte> query = em.createQuery("select sc from StatutCompte sc", StatutCompte.class);
        List<StatutCompte> statutsComptes = query.getResultList();
        em.close();
        return statutsComptes;
    }

    @Override
    public Optional<StatutCompte> findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        return Optional.of(em.find(StatutCompte.class, id));
    }

    public StatutCompte findByIntitule(String intitule){
        StatutCompte result = null;
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<StatutCompte> query = em.createQuery("select sc from StatutCompte sc where sc.intitule = :intitule", StatutCompte.class);
        query.setParameter("intitule", intitule);
        List<StatutCompte> scs = query.getResultList();
        if(scs.size() > 0){
            result = scs.get(0);
        }
        em.close();
        return result;
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Optional<StatutCompte> sc = findById(id);
        if(sc.isPresent()){
            trans.begin();
            em.remove(sc.get());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    @Override
    public boolean update(StatutCompte element) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long id = element.getIdStatutCompte();
        Optional<StatutCompte> sc = findById(id);
        if(sc.isPresent()) {
            trans.begin();
            sc.get().setIntitule(element.getIntitule());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    public void initialize(){
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<StatutCompte> query = em.createQuery("select sc from StatutCompte sc", StatutCompte.class);
        List<StatutCompte> resultList = query.setMaxResults(1).getResultList();

        if(resultList.size() == 0){
            StatutCompte statutActif = new StatutCompte("actif");
            StatutCompte statutDesactive = new StatutCompte("désactivé");
            create(statutActif);
            create(statutDesactive);
        }
    }
    public boolean dbEmpty(){
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<StatutCompte> query = em.createQuery("select sc from StatutCompte sc", StatutCompte.class);
        List<StatutCompte> resultList = query.setMaxResults(1).getResultList();
        em.close();
        return resultList.size() == 0;
    }
}