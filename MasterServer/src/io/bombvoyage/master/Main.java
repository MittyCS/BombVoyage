package io.bombvoyage.master;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @author arshsab
 * @since 04 2014
 */

public class Main {
    public static void main(String... args) throws Exception {
        Server server = new Server(Integer.parseInt(args[0]));

        ResourceHandler resources = new ResourceHandler();

        resources.setResourceBase("web");
        resources.setDirectoriesListed(false);

        ServletContextHandler servlets = new ServletContextHandler();

        servlets.addServlet(CreateGameServlet.class, "/create");
        servlets.addServlet(ListGamesServlet.class, "/list");

        HandlerList handlers = new HandlerList();

        handlers.addHandler(resources);
        handlers.addHandler(servlets);

        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
