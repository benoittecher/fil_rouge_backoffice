package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Role;
import model.Role;

import java.util.List;
import java.util.Optional;

public class RoleJPADAO implements CrudDAO<Role>{
    @Override
    public List<Role> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Role> query = em.createQuery("select r from Role r", Role.class);
        List<Role> roles = query.getResultList();
        em.close();
        return roles;
    }

    @Override
    public Optional<Role> findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        return Optional.of(em.find(Role.class, id));
    }
    public Role findByIntitule(String intitule){
        Role result = null;
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.intitule = :intitule", Role.class);
        query.setParameter("intitule", intitule);
        List<Role> roles = query.getResultList();
        if(roles.size() > 0){
            result = roles.get(0);
        }
        em.close();
        return result;
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Optional<Role> role = findById(id);
        if(role.isPresent()){
            trans.begin();
            em.remove(role.get());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    @Override
    public boolean update(Role element) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long id = element.getIdRole();
        Optional<Role> role = findById(id);
        if(role.isPresent()) {
            trans.begin();
            role.get().setIntitule(element.getIntitule());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
    public void initialize(){
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Role> query = em.createQuery("select sc from Role sc", Role.class);
        List<Role> resultList = query.setMaxResults(1).getResultList();

        if(resultList.size() == 0){
            Role superAdmin = new Role("superAdmin");
            Role admin = new Role("admin");
            Role utilisateur = new Role("utilisateur");
            create(superAdmin);
            create(admin);
            create(utilisateur);
        }
    }
}
