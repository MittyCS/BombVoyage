package io.bombvoyage.master;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author arshsab
 * @since 04 2014
 */

public class ListGamesServlet extends HttpServlet {
    private List<GameServer> gameServers;

    @Override
    @SuppressWarnings("unchecked")
    public void init() {
        gameServers = (List<GameServer>) getServletContext().getAttribute("gameServers");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Game> games = gameServers.stream()
                .map(GameServer::listGames)
                .collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);

        JsonGenerator jGen = new JsonFactory().createGenerator(resp.getWriter());

        jGen.writeStartArray();

        for (Game game : games) {
            jGen.writeStartObject();

            jGen.writeStringField("id", game.id);
            jGen.writeBooleanField("status", game.inProg);
            jGen.writeNumberField("players", game.players);
            jGen.writeStringField("url", game.wsUrl);

            jGen.writeEndObject();
        }

        jGen.writeEndArray();

        jGen.flush();
        jGen.close();
    }
}
