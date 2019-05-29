package dao;

import dao.interfaces.IKweetDAO;
import dao.interfaces.IUserDAO;
import dao.memory.KweetDAOMEMImpl;
import dao.memory.UserDAOMEMImpl;
import org.junit.After;
import org.junit.Before;

public class KweetDAOMEMImplTest extends KweetDAOTest {
    @Before
    public void setup(){
        IKweetDAO kweetDAO = new KweetDAOMEMImpl();
        IUserDAO userDAO = new UserDAOMEMImpl();
        setKweetDAO(kweetDAO);
        setUserDAO(userDAO);
        super.setup();
    }

    @After
    public void teardown(){
        super.teardown();
    }
}
