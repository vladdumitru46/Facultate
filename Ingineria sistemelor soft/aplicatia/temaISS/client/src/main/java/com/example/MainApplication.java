package com.example;

import com.example.objectProtocol.ObjectProxy;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class MainApplication extends Application {
    Stage stage;
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
        Service service = new Service(new RepoBoss(), new RepoEmployee(), new RepoTask(), new RepoTaskOfEmployee(), new RepoEmployeeLogInTime());


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/logIn.fxml"));
        Parent loader = (Parent) fxmlLoader.load();
        LogIn logIn = fxmlLoader.getController();

        FXMLLoader fLoader = new FXMLLoader(getClass().getResource("/bossMainPage.fxml"));
        Parent parent = fLoader.load();

        BossMainPage mainPage = fLoader.<BossMainPage>getController();
        mainPage.setProxy(server);
        mainPage.setService(service);


        FXMLLoader fLoaderEmployee = new FXMLLoader(getClass().getResource("/employeeArrivalPage.fxml"));
        Parent parentEmployee = fLoaderEmployee.load();
        EmployeeArrivalPage mainPageEmployee = fLoaderEmployee.<EmployeeArrivalPage>getController();
        mainPageEmployee.setProxy(server);
        mainPageEmployee.setService(service);

        logIn.setStage(stage);
        logIn.setProxy(server);
        logIn.setClient(mainPage);
        logIn.setClientEmployee(mainPageEmployee);
        logIn.setParent(parent);
        logIn.setParentEmployee(parentEmployee);

        Scene scene = new Scene(loader, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}