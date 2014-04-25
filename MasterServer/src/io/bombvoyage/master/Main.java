package io.bombvoyage.master;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author arshsab
 * @since 04 2014
 */

public class Main {
    public static void main(String... args) throws Exception {
        System.getProperties().load(new FileInputStream(args[0]));

        Server server = new Server(Integer.parseInt(System.getProperty("server.port")));

        ResourceHandler resources = new ResourceHandler();

        resources.setResourceBase("web");
        resources.setDirectoriesListed(false);

        ServletContextHandler servlets = new ServletContextHandler();

        servlets.addServlet(CreateGameServlet.class, "/create");
        servlets.addServlet(ListGamesServlet.class, "/list");

        List<GameServer> gameServers = Collections.synchronizedList(initGameServers());

        servlets.setAttribute("gameServers", gameServers);

        HandlerList handlers = new HandlerList();

        handlers.addHandler(resources);
        handlers.addHandler(servlets);

        server.setHandler(handlers);

        server.start();
        server.join();
    }

    private static List<GameServer> initGameServers() {
        List<GameServer> gameServers = new ArrayList<>();

        for (int i = 0; System.getProperty("gameserver." + i + ".zmq") != null; i++) {
            String zmqLoc = System.getProperty("gameserver." + i + ".zmq");
            String wsLoc = System.getProperty("gameserver." + i + ".ws");

            GameServer gameServer = new GameServer(zmqLoc, wsLoc);

            gameServers.add(gameServer);
        }

        return gameServers;
    }
}
