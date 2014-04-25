package io.bombvoyage.master;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author arshsab
 * @since 04 2014
 */

public class CreateGameServlet extends HttpServlet {
    private final Random random = new Random();
    private List<GameServer> gameServers;
    private final AtomicInteger atomic = new AtomicInteger();

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        gameServers = (List<GameServer>) getServletContext().getAttribute("gameServers");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int pos = random.nextInt(gameServers.size());
        GameServer server = gameServers.get(pos);

        Game game = server.createGame(atomic.getAndIncrement() + "");

        JsonGenerator jGen = new JsonFactory().createGenerator(resp.getWriter());

        jGen.writeStartObject();

        jGen.writeStringField("id", game.id);
        jGen.writeBooleanField("status", game.inProg);
        jGen.writeNumberField("players", game.players);
        jGen.writeStringField("url", game.wsUrl);

        jGen.writeEndObject();

        jGen.flush();
        jGen.close();
    }
}
