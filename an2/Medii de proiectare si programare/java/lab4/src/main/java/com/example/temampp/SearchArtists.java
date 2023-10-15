package com.example.temampp;

import com.example.temampp.domains.Show;
import com.example.temampp.repositoryes.ArtistRepo;
import com.example.temampp.repositoryes.BuyerRepo;
import com.example.temampp.repositoryes.EmployeeRepo;
import com.example.temampp.repositoryes.ShowRepository;
import com.example.temampp.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class SearchArtists {
    Service service = new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties()));

    private final ObservableList<Show> observableList = FXCollections.observableArrayList();
    @FXML
    private TableView<Show> tableView;

    @FXML
    private TableColumn<Show, String> stageName;

    @FXML
    private TableColumn<Show, LocalDate> date;

    @FXML
    TableColumn<Show, String> place;

    @FXML
    TableColumn<Show, Integer> ticketsAvailable;

    @FXML
    TableColumn<Show, Integer> ticketsSold;

    @FXML
    DatePicker datePicker;

    Stage stage = new Stage();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        stageName.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        place.setCellValueFactory(new PropertyValueFactory<>("placeOfShow"));
        ticketsAvailable.setCellValueFactory(new PropertyValueFactory<>("noTicketsAvailable"));
        ticketsSold.setCellValueFactory(new PropertyValueFactory<>("noTicketsSold"));
        tableView.setItems(observableList);
    }

    public void onSellPush(ActionEvent actionEvent) throws IOException {
        Show show = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sellTickets.fxml"));
        Parent parent = fxmlLoader.load();
        SellTickets sellTickets = fxmlLoader.getController();
        sellTickets.setLabel(show.getShowName());
        Scene scene = new Scene(parent, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void onSearchPush(ActionEvent actionEvent) {
        observableList.setAll(service.searchArtistByDate(datePicker.getValue()));
        tableView.setItems(observableList);
    }

    public void onBackPush(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent loader = fxmlLoader.load();
        MainPage mainPage = fxmlLoader.getController();
        Scene scene = new Scene(loader, 600, 400);
        mainPage.setService(new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties())));
        mainPage.setStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
