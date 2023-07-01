package com.example.temampp;

import com.example.temampp.domains.Show;
import com.example.temampp.events.ShowChangeEvent;
import com.example.temampp.observer.Observer;
import com.example.temampp.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class MainPage implements Observer<ShowChangeEvent> {
    Service service;

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

    Integer id = 0;

    Stage stage = new Stage();

    public void setEmployeeName(Integer id) {
        this.id = id;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        stageName.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        place.setCellValueFactory(new PropertyValueFactory<>("placeOfShow"));
        ticketsAvailable.setCellValueFactory(new PropertyValueFactory<>("noTicketsAvailable"));
        ticketsSold.setCellValueFactory(new PropertyValueFactory<>("noTicketsSold"));
//        observableList.setAll(service.getAllShows());
        tableView.setItems(observableList);
    }

    public void onSearchPush(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("searchArtists.fxml"));
        Parent parent = fxmlLoader.load();
        SearchArtists searchArtists = fxmlLoader.getController();
        Scene scene = new Scene(parent, 600, 400);
        searchArtists.setStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void onSellPush(ActionEvent actionEvent) throws IOException {
        Show show = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sellTickets.fxml"));
        Parent parent = fxmlLoader.load();
        SellTickets sellTickets = fxmlLoader.getController();
        sellTickets.setLabel(show.getShowName());
        Scene scene = new Scene(parent, 600, 400);
        sellTickets.setStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void setService(Service service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        observableList.setAll(service.getAllShows());
        tableView.setItems(observableList);
    }

    @Override
    public void update(ShowChangeEvent showChangeEvent) {
        initModel();
    }

    public void onLogOutPush(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        Parent loader = fxmlLoader.load();
        LogIn logIn = fxmlLoader.getController();
        logIn.setStage(stage);
        Scene scene = new Scene(loader, 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}