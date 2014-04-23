package io.bombvoyage.adapters.jetty;

import io.bombvoyage.adapters.zeromq.LobbyManager;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

/**
 * @author arshsab
 * @since 04 2014
 */

public class GameSocketCreator implements WebSocketCreator {
    private final LobbyManager manager;

    GameSocketCreator(LobbyManager manager) {
        this.manager = manager;
    }

    @Override
    public Object createWebSocket(ServletUpgradeRequest servletUpgradeRequest, ServletUpgradeResponse servletUpgradeResponse) {
        return new GameSocket(manager);
    }
}
