package io.bombvoyage;

import io.bombvoyage.adapters.jetty.JettyAdapter;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author arshsab
 * @since 04 2014
 */

public class Main {
    public static void main(String... args) throws IOException {
        System.getProperties().load(new FileInputStream(args[0]));

        JettyAdapter jetty = new JettyAdapter(Integer.parseInt(System.getProperty("jetty.port")));

        jetty.start();
        jetty.join();
    }
}
