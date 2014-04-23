package io.bombvoyage.game;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bombvoyage.adapters.Connection;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author arshsab
 * @author Adrian Chmielewski-Anders
 * @since 04 2014
 */

public class Lobby {
    private final static int NUMBER_OF_PLAYERS = 4;
    private Set<Connection> connections = new HashSet<>(NUMBER_OF_PLAYERS);
    private Set<Connection> votes = new HashSet<>(NUMBER_OF_PLAYERS);
    private ObjectMapper mapper = new ObjectMapper();

    public boolean onConnect(Connection conn) {
        return connections.size() >= NUMBER_OF_PLAYERS;
    }

    // see game spec
    public void onMessage(String text, Connection conn) throws IOException {
        JsonNode node = mapper.readTree(text);
        String type = node.path("type").textValue();
        if ("JOIN".equals(type) && connections.size() < NUMBER_OF_PLAYERS) {
            connections.add(conn);
        } else if ("START".equals(type)) {
            votes.add(conn);
            if (votes.size() == NUMBER_OF_PLAYERS / 2 + 1) {
                Game g = new Game();
                g.onStart((Connection[]) connections.toArray());
            }
        }
    } 

    public void onDisconnect(Connection conn) {
        connections.remove(conn);
    }
}
