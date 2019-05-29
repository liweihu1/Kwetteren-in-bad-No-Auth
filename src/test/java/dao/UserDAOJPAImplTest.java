package dao;

import dao.JPA.UserDAOJPAImpl;
import dao.interfaces.IUserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDAOJPAImplTest extends UserDAOTest{

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeClass
    public static void setupOnce(){
        emf = Persistence.createEntityManagerFactory("kwetterTestPU");
//        emf = Persistence.createEntityManagerFactory("kwetterTestPU");
    }



    @Before
    public void setup(){
        em = emf.createEntityManager();
        em.getTransaction().begin();
        IUserDAO userDAO = new UserDAOJPAImpl();
        userDAO.setEm(em);
        setUserDAO(userDAO);
        super.setup();
    }

    @After
    public void teardown(){
        try {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        super.teardown();
    }

    public static EntityManager getEm(){
        return em;
    }
}
