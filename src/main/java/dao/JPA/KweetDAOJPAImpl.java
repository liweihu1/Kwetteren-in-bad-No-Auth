package dao.JPA;

import dao.interfaces.IKweetDAO;
import domain.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

@Stateless
@Default
@Named("KweetDAOJPAImpl")
public class KweetDAOJPAImpl implements IKweetDAO {
    @PersistenceContext(unitName = "kwetterPU")
    private EntityManager em;

    public KweetDAOJPAImpl() {

    }

    @Override
    public Kweet add(Kweet kweet) {
        em.persist(kweet);
        return kweet;
    }

    @Override
    public void delete(Kweet kweet) {
        em.remove(kweet);
    }

    @Override
    public Kweet findById(UUID id) {
        return em.find(Kweet.class, id);
    }

    @Override
    public List<Kweet> getAllKweets() {
        return em.createNamedQuery("kweet.getAllKweets", Kweet.class).getResultList();
    }

    @Override
    public List<Kweet> getPaginatedKweets(int page, int count) {
        return em.createNamedQuery("kweet.getAllKweets", Kweet.class).setMaxResults(count).setFirstResult(page * count).getResultList();
    }

    @Override
    public List<Kweet> getKweetLikesForUserId(UUID id) {
        return null;
    }

    @Override
    public List<Kweet> getAllKweetsByUserId(UUID id, int page, int count) {
        return em.createNamedQuery("kweet.getLatestForUser", Kweet.class).setParameter("userId", id).setMaxResults(count).setFirstResult(page * count).getResultList();
    }

    @Override
    public List<Kweet> getKweetForUserIdWithFollowing(UUID id, int page, int count) {
        List<Kweet> test = em.createNamedQuery("kweet.getKweetForUserAndFollowing", Kweet.class).setParameter("userId", id).getResultList();
        return em.createNamedQuery("kweet.getKweetForUserAndFollowing", Kweet.class).setParameter("userId", id).setMaxResults(count).setFirstResult(page * count).getResultList();
    }

    @Override
    public List<Kweet> getLatestKweetsForUserId(UUID id, int page, int count) {
        return em.createNamedQuery("kweet.getLatestForUser", Kweet.class).setMaxResults(10).setParameter("userId", id).setMaxResults(count).setFirstResult(page * count).getResultList();
    }

    @Override
    public List<Kweet> getKweetThatContainsSearch(String search, int page, int count) {
        return em.createNamedQuery("kweet.getKweetWithMessage", Kweet.class).setParameter("search", "%" + search + "%").getResultList();
    }

    @Override
    public Kweet update(Kweet Kweet) {
        return em.merge(Kweet);
    }

    @Override
    public void clearData() {
        if(em.isOpen()){
            em.clear();
        }
    }

    @Override
    public void setEm(EntityManager em) {
        this.em = em;
    }
}
