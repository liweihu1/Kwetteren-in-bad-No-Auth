package dao;

import dao.interfaces.IUserDAO;
import dao.memory.UserDAOMEMImpl;
import org.junit.After;
import org.junit.Before;

public class UserDAOMEMImplTest extends UserDAOTest{
    @Before
    public void setup(){
        IUserDAO userDAO = new UserDAOMEMImpl();
        setUserDAO(userDAO);
        super.setup();
    }

    @After
    public void teardown(){
        super.teardown();
    }
}
