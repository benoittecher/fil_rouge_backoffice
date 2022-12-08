package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<E> {
    List<E> findAll();

    Optional<E> findById(Long id);

    boolean delete(Long id);

    boolean update(E element);

    default E create(E element) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            em.persist(element);
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if(et.isActive()) {
                et.rollback();
            }
        } finally {
            // em.close();
        }
        return element;
    }
}
