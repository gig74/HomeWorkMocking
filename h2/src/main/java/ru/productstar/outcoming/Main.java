package ru.productstar.outcoming;

import org.h2.tools.Server;

public class Main {
    public static void main(String[] args) {
        // run H2
        try {
            Server h2Server = Server.createTcpServer().start();
            if (h2Server.isRunning(true)) {
                System.out.println(h2Server.getStatus());
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }
}
