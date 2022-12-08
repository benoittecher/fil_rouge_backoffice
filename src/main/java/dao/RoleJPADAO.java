package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
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
}
