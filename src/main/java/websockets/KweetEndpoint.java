package websockets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.Kweet;
import dto.KweetDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ServerEndpoint(value = "/socket", configurator = KweetEndpointConfigurator.class)
public class KweetEndpoint {
    @Inject
    private KweetSessionHandler sessionHandler;
    @OnOpen
    public void open(Session session, EndpointConfig config) {
        sessionHandler.addSession(UUID.fromString(config.getUserProperties().get("id").toString()), session);
    }

    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }

    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(KweetEndpoint.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        try {
            Gson gson = new Gson();
            JsonObject jsonMessage = gson.fromJson(message, JsonObject.class);
            Kweet result = sessionHandler.createKweet(jsonMessage.get("message").getAsString(), jsonMessage.get("authorId").getAsString());
            session.getBasicRemote().sendText(gson.toJson(new KweetDTO(result)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
