package io.bombvoyage.master;

/**
 * @author arshsab
 * @since 04 2014
 */

public class Game {
    public final String id;
    public final int players;
    public final boolean inProg;
    public final String wsUrl;

    public Game(String id, int players, boolean inProg, String wsUrl) {
        this.id = id;
        this.players = players;
        this.inProg = inProg;
        this.wsUrl = wsUrl;
    }
}
