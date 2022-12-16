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
        Utilisateur utilisateur = em.find(Utilisateur.class, id);
        return utilisateur != null ? Optional.of(utilisateur) : Optional.empty();
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
        boolean isAvailableEmail = isAvailableEmail(element.getMail());
        EntityManager em = ConnectionManager.getEntityManager();
        Long id = element.getIdUtilisateur();
        Optional<Utilisateur> utilisateur = findById(id);
        if(utilisateur.isPresent()){
            if(element.getMail().equals(utilisateur.get().getMail()) || isAvailableEmail){
                em = ConnectionManager.getEntityManager();
                EntityTransaction trans = em.getTransaction();
                trans.begin();
                utilisateur.get().setMail(element.getMail());
                utilisateur.get().setNom(element.getNom());
                utilisateur.get().setPrenom(element.getPrenom());
                utilisateur.get().setPays(element.getPays());
                utilisateur.get().setPhoto(element.getPhoto());
                utilisateur.get().setRole(element.getRole());
                utilisateur.get().setVille(element.getVille());
                utilisateur.get().setStatutCompte(element.getStatutCompte());
                utilisateur.get().setPlanning(element.getPlanning());
                trans.commit();
                em.close();
                return true;
            }
            return false;
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

    public Optional<Utilisateur> getByMailAndPwd(String mail, String pwd){
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where u.mail = :mail and u.motDePasse = :pwd ", Utilisateur.class);
        query.setParameter("mail", mail);
        query.setParameter("pwd", pwd);
        List<Utilisateur> utilisateur = query.setMaxResults(1).getResultList();
        em.close();
        return utilisateur != null && utilisateur.size() > 0 ? Optional.of(utilisateur.get(0)) : Optional.empty();
    }

    public boolean isEmailFormat(String email){
        if(email == null) return false;
        String[] split = email.split("@");
        if(split.length != 2 || split[0].length() == 0 || split[1].length() == 0) return false;
        String lastPart = split[1];
        String[] lastPartSplit = lastPart.split("\\.");
        return lastPartSplit.length == 2;
    }
    public boolean isAvailableEmail(String email){
        if(!isEmailFormat(email)) return false;
        EntityManager em = ConnectionManager.getEntityManager();
        TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where u.mail=:email", Utilisateur.class);
        query.setParameter("email", email);
        List<Utilisateur> utilisateur = query.setMaxResults(1).getResultList();
        em.close();
        return utilisateur == null || utilisateur.size() == 0;
    }
    @Override
    public Utilisateur create(Utilisateur utilisateur){
        if(isAvailableEmail(utilisateur.getMail())){
            return create(utilisateur);
        }
        return null;
    }
}