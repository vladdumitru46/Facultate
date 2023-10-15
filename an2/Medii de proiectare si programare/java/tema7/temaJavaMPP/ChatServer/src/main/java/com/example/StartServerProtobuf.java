package com.example;

import com.example.repositoryes.ArtistRepo;
import com.example.repositoryes.BuyerRepo;
import com.example.repositoryes.EmployeeRepo;
import com.example.repositoryes.ShowRepository;
import com.example.utils.AbstractServer;
import com.example.utils.ProtobuffConcurrentServer;
import com.example.utils.ServerException;

import java.io.IOException;
import java.util.Properties;

public class StartServerProtobuf {
    private static int defaultPort = 55555;

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
        Service service = new Service(new ArtistRepo(serverProperties), new BuyerRepo(serverProperties), new EmployeeRepo(serverProperties), new ShowRepository(serverProperties));
        int serverPort = defaultPort;
        try {
            serverPort = Integer.parseInt(serverProperties.getProperty("server.port"));
        } catch (NumberFormatException e) {
            System.err.println("Wrong port number" + e.getMessage());
        }
        System.out.println("Starting server on port: " + serverPort);
        AbstractServer server = new ProtobuffConcurrentServer(serverPort, service);
        try {
            server.start();
        } catch (ServerException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
