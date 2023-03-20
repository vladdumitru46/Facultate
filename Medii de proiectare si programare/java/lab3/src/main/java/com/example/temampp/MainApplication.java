package com.example.temampp;

import com.example.temampp.domains.Artist;
import com.example.temampp.domains.EmployeeAtOffice;
import com.example.temampp.domains.Show;
import com.example.temampp.repositoryes.ArtistRepo;
import com.example.temampp.repositoryes.BuyerRepo;
import com.example.temampp.repositoryes.EmployeeRepo;
import com.example.temampp.repositoryes.ShowRepository;

import java.time.LocalDate;
import java.util.Properties;

public class MainApplication {// extends Application {

    //@Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }

    public static void main(String[] args) {
//        launch();
        Properties properties = new Properties();
        EmployeeRepo employeeRepo = new EmployeeRepo(properties);
        System.out.println(employeeRepo.findOne(1));
        employeeRepo.add(new EmployeeAtOffice(2, "djshfs", "dsjh@yahoo.com", "hffaf"));
        System.out.println(employeeRepo.findAll());
        employeeRepo.delete(2);

        ArtistRepo artistRepo = new ArtistRepo(properties);
        artistRepo.add(new Artist("sdsd", "sjkdgfs", 44));
        System.out.println(artistRepo.findAll());
        artistRepo.delete("sjkdgfs");

        ShowRepository showRepository = new ShowRepository(properties);
        showRepository.add(new Show("sidug", "sjkdgfs", LocalDate.now(), "jegef", 341234, 33));
        System.out.println(showRepository.findAll());
        showRepository.delete("sidug");

        BuyerRepo buyerRepo = new BuyerRepo(properties);

        buyerRepo.sellTicketsToShow("sidug", "jfhguis", 44);
    }
}