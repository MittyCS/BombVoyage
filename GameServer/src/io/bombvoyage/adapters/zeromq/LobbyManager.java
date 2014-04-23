package io.bombvoyage.adapters.zeromq;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.bombvoyage.game.Lobby;
import org.zeromq.ZMQ;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author arshsab
 * @since 04 2014
 */

public class LobbyManager {
    private static final ObjectMapper mapper = new ObjectMapper();

    private final Thread t;
    private final Map<String, Lobby> lobbies = new ConcurrentHashMap<>();

    public LobbyManager(final int PORT) {
        ZMQ.Context ctx = ZMQ.context(1);
        ZMQ.Socket sock = ctx.socket(ZMQ.REP);

        sock.bind("tcp://*:" + PORT);

        this.t = new Thread(() -> {
            while (!Thread.interrupted()) {
                try {
                    String str = new String(sock.recv(0), ZMQ.CHARSET);
                    JsonNode node = mapper.readTree(str);

                    String id;

                    switch (node.path("action").textValue()) {
                        case "CREATE_GAME":
                            id = node.path("id").textValue();

                            lobbies.put(id, new Lobby());
                            sock.send("");
                            break;
                        case "DESTROY_GAME":
                            id = node.path("id").textValue();

                            lobbies.remove(id);
                            sock.send("");
                            break;
                        case "LIST_GAMES":
                            OutputStream out = new OutputStream() {
                                final StringBuilder sb = new StringBuilder();

                                @Override
                                public void write(int b) throws IOException {
                                    if (b != -1)
                                        sb.append((char) b);
                                }

                                @Override
                                public String toString() {
                                    return sb.toString();
                                }
                            };

                            JsonGenerator jGen = new JsonFactory().createGenerator(out);

                            jGen.writeStartArray();
                            for (Map.Entry<String, Lobby> entry : lobbies.entrySet()) {
                                jGen.writeStartObject();

                                jGen.writeStringField("id", entry.getKey());
                                jGen.writeNumberField("players", entry.getValue().getNumberOfPlayers());

                                jGen.writeEndObject();
                            }

                            jGen.flush();
                            jGen.close();

                            sock.send(out.toString());

                            break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            sock.close();
            ctx.term();
        });
    }

    public Lobby retrieve(String id) {
        return lobbies.get(id);
    }

    public void start() {
        t.start();
    }

    public void join() throws InterruptedException {
        t.join();
    }

    public void stop() {
        t.interrupt();
    }
}
