package websockets;

import com.google.gson.Gson;
import domain.Kweet;
import domain.User;
import dto.KweetDTO;
import service.KweetService;
import service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class KweetSessionHandler {
    private final Map<UUID, Session> sessions;

    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    public KweetSessionHandler() {
        sessions = new HashMap<>();
    }

    public void addSession(UUID id, Session session) {
        sessions.put(id, session);
    }

    public void removeSession(Session session) {
        Session mapSession = sessions.values().stream().filter(f -> f == session).findAny().orElse(null);
        if (mapSession != null) sessions.remove(mapSession);
    }

    public Kweet createKweet(String message, String authorId) {
        Kweet result = kweetService.createKweet(message, authorId);
        if (result != null) {
            sendToAllConnectedSessions(result, UUID.fromString(authorId));
        }
        return result;
    }

    private void sendToAllConnectedSessions(Kweet kweet, UUID id) {
        User u = userService.getUserById(id);
        if (u != null) {
            u.getFollowers().forEach(follower -> {
                Session s;
                if ((s = sessions.get(follower.getId()))!= null) {
                    try {
                        s.getBasicRemote().sendText(new Gson().toJson(new KweetDTO(kweet)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void sendToSession(Session session, JsonObject message) {
    }
}
