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
    private final Set<Connection> connections = new HashSet<>(NUMBER_OF_PLAYERS);
    private final Set<Connection> votes = new HashSet<>(NUMBER_OF_PLAYERS);
    private final ObjectMapper mapper = new ObjectMapper();
    private boolean started = false;
    private Game game;

    public boolean onConnect(Connection conn) {
        checkForGameOver();

        if (!started && connections.size() >= NUMBER_OF_PLAYERS) {
            connections.add(conn);
            return true;
        }

        return false;
    }

    // see game spec
    public void onMessage(String text, Connection conn) throws IOException {
        checkForGameOver();

        JsonNode node = mapper.readTree(text);

        String type = node.path("type").textValue();

        if ("START".equals(type)) {
            if (!started) {
                votes.add(conn);

                if (votes.size() > NUMBER_OF_PLAYERS / 2) {
                    this.game = new Game();
                    started = true;
                    game.onStart(connections.toArray(new Connection[connections.size()]));
                    votes.clear();
                }
            }
        } else if ("MESSAGE".equals(type)) {
            if (started)
                game.onMessage(conn, node.path("payload").textValue());

            for (Connection c : connections) {
                if (c != conn) {
                    c.sendText(text);
                }
            }
        }
    } 

    public void onDisconnect(Connection conn) {
        checkForGameOver();

        if (started) {
            game.onDisconnect(conn);
        }

        connections.remove(conn);
        votes.remove(conn);
    }

    public int getNumberOfPlayers() {
        return connections.size();
    }

    private void checkForGameOver() {
        if (started && game.isOver()) {
            started = false;
        }
    }
}
