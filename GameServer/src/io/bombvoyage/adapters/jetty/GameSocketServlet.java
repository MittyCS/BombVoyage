package io.bombvoyage.adapters.jetty;

import io.bombvoyage.adapters.zeromq.LobbyManager;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * @author arshsab
 * @since 04 2014
 */

public class GameSocketServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {
        final int PORT = Integer.parseInt(System.getProperty("zmq.rep.port"));

        factory.getPolicy().setIdleTimeout(10000);
        factory.setCreator(new GameSocketCreator(new LobbyManager(PORT)));
    }
}
