package io.bombvoyage.adapters.jetty;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bombvoyage.adapters.Connection;
import io.bombvoyage.adapters.zeromq.LobbyManager;
import io.bombvoyage.game.Lobby;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

/**
 * @author arshsab
 * @since 04 2014
 */

@WebSocket
public class GameSocket {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final LobbyManager manager;
    private Connection connection;
    private Lobby lobby;

    GameSocket(LobbyManager manager) {
        this.manager = manager;
    }

    @OnWebSocketConnect
    public void onOpen(Session session) {
        connection = session.getRemote()::sendStringByFuture;
    }

    @OnWebSocketMessage
    public void onTextMessage(Session session, String text) {
        if (inLobby()) {
            try {
                lobby.onMessage(text, connection);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JsonNode node = mapper.readTree(text);

                if (node.path("lobby") != null) {
                    String lobbyId = node.path("lobby").textValue();

                    Lobby lobby = manager.retrieve(lobbyId);
                    if (lobby.onConnect(connection)) {
                        this.lobby = lobby;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @OnWebSocketClose
    public void onClose(Session session, int closeCode, String closeReason) {
        if (inLobby()) {
            lobby.onDisconnect(connection);
        }
    }

    private boolean inLobby() {
        return lobby == null;
    }
}
