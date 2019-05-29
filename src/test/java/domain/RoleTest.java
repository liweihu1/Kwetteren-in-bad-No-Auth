package domain;

import domain.Role;
import domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class RoleTest {
    private User testUser1;
    private User testAdministrator1;
    private User testModerator1;

    @Before
    public void setup() {
        testUser1 = new User(UUID.randomUUID(), "Test","Test", "Test", "Test", "Test bio", "", "", null, null, new ArrayList<Role>() {{
            add(Role.Standard);
        }}, null, null, null);

        testAdministrator1 = new User(UUID.randomUUID(), "Test", "Test", "Test", "Test", "Test bio", "", "", null, null, new ArrayList<Role>() {{
            add(Role.Administrator);
        }}, null, null, null);

        testModerator1 = new User(UUID.randomUUID(), "Test", "Test", "Test", "Test", "Test bio", "", "", null, null, new ArrayList<Role>() {{
            add(Role.Moderator);
        }}, null, null, null);
    }

    @Test
    public void checkStandardRole() {
        assertEquals(Role.Standard, testUser1.getRoles().get(0));
    }

    @Test
    public void checkAdministratorRole() {
        assertEquals(Role.Administrator, testAdministrator1.getRoles().get(0));
    }

    @Test
    public void checkModeratorRole() {
        assertEquals(Role.Moderator, testModerator1.getRoles().get(0));
    }
}