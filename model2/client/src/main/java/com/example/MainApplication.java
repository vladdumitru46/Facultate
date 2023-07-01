package com.example;

import com.example.controller.LogIn;
import com.example.controller.MainPage;
import com.example.objectProtocol.protocols.ObjectProxy;
import com.example.repository.RepoUser;
import com.example.repository.RepoWords;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class MainApplication extends Application {
    private static final int defaultChatPort = 55555;
    private static final String defaultServer = "localhost";

    @Override
    public void start(Stage stage) throws IOException {
        Properties clientProps = new Properties();
        try {
            clientProps.load(MainApplication.class.getResourceAsStream("/client.properties"));
            clientProps.list(System.out);
        } catch (IOException e) {
            System.err.println("Cannot find client.properties " + e);
            return;
        }

        String serverIP = clientProps.getProperty("server.host", defaultServer);
        int serverPort = defaultChatPort;

        try {
            serverPort = Integer.parseInt(clientProps.getProperty("server.port"));
        } catch (NumberFormatException ex) {
            System.err.println("Wrong port number " + ex.getMessage());
            System.out.println("Using default port: " + defaultChatPort);
        }
        System.out.println("Using server IP " + serverIP);
        System.out.println("Using server port " + serverPort);

        IService server = new ObjectProxy(serverIP, serverPort);
        Service service = new Service(new RepoUser(), new RepoWords());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/logIn.fxml"));
        Parent loader = fxmlLoader.load();
        LogIn logIn = fxmlLoader.getController();

        FXMLLoader fLoader = new FXMLLoader(getClass().getResource("/mainPage.fxml"));
        Parent parent = fLoader.load();

        MainPage mainPage = fLoader.getController();
        mainPage.setProxy(server);
        mainPage.setService(service);
//
//
//        FXMLLoader fLoaderEmployee = new FXMLLoader(getClass().getResource("/employeeArrivalPage.fxml"));
//        Parent parentEmployee = fLoaderEmployee.load();
//        EmployeeArrivalPage mainPageEmployee = fLoaderEmployee.getController();
//        mainPageEmployee.setProxy(server);
//        mainPageEmployee.setService(service);
//
//        FXMLLoader fLoaderEmployeeMain = new FXMLLoader(getClass().getResource("/employeeMainPage.fxml"));
//        Parent parentEmployeeMain = fLoaderEmployeeMain.load();
//        EmployeeMainPage mainPageEmployeeMain = fLoaderEmployeeMain.getController();
//        mainPageEmployeeMain.setProxy(server);
//        mainPageEmployeeMain.setService(service);
//
        logIn.setStage(stage);
        logIn.setService(service);
        logIn.setProxy(server);
        logIn.setClient(mainPage);
        logIn.setParent(parent);

        Scene scene = new Scene(loader, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}