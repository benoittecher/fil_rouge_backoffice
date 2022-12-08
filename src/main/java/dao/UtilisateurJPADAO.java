package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Role;
import model.StatutCompte;
import model.Utilisateur;

import java.util.List;
import java.util.Optional;

public class UtilisateurJPADAO implements CrudDAO<Utilisateur>{
    @Override
    public List<Utilisateur> findAll() {
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u", Utilisateur.class);
        List<Utilisateur> utilisateurs = query.getResultList();
        em.close();
        return utilisateurs;
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        return Optional.of(em.find(Utilisateur.class, id));
    }

    @Override
    public boolean delete(Long id) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Optional<Utilisateur> utilisateur = findById(id);
        if(utilisateur.isPresent()){
            trans.begin();
            em.remove(utilisateur.get());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }

    @Override
    public boolean update(Utilisateur element) {
        EntityManager em = ConnectionManager.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        Long id = element.getIdUtilisateur();
        Optional<Utilisateur> utilisateur = findById(id);
        if(utilisateur.isPresent()){
            trans.begin();
            utilisateur.get().setMail(element.getMail());
            utilisateur.get().setNom(element.getNom());
            utilisateur.get().setPrenom(element.getPrenom());
            utilisateur.get().setPays(element.getPays());
            utilisateur.get().setPhoto(element.getPhoto());
            utilisateur.get().setRole(element.getRole());
            utilisateur.get().setVille(element.getVille());
            utilisateur.get().setMotDePasse(element.getMotDePasse());
            utilisateur.get().setStatutCompte(element.getStatutCompte());
            utilisateur.get().setPlanning(element.getPlanning());
            trans.commit();
            em.close();
            return true;
        }
        em.close();
        return false;
    }
    public void initialize(){
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Utilisateur> query = em.createQuery("select sc from Utilisateur sc", Utilisateur.class);
        List<Utilisateur> resultList = query.setMaxResults(1).getResultList();
        Role superAdminRole = new RoleJPADAO().findByIntitule("superAdmin");
        StatutCompte statutActif = new StatutCompteJPADAO().findByIntitule("actif");
        if(resultList.size() == 0){
            Utilisateur superAdmin = new Utilisateur("superAdmin", "superAdmin", "azerty", statutActif, superAdminRole);
            create(superAdmin);
        }
    }
}