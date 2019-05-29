package dao;

import dao.JPA.KweetDAOJPAImpl;
import dao.JPA.UserDAOJPAImpl;
import dao.interfaces.IKweetDAO;
import dao.interfaces.IUserDAO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class KweetDAOJPAImplTest extends KweetDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setupOnce(){
        emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    }

    @Before
    public void setup(){
        em = emf.createEntityManager();
        em.getTransaction().begin();

        IKweetDAO kweetDAO = new KweetDAOJPAImpl();
        IUserDAO userDAO = new UserDAOJPAImpl();

        kweetDAO.setEm(em);
        userDAO.setEm(em);

        setUserDAO(userDAO);
        setKweetDAO(kweetDAO);

        super.setup();
    }

    @After
    public void teardown(){
        try {
            em.getTransaction().rollback();
        } finally {
//            em.close();
        }
        super.teardown();
    }

    @AfterClass
    public static void teardownOnce() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public static EntityManager getEm(){
        return em;
    }
}
