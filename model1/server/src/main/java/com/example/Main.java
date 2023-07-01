package com.example;

import com.example.repository.RepoClue;
import com.example.repository.RepoGame;
import com.example.repository.RepoGameForPlayer;
import com.example.repository.RepoPlayer;
import com.example.utils.AbstractServer;
import com.example.utils.ObjectConcurrentServer;
import com.example.utils.ServerException;

import java.io.IOException;
import java.util.Properties;

public class Main {
    private static final int defaultPort = 55555;

    public static void main(String[] args) {
        Properties serverProperties = new Properties();
        try {
            serverProperties.load(Main.class.getResourceAsStream("/server.properties"));
            System.out.println("server properties set");
            serverProperties.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find properties " + e);
            return;
        }
        Service service = new Service(new RepoPlayer(), new RepoClue(), new RepoGame(), new RepoGameForPlayer());
        int serverPort = defaultPort;
        try {
            serverPort = Integer.parseInt(serverProperties.getProperty("server.port"));
        } catch (NumberFormatException e) {
            System.err.println("Wrong port number" + e.getMessage());
        }
        System.out.println("Starting server on port: " + serverPort);
        AbstractServer server = new ObjectConcurrentServer(serverPort, service);
        try {
            server.start();
        } catch (ServerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}