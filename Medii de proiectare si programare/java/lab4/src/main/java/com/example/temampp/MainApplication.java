package com.example.temampp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        Parent loader = fxmlLoader.load();
        LogIn logIn = fxmlLoader.getController();
        logIn.setStage(stage);
        Scene scene = new Scene(loader, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
//        Properties properties = new Properties();
//        EmployeeRepo employeeRepo = new EmployeeRepo(properties);
//        System.out.println(employeeRepo.findOne(1));
//        employeeRepo.add(new EmployeeAtOffice(2, "djshfs", "dsjh@yahoo.com", "hffaf"));
//        System.out.println(employeeRepo.findAll());
//        employeeRepo.delete(2);
//
//        ArtistRepo artistRepo = new ArtistRepo(properties);
//        artistRepo.add(new Artist("sdsd", "sjkdgfs", 44));
//        System.out.println(artistRepo.findAll());
//        artistRepo.delete("sjkdgfs");
//
//        System.out.println(showRepository.findAll());
//        showRepository.delete("sidug");
//
//        BuyerRepo buyerRepo = new BuyerRepo(properties);
//
//        buyerRepo.sellTicketsToShow("sidug", "jfhguis", 44);
//    }
    }
}