package io.bombvoyage.master;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author arshsab
 * @since 04 2014
 */

public class GameServer {
    private static final ZMQ.Context ctx = ZMQ.context(1);
    private static final ObjectMapper mapper = new ObjectMapper();

    private final ZMQ.Socket sock;
    private final String url;

    public GameServer(String zmqLoc, String wsLoc) {
        this.sock = ctx.socket(ZMQ.REQ);
        this.url = wsLoc;

        sock.bind("tcp://" + zmqLoc);
    }

    public String getUrl() {
        return url;
    }

    public synchronized Game createGame(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>() {{
                put("action", "CREATE_GAME");
                put("id", id);
            }};

            sock.send(mapper.writeValueAsString(map));
            sock.recv();

            return new Game(id, 0, false, url);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void destroyGame(String id) {
        try {
            Map<String, String> map = new HashMap<String, String>() {{
                put("action", "DESTROY_GAME");
                put("id", id);
            }};

            sock.send(mapper.writeValueAsString(map));
            sock.recv();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized List<Game> listGames() {
        try {
            List<Game> games = new ArrayList<>();

            Map<String, String> map = new HashMap<String, String>() {{
                put("action", "LIST_GAMES");
            }};

            sock.send(mapper.writeValueAsBytes(map));
            String str = sock.recvStr();

            JsonNode arr = mapper.readTree(str);

            for (int i = 0; arr.has(i); i++) {
                JsonNode jsonGame = arr.get(i);

                String id = jsonGame.path("id").textValue();
                int players = jsonGame.path("players").intValue();
                boolean inProg = jsonGame.path("inProg").booleanValue();

                Game game = new Game(id, players, inProg, this.url);
                games.add(game);
            }

            return games;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
