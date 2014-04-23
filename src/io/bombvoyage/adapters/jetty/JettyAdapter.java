package io.bombvoyage.adapters.jetty;

import io.bombvoyage.adapters.Adapter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

/**
 * @author arshsab
 * @since 04 2014
 */

public class JettyAdapter implements Adapter {
    private final Server server;

    public JettyAdapter(final int PORT) {
        server = new Server(PORT);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(GameSocketServlet.class, "/");

        server.setHandler(handler);
    }

    @Override
    public void start() {
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void join() {
        try {
            server.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
