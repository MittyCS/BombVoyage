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

    private Set<Connection> connections = new HashSet<>();
    ObjectMapper mapper = new ObjectMapper();

    public boolean onConnect(Connection conn) {
        if (connections.size() < 4) {
            return false;
        }
        return true;
    }

    // see game spec
    public void onMessage(String text, Connection conn) throws IOException {
        JsonNode node = mapper.readTree(text);
        String type = node.path("type").textValue();
        if ("JOIN".equals(type) && connections.size() < 4) {
            connections.add(conn);
        }
    }

    public void onDisconnect(Connection conn) {
        connections.remove(conn);
    }
}
