package com.example.gui;

import com.example.*;
import com.example.repositoryes.ArtistRepo;
import com.example.repositoryes.BuyerRepo;
import com.example.repositoryes.EmployeeRepo;
import com.example.repositoryes.ShowRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class MainPagePane implements IServiceObserver {
    private Service service = new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties()));

    @FXML
    Label label1;
    private ObservableList<Show> observableList = FXCollections.observableArrayList();
    @FXML
    public TableView<Show> tableView;

    @FXML
    public TableColumn<Show, String> stageName;

    @FXML
    public TableColumn<Show, LocalDate> date;

    @FXML
    public TableColumn<Show, String> place;

    @FXML
    public TableColumn<Show, Integer> ticketsAvailable;

    @FXML
    public TableColumn<Show, Integer> ticketsSold;

    private Stage stage = new Stage();
    public IService serviceProxy;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField ticketsTF;

    public Integer selectedIndex;

    public EmployeeAtOffice employeeAtOffice;
    private Integer id;

    private Stage logInStage;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab mainPageTab;

    @FXML
    private Tab sellTicketsTab;

    @FXML
    DatePicker datePicker;

    public void setProxy(IService serviceProxy) {
        this.serviceProxy = serviceProxy;
    }

    public void setShows() {
        List<Show> showList = service.getAllShows(employeeAtOffice);
        observableList.setAll(showList);
    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLogInStage(Stage logInStage) {
        this.logInStage = logInStage;
    }

    public void setEmployeeAtOffice(Integer id) {
        this.id = id;
    }

    @FXML
    public void initialize() throws ChatException {

        sellTicketsTab.setDisable(true);

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null && newTab.getId().equals("tripsTab")) {
                sellTicketsTab.setDisable(true);
            }
        });
        stageName.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        place.setCellValueFactory(new PropertyValueFactory<>("placeOfShow"));
        ticketsAvailable.setCellValueFactory(new PropertyValueFactory<>("noTicketsAvailable"));
        ticketsSold.setCellValueFactory(new PropertyValueFactory<>("noTicketsSold"));
//        observableList.setAll(serviceProxy.getAllShows(employeeAtOffice));
        tableView.setItems(observableList);
    }


    public void onSellTPush(ActionEvent actionEvent) {
        try {
            String name = nameTF.getText();
            Integer noOfTickets = Integer.parseInt(ticketsTF.getText());
            Show sho1w = tableView.getSelectionModel().getSelectedItem();

            String showName = sho1w.getShowName();
            Buyers buyers = new Buyers(name, noOfTickets, showName);
            serviceProxy.sellTickets(showName, name, noOfTickets);

            Show show = service.findOneShow(buyers.getShowName());
            show.setNoTicketsSold(show.getNoTicketsSold() + buyers.getNoTicketsBought());
            show.setNoTicketsAvailable(show.getNoTicketsAvailable() - buyers.getNoTicketsBought());
            service.updateShow(show);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ticket sold!");
            alert.setHeaderText(buyers.getBuyerName() + '\n' + buyers.getShowName() + '\n' + buyers.getNoTicketsBought());
            alert.showAndWait();

            tabPane.getSelectionModel().select(mainPageTab);
            sellTicketsTab.setDisable(true);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Sell error");
            alert.setHeaderText(ex.getMessage());
        }
    }

    @FXML
    public void onMouseClicked() {
        sellTicketsTab.setDisable(false);
        tabPane.getSelectionModel().select(sellTicketsTab);
        selectedIndex = tableView.getSelectionModel().getSelectedIndex();
    }

    @Override
    public void ticketSold(Buyers buyers) {
        Show show = service.findOneShow(buyers.getShowName());
        Platform.runLater(() -> {
            try {
                System.out.println("acum e in main");
                observableList.setAll(serviceProxy.getAllShows(employeeAtOffice));
            } catch (ChatException e) {
                throw new RuntimeException(e);
            }
            tableView.setItems(observableList);
            tableView.refresh();
            if (selectedIndex != null) {
                tableView.getSelectionModel().select(selectedIndex);
            }
            System.out.println(tableView.getItems());
        });
    }

    public void setService(Service service) {
        this.service = service;
    }

    void logIn(String email, String password) throws Exception {
        EmployeeAtOffice employeeAtOffice = service.findEmployeeByEmailAndPassword(email, password);
        serviceProxy.logInEmployee(email, password, this);
        this.employeeAtOffice = employeeAtOffice;
    }


    public void onLogOutPush(ActionEvent actionEvent) throws IOException {
        try {
            stage.close();
            logInStage.show();
            serviceProxy.logOut(employeeAtOffice);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void onSearchPush(ActionEvent actionEvent) {
        List<Show> list = service.searchArtistByDate(datePicker.getValue());
        if (list.size() > 0) {
            observableList.setAll(list);
            tableView.setItems(observableList);
            datePicker.setValue(null);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search error");
            alert.setHeaderText("No show in that date!");
            alert.show();
        }
    }

    public void initModel() {
        try {
            List<Show> list = serviceProxy.getAllShows(employeeAtOffice);
            tableView.getItems().clear();
            for (var i : list) {
                tableView.getItems().add(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
